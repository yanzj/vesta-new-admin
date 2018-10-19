package com.maxrocky.vesta.application.AdminDTO;

import java.util.Date;

/**
 * 新增用户日志导出Excel_DTO
 * Created by WeiYangDong on 2017/2/20.
 */
public class ExportExcelAddUserLogDTO {

    private int num;                //序号
    private String logTime;           //注册时间
    private String userName;        //用户昵称
    private String userType;        //用户类型
    private String userMobile;      //手机号
    private String idCard;          //身份证
    private String sourceFrom;      //注册来源
    private String sysVersion;      //系统版本
    private String projectId;       //项目ID

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
