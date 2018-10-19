package com.maxrocky.vesta.application.AdminDTO;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/10.
 */
public class IntegralRuleQuerry {

    private String clientName;
    private String modelName;
    private String integralRuleId;

    public String getIntegralRuleId() {
        return integralRuleId;
    }

    public void setIntegralRuleId(String integralRuleId) {
        this.integralRuleId = integralRuleId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
