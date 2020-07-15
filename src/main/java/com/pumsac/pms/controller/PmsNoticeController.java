package com.pumsac.pms.controller;

import com.pumsac.command.SearchCriteria;
import com.pumsac.dao.MemberDao;
import com.pumsac.pms.dao.PmsNoticeBoardDao;
import com.pumsac.pms.service.CommonProjectService;
import com.pumsac.pms.service.PmsNoticeBoardService;
import com.pumsac.pms.service.ProjectNoticeBoardAttachService;
import com.pumsac.vo.*;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/pms/team/*")
public class PmsNoticeController {
    @Autowired
    PmsNoticeBoardService pmsNoticeBoardService;
    @Autowired
    ProjectNoticeBoardAttachService projectNoticeBoardAttachService;
    @Resource(name = "teamNoticeBoard")
    String fileUploadPath;
    @Autowired
    CommonProjectService commonProjectService;
    @Autowired
    PmsNoticeBoardDao pmsNoticeBoardDao;
    @Autowired
    MemberDao memberDao;

    @GetMapping("notice/list")
    public String noticlist(int projectId, SearchCriteria cri, Model model, HttpSession session) throws SQLException {
        MemberVO memberVO = (MemberVO) session.getAttribute("loginUser");
        cri.setProjectId(projectId);
        Map<String, Object> dataMap = pmsNoticeBoardService.getNoticeList(cri);
        ProTeamVO proTeamVO = new ProTeamVO();
        proTeamVO.setMemberId(memberVO.getEmail());
        proTeamVO.setProjectId(projectId);
        int job = pmsNoticeBoardDao.selectJobByProjectId(proTeamVO);
        cri.setJob(job);
        dataMap.put("job", job);
        model.addAttribute("projectId", projectId);
        model.addAllAttributes(dataMap);
        return "pms/team/notice/list";
    }

    @PostMapping("notice/list")
    public void doSearch(int projectId, SearchCriteria cri, Model model, HttpSession session) throws SQLException {
        MemberVO memberVO = (MemberVO) session.getAttribute("loginUser");
        cri.setProjectId(projectId);
        Map<String, Object> dataMap = pmsNoticeBoardService.getNoticeList(cri);
        ProTeamVO proTeamVO = new ProTeamVO();
        proTeamVO.setMemberId(memberVO.getEmail());
        proTeamVO.setProjectId(projectId);
        int job = pmsNoticeBoardDao.selectJobByProjectId(proTeamVO);
        cri.setJob(job);
        dataMap.put("job", job);
        model.addAttribute("projectId", projectId);
        model.addAllAttributes(dataMap);
    }

    @GetMapping("notice/detail")
    public String detail(Model model, int no, int projectId) throws SQLException {
        ProjectNoticeBoardVO proBoardVO = pmsNoticeBoardService.read(no);
        ProjectNoticeAttachFileVO vo = projectNoticeBoardAttachService.getFileListAtPmsNoticBoardByNo(no);
        if (vo != null)
            model.addAttribute("attachFileList", vo);
        model.addAttribute("proBoardVO", proBoardVO);
        return "pms/team/notice/detail";
    }

    @GetMapping("notice/regist")
    public String regist(String projectId, HttpSession session, Model model) {
        MemberVO login = (MemberVO) session.getAttribute("loginUser");
        model.addAttribute("id", login.getEmail());
        model.addAttribute("projectId", projectId);
        model.addAttribute("projectNoticeBoardVO", new ProjectNoticeBoardVO());
        return "pms/team/notice/regist";
    }

    @PostMapping("notice/regist")
    public String doRegist(@ModelAttribute("projectNoticeBoardVO") @Valid ProjectNoticeBoardVO vo, BindingResult bindingResult, MultipartFile file, ModelAndView modelAndView, HttpSession session) throws SQLException, CoolsmsException {
        MemberVO login = (MemberVO) session.getAttribute("loginUser");

        if (bindingResult.hasErrors()) {
            return "pms/team/notice/regist";
        } else {
            pmsNoticeBoardService.regist(vo);
            int seq = pmsNoticeBoardService.getProjectNoticeBoardCurrentVal();
            if (!file.isEmpty()) {
                try {
                    fileUpload(file, seq);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
           /* List<MemberVO> projectTeamMemberList = pmsNoticeBoardDao.selectEmailByProjectId(Integer.parseInt(vo.getProjectId()));
            String api_key = "NCSIZYZBIEEPCQCY";
            String api_secret = "JWFOMUWT17LSKNVXLYP8LVZNLXOOYRQH";
            Message coolsms = new Message(api_key, api_secret);
            HashMap<String, String> params = null;
            for (MemberVO memberVO : projectTeamMemberList) {
                MemberVO getSmsMember = new MemberVO();
                getSmsMember = memberDao.selectMemberByEmail(memberVO.getEmail());

                params = new HashMap<String, String>();
                params.put("to", getSmsMember.getPhoneNumber());
                params.put("from", "010-2125-4151"); //무조건 자기번호 (인증)
                params.put("type", "SMS");
                params.put("text", "프로젝트 공지사항에 새로운 글이 등록되었으니 확인 바랍니다.");
                params.put("app_version", "test app 1.2");
                JSONObject smsResult = coolsms.send(params); // 보내기
            }*/
            return "pms/team/notice/regist_success";
        }
    }

    @GetMapping("notice/modify")
    public void modify(int no, int projectId, Model model) throws SQLException {
        ProjectNoticeBoardVO proBoardVO = pmsNoticeBoardService.modify(no);
        ProjectNoticeAttachFileVO vo = projectNoticeBoardAttachService.getFileListAtPmsNoticBoardByNo(no);
        if (vo != null)
            model.addAttribute("attachFileList", vo);
        model.addAttribute("proBoardVO", proBoardVO);
        model.addAttribute("ProjectNoticeAttachFileVO", vo);

    }

    @PostMapping("notice/modify")
    public String doModify(int no, int projectId, @ModelAttribute("projectNoticeBoardVO") @Valid ProjectNoticeBoardVO vo, BindingResult bindingResult, MultipartFile file, ModelAndView modelAndView) throws SQLException {
        pmsNoticeBoardService.modify(no);
        ProjectNoticeAttachFileVO projectNoticeAttachFileVO = projectNoticeBoardAttachService.getFileListAtPmsNoticBoardByNo(no);
        if (vo.getFileDeleteYn().equals("y")) {
            if (projectNoticeAttachFileVO != null) {
                projectNoticeBoardAttachService.removeProjectNoticeFileInfo(no);
            }
            try {
                fileUpload(file, no);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return "pms/team/notice/modify_success";
    }

    @GetMapping("notice/remove")
    public String doRemove(int no) throws Exception {
        ResponseEntity<String> entity = null;
        ProjectNoticeAttachFileVO vo = projectNoticeBoardAttachService.getFileListAtPmsNoticBoardByNo(no);
        if (vo != null) {
            String filePath = vo.getAttachDirectory();
            String fileName = vo.getStoredFilename();

            String pathname = filePath + File.separator + fileName;
            File file = new File(pathname);
            try {
                if (file.exists()) {
                    file.delete();
                }
                projectNoticeBoardAttachService.removeProjectNoticeFileInfo(no);
                pmsNoticeBoardService.remove(no);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            pmsNoticeBoardService.remove(no);
        }
        return "pms/team/notice/remove_success";
    }


    //파일 업로드
    private void fileUpload(MultipartFile file, int seq) throws Exception, IOException {
        ProjectNoticeAttachFileVO attachFileVO = new ProjectNoticeAttachFileVO();
        attachFileVO.setOriginFilename(file.getOriginalFilename());
        int pos = file.getOriginalFilename().lastIndexOf(".");
        attachFileVO.setExtension(file.getOriginalFilename().substring(pos + 1));
        attachFileVO.setStoredFilename(UUID.randomUUID().toString().replaceAll("-", "") + "." + file.getOriginalFilename().substring(pos + 1));
        attachFileVO.setFileSize((int) file.getSize());
        attachFileVO.setNo(seq);
        attachFileVO.setAttachDirectory(fileUploadPath);
        File storedFile = new File(fileUploadPath + File.separator + attachFileVO.getStoredFilename());
        if (!storedFile.exists())
            storedFile.mkdirs();
        projectNoticeBoardAttachService.registProjectNoticeAttach(attachFileVO);
        file.transferTo(storedFile);

    }

    //파일 다운 로드  기능
    @ResponseBody
    @PostMapping("notice/downloadFile")
    public void fileDownLoad(String fileName) throws SQLException, Exception {
        System.out.println(fileName);

        ProjectNoticeAttachFileVO attachFileVO = projectNoticeBoardAttachService.getFileListAtPmsNoticBoardByOriginFileName(fileName);
        System.out.println(attachFileVO.toString());
        FileInputStream inputStream = new FileInputStream(attachFileVO.getAttachDirectory() + File.separator + attachFileVO.getStoredFilename());
        String copyFilePath = "c:\\Users\\" + System.getenv("USERNAME") + "\\Downloads";
        File file = new File(copyFilePath);
        if (!file.exists())
            file.mkdirs();
        FileOutputStream outputStream = new FileOutputStream(copyFilePath + File.separator + attachFileVO.getOriginFilename());
        FileChannel fcin = inputStream.getChannel();
        FileChannel fcout = outputStream.getChannel();
        long size = fcin.size();
        fcin.transferTo(0, size, fcout);
        fcout.close();
        fcin.close();
        outputStream.close();
        inputStream.close();
    }
}
