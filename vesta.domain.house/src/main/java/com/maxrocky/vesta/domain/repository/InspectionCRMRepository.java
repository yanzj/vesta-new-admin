package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.InternalacceptanceHouseEntity;

import java.util.List;

/**
 * Created by dl on 2016/5/9.
 */
public interface InspectionCRMRepository {
    /**
     * Describe:创建预验单
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    void create(InternalacceptanceHouseEntity internalacceptanceHouseEntity);

    /**
     * Describe:修改预验单
     * CreateBy:dl
     * CreateOn:2016-01-19 10:37:54
     */
    void update(InternalacceptanceHouseEntity internalacceptanceHouseEntity);
    /**
     * Describe:根据房间获取预验单
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:19:53
     */
    InternalacceptanceHouseEntity getByHouseCode(String housecode);

    /**
     * Describe:获取全部预验单信息
     * CreateBy:dl
     * CreateOn:2016-01-17 01:19:53
     */
    List<InternalacceptanceHouseEntity>getInternalacceptance();
}
