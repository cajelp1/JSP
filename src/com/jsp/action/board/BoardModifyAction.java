package com.jsp.action.board;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;

public class BoardModifyAction implements Action {
	
	private BoardService boardService;
	public void setBoardservice(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url="board/modify_success";
		
		BoardVO board = null;
		try {
			board=modifyFile(request,response);
			boardService.modify(board);
			
		}catch(SQLException e1) {	//.modify 에서 터질때
			e1.printStackTrace();
			url="board/modify_fail";
		}catch(Exception e) {		//파일 저장 중 터질 때. pds가 null임.
			e.printStackTrace();
			url="error/500_error";
		}finally {
			request.setAttribute("board", board);
		}
		
		
		return url;
	}
	
	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
	
	
	private BoardVO modifyFile(HttpServletRequest request, HttpServletResponse response)
								throws Exception{
		BoardVO board = new BoardVO();
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		
		// 업로드를 위한 upload 환경설정 적용.
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 저장을 위한 threshold memory 적용.
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 임시 저장 위치 결정.
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 업로드 파일의 크기 적용.
		upload.setFileSizeMax(MAX_FILE_SIZE);
		
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 실제 저장 경로를 설정.
		String uploadpath = GetUploadPath.getUploadPathToday("board.attach");

		File file = new File(uploadpath);
		if (!file.mkdirs()) {
			System.out.println(uploadpath + "가 이미 존재하거나 생성을 실패했습니다.");
		}
		
		
		int bno = -1;
		String title = null;
		String content = null;
		String writer = null;
		
		
		List<FileItem> formItems = upload.parseRequest(request);
		if (formItems != null && formItems.size() > 0) {			
			List<String> deleteFile = new ArrayList<String>();
			for (FileItem item : formItems) {
				if (!item.isFormField()) { // 파일가져오기
					//summernote의 files 를 제외함.
					if(!item.getFieldName().equals("uploadFile")) continue;
					
					String filename = new File(item.getName()).getName();
					filename = MakeFileName.toUUIDFileName(filename, "$$");
					String filePath = uploadpath + File.separator
							+ filename;
					File storeFile = new File(filePath);
					
					// local HDD 에 저장.
					item.write(storeFile);
					
					// DB에 저장할 attach에 file 내용 추가.
					AttachVO attach = new AttachVO();
					attach.setAno(boardService.getAttachAnoNextVal());
					attach.setFilename(filename);
					attach.setUploadpath(uploadpath);
					attach.setFiletype(filename.substring(filename.lastIndexOf(".") + 1));
					attachList.add(attach);
					
					System.out.println("upload file : " + attach);
					
					request.setAttribute("message", "업로드 되었습니다.");
				}else {
					switch (item.getFieldName()) {
					case "title":
						title = item.getString("utf-8");
						break;
					case "writer":
						writer = item.getString("utf-8");
						break;
					case "content":
						content = item.getString("utf-8");
						break;
					case "bno":
						bno = Integer.parseInt(item.getString("utf-8"));
						break;
					case "deleteFile":
						int ano = Integer.parseInt(item.getString("utf-8"));
						
						AttachVO attach = boardService.getAttachByAno(ano);
						
						String filePath = attach.getUploadpath() 
								+ File.separator + attach.getFilename();
						File targetFile = new File(filePath);
						
						targetFile.delete(); //파일 삭제
						
						boardService.removeAttach(ano); //DB 삭제
						
						deleteFile.add(item.getString("utf-8"));
						
						break;
					}
				}
			}
			
			for(AttachVO attach : attachList) {
				attach.setBno(bno);
			}
			
			board.setTitle(title);
			board.setContent(content);
			board.setWriter(writer);
			board.setBno(bno);
			board.setAttachList(attachList);						
			
		}
		return board;				
	}
}







