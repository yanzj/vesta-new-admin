package com.maxrocky.vesta.application.messagePushUtil;

import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;

import java.util.List;

/**
 * Created by Magic on 2016/7/27.
 */
public class IOSStaffThread_1 implements Runnable{

    private List<MessagePushDTO> pushAdrDtoStaff;
    public IOSStaffThread_1(List<MessagePushDTO> pushAdrDtoStaff){

        this.pushAdrDtoStaff = pushAdrDtoStaff;
    }
    @Override
    public void run() {
        try{
            if (pushAdrDtoStaff.size() > 0) {
                MessageAdrPushIOSUtil staffAdrPush = new MessageAdrPushIOSUtil();
                staffAdrPush.messageUserAdrPush(pushAdrDtoStaff, "2");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
