package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.json.PayJsonDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.PaymentEntity;

import java.util.Map;

/**
 * Created by Tom on 2016/1/27 16:04.
 * Describe:支付Service接口
 */
public interface PaymentService {

    /**
     * Code:PM0001
     * Type:UI Method
     * Describe:支付接口
     * CreateBy:Tom
     * CreateOn:2016-02-01 03:14:54
     */
    //ApiResult pay(PayJsonDTO payJsonDTO);

    /**
     * Code:For Pay
     * Type:Pay Method
     * Describe:微信支付回调
     * CreateBy:Tom
     * CreateOn:2016-02-20 10:20:13
     */
    ApiResult weChatCallBack(Map request_map);

    /**
     * Code:For PM0001
     * Type:Service Method
     * Describe:微信支付
     * CreateBy:Tom
     * CreateOn:2016-02-01 03:23:56
     */
    ApiResult weChatPay(PayJsonDTO payJsonDTO);

    /**
     * Code:For Pay
     * Type:Pay Method
     * Describe:快钱支付回调
     * CreateBy:Tom
     * CreateOn:2016-02-24 03:15:21
     */
    String billCallBack(Map<String, String[]> map);

    /**
     * 根据支付ID查询支付信息
     */
    PaymentEntity get(String paymentId);
}
