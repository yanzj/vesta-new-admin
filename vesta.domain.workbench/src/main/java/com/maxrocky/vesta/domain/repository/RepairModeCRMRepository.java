package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.RepairModeEntity;

/**
 * Created by dl on 2016/5/9.
 */
public interface RepairModeCRMRepository {
    /**
     * Describe:根据id获取信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     */
    RepairModeEntity get(String id);
    /**
     * Describe:创建维修方式
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    void create(RepairModeEntity repairModeEntity);
    /**
     * CreatedBy:dl
     * Describe:
     * 修改维修方式
     * ModifyBy:
     */
    void update(RepairModeEntity repairModeEntity);

    /**
     * CreatedBy:dl
     * Describe:
     * 删除维修方式
     * ModifyBy:
     */
    void delete();
}
