package com.pumsac.dao;

import java.sql.SQLException;
import java.util.List;

import com.pumsac.command.SearchCriteria;
import com.pumsac.vo.BoardVO;

public interface SiteNoticeDao {
	
	//공지사항 등록
	public void insertSiteNotice(BoardVO board) throws SQLException;
	
	// 전체 리스트 조회
	public List<BoardVO> selectAllSiteNotice(SearchCriteria cri) throws SQLException;
	
	// 상세보기
	public BoardVO selectDetailSiteNotice(int sno) throws SQLException;
	
	//공지사항 수정
	public void updateSiteNotice(BoardVO board) throws SQLException;

	//공지사항 삭제
	public void deleteSiteNotice(int sno) throws SQLException;
	
	// 조회수 증가
	public void increaseSiteNoticeViewCount (int sno) throws SQLException;
	
	//등록을 위해 seq값 가져오기
	public int getNoticeSequence() throws SQLException;
	
	//전체 게시글 갯수 가져오기
	public int selectCountSiteNotice(SearchCriteria cri) throws SQLException;

	//게시기간 중인 글 목록 가져옴
	public List<BoardVO> selectCurrentSiteNotice(SearchCriteria cri) throws SQLException;
}
