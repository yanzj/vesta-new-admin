package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.IntegralRuleEntity;
import com.maxrocky.vesta.domain.model.IntegralRuleModelEntity;
import com.maxrocky.vesta.domain.model.SellerCollectEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 积分规则管理
 */
public interface IntegralRuleRepository {
    /**
     * 获取所有积分规则
     * @return
     */
    List<Object> getIntegralRuleList(Map<String,Object> paramsMap, WebPage webPage);

    /**
     * 根据ID获取规则
     * @param integralRuleId
     * @return
     */
    IntegralRuleEntity getIntegralRuleById(String integralRuleId);

    /**
     * 新增规则
     * @param itegralRuleEntity
     */
    void AddIntegralRuleEntity(IntegralRuleEntity itegralRuleEntity);

    /**
     * 修改规则
     * @param itegralRuleEntity
     */
    void UpdateIntegralRuleEntity(IntegralRuleEntity itegralRuleEntity);

    /**
     * 检索重复规则
     */
    List<IntegralRuleEntity> CheckIntegralRule(IntegralRuleEntity itegralRuleEntity);


    IntegralRuleEntity getIntegralRuleModelByTypeClien(int appid, String type);
}
