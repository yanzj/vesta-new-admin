package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.UserIntegralRuleEntity;
import com.maxrocky.vesta.domain.repository.UserIntegralRuleRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/11.
 */
@Repository
public class UserIntegralRuleRepositoryImpl implements UserIntegralRuleRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void AddUserIntegral(UserIntegralRuleEntity user) {
        Session session = getCurrentSession();
        session.save(user);
        session.flush();
    }

    @Override
    public void UpdateUserIntegral(UserIntegralRuleEntity user) {
        Session session = getCurrentSession();
        session.update(user);
        session.flush();
    }

    @Override
    public UserIntegralRuleEntity get(String userId,String appId) {
        String hql = "from UserIntegralRuleEntity where userId=:userId and weChatAppId=:appId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        query.setParameter("appId",appId);
        UserIntegralRuleEntity t = (UserIntegralRuleEntity) query.uniqueResult();
        return t;
    }

}
