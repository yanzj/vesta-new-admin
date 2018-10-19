package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.IntegralRuleModelEntity;
import com.maxrocky.vesta.domain.model.SellerCollectEntity;
import com.maxrocky.vesta.domain.model.SellerPictureEntity;
import com.maxrocky.vesta.domain.repository.IntegralRuleModelRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 规则模块管理
 */

@Repository
public class IntegralRuleModelRepositoryImpl implements IntegralRuleModelRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<IntegralRuleModelEntity> getIntegralRuleModelList() {
        String hql = "from IntegralRuleModelEntity ";
        Query query = getCurrentSession().createQuery(hql);
        List<IntegralRuleModelEntity> integralRuleModelList = query.list();
        return integralRuleModelList;
    }

    @Override
    public IntegralRuleModelEntity getIntegralRuleModelById(String modelId) {
        String hql = "from IntegralRuleModelEntity where modelId=:modelId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modelId",modelId);
        IntegralRuleModelEntity integralRuleModelEntity = (IntegralRuleModelEntity) query.uniqueResult();
        return integralRuleModelEntity;
    }
}
