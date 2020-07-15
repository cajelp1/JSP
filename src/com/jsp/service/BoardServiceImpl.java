package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.dao.AttachDAO;
import com.jsp.dao.BoardDAO;
import com.jsp.dao.ReplyDAO;
import com.jsp.dto.AttachVO;
import com.jsp.dto.BoardVO;
import com.jsp.dto.ReplyVO;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;

public class BoardServiceImpl implements BoardService {
	
	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO=attachDAO;
	}
	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	
	
	@Override
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException {
		List<BoardVO> boardList = boardDAO.selectBoardCriteria(cri);
		
		for(BoardVO board : boardList) {
			List<AttachVO> attach = 
					(List<AttachVO>) attachDAO.selectAttachesByBno(board.getBno());
			board.setAttachList(attach);
			
			List<ReplyVO> reply = replyDAO.selectReplyList(board.getBno());
			if(reply!=null)board.setReplycnt(reply.size());
		}
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardDAO.selectBoardCriteriaTotalCount(cri));
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("boardList", boardList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
	
	@Override
	public BoardVO getBoard(int bno) throws SQLException {
		boardDAO.increaseViewCnt(bno);
		BoardVO board = boardDAO.selectBoardByBno(bno);
		List<AttachVO> attachList=attachDAO.selectAttachesByBno(bno);
		board.setAttachList(attachList);
		
		return board;
	}
	
	@Override
	public BoardVO getBoardForModify(int bno) throws SQLException {
		BoardVO board = boardDAO.selectBoardByBno(bno);
		List<AttachVO> attachList=attachDAO.selectAttachesByBno(bno);
		board.setAttachList(attachList);
		
		return board;
	}
	
	@Override
	public void regist(BoardVO board) throws SQLException {
		int bno = boardDAO.selectBoardSeqValue();
		board.setBno(bno);
		boardDAO.insertBoard(board);
		for(AttachVO attach:board.getAttachList()) {
			int ano = attachDAO.selectAttachSeqValue();
			attach.setAno(ano);
			attach.setBno(bno);
			attach.setUploader(board.getWriter());
			attachDAO.insertAttach(attach);
		}
	}
	
	@Override
	public void modify(BoardVO board) throws SQLException {
		boardDAO.updateBoard(board);
		for(AttachVO attach:board.getAttachList()) {
			attach.setBno(board.getBno());
			attach.setUploader(board.getWriter());
			attachDAO.insertAttach(attach);
		}
	}
	
	@Override
	public void remove(int bno) throws SQLException {
		boardDAO.deleteBoard(bno);
	}
	
	@Override
	public void registAttach(AttachVO attach) throws SQLException {
		attachDAO.insertAttach(attach);
	}
	
	@Override
	public void removeAttach(int ano) throws SQLException {
		attachDAO.deleteAttach(ano);
	}
	
	@Override
	public AttachVO getAttachByAno(int ano) throws SQLException {
		return attachDAO.selectAttachByAno(ano);
	}
	
	@Override
	public int getAttachAnoNextVal() throws SQLException {
		return attachDAO.selectAttachSeqValue();
	}
	
}
