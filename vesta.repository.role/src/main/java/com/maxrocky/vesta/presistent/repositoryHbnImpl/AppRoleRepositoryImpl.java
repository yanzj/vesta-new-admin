package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.AppRoleEntity;
import com.maxrocky.vesta.domain.repository.AppRoleRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chen on 2016/5/4.
 */
@Repository
public class AppRoleRepositoryImpl implements AppRoleRepository {
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<AppRoleEntity> getRoles(String setIds) {
        String hql = "from AppRoleEntity where appRoleId in ( select distinct appRoleId from AppRolesetMapEntity where appSetId in(" + setIds + "))";
        Query query = getCurrentSession().createQuery(hql);
        List<AppRoleEntity> appRoleEntities = query.list();
        return appRoleEntities;
    }

    @Override
    public List<AppRoleEntity> getRoleList(String setId) {
        String hql = "from AppRoleEntity where appRoleId in ( select distinct appRoleId from AppRolesetMapEntity where appSetId =:setId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("setId", setId);
        List<AppRoleEntity> appRoleEntities = query.list();
        return appRoleEntities;
    }

    @Override
    public List<AppRoleEntity> roleList() {
        String hql = "from AppRoleEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<AppRoleEntity> appRoleEntities = query.list();
        return appRoleEntities;
    }

    @Override
    public List<AppRoleEntity> getRepairAppRoleList(String appSetId) {
        String hql = "from AppRoleEntity where appRoleId in ( select distinct appRoleId from AppRolesetMapEntity where (appRoleId='n2' or appRoleId='n3') and appSetId =:setId )";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("setId", appSetId);
        List<AppRoleEntity> appRoleEntities = query.list();
        return appRoleEntities;
    }

    @Override
    public List<AppRoleEntity> getEngineeringAppRoleList(String appSetId) {
        String hql = "from AppRoleEntity where appRoleId in ( select distinct appRoleId from AppRolesetMapEntity where appRoleId='n1'and appSetId =:setId )";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("setId", appSetId);
        List<AppRoleEntity> appRoleEntities = query.list();
        return appRoleEntities;
    }
}
