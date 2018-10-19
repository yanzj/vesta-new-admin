package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.propertyPayment.WeChatReceiveRequest;
import com.maxrocky.vesta.application.propertyPayment.WeChatResponseBody;

/**
 * CRM_物业缴费Service
 * Created by WeiYangDong on 2017/11/13.
 */
public interface CrmPropertyPaymentService {

    WeChatResponseBody getCrmPayOrderList(WeChatReceiveRequest weChatReceiveRequest);
}
