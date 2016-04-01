package com.alarmsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {
	
	private static DataSource DS;	
	private static Connection CONN;
	
	/* 외부에서 new 연산자를 이용해 인스턴스를 생성하지 못하도록
	 * 접근 제어자를 private으로 지정해 생성자를 선언하고 있다.
	 * 
	 * 이 클래스의 모든 메소드는 static으로 선언되어 있으므로 외부에서
	 * 인스턴스를 생성할 필요없이 클래스 이름으로 메소드에 접근할 수 있다. 
	 **/
	private DBManager() { }
	
	/* static 초기화 블럭은 클래스가 메모리에 로딩된 직후에 실행된다.
	 * static 초기화 블럭은 생성자 보다 먼저 실행된다.
	 **/
	static {
		
		try {
			/* 1. 자바 네이밍 서비스를 사용하기 위해 
			 * javax.naming 패키지의 InitialContext 객체를 생성한다.
			 * 
			 * 이 예제는 commons-dbcp2-2.0.1.jar, commons-pool2-2.2.jar
			 * 그리고 commons-logging-1.2.jar를 참조하므로 이들 라이브러리를 
			 * http://commons.apache.org에서 다운 받아 WEB-INF/lib에 추가해야 한다.  
			 * 또한 오라클 접속 드라이버도 필요하므로 http://www.oracle.com에서
			 * 다운로드 받아  WEB-INF/lib 폴더에 추가해야 한다.
			 **/  
			Context initContext = new InitialContext();
			
			/* 2. InitialContext 객체를 이용해 디렉토리 서비스에서 필요한 객체를
			 * 찾기 위해 기본 네임스페이스를 인자로 지정해 Context 객체를 얻는다.
			 * 
			 * 디렉토리 서비스에서 필요한 객체를 찾기 위한 일종의 URL 개념으로 
			 * 디렉토리 방식을 사용하므로 java:comp/env와 같이 지정한다. 
			 **/
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			
			/* 3. "jdbc/bbsDBPool" 이름을 가진 DBCP에서 DataSource 객체를 얻는다.
			 * context.xml 파일에서 지정한 수의 커넥션을 생성해 커넥션 풀에 저장한다.
			 * 
			 * "java:/comp/env"는 JNDI에서 기본적으로 사용하는 네임스페이스 이고 
			 * "jdbc/bbsDBPool"은 DBCP 이름으로 임의로 지정하여 사용할 수 있다.
			 **/
			DS = (DataSource) envContext.lookup("jdbc/AlarmDBPool");
		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// DBMS에 접속하고 활성화된 Connection 객체를 리턴 하는 메소드
	public static Connection getConnection() {
		
		try {
			
			// 4. DataSource 객체를 이용해 커넥션을 대여한다.
			CONN = DS.getConnection();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return CONN;
	}
	
	// DB 작업에 사용된 자원을 해제하는 메소드
	public static void close(Connection conn, PreparedStatement pstmt) {
		
		try {
			/* 매개변수로 넘어온 pstmt, conn이 null 아니면 자원을 해제 한다.
			 * DB 작업과 관련된 개체를 닫을 때는 객체가 생성된 역순으로 닫는다.
			 **/
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		} catch(SQLException e) { e.printStackTrace(); }
    }
	
	
	// DB 작업에 사용된 자원을 해제하는 메소드
	public static void close(Connection conn, 
			PreparedStatement pstmt, ResultSet rs) {
		
		try {
			
			/* 매개변수로 넘어온 rs, pstmt, conn이 null 아니면 자원을 해제 한다.
			 * DB 작업과 관련된 개체를 닫을 때는 객체가 생성된 역순으로 닫는다.
			 **/
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		} catch(SQLException e) { e.printStackTrace(); }		
	}

}