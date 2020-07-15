package com.pumsac.pms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.pumsac.vo.PersonalScheduleVO;

public interface PmsPersonalScheduleDao {
	
	int selectSeqToPersonalSchedual() throws SQLException;

	int selectPersonalScheduleAllCountForAdmin(PersonalScheduleVO personalScheduleVO) throws SQLException;
	List<PersonalScheduleVO> selectPersonalScheduleAllForAdmin(Map<String, Object> map) throws SQLException;
	
	int selectPersonalScheduleCount(PersonalScheduleVO personalScheduleVO) throws SQLException;
	List<PersonalScheduleVO> selectPersonalScheduleList(Map<String, Object> map) throws SQLException;

	void insertPersonalSchedule(PersonalScheduleVO personalScheduleVO) throws SQLException;

	PersonalScheduleVO selectDetailPersonalSchedule(int no) throws SQLException;

	void updatePersonalScheduleByUser(PersonalScheduleVO personalScheduleVO) throws SQLException;

	void updatePersonalScheduleByAdmin(PersonalScheduleVO personalScheduleVO) throws SQLException;

	void updatePersonalScheduleStatus(PersonalScheduleVO schedule) throws SQLException;

	void updateCompleteScheduleStatus(PersonalScheduleVO schedule);

	void deleteTaskByAdmin(int no) throws SQLException;

	List<PersonalScheduleVO> selectTaskForProjectManager(PersonalScheduleVO paramTask) throws SQLException;

}
