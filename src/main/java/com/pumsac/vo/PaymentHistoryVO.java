package com.pumsac.vo;

public class PaymentHistoryVO {
    private int no;
    private String register;
    private int amount;
    private int  meetingNo;
    private String projectId;
    private String startDate;
    private String endDate;
    private String regDate;


    public PaymentHistoryVO() {
    }

    public PaymentHistoryVO(int no, String register, int amount, int meetingNo, String startDate, String endDate, String regDate, String projectId) {
        this.no = no;
        this.register = register;
        this.amount = amount;
        this.meetingNo = meetingNo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.regDate = regDate;
        this.projectId = projectId;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getMeetingNo() {
        return meetingNo;
    }

    public void setMeetingNo(int meetingNo) {
        this.meetingNo = meetingNo;
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

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "PaymentHistoryVO{" +
                "no=" + no +
                ", register='" + register + '\'' +
                ", amount=" + amount +
                ", meetingNo=" + meetingNo +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", regDate='" + regDate + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
