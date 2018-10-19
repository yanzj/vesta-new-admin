package com.maxrocky.vesta.presistent.clientAccredit.repositoryHbnImpl;

import com.maxrocky.vesta.domain.clientAccredit.repository.ClientUserAccreditRepository;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.projectAccredit.repository.ProjectUserAccreditRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by hp on 2018/5/10.
 */
@Repository
public class ClientUserAccreditRepositoryImpl implements ClientUserAccreditRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    @Override
    public List<AuthAgencyQCEntity> getClientAgencyListAll() {
        StringBuilder hql = new StringBuilder(" FROM  AuthAgencyQCEntity where 1=1 ");
        hql.append( " and status= '1' " );
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        List<AuthAgencyQCEntity> list = query.list();
        return list;
    }

    @Override
    public List<AuthAgencyQCEntity> getClientAgencyListAllByIds(List<String> updateProject) {
        StringBuilder hql = new StringBuilder(" FROM  AuthAgencyQCEntity where 1=1 ");
        hql.append( " and status= '1' and agencyId in (:agencyId) " );
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        query.setParameterList("agencyId",updateProject);
        List<AuthAgencyQCEntity> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getAccreditManageListByCondition(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder(" SELECT ui.STAFF_ID,ui.USER_NAME,ui.SYSNAME,um.SOURCE_FROM,arat.AGENCY_NAME,arr.ROLE_NAME,rspm.MODIFY_ON,ui.STAFF_NAME,rspm.ID,arat.AGENCY_ID ");
        sql.append( " FROM user_map um " );//人员启用状态表
        sql.append( " LEFT JOIN user_information ui ON um.STAFF_ID = ui.STAFF_ID " );//人员信息表
        sql.append( " LEFT JOIN user_agency ua ON um.STAFF_ID = ua.STAFF_ID " );//人员与组织架构关系表
        sql.append( " LEFT JOIN auth_outer_role_agency aora ON aora.AGENCY_ID = ua.AGENCY_ID " );//组织架构表
        sql.append( " LEFT JOIN role_staff_projectqc_map rspm ON rspm.STAFF_ID = ui.STAFF_ID ");//人员与角色与项目层级关系表
        sql.append( " LEFT JOIN auth_role_roleset arr ON arr.ROLE_ID = rspm.ROLE_ID" );//角色表
        sql.append( " LEFT JOIN auth_role_agencyqc arat ON arat.AGENCY_ID = rspm.AGENCY_ID " );//项目层级表
        sql.append( " WHERE um.STATE = '1' AND aora.STATUS='1' AND arat.STATUS='1' AND rspm.STATE='1' AND ui.STAFF_STATE = '1' AND arr.STATE='0' AND arr.CATEGORY='1' AND um.PROJECT_MODULE='qc' AND (aora.TEMPORARY_SCOPE='qc' OR aora.TEMPORARY_SCOPE is null) " );
        //机构Id
        if(!StringUtil.isEmpty(map.get("agencyId").toString())){
            sql.append( " AND aora.AGENCY_ID =? " );
            params.add(map.get("agencyId").toString());
        }
        //姓名
        if(!StringUtil.isEmpty(map.get("staffName").toString())){
            sql.append( " AND ui.STAFF_NAME like? " );
            params.add(map.get("staffName").toString());
        }
        //授权项目层级
        if(!StringUtil.isEmpty(map.get("projectName").toString())){
            sql.append( " AND arat.AGENCY_NAME like? " );
            params.add(map.get("projectName").toString());
        }
        //OA账号
        if(!StringUtil.isEmpty(map.get("userName").toString())){
            sql.append( " AND ui.USER_NAME like? " );
            params.add(map.get("userName").toString());
        }
        //系统账号
        if(!StringUtil.isEmpty(map.get("sysName").toString())){
            sql.append( " AND ui.SYSNAME like? " );
            params.add(map.get("sysName").toString());
        }
        //角色
        if(!StringUtil.isEmpty(map.get("role").toString())){
            sql.append( " AND arr.ROLE_NAME like? " );
            params.add(map.get("role").toString());
        }
        sql.append(" order by ui.STAFF_ID,ui.MODIFY_ON DESC");
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(sql.toString());
        for (int i = 0,length = params.size(); i < length; i++){
            query.setParameter(i,params.get(i));
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public List<AuthAgencyEntity> getAllAgencyMessage() {
        StringBuilder hql = new StringBuilder( " FROM  AuthAgencyEntity where 1=1 " );
        hql.append( " and status= '1' " );
        Query query = getCurrentSession().createQuery(hql.toString());
        List<AuthAgencyEntity> list = query.list();
        return list;
    }

    @Override
    public List<AuthOuterAgencyEntity> getClientAuthOuterAgency() {
        StringBuilder hql = new StringBuilder( " FROM  AuthOuterAgencyEntity where 1=1 " );
        hql.append( " and (temporaryScope='qc' or temporaryScope is null) order by agencyType,orderNum " );
        Query query = getCurrentSession().createQuery(hql.toString());
        List<AuthOuterAgencyEntity> list = query.list();
        return list;
    }

    @Override
    public List<AuthOuterAgencyEntity> getOwnerAuthOuterAgency() {
        StringBuilder hql = new StringBuilder( " FROM  AuthOuterAgencyEntity where 1=1 " );
        hql.append( " and status= '1' and outEmploy='0' order by orderNum " );
        Query query = getCurrentSession().createQuery(hql.toString());
        List<AuthOuterAgencyEntity> list = query.list();
        return list;
    }


    @Override
    public List<Object[]> getUserByAgencyId(String agencyId) {
        StringBuilder sql = new StringBuilder( " SELECT ui.STAFF_ID,ui.STAFF_NAME,ui.USER_NAME,ui.SYSNAME " );
        sql.append( " FROM user_map um " );//用户关系表
        sql.append( " LEFT JOIN user_information ui ON ui.STAFF_ID = um.STAFF_ID " );//用户表
        sql.append( " LEFT JOIN user_agency ua ON ua.STAFF_ID = ui.STAFF_ID " );//人员与组织架构关联关系表
        sql.append( " LEFT JOIN role_agency ra ON ra.AGENCY_ID = ua.AGENCY_ID " );//组织架构表
        sql.append( " WHERE um.STATE='1' AND ui.STAFF_STATE='1' AND ua.STATUS='1' AND ra.STATUS='1' " );
        sql.append( " AND ua.AGENCY_ID=:agencyId" );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("agencyId",agencyId);
        List list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getOuterUserByAgencyId(String agencyId,String category) {
        StringBuilder sql = new StringBuilder( " SELECT ui.STAFF_ID,ui.STAFF_NAME,ui.USER_NAME,ui.SYSNAME " );
        sql.append( " FROM user_map um " );//用户关系表
        sql.append( " LEFT JOIN user_information ui ON ui.STAFF_ID = um.STAFF_ID " );//用户表
        sql.append( " LEFT JOIN user_agency ua ON ua.STAFF_ID = ui.STAFF_ID " );//人员与组织架构关联关系表
        sql.append( " LEFT JOIN auth_outer_role_agency aora ON aora.AGENCY_ID = ua.AGENCY_ID " );//组织架构表
        sql.append( " WHERE um.STATE='1' AND ui.STAFF_STATE='1' AND ua.STATUS='1' AND aora.STATUS='1' " );
        sql.append( " AND ua.AGENCY_ID=:agencyId AND um.PROJECT_MODULE=:category " );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("agencyId",agencyId);
        query.setParameter("category",category);
        List list = query.list();
        return list;
    }

    @Override
    public List<AuthRoleseEntity> getClientRoleByAgencyId(String agencyId) {
        StringBuilder hql = new StringBuilder( " FROM AuthRoleseEntity " );
        hql.append( " WHERE state='0' AND category='1' AND roleId in (SELECT authRoleId FROM AuthRoleProjectEntity where authAgencyId =:agencyId) " );
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("agencyId",agencyId);
        List<AuthRoleseEntity> list = query.list();
        return list;
    }

    @Override
    public List<AuthRoleseEntity> getOwnerRoleByAgencyId(String agencyId) {
        StringBuilder hql = new StringBuilder( " FROM AuthRoleseEntity " );
        hql.append( " WHERE state='0' AND roleType='0' AND category='1' AND roleId in (SELECT authRoleId FROM AuthRoleProjectEntity where authAgencyId =:agencyId) " );
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("agencyId",agencyId);
        List<AuthRoleseEntity> list = query.list();
        return list;
    }

    @Override
    public AuthAgencyQCEntity getAuthAgencyByAgencyId(String agencyId) {
        StringBuilder hql = new StringBuilder( " FROM AuthAgencyQCEntity " );
        hql.append( " WHERE 1=1 AND agencyId=:agencyId " );
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("agencyId",agencyId);
        AuthAgencyQCEntity authAgencyQCEntity = (AuthAgencyQCEntity) query.uniqueResult();
        return authAgencyQCEntity;
    }

    @Override
    public List<Object[]> getUserByName(String staffName,String category) {
        if(StringUtil.isEmpty(staffName)){
            return null;
        }
        StringBuilder sql = new StringBuilder( " SELECT ui.STAFF_ID,ui.STAFF_NAME,ui.USER_NAME,ui.SYSNAME " );
        sql.append( " FROM user_information ui " );
        sql.append( " LEFT JOIN user_map um ON ui.STAFF_ID = um.STAFF_ID " );
        sql.append( " WHERE ui.STAFF_STATE='1' AND um.STATE='1' AND um.PROJECT_MODULE='"+category+"' AND ui.STAFF_NAME like '%"+staffName+"%' " );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        List list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getOwnerUserByName(String staffName) {
        if(StringUtil.isEmpty(staffName)){
            return null;
        }
        StringBuilder sql = new StringBuilder( " SELECT ui.STAFF_ID,ui.STAFF_NAME,ui.USER_NAME,ui.SYSNAME " );
        sql.append( " FROM user_information ui " );
        sql.append( " LEFT JOIN user_map um ON ui.STAFF_ID = um.STAFF_ID " );
        sql.append( " WHERE ui.STAFF_STATE='1' AND um.STATE='1' AND ui.JINMAO_IS='1' AND um.PROJECT_MODULE='qc' AND ui.STAFF_NAME like '%"+staffName+"%' " );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        List list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getRoleByName(String roleName) {
        if(StringUtil.isEmpty(roleName)){
            return null;
        }
        StringBuilder sql = new StringBuilder( " SELECT arr.ROLE_ID,arr.ROLE_NAME,arp.AUTH_AGENCY_ID,ara.AGENCY_NAME " );
        sql.append( " FROM auth_role_roleset arr " );
        sql.append( " LEFT JOIN auth_role_project arp ON arr.ROLE_ID = arp.AUTH_ROLE_ID " );
        sql.append( " LEFT JOIN auth_role_agencyqc ara ON ara.AGENCY_ID = arp.AUTH_AGENCY_ID " );
        sql.append( " WHERE arr.STATE='0' AND arr.CATEGORY='1' AND ara.STATUS='1' AND arr.ROLE_NAME like '%"+roleName+"%' " );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        List list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getRoleByName(String roleName, List<String> updateProject) {
        if(StringUtil.isEmpty(roleName)){
            return null;
        }
        StringBuilder sql = new StringBuilder( " SELECT arr.ROLE_ID,arr.ROLE_NAME,arp.AUTH_AGENCY_ID,ara.AGENCY_NAME " );
        sql.append( " FROM auth_role_roleset arr " );
        sql.append( " LEFT JOIN auth_role_project arp ON arr.ROLE_ID = arp.AUTH_ROLE_ID " );
        sql.append( " LEFT JOIN auth_role_agencyqc ara ON ara.AGENCY_ID = arp.AUTH_AGENCY_ID " );
        sql.append( " WHERE arr.STATE='0' AND arr.CATEGORY='1' AND ara.STATUS='1' AND arr.ROLE_NAME like '%"+roleName+"%' AND arp.AUTH_AGENCY_ID in (:agencyId) " );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameterList("agencyId",updateProject);
        List list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getOwnerRolerByName(String roleName, List<String> updateProject) {
        if(StringUtil.isEmpty(roleName)){
            return null;
        }
        StringBuilder sql = new StringBuilder( " SELECT arr.ROLE_ID,arr.ROLE_NAME,arp.AUTH_AGENCY_ID,ara.AGENCY_NAME " );
        sql.append( " FROM auth_role_roleset arr " );
        sql.append( " LEFT JOIN auth_role_project arp ON arr.ROLE_ID = arp.AUTH_ROLE_ID " );
        sql.append( " LEFT JOIN auth_role_agencyqc ara ON ara.AGENCY_ID = arp.AUTH_AGENCY_ID " );
        sql.append( " WHERE arr.STATE='0' AND arr.ROLE_TYPE='0' AND arr.CATEGORY='1' AND ara.STATUS='1' AND arr.ROLE_NAME like '%"+roleName+"%' AND arp.AUTH_AGENCY_ID in (:agencyId) " );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameterList("agencyId",updateProject);
        List list = query.list();
        return list;
    }

    @Override
    public void saveOrUpdateProjectRoleStaff(RoleStaffProjectMapQCEntity roleStaffProjectMapQCEntity) {
        StringBuilder sql = new StringBuilder(" INSERT INTO role_staff_projectqc_map(STAFF_ID,ROLE_ID,AGENCY_ID,STATE,MODIFY_ON) VALUES(?,?,?,1,?) ON DUPLICATE KEY UPDATE STATE='1',MODIFY_ON=? ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setString(0, roleStaffProjectMapQCEntity.getStaffId());
        query.setString(1, roleStaffProjectMapQCEntity.getRoleId());
        query.setString(2, roleStaffProjectMapQCEntity.getAgencyId());
        query.setString(3, DateUtils.format(new Date()));
        query.setString(4, DateUtils.format(new Date()));
        query.executeUpdate();
    }

    @Override
    public RoleStaffProjectMapQCEntity getRoleStaffProjectMapById(String id) {
        StringBuilder hql = new StringBuilder(" FROM RoleStaffProjectMapQCEntity ");
        hql.append(" WHERE 1=1 AND id='"+id+"'");
        Query query = getCurrentSession().createQuery(hql.toString());
        RoleStaffProjectMapQCEntity roleStaffProjectMapQCEntity =(RoleStaffProjectMapQCEntity) query.uniqueResult();
        return roleStaffProjectMapQCEntity;
    }

    @Override
    public void saveClientRoleStaffProjectMap(RoleStaffProjectMapQCEntity roleStaffProjectMapQCEntity) {
        Session session = getCurrentSession();
        session.update(roleStaffProjectMapQCEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<String> getClientAuthFunctionAndProjectIdByStaffId(String function, String staffId, String level) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" asp.AGENCY_ID ");
        sql.append(" FROM role_staff_projectqc_map asp ");
        sql.append(" LEFT JOIN auth_function_point_role afp ON afp.AUTH_ROLE_ID = asp.ROLE_ID ");
        sql.append(" LEFT JOIN auth_function_point af on afp.AUTH_FUNCTION_ID=af.CURRENT_ID ");
        sql.append(" where af.STATE='0' and af.CATEGORY='1' and af.CLASSIFICATION='2' and afp.STATE='0' and asp.STATE='1' ");

        sql.append(" and  af.`LEVEL`=:level and asp.STAFF_ID =:staffId and afp.AUTH_FUNCTION_ID=:function ");
        sql.append(" GROUP BY asp.AGENCY_ID");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("level", level);
        query.setParameter("staffId", staffId);
        query.setParameter("function",function);
        List<String> list = query.list();
        return list;
    }
}
