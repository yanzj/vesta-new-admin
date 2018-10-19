package com.maxrocky.vesta.presistent.projectSyncForApp.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.RoleStaffProjectMapESAppEntity;
import com.maxrocky.vesta.domain.projectSyncForApp.repository.ProjectSyncForRepository;
import com.maxrocky.vesta.utility.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by yuanyn on 2018/4/24.
 * app项目同步Repository实现
 */
@Repository
public class ProjectSyncForAppRepositoryImpl implements ProjectSyncForRepository{

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<Object[]> getUserIdByProjectId(String agencyId) {
        StringBuilder sql = new StringBuilder(" SELECT DISTINCT ui.STAFF_ID,ui.STAFF_NAME,ui.SYSNAME ");
        sql.append( " FROM user_map um " );//人员启用状态表
        sql.append( " LEFT JOIN user_information ui ON um.STAFF_ID = ui.STAFF_ID " );//人员信息表
        sql.append( " LEFT JOIN role_staff_projectes_map rspm ON rspm.STAFF_ID = ui.STAFF_ID ");//人员与角色与项目层级关系表
        sql.append( " LEFT JOIN auth_role_roleset arr ON arr.ROLE_ID = rspm.ROLE_ID" );//角色表
        sql.append( " LEFT JOIN auth_function_point_role afpr ON arr.ROLE_ID = afpr.AUTH_ROLE_ID" );//角色功能点关系表
        sql.append( " LEFT JOIN auth_role_agencyes arat ON arat.AGENCY_ID = rspm.AGENCY_ID " );//项目层级表
        sql.append( " WHERE um.STATE = '1' AND arat.STATUS='1' AND rspm.STATE='1' AND ui.STAFF_STATE = '1' AND arr.STATE='0' AND arr.CATEGORY='2' AND afpr.STATE='0' AND um.PROJECT_MODULE='es' AND afpr.CLASSIFICATION='1' AND afpr.CATEGORY='2' " );
        sql.append( " AND arat.AGENCY_ID=:agencyId " );
        sql.append(" ORDER BY ui.STAFF_ID,ui.MODIFY_ON DESC");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("agencyId",agencyId);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<String> getProjectNameByUserId(String userId) {
        StringBuilder sql = new StringBuilder(" SELECT DISTINCT ara.AGENCY_NAME FROM ");
        sql.append(" role_staff_projectes_map rspm  ");
        sql.append(" LEFT JOIN auth_function_point_role afpr ON rspm.ROLE_ID = afpr.AUTH_ROLE_ID ");
        sql.append(" LEFT JOIN auth_role_agencyes ara ON ara.AGENCY_ID = rspm.AGENCY_ID ");
        sql.append(" WHERE afpr.CLASSIFICATION='1' AND afpr.STATE='0' AND afpr.CATEGORY='2' AND rspm.STATE='1' ");
        sql.append(" AND rspm.STAFF_ID=:userId ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("userId",userId);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<String> getProjectIdByUserId(String userId) {
        StringBuilder sql = new StringBuilder(" SELECT DISTINCT ara.AGENCY_ID FROM ");
        sql.append(" role_staff_projectes_map rspm  ");
        sql.append(" LEFT JOIN auth_function_point_role afpr ON rspm.ROLE_ID = afpr.AUTH_ROLE_ID ");
        sql.append(" LEFT JOIN auth_role_agencyes ara ON ara.AGENCY_ID = rspm.AGENCY_ID ");
        sql.append(" WHERE afpr.CLASSIFICATION='1' AND afpr.STATE='0' AND afpr.CATEGORY='2' AND rspm.STATE='1' ");
        sql.append(" AND rspm.STAFF_ID=:userId ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("userId",userId);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<String> getProjectIdByRoleId(String roleId, String userId) {
        StringBuilder sql = new StringBuilder(" SELECT DISTINCT ara.AGENCY_ID FROM ");
        sql.append(" role_staff_projectes_map rspm  ");
        sql.append(" LEFT JOIN auth_function_point_role afpr ON rspm.ROLE_ID = afpr.AUTH_ROLE_ID ");
        sql.append(" LEFT JOIN auth_role_agencyes ara ON ara.AGENCY_ID = rspm.AGENCY_ID ");
        sql.append(" WHERE afpr.CLASSIFICATION='1' AND afpr.STATE='0' AND afpr.CATEGORY='2' AND rspm.STATE='1' ");
        sql.append(" AND rspm.ROLE_ID=:roleId AND rspm.STAFF_ID=:userId ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("roleId",roleId);
        query.setParameter("userId",userId);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<String> getProjectAppNameByUserId(String userId) {
        StringBuilder sql = new StringBuilder(" SELECT DISTINCT ara.AGENCY_NAME FROM ");
        sql.append(" role_staff_projectes_map_app rspma  ");
        sql.append(" LEFT JOIN auth_function_point_role afpr ON rspma.ROLE_ID = afpr.AUTH_ROLE_ID ");
        sql.append(" LEFT JOIN auth_role_agencyes ara ON ara.AGENCY_ID = rspma.AGENCY_ID ");
        sql.append(" WHERE afpr.CLASSIFICATION='1' AND afpr.STATE='0' AND afpr.CATEGORY='2' AND rspma.STATE='1' ");
        sql.append(" AND rspma.STAFF_ID=:userId ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("userId",userId);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getRoleByUserId(String userId) {
        StringBuilder sql = new StringBuilder(" SELECT DISTINCT arr.ROLE_ID,arr.ROLE_NAME FROM ");
        sql.append(" role_staff_projectes_map rspm  ");
        sql.append(" LEFT JOIN auth_function_point_role afpr ON rspm.ROLE_ID = afpr.AUTH_ROLE_ID ");
        sql.append(" LEFT JOIN auth_role_roleset arr ON arr.ROLE_ID = rspm.ROLE_ID ");
        sql.append(" WHERE afpr.CLASSIFICATION='1' AND afpr.STATE='0' AND afpr.CATEGORY='2' AND rspm.STATE='1' AND arr.STATE='0' AND arr.CATEGORY='2' ");
        sql.append(" AND rspm.STAFF_ID=:userId ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("userId",userId);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getRoleIdByUserId(String userId) {
        StringBuilder sql = new StringBuilder(" SELECT DISTINCT arr.ROLE_ID,arr.ROLE_NAME FROM ");
        sql.append(" role_staff_projectes_map_app rspma  ");
        sql.append(" LEFT JOIN auth_role_roleset arr ON arr.ROLE_ID = rspma.ROLE_ID ");
        sql.append(" WHERE rspma.STATE='1' AND arr.STATE='0' AND arr.CATEGORY='2' ");
        sql.append(" AND rspma.STAFF_ID=:userId ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("userId",userId);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<RoleStaffProjectMapESAppEntity> getRoleStaffProjectMapEntity(String roleId, String userId) {
        StringBuilder hql = new StringBuilder(" FROM RoleStaffProjectMapESAppEntity WHERE state='1' AND roleId=:roleId AND staffId=:userId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("roleId",roleId);
        query.setParameter("userId",userId);
        List list = query.list();
        return list;
    }

    @Override
    public void deleteRoleStaffProjectMapESAppEntity( String staffId) {
        StringBuilder hql = new StringBuilder(" UPDATE RoleStaffProjectMapESAppEntity SET state='0',modifyOn=:modifyOn WHERE staffId=:staffId  ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("modifyOn",new Date());
        query.setParameter("staffId",staffId);
        query.executeUpdate();
    }

    @Override
    public void saveRoleStaffProjectMapESAppEntity(RoleStaffProjectMapESAppEntity roleStaffProjectMapESAppEntity) {
        StringBuilder sql = new StringBuilder(" INSERT INTO role_staff_projectes_map_app(STAFF_ID,ROLE_ID,AGENCY_ID,STATE,MODIFY_ON) VALUES(?,?,?,1,?) ON DUPLICATE KEY UPDATE STATE='1',MODIFY_ON=? ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setString(0, roleStaffProjectMapESAppEntity.getStaffId());
        query.setString(1, roleStaffProjectMapESAppEntity.getRoleId());
        query.setString(2, roleStaffProjectMapESAppEntity.getAgencyId());
        query.setString(3, DateUtils.format(new Date()));
        query.setString(4, DateUtils.format(new Date()));
        query.executeUpdate();
    }
}
