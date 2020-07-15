package com.pumsac.vo;

public class PrjPsVO {
    private int no;
    private String processPercent;
    private String approvalYn;
    private String title;
    private String content;
    private String charger;
    private String startDate;
    private String endDate;
    private String projectId;
    private String job;

    public PrjPsVO() {
    }

    public PrjPsVO(int no, String processPercent, String approvalYn, String title, String content, String charger, String startDate, String endDate, String projectId, String job) {
        this.no = no;
        this.processPercent = processPercent;
        this.approvalYn = approvalYn;
        this.title = title;
        this.content = content;
        this.charger = charger;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectId = projectId;
        this.job = job;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getProcessPercent() {
        return processPercent;
    }

    public void setProcessPercent(String processPercent) {
        this.processPercent = processPercent;
    }

    public String getApprovalYn() {
        return approvalYn;
    }

    public void setApprovalYn(String approvalYn) {
        this.approvalYn = approvalYn;
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

    public String getCharger() {
        return charger;
    }

    public void setCharger(String charger) {
        this.charger = charger;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "PrjPsVO{" +
                "no=" + no +
                ", processPercent='" + processPercent + '\'' +
                ", approvalYn='" + approvalYn + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", charger='" + charger + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", projectId='" + projectId + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
