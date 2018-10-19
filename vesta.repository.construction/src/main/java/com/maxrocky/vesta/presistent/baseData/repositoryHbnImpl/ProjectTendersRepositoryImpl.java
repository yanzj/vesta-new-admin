package com.maxrocky.vesta.presistent.baseData.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.ProjectCategoryEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectTendersEntity;
import com.maxrocky.vesta.domain.baseData.model.TendersBuildingEntity;
import com.maxrocky.vesta.domain.baseData.model.TendersCategoryEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectTendersRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/10/24.
 */
@Repository
public class ProjectTendersRepositoryImpl extends HibernateDaoImpl implements ProjectTendersRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addProjectTenders(ProjectTendersEntity projectTendersEntity) {
        Session session = getCurrentSession();
        session.save(projectTendersEntity);
        session.flush();
    }

    @Override
    public void updateProjectTenders(ProjectTendersEntity projectTendersEntity) {
        Session session = getCurrentSession();
        session.update(projectTendersEntity);
        session.flush();
    }

    @Override
    public ProjectTendersEntity getTendersDetail(String tenderId) {
        String hql = "from ProjectTendersEntity where tenderId=:tenderId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("tenderId", tenderId);
        ProjectTendersEntity projectTendersEntity = (ProjectTendersEntity) query.uniqueResult();
        return projectTendersEntity;
    }

    @Override
    public List<ProjectTendersEntity> getTendersByProjectId(String projectId, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from ProjectTendersEntity where tenderState='1' and projectId='" + projectId + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<ProjectTendersEntity> projectTendersEntities = query.list();
        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        return projectTendersEntities;
    }

    @Override
    public List<ProjectTendersEntity> getTendersByProjectId(String projectId) {
        String hql = "from ProjectTendersEntity where tenderState='1' and projectId='" + projectId + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<ProjectTendersEntity> projectTendersEntities = query.list();
        return projectTendersEntities;
    }

    @Override
    public void addTendersBuild(TendersBuildingEntity tendersBuildingEntity) {
        Session session = getCurrentSession();
        session.save(tendersBuildingEntity);
        session.flush();
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 更新标段楼栋关系
     */
    @Override
    public void updateTendersBuild(TendersBuildingEntity tendersBuildingEntity) {
        Session session = getCurrentSession();
        session.update(tendersBuildingEntity);
        session.flush();
        session.close();
    }

    @Override
    public void addTendersCategory(TendersCategoryEntity tendersCategoryEntity) {
        Session session = getCurrentSession();
        session.save(tendersCategoryEntity);
        session.flush();
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 更新标段检查项关系
     */
    @Override
    public void updateTendersCategory(TendersCategoryEntity tendersCategoryEntity) {
        Session session = getCurrentSession();
        session.update(tendersCategoryEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<TendersBuildingEntity> getTendersBuildForTime(String projectId, long autoId, String timeStamp) {
        String hql = "from TendersBuildingEntity where id in(select a.id from TendersBuildingEntity a,ProjectTendersEntity b where a.tendersId=b.tenderId and b.projectId=:projectId) and((modifyTime='" + timeStamp + "' and id>:autoId) or modifyTime>'" + timeStamp + "') order by modifyTime,id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("autoId", autoId);
        query.setParameter("projectId", projectId);
        query.setMaxResults(1000);
        List<TendersBuildingEntity> tendersBuildingEntities = query.list();
        return tendersBuildingEntities;
    }

    @Override
    public List<TendersCategoryEntity> getTendersCategoryForTime(String projectId, long autoId, String timeStamp) {
        String hql = "from TendersCategoryEntity where ((modifyTime='" + timeStamp + "' and id>:autoId) or modifyTime>'" + timeStamp + "') and ckState='1' and id in(select a.id from TendersCategoryEntity a,ProjectTendersEntity b where a.tendersId = b.tenderId and b.projectId=:projectId) order by modifyTime,id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("autoId", autoId);
        query.setParameter("projectId", projectId);
        query.setMaxResults(1000);
        List<TendersCategoryEntity> tendersCategoryEntities = query.list();
        return tendersCategoryEntities;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description:
     */
    @Override
    public List<ProjectCategoryEntity> getCategoryByParentId(String parentId) {
        StringBuffer hql = new StringBuffer("from ProjectCategoryEntity where categoryState='1' and parentId=:parentId order by createOn");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("parentId", parentId);
        List<ProjectCategoryEntity> list = query.list();
        return list;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description:
     */
    @Override
    public List<ProjectCategoryEntity> getCategoryByModelId(String modelId) {
        StringBuffer hql = new StringBuffer("from ProjectCategoryEntity where categoryState='1' and domain=:modelId and parentId is null order by createOn");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("modelId", modelId);
        List<ProjectCategoryEntity> list = query.list();
        return list;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 根据标段Id获取该标段对应的所有楼栋，之后如果要查询具体标段对应的楼栋信息，可以改变hql语句
     */
    @Override
    public List<TendersBuildingEntity> getTendersBuildingByTenderId(String tenderId) {
        StringBuffer hql = new StringBuffer("from TendersBuildingEntity where tendersId=:tenderId and tenderStatus = '1'");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("tenderId", tenderId);
        List<TendersBuildingEntity> list = query.list();
        return list;
    }

    @Override
    public List<String> getTendersBuilds(String tenderId) {
        StringBuffer hql = new StringBuffer("select buildingId from TendersBuildingEntity where tendersId=:tenderId and tenderStatus = '1'");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("tenderId", tenderId);
        List<String> builds = query.list();
        return builds;
    }

    @Override
    public List<String> getTendBuildsByProjectId(String projectId,String tendId) {
        String hql = "select buildingId from TendersBuildingEntity where tenderStatus ='1' and tendersId in(select tenderId from ProjectTendersEntity where tenderState='1' and tenderId<>:tendId and projectId=:projectId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("tendId",tendId);
        query.setParameter("projectId",projectId);
        List<String> builds = query.list();
        return builds;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 根据标段Id获取该标段对应的所有检查项
     */
    @Override
    public List<TendersCategoryEntity> getTendersCategoryByTenderId(String tenderId) {
        StringBuffer hql = new StringBuffer(" from TendersCategoryEntity where tendersId=:tenderId and nexusStatus='1'");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("tenderId", tenderId);
        List<TendersCategoryEntity> list = query.list();
        return list;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 根据标段id和楼栋id，查看关系是否存在
     */
    @Override
    public TendersBuildingEntity getTenderBuildById(String tenderId, String buildId) {
        StringBuffer hql = new StringBuffer("from TendersBuildingEntity where tendersId=:tenderId and buildingId=:buildId");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("tenderId", tenderId);
        query.setParameter("buildId", buildId);
        TendersBuildingEntity tendersBuildingEntity = (TendersBuildingEntity) query.uniqueResult();
        return tendersBuildingEntity;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Param:
     * @Description: 根据标段id和检查项id，查看标段检查项关系是否存在
     */
    @Override
    public TendersCategoryEntity getTenderCategoryById(String tenderId, String categoryId) {
        StringBuffer hql = new StringBuffer("from TendersCategoryEntity where tendersId=:tenderId and categoryId=:categoryId and nexusStatus='1'");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("tenderId", tenderId);
        query.setParameter("categoryId", categoryId);
        TendersCategoryEntity tendersCategoryEntity = (TendersCategoryEntity) query.uniqueResult();
        return tendersCategoryEntity;
    }

    @Override
    public void deleteTendBuild(TendersBuildingEntity tendersBuildingEntity) {
        String hql = "update TendersBuildingEntity set tenderStatus='0', modifyTime=:modifyTime where tendersId=:tendId and buildingId=:buildId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime",new Date());
        query.setParameter("tendId",tendersBuildingEntity.getTendersId());
        query.setParameter("buildId",tendersBuildingEntity.getBuildingId());
        query.executeUpdate();
    }

    @Override
    public void dumpAddTendBuild(TendersBuildingEntity tendersBuildingEntity) {
        String sql1 = "INSERT INTO project_tenders_building(BUILDING_ID,TENDERS_ID,MODIFY_TIME,TENDER_STATUS) VALUES(?,?,?,1) ON DUPLICATE KEY UPDATE TENDER_STATUS='1',MODIFY_TIME=?";
        Query query1 = getCurrentSession().createSQLQuery(sql1);
        query1.setString(0, tendersBuildingEntity.getBuildingId());
        query1.setString(1, tendersBuildingEntity.getTendersId());
        query1.setString(2, DateUtils.format(new Date()));
        query1.setString(3, DateUtils.format(new Date()));
        query1.executeUpdate();
    }
}
