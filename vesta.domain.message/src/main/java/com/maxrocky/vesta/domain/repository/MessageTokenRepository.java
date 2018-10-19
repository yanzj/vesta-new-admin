package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.MessageTokenEntity;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/17.
 */
public interface MessageTokenRepository {

    /**
     * 添加新的messagetoken
     * @param messageTokenEntity
     * @return
     */
    public boolean saveMessageToken(MessageTokenEntity messageTokenEntity);


    /**
     * 删除token的参数还不确定
     * @return
     */
    public boolean deleteMessageToken(MessageTokenEntity messageTokenEntity);

    /**
     * 根据userId和phoneType获取messagetoken
     * @param userId
     * @param phoneType
     * @return
     */
    public List<MessageTokenEntity> listMessageToken(String userId, String phoneType);

    /**
     * 通过用户id获取token信息
     * @param userId
     * @return
     */
    public MessageTokenEntity getByUserId(String userId);

    /**
     * 根据tokenId获取token信息
     * @param tokenId
     * @return
     */
    public MessageTokenEntity getById(String tokenId);

    /**
     * 更新token信息
     * @param messageTokenEntity
     * @return
     */
    public boolean updateToken(MessageTokenEntity messageTokenEntity);

    /**
     * 根据用户id获取所有token
     * @param userId
     * @return
     */
    public List<MessageTokenEntity> listTokenByUser(String userId);
}
