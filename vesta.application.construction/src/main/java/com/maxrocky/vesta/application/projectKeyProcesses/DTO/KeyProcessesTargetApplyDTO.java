package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

/**
 * Created by Talent on 2016/11/22.
 */
public class KeyProcessesTargetApplyDTO {
    private String targetId;//指标ID
    private String targetName;//指标名称
    private String description;//指标描述
    private String imageUrl;//指标图片地址
    private String qualifiedState;//合格(合格、不合格)
    private String serialNumber;//排序号
    private String targetDescription;//指标描述

    public String getTargetDescription() {
        return targetDescription;
    }

    public void setTargetDescription(String targetDescription) {
        this.targetDescription = targetDescription;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
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

    public String getQualifiedState() {
        return qualifiedState;
    }

    public void setQualifiedState(String qualifiedState) {
        this.qualifiedState = qualifiedState;
    }
}
