package com.pumsac.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pumsac.util.PasswordEncryption;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pumsac.vo.MemberVO;

@Repository("memberDao")
public class MemberDaoImpl implements MemberDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public MemberVO selectMemberByEmail(String email) throws SQLException {
		MemberVO member = null;
		
		member = sqlSession.selectOne("member-mapper.selectMemberByEmail", email);

		return member;
	}

	@Override
	public MemberVO selectMemberByNameAndPhone(MemberVO member) throws SQLException {
		MemberVO result = null;

		result = sqlSession.selectOne("member-mapper.selectMemberByNameAndPhone", member);

		return result;
	}

	@Override
	public void updatePassword(MemberVO member) throws SQLException {
		sqlSession.update("member-mapper.updatePassword", member);
	}

	@Override
	public void registMember(MemberVO member) throws SQLException, NoSuchAlgorithmException {
		member.setPassword(PasswordEncryption.passwordEncryption(member.getPassword()));

		sqlSession.update("member-mapper.registMember", member);

	}

	@Override
	public void updateMemberInfo(MemberVO member) throws SQLException {
		sqlSession.update("member-mapper.updateMemberInfo", member);
	}

	@Override
	public void deleteMember(String email) throws SQLException {
		sqlSession.update("member-mapper.deleteMember", email);
	}

	@Override
	public int selectSmsHistorySeq() throws SQLException {
		return sqlSession.selectOne("member-mapper.selectSmsHistorySeq");
	}

	@Override
	public MemberVO selectMemberByNickname(String nickname) {
		MemberVO member = new MemberVO();
		member = sqlSession.selectOne("member-mapper.selectMemberByNickname",nickname);
		return member;
	}

	@Override
	public List<MemberVO> selectAllMember() throws SQLException {
		return sqlSession.selectList("member-mapper.selectAllMember");
	}

	@Override
	public void updateEncryptionPassword(MemberVO vo) throws SQLException {
		sqlSession.update("member-mapper.updateEncryptionPassword",vo);
	}

	@Override
	public List<MemberVO> selectAdminList() throws SQLException {
		return sqlSession.selectList("member-mapper.selectAdminList");
	}

	@Override
	public MemberVO selectMemberByNameAndPhoneAndEmail(MemberVO member) throws SQLException {
		return sqlSession.selectOne("member-mapper.selectMemberByNameAndPhoneAndEmail", member);
	}
}
