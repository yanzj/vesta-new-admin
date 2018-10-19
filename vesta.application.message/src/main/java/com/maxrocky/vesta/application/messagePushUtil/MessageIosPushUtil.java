package com.maxrocky.vesta.application.messagePushUtil;

import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;
import com.maxrocky.vesta.domain.model.MessageTokenEntity;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

/**
 * Created by zhanghj on 2016/3/8.
 */
public class MessageIosPushUtil {
    public int MessageIOSPush(MessageTokenEntity messageTokenEntity, MessagePushDTO messagePushDto) throws Exception{
        int i = 0;
        String IosPushP12Path="";//定义p12路径
        String IosPushP12Password="";//定义P12密码
        String IosPushP12Type="";
//        if (messagePushDto.getmUserType().equals("1")){//当为1时获取业主P12路径
//            IosPushP12Path = AppConfig.USER_IOS_P12_PATH;
//            IosPushP12Password = AppConfig.USER_IOS_P12_PWD;
//            IosPushP12Type=AppConfig.USER_IOS_P12_TYPE;
//        }
//        else if (messagePushDto.getmUserType().equals("2")){//当为2时获取员工P12路径
//            IosPushP12Path = AppConfig.STAFF_IOS_P12_PATH;
//            IosPushP12Password = AppConfig.STAFF_IOS_P12_PWD;
//            IosPushP12Type=AppConfig.STAFF_IOS_P12_TYPE;
//        }
        IosPushP12Path = messagePushDto.getIosPushP12Path();
        IosPushP12Password = messagePushDto.getIosPushP12Password();
        IosPushP12Type = messagePushDto.getIosPushP12Type();
        String  p12Path =IosPushP12Path;
        String  password=IosPushP12Password;
        String  p12Type =IosPushP12Type;//动态获得环境更改


                //动态获得DeviceToken
                String  pushToken = messageTokenEntity.getMessageTokenNum();//"1106196ce98b90f1869ba9e212e0ef20b8b36090488c331d6127847fb2cdb75b";//messageDeviceToken.getDeviceTokenNum();
                String pushsound = messagePushDto.getmPushSound();
                if(messagePushDto.getmPushSound()==null){//IOS推送声音，可以写成网页人为设置，此处默认为default（默认声音）
                    pushsound = "default";
                }
                String pushMessage = messagePushDto.getmPushContent();//得到消息的内容
                try {
                    /**设置参数，发送数据**/

                    if(p12Type.equals("0")) {
                        ApnsService service = APNS.newService()
                                .withCert(p12Path, password)//添加APNs证书和密码
                                .withProductionDestination()//正式证书代码
                                .build();
                        String payload = APNS.newPayload()
                                .alertBody(pushMessage)//添加推送主题
                                .badge(1)//页面显示的+1小圈圈
                                .customField("params", messagePushDto.getmPushUrl())
                                .customField("messageType", messagePushDto.getmType())
                                .sound("default")//添加推送声音，此处我用来传附加参数了。。。（点击跳转网址）
                                .build();
                        service.push(pushToken, payload);
                        System.out.println("IOS推送信息已发送！"+messageTokenEntity.getMessageTokenNum()+"消息类型"+messagePushDto.getmType()+"推送证书"+p12Path);
                        i++;
                    }
                    else if(p12Type.equals("1")){

                        ApnsService service = APNS.newService()
                                .withCert(p12Path, password)//添加APNs证书和密码
//                            .withProductionDestination()//正式证书代码
                                .withSandboxDestination()//测试证书代码，与正式证书代码只能存在一个
                                .build();
                        String payload = APNS.newPayload()
                                .alertBody(pushMessage)//添加推送主题
                                .badge(1)//页面显示的+1小圈圈
                                .customField("params", messagePushDto.getmPushUrl())
                                .customField("messageType", messagePushDto.getmType())
                                .sound("default")//添加推送声音，此处我用来传附加参数了。。。（点击跳转网址）
                                .build();
                        service.push(pushToken, payload);
                        System.out.println("IOS推送信息已发送！"+messageTokenEntity.getMessageTokenNum()+"消息类型"+messagePushDto.getmType()+"推送证书"+p12Path);
                        i++;

                    }



                } catch (Exception e) {
                    System.out.println("出错了："+e.getMessage());
                }


        return i;
    }
}
