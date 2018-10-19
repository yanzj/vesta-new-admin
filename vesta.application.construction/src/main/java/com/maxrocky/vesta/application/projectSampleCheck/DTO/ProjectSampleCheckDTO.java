package com.maxrocky.vesta.application.projectSampleCheck.DTO;

import com.maxrocky.vesta.application.projectKeyProcesses.DTO.KeyProcessesCopyDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Magic on 2017/1/3.
 * 样板点评信息
 */
public class ProjectSampleCheckDTO {
    private String id;//自增长ID
    private String appId;//app端传入 校验唯一性 防止重复
    private String sampleCheckId;//样板点评ID
    private String title;//样板点评标题
    private String description;//描述
    private String createBy;//创建人ID
    private String createName;//创建人姓名
    private String createOn;//创建时间
    private String modifyDate;//修改时间
    private String projectId;//项目ID
    private String projectName;//项目名称
    private String buildingId;//楼栋id
    private String buildingName;//楼栋名称
    private String classifyOne;//一级分类
    private String classifyTwo;//二级分类
    private String classifyThree;//三级分类
    private String classifyOneName;//一级分类名称
    private String classifyTwoName;//二级分类名称
    private String classifyThreeName;//三级分类名称
    private String state;//状态
    private String severityLevel;//严重等级
    private String floorNum1;//楼层区间-始
    private String floorNum2;//楼层区间-终
    private String checkPosition;//检查部位
    private String checkDate;//检查时间

    private String supplierId;          //责任单位ID
    private String supplier;            //责任单位名称
    private String assignId;            //乙方负责人ID
    private String assignName;          //乙方负责人名字
    private String supervisorId;          //第三方监理id
    private String supervisorName;      //第三方监理名字
    private String firstParty;          //甲方负责人ID
    private String firstPartyName;      //甲方负责人名字
    private String dealPeople;          //处理人ID

    private String rectificationPeriod;//整改时限

    private List<ProjectSampleCheckDetailsDTO> sampleCheckDetails;//样板点评指标信息
    private List<KeyProcessesCopyDTO> copyList;//抄送人

    public ProjectSampleCheckDTO() {
        this.id = "";//自增长ID
        this.appId = "";//app端传入 校验唯一性 防止重复
        this.sampleCheckId = "";//样板点评ID
        this.title = "";//样板点评标题
        this.description = "";//描述
        this.createBy = "";//创建人ID
        this.createName = "";//创建人姓名
        this.createOn = "";//创建时间
        this.modifyDate = "";//修改时间
        this.projectId = "";//项目ID
        this.projectName = "";//项目名称
        this.buildingId = "";//楼栋id
        this.buildingName = "";//楼栋名称
        this.classifyOne = "";//一级分类
        this.classifyTwo = "";//二级分类
        this.classifyThree = "";//三级分类
        this.classifyOneName = "";//一级分类名称
        this.classifyTwoName = "";//二级分类名称
        this.classifyThreeName = "";//三级分类名称
        this.state = "";//状态
        this.severityLevel = "";//严重等级
        this.floorNum1 = "";//楼层区间-始
        this.floorNum2 = "";//楼层区间-终
        this.checkPosition = "";//检查部位
        this.checkDate = "";//检查时间

        this.supplierId = "";          //责任单位ID
        this.supplier = "";            //责任单位名称
        this.assignId = "";            //乙方负责人ID
        this.assignName = "";          //乙方负责人名字
        this.supervisorId = "";          //第三方监理id
        this.supervisorName = "";      //第三方监理名字
        this.firstParty = "";          //甲方负责人ID
        this.firstPartyName = "";      //甲方负责人名字
        this.dealPeople = "";          //处理人ID

        this.rectificationPeriod = "";//整改时限
        this.sampleCheckDetails = new ArrayList<ProjectSampleCheckDetailsDTO>();
        this.copyList = new ArrayList<KeyProcessesCopyDTO>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSampleCheckId() {
        return sampleCheckId;
    }

    public void setSampleCheckId(String sampleCheckId) {
        this.sampleCheckId = sampleCheckId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
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

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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

    public String getClassifyThree() {
        return classifyThree;
    }

    public void setClassifyThree(String classifyThree) {
        this.classifyThree = classifyThree;
    }

    public String getClassifyOneName() {
        return classifyOneName;
    }

    public void setClassifyOneName(String classifyOneName) {
        this.classifyOneName = classifyOneName;
    }

    public String getClassifyTwoName() {
        return classifyTwoName;
    }

    public void setClassifyTwoName(String classifyTwoName) {
        this.classifyTwoName = classifyTwoName;
    }

    public String getClassifyThreeName() {
        return classifyThreeName;
    }

    public void setClassifyThreeName(String classifyThreeName) {
        this.classifyThreeName = classifyThreeName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getFloorNum1() {
        return floorNum1;
    }

    public void setFloorNum1(String floorNum1) {
        this.floorNum1 = floorNum1;
    }

    public String getFloorNum2() {
        return floorNum2;
    }

    public void setFloorNum2(String floorNum2) {
        this.floorNum2 = floorNum2;
    }

    public String getCheckPosition() {
        return checkPosition;
    }

    public void setCheckPosition(String checkPosition) {
        this.checkPosition = checkPosition;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }

    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    public String getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }

    public String getFirstPartyName() {
        return firstPartyName;
    }

    public void setFirstPartyName(String firstPartyName) {
        this.firstPartyName = firstPartyName;
    }

    public String getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(String dealPeople) {
        this.dealPeople = dealPeople;
    }

    public String getRectificationPeriod() {
        return rectificationPeriod;
    }

    public void setRectificationPeriod(String rectificationPeriod) {
        this.rectificationPeriod = rectificationPeriod;
    }

    public List<ProjectSampleCheckDetailsDTO> getSampleCheckDetails() {
        return sampleCheckDetails;
    }

    public void setSampleCheckDetails(List<ProjectSampleCheckDetailsDTO> sampleCheckDetails) {
        this.sampleCheckDetails = sampleCheckDetails;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public List<KeyProcessesCopyDTO> getCopyList() {
        return copyList;
    }

    public void setCopyList(List<KeyProcessesCopyDTO> copyList) {
        this.copyList = copyList;
    }
}
