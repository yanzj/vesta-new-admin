package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.RoleRoleanthorityEntity;
import com.maxrocky.vesta.domain.repository.RoleAnthorityRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/25.
 */

@Repository
public class RoleAnthorityRepositoryImpl implements RoleAnthorityRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public RoleRoleanthorityEntity getAnthorityByStaffId(String staffId) {

        String hql = "from RoleRoleanthorityEntity where staffId = :staffId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId", staffId);
        if (query.list().size() > 0) {
            return (RoleRoleanthorityEntity) query.list().get(0);
        } else {
            return null;
        }
    }

    /**
     * 更新
     *
     * @param anthority
     * @return
     */
    @Override
    public boolean updateAnthority(RoleRoleanthorityEntity anthority) {
        RoleRoleanthorityEntity roleRoleanthorityEntity = getAnthorityByStaffId(anthority.getStaffId());
        if (roleRoleanthorityEntity != null) {
            Session session = getCurrentSession();
            session.update(anthority);
            session.flush();
            session.close();
            return true;
        } else {
            Session session = getCurrentSession();
            session.save(anthority);
            session.flush();
            session.close();
            return true;
        }
    }

    @Override
    public List<RoleRoleanthorityEntity> getRoleanthorityList(String appSetId) {
        String hql = "from RoleRoleanthorityEntity where appSetId=:appSetId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appSetId", appSetId);
        List<RoleRoleanthorityEntity> roleRoleanthorityEntities = query.list();
        return roleRoleanthorityEntities;
    }

    /**
     * 删除员工角色关系
     *
     * @param staffId
     * @return
     */
    @Override
    public boolean deleteAnthority(String staffId) {
        RoleRoleanthorityEntity roleRoleanthorityEntity = getAnthorityByStaffId(staffId);
        if (roleRoleanthorityEntity != null) {
            Session session = getCurrentSession();
            session.update(roleRoleanthorityEntity);
            session.flush();
            session.close();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<RoleRoleanthorityEntity> getRoleanthoritys(String staffId) {
        String hql = "from RoleRoleanthorityEntity where staffId = :staffId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId", staffId);
        List<RoleRoleanthorityEntity> roleRoleanthorityEntities = query.list();
        return roleRoleanthorityEntities;
    }

    @Override
    public void roleAdduser(RoleRoleanthorityEntity roleRoleanthorityEntity) {
        Session session = getCurrentSession();
        session.save(roleRoleanthorityEntity);
        session.flush();
    }

    @Override
    public int getStaffCount(String appRoleSetId) {
        String hql = "from RoleRoleanthorityEntity where appSetId=:appRoleSetId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appRoleSetId", appRoleSetId);
        List<RoleRoleanthorityEntity> roleRoleanthorityEntities = query.list();
        if (roleRoleanthorityEntities == null) {
            return 0;
        }
        return roleRoleanthorityEntities.size();
    }

    @Override
    public void delRoleanthority(String appRoleSetId) {
        String hql = "delete from RoleRoleanthorityEntity where appSetId=:appRoleSetId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appRoleSetId", appRoleSetId);
        query.executeUpdate();
    }

    @Override
    public void delStaffRoleSet(String staffId, String appRoleSetId) {
        String hql = "delete from RoleRoleanthorityEntity where appSetId=:appRoleSetId and staffId=:staffId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appRoleSetId", appRoleSetId);
        query.setParameter("staffId", staffId);
        query.executeUpdate();
    }

    @Override
    public void delAdminStaffRoleSet(String staffId, String roleSetId) {
        String hql = "delete from RoleRoleanthorityEntity where setId=:roleSetId and staffId=:staffId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("roleSetId", roleSetId);
        query.setParameter("staffId", staffId);
        query.executeUpdate();
    }

    @Override
    public void delstaffRole(String setId) {
        String hql = "delete from RoleRoleanthorityEntity where setId=:setId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("setId", setId);
        query.executeUpdate();
    }

    @Override
    public void delstaffRole(String setId, String projectId) {
        String hql = "delete from RoleRoleanthorityEntity where setId=:setId and projectid=:projectId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("setId", setId);
        query.setParameter("projectId", projectId);
        query.executeUpdate();
    }

    @Override
    public List<RoleRoleanthorityEntity> getRoleanthoritys(String staffId, String appRoleSetId) {
        String hql = "from RoleRoleanthorityEntity where appSetId=:appRoleSetId and staffId=:staffId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appRoleSetId", appRoleSetId);
        query.setParameter("staffId", staffId);
        List<RoleRoleanthorityEntity> roleRoleanthorityEntities = query.list();
        return roleRoleanthorityEntities;
    }
}
