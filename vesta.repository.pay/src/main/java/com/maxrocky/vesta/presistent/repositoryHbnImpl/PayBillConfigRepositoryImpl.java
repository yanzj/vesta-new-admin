package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PayBillConfigEntity;
import com.maxrocky.vesta.domain.repository.PayBillConfigRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Tom on 2016/2/23 17:42.
 * Describe:快钱支付配置Repository接口实现类
 */
@Repository
public class PayBillConfigRepositoryImpl implements PayBillConfigRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据域获取快钱配置
     * CreateBy:Tom
     * CreateOn:2016-2-23 17:43:49
     */
    @Override
    public PayBillConfigEntity getByDomain(String domain) {
        String hql = "FROM PayBillConfigEntity WHERE domain = :domain";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("domain", domain);
        PayBillConfigEntity payBillConfigEntity = (PayBillConfigEntity)query.uniqueResult();
        return payBillConfigEntity;
    }
}
