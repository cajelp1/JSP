package com.jsp.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.request.RegistBoardRequest;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;
import com.jsp.utils.MakeLogForException;

public class BoardRegistAction implements Action {
	
	private BoardService boardService;// = BoardServiceImpl.getInstance();
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "board/regist_success";

		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		BoardVO vo = new RegistBoardRequest(title, content, writer).toBoardVO();
//		vo.setTitle(title);
//		vo.setWriter(writer);
//		vo.setContent(content);
		
		try {
			boardService.write(vo);
		} catch (SQLException e) {
			e.printStackTrace();
			MakeLogForException.makeLog(request, e);
			url="board/regist_fail";
		}
		
		return url;
	}

}
