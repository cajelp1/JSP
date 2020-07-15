package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.dao.BoardDAO;
import com.jsp.dao.ReplyDAO;
import com.jsp.dto.BoardVO;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;

public class BoardServiceImpl implements BoardService {
	
	//리스너에서 인스턴스 생성도 다 하니까 싱글톤은 지워버리쟈
//	private BoardServiceImpl() {}
//	private static BoardServiceImpl service;
//	public static BoardServiceImpl getInstance() {
//		if(service == null) {
//			service = new BoardServiceImpl();
//		}
//		return service;
//	}
	
	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}
	
	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	
	
	@Override
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException {
		
		List<BoardVO> boardList = boardDAO.selectBoardCriteria(cri);
		
		//댓글수 확인하기
		for(BoardVO vo : boardList) {
			int reply = replyDAO.countReply(vo.getBno());
			if(reply!=0) {
				vo.setReplycnt(reply);
			}
		}
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardDAO.selectBoardCriteriaTotalCount(cri));
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardList", boardList);
		map.put("pageMaker", pageMaker);
		
		return map;
	}

	@Override
	public BoardVO getBoard(int bno) throws SQLException {
		boardDAO.increaseViewCnt(bno);
		BoardVO vo = boardDAO.selectBoardByBno(bno);
		return vo;
	}

	@Override
	public BoardVO getBoardForModify(int bno) throws SQLException {
		BoardVO vo = boardDAO.selectBoardByBno(bno);
		return vo;
	}

	@Override
	public void write(BoardVO board) throws SQLException {
		int bno = boardDAO.selectBoardSeqNext();
		board.setBno(bno);
		boardDAO.insertBoard(board);
	}

	@Override
	public void modify(BoardVO board) throws SQLException {
		boardDAO.updateBoard(board);
	}

	@Override
	public void remove(int bno) throws SQLException {
		boardDAO.deleteBoard(bno);
	}

}
