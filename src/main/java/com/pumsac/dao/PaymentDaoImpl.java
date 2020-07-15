package com.pumsac.dao;

import com.pumsac.vo.MemberVO;
import com.pumsac.vo.PaymentHistoryVO;
import com.pumsac.vo.ProjectVO;
import com.pumsac.vo.ReservationForMyPageVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository("paymentDao")
public class PaymentDaoImpl implements  PaymentDao{
    @Autowired
    private SqlSession sqlSession;

    @Override
    public void insertPayment(PaymentHistoryVO vo) throws SQLException {
        sqlSession.update("payment-mapper.insertPayment",vo);
    }

    @Override
    public int getReservateSeqNetxval() throws SQLException {
        return sqlSession.selectOne("payment-mapper.getReservateSeqNetxval");
    }

    @Override
    public List<Map<String, String>> selectReservationByMeetingRoomNo(int no) throws SQLException {
        return sqlSession.selectList("payment-mapper.selectReservationByMeetingRoomNo",no);
    }

    @Override
    public int selectReservationCheckByMeetingRoomNo(PaymentHistoryVO vo) throws SQLException {
        return sqlSession.selectOne("payment-mapper.selectReservationCheckByMeetingRoomNo",vo);
    }

    @Override
    public int selectJobCodeIntoProTeamNatureJoinCommonCodeByMemberID(String memberId) throws SQLException {
        return sqlSession.selectOne("payment-mapper.selectJobCodeIntoProTeamNatureJoinCommonCodeByMemberID",memberId);
    }

	@Override
	public List<ReservationForMyPageVO> getReservationList(String email) throws SQLException {
    	List<ReservationForMyPageVO> list = null;
    	list = sqlSession.selectList("payment-mapper.getReservationList",email);
		return list;
	}

    @Override
    public List<ProjectVO> selectProjectInfoById(MemberVO vo) throws SQLException {
        return sqlSession.selectList("payment-mapper.selectProjectInfoById",vo);
    }

	@Override
	public void deleteReservationMeetingRoom(int no) throws SQLException {
		sqlSession.update("payment-mapper.deleteReservationMeetingRoom",no);
	}

    @Override
    public int selectPricePerDayByno(int no) throws SQLException {
        return sqlSession.selectOne("payment-mapper.selectPricePerDayByno",no);
    }
}
