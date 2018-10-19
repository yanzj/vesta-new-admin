package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.PayConfigEntity;
import com.maxrocky.vesta.domain.model.PayWeChatConfigEntity;

/**
 * Created by Tom on 2016/2/15 14:53.
 * Describe:微信支付配置Repository接口
 */
public interface PayWeChatConfigRepository {

    /**
     * Describe:根据域获取微信配置
     * CreateBy:Tom
     * CreateOn:2016-2-15 14:55:37
     */
    PayWeChatConfigEntity getByDomain(String domain);

    /**
     * Describe:根据支付类型Code获取支付回调地址配置
     * CreateBy:WeiYangDong
     * CreateOn:2016-10-11 11:11:37
     */
    PayConfigEntity getPayConfigByCode(String code);

}
