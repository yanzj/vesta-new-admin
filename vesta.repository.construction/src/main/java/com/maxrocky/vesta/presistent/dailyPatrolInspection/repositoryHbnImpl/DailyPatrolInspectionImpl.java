package com.maxrocky.vesta.presistent.dailyPatrolInspection.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.DailyPatrolInspectionDetailsEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.DailyPatrolInspectionEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyDetailsEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.repository.DailyPatrolInspectionRepository;
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
 * Created by Magic on 2016/10/17.
 */
@Repository
public class DailyPatrolInspectionImpl extends HibernateDaoImpl implements DailyPatrolInspectionRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public boolean checkInspectionByApp(String id, String timeStamp, String projectId, String creaBy, String type) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" count(1) ");
        sql.append(" from project_inspection s ");
        sql.append(" LEFT JOIN project_copy pc ON s.INSPECTION_ID=pc.BUSINESS ");
        sql.append(" LEFT JOIN project_copy_details pcd ON pc.ID=pcd.COPY_ID ");
        sql.append(" where 1=1 ");
        sql.append(" and s.PROJECT_ID=:projectId ");
        if ("2".equals(type)) {
            sql.append(" and (s.CREATE_BY=:creaBy or pcd.MEMBER_ID=:creaBy or s.SUPERVISOR=:creaBy or s.DEAL_PEOPLE=:creaBy)");
        }
        if ("3".equals(type)) {
            sql.append(" and s.DEAL_PEOPLE=:creaBy ");
            sql.append(" or (s.ASSIGN_ID=:creaBy and s.STATE='完成')");
        }
        sql.append(" and ((s.CREATE_BY =:creaBy and s.STATE='草稿') or s.STATE <>'草稿') ");
        sql.append(" and ((s.MODIFY_ON=:tim and s.ID>:iid) or s.MODIFY_ON>:tim) ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("projectId", projectId);
        query.setParameter("creaBy", creaBy);
        query.setParameter("tim", timeStamp);
        query.setParameter("iid", id);
        List list = query.list();
        if (!list.get(0).toString().equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Object[]> getInspectionListByApp(String id, String timeStamp, String projectId, String creaid, String type) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" s.TITLE,s.CLASSIFY_ONE,s.CLASSIFY_TWO,s.CLASSIFY_THREE,s.PROJECT_ID,p.PROJECT_NAME,s.POINT_ID,s.BUILDING_ID,b.BUILD_NAME,");
        sql.append(" s.FLOOR_ID,f.FLOOR_NAME,s.SEVERITY_LEVEL,s.DESCRIPTION,s.SUPPLIER_ID,s.SUPPLIER,s.DEAL_PEOPLE,s.FIRST_PARTY,s.FIRST_PARTY_NAME,");
        sql.append(" s.ASSIGN_ID,s.ASSIGN_NAME,s.SUPERVISOR,s.SUPERVISOR_NAME,s.RECTIFICATION_PERIOD,s.X_COORDINATE,s.Y_COORDINATE,s.STATE,s.ID,");
        sql.append(" s.INSPECTION_ID,s.CREATE_NAME,s.CREATE_ON,s.MODIFY_ON,s.CLASSIFY_ONE_NAME,s.CLASSIFY_TWO_NAME,s.CLASSIFY_THREE_NAME,s.APP_ID ");
        sql.append(" from project_inspection s ");
        sql.append(" LEFT JOIN project_project p ON s.PROJECT_ID = p.PROJECT_ID ");
        sql.append(" LEFT JOIN project_building b on s.BUILDING_ID = b.BUILD_ID ");
        sql.append(" LEFT JOIN project_floor f on s.FLOOR_ID = f.FLOOR_ID ");
        sql.append(" LEFT JOIN project_copy pc ON s.INSPECTION_ID=pc.BUSINESS ");
        sql.append(" LEFT JOIN project_copy_details pcd ON pc.ID=pcd.COPY_ID ");
        sql.append(" where 1=1 ");
        sql.append(" and s.PROJECT_id=:projectId ");
//        if("1".equals(type)){
//            sql.append(" and ((s.CREATE_BY =:creaid and s.STATE='草稿') or s.STATE <>'草稿') ");
//        }
        if ("2".equals(type)) {
            sql.append(" and (s.CREATE_BY=:creaid or pcd.MEMBER_ID=:creaid or s.SUPERVISOR=:creaid or s.DEAL_PEOPLE=:creaid)");
        }
        if ("3".equals(type)) {
            sql.append(" and (s.DEAL_PEOPLE=:creaid or (s.ASSIGN_ID=:creaid and s.STATE='完成') or pcd.MEMBER_ID=:creaid )");
        }
        sql.append(" and ((s.CREATE_BY =:creaid and s.STATE='草稿') or s.STATE <>'草稿') ");
        sql.append(" and ((s.MODIFY_ON=:tim and s.ID>:iid) or s.MODIFY_ON>:tim) GROUP BY INSPECTION_ID ORDER BY s.MODIFY_ON , s.ID limit 500 ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("tim", timeStamp);
        query.setParameter("iid", id);
        query.setParameter("creaid", creaid);
        query.setParameter("projectId", projectId);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getInspectionList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" select ");
        sql.append("dpl.inspectionId,dpl.rectificationPeriod,p.agencyName as projectName,b.name as buildName,f.floorName,dpl.severityLevel,c.categoryName,");
        sql.append("dpl.supplier,dpl.assignName,dpl.supervisorName,dpl.createOn,dpl.state,dpl.projectId,dpl.createName");
        sql.append(" from DailyPatrolInspectionEntity dpl ");
        sql.append(" ,AuthAgencyESEntity p,ProjectBuildingEntity b,ProjectFloorEntity f,ProjectCategoryEntity c ");
        sql.append(" where 1=1 and dpl.projectId=p.agencyId ");
        sql.append(" and dpl.buildingId=b.id ");
        sql.append(" and dpl.floorId=f.floorId ");
        sql.append(" and dpl.classifyThree=c.categoryId ");
        //项目id
        if (!StringUtil.isEmpty(map.get("projectId").toString())) {
            sql.append(" and dpl.projectId=? ");
            params.add(map.get("projectId").toString());
        }
        //项目权限
        if(!"NO".equals(map.get("projectList").toString())){
            sql.append(" and dpl.projectId in (" + map.get("projectList").toString() + ") ");
        }
        //楼栋id
        if (!StringUtil.isEmpty(map.get("buildingId").toString())) {
            sql.append(" and dpl.buildingId=? ");
            params.add(map.get("buildingId").toString());
        }
        //一级分类
        if (!StringUtil.isEmpty(map.get("classifyTwo").toString())) {
            sql.append(" and dpl.classifyTwo=? ");
            params.add(map.get("classifyTwo").toString());

        }
        //二级分类
        if (!StringUtil.isEmpty(map.get("classifyOne").toString())) {
            sql.append(" and dpl.classifyOne=? ");
            params.add(map.get("classifyOne").toString());
        }
//        //三级分类
//        if (!StringUtil.isEmpty(map.get("classfiyThree").toString())) {
//            sql.append(" and dpl.classfiyThree=? ");
//            params.add(map.get("classfiyThree").toString());
//        }
        //状态
        if (!StringUtil.isEmpty(map.get("state").toString())) {
            sql.append(" and dpl.state=? ");
            params.add(map.get("state").toString());
        }
        //严重等级
        if (!StringUtil.isEmpty(map.get("severityLevel").toString())) {
            sql.append(" and dpl.severityLevel=? ");
            params.add(map.get("severityLevel").toString());
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
        //整改人名字
        if (!StringUtil.isEmpty(map.get("assignName").toString())) {
            sql.append(" and dpl.assignName like? ");
            params.add(map.get("assignName").toString());
        }
        //创建人姓名
        if(!StringUtil.isEmpty(map.get("createName").toString())){
            sql.append(" and dpl.createName like? ");
            params.add(map.get("createName").toString());
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
//        //开始日期
//        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
//            sql.append(" and dpl.createOn>=? ");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String strDate = (String) map.get("startDate");
//            try {
//                Date startDate = sdf.parse(strDate);
//                params.add(startDate);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        //结束时间
//        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
//            sql.append(" and dpl.createOn<=? ");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String strDate = (String) map.get("endDate");
//            try {
//                Date endDate = sdf.parse(strDate);
//                params.add(endDate);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
        if (!StringUtil.isEmpty(map.get("creaBy").toString())) {
            sql.append(" and ((dpl.createBy=? and dpl.state='草稿') or dpl.state <>'草稿') ");
            params.add(map.get("creaBy").toString());
        }
        sql.append(" order by dpl.modifyOn desc ");

        if (webPage != null) {
            return this.findByPage(sql.toString(), webPage, params);
        }
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().find(sql.toString(), params.toArray());
        return list;
    }

    @Override
    public List<Object[]> getexportExcelList(Map map) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" dpl.RECTIFICATION_PERIOD,p.AGENCY_NAME,b.BUILD_NAME,f.FLOOR_NAME,dpl.SEVERITY_LEVEL,c.CATEGORY_NAME,");
        sql.append(" dpl.SUPPLIER,dpl.FIRST_PARTY_NAME,dpl.SUPERVISOR_NAME,dpl.ASSIGN_NAME,dpl.CREATE_ON,dpl.DESCRIPTION,dpl.POINT_ID,dpl.STATE, ");
        sql.append(" c1.CATEGORY_NAME as one ,c2.CATEGORY_NAME as two ,dpl.CREATE_NAME,dpl.INSPECTION_ID");
        sql.append(" from project_inspection dpl ");
        sql.append(" ,auth_role_agencyes p, project_building b,project_floor f , project_category c , project_category c1, project_category c2 ");
        sql.append(" where 1=1 and dpl.PROJECT_ID=p.AGENCY_ID ");
        sql.append(" and dpl.BUILDING_ID=b.BUILD_ID ");
        sql.append(" and dpl.FLOOR_ID=f.FLOOR_ID ");
        sql.append(" and dpl.CLASSIFY_ONE=c1.CATEGORY_ID ");
        sql.append(" and dpl.CLASSIFY_TWO=c2.CATEGORY_ID ");
        sql.append(" and dpl.CLASSIFY_THREE=c.CATEGORY_ID ");
        //项目权限
        if(!"NO".equals(map.get("projectList").toString())){
            sql.append(" and dpl.PROJECT_ID in (" + map.get("projectList").toString() + ") ");
        }
        //项目id
        if (!StringUtil.isEmpty(map.get("projectId").toString())) {
            sql.append(" and dpl.PROJECT_ID='" + map.get("projectId").toString() + "' ");
        }
        //楼栋id
        if (!StringUtil.isEmpty(map.get("buildingId").toString())) {
            sql.append(" and dpl.BUILDING_ID='" + map.get("buildingId").toString() + "' ");
        }
        //一级分类
        if (!StringUtil.isEmpty(map.get("classfiyTwo").toString())) {
            sql.append(" and dpl.CLASSIFY_THREE='" + map.get("classfiyTwo").toString() + "' ");
        }
        //二级分类
        if (!StringUtil.isEmpty(map.get("classfiyOne").toString())) {
            sql.append(" and dpl.CLASSIFY_ONE='" + map.get("classfiyOne").toString() + "' ");
        }
        //状态
        if (!StringUtil.isEmpty(map.get("state").toString())) {
            sql.append(" and dpl.STATE='" + map.get("state").toString() + "' ");
        }
        //严重等级
        if (!StringUtil.isEmpty(map.get("severityLevel").toString())) {
            sql.append(" and dpl.SEVERITY_LEVEL='" + map.get("severityLevel").toString() + "' ");
        }
        //责任单位
        if (!StringUtil.isEmpty(map.get("supplier").toString())) {
            sql.append(" and dpl.SUPPLIER like'" + map.get("severityLevel").toString() + "' ");
        }
        //甲方负责人名字
        if (!StringUtil.isEmpty(map.get("firstPartyName").toString())) {
            sql.append(" and dpl.FIRST_PARTY_NAME like'" + map.get("firstPartyName").toString() + "' ");
        }
        //第三方监理名字
        if (!StringUtil.isEmpty(map.get("supervisorName").toString())) {
            sql.append(" and dpl.SUPERVISOR_NAME like'" + map.get("supervisorName").toString() + "' ");
        }
        //创建人姓名
        if(!StringUtil.isEmpty(map.get("createName").toString())){
            sql.append(" and dpl.CREATE_NAME like'" + map.get("createName").toString() +"' ");
        }
        //整改人名字
        if (!StringUtil.isEmpty(map.get("assignName").toString())) {
            sql.append(" and dpl.ASSIGN_NAME like'" + map.get("assignName").toString() + "' ");
        }

        //开始日期
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql.append(" and dpl.CREATE_ON >= '" + startDate +"' ");
        }
        //结束时间
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql.append(" and dpl.CREATE_ON <= '" + endDate +"' ");
        }
//        //开始日期
//        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String strDate = (String) map.get("startDate");
//            try {
//                Date startDate = sdf.parse(strDate);
//                sql.append(" and dpl.CREATE_ON>='" + startDate + "' ");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        //结束时间
//        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String strDate = (String) map.get("endDate");
//            try {
//                Date endDate = sdf.parse(strDate);
//                sql.append(" and dpl.CREATE_ON<='" + endDate + "' ");
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
        if (!StringUtil.isEmpty(map.get("creaBy").toString())) {
            sql.append(" and ((dpl.CREATE_BY='" + map.get("creaBy").toString() + "' and dpl.STATE='草稿') or dpl.STATE <>'草稿') ");
        }
        sql.append(" order by dpl.MODIFY_ON desc ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public DailyPatrolInspectionEntity getInspectionEntityById(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_inspection s where INSPECTION_ID=:id ").addEntity(DailyPatrolInspectionEntity.class);
        sqlQuery.setParameter("id", id);
        List<DailyPatrolInspectionEntity> dailyPatrolInspection = sqlQuery.list();
        if (dailyPatrolInspection.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return dailyPatrolInspection.get(0);
    }

    @Override
    public DailyPatrolInspectionEntity getInspectionEntityByAppId(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_inspection s where APP_ID=:id ").addEntity(DailyPatrolInspectionEntity.class);
        sqlQuery.setParameter("id", id);
        List<DailyPatrolInspectionEntity> dailyPatrolInspection = sqlQuery.list();
        if (dailyPatrolInspection.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return dailyPatrolInspection.get(0);
    }

    @Override
    public void saveInspection(DailyPatrolInspectionEntity dailyPatrolInspection) {
        Session session = getCurrentSession();
        session.save(dailyPatrolInspection);
        session.flush();
        session.close();
    }

    @Override
    public void updateInspection(DailyPatrolInspectionEntity dailyPatrolInspection) {
        Session session = getCurrentSession();
        session.update(dailyPatrolInspection);
        session.flush();
        session.close();
    }

    @Override
    public void saveProjectImages(ProjectImagesEntity projectImages) {
        Session session = getCurrentSession();
        session.save(projectImages);
        session.flush();
        session.close();
    }

    @Override
    public void deleteByNotIds(List<String> id) {
        String sql = "delete from project_images where id not in (:ids)";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameterList("ids", id);
        query.executeUpdate();

    }

    @Override
    public List<ProjectImagesEntity> getProjectImages(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_images s where BUSINESS_ID=:id ").addEntity(ProjectImagesEntity.class);
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
    public void deleteProjectImages(String id, String type) {
        String sql = "delete from project_images where BUSINESS_ID=:id and TYPE=:type";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("id", id);
        query.setParameter("type", type);
        query.executeUpdate();
    }

    @Override
    public void saveProjectCopy(ProjectCopyEntity projectCopy) {
        Session session = getCurrentSession();
        session.save(projectCopy);
        session.flush();
        session.close();
    }

    @Override
    public void deleteProjectCopy(String id, String type) {
        String sql = "delete from project_copy where BUSINESS=:id and DAMAIN=:type";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("id", id);
        query.setParameter("type", type);
        query.executeUpdate();
    }

    @Override
    public void saveProjectCopyDetails(ProjectCopyDetailsEntity ProjectCopyDetails) {
        Session session = getCurrentSession();
        session.save(ProjectCopyDetails);
        session.flush();
        session.close();
    }

    @Override
    public void deleteProjectCopyDetails(String id) {
        String sql = "delete from project_copy_details where COPY_ID=:id";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Object[]> getProjectCopy(String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" c.BUSINESS,cd.MEMBER_ID,cd.MEMBER_NAME ");
        sql.append(" from project_copy c ");
        sql.append(" LEFT JOIN project_copy_details cd on c.ID=cd.COPY_ID ");
        sql.append(" where 1=1 ");
        sql.append(" and c.BUSINESS=:iid ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("iid", id);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<DailyPatrolInspectionDetailsEntity> getDailyPatrolInspectionDetails(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_inspection_details s where INSPECTION_ID=:id  ORDER BY CREATE_ON ").addEntity(DailyPatrolInspectionDetailsEntity.class);
        sqlQuery.setParameter("id", id);
        List<DailyPatrolInspectionDetailsEntity> DailyPatrolInspectionDetails = sqlQuery.list();
        if (DailyPatrolInspectionDetails.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return DailyPatrolInspectionDetails;
    }

    @Override
    public List<Object[]> searchInspection(String projectId) {
        StringBuffer sql = new StringBuffer(" SELECT ");
//        sql.append(" b.BUILD_NAME,b.BUILD_ID,i.PROJECT_ID,p.PROJECT_NAME, ");
//        sql.append(" (select count(1) from project_inspection pi  where pi.STATE<>'草稿' and pi.PROJECT_ID=:projectId and pi.BUILDING_ID=b.BUILD_ID) as inspectionAll, ");
//        sql.append(" (select count(1) from project_inspection pi  where pi.STATE='整改中' and pi.PROJECT_ID=:projectId and pi.BUILDING_ID=b.BUILD_ID) as inspectioning, ");
//        sql.append(" (select count(1) from project_inspection pi  where pi.STATE='已完成' and pi.PROJECT_ID=:projectId and pi.BUILDING_ID=b.BUILD_ID) as inspectionEnd ");
//        sql.append(" FROM project_inspection i ");
//        sql.append(" LEFT JOIN project_building b on i.BUILDING_ID=b.BUILD_ID  ");
//        sql.append(" LEFT JOIN project_project p ON i.PROJECT_ID=p.PROJECT_ID  ");
//        sql.append(" where 1=1 ");
//        sql.append(" GROUP BY i.BUILDING_ID ");
        sql.append(" b.BUILD_ID,b.BUILD_NAME,b.PROJECT_ID,p.PROJECT_NAME, ");
//        sql.append(" (select count(1) from project_inspection pi  where pi.STATE<>'草稿' and pi.STATE<>'非正常关闭' and pi.PROJECT_ID=:projectId and pi.BUILDING_ID=b.BUILD_ID) as inspectionAll,  ");
        sql.append(" (select count(1) from project_inspection pi  where pi.STATE in('整改中','完成') and pi.PROJECT_ID=:projectId and pi.BUILDING_ID=b.BUILD_ID) as inspectionAll,  ");
        sql.append(" (select count(1) from project_inspection pi  where pi.STATE='整改中' and pi.PROJECT_ID=:projectId and pi.BUILDING_ID=b.BUILD_ID) as inspectioning,  ");
        sql.append(" (select count(1) from project_inspection pi  where pi.STATE='完成' and pi.PROJECT_ID=:projectId and pi.BUILDING_ID=b.BUILD_ID) as inspectionEnd  ");
        sql.append(" from project_building b  ");
        sql.append("  LEFT JOIN project_project p ON b.PROJECT_ID=p.PROJECT_ID ");

        sql.append(" where b.PROJECT_ID=:projectId  and b.BUILD_STATE='1' ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("projectId", projectId);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getInspectionListByAppTodo(String id, String timeStamp, String creaid) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" s.TITLE,s.CLASSIFY_ONE,s.CLASSIFY_TWO,s.CLASSIFY_THREE,s.PROJECT_ID,p.PROJECT_NAME,s.POINT_ID,");
        sql.append(" s.BUILDING_ID,b.BUILD_NAME,s.FLOOR_ID,f.FLOOR_NAME,s.SEVERITY_LEVEL,s.DESCRIPTION,s.SUPPLIER_ID,");
        sql.append(" su.NAME,s.ASSIGN_ID,s.ASSIGN_NAME,s.SUPERVISOR,s.SUPERVISOR_NAME,s.RECTIFICATION_PERIOD,s.X_COORDINATE,");
        sql.append(" s.Y_COORDINATE,s.STATE,s.ID,s.INSPECTION_ID,s.CREATE_NAME,s.CREATE_ON,s.MODIFY_ON");
        sql.append(" from project_inspection s ");
        sql.append(" LEFT JOIN project_project p ON s.PROJECT_ID = p.PROJECT_ID ");
        sql.append(" LEFT JOIN project_building b on s.BUILDING_ID = b.BUILD_ID ");
        sql.append(" LEFT JOIN project_floor f on s.FLOOR_ID = f.FLOOR_ID ");
        sql.append(" LEFT JOIN supplier su on s.SUPPLIER_ID=su.ID ");
        sql.append(" LEFT JOIN project_copy pc ON s.INSPECTION_ID=pc.BUSINESS ");
        sql.append(" LEFT JOIN project_copy_details pcd ON pc.ID=pcd.COPY_ID ");
        sql.append(" where 1=1 ");
        sql.append(" and s.DEAL_PEOPLE=:creaid ");
        sql.append(" and s.STATE='开始' ");
        sql.append(" and ((s.MODIFY_ON=:tim and s.ID>:iid) or s.MODIFY_ON>:tim) GROUP BY INSPECTION_ID ORDER BY s.MODIFY_ON , s.ID limit 500 ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("tim", timeStamp);
        query.setParameter("iid", id);
        query.setParameter("creaid", creaid);
        return (List<Object[]>) query.list();
    }

    @Override
    public boolean checkInspectionTodo(String id, String timeStamp, String creaBy) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" count(1) ");
        sql.append(" from project_inspection s ");
        sql.append(" where 1=1 ");
        sql.append(" and s.DEAL_PEOPLE=:creaid ");
        sql.append(" and s.STATE='开始' ");
        sql.append(" and ((s.MODIFY_ON=:tim and s.ID>:iid) or s.MODIFY_ON>:tim) ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("creaBy", creaBy);
        query.setParameter("tim", timeStamp);
        query.setParameter("iid", id);
        List list = query.list();
        if (!list.get(0).toString().equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void saveInspectionDetais(DailyPatrolInspectionDetailsEntity dailyPatrolInspectionDetailsEntity) {
        Session session = getCurrentSession();
        session.save(dailyPatrolInspectionDetailsEntity);
        session.flush();
        session.close();
    }

    @Override
    public void deleteProjectImagesList(List id, String type) {
        String sql = "delete from project_images where BUSINESS_ID in :id and TYPE=:type";
        Query query = getCurrentSession().createSQLQuery(sql);
//        query.setParameter("id",id);
        query.setParameter("type", type);
        query.setParameterList("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Object[]> searchInspectionCount(Map map, WebPage webPage) {
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" pb.BUILD_NAME,p.PROJECT_NAME,");
        sql.append(" (SELECT count(1) FROM project_inspection pi WHERE pi.STATE='完成' AND pi.BUILDING_ID=pb.BUILD_ID) AS qualified,");
        sql.append(" (SELECT count(1) FROM project_inspection pi WHERE pi.STATE = '整改中' AND pi.BUILDING_ID=pb.BUILD_ID) AS unQualified ,");
        sql.append(" (SELECT count(1) FROM project_inspection pi WHERE pi.STATE = '非正常关闭' AND pi.BUILDING_ID=pb.BUILD_ID) AS clQualified ");
        sql.append(" FROM project_building pb ");
        sql.append(" LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID  ");
        sql.append(" WHERE 1=1 AND pb.BUILD_STATE = '1'");
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            sql.append(" and p.PROJECT_ID = '" + map.get("projectId").toString() + "'");
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            sql.append(" and pb.BUILD_ID ='" + map.get("buildingId").toString() + "'");
        }
        if(map.get("projectList") != null && !"".equals(map.get("projectList").toString()) && !"NO".equals(map.get("projectList").toString())){
            sql.append(" and p.PROJECT_ID in (" + map.get("projectList").toString() + ")");
        }
        sql.append(" ORDER BY CONVERT( p.PROJECT_NAME USING gbk ) DESC,pb.AUTO_NUM ASC ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setFirstResult(webPage.getStartRow());
        query.setMaxResults(webPage.getPageSize());
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> searchInspectionCount(Map map) {
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" pb.BUILD_NAME,p.PROJECT_NAME,");
        sql.append(" (SELECT count(1) FROM project_inspection pi WHERE pi.STATE='完成' AND pi.BUILDING_ID=pb.BUILD_ID) AS qualified,");
        sql.append(" (SELECT count(1) FROM project_inspection pi WHERE pi.STATE = '整改中' AND pi.BUILDING_ID=pb.BUILD_ID) AS unQualified, ");
        sql.append(" (SELECT count(1) FROM project_inspection pi WHERE pi.STATE = '非正常关闭' AND pi.BUILDING_ID=pb.BUILD_ID) AS clQualified ");
        sql.append(" FROM project_building pb ");
        sql.append(" LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID  ");
//        sql.append(" LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pb.BUILD_ID  ");
//        sql.append(" LEFT JOIN project_tenders t ON t.TENDER_ID = tb.TENDERS_ID  ");
        sql.append(" WHERE 1=1 AND pb.BUILD_STATE = '1' ");
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            sql.append(" and p.PROJECT_ID = '" + map.get("projectId").toString() + "'");
        }
//        if (map.get("tenderId") != null && !"".equals(map.get("tenderId").toString())) {
//            sql.append(" and tb.TENDERS_ID = '" + map.get("tenderId").toString() + "'");
//        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            sql.append(" and pb.BUILD_ID ='" + map.get("buildingId").toString() + "'");
        }
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return (List<Object[]>) query.list();
    }

    @Override
    public int searchDailyPatrolInspectionCount(Map map) {
        String hql = "select count(1) FROM project_inspection pi ";
        hql += " LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pi.BUILDING_ID ";
        hql += " WHERE 1=1 ";
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            hql += " AND pi.PROJECT_ID = '" + map.get("projectId").toString() + "'";
        }
        if (map.get("tenderId") != null && !"".equals(map.get("tenderId").toString())) {
            hql += " AND tb.TENDERS_ID = '" + map.get("tenderId").toString() + "'";
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())) {
            hql += " AND pi.BUILDING_ID = '" + map.get("buildingId").toString() + "'";
        }
        Query query = getCurrentSession().createSQLQuery(hql);
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return count.intValue();
        } else {
            return 0;
        }
//        if (map.get("projectNum") != null && !"".equals(map.get("projectNum").toString())) {
//            hql += " AND pi.PROJECT_ID = '" + map.get("projectNum").toString() + "'";
//        }
//        if (map.get("buildingNum") != null && !"".equals(map.get("buildingNum").toString())) {
//            hql += " AND pi.BUILDING_ID = '" + map.get("buildingNum").toString() + "'";
//        }
//        Query query = getCurrentSession().createSQLQuery(hql);
//        BigInteger count = (BigInteger) query.uniqueResult();
//        if (count.intValue() > 0) {
//            return count.intValue();
//        } else {
//            return 0;
//        }
    }

    @Override
    public int getCount(Map map) {
        StringBuffer sql = new StringBuffer(" SELECT count(1) ");
        sql.append(" FROM project_building pb ");
        sql.append(" LEFT JOIN project_project p ON pb.PROJECT_ID = p.PROJECT_ID  ");
//        sql.append(" LEFT JOIN project_tenders_building tb ON tb.BUILDING_ID = pb.BUILD_ID  ");
//        sql.append(" LEFT JOIN project_tenders t ON t.TENDER_ID = tb.TENDERS_ID  ");
        sql.append(" WHERE 1=1 AND pb.BUILD_STATE = '1'");
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            sql.append(" and p.PROJECT_ID = '" + map.get("projectId").toString() + "'");
        }
//        if (map.get("tenderId") != null && !"".equals(map.get("tenderId").toString())) {
//            sql.append(" and tb.TENDERS_ID = '" + map.get("tenderId").toString() + "'");
//        }
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
    public int getProjectCount(Map map) {
        StringBuffer sql = new StringBuffer(" SELECT count(1) ");
        sql.append(" FROM project_project pp ");
        sql.append(" LEFT JOIN project_city pc ON pp.CITY_ID = pc.CITY_ID ");
        sql.append(" LEFT JOIN project_operation po ON pc.OPERATION_ID = po.OPERATION_ID ");
        sql.append(" WHERE 1=1 AND pp.PROJECT_STATE = '1'");
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            sql.append(" and pp.PROJECT_ID = '" + map.get("projectId").toString() + "'");
        }
        //按区域公司查询
        if (map.get("operationId") != null && !"".equals(map.get("operationId").toString())) {
            sql.append(" and po.OPERATION_ID = '" + map.get("operationId").toString() + "'");
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
    public int getOperatCount(Map map) {
        StringBuffer sql = new StringBuffer(" SELECT count(1) ");
        sql.append(" FROM project_operation po ");
        sql.append(" WHERE 1=1 AND po.OPERATION_STATE = '1' ");
        //按区域公司查询
        if (map.get("operationId") != null && !"".equals(map.get("operationId").toString())) {
            sql.append(" and po.OPERATION_ID = '" + map.get("operationId").toString() + "'");
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
    public Object[] getInspectionListByAdmin(String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" s.INSPECTION_ID,s.TITLE,s.CREATE_NAME,s.CREATE_ON,s.STATE,s.PROJECT_ID,p.PROJECT_NAME,s.POINT_ID,c.CATEGORY_NAME,s.SEVERITY_LEVEL,");
        sql.append(" s.RECTIFICATION_PERIOD,s.DESCRIPTION,s.SUPPLIER,s.ASSIGN_NAME,s.FIRST_PARTY_NAME,s.SUPERVISOR_NAME,s.X_COORDINATE,s.Y_COORDINATE,");
        sql.append(" s.DEAL_PEOPLE,s.FLOOR_ID,s.SHUT_DOWN,s.SHUT_DOWN_BY,s.SHUT_DOWN_ON ");
        sql.append(" from project_inspection s ");
        sql.append(" LEFT JOIN project_project p ON s.PROJECT_ID = p.PROJECT_ID ");
        sql.append(" LEFT JOIN project_building b on s.BUILDING_ID = b.BUILD_ID ");
        sql.append(" LEFT JOIN project_floor f on s.FLOOR_ID = f.FLOOR_ID ");
        sql.append(" LEFT JOIN supplier su on s.SUPPLIER_ID=su.ID ");
        sql.append(" LEFT JOIN project_category c ON s.CLASSIFY_THREE = c.CATEGORY_ID ");
        sql.append(" where 1=1 ");
        sql.append(" and s.INSPECTION_ID=:id");
        sql.append(" ORDER BY s.MODIFY_ON");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", id);
        return (Object[]) query.list().get(0);
    }

    @Override
    public DailyPatrolInspectionEntity getDailyPatrolInspection(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from  project_inspection s where INSPECTION_ID=:id ").addEntity(DailyPatrolInspectionEntity.class);
        sqlQuery.setParameter("id", id);
        List<DailyPatrolInspectionEntity> dailyPatrolInspectionEntity = sqlQuery.list();
        if (dailyPatrolInspectionEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return dailyPatrolInspectionEntity.get(0);
    }
    /**
     * 日常巡检 按照当前登录人的员工id 查询整改中本人所负责人数据
     * */
    @Override
    public int inspectionCount(String userId) {
        String sql = "select count(1) from project_inspection where  STATE='整改中' ";
        if (!StringUtil.isEmpty(userId)) {
            sql += " and DEAL_PEOPLE = '" + userId + "'";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return count.intValue();
        } else {
            return 0;
        }
//
//        StringBuffer sql = new StringBuffer(" SELECT ");
//        sql.append("  count(1) as cou FROM  where =:userId and STATE='整改中' ");
//        Query query = getCurrentSession().createSQLQuery(sql.toString());
//        query.setParameter("userId",userId);
//        return (List<Object[]>)query.list();
    }

    @Override
    public List<DailyPatrolInspectionEntity> getDailyPatrolInspectionByInspectionId(String inspectionId) {
        Session session = getCurrentSession();
        String sql ="select * from  project_inspection s where INSPECTION_ID IN ("+inspectionId+") ORDER BY s.MODIFY_ON desc";
        SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(DailyPatrolInspectionEntity.class);
        List<DailyPatrolInspectionEntity> dailyPatrolInspectionEntity = sqlQuery.list();
        if (dailyPatrolInspectionEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return dailyPatrolInspectionEntity;
    }
    /**
     * 日常巡检统计（项目）
     * */
    @Override
    public List<Object[]> searchInspectionProjecrCount(Map map, WebPage webPage) {
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" po.OPERATION_ID,po.OPERATION_NAME,pp.PROJECT_ID,pp.PROJECT_NAME, ");
        sql.append(" (SELECT count(1) FROM project_inspection pi WHERE pi.STATE='完成' AND pi.PROJECT_ID=pp.PROJECT_ID) AS qualified, ");
        sql.append(" (SELECT count(1) FROM project_inspection pi WHERE pi.STATE = '整改中' AND pi.PROJECT_ID=pp.PROJECT_ID) AS unQualified , ");
        sql.append(" (SELECT count(1) FROM project_inspection pi WHERE pi.STATE = '非正常关闭' AND pi.PROJECT_ID=pp.PROJECT_ID) AS clQualified  ");
        sql.append(" FROM project_project pp ");
        sql.append(" LEFT JOIN project_city pc ON pp.CITY_ID = pc.CITY_ID  ");
        sql.append(" LEFT JOIN project_operation po ON pc.OPERATION_ID = po.OPERATION_ID ");
        sql.append(" WHERE 1=1  and pp.PROJECT_STATE = '1' ");
        //按项目查询
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            sql.append(" and pp.PROJECT_ID = '" + map.get("projectId").toString() + "'");
        }
        //按区域公司查询
        if (map.get("operationId") != null && !"".equals(map.get("operationId").toString())) {
            sql.append(" and po.OPERATION_ID = '" + map.get("operationId").toString() + "'");
        }
        sql.append("  ORDER BY po.OPERATION_NAME ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setFirstResult(webPage.getStartRow());
        query.setMaxResults(webPage.getPageSize());
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> searchInspectionOperaCount(Map map, WebPage webPage) {
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" po.OPERATION_ID,po.OPERATION_NAME, ");
        sql.append(" (SELECT count(1) FROM project_inspection pi LEFT JOIN project_project pp ON pi.PROJECT_ID = pp.PROJECT_ID LEFT JOIN project_city pc on pp.CITY_ID=pc.CITY_ID LEFT JOIN project_operation poi on pc.OPERATION_ID=poi.OPERATION_ID WHERE pi.STATE='完成' AND poi.OPERATION_ID=po.OPERATION_ID) AS qualified, ");
        sql.append(" (SELECT count(1) FROM project_inspection pi LEFT JOIN project_project pp ON pi.PROJECT_ID = pp.PROJECT_ID LEFT JOIN project_city pc on pp.CITY_ID=pc.CITY_ID LEFT JOIN project_operation poi on pc.OPERATION_ID=poi.OPERATION_ID WHERE pi.STATE='整改中' AND poi.OPERATION_ID=po.OPERATION_ID) AS unQualified, ");
        sql.append(" (SELECT count(1) FROM project_inspection pi LEFT JOIN project_project pp ON pi.PROJECT_ID = pp.PROJECT_ID LEFT JOIN project_city pc on pp.CITY_ID=pc.CITY_ID LEFT JOIN project_operation poi on pc.OPERATION_ID=poi.OPERATION_ID WHERE pi.STATE='非正常关闭' AND poi.OPERATION_ID=po.OPERATION_ID) AS clQualified  ");
        sql.append(" FROM project_operation po ");
        sql.append(" WHERE 1=1 AND po.OPERATION_STATE = '1' ");
        //按区域公司查询
        if (map.get("operationId") != null && !"".equals(map.get("operationId").toString())) {
            sql.append(" and po.OPERATION_ID = '" + map.get("operationId").toString() + "'");
        }
        sql.append(" ORDER BY po.OPERATION_NAME ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setFirstResult(webPage.getStartRow());
        query.setMaxResults(webPage.getPageSize());
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> searchInspectionProjecrCount(Map map) {
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" po.OPERATION_ID,po.OPERATION_NAME,pp.PROJECT_ID,pp.PROJECT_NAME, ");
        sql.append(" (SELECT count(1) FROM project_inspection pi WHERE pi.STATE='完成' AND pi.PROJECT_ID=pp.PROJECT_ID) AS qualified, ");
        sql.append(" (SELECT count(1) FROM project_inspection pi WHERE pi.STATE = '整改中' AND pi.PROJECT_ID=pp.PROJECT_ID) AS unQualified , ");
        sql.append(" (SELECT count(1) FROM project_inspection pi WHERE pi.STATE = '非正常关闭' AND pi.PROJECT_ID=pp.PROJECT_ID) AS clQualified  ");
        sql.append(" FROM project_project pp ");
        sql.append(" LEFT JOIN project_city pc ON pp.CITY_ID = pc.CITY_ID  ");
        sql.append(" LEFT JOIN project_operation po ON pc.OPERATION_ID = po.OPERATION_ID ");
        sql.append(" WHERE 1=1  and pp.PROJECT_STATE = '1' ");
        //按项目查询
        if (map.get("projectId") != null && !"".equals(map.get("projectId").toString())) {
            sql.append(" and pp.PROJECT_ID = '" + map.get("projectId").toString() + "'");
        }
        //按区域公司查询
        if (map.get("operationId") != null && !"".equals(map.get("operationId").toString())) {
            sql.append(" and po.OPERATION_ID = '" + map.get("operationId").toString() + "'");
        }
        sql.append("  ORDER BY po.OPERATION_NAME ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> searchInspectionOperaCount(Map map) {
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" po.OPERATION_ID,po.OPERATION_NAME, ");
        sql.append(" (SELECT count(1) FROM project_inspection pi LEFT JOIN project_project pp ON pi.PROJECT_ID = pp.PROJECT_ID LEFT JOIN project_city pc on pp.CITY_ID=pc.CITY_ID LEFT JOIN project_operation poi on pc.OPERATION_ID=poi.OPERATION_ID WHERE pi.STATE='完成' AND poi.OPERATION_ID=po.OPERATION_ID) AS qualified, ");
        sql.append(" (SELECT count(1) FROM project_inspection pi LEFT JOIN project_project pp ON pi.PROJECT_ID = pp.PROJECT_ID LEFT JOIN project_city pc on pp.CITY_ID=pc.CITY_ID LEFT JOIN project_operation poi on pc.OPERATION_ID=poi.OPERATION_ID WHERE pi.STATE='整改中' AND poi.OPERATION_ID=po.OPERATION_ID) AS unQualified, ");
        sql.append(" (SELECT count(1) FROM project_inspection pi LEFT JOIN project_project pp ON pi.PROJECT_ID = pp.PROJECT_ID LEFT JOIN project_city pc on pp.CITY_ID=pc.CITY_ID LEFT JOIN project_operation poi on pc.OPERATION_ID=poi.OPERATION_ID WHERE pi.STATE='非正常关闭' AND poi.OPERATION_ID=po.OPERATION_ID) AS clQualified  ");
        sql.append(" FROM project_operation po ");
        sql.append(" WHERE 1=1 AND po.OPERATION_STATE = '1' ");
        //按区域公司查询
        if (map.get("operationId") != null && !"".equals(map.get("operationId").toString())) {
            sql.append(" and po.OPERATION_ID = '" + map.get("operationId").toString() + "'");
        }
        sql.append(" ORDER BY po.OPERATION_NAME ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return (List<Object[]>) query.list();
    }
}
