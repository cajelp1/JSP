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
import com.jsp.utils.MakeLogForException;

public class RegistPdsAction2 implements Action {
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "pds/regist_success";
		
		//업로드 파일 환경 설정
		final int MEMORY_THRESHOLD = 1024 * 1024 * 1;	// 1MB. 얼마의 단위로 임시 디렉토리에 저장할지 정해줌. 
		final int MAX_FILE_SIZE = 1024 * 1024 * 40; 	// 40MB
		final int MAX_REQUEST_SIZE = 1024 * 1024 * 50;	// 50MB. 얘는 MAX_FILE_SIZE보다 커야한다!
		
		
		
		//1. request 파일 첨부 여부 확인
		if(!ServletFileUpload.isMultipartContent(request)) {
			url="erre/500_error";
			return url;
		}
		
		
		//2. 제대로 된 파일을 가져오기 전 임시저장 경로
		//업로드를 위해 upload 환경설정 적용
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//저장을 위한 thrshold memory 적용
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		//임시 저장위치 결정
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		
		//3. 실제로 업로드되는 환경 설정
		ServletFileUpload upload = new ServletFileUpload(factory);
		//업로드 파일 크기 적용
		upload.setFileSizeMax(MAX_FILE_SIZE);
		//업로드 request 크기 적용. 파일을 담을 그릇 크기임..
		upload.setSizeMax(MAX_REQUEST_SIZE);
		//실제 저장경로 설정
		String uploadPath = GetUploadPath.getUploadPathToday("pds.upload");
		File file = new File(uploadPath);
		if(!file.mkdirs()) {
			System.out.println(uploadPath+"가 이미 존재하거나 생성 실패했습니다.");
		}
		
		
		//4. 파일저장
		List<FileItem> formItems = null;
		//request 에서 받아온 객체를 parse 해서 list에 저장
		try {
			formItems = upload.parseRequest(request);
		} catch (FileUploadException e) {
			MakeLogForException.makeLog(request, e);
			e.printStackTrace();
			
			url="pds/regist_fail";
			String error = "파일 저장 객체 생성에 실패했습니다.\n관리자에게 문의하세요.";
			request.setAttribute("error", error);
			return url;
		}
		
		//파일 내용 확인
		if(formItems != null && formItems.size() > 0) {
			
			PdsVO pdsVO = new PdsVO();
			List<AttachVO> attachList = new ArrayList<AttachVO>();
			
			for(FileItem item : formItems) {
				//리스트에 담긴게 formField가 아닐때. 즉, 파일일때
				if(item.getFieldName().equals("uploadFile")) {
					//attachVO 생성
					AttachVO attachVO = new AttachVO();
					
					//파일 확장자 확인
					String fileType = item.getName().substring(item.getName().lastIndexOf("."));
					//유니크 파일이름 생성
					String fileName = MakeFileName.toUUIDFileName(fileType, "");
					//파일 경로 생성
					String filePath = uploadPath + File.separator + fileName;
					//파일 저장
					File storeFile = new File(filePath);
					//로컬 HDD에 저장
					try {
						item.write(storeFile);
					} catch (Exception e) {
						MakeLogForException.makeLog(request, e);
						e.printStackTrace();
						
						url="pds/regist_fail";
						String error = fileName+"을 로컬HHD에 저장할 수 없습니다.\n관리자에게 문의하세요.";
						request.setAttribute("error", error);
						return url;
					}
					
					attachVO.setFileName(item.getName());
					attachVO.setFiletype(fileType);
					attachVO.setUploadPath(filePath);
					attachList.add(attachVO);
					
					System.out.println(item.getFieldName()+"파일입니다");
				}
				
				//리스트에 담긴게 formField일 때.
				else {
					
					switch (item.getFieldName()) {
					
					case "writer" :
						pdsVO.setWriter(item.getString("utf-8"));
						break;
					case "title" :
						pdsVO.setTitle(item.getString("utf-8"));
						break;
					case "content" : 
						pdsVO.setContent(item.getString("utf-8"));
						break;
					}
					
				}
			}
			
			pdsVO.setAttachList(attachList);
			
			try {
				pdsService.regist(pdsVO);
			} catch (SQLException e) {
				MakeLogForException.makeLog(request, e);
				e.printStackTrace();
				
				url="pds/regist_fail";
				String error ="작성하신 글 내용을 저장할 수 없습니다.\n관리자에게 문의하세요.";
				request.setAttribute("error", error);
				return url;
			}
			
		}
		
		
		return url;
	}
	
}
