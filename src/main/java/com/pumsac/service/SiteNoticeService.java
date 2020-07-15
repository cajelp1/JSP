package com.pumsac.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.BoardVO;

public interface SiteNoticeService {
	
	public void registSiteNotice(BoardVO board) throws SQLException;
	
	public List<BoardVO> getAllSiteNotice() throws SQLException;
	
	public Map<String, Object> getSiteNotice(SearchCriteria cri) throws SQLException;
	
	public void modifySiteNotice(BoardVO board) throws SQLException;

	public void removeSiteNotice(int sno) throws SQLException;
	
	public BoardVO getSiteNoticeDetail(int sno, HttpSession session) throws SQLException;
}
