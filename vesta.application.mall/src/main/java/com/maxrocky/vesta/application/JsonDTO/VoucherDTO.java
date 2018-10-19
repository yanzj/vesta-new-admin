package com.maxrocky.vesta.application.JsonDTO;

/**
 * Created by chen on 2016/4/5.
 */
public class VoucherDTO {
    private String voucherId;    //优惠券ID
    private String title;        //标题
    private String logo;         //图片
    private String beginTime;    //开始时间
    private String endTime;      //结束时间
    private String fullMoney;    //条件金额
    private String money;        //优惠金额

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFullMoney() {
        return fullMoney;
    }

    public void setFullMoney(String fullMoney) {
        this.fullMoney = fullMoney;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
