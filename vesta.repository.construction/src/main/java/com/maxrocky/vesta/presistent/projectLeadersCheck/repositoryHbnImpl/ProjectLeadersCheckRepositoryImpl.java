package com.maxrocky.vesta.presistent.projectLeadersCheck.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.model.ProjectKeyProcessesEntity;
import com.maxrocky.vesta.domain.projectLeadersCheck.model.ProjectLeadersCheckDetailEntity;
import com.maxrocky.vesta.domain.projectLeadersCheck.model.ProjectLeadersCheckEntity;
import com.maxrocky.vesta.domain.projectLeadersCheck.repository.ProjectLeadersCheckRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
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
 * Created by Talent on 2017/1/16.
 */
@Repository
public class ProjectLeadersCheckRepositoryImpl extends HibernateDaoImpl implements ProjectLeadersCheckRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<ProjectLeadersCheckEntity> getLeaderCheckList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from ProjectLeadersCheckEntity lc where 1=1 ";
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            hql += " and lc.projectId = '" + map.get("projectId").toString() + "' ";
        }
        if (map.get("createName") != null && !"".equals(map.get("createName").toString())) {
            hql += " and lc.createName like '%" + map.get("createName").toString() + "%' ";
        }
        if (map.get("assignName") != null && !"".equals(map.get("assignName").toString())) {
            hql += " and lc.assignName like '%" + map.get("assignName").toString() + "%'";
        }
        if (map.get("state") != null && !"".equals(map.get("state").toString())) {
            hql += " and lc.state = '" + map.get("state").toString() + "' ";
        }
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            hql += " and lc.createDate >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            hql += " and lc.createDate <= '" + endDate + "' ";
        }
        hql += " order by lc.modifyDate desc";

        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<ProjectLeadersCheckEntity> projectLeadersCheckEntityList = (List<ProjectLeadersCheckEntity>) getHibernateTemplate().find(hql, params.toArray());
        return projectLeadersCheckEntityList;
    }

    @Override
    public List<ProjectLeadersCheckEntity> getLeaderCheckList(Map map, WebPage webPage, String staffId) {
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

        String hql = " from ProjectLeadersCheckEntity lc where 1=1 ";
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            hql += " and lc.projectId = '" + map.get("projectId").toString() + "' ";
        } else {
            hql += " and (lc.projectId in(" + projectId1s + ") or lc.projectId in(" + projectId2s + ")) ";
        }
        if (map.get("createName") != null && !"".equals(map.get("createName").toString())) {
            hql += " and lc.createName like '%" + map.get("createName").toString() + "%' ";
        }
        if (map.get("assignName") != null && !"".equals(map.get("assignName").toString())) {
            hql += " and lc.assignName like '%" + map.get("assignName").toString() + "%'";
        }
        if (map.get("state") != null && !"".equals(map.get("state").toString())) {
            hql += " and lc.state = '" + map.get("state").toString() + "' ";
        }
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            hql += " and lc.createDate >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            hql += " and lc.createDate <= '" + endDate + "' ";
        }
        hql += " order by lc.modifyDate desc";

        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<ProjectLeadersCheckEntity> projectLeadersCheckEntityList = (List<ProjectLeadersCheckEntity>) getHibernateTemplate().find(hql, params.toArray());
        return projectLeadersCheckEntityList;
    }

    @Override
    public ProjectLeadersCheckEntity getLeaderCheckById(String checkId) {
        String hql = "from ProjectLeadersCheckEntity  where checkId =:checkId ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("checkId", checkId);
        return (ProjectLeadersCheckEntity) query.uniqueResult();
    }

    @Override
    public List<ProjectLeadersCheckDetailEntity> getListByCheckId(String checkId) {
        String hql = "from ProjectLeadersCheckDetailEntity  where checkId =:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", checkId);
        List<ProjectLeadersCheckDetailEntity> leadersCheckDetailEntity = query.list();
        return leadersCheckDetailEntity;
    }

    @Override
    public List<ProjectImagesEntity> getProjectImages(String id) {
        String hql = "from ProjectImagesEntity  where businessId =:id and type='8'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        List<ProjectImagesEntity> imagesEntity = query.list();
        return imagesEntity;
    }

    @Override
    public boolean modifyLeaderCheck(ProjectLeadersCheckEntity leadersCheckEntity) {
        Session session = getCurrentSession();
        session.update(leadersCheckEntity);
        session.flush();
        session.close();
        return true;
    }
}
