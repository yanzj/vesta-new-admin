package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SupplierAgencyMapEntity;

/**
 * Created by chen on 2016/9/9.
 */
public interface SupplierAgencyRepository {
    /**新增供应商组织机构关系*/
    void addSupplierAgency(SupplierAgencyMapEntity supplierAgencyMapEntity);
    /**删除供应商组织机构关系*/
    void delSupplierAgency(SupplierAgencyMapEntity supplierAgencyMapEntity);
    /**根据组织机构ID获取关系*/
    SupplierAgencyMapEntity getSupplierAgencys(String agencyId);
    /**更新关系*/
    void updateSupplierAgency(SupplierAgencyMapEntity supplierAgencyMapEntity);
}
