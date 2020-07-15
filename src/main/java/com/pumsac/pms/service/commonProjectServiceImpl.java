package com.pumsac.pms.service;

import com.pumsac.pms.dao.CommonProjectDao;
import com.pumsac.vo.MemberVO;
import com.pumsac.vo.ProBoardInfoVO;
import com.pumsac.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("commonProjectService")
public class commonProjectServiceImpl  implements  CommonProjectService{
    @Autowired
    private CommonProjectDao commonProjectDao;
    @Override
    public List<ProjectVO> getProjectId(MemberVO vo) throws SQLException {
       return  commonProjectDao.selectProjectIdById(vo);
    }

    @Override
    public ProBoardInfoVO getProjectBoardInfo(ProBoardInfoVO vo) throws SQLException {
        return commonProjectDao.selectNoticeByProjectId(vo);
    }
}
