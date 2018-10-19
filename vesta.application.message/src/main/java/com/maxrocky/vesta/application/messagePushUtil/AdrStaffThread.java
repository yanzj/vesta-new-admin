package com.maxrocky.vesta.application.messagePushUtil;

import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;

import java.util.List;

/**
 * Created by zhanghj on 2016/3/17.
 */
public class AdrStaffThread implements Runnable{

    private List<MessagePushDTO> pushAdrDtoStaff;
    public AdrStaffThread(List<MessagePushDTO> pushAdrDtoStaff){

        this.pushAdrDtoStaff = pushAdrDtoStaff;
    }
    @Override
    public void run() {
        try{
            if (pushAdrDtoStaff.size() > 0) {
                MessageAdrPushUtil_2 staffAdrPush = new MessageAdrPushUtil_2();
                staffAdrPush.messageUserAdrPush(pushAdrDtoStaff, "2");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
