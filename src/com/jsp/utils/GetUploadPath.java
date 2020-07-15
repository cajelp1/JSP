package com.jsp.utils;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;

public class GetUploadPath {
	
	public static Properties properties = new Properties();
	
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
		String path = null;
		
		path = properties.getProperty(key);
		path = path.replace("/", File.separator);
		
		return path;
	}
	
}
