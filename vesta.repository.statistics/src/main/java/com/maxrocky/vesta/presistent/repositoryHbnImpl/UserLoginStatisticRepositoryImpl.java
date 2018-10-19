package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.UserLoginBookEntity;
import com.maxrocky.vesta.domain.repository.UserLoginStatisticRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 用户登录统计 持久层实现类
 */
@Repository
public class UserLoginStatisticRepositoryImpl extends HibernateDaoImpl implements UserLoginStatisticRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {

        return sessionFactory.openSession();
    }

    /**
     * 根据用户ID查询 登录信息
     * @param userId
     * @return
     */
    @Override
    public List<UserLoginBookEntity> getLoginByUserId(String userId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" FROM UserLoginBookEntity AS ul where 1=1");
        hql.append(" AND ul.userId = :userId ");
        hql.append(" AND ul.state = :state ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("state", UserLoginBookEntity.STATE_NORMAL);
        query.setParameter("userId", userId);
        List<UserLoginBookEntity> userLoginBookEntityList = query.list();
        return userLoginBookEntityList;
    }
}
