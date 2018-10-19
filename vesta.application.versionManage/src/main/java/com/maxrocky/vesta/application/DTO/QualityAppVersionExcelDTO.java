package com.maxrocky.vesta.application.DTO;

/**
 * Created by Talent on 2016/11/3.
 */
public class QualityAppVersionExcelDTO {
    private int serialNumber;//编号
    private String appEdition;//app版本类型  1.交付app  2.工程app
    private String appVersionType;//版本类型IOS-1,安卓-2
    private String appVersionName;//版本名称
    private String appVersionNum;//1.0.1；版本号
    private String fileSize; // 文件大小
    private String createBy;//发布人
    private String createOn;//发布时间
    private String downUrl;//下载地址

    public QualityAppVersionExcelDTO() {
        this.serialNumber = 0;
        this.appVersionType = "";////IOS-1,安卓-2
        this.appVersionNum = "";//1.0.1；版本号
        this.createBy = "";//发布人
        this.createOn = "";//发布时间
        this.downUrl = "";//下载地址
        this.appVersionName = "";//版本名称
        this.fileSize = ""; // 文件大小
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAppVersionType() {
        return appVersionType;
    }

    public void setAppVersionType(String appVersionType) {
        this.appVersionType = appVersionType;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getAppVersionNum() {
        return appVersionNum;
    }

    public void setAppVersionNum(String appVersionNum) {
        this.appVersionNum = appVersionNum;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateOn() {
        return createOn;
    }

    public void setCreateOn(String createOn) {
        this.createOn = createOn;
    }

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getAppEdition() {
        return appEdition;
    }

    public void setAppEdition(String appEdition) {
        this.appEdition = appEdition;
    }
}
