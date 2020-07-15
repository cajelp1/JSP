package com.jsp.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.jsp.dto.MemberVO;

import ibatis.config.SqlMapClientFactroy;

public class LoginDaoImpl implements ILoginDao {
	
	private SqlMapClient client;
	private static ILoginDao dao;
	
	private LoginDaoImpl(){
		client = SqlMapClientFactroy.getSMClient();
	}
	
	public static ILoginDao getInstance() {
		if(dao == null) {
			dao = new LoginDaoImpl();
		}
		return dao;
	}
	
	@Override
	public MemberVO isLogin(MemberVO mem) {
		MemberVO res = null;
		
		try {
			res = (MemberVO)client.queryForObject("login.isLogin", mem);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
}
