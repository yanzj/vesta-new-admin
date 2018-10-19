package com.maxrocky.vesta.application.DTO;

/**
 * Created by Talent on 2016/11/3.
 */
public class QualityAppVersionManageDTO {
    private String appVersionId;//版本ID；
    private String appVersionName;//版本名称
    private String appEdition;//app版本类型  1.交付app  2.工程app
    private String appVersionType;//系统类型IOS-1,安卓-2
    private String appVersionNum;//1.0.1；版本号
    private String appVersionStatus;//0：不需要升级，1：推荐升级，2：强制升级
    private String createBy;//发布人
    private String appRemark;//记录
    private String createOn;//发布时间
    private String modifyBy;//修改人
    private String modifyOn;//修改时间
    private String deleteBy;//删除人
    private String deleteOn;//删除时间
    private String state;//是否有效 1-有效，0-无效
    private String downUrl;//下载地址
    private String beginDate; //开始时间
    private String endDate; // 结束时间
    private String fileSize; // 文件大小

    public QualityAppVersionManageDTO() {
        this.appEdition = "";//app版本类型  1.交付app  2.工程app
        this.appVersionId = "";//版本ID；
        this.appVersionType = "";////IOS-1,安卓-2
        this.appVersionNum = "";//1.0.1；版本号
        this.appVersionStatus = "";//0：不需要升级，1：推荐升级，2：强制升级
        this.createBy = "";//发布人
        this.appRemark = "";//记录
        this.createOn = "";//发布时间
        this.modifyBy = "";//修改人
        this.modifyOn = "";//修改时间
        this.deleteBy = "";//删除人
        this.deleteOn = "";//删除时间
        this.state = "";//是否有效 1-已发布，0-未发布
        this.downUrl = "";//下载地址
        this.appVersionName = "";//版本名称
        this.beginDate = ""; //开始时间
        this.endDate = ""; // 结束时间
        this.fileSize = ""; // 文件大小
    }

    public String getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(String appVersionId) {
        this.appVersionId = appVersionId;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getAppVersionType() {
        return appVersionType;
    }

    public void setAppVersionType(String appVersionType) {
        this.appVersionType = appVersionType;
    }

    public String getAppVersionNum() {
        return appVersionNum;
    }

    public void setAppVersionNum(String appVersionNum) {
        this.appVersionNum = appVersionNum;
    }

    public String getAppVersionStatus() {
        return appVersionStatus;
    }

    public void setAppVersionStatus(String appVersionStatus) {
        this.appVersionStatus = appVersionStatus;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getAppRemark() {
        return appRemark;
    }

    public void setAppRemark(String appRemark) {
        this.appRemark = appRemark;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    public String getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(String modifyOn) {
        this.modifyOn = modifyOn;
    }

    public String getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(String deleteBy) {
        this.deleteBy = deleteBy;
    }

    public String getDeleteOn() {
        return deleteOn;
    }

    public void setDeleteOn(String deleteOn) {
        this.deleteOn = deleteOn;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getAppEdition() {
        return appEdition;
    }

    public void setAppEdition(String appEdition) {
        this.appEdition = appEdition;
    }
}
