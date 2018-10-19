package com.maxrocky.vesta.application.inf;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.maxrocky.vesta.application.DTO.adminDTO.MessagePushDTO;
import com.maxrocky.vesta.domain.model.MessageTokenEntity;

import java.util.List;

/**
 * Created by zhanghj on 2016/3/4.
 */
public interface MessagePushUitl {

    /**
     * 安卓消息推送
     * @param messageTokenEntities
     * @param messagePushDTO
     * @return
     */
    public int MessageAdrPush(List<MessageTokenEntity> messageTokenEntities,MessagePushDTO messagePushDTO) throws PushClientException,PushServerException;

    /**
     * IOS消息推送
     * @param messageTokenEntities
     * @param messagePushDTO
     * @return
     */
    public int MessageIOSPush(List<MessageTokenEntity> messageTokenEntities,MessagePushDTO messagePushDTO) throws Exception;
}
