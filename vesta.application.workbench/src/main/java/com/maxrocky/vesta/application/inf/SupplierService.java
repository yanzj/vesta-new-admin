package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.jsonDTO.SupplierAppDTO;
import com.maxrocky.vesta.application.adminDTO.SupplierDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mql on 2016/6/13.
 */
public interface SupplierService {

    /**
     * 查询供应商信息
     * @param supplierDate
     * @param relationshipDate
     * @return
     */
    SupplierAppDTO getSupplier(Date supplierDate,Date relationshipDate);

    /**
     * 根据项目编码和三级分类获取供应商列表
     * @param projectNum
     * @param thirdId
     * @return
     */
    Map<String,String> getSupplierByProjectNumAndThirdId(String projectNum,String thirdId);

    /**
     * Code:D
     * Type:
     * Describe:根据供应商的名字模糊查询
     * CreateBy:zhangzhaowen
     * CreateOn:2016/8/12
     */
    public String getSupplierByName(String supplierName);

    /**
     * 根据时间获取供应商人员信息
     * */
    ApiResult getSupplierPeople(String timeStamp,String num);

    /**根据供应商ID获取详情*/
    SupplierDTO getSupplierDetail(String supplierId);

    /**获取供应商列表*/
    List<SupplierDTO> getSupplierList();

    /**根据名字查询供应商列表*/
    ApiResult getSuplliers(String supplierName);

}
