package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SupplierCRMEntity;

import java.util.List;

/**
 * Created by chen on 2016/4/22.
 */
public interface SupplierCRMRepository {
    //新增供应商
    void AddSupplier(SupplierCRMEntity supplierCRMEntity);
    //修改供应商
    void UpdateSupplier(SupplierCRMEntity supplierCRMEntity);

    /**
     * 查询供应商
     * @return
     */
    public List<SupplierCRMEntity> getSupplierList();
}
