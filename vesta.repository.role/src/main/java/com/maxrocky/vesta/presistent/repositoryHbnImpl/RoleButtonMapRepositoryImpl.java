package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.RoleRolebuttonmapEntity;
import com.maxrocky.vesta.domain.repository.RoleButtonMapRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghj on 2016/4/18.
 */

@Repository
public class RoleButtonMapRepositoryImpl implements RoleButtonMapRepository {


    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    @Override
    public List<RoleRolebuttonmapEntity> getButtonMapList(String appRoleSetId) {
        String hql = "from RoleRolebuttonmapEntity where buttonId=:appRoleSetId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appRoleSetId",appRoleSetId);
        List<RoleRolebuttonmapEntity> roleRolebuttonmapEntities = query.list();
        return roleRolebuttonmapEntities;
    }

    @Override
    public boolean addButtonMap(RoleRolebuttonmapEntity roleRolebuttonmapEntity) {
        Session session = getCurrentSession();
        session.save(roleRolebuttonmapEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public void delButtonMap(String buttonId) {
        String hql = "delete from RoleRolebuttonmapEntity where buttonId=:buttonId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("buttonId",buttonId);
        query.executeUpdate();
    }
}
