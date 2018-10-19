package com.maxrocky.vesta.application.projectSampleCheck.DTO;

/**
 * Created by Magic on 2017/1/12.
 */
public class RequestSampleAdminDTO {
    private String sampleCheckId;//样板点评ID
    private String groupId;//z总部
    private String regionId;//区域
    private String cityId;//城市
    private String projectId;//项目编码    项目id
    private String buildingId;//楼栋编码  楼栋id
    private String state;//状态
    private String severityLevel;//严重等级
    private String classifyOne;//一级分类
    private String classifyTwo;//二级分类
    private String startDate;//开始日期
    private String endDate;//结束时间
    private String supplier;//责任单位
    private String assignName;//材料负责人
    private String firstPartyName;//甲方负责人名字
    private String supervisorName;//第三方监理名字
    public RequestSampleAdminDTO(){
        this.groupId="";//z总部
        this.regionId="";//区域
        this.cityId="";//城市
        this.sampleCheckId="";
        this.projectId="";//项目编码    项目id
        this.buildingId="";//楼栋编码  楼栋id
        this.state="";//状态
        this.classifyOne="";//一级分类
        this.classifyTwo="";//二级分类
        this.severityLevel="";//严重等级
        this.startDate="";//开始日期
        this.endDate="";//结束时间
        this.supplier="";//责任单位
        this.assignName="";//材料负责人
        this.firstPartyName="";//甲方负责人名字
        this.supervisorName="";//第三方监理名字
    }
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getClassifyOne() {
        return classifyOne;
    }

    public void setClassifyOne(String classifyOne) {
        this.classifyOne = classifyOne;
    }

    public String getClassifyTwo() {
        return classifyTwo;
    }

    public void setClassifyTwo(String classifyTwo) {
        this.classifyTwo = classifyTwo;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public String getFirstPartyName() {
        return firstPartyName;
    }

    public void setFirstPartyName(String firstPartyName) {
        this.firstPartyName = firstPartyName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getSampleCheckId() {
        return sampleCheckId;
    }

    public void setSampleCheckId(String sampleCheckId) {
        this.sampleCheckId = sampleCheckId;
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
}
