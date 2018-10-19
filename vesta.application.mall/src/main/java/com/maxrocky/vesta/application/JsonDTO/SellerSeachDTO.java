package com.maxrocky.vesta.application.JsonDTO;

/**
 * Created by chen on 2016/1/17.
 */
public class SellerSeachDTO {
    private String pictureURL;    //图片地址
    private String sellerId;      //商户ID
    private String evaluateCircs; //评价好坏
    private String categoryId;    //商户类型ID

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getEvaluateCircs() {
        return evaluateCircs;
    }

    public void setEvaluateCircs(String evaluateCircs) {
        this.evaluateCircs = evaluateCircs;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
