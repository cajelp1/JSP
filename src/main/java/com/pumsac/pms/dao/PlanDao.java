package com.pumsac.pms.dao;

import java.sql.SQLException;
import java.util.List;

import com.pumsac.vo.PlanVO;

public interface PlanDao {

	public void insertPersonalPlan(PlanVO plan) throws SQLException;
	
	public List<PlanVO> selectPersonalPlan(PlanVO plan) throws SQLException;
	
	public void updatePlan(PlanVO plan) throws SQLException;
	
	public void deletePlan(int no) throws SQLException;
	
	public int selectPlanSeq() throws SQLException;

	public List<PlanVO> selectTeamPlan(PlanVO plan) throws SQLException;

	public PlanVO selectPlanDetail(int no) throws SQLException;
	
}
