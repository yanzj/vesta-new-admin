package com.maxrocky.vesta.application.projectSideStation.DTO;

/**
 * Created by Talent on 2016/11/8.
 */
public class ProjectSideStationDetailsDTO {
    private String detailsId;//详情ID
    private String description;//描述
    private String imageUrl;//图片地址
    private String recordTime;//旁站记录时间
    private String serialNumber;//序号

    public ProjectSideStationDetailsDTO() {
        this.detailsId = "";
        this.description = "";
        this.imageUrl = "";
        this.recordTime = "";
        this.serialNumber="";
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(String detailsId) {
        this.detailsId = detailsId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }
}
