package com.maxrocky.vesta.application.inf;


import com.maxrocky.vesta.application.DTO.adminDTO.MessageMouldDTO;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/15.
 */
public interface MessageMouldService {


    /**
     * 通过传进的用户类型，调用模块，该模块的状态查找对应模板
     * @param messageUserType
     * @param messageType
     * @param messageTypeState
     * @return
     */
        public MessageMouldDTO getMessageMouldDTO(String messageUserType, String messageType, String messageTypeState);

    /**
     * 获得业主端或者员工端所有消息模板
     * @return
     */
        public List<MessageMouldDTO> listMessageMould(String userType,Page page);


    /**
     * 获得业主端或者员工端所有消息模板
     * @return
     */
    public List<MessageMouldDTO> listMessageMould(String userType,WebPage webpage);
}
