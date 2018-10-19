package com.maxrocky.vesta.application.AdminDTO;

import java.util.Date;

/**
 * 信息发布日志导出Excel_DTO
 * Created by WeiYangDong on 2017/2/21.
 */
public class ExportExcelInfoReleaseDTO {

    private int num;                    //序号
    private String logTime;             //发布时间
    private String userName;            //用户名
    private String thisSection;         //版块
    private String thisFunction;        //功能
    private String thisType;            //操作类型
    private String asscommunity;        //关联社区
    private String LogContent;          //发布内容

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

    public String getThisType() {
        return thisType;
    }

    public void setThisType(String thisType) {
        this.thisType = thisType;
    }

    public String getAsscommunity() {
        return asscommunity;
    }

    public void setAsscommunity(String asscommunity) {
        this.asscommunity = asscommunity;
    }

    public String getLogContent() {
        return LogContent;
    }

    public void setLogContent(String logContent) {
        LogContent = logContent;
    }
}
