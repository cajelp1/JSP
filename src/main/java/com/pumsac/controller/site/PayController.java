package com.pumsac.controller.site;

import com.pumsac.dao.PaymentDao;
import com.pumsac.service.PaymentService;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.PaymentHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
/*
* 추후 해야할일 :프로젝트 생성 페이지와  장소 찾기의 정보를 취합후 예약테이블에 이력을 쌓을것
* 선행페이지의 기능에 미구현 완료시 할 예정
*
* */
@Controller
@RequestMapping("/commons/pay/*")
public class PayController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentDao paymentDao;
    @GetMapping("initPayAPI")
    public void initPayAPI(HttpSession session, Model model) {
        MemberVO loginUser=(MemberVO) session.getAttribute("loginUser");
        model.addAttribute("paymentUserName",loginUser.getName());
        model.addAttribute("paymentUserPhoneNum",loginUser.getPhoneNumber());
        model.addAttribute("paymentUserEmail","ruddns0208@naver.com");





    }
    @GetMapping("resultPayment")
    public void resultPayment(HttpSession session) throws SQLException {
        MemberVO loginUser=(MemberVO) session.getAttribute("loginUser");
        PaymentHistoryVO vo =new PaymentHistoryVO();
        vo.setAmount(100);
        vo.setRegister(loginUser.getEmail());
        vo.setNo(paymentDao.getReservateSeqNetxval());
        vo.setMeetingNo(1);

        paymentService.reigistPaymentHistory(vo);
      //  System.out.println("결제완료후 redirecturl 들어왔나요?");
    }
}
