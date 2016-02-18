package iedu.sql;

public class MssqlSqlJDBCDao extends BaseJDBCDao {
	
	public MssqlSqlJDBCDao() throws  Exception  {
		super("com.microsoft.sqlserver.jdbc.SQLServerDriver","jdbc:sqlserver://localhost:1433;databaseName=mytest",
				"mssql","mssql"
				);			
	}
	
	public MssqlSqlJDBCDao(int initnum) throws  Exception  {
		super("com.microsoft.sqlserver.jdbc.SQLServerDriver","jdbc:sqlserver://localhost:1433;databaseName=mytest",
				"mssql","mssql",initnum
				);			
	}
	
	public  MssqlSqlJDBCDao(String userid,String password, String host,int port,String dbname,  int initnum) throws  Exception  {
		super("com.microsoft.sqlserver.jdbc.SQLServerDriver","jdbc:sqlserver://"+host+":"+port+";databaseName="+dbname,
				userid,password,initnum
				);			
	}
		
	public MssqlSqlJDBCDao(String userid,String password, String host,int port,String dbname) throws  Exception  {
		super("com.microsoft.sqlserver.jdbc.SQLServerDriver","jdbc:sqlserver://"+host+":"+port+";databaseName="+dbname,
				userid,password,4
				);			
	}
	
	public MssqlSqlJDBCDao(String userid,String password, String host,String dbname) throws  Exception  {
		super("com.microsoft.sqlserver.jdbc.SQLServerDriver","jdbc:sqlserver://"+host+":"+1433+";databaseName="+dbname,
				userid,password,4
				);			
	}	

	public MssqlSqlJDBCDao(String userid,String password, String host,String dbname,int initnum) throws  Exception  {
		super("com.microsoft.sqlserver.jdbc.SQLServerDriver","jdbc:sqlserver://"+host+":"+1433+";databaseName="+dbname,
				userid,password,initnum
				);			
	}	

	public MssqlSqlJDBCDao(String userid,String password,int initnum) throws  Exception  {
		super("com.microsoft.sqlserver.jdbc.SQLServerDriver","jdbc:sqlserver://"+"localhost"+":"+1433+";databaseName="+"mytest",
				userid,password,initnum
				);			
	}	
	
	
	public MssqlSqlJDBCDao(String userid,String password) throws  Exception  {
		super("com.microsoft.sqlserver.jdbc.SQLServerDriver","jdbc:sqlserver://"+"localhost"+":"+1433+";databaseName="+"mysql2",
				userid,password,4
				);			
	}

	public MssqlSqlJDBCDao(String url,String userid,String password) throws  Exception  {
		super("com.microsoft.sqlserver.jdbc.SQLServerDriver",url,
				userid,password,4
				);			
	}
	
}
