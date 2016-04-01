<%@ page language="java" pageEncoding="utf-8" 
	contentType="text/html; charset=utf-8" %>
<%@ page import="com.alarmsystem.dao.*,com.alarmsystem.domain.*, java.util.*" %>	
<%	
	AlarmSystemDao dao = AlarmSystemDao.getInstance(); 
	Temperature temp = dao.getTemperature();
	pageContext.setAttribute("temp", temp);
%>	
<!DOCTYPE html>
<html>
<head>
	<title>Main Page</title>
	<style type="text/css">
		#warning {
			display: inline-block;
			width: 200px; 
		}
		p, h1 {
			text-align: center;
		}
	</style>
	<script src="js/jquery-2.1.4.min.js"></script>
	<script>
		
		$(document).ready(function() {
	
			$("#refresh").click(function() {	
							
				// 아두이노 웹 서버의 IP 주소 지정
							
    			$.ajax({
    			     url:'http://192.168.56.153:80',
    			        async:true,
    			        type:'post',
    			        dataType:'html',
    			        success:function(data){    				        	
    				    		$("#readTemp").text(data.split(",")[1]);
    		   					$("#result").text(data.split(",")[0]);
    	    					var start = ${temp.tempFrom};
    	    					var end = ${temp.tempTo};
    	    					console.log(start + ", " + end + ", " + data);
    	    					var temp = data.split(" : ")[1].split("℃")[0];					
    	    					
    	    					if(start > temp || end < temp) {						
    	    						$("#warning").css("background-color", "red")
    	    							.css("color", "white").text("현재 온도 상태 >> 경고"); 
    	    					} else {
    	    						$("#warning").css("background-color", "blue")
    	    						.css("color", "white").text("현재 온도 상태 >> 양호");
    	    					}
    	        		
    				        },
    				        error:function( xhr) {
    				        	 alert("An error occured: " + xhr.status + " " + xhr.statusText)
    				        }
    				 
    });
							
			}
			
			);
	
			var timer = setInterval(function() {
				
    			$.ajax({
    			     url:'http://192.168.56.153:80',
    			        async:true,
    			        type:'post',
    			        dataType:'html',
    			        success:function(data){    				        	
    				    		$("#readTemp").text(data.split(",")[1]);
    		   					$("#result").text(data.split(",")[0]);
    	    					var start = ${temp.tempFrom};
    	    					var end = ${temp.tempTo};
    	    					console.log(start + ", " + end + ", " + data);
    	    					var temp = data.split(" : ")[1].split("℃")[0];					
    	    					
    	    					if(start > temp || end < temp) {						
    	    						$("#warning").css("background-color", "red")
    	    							.css("color", "white").text("현재 온도 상태 >> 경고"); 
    	    					} else {
    	    						$("#warning").css("background-color", "blue")
    	    						.css("color", "white").text("현재 온도 상태 >> 양호");
    	    					}
    	        		
    				        },
    				        error:function( xhr) {
    				        	 alert("An error occured: " + xhr.status + " " + xhr.statusText)
    				        }
    				 
 			   });
				
				
				
			}, 3000);
			
			$("#autoRefresh").click(function() {				
				clearInterval(timer);
			});
			
			$("#tempList").click(function() {				
				location.href = "temperatureHistoryList.jsp";
			});
			
			$("#setTemp").click(function() {				
				location.href = "setTemp.jsp";
			});
		
		}
		
		
		);
	
	</script>
</head>
<body>	
	<h1>경보시스템 상황보기</h1><br/>
	<p><span id="readTemp">경보시스템 설정 온도 : 데이터 읽는중...</span></p>
	<p><span id="result">현재 거실 온도 : 데이터 읽는중...</span>&nbsp; 
			<span id="warning">현재 온도 상태 체크중...</span>&nbsp;</p><br/>
	<p><input type="button" id="refresh" value="갱신하기">
		<input type="button" id="autoRefresh" value="자동갱신종료">
		<input type="button" id="tempList" value="온도설정 이력보기"/>
		<input type="button" id="setTemp" value="온도 설정하기" /></p>	
</body>
</html>


