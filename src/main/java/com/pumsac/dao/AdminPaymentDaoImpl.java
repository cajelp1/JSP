package com.pumsac.dao;

import com.pumsac.command.ReservationDTO;
import com.pumsac.command.SearchCriteria;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("adminPaymentDao")
public class AdminPaymentDaoImpl implements AdminPaymentDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ReservationDTO> selectReservationList(SearchCriteria cri) throws SQLException {
		List<ReservationDTO> list = null;
		list = sqlSession.selectList("admin-payment-mapper.selectReservationList", cri);
		return list;
	}

	@Override
	public int countReservationList(SearchCriteria cri) throws SQLException {
		int count = -1;
		count = sqlSession.selectOne("admin-payment-mapper.countReservationList", cri);
		return count;
	}

	@Override
	public ReservationDTO selectReservationDetail(int no) throws SQLException {
		ReservationDTO reservation = null;
		reservation = sqlSession.selectOne("admin-payment-mapper.selectReservationDetail", no);
		return reservation;
	}

	@Override
	public void deleteReservation(int no) throws SQLException {
		sqlSession.update("admin-payment-mapper.updateReservation", no);
	}

	
}
