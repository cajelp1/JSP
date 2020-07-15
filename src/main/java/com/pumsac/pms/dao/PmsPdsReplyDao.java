package com.pumsac.pms.dao;

import java.sql.SQLException;
import java.util.List;

import com.pumsac.command.PdsFileReplyDTO;
import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.ReplyVO;

public interface PmsPdsReplyDao {
	
	public PdsFileReplyDTO selectPdsFileReplyYN(int no) throws SQLException;
	
	public List<ReplyVO> selectPbnoReplies(int pbno, SearchCriteria cri) throws SQLException;
	
	public int countPbnoReplies(int pbno) throws SQLException;
	
	public int selectNextReplySeq() throws SQLException;
	
	public void insertPbnoReply(ReplyVO reply) throws SQLException;
	
	public void updatePbnoReply(ReplyVO reply) throws SQLException;
	
	public void deletePbnoReply(ReplyVO reply) throws SQLException;
	
}
