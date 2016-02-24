package com.wing.mainApp.controller;

import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import iedu.config.Globalconfig;
import iedu.sql.BaseJDBCDao;
import iedu.sql.DBTYPE;
import iedu.util.MailSendInfo;
import iedu.util.util;

//  setup page setting 관련 루틴 
@Controller
public class Setup {

	
	@RequestMapping(value = "/Setup/setup")
	public String Setup(Locale locale, Model model) {

		return "Setup/setup";
	}
	
		
	@RequestMapping(value = "/Setup/getglobalconfig")
	public void getglobalconfig(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
	//	response.setContentType("text/html");
	//	response.setCharacterEncoding("utf-8");

		
		Globalconfig config = new Globalconfig();

		Gson gson = new Gson();

		String jsonstr = gson.toJson(config);

		PrintWriter out = response.getWriter();
		out.print(jsonstr); // out.print 내용을 ajax의 dataType이 jason인 놈에게 데이터 쏴줌		
		 
	}
	
	
	
	@RequestMapping(value = "/Setup/globalsave")
	public void globalsave(HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		  
		int  dbtype = 0 ;
		String dbname ="";
		String host ="";
		int     port = 0 ;
		String userid ="" ;				
		String password="";
		String savedir="";
		
		String  smtphost = "";
		int    smtpport = 0;
		String  smtppassword= "";
		String  smtpuserid= "";        
		int    encryptedmethod = 0; // 보안 접속 방법  
		String smtpemail ;
		String smtpsender;
		
		dbtype = Integer.parseInt(request.getParameter("dbtype") );
		dbname = request.getParameter("dbname");
		 host = request.getParameter("dbhost");
		 port = Integer.parseInt(request.getParameter("dbport"));
		 userid = request.getParameter("dbuser");
		 password = request.getParameter("dbpassword");
		 
		 smtphost = request.getParameter("smtphost");
		 smtpuserid = request.getParameter("smtpuserid");
		 smtppassword = request.getParameter("smtppassword");
		 smtpemail = request.getParameter("smtpemail");
		 smtpsender = request.getParameter("smtpsender");
		 
		 
		 
		 encryptedmethod = Integer.parseInt(request.getParameter("encryptedmethod") );
		 smtpport = Integer.parseInt(request.getParameter("smtpport") );
		 
		 
		 
		 savedir = request.getParameter("savedir");
		 		 
		 
		Globalconfig config = new Globalconfig();
		
		config.setDbtype(dbtype);
		config.setDbname(dbname);
		config.setHost(host);
		config.setPort(port);;
		config.setUserid(userid);
		config.setPassword(password);
		
		config.setSmtphost(smtphost);
		config.setSmtpuserid(smtpuserid);
		config.setSmtppassword(smtppassword);
		config.setSmtphost(smtphost);
		config.setSmtpport(smtpport);
		config.setEncryptedmethod(encryptedmethod);
		config.setSmtpemail(smtpemail);
		config.setSmtpsender(smtpsender);
		
		config.setSavedir(savedir);
		
		config.Save();
	//	response.setContentType("text/html");
	//	response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.print("저장 했습니다");

	}
	
	@RequestMapping(value = "/Setup/smtptest")
	public void smtptest(HttpServletRequest request, HttpServletResponse response)
	{
		
	    int    smtpport;
	    String smtphost;
	    String smtpuserid;
	    String smtppassword;
	    
	    String  smtpemail;
	    
	    String  smtpsender;
	    
	    int    encryptedmethod; // 0 : 없음 1: tls 2: ssl   
		
	    smtpport = Integer.parseInt(request.getParameter("smtpport") );
	    smtphost = request.getParameter("smtphost");
	    smtpuserid = request.getParameter("smtpuserid");
	    encryptedmethod = Integer.parseInt(request.getParameter("encryptedmethod"));
	    smtpemail = request.getParameter("smtpemail");
	    smtpsender = request.getParameter("smtpsender");
	    smtppassword 	= request.getParameter("smtppassword");	
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out= null;
		
		try {
		 out = response.getWriter();
		
		} catch(Exception ex) { 
			
			
		}
		
		MailSendInfo info = new MailSendInfo();
		
		info.body = "테스트 메일 본문입니다.";
		info.subject = "테스트 메일입니다.";
		info.from = smtpemail;
		info.fromname = smtpsender;
		info.userid  = smtpuserid;
		info.password  = smtppassword;
		info.port =  smtpport;
		info.smtpserver = smtphost;
		info.to = smtpemail;

       switch(encryptedmethod)
       {
          case 1:
        	 info.istls = true;
        	 break;
          case 2: 	  
        	  info.isssl = true;
         	 break;       
       }		
		
		String str = "메일 발송 테스트 성공 했습니다.";
		try {
		util.SendMail(info);
		} catch(Exception ex) {
			ex.printStackTrace();
			str = ex.toString();
		}
		out.print(str);
				
	}
	
	
	@RequestMapping(value = "/Setup/dbtest")
	public void dbtest(HttpServletRequest request, HttpServletResponse response)
	{	
		int  dbtype = 0 ;
		String dbname ="";
		String host ="";
		int     port = 0 ;
		String userid ="" ;				
		String password="";
		
		dbtype = Integer.parseInt(request.getParameter("dbtype") );
		dbname = request.getParameter("dbname");
		 host = request.getParameter("dbhost");
		 port = Integer.parseInt(request.getParameter("dbport"));
		 userid = request.getParameter("dbuser");
		 password = request.getParameter("dbpassword");
				
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out= null;
		
		try {
		 out = response.getWriter();
		
		} catch(Exception ex) { 
			
			
		}
		
		String str = getTestString(dbtype,dbname,host,port,userid,password);
		out.print(str);
	}
	
	private  String getTestString(int dbtype,String dbname,String host,int port,
			  String userid,String password)
	{		
		 DBTYPE type = DBTYPE.fromInt(dbtype);     
		 try {
		    BaseJDBCDao dao = BaseJDBCDao.GetjdbcDao(type, host, port, dbname, userid, password);
		    dao.closeAll();
		    return "Success";
		 } catch(Exception ex) {
			 
			 return ex.toString(); 
		 }
	}

	
}
