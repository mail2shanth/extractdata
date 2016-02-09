package se.signa.cdrextract.commons;

/**
*
* @author VGajja
*/
public interface Constants {
	
	String PROP_DB_NAME = "db.name";
	String PROP_DB_USR_NAME = "db.user.name";
	
	String PROP_DB_PASSWORD = "db.password";
	String PROP_DB_CONNECTION_URL = "db.connection.url";
	String PROP_OUTPUT_DIR = "output.directory";
	String PROP_CDRTABLE_NAME = "cdr.tablename";
	String PROP_OUTPUT_SHEET_SIZE = "output.page.size";
	String PROP_MAX_DATEDIFF_ALLOWED = "max.date.diff.allowed";
	//String PROP_MULTI_VALUE_INPUT_DELIMETER = "multi.value.delim";
	
	String MULTI_VALUE_INPUT_DELIMETER = ",";
	
	// TODO Table Column names, set appropriate column names
	String CDR_TABLE_COLUMN_ANUMBER = "anumber";
	String CDR_TABLE_COLUMN_BNUMBER = "bnumber";
	String CDR_TABLE_COLUMN_ACC_NAME = "accname";
	String CDR_TABLE_COLUMN_BND_NAME = "bndname";
	String CDR_TABLE_COLUMN_EVT_DTTM = "eventdate";
	String CDR_TABLE_COLUMN_BILL_PROFILE = "billprofile";
	String CDR_TABLE_COLUMN_CAUSE_CODE = "causecode";

}
