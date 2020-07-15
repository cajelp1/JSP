package com.jsp.action.reply;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.action.Action;
import com.jsp.dto.ReplyVO;
import com.jsp.service.ReplyService;

public class RegistReplyAction implements Action {
	
	private ReplyService replyService;
	public void setReplyService (ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;
		
		ObjectMapper mapper = new ObjectMapper();
		
		ReplyVO reply = mapper.readValue(request.getReader(), ReplyVO.class);
		System.out.println(reply.getBno());
		System.out.println(reply.getReplyer());
		System.out.println(reply.getReplytext());
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			replyService.registReply(reply);
			
			out.print("SUCCESS");
			
		} catch (SQLException e) {
			e.printStackTrace();
			out.print("FAIL");
		}finally {
			if(out!=null) out.close();
		}
		
		return url;
	}

}
