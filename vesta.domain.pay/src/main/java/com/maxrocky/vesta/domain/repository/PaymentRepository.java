package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PaymentEntity;

/**
 * Created by Tom on 2016/1/27 15:28.
 * Describe:支付Repository接口
 */
public interface PaymentRepository {

    /**
     * 创建支付
     */
    void create(PaymentEntity paymentEntity);

    /**
     * 根据支付ID查询支付
     */
    PaymentEntity get(String paymentId);

    /**
     * 修改支付信息
     */
    void update(PaymentEntity paymentEntity);

}
