package com.wing.mainApp.config;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import iedu.config.Globalconfig;
import iedu.sql.DBTYPE;


@Configuration
public class Config {
		
	   @Bean
	    public BasicDataSource dataSource() {		   		   
		   Globalconfig config =  new Globalconfig();		   		
		   BasicDataSource dataSource = new BasicDataSource();
		   
		   String classname = "";
		   String url = "";
		   
		   String userid ="";
		   String password ="";
		   try {
			    userid = config.getUserid();			   
		   } catch(Exception ex) { 			   			   
		   }
		   		   
		   try {
			   password = config.getPassword();
			   
		   } catch(Exception ex) { 
			   			   
		   }
		   
		  
		   
		   int port = config.getPort();
		   
		   switch(DBTYPE.fromInt(config.getDbtype())) 
		   {
		     case ORACLE_TYPE:
		    	  classname = "oracle.jdbc.driver.OracleDriver";
		    	  if (port == 0) {
		    		 port=1521;  
		    		 
		    	  }
		    	  url = String.format("jdbc:oracle:thin:@%s:%d:%s", config.getHost(),port,config.getDbname()) ;
			   break;  
			   
		     case MSSQL_TYPE:
		     	  classname = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		     	 if (port == 0) {
		    		  
		    		  port =1433;
		    	  }
	    	     url = "jdbc:sqlserver://"+config.getHost()+":"+port+";databaseName="+config.getDbname();
				 break;   
				 
		     case  MYSQL_TYPE:
		    	 		  
		   	     classname = "com.mysql.jdbc.Driver";
	    	     if (port == 0) {		    		  
	    	    	 port =3306;
		    	  }
	    	     url = "jdbc:mysql://"+config.getHost()+":"+port+"/"+config.getDbname()+"?useSSL=false"	;	
			    break;
				 
		   }
		   
		   
	        dataSource.setDriverClassName(classname);
	        dataSource.setUsername(userid);
	        dataSource.setUrl(url);
	        dataSource.setPassword(password);
	        
	        return dataSource;
	    }
	     
	    @Bean
	    public DataSourceTransactionManager transactionManager()
	    {
	        return new DataSourceTransactionManager(dataSource());
	    }
	     


	 @Bean
	 // public SqlSessionFactory sqlSessionFatory(DataSource datasource,ApplicationContext applicationContext) throws Exception{
	 public SqlSessionFactory sqlSessionFatory(BasicDataSource datasource) throws Exception{
	  		 
	  SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
	  sqlSessionFactory.setDataSource(datasource);
	//  sqlSessionFactory.setMapperLocations(mapperLocations);
	  sqlSessionFactory.setConfigLocation(new ClassPathResource("/com/wing/mainApp/myBatis/myBatis-config.xml"));
	  
	  sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/com/wing/mainApp/sql/*.xml"));
	  //configLocation 	  	 
	  	   
	  org.apache.ibatis.mapping.VendorDatabaseIdProvider venderid = new org.apache.ibatis.mapping.VendorDatabaseIdProvider();
	    
	   Properties  prof = new Properties();
	   prof.setProperty("SQL Server", "sqlserver");
	   prof.setProperty("DB2", "db2");
	   prof.setProperty("Oracle", "oracle");
	   prof.setProperty("MySQL", "mysql");	  
	   venderid.setProperties(prof);	  
 	   sqlSessionFactory.setDatabaseIdProvider(venderid);
 	   try {
 		  if (datasource.getUsername().isEmpty()) 
 			  return null;
 		   SqlSessionFactory   factory= sqlSessionFactory.getObject();
	   //  return (SqlSessionFactory) ;
	      return factory;
 	   } catch (Exception ex) { 		   
 		   return null;
 	   }
	   	   
	 } 
	
	 @Bean
	 public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		 try {
	    return new SqlSessionTemplate(sqlSessionFactory);
	    
		 } catch(Exception ex ) {
			 
			 return null;
		 }
	 }
	 	 	 
}
