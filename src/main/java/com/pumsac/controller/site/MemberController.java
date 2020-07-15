package com.pumsac.controller.site;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

import com.pumsac.dao.MemberDao;
import com.pumsac.util.PasswordEncryption;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pumsac.command.RegistMemberCommand;
import com.pumsac.service.AttachFileService;
import com.pumsac.service.MemberService;
import com.pumsac.vo.MemberVO;

@Controller
@RequestMapping("/commons/member/*")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private AttachFileService attachFileService;
    private String tempPass;

    // ---------------------------------------------------------아이디 찾기
    @GetMapping("findEmail")
    //@RequestMapping(value = "findEmail", method = RequestMethod.GET)
    public void findEmailGET() {
    }

    @PostMapping("findEmail")
    //@RequestMapping(value = "findEmail", method = RequestMethod.POST)
    public ModelAndView findEmailPOST(ModelAndView mnv, MemberVO member) throws Exception {
        String url = "/commons/member/findEmail_success";
        MemberVO result = null;
        String tempPass = "";

        try {
            result = memberService.getMemberEmail(member);
        } catch (Exception e) {
            e.printStackTrace();
            mnv.setViewName("/commons/member/findEmail_fail");
            return mnv;
        }

        mnv.addObject("email", result.getEmail());
        mnv.setViewName(url);
        return mnv;
    }

    // ---------------------------------------------------------패스워드 찾기

    @RequestMapping(value = "findPassword", method = RequestMethod.GET)
    public void findPasswordGET() {
    }

    @RequestMapping(value = "findPassword", method = RequestMethod.POST)
    public ModelAndView findPasswordPOST(ModelAndView mnv, MemberVO member) throws CoolsmsException, SQLException, NoSuchAlgorithmException {
        String url = "commons/member/findPassword_success";
        MemberVO result = null;

        try {
            result = memberService.getMemberInfoFindPassword(member);
        } catch (Exception e) {
            e.printStackTrace();
            mnv.setViewName("/error/500_error");
            return mnv;
        }

        if (result == null) {
            mnv.addObject("msg", "입력하신 정보를 찾을 수 없습니다");
            mnv.setViewName("/commons/member/findPassword_fail");
        } else {
            // 6자리 랜덤 문자열을 임시 비밀번호로 생성
            tempPass = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
            result.setPassword(PasswordEncryption.passwordEncryption(tempPass));

            try {
                memberService.modifyMemberPassword(result);
            } catch (Exception e) {
                e.printStackTrace();
                mnv.addObject("msg", "임시 비밀번호 생성에 실패했습니다");
                mnv.setViewName("/commons/member/findPassword_fail");
                return mnv;
            }

            // 이 부분에 문자 발송 필요
            String api_key = "NCSIZYZBIEEPCQCY";
            String api_secret = "JWFOMUWT17LSKNVXLYP8LVZNLXOOYRQH";
            Message coolsms = new Message(api_key, api_secret);
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("to", result.getPhoneNumber());
            params.put("from", "010-2125-4151"); //무조건 자기번호 (인증)
            params.put("type", "SMS");
            params.put("text", "임시비밀번호는" + tempPass + "입니다.");
            params.put("app_version", "test app 1.2");
            JSONObject smsResult = coolsms.send(params); // 보내기
            mnv.setViewName(url);
        }
        return mnv;
    }

    @RequestMapping(value = "agree", method = RequestMethod.GET)
    public void agreeGET() {
    }

    @RequestMapping(value = "regist", method = RequestMethod.GET)
    public void registGET() {
    }

    @RequestMapping(value = "imagePreview", method = RequestMethod.GET)
    public void imagePreview() {
    }

    @RequestMapping(value = "regist", method = RequestMethod.POST)
    public ModelAndView registPOST(RegistMemberCommand command) {
        ModelAndView mnv = new ModelAndView();
        String url = "/commons/member/regist_success";

        try {
            memberService.registMember(command.toMemberVO());
            attachFileService.registMemberProfileImage(command.toAttachFileVO());
            mnv.setViewName(url);
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return mnv;
    }
}
