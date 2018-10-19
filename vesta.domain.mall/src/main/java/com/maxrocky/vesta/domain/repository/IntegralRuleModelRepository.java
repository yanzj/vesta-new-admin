package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.IntegralRuleModelEntity;

import java.util.List;

/**
 * 积分规则模块管理
 */
public interface IntegralRuleModelRepository {

    /**
     * 获取所有模块
     * @return
     */
    List<IntegralRuleModelEntity> getIntegralRuleModelList();

    /**
     * 根据ID获取模块
     * @return
     */
    IntegralRuleModelEntity getIntegralRuleModelById(String modelId);
}
