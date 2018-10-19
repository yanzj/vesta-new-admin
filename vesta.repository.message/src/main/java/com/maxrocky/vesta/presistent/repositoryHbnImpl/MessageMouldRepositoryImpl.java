package com.maxrocky.vesta.presistent.repositoryHbnImpl;


import com.maxrocky.vesta.domain.model.MessageMouldEntity;
import com.maxrocky.vesta.domain.repository.MessageMouldRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;
import com.maxrocky.vesta.utility.PageInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/15.
 */

@Repository
public class MessageMouldRepositoryImpl extends HibernateDaoImpl implements MessageMouldRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.openSession();
    }

    /**
     * 通过传进的用户类型，调用模块，该模块的状态查找对应模板
     * @param messageUserType
     * @param messageType
     * @param messageTypeState
     * @return
     */
    @Override
    public MessageMouldEntity getMessageMould(String messageUserType, String messageType, String messageTypeState) {

        String hql = "from MessageMouldEntity as o where o.messageUserType = '"+messageUserType+"' and o.messageType = '"+messageType+"' and o.messageTypeState = '"+messageTypeState+"'";

        Query query = getCurrentSession().createQuery(hql);
        if (query.list().size()>0){
            MessageMouldEntity messageMouldEntity = (MessageMouldEntity)query.list().get(0);
            return messageMouldEntity;
        }
        else {
            return null;
        }
    }

    @Override
    public List<MessageMouldEntity> listMessageMould(String userType,Page page) {
        String hql = "from MessageMouldEntity as o where o.messageUserType = '"+userType+"'";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<MessageMouldEntity> messageMouldEntities = query.list();
        return messageMouldEntities;
    }

    @Override
    public List<MessageMouldEntity> listMessageMould(String userType, WebPage webPage) {
        String hql = "from MessageMouldEntity as o where o.messageUserType = '"+userType+"'";

        List<Object> params = new ArrayList<Object>();

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }

        List<MessageMouldEntity> messageMouldEntities = (List<MessageMouldEntity>)getHibernateTemplate().find(hql.toString());
        return messageMouldEntities;
    }
}
