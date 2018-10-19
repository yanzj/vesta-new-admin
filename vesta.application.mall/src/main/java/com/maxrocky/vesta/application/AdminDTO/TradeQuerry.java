package com.maxrocky.vesta.application.AdminDTO;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
public class TradeQuerry {

    private String tradeId;

    private String tradeNo;//订单号

    private String productName; //商品名称

    private String name;//业主名称

    private String phone;//手机号

    private String tradeStatus; //订单状态 1已支付 2已发货

    private String weChatAppId;     //微信AppId

    public String getWeChatAppId() {
        return weChatAppId;
    }

    public void setWeChatAppId(String weChatAppId) {
        this.weChatAppId = weChatAppId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }
}
