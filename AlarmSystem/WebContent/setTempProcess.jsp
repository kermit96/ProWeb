<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.alarmsystem.dao.*, 
		com.alarmsystem.domain.*" %>    
<%
	request.setCharacterEncoding("utf-8");
	
	// 온도 설정 페이지에서 입력된 온도 값을 읽는다.
	String tempFrom = request.getParameter("tempFrom");
	String tempTo = request.getParameter("tempTo");
	
	Temperature temp = new Temperature();
	temp.setTempFrom(Integer.parseInt(tempFrom));
	temp.setTempTo(Integer.parseInt(tempTo));
	
	AlarmSystemDao dao = AlarmSystemDao.getInstance();
	dao.addTemperature(temp);
		
	response.sendRedirect("index.jsp");
%>	
