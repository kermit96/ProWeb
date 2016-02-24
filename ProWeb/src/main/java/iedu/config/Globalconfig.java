package iedu.config;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import iedu.sql.DBTYPE;

public class Globalconfig {
    private String dbname;
    private int    dbtype;
    
    private String  userid;
    private String  password;
    private String  host;
    private int    port;
    
    private int    smtpport;
    private String smtphost;
    private String smtpuserid;
    private String smtppassword;
    
    private String  smtpemail;
    
    private String  smtpsender;
    
    private int    encryptedmethod; // 0 : 없음 1: tls 2: ssl   
    
    
    
    public String getSmtpemail() {
		return smtpemail;
	}

	public void setSmtpemail(String smtpemail) {
		this.smtpemail = smtpemail;
		handler.setValue("smtpemail",smtpemail );
		
	}

	public String getSmtpsender() {
		return smtpsender;
	}

	public void setSmtpsender(String smtpsender) {
		this.smtpsender = smtpsender;
		handler.setValue("smtpsender",smtpsender );
	}

	
    
    private String savedir;
    
    /** 
	 * @return the savedir
	 */
	public String getSavedir() {
		return savedir;
	}

	/**
	 * @param savedir the savedir to set
	 */
	public void setSavedir(String savedir) {
		this.savedir = savedir;
		
        File dir = new File(savedir);
        if (dir.exists() == false) 
		  dir.mkdirs();
		
		
		handler.setValue("savedir",savedir );
	}

	ConfigFileHandler handler;
    /**
	 * @return the dbname
	 */
	public String getDbname() {
		return dbname;
	}

	/**
	 * @param dbname the dbname to set
	 */
	public void setDbname(String dbname) {
		this.dbname = dbname;
		handler.setValue("dbname",dbname );
	}

	/**
	 * @return the dbtype
	 */
	public int getDbtype() {
		return dbtype;
	}

	/**
	 * @param dbtype the dbtype to set
	 */
	public void setDbtype(int dbtype) {
		this.dbtype = dbtype;
		handler.setValue("dbtype",dbtype );
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		
		
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
		handler.setValue("userid",userid );
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
				
		try {
			handler.setValue("password",		iedu.util.ase256.AES_Encode(password));
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
		handler.setValue("host",host );
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
		
		handler.setValue("port",port );
	}


	public int getSmtpport() {
		return smtpport;
	}

	public void setSmtpport(int smtpport) {
		this.smtpport = smtpport;
		handler.setValue("smtpport",smtpport );
	}

	public String getSmtphost() {
		return smtphost;
	}

	public void setSmtphost(String smtphost) {
		this.smtphost = smtphost;
		handler.setValue("smtphost",smtphost );
		}

	public String getSmtpuserid() {
		return smtpuserid;
	}

	public void setSmtpuserid(String smtpuserid) {
		this.smtpuserid = smtpuserid;
		handler.setValue("smtpuserid",smtpuserid );
	}

	public String getSmtppassword() {
		return smtppassword;
	}

	public void setSmtppassword(String smtppassword) {
		this.smtppassword = smtppassword;
		handler.setValue("smtppassword",smtppassword );
	}

	public int getEncryptedmethod() {
		return encryptedmethod;
	}

	public void setEncryptedmethod(int encryptedmethod) {
		this.encryptedmethod = encryptedmethod;
		handler.setValue("encryptedmethod",encryptedmethod );
	}

	public ConfigFileHandler getHandler() {
		return handler;
	}

	public void setHandler(ConfigFileHandler handler) {
		this.handler = handler;
	}

	public void Save()
	{
		handler.Save();
		
	}
	
	public Globalconfig(String filename)
	{
		
	    handler = ConfigFileHandler.getConfigFileHandler(filename);
    	
    	host = handler.getValue("host");
		port = 0;
		
		try {
		  port = 	Integer.parseInt( handler.getValue("port"));

		} catch (Exception ex ) {}
		
		dbname =  handler.getValue("dbname");
		userid =  handler.getValue("userid");
				
		if (userid == null)
			userid = "";
		
		 try {
			password =   iedu.util.ase256.AES_Decode( handler.getValue("password"));
			
		} catch (Exception	ex) {
			ex.printStackTrace();
			password = handler.getValue("password");
		}
		
		 try {
		   dbtype = Integer.parseInt( handler.getValue("dbtype"));		
		 } catch (Exception ex) {
			 dbtype =0;
		 }
		 
		
		 
		 try {
			   this.smtpport = Integer.parseInt( handler.getValue("smtpport"));		
			 } catch (Exception ex) {
				 smtpport =0;
		 }
			 
		
 	    this.smtphost = handler.getValue("smtphost");		
       	
 	    this.smtpuserid = handler.getValue("smtpuserid");
 	   
 	    this.smtppassword = handler.getValue("smtppassword");

 	   
 	    this.smtpemail = handler.getValue("smtpemail");
 	  
 	    this.smtpsender= handler.getValue("smtpsender");
 	   
		 
 		 try {
			   this.encryptedmethod = Integer.parseInt( handler.getValue("encryptedmethod"));		
			 } catch (Exception ex) {
				 encryptedmethod =0;
		 }
 	   
		 		 
		 savedir = handler.getValue("savedir");
		 if (savedir == null)
			 savedir = "";
		 
	}
	
    public Globalconfig()
    {
      this("iedu.conf");  	
    }
    
}
