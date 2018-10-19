package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.AgencyRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
public class AgencyRepositoryImpl extends HibernateDaoImpl implements AgencyRepository {
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<AgencyEntity> getAgencyRoots() {
        String hql = "from AgencyEntity where isRoot = '1' and status = '1' order by orderNum";
        Query query = getCurrentSession().createQuery(hql);
        List<AgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AgencyEntity> getAgencyList(String parentId) {
        String hql = "from AgencyEntity where isRoot <> '1' and status = '1' and parentId = :parentId order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("parentId",parentId);
        List<AgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }
    @Override
    public List<AgencyEntity> searchAgencyList(String parentId) {
//        String hql = "from AgencyEntity where isRoot <> '1' and status = '1' and parentId = :parentId  order by agencyType,orderNum";
        String hql = "from AgencyEntity where isRoot <> '1' and status = '1' and parentId = :parentId and agencyType<>'4' and agencyType<>'5' order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("parentId",parentId);
        List<AgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AgencyEntity> getAgency1(String parentId) {
        String hql = "from AgencyEntity where isRoot <> '1' and status = '1' and agencyType='1' and parentId = :parentId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("parentId",parentId);
        List<AgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AgencyEntity> getAgencys(String agencyId,String agencyName, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from AgencyEntity where status = '1' and parentId ='"+agencyId+"' and agencyType<>'4' and agencyType<>'5'";
        if(!StringUtil.isEmpty(agencyName)){
            hql += " and agencyName like '%"+agencyName+"%'";
        }
        hql +=" order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        List<AgencyEntity> agencyEntities = query.list();
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        return agencyEntities;
    }

    @Override
    public List<AuthOuterAgencyEntity> getAgencys() {
        String hql = "from AuthOuterAgencyEntity where  status='1'";
        Query query = getCurrentSession().createQuery(hql);
        List<AuthOuterAgencyEntity> authOuterAgencyEntities = query.list();
        return authOuterAgencyEntities;
    }

    @Override
    public List<AgencyEntity> getAllAgencyList() {
        String hql = "from AgencyEntity where status = '1' and agencyType<>'4' and agencyType<>'5' order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        List<AgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AuthOuterAgencyEntity> getOuterAgencyList() {
        StringBuilder hql = new StringBuilder( " FROM  AuthOuterAgencyEntity where 1=1 " );
        hql.append( " and status= '1' and (isTemporary='0' OR (isTemporary='1' AND temporaryScope='st'))  ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<AuthOuterAgencyEntity> list = query.list();
        return list;
    }

    @Override
    public List<String> getAgencyByOA() {
        StringBuilder hql = new StringBuilder( " SELECT agencyId FROM  AuthOuterAgencyEntity where 1=1 " );
        hql.append( " and ( agencyId like '%cpx' or agencyId like '%dpx' )   ");
        Query query = getCurrentSession().createQuery(hql.toString());
        List<String> list = query.list();
        return list;
    }

    @Override
    public AgencyEntity getAgencyDetail(String agencyId) {
        String hql = "from AgencyEntity where agencyId = :agencyId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("agencyId",agencyId);
        AgencyEntity agencyEntity = (AgencyEntity) query.uniqueResult();
        return agencyEntity;
    }

    @Override
    public AuthOuterAgencyEntity getAgencyDetailByAgencyId(String agencyId) {
        String hql = "from AuthOuterAgencyEntity where agencyId = :agencyId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("agencyId",agencyId);
        AuthOuterAgencyEntity authOuterAgencyEntity = (AuthOuterAgencyEntity) query.uniqueResult();
        return authOuterAgencyEntity;
    }

    @Override
    public List<AgencyEntity> getChildAgancy(String path,String agencyId) {
        String hql = "from AgencyEntity where agencyPath like '"+path+"%' and status = '1'and agencyId <>:agencyId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("agencyId",agencyId);
        List<AgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public AgencyEntity getAgency(String parentId) {
        String hql = "from AgencyEntity where agencyId=:parentId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("parentId", parentId);
        AgencyEntity agencyEntity = (AgencyEntity) query.uniqueResult();
        return agencyEntity;
    }

    @Override
    public void addAgency(AgencyEntity agencyEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(agencyEntity);
        session.flush();
    }
    @Override
    public boolean addAgencys(AgencyEntity agencyEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(agencyEntity);
        session.flush();
        return true;
    }

    @Override
    public void updateAgency(AgencyEntity agencyEntity) {
        Session session = getCurrentSession();
        session.update(agencyEntity);
        session.flush();
    }

    @Override
    public List<HouseProjectEntity> getProjectsByAgency(String staffId) {
        String hql = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
                " from UserAgencymapEntity where status='1' and staffId = :staffId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId", staffId);
        List<String> paths = query.list();
        String str = "";
        if(paths!=null&&paths.size()>0){
            for(String ids:paths){
                str += ids.replace("/","','");
            }
        }
        if(!StringUtil.isEmpty(str)){
//            Set<String> ptSet = new HashSet<String>();
            str = str.substring(2)+"'";
//            String [] stringArr= str.split(",");
//            for(String a:stringArr){
//                ptSet.add(a);
//            }
//            String[] pth = (String[]) ptSet.toArray();
//            String agency=null;
//            for (int i = 0; i < pth.length; i++){
//                if(i ==0) agency = pth[i];
//                else agency = "," + pth[i];
//            }
            String sql = "select agencyId from AgencyEntity where agencyId in ("+str+")";
            Query query1 = getCurrentSession().createQuery(sql);
            String authorIds = query1.getQueryString();
            String sql1 = "select dataId from RoleDataEntity where dataType = '1' and status = '1' and " +
                    "authorityType = '1' and authorityId in("+authorIds+")";
            Query query2 = getCurrentSession().createQuery(sql1);
            String projectIds = query2.getQueryString();
            String hql1 = "from HouseProjectEntity where id in ("+projectIds+")";
            Query query3 = getCurrentSession().createQuery(hql1);
            if(!StringUtil.isEmpty(authorIds)&&!StringUtil.isEmpty(projectIds)){
                List<HouseProjectEntity> houseProjectEntities = query3.list();
                return houseProjectEntities;
            }
        }
        return null;
    }

    @Override
    public void delAgency(AgencyEntity agencyEntity) {
        String hql = "update AgencyEntity set status = '0' ,modifyTime=:modifyTime where agencyPath like '"+agencyEntity.getAgencyPath()+"%'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime",new Date());
        query.executeUpdate();
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 新增修改标段的时候，获取对应总包
     */
    @Override
    public List<AgencyEntity> getAAgencyList(String projectId) {
        String hql = "from AgencyEntity where agencyType='4' and nature='1' and status='1' and agencyId in(select distinct dataId from ProjectStaffEmployEntity where projectId=:projectId and projectRole is null and dataType='1' and status='1') order by orderNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        List<AgencyEntity> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getAuthSupplierByprojectId(String projectId, String type1,String supplierId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" SUPPLIER_ID,SUPPLIER_NAME ");
        sql.append(" from base_project_people ");
        sql.append(" where 1=1 and SUPPLIER_TYPE=:type1 and `STATUS`='1' ");
        if(!StringUtil.isEmpty(projectId)){
            sql.append(" and PROJECT_ID=:projectId ");
        }
        if(!StringUtil.isEmpty(supplierId)){
            sql.append(" and SUPPLIER_ID=:supplierId ");
        }

        sql.append(" GROUP BY SUPPLIER_ID  ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("type1", type1);
        if(!StringUtil.isEmpty(projectId)){
            query.setParameter("projectId", projectId);
        }
        if(!StringUtil.isEmpty(supplierId)){
            query.setParameter("supplierId", supplierId);
        }


        List<Object[]> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public AgencyEntity getAgencyDetailByName(String content) {
        String hql = "from AgencyEntity where agencyName=:agencyName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("agencyName", content);
        AgencyEntity agencyEntity = (AgencyEntity) query.uniqueResult();
        return agencyEntity;
    }

    @Override
    public AgencyEntity getAgencyDetailListByName(String content,String projectId) {
//        String hql = "from AgencyEntity where agencyName=:agencyName and agencyId in (select dataId from ProjectStaffEmployEntity where dataType='1' and projectId=:projectId and status='1' and projectRole='null')";
        String sql ="SELECT * FROM role_agency a WHERE 1=1 AND a.AGENCY_ID IN (SELECT s.DATA_ID FROM project_staff_employ s WHERE " +
                "s.DATA_TYPE = '1' AND s.`STATUS` = '1' AND s.PROJECT_ROLE is null " +
                "AND s.PROJECT_ID = :projectId) " +
                "AND a.AGENCY_NAME = :agencyName";
        Query query = getCurrentSession().createSQLQuery(sql).addEntity(AgencyEntity.class);
        query.setParameter("agencyName", content);
        query.setParameter("projectId",projectId);
        AgencyEntity agencyEntity = (AgencyEntity) query.uniqueResult();
        return agencyEntity;
    }

    @Override
    public AgencyEntity getAgencyByName(String agencyName,String agencyId) {
        String hql = "from AgencyEntity where agencyName=:agencyName and parentId=:parentId and status='1' and agencyType='1' ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("agencyName", agencyName);
        query.setParameter("parentId", agencyId);
        AgencyEntity agencyEntity = (AgencyEntity) query.uniqueResult();
        return agencyEntity;
    }

    @Override
    public AgencyEntity getAgencyByAgencyName(String agencyName) {
        String hql = "from AgencyEntity where agencyName=:agencyName and agencyType='2' and status='1' ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("agencyName", agencyName);
        AgencyEntity agencyEntity = (AgencyEntity) query.uniqueResult();
        return agencyEntity;
    }

    @Override
    public List<AuthOuterAgencyEntity> getOAAgencyList() {
        String hql = "from AuthOuterAgencyEntity where status = '1' and outEmploy='0' order by orderNum";
        Query query = getCurrentSession().createQuery(hql);
        List<AuthOuterAgencyEntity> authOuterAgencyEntities = query.list();
        return authOuterAgencyEntities;
    }

    @Override
    public List<AuthOuterAgencyEntity> getOAAgencyListIsDel() {
        String hql = "from AuthOuterAgencyEntity where outEmploy='0' order by agencyType,orderNum";
        Query query = getCurrentSession().createQuery(hql);
        List<AuthOuterAgencyEntity> authOuterAgencyEntities = query.list();
        return authOuterAgencyEntities;
    }

    @Override
    public void saveOrUpdateStaff(UserMapEntity userMapEntity) {
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
    public List<Object[]> getEnabledUserList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder( " SELECT DISTINCT " );
        sql.append( " ui.STAFF_ID,ui.USER_NAME,ui.SYSNAME,ui.STAFF_NAME,ui.MOBILE,ui.EMAIL,ui.MODIFY_ON,um.SOURCE_FROM ");
        sql.append( " FROM user_map um ");//用户启用状态表
        sql.append( " LEFT JOIN user_information ui ON um.STAFF_ID = ui.STAFF_ID " );//人员表
        sql.append( " LEFT JOIN user_agency ua ON um.STAFF_ID = ua.STAFF_ID " );//人员组织机构关系表
        sql.append( " LEFT JOIN auth_outer_role_agency ra ON ra.AGENCY_ID = ua.AGENCY_ID " );//组织机构表
        sql.append( " where 1=1 and um.STATE = '1' and ua.STATUS='1' and ui.STAFF_STATE='1' and ra.STATUS='1' and um.PROJECT_MODULE = 'st' and (ra.IS_TEMPORARY is null or ra.IS_TEMPORARY='0' or (ra.IS_TEMPORARY='1' and ra.TEMPORARY_SCOPE='st'))" );
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
    public List<Object[]> getOuterUserList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder( " SELECT DISTINCT " );
        sql .append( " ui.STAFF_ID,ui.SYSNAME,ui.STAFF_NAME,ui.MOBILE,ui.EMAIL,ui.MODIFY_ON,um.STATE " );
        sql .append( " FROM user_information ui " ); //人员表
        sql .append( " LEFT JOIN user_map um ON um.STAFF_ID = ui.STAFF_ID " ); //人员启用关系表
        sql .append( " LEFT JOIN user_agency ua ON um.STAFF_ID = ua.STAFF_ID " ); //人员与组织关系关联表
        sql .append( " LEFT JOIN auth_outer_role_agency aora ON aora.AGENCY_ID = ua.AGENCY_ID " );//外部合作单位组织机构表
        sql .append( " where 1=1 and ua.STATUS='1' and aora.STATUS='1' and ui.STAFF_STATE='1' and um.PROJECT_MODULE = 'st' and aora.OUT_EMPLOY='1' and (aora.IS_TEMPORARY='0' or (aora.IS_TEMPORARY='1' and aora.TEMPORARY_SCOPE='st')) " );
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
    public List<String> getAgencyNameByStaffId(String staffId) {
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql .append( " aora.AGENCY_NAME ");
        sql .append( " FROM user_agency ua " ); //人员与组织机构关系表
        sql .append( " LEFT JOIN auth_outer_role_agency aora ON aora.AGENCY_ID = ua.AGENCY_ID " );//外部合作单位组织机构表
        sql .append( " WHERE ua.STAFF_ID = :id AND ua.STATUS='1' AND aora.STATUS='1' AND (aora.IS_TEMPORARY='0' OR (aora.IS_TEMPORARY='1' AND aora.TEMPORARY_SCOPE='st')) " );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", staffId);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<String> getAgencyAllNameByStaffId(String staffId) {
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql .append( " aora.AGENCY_NAME ");
        sql .append( " FROM user_agency ua " ); //人员与组织机构关系表
        sql .append( " LEFT JOIN auth_outer_role_agency aora ON aora.AGENCY_ID = ua.AGENCY_ID " );//外部合作单位组织机构表
        sql .append( " WHERE ua.STAFF_ID = :id AND ua.STATUS='1' AND aora.STATUS='1' " );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", staffId);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<String> getOAAgencyNameByStaffId(String staffId) {
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql .append( " ra.AGENCY_NAME ");
        sql .append( " FROM user_agency ua " ); //人员与组织机构关系表
        sql .append( " LEFT JOIN role_agency ra ON ra.AGENCY_ID = ua.AGENCY_ID " );//OA组织机构表
        sql .append( " WHERE ua.STAFF_ID = :id AND ua.STATUS='1' AND ra.STATUS='1' " );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", staffId);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<UserAgencyEntity> getUserAgency(String staffId) {
        String sql = " FROM UserAgencyEntity ";
        sql += " WHERE 1=1 AND status='1' AND staffId='"+staffId+"' ";
        Session session = getCurrentSession();
        Query query = session.createQuery(sql);
        List list = query.list();
        return list;
    }

    @Override
    public AuthAgencyEntity getAgencyById(String AgencyId) {
        String sql = " FROM AuthAgencyEntity ";
        sql += " WHERE 1=1 AND status='1' and agencyId='"+AgencyId+"' ";
        Session session = getCurrentSession();
        Query query = session.createQuery(sql);
        AuthAgencyEntity authAgencyEntity = (AuthAgencyEntity) query.uniqueResult();
        return authAgencyEntity;
    }

    @Override
    public UserMapEntity getUserMap(String staffId) {
        String sql = " FROM UserMapEntity ";
        sql += " WHERE 1=1 AND staffId='"+staffId+"' AND projectModule='st' ";
        Session session = getCurrentSession();
        Query query = session.createQuery(sql);
        UserMapEntity userMapEntity = (UserMapEntity)query.uniqueResult();
        return userMapEntity;
    }

    @Override
    public List<UserInformationEntity> getUserInformation(String staffId) {
        String sql = " FROM UserInformationEntity ";
        sql += " WHERE 1=1 AND staffState='1' AND staffId='"+staffId+"' ";
        Session session = getCurrentSession();
        Query query = session.createQuery(sql);
        List list = query.list();
        return list;
    }

    @Override
    public <T> void saveOrupdate(T entity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    @Override
    public AuthOuterAgencyEntity getAgencyByAgencyId(String id) {
        StringBuilder hql = new StringBuilder(" FROM AuthOuterAgencyEntity ");
        hql.append( " WHERE 1=1 AND status='1' AND agencyId='"+id+"' " );
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        AuthOuterAgencyEntity authOuterAgencyEntity = (AuthOuterAgencyEntity) query.uniqueResult();
        return authOuterAgencyEntity;
    }

    @Override
    public List<String> getAgencyByStaffId(String staffId) {
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql .append( " aora.AGENCY_ID ");
        sql .append( " FROM user_agency ua " ); //人员与组织机构关系表
        sql .append( " LEFT JOIN auth_outer_role_agency aora ON aora.AGENCY_ID = ua.AGENCY_ID " );//外部合作单位组织机构表
        sql .append( " WHERE ua.STAFF_ID = :id AND ua.STATUS='1' AND aora.STATUS='1' AND aora.OUT_EMPLOY='1' AND (aora.IS_TEMPORARY='0' OR (aora.IS_TEMPORARY='1' AND aora.TEMPORARY_SCOPE='st')) " );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", staffId);
        List<String> list = query.list();
        return list;
    }

    @Override
    public UserInformationEntity getUserByName(String userName) {
        StringBuilder hql = new StringBuilder( " FROM UserInformationEntity " );
        hql.append( " WHERE 1=1 AND sysName=:userName " );
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("userName",userName);
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
    public UserInformationEntity getUserByNameAndId(String userName, String id) {
        StringBuilder hql = new StringBuilder( " FROM UserInformationEntity " );
        hql.append( " WHERE 1=1 AND sysName=:userName AND staffId<>:id " );
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("userName",userName);
        query.setParameter("id",id);
        UserInformationEntity userInformationEntity = (UserInformationEntity) query.uniqueResult();
        return userInformationEntity;
    }

    @Override
    public UserInformationEntity getUserById(String id) {
        StringBuilder hql = new StringBuilder( " FROM UserInformationEntity " );
        hql.append( " WHERE 1=1 AND staffId=:id " );
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("id",id);
        UserInformationEntity userInformationEntity = (UserInformationEntity) query.uniqueResult();
        return userInformationEntity;
    }

    @Override
    public AuthOuterAgencyEntity getOuterAgencyByName(String agencyName) {
        StringBuilder hql = new StringBuilder(" FROM AuthOuterAgencyEntity ");
        hql.append( " WHERE 1=1 AND status='1' AND agencyName='"+agencyName+"' AND outEmploy='1' AND (isTemporary='0' OR (isTemporary='1' AND temporaryScope='st')) " );
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        AuthOuterAgencyEntity authOuterAgencyEntity = (AuthOuterAgencyEntity) query.uniqueResult();
        return authOuterAgencyEntity;
    }

    @Override
    public void delUserAgencyMap(String staffId,Date modifyOn) {
        StringBuilder sql = new StringBuilder(" UPDATE user_agency ua ");
        sql.append(" SET ");
        sql.append(" ua.STATUS='0',ua.MODIFY_TIME=:modifyOn");
        sql.append(" WHERE ");
        sql.append(" ua.STAFF_ID=:staffId ");
        SQLQuery sqlQuery = getCurrentSession().createSQLQuery(sql.toString());
        sqlQuery.setParameter("modifyOn",DateUtils.format(modifyOn));
        sqlQuery.setParameter("staffId",staffId);
        sqlQuery.executeUpdate();
    }

    @Override
    public void saveOrUpdateUserAgencyMap(UserAgencyEntity userAgencyEntity) {
        StringBuilder sql = new StringBuilder( "INSERT INTO user_agency(MAP_ID,STAFF_ID,AGENCY_ID,MODIFY_TIME,STATUS,SOURCE_FROM) VALUES(?,?,?,?,?,?) ON DUPLICATE KEY UPDATE MODIFY_TIME=?,STATUS=?" );
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setString(0, userAgencyEntity.getMapId());
        query.setString(1, userAgencyEntity.getStaffId());
        query.setString(2, userAgencyEntity.getAgencyId());
        query.setString(3, DateUtils.format(userAgencyEntity.getModifyTime()));
        query.setString(4, userAgencyEntity.getStatus());
        query.setString(5, userAgencyEntity.getSourceFrom());
        query.setString(6, DateUtils.format(userAgencyEntity.getModifyTime()));
        query.setString(7, userAgencyEntity.getStatus());
        query.executeUpdate();
    }

    @Override
    public List<String> getUserByAgencyId(String agencyId) {
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql.append(" ui.STAFF_NAME ");
        sql.append(" FROM user_information ui ");
        sql.append(" LEFT JOIN user_agency ua ON ui.STAFF_ID = ua.STAFF_ID ");
        sql.append(" WHERE ui.STAFF_STATE='1' AND ua.STATUS='1' AND ua.AGENCY_ID=:agencyId ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("agencyId",agencyId);
        List<String> list = query.list();
        return list;
    }

    @Override
    public void delAgencyByAgencyId(String departmentid, String userId) {
        StringBuilder hql = new StringBuilder(" update UserAgencyEntity ");
        hql.append(" set status='0',modifyTime=:modifyTime ");
        hql.append(" WHERE 1=1 AND staffId=:userId AND agencyId<>:departmentid AND status='1' ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("modifyTime",new Date());
        query.setParameter("userId",userId);
        query.setParameter("departmentid",departmentid);
        query.executeUpdate();
    }

    @Override
    public UserAgencyEntity getAgencyUserByAgencyId(String agencyId, String userId) {
        StringBuilder hql = new StringBuilder(" FROM UserAgencyEntity ");
        hql.append(" WHERE 1=1 AND agencyId=:agencyId AND staffId=:userId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("agencyId",agencyId);
        query.setParameter("userId",userId);
        UserAgencyEntity userAgencyEntity = (UserAgencyEntity) query.uniqueResult();
        return userAgencyEntity;
    }

    @Override
    public UserInformationEntity getUserByUserId(String userId) {
        StringBuilder hql = new StringBuilder(" FROM UserInformationEntity ");
        hql.append(" WHERE 1=1 AND userId=:userId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("userId",userId);
        List<UserInformationEntity> list = query.list();
        if(null != list && list.size()>0){
            return list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public void updateAgency(AuthOuterAgencyEntity authOuterAgencyEntity) {
        Session session = this.getCurrentSession();
        session.update(authOuterAgencyEntity);
        session.flush();
        session.close();
    }

    @Override
    public void saveAgency(AuthOuterAgencyEntity authOuterAgencyEntity) {
        Session session = this.getCurrentSession();
        session.save(authOuterAgencyEntity);
        session.flush();
        session.close();
    }

    @Override
    public void delAgencyByOA() {
        StringBuilder sql = new StringBuilder(" select dcrm.DEPARTMENT_ID ss from department_crm dcrm where dcrm.SUPDEPARTMENT_ID<>'0' and dcrm.CANCELED='0' and dcrm.SUPDEPARTMENT_ID in ( select crm.DEPARTMENT_ID s from department_crm crm where crm.CANCELED='1') ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        List<String> list = query.list();
        if(null != list && list.size()>0){
            StringBuilder hql = new StringBuilder(" DELETE FROM DepartmentCRMEntity WHERE departmentid IN (:ids) ");
            Query query1 = getCurrentSession().createQuery(hql.toString());
            query1.setParameterList("ids", list);
            query1.executeUpdate();
        }
    }

    @Override
    public void delWasteAgency() {
        StringBuilder hql = new StringBuilder(" DELETE FROM AuthOuterAgencyEntity WHERE agencyPath is null ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.executeUpdate();
    }

    @Override
    public void removeAgency(List<String> id) {
        StringBuilder hql = new StringBuilder(" UPDATE AuthOuterAgencyEntity SET status='0' WHERE agencyId in(:id) ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameterList("id",id);
        query.executeUpdate();
    }

    @Override
    public void updateBasePeople(String id, String name, Date date) {
        StringBuilder hql = new StringBuilder(" UPDATE BaseProjectPeopleEntity SET peopleName=:name,modifyTime=:date  WHERE peopleId=:id ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("name",name);
        query.setParameter("date",date);
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public void updateRoleStaff(String id, Date date) {
        StringBuilder hql = new StringBuilder(" UPDATE RoleStaffProjectMapESEntity SET modifyOn=:date  WHERE staffId=:id ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("date",date);
        query.setParameter("id",id);
        query.executeUpdate();
    }

    @Override
    public boolean checkUserByName(String sysName) {
        StringBuilder hql = new StringBuilder( " FROM UserInformationEntity " );
        hql.append( " WHERE 1=1 AND sysName=:sysName AND staffState='1' " );
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("sysName",sysName);
        UserInformationEntity userInformationEntity = (UserInformationEntity) query.uniqueResult();
        if(userInformationEntity!=null){
            return false;
        }else{
            return  true;
        }
    }
}
