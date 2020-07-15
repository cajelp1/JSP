package com.pumsac.pms.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.pumsac.vo.AttachFileVO;
import com.pumsac.vo.ProMasterVO;
import com.pumsac.vo.ProTeamVO;

public interface ProjectCommonService {

	/**
	 * @내용 : 프로젝트 등록. ProMasterVO안에 ProTeamVO가 List형식으로 되어있음.
	 */
	void registProject(ProMasterVO proMasterVO) throws SQLException;

	/**
	 * @내용 : 로그인한 사람의 프로젝트를 가져오는 Service 
	 */
	List<ProTeamVO> getProjectList(String memberEmail) throws SQLException;

	/**
	 * @내용 프로젝트 비활성화
	 */
	void removeProject(int projectId) throws SQLException;

	/**
	 * @내용 해당 프로젝트의 멤버들을 조회한다.
	 */
	List<ProTeamVO> getProjectMember(int projectId) throws SQLException;
	
	/**
	 * @내용 해당 프로젝트의 정보를 반환한다. (이름, 시작일, 종료일)
	 */
	ProMasterVO getProjectInfo(int projectId) throws SQLException;
	
	
	public AttachFileVO getFileInfo(int no) throws SQLException;
	
	/**
	 * @내용 해당프로젝트를 완료 상태로 변경한다. 
	 */
	void modifyProjectComplete(Map<String, Object> map) throws SQLException;


}
