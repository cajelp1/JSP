package com.jsp.action.reply;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.action.Action;
import com.jsp.dto.ReplyVO;
import com.jsp.service.ReplyService;
import com.jsp.service.ReplyServiceImpl;

public class ListReplyAction implements Action {

	private ReplyService replyService;// = ReplyServiceImpl.getInstance();
	public void setReplyService(ReplyService replyService) {
		this.replyService=replyService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url=null;
		
		int bno	= Integer.parseInt(request.getParameter("bno"));
		
		try {
			List<ReplyVO> replyList = replyService.getReplyList(bno);
			
			ObjectMapper mapper=new ObjectMapper();
			
			response.setContentType("application/json;charset=utf-8");
			PrintWriter out=response.getWriter();
			
			out.println(mapper.writeValueAsString(replyList));
			
			out.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return url;
	}

}
