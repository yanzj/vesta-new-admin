package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.RoleRoleEntity;
import com.maxrocky.vesta.domain.repository.RoleRoleRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/18.
 */
@Repository
public class RoleRoleRepositoryImpl  implements RoleRoleRepository{
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    /**
     * 获取所有权限列表
     * @return
     */
    @Override
    public List<RoleRoleEntity> listRoleRole() {
        String hql = "from RoleRoleEntity";//roleSetId判断业主端权限和管理端权限，0002-管理后台权限
        Query query = getCurrentSession().createQuery(hql);
        List<RoleRoleEntity> roleRoleEntities = query.list();
        return roleRoleEntities;
    }


    /**
     * 获取某个模块下所有的权限列表
     * @param roledesc
     * @return
     */
    @Override
    public List<RoleRoleEntity> listRoleRoleByroledesc(String roledesc) {
        String hql = "from RoleRoleEntity as o where o.roledesc = '"+roledesc+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<RoleRoleEntity> roleRoleEntities = query.list();
        return roleRoleEntities;
    }

    /**
     * 获取权限模块
     * @return
     */
    @Override
    public List<String> getGroup() {
        String hql = "select o.roledesc  from RoleRoleEntity as o group by o.roledesc";//roleSetId判断业主端权限和管理端权限，0002-管理后台权限
        Query query = getCurrentSession().createQuery(hql);
        List<String> list = query.list();
        return list;
    }

    @Override
    public boolean addRole(RoleRoleEntity roleRoleEntity) {
        Session session = getCurrentSession();
        session.save(roleRoleEntity);
        session.flush();
        session.close();
        return true;
    }
}
