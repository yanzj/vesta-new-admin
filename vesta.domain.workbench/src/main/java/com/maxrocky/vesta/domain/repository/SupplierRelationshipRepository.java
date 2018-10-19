package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SupplierRelationshipEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by liudongxin on 2016/6/7.
 * 供应商关系数据操作
 */
public interface SupplierRelationshipRepository {
    /**
     * Describe:创建供应商信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    void create(SupplierRelationshipEntity supplierEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:修改供应商信息
     * ModifyBy:
     */
    void update(SupplierRelationshipEntity supplierEntity);

    /**
     * Describe:根据id获取信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     */
    SupplierRelationshipEntity getById(String id);

    /**
     * 根据修改时间查询
     * @param modifyDate
     * @return
     */
    List<SupplierRelationshipEntity> getByModifyDate(Date modifyDate);

    /**
     * CreatedBy:liudongxin
     * Describe:删除
     * ModifyBy:
     */
    void delete(String projectNum);
}
