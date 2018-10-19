package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserIntegralRuleEntity;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/11.
 */
public interface UserIntegralRuleRepository {
    /**
     * 新增
     * @param user
     */
    void AddUserIntegral(UserIntegralRuleEntity user);

    /**
     * 修改
     * @param user
     */
    void UpdateUserIntegral(UserIntegralRuleEntity user);

    UserIntegralRuleEntity get(String userId,String appId);
}
