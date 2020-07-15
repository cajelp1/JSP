package com.jsp.service;

import java.sql.SQLException;

import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;

public class MemberServiceImpl implements MemberService {

	private MemberDAO memberDao;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDao = memberDAO;
	}
	
	@Override
	public MemberVO login(String id, String pwd) throws SQLException {
		MemberVO member = memberDao.selectMemberById(id);
		return member;
	}
}



