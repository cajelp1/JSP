package com.pumsac.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumsac.dao.MemberDao;
import com.pumsac.dao.PaymentDao;
import com.pumsac.exception.EmailNotFoundException;
import com.pumsac.exception.EmailOrPasswordNotFoundException;
import com.pumsac.exception.NicknameNotFoundException;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.ReservationForMyPageVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private PaymentDao payDao;
	
	@Override
	public MemberVO getMember(String email, String password) throws Exception {
		
		MemberVO memberVo = memberDao.selectMemberByEmail(email);
		if(memberVo==null || !memberVo.getPassword().equals(password)) {
			throw new EmailOrPasswordNotFoundException();
		}
		return memberVo;
	}
	
	@Override
	public MemberVO getMemberEmail(MemberVO member) throws SQLException {
		return memberDao.selectMemberByNameAndPhone(member);
	}

	@Override
	public MemberVO getMemberInfoFindPassword(MemberVO member) throws Exception {
		
		MemberVO member2 = memberDao.selectMemberByNameAndPhoneAndEmail(member);
		
		return member2;
	}
	
	@Override
	public void modifyMemberPassword(MemberVO member) throws Exception {
		memberDao.updateEncryptionPassword(member);
	}

	@Override
	public void registMember(MemberVO member) throws SQLException, NoSuchAlgorithmException {
		memberDao.registMember(member);
	}

	@Override
	public void modifyMemberInfo(MemberVO member) throws SQLException {
		memberDao.updateMemberInfo(member);
	}

	@Override
	public void removeMember(String email) throws SQLException {
		memberDao.deleteMember(email);
	}

	@Override
	public MemberVO checkEmail(String email) throws SQLException, EmailNotFoundException {
		MemberVO member = memberDao.selectMemberByEmail(email);
		if(member == null) {
			throw new EmailNotFoundException();
		}
		return member;
	}

	@Override
	public List<ReservationForMyPageVO> getReservationList(String email) throws SQLException {
		List<ReservationForMyPageVO> list = null;

		list = payDao.getReservationList(email);
		
		return list;
	}

	@Override
	public MemberVO checkNickname(String nickname) throws SQLException, NicknameNotFoundException {
		MemberVO member = memberDao.selectMemberByNickname(nickname);
		if(member == null) {
			throw new NicknameNotFoundException();
		}
		return member;
		
	}

	@Override
	public List<MemberVO> getAdminList() throws SQLException {
		return memberDao.selectAdminList();
	}

}
