package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SupplierEntity;
import com.maxrocky.vesta.domain.repository.SupplierRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by liudongxin on 2016/6/7.
 */
@Repository
public class SupplierRepositoryImpl implements SupplierRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:创建供应商信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    @Override
    public void create(SupplierEntity supplierEntity) {
        Session session=getCurrentSession();
        session.save(supplierEntity);
        session.flush();
        session.close();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:修改供应商信息
     * ModifyBy:
     */
    @Override
    public void update(SupplierEntity supplierEntity) {
        Session session=getCurrentSession();
        session.update(supplierEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:根据id获取信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public SupplierEntity getById(String id) {
        String hql="FROM SupplierEntity WHERE id='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<SupplierEntity> list=query.list();
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<SupplierEntity> getByModifyDate(Date modifyDate) {
        String hql="FROM SupplierEntity ";
        if(modifyDate != null){
            hql += " where modifyDate > :modifyDate";
        }
        hql += " order by modifyDate desc ";
        Query query = getCurrentSession().createQuery(hql);
        if(modifyDate != null) {
            query.setParameter("modifyDate", modifyDate);
        }
        List<SupplierEntity> list=query.list();
        return list;
    }

    @Override
    public List<Object[]> getByProjectNumAndThirdId(String projectNum, String thirdId) {
        String hql = "select id,name from supplier where id in (select supplier_id from supplier_relationship where project_num=:projectNum and third_id=:thirdId) order by name";
        Query query = getCurrentSession().createSQLQuery(hql);
        if(projectNum != null) {
            query.setParameter("projectNum", projectNum);
        }
        if(thirdId != null) {
            query.setParameter("thirdId", thirdId);
        }
        List<Object[]> list=query.list();
        return list;
    }

    @Override
    public List<Object> getSupplierByName(String supplierName) {
        String hql = "select id from supplier where name like :supplierName order by name";
        Query query = getCurrentSession().createSQLQuery(hql);
        if(supplierName != null) {
            query.setString("supplierName", "%" + supplierName + "%");
        }
        List<Object> list=query.list();
        return list;
    }

    //从视图获取数据
    @Override
    public List<Object[]> getSupplierPeople(String timeStamp, int num) {
        String sql = "SELECT * FROM supplier_snap_view WHERE modifyDate>= '"+timeStamp+"' order by modifyDate";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setFirstResult(num * 500);
        query.setMaxResults(500);
        List<Object[]> objects = query.list();
        return objects;
    }

    @Override
    public List<SupplierEntity> getSupplierList() {
        String hql = "from SupplierEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<SupplierEntity> supplierEntities = query.list();
        return supplierEntities;
    }

    @Override
    public List<SupplierEntity> getSuppliers(String supplierName) {
        String hql = "from SupplierEntity where name like '%"+supplierName+"%'";
        Query query = getCurrentSession().createQuery(hql);
        List<SupplierEntity> supplierEntities = query.list();
        return supplierEntities;
    }
}
