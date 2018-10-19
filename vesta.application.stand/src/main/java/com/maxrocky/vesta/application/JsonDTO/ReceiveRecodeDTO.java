package com.maxrocky.vesta.application.JsonDTO;

/**
 * Created by chen on 2016/5/19.
 */
public class ReceiveRecodeDTO {
    private String imageUrl;    //图片地址
    private String recodeDesc;  //记录描述
    private String recodeTime;  //记录时间

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

    public String getRecodeTime() {
        return recodeTime;
    }

    public void setRecodeTime(String recodeTime) {
        this.recodeTime = recodeTime;
    }
}
