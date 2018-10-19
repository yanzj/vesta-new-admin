package com.maxrocky.vesta.application.impl;



import com.maxrocky.vesta.application.inf.MessageDetailService;
import com.maxrocky.vesta.application.DTO.adminDTO.MessageDetailDTO;

import com.maxrocky.vesta.domain.model.MessageDetailEntity;
import com.maxrocky.vesta.domain.model.MessageTargetEntity;
import com.maxrocky.vesta.domain.repository.MessageDetailRepository;
import com.maxrocky.vesta.domain.repository.MessageTargetRepository;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/14.
 */

@Service
public class MessageDetailServiceImpl implements MessageDetailService {


    @Resource
    private MessageDetailRepository messageDetailRepository;

    @Resource
    private MessageTargetRepository messageTargetRepository;


    /**
     * 通过消息详情Id获得消息详情
     * @param messageDetailId
     * @return
     */
    @Override
    public MessageDetailDTO getMessageDetailById(String messageDetailId) {

        MessageDetailEntity messageDetailEntity = messageDetailRepository.getMessageDetailById(messageDetailId);

        MessageDetailDTO messageDetailDTO = new MessageDetailDTO();
        if (messageDetailEntity !=null){
            messageDetailDTO.setMessageDetailId(messageDetailEntity.getMessageDetailId());
            messageDetailDTO.setMessageTitle(messageDetailEntity.getMessageTitle());
            messageDetailDTO.setMessageCreateTime(messageDetailEntity.getMessageCreateTime());
            messageDetailDTO.setMessageContent(messageDetailEntity.getMessageContent());
            messageDetailDTO.setMessageTargetUrl(messageDetailEntity.getMessageTargetUrl());
            messageDetailDTO.setMessageSenderName(messageDetailEntity.getMessageSenderName());
            messageDetailDTO.setMessageType(messageDetailEntity.getMessageType());
            return messageDetailDTO;
        }
        else {
            return null;
        }

    }


    /**
     * 添加新消息
     * DTO要带的参数MessageTitle，MessageContent，MessageTargetUrl,MessageSenderName,MessageType
     * @param messageDetailDTO
     * @return
     */
    @Override
    public String  saveMessageDetail(MessageDetailDTO messageDetailDTO) {
        MessageDetailEntity messageDetailEntity = new MessageDetailEntity();

        String messageDetailId = IdGen.uuid();
        messageDetailEntity.setMessageDetailId(messageDetailId);

        messageDetailEntity.setMessageTitle(messageDetailDTO.getMessageTitle());
        messageDetailEntity.setMessageContent(messageDetailDTO.getMessageContent());
        messageDetailEntity.setMessageTargetUrl(messageDetailDTO.getMessageTargetUrl());
        messageDetailEntity.setMessageCreateTime(DateUtils.getDate());
        messageDetailEntity.setMessageSenderName(messageDetailDTO.getMessageSenderName());
        messageDetailEntity.setMessageType(messageDetailDTO.getMessageType());

        if(messageDetailRepository.saveMessageDetail(messageDetailEntity)){
            return messageDetailId;                 //如果消息添加成功，则返回消息Id
        }else {
            return null;                              //如果消息添加失败，则返回null
        }
    }

    /**
     * 删除消息
     * @param messageDetailId
     * @return
     */
    @Override
    public boolean deleteMessageDetail(String messageDetailId) {

        messageDetailRepository.deleteMessageDetail(messageDetailId);
        List<MessageTargetEntity> messageTargetEntityList = messageTargetRepository.listMessageTargetByMessageDetailId(messageDetailId);
        if (messageTargetEntityList.size()>0){
            for(MessageTargetEntity messageTargetEntity : messageTargetEntityList){
                messageTargetRepository.deleteMessageTarget(messageTargetEntity);
            }
        }
        return true;
    }
}
