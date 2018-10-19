package com.maxrocky.vesta.application.messagePushUtil;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushBatchUniMsgRequest;
import com.baidu.yun.push.model.PushBatchUniMsgResponse;
import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;
import com.maxrocky.vesta.domain.model.MessageTokenEntity;
import com.maxrocky.vesta.domain.model.SystemConfigEntity;
import com.maxrocky.vesta.domain.repository.SystemConfigRepository;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/17.
 */
@Component
public class MessagePushUtilTest {

    /* 系统配置 */
    @Resource
    SystemConfigRepository systemConfigRepository;

    /*
IOS推送，由于jar包问题暂时注释
*/
    public int MessageIOSPush(List<MessageTokenEntity> messageTokenEntities, MessagePushDTO messagePushDto) {


        int i = 0;

        SystemConfigEntity pathEntity = systemConfigRepository.get("IosPushP12Path");
        if (pathEntity==null){
            return 0;
        }
        String  p12Path =pathEntity.getConfigValue();
        SystemConfigEntity pwdEntity = systemConfigRepository.get("IosPushP12Password");
        if (pwdEntity==null){
            return 0;
        }
        String  password=pwdEntity.getConfigValue();
//        String  p12Type =sysFiledRepository.getValueByType("p12Type");//动态获得环境更改
        String  p12Type = "0";
        if (messageTokenEntities.size()>0){
            for(MessageTokenEntity messageTokenEntity : messageTokenEntities){

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
//                            .withProductionDestination()//正式证书代码
                                .withSandboxDestination()//测试证书代码，与正式证书代码只能存在一个
                                .build();
                        String payload = APNS.newPayload()
                                .alertBody(pushMessage)//添加推送主题
                                .badge(1)//页面显示的+1小圈圈
                                .customField("url", messagePushDto.getmPushUrl())
                                .sound("default")//添加推送声音，此处我用来传附加参数了。。。（点击跳转网址）
                                .build();
                        service.push(pushToken, payload);
                        System.out.println("推送信息已发送！");
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
                                .customField("url", messagePushDto.getmPushUrl())
                                .sound("default")//添加推送声音，此处我用来传附加参数了。。。（点击跳转网址）
                                .build();
                        service.push(pushToken, payload);
                        System.out.println("推送信息已发送！");
                        i++;

                    }



                } catch (Exception e) {
                    System.out.println("出错了："+e.getMessage());
                }

            }

        }

        return i;
    }


    /**
     * 安卓消息推送
     */
    public int MessageAdrPush(List<MessageTokenEntity> messageTokenEntities, MessagePushDTO messagePushDto) throws PushClientException,PushServerException{

        SystemConfigEntity apiKeyEntity = systemConfigRepository.get("apiKey");
        if (apiKeyEntity==null){
            return 0;
        }
        SystemConfigEntity secretKeyEntity = systemConfigRepository.get("secretKey");
        if (secretKeyEntity==null){
            return 0;
        }
        int j = 0;
        // 1. get apiKey and secretKey from developer console
        String apiKey =apiKeyEntity.getConfigValue() ;//"EkSXHr12UmYlGYPpEXQgeVrn";
        String secretKey =secretKeyEntity.getConfigValue(); //"113f1689423f7811c06327ea407b896c";

        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        // 2. build a BaidupushClient object to access released interfaces
        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);

        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. specify request arguments
            //创建Android通知
            JSONObject notification = new JSONObject();
            notification.put("title", messagePushDto.getmPushTitle());//消息标题
            notification.put("description",messagePushDto.getmPushContent());//内容
            notification.put("notification_builder_id", 0);
            notification.put("notification_basic_style", 4);
            notification.put("open_type", 3);//(1：打开Url; 2：自定义行为；3：默认打开应用;);
            //notification.put("url", "http://push.baidu.com");


            if (messagePushDto.getmPushUrl()!= null) {
                JSONObject jsonCustormCont = new JSONObject();
                String pushUrl = messagePushDto.getmPushUrl();
                jsonCustormCont.put("key", pushUrl); //自定义内容，key-value,多余参数位置***************重点
                notification.put("custom_content", jsonCustormCont);
            }


            String[] channelIds = new String[messageTokenEntities.size()];
            for(int i =0;i< messageTokenEntities.size();i++){//将chanled集合转成数组，安卓需要推送循环数组
                channelIds[i] = messageTokenEntities.get(i).getMessageTokenNum();
                j++;
            }

            //String[] channelIds = //{ "3555225666768992151","4180679567486402138","4064697652036298257",};//手机id
            PushBatchUniMsgRequest request = new PushBatchUniMsgRequest()
                    .addChannelIds(channelIds)
                            //.addMsgExpires(new Integer(600))
                    .addMessageType(1)
                    .addMessage(notification.toString())
                    .addDeviceType(3);//3：Android，4：IOS
            // .addTopicId("Baid123uPush");// 设置类别主题
            // 5. http request
            PushBatchUniMsgResponse response = pushClient
                    .pushBatchUniMsg(request);
            // Http请求结果解析打印
            System.out.println(String.format("msgId: %s, sendTime: %d",
                    response.getMsgId(), response.getSendTime()));
        } catch (PushClientException e) {
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
                        "requestId: %d, errorCode: %d, errorMessage: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }

        }

        return j;

    }
}
