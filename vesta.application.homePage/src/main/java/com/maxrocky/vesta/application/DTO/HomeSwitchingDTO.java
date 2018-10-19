package com.maxrocky.vesta.application.DTO;

/**
 * Created by ZhangBailiang on 2016/2/22..
 * 首页 切换 项目 DTO
 */
public class HomeSwitchingDTO {
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String pinyinCode;//小区Id
    private String userIdOrPhoneUUID;//游客手机的UUID或者用户Id

    public String getUserIdOrPhoneUUID() {
        return userIdOrPhoneUUID;
    }

    public void setUserIdOrPhoneUUID(String userIdOrPhoneUUID) {
        this.userIdOrPhoneUUID = userIdOrPhoneUUID;
    }

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
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
}
