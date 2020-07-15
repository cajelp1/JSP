package com.pumsac.pms.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pumsac.command.PdsFileReplyDTO;
import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.ReplyVO;

@Repository("pmsPdsreplyDao")
public class PmsPdsReplyDaoImpl implements PmsPdsReplyDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public PdsFileReplyDTO selectPdsFileReplyYN(int no) throws SQLException {
		PdsFileReplyDTO frDTO = null;
		frDTO = sqlSession.selectOne("pms-pds-reply-mapper.selectPdsFileReplyYN", no);
		return frDTO;
	}
	
	@Override
	public List<ReplyVO> selectPbnoReplies(int pbno, SearchCriteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<ReplyVO> list = null;
		list = sqlSession.selectList("pms-pds-reply-mapper.selectPbnoReplies",pbno,rowBounds);
		
		return list;
	}

	@Override
	public int countPbnoReplies(int pbno) throws SQLException{
		int count = 0;
		count = sqlSession.selectOne("pms-pds-reply-mapper.countPbnoReplies", pbno);
		return count;
	}

	@Override
	public int selectNextReplySeq() throws SQLException {
		int seq = 0;
		seq = sqlSession.selectOne("pms-pds-reply-mapper.selectNextReplySeq", null);
		return seq;
	}

	@Override
	public void insertPbnoReply(ReplyVO reply) throws SQLException {
		sqlSession.update("pms-pds-reply-mapper.insertPbnoReply", reply);
	}

	@Override
	public void updatePbnoReply(ReplyVO reply) throws SQLException {
		sqlSession.update("pms-pds-reply-mapper.updatePbnoReply", reply);
	}

	@Override
	public void deletePbnoReply(ReplyVO reply) throws SQLException {
		sqlSession.update("pms-pds-reply-mapper.deletePbnoReply", reply);
	}

}
