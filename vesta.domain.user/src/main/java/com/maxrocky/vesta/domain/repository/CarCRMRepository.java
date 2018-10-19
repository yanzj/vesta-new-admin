package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.CarCRMEntity;

import java.util.List;

/**
 * Created by liudongxin on 2016/4/28.
 */
public interface CarCRMRepository {
    /**
     * Describe:根据id、会员编号获取信息
     * CreateBy:lingdongxin
     * CreateOn:2016-01-14 09:40:37
     */
    CarCRMEntity get(String id, String memberId);

    /**
     * Describe:创建车辆信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-19 10:01:12
     */
    void create(CarCRMEntity carCRMEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改车辆信息
     * ModifyBy:
     */
    void update(CarCRMEntity carCRMEntity);
    /**
     * CreatedBy:dl
     * Describe:
     * 获取车辆的全部信息
     * ModifyBy:
     */
    List<CarCRMEntity> getcarinfo();
}
