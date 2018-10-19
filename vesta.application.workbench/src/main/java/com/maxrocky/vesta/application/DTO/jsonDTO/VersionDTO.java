package com.maxrocky.vesta.application.DTO.jsonDTO;

/**
 * Created by zhanghj on 2016/3/10.
 */
public class VersionDTO {
    private String appVersionId;//版本ID；
    private String appVersionType;////IOS-1,安卓-2
    private String appVersion;//1.0.1；版本号
    private String appVersionStatus;//1：不需要升级，2：推荐升级，2：强制升级
    private String downUrl;//下载地址
    private String appVersionName;//版本名称
    private String appRemark;//更新记录
    private String userType;//用户类型

    public String getAppVersionId() {
        return appVersionId;
    }

    public void setAppVersionId(String appVersionId) {
        this.appVersionId = appVersionId;
    }

    public String getAppVersionType() {
        return appVersionType;
    }

    public void setAppVersionType(String appVersionType) {
        this.appVersionType = appVersionType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppVersionStatus() {
        return appVersionStatus;
    }

    public void setAppVersionStatus(String appVersionStatus) {
        this.appVersionStatus = appVersionStatus;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getAppRemark() {
        return appRemark;
    }

    public void setAppRemark(String appRemark) {
        this.appRemark = appRemark;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
