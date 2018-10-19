package com.maxrocky.vesta.presistent.baseData.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.BuildingSupplierEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectBuildingEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectTendersEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectBuildingRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/10/18.
 */

@Repository
public class ProjectBuildingRepositoryImpl extends HibernateDaoImpl implements ProjectBuildingRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addProjectBuild(ProjectBuildingEntity projectBuildingEntity) {
        Session session = getCurrentSession();
        session.save(projectBuildingEntity);
        session.flush();
    }

    /**
     * @param projectId
     * @param webPage
     * @return projectBuildingEntities
     */
    @Override
    public List<ProjectBuildingEntity> getBuildingsByProjectId(String projectId, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from ProjectBuildingEntity where state='1' and projectId='" + projectId + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<ProjectBuildingEntity> projectBuildingEntities = query.list();
        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        return projectBuildingEntities;
    }

    @Override
    public List<ProjectBuildingEntity> getBuildingsByProjectId(String projectId) {
        String hql = "from ProjectBuildingEntity where state='1' and projectId='" + projectId + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<ProjectBuildingEntity> projectBuildingEntities = query.list();
        return projectBuildingEntities;
    }

    @Override
    public int getBuildingsCountByProjectId(String projectId){
        String sql = "select count(*) from project_building where BUILD_STATE = '1' and PROJECT_ID = '" + projectId + "'";
        Query query = getCurrentSession().createSQLQuery(sql);
        BigInteger count = (BigInteger) query.uniqueResult();
        if(count != null){
            return count.intValue();
        }else{
            return 0;
        }
    }

    @Override
    public void altProjectBuild(ProjectBuildingEntity projectBuildingEntity) {
        Session session = getCurrentSession();
        session.update(projectBuildingEntity);
        session.flush();
    }

    @Override
    public void delProjectBuild(String buildId) {
        String hql = "update ProjectBuildingEntity set state='0' where id=:buildId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("buildId", buildId);
        query.executeUpdate();
    }

    /**
     * @param buildId
     * @return projectBuildingEntity
     * @author chenning
     */
    @Override
    public ProjectBuildingEntity getBuildDetail(String buildId) {
        String hql = "from ProjectBuildingEntity where id=:buildId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("buildId", buildId);
        ProjectBuildingEntity projectBuildingEntity = (ProjectBuildingEntity) query.uniqueResult();
        return projectBuildingEntity;
    }

    @Override
    public boolean checkThisName(String buildId, String buildName, String projectId) {
        String hql = "from ProjectBuildingEntity where name=:buildName and state='1' and projectId=:projectId";
        if (!StringUtil.isEmpty(buildId)) {
            hql += " and id<>:buildId";
        }
        Query query = getCurrentSession().createQuery(hql);
        if (!StringUtil.isEmpty(buildId)) {
            query.setParameter("buildId", buildId);
        }
        query.setParameter("buildName", buildName);
        query.setParameter("projectId", projectId);
        List<ProjectBuildingEntity> projectBuildingEntities = query.list();
        if (projectBuildingEntities != null && projectBuildingEntities.size() > 0) {
            return false;
        } else
            return true;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 新增楼栋
     */
    @Override
    public void insertBuilding(ProjectBuildingEntity projectBuildingEntity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(projectBuildingEntity);
        session.flush();
        session.close();
    }

    @Override
    public boolean checkUpdateBuild(String autoId, String projectId, String modifyTime) {
        String hql = "from ProjectBuildingEntity where projectId=:projectId and ((modifyOn='" + modifyTime + "' and autoNum>:autoId) or modifyOn>'" + modifyTime + "') order by modifyOn,autoNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId", projectId);
        List<ProjectBuildingEntity> projectBuildingEntities = query.list();
        if (projectBuildingEntities != null && projectBuildingEntities.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<ProjectBuildingEntity> getBuildingForTime(String projectId, String timeStamp, long autoId) {
        String hql = "from ProjectBuildingEntity where projectId=:projectId and ((modifyOn='" + timeStamp + "' and autoNum>:autoId) or modifyOn>'" + timeStamp + "') order by modifyOn,autoNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId", projectId);
        query.setParameter("autoId", autoId);
        query.setMaxResults(1000);
        List<ProjectBuildingEntity> projectBuildingEntities = query.list();
        return projectBuildingEntities;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 根据楼栋名称，以及项目id，查询该楼栋是否存在
     */
    @Override
    public ProjectBuildingEntity getProjectBuildByName(String buildName, String projectId) {
        StringBuffer hql = new StringBuffer(" from ProjectBuildingEntity where state='1' and projectId=:projectId and name=:buildName");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("projectId", projectId);
        query.setParameter("buildName", buildName);
        ProjectBuildingEntity projectBuildingEntity = (ProjectBuildingEntity) query.uniqueResult();
        return projectBuildingEntity;
    }

    @Override
    public void addSupplierBuild(BuildingSupplierEntity buildingSupplierEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(buildingSupplierEntity);
        session.flush();
    }

    @Override
    public List<String> getBuildSupplier(String dutyId) {
        String hql = "select buildId from BuildingSupplierEntity where status='1' and agencyId=:dutyId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dutyId", dutyId);
        List<String> buildIds = query.list();
        return buildIds;
    }

    @Override
    public List<String> getProjectBuildSupplier(String dutyId, String projectId) {
        String sql = "SELECT bs.BUILD_ID FROM building_supplier bs " +
                " LEFT JOIN project_building pb ON bs.BUILD_ID = pb.BUILD_ID  " +
                " WHERE 1 = 1  AND pb.PROJECT_ID ='"+projectId+"'AND bs.AGENCY_ID ='"+dutyId+"' AND bs.`STATUS` ='1' ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<String> buildIds = query.list();
        return buildIds;
    }

    @Override
    public void dumpAddSupplierBuild(BuildingSupplierEntity buildingSupplierEntity) {
        String sql1 = "INSERT INTO building_supplier(BUILD_ID,AGENCY_ID,MODIFY_TIME,STATUS) VALUES(?,?,?,1) ON DUPLICATE KEY UPDATE STATUS='1',MODIFY_TIME=?";
        Query query1 = getCurrentSession().createSQLQuery(sql1);
        query1.setString(0, buildingSupplierEntity.getBuildId());
        query1.setString(1, buildingSupplierEntity.getAgencyId());
        query1.setString(2, DateUtils.format(new Date()));
        query1.setString(3, DateUtils.format(new Date()));
        query1.executeUpdate();
    }

    @Override
    public void delSupplierBuildByDutyId(String dutyId) {
        String hql = "update BuildingSupplierEntity set status='0',modifyTime=:modifyTime where agencyId=:dutyId and status='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dutyId", dutyId);
        query.setParameter("modifyTime", new Date());
        query.executeUpdate();
    }

    @Override
    public void delProjectSupplierBuildByDutyId(String dutyId,String projectId) {
        String hql = " UPDATE building_supplier bs, project_building pb SET bs.`STATUS` = '0', bs.MODIFY_TIME =:modifyTime where bs.BUILD_ID=pb.BUILD_ID and bs.AGENCY_ID=:dutyId and pb.PROJECT_ID=:projectId ";
        Query query = getCurrentSession().createSQLQuery(hql);
        query.setParameter("projectId", projectId);
        query.setParameter("dutyId", dutyId);
        query.setParameter("modifyTime", new Date());
        query.executeUpdate();
    }

    @Override
    public void deleteSupplierBuild(BuildingSupplierEntity buildingSupplierEntity) {
        String hql = "update BuildingSupplierEntity set status='0',modifyTime=:modifyTime where agencyId=:dutyId and buildId=:buildId and status='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("dutyId", buildingSupplierEntity.getAgencyId());
        query.setParameter("buildId", buildingSupplierEntity.getBuildId());
        query.setParameter("modifyTime", new Date());
        query.executeUpdate();
    }

    @Override
    public List<Object[]> getBuildingsByTenderId(String tenderId) {
        String sql = "SELECT pb.BUILD_ID,pb.BUILD_NAME " +
                "FROM project_building pb " +
                " LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pb.BUILD_ID " +
                " WHERE pb.BUILD_STATE='1' AND tb.TENDER_STATUS='1' AND tb.TENDERS_ID= '" + tenderId + "' ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getBuildBySupplierId(String supplierId,String projectId) {
        String sql = "SELECT pb.BUILD_ID,pb.BUILD_NAME FROM building_supplier bs " +
                "LEFT JOIN project_building pb ON pb.BUILD_ID = bs.BUILD_ID " +
                "WHERE bs. STATUS = '1' AND pb.BUILD_STATE = '1'and pb.PROJECT_ID= '"+projectId+"'  AND bs.AGENCY_ID = '" + supplierId + "'";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> buildIds = query.list();
        return buildIds;
    }
}
