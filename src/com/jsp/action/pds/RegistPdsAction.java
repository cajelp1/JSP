package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;
import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;

public class RegistPdsAction implements Action {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "pds/regist_success";
		
		try {
			PdsVO pds = fileUpload(request);
			// 3. service(VO) 결과
			pdsService.regist(pds);
		} catch (Exception e) {
			// 4. 결과에 따른 화면 결정.
			url = "pds/regist_fail";
			e.printStackTrace();
		}
		
		return url;
	}
	
	//업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;	//3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;		//40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;	//50MB. filesize보다 커야 한다.
	
	private PdsVO fileUpload(HttpServletRequest request) throws Exception {
		
		PdsVO pds = new PdsVO();
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
		String uploadPath = GetUploadPath.getUploadPathToday("pds.upload");
		
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
					
					String fileName = new File(item.getName()).getName();
					fileName = MakeFileName.toUUIDFileName(fileName, "$$");
					String filePath = uploadPath + File.separator + fileName;
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
					attach.setFileName(fileName);
					attach.setUploadPath(uploadPath);
					attach.setFiletype(fileName.substring(fileName.lastIndexOf(".")+1));
					
					//List<AttachVO>에 추가
					attachList.add(attach);
					
					System.out.println("upload file : "+ attach);
				}
			}
			
			pds.setAttachList(attachList);
			pds.setWriter(writer);
			pds.setContent(content);
			pds.setTitle(title);
			
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pds;
	}
	
	
}
