package com.pumsac.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import com.pumsac.exception.EmailNotFoundException;
import com.pumsac.exception.NicknameNotFoundException;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.ReservationForMyPageVO;

public interface MemberService {
	
	public MemberVO getMember(String email, String password) throws Exception;
	
	public MemberVO getMemberEmail(MemberVO member) throws SQLException;
	
	public MemberVO getMemberInfoFindPassword(MemberVO member) throws Exception;
	
	public void modifyMemberPassword(MemberVO member) throws Exception;
	
	public void registMember(MemberVO member) throws SQLException, NoSuchAlgorithmException;
	
	public void modifyMemberInfo(MemberVO member) throws SQLException;
	
	public void removeMember(String email) throws SQLException;
	
	public MemberVO checkEmail(String email) throws SQLException, EmailNotFoundException;

	public List<ReservationForMyPageVO> getReservationList(String email) throws SQLException;

	public MemberVO checkNickname(String nickname) throws SQLException, NicknameNotFoundException;

	
	/**
	 * @내용 site의 관리자목록을 불러오는 메소드
	 */
	public List<MemberVO> getAdminList() throws SQLException;

}
