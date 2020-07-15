package com.jsp.action.board;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;
import com.jsp.utils.MakeFileName;

public class GetAttachAction implements Action {
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		int ano = Integer.parseInt(request.getParameter("ano"));
		
		String filename = null;
		String filepath = null;
		
		try {
			BoardVO board = boardService.getBoard(bno);
			
			List<AttachVO> attachList = board.getAttachList();
			for(AttachVO attach : attachList) {
				if(ano == attach.getAno()) {
					filename = attach.getFilename();
					filepath = attach.getUploadpath();
					break;
				}
			}
			
			sendFile(request, response, filename, filepath);
			
		} catch (Exception e) {
			url="error/500_error";
			e.printStackTrace();
		}
		
		return url;
	}
	
	private void sendFile(HttpServletRequest request, HttpServletResponse response, 
			String fileName, String filePath) throws Exception{
		
		filePath += File.separator + fileName;
		
		//보낼 파일 설정
		File downloadFile = new File(filePath);
		FileInputStream inStream = new FileInputStream(downloadFile);
		
		ServletContext context = request.getServletContext();
		//파일 포맷으로 MIME를 결정한다
		String mimeType = context.getMimeType(filePath);
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		
		//response 수정
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		
		//파일명 한글깨짐 방지 : utf-8
		String downloadFileName = new String(downloadFile.getName().getBytes("utf-8"), "ISO-8859-1");
		downloadFileName = MakeFileName.parseFileNameFromUUID(downloadFileName, "\\$\\$");
		
		
		//response header setting
		String headerkey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFileName);
		response.setHeader(headerkey, headerValue);
		
		
		//파일내보내기
		OutputStream outStream = null;
		try {
			
			outStream = response.getOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			
			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			
		}finally {
			if(inStream!=null)inStream.close();
			if(outStream!=null)outStream.close();
		}
	}
	
}






