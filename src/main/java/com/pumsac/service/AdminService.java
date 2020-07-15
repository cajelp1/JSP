package com.pumsac.service;

import java.sql.SQLException;
import java.util.Map;

import com.pumsac.command.MeetingRoomJusoCommand;
import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.AdminCommonVO;
import com.pumsac.vo.MeetingRoomVO;
import com.pumsac.vo.MemberVO;

public interface AdminService {

    //사무실 등록
    public void registMeetingRoom(MeetingRoomJusoCommand meetingRoom) throws Exception;

    //사무실 번호 상세 조회
    public MeetingRoomJusoCommand getMeetingRoomDetail(int no) throws Exception;

    public Map<String, Object> getMeetingRoomList(SearchCriteria cri) throws Exception;


    public void modifyMeetingRoom(MeetingRoomJusoCommand meetingRoom) throws SQLException;

    public Map<String, Object> getMemberList(SearchCriteria cri) throws Exception;

    public MemberVO getMemberDetail(String email) throws Exception;

    public void modifyMember(MemberVO member) throws Exception;

    public void removeMeetingRoom(MeetingRoomVO meetingRoom) throws Exception;

    public void removeAttachFile(int no) throws SQLException;
    //푸터에있는 개인정보 방침 읽기 
    public AdminCommonVO getPrivacyRule(String item) throws SQLException;
	//푸터에있는 개인정보 방침 수정 
    public  AdminCommonVO  modifyPrivacyRule(String item) throws  SQLException;

}

