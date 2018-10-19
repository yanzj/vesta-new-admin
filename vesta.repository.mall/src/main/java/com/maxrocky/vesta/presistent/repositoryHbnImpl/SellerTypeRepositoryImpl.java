package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SellerTypeEntity;
import com.maxrocky.vesta.domain.repository.SellerTypeRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/1/15.
 */

@Repository
public class SellerTypeRepositoryImpl extends HibernateDaoImpl implements SellerTypeRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void AddSellerType(SellerTypeEntity sellerTypeEntity) {
        Session session = getCurrentSession();
        session.save(sellerTypeEntity);
        session.flush();
    }

    @Override
    public void UpdateSellerType(SellerTypeEntity sellerTypeEntity) {
        Session session = getCurrentSession();
        session.update(sellerTypeEntity);
        session.flush();
    }

    @Override
    public List<SellerTypeEntity> getSellerTypeList() {
        String hql = "from SellerTypeEntity where status ="+ SellerTypeEntity.STATUS_VALID;
        Query query = getCurrentSession().createQuery(hql);
        List<SellerTypeEntity> sellerTypeEntities = query.list();
        return sellerTypeEntities;
    }

    @Override
    public List<SellerTypeEntity> getSellerTypeList(WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from SellerTypeEntity where status ="+ SellerTypeEntity.STATUS_VALID;
        Query query = getCurrentSession().createQuery(hql);
        List<SellerTypeEntity> sellerTypeEntities = query.list();
        if(webPage !=null){
            return this.findByPage(hql, webPage,params);
        }
        return sellerTypeEntities;
    }

    @Override
    public SellerTypeEntity getSellerTypeDetail(String id) {
        String hql = "from SellerTypeEntity where typeId=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        SellerTypeEntity sellerTypeEntity = (SellerTypeEntity) query.uniqueResult();
        return sellerTypeEntity;
    }
}
