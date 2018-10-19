package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.LoginLogEntity;
import com.maxrocky.vesta.domain.model.MessageDepartmentEntity;
import com.maxrocky.vesta.domain.model.MessageManageEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by sunmei on 2016/3/3.
 */
public interface MessageManageRepository {
    /**
     * 初始化列表，属性
     * @return
     */
    List<MessageManageEntity> MessageList(MessageManageEntity messageManageEntity, WebPage webPage);

    /**
     * 录入
     * @param messageManageEntity
     */
    void addMessage(MessageManageEntity messageManageEntity);

    void addMessageDepartment(MessageDepartmentEntity messageDepartmentEntity);

    List<MessageDepartmentEntity> getMessageDepartment(String messageId);

    MessageManageEntity getMessages(String Id);

    void updateMessage(MessageManageEntity messageManageEntity);

    void deleteMessageDepartment(String adId);
}
