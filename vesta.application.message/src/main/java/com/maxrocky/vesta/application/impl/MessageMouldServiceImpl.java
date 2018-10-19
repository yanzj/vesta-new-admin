package com.maxrocky.vesta.application.impl;


import com.maxrocky.vesta.application.DTO.adminDTO.MessageMouldDTO;
import com.maxrocky.vesta.application.inf.MessageMouldService;

import com.maxrocky.vesta.domain.model.MessageMouldEntity;
import com.maxrocky.vesta.domain.repository.MessageMouldRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/15.
 */
@Service
public class MessageMouldServiceImpl implements MessageMouldService {

    @Resource
    private MessageMouldRepository messageMouldRepository;

    /**
     * 通过传进的用户类型，调用模块，该模块的状态查找对应模板
     * @param messageUserType   用户类型业主员工
     * @param messageType       消息类型
     * @param messageTypeState  消息类型的状态
     * @return
     */
    @Override
    public MessageMouldDTO getMessageMouldDTO(String messageUserType, String messageType, String messageTypeState) {
        MessageMouldEntity messageMouldEntity = messageMouldRepository.getMessageMould(messageUserType,messageType,messageTypeState);
        MessageMouldDTO messageMouldDTO = new MessageMouldDTO();
        if (messageMouldEntity!=null) {
            messageMouldDTO.setMessageMouldId(messageMouldEntity.getMessageMouldId());
            messageMouldDTO.setMessageUserType(messageMouldEntity.getMessageUserType());
            messageMouldDTO.setMessageUserTypeRemark(messageMouldEntity.getMessageUserTypeRemark());
            messageMouldDTO.setMessageType(messageMouldEntity.getMessageType());
            messageMouldDTO.setMessageTypeRemark(messageMouldEntity.getMessageTypeRemark());
            messageMouldDTO.setMessageTypeState(messageMouldEntity.getMessageTypeState());
            messageMouldDTO.setMessageTypeStateRemark(messageMouldEntity.getMessageTypeStateRemark());
            messageMouldDTO.setMessageMouldTitle(messageMouldEntity.getMessageMouldTitle());
            messageMouldDTO.setMessageMouldContent(messageMouldEntity.getMessageMouldContent());
            messageMouldDTO.setMessageMouldUrl(messageMouldEntity.getMessageMouldUrl());
            messageMouldDTO.setMessageMouldRemark(messageMouldEntity.getMessageMouldRemark());
        }
        return messageMouldDTO;
    }

    /**
     * 获得所有消息模板
     * @return
     */
    @Override
    public List<MessageMouldDTO> listMessageMould(String userType,Page page) {
        List<MessageMouldEntity> messageMouldEntities = messageMouldRepository.listMessageMould(userType, page);
        List<MessageMouldDTO> messageMouldDTOs = new ArrayList<>();
        if (messageMouldEntities.size()>0){
            for (MessageMouldEntity messageMouldEntity : messageMouldEntities){
                MessageMouldDTO messageMouldDTO = new MessageMouldDTO();

                messageMouldDTO.setMessageMouldId(messageMouldEntity.getMessageMouldId());
                messageMouldDTO.setMessageUserType(messageMouldEntity.getMessageUserType());
                messageMouldDTO.setMessageUserTypeRemark(messageMouldEntity.getMessageUserTypeRemark());
                messageMouldDTO.setMessageType(messageMouldEntity.getMessageType());
                messageMouldDTO.setMessageTypeRemark(messageMouldEntity.getMessageTypeRemark());
                messageMouldDTO.setMessageTypeState(messageMouldEntity.getMessageTypeState());
                messageMouldDTO.setMessageTypeStateRemark(messageMouldEntity.getMessageTypeStateRemark());
                messageMouldDTO.setMessageMouldTitle(messageMouldEntity.getMessageMouldTitle());
                messageMouldDTO.setMessageMouldContent(messageMouldEntity.getMessageMouldContent());
                messageMouldDTO.setMessageMouldUrl(messageMouldEntity.getMessageMouldUrl());
                messageMouldDTO.setMessageMouldRemark(messageMouldEntity.getMessageMouldRemark());

                messageMouldDTOs.add(messageMouldDTO);

            }
        }

        return messageMouldDTOs;
    }

    /**
     * 获得所有消息模板
     * @return
     */
    @Override
    public List<MessageMouldDTO> listMessageMould(String userType,WebPage webpage) {
        List<MessageMouldEntity> messageMouldEntities = messageMouldRepository.listMessageMould(userType,webpage);
        List<MessageMouldDTO> messageMouldDTOs = new ArrayList<>();
        if (messageMouldEntities.size()>0){
            for (MessageMouldEntity messageMouldEntity : messageMouldEntities){
                MessageMouldDTO messageMouldDTO = new MessageMouldDTO();

                messageMouldDTO.setMessageMouldId(messageMouldEntity.getMessageMouldId());
                messageMouldDTO.setMessageUserType(messageMouldEntity.getMessageUserType());
                messageMouldDTO.setMessageUserTypeRemark(messageMouldEntity.getMessageUserTypeRemark());
                messageMouldDTO.setMessageType(messageMouldEntity.getMessageType());
                messageMouldDTO.setMessageTypeRemark(messageMouldEntity.getMessageTypeRemark());
                messageMouldDTO.setMessageTypeState(messageMouldEntity.getMessageTypeState());
                messageMouldDTO.setMessageTypeStateRemark(messageMouldEntity.getMessageTypeStateRemark());
                messageMouldDTO.setMessageMouldTitle(messageMouldEntity.getMessageMouldTitle());
                messageMouldDTO.setMessageMouldContent(messageMouldEntity.getMessageMouldContent());
                messageMouldDTO.setMessageMouldUrl(messageMouldEntity.getMessageMouldUrl());
                messageMouldDTO.setMessageMouldRemark(messageMouldEntity.getMessageMouldRemark());

                messageMouldDTOs.add(messageMouldDTO);

            }
        }

        return messageMouldDTOs;
    }
}
