package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.repository.CommunityDetailRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by wangyifan on 2016/5/09.
 */
@Repository
public class CommunityDetailRepositoryImpl extends BaseRepositoryImpl implements CommunityDetailRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }



}
