package com.groupware.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.groupware.exception.FileSizeOverflowException;
import com.groupware.exception.MultipartFileEmptyException;

public class FileUpload {
	
	public static String saveFile(MultipartFile multi, String uploadPath)
			throws MultipartFileEmptyException, FileSizeOverflowException,
					IOException{
		
		if (multi.isEmpty()) {
			throw new MultipartFileEmptyException();
		}
		
		if (multi.getSize() > 1024 * 1024 * 50) {
			throw new FileSizeOverflowException();
		}
		
		/* 파일명 중복방지 */
		String fileName = UUID.randomUUID().toString().replace("-", "") 
					+ "$$" + multi.getOriginalFilename();
		
		/* 파일 경로확인 및 생성 */
		File file = new File(uploadPath, fileName);
		
		/*파일 경로 생성 */
		if (!file.exists()) {
			file.mkdirs();
		}
		
		/* 파일저장 */
		multi.transferTo(file);
		
		return fileName;
	}
	
}
