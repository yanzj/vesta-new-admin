package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;
import java.util.Map;

/**
 * 物业缴费持久层接口
 * Created by WeiYangDong on 2016/10/9.
 */
public interface PropertyPayRepository {

    /**
     * 保存或更新Entity
     */
    <T> void saveOrUpdate(T entity);

    /**
     * 删除Entity
     */
    public <T> void delete(T entity);

    /**
     * 获取缴费单列表数据  WeiYangDong_2016-11-18
     * @param params    检索条件
     * @param webPage   分页
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> getPropertyPayOrderList(Map<String,Object> params,WebPage webPage);

    /**
     * 通过房产信息获取缴费订单列表
     */
    List<Map<String,Object>> getPropertyPayOrderListByHouse(Map<String,Object> params,WebPage webPage);

    /**
     * 通过缴费订单Id检索缴费订单信息
     */
    Map<String,Object> getPropertyPayOrderById(String payOrderId);

    /**
     * 通过缴费支付订单Id检索缴费支付信息
     */
    List<PropertyPaymentEntity> getPropertyPaymentById(String id);

    /**
     * 通过缴费订单Id检索缴费支付信息
     */
    PropertyPaymentEntity getPropertyPaymentByPayOrderId(String payOrderId);

    /**
     * 通过支付订单获取缴费支付订单列表
     * @param paymentId 缴费支付订单ID
     * @return  List<Map<String, Object>>
     */
    List<Map<String, Object>> getPropertyPaymentListByPaymentId(String paymentId);

    /**
     * 通过票据Id获取该笔支付票据信息
     * @param invoiceId 票据ID
     * @return  PropertyPayInvoiceEntity
     */
    PropertyPayInvoiceEntity getPropertyPayInvoiceByInvoiceId(String invoiceId);

    /**
     * 获取缴费票据列表 WeiYangDong 2016-12-01
     * @param params 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getPropertyPayInvoiceList(Map<String,Object> params,WebPage webPage);

    /* -----------物业缴费正式开发接口数据持久层----------- */
    /* ------------WeiYangDong 2016-11-21------------- */

    /**
     * 获取物业收费类目数据列表 WeiYangDong 2016-11-21
     * @return  List<PropertyIpItemEntity>
     */
    List<PropertyIpItemEntity> getPropertyIpItemList(Map<String,Object> params,WebPage webPage);

    /**
     * 通过主键ID获取物业缴费单详情
     * @param payOrderId    缴费单ID
     * @return  PropertyPayOrderLogEntity
     */
    PropertyPayOrderLogEntity getPropertyPayOrderLogById(String payOrderId);

    /**
     * 通过支付订单ID获取物业缴费单列表
     * @param paymentId    支付单ID
     * @return  List<PropertyPayOrderLogEntity>
     */
    List<PropertyPayOrderLogEntity> getPropertyPayOrderLogListByPaymentId(String paymentId);

    /**
     * 通过思源缴费单编码获取物业缴费单详情
     * @param revId 思源缴费单编码
     * @return PropertyPayOrderLogEntity
     */
    PropertyPayOrderLogEntity getPropertyPayOrderLogByRevId(String revId);

    /**
     * 通过不同缴费单状态及支付单状态检索物业缴费单信息
     * @param payOrderState 缴费单状态
     * @param paymentState 支付单状态
     * @return List<PropertyPayOrderLogEntity>
     */
    List<Map<String,Object>> getHandlePropertyPayOrderList(String payOrderState,String paymentState);


    /* ------------------------------------------------ */
}
