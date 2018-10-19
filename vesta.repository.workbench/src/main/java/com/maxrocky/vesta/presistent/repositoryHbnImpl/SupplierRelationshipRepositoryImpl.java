package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.ctc.wstx.util.StringUtil;
import com.maxrocky.vesta.domain.model.SupplierRelationshipEntity;
import com.maxrocky.vesta.domain.repository.SupplierRelationshipRepository;
import org.apache.axis.utils.StringUtils;
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
public class SupplierRelationshipRepositoryImpl implements SupplierRelationshipRepository {
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
    public void create(SupplierRelationshipEntity supplierEntity) {
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
    public void update(SupplierRelationshipEntity supplierEntity) {
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
    public SupplierRelationshipEntity getById(String id) {
        String hql="FROM SupplierRelationshipEntity WHERE id='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<SupplierRelationshipEntity> list=query.list();
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<SupplierRelationshipEntity> getByModifyDate(Date modifyDate) {
        String hql="FROM SupplierRelationshipEntity ";
        if(modifyDate != null){
            hql += " where modifyDate > :modifyDate";
        }
        hql+= " order by modifyDate desc";
        Query query = getCurrentSession().createQuery(hql);
        if(modifyDate != null) {
            query.setParameter("modifyDate", modifyDate);
        }
        List<SupplierRelationshipEntity> list=query.list();
        return list;
    }

    /**
     * CreatedBy:liudongxin
     * Describe:删除
     * ModifyBy:
     */
    @Override
    public void delete(String projectNum) {
        String hql="delete FROM SupplierRelationshipEntity";
        if(!StringUtils.isEmpty(projectNum)){
            hql+=" WHERE projectNum='"+projectNum+"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }
}
