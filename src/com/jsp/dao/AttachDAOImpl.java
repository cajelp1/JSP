package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dto.AttachVO;

public class AttachDAOImpl implements AttachDAO{
	
	private SqlSessionFactory sessionFactory;
	public void setSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public void insertAttach(AttachVO attach) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try {
			session.update("Attach-Mapper.insertAttach",attach);
		}finally{
			if(session!=null)session.close();		
		}
	}

	@Override
	public void deleteAttach(int ano) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try {
			session.update("Attach-Mapper.deleteAttach",ano);		
		}finally {
			if(session!=null)session.close();
		}
	}

	@Override
	public List<AttachVO> selectAttachesByBno(int bno) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		List<AttachVO> attachList= null;
		try {
			attachList =session.selectList("Attach-Mapper.selectAttachByBno",bno);
		}finally{
			if(session!=null)session.close();
		}
		return attachList;
	}

	@Override
	public void deleteAllAttach(int bno) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try {
			session.update("Attach-Mapper.deleteAllAttach",bno);		
		}finally{
			if(session!=null)session.close();		
		}
	}
	@Override
	public AttachVO selectAttachByAno(int ano) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		AttachVO attach = null;
		try {
			attach=session.selectOne("Attach-Mapper.selectAttachByAno",ano);
		}finally {
			if(session!=null)session.close();
		}
		return attach;
	}


	@Override
	public int selectAttachSeqValue() throws SQLException {
		SqlSession session = sessionFactory.openSession();
		int seq = -1;
		try {
			seq = session.selectOne("Attach-Mapper.selectAttachSeqValue", null);
		}finally {
			if(session!=null)session.close();
		}
		return seq;
	}

}
