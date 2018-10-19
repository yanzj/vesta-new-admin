package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.DepartmentCRMEntity;
import com.maxrocky.vesta.domain.model.HouseLocationEntity;
import com.maxrocky.vesta.domain.model.StaffCRMEntity;
import com.maxrocky.vesta.domain.model.SubcompanyCRMEntity;
import com.maxrocky.vesta.domain.repository.UserMessageCRMRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hp on 2018/2/5.
 */
@Repository
public class UserMessageCRMRepositoryImpl implements UserMessageCRMRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public StaffCRMEntity get(String id) {
        StringBuilder hql = new StringBuilder(" FROM StaffCRMEntity WHERE userid='"+id+"' ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<StaffCRMEntity> staffCRMEntities=query.list();
        if(staffCRMEntities.size()>0){
            return staffCRMEntities.get(0);
        }
        return null;
    }

    @Override
    public void create(StaffCRMEntity staffCRMEntity) {
        Session session = getCurrentSession();
        session.save(staffCRMEntity);
        session.flush();
        session.close();
    }

    @Override
    public void update(StaffCRMEntity staffCRMEntity) {
        Session session = getCurrentSession();
        session.update(staffCRMEntity);
        session.flush();
        session.close();
    }

    @Override
    public void delete() {
        StringBuilder hql= new StringBuilder("delete FROM StaffCRMEntity");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.executeUpdate();
    }

    @Override
    public SubcompanyCRMEntity getSubcompany(String id) {
        StringBuilder hql = new StringBuilder(" FROM SubcompanyCRMEntity WHERE subcompanyid='"+id+"' ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<SubcompanyCRMEntity> subcompanyCRMEntities=query.list();
        if(subcompanyCRMEntities.size()>0){
            return subcompanyCRMEntities.get(0);
        }
        return null;
    }

    @Override
    public void createSubcompany(SubcompanyCRMEntity subcompanyCRMEntity) {
        Session session = getCurrentSession();
        session.save(subcompanyCRMEntity);
        session.flush();
        session.close();
    }

    @Override
    public void updateSubcompany(SubcompanyCRMEntity subcompanyCRMEntity) {
        Session session = getCurrentSession();
        session.update(subcompanyCRMEntity);
        session.flush();
        session.close();
    }

    @Override
    public void deleteSubcompany() {
        StringBuilder hql= new StringBuilder("delete FROM SubcompanyCRMEntity");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.executeUpdate();
    }

    @Override
    public DepartmentCRMEntity getDepartment(String id) {
        StringBuilder hql = new StringBuilder(" FROM DepartmentCRMEntity WHERE departmentid='"+id+"' ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<DepartmentCRMEntity> departmentCRMEntities=query.list();
        if(departmentCRMEntities.size()>0){
            return departmentCRMEntities.get(0);
        }
        return null;
    }

    @Override
    public void createDepartment(DepartmentCRMEntity departmentCRMEntity) {
        Session session = getCurrentSession();
        session.save(departmentCRMEntity);
        session.flush();
        session.close();
    }

    @Override
    public void updateDepartment(DepartmentCRMEntity departmentCRMEntity) {
        Session session = getCurrentSession();
        session.update(departmentCRMEntity);
        session.flush();
        session.close();
    }

    @Override
    public void deleteDepartment() {
        StringBuilder hql= new StringBuilder("delete FROM DepartmentCRMEntity");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.executeUpdate();
    }
}
