package com.maxrocky.vesta.application.projectSampleCheck.DTO;

import com.maxrocky.vesta.application.projectKeyProcesses.DTO.KeyProcessesCopyDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Magic on 2017/1/3.
 * 样板点评信息
 */
public class ProjectSampleCheckAdminBackDTO {
    private String sampleCheckId;//样板点评ID
    private String title;//样板点评标题
    private String description;//描述
    private String createName;//创建人姓名
    private String createOn;//创建时间
    private String modifyDate;//修改时间
    private String projectName;//项目名称
    private String buildingName;//楼栋名称
    private String classifyThreeName;//三级分类名称
    private String state;//状态
    private String severityLevel;//严重等级
    private String checkPosition;//检查部位
    private String checkDate;//检查时间

    private String supplier;            //责任单位名称
    private String assignName;          //乙方负责人名字
    private String supervisorName;      //第三方监理名字
    private String firstPartyName;      //甲方负责人名字
    private String rectificationPeriod;//整改时限
    private String shutDownBy;//关闭人姓名
    private String shutDown;//关闭理由
    private String shutDownOn;//关闭时间
    private List<ProjectSampleCheckDetailsAdminBackDTO> sampleCheckDetails;//样板点评指标信息
    private List<KeyProcessesCopyDTO> copyList;//抄送人

    public ProjectSampleCheckAdminBackDTO() {
        this.sampleCheckId = "";//样板点评ID
        this.title = "";//样板点评标题
        this.description = "";//描述
        this.createName = "";//创建人姓名
        this.createOn = "";//创建时间
        this.modifyDate = "";//修改时间
        this.projectName = "";//项目名称
        this.buildingName = "";//楼栋名称
        this.classifyThreeName = "";//三级分类名称
        this.state = "";//状态
        this.severityLevel = "";//严重等级
        this.checkPosition = "";//检查部位
        this.checkDate = "";//检查时间

        this.supplier = "";            //责任单位名称
        this.assignName = "";          //乙方负责人名字
        this.supervisorName = "";      //第三方监理名字
        this.firstPartyName = "";      //甲方负责人名字

        this.rectificationPeriod = "";//整改时限
        this.shutDownBy = "";
        this.shutDown = "";
        this.shutDownOn = "";
        this.sampleCheckDetails = new ArrayList<ProjectSampleCheckDetailsAdminBackDTO>();
        this.copyList = new ArrayList<KeyProcessesCopyDTO>();
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

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
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

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getFirstPartyName() {
        return firstPartyName;
    }

    public void setFirstPartyName(String firstPartyName) {
        this.firstPartyName = firstPartyName;
    }

    public String getRectificationPeriod() {
        return rectificationPeriod;
    }

    public void setRectificationPeriod(String rectificationPeriod) {
        this.rectificationPeriod = rectificationPeriod;
    }

    public List<ProjectSampleCheckDetailsAdminBackDTO> getSampleCheckDetails() {
        return sampleCheckDetails;
    }

    public void setSampleCheckDetails(List<ProjectSampleCheckDetailsAdminBackDTO> sampleCheckDetails) {
        this.sampleCheckDetails = sampleCheckDetails;
    }

    public List<KeyProcessesCopyDTO> getCopyList() {
        return copyList;
    }

    public void setCopyList(List<KeyProcessesCopyDTO> copyList) {
        this.copyList = copyList;
    }

    public String getShutDownBy() {
        return shutDownBy;
    }

    public void setShutDownBy(String shutDownBy) {
        this.shutDownBy = shutDownBy;
    }

    public String getShutDown() {
        return shutDown;
    }

    public void setShutDown(String shutDown) {
        this.shutDown = shutDown;
    }

    public String getShutDownOn() {
        return shutDownOn;
    }

    public void setShutDownOn(String shutDownOn) {
        this.shutDownOn = shutDownOn;
    }
}
