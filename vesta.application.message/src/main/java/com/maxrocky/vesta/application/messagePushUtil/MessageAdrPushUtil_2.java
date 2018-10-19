package com.maxrocky.vesta.application.messagePushUtil;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;
import com.maxrocky.vesta.utility.AppConfig;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/3/16.
 */
public class MessageAdrPushUtil_2 {

    public int messageUserAdrPush(List<MessagePushDTO> messagePushDtos,String userType) throws  Exception{

        String apiKey="";
        String secretKey="";
        if (userType.equals("1")) {
            apiKey = AppConfig.USERAPIKEY;
            secretKey = AppConfig.USERSECRETKEY;
        }else if (userType.equals("2")){
            apiKey = AppConfig.STAFFAPIKEY;
            secretKey = AppConfig.STAFFSECRETKEY;
        }
        if (apiKey.equals("")||secretKey.equals("")){
            return 0;
        }
        PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

        BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);


        pushClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
        try {
            List<PushMsgToSingleDeviceRequest> requests = new ArrayList<>();
                if (messagePushDtos.size()>0){
                    for (MessagePushDTO messagePushDTO:messagePushDtos){
                        JSONObject notification = new JSONObject();
                        notification.put("title", messagePushDTO.getmPushTitle());//消息标题
                        notification.put("description", messagePushDTO.getmPushContent());//内容
                        notification.put("notification_builder_id", 0);
                        notification.put("notification_basic_style", 4);
                        notification.put("open_type", 3);//(1：打开Url; 2：自定义行为；3：默认打开应用;);
                        //notification.put("url", "http://push.baidu.com");

                        JSONObject jsonCustormCont = new JSONObject();
                        jsonCustormCont.put("messageType", messagePushDTO.getmType());//消息类型
                        notification.put("custom_content", jsonCustormCont);
                        // 4. 设置请求参数，创建请求实例

                        PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
                                .addChannelId(messagePushDTO.getPushToken())
                                .addMsgExpires(new Integer(3600)).   //设置消息的有效时间,单位秒,默认3600*5.
                                        addMessageType(1).        //设置消息类型,0表示透传消息,1表示通知,默认为0.
                                        addMessage(notification.toString()).
                                        addDeviceType(3);
                        requests.add(request);
                    }
                }
            if (requests.size()>0){
                for (PushMsgToSingleDeviceRequest request:requests){
                    pushClient.pushMsgToSingleDevice(request);
                    System.out.println("推送安卓-----------------" + userType);
                }
            }
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
        return 0;
    }

}
