package com.jsp.action.board;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.service.BoardService;

public class BoardRemoveAction implements Action {

	private BoardService boardService;
	public void setBoardservice(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "board/remove_success";
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		List<AttachVO> attachList = null;
		try {
			attachList = boardService.getBoard(bno).getAttachList();
			
			if(attachList != null) {
				for (AttachVO attach : attachList) {
					String storedFilePath = attach.getUploadpath() + File.separator
							+ attach.getFilename();
					File file = new File(storedFilePath);
					if (file.exists()) {
						file.delete();
					}
				}
			}
			
			boardService.remove(bno);
			
		} catch (Exception e1) {
			url="error/500_error";
			e1.printStackTrace();
		}

		return url;
	}

}








