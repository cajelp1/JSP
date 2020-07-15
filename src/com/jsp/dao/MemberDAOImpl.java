package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dto.MemberVO;
import com.jsp.mybatis.OracleMyBatisSqlSessionFactoryBuilder;
import com.jsp.request.SearchCriteria;

public class MemberDAOImpl implements MemberDAO {
	
	//sql session factory
	private SqlSessionFactory sessionFactory;
	
	public void setSessionFactory (SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	//이걸 이제 리스너를 사용해서 만들어보쟈!
/*	private SqlSessionFactory sessionFactory	
		= OracleMyBatisSqlSessionFactoryBuilder.getSqlSessionFactory();*/
	
	//싱글톤
/*	private static MemberDao dao;
	private MemberDaoImpl() {}
	
	public static MemberDao getInstance() {
		if(dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}
*/
	
	
	@Override
	public List<MemberVO> selectMemberList() throws SQLException {
		List<MemberVO> memberList = null;
		
		SqlSession session = sessionFactory.openSession();
		try{
			memberList = session.selectList("Member-Mapper.selectMemberList", null);
		}finally {
			if(session!=null)session.close();
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
			if(session!=null)session.close();
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
			if(session!=null)session.close();
		}
		return member;
	}

	@Override
	public void insertMember(MemberVO member) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try{
			session.update("Member-Mapper.insertMember", member);
		}finally {
			if(session!=null)session.close();
		}
	}

	@Override
	public void updateMember(MemberVO member) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try{
			session.update("Member-Mapper.updateMember", member);
		}finally {
			if(session!=null)session.close();
		}
	}

	@Override
	public void deleteMember(String id) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try{
			session.update("Member-Mapper.deleteMember", id);
		}finally {
			if(session!=null)session.close();
		}
	}

	@Override
	public void disabledMember(String id) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try{
			session.update("Member-Mapper.disabledMember", id);
		}finally {
			if(session!=null)session.close();
		}
	}

	@Override
	public void enabledMember(String id) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try{
			session.update("Member-Mapper.enabledMember", id);
		}finally {
			if(session!=null) session.close();
		}
	}

	@Override
	public List<MemberVO> selectMemberList(SearchCriteria cri) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<MemberVO> memberList = null;
		
		try {
			memberList = session.selectList("Member-Mapper.selectSearchMemberList", cri, rowBounds);
		}finally {
			if(session!=null) session.close();
		}
		
		return memberList;
	}

	@Override
	public int selectMemberListCount(SearchCriteria cri) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		
		int count = 0;
		try {
			count = session.selectOne("Member-Mapper.selectSearchMemberListCount",cri);
		}finally {
			if(session != null) session.close();
		}
		
		return count;
	}

}


