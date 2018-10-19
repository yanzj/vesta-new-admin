package com.maxrocky.vesta.presistent.baseData.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.BaseProjectPeopleEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectProjectEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectStaffEmployEntity;
import com.maxrocky.vesta.domain.baseData.repository.StaffEmployRepository;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/10/25.
 */
@Repository
public class StaffEmployRepositoryImpl extends HibernateDaoImpl implements StaffEmployRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addProjectStaffEmploy(ProjectStaffEmployEntity projectStaffEmployEntity) {
        Session session = getCurrentSession();
        session.save(projectStaffEmployEntity);
        session.flush();
    }

    @Override
    public List<ProjectProjectEntity> getProjectListForAgency(String staffId) {
        String hql = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
                " from UserAgencymapEntity where status='1' and staffId = :staffId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId",staffId);
        List<String> paths = query.list();
        String str = "";
        if(paths!=null&&paths.size()>0){
            for(String ids:paths){
                str += ids.replace("/","','");
            }
        }
        if(!StringUtil.isEmpty(str)){
            str = str.substring(2)+"'";
//            String sql = "select agencyId from AgencyEntity where agencyId in ("+str+")";
//            Query query1 = getCurrentSession().createQuery(sql);
//            String authorIds = query1.getQueryString();
            String sql1 = "select projectId from ProjectStaffEmployEntity where dataType = '1' and status = '1' and dataId in("+str+")";
            Query query2 = getCurrentSession().createQuery(sql1);
            String projectIds = query2.getQueryString();
            String hql1 = "from ProjectProjectEntity where id in ("+projectIds+")";
            Query query3 = getCurrentSession().createQuery(hql1);
            if(!StringUtil.isEmpty(projectIds)){
                List<ProjectProjectEntity> projectProjectEntities = query3.list();
                return projectProjectEntities;
            }
        }
        return null;
    }

    @Override
    public List<ProjectProjectEntity> getProjectListByStaffId(String staffId) {
        String hql = "from ProjectProjectEntity where id in (select distinct projectId from ProjectStaffEmployEntity where dataType='2' and status='1' and dataId=:staffId) and state='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId",staffId);
        List<ProjectProjectEntity> projectProjectEntities = query.list();
        return projectProjectEntities;
    }

    @Override
    public List<AgencyEntity> getEmploys(ProjectStaffEmployEntity projectStaffEmployEntity,WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from AgencyEntity where 1=1";
        if(!StringUtil.isEmpty(projectStaffEmployEntity.getDataId())){
            hql += " and agencyName like '%"+projectStaffEmployEntity.getDataId()+"%'";
        }
        hql += " and status= '1'";
        if(!StringUtil.isEmpty(projectStaffEmployEntity.getProjectId())){
            hql += "and agencyId in(select distinct dataId from ProjectStaffEmployEntity where dataType='1' and projectRole is null and status='1' and projectId='"+projectStaffEmployEntity.getProjectId()+"')";
        }
        hql += "order by modifyTime desc";
        Query query = getCurrentSession().createQuery(hql);
        List<AgencyEntity> agencyEntities = query.list();
        if(webPage !=null){
            return this.findByPage(hql, webPage,params);
        }
        return agencyEntities;
    }

    @Override
    public AgencyEntity getEmployDetail(String agencyId) {
        String hql = "from AgencyEntity where status='1' and agencyId=:agencyId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("agencyId",agencyId);
        AgencyEntity agencyEntity = (AgencyEntity) query.uniqueResult();
        return agencyEntity;
    }

    @Override
    public List<AgencyEntity> getEmploysForProjectPermission(String projectId, String permission) {
        String hql = "from AgencyEntity where status='1' and agencyId in (select distinct dataId from ProjectStaffEmployEntity where status='1' and source<>'1' and dataType='1' and projectId =:projectId and projectRole=:permission)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        query.setParameter("permission",permission);
        List<AgencyEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<ProjectStaffEmployEntity> getStaffEmploys(String projectId, String dataType, String permission) {
        String hql = "from ProjectStaffEmployEntity where projectId=:projectId and dataType=:dataType and source<>'1' and projectRole=:permission and status='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        query.setParameter("dataType",dataType);
        query.setParameter("permission",permission);
        List<ProjectStaffEmployEntity> projectStaffEmployEntities = query.list();
        return projectStaffEmployEntities;
    }

    @Override
    public List<UserPropertyStaffEntity> getStaffsForProjectPermission(String projectId, String permission) {
        String hql = "from UserPropertyStaffEntity where staffState='1' and staffId in (select distinct dataId from ProjectStaffEmployEntity where status='1' and source<>'1' and dataType='2' and projectId =:projectId and projectRole=:permission)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        query.setParameter("permission",permission);
        List<UserPropertyStaffEntity> userPropertyStaffEntities = query.list();
        return userPropertyStaffEntities;
    }

    @Override
    public void deleteProjectRole(ProjectStaffEmployEntity projectStaffEmployEntity) {
        String hql = "update ProjectStaffEmployEntity set modifyTime=:modifyTime,status='0' where dataType =:dataType and dataId =:dataId and source<>'1' and projectId =:projectId and projectRole=:permission";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dataId",projectStaffEmployEntity.getDataId());
        query.setParameter("dataType",projectStaffEmployEntity.getDataType());
        query.setParameter("projectId",projectStaffEmployEntity.getProjectId());
        query.setParameter("permission",projectStaffEmployEntity.getProjectRole());
        query.setParameter("modifyTime",new Date());
        query.executeUpdate();
    }

    @Override
    public void delProjectRole(String staffId) {
        String hql = "update ProjectStaffEmployEntity set modifyTime=:modifyTime,status='0' where dataType =:dataType and dataId =:dataId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dataId",staffId);
        query.setParameter("dataType","2");
        query.setParameter("modifyTime",new Date());
        query.executeUpdate();
    }

    @Override
    public void dumpAddProjectRole(ProjectStaffEmployEntity projectStaffEmployEntity) {
        String sql1 = "INSERT INTO project_staff_employ(PROJECT_ID,PROJECT_ROLE,DATA_TYPE,DATA_ID,MODIFY_TIME,STATUS,SOURCE) VALUES(?,?,?,?,?,1,?) ON DUPLICATE KEY UPDATE STATUS='1',MODIFY_TIME=? ,SOURCE=?";
        Query query1 = getCurrentSession().createSQLQuery(sql1);
        query1.setString(0,projectStaffEmployEntity.getProjectId());
        query1.setString(1,projectStaffEmployEntity.getProjectRole());
        query1.setString(2,projectStaffEmployEntity.getDataType());
        query1.setString(3,projectStaffEmployEntity.getDataId());
        query1.setString(4, DateUtils.format(new Date()));
        if(projectStaffEmployEntity.getSource() !=null ){
            query1.setString(5,projectStaffEmployEntity.getSource());
        }else{
            query1.setString(5,"0");
        }
        query1.setString(6, DateUtils.format(new Date()));
        if(projectStaffEmployEntity.getSource() !=null ){
            query1.setString(7,projectStaffEmployEntity.getSource());
        }else{
            query1.setString(7,"0");
        }
        query1.executeUpdate();
    }

    @Override
    public boolean checkOwner(String staffId, String projectId, String permission) {
        String hql2 = "select count(*) from ProjectStaffEmployEntity where projectId=:projectId and dataType='2' and dataId=:staffId and projectRole=:permission and status='1'";
        Query query4 = getCurrentSession().createQuery(hql2);
        query4.setParameter("projectId",projectId);
        query4.setParameter("permission", permission);
        query4.setParameter("staffId", staffId);
        long num = (Long) query4.uniqueResult();
        if(num>0){
            return true;
        }
        String hql = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
                " from UserAgencymapEntity where status='1' and staffId = :staffId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId",staffId);
        List<String> paths = query.list();
        String str = "";
        if(paths!=null&&paths.size()>0){
            for(String ids:paths){
                str += ids.replace("/","','");
            }
        }
        if(!StringUtil.isEmpty(str)){
            str = str.substring(2)+"'";
//            String sql = "select agencyId from AgencyEntity where agencyId in ("+str+")";
//            Query query1 = getCurrentSession().createQuery(sql);
//            String authorIds = query1.getQueryString();
            String sql1 = "select count(*) from ProjectStaffEmployEntity where dataType = '1' and status = '1' and dataId in("+str+") and projectRole=:permission and projectId=:projectId";
            Query query2 = getCurrentSession().createQuery(sql1);
            query2.setParameter("permission", permission);
            query2.setParameter("projectId", projectId);
            long result = (Long) query2.uniqueResult();
            if(result>0){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getPurviewName(String staffId, String projectId) {
        String hql = "select c.agencyType from UserAgencymapEntity a,ProjectStaffEmployEntity b,AgencyEntity c where a.staffId=:staffId and b.projectId=:projectId and a.status='1' and b.dataType='1' and a.agencyId=b.dataId and b.status='1' and b.dataId=c.agencyId and c.status='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId",staffId);
        query.setParameter("projectId",projectId);
        String str = (String) query.uniqueResult();
        return str;
    }

    @Override
    public String getProjectIdByDuty(String dutyId) {
        String hql = "select projectId from ProjectStaffEmployEntity where dataType='1' and dataId=:dutyId and status='1' and projectRole="+null;
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dutyId",dutyId);
        String projectId = (String) query.list().get(0);
        return projectId;
    }

    @Override
    public List<Object[]> getOwnerProjectRole(String projectId, String timeStamp, int num) {
        String sql = "select staffId,staffName,projectId,projectName,state,modifyTime from project_owner_people where projectId='"+projectId+"' and modifyTime>='"+timeStamp+"' order by modifyTime";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setFirstResult(num * 1000);
        query.setMaxResults(1000);
        List<Object[]> objects = query.list();
        return objects;
    }

    @Override
    public void deleProjectRole(String ids, String type) {
        String hql = "update ProjectStaffEmployEntity set modifyTime=:modifyTime,status='0' where dataType =:dataType and status='1' and dataId in(:id) ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",ids);
        query.setParameter("dataType",type);
        query.setParameter("modifyTime",new Date());
        query.executeUpdate();
    }

    @Override
    public void deleteProjectRole() {
        String hql = "update ProjectStaffEmployEntity set modifyTime=:modifyTime,status='0' where status='1' and source='1' ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime",new Date());
        query.executeUpdate();
    }

    @Override
    public boolean checkAgencyName(String projectId, String agencyName) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ra.AGENCY_ID  ");
        sql.append(" from project_staff_employ pse  ");
        sql.append(" LEFT JOIN role_agency ra ON pse.DATA_ID = ra.AGENCY_ID  ");
        sql.append(" where 1=1 and pse.PROJECT_ID=projectId  and ra.AGENCY_NAME=agencyName ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("agencyName", agencyName);
        query.setParameter("projectId", projectId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return false;
        }
        return true;
    }

    @Override
    public AuthOuterAgencyEntity getAuthOuterAgency(String agencyId) {
        String hql = "from AuthOuterAgencyEntity where status='1' and agencyId=:agencyId and parentId='03d3df6a599747ef9bfa4332c0f919b6' ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("agencyId",agencyId);
        AuthOuterAgencyEntity agencyEntity = (AuthOuterAgencyEntity) query.uniqueResult();
        return agencyEntity;
    }

    @Override
    public AuthOuterAgencyEntity getAuthOuterAgencyByName(String agencyName) {
        String hql = "from AuthOuterAgencyEntity where status='1' and agencyName=:agencyName and parentId='03d3df6a599747ef9bfa4332c0f919b6' ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("agencyName",agencyName);
        List<AuthOuterAgencyEntity> list = query.list();
        if(list!=null && list.size()>0){
            return  list.get(0);
        }else {
            return null;
        }
//        AuthOuterAgencyEntity agencyEntity = (AuthOuterAgencyEntity) query.uniqueResult();
//        return agencyEntity;
    }

    @Override
    public void addAgency(AuthOuterAgencyEntity authOuterAgencyEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(authOuterAgencyEntity);
        session.flush();
    }

    @Override
    public void addbaseProjectPeople(BaseProjectPeopleEntity baseProjectPeopleEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(baseProjectPeopleEntity);
        session.flush();
    }

    @Override
    public void deleteBaseProjectPeople(String projectId, String authOuterAgencyId, String userId,String supplierName,String supplierType,String abbreviationName,String status) {
        String hql = "update BaseProjectPeopleEntity set status='"+status+"',modifyTime=:modifyTime ";
        if(!StringUtil.isEmpty(supplierName)){
            hql+=",supplierName=:supplierName";
        }
        if(!StringUtil.isEmpty(supplierType)){
            hql+=",supplierType=:supplierType";
        }
        if(!StringUtil.isEmpty(abbreviationName)){
            hql+=",abbreviationName=:abbreviationName";
        }
        hql+="  where 1=1 and status='1'";
        if(!StringUtil.isEmpty(projectId)){
            hql+="  and projectId=:projectId ";
        }
        if(!StringUtil.isEmpty(authOuterAgencyId)){
            hql+="  and supplierId=:authOuterAgencyId ";
        }
        if(!StringUtil.isEmpty(userId)){
            hql+="  and peopleId=:userId ";
        }
        Query query = getCurrentSession().createQuery(hql);
        if(!StringUtil.isEmpty(supplierName)){
            query.setParameter("supplierName", supplierName);
        }
        if(!StringUtil.isEmpty(supplierType)){
            query.setParameter("supplierType", supplierType);
        }
        if(!StringUtil.isEmpty(abbreviationName)){
            query.setParameter("abbreviationName", abbreviationName);
        }
        if(!StringUtil.isEmpty(projectId)){
            query.setParameter("projectId", projectId);
        }
        if(!StringUtil.isEmpty(authOuterAgencyId)){
            query.setParameter("authOuterAgencyId", authOuterAgencyId);
        }
        if(!StringUtil.isEmpty(userId)){
            query.setParameter("userId", userId);
        }
        query.setParameter("modifyTime", new Date());
        query.executeUpdate();
    }

    @Override
    public List<BaseProjectPeopleEntity> getBaseProjectBByAgencyId(String projectId, String supplierName,WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from BaseProjectPeopleEntity where 1=1";
        if(!StringUtil.isEmpty(supplierName)){
            hql += " and supplierName like '%"+supplierName+"%'";
        }
        hql += " and status= '1'";
        if(!StringUtil.isEmpty(projectId)){
            hql += " and  projectId = '"+projectId+"'  ";
        }
        hql += "group by supplierId order by modifyTime desc ";
        Query query = getCurrentSession().createQuery(hql);
        List<BaseProjectPeopleEntity> agencyEntities = query.list();
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        return agencyEntities;
    }

    @Override
    public List<Object[]> getAuthOuterAgencyProject(String projectId, String supplierName, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql.append(" bpp.SUPPLIER_ID,aoa.AGENCY_NAME,bpp.SUPPLIER_TYPE ");
        sql.append(" from base_project_people bpp  ");
        sql.append(" LEFT JOIN auth_outer_role_agency aoa ON bpp.SUPPLIER_ID = aoa.AGENCY_ID ");
        sql.append(" WHERE 1 = 1 AND bpp.`STATUS` = '1' AND aoa.`STATUS` = '1'  ");
        //机构Id
        if(!StringUtil.isEmpty(projectId)){
            sql.append( " AND bpp.PROJECT_ID =? " );
            params.add(projectId);
        }
        if(!StringUtil.isEmpty(supplierName)){
            sql.append( " and aoa.AGENCY_NAME like ? " );
            params.add("%"+supplierName+"%");
        }
        sql.append(" GROUP BY bpp.SUPPLIER_ID ORDER BY bpp.MODIFY_TIME desc  ");
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
    public BaseProjectPeopleEntity getBaseProjectByProjectId(String projectId, String supplierId) {
        String hql = "from BaseProjectPeopleEntity where status='1' and supplierId=:supplierId and projectId=:projectId  GROUP BY supplierId,projectId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("supplierId",supplierId);
        query.setParameter("projectId",projectId);
        List<BaseProjectPeopleEntity> list = query.list();
        if(list!=null && list.size()>0){
            return  list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<String> getIdListByProjectId(String projectId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" SUPPLIER_ID ");
        sql.append(" FROM base_project_people  ");
        sql.append(" where `STATUS`='1' and PROJECT_ID=:projectId  GROUP BY SUPPLIER_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("projectId", projectId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public List<String> getNameListByProjectId(List<String> agencyIdList) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" AGENCY_NAME ");
        sql.append(" FROM auth_outer_role_agency  ");
        sql.append(" where `STATUS`='1' and PARENT_ID='03d3df6a599747ef9bfa4332c0f919b6' ");
        if(agencyIdList!=null && agencyIdList.size()>0){
            sql.append(" and AGENCY_ID not in(:agencyIdList) ");
        }
        sql.append("  GROUP BY AGENCY_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if(agencyIdList!=null && agencyIdList.size()>0){
            query.setParameterList("agencyIdList", agencyIdList);
        }
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public List<String> getRoleIdlistById(String roleType, String agencyId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" arr.ROLE_NAME ");
        sql.append(" from auth_role_project arp ");
        sql.append(" LEFT JOIN auth_role_roleset arr  on arp.AUTH_ROLE_ID=arr.ROLE_ID ");
        sql.append(" where 1=1 and CATEGORY='2'  and STATE='0'  and ROLE_TYPE=:roleType and arp.AUTH_AGENCY_ID=:agencyId  ");
        sql.append("  GROUP BY arr.ROLE_ID  ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("agencyId", agencyId);
        query.setParameter("roleType", roleType);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public List<Object[]> getRoleIdNamelistById(String roleType, String agencyId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" arr.ROLE_ID,arr.ROLE_NAME ");
        sql.append(" from auth_role_project arp ");
        sql.append(" LEFT JOIN auth_role_roleset arr  on arp.AUTH_ROLE_ID=arr.ROLE_ID ");
        sql.append(" where 1=1 and CATEGORY='2'  and STATE='0'  and ROLE_TYPE=:roleType and arp.AUTH_AGENCY_ID=:agencyId  ");
        sql.append("  GROUP BY arr.ROLE_ID  ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("agencyId", agencyId);
        query.setParameter("roleType", roleType);
        List<Object[]> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public List<Object[]> getAgencyPeopleUser(String projectId, String supplierId, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql.append( " ui.STAFF_ID,ui.STAFF_NAME,ui.SYSNAME,ui.MOBILE,ui.EMAIL,bpp.MODIFY_TIME " );
        sql.append( " from base_project_people bpp " );
        sql.append( " LEFT JOIN user_information ui ON bpp.PEOPLE_ID = ui.STAFF_ID " );
        sql.append( " where 1=1 and bpp.`STATUS`='1' and  ui.STAFF_STATE='1' and ui.STAFF_ID is not null " );
        //机构Id
        if(!StringUtil.isEmpty(projectId)){
            sql.append( " AND bpp.PROJECT_ID  =? " );
            params.add(projectId);
        }
        if(!StringUtil.isEmpty(supplierId)){
            sql.append( " AND bpp.SUPPLIER_ID =? " );
            params.add(supplierId);
        }
        sql.append(" order by ui.STAFF_ID ");
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
    public List<UserInformationEntity> getAgencyPeopleUserAuth(String projectId, String supplierId, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from UserInformationEntity where 1=1 ";
        hql += " and staffState='1'";
        hql += " and staffId in (select peopleId from BaseProjectPeopleEntity where status = '1' and projectId ='"+projectId+"' and supplierId ='" + supplierId + "')";
        hql += " order by staffId";
        Query query = getCurrentSession().createQuery(hql);
        List<UserInformationEntity> userPropertyStaffEntities = query.list();
        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }
        return userPropertyStaffEntities;
    }

    @Override
    public List<String> getRoleNamesByEs(String roleType, String agencyId, String userId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" arr.ROLE_NAME ");
        sql.append(" from auth_role_roleset arr ");
        sql.append(" LEFT JOIN role_staff_projectes_map rsp ON arr.ROLE_ID = rsp.ROLE_ID ");
        sql.append(" where 1=1 and arr.STATE='0' and rsp.STATE='1' and arr.ROLE_TYPE=:roleType  and rsp.AGENCY_ID=:agencyId and rsp.STAFF_ID=:userId  ");
        sql.append(" GROUP BY arr.ROLE_ID  ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("roleType", roleType);
        query.setParameter("agencyId", agencyId);
        query.setParameter("userId", userId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public UserInformationEntity get(String staffId) {
        return (UserInformationEntity)getCurrentSession().get(UserInformationEntity.class, staffId);
    }

    @Override
    public List<Object[]> getRoleIDNamesByEs(String roleType, String agencyId, String userId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" arr.ROLE_ID,arr.ROLE_NAME ");
        sql.append(" from auth_role_roleset arr ");
        sql.append(" LEFT JOIN role_staff_projectes_map rsp ON arr.ROLE_ID = rsp.ROLE_ID ");
        sql.append(" where 1=1 and  arr.STATE='0' and rsp.STATE='1' and arr.ROLE_TYPE=:roleType  and rsp.AGENCY_ID=:agencyId and rsp.STAFF_ID=:userId  ");
        sql.append(" GROUP BY arr.ROLE_ID  ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("roleType", roleType);
        query.setParameter("agencyId", agencyId);
        query.setParameter("userId", userId);
        List<Object[]> list = query.list();
        if(list!=null && list.size()>0){
            return list;
        }else {
            return null;
        }
    }

    @Override
    public void delAgencyRoleUser(String agencyId, String userId, String roleId, String status) {
        String hql = "update RoleStaffProjectMapESEntity set state='"+status+"',modifyOn=:modifyTime where 1=1 and state='1'  ";
        if(!StringUtil.isEmpty(userId)){
            hql+=" and staffId=:userId ";
        }
        if(!StringUtil.isEmpty(agencyId)){
            hql+=" and agencyId=:agencyId ";
        }
        if(!StringUtil.isEmpty(roleId)){
            hql+=" and roleId=:roleId ";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime", new Date());
        if(!StringUtil.isEmpty(userId)){
            query.setParameter("userId", userId);
        }
        if(!StringUtil.isEmpty(agencyId)){
            query.setParameter("agencyId", agencyId);
        }
        if(!StringUtil.isEmpty(roleId)){
            query.setParameter("roleId", roleId);
        }
        query.executeUpdate();
    }

    @Override
    public void saveOrUpdateProjectRoleStaff(RoleStaffProjectMapESEntity roleStaffProjectMapESEntity) {
        StringBuilder sql = new StringBuilder(" INSERT INTO role_staff_projectes_map(STAFF_ID,ROLE_ID,AGENCY_ID,STATE,MODIFY_ON) VALUES(?,?,?,1,?) ON DUPLICATE KEY UPDATE STATE='1',MODIFY_ON=? ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setString(0, roleStaffProjectMapESEntity.getStaffId());
        query.setString(1, roleStaffProjectMapESEntity.getRoleId());
        query.setString(2, roleStaffProjectMapESEntity.getAgencyId());
        query.setString(3, DateUtils.format(new Date()));
        query.setString(4, DateUtils.format(new Date()));
        query.executeUpdate();
    }

    @Override
    public void saveOrUpdateuserAgencyEntity(UserAgencyEntity userAgencyEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(userAgencyEntity);
        session.flush();
    }

    @Override
    public void saveUserInformationEntity(UserInformationEntity userInformationEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(userInformationEntity);
        session.flush();
    }

    @Override
    public void saveUserMapEntity(UserMapEntity userMapEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(userMapEntity);
        session.flush();
    }

    @Override
    public boolean getCheckUserAgencyEntity(String supplierId, String userId,String statuss) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" AGENCY_ID ");
        sql.append(" from user_agency ");
        sql.append(" where 1=1 and  AGENCY_ID=:supplierId and STAFF_ID=:userId and `STATUS`=:statuss ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("supplierId", supplierId);
        query.setParameter("userId", userId);
        query.setParameter("statuss", statuss);
        List<Object[]> list = query.list();
        if(list!=null && list.size()>0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public UserAgencyEntity getUserAgencyEntity(String supplierId, String userId) {
        String hql = "from UserAgencyEntity where  staffId=:userId and agencyId=:supplierId  ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("supplierId",supplierId);
        query.setParameter("userId",userId);
        List<UserAgencyEntity> list = query.list();
        if(list!=null && list.size()>0){
            return  list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public boolean getCheckBaseProjectPeopleEntity(String supplierId, String userId, String projectId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" PEOPLE_ID ");
        sql.append(" from base_project_people");
        sql.append(" where 1=1  and PROJECT_ID=:projectId and SUPPLIER_ID=:supplierId and `STATUS`='1' ");
        if(!StringUtil.isEmpty(userId)){
            sql.append(" and  PEOPLE_ID=:userId  ");
        }
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("supplierId", supplierId);
        if(!StringUtil.isEmpty(userId)){
            query.setParameter("userId", userId);
        }
        query.setParameter("projectId", projectId);
        List<Object[]> list = query.list();
        if(list!=null && list.size()>0){
            return false;
        }else {
            return true;
        }
    }

    @Override
    public UserMapEntity getUserMapEntity(String staffId,String type) {
//        String hql = "from UserMapEntity where state='1' and staffId=:staffId and projectModule=:type  ";
        String hql = "from UserMapEntity where 1=1 and staffId=:staffId and projectModule=:type  ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("staffId",staffId);
        query.setParameter("type",type);
        List<UserMapEntity> list = query.list();
        if(list!=null && list.size()>0){
            return  list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public AuthAgencyESEntity getAuthAgencyESEntityByid(String id) {
        String hql = "from AuthAgencyESEntity where status='1' and agencyId=:id and agencyType='100000002'  ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        List<AuthAgencyESEntity> list = query.list();
        if(list!=null && list.size()>0){
            return  list.get(0);
        }else {
            return null;
        }
    }

    @Override
    public String getRoleIdBylikeName(String roleName, String roleType, String projectId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" arr.ROLE_ID ");
        sql.append(" from auth_role_project arp ");
        sql.append(" LEFT JOIN auth_role_roleset arr ON arp.AUTH_ROLE_ID = arr.ROLE_ID ");
        sql.append(" where 1=1  AND arr.STATE = '0' AND CATEGORY = '2'  ");
        sql.append(" AND arp.AUTH_AGENCY_ID =:projectId and arr.ROLE_TYPE =:roleType   ");
//        sql.append(" AND arp.AUTH_AGENCY_ID =:projectId and arr.ROLE_TYPE =:roleType and arr.ROLE_NAME LIKE:roleName  ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
//        query.setParameter("roleName", "%"+roleName+"%");
        query.setParameter("roleType", roleType);
        query.setParameter("projectId", projectId);
        List<String> list = query.list();
        if(list!=null && list.size()>0){
            return list.get(0).toString();
        }else {
            return "";
        }
    }

    @Override
    public void upBaseProjectPeopleName(String userId,String userName,String abbreviationName,String supplierType,String authStatus) {
        String hql = "update BaseProjectPeopleEntity set peopleName='"+userName+"',modifyTime=:modifyTime ";
        if(!StringUtil.isEmpty(abbreviationName)){
            hql+=",abbreviationName=:abbreviationName";
        }
        if(!StringUtil.isEmpty(supplierType)){
            hql+=",supplierType=:supplierType";
        }
        if(!StringUtil.isEmpty(authStatus)){
            hql+=",status=:authStatus";
        }
        hql+="  where 1=1 and status='1'";
        hql+="  and peopleId=:userId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId", userId);
        if(!StringUtil.isEmpty(abbreviationName)){
            query.setParameter("abbreviationName", abbreviationName);
        }
        if(!StringUtil.isEmpty(supplierType)){
            query.setParameter("supplierType", supplierType);
        }
        if(!StringUtil.isEmpty(authStatus)){
            query.setParameter("authStatus", authStatus);
        }
        query.setParameter("modifyTime", new Date());
        query.executeUpdate();
    }
}
