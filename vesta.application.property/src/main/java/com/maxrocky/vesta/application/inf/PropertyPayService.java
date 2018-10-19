package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyIpItemDTO;
import com.maxrocky.vesta.application.DTO.PropertyPayOrderDTO;
import com.maxrocky.vesta.application.DTO.SYOrderResponseDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.PropertyIpItemEntity;
import com.maxrocky.vesta.domain.model.PropertyPayInvoiceEntity;
import com.maxrocky.vesta.domain.model.PropertyPayOrderLogEntity;
import com.maxrocky.vesta.domain.model.PropertyPaymentEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 物业缴费Service接口
 * Created by WeiYangDong on 2016/10/9.
 */
public interface PropertyPayService {

    List<Map<String,Object>> getPropertyPayOrderList(PropertyPayOrderDTO propertyPayOrderDTO,WebPage webPage);

    /**
     * 通过房产信息获取缴费订单列表
     */
    List<Map<String, Object>> getPropertyPayOrderListByHouse(PropertyPayOrderDTO propertyPayOrderDTO);

    /**
     * 通过缴费订单Id检索缴费订单信息
     */
    Map<String,Object> getPropertyPayOrderById(String payOrderId);

    /**
     * 生成支付订单
     */
    String createPropertyPayment(PropertyPayOrderDTO propertyPayOrderDTO);

    /**
     * 获取缴费支付订单详情
     */
    List<Map<String,Object>> getPropertyPaymentListByPaymentId(String paymentId);

    /**
     * 通过票据Id获取该笔支付票据信息
     */
    PropertyPayInvoiceEntity getPropertyPayInvoiceByInvoiceId(String invoiceId);

    /**
     * 保存或更新支付订单票据信息
     */
    void saveOrUpdateInvoice(PropertyPayOrderDTO propertyPayOrderDTO);

    /**
     * 通过缴费订单Id检索缴费支付信息
     */
    PropertyPaymentEntity getPropertyPaymentByPayOrderId(String payOrderId);

    /**
     * 获取缴费票据列表 WeiYangDong 2016-12-01
     */
    List<Map<String,Object>> getPropertyPayInvoiceList(PropertyPayOrderDTO propertyPayOrderDTO,WebPage webPage);

    /**
     * 获取缴费票据相关数据信息 WeiYangDong 2016-12-01
     */
    Map<String,Object> getPropertyPayInvoiceInfo(PropertyPayOrderDTO propertyPayOrderDTO);

    /* ------------物业缴费正式开发接口Service层----------- */
    /* -------------WeiYangDong 2016-11-21------------- */

    /**
     * 获取物业收费类目数据列表 WeiYangDong 2016-11-21
     */
    List<PropertyIpItemEntity> getPropertyIpItemList(PropertyIpItemDTO propertyIpItemDTO,WebPage webPage);

    /**
     * 通过思源缴费单编码获取物业缴费单详情
     * @param revId 思源缴费单编码
     */
    PropertyPayOrderLogEntity getPropertyPayOrderLogByRevId(String revId);


    /* ------------------------------------------------ */

    /**
     * 获取思源缴费单列表(生成支付单),作同步更新物业缴费单日志表
     * WeiYangDong 2017-01-19
     */
    List<Map<String,Object>> getSYPayOrderList(PropertyPayOrderDTO propertyPayOrderDTO);

    /**
     * WebService获取思源缴费单列表接口1.1
     * WeiYangDong 2017-01-05
     */
    SYOrderResponseDTO requestSYOrderList(PropertyPayOrderDTO propertyPayOrderDTO);

    /**
     * 缴费订单状态同步定时器(思源支付接口1.2)
     */
    void synSYPayment();
}
