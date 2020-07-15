package com.pumsac.service;

import java.sql.SQLException;
import java.util.Map;

import com.pumsac.command.ReservationDTO;
import com.pumsac.command.SearchCriteria;

public interface AdminPaymentService {
	
	public Map<String, Object> getPayments(SearchCriteria cri) throws SQLException;
	
	public ReservationDTO getReservationDetail(int no) throws SQLException; 
	
	public void removeReservation(int no) throws SQLException;
	
}
