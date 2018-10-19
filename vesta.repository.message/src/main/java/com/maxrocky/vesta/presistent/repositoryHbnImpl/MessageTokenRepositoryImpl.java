package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.MessageTokenEntity;
import com.maxrocky.vesta.domain.repository.MessageTokenRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/17.
 */
@Repository
public class MessageTokenRepositoryImpl implements MessageTokenRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.openSession();
    }

    /**
     * 保存messageToken
     * @param messageTokenEntity
     * @return
     */
    @Override
    public boolean saveMessageToken(MessageTokenEntity messageTokenEntity) {
        Session session = getCurrentSession();
        session.save(messageTokenEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 根据userid和phoneType获取messagetoken列表
     * @param userId
     * @param phoneType
     * @return
     */
    @Override
    public List<MessageTokenEntity> listMessageToken(String userId, String phoneType) {
        String hql = "from MessageTokenEntity as o where o.userId = '"+userId+"' and o.mobileType = '"+phoneType+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<MessageTokenEntity> messageTokenEntities = query.list();
        return messageTokenEntities;
    }

    /**
     * 删除token。由于参数未确定，先不写
     * @return
     */
    @Override
    public boolean deleteMessageToken(MessageTokenEntity messageTokenEntity) {
        Session session = getCurrentSession();
        session.delete(messageTokenEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 根据用户id获取token信息
     * @param userId
     * @return
     */
    @Override
    public MessageTokenEntity getByUserId(String userId) {
        String hql = "from MessageTokenEntity as o where o.userId = :userId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        if (query.list().size()>0) {
            return (MessageTokenEntity)query.list().get(0);
        }
        return null;
    }

    /**
     * 更新token信息
     * @param messageTokenEntity
     * @return
     */
    @Override
    public boolean updateToken(MessageTokenEntity messageTokenEntity) {
        MessageTokenEntity messageTokenEntity1 = getById(messageTokenEntity.getMessageTokenId());
        if (messageTokenEntity1!=null){
            Session session = getCurrentSession();
            session.update(messageTokenEntity);
            session.flush();
            session.close();
            return true;
        }
        return false;
    }

    /**
     * 根据tokenid获取token信息
     * @param tokenId
     * @return
     */
    @Override
    public MessageTokenEntity getById(String tokenId) {
        String hql = "from MessageTokenEntity as o where o.messageTokenId = :messageTokenId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("messageTokenId",tokenId);
        if (query.list().size()>0) {
            return (MessageTokenEntity)query.list().get(0);
        }
        return null;
    }

    /**
     * 根据用户id获取所有token
     * @param userId
     * @return
     */
    @Override
    public List<MessageTokenEntity> listTokenByUser(String userId) {
        String hql = "from MessageTokenEntity as o where o.userId = :userId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        List<MessageTokenEntity> messageTokenEntities = query.list();
        return messageTokenEntities;
    }
}
