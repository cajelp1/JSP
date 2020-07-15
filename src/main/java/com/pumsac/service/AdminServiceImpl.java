package com.pumsac.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import com.pumsac.vo.AdminCommonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pumsac.command.MeetingRoomJusoCommand;
import com.pumsac.command.PageMaker;
import com.pumsac.command.SearchCriteria;
import com.pumsac.dao.AdminDao;
import com.pumsac.vo.MeetingRoomAttachFileVO;
import com.pumsac.vo.MeetingRoomVO;
import com.pumsac.vo.MemberVO;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;

	@Resource(name = "meetingRoomPictures")
	String uploadPath;

	@Override
	public Map<String, Object> getMeetingRoomList(SearchCriteria cri) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<MeetingRoomJusoCommand> roomJusoList = null;
		roomJusoList = adminDao.selectMeetingRoomList(cri);
		int totalCount = adminDao.countMeetingRoomList(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);
		
		map.put("roomJusoList", roomJusoList);
		map.put("pageMaker", pageMaker);
		
		return map;
	}

	@Override
	public MeetingRoomJusoCommand getMeetingRoomDetail(int no) throws Exception {
		MeetingRoomJusoCommand room = null;
		
		room = adminDao.selectMeetingRoomDetail(no);
		List<MeetingRoomAttachFileVO> attachList = adminDao.getDetailImgByRoomNo(room.getRoomNo());
		MeetingRoomAttachFileVO profile = adminDao.selectMeetingRoomProfileFileByRoomNo(room.getRoomNo());
		room.setProfile(profile);
		room.setAttachFileList(attachList);

		return room;
	}

	@Override
	public Map<String, Object> getMemberList(SearchCriteria cri) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<MemberVO> memberList = null;
		memberList = adminDao.selectMember(cri);
		int totalCount = adminDao.countMember(cri);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(totalCount);

		map.put("memberList", memberList);
		map.put("pageMaker", pageMaker);
		return map;
	}

	@Override
	public MemberVO getMemberDetail(String email) throws Exception {
		MemberVO member = null;
		member = adminDao.selectMemberByEmail(email);
		return member;
	}

	@Override
	public void modifyMember(MemberVO member) throws Exception {
		adminDao.updateMember(member);
	}

	@Override
	public void modifyMeetingRoom(MeetingRoomJusoCommand meetingRoom) throws SQLException {
		int jusoNo = adminDao.getJusoNoByArea(meetingRoom.getArea());
		MeetingRoomVO room = meetingRoom.toMeetingRoomVO();
		int roomNo = room.getNo();
		room.setJusoNo(jusoNo);
		adminDao.updateMeetingRoomInfo(room);

		modifyProfileImg(meetingRoom, roomNo);

		List<MeetingRoomAttachFileVO> oldVO = adminDao.getDetailImgByRoomNo(roomNo);
		if(oldVO.size() != 0) {
			compareDetailImg(meetingRoom, roomNo);			
		}
		// 나머지 사진 등록
		List<MultipartFile> list = meetingRoom.getPicture();
		uploadDetailImg(list, roomNo);
	}
	/**
	 * 이전에 있는 파일 정보와 입력받은 파일 정보를 비교해 삭제
	 * @param meetingRoom
	 * @param roomNo
	 * @throws SQLException
	 */
	private void compareDetailImg(MeetingRoomJusoCommand meetingRoom, int roomNo) throws SQLException {
		List<MultipartFile> inputList = meetingRoom.getPicture();
		if (inputList == null) {
			return;
		}
		List<MeetingRoomAttachFileVO> oldVO = adminDao.getDetailImgByRoomNo(roomNo);
		if(oldVO != null) {
			for (int i = 0; i < inputList.size(); i++) {
				if (inputList.get(i).getOriginalFilename() != oldVO.get(i).getOriginFileName()) {
					if (inputList.get(i).getSize() != oldVO.get(i).getFileSize()) {
						File oldFile = new File(uploadPath + File.separator + oldVO.get(i).getFileName());
						if (oldFile.exists()) {
							oldFile.delete();
						}
						adminDao.deleteMeetingRoomAttachFileByNo(oldVO.get(i).getNo());
					}
				}
			}			
		}
	}

	private void modifyProfileImg(MeetingRoomJusoCommand meetingRoom, int roomNo) throws SQLException {
		if (meetingRoom.getProfileImg() != null) {

			// 파일 수정
			MeetingRoomAttachFileVO profileVO = adminDao.selectMeetingRoomProfileFileByRoomNo(roomNo);
			MultipartFile profile = meetingRoom.getProfileImg();

			String originFileName = profile.getOriginalFilename();
			String fileName = UUID.randomUUID().toString() + "$$" + originFileName;
			int fileSize = (int) profile.getSize();
			// DB 정보와 비교
			if (profileVO.getOriginFileName() != profile.getOriginalFilename()) {
				if (profileVO.getFileSize() != (int) profile.getSize()) {
					// 이전 DB 정보 삭제
					MeetingRoomAttachFileVO oldFileVO = adminDao.selectMeetingRoomProfileFileByRoomNo(roomNo);
					adminDao.deleteMeetingRoomAttachFileByNo(oldFileVO.getNo());
					String oldFileName = oldFileVO.getFileName();
					File oldFile = new File(uploadPath + File.separator + oldFileName);
					if (oldFile.exists()) {
						oldFile.delete();
					}

					// 수정할 DB 정보 regist
					MeetingRoomAttachFileVO modifiedProfile = new MeetingRoomAttachFileVO();
					int attachNo = adminDao.selectMeetingRoomAttachSeq();
					modifiedProfile.setNo(attachNo);
					modifiedProfile.setFileName(fileName);
					modifiedProfile.setFileSize(fileSize);
					modifiedProfile.setOriginFileName(originFileName);
					modifiedProfile.setProfileyn("y");
					modifiedProfile.setRoomNo(roomNo);
					modifiedProfile.setUploadPath(uploadPath);
					adminDao.insertMeetingRoomAttachFile(modifiedProfile);

					// 파일 전송
					File file = new File(uploadPath, fileName);
					try {
						profile.transferTo(file);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	@Override
	public void registMeetingRoom(MeetingRoomJusoCommand meetingRoom) throws Exception {
		int no = adminDao.selectMeetingRoomSeq();
		int jusoNo = adminDao.getJusoNoByArea(meetingRoom.getArea());

		System.out.println(no);
		MeetingRoomVO room = meetingRoom.toMeetingRoomVO();
		room.setNo(no);
		room.setJusoNo(jusoNo);
		adminDao.insertMeetingRoomInfo(room);

		// 첨부파일 업로드
		uploadAttach(meetingRoom, no);

	}

	@Override
	public void removeMeetingRoom(MeetingRoomVO meetingRoom) throws Exception {

		adminDao.deleteMeetingRoomInfo(meetingRoom);
		
		/**
		 * 회의실 첨부 파일 정보 가져와서 파일 삭제
		 */
		//deleteAllAttach(roomNo);
	}

	private void deleteAllAttach(int roomNo) {
		List<MeetingRoomAttachFileVO> list = adminDao.getAttachFileListByRoomNo(roomNo);
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) != null) {
					String fileName = list.get(i).getFileName();
					String savePath = list.get(i).getUploadPath();

					File file = new File(savePath, fileName);
					if (file.exists()) {
						file.delete();
						System.out.println("파일 삭제 완료");
					}
				}
			}
		}
	}

	@Override
	public void removeAttachFile(int no) throws SQLException {
		MeetingRoomAttachFileVO attach = adminDao.selectAttachFileByNo(no);
		if (attach != null) {
			String fileName = attach.getFileName();
			String savePath = attach.getUploadPath();
			System.out.println(fileName);
			System.out.println(savePath);
			System.out.println(savePath + File.separator + fileName);
			File file = new File(savePath + File.separator + fileName);
			if (file.exists()) {
				file.delete();
				System.out.println("파일 삭제 완료");
			}
			adminDao.deleteMeetingRoomAttachFileByNo(no);
		}
	}

	private void uploadAttach(MeetingRoomJusoCommand meetingRoom, int no) throws SQLException {

		// 사무실 프로필 이미지 등록
		uploadProfileImg(meetingRoom, no);

		// 나머지 사진 등록
		List<MultipartFile> list = meetingRoom.getPicture();
		uploadDetailImg(list, no);

	}

	private void uploadDetailImg(List<MultipartFile> list, int roomNo) throws SQLException {
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) != null) {
					MeetingRoomAttachFileVO attachVO = new MeetingRoomAttachFileVO();
					// 원본 파일 이름
					String originFileName = list.get(i).getOriginalFilename();
					// UUID 파일 이름
					String fileName = UUID.randomUUID().toString() + "$$" + originFileName;
					// 파일 사이즈
					int fileSize = (int) list.get(i).getSize();
					// VO에 세팅
					int attachNo = adminDao.selectMeetingRoomAttachSeq();
					attachVO.setNo(attachNo);
					attachVO.setFileName(fileName);
					attachVO.setOriginFileName(originFileName);
					attachVO.setUploadPath(uploadPath);
					attachVO.setRoomNo(roomNo);
					attachVO.setProfileyn("n");
					attachVO.setFileSize(fileSize);

					adminDao.insertMeetingRoomAttachFile(attachVO);

					// 파일 전송
					File file = new File(uploadPath + File.separator + fileName);
					if (!file.exists()) {
						file.mkdirs();
					}
					try {
						list.get(i).transferTo(file);
					} catch (IllegalStateException | IOException e) {
						System.out.println("파일전송 실패");
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void uploadProfileImg(MeetingRoomJusoCommand meetingRoom, int roomNo) throws SQLException {
		MultipartFile profileImg = meetingRoom.getProfileImg();
		if (profileImg != null) {
			MeetingRoomAttachFileVO attachVO = new MeetingRoomAttachFileVO();
			String originFileName = profileImg.getOriginalFilename();
			String fileName = UUID.randomUUID().toString() + "$$" + originFileName;
			int fileSize = (int) profileImg.getSize();
			// vo에 세팅
			int attachNo = adminDao.selectMeetingRoomAttachSeq();
			attachVO.setNo(attachNo);
			attachVO.setFileName(fileName);
			attachVO.setOriginFileName(originFileName);
			attachVO.setUploadPath(uploadPath);
			attachVO.setRoomNo(roomNo);
			attachVO.setProfileyn("y");
			attachVO.setFileSize(fileSize);

			adminDao.insertMeetingRoomAttachFile(attachVO);

			File file = new File(uploadPath, fileName);
			if (!file.exists()) {
				file.mkdirs();
			}
			try {
				profileImg.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				System.out.println("사무실 프로필 이미지 전송 실패");
			}
		}
	}

	@Override
	public AdminCommonVO getPrivacyRule(String item) throws SQLException {
		return null;
	}

	@Override
	public AdminCommonVO modifyPrivacyRule(String item) throws SQLException {
		return null;
	}
}
