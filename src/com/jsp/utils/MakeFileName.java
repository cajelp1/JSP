package com.jsp.utils;

import java.util.UUID;

public class MakeFileName {
	
	//랜덤으로 16자리를 만들어주는 UUID를 이용한다. 다만 중간의 "-" 는 공백처리.
	public static String toUUIDFileName(String fileName, String delimiter) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid + delimiter + fileName;
	}
	
}
