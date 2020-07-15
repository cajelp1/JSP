package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dao.LoginLogDAO;
import com.spring.dto.MemberLoginLogVO;

public class LoginLogServiceImpl implements LoginLogService{
	
	private LoginLogDAO logDAO;
	public void setLogDAO(LoginLogDAO logDAO) {
		this.logDAO=logDAO;
	}
	
	@Override
	public List<MemberLoginLogVO> getMemberLoginLogList() throws SQLException {
		List<MemberLoginLogVO> logList = logDAO.selectLogList();				
		return logList;
	}

	@Override
	public void write(List<MemberLoginLogVO> logList) throws Exception {

		logDAO.deleteAllLoginLog();
		
		for(MemberLoginLogVO logVO : logList) {
			logDAO.insertLoginLog(logVO);
		}
	}
}

