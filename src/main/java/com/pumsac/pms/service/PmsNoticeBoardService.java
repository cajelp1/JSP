package com.pumsac.pms.service;

import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.ProjectNoticeBoardVO;

import java.sql.SQLException;
import java.util.Map;

public interface PmsNoticeBoardService {

	/**
	 * @내용 공지사항 리스트 가져오기
	 */

	public Map<String, Object> getNoticeList(SearchCriteria cri) throws SQLException;
	public ProjectNoticeBoardVO read(int no) throws SQLException;
	public ProjectNoticeBoardVO modify(int no) throws SQLException;
    public void regist(ProjectNoticeBoardVO vo) throws SQLException;
    int getProjectNoticeBoardCurrentVal()throws SQLException;
    public void remove	(int no) throws SQLException;



}
