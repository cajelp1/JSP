package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dto.ReplyVO;
import com.jsp.request.SearchCriteria;

public class ReplyDAOImpl implements ReplyDAO {
	
	private SqlSessionFactory sessionFactory;
	public void setSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void insertReply(ReplyVO reply) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try {
			session.update("Reply-Mapper.insertReply",reply);
		}finally{
			if(session!=null)session.close();
		}
	}

	@Override
	public void updateReply(ReplyVO reply) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try{
			session.update("Reply-Mapper.updateReply",reply);
		}finally{
			if(session!=null)session.close();
		}
	}

	@Override
	public void deleteReply(int rno) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		try{
			session.update("Reply-Mapper.deleteReply",rno);
		}finally {
			session.close();
		}
	}

	@Override
	public int selectReplySeqValue() throws SQLException {
		SqlSession session=sessionFactory.openSession();
		int seq = 10000001;
		try {
			seq = session.selectOne("Reply-Mappper.selectReplySeqValue",null);
		} finally {
			if(session!=null) session.close();
		}
		return seq;
	}

	@Override
	public List<ReplyVO> selectReplyList(int bno) throws SQLException {
		SqlSession session=sessionFactory.openSession();
		List<ReplyVO> replyList=null;
		try {
			replyList = session.selectList("Reply-Mapper.selectReplyList",bno);
		}finally {
			if(session!=null) session.close();
		}
		return replyList;
	}

}
