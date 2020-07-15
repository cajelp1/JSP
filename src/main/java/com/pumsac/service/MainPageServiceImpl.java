package com.pumsac.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumsac.command.MainImageDTO;
import com.pumsac.dao.MainDao;
import com.pumsac.vo.BoardVO;

@Service("mainPageService")
public class MainPageServiceImpl implements MainPageService {

	@Autowired
	private MainDao mainDao;
	
	@Override
	public Map<String, Object> getImages() throws SQLException {
		Map<String, Object> dataMap = new HashMap<>();
		List<MainImageDTO> recentRoom = mainDao.getRecentlyRegistedRoomProfileImg();
		List<BoardVO> noticeList = mainDao.getSiteNoticeForMain();
		dataMap.put("recentRoom", recentRoom);
		dataMap.put("noticeList", noticeList);
		return dataMap;
	}

}
