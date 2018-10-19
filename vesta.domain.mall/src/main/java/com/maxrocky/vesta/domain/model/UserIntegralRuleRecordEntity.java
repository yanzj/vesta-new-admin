package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/11.
 */
@Entity
@Table(name = "User_Integral_Rule_Record")
public class UserIntegralRuleRecordEntity {

    public UserIntegralRuleRecordEntity() {
    }

    public UserIntegralRuleRecordEntity(String userIntegralRuleRecordId, String weChatAppId, String houseProjectId, String userAddress, String userId, String modelType, String number, Date createOn) {
        this.userIntegralRuleRecordId = userIntegralRuleRecordId;
        this.weChatAppId = weChatAppId;
        this.houseProjectId = houseProjectId;
        this.userAddress = userAddress;
        this.userId = userId;
        this.modelType = modelType;
        this.number = number;
        this.createOn = createOn;
    }

    @Id
    @Column(name = "user_integral_rule_record_id", length = 32)
    private String userIntegralRuleRecordId;//ID

    @Basic
    @Column(name = "weChatAppId", length = 32)
    private String weChatAppId;     //微信AppId

    @Basic
    @Column(name = "houseProjectId", length = 32)
    private String houseProjectId;    //项目ID

    @Basic
    @Column(name = "userAddress", length = 32)
    private String userAddress;    //地址

    @Basic
    @Column(name = "userId", length = 32)
    private String userId;    //用户ID

    @Basic
    @Column(name = "modelType", length = 32)
    private String modelType;    //模块类型

    @Basic
    @Column(name = "number", length = 32)
    private String number; //积分数量

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    private Date createOn; //创建日期

    @Basic
    @Column(name = "rname", length = 255)
    private String rname;


    @Basic
    @Column(name = "rid", length = 255)
    private String rid;

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
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
