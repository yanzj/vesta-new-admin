package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.TransLogEntity;
import com.maxrocky.vesta.domain.repository.TransLogRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by JillChen on 2016/1/26.
 */
@Repository
public class TransLogRepositoryImpl implements TransLogRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void save(TransLogEntity s) {
        Session session = getCurrentSession();
        session.save(s);
        session.flush();
    }

    @Override
    public void update(TransLogEntity transLogEntity) {
        Session session = getCurrentSession();
        session.update(transLogEntity);
        session.flush();
    }
}
