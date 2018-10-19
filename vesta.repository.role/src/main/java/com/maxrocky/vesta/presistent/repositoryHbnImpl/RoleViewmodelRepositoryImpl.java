package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.RoleViewmodelEntity;
import com.maxrocky.vesta.domain.repository.RoleViewmodelRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JillChen on 2016/1/13.
 */
@Repository
public class RoleViewmodelRepositoryImpl extends HibernateDaoImpl implements RoleViewmodelRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<RoleViewmodelEntity> getViewLisOthertByUserId(String property, String userid) {
        if (userid == null || "".equals(userid.trim())) {
            return null;
        }
        //根据用户ID判断所属组织架构是否有角色权限
        String hq = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
                " from UserAgencymapEntity where status='1' and staffId = :staffId)";
        Query queryh = getCurrentSession().createQuery(hq);
        queryh.setParameter("staffId", userid);
        List<String> paths = queryh.list();
        String str = "";
        if (paths != null && paths.size() > 0) {
            for (String ids : paths) {
                str += ids.replace("/", "','");
            }
        }
        String hql = "";
        List menuId = new ArrayList();
        if (!StringUtil.isEmpty(str)) {
            str = str.substring(2) + "'";
            String sql = "SELECT DISTINCT rd.DATA_ID FROM role_data rd LEFT JOIN role_agency ra ON ra.AGENCY_ID = rd.AUTHORITY_ID " +
                    "WHERE rd.DATA_TYPE = '2' AND rd.`STATUS` = '1' AND rd.AUTHORITY_TYPE = '1' " +
                    "AND rd.AUTHORITY_ID IN (" + str + ")";
            Query query = getCurrentSession().createSQLQuery(sql);
            List dataId = query.list();

            String menusql = "SELECT DISTINCT rrbm.MenuId FROM role_roleset rrs " +
                    "RIGHT JOIN role_roleanthority rrt ON rrt.setid = rrs.setid " +
                    "RIGHT JOIN role_rolesetmap rrsm ON rrsm.setid = rrs.setid " +
                    "RIGHT JOIN role_role rr ON rr.roleid = rrsm.rol_roleid " +
                    "RIGHT JOIN role_rolebuttonmap rrbm ON rr.roleid = rrbm.roleid " +
                    "WHERE rrs.SetState = '01' AND (rrt.StaffId = :userid ";
            if (dataId.size() > 0) {
                menusql += " OR rrs.setid IN (:setIds)";
            }
            menusql += ")";
            Query menuquery = getCurrentSession().createSQLQuery(menusql);
            menuquery.setParameter("userid", userid);
            if (dataId.size() > 0) {
                menuquery.setParameterList("setIds", dataId);
            }
            menuId = menuquery.list();

            hql = "FROM RoleViewmodelEntity WHERE menulevel<>:menulevel and owner =:owner and  menuId IN  (:menuIds)  order by menuId,menuorder";
//            String sql = "select agencyId from AgencyEntity where agencyId in ("+str+")";
//            Query query1 = getCurrentSession().createQuery(sql);
////            String authorIds = query1.getQueryString();
//            List<String> authorIds = query1.list();
//            String sql1 = "select dataId from RoleDataEntity where dataType = '2' and status = '1' and " +
//                    "authorityType = '1' and authorityId in("+authorIds+")";
//            Query query2 = getCurrentSession().createQuery(sql1);
//            String setIds = query2.getQueryString();
//            hql= "FROM RoleViewmodelEntity WHERE menulevel<>:menulevel and owner =:owner and  menuId IN  (" +
//                    "SELECT DISTINCT e.menuId FROM  RoleRolesetEntity a,RoleRoleanthorityEntity b,RoleRolesetmapEntity c,RoleRoleEntity d ,RoleRolebuttonmapEntity e" +
//                    " WHERE a.setId = c.setId AND (a.setId = b.setId or a.setId in (:setIds)) AND c.rolRoleId = d.roleId  AND d.roleId=e.roleId" +
//                    " AND b.staffId = :userid  and a.setState='01')  order by menuId,menuorder";
        } else {
            hql = "FROM RoleViewmodelEntity WHERE menulevel<>:menulevel and owner =:owner and  menuId IN  (" +
                    "SELECT DISTINCT e.menuId FROM  RoleRolesetEntity a,RoleRoleanthorityEntity b,RoleRolesetmapEntity c,RoleRoleEntity d ,RoleRolebuttonmapEntity e" +
                    " WHERE a.setId = b.setId AND a.setId = c.setId AND c.rolRoleId = d.roleId  AND d.roleId=e.roleId" +
                    " AND b.staffId = :userid  and a.setState='01')  order by menuId,menuorder";
        }

        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("menulevel", "1");
        query.setParameter("owner", property);
        if (!StringUtil.isEmpty(str)) {
            query.setParameterList("menuIds", menuId);
        } else {
            query.setParameter("userid", userid);
        }
        List<RoleViewmodelEntity> viewmodels = query.list();
        if (viewmodels == null || viewmodels.size() <= 0) {
            return null;
        }
        return viewmodels;
    }

    @Override
    public List<RoleViewmodelEntity> getAuthViewLisOthertByUserId(String property, String userid) {
        if (userid == null || "".equals(userid.trim())) {
            return null;
        }
        //根据用户ID判断所属组织架构是否有角色权限
        String hq = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
                " from UserAgencymapEntity where status='1' and staffId = :staffId)";
        Query queryh = getCurrentSession().createQuery(hq);
        queryh.setParameter("staffId", userid);
        List<String> paths = queryh.list();
        String str = "";
        if (paths != null && paths.size() > 0) {
            for (String ids : paths) {
                str += ids.replace("/", "','");
            }
        }
        String hql = "";
        List menuId = new ArrayList();
        if (!StringUtil.isEmpty(str)) {
            str = str.substring(2) + "'";
            String sql = "SELECT DISTINCT rd.DATA_ID FROM role_data rd LEFT JOIN role_agency ra ON ra.AGENCY_ID = rd.AUTHORITY_ID " +
                    "WHERE rd.DATA_TYPE = '2' AND rd.`STATUS` = '1' AND rd.AUTHORITY_TYPE = '1' " +
                    "AND rd.AUTHORITY_ID IN (" + str + ")";
            Query query = getCurrentSession().createSQLQuery(sql);
            List dataId = query.list();

            String menusql = "SELECT DISTINCT rrbm.MenuId FROM role_roleset rrs " +
                    "RIGHT JOIN role_roleanthority rrt ON rrt.setid = rrs.setid " +
                    "RIGHT JOIN role_rolesetmap rrsm ON rrsm.setid = rrs.setid " +
                    "RIGHT JOIN role_role rr ON rr.roleid = rrsm.rol_roleid " +
                    "RIGHT JOIN role_rolebuttonmap rrbm ON rr.roleid = rrbm.roleid " +
                    "WHERE rrs.SetState = '01' AND (rrt.StaffId = :userid ";
            if (dataId.size() > 0) {
                menusql += " OR rrs.setid IN (:setIds)";
            }
            menusql += ")";
            Query menuquery = getCurrentSession().createSQLQuery(menusql);
            menuquery.setParameter("userid", userid);
            if (dataId.size() > 0) {
                menuquery.setParameterList("setIds", dataId);
            }
            menuId = menuquery.list();
            if(menuId!=null && menuId.size()>0){
                hql = "FROM RoleViewmodelEntity WHERE menulevel<>:menulevel and owner =:owner and  menuId IN  (:menuIds) and belong ='1'  order by menuId,menuorder";
            }else{
                return null;
            }
        } else {
            hql = "FROM RoleViewmodelEntity WHERE menulevel<>:menulevel and owner =:owner and  menuId IN  (" +
                    "SELECT DISTINCT e.menuId FROM  RoleRolesetEntity a,RoleRoleanthorityEntity b,RoleRolesetmapEntity c,RoleRoleEntity d ,RoleRolebuttonmapEntity e" +
                    " WHERE a.setId = b.setId AND a.setId = c.setId AND c.rolRoleId = d.roleId  AND d.roleId=e.roleId" +
                    " AND b.staffId = :userid  and a.setState='01') and belong ='1'  order by menuId,menuorder";
        }

        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("menulevel", "1");
        query.setParameter("owner", property);
        if (!StringUtil.isEmpty(str)) {
            query.setParameterList("menuIds", menuId);
        } else {
            query.setParameter("userid", userid);
        }
        List<RoleViewmodelEntity> viewmodels = query.list();
        if (viewmodels == null || viewmodels.size() <= 0) {
            return null;
        }
        return viewmodels;
    }

    @Override
    public List<RoleViewmodelEntity> getViewListByUserId(String property, String userid) {
        if (userid == null || "".equals(userid.trim())) {
            return null;
        }
        //根据用户ID判断所属组织架构是否有角色权限
        String hq = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
                " from UserAgencymapEntity where status='1' and staffId = :staffId)";
        Query queryh = getCurrentSession().createQuery(hq);
        queryh.setParameter("staffId", userid);
        List<String> paths = queryh.list();
        String str = "";
        if (paths != null && paths.size() > 0) {
            for (String ids : paths) {
                str += ids.replace("/", "','");
            }
        }
        String hql;
        List menuId = new ArrayList();
        if (!StringUtil.isEmpty(str)) {
            str = str.substring(2) + "'";
            String sql = "SELECT DISTINCT rd.DATA_ID FROM role_data rd LEFT JOIN role_agency ra ON ra.AGENCY_ID = rd.AUTHORITY_ID " +
                    "WHERE rd.DATA_TYPE = '2' AND rd.`STATUS` = '1' AND rd.AUTHORITY_TYPE = '1' " +
                    "AND rd.AUTHORITY_ID IN (" + str + ")";
            Query query = getCurrentSession().createSQLQuery(sql);
            List dataId = query.list();

            String menusql = "SELECT DISTINCT rrbm.MenuId FROM role_roleset rrs " +
                    "RIGHT JOIN role_roleanthority rrt ON rrt.setid = rrs.setid " +
                    "RIGHT JOIN role_rolesetmap rrsm ON rrsm.setid = rrs.setid " +
                    "RIGHT JOIN role_role rr ON rr.roleid = rrsm.rol_roleid " +
                    "RIGHT JOIN role_rolebuttonmap rrbm ON rr.roleid = rrbm.roleid " +
                    "WHERE rrs.SetState = '01' AND (rrt.StaffId = :userid ";
            if (dataId.size() > 0) {
                menusql += " OR rrs.setid IN (:setIds)";
            }
            menusql += ")";
            Query menuquery = getCurrentSession().createSQLQuery(menusql);
            menuquery.setParameter("userid", userid);
            if (dataId.size() > 0) {
                menuquery.setParameterList("setIds", dataId);
            }
            menuId = menuquery.list();

            hql = "FROM RoleViewmodelEntity WHERE menulevel=:menulevel and owner =:owner and  menuId IN  (:menuIds) and belong <>'6'and belong <>'2'  order by menuId,menuorder";

//            String sql = "select agencyId from AgencyEntity where agencyId in (" + str + ")";
//            Query query1 = getCurrentSession().createQuery(sql);
//            String authorIds = query1.getQueryString();
//            String sql1 = "select dataId from RoleDataEntity where dataType = '2' and status = '1' and " +
//                    "authorityType = '1' and authorityId in(" + authorIds + ")";
//            Query query2 = getCurrentSession().createQuery(sql1);
//            String setIds = query2.getQueryString();
//            hql = "FROM RoleViewmodelEntity WHERE menulevel=:menulevel and owner =:owner and  menuId IN  (" +
//                    "SELECT DISTINCT e.menuId FROM  RoleRolesetEntity a,RoleRoleanthorityEntity b,RoleRolesetmapEntity c,RoleRoleEntity d ,RoleRolebuttonmapEntity e" +
//                    " WHERE a.setId = c.setId AND (a.setId = b.setId or a.setId =any (" + setIds + ")) AND c.rolRoleId = d.roleId  AND d.roleId=e.roleId" +
//                    " AND b.staffId = :userid  and a.setState='01')  order by menuId,menuorder";
        } else {
            hql = "FROM RoleViewmodelEntity WHERE menulevel=:menulevel and owner =:owner and  menuId IN  (" +
                    "SELECT DISTINCT e.menuId FROM  RoleRolesetEntity a,RoleRoleanthorityEntity b,RoleRolesetmapEntity c,RoleRoleEntity d ,RoleRolebuttonmapEntity e" +
                    " WHERE a.setId = b.setId AND a.setId = c.setId AND c.rolRoleId = d.roleId  AND d.roleId=e.roleId" +
                    " AND b.staffId = :userid  and a.setState='01') and belong <>'6' and belong <>'2' order by menuId,menuorder";
        }

//        String hql= "FROM RoleViewmodelEntity WHERE menulevel=:menulevel and owner =:owner and  menuId IN  (" +
//                "SELECT DISTINCT e.menuId FROM  RoleRolesetEntity a,RoleRoleanthorityEntity b,RoleRolesetmapEntity c,RoleRoleEntity d ,RoleRolebuttonmapEntity e" +
//                " WHERE a.setId = b.setId AND a.setId = c.setId AND c.rolRoleId = d.roleId  AND d.roleId=e.roleId" +
//                " AND b.staffId = :userid and a.setState='01') order by menuorder";

        Query query = getCurrentSession().createQuery(hql);
        if (!StringUtil.isEmpty(str)) {
            query.setParameterList("menuIds", menuId);
        } else {
            query.setParameter("userid", userid);
        }
        query.setParameter("menulevel", "1");
        query.setParameter("owner", property);
        List<RoleViewmodelEntity> viewmodels = query.list();
        if (viewmodels == null || viewmodels.size() <= 0) {
            return null;
        }
        return viewmodels;
    }

    @Override
    public List<RoleViewmodelEntity> getAuthViewListByUserId(String property, String userid) {
        if (userid == null || "".equals(userid.trim())) {
            return null;
        }
        //根据用户ID判断所属组织架构是否有角色权限
        String hq = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
                " from UserAgencymapEntity where status='1' and staffId = :staffId)";
        Query queryh = getCurrentSession().createQuery(hq);
        queryh.setParameter("staffId", userid);
        List<String> paths = queryh.list();
        String str = "";
        if (paths != null && paths.size() > 0) {
            for (String ids : paths) {
                str += ids.replace("/", "','");
            }
        }
        String hql;
        List menuId = new ArrayList();
        if (!StringUtil.isEmpty(str)) {
            str = str.substring(2) + "'";
            String sql = "SELECT DISTINCT rd.DATA_ID FROM role_data rd LEFT JOIN role_agency ra ON ra.AGENCY_ID = rd.AUTHORITY_ID " +
                    "WHERE rd.DATA_TYPE = '2' AND rd.`STATUS` = '1' AND rd.AUTHORITY_TYPE = '1' " +
                    "AND rd.AUTHORITY_ID IN (" + str + ")";
            Query query = getCurrentSession().createSQLQuery(sql);
            List dataId = query.list();

            String menusql = "SELECT DISTINCT rrbm.MenuId FROM role_roleset rrs " +
                    "RIGHT JOIN role_roleanthority rrt ON rrt.setid = rrs.setid " +
                    "RIGHT JOIN role_rolesetmap rrsm ON rrsm.setid = rrs.setid " +
                    "RIGHT JOIN role_role rr ON rr.roleid = rrsm.rol_roleid " +
                    "RIGHT JOIN role_rolebuttonmap rrbm ON rr.roleid = rrbm.roleid " +
                    "WHERE rrs.SetState = '01' AND (rrt.StaffId = :userid ";
            if (dataId.size() > 0) {
                menusql += " OR rrs.setid IN (:setIds)";
            }
            menusql += ")";
            Query menuquery = getCurrentSession().createSQLQuery(menusql);
            menuquery.setParameter("userid", userid);
            if (dataId.size() > 0) {
                menuquery.setParameterList("setIds", dataId);
            }
            menuId = menuquery.list();
            if(menuId !=null && menuId.size()>0){
                hql = "FROM RoleViewmodelEntity WHERE menulevel=:menulevel and owner =:owner and  menuId IN  (:menuIds) and belong ='1'  order by menuId,menuorder";
            }else{
                return null;
            }
        } else {
            hql = "FROM RoleViewmodelEntity WHERE menulevel=:menulevel and owner =:owner and  menuId IN  (" +
                    "SELECT DISTINCT e.menuId FROM  RoleRolesetEntity a,RoleRoleanthorityEntity b,RoleRolesetmapEntity c,RoleRoleEntity d ,RoleRolebuttonmapEntity e" +
                    " WHERE a.setId = b.setId AND a.setId = c.setId AND c.rolRoleId = d.roleId  AND d.roleId=e.roleId" +
                    " AND b.staffId = :userid  and a.setState='01') and belong ='1' order by menuId,menuorder";
        }
        Query query = getCurrentSession().createQuery(hql);
        if (!StringUtil.isEmpty(str)) {
            query.setParameterList("menuIds", menuId);
        } else {
            query.setParameter("userid", userid);
        }
        query.setParameter("menulevel", "1");
        query.setParameter("owner", property);
        List<RoleViewmodelEntity> viewmodels = query.list();
        if (viewmodels == null || viewmodels.size() <= 0) {
            return null;
        }
        return viewmodels;
    }


    @Override
    public RoleViewmodelEntity getLastFirVeiwModel() {
        RoleViewmodelEntity roleViewmodelEntity = new RoleViewmodelEntity();
        String hql = "from RoleViewmodelEntity as r where r.menulevel = '1' order by r.menuId desc";
        Query query = getCurrentSession().createQuery(hql);
        if (query.list().size() > 0) {
            roleViewmodelEntity = (RoleViewmodelEntity) query.list().get(0);
        }
        return roleViewmodelEntity;
    }

    @Override
    public boolean addViewModel(RoleViewmodelEntity roleViewmodelEntity) {
        Session session = getCurrentSession();
        session.save(roleViewmodelEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public RoleViewmodelEntity getLastSecViewModel(String parId) {
        RoleViewmodelEntity roleViewmodelEntity = new RoleViewmodelEntity();
        String hql = "from RoleViewmodelEntity as r where r.parantmenuid = :parantmenuid order by r.menuorder desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("parantmenuid", parId);
        if (query.list().size() > 0) {
            roleViewmodelEntity = (RoleViewmodelEntity) query.list().get(0);
        }
        return roleViewmodelEntity;
    }

    @Override
    public RoleViewmodelEntity getModelById(String menuId) {
        RoleViewmodelEntity roleViewmodelEntity = new RoleViewmodelEntity();
        String hql = "from RoleViewmodelEntity as r where r.menuId = :menuId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("menuId", menuId);
        if (query.list().size() > 0) {
            roleViewmodelEntity = (RoleViewmodelEntity) query.list().get(0);
        }
        return roleViewmodelEntity;
    }

    @Override
    public List<RoleViewmodelEntity> listMenu(String parId, WebPage webPage) {
        StringBuffer hql = new StringBuffer("from RoleViewmodelEntity as r");
        List<Object> params = new ArrayList<Object>();
        if (!parId.equals("999")) {
            hql.append(" where r.parantmenuid =?");
            params.add(parId);
        } else {
            hql.append(" where r.menulevel = '1'");
        }
        hql.append(" ORDER BY r.menuorder");
        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }
        List<RoleViewmodelEntity> roleViewmodelEntities = (List<RoleViewmodelEntity>) getHibernateTemplate().find(hql.toString());
        return roleViewmodelEntities;
    }

    @Override
    public List<RoleViewmodelEntity> getMenuList(String roleId, String roleSetId) {
        String hql = "from RoleViewmodelEntity where owner = 'app' and menuId in (select distinct menuId from RoleRolebuttonmapEntity where roleId =:roleId and buttonId =:roleSetId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("roleId", roleId);
        query.setParameter("roleSetId", roleSetId);
        List<RoleViewmodelEntity> roleViewmodelEntities = query.list();
        return roleViewmodelEntities;
    }

    @Override
    public List<RoleViewmodelEntity> appRoleMenus(String appRoleId) {
        String hql = "from RoleViewmodelEntity where owner = 'app' and menuId in (select distinct menuId from RoleRolebuttonmapEntity where roleId =:appRoleId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appRoleId", appRoleId);
        List<RoleViewmodelEntity> roleViewmodelEntities = query.list();
        return roleViewmodelEntities;
    }

    @Override
    public List<RoleViewmodelEntity> appMenuList(String roleSetId) {
        String hql = "from RoleViewmodelEntity where owner = 'app' and menuId in (select distinct menuId from RoleRolebuttonmaoEntity where buttonId=:roleSetId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("roleSetId", roleSetId);
        List<RoleViewmodelEntity> roleViewmodelEntities = query.list();
        return roleViewmodelEntities;
    }

    @Override
    public List<RoleViewmodelEntity> getAuthV0iewListByIdList(List<String> viewIdList,String belong1) {
        String hql = "from RoleViewmodelEntity where belong=:belong1 and menuId in (:viewIdList)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameterList("viewIdList", viewIdList);
        query.setParameter("belong1", belong1);
        List<RoleViewmodelEntity> roleViewmodelEntities = query.list();
        return roleViewmodelEntities;
    }
}
