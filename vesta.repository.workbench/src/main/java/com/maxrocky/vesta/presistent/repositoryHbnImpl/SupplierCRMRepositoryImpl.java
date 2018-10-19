package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SupplierCRMEntity;
import com.maxrocky.vesta.domain.repository.SupplierCRMRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/4/22.
 */
@Repository
public class SupplierCRMRepositoryImpl implements SupplierCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void AddSupplier(SupplierCRMEntity supplierCRMEntity) {
        Session session = getCurrentSession();
        session.save(supplierCRMEntity);
        session.flush();
    }

    @Override
    public void UpdateSupplier(SupplierCRMEntity supplierCRMEntity) {
        Session session = getCurrentSession();
        session.update(supplierCRMEntity);
        session.flush();
    }

    @Override
    public List<SupplierCRMEntity> getSupplierList(){
        String hql = " from SupplierCRMEntity ";
        Query query = getCurrentSession().createQuery(hql);
        return (List<SupplierCRMEntity>)query.list();
    }
}
