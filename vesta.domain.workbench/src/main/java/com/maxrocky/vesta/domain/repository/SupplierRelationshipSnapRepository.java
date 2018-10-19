package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SupplierRelationshipSnapEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by mql on 2016/6/15.
 */
public interface SupplierRelationshipSnapRepository {
    /**
     * 根据业务ID查询
     * @param businessId
     * @return
     */
    SupplierRelationshipSnapEntity get(String businessId);

    /**
     * 创建
     * @param supplierRelationshipSnapEntity
     */
    void create(SupplierRelationshipSnapEntity supplierRelationshipSnapEntity);

    /**
     *更新
     * @param supplierRelationshipSnapEntity
     */
    void update(SupplierRelationshipSnapEntity supplierRelationshipSnapEntity);

    /**
     * 根据跟新时间和自增id查询
     * @param modifyDate
     * @param id
     * @return
     */
    public List<SupplierRelationshipSnapEntity> getByModifyDateAndId(Date modifyDate,String id);

    /**
     * 获取所有供应商信息
     */
    List<SupplierRelationshipSnapEntity> getSupplierList();
}
