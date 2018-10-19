package com.maxrocky.vesta.application.dto.adminDTO;

/**
 * Created by chen on 2016/6/2.
 * 组 员工
 */
public class OrganizeUserDTO {
    private String organizeId;   //组ID
    private String staffId;      //员工ID
    private String staffName;    //员工名
    private String userName;     //登录名
    private String projectId;    //项目ID
    private String projectName;  //项目名


    public String getOrganizeId() {
        return organizeId;
    }

    public void setOrganizeId(String organizeId) {
        this.organizeId = organizeId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
