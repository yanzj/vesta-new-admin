package com.maxrocky.vesta.application.messagePushUtil;

import com.notnoop.apns.*;
import com.notnoop.apns.internal.*;

import javax.net.SocketFactory;
import java.util.Date;

/**
 * Created by zhanghj on 2016/3/14.
 * IOS队列推送单元测试
 */
public class IOSTest {
    public static void main(String[] args) {

        String  p12Path ="D:\\ios/develop_quality.p12";
        String  password="2";
        String  p12Type ="0";//动态获得环境更改


        //动态获得DeviceToken
        String  pushToken = "5044924873242611987";//"1106196ce98b90f1869ba9e212e0ef20b8b36090488c331d6127847fb2cdb75b";//messageDeviceToken.getDeviceTokenNum();

        String pushMessage = "测试推送";//得到消息的内容
        try {
            /**设置参数，发送数据**/

            if(p12Type.equals("0")) {
                ApnsService service = APNS.newService()
                        .withCert(p12Path, password)//添加APNs证书和密码
                        .withProductionDestination()//正式证书代码
                        .build();
                long beginTime = new Date().getTime();


                    String payload = APNS.newPayload()
                            .alertBody("推送测试")//添加推送主题
                            .badge(1)//页面显示的+1小圈圈
                            .customField("params", "2016030717144410093")
                            .customField("messageType", "2")
                            .sound("default")//添加推送声音，此处我用来传附加参数了。。。（点击跳转网址）
                            .build();
                        service.push(pushToken, payload);


                System.out.println("----------------开始时间---------------"+ beginTime);
                System.out.println("-----------------结束时间--------------" + new Date().getTime());

//                QueuedApnsService apnsService = new QueuedApnsService(service);
////                ApnsNotification apnsNotification_1 = new SimpleApnsNotification(pushToken,payload_1);
////                ApnsNotification apnsNotification_2 = new SimpleApnsNotification(pushToken,payload_2);
//                apnsService.start();
//                apnsService.push(pushToken, payload_1);
//                apnsService.push(pushToken,payload_2);
//                apnsService.push(pushToken,payload_2);
//                apnsService.stop();

//                ApnsConnection apnsConnection = new ApnsConnectionImpl();
//                BatchApnsService batchApnsService = new BatchApnsService();
//                ApnsNotification  apnsNotification_1 = new SimpleApnsNotification(pushToken,payload_1);
//                batchApnsService.push(apnsNotification_1);

//

                System.out.println("IOS推送信息已发送！" + "qweqweqwe" + "消息类型" + "qweqweqwe" + "推送证书" + p12Path);

            }



        } catch (Exception e) {
            System.out.println("出错了："+e.getMessage());
        }


    }
}
