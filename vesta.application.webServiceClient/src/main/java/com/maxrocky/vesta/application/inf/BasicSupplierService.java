package com.maxrocky.vesta.application.inf;

/**
 * Created by liudongxin on 2016/6/7.
 * 接收金茂项目CRM传递供应商数据
 */
public interface BasicSupplierService {
    /**
     * 供应商查询
     * param:项目编码
     * return
     */
    String queryBasicInfo(String[] projectCodeList);
}
