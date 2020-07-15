package com.pumsac.controller.site;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pumsac.command.SearchCriteria;
import com.pumsac.service.AttachFileService;
import com.pumsac.service.SiteNoticeService;
import com.pumsac.vo.AttachFileVO;
import com.pumsac.vo.BoardVO;
import com.pumsac.vo.MemberVO;

@Controller
@RequestMapping(value="/dsp/notice/*")
public class SiteNoticeController {

	@Autowired
	private SiteNoticeService noticeService;
	
	@Autowired
	private AttachFileService attachService;
	
	@Resource(name="siteNoticeBoard")
	private String noticeAttach;
	
	@RequestMapping(value="list")
	public ModelAndView getNoticeList(SearchCriteria cri, ModelAndView mnv , HttpSession session) throws SQLException {
//		List<BoardVO> list = new ArrayList<BoardVO>();
//		list = noticeService.getAllSiteNotice();
//		MemberVO loginMember=(MemberVO)session.getAttribute("loginUser");
		Map<String,Object> dataMap = noticeService.getSiteNotice(cri);
		mnv.addAllObjects(dataMap);

//		mnv.addObject("loginInfo",loginMember);
		mnv.setViewName("/site/notice/list");
		return mnv;
	}
	
	@RequestMapping(value="regist", method = RequestMethod.GET)
	public ModelAndView registNoticeGet(ModelAndView mnv, HttpSession session) {
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if(loginUser != null) {
			if(loginUser.getRole().equals("ROLE_ADMIN")) {
				mnv.addObject("loginUserEmail", loginUser.getEmail());				
				mnv.setViewName("/site/notice/regist");
			} else {
				mnv.setViewName("/commons/main");
			}
		}
		return mnv;
	}
	
	@RequestMapping(value="regist", method = RequestMethod.POST)
	public ModelAndView registNoticePost(BoardVO board, MultipartFile file) {
		ModelAndView mnv = new ModelAndView();
		String url = "/site/notice/regist_success";
		
		try {
			noticeService.registSiteNotice(board);
			
			//파일 첨부를 했을때 파일 업로드
			if(file.getSize() != 0) {
				try {
					fileUpload(file,board.getSno(),"regist");
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} 
		
		mnv.setViewName(url);
		return mnv;
	}
		
	private void fileUpload(MultipartFile file, int sno, String key) throws IllegalStateException, IOException, SQLException {
		//저장 경로
		String savePath = noticeAttach;
		
		//원본 파일 이름
		String originFileName = file.getOriginalFilename();
		
		//저장 파일 이름
		String saveFileName = UUID.randomUUID().toString() + "$$" + originFileName;
		
		//파일 사이즈
		int fileSize = (int) file.getSize();
		
		//파일 확장자
		String extension = originFileName.substring(originFileName.lastIndexOf(".") + 1); 
		
		File attach = new File(savePath,saveFileName);
				
		AttachFileVO attachVO = new AttachFileVO();
		attachVO.setSno(sno);
		attachVO.setAttachDirectory(savePath);
		attachVO.setFileSize(fileSize);
		attachVO.setOriginFilename(originFileName);
		attachVO.setStoredFilename(saveFileName);
		attachVO.setExtension(extension);
		
		if(key.equalsIgnoreCase("regist")) {
			attachService.registSiteNoticeAttach(attachVO);			
		}
		
		if(key.equalsIgnoreCase("modify")) {
			attachService.modifyAttachFile(attachVO);
		}
		
		if(!attach.exists()) {
			attach.mkdirs();
		}
		file.transferTo(attach);
		
	}

	@RequestMapping(value="detail")
	public ModelAndView getNoticeDetail(int sno, ModelAndView mnv, HttpSession session) {
		String url = "/site/notice/detail";
		BoardVO board = new BoardVO();
		AttachFileVO attach = new AttachFileVO();
		
		try {
			board = noticeService.getSiteNoticeDetail(sno,session);
			attach = attachService.getAttachFileBySno(sno);
		} catch (Exception e) {
			e.printStackTrace();
//			url = "/error/500_error";
		}
		mnv.addObject("board", board);
		mnv.addObject("attach", attach);
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value="modify", method=RequestMethod.GET)
	public ModelAndView getModifyNoticeDetail(int sno, ModelAndView mnv, MultipartFile file, HttpSession session) {
		String url = "/site/notice/modify";
		BoardVO board = new BoardVO();
		AttachFileVO attach = new AttachFileVO();
		
		try {
			board = noticeService.getSiteNoticeDetail(sno,session);
			attach = attachService.getAttachFileBySno(sno);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		mnv.addObject("board", board);
		mnv.addObject("attach", attach);
		mnv.setViewName(url);
		return mnv;
	}
	
	@RequestMapping(value="modify", method = RequestMethod.POST)
	public ModelAndView modifyNotice(BoardVO board, ModelAndView mnv, MultipartFile file, int sno)	{
		String url = "/site/notice/modify_success";
		AttachFileVO attach = new AttachFileVO(); 

		board.setSno(sno);

		try {
			attach = attachService.getAttachFileBySno(sno);
			noticeService.modifySiteNotice(board);
			if(file != null) {
				
				//기존에 첨부파일이 있다면
				if(attach != null) {
					
					//첨부파일 변경사항 있는지 체크 
					if(attach.getOriginFilename() != file.getOriginalFilename() || attach.getFileSize() != file.getSize()){
						try {
							attachService.modifyAttachFile(attach);					
							modifyAttach(attach, file, sno);
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}
				//기존에 첨부파일을 추가하지 않았다면
				if(attach == null) {
					try {
						fileUpload(file,sno,"regist");
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		mnv.setViewName(url);
		return mnv;
	}
	
	/**
	 * 첨부파일의 수정사항을 수정함
	 * @param attach
	 * @param file
	 * @param sno
	 * @throws IllegalStateException IOException SQLException
	 */
	private void modifyAttach(AttachFileVO attach, MultipartFile file, int sno) throws IllegalStateException, IOException, SQLException {
		//기존파일 삭제
		deleteFile(attach);
		
		//새로운 파일 등록
		fileUpload(file, sno, "modify");
	}

	@GetMapping(value="remove")
	public ModelAndView removeNotice(int sno, ModelAndView mnv) {
		String url = "/site/notice/remove_success";
		try {
			noticeService.removeSiteNotice(sno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		mnv.setViewName(url);
		return mnv;
	}
	
	/**
	 * AttachFile table의 서버 저장 경로와 저장 파일 이름을 가지고 파일을 삭제하는 메소드 
	 * @param AttachVO
	 */
	private void deleteFile(AttachFileVO attach) {
		String savePath = attach.getAttachDirectory();
		String fileName = attach.getStoredFilename();
		
		File file = new File(savePath, fileName);
		if(file.exists()) {
			file.delete();			
		}
	}
}