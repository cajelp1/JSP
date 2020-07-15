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
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.OfficialScheduleVO;
import com.pumsac.vo.PersonalScheduleVO;
import com.pumsac.vo.ProTeamVO;
import com.pumsac.vo.ProjectVO;

@Repository("pmsDashBoardDao")
public class PmsDashBaordDaoImpl implements PmsDashBoardDao {
    @Autowired
    SqlSession sqlSession;

    /*@Override
    public List<Map<String, String>> selectProTeamNatureJoinProMasterByProjectId(SearchCriteria cri) throws SQLException {
        int offset = cri.getPageStartRowNum();
        int limit = cri.getPerPageNum();
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<Map<String,String>> list = sqlSession.selectList("pms-dashboard-mapper.selectProTeamNatureJoinProMasterByProjectId",cri,rowBounds);
        return list;
    }
*/
    @Override
    public List<Map<String, String>> selectProTeamNatureJoinProMasterByProjectIdOrderByCondition(SearchCriteria cri) throws SQLException {
        int offset = cri.getPageStartRowNum();
        int limit = cri.getPerPageNum();
        RowBounds rowBounds = new RowBounds(offset, limit);
        List<Map<String,String>> list = sqlSession.selectList("pms-dashboard-mapper.selectProTeamNatureJoinProMasterByProjectIdOrderByCondition",cri,rowBounds);
        return  list;
    }

	@Override
	public ProjectVO selectProjectInfoByProjcetId(int projectId) throws SQLException {
		ProjectVO project = new ProjectVO();
		project = sqlSession.selectOne("pms-dashboard-mapper.selectProjectInfoByProjcetId",projectId);
		return project;
	}

	@Override
	public List<OfficialScheduleVO> selectProjectOfficialSchedule(int projectId) throws SQLException {
		List<OfficialScheduleVO> schedule = new ArrayList<OfficialScheduleVO>();
		schedule = sqlSession.selectList("pms-dashboard-mapper.selectProjectOfficialSchedule", projectId);
		return schedule;
	}

	@Override
	public List<ProTeamVO> selectProjectTeamMember(int projectId) throws SQLException{
		List<ProTeamVO> team = new ArrayList<ProTeamVO>();
		team = sqlSession.selectList("pms-dashboard-mapper.selectProjectTeamMember", projectId);
		return team;
	}

	@Override
	public List<PersonalScheduleVO> selectRecentPersonalSchedule(int projectId) throws SQLException{
		List<PersonalScheduleVO> schedule = new ArrayList<PersonalScheduleVO>();
		schedule = sqlSession.selectList("pms-dashboard-mapper.selectRecentPersonalSchedule", projectId);
		return schedule;
	}

	@Override
	public int selectCountPersonalSchedule(int projectId) throws SQLException{
		int count = 0;
		count = sqlSession.selectOne("pms-dashboard-mapper.selectCountPersonalSchedule", projectId);
		return count;
	}

	@Override
	public float selectTotalProcessPercent(int projectId) throws SQLException {
		float percent = 0f;
		percent = sqlSession.selectOne("pms-dashboard-mapper.selectTotalProcessPercent", projectId);
		return percent;
	}

	@Override
	public List<PersonalScheduleVO> selectclosingPersonalSchedule(int projectId) throws SQLException {
		List<PersonalScheduleVO> schdule = new ArrayList<PersonalScheduleVO>();
		schdule = sqlSession.selectList("pms-dashboard-mapper.selectclosingPersonalSchedule", projectId);
		return schdule;
	}

	@Override
	public List<PersonalScheduleVO> selectMemberProcessPercent(int projectId) throws SQLException {
		List<PersonalScheduleVO> memberPercent = new ArrayList<PersonalScheduleVO>();
		memberPercent = sqlSession.selectList("pms-dashboard-mapper.selectMemberProcessPercent", projectId);
		return memberPercent;
	}

	@Override
	public List<PersonalScheduleVO> selectWaitingCompletedScheduleList(int projectId) throws SQLException {
		List<PersonalScheduleVO> watingList = new ArrayList<PersonalScheduleVO>();
		watingList = sqlSession.selectList("pms-dashboard-mapper.selectWaitingCompletedScheduleList", projectId);
		return watingList;
	}

	@Override
	public List<MemberVO> selectProjectMember(SearchCriteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		memberList = sqlSession.selectList("pms-dashboard-mapper.selectProjectMember",cri,rowBounds);
		return memberList;
	}

	@Override
	public void deleteProejectMember(ProTeamVO project) throws SQLException {
		sqlSession.update("pms-dashboard-mapper.deleteProejectMember", project);
	}

	@Override
	public List<MemberVO> selectRegistrableProjectMember(SearchCriteria cri) throws SQLException {
		int offset = cri.getPageStartRowNum();
		cri.setPerPageNum(5);
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		memberList = sqlSession.selectList("pms-dashboard-mapper.selectRegistrableProjectMember",cri,rowBounds);
		return memberList;
	}

	@Override
	public void insertNewProjectMember(ProTeamVO project) throws SQLException {
		sqlSession.update("pms-dashboard-mapper.insertNewProjectMember",project);
	}

	@Override
	public String selectProjectManger(int projectId) throws SQLException {
		return sqlSession.selectOne("pms-dashboard-mapper.selectProjectManger",projectId);
	}

	
	@Override
	public List<PersonalScheduleVO> selectPersonalSchedule(PersonalScheduleVO vo) throws SQLException {
		List<PersonalScheduleVO> task = null;
		task = sqlSession.selectList("pms-dashboard-mapper.selectPersonalSchedule", vo);
		return task;
	}

	@Override
	public int selectCountProjectMemeberByProjectId(SearchCriteria cri) {
		int cnt = 0;
		cnt = sqlSession.selectOne("pms-dashboard-mapper.selectCountProjectMemeberByProjectId", cri);
		return cnt;
	}

	@Override
	public int selectCurrentCountProjectMemeberByProjectId(int projectId) {
		int cnt = 0;
		cnt = sqlSession.selectOne("pms-dashboard-mapper.selectCurrentCountProjectMemeberByProjectId", projectId);
		return cnt;
	}

	
}
