package com.wing.mainApp.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * 	이 클래스는 데이터베이스 작업을 해주기 위한 클래스이다.
 * 	고로	이 클래스에서는 컨넥션 풀에 있는 컨넥션과 myBatis가 제공하는
 * 			스테이트먼트를 이용해서 작업을 해주어야 한다.
 * 	그러기 위해서는 반드시
 * 		SqlSessionDaoSupport	라는 클래스를 상속받아서 제작해야 한다.
 * 
 * 	이것을 상속 받으면 반드시 getSqlSession()를 이용해서  session을 받아야 한다.
 * 
 * 	만약 DI 기법을 이용하고자 하면 SqlSessionDaoSupport을 상속받지 않는다.
 */
//	xml 파일에 등록하지 않고 이 클래스가 자동 DI가 되도록 해주는 어노테이션
@Repository
public class SampleDAO {//extends	SqlSessionDaoSupport{
/*
	//	사용방법1
	public int getTotal() {
		//	SqlSession을 함수를 이용해서 받아와서 사용한다.
		SqlSession	session = this.getSqlSession();
		//	질의 명령을 실행한다.
		int	total = session.selectOne("sample.total");
		return total;
	}
*/

	//	사용방법2
	//		DI 기법으로  SqlSession을 알아와서 사용한다.
	@Autowired
	SqlSessionTemplate sqlSession;
	//	이 클래스가 DI기법을 사용하면 이것은 자동적으로 DI 클래스가 되도록
	//	이주어야 한다.
	//	그렇지 않으면 setXxx()를 이용해서 DI로 사용하는 모든것을
	//	받도록 처리해야한다.
	public int getTotal() {
		int	total = sqlSession.selectOne("sample.total");
		
		return total;
	}
}

