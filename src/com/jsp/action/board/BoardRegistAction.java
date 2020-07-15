package com.jsp.action.board;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;

public class BoardRegistAction implements Action {
	
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "board/regist_success";
		
		try {
			BoardVO board = fileUpload(request);
			boardService.regist(board);
		} catch (Exception e) {
			url = "board/regist_fail";
			e.printStackTrace();
		}
		
		return url;
	}
	
	//업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;	//3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;		//40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;	//50MB. filesize보다 커야 한다.
	
	private BoardVO fileUpload(HttpServletRequest request) throws Exception {
		
		BoardVO board = new BoardVO();
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		//1. 파라메터 데이터 저장 : request.getParameter() 안됨. enctype임.
		
		// 업로드를 위한 upload 환경설정 적용
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 저장을 위한 threshold memory 적용
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 임시 저장 위치 결정
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		// 업로드 파일의 크기 적용
		upload.setFileSizeMax(MAX_FILE_SIZE);
		// 업로드 request 크기 적용
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		//실제 저장경로를 설정
		String uploadPath = GetUploadPath.getUploadPathToday("board.attach");
		
		File file = new File(uploadPath);
		if(!file.mkdirs()) {
			System.out.println(uploadPath + "가 이미 존재하거나 생성을 실패했습니다.");
		}
		
		List<FileItem> formItems;
		
		String title = null; //request.getParameter("title");
		String writer = null; //request.getParameter("writer");
		String content = null; //request.getParameter("content");
		
		try {
			formItems = upload.parseRequest(request);
			
			for(FileItem item : formItems) {
				// 1.1 필드
				if(item.isFormField()) {
					// 파라미터 구분 (파라미터 이름)
					switch(item.getFieldName()) {
					case "title":
						title = item.getString("utf-8");
						break;
					case "writer":
						writer = item.getString("utf-8");
						break;
					case "content":
						content = item.getString("utf-8");
						break;
					}
					
					//PdsVO 생성
				}else {//1.2 파일
					//summernote의 files를 제외함
					if(!item.getFieldName().equals("uploadFile")) continue;
					
					String filename = new File(item.getName()).getName();
					filename = MakeFileName.toUUIDFileName(filename, "$$");
					String filePath = uploadPath + File.separator + filename;
					File storeFile = new File(filePath);
					
					//1.5 파일 저장
					//local HDD에 저장
					try {
						item.write(storeFile);
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}

					//AttachVO 생성
					AttachVO attach = new AttachVO();
					//DB에 저장할 attach에 file 내용 추가
					attach.setFilename(filename);
					
					System.out.println(uploadPath);
					
					attach.setUploadpath(uploadPath);
					attach.setFiletype(filename.substring(filename.lastIndexOf(".")+1));
					
					//List<AttachVO>에 추가
					attachList.add(attach);
					
					System.out.println("upload file : "+ attach);
				}
			}
			
			board.setAttachList(attachList);
			board.setWriter(writer);
			board.setContent(content);
			board.setTitle(title);
			
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return board;
	}
	
	
}
