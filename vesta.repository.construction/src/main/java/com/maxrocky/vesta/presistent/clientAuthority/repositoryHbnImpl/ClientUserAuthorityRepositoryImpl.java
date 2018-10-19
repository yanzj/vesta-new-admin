package com.maxrocky.vesta.presistent.clientAuthority.repositoryHbnImpl;

import com.maxrocky.vesta.domain.clientAuthority.repository.ClientUserAuthorityRepository;
import com.maxrocky.vesta.domain.model.AuthOuterAgencyEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserMapEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanyn on 2018/5/7.
 */
@Repository
public class ClientUserAuthorityRepositoryImpl implements ClientUserAuthorityRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    @Override
    public List<UserMapEntity> getUserMap() {
        String sql = "from UserMapEntity where state = '1' and projectModule = 'qc' and sourceFrom ='OA' ";
        Query query = getCurrentSession().createQuery(sql);
        List<UserMapEntity> list = query.list();
        return list;
    }

    @Override
    public UserMapEntity getUserMapById(String staffId) {
        String sql = " FROM UserMapEntity ";
        sql += " WHERE 1=1 AND staffId='"+staffId+"' AND projectModule='qc' ";
        Session session = getCurrentSession();
        Query query = session.createQuery(sql);
        UserMapEntity userMapEntity = (UserMapEntity)query.uniqueResult();
        return userMapEntity;
    }

    @Override
    public List<Object[]> getOAUserManageListByCondition(Map map, WebPage webPage, List<String> idList) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder(" SELECT ui.STAFF_ID,ui.USER_NAME,ui.SYSNAME,ui.MOBILE,ui.EMAIL,ra.AGENCY_NAME,ui.MODIFY_ON,ui.STAFF_NAME,um.STATE,ra.AGENCY_ID ");
        sql.append( " FROM user_information ui " );//人员信息表
        sql.append( " LEFT JOIN user_map um ON um.STAFF_ID = ui.STAFF_ID " );//人员启用状态表
        sql.append( " LEFT JOIN user_agency ua ON um.STAFF_ID = ua.STAFF_ID " );//人员与组织架构关系表
        sql.append( " LEFT JOIN auth_outer_role_agency ra ON ra.AGENCY_ID = ua.AGENCY_ID " );//组织架构表
        sql.append( " WHERE ra.STATUS='1' AND ui.STAFF_STATE = '1' AND ua.STATUS = '1' AND um.PROJECT_MODULE='qc' AND ra.OUT_EMPLOY='0' " );
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
    public void saveOrUpdateClientStaff(UserMapEntity userMapEntity) {
        String sql = "INSERT INTO user_map(STAFF_ID,STATE,SOURCE_FROM,MODIFY_ON,PROJECT_MODULE) VALUES(?,?,?,?,?) ON DUPLICATE KEY UPDATE STATE=?,MODIFY_ON=?";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setString(0, userMapEntity.getStaffId());
        query.setString(1, userMapEntity.getState());
        query.setString(2, userMapEntity.getSourceFrom());
        query.setString(3, DateUtils.format(userMapEntity.getModifyOn()));
        query.setString(4, userMapEntity.getProjectModule());
        query.setString(5, userMapEntity.getState());
        query.setString(6, DateUtils.format(userMapEntity.getModifyOn()));
        query.executeUpdate();
    }

    @Override
    public List<AuthOuterAgencyEntity> getClientOuterAgencyList() {
        StringBuilder hql = new StringBuilder( " FROM  AuthOuterAgencyEntity where 1=1 " );
        hql.append( " and status= '1' and (isTemporary='0' OR (isTemporary='1' AND temporaryScope='qc'))  ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<AuthOuterAgencyEntity> list = query.list();
        return list;
    }

    @Override
    public List<String> getClientAgencyByStaffId(String staffId) {
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql .append( " aora.AGENCY_ID ");
        sql .append( " FROM user_agency ua " ); //人员与组织机构关系表
        sql .append( " LEFT JOIN auth_outer_role_agency aora ON aora.AGENCY_ID = ua.AGENCY_ID " );//外部合作单位组织机构表
        sql .append( " WHERE ua.STAFF_ID = :id AND ua.STATUS='1' AND aora.STATUS='1' AND aora.OUT_EMPLOY='1' AND (aora.IS_TEMPORARY='0' OR (aora.IS_TEMPORARY='1' AND aora.TEMPORARY_SCOPE='qc')) " );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", staffId);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getClientOuterUserList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder( " SELECT DISTINCT " );
        sql .append( " ui.STAFF_ID,ui.SYSNAME,ui.STAFF_NAME,ui.MOBILE,ui.EMAIL,ui.MODIFY_ON,um.STATE " );
        sql .append( " FROM user_information ui " ); //人员表
        sql .append( " LEFT JOIN user_map um ON um.STAFF_ID = ui.STAFF_ID " ); //人员启用关系表
        sql .append( " LEFT JOIN user_agency ua ON um.STAFF_ID = ua.STAFF_ID " ); //人员与组织关系关联表
        sql .append( " LEFT JOIN auth_outer_role_agency aora ON aora.AGENCY_ID = ua.AGENCY_ID " );//外部合作单位组织机构表
        sql .append( " where 1=1 and ua.STATUS='1' and aora.STATUS='1' and ui.STAFF_STATE='1' and um.PROJECT_MODULE = 'qc' and aora.OUT_EMPLOY='1' and (aora.IS_TEMPORARY='0' or (aora.IS_TEMPORARY='1' and aora.TEMPORARY_SCOPE='qc')) " );
        //所属机构
        if(!StringUtil.isEmpty(map.get("agencyName").toString())){
            sql.append( " and aora.AGENCY_NAME like? " );
            params.add(map.get("agencyName").toString());
        }
        //人员
        if(!StringUtil.isEmpty(map.get("staffName").toString())){
            sql.append( " and ui.STAFF_NAME like? " );
            params.add(map.get("staffName").toString());
        }
        //系统账号
        if(!StringUtil.isEmpty(map.get("sysName").toString())){
            sql.append( " and ui.SYSNAME like? " );
            params.add(map.get("sysName").toString());
        }
        //启用状态
        if(!StringUtil.isEmpty(map.get("state").toString())){
            sql.append( " and um.STATE=? ");
            params.add(map.get("state").toString());
        }
        //电话
        if(!StringUtil.isEmpty(map.get("mobile").toString())){
            sql.append( " and ui.MOBILE like? " );
            params.add(map.get("mobile").toString());
        }
        //邮箱
        if(!StringUtil.isEmpty(map.get("email").toString())){
            sql.append( " and ui.EMAIL like? " );
            params.add(map.get("email").toString());
        }
        //机构Id
        if(!StringUtil.isEmpty(map.get("agencyId").toString())){
            sql.append( " and aora.AGENCY_ID =? " );
            params.add(map.get("agencyId").toString());
        }
        sql.append( " order by ui.MODIFY_ON desc " );
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
    public List<String> getClientAgencyNameByStaffId(String staffId) {
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql .append( " aora.AGENCY_NAME ");
        sql .append( " FROM user_agency ua " ); //人员与组织机构关系表
        sql .append( " LEFT JOIN auth_outer_role_agency aora ON aora.AGENCY_ID = ua.AGENCY_ID " );//外部合作单位组织机构表
        sql .append( " WHERE ua.STAFF_ID = :id AND ua.STATUS='1' AND aora.STATUS='1' AND aora.OUT_EMPLOY='1' AND (aora.IS_TEMPORARY='0' OR (aora.IS_TEMPORARY='1' AND aora.TEMPORARY_SCOPE='qc')) " );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", staffId);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getClientEnabledUserList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder( " SELECT DISTINCT " );
        sql.append( " ui.STAFF_ID,ui.USER_NAME,ui.SYSNAME,ui.STAFF_NAME,ui.MOBILE,ui.EMAIL,ui.MODIFY_ON,um.SOURCE_FROM ");
        sql.append( " FROM user_map um ");//用户启用状态表
        sql.append( " LEFT JOIN user_information ui ON um.STAFF_ID = ui.STAFF_ID " );//人员表
        sql.append( " LEFT JOIN user_agency ua ON um.STAFF_ID = ua.STAFF_ID " );//人员组织机构关系表
        sql.append( " LEFT JOIN auth_outer_role_agency ra ON ra.AGENCY_ID = ua.AGENCY_ID " );//组织机构表
        sql.append( " where 1=1 and um.STATE = '1' and ua.STATUS='1' and ui.STAFF_STATE='1' and ra.STATUS='1' and um.PROJECT_MODULE = 'qc' and (ra.IS_TEMPORARY is null or ra.IS_TEMPORARY='0' or (ra.IS_TEMPORARY='1' and ra.TEMPORARY_SCOPE='qc'))" );
        //所属机构
        if(!StringUtil.isEmpty(map.get("agencyName").toString())){
            sql.append( " and ( ra.AGENCY_NAME like? ) " );
            params.add(map.get("agencyName").toString());
        }
        //人员
        if(!StringUtil.isEmpty(map.get("staffName").toString())){
            sql.append( " and ui.STAFF_NAME like? " );
            params.add(map.get("staffName").toString());
        }
        //OA账号
        if(!StringUtil.isEmpty(map.get("userName").toString())){
            sql.append( " and ui.SYSNAME like? " );
            params.add(map.get("userName").toString());
        }
        //用户来源
        if(!StringUtil.isEmpty(map.get("sourceFrom").toString())){
            sql.append( " and um.SOURCE_FROM=? " );
            params.add(map.get("sourceFrom").toString());
        }
        //电话
        if(!StringUtil.isEmpty(map.get("mobile").toString())){
            sql.append( " and ui.MOBILE like? " );
            params.add(map.get("mobile").toString());
        }
        //邮箱
        if(!StringUtil.isEmpty(map.get("email").toString())){
            sql.append( " and ui.EMAIL like? " );
            params.add(map.get("email").toString());
        }
        sql.append( " order by ui.MODIFY_ON desc " );
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
    public AuthOuterAgencyEntity getOuterAgencyByName(String agencyName) {
        StringBuilder hql = new StringBuilder(" FROM AuthOuterAgencyEntity ");
        hql.append( " WHERE 1=1 AND status='1' AND agencyName='"+agencyName+"' AND outEmploy='1' AND (isTemporary='0' OR (isTemporary='1' AND temporaryScope='qc')) " );
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        AuthOuterAgencyEntity authOuterAgencyEntity = (AuthOuterAgencyEntity) query.uniqueResult();
        return authOuterAgencyEntity;
    }

    @Override
    public UserInformationEntity getStaffBySysName(String sysName) {
        String hql = "from UserInformationEntity where sysName=:sysName and staffState='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("sysName",sysName);//用户名
        List<UserInformationEntity> userInformationEntities = query.list();
        if(userInformationEntities == null){
            return null;
        }
        if(userInformationEntities.size() == 0){
            return null;
        }
        return userInformationEntities.get(0);
    }

    @Override
    public boolean addClientOuterStaff(UserInformationEntity userInformationEntity) {
        UserInformationEntity staffEntity = (UserInformationEntity)getCurrentSession().get(UserInformationEntity.class, userInformationEntity.getStaffId());
        if (staffEntity==null) {
            Session session = getCurrentSession();
            session.save(userInformationEntity);
            session.flush();
            session.close();
            return true;
        }else {
            return false;
        }
    }

}
