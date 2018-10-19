package com.maxrocky.vesta.application.AdminDTO;

import java.util.Date;

/**
 * 积分规则管理
 */
public class IntegralRuleListDTO {

    private String integralRuleId;//ID

    private String integralNumber;//积分数量

    private String modelName;//模块

    private String clientName;//公众号


    public IntegralRuleListDTO(String integralRuleId, String integralNumber, String modelName, String clientName) {
        this.integralRuleId = integralRuleId;
        this.integralNumber = integralNumber;
        this.modelName = modelName;
        this.clientName = clientName;
    }

    public IntegralRuleListDTO() {
    }

    public String getIntegralRuleId() {
        return integralRuleId;
    }

    public void setIntegralRuleId(String integralRuleId) {
        this.integralRuleId = integralRuleId;
    }

    public String getIntegralNumber() {
        return integralNumber;
    }

    public void setIntegralNumber(String integralNumber) {
        this.integralNumber = integralNumber;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
