package com.maxrocky.vesta.application.messagePushUtil;

import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;

import java.util.List;

/**
 * Created by zhanghj on 2016/3/17.
 */
public class MessageThread_2 implements Runnable {

    private List<MessagePushDTO> pushIOSDtoUser;
    private List<MessagePushDTO> pushIOSDtoStaff;
    private List<MessagePushDTO> pushAdrDtoUser;
    private List<MessagePushDTO> pushAdrDtoStaff;

    public MessageThread_2(List<MessagePushDTO> pushIOSDtoUser,List<MessagePushDTO> pushIOSDtoStaff,List<MessagePushDTO> pushAdrDtoUser,List<MessagePushDTO> pushAdrDtoStaff){
        this.pushIOSDtoUser = pushIOSDtoUser;
        this.pushIOSDtoStaff=pushIOSDtoStaff;
        this.pushAdrDtoUser = pushAdrDtoUser;
        this.pushAdrDtoStaff = pushAdrDtoStaff;
    }
    @Override
    public void run() {
        try{
            if (pushIOSDtoUser.size() > 0) {
                MessageIosPushUtil_2 userIOSPush = new MessageIosPushUtil_2();
                userIOSPush.messageUserIOSPush(pushIOSDtoUser, "1");
            }
            if (pushIOSDtoUser.size() > 0) {
                MessageIosPushUtil_2 staffIOSPush = new MessageIosPushUtil_2();
                staffIOSPush.messageUserIOSPush(pushIOSDtoStaff, "2");
            }
            if (pushAdrDtoUser.size() > 0) {
                MessageAdrPushUtil_2 userAdrPush = new MessageAdrPushUtil_2();
                userAdrPush.messageUserAdrPush(pushAdrDtoUser, "1");
            }
            if (pushAdrDtoUser.size() > 0) {
                MessageAdrPushUtil_2 staffAdrPush = new MessageAdrPushUtil_2();
                staffAdrPush.messageUserAdrPush(pushAdrDtoStaff, "2");
            }
        }
            catch (Exception e) {
                e.printStackTrace();
            }
    }
}
