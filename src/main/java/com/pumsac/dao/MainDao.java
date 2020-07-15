package com.pumsac.dao;

import java.sql.SQLException;
import java.util.List;

import com.pumsac.command.MainImageDTO;
import com.pumsac.vo.BoardVO;

public interface MainDao {
	
	public List<MainImageDTO> getRecentlyRegistedRoomProfileImg() throws SQLException;
	
	public List<MainImageDTO> getHotplaceRoomProfileImg() throws SQLException;
	
	public List<BoardVO> getSiteNoticeForMain() throws SQLException;
}
