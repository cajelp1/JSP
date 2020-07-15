package com.pumsac.pms.service;

import com.pumsac.vo.NotHistoryVO;

import java.sql.SQLException;
import java.util.Map;

public interface PmsNotHistoryService {
	
	Map<String, Object> getNotHistoryList(Map<String, Object> map) throws SQLException;
	
	Map<String, Object> getNotHistoryListInfoReadN(Map<String, Object> map) throws SQLException;
	
	void registNotHistory(NotHistoryVO notHistoryVO) throws SQLException;

	/**
	 * @내용 읽지않은 알림클릭시 해당 알림을 읽음상태로 전환
	 */
	void modifyReadYN(int no) throws SQLException;

	NotHistoryVO getNotHistoryDetail(int no) throws SQLException;
}
