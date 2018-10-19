package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SellerEvaluateEntity;
import com.maxrocky.vesta.domain.repository.SellerEvaluateRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/1/15.
 */
@Repository
public class SellerEvaluateRepositoryImpl implements SellerEvaluateRepository{
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
            return sessionFactory.openSession();
        }

    public void setSessionFactory(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }

    @Override
    public void AddSellerEvaluate(SellerEvaluateEntity sellerEvaluateEntity) {
        Session session = getCurrentSession();
        session.save(sellerEvaluateEntity);
        session.flush();
    }

    @Override
    public List<SellerEvaluateEntity> getGoodEvaluates(String sellerId) {
        String sql = "from SellerEvaluateEntity where sellerId = ? and evaluateCircs = "+ SellerEvaluateEntity.CIRCS_FLOWER +
                " and evaluateStatus ="+ SellerEvaluateEntity.STATUS_VALID;
        Query query = getCurrentSession().createQuery(sql);
        query.setParameter(0,sellerId);
        List<SellerEvaluateEntity> sellerEvaluateEntityList = query.list();
        return sellerEvaluateEntityList;
    }

    @Override
    public List<SellerEvaluateEntity> getBadEvaluates(String sellerId) {
        String sql = "from SellerEvaluateEntity where sellerId = ? and evaluateCircs = "+ SellerEvaluateEntity.CIRCS_NEGATIVE+
                "and evaluateStatus = "+ SellerEvaluateEntity.STATUS_VALID;
        Query query = getCurrentSession().createQuery(sql);
        query.setParameter(0,sellerId);
        List<SellerEvaluateEntity> sellerEvaluateEntityList = query.list();
        return sellerEvaluateEntityList;
    }

    @Override
    public void UpdateEvaluate(SellerEvaluateEntity sellerEvaluateEntity) {
        Session session = getCurrentSession();
        session.update(sellerEvaluateEntity);
        session.flush();
    }

    @Override
    public SellerEvaluateEntity getEvaluateDetail(String id) {
        String hql = "from SellerEvaluateEntity where evaluateId = ?";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter(0,id);
        SellerEvaluateEntity sellerEvaluateEntity = (SellerEvaluateEntity) query.uniqueResult();
        return sellerEvaluateEntity;
    }

    @Override
    public List<Object[]> getReputationList() {
        String sql = "select sellerId,count(*) from SellerEvaluateEntity where evaluateStatus="
                +SellerEvaluateEntity.STATUS_VALID +"and evaluateCircs ="+SellerEvaluateEntity.CIRCS_FLOWER+" group by sellerId";
        Query query = getCurrentSession().createQuery(sql);
        List<Object[]> list=query.list();
        return list;
    }

    @Override
    public List<SellerEvaluateEntity> getUserEvaluate(String userId, String sellerId) {
        String hql = "from SellerEvaluateEntity where userId=:userId and sellerId = :sellerId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        query.setParameter("sellerId",sellerId);
        List<SellerEvaluateEntity> sellerEvaluateEntities = query.list();
        return sellerEvaluateEntities;
    }
}
