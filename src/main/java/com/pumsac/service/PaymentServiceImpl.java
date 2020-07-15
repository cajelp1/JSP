package com.pumsac.service;

import com.pumsac.dao.PaymentDao;
import com.pumsac.vo.PaymentHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentDao paymentDao;
    @Override
    public void reigistPaymentHistory(PaymentHistoryVO vo) throws SQLException {
        paymentDao.insertPayment(vo);

    }

    @Override
    public List<Map<String, String>> reservationHistory(int  no) throws SQLException {
        return  paymentDao.selectReservationByMeetingRoomNo(no);
    }

	@Override
	public void requestCancelReservation(int no) throws SQLException {
		paymentDao.deleteReservationMeetingRoom(no);
	}
}
