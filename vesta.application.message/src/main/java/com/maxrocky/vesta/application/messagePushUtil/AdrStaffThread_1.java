package com.maxrocky.vesta.application.messagePushUtil;

import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;

import java.util.List;

/**
 * Created by Magic on 2016/7/27.
 */
public class AdrStaffThread_1 implements Runnable{
    private List<MessagePushDTO> pushAdrDtoStaff;
    public AdrStaffThread_1(List<MessagePushDTO> pushAdrDtoStaff){

        this.pushAdrDtoStaff = pushAdrDtoStaff;
    }
    @Override
    public void run() {
        try{
            if (pushAdrDtoStaff.size() > 0) {
                MessageAdrPushADUtil staffAdrPush = new MessageAdrPushADUtil();
                staffAdrPush.messageUserAdrPush(pushAdrDtoStaff, "2");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
