package com.maxrocky.vesta.application.messagePushUtil;

import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;
import com.maxrocky.vesta.utility.AppConfig;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/3/16.
 */
public class MessageIosPushUtil_2 {
    public int messageUserIOSPush(List<MessagePushDTO> messagePushDtos,String userType) throws Exception {
        try {
            int i = 0;
            String IOSP12Type="";
            String IOSP12Path ="";
            String IOSP12Pwd="";

            if (userType.equals("1")){
                IOSP12Type=AppConfig.USER_IOS_P12_TYPE;
                IOSP12Path=AppConfig.USER_IOS_P12_PATH;
                IOSP12Pwd=AppConfig.USER_IOS_P12_PWD;
            }else if (userType.equals("2")){
                IOSP12Type=AppConfig.STAFF_IOS_P12_TYPE;
                IOSP12Path=AppConfig.STAFF_IOS_P12_PATH;
                IOSP12Pwd=AppConfig.STAFF_IOS_P12_PWD;
            }else if (userType.equals("3")){
                IOSP12Type=AppConfig.USER_STORE_P12_TYPE;
                IOSP12Path=AppConfig.USER_STORE_P12_PATH;
                IOSP12Pwd=AppConfig.USER_STORE_P12_PWD;
            }else if (userType.equals("4")){
                IOSP12Type=AppConfig.STAFF_STORE_P12_TYPE;
                IOSP12Path=AppConfig.STAFF_STORE_P12_PATH;
                IOSP12Pwd=AppConfig.STAFF_STORE_P12_PWD;
            }
            if (IOSP12Path.equals("")||IOSP12Pwd.equals("")){
                return 0;
            }
            //定义业主端IOS推送ApnsService
//            ApnsService service_user = APNS.newService()
//                    .withCert(IOSP12Path, IOSP12Pwd)//添加APNs证书和密码
////                    .withProductionDestination()//正式证书代码
//                    .withSandboxDestination()//测试证书代码，与正式证书代码只能存在一个
//                    .build();


            //生成推送内容
            List<MessageIosPushDto_2> pushs = new ArrayList<>();
            if (messagePushDtos.size() > 0) {
                for (MessagePushDTO messagePushDto : messagePushDtos) {
                    //动态获得DeviceToken
                    String pushToken = messagePushDto.getPushToken();
                    String pushMessage = messagePushDto.getmPushContent();//得到消息的内容
                    /**设置参数，发送数据**/
                        String payload = APNS.newPayload()
                                .alertBody(pushMessage)//添加推送主题
                                .badge(1)//页面显示的+1小圈圈
                                .customField("params", messagePushDto.getmPushUrl())
                                .customField("messageType", messagePushDto.getmType())
                                .sound("default")//添加推送声音，此处我用来传附加参数了。。。（点击跳转网址）
                                .build();
                        MessageIosPushDto_2 messageIosPushDto_2 = new MessageIosPushDto_2();
                        messageIosPushDto_2.setPushToken(pushToken);
                        messageIosPushDto_2.setPayload(payload);
                        pushs.add(messageIosPushDto_2);
                    i++;
                    }
            }
            if (pushs.size()>0){

                    //正式证书
            if (IOSP12Type.equals("0")) {
               ApnsService service = APNS.newService()
                        .withCert(IOSP12Path, IOSP12Pwd)//添加APNs证书和密码
                        .withProductionDestination()//正式证书代码
                        .build();
                for (MessageIosPushDto_2 push:pushs) {
                    service.push(push.getPushToken(), push.getPayload());
                    System.out.println("推送IOS-----------------" + userType);
                }
            }//测试证书
            else if (IOSP12Type.equals("1")){
                ApnsService service = APNS.newService()
                        .withCert(IOSP12Path, IOSP12Pwd)//添加APNs证书和密码
//                            .withProductionDestination()//正式证书代码
                        .withSandboxDestination()//测试证书代码，与正式证书代码只能存在一个
                        .build();
                for (MessageIosPushDto_2 push:pushs) {
                service.push(push.getPushToken(),push.getPayload());
                System.out.println("推送IOS-----------------"+userType);
                     }
                }
            }
            return i;
        } catch (Exception e) {
            System.out.println("出错了：" + e.getMessage());
            return 0;
        }
    }

    public int messageStaffIOSPush(List<MessagePushDTO> messagePushDtos) throws Exception {
        try {
            int i = 0;
            //定义员工端IOS推送ApnsService
            ApnsService service_staff = APNS.newService().build();
            if (AppConfig.STAFF_IOS_P12_TYPE.equals("0")) {
                service_staff = APNS.newService()
                        .withCert(AppConfig.STAFF_IOS_P12_PATH, AppConfig.STAFF_IOS_P12_PWD)//添加APNs证书和密码
                        .withProductionDestination()//正式证书代码
                        .build();
            }else if (AppConfig.STAFF_IOS_P12_TYPE.equals("1")){
                service_staff = APNS.newService()
                        .withCert(AppConfig.STAFF_IOS_P12_PATH, AppConfig.STAFF_IOS_P12_PWD)//添加APNs证书和密码
                        .withSandboxDestination()//测试证书代码，与正式证书代码只能存在一个
                        .build();
            }
            //生成推送内容
            List<MessageIosPushDto_2> pushs = new ArrayList<>();
            if (messagePushDtos.size() > 0) {
                for (MessagePushDTO messagePushDto : messagePushDtos) {
                    //动态获得DeviceToken
                    String pushToken = messagePushDto.getPushToken();
                    String pushMessage = messagePushDto.getmPushContent();//得到消息的内容
                    /**设置参数，发送数据**/
                    String payload = APNS.newPayload()
                            .alertBody(pushMessage)//添加推送主题
                            .badge(1)//页面显示的+1小圈圈
                            .customField("params", messagePushDto.getmPushUrl())
                            .customField("messageType", messagePushDto.getmType())
                            .sound("default")//添加推送声音，此处我用来传附加参数了。。。（点击跳转网址）
                            .build();
                    MessageIosPushDto_2 messageIosPushDto_2 = new MessageIosPushDto_2();
                    messageIosPushDto_2.setPushToken(messagePushDto.getPushToken());
                    messageIosPushDto_2.setPayload(payload);
                    pushs.add(messageIosPushDto_2);
                    i++;
                }
            }
            if (pushs.size()>0){
                for (MessageIosPushDto_2 push:pushs){
                    service_staff.push(push.getPushToken(),push.getPayload());
                }
            }
            return i;
        } catch (Exception e) {
            System.out.println("出错了：" + e.getMessage());
            return 0;
        }


    }
}
