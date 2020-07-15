package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.dao.MemberDAO;
import com.jsp.dao.MemberDAOImpl;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;

public class MemberServiceImpl implements MemberService {

	private MemberDAO memberDao;
	
	//리스너에서 인스턴스 생성도 다 하니까 싱글톤은 지워버리쟈
//	private static MemberServiceImpl service;
//	private MemberServiceImpl() {
//	}
//	
//	public static MemberServiceImpl getInstance() {
//		if (service == null) {
//			service = new MemberServiceImpl();
//		}
//		return service;
//	}
	
	// dao를 listener로 받아서 써보쟈
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDao = memberDAO;
	}
	
	@Override
	public void login(String id, String pwd) throws SQLException, NotFoundIDException, InvalidPasswordException {
		MemberVO member = memberDao.selectMemberById(id);
		if (member == null)
			throw new NotFoundIDException();
		if (!pwd.equals(member.getPwd()))
			throw new InvalidPasswordException();
	}

	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		List<MemberVO> list = memberDao.selectMemberList();
		return list;
	}

	@Override
	public MemberVO getMember(String id) throws SQLException {
		MemberVO member = memberDao.selectMemberById(id);
		return member;
	}

	@Override
	public void regist(MemberVO member) throws SQLException {
		memberDao.insertMember(member);
	}

	@Override
	public void modify(MemberVO member) throws SQLException {
		memberDao.updateMember(member);
	}

	@Override
	public void remove(String id) throws SQLException {
		memberDao.deleteMember(id);
	}

	@Override
	public void disable(String id) throws SQLException {
		memberDao.disabledMember(id);
	}

	@Override
	public void enable(String id) throws SQLException {
		memberDao.enabledMember(id);
	}

	@Override
	public Map<String, Object> getMemberList(SearchCriteria cri) throws SQLException {
		List<MemberVO> memberList = memberDao.selectMemberList(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);	//얘를 먼저 세팅 안해주면 터짐! pageMaker의 데이터 플로우가 그러함!
		pageMaker.setTotalCount(memberDao.selectMemberListCount(cri));
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("memberList", memberList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

}
