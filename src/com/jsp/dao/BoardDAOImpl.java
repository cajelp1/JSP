package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dto.BoardVO;
import com.jsp.request.SearchCriteria;

public class BoardDAOImpl implements BoardDAO {
	
	private SqlSessionFactory sessionFactory;
	
	public void setSessionFactory (SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Override
	public List<BoardVO> selectBoardCriteria(SearchCriteria cri) throws SQLException {
		List<BoardVO> list = null;
		
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		SqlSession session = sessionFactory.openSession();
		try{
			 list = session.selectList("Board-Mapper.selectBoardCriteria", cri, rowBounds);
		}finally {
			if(session!=null)session.close();
		}
		
		return list;
	}

	@Override
	public int selectBoardCriteriaTotalCount(SearchCriteria cri) throws SQLException {
		int num = 0;
		
		SqlSession session = sessionFactory.openSession();
		try{
			 num = session.selectOne("Board-Mapper.selectBoardCriteriaTotalCount", cri);
		}finally {
			if(session!=null)session.close();
		}
		
		return num;
	}

	@Override
	public BoardVO selectBoardByBno(int bno) throws SQLException {
		BoardVO vo = null;
		
		SqlSession session = sessionFactory.openSession();
		try{
			vo = session.selectOne("Board-Mapper.selectBoardByBno", bno);
		}finally {
			if(session!=null)session.close();
		}
		
		return vo;
	}

	@Override
	public void insertBoard(BoardVO board) throws SQLException {
		
		SqlSession session = sessionFactory.openSession(true);
		try{
			 session.update("Board-Mapper.insertBoard", board);
		}finally {
			if(session!=null)session.close();
		}
	}

	@Override
	public void updateBoard(BoardVO board) throws SQLException {
		
		SqlSession session = sessionFactory.openSession(true);
		try{
			 session.update("Board-Mapper.updateBoard", board);
		}finally {
			if(session!=null)session.close();
		}
	}

	@Override
	public void deleteBoard(int bno) throws SQLException {
		
		SqlSession session = sessionFactory.openSession(true);
		try{
			 session.update("Board-Mapper.deleteBoard", bno);
		}finally {
			if(session!=null)session.close();
		}
	}

	@Override
	public void increaseViewCnt(int bno) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try{
			 session.update("Board-Mapper.increaseViewCnt", bno);
		}finally {
			if(session!=null)session.close();
		}
	}

	@Override
	public int selectBoardSeqNext() throws SQLException {
		int num = 0;
		
		SqlSession session = sessionFactory.openSession();
		try{
			 num = session.selectOne("Board-Mapper.selectBoardSeqNext", null);
		}finally {
			if(session!=null)session.close();
		}
		
		return num;
	}

}
