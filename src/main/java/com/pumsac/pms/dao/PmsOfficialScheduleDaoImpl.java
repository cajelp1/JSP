package com.pumsac.pms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.OffScheduleDetailVO;
import com.pumsac.vo.OfficialScheduleVO;
import com.pumsac.vo.PlanVO;

@Repository("pmsOfficialScheduleDao")
public class PmsOfficialScheduleDaoImpl implements PmsOfficialScheduleDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int selectSeqToOfficialSchedual() throws SQLException {
		return sqlSession.selectOne("officalSchedule-mapper.selectSeqToOfficialSchedual");
	}
	
	@Override
	public int selectOfficalScheduleCount(int projectId) throws SQLException {
		return sqlSession.selectOne("officalSchedule-mapper.selectOfficalScheduleCount", projectId);
	}
	
	@Override
	public List<PlanVO> selectOfficalScheduleList(Map<String, Object> map) {
		SearchCriteria cri = (SearchCriteria)map.get("cri");
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset,limit);
		List<PlanVO> list = null;
		list = sqlSession.selectList("officalSchedule-mapper.selectOfficalScheduleListMap", map, rowBounds);
		return list;
	}
	
	@Override
	public void insertOffcialSchedule(PlanVO officialScheduleVO) throws SQLException {
		sqlSession.update("officalSchedule-mapper.insertOffcialSchedule", officialScheduleVO);
	}

	@Override
	public PlanVO selectDetailOfficialSchedule(int no) throws SQLException {
		return sqlSession.selectOne("officalSchedule-mapper.selectDetailOfficialSchedule", no);
	}
	
	@Override
	public void updateOffcialSchedule(PlanVO officialScheduleVO) throws SQLException {
		sqlSession.update("officalSchedule-mapper.updateOffcialSchedule", officialScheduleVO);
	}

	@Override
	public void deleteOfficialSchedule(int no) throws SQLException {
		sqlSession.delete("officalSchedule-mapper.deleteOfficialSchedule", no);
	}

}

