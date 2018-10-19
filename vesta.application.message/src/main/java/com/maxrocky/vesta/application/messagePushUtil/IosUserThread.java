package com.maxrocky.vesta.application.messagePushUtil;

import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;

import java.util.List;

/**
 * Created by zhanghj on 2016/3/17.
 */
public class IosUserThread implements Runnable{

    private List<MessagePushDTO> pushIOSDtoUser;
    public IosUserThread(List<MessagePushDTO> pushIOSDtoUser){

        this.pushIOSDtoUser = pushIOSDtoUser;
    }
    @Override
    public void run() {
        try {
            if (pushIOSDtoUser.size() > 0) {
                MessageIosPushUtil_2 userIOSPush = new MessageIosPushUtil_2();
                userIOSPush.messageUserIOSPush(pushIOSDtoUser, "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
