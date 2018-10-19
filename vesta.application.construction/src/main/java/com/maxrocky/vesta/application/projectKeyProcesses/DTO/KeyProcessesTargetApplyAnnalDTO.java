package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

/**
 * Created by Talent on 2016/11/24.
 */
public class KeyProcessesTargetApplyAnnalDTO {
    private String processTargetId;//工序指标ID
    private String processTargetDetailId;//工序详情ID
    private String description;//指标描述
    private String imageUrl;//指标图片地址
    private String changeTime;//整改时间
    private String qualifiedState;//合格(合格、不合格)
    private String serialNumber;//排序号
    private String flag;//指标审核标识

    public KeyProcessesTargetApplyAnnalDTO() {
        this.processTargetId = "";
        this.processTargetDetailId = "";
        this.description = "";
        this.imageUrl = "";
        this.changeTime = "";
        this.qualifiedState = "";
        this.serialNumber = "";
        this.flag="";
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProcessTargetId() {
        return processTargetId;
    }

    public void setProcessTargetId(String processTargetId) {
        this.processTargetId = processTargetId;
    }

    public String getProcessTargetDetailId() {
        return processTargetDetailId;
    }

    public void setProcessTargetDetailId(String processTargetDetailId) {
        this.processTargetDetailId = processTargetDetailId;
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

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getQualifiedState() {
        return qualifiedState;
    }

    public void setQualifiedState(String qualifiedState) {
        this.qualifiedState = qualifiedState;
    }
}
