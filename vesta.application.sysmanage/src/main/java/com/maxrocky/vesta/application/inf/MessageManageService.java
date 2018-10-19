package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.MessageManageDTO;
import com.maxrocky.vesta.application.DTO.MessageTypeDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by sunmei on 2016/3/3.
 */
public interface MessageManageService {
    /**
     * 初始化列表，属性
     * @return
     */
    List<MessageManageDTO> MessageList(MessageManageDTO messageManageDTO, WebPage webPage);

    /**
     * 录入
     * @param messageManageDTO
     */
    void addMessage(UserPropertyStaffEntity userPropertystaffEntit,MessageManageDTO messageManageDTO);

    /**
     * 消息类型下拉框
     */
    List<MessageTypeDTO> getMessageType();

    List<String> getMessageDepartment(String messageId);


    MessageManageDTO getMessages(String Id);


}
