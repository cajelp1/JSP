package com.pumsac.controller;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pumsac.dao.MemberDao;
import com.pumsac.exception.EmailNotFoundException;
import com.pumsac.exception.EmailOrPasswordNotFoundException;
import com.pumsac.exception.NicknameNotFoundException;
import com.pumsac.service.MemberService;
import com.pumsac.util.PasswordEncryption;
import com.pumsac.vo.MemberVO;

@Controller
@RequestMapping("/commons/*")
public class CommonsController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	MemberDao memberDao;
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String loginGET() throws SQLException, NoSuchAlgorithmException {
/*		List<MemberVO> memList=memberDao.selectAllMember();
		for(MemberVO  memberVO:memList){
			MemberVO vo =new MemberVO();
			vo.setEmail(memberVO.getEmail());
			vo.setPassword(PasswordEncryption.passwordEncryption(memberVO.getPassword()));
			memberDao.updateEncryptionPassword(vo);
		}*/
		String url = "commons/login";
		return url;
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ModelAndView loginPOST(ModelAndView mnv, HttpSession session, 
							String email, String password) {
		String url = "redirect:/";
		MemberVO member = null;
		
		try {
			member = memberService.getMember(email,PasswordEncryption.passwordEncryption( password));
			if(member.getRole().equals("ROLE_DISABLED")) {
				mnv.addObject("msg", "탈퇴계정이므로 로그인 불가합니다. \\n관리자에게 문의하세요.");
				mnv.setViewName("commons/login");
			}else {
				session.setAttribute("loginUser", member);
				mnv.setViewName(url);
			}
		} catch (EmailOrPasswordNotFoundException e) {
			mnv.addObject("msg", e.getMessage());
			mnv.setViewName("commons/login");
			e.printStackTrace();
		} catch (Exception e) {
			mnv.addObject("msg", "로그인을 할 수 없습니다. \\n관리자에게 문의하세요.");
			mnv.setViewName("commons/login");
			e.printStackTrace();
		}
		
		return mnv;
	}
	
	@RequestMapping("logout")
	public ModelAndView logout(ModelAndView mnv, HttpSession session) throws Exception{
		//String url = "redirect:/";
		String url = "/commons/logout";
		
		session.invalidate();
		
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping("member/emailcheck")
	@ResponseBody
	public ResponseEntity<Map<String, Boolean>> emailCheck(String email){
		ResponseEntity<Map<String, Boolean>> entity = null;
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		System.out.println(email);
		try {
			memberService.checkEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
			result.put("result", false);
		} catch (EmailNotFoundException e) {
			e.printStackTrace();
			result.put("result", true);
		}
		entity = new ResponseEntity<Map<String,Boolean>>(result,HttpStatus.OK);
		return entity;
	}
	
	@RequestMapping("member/nickCheck")
	@ResponseBody
	public ResponseEntity<Map<String, Boolean>> nickCheck(String nickname){
		ResponseEntity<Map<String, Boolean>> entity = null;
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		System.out.println(nickname);
		try {
			memberService.checkNickname(nickname);
		} catch (SQLException e) {
			e.printStackTrace();
			result.put("result", false);
		} catch (NicknameNotFoundException e) {
			e.printStackTrace();
			result.put("result", true);
		}
		entity = new ResponseEntity<Map<String,Boolean>>(result,HttpStatus.OK);
		return entity;
	}

}
