package com.maxrocky.vesta.application.DTO.admin;

import java.util.List;
import java.util.Map;

/**
 * Created by sunmei on 2016/2/24.
 */
public class UserFeedbackSearchDTO {
    private String id;
    private String userType;//用户类型
    private String status;//反馈状态
    private String feedbackType;//反馈类型
    private String memo;//来源
    private String phone;//电话
    private String userName;//用户名
    private String content;//反馈内容
    private String startDate;//反馈开始时间
    private String endDate;//反馈结束时间
    private String queryScope;//查询负责范围(模块条件
    private String state;

    private Map<String,String> userTypeMap;//用户类型
    private Map<String,String> stateMap;//反馈状态
    private Map<String,String> feedBackTypeMap;//工单类型
    private Map<String,String> resourceMap;//工单来源

    /* 新增查询字段(用户权限范围)_2016-08-31_Wyd */
    private List<Map<String,Object>> roleScopeList;

    private String fbClassification;//意见反馈来源分类
    private Map<String,String> fbMap;//意见反馈来源分类map

    private String address;//地址
    /* ------------------------------------- */

    private String menuId;      //菜单ID
    private String scopeId;     //区域Id
    private String projectCode; //项目Code


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getQueryScope() {
        return queryScope;
    }

    public void setQueryScope(String queryScope) {
        this.queryScope = queryScope;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Map<String, String> getUserTypeMap() {
        return userTypeMap;
    }

    public void setUserTypeMap(Map<String, String> userTypeMap) {
        this.userTypeMap = userTypeMap;
    }

    public Map<String, String> getStateMap() {
        return stateMap;
    }

    public void setStateMap(Map<String, String> stateMap) {
        this.stateMap = stateMap;
    }

    public Map<String, String> getFeedBackTypeMap() {
        return feedBackTypeMap;
    }

    public void setFeedBackTypeMap(Map<String, String> feedBackTypeMap) {
        this.feedBackTypeMap = feedBackTypeMap;
    }

    public Map<String, String> getResourceMap() {
        return resourceMap;
    }

    public void setResourceMap(Map<String, String> resourceMap) {
        this.resourceMap = resourceMap;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<Map<String, Object>> getRoleScopeList() {
        return roleScopeList;
    }

    public void setRoleScopeList(List<Map<String, Object>> roleScopeList) {
        this.roleScopeList = roleScopeList;
    }

    public String getFbClassification() {
        return fbClassification;
    }

    public void setFbClassification(String fbClassification) {
        this.fbClassification = fbClassification;
    }

    public Map<String, String> getFbMap() {
        return fbMap;
    }

    public void setFbMap(Map<String, String> fbMap) {
        this.fbMap = fbMap;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
