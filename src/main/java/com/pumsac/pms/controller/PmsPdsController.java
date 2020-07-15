package com.pumsac.pms.controller;

import com.pumsac.command.SearchCriteria;
import com.pumsac.pms.service.PmsPdsService;
import com.pumsac.vo.AttachFileVO;
import com.pumsac.vo.ProBoardMasterVO;
import com.pumsac.vo.ProBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@Controller
@RequestMapping("/pms/team/pds/*")
public class PmsPdsController {
	
	private final String path = "pms/team/pds/";

	@Autowired
	private PmsPdsService pmsPdsService;

	@Resource(name = "teamPdsUploadPath")
	private String uploadPath;

	private ProBoardMasterVO GetBoardMaster(int projectId) throws SQLException {
		ProBoardMasterVO master = new ProBoardMasterVO();
		master.setBoardType("자료실");
		master.setProjectId(projectId);
		ProBoardMasterVO boardMasterVO = pmsPdsService.getBoardMasterInfo(master);

		return boardMasterVO;
	}

	@RequestMapping(value = "list")
	public String getPdsList(Model model, HttpSession session, ProBoardMasterVO boardMaster, SearchCriteria cri) throws Exception {

		boardMaster.setBoardType("자료실");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardMaster", boardMaster);
		map.put("cri", cri);

		List<ProBoardVO> pdsList = pmsPdsService.getPdsList(map);

		model.addAttribute("list", pdsList);
		model.addAttribute("projectId", boardMaster.getProjectId());
		return path + "list";
	}

	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String getPdsDetail(Model model, int projectId, int no) throws Exception {

		Map<String, Object> dataMap = pmsPdsService.getDetail(no);
		model.addAllAttributes(dataMap);
		model.addAttribute("projectId", projectId);
		model.addAttribute("boardMaster", GetBoardMaster(projectId));

		return path + "detail";
	}

	@RequestMapping(value = "regist", method = RequestMethod.GET)
	public String getRegistPds(Model model, int projectId) throws Exception {

		model.addAttribute("projectId", projectId);

		model.addAttribute("boardMaster", GetBoardMaster(projectId));

		return path + "regist";
	}

	@RequestMapping(value = "regist", method = RequestMethod.POST)
	public String postRegistPds(Model model, int projectId, ProBoardVO board,

			@RequestParam("uploadFile") MultipartFile[] fileList) throws Exception {

		// 파일 저장후 vo를 board에 저장
		List<AttachFileVO> attachList = uploadFile(fileList);
		board.setAttachList(attachList);

		ProBoardMasterVO boardMaster = new ProBoardMasterVO();
		boardMaster.setBoardType("자료실");
		boardMaster.setProjectId(projectId);

		pmsPdsService.registPds(board, boardMaster);

		return  path + "pdsSuccess";
	}

	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public String modifyPdsGet(Model model, int no, int projectId) throws Exception {

		Map<String, Object> dataMap = pmsPdsService.getDetail(no);
		model.addAllAttributes(dataMap);
		model.addAttribute("projectId", projectId);

		model.addAttribute("boardMaster", GetBoardMaster(projectId));

		return  path + "modify";
	}

	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modifyPdsPost(Model model, int projectId, ProBoardVO board,
			@RequestParam(value = "uploadFile", required = false) MultipartFile[]

					fileList, @RequestParam(value = "deleteFile", required = false) List<Integer>

	deleteFileList) throws Exception {

		// 파일 저장후 vo를 board에 저장
		List<AttachFileVO> attachList = uploadFile(fileList);
		board.setAttachList(attachList);

		ProBoardMasterVO boardMaster = new ProBoardMasterVO();
		boardMaster.setBoardType("자료실");
		boardMaster.setProjectId(projectId);

		pmsPdsService.modifyPds(board, boardMaster, deleteFileList);
		
		model.addAttribute("projectId", projectId);
		model.addAttribute("no", board.getNo());

		return path + "modifySuccess";
	}

	@RequestMapping(value = "remove", method = RequestMethod.GET)
	public String removePds(Model model, int no) throws Exception {

		pmsPdsService.removePds(no);

		return  path + "pdsSuccess";
	}

	private List<AttachFileVO> uploadFile(MultipartFile[] fileList) throws IOException {
		List<AttachFileVO> attachList = new ArrayList<AttachFileVO>();
		for (MultipartFile file : fileList) {
			// 파일 이름, 경로, 타입 추출
			String originfileName = file.getOriginalFilename();
			String fileName = UUID.randomUUID().toString().replace("-", "") + "$$" + originfileName;
			String fileType = originfileName.substring(originfileName.lastIndexOf(".") + 1);

			AttachFileVO attach = new AttachFileVO();
			attach.setAttachDirectory(uploadPath);
			attach.setExtension(fileType);
			attach.setFileSize((int) file.getSize());
			attach.setOriginFilename(originfileName);
			attach.setStoredFilename(fileName);

			File saveFile = new File(uploadPath, fileName);
			if (!saveFile.exists()) {
				saveFile.mkdirs();
			}
			file.transferTo(saveFile);

			attachList.add(attach);
		}

		return attachList;
	}

}
