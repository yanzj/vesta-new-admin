package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.json.PayJsonDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;

/**
 * Created by Tom on 2016/2/14 16:01.
 * Describe:支付日志Service接口
 */
public interface PayLogService {

    /**
     * Code:For Pay
     * Type:Log Method
     * Describe:创建支付日志
     * CreateBy:Tom
     * CreateOn:2016-02-14 04:02:45
     */
    ApiResult createPayLog(PayJsonDTO payJsonDTO);

    /**
     * Code:For Pay
     * Type:Log Method
     * Describe:创建支付日志
     * CreateBy:Tom
     * CreateOn:2016-02-20 11:28:19
     */
    ApiResult createPayLog(String paymentId, String content, String type);

}
