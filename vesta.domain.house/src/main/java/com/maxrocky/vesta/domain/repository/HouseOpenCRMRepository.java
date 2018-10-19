package com.maxrocky.vesta.domain.repository;


import com.maxrocky.vesta.domain.model.HouseOpenEntity;

import java.util.List;

/**
 * Created by dl on 2016/5/9.
 */
public interface HouseOpenCRMRepository {
    /**
     * Describe:创建业主开放日
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    void create(HouseOpenEntity HouseOpenEntity);

    /**
     * Describe:修改业主开放日
     * CreateBy:dl
     * CreateOn:2016-01-19 10:37:54
     */
    void update(HouseOpenEntity HouseOpenEntity);
    /**
     * Describe:根据房间获取业主开放日
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:19:53
     */
    HouseOpenEntity getByHouseCode(String housecode);

    /**
     * Describe:获取全部业主开放日信息
     * CreateBy:dl
     * CreateOn:2016-01-17 01:19:53
     */
    List<HouseOpenEntity> getOpenHouse();
}
