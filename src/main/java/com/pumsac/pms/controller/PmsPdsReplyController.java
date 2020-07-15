package com.pumsac.pms.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pumsac.command.PageMaker;
import com.pumsac.command.RemoveReplyCommand;
import com.pumsac.command.SearchCriteria;
import com.pumsac.pms.service.PmsPdsReplyService;
import com.pumsac.vo.ReplyVO;

@Controller
@RequestMapping("/pms/reply/")
public class PmsPdsReplyController {
	
	@Autowired
	private PmsPdsReplyService pmsPdsReplyService;
	
	
	@RequestMapping("list")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> getList(int pbno, SearchCriteria cri) {
		ResponseEntity<Map<String,Object>> entity = null;
		
		try {
			Map<String, Object> map = pmsPdsReplyService.getPbnoReplies(pbno, cri);
			entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			return entity;
		}
		
		return entity;
	}
	
	
	@RequestMapping("regist")
	public ResponseEntity<Integer> registPbnoReply(@RequestBody ReplyVO reply){
		ResponseEntity<Integer> entity = null; //덧글을 등록한 후 맨 마지막 페이지로 보내주기 위한 entity
		int endPage = 0;
		try {
			pmsPdsReplyService.registPbnoReply(reply);
			
			Map<String, Object> map = pmsPdsReplyService.getPbnoReplies(reply.getPbno(), new SearchCriteria());
			PageMaker pageMaker = (PageMaker)map.get("pageMaker");
			endPage = pageMaker.getRealEndPage();
			
			entity = new ResponseEntity<>(endPage, HttpStatus.OK);
		} catch (SQLException e) {
			entity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	
	@RequestMapping("modify")
	public ResponseEntity<String> modifyReply(@RequestBody ReplyVO reply){
		ResponseEntity<String> entity = null;
		try {
			pmsPdsReplyService.modifyPbnoReply(reply);
			entity = new ResponseEntity<>(HttpStatus.OK);
		} catch (SQLException e) {
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	//아 그 참 드럽게 데이터 못 받아오네 ㅡㅡ
	@RequestMapping("remove")
	public ResponseEntity<Integer> removeReply(@RequestBody RemoveReplyCommand replyCommand){
		ResponseEntity<Integer> entity = null;
		
		int no = replyCommand.getNo();
		int replyPage = replyCommand.getReplyPage();
		
		System.out.println("no : "+no);
		System.out.println("replyPage : "+replyPage);
		
		ReplyVO reply = new ReplyVO();
		reply.setNo(no);
		try {
			pmsPdsReplyService.removePbnoReply(reply);
			
			Map<String, Object> map = pmsPdsReplyService.getPbnoReplies(replyCommand.getPbno(), new SearchCriteria());
			PageMaker pageMaker = (PageMaker)map.get("pageMaker");
			int realEndPage = pageMaker.getRealEndPage();
			
			System.out.println(realEndPage);
			System.out.println(replyPage);
			
			if(realEndPage < replyPage) {
				replyPage = realEndPage;
			}
			entity = new ResponseEntity<>(replyPage, HttpStatus.OK);
		} catch (SQLException e) {
			entity = new ResponseEntity<Integer>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
}
