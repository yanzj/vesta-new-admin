package com.maxrocky.vesta.presistent.projectSampleCheck.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyDetailsEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyEntity;
import com.maxrocky.vesta.domain.projectMaterial.model.ProjectMaterialEntity;
import com.maxrocky.vesta.domain.projectSampleCheck.model.ProjectSampleCheckChangedEntity;
import com.maxrocky.vesta.domain.projectSampleCheck.model.ProjectSampleCheckDetailsEntity;
import com.maxrocky.vesta.domain.projectSampleCheck.model.ProjectSampleCheckEntity;
import com.maxrocky.vesta.domain.projectSampleCheck.repository.ProjectSampleCheckRepository;
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
 * Created by Magic on 2017/1/3.
 */
@Repository
public class ProjectSampleCheckRepositoryImpl extends HibernateDaoImpl implements ProjectSampleCheckRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 保存样板点评主表信息
     */
    @Override
    public boolean saveOrUpdateSampleCheck(ProjectSampleCheckEntity projectSampleCheckEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(projectSampleCheckEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 保存样板点评指标信息
     */
    @Override
    public boolean saveOrUpdateSampleCheckDetails(ProjectSampleCheckDetailsEntity projectSampleCheckDetailsEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(projectSampleCheckDetailsEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 保存+修改 样板点评指标整改信息
     */
    @Override
    public void saveSampleCheckChanged(ProjectSampleCheckChangedEntity projectSampleCheckChangedEntity) {
        Session session = getCurrentSession();
        session.save(projectSampleCheckChangedEntity);
        session.flush();
        session.close();
    }

    /**
     * 按样板点评ID+appId查询样板点评主表信息
     */
    @Override
    public ProjectSampleCheckEntity querySampleCheckByID(String id, String appId) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_sample_check s where SAMPLE_CHECK_ID=:id or APP_ID=:aid").addEntity(ProjectSampleCheckEntity.class);
        sqlQuery.setParameter("id", id);
        sqlQuery.setParameter("aid", appId);
        List<ProjectSampleCheckEntity> sampleCheckEntity = sqlQuery.list();
        if (sampleCheckEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return sampleCheckEntity.get(0);
    }

    /**
     * 根据样板点评ID查询指标表数据
     */
    @Override
    public List<ProjectSampleCheckDetailsEntity> querySampleCheckDetailsById(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_sample_check_details s where SAMPLE_CHECK_ID=:id  ORDER BY CREATEON ASC ").addEntity(ProjectSampleCheckDetailsEntity.class);
        sqlQuery.setParameter("id", id);
        List<ProjectSampleCheckDetailsEntity> sampleCheckDetailsEntity = sqlQuery.list();
        if (sampleCheckDetailsEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return sampleCheckDetailsEntity;
    }

    @Override
    public ProjectSampleCheckDetailsEntity querySampleCheckDetailsEntity(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_sample_check_details s where ID=:id  ORDER BY CREATEON ASC ").addEntity(ProjectSampleCheckDetailsEntity.class);
        sqlQuery.setParameter("id", id);
        List<ProjectSampleCheckDetailsEntity> list = sqlQuery.list();
        if (list.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return list.get(0);
    }

    /**
     * 根据样板点评指标ID查询整改信息
     */
    @Override
    public List<ProjectSampleCheckChangedEntity> querySampleCheckChangedEntity(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_sample_check_changed s where CHECK_DETAILS_ID=:id ORDER BY CREATEON ASC ").addEntity(ProjectSampleCheckChangedEntity.class);
        sqlQuery.setParameter("id", id);
        List<ProjectSampleCheckChangedEntity> sampleCheckChangedEntity = sqlQuery.list();
        if (sampleCheckChangedEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return sampleCheckChangedEntity;
    }

    @Override
    public List<ProjectImagesEntity> getProjectImages(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_images s where BUSINESS_ID=:id and TYPE='4' ").addEntity(ProjectImagesEntity.class);
        sqlQuery.setParameter("id", id);
        List<ProjectImagesEntity> ProjectImagesEntity = sqlQuery.list();
        if (ProjectImagesEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return ProjectImagesEntity;
    }

    @Override
    public List<ProjectImagesEntity> getQualifiedImage(String id, String state) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_images s where BUSINESS_ID=:id and TYPE='4' and QUALIFIED_STATE=:sta  ").addEntity(ProjectImagesEntity.class);
        sqlQuery.setParameter("id", id);
        sqlQuery.setParameter("sta", state);
        List<ProjectImagesEntity> ProjectImagesEntity = sqlQuery.list();
        if (ProjectImagesEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return ProjectImagesEntity;
    }

    @Override
    public boolean saveProjectCopy(ProjectCopyEntity projectCopy) {
        Session session = getCurrentSession();
        session.save(projectCopy);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public void saveProjectCopyDetails(ProjectCopyDetailsEntity projectCopyDetailsEntity) {
        Session session = getCurrentSession();
        session.save(projectCopyDetailsEntity);
        session.flush();
        session.close();
    }

    @Override
    public boolean sampleCheckForUpdate(String id, String time, String projectId, String staffId, String type) {
        String sql = "select count(1) from project_sample_check psc ";
        sql += " LEFT JOIN project_copy pc ON psc.SAMPLE_CHECK_ID=pc.BUSINESS ";
        sql += " LEFT JOIN project_copy_details pcd ON pc.ID=pcd.COPY_ID ";
        sql += " where psc.STATE != 'AbnormalShutdown'";
        if (!StringUtil.isEmpty(time)) {
            if (!StringUtil.isEmpty(id)) {
                if (!StringUtil.isEmpty(projectId)) {
                    sql += " and psc.MODIFYDATE > '" + time + "' or (psc.MODIFYDATE = '" + time + "' and psc.ID > '" + id + "' and psc.PROJECT_ID = '" + projectId + "')";
                } else {
                    sql += " and psc.MODIFYDATE > '" + time + "' or (psc.MODIFYDATE = '" + time + "' and psc.ID > '" + id + "')";
                }
            } else {
                sql += " and psc.MODIFYDATE > '" + time + "'";
            }
        }
        //第三方监理
        if ("2".equals(type)) {
            sql += " and ( pcd.MEMBER_ID='" + staffId + "' or psc.SUPERVISOR_ID='" + staffId + "' or psc.DEALPEOPLE='" + staffId + "')";
        }
        //乙方
        if ("3".equals(type)) {
            sql += " and (psc.DEALPEOPLE='" + staffId + "' or (psc.ASSIGN_ID='"+staffId+"' and psc.STATE='合格') or pcd.MEMBER_ID='" + staffId + "') ";
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
    public List<ProjectSampleCheckEntity> getAllKeyProcessesQuestion(String id, String time, String projectId, String staffId, String type) {
        Session session = getCurrentSession();
        String sql = "select * from project_sample_check psc ";
        sql += " LEFT JOIN project_copy pc ON psc.SAMPLE_CHECK_ID=pc.BUSINESS ";
        sql += " LEFT JOIN project_copy_details pcd ON pc.ID=pcd.COPY_ID ";
        sql += " where psc.STATE != 'AbnormalShutdown'";
        if (!StringUtil.isEmpty(time)) {
            if (!StringUtil.isEmpty(id)) {
                if (!StringUtil.isEmpty(projectId)) {
                    sql += " and psc.MODIFYDATE > '" + time + "' or (psc.MODIFYDATE = '" + time + "' and psc.ID > '" + id + "' and psc.PROJECT_ID = '" + projectId + "')";
                } else {
                    sql += " and psc.MODIFYDATE > '" + time + "' or (psc.MODIFYDATE = '" + time + "' and psc.ID > '" + id + "')";
                }
            } else {
                sql += " and psc.MODIFYDATE > '" + time + "'";
            }
        }
        //第三方监理
        if ("2".equals(type)) {
            sql += " and ( pcd.MEMBER_ID='" + staffId + "' or psc.SUPERVISOR_ID='" + staffId + "' or psc.DEALPEOPLE='" + staffId + "')";
        }
        //乙方
        if ("3".equals(type)) {
            sql += " and (psc.DEALPEOPLE='" + staffId + "' or (psc.ASSIGN_ID='"+staffId+"' and psc.STATE='合格') or pcd.MEMBER_ID='" + staffId + "') ";
        }
        sql += " GROUP BY psc.SAMPLE_CHECK_ID ORDER BY psc.MODIFYDATE,psc.ID";
        SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(ProjectSampleCheckEntity.class);
        List<ProjectSampleCheckEntity> projectSampleCheckEntityList = sqlQuery.list();
        if (projectSampleCheckEntityList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return projectSampleCheckEntityList;
    }

    @Override
    public List<Object[]> searchSampleCheck(String projectId) {
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" pm.CATEGORY_ID,pm.CATEGORY_NAME,mateAll.mAll,mateOK.mOK,mateNO.mNO ");
        sql.append(" from project_category pm   ");
        sql.append(" LEFT JOIN (SELECT COUNT(1) as mAll,CLASSIFY_ONE from project_sample_check where 1=1 and PROJECT_ID=:projectId and STATE in('合格','不合格') GROUP BY CLASSIFY_ONE) as mateAll ON pm.CATEGORY_ID=mateAll.CLASSIFY_ONE  ");
        sql.append(" LEFT JOIN (SELECT COUNT(1) as mOK,CLASSIFY_ONE from project_sample_check where 1=1 and PROJECT_ID=:projectId and STATE='合格' GROUP BY CLASSIFY_ONE) as mateOK ON  pm.CATEGORY_ID=mateOK.CLASSIFY_ONE  ");
        sql.append(" LEFT JOIN (SELECT COUNT(1) as mNO,CLASSIFY_ONE from project_sample_check where 1=1 and PROJECT_ID=:projectId and STATE='不合格' GROUP BY CLASSIFY_ONE) as mateNO ON pm.CATEGORY_ID=mateNO.CLASSIFY_ONE  ");
        sql.append(" where CATEGORY_DOMAIN='3' and CATEGORY_LEVEL='1' ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("projectId", projectId);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<ProjectSampleCheckEntity> getSampleCheckAdmin(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer("");
        sql.append(" from ProjectSampleCheckEntity dpl ");
        sql.append(" where 1=1 ");
        //项目id
        if (!StringUtil.isEmpty(map.get("projectId").toString())) {
            sql.append(" and dpl.projectId=? ");
            params.add(map.get("projectId").toString());
        }
        //项目权限
        if(!"NO".equals(map.get("projectList").toString())){
            sql.append(" and dpl.projectId in (" + map.get("projectList").toString() + ") ");
        }
        //一级分类
        if (!StringUtil.isEmpty(map.get("classifyOne").toString())) {
            sql.append(" and dpl.classifyOne=? ");
            params.add(map.get("classifyOne").toString());
        }
        //二级分类
        if (!StringUtil.isEmpty(map.get("classifyTwo").toString())) {
            sql.append(" and dpl.classifyTwo=? ");
            params.add(map.get("classifyTwo").toString());
        }
        //状态
        if (!StringUtil.isEmpty(map.get("state").toString())) {
            sql.append(" and dpl.state=? ");
            params.add(map.get("state").toString());
        }
        //责任单位
        if (!StringUtil.isEmpty(map.get("supplier").toString())) {
            sql.append(" and dpl.supplier like? ");
            params.add(map.get("supplier").toString());
        }
        //甲方负责人名字
        if (!StringUtil.isEmpty(map.get("firstPartyName").toString())) {
            sql.append(" and dpl.firstPartyName like? ");
            params.add(map.get("firstPartyName").toString());
        }
        //第三方监理名字
        if (!StringUtil.isEmpty(map.get("supervisorName").toString())) {
            sql.append(" and dpl.supervisorName like? ");
            params.add(map.get("supervisorName").toString());
        }
        //材料负责人名字
        if (!StringUtil.isEmpty(map.get("assignName").toString())) {
            sql.append(" and dpl.assignName like? ");
            params.add(map.get("assignName").toString());
        }
        //严重等级
        if (!StringUtil.isEmpty(map.get("severityLevel").toString())) {
            sql.append(" and dpl.severityLevel=? ");
            params.add(map.get("severityLevel").toString());
        }
        //开始日期
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql.append(" and dpl.createOn >= '" + startDate +"' ");
        }
        //结束时间
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql.append(" and dpl.createOn <= '" + endDate +"' ");
        }
//        if (!StringUtil.isEmpty(map.get("creaBy").toString())) {
//            sql.append(" and ((dpl.createBy=? and dpl.state='草稿') or dpl.state <>'草稿') ");
//            params.add(map.get("creaBy").toString());
//        }
        sql.append(" order by dpl.modifyDate desc ");

        if (webPage != null) {
            return this.findByPage(sql.toString(), webPage, params);
        }
        List<ProjectSampleCheckEntity> list = (List<ProjectSampleCheckEntity>) getHibernateTemplate().find(sql.toString(), params.toArray());
        return list;
    }

    @Override
    public List<Object[]> getSampleCheckCount(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append("  p.PROJECT_ID,p.PROJECT_NAME,pp.CLASSIFY_THREE,pp.CLASSIFY_THREE_NAME,ps.ok,psn.noc ");
        sql.append(" from project_project p   ");
        sql.append(" LEFT JOIN project_sample_check pp on p.PROJECT_ID=pp.PROJECT_ID  ");
        sql.append(" LEFT JOIN (SELECT count(1) as ok,CLASSIFY_THREE from project_sample_check where STATE='合格' GROUP BY CLASSIFY_THREE) ps ON ps.CLASSIFY_THREE=pp.CLASSIFY_THREE  ");
        sql.append(" LEFT JOIN (SELECT count(1) as noc,CLASSIFY_THREE from project_sample_check where STATE='不合格' GROUP BY CLASSIFY_THREE) psn ON psn.CLASSIFY_THREE=pp.CLASSIFY_THREE ");
        sql.append(" where 1=1 and p.PROJECT_STATE='1' ");
        if (!StringUtil.isEmpty(map.get("projectId").toString())) {
            sql.append(" and p.PROJECT_ID='" + map.get("projectId").toString() + "' ");
        }
        //项目权限
        if(!"NO".equals(map.get("projectList").toString())){
            sql.append(" and p.PROJECT_ID in (" + map.get("projectList").toString() + ") ");
        }
        sql.append(" GROUP BY p.PROJECT_ID,pp.CLASSIFY_THREE ");
        sql.append(" ORDER BY pp.CLASSIFY_THREE_NAME DESC,p.PROJECT_NAME ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (webPage != null) {
            query.setFirstResult(webPage.getStartRow());
            query.setMaxResults(webPage.getPageSize());
        }
        return query.list();
    }

    @Override
    public int getSampleCheckWebPage(Map map) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" SELECT count(1) ");
        sql.append(" FROM project_project p ");
        sql.append(" WHERE 1=1 AND p.PROJECT_STATE='1' ");
        if (!StringUtil.isEmpty(map.get("projectId").toString())) {
            sql.append(" and p.PROJECT_ID='" + map.get("projectId").toString() + "' ");
        }
        //项目权限
        if(!"NO".equals(map.get("projectList").toString())){
            sql.append(" and p.PROJECT_ID in (" + map.get("projectList").toString() + ") ");
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
    public int getCountSampleCheck(String staffId) {
        String sql = "select count(1) from project_sample_check where  STATE='不合格' ";
        if (!StringUtil.isEmpty(staffId)) {
            sql += " and DEALPEOPLE = '" + staffId + "'";
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
