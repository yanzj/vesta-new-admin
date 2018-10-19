package com.maxrocky.vesta.application.messagePushUtil;

import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;

import java.util.List;

/**
 * Created by zhanghj on 2016/3/28.
 */
public class IosUserStoreThread implements Runnable{


    private List<MessagePushDTO> pushIOSStoreUser;
    public IosUserStoreThread(List<MessagePushDTO> pushIOSStoreUser){

        this.pushIOSStoreUser = pushIOSStoreUser;
    }

    @Override
    public void run() {

        try {
            if (pushIOSStoreUser.size() > 0) {
                MessageIosPushUtil_2 staffIOSPush = new MessageIosPushUtil_2();
                staffIOSPush.messageUserIOSPush(pushIOSStoreUser, "3");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
