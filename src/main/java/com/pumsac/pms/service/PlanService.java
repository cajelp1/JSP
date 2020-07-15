package com.pumsac.pms.service;

import java.sql.SQLException;
import java.util.List;

import com.pumsac.vo.PlanVO;

public interface PlanService {

	/**
	 * 개인 일정 등록하는 서비스
	 * @param plan
	 * @throws SQLException
	 */
	public void registPersonalPlan(PlanVO plan) throws SQLException;
	
	/**
	 * 팀 일정 등록하는 서비스
	 * @param plan
	 * @throws SQLException
	 */
	public void registTeamPlan(PlanVO plan) throws SQLException;
	
	/**
	 * 일정 수정하는 서비스
	 * @param plan
	 * @throws SQLException
	 */
	public void modifyPlan(PlanVO plan) throws SQLException;
	
	/**
	 * 개인 일정 리스트를 조회하는 서비스
	 * @param plan
	 * @return
	 * @throws SQLException
	 */
	public List<PlanVO> getPersonalPlan(PlanVO plan) throws SQLException;
	
	/**
	 * 팀 일정 리스트를 조회하는 서비스
	 * @param projectId
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	public List<PlanVO> getTeamPlan(int projectId, String code) throws SQLException;
	
	/**
	 * 일정을 삭제하는 서비스
	 * @param no
	 * @throws SQLException
	 */
	public void removePlan(int no) throws SQLException;

	/**
	 * 일정을 수정하는 서비스
	 * @param no
	 * @return
	 * @throws SQLException
	 */
	public PlanVO getTeamPlanDetail(int no) throws SQLException;
}
