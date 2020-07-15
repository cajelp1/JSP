package com.pumsac.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pumsac.command.ReservationDTO;
import com.pumsac.command.SearchCriteria;
import com.pumsac.service.AdminPaymentService;

@Controller
@RequestMapping("dsp/admin/payment")
public class AdminPaymentController {
	
	@Autowired
	private AdminPaymentService adminPaymentService;
	
	@RequestMapping("/list")
	public ModelAndView getReservationList(ModelAndView mnv, SearchCriteria cri) {
		String url = "site/admin/payment/list";
		
		Map<String, Object> map = null;
		try {
			map = adminPaymentService.getPayments(cri);
			mnv.addAllObjects(map);
			mnv.setViewName(url);
		} catch (SQLException e) {
			e.printStackTrace();
			mnv.setViewName("error/500_error");
		}
		return mnv;
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public ModelAndView getReservationDetail(ModelAndView mnv, int reservNo) {
		String url = "site/admin/payment/detail";
		
		ReservationDTO reserv = null;
		try {
			reserv = adminPaymentService.getReservationDetail(reservNo);
			mnv.addObject("reserv", reserv);
			mnv.setViewName(url);
		} catch (SQLException e) {
			e.printStackTrace();
			mnv.setViewName("error/500_error");
		}
		return mnv;
	}
	
	@RequestMapping("/modify")
	public ModelAndView updateReservation(ModelAndView mnv, int reservNo) {
		String url = "site/admin/payment/modify_success";
		
		try {
			adminPaymentService.removeReservation(reservNo);
			mnv.setViewName(url);
		} catch (SQLException e) {
			e.printStackTrace();
			mnv.setViewName("error/500_error");
		}
		return mnv;
	}
	
}


