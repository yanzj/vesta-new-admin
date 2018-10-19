package com.maxrocky.vesta.application.DTO;

/**
 * Created by liudongxin on 2016/1/14.
 * 业主端:物业报修历史列表(Histories:History的复数)所需信息/员工端:随手报历史列表
 */
public class PropertyHistoriesDTO {
    private String id;//报修id
    private String content;//内容
    private String status;//状态
    private String memo;//备注：报修、投诉、维修、客服、秩序、环境
    private String imageUrl;//图片路径
    private String src;//图片路径

    public PropertyHistoriesDTO() {
        this.id = "";
        this.content = "";
        this.status = "";
        this.src = "";
        this.memo="";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}