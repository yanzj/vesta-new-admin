package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.InvoicesTotalEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by liudongxin on 2016/6/2.
 * 客户单统计数据操作方法
 */
public interface InvoicesTotalRepository {

    /**
     * 通过日期和项目id获取统计信息
     * param:创建日期
     * param:项目
     * return
     */
    InvoicesTotalEntity getTotalInfo(String date,String project);

    /**
     * 获取统计列表
     * param:invoicesTotalEntity
     * param:webPage
     * return
     */
    List<InvoicesTotalEntity> getTotalList(InvoicesTotalEntity invoicesTotalEntity,WebPage webPage);

    /**
     * 创建统计信息
     * param:invoicesTotalEntity
     */
    void create(InvoicesTotalEntity invoicesTotalEntity);

    /**
     * 修改统计信息
     * param:invoicesTotalEntity
     */
    void update(InvoicesTotalEntity invoicesTotalEntity);
}
