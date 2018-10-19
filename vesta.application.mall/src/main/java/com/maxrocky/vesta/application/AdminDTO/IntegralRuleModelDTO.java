package com.maxrocky.vesta.application.AdminDTO;

import javax.persistence.*;

/**
 * 积分规则模块管理DTO
 */
public class IntegralRuleModelDTO {


    private String modelId;//ID

    private String modelName;//名称

    private String modelType;//类型 1/2/3/4/5

    public IntegralRuleModelDTO() {
    }

    public IntegralRuleModelDTO(String modelId, String modelName, String modelType) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.modelType = modelType;
    }

    public String getModelId() {
        return modelId;
    }

    public IntegralRuleModelDTO setModelId(String modelId) {
        this.modelId = modelId;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public IntegralRuleModelDTO setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getModelType() {
        return modelType;
    }

    public IntegralRuleModelDTO setModelType(String modelType) {
        this.modelType = modelType;
        return this;
    }
}
