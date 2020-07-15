package com.pumsac.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pumsac.command.PageMaker;
import com.pumsac.command.SearchCriteria;
import com.pumsac.dao.MemberDao;
import com.pumsac.dao.SiteNoticeDao;
import com.pumsac.vo.BoardVO;
import com.pumsac.vo.MemberVO;

@Service("SiteNoticeService")
public class SiteNoticeServiceImpl implements SiteNoticeService {

	@Autowired
	private SiteNoticeDao noticeDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public void registSiteNotice(BoardVO board) throws SQLException {
		int sno = noticeDao.getNoticeSequence();
		board.setSno(sno);
		noticeDao.insertSiteNotice(board);
	}

	@Override
	public List<BoardVO> getAllSiteNotice() throws SQLException {
		List<BoardVO> list = new ArrayList<BoardVO>();
//		list = noticeDao.selectAllSiteNotice();
		return list;
	}

	@Override
	public void modifySiteNotice(BoardVO board) throws SQLException {
		noticeDao.updateSiteNotice(board);
	}

	@Override
	public void removeSiteNotice(int sno) throws SQLException {
		noticeDao.deleteSiteNotice(sno);
	}

	@Override
	public BoardVO getSiteNoticeDetail(int sno, HttpSession session) throws SQLException {
		BoardVO board = new BoardVO();
		board = noticeDao.selectDetailSiteNotice(sno);
		
		MemberVO mem = memberDao.selectMemberByEmail(board.getWriter());
		board.setWriterNickName(mem.getNickname());
		
		//작성자가 아닌 사람이 조회했을 때에만 조회수 올림
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if(loginUser == null) {
			noticeDao.increaseSiteNoticeViewCount(sno);
		} else if (loginUser.getEmail() != board.getWriter()) {
			noticeDao.increaseSiteNoticeViewCount(sno);
		}
		return board;
	}

	@Override
	public Map<String, Object> getSiteNotice(SearchCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		List<BoardVO> list = noticeDao.selectAllSiteNotice(cri);
		List<BoardVO> curr_list = noticeDao.selectCurrentSiteNotice(cri);

		int totalCount = noticeDao.selectCountSiteNotice(cri);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		dataMap.put("curr_list", curr_list);
		dataMap.put("boardList", list);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

}
