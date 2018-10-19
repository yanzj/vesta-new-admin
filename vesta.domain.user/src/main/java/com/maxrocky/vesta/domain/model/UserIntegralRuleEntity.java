package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * Created by Tom on 2016/1/13 19:23.
 * Describe:用户信息实体类
 */
@Entity
@Table(name = "User_Integral_Rule")
public class UserIntegralRuleEntity {

    @Id
    @Column(name = "id", length = 32)
    private String id;//ID

    @Basic
    @Column(name = "USER_ID", length = 60)
    private String userId;//USERId

    @Basic
    @Column(name = "weChatAppId", length = 32)
    private String weChatAppId;     //微信AppId

    @Basic
    @Column(name = "integral_number", length = 32)
    private String integralNumber;//积分数量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIntegralNumber() {
        return integralNumber;
    }

    public void setIntegralNumber(String integralNumber) {
        this.integralNumber = integralNumber;
    }

    public String getWeChatAppId() {
        return weChatAppId;
    }

    public void setWeChatAppId(String weChatAppId) {
        this.weChatAppId = weChatAppId;
    }
}
