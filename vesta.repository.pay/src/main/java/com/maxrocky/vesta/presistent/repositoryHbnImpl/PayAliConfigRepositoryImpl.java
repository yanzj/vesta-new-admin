package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.repository.PayAliConfigRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Tom on 2016/2/1 15:43.
 * Describe:支付宝配置Repository接口实现类
 */
@Repository
public class PayAliConfigRepositoryImpl implements PayAliConfigRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据域获取支付宝配置
     * CreateBy:Tom
     * CreateOn:2016-02-01 03:45:47
     */
    /*@Override
    public PayAliConfigEntity getByDomain(String domain) {
        String hql = "FROM PayAliConfigEntity WHERE domain = :domain";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("domain", domain);
        PayAliConfigEntity payAliConfigEntity = (PayAliConfigEntity)query.uniqueResult();
        return payAliConfigEntity;
    }*/
}
