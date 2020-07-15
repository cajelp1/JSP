package com.pumsac.pms.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pumsac.command.PageMaker;
import com.pumsac.command.PdsFileReplyDTO;
import com.pumsac.command.SearchCriteria;
import com.pumsac.pms.dao.PmsPdsReplyDao;
import com.pumsac.vo.ReplyVO;

@Repository("pmsPdsReply")
public class PmsPdsReplyServiceImpl implements PmsPdsReplyService {
	
	@Autowired
	private PmsPdsReplyDao pmsPdsReplyDao;

	@Override
	public PdsFileReplyDTO getFileReplyYN(int no) throws SQLException {
		PdsFileReplyDTO frDTO = null;
		frDTO = pmsPdsReplyDao.selectPdsFileReplyYN(no);
		return frDTO;
	}

	@Override
	public Map<String, Object> getPbnoReplies(int pbno, SearchCriteria cri) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ReplyVO> replyList = pmsPdsReplyDao.selectPbnoReplies(pbno, cri);
		int totalCount = pmsPdsReplyDao.countPbnoReplies(pbno);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		map.put("pageMaker", pageMaker);
		map.put("replyList", replyList);
		return map;
	}

	@Override
	public void registPbnoReply(ReplyVO reply) throws SQLException {
		int seq = 0;
		seq = pmsPdsReplyDao.selectNextReplySeq();
		reply.setNo(seq);
		pmsPdsReplyDao.insertPbnoReply(reply);
	}

	@Override
	public void modifyPbnoReply(ReplyVO reply) throws SQLException {
		pmsPdsReplyDao.updatePbnoReply(reply);
	}

	@Override
	public void removePbnoReply(ReplyVO reply) throws SQLException {
		pmsPdsReplyDao.deletePbnoReply(reply);
	}

}
