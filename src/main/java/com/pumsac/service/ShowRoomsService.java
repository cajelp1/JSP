package com.pumsac.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.pumsac.command.CriteriaRoomList;
import com.pumsac.vo.JusoVO;
import com.pumsac.vo.MeetingRoomAttachFileVO;
import com.pumsac.vo.MeetingRoomVO;

public interface ShowRoomsService {
	
	public List<JusoVO> getJusoMasterArea (JusoVO juso) throws Exception;
	
	public Map<String,Object> getMeetingRooms(CriteriaRoomList cri) throws Exception;
	
	public MeetingRoomVO getMeetingRoomDetail(int no) throws Exception;
	
	public MeetingRoomAttachFileVO getOfficeProfilePicture(int roomNo) throws SQLException;
	
	public List<MeetingRoomAttachFileVO> getMeetingRoomPictures(int roomNo) throws SQLException;
	
}
