package com.maxrocky.vesta.application.AdminDTO;

/**
 * Created by chen on 2016/5/24.
 */
public class RecodeAdminDTO {
    private String recodeId;        //记录ID
    private String imageUrl;        //图片地址
    private String recodeDesc;      //记录说明
    private String createTime;      //记录时间
    private String standId;
    private String createBy;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRecodeDesc() {
        return recodeDesc;
    }

    public void setRecodeDesc(String recodeDesc) {
        this.recodeDesc = recodeDesc;
    }

    public String getRecodeId() {
        return recodeId;
    }

    public void setRecodeId(String recodeId) {
        this.recodeId = recodeId;
    }

    public String getStandId() {
        return standId;
    }

    public void setStandId(String standId) {
        this.standId = standId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
