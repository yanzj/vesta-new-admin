package com.maxrocky.vesta.presistent.baseData.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.ProjectCityEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectOperationEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectProjectEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectStaffRelationEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectProjectRepository;
import com.maxrocky.vesta.domain.model.AgencyEntity;
import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
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
 * Created by chen on 2016/10/19.
 */

@Repository
public class ProjectProjectRepositoryImpl extends HibernateDaoImpl implements ProjectProjectRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * @author chenning
     * @param projectProjectEntity
     */
    @Override
    public void addProjectProject(ProjectProjectEntity projectProjectEntity) {
        Session session = getCurrentSession();
        session.save(projectProjectEntity);
        session.flush();
    }

    /**
     * @param cityId
     * @return projectProjectEntities
     */
    @Override
    public List<ProjectProjectEntity> getProjectProjectsByCityId(String cityId,String staffId) {
        List<ProjectProjectEntity> projectProjectEntityList = new ArrayList<ProjectProjectEntity>();
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
            String sql1 = "select projectId from ProjectStaffEmployEntity where projectRole<>'1' and projectRole is not null and dataType = '1' and status = '1' and dataId in("+str+")";
            Query query2 = getCurrentSession().createQuery(sql1);
            String projectIds = query2.getQueryString();
            String hql1 = "from ProjectProjectEntity where state='1' and id in ("+projectIds+")";
            Query query3 = getCurrentSession().createQuery(hql1);
//            query3.setParameter("cityId",cityId);
            if(!StringUtil.isEmpty(projectIds)){
                List<ProjectProjectEntity> projectProjectEntities = query3.list();
               projectProjectEntityList.addAll(projectProjectEntities);
            }
        }

        String hql5 = "from ProjectProjectEntity where id in (select distinct projectId from ProjectStaffEmployEntity where projectRole<>'1' and projectRole is not null and dataType='2' and status='1' and dataId=:staffId) and state='1'";
        Query query5 = getCurrentSession().createQuery(hql5);
        query5.setParameter("staffId",staffId);
//        query5.setParameter("cityId",cityId);
        List<ProjectProjectEntity> projectProjectEntities2 = query5.list();
        projectProjectEntityList.addAll(projectProjectEntities2);
        return projectProjectEntityList;

//        String hql = "from ProjectProjectEntity where cityId=:cityId and state='1'";
//        Query query = getCurrentSession().createQuery(hql);
//        query.setParameter("cityId",cityId);
//        List<ProjectProjectEntity> projectProjectEntities = query.list();
//        return projectProjectEntities;
    }

    @Override
    public List<ProjectProjectEntity> getProjectProjectsByStaffId(String staffId) {
        List<ProjectProjectEntity> projectProjectEntityList = new ArrayList<ProjectProjectEntity>();
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
//            String sql1 = "select projectId from ProjectStaffEmployEntity where  projectRole is not null and dataType = '1' and status = '1' and dataId in("+str+")";
            String sql1 = "select projectId from ProjectStaffEmployEntity where  dataType = '1' and status = '1' and dataId in("+str+")";
            Query query2 = getCurrentSession().createQuery(sql1);
            String projectIds = query2.getQueryString();
            String hql1 = "from ProjectProjectEntity where state='1' and id in ("+projectIds+")";
            Query query3 = getCurrentSession().createQuery(hql1);
            if(!StringUtil.isEmpty(projectIds)){
                List<ProjectProjectEntity> projectProjectEntities = query3.list();
                projectProjectEntityList.addAll(projectProjectEntities);
            }
        }

//        String hql5 = "from ProjectProjectEntity where id in (select distinct projectId from ProjectStaffEmployEntity where  projectRole is not null and dataType='2' and status='1' and dataId=:staffId) and state='1'";
        String hql5 = "from ProjectProjectEntity where id in (select distinct projectId from ProjectStaffEmployEntity where   dataType='2' and status='1' and dataId=:staffId) and state='1'";

        Query query5 = getCurrentSession().createQuery(hql5);
        query5.setParameter("staffId",staffId);
        List<ProjectProjectEntity> projectProjectEntities2 = query5.list();
        projectProjectEntityList.addAll(projectProjectEntities2);
        return projectProjectEntityList;
    }

    @Override
    public List<ProjectProjectEntity> getProjectProjects() {
        String hql = "from ProjectProjectEntity where  state='1'";
        Query query = getCurrentSession().createQuery(hql);
        List<ProjectProjectEntity> projectProjectEntities = query.list();
        return projectProjectEntities;
    }

    @Override
    public void delProjectProject(String projectId) {
        String hql = "update ProjectProjectEntity set state='0' where id=:projectId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        query.executeUpdate();
    }

    @Override
    public void updateProjectProject(ProjectProjectEntity projectProjectEntity) {
        Session session = getCurrentSession();
        session.update(projectProjectEntity);
        session.flush();
    }

    @Override
    public ProjectProjectEntity getProjectProjectDetail(String projectId) {
        String hql = "from ProjectProjectEntity where id=:projectId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        ProjectProjectEntity projectProjectEntity = (ProjectProjectEntity) query.uniqueResult();
        return projectProjectEntity;
    }

    @Override
    public AuthAgencyESEntity getAuthAgencyES(String projectId, String type) {
        String hql = "from AuthAgencyESEntity where agencyId=:projectId and agencyType=:tyope";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        query.setParameter("tyope",type);
        AuthAgencyESEntity authAgencyESEntity = (AuthAgencyESEntity) query.uniqueResult();
        return authAgencyESEntity;
    }

    @Override
    public List<ProjectProjectEntity> getProjectProjectList(String timeStamp, int autoNum) {
        String hql = "from ProjectProjectEntity where ((modifyOn='"+timeStamp+"' and autoNum>:autoNum) or modifyOn>'"+timeStamp+"') order by modifyOn,autoNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("autoNum",autoNum);
        query.setMaxResults(1000);
        List<ProjectProjectEntity> projectProjectEntities = query.list();
        return projectProjectEntities;
    }

    @Override
    public List<ProjectProjectEntity> getProjectAll(ProjectProjectEntity projectProjectEntity, WebPage webPage) {
//        String hql = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
//                " from UserAgencymapEntity where status='1' and staffId ='"+staffId+"')";
//        Query query = getCurrentSession().createQuery(hql);
//        List<String> paths = query.list();
//        String str = "";
//        if(paths!=null&&paths.size()>0){
//            for(String ids:paths){
//                str += ids.replace("/","','");
//            }
//        }
//        String projectIds="";
//        if(!StringUtil.isEmpty(str)){
//            str = str.substring(2)+"'";
//            String sql1 = "select projectId from ProjectStaffEmployEntity where dataType = '1' and status = '1' and dataId in("+str+")";
//            Query query2 = getCurrentSession().createQuery(sql1);
//            projectIds = query2.getQueryString();                                              //当前人所在组织机构对应的项目ID列表
//        }
//
//        String hql5 = "select distinct projectId from ProjectStaffEmployEntity where dataType='2' and status='1' and dataId='"+staffId+"'";
//        Query query5 = getCurrentSession().createQuery(hql5);
//        String ids = query5.getQueryString();                                          //当前人直接对应的项目ID列表
//
        List<Object> params = new ArrayList<Object>();
        String hql6 = "from ProjectProjectEntity where 1=1";
        if(!StringUtil.isEmpty(projectProjectEntity.getName())){
            hql6 += " and name like '%"+projectProjectEntity.getName()+"%'";
        }
//        if(StringUtil.isEmpty(projectIds)){
//            hql6 += "and state='1' and id in("+ids+") order by modifyOn desc";
//        }else{
//            hql6 += "and state='1' and （id in("+projectIds+") or id in("+ids+")） order by modifyOn desc";
//        }
        if(!StringUtil.isEmpty(projectProjectEntity.getCityId())){
            hql6+=" and cityId='"+projectProjectEntity.getCityId()+"'";
        }
        hql6 += "and state='1' order by modifyOn desc";
        Query query6 = getCurrentSession().createQuery(hql6);
        List<ProjectProjectEntity> projectProjectEntities = query6.list();
        if(webPage !=null){
            return this.findByPage(hql6, webPage,params);
        }
        return projectProjectEntities;
    }

    @Override
    public List<Object[]> getDutyList(String projectId) {
        String sql = "select a.AGENCY_ID,a.AGENCY_NAME from role_agency as a,project_staff_employ as b where a.AGENCY_ID=b.DATA_ID and a.AGENCY_TYPE='4' and b.DATA_TYPE='1' and b.STATUS='1' and b.PROJECT_ROLE is null and b.PROJECT_ID='"+projectId+"'";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> objects = query.list();
        return objects;
    }

    @Override
    public List<Object[]> getDutyPeople(String dutyId) {
        String sql = "select a.STAFF_ID,a.STAFF_NAME from user_propertyStaff as a,user_agency_map as b where a.STAFF_ID=b.STAFF_ID and a.STAFF_STATE='1' and b.STATUS='1' and b.AGENCY_ID='"+dutyId+"'";
        Query query = getCurrentSession().createSQLQuery(sql);


        List<Object[]> objects = query.list();
        return objects;
    }

    @Override
    public List<Object[]> getSurveyorUserList(String projectId) {
        String sql = "select a.STAFF_ID,a.STAFF_NAME from user_propertyStaff as a,user_agency_map as b,project_staff_employ as c,role_agency as d where a.STAFF_ID=b.STAFF_ID and a.STAFF_STATE='1' and b.STATUS='1' and b.AGENCY_ID=c.DATA_ID " +
                "and c.DATA_TYPE='1' and c.STATUS='1' and c.DATA_ID=d.AGENCY_ID and d.AGENCY_TYPE='5' and c.PROJECT_ROLE is null and c.PROJECT_ID='"+projectId+"'";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> objects = query.list();
        return objects;
    }

    @Override
    public List<Object[]> getOwnerUserList(String projectId) {
        List<Object[]> objectAll = new ArrayList<Object[]>();
        //直接获取甲方权限对应人员
        String sql = "select a.STAFF_ID,a.STAFF_NAME from user_propertyStaff as a,project_staff_employ as b where a.STAFF_ID = b.DATA_ID and DATA_TYPE='2' and PROJECT_ROLE='1' and b.STATUS='1' and a.STAFF_STATE='1' and PROJECT_ID='"+projectId+"'";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> objects = query.list();
        objectAll.addAll(objects);
        //从部门间接获取甲方权限对应人员
        String sql2 = "select a.AGENCY_PATH from project_staff_employ as a,role_agency as b where a.DATA_TYPE='1' and a.PROJECT_ROLE='1' and a.STATUS='1' and a.DATA_ID=b.AGENCY_ID and a.PROJECT_ID='"+projectId+"'";
        Query query1 = getCurrentSession().createQuery(sql2);
        List<String> agencyPaths = query1.list();
        for(String aPath:agencyPaths){
            String sql3 = "select a.STAFF_ID,a.STAFF_NAME from user_propertyStaff as a,user_agency_map as b where a.STAFF_ID=b.STAFF_ID and b.AGENCY_ID in(select distinct AGENCY_ID from role_agency where AGENCY_PATH like '"+aPath+"%')";
            Query query2 = getCurrentSession().createSQLQuery(sql3);
            List<Object[]> objects1 = query2.list();
            objectAll.addAll(objects1);
        }
        return objectAll;
    }

    @Override
    public List<ProjectProjectEntity> getProjectByName(String name) {
        String sql = " FROM ProjectProjectEntity WHERE state='1' AND name like '%"+name+"%'";
        Query query = getCurrentSession().createQuery(sql);
        List<ProjectProjectEntity> list = query.list();
        return list;
    }

    @Override
    public List<ProjectOperationEntity> getProjectOperation() {
        String sql = " FROM ProjectOperationEntity WHERE state='1' ";
        Query query = getCurrentSession().createQuery(sql);
        List<ProjectOperationEntity> list = query.list();
        return list;
    }

    @Override
    public List<ProjectProjectEntity> getProjectList(String parentId) {
        String sql = " FROM ProjectProjectEntity WHERE state='1' AND cityId in(SELECT cityId FROM ProjectCityEntity WHERE state='1' AND optId='"+parentId+"' ) ";
        Query query = getCurrentSession().createQuery(sql);
        List<ProjectProjectEntity> list = query.list();
        return list;
    }

    @Override
    public List<ProjectStaffRelationEntity> getProjectRole() {
        String sql = " FROM ProjectStaffRelationEntity WHERE 1=1 AND state='1' ";
        Query query = getCurrentSession().createQuery(sql);
        List<ProjectStaffRelationEntity> list = query.list();
        return list;
    }

    @Override
    public void updateProjectStaff() {
        String sql = " UPDATE ProjectStaffRelationEntity SET state = '0',MODIFY_DATE =:date WHERE state='1' ";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameter("date",DateUtils.format(new Date()));
        query.executeUpdate();
    }

    @Override
    public void addProjectStaff(ProjectStaffRelationEntity projectStaffRelationEntity) {
        String sql =" INSERT INTO project_staff_relation(PROJECT_ID,STAFF_ID,UTYPE,PTYPE,MODIFY_DATE,STATE) VALUES(?,?,?,?,?,1) ON DUPLICATE KEY UPDATE STATE='1',MODIFY_DATE=? ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setString(0, projectStaffRelationEntity.getProjectId());
        query.setString(1, projectStaffRelationEntity.getStaffId());
        query.setString(2, projectStaffRelationEntity.getuType());
        query.setString(3, projectStaffRelationEntity.getpType());
        query.setString(4, DateUtils.format(new Date()));
        query.setString(5, DateUtils.format(new Date()));
        query.executeUpdate();
    }

    @Override
    public List<ProjectProjectEntity> getProjectListByArea(List<String> areaIds) {
        String sql = " FROM ProjectProjectEntity WHERE state='1' AND cityId IN(SELECT cityId FROM ProjectCityEntity WHERE state='1' AND optId IN(:areaId) ) ";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameterList("areaId",areaIds);
        List<ProjectProjectEntity> list = query.list();
        return list;
    }

    @Override
    public List<ProjectOperationEntity> getAreaById(List<String> areaId) {
        String sql = " FROM ProjectOperationEntity WHERE 1=1 AND state='1' AND optId IN(:areaId) ";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameterList("areaId",areaId);
        List<ProjectOperationEntity> list = query.list();
        return list;
    }

    @Override
    public List<ProjectProjectEntity> getProjectById(List<String> projectId) {
        String sql = " FROM ProjectProjectEntity WHERE 1=1 AND state='1' AND id IN(:projectId) ";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameterList("projectId",projectId);
        List<ProjectProjectEntity> list = query.list();
        return list;
    }

    @Override
    public List<UserPropertyStaffEntity> getUserByStaffId(List<String> userId) {
        String sql = " FROM UserPropertyStaffEntity WHERE 1=1 AND staffState='1' AND staffId IN(:staffId) ";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameterList("staffId",userId);
        List<UserPropertyStaffEntity> list = query.list();
        return list;
    }

    @Override
    public List<AgencyEntity> getAgencyBydepartmentId(List<String> departmentId) {
        String sql = " FROM AgencyEntity WHERE 1=1 AND status='1' AND agencyId IN(:departmentId)";
        Query query = getCurrentSession().createQuery(sql);
        query.setParameterList("departmentId",departmentId);
        List<AgencyEntity> list = query.list();
        return list;
    }
}
