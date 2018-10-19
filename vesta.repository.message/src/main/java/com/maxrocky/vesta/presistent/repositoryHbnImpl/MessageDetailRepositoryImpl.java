package com.maxrocky.vesta.presistent.repositoryHbnImpl;


import com.maxrocky.vesta.domain.model.MessageDetailEntity;
import com.maxrocky.vesta.domain.repository.MessageDetailRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by zhanghj on 2016/1/14.
 */

@Repository
public class MessageDetailRepositoryImpl implements MessageDetailRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.openSession();
    }


    /**
     * 添加新消息
     * @param messageDetailEntity
     * @return
     */
    @Override
    public boolean saveMessageDetail(MessageDetailEntity messageDetailEntity) {
        Session session = getCurrentSession();
        session.save(messageDetailEntity);
        session.flush();
        session.close();
        return true;
    }


    /**
     * 根据消息Id查询消息详情
     * @param messageDetailId
     * @return
     */
    @Override
    public MessageDetailEntity getMessageDetailById(String messageDetailId) {
        MessageDetailEntity messageDetailEntity = new MessageDetailEntity();
        String hql = "from MessageDetailEntity as o where o.messageDetailId = '"+messageDetailId+"'";
        Query query = getCurrentSession().createQuery(hql);
        if (query.list().size()>0){
            messageDetailEntity = (MessageDetailEntity)query.list().get(0);
        }
        else {
            messageDetailEntity = null;
        }
        return messageDetailEntity;
    }

    /**
     * 删除MessageDetail
     * @param messageDetailId
     * @return
     */
    @Override
    public boolean deleteMessageDetail(String messageDetailId) {
        MessageDetailEntity messageDetailEntity = getMessageDetailById(messageDetailId);
        if (messageDetailEntity !=null){
            Session session = getCurrentSession();
            session.delete(messageDetailEntity);
            session.flush();
            session.close();
            return true;
        }
        return false;
    }
}
