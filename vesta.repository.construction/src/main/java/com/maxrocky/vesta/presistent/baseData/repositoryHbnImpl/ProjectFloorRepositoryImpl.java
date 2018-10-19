package com.maxrocky.vesta.presistent.baseData.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.ProjectFloorEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectHouseImageEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectFloorRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/10/20.
 */

@Repository
public class ProjectFloorRepositoryImpl extends HibernateDaoImpl implements ProjectFloorRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    @Override
    public String getFloorNumByBuildId(String buildId) {
        String sql = "SELECT MAX(CAST(floor_name AS DECIMAL)) FROM project_floor WHERE floor_state='1' AND build_id='" + buildId + "'";
        Query query = getCurrentSession().createSQLQuery(sql);
//        query.setParameter("buildId", buildId);
        BigDecimal floorNum = (BigDecimal) query.uniqueResult();
        return String.valueOf(floorNum);
    }

    @Override
    public List<ProjectFloorEntity> getFloorsByBuildId(String buildId, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from ProjectFloorEntity where buildId=? and floorState='1' order by modifyOn desc, autoNum desc";
        params.add(buildId);
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter(0, buildId);
        List<ProjectFloorEntity> projectFloorEntities = query.list();
        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        return projectFloorEntities;
    }

    @Override
    public void addProjectFloor(ProjectFloorEntity projectFloorEntity) {
        Session session = getCurrentSession();
        session.save(projectFloorEntity);
        session.flush();
    }

    @Override
    public void updateProjectFloor(ProjectFloorEntity projectFloorEntity) {
        Session session = getCurrentSession();
        session.update(projectFloorEntity);
        session.flush();
    }

    @Override
    public ProjectFloorEntity getProjectFloorDetail(String floorId) {
        String hql = "from ProjectFloorEntity where floorId=:floorId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("floorId", floorId);
        ProjectFloorEntity projectFloorEntity = (ProjectFloorEntity) query.uniqueResult();
        return projectFloorEntity;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 批量修改楼层，检验该楼层是否存在
     */
    @Override
    public ProjectFloorEntity getProjectFloor(String floorName, String buildId) {
        StringBuffer hql = new StringBuffer(" from ProjectFloorEntity where floorName=:floorName and floorState='1' and buildId=:buildId");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("floorName", floorName);
        query.setParameter("buildId", buildId);
        ProjectFloorEntity projectFloorEntity = (ProjectFloorEntity) query.uniqueResult();
        return projectFloorEntity;
    }

    @Override
    public boolean checkThisFloorName(String floorName, String buildId, String floorId) {
        String hql = " from ProjectFloorEntity where floorName=:floorName and floorState='1' and buildId=:buildId";
        if (!StringUtil.isEmpty(floorId)) {
            hql += " and floorId<>:floorId";
        }
        Query query = getCurrentSession().createQuery(hql.toString());
        if (!StringUtil.isEmpty(floorId)) {
            query.setParameter("floorId", floorId);
        }
        query.setParameter("floorName", floorName);
        query.setParameter("buildId", buildId);
        List<ProjectFloorEntity> projectFloorEntities = query.list();
        if (projectFloorEntities != null && projectFloorEntities.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<ProjectFloorEntity> getFloorListForTime(String projectId, String timeStamp, long autoId) {
        String hql = "from ProjectFloorEntity where floorId in(select distinct a.floorId from ProjectFloorEntity a,ProjectBuildingEntity b where a.buildId=b.id and b.projectId=:projectId and ((a.modifyOn='" + timeStamp + "' and a.autoNum>:autoId) or a.modifyOn>'" + timeStamp + "')) order by modifyOn,autoNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId", projectId);
        query.setParameter("autoId", autoId);
        List<ProjectFloorEntity> projectFloorEntities = query.list();
        return projectFloorEntities;
    }

    @Override
    public List<ProjectHouseImageEntity> getHouseImageForTime(String projectId, String timeStamp, long autoId) {
        String hql = "from ProjectHouseImageEntity where imgId in(select distinct a.imgId from ProjectHouseImageEntity a,ProjectFloorEntity b,ProjectBuildingEntity c where a.floorId=b.floorId and b.buildId=c.id and c.projectId=:projectId and ((a.modifyOn='" + timeStamp + "' and a.autoNum>:autoId) or a.modifyOn>'" + timeStamp + "')) order by modifyOn,autoNum";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId", projectId);
        query.setParameter("autoId", autoId);
        List<ProjectHouseImageEntity> projectHouseImageEntities = query.list();
        return projectHouseImageEntities;
    }

    @Override
    public List<Object[]> getBuildFloorsByProject(String id) {
        Session session = getCurrentSession();
        String sql = "SELECT pb.BUILD_NAME,MIN(CAST(pf.FLOOR_NAME AS DECIMAL)) AS startFloor,MAX(CAST(pf.FLOOR_NAME AS DECIMAL)) AS endFloor " +
                " FROM project_building pb " +
                " LEFT JOIN project_floor pf ON pf.BUILD_ID = pb.BUILD_ID\n" +
                "WHERE pb.BUILD_STATE = '1' AND pf.FLOOR_STATE = '1'";
        if (!StringUtil.isEmpty(id)) {
            sql += " AND pb.PROJECT_ID='" + id + "'";
        }
        sql += " GROUP BY pb.BUILD_NAME";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        List<Object[]> acceptanceList = sqlQuery.list();
        return acceptanceList;
    }

    @Override
    public List<ProjectFloorEntity> getProjectFloorsByBuildId(String buildId) {
        String hql = " from ProjectFloorEntity where floorState='1' and buildId=:buildId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("buildId", buildId);
        List<ProjectFloorEntity> projectFloorEntityList = query.list();
        return projectFloorEntityList;
    }
}
