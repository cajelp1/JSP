package com.pumsac.pms.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.PersonalScheduleVO;

@Repository("pmsPersonalScheduleDao")
public class PmsPersonalScheduleDaoImpl implements PmsPersonalScheduleDao{

	@Autowired
	private SqlSession sqlsession;

	@Override
	public int selectSeqToPersonalSchedual() throws SQLException {
		return sqlsession.selectOne("personalSchedule-mapper.selectSeqToPersonalSchedual");
	}
	
	@Override
	public int selectPersonalScheduleAllCountForAdmin(PersonalScheduleVO personalScheduleVO) throws SQLException {
		return sqlsession.selectOne("personalSchedule-mapper.selectPersonalScheduleAllCountForAdmin", personalScheduleVO);
	}
	@Override
	public List<PersonalScheduleVO> selectPersonalScheduleAllForAdmin(Map<String, Object> map) throws SQLException {
		SearchCriteria cri = (SearchCriteria) map.get("cri");
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		return sqlsession.selectList("personalSchedule-mapper.selectPersonalScheduleAllForAdmin", map,rowBounds);
	}
	
	@Override
	public int selectPersonalScheduleCount(PersonalScheduleVO personalScheduleVO) throws SQLException {
		return sqlsession.selectOne("personalSchedule-mapper.selectPersonalScheduleCount", personalScheduleVO);
	}
	@Override
	public List<PersonalScheduleVO> selectPersonalScheduleList(Map<String, Object> map) throws SQLException {
		return sqlsession.selectList("personalSchedule-mapper.selectPersonalScheduleList", map);
	}

	@Override
	public void insertPersonalSchedule(PersonalScheduleVO personalScheduleVO) throws SQLException {
		sqlsession.update("personalSchedule-mapper.insertPersonalSchedule", personalScheduleVO);
	}

	@Override
	public PersonalScheduleVO selectDetailPersonalSchedule(int no) throws SQLException {
		return sqlsession.selectOne("personalSchedule-mapper.selectDetailPersonalSchedule", no);
	}

	@Override
	public void updatePersonalScheduleByUser(PersonalScheduleVO personalScheduleVO) throws SQLException {
		sqlsession.update("personalSchedule-mapper.updatePersonalScheduleByUser", personalScheduleVO);
	}

	@Override
	public void updatePersonalScheduleByAdmin(PersonalScheduleVO personalScheduleVO) throws SQLException {
		sqlsession.update("personalSchedule-mapper.updatePersonalScheduleByAdmin", personalScheduleVO);
	}

	@Override
	public void updatePersonalScheduleStatus(PersonalScheduleVO schedule) throws SQLException {
		sqlsession.update("personalSchedule-mapper.updatePersonalScheduleStatus", schedule);
	}

	@Override
	public void updateCompleteScheduleStatus(PersonalScheduleVO schedule) {
		sqlsession.update("personalSchedule-mapper.updateCompleteTaskStatus", schedule);
	}

	@Override
	public void deleteTaskByAdmin(int no) throws SQLException{
		sqlsession.update("personalSchedule-mapper.deleteTaskByAdmin",no);
	}

	@Override
	public List<PersonalScheduleVO> selectTaskForProjectManager(PersonalScheduleVO paramTask) throws SQLException {
		List<PersonalScheduleVO> list = new ArrayList<PersonalScheduleVO>();
		list = sqlsession.selectList("personalSchedule-mapper.selectTaskForProjectManager", paramTask);
		return list;
	}

}
