package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring.dao.MenuDAO;
import com.spring.dto.MenuVO;

public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDAO menuDAO;
	public void setMenuDAO (MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
	
	@Override
	public List<MenuVO> getMenuList_level1() throws SQLException {
		List<MenuVO> menuList = menuDAO.getMenuList_level1();
		return menuList;
	}

}
