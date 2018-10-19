package com.maxrocky.vesta.application.AdminDTO;

import javax.persistence.*;
import java.util.Date;

/**
 * 积分规则管理
 */
public class IntegralRuleDTO {
    private String integralRuleId;//ID

    private Integer clientConfigId;//公众号ID

    private String integralRuleModelId;//模块ID

    private String integralNumber;//积分数量

    private String createBy;    //创建人

    private Date createOn;      //创建时间

    public IntegralRuleDTO() {
    }

    public IntegralRuleDTO(String integralRuleId, Integer clientConfigId, String integralRuleModelId, String integralNumber, String createBy, Date createOn) {
        this.integralRuleId = integralRuleId;
        this.clientConfigId = clientConfigId;
        this.integralRuleModelId = integralRuleModelId;
        this.integralNumber = integralNumber;
        this.createBy = createBy;
        this.createOn = createOn;
    }

    public String getIntegralRuleId() {
        return integralRuleId;
    }

    public void setIntegralRuleId(String integralRuleId) {
        this.integralRuleId = integralRuleId;
    }

    public Integer getClientConfigId() {
        return clientConfigId;
    }

    public void setClientConfigId(Integer clientConfigId) {
        this.clientConfigId = clientConfigId;
    }

    public String getIntegralRuleModelId() {
        return integralRuleModelId;
    }

    public void setIntegralRuleModelId(String integralRuleModelId) {
        this.integralRuleModelId = integralRuleModelId;
    }

    public String getIntegralNumber() {
        return integralNumber;
    }

    public void setIntegralNumber(String integralNumber) {
        this.integralNumber = integralNumber;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }
}
