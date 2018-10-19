package com.maxrocky.vesta.application.messagePushUtil;

import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;

import java.util.List;

/**
 * Created by zhanghj on 2016/3/28.
 */
public class IosStaffStoreThread implements Runnable{

    private List<MessagePushDTO> pushIOSStoreStaff;
    public IosStaffStoreThread(List<MessagePushDTO> pushIOSStoreStaff){

        this.pushIOSStoreStaff = pushIOSStoreStaff;
    }
    @Override
    public void run() {
        try {
            if (pushIOSStoreStaff.size() > 0) {
                MessageIosPushUtil_2 staffIOSPush = new MessageIosPushUtil_2();
                staffIOSPush.messageUserIOSPush(pushIOSStoreStaff, "4");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
