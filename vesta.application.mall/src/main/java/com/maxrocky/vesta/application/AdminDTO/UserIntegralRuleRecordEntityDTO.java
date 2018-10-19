package com.maxrocky.vesta.application.AdminDTO;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/11.
 */
public class UserIntegralRuleRecordEntityDTO {

    private String userIntegralRuleRecordId;//ID


    private String weChatAppId;     //微信AppId


    private String houseProjectId;    //项目ID


    private String userAddress;    //地址


    private String userId;    //用户ID


    private String modelType;    //模块类型

    private String modelName;    //模块名称

    private String number; //积分数量


    private Date createOn; //创建日期

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIntegralRuleRecordId() {
        return userIntegralRuleRecordId;
    }

    public void setUserIntegralRuleRecordId(String userIntegralRuleRecordId) {
        this.userIntegralRuleRecordId = userIntegralRuleRecordId;
    }

    public String getWeChatAppId() {
        return weChatAppId;
    }

    public void setWeChatAppId(String weChatAppId) {
        this.weChatAppId = weChatAppId;
    }

    public String getHouseProjectId() {
        return houseProjectId;
    }

    public void setHouseProjectId(String houseProjectId) {
        this.houseProjectId = houseProjectId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
