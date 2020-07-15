package com.pumsac.vo;

import java.util.Date;

public class ProjectNoticeAttachFileVO {
    private int no;
    private String attachDirectory;
    private String storedFilename;
    private String originFilename;
    private String extension;
    private int fileSize;
    private Date regdate;

    public ProjectNoticeAttachFileVO() {
    }

    public ProjectNoticeAttachFileVO(int no, String attachDirectory, String storedFilename, String originFilename, String extension, int fileSize, Date regdate) {
        this.no = no;
        this.attachDirectory = attachDirectory;
        this.storedFilename = storedFilename;
        this.originFilename = originFilename;
        this.extension = extension;
        this.fileSize = fileSize;
        this.regdate = regdate;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getAttachDirectory() {
        return attachDirectory;
    }

    public void setAttachDirectory(String attachDirectory) {
        this.attachDirectory = attachDirectory;
    }

    public String getStoredFilename() {
        return storedFilename;
    }

    public void setStoredFilename(String storedFilename) {
        this.storedFilename = storedFilename;
    }

    public String getOriginFilename() {
        return originFilename;
    }

    public void setOriginFilename(String originFilename) {
        this.originFilename = originFilename;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    @Override
    public String toString() {
        return "ProjectNoticeAttachFileVO{" +
                "no=" + no +
                ", attachDirectory='" + attachDirectory + '\'' +
                ", storedFilename='" + storedFilename + '\'' +
                ", originFilename='" + originFilename + '\'' +
                ", extension='" + extension + '\'' +
                ", fileSize=" + fileSize +
                ", regdate=" + regdate +
                '}';
    }
}
