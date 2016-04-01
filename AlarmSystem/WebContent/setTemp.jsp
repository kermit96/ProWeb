<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>경보시스템 온도 설정하기</title>
<style type="text/css">
	h1, form {
		text-align: center;
	}
</style>
<script src="js/jquery-2.1.4.min.js"></script>
<script>	
	$(document).ready(function() {		
		$("#tempList").click(function() {
			location.href = "temperatureHistoryList.jsp";
		});
		
		$("#present").click(function() {
			location.href = "index.jsp";
		});
	});
</script>
</head>
<body>
	<h1>경보시스템 모듈 온도 설정하기</h1><br/>
	<form name="f1" action="setTempProcess.jsp">
		<input type="range" name="tempFrom" min="0" max="50" value="30"/> ~ 
		<input type="range" name="tempTo" min="30" max="100" value="40"/>
		<br/><br/><br/>
		<input type="submit" value="온도설정 등록하기" />
		<input type="button" id="tempList" value="온도설정 이력보기" />
		<input type="button" id= "present" value="경보시스템 현재 상황보기" />
	</form>
</body>
</html>