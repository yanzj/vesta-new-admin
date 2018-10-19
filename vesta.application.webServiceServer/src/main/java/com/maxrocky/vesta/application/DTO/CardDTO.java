package com.maxrocky.vesta.application.DTO;

/**
 * Created by liudongxin on 2016/4/13.
 * 会员卡信息
 */
public class CardDTO {

    private String id;//会员卡id
    //private String memberId;//会员编号
    private String cardNumber;//卡号
    private String cardType;//卡类型
    private String businessSource;//所属业态
    private String sendCardShop;//发卡店铺
    private String cardStatus;//卡状态
    private String formerCardNumber;//原卡号
    private String sendCardDate;//发卡日期
    private String FailDate;//失效日期

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /*public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }*/

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBusinessSource() {
        return businessSource;
    }

    public void setBusinessSource(String businessSource) {
        this.businessSource = businessSource;
    }

    public String getSendCardShop() {
        return sendCardShop;
    }

    public void setSendCardShop(String sendCardShop) {
        this.sendCardShop = sendCardShop;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getFormerCardNumber() {
        return formerCardNumber;
    }

    public void setFormerCardNumber(String formerCardNumber) {
        this.formerCardNumber = formerCardNumber;
    }

    public String getSendCardDate() {
        return sendCardDate;
    }

    public void setSendCardDate(String sendCardDate) {
        this.sendCardDate = sendCardDate;
    }

    public String getFailDate() {
        return FailDate;
    }

    public void setFailDate(String failDate) {
        FailDate = failDate;
    }
}
