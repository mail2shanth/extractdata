package se.signa.cdrextract.writer;

import java.io.FileOutputStream;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import se.signa.cdrextract.commons.CallDetailRecord;

/**
 *
 * @author VGajja
 */
public class ExcelWriter {

	private  String outputFilePath;
	private  HSSFWorkbook workbook = null;
	final static Logger logger = Logger.getLogger(ExcelWriter.class);

	public ExcelWriter(){
		this.workbook = new HSSFWorkbook();
	}
	public ExcelWriter(String fileName){
		this.outputFilePath = fileName;
		this.workbook = new HSSFWorkbook();
	}

	public HSSFWorkbook getWorkBook(){
		return this.workbook;
	}

	public Sheet addAndGetNextSheet(HSSFWorkbook workbook, int sheetNumber){
		Sheet cdrSheet = workbook.createSheet("CDR-Sheet-"+sheetNumber);
		return cdrSheet;
	}

	public void writeRow(Sheet sheet, CallDetailRecord cdr, int rowIndex){
		Row row = sheet.createRow(rowIndex++);	
		int cellIndex = 0;
		row.createCell(cellIndex++).setCellValue(cdr.getANumber());
		row.createCell(cellIndex++).setCellValue(cdr.getBNumber());
	}

	public String saveWorkbook() throws Exception{
		FileOutputStream fos = null;
		String outputFileName = outputFilePath + UUID.randomUUID().toString() + ".xls";
		try {
			fos = new FileOutputStream(outputFileName);
			workbook.write(fos);
			return outputFileName;
		}catch (Exception e) {
			logger.error(e);
			throw new Exception(e);
		}finally{
			try{
				if(fos != null)
					fos.close();
			}catch(Exception e){
				logger.error(e);
			}
		}
	}
}
