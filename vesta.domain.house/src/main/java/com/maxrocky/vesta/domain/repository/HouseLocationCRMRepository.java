package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HouseLocationEntity;

/**
 * Created by dl on 2016/5/10.
 */
public interface HouseLocationCRMRepository {
    /**
     * Describe:根据id获取信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     */
    HouseLocationEntity get(String id);
    /**
     * Describe:创建房屋位置
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    void create(HouseLocationEntity houseLocationEntity);
    /**
     * CreatedBy:dl
     * Describe:
     * 修改房屋位置
     * ModifyBy:
     */
    void update(HouseLocationEntity houseLocationEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:删除
     * ModifyBy:
     */
    void delete();
}

