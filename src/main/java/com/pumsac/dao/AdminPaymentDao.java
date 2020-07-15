package com.pumsac.dao;

import com.pumsac.command.ReservationDTO;
import com.pumsac.command.SearchCriteria;

import java.sql.SQLException;
import java.util.List;

public interface AdminPaymentDao {
	
	public List<ReservationDTO> selectReservationList(SearchCriteria cri) throws SQLException;
	
	public int countReservationList(SearchCriteria cri) throws SQLException;
	
	public ReservationDTO selectReservationDetail(int no) throws SQLException;
	
	public void deleteReservation(int no) throws SQLException;
}
