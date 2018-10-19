package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.AppRolesetMapEntity;
import com.maxrocky.vesta.domain.model.RoleRolesetmapEntity;
import com.maxrocky.vesta.domain.repository.RoleRolesetmapRepository;
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
public class RoleRolesetmapImpl implements RoleRolesetmapRepository{
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 查询角色的所有权限
     * @param setId
     * @return
     */
    @Override
    public List<RoleRolesetmapEntity> listRolesetMapBySetId(String setId) {
        String hql = "from RoleRolesetmapEntity as o where o.setId = '"+setId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<RoleRolesetmapEntity> roleRolesetmapEntities = query.list();
        return roleRolesetmapEntities;
    }

    /**
     * 删除权限关系
     * @param roleRolesetmap
     * @return
     */
    @Override
    public boolean deleteRoleRolesetMap(RoleRolesetmapEntity roleRolesetmap) {
        Session session = getCurrentSession();
        RoleRolesetmapEntity roleRolesetmapEntity = getRoleSetmapById(roleRolesetmap.getRolesetid());
        if (roleRolesetmapEntity!=null){
            session.delete(roleRolesetmapEntity);
            session.flush();
            session.close();
        }
        return true;
    }
    /**
     * 根据权限角色关系Id获得关系
     * @param rolesetId
     * @return
     */
    @Override
    public RoleRolesetmapEntity getRoleSetmapById(String rolesetId){
        String hql = "from RoleRolesetmapEntity as o where o.rolesetid = '"+rolesetId+"'";
        Query query = getCurrentSession().createQuery(hql);
        if (query.list().size()>0){
            return  (RoleRolesetmapEntity)query.list().get(0);
        }
        else {
            return null;
        }
    }

    /**
     * 保存角色权限关系
     * @param roleRolesetmapEntity
     * @return
     */
    @Override
    public boolean saveRolesetMap(RoleRolesetmapEntity roleRolesetmapEntity) {
        Session session = getCurrentSession();
        session.save(roleRolesetmapEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 判断角色和权限关系是否存在
     * @param setId
     * @param roleId
     * @return
     */
    @Override
    public RoleRolesetmapEntity getRolesetMap(String setId, String roleId) {
        String hql = "from RoleRolesetmapEntity as o where setId = :setId and rolRoleId = :roleId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("setId",setId);
        query.setParameter("roleId",roleId);
        if (query.list().size()>0){
            return (RoleRolesetmapEntity)query.list().get(0);
        }else {
            return null;
        }
    }

    @Override
    public void saveAppRolestmap(AppRolesetMapEntity appRolesetMapEntity) {
        Session session = getCurrentSession();
        session.save(appRolesetMapEntity);
        session.flush();
        session.close();
    }

    @Override
    public void delAppRolesetmap(String roleSetId) {
        String hql = "delete from AppRolesetMapEntity where appSetId = :roleSetId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("roleSetId",roleSetId);
        query.executeUpdate();
    }
}
