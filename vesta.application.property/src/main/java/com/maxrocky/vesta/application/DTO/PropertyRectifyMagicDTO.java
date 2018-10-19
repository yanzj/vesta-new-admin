package com.maxrocky.vesta.application.DTO;

import java.util.List;
import java.util.Map;

/**
 * 交房单打印 查询显示 实体
 * Created by maxrocky on 2017/5/5.
 */
public class PropertyRectifyMagicDTO {
    private String groupId;//z总部
    private String regionId;//区域
    private String cityId;//城市
    private String projectId;//项目
    private String type;//100000000 集团  100000001 区域  100000003 城市 100000002 项目
    private String cityNum;//城市编码
    private String projectNum;//项目编码
    private String planNum;//活动计划编码
    private String roomNum;//房间编码
    private String state;//办理状态
    private String userName;//客户姓名
    private String startDate;//开始日期
    private String endDate;//结束时间
    private String detype;//0 当前  1 所有
    private String planType;// 11 内部预验 12 工地开放  13 正式交房
    private String successOrFailure;//0 成功 1 失败 推送crm
    private String caseState;//问题状态  默认有效
    private String signaType;//是否为验房 1 为验房打印
    private List<String> userProject;

    private Map<String,String> cityList;//城市下拉框
    private Map<String,String> projectList;//项目下拉框
    private Map<String,String> planList;//活动计划下拉框
    private Map<String,String> roomList;//房间下拉框

    public String getCityNum() {
        return cityNum;
    }

    public void setCityNum(String cityNum) {
        this.cityNum = cityNum;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getDetype() {
        return detype;
    }

    public void setDetype(String detype) {
        this.detype = detype;
    }

    public Map<String, String> getCityList() {
        return cityList;
    }

    public void setCityList(Map<String, String> cityList) {
        this.cityList = cityList;
    }

    public Map<String, String> getProjectList() {
        return projectList;
    }

    public void setProjectList(Map<String, String> projectList) {
        this.projectList = projectList;
    }

    public Map<String, String> getPlanList() {
        return planList;
    }

    public void setPlanList(Map<String, String> planList) {
        this.planList = planList;
    }

    public Map<String, String> getRoomList() {
        return roomList;
    }

    public void setRoomList(Map<String, String> roomList) {
        this.roomList = roomList;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public String getSuccessOrFailure() {
        return successOrFailure;
    }

    public void setSuccessOrFailure(String successOrFailure) {
        this.successOrFailure = successOrFailure;
    }

    public String getCaseState() {
        return caseState;
    }

    public void setCaseState(String caseState) {
        this.caseState = caseState;
    }

    public List<String> getUserProject() {
        return userProject;
    }

    public void setUserProject(List<String> userProject) {
        this.userProject = userProject;
    }

    public String getSignaType() {
        return signaType;
    }

    public void setSignaType(String signaType) {
        this.signaType = signaType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
