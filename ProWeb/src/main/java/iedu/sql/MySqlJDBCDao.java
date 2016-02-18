package iedu.sql;

public class MySqlJDBCDao extends BaseJDBCDao {
	
	public MySqlJDBCDao() throws  Exception  {
		super("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/mysql2?useSSL=false",
				"mysql2","mysql2"
				);			
	}	
	
	public MySqlJDBCDao(int initnum) throws  Exception  {
		super("com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/mysql2?useSSL=false",
				"mysql2","mysql2",initnum
				);			
	}
	
	public  MySqlJDBCDao(String userid,String password, String host,int port,String dbname,  int initnum) throws  Exception  {
		super("com.mysql.jdbc.Driver","jdbc:mysql://"+host+":"+port+"/"+dbname+"?useSSL=false",
				userid,password,initnum
				);			
	} 
		
	public MySqlJDBCDao(String userid,String password, String host,int port,String dbname) throws  Exception  {
		super("com.mysql.jdbc.Driver","jdbc:mysql://"+host+":"+port+"/"+dbname+"?useSSL=false",
				userid,password,4
				);			
	}
	
	public MySqlJDBCDao(String userid,String password, String host,String dbname) throws  Exception  {
		super("com.mysql.jdbc.Driver","jdbc:mysql://"+host+":"+3306+"/"+dbname+"?useSSL=false",
				userid,password,4
				);			
	}	

	public MySqlJDBCDao(String userid,String password, String host,String dbname,int initnum) throws  Exception  {
		super("com.mysql.jdbc.Driver","jdbc:mysql://"+host+":"+3306+"/"+dbname+"?useSSL=false",
				userid,password,initnum
				);			
	}	

	public MySqlJDBCDao(String userid,String password,int initnum) throws  Exception  {
		super("com.mysql.jdbc.Driver","jdbc:mysql://"+"localhost"+":"+3306+"/"+"mysql2"+"?useSSL=false",
				userid,password,initnum
				);			
	}	
	
	
	public MySqlJDBCDao(String userid,String password) throws  Exception  {
		super("com.mysql.jdbc.Driver","jdbc:mysql://"+"localhost"+":"+3306+":"+"mysql2"+"?useSSL=false",
				userid,password,4
				);			
	}

	public MySqlJDBCDao(String url,String userid,String password) throws  Exception  {
		super("com.mysql.jdbc.Driver",url,
				userid,password,4
				);			
	}
	
}
