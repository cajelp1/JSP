package com.pumsac.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pumsac.service.MainPageService;

@Controller	
public class MainController {
	
	@Autowired
	private MainPageService mainService;
	
	@Resource(name = "meetingRoomPictures")
    String uploadPath;
	
/*	@RequestMapping("/main")
	@ResponseBody
	public ResponseEntity<Map<String, List<MenuVO>>> getMenu(Model model) throws Exception {
		
		ResponseEntity<Map<String, List<MenuVO>>> result = null;

		Map<String, List<MenuVO>> menuMap = new HashMap<String, List<MenuVO>>();

		List<MenuVO> menuList = menuService.getMenuList_level1();
		
		menuMap.put("subMenuCode", menuList);
		result = new ResponseEntity<Map<String, List<MenuVO>>>(menuMap, HttpStatus.OK);
	
		return result;
		
	}*/
	
	@RequestMapping("/dsp/main")
	public ModelAndView mainPage(ModelAndView mnv) {
		String url = "commons/main_bootstrap";
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			dataMap = mainService.getImages();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mnv.addObject("dataMap",dataMap);
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping("/")
	public String moveMainPage() {
		String url = "/commons/main";
		return url;
	}
	
	@RequestMapping("/main2")
	public String page2() {
		String url = "commons/main_bootstrap";
		return url;
	}
	
	@RequestMapping("/dsp/main/img")
	@ResponseBody
	public ResponseEntity<byte[]> getMeetingRoomPictures(String fileName) {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;	//이미지 파일을 byte스트림으로 내보내기
		
		try {
			in = new FileInputStream(uploadPath + File.separator + fileName);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
					HttpStatus.CREATED);
			
		}catch(IOException e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
		}finally {
			try {
				if(in != null)in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}
	
	@RequestMapping("/dsp/guide")
	public ModelAndView showGuide(ModelAndView mnv) {
		String url = "commons/guide";
		mnv.setViewName(url);
		return mnv;
	}
	
}
