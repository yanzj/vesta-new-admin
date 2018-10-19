package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PropertyFeedbackEntity;

/**
 * Created by liudongxin on 2016/1/22.
 * 物业报修评价数据操作接口
 */
public interface PropertyFeedbackRepository {
    /**
     * 添加评价
     * @param propertyFeedbackEntity
     */
    void createFeedback(PropertyFeedbackEntity propertyFeedbackEntity);

    /**
     * 通过id，获取评价信息
     * @param id：报修单id
     * @return
     */
    PropertyFeedbackEntity getFeedback(String id);

    /**
     * 修改评价信息
     * @param propertyFeedbackEntity
     */
    void updateFeedback(PropertyFeedbackEntity propertyFeedbackEntity);
}