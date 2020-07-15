package com.pumsac.dao;

import com.pumsac.command.MainImageDTO;
import com.pumsac.vo.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("mainDao")
public class MainDaoImpl implements MainDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<MainImageDTO> getRecentlyRegistedRoomProfileImg() throws SQLException {
		List<MainImageDTO> imgList = sqlSession.selectList("mainPage-mapper.getRecentlyRegistedRoomProfileImg"); 
		return imgList;
	}

	@Override
	public List<MainImageDTO> getHotplaceRoomProfileImg() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> getSiteNoticeForMain() throws SQLException {
		List<BoardVO> noticeList = sqlSession.selectList("mainPage-mapper.getSiteNoticeForMain");
		return noticeList;
	}

}
