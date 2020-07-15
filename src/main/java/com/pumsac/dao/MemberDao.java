package com.pumsac.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import com.pumsac.vo.MemberVO;
import org.apache.ibatis.jdbc.SQL;

public interface MemberDao {

	public MemberVO selectMemberByEmail(String email) throws SQLException;

	public MemberVO selectMemberByNameAndPhone(MemberVO member) throws SQLException;
	
	public MemberVO selectMemberByNameAndPhoneAndEmail(MemberVO member) throws SQLException;

	public void updatePassword(MemberVO member) throws SQLException;

	public void registMember(MemberVO member) throws SQLException, NoSuchAlgorithmException;

	//회원 정보 수정
	public void updateMemberInfo(MemberVO member) throws SQLException;

	//회원 탈퇴
	public void deleteMember(String email) throws SQLException;
	//문자 이력 테이블 시퀀스
	int selectSmsHistorySeq () throws SQLException;

	// 닉네임 중복체크하는 메소드
	public MemberVO selectMemberByNickname(String nickname);
	public List<MemberVO> selectAllMember() throws SQLException;
	void updateEncryptionPassword(MemberVO vo)throws SQLException;

	public List<MemberVO> selectAdminList() throws SQLException;
}
