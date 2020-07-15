package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.MemberLoginLogVO;

public interface LoginLogDAO {
	
	public List<MemberLoginLogVO> selectLogList() throws SQLException;
	public void insertLoginLog(MemberLoginLogVO logVO) throws SQLException;
	public void deleteAllLoginLog() throws SQLException;
	
}
