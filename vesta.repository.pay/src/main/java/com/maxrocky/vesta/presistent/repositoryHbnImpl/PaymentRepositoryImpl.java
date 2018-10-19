package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PaymentEntity;
import com.maxrocky.vesta.domain.repository.PaymentRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Tom on 2016/1/27 16:02.
 * Describe:支付Repository接口实现类
 */
@Repository
public class PaymentRepositoryImpl implements PaymentRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 创建支付
     */
    @Override
    public void create(PaymentEntity paymentEntity) {
        Session session = getCurrentSession();
        session.save(paymentEntity);
        session.flush();
    }

    /**
     * 根据支付ID查询支付
     */
    @Override
    public PaymentEntity get(String paymentId) {
        return (PaymentEntity)getCurrentSession().get(PaymentEntity.class, paymentId);
    }

    /**
     * 修改支付信息
     */
    @Override
    public void update(PaymentEntity paymentEntity) {
        Session session = getCurrentSession();
        session.update(paymentEntity);
        session.flush();
    }

}
