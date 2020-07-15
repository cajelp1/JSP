package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.dto.MenuVO;

public class MenuDAOImpl implements MenuDAO {

	@Autowired
	private SqlSession sqlSession;
	public void setSqlSession (SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<MenuVO> getMenuList_level1() throws SQLException {
		List<MenuVO> menuList = null;
		menuList = sqlSession.selectList("Menu-Mapper.getMenuList_level1",null);
		return menuList;
	}

}
