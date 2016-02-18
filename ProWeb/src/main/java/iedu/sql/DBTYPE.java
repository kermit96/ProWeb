package iedu.sql;
public enum DBTYPE {
	ORACLE_TYPE,MSSQL_TYPE,MYSQL_TYPE;
    public static DBTYPE fromInt(int i) {    	        	
        	return DBTYPE.values()[i];   
    }
}