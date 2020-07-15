package com.pumsac.vo;


import autovalue.shaded.com.google$.common.annotations.$VisibleForTesting;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProjectNoticeBoardVO {
    @NotNull
    private int no;
    @NotNull
    private String projectId;
    @NotNull
    @Size(min = 1, max = 60)
    private String title;
    @NotNull
    @Size(min = 1, max = 100)
    private String content;
    @NotNull
    private String writer;
    private String regDate;
    private String viewCount;
    private String fileDeleteYn;

    public ProjectNoticeBoardVO() {
    }

    public ProjectNoticeBoardVO(@NotNull int no, @NotNull String projectId, @NotNull @Size(min = 1, max = 60) String title, @NotNull @Size(min = 1, max = 100) String content, @NotNull String writer, String regDate, String viewCount, String fileDeleteYn) {
        this.no = no;
        this.projectId = projectId;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regDate = regDate;
        this.viewCount = viewCount;
        this.fileDeleteYn = fileDeleteYn;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getFileDeleteYn() {
        return fileDeleteYn;
    }

    public void setFileDeleteYn(String fileDeleteYn) {
        this.fileDeleteYn = fileDeleteYn;
    }

    @Override
    public String toString() {
        return "ProjectNoticeBoardVO{" +
                "no=" + no +
                ", projectId='" + projectId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", regDate='" + regDate + '\'' +
                ", viewCount='" + viewCount + '\'' +
                ", fileDeleteYn='" + fileDeleteYn + '\'' +
                '}';
    }
}
