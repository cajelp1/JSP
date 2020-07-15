package com.pumsac.pms.service;

import com.pumsac.dao.MemberDao;
import com.pumsac.pms.dao.PdsAttachFileDao;
import com.pumsac.pms.dao.PmsPdsDao;
import com.pumsac.pms.dao.ProjectCommonDao;
import com.pumsac.vo.AttachFileVO;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.ProBoardMasterVO;
import com.pumsac.vo.ProBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("pmsPdsService")
public class PmsPdsServiceImpl implements PmsPdsService {

	@Autowired
	private PmsPdsDao pmsPdsDao;
	
	@Autowired
	private ProjectCommonDao projectCommonDao;
	
	@Autowired
	private PdsAttachFileDao pdsAttachFileDao;
	
	@Autowired
	private MemberDao memberDao;
	
	
	private void deleteFile(List<Integer> deleteFileNoList) throws SQLException, IOException {
		if (deleteFileNoList != null && deleteFileNoList.size() > 0) {
			for (int no : deleteFileNoList) {
				AttachFileVO fileVO = projectCommonDao.selectFileInfo(no);
				pdsAttachFileDao.removePdsFile(no);
				File file = new File(fileVO.getAttachDirectory() + File.separator + fileVO.getStoredFilename());
				if (file.exists()) {
					file.delete();
				}
			}
		}
	}
	
	
	@Override
	public void registPds(ProBoardVO board, ProBoardMasterVO boardMaster) throws SQLException {
		ProBoardMasterVO master = projectCommonDao.selectBoardName(boardMaster);
		int no = pmsPdsDao.selectNextProBoardSeq();
		board.setNo(no);
		board.setBoardName(master.getBoardName());
		
		pmsPdsDao.insertPds(board);
		
		List<AttachFileVO> attachList = board.getAttachList();
		if (attachList != null) {
			for (AttachFileVO vo : attachList) {
				vo.setPno(no);
				pdsAttachFileDao.insertAttachFileToPds(vo);
			}
		}
	}

	@Override
	public List<ProBoardVO> getPdsList(Map<String, Object> map) throws SQLException {
		
		ProBoardMasterVO boardMaster = (ProBoardMasterVO)map.get("boardMaster");
		ProBoardMasterVO master = projectCommonDao.selectBoardName(boardMaster);
		map.put("master", master);
		
		List<ProBoardVO> boardlist = new ArrayList<ProBoardVO>(); 
		
		List<ProBoardVO> list = pmsPdsDao.selectPdsList(map);
		for (ProBoardVO proBoardVO : list) {
			MemberVO mem = memberDao.selectMemberByEmail(proBoardVO.getWriter());
			proBoardVO.setWrtierNickName(mem.getNickname());
			boardlist.add(proBoardVO);
		}
		
		return boardlist;
	}

	@Override
	public Map<String, Object> getDetail(int no) throws SQLException {
		pmsPdsDao.updatePdsViewCnt(no);

		Map<String, Object> map = new HashMap<>();
		ProBoardVO proBoardVO = pmsPdsDao.selectPdsDetail(no);
		
		MemberVO member = memberDao.selectMemberByEmail(proBoardVO.getWriter());
		
		List<AttachFileVO> attachFileList = pdsAttachFileDao.selectPdsFileList(no);
		
		map.put("member", member);
		map.put("proBoardVO", proBoardVO);
		map.put("attachFileList", attachFileList);
		
		return map;
	}

	@Override
	public void modifyPds(ProBoardVO board, ProBoardMasterVO boardMaster, List<Integer> deleteFileNoList) throws SQLException, IOException {
		ProBoardMasterVO master = projectCommonDao.selectBoardName(boardMaster);
		board.setBoardName(master.getBoardName());
		
		deleteFile(deleteFileNoList);
				
		List<AttachFileVO> attachList = board.getAttachList();
		if (attachList != null) {
			for (AttachFileVO vo : attachList) {
				vo.setPno(board.getNo());
				pdsAttachFileDao.insertAttachFileToPds(vo);
			}
		}
		
		pmsPdsDao.updatePds(board);
	}

	@Override
	public void removePds(int no) throws SQLException, IOException {
		List<AttachFileVO> fileList = pdsAttachFileDao.selectPdsFileList(no);
		List<Integer> fileNoList = new ArrayList<Integer>();
		if (fileList != null && fileList.size() > 0) {
			for (AttachFileVO vo : fileList) {
				fileNoList.add(vo.getNo());
			}
		}
		deleteFile(fileNoList);
		
		pmsPdsDao.removePds(no);
	}

	@Override
	public ProBoardMasterVO getBoardMasterInfo(ProBoardMasterVO master) throws SQLException {
		return projectCommonDao.selectBoardName(master);
	}
	
	
	
	
	@Override
	public List<ProBoardMasterVO> getBoadMasterList(int projectId) throws SQLException {
		List<ProBoardMasterVO> list = null;
		list = pmsPdsDao.selectBoardMasterList(projectId);
		return list;
	}
	
}
