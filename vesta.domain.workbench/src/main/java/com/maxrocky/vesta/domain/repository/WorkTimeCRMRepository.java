package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.WorkTimeEntity;

/**
 * Created by dl on 2016/5/9.
 */
public interface WorkTimeCRMRepository {
    /**
     * Describe:根据id获取信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     */
    WorkTimeEntity get(String id);
    /**
     * Describe:创建维修工时
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    void create(WorkTimeEntity workTimeEntity);
    /**
     * CreatedBy:dl
     * Describe:
     * 修改维修工时
     * ModifyBy:
     */
    void update(WorkTimeEntity workTimeEntity);

    /**
     * CreatedBy:dl
     * Describe:
     * 删除维修工时
     * ModifyBy:
     */
    void delete();
}
