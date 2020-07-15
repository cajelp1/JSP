package com.pumsac.service;

import com.pumsac.vo.PaymentHistoryVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PaymentService {
    void  reigistPaymentHistory(PaymentHistoryVO vo) throws SQLException;
    List<Map<String,String>> reservationHistory(int meetingRoomNo) throws SQLException;
	void requestCancelReservation(int no) throws SQLException;
}
