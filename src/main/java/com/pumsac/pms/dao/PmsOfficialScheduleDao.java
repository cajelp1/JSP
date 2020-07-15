package com.pumsac.pms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.pumsac.vo.OffScheduleDetailVO;
import com.pumsac.vo.OfficialScheduleVO;
import com.pumsac.vo.PlanVO;

public interface PmsOfficialScheduleDao {
	
	// 시퀀스 가져오기
	int selectSeqToOfficialSchedual() throws SQLException;
	
	// 갯수 가져오기
	int selectOfficalScheduleCount(int projectId) throws SQLException;
	
	// 리스트 가져오기
	List<PlanVO> selectOfficalScheduleList(Map<String, Object> map);

	void insertOffcialSchedule(PlanVO officialScheduleVO) throws SQLException;

	PlanVO selectDetailOfficialSchedule(int no) throws SQLException;

	void updateOffcialSchedule(PlanVO officialScheduleVO) throws SQLException;
	
	void deleteOfficialSchedule(int no) throws SQLException;

	
}
