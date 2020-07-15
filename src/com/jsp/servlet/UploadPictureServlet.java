package com.jsp.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.jsp.utils.GetUploadPath;
import com.jsp.utils.MakeFileName;
import com.jsp.utils.MakeLogForException;

/*@WebServlet("/member/picture")*/
public class UploadPictureServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//upload는 get으로 오면 안됨! 그러니까 return하자. 
		//post로 넘기더라도 파일 자체가 get으로 올 수 없으니 안 받을거임.
		return;
	}
	
	//업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500;		// 500KB. 얼마의 단위로 임시 디렉토리에 저장할지 정해줌. 
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1; 	// 1MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2;// 2MB
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fileName = null;
		
		try {
			fileName = saveFile(request, response);
		} /*catch (FileUploadException e) {
			e.printStackTrace();
			//여기서는 에러로 보내줘야 ajax에서 에러로 처리한다. 그냥 화면주소를 보내면 ajax가 oldfile에 그 주소를 그냥 저장함!
		} 
		// 그러니까 여기말고 아래 exception에서 퉁치자. */
		
		catch (Exception e) {	//얘는 EXCEPTION의 가장 큰 부모객체이기 때문에 가장 나중에 받아야한다.
			e.printStackTrace();					//개발 중 에러 확인
			MakeLogForException.makeLog(request, e);//에러로그로 저장하기. 클래스로 따로 뺀다. 
			throw new IOException("파일 업로드 실패");	//사용자 화면용 (ajax)
		}
		
		//일단 파일이 제대로 나오는지부터 확인해보자. 
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(fileName);
	}
	
	private String saveFile (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
		
		// 1. null 판단.
		// request 파일 첨부 여부 확인
		if(!ServletFileUpload.isMultipartContent(request)) {
			//return null; //이렇게하면 ajax에서 파일이 저장된줄 알고 그냥 성공메시지를 띄워버림.
			throw new Exception();
			
		}
		
		// 2. 제대로 된 파일을 가져오기 전 임시저장해뒀다가 가져오기
		// 업로드를 위한  upload 환경설정 적용
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 저장을 위한 threshold memory 적용
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 임시 저장 위치 결정.
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		
		// 3. 실질적으로 업로드되는 환경 설정
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//업로드 파일의 크기 적용
		upload.setFileSizeMax(MAX_FILE_SIZE);
		//업로드 request 크기 적용. 파일을 담을 그릇의 크기를 정해주는 것
		upload.setSizeMax(MAX_REQUEST_SIZE);
		
		//실제 저장 경로를 설정
		String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");
		File file = new File(uploadPath);
		if(!file.mkdirs()) {
			System.out.println(uploadPath+"가 이미 존재하거나 실패했습니다.");
		};
		
		
		//4. 파일 저장
		//request로 부터 받는 파일을 추출해서 저장.
		List<FileItem> formItems = upload.parseRequest(request);	//exception발생. 변환되지 않을 때.
		String fileName = null;
		//파일 개수 확인
		if(formItems != null && formItems.size() > 0) {
			for(FileItem item : formItems) {
				if(!item.isFormField()) {	/*폼 필드.. input type 이 text, password 이런 것들이 아닐 때. 
											즉, 파일일때!
											고로 업로드 된 파일을 저장한다.*/
					// uuid + 구분자 + 파일명
					fileName = MakeFileName.toUUIDFileName(".jpg", "");
					String filePath = uploadPath + File.separator + fileName;
					File storeFile = new File(filePath);
					
					// local HDD 에 저장.
					item.write(storeFile);	//exception 발생
					
				}else {	 /*여기에는 oldFile이 들어온다!!!!
				 			hidden 타입은 text로 오기 때문.
				 			고로 이전에 업로드된 파일을 삭제한다. */
					
					switch (item.getFieldName()) {
					case "oldPicture" :					//이거는 JDK 1.6에서는 소용이 없다. 
						String oldFileName = item.getString("utf-8");
						File oldFile = new File(uploadPath+File.separator+oldFileName);
						if(oldFile.exists()) {
							oldFile.delete();
						}
						break;
					}
				}
			}
		}
		
		return fileName;
		
	}
	
}


























