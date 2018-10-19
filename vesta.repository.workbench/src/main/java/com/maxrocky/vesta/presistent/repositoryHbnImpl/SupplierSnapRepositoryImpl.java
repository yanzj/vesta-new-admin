package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SupplierSnapEntity;
import com.maxrocky.vesta.domain.repository.SupplierSnapRepository;
import com.maxrocky.vesta.utility.StringUtil;
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
public class SupplierSnapRepositoryImpl implements SupplierSnapRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public SupplierSnapEntity get(String id) {
        String hql = "from SupplierSnapEntity where businessId = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        List<SupplierSnapEntity> list = query.list();
        if(list != null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    @Override
    public void create(SupplierSnapEntity supplierSnapEntity) {
        Session session = getCurrentSession();
        session.save(supplierSnapEntity);
        session.flush();
    }

    @Override
    public void update(SupplierSnapEntity supplierSnapEntity) {
        Session session = getCurrentSession();
        session.update(supplierSnapEntity);
        session.flush();
    }

    @Override
    public List<SupplierSnapEntity> getByModifyDateAndId(String modifyDate,String id) {
        String hql="FROM SupplierSnapEntity where 1=1";

        if(!StringUtil.isEmpty(modifyDate) && !StringUtil.isEmpty(id)){
            hql += " and (modifyDate >'"+modifyDate+"' or (modifyDate ='"+modifyDate+"' and id>'"+id+"' ) )";
        }
        hql += " order by modifyDate,id ";
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(500);
        List<SupplierSnapEntity> list=query.list();
        return list;
    }

    /**
     * 获取所有供应商信息
     */
    @Override
    public List<SupplierSnapEntity> getSupplierSnapList() {
        String hql="FROM SupplierSnapEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<SupplierSnapEntity> list=query.list();
        return list;
    }
}
