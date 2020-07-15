package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dto.MemberVO;
import com.jsp.mybatis.OracleMyBatisSqlSessionFactoryBuilder;

public class MemberDaoImpl implements MemberDao {

/*
	//sql session factory
	private SqlSessionFactory sessionFactory = OracleMyBatisSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	
	//싱글톤
	private static MemberDao dao;
	private MemberDaoImpl() {}
	
	public static MemberDao getInstance() {
		if(dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}
*/
	
	
	//위의 패턴은 클래스간의 결합도가 높기 때문에 setter를 이용해 DI를 하는 방식으로 수정한다.
	//sessionFactory는 listener에서 주입
	private SqlSessionFactory sessionFactory;
	
	public void setSessionFactory (SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public List<MemberVO> selectMemberList() throws SQLException {
		List<MemberVO> memberList = null;
		
		SqlSession session = sessionFactory.openSession();
		try{
			memberList = session.selectList("Member-Mapper.selectMemberList", null);
		}finally {
			session.close();
		}
		return memberList;
	}

	@Override
	public int selectMemberListCount() throws SQLException {
		int count = 0;
		
		SqlSession session = sessionFactory.openSession();
		try{
			count = session.selectOne("Member-Mapper.selectMemberListCount", null);
		}finally {
			session.close();
		}
		return count;
	}

	@Override
	public MemberVO selectMemberById(String id) throws SQLException {
		MemberVO member = null;
		
		SqlSession session = sessionFactory.openSession();
		try{
			member = session.selectOne("Member-Mapper.selectMemberById", id);
		}finally {
			session.close();
		}
		return member;
	}

	@Override
	public void insertMember(MemberVO member) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try{
			session.update("Member-Mapper.insertMember", member);
		}finally {
			session.close();
		}
	}

	@Override
	public void updateMember(MemberVO member) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try{
			session.update("Member-Mapper.updateMember", member);
		}finally {
			session.close();
		}
	}

	@Override
	public void deleteMember(String id) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try{
			session.update("Member-Mapper.deleteMember", id);
		}finally {
			session.close();
		}
	}

	@Override
	public void disabledMember(String id) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try{
			session.update("Member-Mapper.disabledMember", id);
		}finally {
			session.close();
		}
	}

	@Override
	public void enabledMember(String id) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try{
			session.update("Member-Mapper.enabledMember", id);
		}finally {
			session.close();
		}
	}

}
