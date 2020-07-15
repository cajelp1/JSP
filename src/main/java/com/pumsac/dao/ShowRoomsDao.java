package com.pumsac.dao;

import java.sql.SQLException;
import java.util.List;

import com.pumsac.command.CriteriaRoomList;
import com.pumsac.command.MainImageDTO;
import com.pumsac.vo.JusoVO;
import com.pumsac.vo.MeetingRoomAttachFileVO;
import com.pumsac.vo.MeetingRoomVO;

public interface ShowRoomsDao {
	
	public List<JusoVO> selectJusoMasterArea(JusoVO juso) throws SQLException;
	
	public List<MeetingRoomVO> selectMeetingRooms(CriteriaRoomList cri) throws SQLException;
	
	public int countMeetingRooms(CriteriaRoomList cri) throws SQLException;
	
	public MeetingRoomVO selectMeetingRoomDetail(int no) throws SQLException;
	
	public List<MainImageDTO> selectOfficeProfileImg() throws SQLException;
	
	public List<MeetingRoomAttachFileVO> selectMeetingRoomAttachFile(int roomNo) throws SQLException;
	
}
