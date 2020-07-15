package com.pumsac.pms.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pumsac.vo.PlanVO;
@Repository("planDao")
public class PlanDaoImpl implements PlanDao {

	@Autowired
	private SqlSession session;
	
	@Override
	public void insertPersonalPlan(PlanVO plan) throws SQLException {
		session.update("plan-mapper.insertPersonalPlan", plan);
	}

	@Override
	public List<PlanVO> selectPersonalPlan(PlanVO plan) throws SQLException {
		List<PlanVO> planList = new ArrayList<PlanVO>();
		planList = session.selectList("plan-mapper.selectPersonalPlan", plan);
		return planList;
	}

	@Override
	public void updatePlan(PlanVO plan) throws SQLException {
		session.update("plan-mapper.updatePlan", plan);
	}

	@Override
	public void deletePlan(int no) throws SQLException {
		session.update("plan-mapper.deletePlan",no);
	}

	@Override
	public int selectPlanSeq() throws SQLException {
		return session.selectOne("plan-mapper.selectPlanSeq");
	}

	@Override
	public List<PlanVO> selectTeamPlan(PlanVO plan) throws SQLException {
		List<PlanVO> planList = new ArrayList<PlanVO>();
		planList = session.selectList("plan-mapper.selectTeamPlan", plan);
		return planList;
	}

	@Override
	public PlanVO selectPlanDetail(int no) throws SQLException {
		PlanVO plan = new PlanVO();
		plan = session.selectOne("plan-mapper.selectPlanDetail", no);
		return plan;
	}

}
