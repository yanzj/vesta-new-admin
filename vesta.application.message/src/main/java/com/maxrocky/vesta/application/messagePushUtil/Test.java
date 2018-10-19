package com.maxrocky.vesta.application.messagePushUtil;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.*;
import com.maxrocky.vesta.utility.DateUtils;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhanghj on 2016/3/14.
 * 安卓单点推送单元测试
 */
public class Test {
    public static void main(String[] args)
            throws PushClientException,PushServerException {
        /*1. 创建PushKeyPair
         *用于app的合法身份认证
         *apikey和secretKey可在应用详情中获取
         */
        String apiKey = "eSb4GVXGYw53Vmp0vdmCcrfH";
        String secretKey = "2A2INHELs1u7TBV1ooOhHMX7pvBNDiAG";
        PushKeyPair pair = new PushKeyPair(apiKey,secretKey);

        // 2. 创建BaiduPushClient，访问SDK接口
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. 注册YunLogHandler，获取本次请求的交互信息
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            //3 for android, 4 for ios, 5 for wp.
            // 5. 执行Http请求
            long beginTime = new Date().getTime();
for (int i=0;i<1;i++) {
    JSONObject notification = new JSONObject();
    JSONObject jsonCustorm = new JSONObject();
    jsonCustorm.put("alert", "测试数据！测试内容ios");//消息类型
    jsonCustorm.put("badge", 0);//消息类型
    jsonCustorm.put("sound","default");

//    notification.put("title", "标题测试");//消息标题
//    notification.put("description", "测试数据！测试内容ios");//内容
//    notification.put("notification_builder_id", 0);
//    notification.put("notification_basic_style", 0x04);
//    notification.put("open_type", 3);//(1：打开Url; 2：自定义行为；3：默认打开应用;);
    //notification.put("url", "http://push.baidu.com");
//
//    JSONObject jsonCustormCont = new JSONObject();
//    jsonCustormCont.put("messageType", "1");//消息类型
    notification.put("aps", jsonCustorm);
    notification.put("messageType", "1");
    // 4. 设置请求参数，创建请求实例

                PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
                        .addChannelId("5044924873242611987")
                        .addMsgExpires(new Integer(3600)).   //设置消息的有效时间,单位秒,默认3600*5.
                                addMessageType(1).        //设置消息类型,0表示透传消息,1表示通知,默认为0.
                                addMessage(notification.toString()).
                                addDeployStatus(1).
                                addDeviceType(4);     //设置设备类型，deviceType => 1 for web, 2 for pc,
                PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);
}
    System.out.println("----------------开始时间---------------" + beginTime);
    System.out.println("-----------------结束时间--------------" + new Date().getTime());
    System.out.println("---------------------------------------------------------------1");
    // 6. Http请求返回值解析
//            System.out.println("msgId: " + response.getMsgId()
//                    + ",sendTime: " + response.getSendTime());

        } catch (PushClientException e) {
            //ERROROPTTYPE 用于设置异常的处理方式 -- 抛出异常和捕获异常,
            //'true' 表示抛出, 'false' 表示捕获。
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
    }

}
