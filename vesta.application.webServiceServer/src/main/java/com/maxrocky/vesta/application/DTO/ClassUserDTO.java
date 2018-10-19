package com.maxrocky.vesta.application.DTO;

import java.util.Date;

/**
 * Created by Magic on 2017/7/17.
 * 分类人员信息
 */
public class ClassUserDTO {
    private String username;    //登录账号
    private String staffName;   //用户姓名
    private String phone;       //手机号
    private String classificationId;    //分类id
    private String classificationNum;   //分类编号
    private String projectNum;      //项目编码
    private String projectId;       //项目id

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(String classificationId) {
        this.classificationId = classificationId;
    }

    public String getClassificationNum() {
        return classificationNum;
    }

    public void setClassificationNum(String classificationNum) {
        this.classificationNum = classificationNum;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
