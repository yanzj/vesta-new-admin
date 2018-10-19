package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ViewAppHouseInfoEntityForPayment;

import java.util.List;

/**
 * Created by Tom on 2016/1/18 15:37.
 * Describe:基础房产信息Repository接口
 */
public interface ViewAppHouseInfoRepositoryForPayment {

    /**
     * Describe:根据条件查询有效房产信息
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:38:19
     */
    ViewAppHouseInfoEntityForPayment getByQuery(ViewAppHouseInfoEntityForPayment viewAppHouseInfoEntityForPayment);

    /**
     * Describe:根据业主Id查询有效房产列表
     * CreateBy:Tom
     * CreateOn:2016-01-22 03:44:10
     */
    List<ViewAppHouseInfoEntityForPayment> getListByOwnerId(int viewAppOwnerId);

    /****
     * 根据房屋id查询房屋信息
     * @param hourseId
     * @return
     */
    ViewAppHouseInfoEntityForPayment getHomeInfoByHouseId(String hourseId);
}
