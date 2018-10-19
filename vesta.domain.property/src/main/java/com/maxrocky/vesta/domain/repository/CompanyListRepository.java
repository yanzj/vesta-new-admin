package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.SupplierEntity;

import java.util.List;

/**
 * Created by 27978 on 2016/8/12.
 */
public interface CompanyListRepository {

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取责任单位列表
    */
    public List<Object[]> getCompanyOnes(String projectId, String thirdId);
}
