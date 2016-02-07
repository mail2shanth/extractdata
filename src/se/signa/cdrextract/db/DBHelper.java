/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.signa.cdrextract.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import se.signa.cdrextract.commons.ApplicationProperties;
import se.signa.cdrextract.commons.CDRFieldUtils;
import se.signa.cdrextract.commons.Constants;
import se.signa.cdrextract.commons.RequestDetail;

/**
 *
 * @author VGajja
 */
public class DBHelper {

	public Connection getDbConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		Connection con=DriverManager.
		getConnection( "jdbc:oracle:thin:@" + ApplicationProperties.getInstance().getPropertyValue(Constants.PROP_DB_CONNECTION_URL),
				ApplicationProperties.getInstance().getPropertyValue(Constants.PROP_DB_NAME),
				ApplicationProperties.getInstance().getPropertyValue(Constants.PROP_DB_PASSWORD));
		return con;
	}

	public ResultSet fetchResults(RequestDetail reqDetail) throws Exception{
		Connection dbConn = null;
		Statement stmt=null;
		String query =	getQuery(reqDetail);
		ResultSet rs = null;
		dbConn  = getDbConnection();
		stmt = dbConn.createStatement();
		rs = stmt.executeQuery(query);
		return rs;
	}
	
	private String getQuery(RequestDetail reqDetail){
		String tableName = ApplicationProperties.getInstance().getPropertyValue(Constants.PROP_CDRTABLE_NAME);
		StringBuilder query = new StringBuilder("select * from " + tableName) ;
		List<String> conditions = new LinkedList<String>();
		if(!reqDetail.isEmptyRequest()){
			query.append(" WHERE ");
		}
		if(reqDetail.getFromDateTime() != null){
			String condition = "to_char(" + Constants.CDR_TABLE_COLUMN_EVT_DTTM + ",'YYYY-MM-DD')" + 
			" >= '" +  new Date(reqDetail.getFromDateTime().getTime()) + "'";
			conditions.add(condition);
		}
		if(reqDetail.getToDateTime() != null){
			String condition = "to_char(" + Constants.CDR_TABLE_COLUMN_EVT_DTTM + ",'YYYY-MM-DD')" + 
			" <= '" + new Date(reqDetail.getToDateTime().getTime()) +"'";
			conditions.add(condition);
		}
		if(reqDetail.getAccName() != null && !reqDetail.getAccName().isEmpty()){
			String condition = Constants.CDR_TABLE_COLUMN_ACC_NAME + " = " + warpInQuotes(reqDetail.getAccName()); 
			conditions.add(condition);
		}
		if(reqDetail.getBndName() != null && !reqDetail.getBndName().isEmpty()){
			String condition = Constants.CDR_TABLE_COLUMN_BND_NAME + " = " + warpInQuotes(reqDetail.getBndName());
			conditions.add(condition);
		}
		if(reqDetail.getaNumbers() != null && !reqDetail.getaNumbers().isEmpty()){
			String condition = Constants.CDR_TABLE_COLUMN_ANUMBER + " IN ( " + CDRFieldUtils.getListAsString(reqDetail.getaNumbers()) + " )";
			conditions.add(condition);
		}
		if(reqDetail.getbNumbers() != null && !reqDetail.getbNumbers().isEmpty()){
			String condition = Constants.CDR_TABLE_COLUMN_BNUMBER + " IN ( " +  CDRFieldUtils.getListAsString(reqDetail.getbNumbers()) + " )";
			conditions.add(condition);
		}
		if(reqDetail.getBillProfile() != null && !reqDetail.getBillProfile().isEmpty()){
			String condition = Constants.CDR_TABLE_COLUMN_BILL_PROFILE + " = " + warpInQuotes(reqDetail.getBillProfile());
			conditions.add(condition);
		}
		if(reqDetail.getCauseCode() != null && !reqDetail.getCauseCode().isEmpty()){
			String condition = Constants.CDR_TABLE_COLUMN_CAUSE_CODE + " = " + warpInQuotes(reqDetail.getCauseCode());
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
	
	private String warpInQuotes(String str){
		StringBuilder sb = new StringBuilder();
		sb.append("'");
		sb.append(str);
		sb.append("'");
		return sb.toString();
	}
}
