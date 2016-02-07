
package se.signa.cdrextract.commons;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import se.signa.cdrextract.db.DBHelper;
import se.signa.cdrextract.writer.ExcelWriter;

/**
 *
 * @author VGajja
 */
public class RequestHandler {
	final static Logger logger = Logger.getLogger(RequestHandler.class);
	public static void loadProps(){
		String configFileName =  "tool.properties";
		Properties props = new Properties();
		InputStream input = null;
		try {
			if(configFileName != null){
				input  = new FileInputStream(configFileName);
			}
			props.load(input);
			ApplicationProperties.getInstance().setProperties(props);
		} catch (FileNotFoundException ex) {
			logger.error(ex);
			return;
		} catch (IOException ex) {
			logger.error(ex);
			return;
		}
	}

	public Response processRequest(RequestDetail reqDetail){
		logger.info("loading tool properties.");
		loadProps();
		Response response = new Response();
		ResultSet results = null;
		long startTime = System.currentTimeMillis();
		try{
			logger.info("getting DBConnection.");
			DBHelper dh = new DBHelper();
			logger.info("Fetching results.");
			results = dh.fetchResults(reqDetail);
			logger.info("Writing to xls file.");
			String outputFile = writeCDRs(results);
			logger.info("Request completed in " + (System.currentTimeMillis() - startTime) + "mSecs.");
			response.setStatus(1);
			response.setOutputFile(outputFile);
		}catch(Exception e){
			logger.error(e);
			response.setStatus(-1);
		}
		return response;
	}

	private String writeCDRs(ResultSet result) throws Exception{
		if(result == null){
			logger.error("Not a valid resultSet...!");
			return null;
		}
		String opFile = null;
		int sheetNumber = 1;
		String outputDir = ApplicationProperties.getInstance().getPropertyValue(Constants.PROP_OUTPUT_DIR);
		ExcelWriter wtr = new ExcelWriter(outputDir);
		HSSFWorkbook workbook = wtr.getWorkBook();
		Sheet currentSheet = wtr.addAndGetNextSheet(workbook, sheetNumber);
		String sheetSize = ApplicationProperties.getInstance().getPropertyValue(Constants.PROP_OUTPUT_SHEET_SIZE);
		
		int sheetSizeValue = 65500;
		if(sheetSize != null && !sheetSize.isEmpty()){
			sheetSizeValue = Integer.parseInt(sheetSize);
		}
		if(sheetSizeValue > 65500)
			sheetSizeValue = 65500;
		int rowIndex = 0;
		try {
			while(result.next()){
				CallDetailRecord cdr = getCallDetailRecord(result);
				if(rowIndex == sheetSizeValue){
					rowIndex = 0;
					sheetNumber++;
					logger.info("Captured " + (sheetSizeValue * sheetNumber) + " CDRs so far..");
					logger.info("Adding sheet " + sheetNumber + " to workbook.");
					currentSheet = wtr.addAndGetNextSheet(workbook, sheetNumber);
				}
				wtr.writeRow(currentSheet, cdr, rowIndex);
				rowIndex++;
			}
			if(rowIndex > 0 || sheetNumber > 1){
				opFile = wtr.saveWorkbook();
			}
		} catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}
		return opFile;
	}

	private CallDetailRecord getCallDetailRecord(ResultSet rs){
		CallDetailRecord cdr = new CallDetailRecord();
		try{
			String an = rs.getString(Constants.CDR_TABLE_COLUMN_ANUMBER);
			String bn = rs.getString(Constants.CDR_TABLE_COLUMN_BNUMBER);
			// TODO set all select columns in this object.
			cdr.setaNumber(an);
			cdr.setbNumber(bn);
		}catch(SQLException e){
			logger.error(e);
		}
		return cdr;
	}
}
