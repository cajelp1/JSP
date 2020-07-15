package com.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.spring.dto.MemberLoginLogVO;

public class LoginLogDAOImpl implements LoginLogDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<MemberLoginLogVO> selectLogList() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<MemberLoginLogVO> logList = null;

		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();

			String sql = "select * from member_login_log";

			rs = stmt.executeQuery(sql);

			logList = new ArrayList<MemberLoginLogVO>();

			while (rs.next()) {
				MemberLoginLogVO log = new MemberLoginLogVO();
				log.setId(rs.getString("id"));
				log.setPhone(rs.getString("phone"));
				log.setEmail(rs.getString("email"));
				log.setIndate(rs.getDate("indate"));
				log.setIpAddress(rs.getString("ipAddress"));

				logList.add(log);
			}
		} finally {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
		return logList;

	}

	@Override
	public void insertLoginLog(MemberLoginLogVO logVO) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into member_login_log(id,phone,email,ipAddress,indate) " 
					 + "values(?,?,?,?,?)";

		try {
			conn = dataSource.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, logVO.getId());
			pstmt.setString(2, logVO.getPhone());
			pstmt.setString(3, logVO.getEmail());
			pstmt.setString(4, logVO.getIpAddress());
			pstmt.setDate(5, new java.sql.Date(logVO.getIndate().getTime()));

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

	}

	@Override
	public void deleteAllLoginLog() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		String sql = "delete from member_login_log";
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();

			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw e;
		} finally {
			if(stmt!=null)stmt.close();
			if(conn!=null)conn.close();
		}
	}
}
