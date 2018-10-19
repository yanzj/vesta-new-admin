package com.maxrocky.vesta.application.JsonDTO;

/**
 * Created by chen on 2016/5/18.
 */
public class RecodeDTO {
    private String id;             //主键
    private String standId;        //旁站ID
    private String imageUrl;       //图片地址
    private String recodeDesc;     //记录说明
    private String createDate;     //时间

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getStandId() {
        return standId;
    }

    public void setStandId(String standId) {
        this.standId = standId;
    }
}
