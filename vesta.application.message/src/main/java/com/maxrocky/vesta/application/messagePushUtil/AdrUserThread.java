package com.maxrocky.vesta.application.messagePushUtil;

import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;

import java.util.List;

/**
 * Created by zhanghj on 2016/3/17.
 */
public class AdrUserThread implements Runnable {
    private List<MessagePushDTO> pushAdrDtoUser;
    public AdrUserThread(List<MessagePushDTO> pushAdrDtoUser){

        this.pushAdrDtoUser = pushAdrDtoUser;
    }
    @Override
    public void run() {
        try {
            if (pushAdrDtoUser.size() > 0) {
                MessageAdrPushUtil_2 userAdrPush = new MessageAdrPushUtil_2();
                userAdrPush.messageUserAdrPush(pushAdrDtoUser, "1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
