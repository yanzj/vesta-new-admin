package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.common.restHTTPResult.ApiResult;

/**
 * Created by Tom on 2016/1/26 13:50.
 * Describe:支付宝移动支付测试
 */
public interface PayTestService {

    /**
     * Code:For test of ali pay
     * Type:Test Method
     * Describe:支付宝移动支付测试接口
     * CreateBy:Tom
     * CreateOn:2016-01-26 01:53:56
     */
    ApiResult getAliPay(String pid);

    /**
     * Code:For test of 99bill pay
     * Type:UI Method
     * Describe:快钱网关支付测试接口
     * CreateBy:Tom
     * CreateOn:2016-02-22 11:15:10
     */
    ApiResult billForTest();

}
