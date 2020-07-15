package com.pumsac.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;

public class FileUploadDelete {
	
	//경로 유동 파일저장
	public static String uploadFile(MultipartFile multi, String uploadPath) throws Exception{
		
		//중복파일명 makeFileName 클래스 불러와 해결
		String originalName = multi.getOriginalFilename();
		String saveName = MakeFileName.toUUIDFileName(originalName, "$$");
		
		//파일 저장
		//File target=new File(uploadPath+savePath,saveName);
		File target=new File(uploadPath,saveName);
		if(!target.exists()) {
			target.mkdirs();
		}
		multi.transferTo(target);
		
		return saveName;		
	}
	
	//경로 유동 파일 삭제
	public static void deleteFile(String fileName, String uploadPath) throws Exception{
		File file = new File(uploadPath, fileName);
		if(file.exists()) {
			file.delete();
		}
	}
	
	//저장경로에 \\년\\월\\일 추가해서 리턴
	public static String calcPath(String uploadPath)throws Exception{
		Calendar cal=Calendar.getInstance();
		
		String yearPath=File.separator+cal.get(Calendar.YEAR);
		String monthPath=yearPath+File.separator+
				new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath=monthPath+File.separator+
				new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		File file = new File(uploadPath+datePath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		return datePath;
	}
	
}
