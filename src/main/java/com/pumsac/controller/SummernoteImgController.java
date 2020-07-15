package com.pumsac.controller;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SummernoteImgController {

	@Resource(name="imgPath")
	private String imgPath;

	@RequestMapping("/commons/uploadImg")
	@ResponseBody
	public ResponseEntity<String> uploadImg(MultipartFile file, String id, HttpServletRequest request)
			throws Exception {
		ResponseEntity<String> result = null;
		
		int fileSize = 5 * 1024 * 1024; // 5MB 제한

		if (file.getSize() > fileSize) {
			return new ResponseEntity<String>("용량 초과입니다.", HttpStatus.BAD_REQUEST);
		}

		String savePath = request.getServletContext().getRealPath(imgPath + "/" + id + "/");
		System.out.println(savePath);
		String fileName = UUID.randomUUID().toString().replace("-", "");
		String fileFormat = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		fileName = fileName + fileFormat;

		File saveFile = new File(savePath , fileName);

		if (!saveFile.exists()) {
			saveFile.mkdirs();
		}
		try {
			file.transferTo(saveFile);
			result = new ResponseEntity<String>(request.getContextPath() + imgPath +"/"+id + "/" + fileName,
					HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return result;
	}

	@RequestMapping("/commons/deleteImg")
	@ResponseBody
	public ResponseEntity<String> deleteImg(String fileName, String id, HttpServletRequest request) throws Exception {
		ResponseEntity<String> result = null;

		String savePath = request.getServletContext().getRealPath(imgPath + "/" + id + "/");

		File target = new File(savePath + fileName);

		if (!target.exists()) {
			result = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		} else {
			try {
				target.delete();
				result = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} catch (Exception e) {
				result = new ResponseEntity<String>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

		return result;
	}
}
