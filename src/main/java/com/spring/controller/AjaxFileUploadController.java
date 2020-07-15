package com.spring.controller;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.util.FileUpload;
import com.spring.util.MediaUtils;

@Controller
public class AjaxFileUploadController {
	
	@Resource(name="uploadPath") //이러면 root-context resource에서 파일을 직접 지명해서 읽어온다.
	private String uploadPath;
	
	@RequestMapping("/ajaxFileUploadForm")
	public void ajaxFileUploadForm() {}
	
	
	@RequestMapping(value="/uploadAjax",method=RequestMethod.POST,
					produces="text/plain;charset=utf-8") //제목을 한글로 저장하기위해 필요함
	@ResponseBody
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		String sourcePath = null;
		
		//이미지인 경우 url을 /2020/05/08/s_UUID$$fileName.jpg
		//etc : /2020/05/08/s_UUID$$fileName.txt 로 표시된다
		//↓↓↓
		try {
			sourcePath = FileUpload.uploadFile(file, uploadPath); 
			entity = new ResponseEntity<String>(sourcePath,HttpStatus.CREATED);
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
		return entity;
	}
	
	
	@RequestMapping(value="/deleteFile",method=RequestMethod.POST
					,produces="text/plain;charset=utf-8") //제목을 한글로 받기위해 필요함
	@ResponseBody
	public ResponseEntity<String> deleteFile(@RequestBody Map<String, String> dataMap) throws Exception{
		
		ResponseEntity<String> entity = null;
		
		String fileName = dataMap.get("fileName");
		
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		try {
			if(mType!=null) {
				String front = fileName.substring(0,12);
				String end = fileName.substring(14);
				new File(uploadPath+(front+end).replace('/', File.separatorChar)).delete(); //원본 이미지 삭제
			}
			new File(uploadPath+fileName.replace('/', File.separatorChar)).delete(); //썸네일 삭제 혹은 원본 삭제
			
			entity = new ResponseEntity<String>("deleted",HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	
	
	
}
