package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.CrmPropertyPaymentService;
import com.maxrocky.vesta.application.inf.IBasicService;
import com.maxrocky.vesta.application.propertyPayment.WeChatReceiveRequest;
import com.maxrocky.vesta.application.propertyPayment.WeChatResponse;
import com.maxrocky.vesta.application.propertyPayment.WeChatResponseBody;
import com.maxrocky.vesta.application.propertyPayment.WeChatResponseHeader;
import org.springframework.stereotype.Service;

/**
 * CRM_物业缴费Service实现类
 * Created by WeiYangDong on 2017/11/13.
 */
@Service
public class CrmPropertyPaymentServiceImpl implements CrmPropertyPaymentService{

    /**
     * WebService请求CRM物业缴费订单列表
     * @param weChatReceiveRequest 请求参数
     * @return List<WeChatResponseData>
     */
    @Override
    public WeChatResponseBody getCrmPayOrderList(WeChatReceiveRequest weChatReceiveRequest){
        WeChatResponseBody weChatResponseBody = null;
        /*
        //生成请求
        WeChatReceiveRequest weChatReceiveRequest = new WeChatReceiveRequest();
        //备案房间编码
        weChatReceiveRequest.setCrmHouseCode("");
        //预售房间编码
        weChatReceiveRequest.setHouseCode("");
        //房间GUID
        weChatReceiveRequest.setHouseId("");
        //收费状态(0 未清 1 已清 2 全部)
        weChatReceiveRequest.setStatus(0);
        //收费项目
        weChatReceiveRequest.setServiceNo("100000000");
        //账期开始时间
        weChatReceiveRequest.setStart(null);
        //账期结束时间
        weChatReceiveRequest.setEnd(null);
        */
        try {
            System.out.println("----->>CRM缴费消息WebService接口准备发送请求,请求备案房间编码为:"+weChatReceiveRequest.getCrmHouseCode()+",预售房间编码为:"+weChatReceiveRequest.getHouseCode()+"<<-----");
            BasicServiceLocator basicService = new BasicServiceLocator();
            IBasicService iBasicService = basicService.getBasicHttpBinding_IBasicService();
            WeChatResponse weChatResponse = iBasicService.queryReceive(weChatReceiveRequest);
            //解析响应
            WeChatResponseHeader header = weChatResponse.getHeader();
            //0:失败;1:成功
            if ("1".equals(header.getStatus())){
                weChatResponseBody = weChatResponse.getBody();
                System.out.println("----->>CRM缴费消息WebService接口响应完毕<<-----");
            }else{
                //请求失败,打印日志
                System.out.println("----->>CRM缴费消息WebService接口请求失败,错误信息为:"+header.getErrorMessage()+"<<-----");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //请求异常
            System.out.println("----->>CRM缴费消息WebService接口请求异常,代码异常<<-----");
        }
        return weChatResponseBody;
    }
}
