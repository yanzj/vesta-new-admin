package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PayConfigEntity;
import com.maxrocky.vesta.domain.model.PayWeChatConfigEntity;
import com.maxrocky.vesta.domain.repository.PayWeChatConfigRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Tom on 2016/2/15 14:53.
 * Describe:微信支付配置Repository接口实现类
 */
@Repository
public class PayWeChatConfigRepositoryImpl implements PayWeChatConfigRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据域获取微信配置
     * CreateBy:Tom
     * CreateOn:2016-2-15 14:55:37
     */
    @Override
    public PayWeChatConfigEntity getByDomain(String domain) {
        String hql = "FROM PayWeChatConfigEntity WHERE domain = :domain";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("domain", domain);
        PayWeChatConfigEntity payWeChatConfigEntity = (PayWeChatConfigEntity)query.uniqueResult();
        return payWeChatConfigEntity;
    }

    /**
     * Describe:根据支付类型Code获取支付回调地址配置
     * CreateBy:WeiYangDong
     * CreateOn:2016-10-11 11:11:37
     */
    public PayConfigEntity getPayConfigByCode(String code){
        Query query = getCurrentSession().createQuery("FROM PayConfigEntity WHERE configCode = :configCode");
        query.setParameter("configCode", code);
        PayConfigEntity payConfigEntity = (PayConfigEntity) query.uniqueResult();
        return payConfigEntity;
    }

}
