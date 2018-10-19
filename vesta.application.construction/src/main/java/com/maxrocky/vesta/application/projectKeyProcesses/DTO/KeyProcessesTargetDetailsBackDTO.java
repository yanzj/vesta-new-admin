package com.maxrocky.vesta.application.projectKeyProcesses.DTO;

/**
 * Created by Talent on 2016/11/22.
 */
public class KeyProcessesTargetDetailsBackDTO {
    private String id;//工序指标详情id
    private String processTargetId;//工序指标id
    private String description;//指标描述
    private String imageUrl;//指标图片地址
    private String changeTime;//整改时间
    private String type;//0 乙方整改  1 第三方监理整改   2 甲方整改
    private String state;//状态
    private String serialNumber;//排序号

    public KeyProcessesTargetDetailsBackDTO() {
        this.processTargetId = "";//工序指标id
        this.description = "";//指标描述
        this.imageUrl = "";//指标图片地址
        this.changeTime = "";//掌柜时间
        this.type = "";//0 乙方整改  1 第三方监理整改   2 甲方整改
        this.state = "";//状态
        this.serialNumber="";//排序号
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessTargetId() {
        return processTargetId;
    }

    public void setProcessTargetId(String processTargetId) {
        this.processTargetId = processTargetId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
