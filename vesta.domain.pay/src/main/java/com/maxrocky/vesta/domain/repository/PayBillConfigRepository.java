package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PayBillConfigEntity;

/**
 * Created by Tom on 2016/2/23 17:41.
 * Describe:快钱支付配置Repository接口
 */
public interface PayBillConfigRepository {

    /**
     * Describe:根据域获取快钱配置
     * CreateBy:Tom
     * CreateOn:2016-2-23 17:43:49
     */
    PayBillConfigEntity getByDomain(String domain);

}
