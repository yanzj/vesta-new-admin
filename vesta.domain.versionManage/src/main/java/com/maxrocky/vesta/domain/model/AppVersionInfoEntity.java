package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by jiazefeng on 2016/11/3.
 */
@Entity
@Table(name = "app_version_info")
public class AppVersionInfoEntity {

    public final static String TYPE_IOS = "1";//手机类型appVersionType
    public final static String TYPE_ADR = "2";

    public final static String STATUS_UNREQUIRED = "1";//升级情况appVersionStatus
    public final static String STATUS_RECOMMEND = "2";
    public final static String STATUS_COMPEL = "3";

    public final static String STATE_ON = "1";//是否有效
    public final static String STATE_OFF = "0";


    public static class User_Type{
        public static final  String User_Type_USER = "user";
        public static final  String User_Type_STAFF = "staff";
    }


    private String appVersionId;//版本ID；
    private String appVersionType;//版本类型：IOS-1,安卓-2
    private String appVersionNum;//1.0.1；版本号
    private String appVersionStatus;//1：不需要升级，2：推荐升级，2：强制升级
    private String createBy;//发布人
    private Date createOn;//发布时间
    private String  modifyBy;//修改人
    private Date modifyOn;//修改时间
    private String deleteBy;//删除人
    private Date deleteOn;//删除时间
    private String state;//是否有效 0-有效，1-无效
    private String downUrl;//下载地址
    private String appVersionName;//版本名称
    private String appRemark;//更新记录
    private String userType;//用户类型
    private String fileSize; // 文件大小
    private String businessType;//业务类型
    private String appEdition;//app版本  1.交付app  2.工程app

    @Id
    @Column(name = "APP_VERSIONID", nullable = false, insertable = true, updatable = true, length = 32)
    public String getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(String appVersionId) {
        this.appVersionId = appVersionId;
    }

    @Basic
    @Column(name = "APP_VERSIONTYPE", length = 32)
    public String getAppVersionType() {
        return appVersionType;
    }

    public void setAppVersionType(String appVersionType) {
        this.appVersionType = appVersionType;
    }
    @Basic
    @Column(name = "APP_VERSION_NUM", length = 32)
    public String getAppVersionNum() {
        return appVersionNum;
    }

    public void setAppVersionNum(String appVersionNum) {
        this.appVersionNum = appVersionNum;
    }
    @Basic
    @Column(name = "APP_FILE_SIZE", length = 32)
    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
    @Basic
    @Column(name = "BUSINESS_TYPE", length = 32)
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Basic
    @Column(name = "APP_VERSIONSTATUS", length = 32)
    public String getAppVersionStatus() {
        return appVersionStatus;
    }

    public void setAppVersionStatus(String appVersionStatus) {
        this.appVersionStatus = appVersionStatus;
    }
    @Basic
    @Column(name = "APP_CREATEBY")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    @Basic
    @Column(name = "APP_CREATEON")
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "APP_MODIFYBY")
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }
    @Basic
    @Column(name = "APP_MODIFYON")
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }
    @Basic
    @Column(name = "APP_DELETEBY")
    public String getDeleteBy() {
        return deleteBy;
    }

    public void setDeleteBy(String deleteBy) {
        this.deleteBy = deleteBy;
    }
    @Basic
    @Column(name = "APP_DELETEON")
    public Date getDeleteOn() {
        return deleteOn;
    }

    public void setDeleteOn(Date deleteOn) {
        this.deleteOn = deleteOn;
    }

    @Basic
    @Column(name = "APP_STATE")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "APP_DOWNURL")
    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }
    @Basic
    @Column(name = "APP_VERSIONNAME")
    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }
    @Basic
    @Column(name = "APP_REMARK")
    public String getAppRemark() {
        return appRemark;
    }

    public void setAppRemark(String appRemark) {
        this.appRemark = appRemark;
    }

    @Basic
    @Column(name = "APP_USERTYPE")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "APP_EDITION")
    public String getAppEdition() {
        return appEdition;
    }

    public void setAppEdition(String appEdition) {
        this.appEdition = appEdition;
    }
}
