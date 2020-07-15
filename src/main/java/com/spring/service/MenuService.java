package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.MenuVO;

public interface MenuService {

	public List<MenuVO> getMenuList_level1() throws SQLException; 
}
