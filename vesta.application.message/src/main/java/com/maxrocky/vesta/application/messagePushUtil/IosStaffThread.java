package com.maxrocky.vesta.application.messagePushUtil;

import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;

import java.util.List;

/**
 * Created by zhanghj on 2016/3/17.
 */
public class IosStaffThread implements Runnable{

    private List<MessagePushDTO> pushIOSDtoStaff;
    public IosStaffThread(List<MessagePushDTO> pushIOSDtoStaff){

        this.pushIOSDtoStaff = pushIOSDtoStaff;
    }
    @Override
    public void run() {
        try {
            if (pushIOSDtoStaff.size() > 0) {
                MessageIosPushUtil_2 staffIOSPush = new MessageIosPushUtil_2();
                staffIOSPush.messageUserIOSPush(pushIOSDtoStaff, "2");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
