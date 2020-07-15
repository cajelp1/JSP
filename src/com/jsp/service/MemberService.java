package com.jsp.service;

import java.sql.SQLException;

import com.jsp.dto.MemberVO;

public interface MemberService {
	
	//로그인
	MemberVO login(String id, String pwd) throws SQLException;
	
}
