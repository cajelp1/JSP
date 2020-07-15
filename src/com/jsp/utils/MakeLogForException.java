package com.jsp.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class MakeLogForException {
	
	public static void makeLog(HttpServletRequest request, Exception e) throws IOException {
		
		String today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String uri = request.getRequestURI();
		String errorMessage = e.getMessage();
		
		String log = today+","+uri+","+errorMessage;
		
		String savePath = GetUploadPath.getUploadPath("error.log")
				.replace("/", File.separator);
		
		File file = new File(savePath);
		if(!file.exists()) {
			file.mkdirs();	//파일이 있지 않다면 새 파일을 만드는데, 그 경로까지 새로 만든다.
		}
		
		String fileName = today.split(" ")[0]+"-log.csv";
		
		String logFilePath = savePath + fileName;
		
		//파일쓰기
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter("logFilePath", true));
		}finally {
			if(out!=null) out.close();
		}
		
	}
	
}
