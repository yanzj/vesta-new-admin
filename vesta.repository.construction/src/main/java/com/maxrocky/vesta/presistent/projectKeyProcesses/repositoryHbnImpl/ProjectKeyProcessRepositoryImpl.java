package com.maxrocky.vesta.presistent.projectKeyProcesses.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.ProjectProjectEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.model.ProjectKeyProcessesEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.model.ProjectKeyProcessesTargetDetailsEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.model.ProjectKeyProcessesTargetEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.repository.ProjectKeyProcessesRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Talent on 2016/11/22.
 */
@Repository
public class ProjectKeyProcessRepositoryImpl extends HibernateDaoImpl implements ProjectKeyProcessesRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 新增关键工序
     *
     * @param projectKeyProcessesEntity
     * @return
     */
    @Override
    public boolean addKeyProcesses(ProjectKeyProcessesEntity projectKeyProcessesEntity) {
        Session session = getCurrentSession();
        session.save(projectKeyProcessesEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public boolean modifyKeyProcesses(ProjectKeyProcessesEntity projectKeyProcessesEntity) {
        Session session = getCurrentSession();
        session.update(projectKeyProcessesEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 通过APPID查询工序信息
     *
     * @param appId
     * @return
     */
    @Override
    public ProjectKeyProcessesEntity getKeyProcessesInfoByAppId(String appId) {
        String hql = " FROM ProjectKeyProcessesEntity WHERE appId = '" + appId + "' ";
        Query query = getCurrentSession().createQuery(hql);
        return (ProjectKeyProcessesEntity) query.uniqueResult();
    }

    @Override
    public boolean addKeyProcessesTarget(ProjectKeyProcessesTargetEntity projectKeyProcessesTargetEntity) {
        Session session = getCurrentSession();
        session.save(projectKeyProcessesTargetEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public boolean modifyKeyProcessesTarget(ProjectKeyProcessesTargetEntity projectKeyProcessesTargetEntity) {
        Session session = getCurrentSession();
        session.update(projectKeyProcessesTargetEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public boolean addKeyProcessesTargetDetails(ProjectKeyProcessesTargetDetailsEntity projectKeyProcessesTargetDetailsEntity) {
        Session session = getCurrentSession();
        session.save(projectKeyProcessesTargetDetailsEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public ProjectKeyProcessesEntity getKeyProcessesInfoByProcessId(String processId) {
        String hql = " FROM ProjectKeyProcessesEntity WHERE  processId = '" + processId + "' ";
        Query query = getCurrentSession().createQuery(hql);
        return (ProjectKeyProcessesEntity) query.uniqueResult();
    }

    @Override
    public List<Object[]> getKeyProcessesTargetListByProcessId(String processId) {
        String sql = "SELECT t.PROCESS_ID,t.ID,t.TARGET_ID,t.TARGET_NAME,t.QUALIFIED_STATE,td.DESCRIPTION,pi.IMAGE_URL,t.FLAG " +
                "FROM project_images pi " +
                "LEFT JOIN project_key_processes_target_details td ON pi.BUSINESS_ID = td.ID " +
                "LEFT JOIN project_key_processes_target t ON td.PROCESS_TARGET_ID = t.ID " +
                "WHERE td.TYPE IS NULL ";
        if (!StringUtil.isEmpty(processId)) {
            sql += " AND t.PROCESS_ID='" + processId + "'";
        }
        sql += " ORDER BY t.CREATE_ON ASC ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> searchKeyProcessesTargetListByProcessId(String processId) {
        String sql = "SELECT pt.NAME,t.QUALIFIED_STATE,pt.DESCRIPTION AS targetDescription,td.DESCRIPTION,pi.IMAGE_URL,t.ID " +
                "FROM project_images pi " +
                "LEFT JOIN project_key_processes_target_details td ON pi.BUSINESS_ID = td.ID " +
                "LEFT JOIN project_key_processes_target t ON td.PROCESS_TARGET_ID = t.ID " +
                "LEFT JOIN project_target pt ON pt.ID = t.TARGET_ID " +
                "WHERE td.TYPE IS NULL ";
        if (!StringUtil.isEmpty(processId)) {
            sql += " AND t.PROCESS_ID='" + processId + "'";
        }
        sql += " ORDER BY t.CREATE_ON ASC ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getKeyProcessesTargetDetailListByProcessId(String processTargetId) {
        String sql = "SELECT td.ID,td.PROCESS_TARGET_ID,td.DESCRIPTION,td.CHANGE_TIME,pi.IMAGE_URL,td.TYPE,td.STATE,td.SERIAL_NUMBER " +
                "FROM project_images pi " +
                "LEFT JOIN project_key_processes_target_details td ON pi.BUSINESS_ID = td.ID " +
                "WHERE td.TYPE IS NOT NULL ";
        if (!StringUtil.isEmpty(processTargetId)) {
            sql += " AND td.PROCESS_TARGET_ID='" + processTargetId + "'";
        }
        sql += " ORDER BY td.CREATE_ON ASC ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getPartyBTargetDetailListByProcessId(String processTargetId) {
        String sql = "SELECT t.TARGET_NAME,td.DESCRIPTION,pi.IMAGE_URL " +
                "FROM project_images pi " +
                "LEFT JOIN project_key_processes_target_details td ON pi.BUSINESS_ID = td.ID " +
                "LEFT JOIN project_key_processes_target t ON td.PROCESS_TARGET_ID = t.ID " +
                "WHERE td.TYPE='0' ";
        if (!StringUtil.isEmpty(processTargetId)) {
            sql += " AND td.PROCESS_TARGET_ID='" + processTargetId + "'";
        }
        sql += " ORDER BY td.CREATE_ON ASC ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getSupervisionTargetDetailListByProcessId(String processTargetId) {
        String sql = "SELECT t.TARGET_NAME,td.DESCRIPTION,pi.IMAGE_URL " +
                "FROM project_images pi " +
                "LEFT JOIN project_key_processes_target_details td ON pi.BUSINESS_ID = td.ID " +
                "LEFT JOIN project_key_processes_target t ON td.PROCESS_TARGET_ID = t.ID " +
                "WHERE td.TYPE='1' ";
        if (!StringUtil.isEmpty(processTargetId)) {
            sql += " AND td.PROCESS_TARGET_ID='" + processTargetId + "'";
        }
        sql += " ORDER BY td.CREATE_ON ASC ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getPartyATargetDetailListByProcessId(String processTargetId) {
        String sql = "SELECT t.TARGET_NAME,td.DESCRIPTION,pi.IMAGE_URL " +
                "FROM project_images pi " +
                "LEFT JOIN project_key_processes_target_details td ON pi.BUSINESS_ID = td.ID " +
                "LEFT JOIN project_key_processes_target t ON td.PROCESS_TARGET_ID = t.ID " +
                "WHERE td.TYPE='2' ";
        if (!StringUtil.isEmpty(processTargetId)) {
            sql += " AND td.PROCESS_TARGET_ID='" + processTargetId + "'";
        }
        sql += " ORDER BY td.CREATE_ON ASC ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public boolean keyProcessesCheckForUpdate(String id, String time, String projectId, String creatBy, String type) {
        String sql = "select count(1) from project_key_processes kp  ";
        sql += " LEFT JOIN project_copy pc ON kp.PROCESS_ID=pc.BUSINESS ";
        sql += " LEFT JOIN project_copy_details pcd ON pc.ID=pcd.COPY_ID ";
        sql += " where 1=1 and kp.STATE != 'AbnormalShutdown'";
        if (!StringUtil.isEmpty(time)) {
            if (!StringUtil.isEmpty(id)) {
                if (!StringUtil.isEmpty(projectId)) {
                    sql += " and kp.MODIFY_ON > '" + time + "' or (kp.MODIFY_ON = '" + time + "' and kp.ID > '" + id + "' and kp.PROJECT_ID = '" + projectId + "')";
                } else {
                    sql += " and kp.MODIFY_ON > '" + time + "' or (kp.MODIFY_ON = '" + time + "' and kp.ID > '" + id + "')";
                }
            } else {
                sql += " and kp.MODIFY_ON > '" + time + "'";
            }
        }
        //第三方监理
        if ("2".equals(type)) {
            sql += " and ( pcd.MEMBER_ID='" + creatBy + "' or kp.SUPERVISOR_ID='" + creatBy + "' or kp.HANDLE_PEOPLE_ID='" + creatBy + "')";
        }
        //乙方
        if ("3".equals(type)) {
            sql += " and (kp.HANDLE_PEOPLE_ID='" + creatBy + "' or (kp.ASSIGN_ID='"+creatBy+"' and kp.STATE='合格') or pcd.MEMBER_ID='" + creatBy + "') ";
        }
//        sql+=" GROUP BY kp.PROCESS_ID";
        Query query = getCurrentSession().createSQLQuery(sql);
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Object[]> getAllKeyProcessesQuestion(String id, String time, String projectId,String creatBy,String type) {
        String sql = "select kp.ID,kp.PROCESS_ID from project_key_processes kp  ";
        sql += " LEFT JOIN project_copy pc ON kp.PROCESS_ID=pc.BUSINESS ";
        sql += " LEFT JOIN project_copy_details pcd ON pc.ID=pcd.COPY_ID ";
        sql += " where 1=1 and kp.STATE != 'AbnormalShutdown'";
        if (!StringUtil.isEmpty(time)) {
            if (!StringUtil.isEmpty(id)) {
                if (!StringUtil.isEmpty(projectId)) {
                    sql += " and kp.MODIFY_ON > '" + time + "' or (kp.MODIFY_ON = '" + time + "' and kp.ID > '" + id + "' and kp.PROJECT_ID = '" + projectId + "')";
                } else {
                    sql += " and kp.MODIFY_ON > '" + time + "' or (kp.MODIFY_ON = '" + time + "' and kp.ID > '" + id + "')";
                }
            } else {
                sql += " and kp.MODIFY_ON > '" + time + "'";
            }
        }
        //第三方监理
        if ("2".equals(type)) {
            sql += " and ( pcd.MEMBER_ID='" + creatBy + "' or kp.SUPERVISOR_ID='" + creatBy + "' or kp.HANDLE_PEOPLE_ID='" + creatBy + "')";
        }
        //乙方
        if ("3".equals(type)) {
            sql += " and (kp.HANDLE_PEOPLE_ID='" + creatBy + "' or (kp.ASSIGN_ID='"+creatBy+"' and kp.STATE='合格') or pcd.MEMBER_ID='" + creatBy +"') ";
        }
        sql += " GROUP BY kp.PROCESS_ID ORDER BY kp.MODIFY_ON,kp.ID ";
//        String hql = " FROM ProjectKeyProcessesEntity  WHERE 1=1 and state != 'AbnormalShutdown'";
//        if (!StringUtil.isEmpty(time)) {
//            if (!StringUtil.isEmpty(id)) {
//                if (!StringUtil.isEmpty(projectId)) {
//                    hql += " and modifyOn > '" + time + "' or (modifyOn = '" + time + "' and id > '" + id + "' and projectId = '" + projectId + "')";
//                } else {
//                    hql += " and modifyOn > '" + time + "' or (modifyOn = '" + time + "' and id > '" + id + "')";
//                }
//            } else {
//                hql += " and modifyOn > '" + time + "'";
//            }
//        }
//        hql += " ORDER BY modifyOn,id ";

//        Query query = getCurrentSession().createQuery(hql);
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setMaxResults(500);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> addUpKeyProcessesByProjectId(String projectId) {
        Session session = getCurrentSession();
        String sql = "SELECT pb.PROJECT_ID,pb.BUILD_ID,pb.BUILD_NAME," +
                "(SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE != 'AbnormalShutdown' AND pkp.BUILDING_ID=pb.BUILD_ID) AS hasbeengeton," +
                "(SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = '合格' AND pkp.BUILDING_ID=pb.BUILD_ID) AS qualified," +
                "(SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = '整改中' AND pkp.BUILDING_ID=pb.BUILD_ID) AS unQualified," +
                "(SELECT count(1) FROM project_key_processes pkp WHERE pkp.FIRST_QUALIFIED_STATE = '合格' AND pkp.BUILDING_ID=pb.BUILD_ID) AS onePass " +
                " FROM project_building pb WHERE pb.BUILD_STATE='1'AND pb.PROJECT_ID = :projectId ORDER BY CONVERT(pb.PROJECT_ID USING gbk ) ASC, pb.AUTO_NUM ASC";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter("projectId", projectId);
        List<Object[]> acceptanceList = sqlQuery.list();
        return acceptanceList;
    }

    @Override
    public ProjectKeyProcessesTargetEntity getKeyProcessesTargetListById(String processTargetId) {
        String hql = " FROM ProjectKeyProcessesTargetEntity WHERE  id = '" + processTargetId + "' ";
        Query query = getCurrentSession().createQuery(hql);
        return (ProjectKeyProcessesTargetEntity) query.uniqueResult();
    }

    @Override
    public List<ProjectKeyProcessesEntity> searchKeyProcessesList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from ProjectKeyProcessesEntity kp where 1=1 ";
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            hql += " and kp.projectId = '" + map.get("projectId").toString() + "' ";
//            params.add(map.get("projectNum").toString());
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            hql += " and kp.buildingId = '" + map.get("buildingId").toString() + "' ";
        }
        if (map.get("threeSort") != null && !"".equals(map.get("threeSort").toString())) {
            hql += " and kp.fourSort IN (SELECT pc.categoryId FROM ProjectCategoryEntity pc WHERE pc.domain='2' AND pc.level=4 AND pc.parentId = ? ) ";
            params.add(map.get("threeSort").toString());
        }
        if (map.get("state") != null && !"".equals(map.get("state").toString())) {
            hql += " and kp.state = '" + map.get("state").toString() + "' ";
        }
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            hql += " and kp.createOn >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            hql += " and kp.createOn <= '" + endDate + "' ";
        }
        if (map.get("partyPrincipalName") != null && !"".equals(map.get("partyPrincipalName").toString())) {
            hql += " and kp.partyPrincipalName like '%" + map.get("partyPrincipalName").toString() + "%' ";
        }
        if (map.get("supervisorName") != null && !"".equals(map.get("supervisorName").toString())) {
            hql += " and kp.supervisorName like '%" + map.get("supervisorName").toString() + "%'";
        }
        if (map.get("supplier") != null && !"".equals(map.get("supplier").toString())) {
            hql += " and kp.supplierName like '%" + map.get("supplier").toString() + "%' ";
        }
        if (map.get("assignName") != null && !"".equals(map.get("assignName").toString())) {
            hql += " and kp.assignName like '%" + map.get("assignName").toString() + "%'";
        }
        if (map.get("severityRating") != null && !StringUtil.isEmpty(map.get("severityRating").toString())) {
            hql += " and kp.severityRating = '" + map.get("severityRating").toString() + "'";
        }
        hql += " order by kp.modifyOn desc";

        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<ProjectKeyProcessesEntity> projectKeyProcessesEntityList = (List<ProjectKeyProcessesEntity>) getHibernateTemplate().find(hql, params.toArray());
        return projectKeyProcessesEntityList;
    }

    @Override
    public List<ProjectKeyProcessesEntity> searchKeyProcessesList(Map map, WebPage webPage, String staffId) {
        List<Object> params = new ArrayList<Object>();
        String projectId1s = null;
        String projectId2s = null;
        String hql1 = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
                " from UserAgencymapEntity where status='1' and staffId = '" + staffId + "')";
        Query query = getCurrentSession().createQuery(hql1);
        List<String> paths = query.list();
        String str = "";
        if (paths != null && paths.size() > 0) {
            for (String ids : paths) {
                str += ids.replace("/", "','");
            }
        }
        if (!StringUtil.isEmpty(str)) {
            str = str.substring(2) + "'";
            String sql1 = "select projectId from ProjectStaffEmployEntity where  dataType = '1' and status = '1' and dataId in(" + str + ")";
            Query query2 = getCurrentSession().createQuery(sql1);
            projectId1s = query2.getQueryString();
        }

        String hql3 = "select distinct projectId from ProjectStaffEmployEntity where  dataType='2' and status='1' and dataId='" + staffId + "'";
        Query query5 = getCurrentSession().createQuery(hql3);
        projectId2s = query5.getQueryString();

        String hql = " from ProjectKeyProcessesEntity kp where 1=1 ";
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            hql += " and kp.projectId = '" + map.get("projectId").toString() + "' ";
//            params.add(map.get("projectNum").toString());
        } else {
            hql += " and (kp.projectId in(" + projectId1s + ") or kp.projectId in(" + projectId2s + ")) ";
//            hql +=" and kp.projectId in (select distinct projectId from ProjectStaffEmployEntity " +
//                    "where projectRole='1' and projectRole is not null and dataType='2' and status='1' and dataId='"+staffId+"') and state='1'";
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            hql += " and kp.buildingId = '" + map.get("buildingId").toString() + "' ";
        }
        if (map.get("threeSort") != null && !"".equals(map.get("threeSort").toString())) {
            hql += " and kp.fourSort IN (SELECT pc.categoryId FROM ProjectCategoryEntity pc WHERE pc.domain='2' AND pc.level=4 AND pc.parentId = ? ) ";
            params.add(map.get("threeSort").toString());
        }
        if (map.get("state") != null && !"".equals(map.get("state").toString())) {
            hql += " and kp.state = '" + map.get("state").toString() + "' ";
        }
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            hql += " and kp.createOn >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            hql += " and kp.createOn <= '" + endDate + "' ";
        }
        if (map.get("partyPrincipalName") != null && !"".equals(map.get("partyPrincipalName").toString())) {
            hql += " and kp.partyPrincipalName like '%" + map.get("partyPrincipalName").toString() + "%' ";
        }
        if (map.get("supervisorName") != null && !"".equals(map.get("supervisorName").toString())) {
            hql += " and kp.supervisorName like '%" + map.get("supervisorName").toString() + "%'";
        }
        if (map.get("supplier") != null && !"".equals(map.get("supplier").toString())) {
            hql += " and kp.supplierName like '%" + map.get("supplier").toString() + "%' ";
        }
        if (map.get("assignName") != null && !"".equals(map.get("assignName").toString())) {
            hql += " and kp.assignName like '%" + map.get("assignName").toString() + "%'";
        }
        if (map.get("severityRating") != null && !StringUtil.isEmpty(map.get("severityRating").toString())) {
            hql += " and kp.severityRating = '" + map.get("severityRating").toString() + "'";
        }
        hql += " order by kp.modifyOn desc";

        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<ProjectKeyProcessesEntity> projectKeyProcessesEntityList = (List<ProjectKeyProcessesEntity>) getHibernateTemplate().find(hql, params.toArray());
        return projectKeyProcessesEntityList;
    }

    @Override
    public List<ProjectKeyProcessesEntity> searchKeyProcessesList(Map map) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from ProjectKeyProcessesEntity kp where 1=1 ";
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            hql += " and kp.projectId = '" + map.get("projectId").toString() + "' ";
//            params.add(map.get("projectNum").toString());
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            hql += " and kp.buildingId = '" + map.get("buildingId").toString() + "' ";
        }
        if (map.get("threeSort") != null && !"".equals(map.get("threeSort").toString())) {
            hql += " and kp.fourSort IN (SELECT pc.categoryId FROM ProjectCategoryEntity pc WHERE pc.domain='2' AND pc.level=4 AND pc.parentId = ? ) ";
            params.add(map.get("threeSort").toString());
        }
        if (map.get("state") != null && !"".equals(map.get("state").toString())) {
            hql += " and kp.state = '" + map.get("state").toString() + "' ";
        }
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            hql += " and kp.createOn >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            hql += " and kp.createOn <= '" + endDate + "' ";
        }
        if (map.get("partyPrincipalName") != null && !"".equals(map.get("partyPrincipalName").toString())) {
            hql += " and kp.partyPrincipalName like '%" + map.get("partyPrincipalName").toString() + "%' ";
        }
        if (map.get("supervisorName") != null && !"".equals(map.get("supervisorName").toString())) {
            hql += " and kp.supervisorName like '%" + map.get("supervisorName").toString() + "%'";
        }
        if (map.get("supplier") != null && !"".equals(map.get("supplier").toString())) {
            hql += " and kp.supplierName like '%" + map.get("supplier").toString() + "%' ";
        }
        if (map.get("assignName") != null && !"".equals(map.get("assignName").toString())) {
            hql += " and kp.assignName like '%" + map.get("assignName").toString() + "%'";
        }
        if (map.get("severityRating") != null && !StringUtil.isEmpty(map.get("severityRating").toString())) {
            hql += " and kp.severityRating = '" + map.get("severityRating").toString() + "'";
        }
        hql += " order by kp.modifyOn desc";
        List<ProjectKeyProcessesEntity> projectKeyProcessesEntityList = (List<ProjectKeyProcessesEntity>) getHibernateTemplate().find(hql, params.toArray());
        return projectKeyProcessesEntityList;
    }

    @Override
    public List<Object[]> searchKeyProcessesCountList(Map map, WebPage webPage, String staffId) {
        String projectId1s = null;
        String projectId2s = null;
        String hql1 = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
                " from UserAgencymapEntity where status='1' and staffId = '" + staffId + "')";
        Query query1 = getCurrentSession().createQuery(hql1);
        List<String> paths = query1.list();
        String str = "";
        if (paths != null && paths.size() > 0) {
            for (String ids : paths) {
                str += ids.replace("/", "','");
            }
        }
        if (!StringUtil.isEmpty(str)) {
            str = str.substring(2) + "'";
            String sql2 = "select PROJECT_ID from project_staff_employ where DATA_TYPE = '1' and STATUS = '1' and DATA_ID in(" + str + ")";
            Query query2 = getCurrentSession().createSQLQuery(sql2);
            projectId1s = query2.getQueryString();
        }

        String hql3 = "select distinct PROJECT_ID from project_staff_employ where DATA_TYPE='2' and STATUS='1' and DATA_ID='" + staffId + "'";
        Query query3 = getCurrentSession().createSQLQuery(hql3);
        projectId2s = query3.getQueryString();

        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" pb.BUILD_NAME,TENDER_NAME,p.PROJECT_NAME,");
//        sql.append(" pb.BUILD_NAME,p.PROJECT_NAME,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE != 'AbnormalShutdown' AND pkp.BUILDING_ID=pb.BUILD_ID) AS hasbeengeton,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = '合格' AND pkp.BUILDING_ID=pb.BUILD_ID) AS qualified,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = '整改中' AND pkp.BUILDING_ID=pb.BUILD_ID) AS unQualified,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = 'AbnormalShutdown' AND pkp.FIRST_QUALIFIED_STATE = '合格' AND pkp.BUILDING_ID=pb.BUILD_ID) AS onePass, ");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = 'AbnormalShutdown' AND pkp.BUILDING_ID=pb.BUILD_ID) AS abnormalShutdown");
        sql.append(" FROM project_building pb ");
        sql.append(" LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID  ");
        sql.append(" LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pb.BUILD_ID  ");
        sql.append(" LEFT JOIN project_tenders t ON t.TENDER_ID = tb.TENDERS_ID  ");
        sql.append(" WHERE 1=1 AND pb.BUILD_STATE = '1' ");
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            sql.append(" and p.PROJECT_ID = '" + map.get("projectId").toString() + "'");
        } else {
            sql.append(" and (p.PROJECT_ID in(" + projectId1s + ") or p.PROJECT_ID in(" + projectId2s + ")) ");
        }
        if (map.get("tenderId") != null && !"".equals(map.get("tenderId").toString())) {
            sql.append(" and tb.TENDERS_ID = '" + map.get("tenderId").toString() + "'");
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            sql.append(" and pb.BUILD_ID ='" + map.get("buildingId").toString() + "'");
        }
        sql.append(" ORDER BY CONVERT( p.PROJECT_NAME USING gbk ) DESC,pb.AUTO_NUM ASC ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (webPage != null) {
            query.setFirstResult(webPage.getStartRow());
            query.setMaxResults(webPage.getPageSize());
        }
        return query.list();
    }

    @Override
    public List<Object[]> searchKeyProcessesCountList(Map map, String staffId) {
        String projectId1s = null;
        String projectId2s = null;
        String hql1 = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
                " from UserAgencymapEntity where status='1' and staffId = '" + staffId + "')";
        Query query1 = getCurrentSession().createQuery(hql1);
        List<String> paths = query1.list();
        String str = "";
        if (paths != null && paths.size() > 0) {
            for (String ids : paths) {
                str += ids.replace("/", "','");
            }
        }
        if (!StringUtil.isEmpty(str)) {
            str = str.substring(2) + "'";
            String sql2 = "select PROJECT_ID from project_staff_employ where DATA_TYPE = '1' and STATUS = '1' and DATA_ID in(" + str + ")";
            Query query2 = getCurrentSession().createSQLQuery(sql2);
            projectId1s = query2.getQueryString();
        }

        String hql3 = "select distinct PROJECT_ID from project_staff_employ where DATA_TYPE='2' and STATUS='1' and DATA_ID='" + staffId + "'";
        Query query3 = getCurrentSession().createSQLQuery(hql3);
        projectId2s = query3.getQueryString();

        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" pb.BUILD_NAME,TENDER_NAME,p.PROJECT_NAME,");
//        sql.append(" pb.BUILD_NAME,p.PROJECT_NAME,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE != 'AbnormalShutdown' AND pkp.BUILDING_ID=pb.BUILD_ID) AS hasbeengeton,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = '合格' AND pkp.BUILDING_ID=pb.BUILD_ID) AS qualified,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = '整改中' AND pkp.BUILDING_ID=pb.BUILD_ID) AS unQualified,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = 'AbnormalShutdown' AND pkp.FIRST_QUALIFIED_STATE = '合格' AND pkp.BUILDING_ID=pb.BUILD_ID) AS onePass, ");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = 'AbnormalShutdown' AND pkp.BUILDING_ID=pb.BUILD_ID) AS abnormalShutdown");
        sql.append(" FROM project_building pb ");
        sql.append(" LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID  ");
        sql.append(" LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pb.BUILD_ID  ");
        sql.append(" LEFT JOIN project_tenders t ON t.TENDER_ID = tb.TENDERS_ID  ");
        sql.append(" WHERE 1=1 AND pb.BUILD_STATE = '1' ");
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            sql.append(" and p.PROJECT_ID = '" + map.get("projectId").toString() + "'");
        } else {
            sql.append(" and (p.PROJECT_ID in(" + projectId1s + ") or p.PROJECT_ID in(" + projectId2s + ")) ");
        }
        if (map.get("tenderId") != null && !"".equals(map.get("tenderId").toString())) {
            sql.append(" and tb.TENDERS_ID = '" + map.get("tenderId").toString() + "'");
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            sql.append(" and pb.BUILD_ID ='" + map.get("buildingId").toString() + "'");
        }
        sql.append(" ORDER BY CONVERT( p.PROJECT_NAME USING gbk ) DESC,pb.AUTO_NUM ASC ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return query.list();
    }

    @Override
    public List<Object[]> searchKeyProcessesCountList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" pb.BUILD_NAME,TENDER_NAME,p.PROJECT_NAME,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE != 'AbnormalShutdown' AND pkp.BUILDING_ID=pb.BUILD_ID) AS hasbeengeton,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = '合格' AND pkp.BUILDING_ID=pb.BUILD_ID) AS qualified,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = '整改中' AND pkp.BUILDING_ID=pb.BUILD_ID) AS unQualified,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE != 'AbnormalShutdown' AND pkp.FIRST_QUALIFIED_STATE = '合格' AND pkp.BUILDING_ID=pb.BUILD_ID) AS onePass, ");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = 'AbnormalShutdown' AND pkp.BUILDING_ID=pb.BUILD_ID) AS abnormalShutdown");
        sql.append(" FROM project_building pb ");
        sql.append(" LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID  ");
        sql.append(" LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pb.BUILD_ID  ");
        sql.append(" LEFT JOIN project_tenders t ON t.TENDER_ID = tb.TENDERS_ID  ");
        sql.append(" WHERE 1=1 ");
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            sql.append(" and p.PROJECT_ID = '" + map.get("projectId").toString() + "'");
        }
        if (map.get("tenderId") != null && !"".equals(map.get("tenderId").toString())) {
            sql.append(" and tb.TENDERS_ID = '" + map.get("tenderId").toString() + "'");
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            sql.append(" and pb.BUILD_ID ='" + map.get("buildingId").toString() + "'");
        }
        sql.append(" ORDER BY CONVERT( p.PROJECT_NAME USING gbk ) DESC,pb.AUTO_NUM ASC ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (webPage != null) {
            query.setFirstResult(webPage.getStartRow());
            query.setMaxResults(webPage.getPageSize());
        }
        return query.list();
    }

    @Override
    public List<Object[]> searchKeyProcessesCountList(Map map) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" pb.BUILD_NAME,TENDER_NAME,p.PROJECT_NAME,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE != 'AbnormalShutdown' AND pkp.BUILDING_ID=pb.BUILD_ID) AS hasbeengeton,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = '合格' AND pkp.BUILDING_ID=pb.BUILD_ID) AS qualified,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = '整改中' AND pkp.BUILDING_ID=pb.BUILD_ID) AS unQualified,");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE != 'AbnormalShutdown' AND pkp.FIRST_QUALIFIED_STATE = '合格' AND pkp.BUILDING_ID=pb.BUILD_ID) AS onePass, ");
        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.STATE = 'AbnormalShutdown' AND pkp.BUILDING_ID=pb.BUILD_ID) AS abnormalShutdown");
        sql.append(" FROM project_building pb ");
        sql.append(" LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID  ");
        sql.append(" LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pb.BUILD_ID  ");
        sql.append(" LEFT JOIN project_tenders t ON t.TENDER_ID = tb.TENDERS_ID  ");
        sql.append(" WHERE 1=1 ");
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            sql.append(" and p.PROJECT_ID = '" + map.get("projectId").toString() + "'");
        }
        if (map.get("tenderId") != null && !"".equals(map.get("tenderId").toString())) {
            sql.append(" and tb.TENDERS_ID = '" + map.get("tenderId").toString() + "'");
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            sql.append(" and pb.BUILD_ID ='" + map.get("buildingId").toString() + "'");
        }
        sql.append(" ORDER BY CONVERT( p.PROJECT_NAME USING gbk ) DESC,pb.AUTO_NUM ASC ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return query.list();
    }

    @Override
    public int searchKeyProcessesCount(Map map) {
        String sql = "select count(1) FROM project_key_processes pkp ";
//        sql += " LEFT JOIN project_building pb ON pb.PROJECT_ID = pkp.PROJECT_ID ";
//        sql += " LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID ";
        sql += " LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pkp.BUILDING_ID ";
        sql += " WHERE 1=1 ";
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            sql += " AND pkp.PROJECT_ID = '" + map.get("projectId").toString() + "'";
        }
        if (map.get("tenderId") != null && !"".equals(map.get("tenderId").toString())) {
            sql += " AND tb.TENDERS_ID = '" + map.get("tenderId").toString() + "'";
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            sql += " AND pkp.BUILDING_ID = '" + map.get("buildingId").toString() + "'";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return count.intValue();
        } else {
            return 0;
        }
    }

    @Override
    public int getCount(Map map, String staffId) {
        String projectId1s = null;
        String projectId2s = null;
        String hql1 = "select agencyPath from AgencyEntity where agencyId in (select distinct agencyId" +
                " from UserAgencymapEntity where status='1' and staffId = '" + staffId + "')";
        Query query1 = getCurrentSession().createQuery(hql1);
        List<String> paths = query1.list();
        String str = "";
        if (paths != null && paths.size() > 0) {
            for (String ids : paths) {
                str += ids.replace("/", "','");
            }
        }
        if (!StringUtil.isEmpty(str)) {
            str = str.substring(2) + "'";
            String sql2 = "select PROJECT_ID from project_staff_employ where DATA_TYPE = '1' and STATUS = '1' and DATA_ID in(" + str + ")";
            Query query2 = getCurrentSession().createSQLQuery(sql2);
            projectId1s = query2.getQueryString();
        }

        String hql3 = "select distinct PROJECT_ID from project_staff_employ where DATA_TYPE='2' and STATUS='1' and DATA_ID='" + staffId + "'";
        Query query3 = getCurrentSession().createSQLQuery(hql3);
        projectId2s = query3.getQueryString();

        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" SELECT count(1) ");
//        sql.append(" pb.BUILD_NAME,p.PROJECT_NAME,");
//        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.BUILDING_ID=pb.BUILD_ID) AS hasbeengeton,");
//        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.QUALIFIED_STATE = '合格' AND pkp.BUILDING_ID=pb.BUILD_ID) AS qualified,");
//        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.QUALIFIED_STATE = '不合格' AND pkp.BUILDING_ID=pb.BUILD_ID) AS unQualified,");
//        sql.append(" (SELECT count(1) FROM project_key_processes pkp WHERE pkp.FIRST_QUALIFIED_STATE = '合格' AND pkp.BUILDING_ID=pb.BUILD_ID) AS onePass ");
        sql.append(" FROM project_building pb ");
        sql.append(" LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID  ");
        sql.append(" LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pb.BUILD_ID  ");
        sql.append(" WHERE 1=1 AND pb.BUILD_STATE = '1'");
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            sql.append(" and p.PROJECT_ID = '" + map.get("projectId").toString() + "'");
        } else {
            sql.append(" and (p.PROJECT_ID in(" + projectId1s + ") or p.PROJECT_ID in(" + projectId2s + ")) ");
        }
        if (map.get("tenderId") != null && !"".equals(map.get("tenderId").toString())) {
            sql.append(" and tb.TENDERS_ID = '" + map.get("tenderId").toString() + "'");
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            sql.append(" and pb.BUILD_ID ='" + map.get("buildingId").toString() + "'");
        }
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return count.intValue();
        } else {
            return 0;
        }
    }

    @Override
    public int getCount(Map map) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" SELECT count(1) ");
        sql.append(" FROM project_building pb ");
        sql.append(" LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID  ");
        sql.append(" LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pb.BUILD_ID  ");
        sql.append(" WHERE 1=1 AND pb.BUILD_STATE = '1'");
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            sql.append(" and p.PROJECT_ID = '" + map.get("projectId").toString() + "'");
        }
        if (map.get("tenderId") != null && !"".equals(map.get("tenderId").toString())) {
            sql.append(" and tb.TENDERS_ID = '" + map.get("tenderId").toString() + "'");
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            sql.append(" and pb.BUILD_ID ='" + map.get("buildingId").toString() + "'");
        }
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return count.intValue();
        } else {
            return 0;
        }
    }

    @Override
    public ProjectKeyProcessesEntity searchKeyProcessByStaffId(String processId, String staffId) {
        String hql = " FROM ProjectKeyProcessesEntity WHERE createBy='" + staffId + "' and processId = '" + processId + "' ";
        Query query = getCurrentSession().createQuery(hql);
        return (ProjectKeyProcessesEntity) query.uniqueResult();
    }

    @Override
    public int getRectificationCount(String staffId) {
        String sql = "select count(1) from project_key_processes where  STATE='整改中' ";
        if (!StringUtil.isEmpty(staffId)) {
            sql += " and HANDLE_PEOPLE_ID = '" + staffId + "'";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return count.intValue();
        } else {
            return 0;
        }
    }

}
