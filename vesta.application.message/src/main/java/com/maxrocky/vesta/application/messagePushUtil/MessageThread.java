package com.maxrocky.vesta.application.messagePushUtil;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;
import com.maxrocky.vesta.domain.model.MessageTokenEntity;
import com.maxrocky.vesta.utility.DateUtils;

/**
 * Created by zhanghj on 2016/3/8.
 */
public class MessageThread implements Runnable {

    private MessageTokenEntity  token;

    private MessagePushDTO messagePushDTO;
    private int pushType;     //0-IOS，1-安卓

//    public MessageThread(List<MessageTokenEntity> tokens){
//        this.tokens = tokens;
//    }
//
//    public MessageThread(MessagePushDTO messagePushDTO){
//        this.messagePushDTO=messagePushDTO;
//    }
//
//    public MessageThread(int pushType){
//        this.pushType=pushType;
//    }
    public MessageThread(MessageTokenEntity messageTokenEntity,MessagePushDTO messagePushDTO,int pushType){
        this.token = messageTokenEntity;
        this.messagePushDTO=messagePushDTO;
        this.pushType=pushType;
    }
    public  static int success;
    @Override
    public void run() {
        int i = 0;
        int j = 0;
        if (pushType==0) {
            try {
                MessageIosPushUtil messageIosPushUtil = new MessageIosPushUtil();
//                success = messagePushUitl.MessageIOSPush(tokens,messagePushDTO);
                System.out.println("---------------------------"+DateUtils.getDate());
                success=messageIosPushUtil.MessageIOSPush(token,messagePushDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (pushType==1){
            try {
                MessageAdrPushUtil messageAdrPushUtil = new MessageAdrPushUtil();
//                success =  messagePushUitl.MessageAdrPush(tokens,messagePushDTO);
                System.out.println("---------------------------"+DateUtils.getDate());
                success=messageAdrPushUtil.MessageAdrPush(token,messagePushDTO);
            } catch (PushClientException e) {
                e.printStackTrace();
            } catch (PushServerException e) {
                e.printStackTrace();
            }
        }

    }


}
