package com.groupware.controller.board;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.groupware.dto.NoticeAttachVO;
import com.groupware.dto.NoticeVO;
import com.groupware.request.SearchCriteria;
import com.groupware.service.board.NoticeService;

@Controller
@RequestMapping("/board/notice/*")
public class NoticeBoardController {
	
	@Autowired
	private NoticeService nService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping("list")
	public ModelAndView noticeList(SearchCriteria cri, ModelAndView modelnView) throws SQLException{
		String url = "board/notice/notice_list";
		
		Map<String, Object> dataMap = nService.getNoticeList(cri);
		List<ColName> colNames = new ArrayList<ColName>();
		
		String[] colNameArr = {"","번&nbsp;목","작성일","작성자","첨$nbsp;부","조회수"};
		String[] colWidthArr = {"4","10","46","10","10","10","10"};
		for(int i = 0; i <colNameArr.length; i++) {
			colNames.add(new ColName(colNameArr[i],colWidthArr[i]));
		}
		
		dataMap.put("colNames", colNames);
		
		modelnView.addAllObjects(dataMap);
		modelnView.setViewName(url);
		return modelnView;
	}
	
	@RequestMapping(value="regist", method=RequestMethod.POST)
	public void noticeRegist(NoticeVO notice, HttpServletResponse response)throws Exception{
		String url = "redirect:list";
		
		nService.regist(notice);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("window.opener.location.href='/board/notice/list'; window.close();");
		out.println("</script");
	}
	
	@RequestMapping(value="detial", method=RequestMethod.GET)
	public String detail (int nno, Model model) throws Exception{
		String url = "board/notice/notice_detail";
		
		NoticeVO notice = nService.read(nno);
		model.addAttribute("notice",notice);
		
		return url;
	}
	
	@RequestMapping(value="getAttach/{nno}",method=RequestMethod.GET)
	@ResponseBody
	public List<NoticeAttachVO> getAttach(@PathVariable("nno")int nno) throws Exception{
		NoticeVO notice = nService.getNotice(nno);
		List<NoticeAttachVO> attachList = notice.getAttachList();
		return attachList;
	}
	
}
