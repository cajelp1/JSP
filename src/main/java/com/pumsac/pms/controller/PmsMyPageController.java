package com.pumsac.pms.controller;

import com.pumsac.pms.service.CommonProjectService;
import com.pumsac.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@Controller
@RequestMapping("/pms/mypage/*")
public class PmsMyPageController {
    @Autowired
    private CommonProjectService commonProjectService;
    @GetMapping("myprojectList")
    public void myprojectList(Model model, HttpSession session) throws SQLException {
        MemberVO vo=(MemberVO)session.getAttribute("loginUser");
        model.addAttribute("projectList",commonProjectService.getProjectId(vo));
    }



}
