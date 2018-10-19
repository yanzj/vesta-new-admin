package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserIntegralRuleRecordEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/14.
 */
public interface UserIntegralRuleRecordRepository {
    /**
     * 获取所有
     * @return
     */
    List<Object> getAll(Map<String,Object> paramsMap, WebPage webPage);

    /**
     * 根据ID获取用户积分信息
     * @param paramsMap
     * @return
     */
    List<Object> getUserIntegralRuleById(Map<String,Object> paramsMap);

    /**
     * 新增积分信息
     * @param u
     */
    void AddIntegralRuleEntity(UserIntegralRuleRecordEntity u);

    /**
     * 统计
     * @param paramsMap
     * @return
     */
    List<Object> getRule(Map<String,Object> paramsMap);


}
