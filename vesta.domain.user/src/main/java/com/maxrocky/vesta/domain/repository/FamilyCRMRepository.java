package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.FamilyCRMEntity;

import java.util.List;

/**
 * Created by liudongxin on 2016/4/28.
 */
public interface FamilyCRMRepository {
    /**
     * Describe:根据id、会员编号获取信息
     * CreateBy:lingdongxin
     * CreateOn:2016-01-14 09:40:37
     */
    FamilyCRMEntity get(String id, String memberId);

    /**
     * Describe:创建家庭信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-19 10:01:12
     */
    void create(FamilyCRMEntity familyCRMEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:修改家庭信息
     * ModifyBy:
     */
    void update(FamilyCRMEntity familyCRMEntity);
    /**
     * CreatedBy:liudongxin
     * Describe:获取会员的个人信息
     * ModifyBy:
     */
    List<FamilyCRMEntity> getFamilyInfo();
}
