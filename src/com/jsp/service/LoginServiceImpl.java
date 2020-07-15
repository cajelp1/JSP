package com.jsp.service;

import com.jsp.dao.ILoginDao;
import com.jsp.dao.LoginDaoImpl;
import com.jsp.dto.MemberVO;

public class LoginServiceImpl implements ILoginService {

	private ILoginDao dao;
	private static ILoginService service;
	
	private LoginServiceImpl() {
		dao = LoginDaoImpl.getInstance();
	}
	public static ILoginService getInstance() {
		if(service == null) {
			service = new LoginServiceImpl();
		}
		return service;
	}
	
	
	@Override
	public MemberVO isLogin(MemberVO mem) {
		// TODO Auto-generated method stub
		return dao.isLogin(mem);
	}
	
}
