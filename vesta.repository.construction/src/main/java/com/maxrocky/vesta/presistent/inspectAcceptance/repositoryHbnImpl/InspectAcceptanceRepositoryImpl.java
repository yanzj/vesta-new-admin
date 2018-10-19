package com.maxrocky.vesta.presistent.inspectAcceptance.repositoryHbnImpl;

import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyDetailsEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyEntity;
import com.maxrocky.vesta.domain.inspectAcceptance.model.ProjectExamineEntity;
import com.maxrocky.vesta.domain.inspectAcceptance.repository.InspectAcceptanceRepository;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by jiazefeng on 2016/10/17.
 */
@Repository
public class InspectAcceptanceRepositoryImpl extends HibernateDaoImpl implements InspectAcceptanceRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<ProjectExamineEntity> searchInspectAcceptanceList(Map map, WebPage webPage, String staffId) {
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

        List<Object> params = new ArrayList<Object>();
        String hql = " from ProjectExamineEntity iae where 1=1 ";
        if (map.get("projectNum") != null && !"".equals(map.get("projectNum").toString())) {
            hql += " and iae.projectNum = ? ";
            params.add(map.get("projectNum").toString());
        } else {
            hql += " and (iae.projectNum in(" + projectId1s + ") or iae.projectNum in(" + projectId2s + ")) ";
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            hql += " and iae.buildingId = ? ";
            params.add(map.get("buildingId").toString());
        }
        if (map.get("classfiyThree") != null && !"".equals(map.get("classfiyThree").toString())) {
            hql += " and iae.categoryName IN (SELECT pc.categoryName FROM ProjectCategoryEntity pc WHERE pc.domain='2' AND pc.level=4 AND pc.parentId = ? ) ";
            params.add(map.get("classfiyThree").toString());
        }
        if (map.get("State") != null && !"".equals(map.get("State").toString())) {
            hql += " and iae.state = ? ";
            params.add(map.get("State").toString());
        }
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            hql += " and iae.createOn >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            hql += " and iae.createOn <= '" + endDate + "' ";
        }
        if (map.get("supplier") != null && !"".equals(map.get("supplier").toString())) {
            hql += " and iae.supplierName like '%" + map.get("supplier").toString() + "%' ";
        }
        if (map.get("supervisorName") != null && !"".equals(map.get("supervisorName").toString())) {
            hql += " and iae.supervisorName like '%" + map.get("supervisorName").toString() + "%'";
        }
        if (map.get("assignName") != null && !"".equals(map.get("assignName").toString())) {
            hql += " and iae.assignnName like '%" + map.get("assignName").toString() + "%'";
        }
        if (map.get("severityRating") != null && !StringUtil.isEmpty(map.get("severityRating").toString())) {
            hql += " and iae.severityRating = '" + map.get("severityRating").toString() + "'";
        }
//        if (map.get("partyPrincipalName") != null && !"".equals(map.get("partyPrincipalName").toString())) {
//            hql += " and iae.partyPrincipalName like '%" + map.get("partyPrincipalName").toString() + "%'";
//        }

        hql += " order by iae.modifyOn desc";

        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<ProjectExamineEntity> inspectAcceptanceEntityList = (List<ProjectExamineEntity>) getHibernateTemplate().find(hql, params.toArray());
        return inspectAcceptanceEntityList;
    }

    @Override
    public List<ProjectExamineEntity> searchInspectAcceptanceList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from ProjectExamineEntity iae where 1=1 ";
        if (map.get("projectNum") != null && !"".equals(map.get("projectNum").toString())) {
            hql += " and iae.projectNum = ? ";
            params.add(map.get("projectNum").toString());
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            hql += " and iae.buildingId = ? ";
            params.add(map.get("buildingId").toString());
        }
        if (map.get("classfiyThree") != null && !"".equals(map.get("classfiyThree").toString())) {
            hql += " and iae.categoryName IN (SELECT pc.categoryName FROM ProjectCategoryEntity pc WHERE pc.domain='2' AND pc.level=4 AND pc.parentId = ? ) ";
            params.add(map.get("classfiyThree").toString());
        }
        if (map.get("State") != null && !"".equals(map.get("State").toString())) {
            hql += " and iae.state = ? ";
            params.add(map.get("State").toString());
        }
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            hql += " and iae.createOn >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            hql += " and iae.createOn <= '" + endDate + "' ";
        }
        if (map.get("supplier") != null && !"".equals(map.get("supplier").toString())) {
            hql += " and iae.supplierName like '%" + map.get("supplier").toString() + "%' ";
        }
        if (map.get("supervisorName") != null && !"".equals(map.get("supervisorName").toString())) {
            hql += " and iae.supervisorName like '%" + map.get("supervisorName").toString() + "%'";
        }
        if (map.get("assignName") != null && !"".equals(map.get("assignName").toString())) {
            hql += " and iae.assignnName like '%" + map.get("assignName").toString() + "%'";
        }
        if (map.get("severityRating") != null && !StringUtil.isEmpty(map.get("severityRating").toString())) {
            hql += " and iae.severityRating = '" + map.get("severityRating").toString() + "'";
        }
        hql += " order by iae.modifyOn desc";
        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<ProjectExamineEntity> inspectAcceptanceEntityList = (List<ProjectExamineEntity>) getHibernateTemplate().find(hql, params.toArray());
        return inspectAcceptanceEntityList;
    }

    @Override
    public List<ProjectExamineEntity> searchInspectAcceptanceAllList() {
        List<Object> params = new ArrayList<Object>();
        String hql = " from ProjectExamineEntity iae where 1=1 ";
        List<ProjectExamineEntity> inspectAcceptanceEntityList = (List<ProjectExamineEntity>) getHibernateTemplate().find(hql, params.toArray());
        return inspectAcceptanceEntityList;
    }

    @Override
    public List<ProjectExamineEntity> searchInspectAcceptanceAllList(Map map) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from ProjectExamineEntity iae where 1=1 ";
        if (map.get("projectNum") != null && !"".equals(map.get("projectNum").toString())) {
            hql += " and iae.projectNum = ? ";
            params.add(map.get("projectNum").toString());
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            hql += " and iae.buildingId = ? ";
            params.add(map.get("buildingId").toString());
        }
        if (map.get("classfiyThree") != null && !"".equals(map.get("classfiyThree").toString())) {
            hql += " and iae.categoryName IN (SELECT pc.categoryName FROM ProjectCategoryEntity pc WHERE pc.domain='2' AND pc.level=4 AND pc.parentId = ? ) ";
            params.add(map.get("classfiyThree").toString());
        }
        if (map.get("State") != null && !"".equals(map.get("State").toString())) {
            hql += " and iae.state = ? ";
            params.add(map.get("State").toString());
        }
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            hql += " and iae.createOn >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            hql += " and iae.createOn <= '" + endDate + "' ";
        }
        if (map.get("supplier") != null && !"".equals(map.get("supplier").toString())) {
            hql += " and iae.supplierName like '%" + map.get("supplier").toString() + "%' ";
        }
        if (map.get("supervisorName") != null && !"".equals(map.get("supervisorName").toString())) {
            hql += " and iae.supervisorName like '%" + map.get("supervisorName").toString() + "%'";
        }
        if (map.get("assignName") != null && !"".equals(map.get("assignName").toString())) {
            hql += " and iae.assignnName like '%" + map.get("assignName").toString() + "%'";
        }
        if (map.get("severityRating") != null && !StringUtil.isEmpty(map.get("severityRating").toString())) {
            hql += " and iae.severityRating = '" + map.get("severityRating").toString() + "'";
        }

        hql += " order by iae.modifyOn desc";
        List<ProjectExamineEntity> inspectAcceptanceEntityList = (List<ProjectExamineEntity>) getHibernateTemplate().find(hql, params.toArray());
        return inspectAcceptanceEntityList;
    }

    @Override
    public boolean addProjectExamineInfo(ProjectExamineEntity inspectAcceptanceEntity) {
//        this.save(inspectAcceptanceEntity);
        Session session = getCurrentSession();
        session.save(inspectAcceptanceEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public int searchHasBeenGetOnByProjectNum(String projectNum) {
        String sql = "select count(1) from project_examine where  STATE = '整改中'";
        if (projectNum != null && !projectNum.equals("")) {
            sql += " and PROJECT_ID = '" + projectNum + "'";
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
    public int searchQualifiedByProjectNum(String projectNum) {
        String sql = "select count(1) from project_examine where  IS_QUALIFIED = '合格'";
        if (projectNum != null && !projectNum.equals("")) {
            sql += " and PROJECT_NUM = '" + projectNum + "'";
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
    public int searchUnqualifiedByProjectNum(String projectNum) {
        String sql = "select count(1) from project_examine where  IS_QUALIFIED = '不合格'";
        if (projectNum != null && !projectNum.equals("")) {
            sql += " and PROJECT_NUM = '" + projectNum + "'";
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
    public int searchOnePassByProjectNum(String projectNum) {
        String sql = "select count(1) from project_examine where  IS_FIRST_QUALIFIED is not null";
        if (projectNum != null && !projectNum.equals("")) {
            sql += " and PROJECT_NUM = '" + projectNum + "'";
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
    public List<Object[]> searchAcceptanceListByProjectNum(String projectNum) {
        Session session = getCurrentSession();
        String sql = "SELECT pb.BUILD_NAME," +
                "(SELECT count(1) FROM project_examine pe WHERE pe.STATE !='AbnormalShutdown'AND pe.BUILDING_ID=pb.BUILD_ID) AS hasbeengeton," +
                "(SELECT count(1) FROM project_examine pe WHERE pe.STATE = '合格' AND pe.BUILDING_ID=pb.BUILD_ID) AS qualified," +
                "(SELECT count(1) FROM project_examine pe WHERE pe.STATE = '整改中' AND pe.BUILDING_ID=pb.BUILD_ID) AS unQualified," +
                "(SELECT count(1) FROM project_examine pe WHERE pe.IS_FIRST_QUALIFIED ='合格' AND pe.BUILDING_ID=pb.BUILD_ID) AS onePass," +
                "pb.PROJECT_ID,pb.BUILD_ID FROM project_building pb WHERE pb.BUILD_STATE='1' AND pb.PROJECT_ID = :projectId ORDER BY CONVERT(pb.PROJECT_ID USING gbk ) ASC, pb.AUTO_NUM ASC";
//        String sql = "select pe.BUILDING_NAME," +
//                "(select count(1) from project_examine pe  where  pe.PROJECT_NUM = :projectNum) AS hasbeengeton," +
//                "(select count(1) from project_examine pe  where  pe.IS_QUALIFIED = '合格' AND pe.PROJECT_NUM = :projectNum) AS qualified," +
//                "(select count(1) from project_examine pe  where  pe.IS_QUALIFIED = '不合格' AND pe.PROJECT_NUM = :projectNum) AS unQualified," +
//                "(select count(1) from project_examine pe  where  IS_FIRST_QUALIFIED is not null AND PROJECT_NUM = :projectNum) AS onePass," +
//                " pe.PROJECT_NUM,pe.BUILDING_ID FROM project_examine pe WHERE  pe.PROJECT_NUM = :projectNum GROUP BY pe.BUILDING_ID";
//                Query query = getCurrentSession().createQuery(sql.toString());
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter("projectId", projectNum);
        List<Object[]> acceptanceList = sqlQuery.list();
        return acceptanceList;
    }

    @Override
    public List<Object[]> searchAcceptanceBatchList(String buildingId, String projectCategoryName) {
        String sql = "select pe.BATCH_ID, pe.BATCH_NAME,pe.STATE,pe.CREATE_ON FROM project_examine pe WHERE pe.BUILDING_ID=? ";
        if (projectCategoryName != null && !projectCategoryName.equals("")) {
            sql += " and pe.CATEGORY_NAME LIKE '" + projectCategoryName + "'";
        }
        sql += " ORDER BY pe.MODIFY_ON DESC";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter(0, buildingId);
        List<Object[]> acceptanceBatchList = query.list();
        return acceptanceBatchList;
    }

    @Override
    public ProjectExamineEntity searchAcceptanceBatchInfo(String batchId) {
        String sql = " from ProjectExamineEntity where batchId ='" + batchId + "'";
        Query query = getCurrentSession().createQuery(sql);
        return (ProjectExamineEntity) query.uniqueResult();
    }

    @Override
    public boolean modifyAcceptanceBatchInfo(ProjectExamineEntity projectExamineEntity) {
//        this.update(projectExamineEntity);
        Session session = getCurrentSession();
        session.update(projectExamineEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public boolean searchToUpdateForAcceptance(String id, String beginDateNew, String projectNum, String creatBy, String type,String day7Ago) {
        String sql = "select count(1) from project_examine e ";
        sql += " LEFT JOIN project_copy pc ON e.BATCH_ID=pc.BUSINESS ";
        sql += " LEFT JOIN project_copy_details pcd ON pc.ID=pcd.COPY_ID ";
        sql += " where 1=1 and e.STATE = '整改中'";
        if (beginDateNew != null && !beginDateNew.equals("")) {
            if (id != null && !id.equals("")) {
                if (projectNum != null && !projectNum.equals("")) {
                    sql += " and e.MODIFY_ON > '" + beginDateNew + "' or (e.MODIFY_ON = '" + beginDateNew + "' and e.ID > '" + id + "' and e.PROJECT_NUM = '" + projectNum + "')";
                } else {
                    sql += " and e.MODIFY_ON > '" + beginDateNew + "' or (e.MODIFY_ON = '" + beginDateNew + "' and e.ID > '" + id + "')";
                }
            } else {
                sql += " and e.MODIFY_ON > '" + beginDateNew + "'";
            }
        }
        //第三方监理
        if ("2".equals(type)) {
            sql += " and ( e.CREATE_BY='" + creatBy + "' or pcd.MEMBER_ID='" + creatBy + "' or e.SUPERVISOR_ID='" + creatBy + "' or e.HANDLE_PEOPLE_ID='" + creatBy + "')";
        }
        //乙方
        if ("3".equals(type)) {
            sql += " and (e.HANDLE_PEOPLE_ID='" + creatBy + "' or (e.ASSIGN_ID='" + creatBy + "' and e.STATE='合格') or pcd.MEMBER_ID='" + creatBy + "') ";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        BigInteger count = (BigInteger) query.uniqueResult();

        String sql2 = "select count(1) from project_examine e ";
        sql2 += " LEFT JOIN project_copy pc ON e.BATCH_ID=pc.BUSINESS ";
        sql2 += " LEFT JOIN project_copy_details pcd ON pc.ID=pcd.COPY_ID ";
        sql2 += " where 1=1 and e.STATE = '合格' and e.MODIFY_ON >= '" + day7Ago + "'";
        if (beginDateNew != null && !beginDateNew.equals("")) {
            if (id != null && !id.equals("")) {
                if (projectNum != null && !projectNum.equals("")) {
                    sql2 += " and e.MODIFY_ON > '" + beginDateNew + "' or (e.MODIFY_ON = '" + beginDateNew + "' and e.ID > '" + id + "' and e.PROJECT_NUM = '" + projectNum + "')";
                } else {
                    sql2 += " and e.MODIFY_ON > '" + beginDateNew + "' or (e.MODIFY_ON = '" + beginDateNew + "' and e.ID > '" + id + "')";
                }
            } else {
                sql2 += " and e.MODIFY_ON > '" + beginDateNew + "'";
            }
        }
        //第三方监理
        if ("2".equals(type)) {
            sql2 += " and ( e.CREATE_BY='" + creatBy + "' or pcd.MEMBER_ID='" + creatBy + "' or e.SUPERVISOR_ID='" + creatBy + "' or e.HANDLE_PEOPLE_ID='" + creatBy + "')";
        }
        //乙方
        if ("3".equals(type)) {
            sql2 += " and (e.HANDLE_PEOPLE_ID='" + creatBy + "' or (e.ASSIGN_ID='" + creatBy + "' and e.STATE='合格') or pcd.MEMBER_ID='" + creatBy + "' ) ";
        }
        Query query2 = getCurrentSession().createSQLQuery(sql2);
        BigInteger count2 = (BigInteger) query2.uniqueResult();

        int countAll = count.intValue() + count2.intValue();
        if (countAll > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ProjectExamineEntity> getAllProjectAcceptanceQuestion(String id, String timeStamp, String projectNum, String day7Ago) {
        String hql = " FROM ProjectExamineEntity pe WHERE 1=1 and state !='AbnormalShutdown' and modifyOn >= '" + day7Ago + "'";

        if (timeStamp != null && !timeStamp.equals("")) {
            if (id != null && !id.equals("")) {
                if (projectNum != null && !projectNum.equals("")) {
                    hql += " and modifyOn > '" + timeStamp + "' or (modifyOn = '" + timeStamp + "' and id > '" + id + "' and projectNum = '" + projectNum + "')";
                } else {
                    hql += " and modifyOn > '" + timeStamp + "' or (modifyOn = '" + timeStamp + "' and id > '" + id + "')";
                }
            } else {
                hql += " and modifyOn > '" + timeStamp + "'";
            }
        }
        hql += " ORDER BY pe.modifyOn DESC,pe.id DESC";
        Query query = getCurrentSession().createQuery(hql);
        query.setMaxResults(500);
        List<ProjectExamineEntity> acceptanceBatchList = query.list();
        return acceptanceBatchList;
    }

    @Override
    public List<Object[]> getAllProjectAcceptanceQuestion(String id, String timeStamp, String projectNum, String day7Ago, String staffId, String type) {
        List<Object[]> listAll = new ArrayList<Object[]>();
        String sql = "select e.ID,e.BATCH_ID from project_examine e ";
        sql += " LEFT JOIN project_copy pc ON e.BATCH_ID=pc.BUSINESS ";
        sql += " LEFT JOIN project_copy_details pcd ON pc.ID=pcd.COPY_ID ";
        sql += " where 1=1 and e.STATE = '合格' and e.MODIFY_ON >= '" + day7Ago + "'";
        if (timeStamp != null && !timeStamp.equals("")) {
            if (id != null && !id.equals("")) {
                if (projectNum != null && !projectNum.equals("")) {
                    sql += " and e.MODIFY_ON > '" + timeStamp + "' or (e.MODIFY_ON = '" + timeStamp + "' and e.ID > '" + id + "' and e.PROJECT_NUM = '" + projectNum + "')";
                } else {
                    sql += " and e.MODIFY_ON > '" + timeStamp + "' or (e.MODIFY_ON = '" + timeStamp + "' and e.ID > '" + id + "')";
                }
            } else {
                sql += " and e.MODIFY_ON > '" + timeStamp + "'";
            }
        }
        //第三方监理
        if ("2".equals(type)) {
            sql += " and ( e.CREATE_BY='" + staffId + "' or pcd.MEMBER_ID='" + staffId + "' or e.SUPERVISOR_ID='" + staffId + "' or e.HANDLE_PEOPLE_ID='" + staffId + "')";
        }
        //乙方
        if ("3".equals(type)) {
            sql += " and (e.HANDLE_PEOPLE_ID='" + staffId + "' or (e.ASSIGN_ID='" + staffId + "' and e.STATE='合格') or pcd.MEMBER_ID='" + staffId + "') ";
        }
        sql += " GROUP BY e.BATCH_ID ORDER BY e.MODIFY_ON DESC,e.ID DESC";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setMaxResults(500);
        List<Object[]> listBy7Ago = query.list();
        if (listBy7Ago != null && listBy7Ago.size() > 0) {
            listAll.addAll(listBy7Ago);
        }

        String sql2 = "select e.ID,e.BATCH_ID from project_examine e ";
        sql2 += " LEFT JOIN project_copy pc ON e.BATCH_ID=pc.BUSINESS ";
        sql2 += " LEFT JOIN project_copy_details pcd ON pc.ID=pcd.COPY_ID ";
        sql2 += " where 1=1 and e.STATE = '整改中' ";
        if (timeStamp != null && !timeStamp.equals("")) {
            if (id != null && !id.equals("")) {
                if (projectNum != null && !projectNum.equals("")) {
                    sql2 += " and e.MODIFY_ON > '" + timeStamp + "' or (e.MODIFY_ON = '" + timeStamp + "' and e.ID > '" + id + "' and e.PROJECT_NUM = '" + projectNum + "')";
                } else {
                    sql2 += " and e.MODIFY_ON > '" + timeStamp + "' or (e.MODIFY_ON = '" + timeStamp + "' and e.ID > '" + id + "')";
                }
            } else {
                sql2 += " and e.MODIFY_ON > '" + timeStamp + "'";
            }
        }
        //第三方监理
        if ("2".equals(type)) {
            sql2 += " and ( e.CREATE_BY='" + staffId + "' or pcd.MEMBER_ID='" + staffId + "' or e.SUPERVISOR_ID='" + staffId + "' or e.HANDLE_PEOPLE_ID='" + staffId + "')";
        }
        //乙方
        if ("3".equals(type)) {
            sql2 += " and (e.HANDLE_PEOPLE_ID='" + staffId + "' or (e.ASSIGN_ID='" + staffId +"' and e.STATE='合格') or pcd.MEMBER_ID='" + staffId + "') ";
        }
        sql2 += " GROUP BY e.BATCH_ID ORDER BY e.MODIFY_ON DESC,e.ID DESC";
        Query query2 = getCurrentSession().createSQLQuery(sql2);
        query2.setMaxResults(500);
        List<Object[]> list2 = query2.list();
        if (list2 != null && list2.size() > 0) {
            listAll.addAll(list2);
        }
        return listAll;
    }

    @Override
    public List<Object[]> getAllProjectAcceptanceQuestion(String id, String timeStamp, String projectNum, String staffId, String type) {
        String sql = "select e.ID,e.BATCH_ID,e.STATE from project_examine e ";
        sql += " LEFT JOIN project_copy pc ON e.BATCH_ID=pc.BUSINESS ";
        sql += " LEFT JOIN project_copy_details pcd ON pc.ID=pcd.COPY_ID ";
        sql += " where 1=1 and e.STATE != 'AbnormalShutdown' ";
        if (timeStamp != null && !timeStamp.equals("")) {
            if (id != null && !id.equals("")) {
                if (projectNum != null && !projectNum.equals("")) {
                    sql += " and e.MODIFY_ON > '" + timeStamp + "' or (e.MODIFY_ON = '" + timeStamp + "' and e.ID > '" + id + "' and e.PROJECT_NUM = '" + projectNum + "')";
                } else {
                    sql += " and e.MODIFY_ON > '" + timeStamp + "' or (e.MODIFY_ON = '" + timeStamp + "' and e.ID > '" + id + "')";
                }
            } else {
                sql += " and e.MODIFY_ON > '" + timeStamp + "'";
            }
        }
        //第三方监理
        if ("2".equals(type)) {
            sql += " and ( e.CREATE_BY='" + staffId + "' or pcd.MEMBER_ID='" + staffId + "' or e.SUPERVISOR_ID='" + staffId + "' or e.HANDLE_PEOPLE_ID='" + staffId + "')";
        }
        //乙方
        if ("3".equals(type)) {
            sql += " and (e.HANDLE_PEOPLE_ID='" + staffId + "' or (e.ASSIGN_ID='" + staffId + "' and e.STATE='合格')) ";
        }
        sql += " GROUP BY e.BATCH_ID ORDER BY e.MODIFY_ON DESC,e.ID DESC";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setMaxResults(500);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<ProjectCopyEntity> getProjectCopyList(String batchId) {
        String hql = " from ProjectCopyEntity where business = '" + batchId + "' and damain = '2'";
        Query query = getCurrentSession().createQuery(hql);
        List<ProjectCopyEntity> projectCopyEntityList = query.list();

        return projectCopyEntityList;
    }

    @Override
    public List<ProjectCopyDetailsEntity> getProjectCopyDetailsList(String copyId) {
        String hql = " from ProjectCopyDetailsEntity where copyId = '" + copyId + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<ProjectCopyDetailsEntity> projectCopyDetailsEntityList = query.list();

        return projectCopyDetailsEntityList;
    }

    @Override
    public boolean saveProjectCopy(ProjectCopyEntity projectCopy) {
//        this.save(projectCopy);
        Session session = getCurrentSession();
        session.save(projectCopy);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public boolean deleteProjectCopy(String id) {
        String sql = "delete from project_copy where ID=:id ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
        return true;
    }

    @Override
    public boolean saveProjectCopyDetails(ProjectCopyDetailsEntity projectCopyDetails) {
//        this.save(projectCopyDetails);
        Session session = getCurrentSession();
        session.save(projectCopyDetails);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public boolean deleteProjectCopyDetails(String id) {
        String sql = "delete from project_copy_details where COPY_ID=:id";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
        return true;
    }

    @Override
    public List<ProjectExamineEntity> searchInspectAcceptanceCountList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        Session session = getCurrentSession();
        String hql = " FROM ProjectExamineEntity pe WHERE 1=1 ";

        if (map.get("projectNum") != null && !"".equals(map.get("projectNum").toString())) {
            hql += " AND pe.projectNum = '" + map.get("projectNum").toString() + "'";
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            hql += " AND pe.buildingId = '" + map.get("buildingId").toString() + "'";
        }
        hql += " GROUP BY pe.buildingId ";
//        if (webPage != null) {
//            return this.findByPage(hql, webPage, params);
//        }
        List<ProjectExamineEntity> inspectAcceptanceEntityList = (List<ProjectExamineEntity>) getHibernateTemplate().find(hql, params.toArray());
        return inspectAcceptanceEntityList;
    }

    @Override
    public List<Object[]> searchAcceptanceCountList(Map map, WebPage webPage, String staffId) {
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

        String hql3 = "select distinct PROJECT_ID from project_staff_employ where  DATA_TYPE='2' and STATUS='1' and DATA_ID='" + staffId + "'";
        Query query3 = getCurrentSession().createSQLQuery(hql3);
        projectId2s = query3.getQueryString();

        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" pb.BUILD_NAME,TENDER_NAME,p.PROJECT_NAME,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE != 'AbnormalShutdown' AND pe.BUILDING_ID=pb.BUILD_ID) AS hasbeengeton,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE = '合格' AND pe.BUILDING_ID=pb.BUILD_ID) AS qualified,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE = '整改中' AND pe.BUILDING_ID=pb.BUILD_ID) AS unQualified,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.IS_FIRST_QUALIFIED = '合格' AND pe.BUILDING_ID=pb.BUILD_ID) AS onePass, ");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE = 'AbnormalShutdown' AND pe.BUILDING_ID=pb.BUILD_ID) AS abnormalShutdown ");
        sql.append(" FROM project_building pb ");
        sql.append(" LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID  ");
        sql.append(" LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pb.BUILD_ID  ");
        sql.append(" LEFT JOIN project_tenders t ON t.TENDER_ID = tb.TENDERS_ID  ");
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
        sql.append(" ORDER BY CONVERT( p.PROJECT_NAME USING gbk ) DESC,pb.AUTO_NUM ASC ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (webPage != null) {
            query.setFirstResult(webPage.getStartRow());
            query.setMaxResults(webPage.getPageSize());
        }
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> searchAcceptanceCountList(Map map, WebPage webPage) {
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" pb.BUILD_NAME,TENDER_NAME,p.PROJECT_NAME,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE != 'AbnormalShutdown' AND pe.BUILDING_ID=pb.BUILD_ID) AS hasbeengeton,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE = '合格' AND pe.BUILDING_ID=pb.BUILD_ID) AS qualified,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE = '整改中' AND pe.BUILDING_ID=pb.BUILD_ID) AS unQualified,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE != 'AbnormalShutdown' AND pe.IS_FIRST_QUALIFIED = '合格' AND pe.BUILDING_ID=pb.BUILD_ID) AS onePass,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE = 'AbnormalShutdown' AND pe.BUILDING_ID=pb.BUILD_ID) AS abnormalShutdown ");
        sql.append(" FROM project_building pb ");
        sql.append(" LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID  ");
        sql.append(" LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pb.BUILD_ID  ");
        sql.append(" LEFT JOIN project_tenders t ON t.TENDER_ID = tb.TENDERS_ID  ");
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
        sql.append(" ORDER BY CONVERT( p.PROJECT_NAME USING gbk ) DESC,pb.AUTO_NUM ASC ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (webPage != null) {
            query.setFirstResult(webPage.getStartRow());
            query.setMaxResults(webPage.getPageSize());
        }
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> searchAcceptanceCountList(Map map) {
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" pb.BUILD_NAME,TENDER_NAME,p.PROJECT_NAME,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE != 'AbnormalShutdown' AND pe.BUILDING_ID=pb.BUILD_ID) AS hasbeengeton,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE = '合格' AND pe.BUILDING_ID=pb.BUILD_ID) AS qualified,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE = '整改中' AND pe.BUILDING_ID=pb.BUILD_ID) AS unQualified,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE != 'AbnormalShutdown' AND pe.IS_FIRST_QUALIFIED = '合格' AND pe.BUILDING_ID=pb.BUILD_ID) AS onePass,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE = 'AbnormalShutdown' AND pe.BUILDING_ID=pb.BUILD_ID) AS abnormalShutdown ");
        sql.append(" FROM project_building pb ");
        sql.append(" LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID  ");
        sql.append(" LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pb.BUILD_ID  ");
        sql.append(" LEFT JOIN project_tenders t ON t.TENDER_ID = tb.TENDERS_ID  ");
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
        sql.append(" ORDER BY CONVERT( p.PROJECT_NAME USING gbk ) DESC,pb.AUTO_NUM ASC ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> searchAcceptanceCountList(Map map, String staffId) {
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

        String hql3 = "select distinct PROJECT_ID from project_staff_employ where  DATA_TYPE='2' and STATUS='1' and DATA_ID='" + staffId + "'";
        Query query3 = getCurrentSession().createSQLQuery(hql3);
        projectId2s = query3.getQueryString();

        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" pb.BUILD_NAME,TENDER_NAME,p.PROJECT_NAME,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE != 'AbnormalShutdown' AND pe.BUILDING_ID=pb.BUILD_ID) AS hasbeengeton,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE = '合格' AND pe.BUILDING_ID=pb.BUILD_ID) AS qualified,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE = '整改中' AND pe.BUILDING_ID=pb.BUILD_ID) AS unQualified,");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.IS_FIRST_QUALIFIED = '合格' AND pe.BUILDING_ID=pb.BUILD_ID) AS onePass, ");
        sql.append(" (SELECT count(1) FROM project_examine pe WHERE pe.STATE = 'AbnormalShutdown' AND pe.BUILDING_ID=pb.BUILD_ID) AS abnormalShutdown ");
        sql.append(" FROM project_building pb ");
        sql.append(" LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID  ");
        sql.append(" LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pb.BUILD_ID  ");
        sql.append(" LEFT JOIN project_tenders t ON t.TENDER_ID = tb.TENDERS_ID  ");
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
        sql.append(" ORDER BY CONVERT( p.PROJECT_NAME USING gbk ) DESC,pb.AUTO_NUM ASC ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return (List<Object[]>) query.list();
    }

    @Override
    public int searchInspectAcceptanceCount(Map map) {
        String sql = "select count(1) FROM project_examine pe ";
//        sql += " LEFT JOIN project_building pb ON pb.PROJECT_ID = pkp.PROJECT_ID ";
//        sql += " LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID ";
        sql += " LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pe.BUILDING_ID ";
        sql += " WHERE 1=1 ";
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            sql += " AND pe.PROJECT_NUM = '" + map.get("projectId").toString() + "'";
        }
        if (map.get("tenderId") != null && !"".equals(map.get("tenderId").toString())) {
            sql += " AND tb.TENDERS_ID = '" + map.get("tenderId").toString() + "'";
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            sql += " AND pe.BUILDING_ID = '" + map.get("buildingId").toString() + "'";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return count.intValue();
        } else {
            return 0;
        }
//        String hql = "select count(1) FROM project_examine pe WHERE 1=1 ";
//
//        if (map.get("projectNum") != null && !"".equals(map.get("projectNum").toString())) {
//            hql += " AND pe.PROJECT_NUM = '" + map.get("projectNum").toString() + "'";
//        }
//        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
//            hql += " AND pe.BUILDING_ID = '" + map.get("buildingId").toString() + "'";
//        }
////        hql+=" GROUP BY pe.BUILDING_ID ";
//        Query query = getCurrentSession().createSQLQuery(hql);
//        BigInteger count = (BigInteger) query.uniqueResult();
//        if (count.intValue() > 0) {
//            return count.intValue();
//        } else {
//            return 0;
//        }
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
            String sql2 = "select PROJECT_ID from project_staff_employ where  DATA_TYPE = '1' and STATUS = '1' and DATA_ID in(" + str + ")";
            Query query2 = getCurrentSession().createSQLQuery(sql2);
            projectId1s = query2.getQueryString();
        }

        String hql3 = "select distinct PROJECT_ID from project_staff_employ where  DATA_TYPE='2' and STATUS='1' and DATA_ID='" + staffId + "'";
        Query query3 = getCurrentSession().createSQLQuery(hql3);
        projectId2s = query3.getQueryString();

        StringBuffer sql = new StringBuffer(" SELECT count(1) ");
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
        StringBuffer sql = new StringBuffer(" SELECT count(1) ");
        sql.append(" FROM project_building pb ");
        sql.append(" LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID  ");
        sql.append(" LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pb.BUILD_ID  ");
        sql.append(" LEFT JOIN project_tenders t ON t.TENDER_ID = tb.TENDERS_ID  ");
        sql.append(" WHERE 1=1 AND pb.BUILD_STATE = '1' ");
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
    public ProjectExamineEntity searchAcceptanceBatchInfoByStaffId(String batchId, String staffId) {
        String sql = " from ProjectExamineEntity where batchId ='" + batchId + "' and supervisorId = '" + staffId + "'";
        Query query = getCurrentSession().createQuery(sql);
        return (ProjectExamineEntity) query.uniqueResult();
    }

    @Override
    public ProjectExamineEntity getProjectExamineByAppId(String id) {
        String sql = " from ProjectExamineEntity where appId ='" + id + "'";
        Query query = getCurrentSession().createQuery(sql);
        return (ProjectExamineEntity) query.uniqueResult();
    }

    @Override
    public int getRectificationCount(String staffId) {
        String sql = "select count(1) from project_examine where  STATE='整改中' ";
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
