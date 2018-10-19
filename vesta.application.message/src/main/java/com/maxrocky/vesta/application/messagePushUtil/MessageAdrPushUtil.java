package com.maxrocky.vesta.application.messagePushUtil;

import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.yun.push.model.PushMsgToSingleDeviceResponse;
import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;
import com.maxrocky.vesta.domain.model.MessageTokenEntity;
import net.sf.json.JSONObject;

/**
 * Created by zhanghj on 2016/3/8.
 */
public class MessageAdrPushUtil {
    public int MessageAdrPush(MessageTokenEntity messageTokenEntity, MessagePushDTO messagePushDto) throws PushClientException,PushServerException {
        String apiKey1="";
        String secretKey1 = "";
//        if (messagePushDto.getmUserType().equals("1")){
//            apiKey1 = AppConfig.USERAPIKEY;
//            secretKey1=AppConfig.USERSECRETKEY;
//        }
//        else if(messagePushDto.getmUserType().equals("2")){
//            apiKey1 = AppConfig.STAFFAPIKEY;
//            secretKey1=AppConfig.STAFFSECRETKEY;
//        }
        apiKey1 = messagePushDto.getApiKey();
        secretKey1 = messagePushDto.getSecretKey();


        int j = 0;
        // 1. get apiKey and secretKey from developer console
        String apiKey =apiKey1 ;//"EkSXHr12UmYlGYPpEXQgeVrn";
        String secretKey =secretKey1; //"113f1689423f7811c06327ea407b896c";

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
//            //创建Android通知
            JSONObject notification = new JSONObject();
            notification.put("title", messagePushDto.getmPushTitle());//消息标题
            notification.put("description",messagePushDto.getmPushContent());//内容
//            notification.put("notification_builder_id", 0);
//            notification.put("notification_basic_style", 4);
            notification.put("open_type", 3);//(1：打开Url; 2：自定义行为；3：默认打开应用;);
            //notification.put("url", "http://push.baidu.com");

            JSONObject jsonCustormCont = new JSONObject();
            if (messagePushDto.getmPushUrl()!= null) {
                String pushUrl = messagePushDto.getmPushUrl();
                jsonCustormCont.put("params", pushUrl); //自定义内容，key-value,多余参数位置***************重点
            }
            jsonCustormCont.put("messageType",messagePushDto.getmType());//消息类型
            notification.put("custom_content", jsonCustormCont);


//            String[] channelIds = new String[messageTokenEntities.size()];
//            for(int i =0;i< messageTokenEntities.size();i++){//将chanled集合转成数组，安卓需要推送循环数组
//                channelIds[i] = messageTokenEntities.get(i).getMessageTokenNum();
//                j++;
//            }
            String[] channelIds = new String[1];
            //将chanled集合转成数组，安卓需要推送循环数组
                channelIds[0] = messageTokenEntity.getMessageTokenNum();
            //String[] channelIds = //{ "3555225666768992151","4180679567486402138","4064697652036298257",};//手机id
//            PushBatchUniMsgRequest request = new PushBatchUniMsgRequest()
            PushMsgToSingleDeviceRequest request = new PushMsgToSingleDeviceRequest()
                    .addChannelId(channelIds[0])
                            //.addMsgExpires(new Integer(600))
                    .addMessageType(1)
                    .addMessage(notification.toString())
                    .addDeviceType(3);//3：Android，4：IOS
            // .addTopicId("Baid123uPush");// 设置类别主题
            // 5. http request
            PushMsgToSingleDeviceResponse response = pushClient.pushMsgToSingleDevice(request);
//                    .pushBatchUniMsg(request);
            // Http请求结果解析打印
            System.out.println(String.format("msgId: %s, sendTime: %d",
                    response.getMsgId(), response.getSendTime()));
            System.out.println("Adr消息推送成功。"+channelIds[0]+"业主类型"+messagePushDto.getmUserType());
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
