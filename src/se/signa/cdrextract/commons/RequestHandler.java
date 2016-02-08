
package se.signa.cdrextract.commons;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
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
		
		int diffInDays = (int)( (reqDetail.getToDate().getTime() - 
				reqDetail.getFromDate().getTime()) / (1000 * 60 * 60 * 24) ) + 1;
		
		String maxDatesdiffInDays = ApplicationProperties.getInstance().getPropertyValue(Constants.PROP_MAX_DATEDIFF_ALLOWED);
		int maxDatesdiffInDaysValue = 7;
		try{
			maxDatesdiffInDaysValue = Integer.parseInt(maxDatesdiffInDays);
		}catch(Exception e){
			
		}
		if(diffInDays > maxDatesdiffInDaysValue){
			response.setStatus(-2);
			return response;
		}
		
		
		Date fromDt = reqDetail.getFromDate();
		Date toDt = reqDetail.getToDate();
		List<Date> dates = new ArrayList<Date>();
	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(fromDt);
	    while (calendar.getTime().before(toDt)){
	        Date result = calendar.getTime();
	        dates.add(result);
	        calendar.add(Calendar.DATE, 1);
	    }
		
		ResultSet results = null;
		long startTime = System.currentTimeMillis();
		DBHelper dh = new DBHelper();
		Connection connecton = null;
		List<String> opFiles = new ArrayList<String>();
		try{
			connecton = dh.getDbConnection();
			for(Date date : dates){
				DateFormat df = new SimpleDateFormat("ddMMyy");
				String dateSuffix = df.format(date);
				logger.info("Fetching results for " + dateSuffix);
				results = dh.fetchResults(reqDetail, connecton, dateSuffix);
				logger.info("Writing to file for " + dateSuffix);
				String outputFile = writeCDRs(results, dateSuffix, reqDetail.getRequestName());
				logger.info("Saved results for " + dateSuffix);
				opFiles.add(outputFile);
			}
			logger.info("Request completed in " + (System.currentTimeMillis() - startTime) + "mSecs.");
			response.setStatus(1);
			response.setOutputFiles(opFiles);
		}catch(Exception e){
			logger.error(e);
			response.setStatus(-1);
		}finally{
			if(connecton != null){
				try{
					connecton.close();
				}catch(Exception e){
				}
			}
		}
		return response;
	}

	private String writeCDRs(ResultSet result, String dateSuffix, String reqName) throws Exception{
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
				opFile = wtr.saveWorkbook(dateSuffix, reqName);
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
