package com.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.dto.MenuVO;
import com.spring.service.MenuService;

@Controller	
public class MainController {

//	@Autowired
//	private MenuService menuService;
	
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
	@RequestMapping("/main")
	public void mainPage() {}
	
	@RequestMapping("/")
	public String moveMainPage() {
		String url = "/commons/main";
		return url;
	}

}
