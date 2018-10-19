package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SellerCollectEntity;
import com.maxrocky.vesta.domain.model.ShopSellerEntity;
import com.maxrocky.vesta.domain.repository.SellerCollectRepository;
import com.maxrocky.vesta.utility.Page;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/1/27.
 */
@Repository
public class SellerCollectRepositoryImpl implements SellerCollectRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void AddSellerCollect(SellerCollectEntity sellerCollectEntity) {
        Session session = getCurrentSession();
        session.save(sellerCollectEntity);
        session.flush();
    }

    @Override
    public SellerCollectEntity getSellerCollect(String sellerId, String userId) {
        String hql = "from SellerCollectEntity where userId=:userId and sellerId=:sellerId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        query.setParameter("sellerId",sellerId);
        SellerCollectEntity sellerCollectEntity = (SellerCollectEntity) query.uniqueResult();
        return sellerCollectEntity;
    }

    @Override
    public void DelSellerCollect(SellerCollectEntity sellerCollectEntity) {
        Session session = getCurrentSession();
        session.delete(sellerCollectEntity);
        session.flush();
    }

    @Override
    public List<SellerCollectEntity> getUserCollects(String userId,Page page) {
        String hql = "from SellerCollectEntity where userId =:userId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<SellerCollectEntity> sellerCollectEntities = query.list();
        return sellerCollectEntities;
    }

    @Override
    public Boolean SellerCollects(String userId, String sellerId) {
        String hql ="from SellerCollectEntity where userId=:userId and sellerId = :sellerId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        query.setParameter("sellerId",sellerId);
        List<SellerCollectEntity> sellerCollectEntities = query.list();
        if(sellerCollectEntities!=null&&sellerCollectEntities.size()>0){
            return true;
        }else {
         return false;
        }
    }

    @Override
    public List<Object[]> getCollectSeller(String sellerIds) {
        String hql ="SELECT sellerId,sellerLogo,sellerTel,sellerName,sellerAddress," +
                "(SELECT COUNT(*) FROM SellerEvaluateEntity AS b WHERE a.sellerId = b.sellerId AND b.evaluateCircs = '1') AS good," +
                "(SELECT COUNT(*) FROM SellerEvaluateEntity AS b WHERE a.sellerId = b.sellerId AND b.evaluateCircs = '2') AS bad " +
                "FROM ShopSellerEntity as a where sellerStatus = '"+ ShopSellerEntity.STATUS_VALID +"' and a.sellerId in ("+sellerIds+")";
        Query query = getCurrentSession().createQuery(hql);
        List<Object[]> objects= query.list();
        return objects;
    }

    @Override
    public Integer getCollectNum(String sellerId) {
        String hql = "from SellerCollectEntity where sellerId =:sellerId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("sellerId",sellerId);
        List<SellerCollectEntity> sellerCollectEntities = query.list();
        if(sellerCollectEntities==null){
            return 0;
        }
        return sellerCollectEntities.size();
    }
}
