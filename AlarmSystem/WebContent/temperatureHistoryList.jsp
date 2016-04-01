
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page import="com.alarmsystem.dao.*, 
		com.alarmsystem.domain.*, java.util.*" %>
<%
	AlarmSystemDao dao = AlarmSystemDao.getInstance();
	ArrayList<Temperature> tempList = dao.getTemperatureList();
	pageContext.setAttribute("tempList", tempList);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>경보시스템 온도설정 이력조회</title>
<style type="text/css">
	table {
		width: 500px;
		margin: 0px auto;
		border: 1px solid black;
		border-collapse: collapse;
	}
	th {
		border: 1px solid black;
	}
	td {
		border: 1px dotted black;
		text-align: center;
	}
	h1, p { text-align: center; }
</style>
<script src="js/jquery-1.11.3.min.js"></script>
<script>	
	$(document).ready(function() {		
		$("#present").click(function() {
			location.href = "index.jsp";
		});
		
		$("#setTemp").click(function() {			
			location.href = "setTemp.jsp";
		});
	});
</script>
</head>
<body>
	<h1>경보시스템 온도설정 이력조회</h1>
	<table>
		<tr>
			<th class="no">NO</th>
			<th class="temp">온도1</th>
			<th class="temp">온도2</th>
			<th class="date">설정일자</th>
		</tr>
		<c:if test="${ empty tempList }">
			<tr><td colspan="4">
				온도 데이터 설정 이력이 존재하지 안습니다.</td></tr>
		</c:if>
		<c:if test="${ not empty tempList }" >
			<c:forEach var="temp" items="${ tempList }">
				<tr>
					<td>${ temp.no }</td>
					<td>${ temp.tempFrom }</td>
					<td>${ temp.tempTo }</td>
					<td><fmt:formatDate value="${temp.inputDate}" 
						pattern="yyyy년 MM월 dd일 HH시 mm분" ></fmt:formatDate></td>
				</tr>
			</c:forEach> 
		</c:if>
	</table><br/>
	<p><input type="button" id="setTemp" value="온도 설정하기" />		
		<input type="button" id= "present" value="경보시스템 현재 상황보기" /></p>
</body>
</html>
