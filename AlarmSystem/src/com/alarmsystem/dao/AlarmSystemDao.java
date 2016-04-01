package com.alarmsystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.alarmsystem.domain.Temperature;


public class AlarmSystemDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// AlarmSystemDao 클래스가 메모리에 로딩 될 때 인스턴스가 생성 된다.
	private static AlarmSystemDao instance = new AlarmSystemDao();
	
	// 외부에서는 getInstance() 만을 사용해 이 클래스의 인스턴스를 사용할 수 있다.
	public static AlarmSystemDao getInstance() {
		if(instance == null) {
			instance = new AlarmSystemDao();
		}
		return instance;
	}
	
	/* 외부에서 new 연산자를 사용해 여러 개의 인스턴스를 생성하지 못하도록
	 * 접근 제어자를 private으로 하여 생성자를 선언 - Singleton 패턴 
	 * Singleton 패턴으로 구현하면 하나의 클래스 로더 당 하나의 인스턴스만 존재한다.
	 **/
	private AlarmSystemDao() {	}	
	
	public ArrayList<Temperature> getTemperatureList() {
		
		/* DB의 temperature 테이블에서 설정된 온도 데이터를 
		 * 최근 일자로 내림차순 정렬해 읽어오는 SQL 쿼리
		 * 테이블에 저장된 모든 온도 데이터(온도 설정 이력)를 읽어 온다.
		 **/
		String query = "SELECT * FROM temperature "
				+ "ORDER BY no DESC";
		
		// 온도 정보 리스트를 담을 ArrayList를 선언하고 null로 초기화 한다.
		ArrayList<Temperature> tempList = null;
		
		try {
			/* DBManager를 이용해 DB 커넥션 풀에서
			 * 활성화된 Connection 객체를 구한다.
			 **/
			conn = DBManager.getConnection();
			
			/* SQL 쿼리를 발행하기 위해 활성화된 Connection
			 * 객체로 부터 PreparedStatement 객체를 구한다.
			 **/
			pstmt = conn.prepareStatement(query);
			
			/* PreparedStatement 객체를 이용해 DB에 SELECT 쿼리를
			 * 발행하고 가상의 테이블 형태의 데이터인 ResultSet 객체를 구한다.
			 **/
			rs = pstmt.executeQuery();
			
			// 온도 정보 리스트를 담을 ArrayList 객체를 생성한다.
			tempList = new ArrayList<Temperature>();
			
			while(rs.next()) {				
				/* 반복문 안에서 온도 정보를 담을 Temperature 
				 * 객체를 생성해 DB로 부터 읽어온 데이터를 저장한다.
				 **/
				Temperature temp = new Temperature();
				temp.setNo(rs.getInt("no"));
				temp.setTempFrom(rs.getInt("temp_from"));
				temp.setTempTo(rs.getInt("temp_to"));
				temp.setInputDate(rs.getTimestamp("input_date"));
				
				// 온도 정보를 ArrayList에 담는다.
				tempList.add(temp);
			}			
		} catch(SQLException e) {
			System.out.println("AlarmSystemDao - getTemperatureList()");
			e.printStackTrace();
			
		} finally {			
			// DB 작업이 끝나면 Connection 객체를 커넥션 풀에 반납한다.
	//		DBManager.close(conn, pstmt, rs);
		}
		
		// DB로 부터 읽어온 온도 정보 리스트를 반환한다.
		return tempList;
	}
	
	public void addTemperature(Temperature temp) {
		
		/* DB의 temperature 테이블에 사용자가 
		 * 설정한 온도 데이터를 추가하는 SQL 쿼리
		 **/
		String query = "INSERT INTO temperature(no,temp_from, "
				+ " temp_to, input_date) VALUES( (select nvl(max(no),1)+1 from temperature),  ?, ?, sysdate)";
		
		System.out.println("insert query="+query);
		
		try {
			/* DBManager를 이용해 DB 커넥션 풀에서
			 * 활성화된 Connection 객체를 구한다.
			 **/
			conn = DBManager.getConnection();
			
			/* SQL 쿼리를 발행하기 위해 활성화된 Connection
			 * 객체로 부터 PreparedStatement 객체를 구한다.
			 **/
			pstmt = conn.prepareStatement(query);
			
			/* INSERT 쿼리의 플레이스홀더(?)에 해당하는 데이터를 
			 * 매개변수로 넘겨 받은 Temperature 객체를 이용해 지정한다.
			 **/
			pstmt.setInt(1, temp.getTempFrom());
			pstmt.setInt(2, temp.getTempTo());
			
			/* PreparedStatement 객체를 이용해 DB에 INSERT 
			 * 쿼리를 발행해 새로 설정된 온도 데이터를 추가한다.
			 **/
			pstmt.executeUpdate();			
				
		} catch(SQLException e) {
			System.out.println("AlarmSystemDao - addTemperature()");
			e.printStackTrace();
			
		} finally {
			// DB 작업이 끝나면 Connection 객체를 커넥션 풀에 반납한다.
			DBManager.close(conn, pstmt);
		}
	}
	
	public Temperature getTemperature() {
		
		/* DB의 temperature 테이블에서 가장 최근에
		 * 설정된 온도 데이터를 읽어 오는 SQL 쿼리
		 **/
		String query = "SELECT * FROM temperature "
				+ " where rownum=1   ORDER BY input_date DESC ";
		
		/* 가장 최신에 설정된 온도 정보를 담을
		 * Temperature 타입의 변수를 선언
		 **/
		Temperature temp = null;
		
		try {
			/* DBManager를 이용해 DB 커넥션 풀에서
			 * 활성화된 Connection 객체를 구한다.
			 **/
			conn = DBManager.getConnection();
			
			/* SQL 쿼리를 발행하기 위해 활성화된 Connection
			 * 객체로 부터 PreparedStatement 객체를 구한다.
			 **/
			pstmt = conn.prepareStatement(query);
			
			/* PreparedStatement 객체를 이용해 DB에 SELECT 쿼리를
			 * 발행하고 가상의 테이블 형태의 데이터인 ResultSet 객체를 구한다.
			 **/
			
		
			
			rs = pstmt.executeQuery();			

		
			if(rs.next()) {
				
		
				// 온도 정보를 담을 Temperature 객체 생성
				temp = new Temperature();
               				
				// DB로 부터 읽어온 온도 정보를 Temperature 객체에 저장
				temp.setNo(rs.getInt("no"));
				temp.setTempFrom(rs.getInt("temp_from"));
				temp.setTempTo(rs.getInt("temp_to"));
				temp.setInputDate(rs.getTimestamp("input_date"));
			}		
		} catch(SQLException e) {
			System.out.println("AlarmSystemDao - getTemperature()");
			e.printStackTrace(); 
			
		} finally {			
			// DB 작업이 끝나면 Connection 객체를 커넥션 풀에 반납한다.
			DBManager.close(conn, pstmt, rs);
		}
		
		// DB로 부터 읽어온 온도 정보를 반환한다. 
		return temp;		
	}
}
