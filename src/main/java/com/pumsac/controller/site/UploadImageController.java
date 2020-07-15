package com.pumsac.controller.site;

import com.pumsac.service.AttachFileService;
import com.pumsac.vo.AttachFileVO;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.UUID;

@Controller
public class UploadImageController {
	@Resource(name="memberProfileImage")
	private String imgPath;
	
	@Autowired
	private AttachFileService attachService;
	
	@RequestMapping(value="/commons/member/uploadImg", produces="text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> uploadImg(MultipartFile pictureFile, HttpServletRequest request) throws Exception {
		ResponseEntity<String> result = null;
		
		int fileSize = 5 * 1024 * 1024; //5MB 제한
		if(pictureFile.getSize() > fileSize) {
			return new ResponseEntity<String>("용량초과입니다.",HttpStatus.BAD_REQUEST);
		}
		
		String savePath = this.imgPath;
		String fileName = UUID.randomUUID().toString().replace("-", "");
		String fileFormat = pictureFile.getOriginalFilename().substring(pictureFile.getOriginalFilename().lastIndexOf("."));
		fileName = fileName + "$$" + pictureFile.getOriginalFilename() /*+ fileFormat*/;
		
		File saveFile = new File(savePath, fileName);
		
		if(!saveFile.exists()) {
			saveFile.mkdirs();
		}
		
		try {
			pictureFile.transferTo(saveFile);
			result = new ResponseEntity<String>(fileName,HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;	
	}
	
	@RequestMapping(value="/commons/member/getProfile")
	@ResponseBody
	public ResponseEntity<byte[]> getProfileImg(String id) throws Exception {
		ResponseEntity<byte[]> entity = null;
		InputStream in = null;
		AttachFileVO profile = null;
		try {
			profile = attachService.getProfileImage(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String fileName = profile.getStoredFilename();
		
		try {
			in = new FileInputStream(imgPath+File.separator+fileName);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
					HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			if(in != null) in.close();
		}
		
		return entity;
	}
}
