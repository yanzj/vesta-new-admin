package com.maxrocky.vesta.application.impl;



import com.maxrocky.vesta.application.DTO.adminDTO.MessageDetailDTO;
import com.maxrocky.vesta.application.DTO.adminDTO.MessageTargetDTO;
import com.maxrocky.vesta.application.inf.MessageDetailService;
import com.maxrocky.vesta.application.inf.MessageInsertService;
import com.maxrocky.vesta.application.DTO.adminDTO.MessageInsertDTO;
import com.maxrocky.vesta.application.DTO.adminDTO.MessageMouldDTO;
import com.maxrocky.vesta.application.inf.MessageMouldService;
import com.maxrocky.vesta.application.inf.MessageTargetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/15.
 */
@Service
public class MessageInsertServiceImpl implements MessageInsertService {

    @Autowired
    private MessageMouldService messageMouldService;

    @Autowired
    private MessageDetailService messageDetailService;

    @Autowired
    private MessageTargetService messageTargetService;

    @Override
    public boolean InsertMessage(MessageInsertDTO messageInsertDTO, List<String> users) {

        if (messageInsertDTO==null){
            return false;
        }

        //判断传入参数，如果不合规定，直接运行结束，返回失败
        if (messageInsertDTO.getMessageUserType()==null||messageInsertDTO.getMessageType()==null||messageInsertDTO.getMessageTypeState()==null){
            return false;
        }
        //判断消息收件人，如果为0，则运行结束返回失败
        if (users.size()==0){
            return false;
        }
        if (messageInsertDTO.getMessageUrl()==null){
            return  false;
        }

        //判断必要参数存在时，查询消息模板
        MessageMouldDTO messageMouldDTO = messageMouldService.getMessageMouldDTO(messageInsertDTO.getMessageUserType(),messageInsertDTO.getMessageType(),messageInsertDTO.getMessageTypeState());
        //判断传入参数是否准确能够查出模板，如果不能直接运行结束，返回失败
        if (messageMouldDTO==null||messageMouldDTO.getMessageMouldId()==null){
            return false;
        }
        MessageDetailDTO messageDetailDTO = new MessageDetailDTO();

        //定义消息标题
        if (messageInsertDTO.getMessageTitle()!=null){
            //如果传入消息标题，则按传入标题为准
            String  messageTitle = messageMouldDTO.getMessageMouldTitle().replace("XXXXXXXX",messageInsertDTO.getMessageTitle());
            messageDetailDTO.setMessageTitle(messageTitle);
        }else {
            //如果没有传入消息标题,则按模板消息标题为准
            messageDetailDTO.setMessageTitle(messageMouldDTO.getMessageTypeRemark());
        }
        //定义消息内容
        if (messageInsertDTO.getMessageContent()!=null){
            //如果传入了消息内容，则消息内容按传入内容为准
            messageDetailDTO.setMessageContent(messageInsertDTO.getMessageContent());
        }else {
            //如果没有传入消息内容，则消息内容按模板内容为准
            messageDetailDTO.setMessageContent(messageMouldDTO.getMessageMouldContent());
        }
        //定义消息跳转URL
        if (messageInsertDTO.getMessageUrl()!=null){
            //如果传入了消息跳转url，则跳转url按传入url为准
            messageDetailDTO.setMessageTargetUrl(messageInsertDTO.getMessageUrl());
        }
        else {
            //如果没传入跳转URL，则跳转url按模板为准
            messageDetailDTO.setMessageTargetUrl(messageMouldDTO.getMessageMouldUrl());
        }
        //定义消息发送人
        if(messageInsertDTO.getMessageSenderName()!=null){
            //如果传入了消息发送人，则消息发送人按传入为准
            messageDetailDTO.setMessageSenderName(messageInsertDTO.getMessageSenderName());
        }
        //定义消息模块
        messageDetailDTO.setMessageType(messageInsertDTO.getMessageType());
        //保存消息,并接受返回的新消息Id
        String messageDetailId = messageDetailService.saveMessageDetail(messageDetailDTO);


        //添加消息接收人集合
        List<MessageTargetDTO> messageTargetDTOs = new ArrayList<>();
        for (String userid :users){
            MessageTargetDTO messageTargetDTO = new MessageTargetDTO();
            messageTargetDTO.setMessageDetailId(messageDetailId);
            messageTargetDTO.setUserId(userid);
            messageTargetDTO.setMessageType(messageInsertDTO.getMessageType());
            messageTargetDTO.setUserType(messageMouldDTO.getMessageUserType());
            messageTargetDTOs.add(messageTargetDTO);
            if (messageInsertDTO.getIsPush()!=null) {
                if (messageInsertDTO.getIsPush().equals("1")) {
                    messageTargetDTO.setIsPush("1");
                }
            }
        }
        int TargetNum = 0;//初始化添加成功人数
        //如果消息接收人为0，则运行结束，返回失败
        if (messageTargetDTOs.size()==0){
            return false;
        }
        else {
            TargetNum =  messageTargetService.saveMessageTarget(messageTargetDTOs);
        }
        if(TargetNum>0){//如果消息接收人数大于0，运行结束，添加成功
            return true;
        }
        else {
            //如果消息接收人数等于0，运行结束，添加失败,删除添加的MessageDetail
            messageDetailService.deleteMessageDetail(messageDetailId);
            return false;
        }
    }
}
