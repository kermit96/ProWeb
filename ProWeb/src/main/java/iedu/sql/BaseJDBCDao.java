package iedu.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;

import org.apache.commons.dbcp2.BasicDataSource;

import iedu.config.Globalconfig;


public class BaseJDBCDao {
	protected BasicDataSource ds = new BasicDataSource();
	private  Connection con = null;

	protected HashSet<Statement> statements = new HashSet<Statement>();
	protected HashSet<PreparedStatement>  prestatements = new HashSet<PreparedStatement>();
	protected HashSet<ResultSet>  resultsets = new HashSet<ResultSet>();

	 public BaseJDBCDao(String drivename,String  url,String userid,String password )  throws  Exception
	{
		this(drivename,url,userid,password,4);	
	}
	 
	 
	 public BaseJDBCDao(DBTYPE dbtype, String  url,String userid,String password )
	 {
		    
	          switch(dbtype)
	          {
	             case ORACLE_TYPE:
	            	    break;
	             case  MSSQL_TYPE:
	            	     break;
	             case MYSQL_TYPE:
	            	    break;
	          }
		 
	 }
	
	public BaseJDBCDao(String drivename,String  url,String userid,String password,int initnum )  throws  Exception
	{
		try {					
			ds.setDriverClassName(drivename);
			ds.setUrl(url);
			ds.setUsername(userid);
			ds.setPassword(password);			
			ds.setInitialSize(initnum);

		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			throw ex;
		}

		try { 

			con =   getcon();
		} catch (Exception ex) {
			throw ex;		
		}
	}
	
	
	public void setAutoCommit(boolean autoCommit)
	{
	     	
		try {
			getcon().setAutoCommit(autoCommit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Commit()
	{
	     	
		try {
			getcon().commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public ResultSet GetResultStat(String sql) throws Exception
	{

		Statement  stat  =   getSTMT();

		ResultSet result= stat.executeQuery(sql);

		close(stat);

		resultsets.add(result);

		return result;
		
	}

	// result  
	

	/// Connection 을 얻어 온다.
	public Connection getcon() throws Exception   {

		if (con == null) {

			try { 

				con = ds.getConnection();
			} catch (Exception ex) {
				throw ex;		
			}

		}

		return con;
	}

	/// Statement 을 얻어 온다.
	public Statement getSTMT() throws Exception {

		Statement smt = null;
		try {
			smt = con.createStatement();			
			statements.add(smt);						
		} catch(Exception ex) {
			throw ex;					
		}

		return smt;
	}


	public Statement getSTMTForUpdate() throws Exception {

		Statement smt = null;
		try {
			smt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE );			
			statements.add(smt);						
		} catch(Exception ex) {
			throw ex;					
		}

		return smt;
	}



	

/// PreparedStatement 을 얻어 온다.
	public PreparedStatement getPrepare(String sql) throws Exception {

		PreparedStatement smt = null;

		try {
			smt = con.prepareStatement(sql);
			this.prestatements.add(smt);			
		} catch(Exception ex) {
			throw ex;					
		}

		return smt;

	}
	

	public PreparedStatement getPrepareForUpdate(String sql) throws Exception {

		
		PreparedStatement smt = null;
		
		try {
			smt = con.prepareStatement(sql,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE );
			this.prestatements.add(smt);			
		} catch(Exception ex) {
			throw ex;					
		}

		return smt;

	}
	
	
	

	public  void closeAll()
	{

		for(ResultSet set  :this.resultsets) {
			try { 
				set.close();
			} catch (Exception ex) {
				
			}
		}

		resultsets.clear();

		for(Statement stat: statements) {
			try {
				stat.close();
			} catch(Exception ex) {


			}
		}
		statements.clear();


		for(PreparedStatement stat: this.prestatements) {
			try {
				stat.close();
			} catch(Exception ex) {


			}
		}

		prestatements.clear();

		close(con);
		con = null;

	}




	public  void close(Object obj) {		
		try {
			if (obj instanceof ResultSet ) {

				((ResultSet)(obj)).close();
				resultsets.remove(obj);							
			}

			if (obj instanceof Connection ) {

				((Connection)(obj)).close();
				if (obj== this.con) 
					con = null;
			}


			if (obj instanceof PreparedStatement ) {
				((PreparedStatement)(obj)).close();
				this.prestatements.remove(obj);
			}

			if (obj instanceof Statement ) {

				((Statement)(obj)).close();
				this.statements.remove(obj);
			}

		} catch (Exception ex) {

			
		}

	}

	
	
	
	public static  BaseJDBCDao GetjdbcDao(DBTYPE dbtype,String host,  int port, String  dbname,String userid,String password) throws Exception
	{

		BaseJDBCDao dao = null;
		

		if (dbtype==DBTYPE.ORACLE_TYPE) 
		{
					
			if (port ==0) 
				port = 1521;
			dao = new OracleJDBCDao(userid,password,host,port,dbname  ) ;
		}

		if (dbtype==DBTYPE.MSSQL_TYPE) 
		{
		
			if (port ==0) 
				port = 1433;
			dao = new MssqlSqlJDBCDao(userid,password,host,port,dbname );

		}

		if (dbtype==DBTYPE.MYSQL_TYPE) 
		{			
			if (port ==0) 
				port = 3306;

			dao = new MySqlJDBCDao(userid,password,host,port,dbname );
		}		                  

		return dao;
	}
	
}