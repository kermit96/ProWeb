<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.alarmsystem.dao.*, 
		com.alarmsystem.domain.*" %>
<%
	AlarmSystemDao dao = AlarmSystemDao.getInstance();
	Temperature temp = dao.getTemperature();
	pageContext.setAttribute("temp", temp);
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>temperatureLimit.jsp</title>
</head>
<body>
	<span id="start">${temp.tempFrom}</span>
	<span id="end">${temp.tempTo}</span>
</body>
</html>
