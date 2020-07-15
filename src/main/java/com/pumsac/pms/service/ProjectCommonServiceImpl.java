package com.pumsac.pms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumsac.dao.MemberDao;
import com.pumsac.pms.dao.PmsNotHistoryDao;
import com.pumsac.pms.dao.ProjectCommonDao;
import com.pumsac.vo.AttachFileVO;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.NotHistoryVO;
import com.pumsac.vo.ProBoardMasterVO;
import com.pumsac.vo.ProMasterVO;
import com.pumsac.vo.ProTeamVO;

@Service("projectCommonService")
public class ProjectCommonServiceImpl implements ProjectCommonService {

	@Autowired
	private ProjectCommonDao projectCommonDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private PmsNotHistoryDao pmsNotHistoryDao;
	
	@Override
	public void registProject(ProMasterVO proMasterVO) throws SQLException {
		
		int no = projectCommonDao.selectProejctIdForDual();
		proMasterVO.setProjectId(no);
		
		List<ProTeamVO> proTeamList = proMasterVO.getProTeamList();
		if(proTeamList != null) {
			for (ProTeamVO proTeamVO : proTeamList) {
				proTeamVO.setProjectId(no);
				projectCommonDao.insertProjectMemberToProTeamTable(proTeamVO);
			}			
		}
		projectCommonDao.insertProjectToProMasterTable(proMasterVO);
		
		/**
		 * ProTeam Table insert
		 */
		ProTeamVO team = new ProTeamVO();
		team.setJob("110");
		team.setMemberId(proMasterVO.getRegister());
		team.setProjectId(no);		
		projectCommonDao.insertProjectTeamTable(team);
		
		/**
		 * 프로젝트 생성시 자료실과 공지사항 pro_boardmaster에 등록
		 */
		ProBoardMasterVO pro_board = new ProBoardMasterVO();

		pro_board.setBoardName("자료실_"+no);
		pro_board.setBoardType("자료실");
		pro_board.setFileDownloadYn("Y");
		pro_board.setFileYn("Y");
		pro_board.setProjectId(no);
		pro_board.setRegister(proMasterVO.getRegister());
		pro_board.setReplyYn("Y");
		
		projectCommonDao.insert_pro_board(pro_board);
		
		pro_board = new ProBoardMasterVO();

		pro_board.setBoardName("공지사항_"+no);
		pro_board.setBoardType("공지사항");
		pro_board.setFileDownloadYn("Y");
		pro_board.setFileYn("Y");
		pro_board.setProjectId(no);
		pro_board.setRegister(proMasterVO.getRegister());
		pro_board.setReplyYn("Y");
		
		projectCommonDao.insert_pro_board(pro_board);
		
		
		
		
	}

	@Override
	public List<ProTeamVO> getProjectList(String memberEmail) throws SQLException {
		List<ProTeamVO> list = projectCommonDao.selectProjectList(memberEmail);
		
		List<ProTeamVO> teamList = new ArrayList<ProTeamVO>();
		for (ProTeamVO vo : list) {
			ProMasterVO master = projectCommonDao.selectProjectMaster(vo.getProjectId());
			vo.setProjectName(master.getProjectName());
			teamList.add(vo);
		}
		
		return teamList;
	}

	@Override
	public void removeProject(int projectId) throws SQLException {
		ProMasterVO proMasterVO = projectCommonDao.selectProjectMaster(projectId);
		String deleteyn = proMasterVO.getDeleteyn();
		if (deleteyn.equalsIgnoreCase("y")) {
			projectCommonDao.enableProject(projectId);
		} else {
			projectCommonDao.disableProject(projectId);
		}
	}

	@Override
	public List<ProTeamVO> getProjectMember(int projectId) throws SQLException {
		List<ProTeamVO> teamList = projectCommonDao.selectProjectTeamMember(projectId);
		for (ProTeamVO vo : teamList) {
			MemberVO member = memberDao.selectMemberByEmail(vo.getMemberId());
			vo.setMemberName(member.getName());
			vo.setMemberNickName(member.getNickname());
		}
		
		return teamList;
	}

	@Override
	public ProMasterVO getProjectInfo(int projectId) throws SQLException {
		return projectCommonDao.selectProjectMaster(projectId);
	}

	@Override
	public AttachFileVO getFileInfo(int no) throws SQLException {
		return projectCommonDao.selectFileInfo(no);
	}

	@Override
	public void modifyProjectComplete(Map<String, Object> map) throws SQLException {
		int projectId = (int) map.get("projectId");
		projectCommonDao.updateProjectComplete(projectId);
		
		MemberVO loginUser = (MemberVO) map.get("loginUser");
		
		List<ProTeamVO> teamList = projectCommonDao.selectProjectTeamMember(projectId);
		ProMasterVO master = projectCommonDao.selectProjectMaster(projectId);
		NotHistoryVO notHistoryVO = new NotHistoryVO();
		notHistoryVO.setTitle(master.getProjectName() + " : 프로젝트가 완료되었습니다.");
		notHistoryVO.setContent("해당 프로젝트가 완료되었습니다. 수고하셨습니다.");
		notHistoryVO.setProjectId(projectId);
		notHistoryVO.setReadyn("n");
		notHistoryVO.setSender(loginUser.getEmail());
		notHistoryVO.setType(912);
		for (ProTeamVO vo : teamList) {
			int seq = pmsNotHistoryDao.selectSeq();
			notHistoryVO.setNo(seq);
			notHistoryVO.setUrl("/pms/notHistory/detail?no="+seq);
			notHistoryVO.setReceiver(vo.getMemberId());
			pmsNotHistoryDao.InsertNotHistory(notHistoryVO);
		}
		
	}

}
