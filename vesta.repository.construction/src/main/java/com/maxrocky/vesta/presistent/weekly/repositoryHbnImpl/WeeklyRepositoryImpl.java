package com.maxrocky.vesta.presistent.weekly.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;
import com.maxrocky.vesta.domain.weekly.repository.WeeklyRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Itzxs on 2018/4/8.
 */
@Repository
public class WeeklyRepositoryImpl extends HibernateDaoImpl implements WeeklyRepository{

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<Object[]> getInspectAcceptanceCount(Map map){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.DATE, -14);//completeOn-两周
        String completeOn = sdf.format(ca.getTime()).toString()+ " 23:59:59";

        String sql = "select count(CASE " +
                "WHEN (pe.state = '合格' or pe.state = '整改中')";
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " and pe.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " and pe.create_on <= '" + endDate + "' ";
        }
        sql+= " THEN pe.state END) sum,pe.project_num,pe.project_name," +
                "count(CASE " +
                "WHEN pe.state = '合格'" ;
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " AND pe.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " AND pe.create_on <= '" + endDate + "' ";
        }
        sql+="THEN pe.state END) 'upToStandard'," +
                "count(CASE WHEN pe.state = '整改中' "+
                "AND pe.complete_on <= '" + completeOn + "' "+
                "THEN pe.state END) 'overTwoWeek' " +
                "FROM project_examine pe where 1=1    ";
        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and pe.project_num in (:projectIds) ";
        }else{
            sql += " and pe.project_num = ''";
        }
        sql+= "GROUP BY pe.project_num";
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projectIds", li);
        }
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public int getInspectAcceptanceCountState(Map map){
        String sql = "select count(*) from project_examine iae where iae.state = '合格'";
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " and iae.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " and iae.create_on <= '" + endDate + "' ";
        }
        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and iae.project_num in (:projiectNums) ";
        }else{
            sql += " and iae.project_num = ''";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projiectNums", li);
        }
        int count =  Integer.parseInt(query.uniqueResult().toString());
        return count;
    }

    @Override
    public int getOverTwoWeekNum(Map map){
        String sql = "select count(*) from project_examine iae where iae.state = '整改中' ";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.DATE, -14);//completeOn-两周
        String completeOn = sdf.format(ca.getTime()).toString()+ " 23:59:59";
        sql += " and iae.complete_on <= '" + completeOn + "' ";

        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and iae.project_num in (:projiectNums) ";
        }else{
            sql += " and iae.project_num = ''";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projiectNums", li);
        }
        int count =  Integer.parseInt(query.uniqueResult().toString());
        return count;
    }

    @Override
    public List<Object[]> getDailyPatrolInspectionCount(Map map){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.DATE, -14);//completeOn-两周
        String completeOn = sdf.format(ca.getTime()).toString()+ " 23:59:59";

        String sql = "select count(CASE " +
                "WHEN (pi.state = '完成' or pi.state = '整改中')";
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " and pi.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " and pi.create_on <= '" + endDate + "' ";
        }
        sql+= " THEN pi.state END) sum,pi.project_id,pi.PROJECT_NUM," +
                "count(CASE " +
                "WHEN pi.state = '完成'" ;
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " AND pi.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " AND pi.create_on <= '" + endDate + "' ";
        }
        sql+="THEN pi.state END) 'upToStandard'," +
                "count(CASE WHEN pi.state = '整改中' "+
                "AND pi.rectification_period <= '" + completeOn + "' "+
                "THEN pi.state END) 'overTwoWeek', " +
                "count(CASE WHEN (pi.state = '完成' or pi.state = '整改中') " +
                "AND pi.first_party is not null ";
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " AND pi.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " AND pi.create_on <= '" + endDate + "' ";
        }
        sql+= "THEN pi.first_party END)'partyFirstNum' "+
                "FROM project_inspection pi where 1=1    ";
        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and pi.project_id in (:projectIds) ";
        }else{
            sql += " and pi.project_id = ''";
        }
        sql+= "GROUP BY pi.project_id";
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projectIds", li);
        }
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public int getDailyPatrolInspectionState(Map map){
        String sql = "select count(*) from project_inspection pi where pi.state = '完成'";
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " and pi.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " and pi.create_on <= '" + endDate + "' ";
        }
        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and pi.project_id in (:projectIds) ";
        }else{
            sql += " and pi.project_id = ''";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projectIds", li);
        }
        int count =  Integer.parseInt(query.uniqueResult().toString());
        return count;
    }

    @Override
    public int getDailyPatrolInspectionOverTwoWeekNum(Map map){
        String sql = "select count(*) from project_inspection pi where pi.state = '整改中' ";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.DATE, -14);//completeOn-两周
        String completeOn = sdf.format(ca.getTime()).toString()+ " 23:59:59";
        sql += " and pi.rectification_period <= '" + completeOn + "' ";

        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and pi.project_id in (:projectIds) ";
        }else{
            sql += " and pi.project_id = ''";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projectIds", li);
        }
        int count =  Integer.parseInt(query.uniqueResult().toString());
        return count;
    }

    @Override
    public List<Object[]> getProjectMaterialCount(Map map){
        String sql = "select count(CASE " +
                "WHEN (pm.state = '合格' or pm.state = '不合格' or pm.state = '已退场')";
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " and pm.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " and pm.create_on <= '" + endDate + "' ";
        }
        sql+= " THEN pm.state END) sum,pm.project_id,pm.project_name," +
                "count(CASE " +
                "WHEN pm.state = '合格'" ;
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " AND pm.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " AND pm.create_on <= '" + endDate + "' ";
        }
        sql+="THEN pm.state END) 'upToStandard', " +
                "count(CASE WHEN (pm.state = '合格' or pm.state = '不合格' or pm.state = '已退场') " +
                "AND pm.first_party is not null ";
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " AND pm.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " AND pm.create_on <= '" + endDate + "' ";
        }
        sql+= "THEN pm.first_party END)'partyFirstNum' "+
                "FROM project_material pm where 1=1    ";
        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and pm.project_id in (:projectIds) ";
        }else{
            sql += " and pm.project_id = ''";
        }
        sql+= "GROUP BY pm.project_id";
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projectIds", li);
        }
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public int getProjectMaterialState(Map map){
        String sql = "select count(*) from project_material pm where pm.state = '合格'";
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " and pm.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " and pm.create_on <= '" + endDate + "' ";
        }
        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and pm.project_id in (:projectIds) ";
        }else{
            sql += " and pm.project_id = ''";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projectIds", li);
        }
        int count =  Integer.parseInt(query.uniqueResult().toString());
        return count;
    }

    @Override
    public List<Object[]> getProjectSampleCheckCount(Map map){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.DATE, -14);//completeOn-两周
        String completeOn = sdf.format(ca.getTime()).toString()+ " 23:59:59";

        String sql = "select count(CASE " +
                "WHEN (psc.state = '合格' or psc.state = '不合格')";
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " and psc.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " and psc.create_on <= '" + endDate + "' ";
        }
        sql+= " THEN psc.state END) sum,psc.project_id,psc.project_name," +
                "count(CASE " +
                "WHEN psc.state = '合格'" ;
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " AND psc.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " AND psc.create_on <= '" + endDate + "' ";
        }
        sql+="THEN psc.state END) 'upToStandard'," +
                "count(CASE WHEN psc.state = '整改中' "+
                "AND psc.rectificationperiod <= '" + completeOn + "' "+
                "THEN psc.state END) 'overTwoWeek' " +
                "FROM project_sample_check psc where 1=1    ";
        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and psc.project_id in (:projectIds) ";
        }else{
            sql += " and psc.project_id = ''";
        }
        sql+= "GROUP BY psc.project_id";
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projectIds", li);
        }
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public int getProjectSampleCheckState(Map map){
        String sql = "select count(*) from project_sample_check psc where psc.state = '合格'";
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " and psc.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " and psc.create_on <= '" + endDate + "' ";
        }
        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and psc.project_id in (:projectIds) ";
        }else{
            sql += " and psc.project_id = ''";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projectIds", li);
        }
        int count =  Integer.parseInt(query.uniqueResult().toString());
        return count;
    }

    @Override
    public int getProjectSampleCheckOverTwoWeekNum(Map map){
        String sql = "select count(*) from project_sample_check psc where psc.state = '整改中' ";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.DATE, -14);//completeOn-两周
        String completeOn = sdf.format(ca.getTime()).toString()+ " 23:59:59";
        sql += " and psc.rectificationperiod <= '" + completeOn + "' ";

        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and psc.project_id in (:projectIds) ";
        }else{
            sql += " and psc.project_id = ''";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projectIds", li);
        }
        int count =  Integer.parseInt(query.uniqueResult().toString());
        return count;
    }

    @Override
    public List<Object[]> getProjectKeyProcessesCount(Map map){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.DATE, -14);//completeOn-两周
        String completeOn = sdf.format(ca.getTime()).toString()+ " 23:59:59";

        String sql = "select count(CASE " +
                "WHEN (pkp.state = '合格' or pkp.state = '不合格' or pkp.state = '整改中')";
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " and pkp.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " and pkp.create_on <= '" + endDate + "' ";
        }
        sql+= " THEN pkp.state END) sum,pkp.project_id,pkp.project_name," +
                "count(CASE " +
                "WHEN pkp.state = '合格'" ;
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " AND pkp.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " AND pkp.create_on <= '" + endDate + "' ";
        }
        sql+="THEN pkp.state END) 'upToStandard'," +
                "count(CASE WHEN pkp.state = '整改中' "+
                "AND pkp.complete_on <= '" + completeOn + "' "+
                "THEN pkp.state END) 'overTwoWeek' " +
                "FROM project_key_processes pkp where 1=1    ";
        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and pkp.project_id in (:projectIds) ";
        }else{
            sql += " and pkp.project_id = ''";
        }
        sql+= "GROUP BY pkp.project_id";
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projectIds", li);
        }
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public int getProjectKeyProcessesState(Map map){
        String sql = "select count(*) from project_key_processes pkp where pkp.state = '合格'";
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " and pkp.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " and pkp.create_on <= '" + endDate + "' ";
        }
        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and pkp.project_id in (:projectIds) ";
        }else{
            sql += " and pkp.project_id = ''";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projectIds", li);
        }
        int count =  Integer.parseInt(query.uniqueResult().toString());
        return count;
    }

    @Override
    public int getProjectKeyProcessesOverTwoWeekNum(Map map){
        String sql = "select count(*) from project_key_processes pkp where pkp.state = '整改中' ";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        ca.add(Calendar.DATE, -14);//completeOn-两周
        String completeOn = sdf.format(ca.getTime()).toString()+ " 23:59:59";
        sql += " and pkp.complete_on <= '" + completeOn + "' ";

        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and pkp.project_id in (:projectIds) ";
        }else{
            sql += " and pkp.project_id = ''";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projectIds", li);
        }
        int count =  Integer.parseInt(query.uniqueResult().toString());
        return count;
    }

    @Override
    public int getProjectSideStationCount(Map map){
        String sql = "select count(*) from project_side_station pss where 1=1    ";
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " and pss.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " and pss.create_on <= '" + endDate + "' ";
        }
        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and pss.project_id in (:projectIds) ";
        }else{
            sql += " and pss.project_id = ''";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projectIds", li);
        }
        int count =  Integer.parseInt(query.uniqueResult().toString());
        return count;
    }

    @Override
    public int getProjectLeadersCheckCount(Map map){
        String sql = "select count(*) from project_leaders_check plc where 1=1    ";
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql += " and plc.create_on >= '" + startDate + "' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql += " and plc.create_on <= '" + endDate + "' ";
        }
        List li = new ArrayList();
        if (map.get("projectIds") != null) {
            li = (List)map.get("projectIds");
            sql += " and plc.project_id in (:projectIds) ";
        }else{
            sql += " and plc.project_id = ''";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        if (map.get("projectIds") != null) {
            query.setParameterList("projectIds", li);
        }
        int count =  Integer.parseInt(query.uniqueResult().toString());
        return count;
    }

    @Override
    public List<AuthAgencyESEntity> getESAllAgencyListByParentId(List<String> parentIdList,List<String> agencyIdList,String type){
        String hql = "from AuthAgencyESEntity where status = '1' and agencyType=:type1 ";
        if (parentIdList != null && parentIdList.size() > 0) {
            hql += " and parentId in(:parentIdList) ";
        }
        if (agencyIdList != null && agencyIdList.size() > 0) {
            hql += " or  agencyId in(:agencyIdList) ";
        }
        hql += "group by agencyId order by agencyType,orderNum ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("type1", type);
        if (parentIdList != null && parentIdList.size() > 0) {
            query.setParameterList("parentIdList", parentIdList);
        }
        if (agencyIdList != null && agencyIdList.size() > 0) {
            query.setParameterList("agencyIdList", agencyIdList);
        }
        List<AuthAgencyESEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<AuthAgencyESEntity> getVirtualAreaProject(){
        String hql = "from AuthAgencyESEntity where status = '1' and agencyPath like ('%07bad26e90564ffcbe55985d51ed3a06%')";
        hql += "group by agencyId order by agencyType,orderNum ";
        Query query = getCurrentSession().createQuery(hql);
        List<AuthAgencyESEntity> agencyEntities = query.list();
        return agencyEntities;
    }

    @Override
    public List<String> getVirtualAreaProjectId(){
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" agency_id ");
        sql.append(" from auth_role_agencyes ");
        sql.append(" where status = '1' and AGENCY_PATH like ('%07bad26e90564ffcbe55985d51ed3a06%') ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        List<String> list = query.list();
        return list;
    }
}