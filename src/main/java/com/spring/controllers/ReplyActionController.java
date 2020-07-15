package com.spring.controllers;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.request.SearchCriteria;
import com.spring.service.ReplyService;
import com.spring.dto.ReplyVO;
import com.spring.request.DeleteReplyRequest;
import com.spring.request.ModifyReplyRequest;
import com.spring.request.PageMaker;
import com.spring.request.RegistReplyRequest;

@Controller
@RequestMapping("/replies/*")
public class ReplyActionController {
	
	@Autowired
	private ReplyService replyService;
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	
	/*@RequestMapping("list.do")
	@ResponseBody
	public MemberVO list() throws Exception{
		
		MemberVO member = memberService.getMember("admin");
		
		return member;
	}*/
	
	@RequestMapping("list.do")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> list(int bno, SearchCriteria cri) throws Exception{
		
		ResponseEntity<Map<String,Object>> entity = null;
		
		try {
			Map<String,Object> dataMap = replyService.getReplyList(bno, cri);
			entity = new ResponseEntity<Map<String,Object>>(dataMap,HttpStatus.OK);
			
		}catch(SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	
	@RequestMapping("regist.do")
	@ResponseBody
	public ResponseEntity<Integer> regist(@RequestBody RegistReplyRequest registReq) throws Exception {
		
		//String result = "";
		ResponseEntity<Integer> entity = null;
		
		ReplyVO reply = registReq.toReplyVO();
		
		try {			
			replyService.registReply(reply);
			
			//if(true) throw new SQLException();
			
			Map<String, Object> dataMap = replyService.getReplyList(registReq.getBno(), new SearchCriteria());
			PageMaker pageMaker = (PageMaker)dataMap.get("pageMaker");
			int realEndPage = pageMaker.getRealEndPage();
			//result="SUCCESS,"+realEndPage;
			
			//dataMap.put("realEndPage", pageMaker.getRealEndPage());
			
			//결과와 응답코드를 같이 보냄. 200코드
			entity = new ResponseEntity<Integer>(realEndPage,HttpStatus.OK);
			
		}catch(SQLException e) {
			e.printStackTrace();
			//result="FAIL,1";
			
			//500코드
			entity = new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	
	@RequestMapping("modify.do")
	@ResponseBody
	public ResponseEntity<String> modify(@RequestBody ModifyReplyRequest modifyReq) throws Exception {
		//String result = "";
		ResponseEntity<String> entity = null;
		
		ReplyVO reply = modifyReq.toReplyVO();
		
		System.out.println(reply.getBno());
		
		try {			
			replyService.modifyReply(reply);
			//result="SUCCESS";
			
			entity = new ResponseEntity<String>(HttpStatus.OK);			
		}catch(SQLException e) {
			e.printStackTrace();
			//result="FAIL";
			
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return entity;
	}
	
	
	@RequestMapping("remove.do")
	@ResponseBody
	public ResponseEntity<Integer> remove(@RequestBody DeleteReplyRequest deleteReq) throws Exception {
		//String result = "";
		ResponseEntity<Integer> entity = null;
		
		try{
			replyService.removeReply(deleteReq.getRno());
			
			Map<String, Object> dataMap = replyService.getReplyList(deleteReq.getBno(), new SearchCriteria());
			PageMaker pageMaker = (PageMaker)dataMap.get("pageMaker");
			int page = deleteReq.getReplyPage();
			int realEndPage = pageMaker.getRealEndPage();
			if(page>realEndPage) {
				page = realEndPage;
			}
			
			entity = new ResponseEntity<Integer>(realEndPage,HttpStatus.OK);
			
		}catch (SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//result="SUCCESS,"+realEndPage;
		
		return entity;
	}

	
}
