package com.maxrocky.vesta.presistent.projectSideStation.repositoryHbnImpl;

import com.maxrocky.vesta.domain.inspectAcceptance.model.ProjectExamineEntity;
import com.maxrocky.vesta.domain.projectSideStation.model.ProjectSideStationDetailsEntity;
import com.maxrocky.vesta.domain.projectSideStation.model.ProjectSideStationEntity;
import com.maxrocky.vesta.domain.projectSideStation.repository.ProjectSideStationRepository;
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
 * Created by Talent on 2016/11/8.
 */
@Repository
public class ProjectSideStationRepositoryImpl extends HibernateDaoImpl implements ProjectSideStationRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public boolean checkForUpdatesFromSideStation(String id, String time, String projectId, String creatBy, String type) {
        String sql = "select count(1) from project_side_station ss ";
        sql += " where 1=1";
        if (!StringUtil.isEmpty(time)) {
            if (!StringUtil.isEmpty(id)) {
                if (projectId != null && !projectId.equals("")) {
                    sql += " and ss.CREATE_ON > '" + time + "' or (ss.CREATE_ON = '" + time + "' and ss.ID > '" + id + "' and ss.PROJECT_ID = '" + projectId + "')";
                } else {
                    sql += " and ss.CREATE_ON > '" + time + "' or (ss.CREATE_ON = '" + time + "' and ss.ID > '" + id + "')";
                }
            } else {
                sql += " and ss.CREATE_ON > '" + time + "'";
            }
        }
        //第三方监理
        if ("2".equals(type)) {
            sql += " and ( ss.CREATE_BY_ID='" + creatBy + "'or ss.SIDE_STATION_STAFF_ID='" + creatBy + "')";
        }

        Query query = getCurrentSession().createSQLQuery(sql);
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Object[]> getSideStationInfoByParameter(String id, String beginDateNew, String projectId, String staffId, String type) {
        String sql = "select ss.ID,ss.SIDE_STATION_ID from project_side_station ss ";
        sql += " where 1=1";
        if (!StringUtil.isEmpty(beginDateNew)) {
            if (!StringUtil.isEmpty(id)) {
                if (projectId != null && !projectId.equals("")) {
                    sql += " and ss.CREATE_ON > '" + beginDateNew + "' or (ss.CREATE_ON = '" + beginDateNew + "' and ss.ID > '" + id + "' and ss.PROJECT_ID = '" + projectId + "')";
                } else {
                    sql += " and ss.CREATE_ON > '" + beginDateNew + "' or (ss.CREATE_ON = '" + beginDateNew + "' and ss.ID > '" + id + "')";
                }
            } else {
                sql += " and ss.CREATE_ON > '" + beginDateNew + "'";
            }
        }
        //第三方监理
        if ("2".equals(type)) {
            sql += " and ( ss.CREATE_BY_ID='" + staffId + "'or ss.SIDE_STATION_STAFF_ID='" + staffId + "')";
        }
        sql += " GROUP  BY ss.SIDE_STATION_ID  ORDER BY ss.CREATE_ON DESC,ss.ID DESC";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public ProjectSideStationEntity getSideStationInfoById(String sideStationId) {
        String hql = " FROM ProjectSideStationEntity psse where sideStationId = '" + sideStationId + "'";
        Query query = getCurrentSession().createQuery(hql);
        return (ProjectSideStationEntity) query.uniqueResult();
    }

    @Override
    public ProjectSideStationDetailsEntity getSideStationDetailsInfoBySideStationId(String id) {
        String hql = " FROM ProjectSideStationDetailsEntity psse where id = '" + id + "'";
        Query query = getCurrentSession().createQuery(hql);
        return (ProjectSideStationDetailsEntity) query.uniqueResult();
    }

    @Override
    public List<Object[]> getSideStationDetailsBySideStationId(String sideStationId) {
        String sql = "SELECT pssd.ID,pssd.DESCRIPTION,pi.IMAGE_URL,pssd.RECORD_TIME,pssd.SERIAL_NUMBER FROM " +
                " project_images pi LEFT JOIN project_side_station_details pssd ON pssd.ID = pi.BUSINESS_ID " +
                "WHERE pi.TYPE='6'";
        if (!StringUtil.isEmpty(sideStationId)) {
            sql += "  AND pssd.SIDE_STATION_ID='" + sideStationId + "'";
        }
        sql += " ORDER BY pssd.SERIAL_NUMBER ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getSideStationDetailsInfoListBySideStationId(String sideStationId) {
        String sql = "SELECT pssd.ID,pssd.DESCRIPTION,pi.IMAGE_URL,pssd.RECORD_TIME FROM " +
                " project_images pi LEFT JOIN project_side_station_details pssd ON pssd.ID = pi.BUSINESS_ID " +
                "WHERE pi.TYPE='6'";
        if (!StringUtil.isEmpty(sideStationId)) {
            sql += "  AND pssd.SIDE_STATION_ID='" + sideStationId + "'";
        }
        sql += " ORDER BY (-pssd.SERIAL_NUMBER) DESC ";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<ProjectSideStationEntity> getSideStationInfoByParameter(String id, String beginDateNew, String projectId) {
        String hql = " FROM ProjectSideStationEntity psse WHERE 1=1 ";
        if (!StringUtil.isEmpty(beginDateNew)) {
            if (!StringUtil.isEmpty(id)) {
                if (projectId != null && !projectId.equals("")) {
                    hql += " and createOn > '" + beginDateNew + "' or (createOn = '" + beginDateNew + "' and id > '" + id + "' and projectId = '" + projectId + "')";
                } else {
                    hql += " and createOn > '" + beginDateNew + "' or (createOn = '" + beginDateNew + "' and id > '" + id + "')";
                }
            } else {
                hql += " and createOn > '" + beginDateNew + "'";
            }
        }
        hql += " ORDER BY psse.createOn DESC,psse.id DESC";
        Query query = getCurrentSession().createQuery(hql);
        query.setMaxResults(500);
        List<ProjectSideStationEntity> acceptanceBatchList = query.list();
        return acceptanceBatchList;
    }

    @Override
    public List<Object[]> getSideStationCountByProjectId(String projectId) {
        Session session = getCurrentSession();
        String sql = "SELECT pb.BUILD_NAME,pb.BUILD_ID,pb.PROJECT_ID," +
                "(SELECT count(1) FROM project_side_station pss WHERE pss.BUILDING_ID=pb.BUILD_ID) AS total" +
                " FROM project_building pb WHERE pb.BUILD_STATE='1' AND pb.PROJECT_ID= :projectId ORDER BY CONVERT(pb.PROJECT_ID USING gbk ) ASC, pb.AUTO_NUM ASC";
//        String sql = "SELECT pss.BUILDING_NAME,pss.BUILDING_ID,pss.PROJECT_ID," +
//                "(SELECT count(1) FROM project_side_station psss WHERE psss.PROJECT_ID= :projectId GROUP BY psss.BUILDING_ID) AS total " +
//                "FROM project_side_station pss WHERE pss.PROJECT_ID= :projectId GROUP BY pss.BUILDING_ID";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter("projectId", projectId);
        List<Object[]> acceptanceList = sqlQuery.list();
        return acceptanceList;
    }

    @Override
    public boolean addSideStationDetailsInnfo(ProjectSideStationDetailsEntity projectSideStationDetailsEntity) {
        Session session = getCurrentSession();
        session.save(projectSideStationDetailsEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public boolean addSideStationInfo(ProjectSideStationEntity projectSideStationEntity) {
        Session session = getCurrentSession();
        session.save(projectSideStationEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public boolean updateSideStationInfo(ProjectSideStationEntity projectSideStationEntity) {
        Session session = getCurrentSession();
        session.update(projectSideStationEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public List<ProjectSideStationEntity> searchBesideStationList(Map map, WebPage webPage, String staffId) {
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
        String hql = " from ProjectSideStationEntity pss where 1=1 ";
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            hql += " and pss.projectId = ? ";
            params.add(map.get("projectId").toString());
        } else {
            hql += " and (pss.projectId in(" + projectId1s + ") or pss.projectId in(" + projectId2s + ")) ";
        }
        if (map.get("classfiyOne") != null && !"".equals(map.get("classfiyOne").toString())) {
            hql += " and pss.firstCategory = ? ";
            params.add(map.get("classfiyOne").toString());
        }
        if (map.get("classfiyTwo") != null && !"".equals(map.get("classfiyTwo").toString())) {
            hql += " and pss.secondCategory = ? ";
            params.add(map.get("classfiyTwo").toString());
        }
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            hql += " and pss.createOn >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            hql += " and pss.createOn <= '" + endDate + "' ";
        }
        if (map.get("sideStationStaffName") != null && !"".equals(map.get("sideStationStaffName").toString())) {
            hql += " and pss.sideStationStaffName like '%" + map.get("sideStationStaffName").toString() + "%' ";
        }

        hql += " order by pss.createOn desc";

        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<ProjectSideStationEntity> projectSideStationEntityList = (List<ProjectSideStationEntity>) getHibernateTemplate().find(hql, params.toArray());
        return projectSideStationEntityList;
    }

    @Override
    public List<ProjectSideStationEntity> searchBesideStationList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from ProjectSideStationEntity pss where 1=1 ";
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            hql += " and pss.projectId = ? ";
            params.add(map.get("projectId").toString());
        }
        if (map.get("classfiyOne") != null && !"".equals(map.get("classfiyOne").toString())) {
            hql += " and pss.firstCategory = ? ";
            params.add(map.get("classfiyOne").toString());
        }
        if (map.get("classfiyTwo") != null && !"".equals(map.get("classfiyTwo").toString())) {
            hql += " and pss.secondCategory = ? ";
            params.add(map.get("classfiyTwo").toString());
        }
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            hql += " and pss.createOn >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            hql += " and pss.createOn <= '" + endDate + "' ";
        }
        if (map.get("sideStationStaffName") != null && !"".equals(map.get("sideStationStaffName").toString())) {
            hql += " and pss.sideStationStaffName like '%" + map.get("sideStationStaffName").toString() + "%' ";
        }

        hql += " order by pss.createOn desc";

        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<ProjectSideStationEntity> projectSideStationEntityList = (List<ProjectSideStationEntity>) getHibernateTemplate().find(hql, params.toArray());
        return projectSideStationEntityList;
    }

    @Override
    public List<ProjectSideStationEntity> searchBesideStationList() {
        List<Object> params = new ArrayList<Object>();
        String hql = " from ProjectSideStationEntity pss where 1=1 ";
        List<ProjectSideStationEntity> projectSideStationEntityList = (List<ProjectSideStationEntity>) getHibernateTemplate().find(hql, params.toArray());
        return projectSideStationEntityList;
    }

    @Override
    public ProjectSideStationEntity getSideStationInfoByAppId(String id) {
        String hql = " FROM ProjectSideStationEntity psse where appId = '" + id + "'";
        Query query = getCurrentSession().createQuery(hql);
        return (ProjectSideStationEntity) query.uniqueResult();
    }
}
