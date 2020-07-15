package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dao.MemberDao;
import com.jsp.dao.MemberDaoImpl;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao memDao;
	private static MemberServiceImpl service;
	
	private MemberServiceImpl() {
		//클래스간의 결합도를 낮추기 위해 listener를 이용한 DI 방식을 사용. 
		//직접 instance를 생성하는 아래 부분은 주석처리한다.
		//memDao = MemberDaoImpl.getInstance();
	}
	
	//생성자에서 클래스를 만드는 것이 아니라 listener에서 주입하므로 이쪽에 setter 생성
	public void setMemDao(MemberDao memDao) {
		this.memDao = memDao;
	}
	
	public static MemberServiceImpl getInstance() {
		if(service == null) {
			service = new MemberServiceImpl();
		}
		return service;
	}
	
	
	
	@Override
	public void login(String id, String pwd) throws SQLException, NotFoundIDException, InvalidPasswordException {
		MemberVO member = memDao.selectMemberById(id);
		if(member == null) throw new NotFoundIDException();
		if(!pwd.equals(member.getPwd())) throw new InvalidPasswordException();
	}

	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		List<MemberVO> list = memDao.selectMemberList();
		return list;
	}

	@Override
	public MemberVO getMember(String id) throws SQLException {
		MemberVO member = memDao.selectMemberById(id);
		return member;
	}

	@Override
	public void regist(MemberVO member) throws SQLException {
		memDao.insertMember(member);
	}

	@Override
	public void modify(MemberVO member) throws SQLException {
		memDao.updateMember(member);
	}

	@Override
	public void remove(String id) throws SQLException {
		memDao.deleteMember(id);
	}

	@Override
	public void disable(String id) throws SQLException {
		memDao.disabledMember(id);
	}

	@Override
	public void enable(String id) throws SQLException {
		memDao.enabledMember(id);
	}

}
