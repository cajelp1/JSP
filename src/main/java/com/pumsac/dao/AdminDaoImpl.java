package com.pumsac.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pumsac.vo.AdminCommonVO;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pumsac.command.MeetingRoomJusoCommand;
import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.MeetingRoomAttachFileVO;
import com.pumsac.vo.MeetingRoomVO;
import com.pumsac.vo.MemberVO;

@Repository("AdminDao")
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<MeetingRoomJusoCommand> selectMeetingRoomList(SearchCriteria cri) throws SQLException {
		List<MeetingRoomJusoCommand> roomJusoList = null;
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		roomJusoList = sqlSession.selectList("admin-mapper.selectMeetingRoomList", cri, rowBounds);
		
		return roomJusoList;
	}
	
	@Override
	public int countMeetingRoomList(SearchCriteria cri) throws SQLException {
		int count = 0;
		count = sqlSession.selectOne("admin-mapper.countMeetingRoomList", cri);
		return count;
	}

	@Override
	public MeetingRoomJusoCommand selectMeetingRoomDetail(int no) throws SQLException {
		MeetingRoomJusoCommand room = null;
		room = sqlSession.selectOne("admin-mapper.selectMeetingRoomDetail", no);
		return room;
	}

	@Override
	public List<MeetingRoomAttachFileVO> getDetailImgByRoomNo(int roomNo) throws SQLException {
		List<MeetingRoomAttachFileVO> list = null;
		list = sqlSession.selectList("admin-mapper.getDetailImgByRoomNo", roomNo);
		return list;
	}
	
	
	@Override
	public int selectMeetingRoomSeq() throws SQLException {
		int seq = -1;
		seq = sqlSession.selectOne("admin-mapper.selectMeetingRoomSeq");
		return seq;
	}

	@Override
	public void insertMeetingRoomInfo(MeetingRoomVO meetingRoom) throws SQLException {
		sqlSession.update("admin-mapper.insertMeetingRoomInfo", meetingRoom);
	}

	@Override
	public void updateMeetingRoomInfo(MeetingRoomVO meetingRoom) throws SQLException {
		sqlSession.update("admin-mapper.updateMeetingRoomInfo", meetingRoom);
	}

	@Override
	public void deleteMeetingRoomInfo(MeetingRoomVO meetingRoom) throws SQLException {
		sqlSession.update("admin-mapper.deleteMeetingRoomInfo", meetingRoom);
	}
	
	
	@Override
	public int selectMeetingRoomAttachSeq() throws SQLException {
		int seq = -1;
		seq = sqlSession.selectOne("admin-mapper.selectMeetingRoomAttachSeq", null);
		return seq;
	}

	@Override
	public void insertMeetingRoomAttachFile(MeetingRoomAttachFileVO attachFile) throws SQLException {
		sqlSession.update("admin-mapper.insertMeetingRoomAttachFile", attachFile);
	}

	@Override
	public void deleteAllMeetingRoomAttachFiles(int roomNo) throws SQLException {
		sqlSession.update("admin-mapper.deleteAllMeetingRoomAttachFile", roomNo);
	}

	@Override
	public void deleteMeetingRoomAttachFileByNo(int no) throws SQLException {
		sqlSession.update("admin-mapper.deleteMeetingRoomAttachFileByNo", no);
	}
	
	
	@Override
	public List<MemberVO> selectMember(SearchCriteria cri) throws SQLException{
		List<MemberVO> list = null;
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		list = sqlSession.selectList("admin-member-mapper.selectMember", cri, rowBounds);
		return list;
	}
	
	@Override
	public int countMember(SearchCriteria cri) throws SQLException {
		int count = 0;
		count = (int)sqlSession.selectOne("admin-member-mapper.countMember", cri);
		return count;
	}
	
	@Override
	public MemberVO selectMemberByEmail(String email) throws SQLException {
		MemberVO member = null;
		member = sqlSession.selectOne("admin-member-mapper.selectMemberByEmail", email);
		return member;
	}
	
	@Override
	public void updateMember(MemberVO member) throws SQLException {
		sqlSession.update("admin-member-mapper.updateMember", member);
	}

	@Override
	public int getJusoNoByArea(String area) throws SQLException {
		return sqlSession.selectOne("admin-mapper.getJusoNoByArea",area);
	}

	@Override
	public List<MeetingRoomAttachFileVO> getAttachFileListByRoomNo(int roomNo) {
		List<MeetingRoomAttachFileVO> list = new ArrayList<MeetingRoomAttachFileVO>();
		list = sqlSession.selectList("admin-mapper.getAttachFileListByRoomNo",roomNo);
		return list;
	}

	@Override
	public MeetingRoomAttachFileVO selectAttachFileByNo(int no) throws SQLException {
		MeetingRoomAttachFileVO attach = new MeetingRoomAttachFileVO();
		attach = sqlSession.selectOne("admin-mapper.selectAttachFileByNo",no);
		return attach;
	}

	@Override
	public MeetingRoomAttachFileVO selectMeetingRoomProfileFileByRoomNo(int no) throws SQLException {
		MeetingRoomAttachFileVO attach = new MeetingRoomAttachFileVO();
		attach = sqlSession.selectOne("admin-mapper.selectMeetingRoomProfileFileByRoomNo",no);
		return attach;
	}

	@Override
	public void deleteMeetingRoomAttachByOriginFileName(MeetingRoomAttachFileVO profile) {
		sqlSession.update("admin-mapper.deleteMeetingRoomAttachByOriginFileName",profile);
	}

	@Override
	public AdminCommonVO selectPrivacyRule(String item) throws SQLException {
		return sqlSession.selectOne("admin-mapper.selectPrivacyRule",item);
	}

	@Override
	public void updatePrivacyRule(AdminCommonVO adminCommonVO) throws SQLException {
		sqlSession.update("admin-mapper.updatePrivacyRule",adminCommonVO);
	}
}
