package iedu.sql;

public class OracleJDBCDao extends BaseJDBCDao {
	
	public OracleJDBCDao() throws  Exception  {
		super("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1521:orcl",
				"scott","tiger"
				);			
	}	
	
	public OracleJDBCDao(int initnum) throws  Exception  {
		super("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1521:orcl",
				"scott","tiger",initnum
		 		);			
	}
	
	public OracleJDBCDao(String userid,String password, String host,int port,String dbname,  int initnum) throws  Exception  {
		super("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@"+host+":"+port+":"+dbname,
				userid,password,initnum
				);			
	}
		
	public OracleJDBCDao(String userid,String password, String host,int port,String dbname) throws  Exception  {
		super("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@"+host+":"+port+":"+dbname,
				userid,password,4
				);			
	}
	
	public OracleJDBCDao(String userid,String password, String host,String dbname) throws  Exception  {
		super("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@"+host+":"+1521+":"+dbname,
				userid,password,4
				);			
	}	

	public OracleJDBCDao(String userid,String password, String host,String dbname,int initnum) throws  Exception  {
		super("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@"+host+":"+1521+":"+dbname,
				userid,password,initnum
				);			
	}	

	public OracleJDBCDao(String userid,String password,int initnum) throws  Exception  {
		super("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@"+"localhost"+":"+1521+":"+"orcl",
				userid,password,initnum
				);			
	}	
	
	
	public OracleJDBCDao(String userid,String password) throws  Exception  {
		super("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@"+"localhost"+":"+1521+":"+"orcl",
				userid,password,4
				);			
	}
	
}
