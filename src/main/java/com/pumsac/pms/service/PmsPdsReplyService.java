package com.pumsac.pms.service;

import java.sql.SQLException;
import java.util.Map;

import com.pumsac.command.PdsFileReplyDTO;
import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.ReplyVO;

public interface PmsPdsReplyService {
	
	public PdsFileReplyDTO getFileReplyYN(int no) throws SQLException;
	
	public Map<String, Object> getPbnoReplies(int pbno, SearchCriteria cri) throws SQLException;
	
	public void registPbnoReply(ReplyVO reply) throws SQLException;
	
	public void modifyPbnoReply(ReplyVO reply) throws SQLException;
	
	public void removePbnoReply(ReplyVO reply) throws SQLException;
	
}
