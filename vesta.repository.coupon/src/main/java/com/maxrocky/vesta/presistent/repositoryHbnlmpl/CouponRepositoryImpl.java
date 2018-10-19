package com.maxrocky.vesta.presistent.repositoryHbnlmpl;

import com.maxrocky.vesta.domain.model.CouponEntity;
import com.maxrocky.vesta.domain.repository.CouponRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/4/1.
 */
@Repository
public class CouponRepositoryImpl implements CouponRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<CouponEntity> getCouponList(String userId) {
        String hql = "from CouponEntity where userId=:userId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        List<CouponEntity> couponEntities = query.list();
        return couponEntities;
    }

    @Override
    public void addCoupon(CouponEntity couponEntity) {
        Session session = getCurrentSession();
        session.save(couponEntity);
        session.flush();
    }

    @Override
    public Integer getTotal(String voucherId) {
        String hql = "from CouponEntity where voucherId =:voucherId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("voucherId",voucherId);
        List<CouponEntity> couponEntities = query.list();
        if(couponEntities==null){
            return 0;
        }
        return couponEntities.size();
    }
}
