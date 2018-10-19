package com.maxrocky.vesta.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 积分规则管理
 */
@Entity
@Table(name = "integral_rule")
public class IntegralRuleEntity {
    @Id
    @Column(name = "integral_rule_id", length = 32)
    private String integralRuleId;//ID

    @Basic
    @Column(name = "client_config_id",length = 50)
    private Integer clientConfigId;//公众号ID

    @Basic
    @Column(name = "integral_rule_model_id", length = 32)
    private String integralRuleModelId;//模块ID

    @Basic
    @Column(name = "integral_number", length = 32)
    private String integralNumber;//积分数量

    @Basic
    @Column(name = "create_by", length = 32)
    private String createBy;    //创建人

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_on", nullable = true, length = 50)
    private Date createOn;      //创建时间

    public IntegralRuleEntity() {
    }

    public IntegralRuleEntity(String integralRuleId, Integer clientConfigId, String integralRuleModelId, String integralNumber, String createBy, Date createOn) {
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
