package com.pumsac.vo;

public class ProjectVO {
    private String projectId;
    private String projectName;
    private String projectExplain;
    private String startDate;
    private String endDate;
    private String memberId;
    private String regDate;
    private String register;
    private String nickname;
    private String name;
    private String job;
    public ProjectVO() {
    }

    public ProjectVO(String projectId, String projectName, String projectExplain, String startDate, String endDate, String memberId, String regDate, String register, String nickname, String name, String job) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectExplain = projectExplain;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memberId = memberId;
        this.regDate = regDate;
        this.register = register;
        this.nickname = nickname;
        this.name = name;
        this.job = job;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectExplain() {
        return projectExplain;
    }

    public void setProjectExplain(String projectExplain) {
        this.projectExplain = projectExplain;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "ProjectVO{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectExplain='" + projectExplain + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", memberId='" + memberId + '\'' +
                ", regDate='" + regDate + '\'' +
                ", register='" + register + '\'' +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}

