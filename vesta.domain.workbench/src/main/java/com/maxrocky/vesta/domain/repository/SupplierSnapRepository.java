package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SupplierSnapEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by mql on 2016/6/15.
 */
public interface SupplierSnapRepository {

    /**
     * 根据业务ID查询
     * @param businessId
     * @return
     */
    SupplierSnapEntity get(String businessId);

    /**
     * 创建
     * @param supplierSnapEntity
     */
    void create(SupplierSnapEntity supplierSnapEntity);

    /**
     *更新
     * @param supplierSnapEntity
     */
    void update(SupplierSnapEntity supplierSnapEntity);

    /**
      * 根据修改时间查询
      * @param modifyDate
     * @param id
      * @return
    */
    List<SupplierSnapEntity> getByModifyDateAndId(String modifyDate,String id);

    /**
     * 获取所有供应商信息
     */
    List<SupplierSnapEntity> getSupplierSnapList();
}
