package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PayLogEntity;

/**
 * Created by Tom on 2016/1/27 16:17.
 * Describe:支付日志Repository接口
 */
public interface PayLogRepository {

    /**
     * 创建支付日志
     */
    void create(PayLogEntity payLogEntity);

}
