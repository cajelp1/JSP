package com.pumsac.dao;

import com.pumsac.vo.MemberVO;
import com.pumsac.vo.PaymentHistoryVO;
import com.pumsac.vo.ProjectVO;
import com.pumsac.vo.ReservationForMyPageVO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface PaymentDao {
    void insertPayment(PaymentHistoryVO vo) throws SQLException;
    int getReservateSeqNetxval()throws SQLException;
    List<Map<String,String>>selectReservationByMeetingRoomNo(int no) throws SQLException;
    int selectReservationCheckByMeetingRoomNo(PaymentHistoryVO vo) throws  SQLException;
    int selectJobCodeIntoProTeamNatureJoinCommonCodeByMemberID(String memberId) throws SQLException;
	/**
	 * 회원의 결제목록을 보여줌
	 * @param email
	 * @return
	 */
    List<ReservationForMyPageVO> getReservationList(String email) throws SQLException;
    List<ProjectVO> selectProjectInfoById(MemberVO vo) throws SQLException;
	/**
	 * 예약 내역을 취소요청
	 * @param no
	 */
    void deleteReservationMeetingRoom(int no) throws SQLException;
    int selectPricePerDayByno(int no) throws SQLException;

}
