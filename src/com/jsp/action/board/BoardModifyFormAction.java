package com.jsp.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;
import com.jsp.utils.MakeFileName;

public class BoardModifyFormAction implements Action {
	
	private BoardService boardService;
	public void setBoardservice(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "board/modify";
		int bno = Integer.parseInt(request.getParameter("bno")); 
		
		try {
			BoardVO board = boardService.getBoardForModify(bno);
			
			List<AttachVO> renamedAtaachList=
					MakeFileName.parseFileNameFromAttaches(board.getAttachList(), "\\$\\$");
			board.setAttachList(renamedAtaachList);
			
			request.setAttribute("board", board);
			
			
		} catch (Exception e) {
			url="error/500_error";
			e.printStackTrace();
		}
		
		return url;
	}
}


