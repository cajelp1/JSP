package com.jsp.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;
import com.jsp.utils.MakeLogForException;

public class BoardModifyFormAction implements Action {
	
	private BoardService boardService;// = BoardServiceImpl.getInstance();
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "board/modifyBoard";
		
		try {
			int bno = Integer.parseInt(request.getParameter("bno"));
			
			BoardVO board = boardService.getBoardForModify(bno);
			
			request.setAttribute("board", board);
			
		}catch(Exception e) {
			e.printStackTrace();
			MakeLogForException.makeLog(request, e);
			url="error/500_error";
		}
		
		return url;
	}

}
