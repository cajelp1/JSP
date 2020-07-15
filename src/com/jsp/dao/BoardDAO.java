package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.BoardVO;
import com.jsp.request.SearchCriteria;

public interface BoardDAO {
	
	int selectBoardSeqValue() throws SQLException; 
	
	List<BoardVO> selectBoardCriteria(SearchCriteria cri) throws SQLException;
	
	int selectBoardCriteriaTotalCount(SearchCriteria cri) throws SQLException;
	
	BoardVO selectBoardByBno(int bno) throws SQLException;
	
	void insertBoard(BoardVO board) throws SQLException;
	
	void updateBoard(BoardVO board) throws SQLException;
	
	void deleteBoard(int bno) throws SQLException;
	
	void increaseViewCnt(int bno) throws SQLException;
	
}
