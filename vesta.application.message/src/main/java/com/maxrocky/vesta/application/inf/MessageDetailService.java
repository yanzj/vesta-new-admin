package com.maxrocky.vesta.application.inf;


import com.maxrocky.vesta.application.DTO.adminDTO.MessageDetailDTO;

/**
 * Created by zhanghj on 2016/1/14.
 */
public interface MessageDetailService {

    /**
     * 通过消息详情Id获得消息详情
     * @param messageDetailId
     * @return
     */
    public MessageDetailDTO getMessageDetailById(String messageDetailId);

    /**
     * 添加新消息
     * DTO要带的参数MessageTitle，MessageContent，MessageTargetUrl,MessageSenderName,MessageType
     * @param messageDetailDTO
     * @return
     */
    public String saveMessageDetail(MessageDetailDTO messageDetailDTO);

    public boolean deleteMessageDetail(String messageDetailId);
}
