/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.signa.cdrextract.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import se.signa.cdrextract.commons.ApplicationProperties;
import se.signa.cdrextract.commons.CDRFieldUtils;
import se.signa.cdrextract.commons.Constants;
import se.signa.cdrextract.commons.RequestDetail;

/**
 *
 * @author VGajja
 */
public class DBHelper {

	final static Logger logger = Logger.getLogger(DBHelper.class);
	public Connection getDbConnection() throws Exception{
		// TODO use MSSQL server
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection con=DriverManager.
		getConnection( "jdbc:oracle:thin:@" + ApplicationProperties.getInstance().getPropertyValue(Constants.PROP_DB_CONNECTION_URL),
				ApplicationProperties.getInstance().getPropertyValue(Constants.PROP_DB_NAME),
				ApplicationProperties.getInstance().getPropertyValue(Constants.PROP_DB_PASSWORD));
		return con;
	}

	public ResultSet fetchResults(RequestDetail reqDetail, Connection dbConn, String dateSuffix ) throws Exception{
		Statement stmt=null;
		String query =	getQuery(reqDetail, dateSuffix);
		logger.debug("executing query : " + query);
		ResultSet rs = null;
		stmt = dbConn.createStatement();
		rs = stmt.executeQuery(query);
		return rs;
	}
	
	private String getQuery(RequestDetail reqDetail, String dateSuffix){
		String tableName = ApplicationProperties.getInstance().getPropertyValue(Constants.PROP_CDRTABLE_NAME);
		StringBuilder query = new StringBuilder("select * from " + tableName + "_" + dateSuffix) ;
		List<String> conditions = new LinkedList<String>();
		if(!reqDetail.isEmptyRequest()){
			query.append(" WHERE ");
		}
		
		// TODO use LIKE clause for all these filters
		if(reqDetail.getAccName() != null && !reqDetail.getAccName().isEmpty()){
			List<String> accNames = CDRFieldUtils.getListFromString(reqDetail.getAccName());
			String inClauseAccNames = CDRFieldUtils.getListAsString(accNames);
			String condition = Constants.CDR_TABLE_COLUMN_ACC_NAME + " IN ( " + inClauseAccNames + " ) "; 
			conditions.add(condition);
		}
		if(reqDetail.getBndName() != null && !reqDetail.getBndName().isEmpty()){
			List<String> bndNames = CDRFieldUtils.getListFromString(reqDetail.getBndName());
			String inClauseBndNames = CDRFieldUtils.getListAsString(bndNames);
			String condition = Constants.CDR_TABLE_COLUMN_BND_NAME + " IN ( " + inClauseBndNames + " ) "; 
			conditions.add(condition);
		}
		if(reqDetail.getANumbersString() != null && !reqDetail.getANumbersString().isEmpty()){
			List<String> aNumbers = CDRFieldUtils.getListFromString(reqDetail.getANumbersString());
			String inClauseANumbers = CDRFieldUtils.getListAsString(aNumbers);
			String condition = Constants.CDR_TABLE_COLUMN_ANUMBER + " IN ( " + inClauseANumbers + " ) "; 
			conditions.add(condition);
		}
		if(reqDetail.getBNumbersString() != null && !reqDetail.getBNumbersString().isEmpty()){
			List<String> bNumbers = CDRFieldUtils.getListFromString(reqDetail.getBNumbersString());
			String inClauseBNumbers = CDRFieldUtils.getListAsString(bNumbers);
			String condition = Constants.CDR_TABLE_COLUMN_BNUMBER + " IN ( " + inClauseBNumbers + " ) "; 
			conditions.add(condition);
		}
		if(reqDetail.getBillProfile() != null && !reqDetail.getBillProfile().isEmpty()){
			List<String> billProfles = CDRFieldUtils.getListFromString(reqDetail.getBillProfile());
			String inClauseBillProfles = CDRFieldUtils.getListAsString(billProfles);
			String condition = Constants.CDR_TABLE_COLUMN_BILL_PROFILE + " IN ( " + inClauseBillProfles + " ) "; 
			conditions.add(condition);
		}
		if(reqDetail.getCauseCode() != null && !reqDetail.getCauseCode().isEmpty()){
			List<String> causeCodes = CDRFieldUtils.getListFromString(reqDetail.getCauseCode());
			String inClauseCauseCodes = CDRFieldUtils.getListAsString(causeCodes);
			String condition = Constants.CDR_TABLE_COLUMN_CAUSE_CODE + " IN ( " + inClauseCauseCodes + " ) "; 
			conditions.add(condition);
		}
		if(!conditions.isEmpty()){
			int size = conditions.size();
			int index = 0;
			for(String cond : conditions){
				query.append(cond);
				if(index < size-1){
					query.append(" AND ");
				}
				index++;
			}
		}
		return query.toString();
	}
}
