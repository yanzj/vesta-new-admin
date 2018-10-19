package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.adminDTO.MessageInsertDTO;
import com.maxrocky.vesta.application.impl.MessageInsertServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/2/25.
 */
public class MessageDemo {

    private MessageInsertService messageInsertService;
    public void addMessage(){
        MessageInsertDTO messageInsertDTO = new MessageInsertDTO();
        messageInsertDTO.setMessageTitle("123");
        messageInsertDTO.setMessageUserType("1");
        messageInsertDTO.setMessageType("1");
        messageInsertDTO.setMessageTypeState("1");
        messageInsertDTO.setMessageUrl("123123123");
        List<String> users = new ArrayList<>();
        users.add("secret");
        messageInsertService.InsertMessage(messageInsertDTO,users);
    }

    public static void main(String[] args) {
        MessageInsertService messageInsertService = new MessageInsertServiceImpl();
        MessageInsertDTO messageInsertDTO = new MessageInsertDTO();
        messageInsertDTO.setMessageTitle("3月7日行政部部门例会");
        messageInsertDTO.setMessageContent("请行政部的各位同事7日下午三点准时到大会议室开会。");
        messageInsertDTO.setMessageType("6");
        messageInsertDTO.setMessageTypeState("1");
        messageInsertDTO.setMessageUrl("");
        messageInsertDTO.setMessageSenderName("行政部");
        messageInsertDTO.setMessageUserType("2");
        List<String> users = new ArrayList<>();
        users.add("qianwensheng");
        users.add("wangzhonghai");
        messageInsertService.InsertMessage(messageInsertDTO,users);

    }
}
