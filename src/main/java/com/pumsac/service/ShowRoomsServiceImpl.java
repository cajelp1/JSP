package com.pumsac.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pumsac.command.CriteriaRoomList;
import com.pumsac.command.MainImageDTO;
import com.pumsac.command.PageMakerRoomList;
import com.pumsac.dao.AdminDao;
import com.pumsac.dao.ShowRoomsDao;
import com.pumsac.vo.JusoVO;
import com.pumsac.vo.MeetingRoomAttachFileVO;
import com.pumsac.vo.MeetingRoomVO;

@Repository("ShowRoomsService")
public class ShowRoomsServiceImpl implements ShowRoomsService {
	
	@Autowired
	private ShowRoomsDao showRoomsDao;
	
	/**
	 * 프로필 이미지 select하는 쿼리 접근하려고..
	 */
	@Autowired
	private AdminDao adminDao;
	
	@Override
	public List<JusoVO> getJusoMasterArea(JusoVO juso) throws Exception {
		List<JusoVO> jusoList = null;
		
		jusoList = showRoomsDao.selectJusoMasterArea(juso);
		
		return jusoList;
	}
	
	@Override
	public Map<String,Object> getMeetingRooms(CriteriaRoomList cri) throws Exception {
		
		Map<String,Object> dataMap = new HashMap<String, Object>();
		
		List<MeetingRoomVO> roomList = null;
		roomList = showRoomsDao.selectMeetingRooms(cri);
		List<MainImageDTO> imgList = showRoomsDao.selectOfficeProfileImg();
		int roomCount = showRoomsDao.countMeetingRooms(cri);
		PageMakerRoomList pageMaker = new PageMakerRoomList();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(roomCount);
		
//		System.out.println("totalCount : "+pageMaker.getTotalCount());
//		System.out.println("startPage : "+pageMaker.getStartPage());
//		System.out.println("endPage : "+pageMaker.getEndPage());
//		System.out.println("realEndPage : "+pageMaker.getRealEndPage());
		dataMap.put("img", imgList);
		dataMap.put("roomList", roomList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
	@Override
	public MeetingRoomVO getMeetingRoomDetail(int no) throws Exception {
		MeetingRoomVO meetingRoom = null;
		
		meetingRoom = showRoomsDao.selectMeetingRoomDetail(no);
		
		return meetingRoom;
	}
	
	@Override
	public MeetingRoomAttachFileVO getOfficeProfilePicture(int roomNo) throws SQLException {
		MeetingRoomAttachFileVO img = null;
		img = adminDao.selectMeetingRoomProfileFileByRoomNo(roomNo);
		return img;
	}
	
	@Override
	public List<MeetingRoomAttachFileVO> getMeetingRoomPictures(int roomNo) throws SQLException {
		List<MeetingRoomAttachFileVO> imgList = null;
		imgList = showRoomsDao.selectMeetingRoomAttachFile(roomNo);
		return imgList;
	}
	
	
}
