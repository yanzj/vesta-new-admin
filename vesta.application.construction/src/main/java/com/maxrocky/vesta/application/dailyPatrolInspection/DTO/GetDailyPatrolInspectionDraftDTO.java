package com.maxrocky.vesta.application.dailyPatrolInspection.DTO;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Created by Magic on 2016/11/17.
 */
public class GetDailyPatrolInspectionDraftDTO {

    private String inspectionId;//日常巡检id
    private String title;//巡检标题
    private String createName;//创建人名字
    private String createOn;//创建时间
    private String state;//状态
    private String projectName;//工程项目名称
    private String point;//详细位置
    private String classifyOne;//一级分类
    private String classifyTwo;//二级分类
    private String classifyThree;//三级分类
    private String severityLevel;//严重等级
    private String description;//描述
    private List<InspectionImageDTO> imageList;//整改图片
    private MultipartFile[] imgFile;//问题图片

    private String firstParty;              //甲方负责人ID
    private String assignId;                //整改人ID
    private String supervisor;              //第三方监理id
    private String supplierId;//责任单位ID
    private String rectificationPeriod;//整改时限

    private String assignName;//整改人名字
    private String firstPartyName;          //甲方负责人名字
    private String supervisorName;//第三方监理名字
    private String supplier;//责任单位

    private List<String> image;//已添加图片的ID

    private String xCoordinates;//X坐标
    private String yCoordinates;//Y坐标
    private String houseTypeId;
    private String houseTyprUrl;

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


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
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

    public MultipartFile[] getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile[] imgFile) {
        this.imgFile = imgFile;
    }

    public String getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(String firstParty) {
        this.firstParty = firstParty;
    }

    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getRectificationPeriod() {
        return rectificationPeriod;
    }

    public void setRectificationPeriod(String rectificationPeriod) {
        this.rectificationPeriod = rectificationPeriod;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public String getxCoordinates() {
        return xCoordinates;
    }

    public void setxCoordinates(String xCoordinates) {
        this.xCoordinates = xCoordinates;
    }

    public String getyCoordinates() {
        return yCoordinates;
    }

    public void setyCoordinates(String yCoordinates) {
        this.yCoordinates = yCoordinates;
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
}
