package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Magic on 2016/11/11.
 */
public class GetDailyPatrolInspectionAdminDTO {
    private String inspectionId;//日常巡检id
    private String title;//巡检标题
    private String createName;//创建人名字
    private String createOn;//创建时间
    private String state;//状态
    private String projectId;//工程项目_ID
    private String projectName;//工程项目名称
    private String point;//详细位置
    private String classifyThree;//三级分类
    private String severityLevel;//严重等级
    private String rectificationPeriod;//整改时限
    private String description;//描述
    private List<InspectionImageDTO> imageList;//整改图片
    private String supplier;//责任单位名字
    private String assignName;//整改人名字
    private String firstPartyName;          //甲方负责人名字
    private String supervisorName;//第三方监理名字
    private List<CopyDetailsListDTO> idList;//抄送人list
    private List<PostDailyPatrolInspectionDetailsDTO> inspectionList;//整改记录
    private String xCoordinate;//x坐标
    private String yCoordinate;//y坐标
    private String type;//判断权限   1.甲方 2.第三方监理 3.乙方

    private String houseTypeId;
    private String houseTyprUrl;

    private String dealState;//判断是否该处理

    private MultipartFile[] imgFile;//问题图片

    private String detailsDescription;//整改记录描述

    private String checkState;//合格不合格

    private String shutDown;//关闭理由

    private String shutDownBy;//关闭ren

    private String shutDownOn;//关闭时间

    private String shutDownState;//是否有关闭权限

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getClassifyThree() {
        return classifyThree;
    }

    public void setClassifyThree(String classifyThree) {
        this.classifyThree = classifyThree;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getRectificationPeriod() {
        return rectificationPeriod;
    }

    public void setRectificationPeriod(String rectificationPeriod) {
        this.rectificationPeriod = rectificationPeriod;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<InspectionImageDTO> getImageList() {
        return imageList;
    }

    public void setImageList(List<InspectionImageDTO> imageList) {
        this.imageList = imageList;
    }

    public List<PostDailyPatrolInspectionDetailsDTO> getInspectionList() {
        return inspectionList;
    }

    public void setInspectionList(List<PostDailyPatrolInspectionDetailsDTO> inspectionList) {
        this.inspectionList = inspectionList;
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

    public List<CopyDetailsListDTO> getIdList() {
        return idList;
    }

    public void setIdList(List<CopyDetailsListDTO> idList) {
        this.idList = idList;
    }

    public String getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(String xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public String getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(String yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MultipartFile[] getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile[] imgFile) {
        this.imgFile = imgFile;
    }

    public String getDetailsDescription() {
        return detailsDescription;
    }

    public void setDetailsDescription(String detailsDescription) {
        this.detailsDescription = detailsDescription;
    }

    public String getDealState() {
        return dealState;
    }

    public void setDealState(String dealState) {
        this.dealState = dealState;
    }

    public String getCheckState() {
        return checkState;
    }

    public void setCheckState(String checkState) {
        this.checkState = checkState;
    }

    public String getShutDown() {
        return shutDown;
    }

    public void setShutDown(String shutDown) {
        this.shutDown = shutDown;
    }

    public String getShutDownState() {
        return shutDownState;
    }

    public void setShutDownState(String shutDownState) {
        this.shutDownState = shutDownState;
    }

    public String getHouseTypeId() {
        return houseTypeId;
    }

    public void setHouseTypeId(String houseTypeId) {
        this.houseTypeId = houseTypeId;
    }

    public String getHouseTyprUrl() {
        return houseTyprUrl;
    }

    public void setHouseTyprUrl(String houseTyprUrl) {
        this.houseTyprUrl = houseTyprUrl;
    }

    public String getShutDownBy() {
        return shutDownBy;
    }

    public void setShutDownBy(String shutDownBy) {
        this.shutDownBy = shutDownBy;
    }

    public String getShutDownOn() {
        return shutDownOn;
    }

    public void setShutDownOn(String shutDownOn) {
        this.shutDownOn = shutDownOn;
    }
}
