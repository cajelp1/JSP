package com.jsp.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.request.ModifyBoardRequest;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;
import com.jsp.utils.MakeLogForException;

public class BoardModifyAction implements Action {
	
	private BoardService boardService;// = BoardServiceImpl.getInstance();
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "board/modify_success";
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		System.out.println(bno);
		
		BoardVO vo = new ModifyBoardRequest(bno, title, content, writer).toBoardVO();
//		vo.setBno(bno);
//		vo.setTitle(title);
//		vo.setWriter(writer);
//		vo.setContent(content);
		
		try {
			boardService.modify(vo);
		} catch (SQLException e) {
			e.printStackTrace();
			MakeLogForException.makeLog(request, e);
			url="board/modify_fail";
		}
		
		return url;
	}

}
