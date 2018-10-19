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
 * Created by Magic on 2016/7/27.
 */
public class MessageAdrPushIOSUtil {

    public int messageUserAdrPush(List<MessagePushDTO> messagePushDtos,String userType) throws  Exception{

        String apiKey=AppConfig.IOSAPIKEY;
        String secretKey=AppConfig.IOSRSECRETKEY;
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
                    JSONObject jsonCustorm = new JSONObject();
                    jsonCustorm.put("alert", messagePushDTO.getmPushContent());//消息类型

                    jsonCustorm.put("badge", 0);//角标
                    jsonCustorm.put("sound","default");
                    notification.put("aps", jsonCustorm);
                    notification.put("messageType", messagePushDTO.getmType());
                    notification.put("params", messagePushDTO.getmPushID());
                    // 4. 设置请求参数，创建请求实例

                    PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
                            .addChannelId(messagePushDTO.getPushToken())
                            .addMsgExpires(new Integer(3600)).   //设置消息的有效时间,单位秒,默认3600*5.
                                    addMessageType(1).        //设置消息类型,0表示透传消息,1表示通知,默认为0.
                                    addMessage(notification.toString()).
                                    addDeployStatus(1).
                                    addDeviceType(4);
                    requests.add(request);
                }
            }
            if (requests.size()>0){
                for (PushMsgToSingleDeviceRequest request:requests){
                    pushClient.pushMsgToSingleDevice(request);
                    System.out.println("推送IOS-----------------" + request.getChannelId());
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
