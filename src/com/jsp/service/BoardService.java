package com.jsp.service;

import java.sql.SQLException;
import java.util.Map;

import com.jsp.dto.AttachVO;
import com.jsp.dto.BoardVO;
import com.jsp.request.SearchCriteria;

public interface BoardService {
	
	Map<String,Object> getBoardList(SearchCriteria cri)throws SQLException;
	
	//조회증가
	BoardVO getBoard(int bno)throws SQLException;
	//조회 그대로
	BoardVO getBoardForModify(int bno)throws SQLException;
	
	void regist(BoardVO board)throws SQLException;
	void modify(BoardVO board)throws SQLException;
	void remove(int bno)throws SQLException;
	
	AttachVO getAttachByAno(int ano)throws SQLException;
	int getAttachAnoNextVal() throws SQLException;
	void registAttach(AttachVO attach)throws SQLException;
	void removeAttach(int ano)throws SQLException;
	
}
