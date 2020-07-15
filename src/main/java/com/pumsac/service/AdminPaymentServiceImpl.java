package com.pumsac.service;

import com.pumsac.command.PageMaker;
import com.pumsac.command.ReservationDTO;
import com.pumsac.command.SearchCriteria;
import com.pumsac.dao.AdminPaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("adminPaymentService")
public class AdminPaymentServiceImpl implements AdminPaymentService{
	
	@Autowired
	private AdminPaymentDao adminPaymentDao;
	
	@Override
	public Map<String, Object> getPayments(SearchCriteria cri) throws SQLException{
		Map<String, Object> dataMap = new HashMap<String, Object>();
		PageMaker pageMaker = new PageMaker();
		
		List<ReservationDTO> reservationList = null;
		reservationList = adminPaymentDao.selectReservationList(cri);
		int count = adminPaymentDao.countReservationList(cri);
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(count);
		
		dataMap.put("reservationList", reservationList);
		dataMap.put("pageMaker", pageMaker);
		return dataMap;
	}

	@Override
	public ReservationDTO getReservationDetail(int no) throws SQLException {
		ReservationDTO reserv = null;
		reserv = adminPaymentDao.selectReservationDetail(no);
		return reserv;
	}

	@Override
	public void removeReservation(int no) throws SQLException {
		adminPaymentDao.deleteReservation(no);
	}
	
}
