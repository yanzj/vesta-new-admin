package com.maxrocky.vesta.application.admin.dto;

import net.sf.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanghj on 2016/2/3.
 */
public class ActivityApplyAdminDto {

    private String userId;//用户Id
    private String cu_projectId;//项目id
    private String cu_projectName;//项目名称
    private String userName;//用户姓名
    private String userMobile;//用户手机号
    private String userType;//用户类型
    private String applyId;//报名信息Id
    private String applyDate;//报名时间
    private String beginTime;//开始时间
    private String endTime;//结束时间
    private String activityId;//活动id
    private String title;//标题
    private String activityDate;//活动时间

    private String cu_cityId;
    private String cu_cityName;

    private String applyPhone;//报名人联系方式
    private Integer applyCount;//报名人数
    /* 新增字段_报名地址_2016-08-05_Wyd */
    private String applyAddress;//联系地址
    /* ============================= */

    /* 新增查询字段(用户权限范围)_2016-09-01_Wyd */
    private List<Map<String,Object>> roleScopeList;
    /* ------------------------------------- */
    private String menuId;      //菜单ID
    private String scopeId;     //区域ID(城市ID)
    private String projectCode; //项目编码

    private String publishDate;
    private String imgSrc;

    private JSONArray applyInfo;//报名信息

    private JSONArray attributeName;//属性名

    public JSONArray getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(JSONArray attributeName) {
        this.attributeName = attributeName;
    }

    public JSONArray getApplyInfo() {
        return applyInfo;
    }

    public void setApplyInfo(JSONArray applyInfo) {
        this.applyInfo = applyInfo;
    }

    public String getApplyAddress() {
        return applyAddress;
    }

    public void setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
    }

    public String getCu_cityId() {
        return cu_cityId;
    }

    public void setCu_cityId(String cu_cityId) {
        this.cu_cityId = cu_cityId;
    }

    public String getCu_cityName() {
        return cu_cityName;
    }

    public void setCu_cityName(String cu_cityName) {
        this.cu_cityName = cu_cityName;
    }

    public String getApplyPhone() {
        return applyPhone;
    }

    public void setApplyPhone(String applyPhone) {
        this.applyPhone = applyPhone;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getCu_projectId() {
        return cu_projectId;
    }

    public void setCu_projectId(String cu_projectId) {
        this.cu_projectId = cu_projectId;
    }

    public String getCu_projectName() {
        return cu_projectName;
    }

    public void setCu_projectName(String cu_projectName) {
        this.cu_projectName = cu_projectName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
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
