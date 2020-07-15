package com.pumsac.vo;

public class MeetingRoomAttachFileVO {

	private int no;
	private int roomNo;
	private String fileName;
	private String originFileName;
	private String uploadPath;
	private String profileyn;
	private int fileSize;

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getProfileyn() {
		return profileyn;
	}

	public void setProfileyn(String profileyn) {
		this.profileyn = profileyn;
	}

	public String getOriginFileName() {
		return originFileName;
	}

	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MeetingRoomAttachFileVO() {
	}

	public MeetingRoomAttachFileVO(int no, int roomNo, String fileName, String originFileName, String uploadPath, String profileyn, int fileSize) {
		this.no = no;
		this.roomNo = roomNo;
		this.fileName = fileName;
		this.originFileName = originFileName;
		this.uploadPath = uploadPath;
		this.profileyn = profileyn;
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "MeetingRoomAttachFileVO{" +
				"no=" + no +
				", roomNo=" + roomNo +
				", fileName='" + fileName + '\'' +
				", originFileName='" + originFileName + '\'' +
				", uploadPath='" + uploadPath + '\'' +
				", profileyn='" + profileyn + '\'' +
				", fileSize=" + fileSize +
				'}';
	}
}
