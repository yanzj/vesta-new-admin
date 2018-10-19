package com.maxrocky.vesta.application.readilyTake.DTO;

import java.util.Date;
import java.util.List;

/**
 * 随手拍整改单DTO
 * Created by yuanyn on 2017/7/10.
 */
public class ReadilyTakeDetailDTO {
    private String patId;//随手拍id
    private String projectId;//项目公司Id
    private String projectName;//项目公司
    private String state;//状态
    private String examinationParts;//检查部位
    private String severityLevel;//严重等级
    private String createName;//创建人姓名
    private String createDate;//创建时间
    private String modifyDate;//修改时间
    private String description;//问题描述
    private String internalPeople;//总包负责人名字
    private String externalPeople;//监理负责人名字
    private String securityAdministrator;//项目安全员
    private List<RectificationDescriptionDTO> rectificationDescriptionList;//整改描述  和整改图片
    private String rectificationDate;//整改时间
    private String rectificationPeople;//整改负责人
    private String supplementaryDescription;//补充描述    补充废弃状态时的 废弃原因
    private String address;//地址
    private List<ImageDTO> imgList;//随手拍图片

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getExaminationParts() {
        return examinationParts;
    }

    public void setExaminationParts(String examinationParts) {
        this.examinationParts = examinationParts;
    }

    public String getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(String severityLevel) {
        this.severityLevel = severityLevel;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInternalPeople() {
        return internalPeople;
    }

    public void setInternalPeople(String internalPeople) {
        this.internalPeople = internalPeople;
    }

    public String getExternalPeople() {
        return externalPeople;
    }

    public void setExternalPeople(String externalPeople) {
        this.externalPeople = externalPeople;
    }

    public String getSecurityAdministrator() {
        return securityAdministrator;
    }

    public void setSecurityAdministrator(String securityAdministrator) {
        this.securityAdministrator = securityAdministrator;
    }

    public List<RectificationDescriptionDTO> getRectificationDescriptionList() {
        return rectificationDescriptionList;
    }

    public void setRectificationDescriptionList(List<RectificationDescriptionDTO> rectificationDescriptionList) {
        this.rectificationDescriptionList = rectificationDescriptionList;
    }

    public String getRectificationDate() {
        return rectificationDate;
    }

    public void setRectificationDate(String rectificationDate) {
        this.rectificationDate = rectificationDate;
    }

    public String getRectificationPeople() {
        return rectificationPeople;
    }

    public void setRectificationPeople(String rectificationPeople) {
        this.rectificationPeople = rectificationPeople;
    }

    public String getSupplementaryDescription() {
        return supplementaryDescription;
    }

    public void setSupplementaryDescription(String supplementaryDescription) {
        this.supplementaryDescription = supplementaryDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ImageDTO> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImageDTO> imgList) {
        this.imgList = imgList;
    }
}
