package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dao.ReplyDAO;
import com.jsp.dto.ReplyVO;

public class ReplyServiceImpl implements ReplyService {
	
	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO){
		this.replyDAO=replyDAO;
	}
	
	
	@Override
	public List<ReplyVO> getReplyList(int bno) throws SQLException {
		List<ReplyVO> replyList = replyDAO.selectReplyList(bno);
		return replyList;
	}

	@Override
	public void registReply(ReplyVO reply) throws SQLException {
		int rno = replyDAO.selectReplySeqValue();
		reply.setRno(rno);
		replyDAO.insertReply(reply);
	}

	@Override
	public void modifyReply(ReplyVO reply) throws SQLException {
		replyDAO.updateReply(reply);
	}

	@Override
	public void removeReply(int rno) throws SQLException {
		replyDAO.deleteReply(rno);
	}

}
