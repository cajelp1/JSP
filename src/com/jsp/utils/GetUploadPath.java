package com.jsp.utils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

public class GetUploadPath {
	
	private static Properties properties = new Properties();
	static {
		
		String resource = "com/jsp/properties/uploadPath.properties";
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			properties.load(reader);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static String getUploadPath(String key) {
		
		String uploadPath = null;
		uploadPath = properties.getProperty(key);
		uploadPath = uploadPath.replace("/", File.separator);
		// file seperator는 os마다 다르다. 그래서 file seperator로 나눔.
		
		return uploadPath;
	}
	
	//오늘 이름으로 된 폴더 생성 경로
	public static String getUploadPathToday(String key) {
		
		String uploadPath = null;
		uploadPath = properties.getProperty(key);
		uploadPath = uploadPath.replace("/", File.separator);
		
		//하위로 오늘날짜 이름으로 된 폴더를 하나 더 만든다
		uploadPath += File.separator;
		uploadPath += new SimpleDateFormat("yyyyMMdd").format(new Date());
		
		return uploadPath;
	}
	
}
