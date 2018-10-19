package com.maxrocky.vesta.application.JsonDTO;

/**
 * Created by chen on 2016/1/17.
 */
public class SellerPageDTO {
    private String sellerId;        //商户ID
    private String logo;            //商户logo
    private String sellerName;      //商户名
    private String sellerTel;       //商户电话
    private String sellerAddress;   //商户地址
    private String goodLevel;       //好评数
    private String badLevel;        //差评数
    private Boolean isCollect;      //是否已收藏
    private Boolean isLevel;        //是否已评价

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getBadLevel() {
        return badLevel;
    }

    public void setBadLevel(String badLevel) {
        this.badLevel = badLevel;
    }

    public String getGoodLevel() {
        return goodLevel;
    }

    public void setGoodLevel(String goodLevel) {
        this.goodLevel = goodLevel;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerTel() {
        return sellerTel;
    }

    public void setSellerTel(String sellerTel) {
        this.sellerTel = sellerTel;
    }

    public Boolean getIsCollect() {
        return isCollect;
    }

    public void setIsCollect(Boolean isCollect) {
        this.isCollect = isCollect;
    }

    public Boolean getIsLevel() {
        return isLevel;
    }

    public void setIsLevel(Boolean isLevel) {
        this.isLevel = isLevel;
    }
}
