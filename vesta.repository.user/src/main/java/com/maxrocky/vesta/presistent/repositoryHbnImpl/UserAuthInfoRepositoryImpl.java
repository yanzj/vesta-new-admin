package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.UserAuthInfoEntity;
import com.maxrocky.vesta.domain.repository.UserAuthInfoRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Tom on 2016/1/21 0:23.
 * Describe:注册信息Repository接口实现类
 */
@Repository
public class UserAuthInfoRepositoryImpl implements UserAuthInfoRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:创建注册信息
     * CreateBy:Tom
     * CreateOn:2016-01-21 12:25:01
     */
    @Override
    public void create(UserAuthInfoEntity userAuthInfoEntity) {
        Session session = getCurrentSession();
        session.save(userAuthInfoEntity);
        session.flush();
    }
}
