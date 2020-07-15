package com.pumsac.pms.service;

import com.pumsac.command.PageMaker;
import com.pumsac.command.SearchCriteria;
import com.pumsac.dao.MemberDao;
import com.pumsac.pms.dao.PmsNotHistoryDao;
import com.pumsac.vo.NotHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("notHistoryService")
public class PmsNotHistoryServiceImpl implements PmsNotHistoryService{
	
	@Autowired
	private PmsNotHistoryDao notHistoryDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public Map<String, Object> getNotHistoryList(Map<String, Object> map) throws SQLException {

		SearchCriteria cri = (SearchCriteria) map.get("cri");
		int totalCount = notHistoryDao.selectNotHistoryTotalCount(map);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		List<NotHistoryVO> notHistorylist = notHistoryDao.selectNotHistoryList(map);
		
		dataMap.put("notHistorylist", notHistorylist);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

	@Override
	public Map<String, Object> getNotHistoryListInfoReadN(Map<String, Object> map) throws SQLException {
		SearchCriteria cri = (SearchCriteria) map.get("cri");
		int totalCount = notHistoryDao.selectNotHistoryTotalCountReadN(map);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		List<NotHistoryVO> notHistorylist = notHistoryDao.selectNotHistoryListReadN(map);
		
		dataMap.put("notHistorylist", notHistorylist);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	
	@Override
	public void modifyReadYN(int no) throws SQLException {
		notHistoryDao.updateReadYN(no);
	}

	@Override
	public void registNotHistory(NotHistoryVO notHistoryVO) throws SQLException {
		notHistoryDao.InsertNotHistory(notHistoryVO);
	}

	@Override
	public NotHistoryVO getNotHistoryDetail(int no) throws SQLException {
		return notHistoryDao.getNotHistoryDetail(no);
	}

}
