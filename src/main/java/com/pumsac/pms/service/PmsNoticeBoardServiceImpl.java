package com.pumsac.pms.service;

import com.pumsac.command.PageMaker;
import com.pumsac.command.SearchCriteria;
import com.pumsac.pms.dao.PmsNoticeBoardDao;
import com.pumsac.vo.ProjectNoticeBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("pmsNoticeBoardService")
public class PmsNoticeBoardServiceImpl implements PmsNoticeBoardService {
	@Autowired
	PmsNoticeBoardDao pmsNoticeBoardDao;
	@Override
	public Map<String, Object> getNoticeList(SearchCriteria cri) throws SQLException {

		List<ProjectNoticeBoardVO> noticeList = pmsNoticeBoardDao.selectSearchNoticeList(cri);

		/*
		 * for(NoticeVO notice : noticeList) { List<NoticeAttachVO>
		 * attachList=attachDAO.selectAttachesByNno(notice.getNno());
		 * notice.setAttachList(attachList); }
		 */

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(pmsNoticeBoardDao.selectSearchNoticeListCount(cri));


		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("noticeList", noticeList);

		dataMap.put("pageMaker", pageMaker);
		return dataMap;
	}

	public ProjectNoticeBoardVO read(int no) throws SQLException {
		pmsNoticeBoardDao.viewCountIncrease(no);
		ProjectNoticeBoardVO projectNoticeBoardVO = pmsNoticeBoardDao.selectByNo(no);
		return projectNoticeBoardVO;
	}

	@Override
	public ProjectNoticeBoardVO modify(int no) throws SQLException {
		ProjectNoticeBoardVO projectNoticeBoardVO = pmsNoticeBoardDao.selectByNo(no);
		return projectNoticeBoardVO;
	}

	@Override
	public void regist(ProjectNoticeBoardVO vo) throws SQLException {

		pmsNoticeBoardDao.insertNoticeBoard(vo);


	}

	@Override
	public int getProjectNoticeBoardCurrentVal() throws SQLException {
		return pmsNoticeBoardDao.selectProjectNoticeBoardCurrentval();
	}

	@Override
	public void remove(int no) throws SQLException {
		pmsNoticeBoardDao.deleteByNo(no);
	}
}
