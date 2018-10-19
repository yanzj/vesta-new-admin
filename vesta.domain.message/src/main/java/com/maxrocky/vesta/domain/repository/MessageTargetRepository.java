package com.maxrocky.vesta.domain.repository;




import com.maxrocky.vesta.domain.model.MessageTargetEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;
import com.maxrocky.vesta.utility.PageInfo;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/13.
 */
public interface MessageTargetRepository {

    /**
     * 获取用户所有未读消息数量
     * @param userId
     * @return
     */
    public int countUnreadMessage(String userId);

    /**
     * 获取某条消息的所有收件信息
     * @param messageDetailId
     * @return
     */
    public List<MessageTargetEntity> listMessageTargetByMessageDetailId(String messageDetailId);

    /**
     * 根据模块和用户Id获取某个模块的未读消息数量
     * @param messageType
     * @param userId
     * @return
     */
    public int countUnreadMessageByTitleAndUser(String messageType, String userId);


    /**
     * 分页获得该模块下所有消息
     * @param userId
     * @param page
     * @return
     */
    public List<MessageTargetEntity>  listMessageByTargetPage(String userId,Page page);

    public List<MessageTargetEntity>  listMessageByPage(String userId, Page page);

    /**
     * 获得该模块下所有消息
     * @param userId
     * @param messageType
     * @return
     */

    public List<MessageTargetEntity> listMessageByTarget(String userId, String messageType);

    /**
     * 获取用户在该模块下最新一条消息
     * @param userId
     * @param messageType
     * @return
     */
    public MessageTargetEntity getMessageByTarget(String userId,String messageType);
    /**
     * 更新messageTarget
     * @param messageTargetEntity
     * @return
     */
    public boolean updateMessageTarget(MessageTargetEntity messageTargetEntity);

    /**
     * 根据消息接收信息Id查询接收信息
     * @param messageTargetId
     * @return
     */
    public MessageTargetEntity getMessageTarget(String messageTargetId);


    /**
     * 添加消息收件人
     * @param messageTargetEntity
     * @return
     */
    public boolean saveMessageTarget(MessageTargetEntity messageTargetEntity);

    /**
     * 批量插入消息信息
     * @param messageTargetEntities
     * @return
     */
    public boolean saveListTarget(List<MessageTargetEntity> messageTargetEntities)throws Exception;

    /**
     * 批量更新目标
     * @param messageTargetEntities
     * @return
     * @throws Exception
     */
    public boolean updateListTarget(List<MessageTargetEntity> messageTargetEntities)throws Exception;

    /**
     * 删除消息收件人
     * @param messageTargetEntity
     * @return
     */
    public boolean deleteMessageTarget(MessageTargetEntity messageTargetEntity);

    /**
     * 获取时间段内消息
     * @param beforeTime
     * @param Time
     * @return
     */
    public List<MessageTargetEntity> getPushTarget(String beforeTime,String Time);

    public List<Object> getPushDto(String beforeTime,String Time);

    public List<Object> getPushDto(String beforeTime,String endTime,WebPage webPage);

    public int getPushCout(String phoneid);

    public void updateIdRepair()throws Exception;

    public void updateIdRectify()throws Exception;

}
