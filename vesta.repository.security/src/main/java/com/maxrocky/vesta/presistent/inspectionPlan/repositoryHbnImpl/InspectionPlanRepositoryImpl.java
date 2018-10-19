package com.maxrocky.vesta.presistent.inspectionPlan.repositoryHbnImpl;

import com.maxrocky.vesta.domain.inspectionPlan.repository.InspectionPlanRepository;
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
 * Created by Magic on 2017/6/20.
 */
@Repository
public class InspectionPlanRepositoryImpl  extends HibernateDaoImpl implements InspectionPlanRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    @Override
    public List<Object[]> getInspectionPlanList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" rt.planId,rt.planName,rt.createDate,rt.createUnitId,rt.createUnitName,rt.planStart,rt.planEnd,rt.score,rt.state,rt.createName  ");
        sql.append(" from InspectionPlanEntity rt ");
        sql.append(" where 1=1 ");
        //项目公司ID
        if (!StringUtil.isEmpty(map.get("projectId").toString())) {
            sql.append(" and (rt.createUnitId=?  )");
            params.add(map.get("projectId").toString());
        }else if(!StringUtil.isEmpty(map.get("areaId").toString()) ){
            sql.append(" and (rt.createUnitId=?  )");
            params.add(map.get("areaId").toString());
        }else if(!StringUtil.isEmpty(map.get("groupId").toString())){
            sql.append(" and (rt.createUnitId=?  )");
            params.add(map.get("groupId").toString());
        }else{
            sql.append(" and (rt.createUnitId=''  )");
        }
        //状态
        if (!StringUtil.isEmpty(map.get("state").toString())) {
            sql.append(" and rt.state=? ");
            params.add(map.get("state").toString());
        }
        //计划名称
        if (!StringUtil.isEmpty(map.get("planName").toString())) {
            sql.append(" and rt.planName like? ");
            params.add(map.get("planName").toString());
        }
        //开始日期
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql.append(" and rt.createDate >= '" + startDate +"' ");
//            String start1Date = map.get("startDate").toString() + " 23:59:59";
//            sql.append(" and (rt.planStart >= '" + startDate +"' or rt.planStart <= '" + start1Date +"' )");
        }
        //结束时间
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql.append(" and rt.createDate <= '" + endDate +"' ");
//            String end1Date = map.get("endDate").toString() + " 23:59:59";
//            sql.append(" and (rt.planEnd >= '" + endDate +"' or rt.planEnd <= '" + end1Date +"' )");
        }
        sql.append(" order by rt.modifyDate desc ");
        if (webPage != null) {
            return this.findByPage(sql.toString(), webPage, params);
        }
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().find(sql.toString(), params.toArray());
        return list;
    }

    @Override
    public List<String> getAreaIdListByGroup(String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" sa.AREA_ID ");
        sql.append(" from st_area sa ");
        sql.append(" where  STATE='1' ");
        sql.append(" and GROUP_ID=:id ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", id);
        return (List<String>) query.list();
    }

    @Override
    public List<String> getProjectIdListByGroup(String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" sp.PROJECT_ID ");
        sql.append(" from st_project sp ");
        sql.append(" where STATE='1' ");
        sql.append(" and GROUP_ID=:id ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", id);
        return (List<String>) query.list();
    }

    @Override
    public List<String> getProjectIdListByArea(String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" sp.PROJECT_ID ");
        sql.append(" from st_project sp ");
        sql.append(" where STATE='1' ");
        sql.append(" and AREA_ID=:id ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", id);
        return (List<String>) query.list();
    }
}
