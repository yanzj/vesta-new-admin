package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseFamilyCRMEntity;

/**
 * Created by liudongxin on 2016/4/13.
 */
public interface HouseFamilyCRMRepository {

    /**
     * Describe:根据业主关系Id获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    HouseFamilyCRMEntity get(String id);

    /**
     * Describe:创建家庭关系信息
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:01:12
     */
    void create(HouseFamilyCRMEntity houseFamilyCRMEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改家庭关系信息
     * ModifyBy:
     */
    void updateHouseInfo(HouseFamilyCRMEntity houseFamilyCRMEntity);

}
