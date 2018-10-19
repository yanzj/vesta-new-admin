package com.maxrocky.vesta.application.AdminDTO;

import javax.persistence.Basic;
import javax.persistence.Column;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/15.
 */
public class UserIntegralRuleRecordQuerry {
    private String realName;
    private String mobile;
    private String name;
    private String integralNumber;
    private String userId;
    private String houseProjectId;
    private String menuId;
    private String cityId;//城市ID
    private String projectNum;
    private String weChatAppId;     //微信AppId
    private String cardNum;//U+会员卡编号

    public String getWeChatAppId() {
        return weChatAppId;
    }

    public void setWeChatAppId(String weChatAppId) {
        this.weChatAppId = weChatAppId;
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntegralNumber() {
        return integralNumber;
    }

    public void setIntegralNumber(String integralNumber) {
        this.integralNumber = integralNumber;
    }

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

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }
}
