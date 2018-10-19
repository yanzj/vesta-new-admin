package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SupplierEntity;

import java.util.Date;
import java.util.List;

/**
 * Created by liudongxin on 2016/6/7.
 * 供应商数据操作
 */
public interface SupplierRepository {

    /**
     * Describe:创建供应商信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    void create(SupplierEntity supplierEntity);

    /**
     * CreatedBy:liudongxin
     * Describe:修改供应商信息
     * ModifyBy:
     */
    void update(SupplierEntity supplierEntity);

    /**
     * Describe:根据id获取信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     */
    SupplierEntity getById(String id);

    /**
     * 根据修改时间查询
     * @param modifyDate
     * @return
     */
    List<SupplierEntity> getByModifyDate(Date modifyDate);

    /**
     * 根据项目编码和三级分类ID获取供应商信息
     * @param projectNum
     * @param thirdId
     * @return
     */
    List<Object[]> getByProjectNumAndThirdId(String projectNum,String thirdId);

    /**
     * Code:D
     * Type:
     * Describe:根据供应商的名字模糊查询
     * CreateBy:zhangzhaowen
     * CreateOn:2016/8/12
     */
    public List<Object> getSupplierByName(String supplierName);

    /**
     * 根据时间戳获取供应商人员数据
     * */
    List<Object[]> getSupplierPeople(String timeStamp,int num);

    /**
     * 供应商列表
     * */
    List<SupplierEntity> getSupplierList();

    List<SupplierEntity> getSuppliers(String supplierName);
}
