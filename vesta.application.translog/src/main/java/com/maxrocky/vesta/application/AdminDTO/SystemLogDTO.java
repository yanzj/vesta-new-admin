package com.maxrocky.vesta.application.AdminDTO;

import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2016/7/19.
 */
public class SystemLogDTO {
    private String logId;           //主键
    private String userName;        //用户名
    private String userType;        //用户类型
    private String userMobile;      //手机号
    private String idCard;          //身份证
    private String sysVersion;      //系统版本
    private String sourceFrom;      //来源
    private String projectName;     //项目名
    private String logTime;         //时间
    private String logContent;      //内容
    private String thisType;        //操作类型或报修单类型 等 泛指日志自身里面的类型
    private String docNum;          //单据编号
    private String thisStatus;      //单据状态 什么的 泛指状态一类
    private String thisSection;     //版块
    private String thisFunction;    //功能

    //搜索条件
    private String beginTime;      //开始时间
    private String endTime;        //结束时间
    private String projectId;      //项目ID
    private String logType;        //日志类型
    private String asscommunity;    //关联社区
    private String realEstate;   //房产

    /* 新增查询字段(用户权限范围)_2016-10-19_Wyd */
    private List<Map<String,Object>> roleScopeList;
    /* ------------------------------------- */

    private String menuId;      //菜单ID
    private String scopeId;     //区域ID
    private String projectCode; //项目Code

    public String getRealEstate() {
        return realEstate;
    }

    public void setRealEstate(String realEstate) {
        this.realEstate = realEstate;
    }

    public String getAsscommunity() {
        return asscommunity;
    }

    public void setAsscommunity(String asscommunity) {
        this.asscommunity = asscommunity;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public String getThisFunction() {
        return thisFunction;
    }

    public void setThisFunction(String thisFunction) {
        this.thisFunction = thisFunction;
    }

    public String getThisSection() {
        return thisSection;
    }

    public void setThisSection(String thisSection) {
        this.thisSection = thisSection;
    }

    public String getThisStatus() {
        return thisStatus;
    }

    public void setThisStatus(String thisStatus) {
        this.thisStatus = thisStatus;
    }

    public String getThisType() {
        return thisType;
    }

    public void setThisType(String thisType) {
        this.thisType = thisType;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getScopeId() {
        return scopeId;
    }

    public void setScopeId(String scopeId) {
        this.scopeId = scopeId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
}
