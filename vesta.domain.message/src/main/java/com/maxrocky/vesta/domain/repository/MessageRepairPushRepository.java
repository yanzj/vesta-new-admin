package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.MessagePushLogEntity;
import com.maxrocky.vesta.domain.model.MessagePushRepairEntity;
import com.maxrocky.vesta.domain.model.MessagePushTargeEntity;
import com.maxrocky.vesta.domain.model.MessagePushUserEntity;

import java.util.List;

/**
 * Created by Magic on 2017/7/13.
 */
public interface MessageRepairPushRepository {
    /**
     * 根据用户idList查询匹配每台app记录的别名
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    List<String> getaAliasByUserId(List<String> userId);

    /**
     * 获取消息推送的标题和内容 1.员工2.经理
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    MessagePushTargeEntity getTargetByType(String type1);

    /**
     * 根据id 修改推送记录（报修单）
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    void updatePush(String repairId, List<String> alias);

    /**
     * Describe:创建接口日志信息(消息推送)
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    void createPlanLog(MessagePushLogEntity messagePushLogEntity);

    /**
     * 根据用户idList查询匹配 登录记录人员设备信息
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    List<MessagePushUserEntity> getaPushUserByUserId(List<String> userId, String type);

    /**
     * 保存保修单消息推送记录实体信息
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    void saveMessagePushRepairEntity(MessagePushRepairEntity pushRepairEntity);

    /**
     * 根据登录记录设备码id查询信息
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    MessagePushUserEntity getMessagePushUser(String id);

    /**
     * 修改登录记录设备码信息
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    void updateMessagePushUser(MessagePushUserEntity messagePushUserEntity);

    /**
     * 保存登录记录设备码信息
     * CreateBy:Magic
     * CreateOn:2017-07-13
     */
    void saveMessagePushUser(MessagePushUserEntity messagePushUserEntity);
}
