package com.maxrocky.vesta.presistent.repositoryHbnImpl;

//import com.maxrocky.vesta.domain.RoleRolesetEntity;

import com.maxrocky.vesta.domain.model.RoleRolesetEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.RoleRolesetRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/17.
 */
@Repository
public class RoleRolesetRepositoryImpl extends HibernateDaoImpl implements RoleRolesetRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    /**
     * 添加新角色
     *
     * @param roleRolesetEntity
     * @return
     */
    @Override
    public boolean saveRoleSet(RoleRolesetEntity roleRolesetEntity) {
        RoleRolesetEntity roleRolesetEntity1 = getRolesetByDesc(roleRolesetEntity.getRoledesc());
        if (roleRolesetEntity1 == null) {
            Session session = getCurrentSession();
            session.save(roleRolesetEntity);
            session.flush();
            session.close();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通过角色名查找角色
     *
     * @param desc
     * @return
     */
    public RoleRolesetEntity getRolesetByDesc(String desc) {
        String hql = "from RoleRolesetEntity as o where o.roledesc = '" + desc + "' and o.setState = '1'";
        Query query = getCurrentSession().createQuery(hql);
        if (query.list().size() > 0) {
            RoleRolesetEntity roleRolesetEntity = (RoleRolesetEntity) query.list().get(0);
            return roleRolesetEntity;
        } else {
            return null;
        }
    }

    /**
     * 通过角色Id查找角色信息
     *
     * @param rolesetId
     * @return
     */
    @Override
    public RoleRolesetEntity getRolesetById(String rolesetId) {
        String hql = "from RoleRolesetEntity as o where o.setId = '" + rolesetId + "'and o.setState = '01'";
        Query query = getCurrentSession().createQuery(hql);
        if (query.list().size() > 0) {
            RoleRolesetEntity roleRolesetEntity = (RoleRolesetEntity) query.list().get(0);
            return roleRolesetEntity;
        } else {
            return null;
        }
    }

    @Override
    public RoleRolesetEntity getRolesetByName(String rolesetName) {
        String hql = "from RoleRolesetEntity as o where o.roledesc = '" + rolesetName + "'and o.setState = '01'";
        Query query = getCurrentSession().createQuery(hql);
        if (query.list().size() > 0) {
            RoleRolesetEntity roleRolesetEntity = (RoleRolesetEntity) query.list().get(0);
            return roleRolesetEntity;
        } else {
            return null;
        }
    }

    /**
     * 获取角色列表，分页
     *
     * @return
     */
    @Override
    public List<RoleRolesetEntity> listRoleset(WebPage webPage, String setId, String roleName) {
        List<RoleRolesetEntity> roleRolesetEntities = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        String hql = "from RoleRolesetEntity as o where o.setId != '9999' and o.setId != '0001' and o.setState = '01' and setType!='1'";

        if (null != setId && !"".equals(setId)) {
            hql = hql + " and o.setType = '" + setId + "'";
        }
        if (!StringUtil.isEmpty(roleName)) {
            hql += " and roledesc like '%" + roleName + "%'";
        }
        hql = hql + " order by o.modifyDate desc,o.modifyTime desc";

        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }
////        Query query = getCurrentSession().createQuery(hql);
//        List<RoleRolesetEntity> roleRolesetEntities = query.list();
        roleRolesetEntities = (List<RoleRolesetEntity>) getHibernateTemplate().find(hql.toString());
        return roleRolesetEntities;
    }

    /**
     * 更新角色信息
     *
     * @param roleRolesetEntity
     * @return
     */
    @Override
    public boolean updateRoleset(RoleRolesetEntity roleRolesetEntity) {
        RoleRolesetEntity roleRoleset = getRolesetById(roleRolesetEntity.getSetId());
        if (roleRoleset != null) {
            Session session = getCurrentSession();
            session.update(roleRolesetEntity);
            session.flush();
            session.close();
            return true;
        } else {
            return false;
        }
    }


    /**
     * 删除角色
     *
     * @param roleRolesetEntity
     * @return
     */
    @Override
    public boolean deleteRoleset(RoleRolesetEntity roleRolesetEntity) {

        roleRolesetEntity = getRolesetById(roleRolesetEntity.getSetId());
        if (roleRolesetEntity != null) {
            Session session = getCurrentSession();
            session.delete(roleRolesetEntity);
            session.flush();
            session.close();
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserPropertyStaffEntity> getOutStaffs(UserPropertyStaffEntity userPropertyStaffEntity) {
        String hql = "from UserPropertyStaffEntity where 1=1";
        if (!StringUtil.isEmpty(userPropertyStaffEntity.getStaffName())) {
            hql += "and staffName like '%" + userPropertyStaffEntity.getStaffName() + "%'";
        }
        hql += "and staffState='1' and staffId not in (select distinct staffId from RoleRoleanthorityEntity where setId=:setId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("setId", userPropertyStaffEntity.getRoleSetId());
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        return userPropertyStaffEntities;
    }

    @Override
    public List<UserPropertyStaffEntity> getInStaffs(String roleSetId) {
        String hql = "from UserPropertyStaffEntity where staffState='1' and staffId in (select distinct staffId from RoleRoleanthorityEntity where setId=:setId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("setId", roleSetId);
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        return userPropertyStaffEntities;
    }


}
