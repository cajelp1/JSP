package com.spring.controllers;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.dto.BoardVO;
import com.spring.request.ModifyBoardRequest;
import com.spring.request.PageMaker;
import com.spring.request.RegistBoardRequest;
import com.spring.request.SearchCriteria;
import com.spring.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardActionController {

	@Autowired
	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@RequestMapping("list.do")
	public String list(SearchCriteria cri,Model model)throws Exception{
		String url="board/list.page";
		
		Map<String,Object> dataMap=boardService.getBoardList(cri);
		model.addAllAttributes(dataMap);
		
		return url;
	}
	
	@RequestMapping("registForm.do")
	public String registForm()throws Exception{
		String url="board/registBoard.open";
		return url;
	}
	
	
	@RequestMapping(value="regist.do",method=RequestMethod.POST)
	public void registPost(RegistBoardRequest registReq,
							 HttpServletResponse response)throws Exception{
		
		BoardVO board = registReq.toBoardVO();
		
		boardService.write(board);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.href='list.do';window.close();");
		out.println("</script>");
		
	}
	
	
	@RequestMapping("modifyForm.do")
	public String modifyForm(int bno,Model model)throws Exception{
		String url = "board/modifyBoard.open";
		
		BoardVO board = boardService.getBoard(bno);
		
		model.addAttribute("board",board);
		
		return url;				
	}
	
	
	@RequestMapping(value="modify.do",method=RequestMethod.POST)
	public String modifyPost(ModifyBoardRequest modifyReq,
							 SearchCriteria cri)throws Exception{
		String url="redirect:board/detail.do";
						
		url=url+PageMaker.makeQuery(cri)+"&bno="+modifyReq.getBno();
		
		BoardVO board = modifyReq.toBoardVO();
		
		boardService.modify(board);
		
		return url;
	}
	
	
	@RequestMapping("remove.do")
	public void remove(int bno,HttpServletResponse response)throws Exception{
		
		boardService.remove(bno);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
		out.println("alert('삭제되었습니다');");
		out.println("window.opener.location.reload(true);window.close();");
		out.println("</script>");	
	}
	
	@RequestMapping("detail.do")
	public String detail(int bno, Model model) throws Exception{
		String url="board/detailBoard.open";
		
		BoardVO board = boardService.getBoard(bno);
		
		model.addAttribute("board",board);
		
		return url;
	}
	
}

