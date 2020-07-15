package com.jsp.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dto.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	
	private SqlSessionFactory sessionFactory;
	
	public void setSessionFactory (SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public MemberVO selectMemberById(String id) throws SQLException {
		MemberVO member = null;
		
		SqlSession session = sessionFactory.openSession();
		try{
			member = session.selectOne("Member-Mapper.selectMemberById", id);
		}finally {
			if(session!=null)session.close();
		}
		return member;
	}
	
}


