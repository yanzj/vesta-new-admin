package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SupplierAgencyMapEntity;
import com.maxrocky.vesta.domain.repository.SupplierAgencyRepository;
import com.maxrocky.vesta.utility.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/9/9.
 */
@Repository
public class SupplierAgencyRepositoryImpl implements SupplierAgencyRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addSupplierAgency(SupplierAgencyMapEntity supplierAgencyMapEntity) {
        String sql1 = "INSERT INTO supplier_agency_map(MAP_ID,AGENCY_ID,SUPPLIER_ID,MAP_STATUS,MODIFY_TIME) VALUES(?,?,?,1,?) ON DUPLICATE KEY UPDATE MAP_STATUS='1',MODIFY_TIME=?";
        Query query1 = getCurrentSession().createSQLQuery(sql1);
        query1.setString(0,supplierAgencyMapEntity.getMapId());
        query1.setString(1,supplierAgencyMapEntity.getAgencyId());
        query1.setString(2,supplierAgencyMapEntity.getSupplierId());
        query1.setString(3, DateUtils.format(new Date()));
        query1.setString(4, DateUtils.format(new Date()));
        query1.executeUpdate();
    }

    @Override
    public void delSupplierAgency(SupplierAgencyMapEntity supplierAgencyMapEntity) {
        String hql = "update SupplierAgencyMapEntity set status='0',modifyTime=:modifyTime where agencyId=:agencyId and supplierId=:supplierId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime",new Date());
        query.setParameter("agencyId",supplierAgencyMapEntity.getAgencyId());
        query.setParameter("supplierId",supplierAgencyMapEntity.getSupplierId());
        query.executeUpdate();
    }

    @Override
    public SupplierAgencyMapEntity getSupplierAgencys(String agencyId) {
        String hql = "from SupplierAgencyMapEntity where status='1' and agencyId=:agencyId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("agencyId",agencyId);
        SupplierAgencyMapEntity supplierAgencyMapEntity = (SupplierAgencyMapEntity) query.uniqueResult();
        return supplierAgencyMapEntity;
    }

    @Override
    public void updateSupplierAgency(SupplierAgencyMapEntity supplierAgencyMapEntity) {
        Session session = getCurrentSession();
        session.update(supplierAgencyMapEntity);
        session.flush();
    }
}
