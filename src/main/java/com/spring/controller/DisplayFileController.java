package com.spring.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.util.MediaUtils;

@Controller
public class DisplayFileController {
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	@RequestMapping(value="/displayFile",method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;	//이미지 파일을 byte스트림으로 내보내기
		
		try {
			
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders(); //request에 있는 header가 아니라 spring내부 헤더 객체
			
			fileName = fileName.replace('/', File.separatorChar);
			in = new FileInputStream(uploadPath + fileName);
			
			//이미지 타입일경우 헤더를 세팅해줌
			if(mType!=null) {
				headers.setContentType(mType);
			}else {
				fileName = fileName.substring(fileName.indexOf("$$")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment;filename=\""
													+new String(fileName.getBytes("utf-8"),"ISO-8859-1")
													+"\"");
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
					headers,HttpStatus.CREATED);
			
		}catch(IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			in.close();
		}
		
		return entity;
	}
	
}
