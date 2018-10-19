package com.maxrocky.vesta.domain.repository;


import com.maxrocky.vesta.domain.model.MessageDetailEntity;

/**
 * Created by zhanghj on 2016/1/14.
 */
public interface MessageDetailRepository {

    /**
     * 查询消息详情
     * @param messageDetailId
     * @return
     */
    public MessageDetailEntity getMessageDetailById(String messageDetailId);


    /**
     * 添加新消息
     * @param messageDetailEntity
     * @return
     */
    public boolean saveMessageDetail(MessageDetailEntity messageDetailEntity);

    /**
     * 删除MessageDetail
     * @param messageDetailId
     * @return
     */
    public boolean deleteMessageDetail(String messageDetailId);
}
