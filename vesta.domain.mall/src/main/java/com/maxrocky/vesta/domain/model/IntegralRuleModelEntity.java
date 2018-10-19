package com.maxrocky.vesta.domain.model;

import javax.persistence.*;

/**
 * 积分规则模块管理
 */
@Entity
@Table(name = "integral_rule_model")
public class IntegralRuleModelEntity {

    @Id
    @Column(name = "model_id", length = 32)
    private String modelId;//ID

    @Basic
    @Column(name = "model_name",length = 50)
    private String modelName;//名称

    @Basic
    @Column(name = "model_type",length = 20)
    private String modelType;//类型 1/2/3/4/5

    public IntegralRuleModelEntity() {
    }

    public IntegralRuleModelEntity(String modelId, String modelName, String modelType) {
        this.modelId = modelId;
        this.modelName = modelName;
        this.modelType = modelType;
    }

    public String getModelId() {
        return modelId;
    }

    public IntegralRuleModelEntity setModelId(String modelId) {
        this.modelId = modelId;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public IntegralRuleModelEntity setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getModelType() {
        return modelType;
    }

    public IntegralRuleModelEntity setModelType(String modelType) {
        this.modelType = modelType;
        return this;
    }
}
