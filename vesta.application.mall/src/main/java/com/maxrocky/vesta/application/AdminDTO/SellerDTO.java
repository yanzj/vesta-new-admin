package com.maxrocky.vesta.application.AdminDTO;

/**
 * Created by chen on 2016/1/25.
 * 管理后台商户封装数据类
 */
public class SellerDTO {
    private String sellerId;                      //商户ID
    private String sellerName;                    //商户名称
    private String sellerTel;                     //商户电话
    private String sellerAddress;                 //商户地址
    private String sellerDirector;                //商户负责人
    private String sellerDirectorTel;             //商户负责人电话
    private String goodNum;                       //商户好评数
    private String badNum;                        //商户差评数
    private String collect;                       //商户被收藏数
    private String projectId;                     //项目ID

    public String getBadNum() {
        return badNum;
    }

    public void setBadNum(String badNum) {
        this.badNum = badNum;
    }

    public String getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(String goodNum) {
        this.goodNum = goodNum;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerDirector() {
        return sellerDirector;
    }

    public void setSellerDirector(String sellerDirector) {
        this.sellerDirector = sellerDirector;
    }

    public String getSellerDirectorTel() {
        return sellerDirectorTel;
    }

    public void setSellerDirectorTel(String sellerDirectorTel) {
        this.sellerDirectorTel = sellerDirectorTel;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
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

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
