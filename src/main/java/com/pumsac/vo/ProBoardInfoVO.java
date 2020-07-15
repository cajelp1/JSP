package com.pumsac.vo;

public class ProBoardInfoVO {
    private int projectId;
    private String bordName;
    private String boarType;
    private String projectName;

    public ProBoardInfoVO() {
    }

    public ProBoardInfoVO(int projectId, String bordName, String boarType, String projectName) {
        this.projectId = projectId;
        this.bordName = bordName;
        this.boarType = boarType;
        this.projectName = projectName;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getBordName() {
        return bordName;
    }

    public void setBordName(String bordName) {
        this.bordName = bordName;
    }

    public String getBoarType() {
        return boarType;
    }

    public void setBoarType(String boarType) {
        this.boarType = boarType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "ProBoardInfoVO{" +
                "projectId='" + projectId + '\'' +
                ", bordName='" + bordName + '\'' +
                ", boarType='" + boarType + '\'' +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
