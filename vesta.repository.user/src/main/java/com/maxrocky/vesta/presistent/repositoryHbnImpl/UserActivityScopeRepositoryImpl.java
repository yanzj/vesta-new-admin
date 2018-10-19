package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.AccountCRMEntity;
import com.maxrocky.vesta.domain.model.UserActivityScopeEntity;
import com.maxrocky.vesta.domain.repository.UserActivityScopeRepository;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by luxinxin on 2016/7/21.
 */
@Repository
public class UserActivityScopeRepositoryImpl implements UserActivityScopeRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    @Override
    public UserActivityScopeEntity get(String userId, String activityId) {
        String hql="FROM UserActivityScopeEntity WHERE 1=1 ";
        if(!StringUtil.isEmpty(userId)){
            hql+=" and userId='"+userId+"'";
        }
        if(!StringUtil.isEmpty(activityId)){
            hql+=" and activityId='"+activityId+"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<UserActivityScopeEntity> accountCRMList=query.list();
        if(accountCRMList.size()>0){
            return accountCRMList.get(0);
        }
        return null;
    }

    @Override
    public void create(UserActivityScopeEntity userActivityScopeEntity) {
        Session session = getCurrentSession();
        session.save(userActivityScopeEntity);
        session.flush();
    }

    @Override
    public void update(UserActivityScopeEntity userActivityScopeEntity) {
        Session session = getCurrentSession();
        session.update(userActivityScopeEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<UserActivityScopeEntity> get() {
        String hql="FROM UserActivityScopeEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<UserActivityScopeEntity> userActivityScopeList=query.list();
        return userActivityScopeList;
    }
}
