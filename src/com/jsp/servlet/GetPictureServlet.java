package com.jsp.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.utils.GetUploadPath;

//이미지 자체가 브라우저가 접근할 수 없는 곳에 있기때문에 servlet으로 가져올 뿐임. 
/*@WebServlet("/member/picture/get")*/
public class GetPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public GetPictureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = (String)request.getParameter("picture");
		String savedPath = GetUploadPath.getUploadPath("member.picture.upload");
		
		String filePath = savedPath + fileName;
		
		sendFile(response, filePath);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	void sendFile(HttpServletResponse response, String filePath) throws ServletException, IOException{
		
		/*response.getWriter().println(filePath); //서블릿이 값을 제대로 불러오는지부터 확인하고 순차적으로 하라...*/
		
		//보낼 파일 설정
		File downloadFile = new File(filePath);
		FileInputStream inStream = new FileInputStream(downloadFile);
		
		//파일은 헤더를 조작해줘야 받는쪽에서 파일이라고 인식하기에 적어야 할게 많다.
		//적기위해 일단 context를 가져온다.
		ServletContext context = getServletContext();
		
		//파일 포맷으로 MIME를 결정한다
		String mimeType = context.getMimeType(filePath);
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type : "+mimeType);
		
		//response 수정
		response.setContentType(mimeType);
		response.setContentLength((int)downloadFile.length());
		
		//전달을 위해 header도 수정해준다
		String headerKey = "Content-Disposition";
		//이건 대체 뭘 해주는 부분이지....
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		
		response.setHeader(headerKey, headerValue);
		
		//파일 내보내기
		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int byteRead = -1;
		
		while((byteRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, byteRead);
		}//으와아ㅏㅏ 하나도 기억 안 나...
		
		if(inStream != null) inStream.close();
		if(outStream != null) outStream.close();
	}
	
}













