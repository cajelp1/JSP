package com.pumsac.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pumsac.command.CriteriaRoomList;
import com.pumsac.command.MainImageDTO;
import com.pumsac.vo.JusoVO;
import com.pumsac.vo.MeetingRoomAttachFileVO;
import com.pumsac.vo.MeetingRoomVO;

@Repository("ShowRoomsDao")
public class ShowRoomsDaoImpl implements ShowRoomsDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<JusoVO> selectJusoMasterArea(JusoVO juso) throws SQLException {
		List<JusoVO> jusoList = null;
		
		jusoList = sqlSession.selectList("rooms-mapper.selectJusoMasterArea", juso);
		
		return jusoList;
	}

	@Override
	public List<MeetingRoomVO> selectMeetingRooms(CriteriaRoomList cri) throws SQLException{
		List<MeetingRoomVO> roomList = null;
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		roomList = sqlSession.selectList("rooms-mapper.selectMeetingRooms", cri, rowBounds);
		
		return roomList;
	};
	
	@Override
	public int countMeetingRooms(CriteriaRoomList cri) throws SQLException{
		int count = 0;
		
		count = sqlSession.selectOne("rooms-mapper.countMeetingRooms", cri);
		
		return count;
	}

	@Override
	public MeetingRoomVO selectMeetingRoomDetail(int no) throws SQLException {
		MeetingRoomVO meetingRoom = null;
		
		meetingRoom = sqlSession.selectOne("rooms-mapper.selectMeetingRoomDetail", no);
		
		return meetingRoom;
	}

	@Override
	public List<MainImageDTO> selectOfficeProfileImg() throws SQLException {
		List<MainImageDTO> imgList = sqlSession.selectList("rooms-mapper.selectOfficeProfileImg");
		return imgList;
	}

	@Override
	public List<MeetingRoomAttachFileVO> selectMeetingRoomAttachFile(int roomNo) throws SQLException {
		List<MeetingRoomAttachFileVO> imgList = null;
		imgList = sqlSession.selectList("rooms-mapper.selectMeetingRoomAttachFile", roomNo);
		return imgList;
	}
	
}
