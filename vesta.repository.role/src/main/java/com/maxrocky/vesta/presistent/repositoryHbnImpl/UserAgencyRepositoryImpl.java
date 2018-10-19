package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.UserAgencyRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
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
 * Created by chen on 2016/6/18.
 */
@Repository
public class UserAgencyRepositoryImpl extends HibernateDaoImpl implements UserAgencyRepository {
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addUserAgency(UserAgencymapEntity userAgencymapEntity) {
        Session session = getCurrentSession();
        session.save(userAgencymapEntity);
        session.flush();
    }

    @Override
    public void addDumpUserAgency(UserAgencymapEntity userAgencymapEntity) {
        String sql = "INSERT INTO user_agency_map(MAP_ID,STAFF_ID,AGENCY_ID,MODIFY_TIME,STATUS) VALUES(?,?,?,?,1) ON DUPLICATE KEY UPDATE STATUS='1',MODIFY_TIME=?";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setString(0, userAgencymapEntity.getMapId());
        query.setString(1, userAgencymapEntity.getStaffId());
        query.setString(2, userAgencymapEntity.getAgencyId());
        query.setString(3, DateUtils.format(new Date()));
        query.setString(4, DateUtils.format(new Date()));
        query.executeUpdate();
    }

    @Override
    public void addOAUserAgency(UserAgencymapEntity userAgencymapEntity) {
        String sql = "INSERT INTO user_agency_map(MAP_ID,STAFF_ID,AGENCY_ID,MODIFY_TIME,STATUS) VALUES(?,?,?,?,1) ON DUPLICATE KEY UPDATE STATUS='1',MODIFY_TIME=?";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setString(0, userAgencymapEntity.getMapId());
        query.setString(1, userAgencymapEntity.getStaffId());
        query.setString(2, userAgencymapEntity.getAgencyId());
        query.setString(3, DateUtils.format(userAgencymapEntity.getModifyTime()));
        query.setString(4, DateUtils.format(userAgencymapEntity.getModifyTime()));
        query.executeUpdate();
    }

    @Override
    public void addUserAgency(UserAgencyEntity userAgencyEntity) {
        String sql = "INSERT INTO user_agency(MAP_ID,STAFF_ID,AGENCY_ID,MODIFY_TIME,STATUS) VALUES(?,?,?,?,1) ON DUPLICATE KEY UPDATE STATUS='1',MODIFY_TIME=?";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setString(0, userAgencyEntity.getMapId());
        query.setString(1, userAgencyEntity.getStaffId());
        query.setString(2, userAgencyEntity.getAgencyId());
        query.setString(3, DateUtils.format(userAgencyEntity.getModifyTime()));
        query.setString(4, DateUtils.format(userAgencyEntity.getModifyTime()));
        query.executeUpdate();
    }

    @Override
    public void updateUserAgency(String agencyId) {
        String hql = "update UserAgencymapEntity set status = '0',modifyTime=:modifyTime where agencyId = :agencyId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime", new java.util.Date());
        query.setParameter("agencyId", agencyId);
        query.executeUpdate();
    }

    @Override
    public void delUserAgency(UserAgencymapEntity userAgencymapEntity) {
        String hql = "update UserAgencymapEntity set status = '0',modifyTime=:modifyTime where staffId = :staffId and agencyId=:agencyId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId", userAgencymapEntity.getStaffId());
        query.setParameter("modifyTime", new java.util.Date());
        query.setParameter("agencyId", userAgencymapEntity.getAgencyId());
        query.executeUpdate();
    }

    @Override
    public void deleteUserAgency(String staffId) {
        String hql = "update UserAgencymapEntity set status = '0',modifyTime=:modifyTime where staffId = :staffId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId", staffId);
        query.setParameter("modifyTime", new Date());
        query.executeUpdate();
    }

    @Override
    public List<UserAgencymapEntity> getUserAgencymap(String staffId) {
        String hql = "from UserAgencymapEntity where staffId=:staffId and status='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId", staffId);
        List<UserAgencymapEntity> userAgencymapEntities = query.list();
        return userAgencymapEntities;
    }

    @Override
    public List<UserPropertyStaffEntity> getStaffByAgencyId(String agencyId) {
        String hql = "from UserPropertyStaffEntity where staffState='1' and staffId in (select staffId from UserAgencymapEntity where status = '1' and agencyId =:agencyId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("agencyId", agencyId);
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        return userPropertyStaffEntities;
    }

    @Override
    public List<UserInformationEntity> getStaffByAgencyId(List<String> agencyIds) {
        String hql = "from UserInformationEntity where staffId in (select staffId from UserAgencyEntity where status = '1' and agencyId in (:agencyIds) )";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameterList("agencyIds", agencyIds);
        List<UserInformationEntity> userInformationEntities = query.list();
        return userInformationEntities;
    }

    @Override
    public List<UserAgencyEntity> getStaffIdByAgencyId(List<String> agencyIds) {
        String hql = " from UserAgencyEntity where status = '1' and agencyId in (:agencyIds) ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameterList("agencyIds", agencyIds);
        List<UserAgencyEntity> userAgencyEntities = query.list();
        return userAgencyEntities;
    }

    @Override
    public List<UserPropertyStaffEntity> getStaffListByAgencyId(String agencyId, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from UserPropertyStaffEntity where staffState='1' and staffId in (select staffId from UserAgencymapEntity where status = '1' and agencyId ='" + agencyId + "') order by orderNum";
        Query query = getCurrentSession().createQuery(hql);
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }
        return userPropertyStaffEntities;
    }

    @Override
    public List<AppRolesetEntity> getRoleSetFromAgency(String staffId) {
        List<AppRolesetEntity> appRolesetEntityList = new ArrayList<AppRolesetEntity>();
        String hql = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
                " from UserAgencymapEntity where status='1' and staffId = :staffId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId", staffId);
        List<String> paths = query.list();
        String str = "";
        if (paths != null && paths.size() > 0) {
            for (String ids : paths) {
                str += ids.replace("/", "','");
            }
        }
        if (!StringUtil.isEmpty(str)) {
            str = str.substring(2) + "'";
            String sql = "select agencyId from AgencyEntity where agencyId in (" + str + ")";
            Query query1 = getCurrentSession().createQuery(sql);
            String agencyIds = query1.getQueryString();
            String sql1 = "select dataId from RoleDataEntity where status = '1' and dataType = '2' and authorityType = '1' and authorityId in(" + agencyIds + ")";
            Query query2 = getCurrentSession().createQuery(sql1);
            String appRoleSetIds = query2.getQueryString();
            String hql1 = "from AppRolesetEntity where appSetState='1' and appSetId in (" + appRoleSetIds + ")";
            Query query3 = getCurrentSession().createQuery(hql1);
            if (!StringUtil.isEmpty(agencyIds) && !StringUtil.isEmpty(appRoleSetIds)) {
                appRolesetEntityList = query3.list();
            }
        }
        return appRolesetEntityList;
//        String hql = "select dataId from RoleDataEntity where status = '1' and dataType = '2' and authorityType = '1' and " +
//                "authorityId in (select agencyId from UserAgencymapEntity where status = '1' and staffId='"+staffId+"')";
//        Query query = getCurrentSession().createQuery(hql);
//        String roleSetIds = query.getQueryString();
//        String sql = "from AppRolesetEntity where appSetId in ("+roleSetIds+")";
//        Query query1 = getCurrentSession().createQuery(sql);
//        List<AppRolesetEntity> appRolesetEntityList = query1.list();
//        return appRolesetEntityList;
    }

    @Override
    public List<UserPropertyStaffEntity> getAgencyOutStaff(UserPropertyStaffEntity userPropertyStaffEntity) {
        String hql = "from UserPropertyStaffEntity where 1=1";
        if (!StringUtil.isEmpty(userPropertyStaffEntity.getStaffName())) {
            hql += " and staffName like '%" + userPropertyStaffEntity.getStaffName() + "%'";
        }
        if (!StringUtil.isEmpty(userPropertyStaffEntity.getUserName())) {
            hql += " and userName like '%" + userPropertyStaffEntity.getUserName() + "%'";
        }
        hql += " and staffState='1' and staffId not in (select staffId from UserAgencymapEntity where status = '1' and agencyId = '" + userPropertyStaffEntity.getProjectId() + "')";
        Query query = getCurrentSession().createQuery(hql);
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        return userPropertyStaffEntities;
    }

    @Override
    public List<UserPropertyStaffEntity> getStaffForNames(UserPropertyStaffEntity userPropertyStaffEntity, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from UserPropertyStaffEntity where 1=1 ";
        hql += " and staffState='1'";
        if (!StringUtil.isEmpty(userPropertyStaffEntity.getCompanyId()) && !userPropertyStaffEntity.getCompanyId().equals("1")) {
            hql += " and staffId in (select staffId from UserAgencymapEntity where status = '1' and agencyId ='" + userPropertyStaffEntity.getCompanyId() + "')";
        }
        if (!StringUtil.isEmpty(userPropertyStaffEntity.getStaffName())) {
            hql += " and staffName like '%" + userPropertyStaffEntity.getStaffName() + "%'";
        }
        if (!StringUtil.isEmpty(userPropertyStaffEntity.getUserName())) {
            hql += " and userName like '%" + userPropertyStaffEntity.getUserName() + "%'";
        }
        hql += " order by orderNum";
        Query query = getCurrentSession().createQuery(hql);
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }
        return userPropertyStaffEntities;
    }

    @Override
    public List<AgencyEntity> getAgencysByStaffId(String staffId) {
        String hql = "from AgencyEntity where agencyId in (select distinct agencyId from UserAgencymapEntity where status = '1' and staffId =:staffId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId", staffId);
        List<AgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public AgencyEntity getAgencysByStaffId(String staffId, String agencyId) {
        String hql = "from AgencyEntity where agencyId in (select distinct agencyId from UserAgencymapEntity where status = '1' and staffId =:staffId) and agencyId=:agencyId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId", staffId);
        query.setParameter("agencyId", agencyId);
        AgencyEntity agencyEntities = (AgencyEntity) query.uniqueResult();
        return agencyEntities;
    }

    @Override
    public void delOAUserAgency(String staffId) {
        String hql = "update UserAgencymapEntity set status = '0',modifyTime=:modifyTime where staffId = :staffId and sourceFrom='OA'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime", new Date());
        query.setParameter("staffId", staffId);
        query.executeUpdate();
    }

    @Override
    public void deleteUserAgency(String staffId, String agencyId) {
        String hql = "DELETE FROM UserAgencymapEntity  where staffId = :staffId AND agencyId=:agencyId and status='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId", staffId);
        query.setParameter("agencyId", agencyId);
        query.executeUpdate();
    }

    @Override
    public List<Object[]> getStaffInfoByAgencyId(String agencyId) {
        String sql = "SELECT ups.STAFF_ID,ups.STAFF_NAME FROM user_propertyStaff ups " +
                "LEFT JOIN user_agency_map uam ON uam.STAFF_ID = ups.STAFF_ID " +
                "WHERE ups.STAFF_STATE = '1' AND uam.`STATUS` = '1' AND uam.AGENCY_ID in(" + agencyId + ")";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<UserMapEntity> getUserMap() {
        String sql = "from UserMapEntity where state = '1' and projectModule = 'st' and sourceFrom ='OA' ";
        Query query = getCurrentSession().createQuery(sql);
        List<UserMapEntity> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getOAUserManageListByCondition(Map map, WebPage webPage,List<String> idList) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder(" SELECT ui.STAFF_ID,ui.USER_NAME,ui.SYSNAME,ui.MOBILE,ui.EMAIL,ra.AGENCY_NAME,ui.MODIFY_ON,ui.STAFF_NAME,um.STATE,ra.AGENCY_ID ");
        sql.append( " FROM user_information ui " );//人员信息表
        sql.append( " LEFT JOIN user_map um ON um.STAFF_ID = ui.STAFF_ID " );//人员启用状态表
        sql.append( " LEFT JOIN user_agency ua ON um.STAFF_ID = ua.STAFF_ID " );//人员与组织架构关系表
        sql.append( " LEFT JOIN auth_outer_role_agency ra ON ra.AGENCY_ID = ua.AGENCY_ID " );//组织架构表
        sql.append( " WHERE ra.STATUS='1' AND ui.STAFF_STATE = '1' AND ua.STATUS = '1' AND um.PROJECT_MODULE='st' AND ra.OUT_EMPLOY='0' " );
        //机构名称
        if(!StringUtil.isEmpty(map.get("agencyName").toString())){
            sql.append( " AND ra.AGENCY_NAME like ? " );
            params.add(map.get("agencyName").toString());
        }
        //人员
        if(!StringUtil.isEmpty(map.get("staffName").toString())){
            sql.append( " AND ui.STAFF_NAME like? " );
            params.add(map.get("staffName").toString());
        }
        //用户名
        if(!StringUtil.isEmpty(map.get("userName").toString())){
            sql.append( " AND ui.USER_NAME like? " );
            params.add(map.get("userName").toString());
        }
        //是否启用
        if(!StringUtil.isEmpty(map.get("isEnabled").toString())){
            sql.append( " AND um.STATE =? " );
            params.add(map.get("isEnabled").toString());
        }
        //手机号码
        if(!StringUtil.isEmpty(map.get("mobile").toString())){
            sql.append( " AND ui.MOBILE like? " );
            params.add(map.get("mobile").toString());
        }
        //邮箱
        if(!StringUtil.isEmpty(map.get("email").toString())){
            sql.append( " AND ui.EMAIL like? " );
            params.add(map.get("email").toString());
        }
        //机构Id
        if(null != idList && idList.size()>0){
            sql.append( " AND ra.AGENCY_ID IN (:agencyIds) " );
        }
        sql.append(" order by ui.MODIFY_ON DESC");
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(sql.toString());
        for (int i = 0,length = params.size(); i < length; i++){
            query.setParameter(i,params.get(i));
        }
        if(null != idList && idList.size()>0){
            query.setParameterList("agencyIds",idList);
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
    public void addOuterUserAgency(UserAgencyEntity userAgencyEntity) {
        String sql = "INSERT INTO user_agency(MAP_ID,STAFF_ID,AGENCY_ID,MODIFY_TIME,STATUS,SOURCE_FROM) VALUES(?,?,?,?,1,?) ON DUPLICATE KEY UPDATE STATUS='1',MODIFY_TIME=?";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setString(0, userAgencyEntity.getMapId());
        query.setString(1, userAgencyEntity.getStaffId());
        query.setString(2, userAgencyEntity.getAgencyId());
        query.setString(3, DateUtils.format(userAgencyEntity.getModifyTime()));
        query.setString(4, userAgencyEntity.getSourceFrom());
        query.setString(5, DateUtils.format(userAgencyEntity.getModifyTime()));
        query.executeUpdate();
    }

    @Override
    public void saveOrUpdateUserAgencyEntity(UserMapEntity userMapEntity) {
        Session session = getCurrentSession();
        session.save(userMapEntity);
        session.flush();
        session.close();
    }

    @Override
    public void saveOrUpdateUserMap(RoleStaffProjectMapEntity roleStaffProjectMapEntity) {
        Session session = getCurrentSession();
        session.save(roleStaffProjectMapEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<AgencyEntity> getAgencysByOA() {
        StringBuilder hql = new StringBuilder(" FROM AgencyEntity ");
        hql.append(" WHERE agencyId like '%dpx' OR agencyId like '%cpx' ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<AgencyEntity> list = query.list();
        return list;
    }

    @Override
    public List<AgencyEntity> getAgencysByOuter() {
        StringBuilder hql = new StringBuilder(" FROM AgencyEntity ");
        hql.append(" WHERE parentId='03d3df6a599747ef9bfa4332c0f919b6' ");
//        hql.append(" WHERE parentId='1442cfe2f277454c860b082c9bdf47bc' ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<AgencyEntity> list = query.list();
        return list;
    }

    @Override
    public void saveOrUpdateAgency(AuthOuterAgencyEntity authOuterAgencyEntity) {
        Session session = getCurrentSession();
        session.save(authOuterAgencyEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<UserPropertyStaffEntity> getStaff() {
        StringBuilder hql = new StringBuilder(" FROM UserPropertyStaffEntity ");
        hql.append(" WHERE createBy='OA同步' OR modifyBy='OA同步' ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<UserPropertyStaffEntity> list = query.list();
        return list;
    }

    @Override
    public List<UserPropertyStaffEntity> getStaffById(List<String> staffIds) {
        StringBuilder hql = new StringBuilder(" FROM UserPropertyStaffEntity ");
        hql.append(" WHERE staffId in (:staffIds) ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameterList("staffIds",staffIds);
        List<UserPropertyStaffEntity> list = query.list();
        return list;
    }

    @Override
    public void saveOrUpdateUser(UserInformationEntity userInformationEntity) {
        Session session = getCurrentSession();
        session.save(userInformationEntity);
        session.flush();
        session.close();
    }

    @Override
    public void saveOrUpdateUser_2(UserInformationEntity userInformationEntity) {
        Session session = getCurrentSession();
        session.update(userInformationEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<UserAgencymapEntity> getUserAgency() {
        StringBuilder hql = new StringBuilder(" FROM UserAgencymapEntity ");
        hql.append(" WHERE agencyId like '%dpx' OR agencyId like '%cpx' ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<UserAgencymapEntity> list = query.list();
        return list;
    }

    @Override
    public List<UserAgencymapEntity> getUserAgencyById(List<String> idList) {
        StringBuilder hql = new StringBuilder(" FROM UserAgencymapEntity ");
        hql.append(" WHERE agencyId in (:idList) ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameterList("idList",idList);
        List<UserAgencymapEntity> list = query.list();
        return list;
    }

    @Override
    public void saveOrUpdateUserAgency(UserAgencyEntity userAgencyEntity) {
        Session session = getCurrentSession();
        session.save(userAgencyEntity);
        session.flush();
        session.close();
    }

    @Override
    public void updateUserAgencyEntity(UserAgencyEntity userAgencyEntity) {
        Session session = getCurrentSession();
        session.update(userAgencyEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<StaffCRMEntity> getStaffByOA() {
        StringBuilder hql = new StringBuilder(" FROM StaffCRMEntity ");
        hql.append(" WHERE 1=1 ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<StaffCRMEntity> list = query.list();
        return list;
    }

    @Override
    public List<SubcompanyCRMEntity> getSubByOA() {
        StringBuilder hql = new StringBuilder(" FROM SubcompanyCRMEntity ");
        hql.append(" WHERE 1=1 and subcompanyid<>'1' order by (-supsubcompanyid) DESC ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<SubcompanyCRMEntity> list = query.list();
        return list;
    }

    @Override
    public List<DepartmentCRMEntity> getDepByOA() {
        StringBuilder hql = new StringBuilder(" FROM DepartmentCRMEntity ");
        hql.append(" WHERE 1=1 order by (-supdepartmentid) DESC ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<DepartmentCRMEntity> list = query.list();
        return list;
    }

    @Override
    public List<AuthOuterAgencyEntity> getAgencyByLevel(Integer level) {
        StringBuilder hql = new StringBuilder(" FROM AuthOuterAgencyEntity ");
        hql.append(" WHERE 1=1 AND level=:level ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("level",level);
        List<AuthOuterAgencyEntity> authOuterAgencyEntities = query.list();
        if(null != authOuterAgencyEntities && authOuterAgencyEntities.size()>0){
            return authOuterAgencyEntities;
        }else {
            return null;
        }

    }

    @Override
    public List<AuthOuterAgencyEntity> getAgency() {
        StringBuilder hql = new StringBuilder(" FROM AuthOuterAgencyEntity ");
        hql.append(" WHERE agencyPath is null OR level is null ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<AuthOuterAgencyEntity> list = query.list();
        return list;
    }

    @Override
    public void delAuthUserAgency(UserAgencyEntity userAgencyEntity) {
        String hql = "update UserAgencyEntity set status = '0',modifyTime=:modifyTime where staffId = :staffId and agencyId=:agencyId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId", userAgencyEntity.getStaffId());
        query.setParameter("modifyTime", new java.util.Date());
        query.setParameter("agencyId", userAgencyEntity.getAgencyId());
        query.executeUpdate();
    }

    @Override
    public void delUserMapEntity(String staffId) {
        String hql = "update UserMapEntity set state = '0',modifyOn=:modifyTime where staffId = :staffId and projectModule='es' ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId",staffId);
        query.setParameter("modifyTime", new java.util.Date());
        query.executeUpdate();
    }
}
