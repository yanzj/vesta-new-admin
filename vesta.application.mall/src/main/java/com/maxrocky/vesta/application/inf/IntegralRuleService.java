package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.AdminDTO.IntegralRuleDTO;
import com.maxrocky.vesta.application.AdminDTO.IntegralRuleListDTO;
import com.maxrocky.vesta.application.AdminDTO.IntegralRuleQuerry;
import com.maxrocky.vesta.domain.model.IntegralRuleEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 积分规则管理
 */
public interface IntegralRuleService {
    /**
     * 获取所有积分规则
     * @return
     */
    List<IntegralRuleListDTO> getIntegralRuleList(IntegralRuleQuerry q, WebPage webPage);

    /**
     * 根据ID获取规则
     * @param integralRuleId
     * @return
     */
    IntegralRuleDTO getIntegralRuleById(String integralRuleId);

    /**
     * 新增规则
     * @param integralRuleDTO
     */
    Integer AddIntegralRuleEntity(IntegralRuleDTO integralRuleDTO);

    /**
     * 修改规则
     * @param integralRuleDTO
     */
    void UpdateIntegralRuleEntity(IntegralRuleDTO integralRuleDTO);

    /**
     * 检查重复积分规则
     * @return
     */
    Integer getIntegralRuleList(IntegralRuleDTO i);

}
