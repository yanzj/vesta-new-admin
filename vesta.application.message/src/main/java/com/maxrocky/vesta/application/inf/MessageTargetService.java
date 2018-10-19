package com.maxrocky.vesta.application.inf;



import com.maxrocky.vesta.application.DTO.adminDTO.MessageTargetDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/14.
 */
public interface MessageTargetService {


    /**
     * 前台接口----------------------------------------------------------------------------------------
     */
    /**
     * 获取未读消息总数
     * @return
     */
    public int countUnreadMessage(String userId);

    /**
     * 整个模块标为已读接口
     * @param userId
     * @param messageType
     * @return
     */
    public ApiResult readMessageByTitle(String userId, String messageType);

    /**
     * 某条消息标为已读接口
     * @param userId
     * @param messageTargetId
     * @return
     */
    public ApiResult readMessageById(String userId, String messageTargetId)throws GeneralException;

    /**
     * 删除某条消息
     * @param userId
     * @param messageTargetId
     * @return
     * @throws GeneralException
     */
    public ApiResult delMessageById(String userId, String messageTargetId)throws GeneralException;

    /**
     * 获取某人的一级消息列表
     * @param userType  1-业主端，2-员工端
     * @param userId
     * @return
     */
    public ApiResult getFirMessageList(String userId,String userType,Page page) throws GeneralException;

    /**
     * 获取某人的一级消息列表
     * @param userType  1-业主端，2-员工端
     * @param userId
     * @return
     */
    public ApiResult getFirMessageList(String userId,String userType,WebPage webpage) throws GeneralException;
    /**
     * 获取二级消息列表
     * @param userId
     * @param page
     * @return
     */
    public ApiResult getSecMessageList(String userId,Page page)  throws GeneralException ;


    /**
     * 获取员工端消息列表
     * @param userId
     *
     * @param page
     *
     * @return
     * @throws GeneralException
     */
    public ApiResult getStaffMessageList(String userId,Page page)  throws GeneralException ;

    /**
     * 查询消息详情
     * @param messageTargetId
     * @return
     */
    public ApiResult getMessageDetail(String messageTargetId)throws GeneralException ;














    /**
     * 后台接口----------------------------------------------------------------------------------------
     */
    /**
     * 添加消息收件人
     * MessageDetailId，UserId
     * @param messageTargetDTOList
     * @return
     */
    public int saveMessageTarget(List<MessageTargetDTO> messageTargetDTOList);
}
