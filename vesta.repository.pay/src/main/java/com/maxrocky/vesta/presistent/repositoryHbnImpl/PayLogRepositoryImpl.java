package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PayLogEntity;
import com.maxrocky.vesta.domain.repository.PayLogRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Tom on 2016/1/27 16:17.
 * Describe:支付日志Repository实现类
 */
@Repository
public class PayLogRepositoryImpl implements PayLogRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 创建支付日志
     */
    @Override
    public void create(PayLogEntity payLogEntity) {
        Session session = getCurrentSession();
        session.save(payLogEntity);
        session.flush();
    }
}
