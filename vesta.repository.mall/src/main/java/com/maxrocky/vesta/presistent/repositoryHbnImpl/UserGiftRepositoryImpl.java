package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.IntegralRuleEntity;
import com.maxrocky.vesta.domain.model.UserGiftEntity;
import com.maxrocky.vesta.domain.repository.UserGiftRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
@Repository
public class UserGiftRepositoryImpl implements UserGiftRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserGiftEntity getUserGift() {
        String hql = " from UserGiftEntity";
        Query query = getCurrentSession().createQuery(hql);
        UserGiftEntity userGift = (UserGiftEntity) query.uniqueResult();
        return userGift;
    }

    @Override
    public void addUserGify(UserGiftEntity u) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(u);
        session.flush();
        session.close();
    }
}
