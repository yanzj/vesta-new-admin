package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.ViewAppChargeInfoEntity;

import java.util.List;

/**
 * Created by Tom on 2016/1/18 16:04.
 * Describe:基础合同信息Repository接口
 */
public interface ViewAppChargeInfoRepository {

    /**
     * Describe:根据房产Id获取有效的合同信息
     * CreateBy:Tom
     * CreateOn:2016-01-18 04:06:40
     */
    ViewAppChargeInfoEntity getByHouseId(int houseId);

}
