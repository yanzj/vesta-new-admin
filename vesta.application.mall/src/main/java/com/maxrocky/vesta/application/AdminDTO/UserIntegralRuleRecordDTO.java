package com.maxrocky.vesta.application.AdminDTO;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/15.
 */
public class UserIntegralRuleRecordDTO {
    private String userIntegralRuleRecordId ;
    private String realName;
    private String mobile;
    private String nickName;
    private String name;
    private String userAddress;
    private String integralNumber;
    private String clientName;
    private String userId;
    private String houseProjectId;
    private String cardNum;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHouseProjectId() {
        return houseProjectId;
    }

    public void setHouseProjectId(String houseProjectId) {
        this.houseProjectId = houseProjectId;
    }

    public String getUserIntegralRuleRecordId() {
        return userIntegralRuleRecordId;
    }

    public void setUserIntegralRuleRecordId(String userIntegralRuleRecordId) {
        this.userIntegralRuleRecordId = userIntegralRuleRecordId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getIntegralNumber() {
        return integralNumber;
    }

    public void setIntegralNumber(String integralNumber) {
        this.integralNumber = integralNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
}
