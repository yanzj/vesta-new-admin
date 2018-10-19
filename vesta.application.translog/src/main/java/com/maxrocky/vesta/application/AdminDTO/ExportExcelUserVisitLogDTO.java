package com.maxrocky.vesta.application.AdminDTO;

import java.util.Date;

/**
 * 用户访问日志导出Excel_DTO
 * Created by WeiYangDong on 2017/2/20.
 */
public class ExportExcelUserVisitLogDTO {

    private int num;                    //序号
    private String logTime;             //访问时间
    private String userName;            //用户昵称
    private String userType;            //用户类型
    private String userMobile;          //手机号
    private String thisSection;         //访问版块
    private String thisFunction;        //访问功能
    private String LogContent;          //访问内容
    private String sourceFrom;          //访问来源

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

    public String getThisSection() {
        return thisSection;
    }

    public void setThisSection(String thisSection) {
        this.thisSection = thisSection;
    }

    public String getThisFunction() {
        return thisFunction;
    }

    public void setThisFunction(String thisFunction) {
        this.thisFunction = thisFunction;
    }

    public String getLogContent() {
        return LogContent;
    }

    public void setLogContent(String logContent) {
        LogContent = logContent;
    }

    public String getSourceFrom() {
        return sourceFrom;
    }

    public void setSourceFrom(String sourceFrom) {
        this.sourceFrom = sourceFrom;
    }
}
