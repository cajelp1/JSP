package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.MemberLoginLogVO;

public interface LoginLogService {
	
	List<MemberLoginLogVO> getMemberLoginLogList() throws SQLException;
	public void write(List<MemberLoginLogVO> logVO) throws Exception;
}
