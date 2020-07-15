package com.pumsac.dao;

import java.sql.SQLException;
import java.util.List;

import com.pumsac.command.MeetingRoomJusoCommand;
import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.AdminCommonVO;
import com.pumsac.vo.MeetingRoomAttachFileVO;
import com.pumsac.vo.MeetingRoomVO;
import com.pumsac.vo.MemberVO;

public interface AdminDao {
	
	public List<MeetingRoomJusoCommand> selectMeetingRoomList(SearchCriteria cri) throws SQLException;
	
	public int countMeetingRoomList(SearchCriteria cri) throws SQLException;
	
	public MeetingRoomJusoCommand selectMeetingRoomDetail(int no) throws SQLException;
	
	public List<MeetingRoomAttachFileVO> getDetailImgByRoomNo(int roomNo) throws SQLException;
	
	public int selectMeetingRoomSeq() throws SQLException;
	
	public void insertMeetingRoomInfo(MeetingRoomVO meetingRoom) throws SQLException;
	
	public void updateMeetingRoomInfo(MeetingRoomVO meetingRoom) throws SQLException;
	
	public void deleteMeetingRoomInfo(MeetingRoomVO meetingRoom) throws SQLException;
	
	public int selectMeetingRoomAttachSeq() throws SQLException;
	
	public void insertMeetingRoomAttachFile(MeetingRoomAttachFileVO attachFile) throws SQLException;
	
	public void deleteAllMeetingRoomAttachFiles(int roomNo) throws SQLException;
	
	public void deleteMeetingRoomAttachFileByNo(int no) throws SQLException;
	
	
	public List<MemberVO> selectMember(SearchCriteria cri) throws SQLException;
	
	public int countMember(SearchCriteria cri) throws SQLException;
	
	public MemberVO selectMemberByEmail(String email) throws SQLException;
	
	public void updateMember(MemberVO member) throws SQLException;
	
	public int getJusoNoByArea(String area) throws SQLException;

	public List<MeetingRoomAttachFileVO> getAttachFileListByRoomNo(int roomNo);
	
	public MeetingRoomAttachFileVO selectAttachFileByNo(int no) throws SQLException;
	
	public MeetingRoomAttachFileVO selectMeetingRoomProfileFileByRoomNo(int no) throws SQLException;

	public void deleteMeetingRoomAttachByOriginFileName(MeetingRoomAttachFileVO profile);

	public AdminCommonVO selectPrivacyRule(String item) throws  SQLException;
	public void updatePrivacyRule(AdminCommonVO adminCommonVO) throws SQLException;
}
