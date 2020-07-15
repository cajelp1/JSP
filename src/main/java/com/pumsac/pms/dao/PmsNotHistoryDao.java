package com.pumsac.pms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.pumsac.vo.NotHistoryVO;

public interface PmsNotHistoryDao {
	
	int selectNotHistoryTotalCount(Map<String, Object> map) throws SQLException;
	List<NotHistoryVO> selectNotHistoryList(Map<String, Object> map) throws SQLException;
	
	int selectNotHistoryTotalCountReadN(Map<String, Object> map) throws SQLException;
	List<NotHistoryVO> selectNotHistoryListReadN(Map<String, Object> map) throws SQLException;
	
	void InsertNotHistory(NotHistoryVO historyVO) throws SQLException;

	void updateReadYN(int no) throws SQLException;
	
	int selectSeq() throws SQLException;
	
	NotHistoryVO getNotHistoryDetail(int no) throws SQLException;
	
}
