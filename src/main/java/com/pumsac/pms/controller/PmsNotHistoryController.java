package com.pumsac.pms.controller;

import com.pumsac.command.SearchCriteria;
import com.pumsac.pms.service.PmsNotHistoryService;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.NotHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pms/notHistory")
public class PmsNotHistoryController {
	
//	private static final String MemberVO = null;
	
	@Autowired
	private PmsNotHistoryService notHistoryService;
	
	private final String path = "pms/notHistory/";
	
	@RequestMapping(value="readMessage")
	@ResponseBody
	public ResponseEntity<String> readMessage(int no) throws Exception{
		ResponseEntity<String> entity = null;
		
		try {
			notHistoryService.modifyReadYN(no);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}

	@RequestMapping(value="realTimeMessage")
	@ResponseBody
	public ResponseEntity<List<NotHistoryVO>> realTimeMessage(int projectId, SearchCriteria cri, HttpSession session){
		ResponseEntity<List<NotHistoryVO>> entity = null;
		
		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		
		try {
			if (member != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("projectId", projectId);
				map.put("cri", cri);
				map.put("memberEmail", member.getEmail());
				
				Map<String, Object> notHistoryListInfoReadN = notHistoryService.getNotHistoryListInfoReadN(map);
				List<NotHistoryVO> notHistorylist = (List<NotHistoryVO>) notHistoryListInfoReadN.get("notHistorylist");
				entity = new ResponseEntity<List<NotHistoryVO>>(notHistorylist, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<NotHistoryVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	
	// 전체 리스트 보여주기
	@RequestMapping(value="listAll")
	public String notHistoryList(Model model, int projectId, SearchCriteria cri, HttpSession session) throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		Map<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("projectId", projectId);
		inputMap.put("cri", cri);
		inputMap.put("memberEmail", member.getEmail());
		Map<String, Object> map = notHistoryService.getNotHistoryList(inputMap);
		model.addAllAttributes(map);
		model.addAttribute("projectId", projectId);
		return path + "listAll";
	}
	
	// 전체 리스트 보여주기
	@RequestMapping(value="listReadN")
	public String notHistoryListReadN(Model model, int projectId, SearchCriteria cri, HttpSession session) throws Exception {
		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		Map<String, Object> inputMap = new HashMap<String, Object>();
		inputMap.put("projectId", projectId);
		inputMap.put("cri", cri);
		inputMap.put("memberEmail", member.getEmail());
		Map<String, Object> map = notHistoryService.getNotHistoryListInfoReadN(inputMap);
		model.addAllAttributes(map);
		model.addAttribute("projectId", projectId);
		return path + "listReadN";
	}
	
	@RequestMapping(value="detail")
	public String detailNotHistory(Model model, int no) throws Exception {
		
		NotHistoryVO vo = notHistoryService.getNotHistoryDetail(no);
		
		model.addAttribute("detail", vo);
		
		return path + "detail";
	}
}
