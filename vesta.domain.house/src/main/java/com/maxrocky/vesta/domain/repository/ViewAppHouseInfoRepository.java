package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ViewAppHouseInfoEntity;

import java.util.List;

/**
 * Created by Tom on 2016/1/18 15:37.
 * Describe:基础房产信息Repository接口
 */
public interface ViewAppHouseInfoRepository {

    /**
     * Describe:根据条件查询有效房产信息
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:38:19
     */
    ViewAppHouseInfoEntity getByQuery(ViewAppHouseInfoEntity viewAppHouseInfoEntity);

    /**
     * Describe:根据业主Id查询有效房产列表
     * CreateBy:Tom
     * CreateOn:2016-01-22 03:44:10
     */
    List<ViewAppHouseInfoEntity> getListByOwnerId(int viewAppOwnerId);

    /****
     * 根据房屋id查询房屋信息
     * @param hourseId
     * @return
     */
    ViewAppHouseInfoEntity getHomeInfoByHouseId(String hourseId);
}
