package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HousePlanCRMEntity;

import java.util.List;

/**
 * Created by liudongxin on 2016/4/22.
 */
public interface HousePlanCRMRepository {
    /**
     * Describe:创建房屋计划信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    void create(HousePlanCRMEntity deliveryPlanCrmEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:修改房屋计划信息
     * ModifyBy:
     */
    void update(HousePlanCRMEntity deliveryPlanCrmEntity);

    /**
     * Describe:根据id获取信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     */
    HousePlanCRMEntity getById(String id);

    /**
     * Describe:根据计划id和房间id获取信息
     * CreateBy:Magic
     * CreateOn:2017-07-05
     */
    HousePlanCRMEntity getByIdAndPlanId(String rooId,String planId);

    /**
     * 修改活动临时表信息 关闭计划
     * */
    void updateHousePlanStateById(String planId);

    /**
     * 修改活动临时表信息 关闭计划(批量)
     * */
    void updateHousePlanStateByIdList(List<String> idList);



}
