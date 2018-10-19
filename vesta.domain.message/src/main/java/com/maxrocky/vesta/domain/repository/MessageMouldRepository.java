package com.maxrocky.vesta.domain.repository;


import com.maxrocky.vesta.domain.model.MessageMouldEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;
import com.maxrocky.vesta.utility.PageInfo;

    import java.util.List;

    /**
     * Created by zhanghj on 2016/1/15.
     */
    public interface MessageMouldRepository {

        /**
         * 通过传进的用户类型，调用模块，该模块的状态查找对应模板
         * @param messageUserType
     * @param messageType
     * @param messageTypeState
     * @return
     */
    public MessageMouldEntity getMessageMould(String messageUserType, String messageType, String messageTypeState);

    /**
     * 获得业主端或者员工端所有消息模板
     * @return
     */
    public List<MessageMouldEntity> listMessageMould(String userType,Page page);


        /**
         * 获得业主端或者员工端所有消息模板
         * @return
         */
        public List<MessageMouldEntity> listMessageMould(String userType,WebPage webpage);
}
