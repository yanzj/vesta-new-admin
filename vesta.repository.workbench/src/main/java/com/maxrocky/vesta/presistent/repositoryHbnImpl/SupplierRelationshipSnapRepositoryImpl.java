package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SupplierRelationshipSnapEntity;
import com.maxrocky.vesta.domain.repository.SupplierRelationshipSnapRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by mql on 2016/6/15.
 */
@Repository
public class SupplierRelationshipSnapRepositoryImpl implements SupplierRelationshipSnapRepository{

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    @Override
    public SupplierRelationshipSnapEntity get(String id) {
        String hql = "from SupplierRelationshipSnapEntity where businessId = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        List<SupplierRelationshipSnapEntity> list = query.list();
        if(list != null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void create(SupplierRelationshipSnapEntity supplierRelationshipSnapEntity) {
        Session session = getCurrentSession();
        session.save(supplierRelationshipSnapEntity);
        session.flush();
    }

    @Override
    public void update(SupplierRelationshipSnapEntity supplierRelationshipSnapEntity) {
        Session session = getCurrentSession();
        session.update(supplierRelationshipSnapEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<SupplierRelationshipSnapEntity> getByModifyDateAndId(Date modifyDate,String id) {
        String hql="FROM SupplierRelationshipSnapEntity where 1=1";
        if(modifyDate != null){
            hql += " and modifyDate > :modifyDate ";
        }
        if(id != null && !"".equals(id)){
            hql += " or (modifyDate = :modifyDate1 and id>:id) ";
        }
        hql += " order by modifyDate,id ";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(500);
        if(modifyDate != null) {
            query.setParameter("modifyDate", modifyDate);
        }
        if(id != null && !"".equals(id)){
            query.setParameter("modifyDate1", modifyDate);
            query.setParameter("id", Long.parseLong(id));
        }
        List<SupplierRelationshipSnapEntity> list=query.list();
        return list;
    }

    /**
     * 获取所有供应商信息
     */
    @Override
    public List<SupplierRelationshipSnapEntity> getSupplierList() {
        String hql="FROM SupplierRelationshipSnapEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<SupplierRelationshipSnapEntity> list=query.list();
        return list;
    }
}
