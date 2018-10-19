package com.maxrocky.vesta.presistent.statisticsWeekly.repositoryHbnImpl;

import com.maxrocky.vesta.domain.StatisticsAndWeekly.model.StatisticsWeeklyEntity;
import com.maxrocky.vesta.domain.StatisticsAndWeekly.repository.StatisticsWeeklyRepository;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.DailyPatrolInspectionEntity;
import com.maxrocky.vesta.domain.inspectAcceptance.model.ProjectExamineEntity;
import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.model.ProjectKeyProcessesEntity;
import com.maxrocky.vesta.domain.projectLeadersCheck.model.ProjectLeadersCheckEntity;
import com.maxrocky.vesta.domain.projectMaterial.model.ProjectMaterialEntity;
import com.maxrocky.vesta.domain.projectSampleCheck.model.ProjectSampleCheckEntity;
import com.maxrocky.vesta.domain.projectSideStation.model.ProjectSideStationEntity;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yuanyn on 2018/4/10.
 * 定时周报与统计Repository实现类
 */
@Repository
public class StatisticsWeeklyRepositoryImpl implements StatisticsWeeklyRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    @Override
    public List<AuthAgencyESEntity> getProject(String projectOrAll) {
        StringBuilder hql = new StringBuilder(" FROM AuthAgencyESEntity ");
        hql.append(" WHERE status='1' ");
        if(null != projectOrAll && "project".equals(projectOrAll)){
            hql.append(" AND agencyType='100000002' ");
        }
        Query query = getCurrentSession().createQuery(hql.toString());
        List<AuthAgencyESEntity> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getDailyByTime(String startTime, String endTime, String extendedTime) {
        StringBuilder sql = new StringBuilder(" SELECT pi.PROJECT_ID, ");//项目id
        sql.append(" COUNT(CASE WHEN (pi.STATE = '完成' OR pi.STATE = '整改中') AND pi.CREATE_ON >:startTime AND pi.CREATE_ON <:endTime THEN pi.STATE END ) AS inspectionAll, " );//统计总数
        sql.append(" COUNT(CASE WHEN pi.FIRST_PARTY IS NOT NULL AND (pi.STATE = '完成' OR pi.STATE = '整改中') AND pi.CREATE_ON >:startTime AND pi.CREATE_ON <:endTime THEN pi.FIRST_PARTY END ) AS ownerCreate, ");//统计甲方创建的数据
        sql.append(" COUNT(CASE WHEN pi.STATE = '完成' AND pi.CREATE_ON >:startTime AND pi.CREATE_ON <:endTime THEN pi.STATE END ) AS qualified, ");//统计合格数
        sql.append(" COUNT(CASE WHEN pi.RECTIFICATION_PERIOD<:extendedTime AND pi.STATE='整改中' THEN pi.STATE END) AS extended ");//超期两周未整改统计
        sql.append(" FROM project_inspection pi GROUP BY pi.PROJECT_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("startTime",startTime);
        query.setParameter("endTime",endTime);
        query.setParameter("extendedTime",extendedTime);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getAcceptanceByTime(String startTime, String endTime, String extendedTime) {
        StringBuilder sql = new StringBuilder(" SELECT pe.PROJECT_NUM, ");//项目id
        sql.append(" COUNT(CASE WHEN (pe.STATE = '合格' OR pe.STATE = '整改中') AND pe.CREATE_ON >:startTime AND pe.CREATE_ON <:endTime THEN pe.STATE END ) AS inspectionAll, " );//统计总数
        sql.append(" COUNT(CASE WHEN  pe.STATE = '合格' AND pe.CREATE_ON >:startTime AND pe.CREATE_ON <:endTime THEN pe.STATE END ) AS qualified, " );//统计合格数
        sql.append(" COUNT(CASE WHEN  pe.COMPLETE_ON<:extendedTime AND pe.STATE='整改中' THEN pe.STATE END ) AS extended " );//统计超期
        sql.append(" FROM project_examine pe GROUP BY pe.PROJECT_NUM ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("startTime",startTime);
        query.setParameter("endTime",endTime);
        query.setParameter("extendedTime",extendedTime);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getMaterialByTime(String startTime, String endTime) {
        StringBuilder sql = new StringBuilder(" SELECT pm.PROJECT_ID, ");//项目id
        sql.append(" COUNT(CASE WHEN (pm.STATE = '合格' OR pm.STATE = '不合格' OR pm.STATE='已退场') AND pm.CREATE_ON >:startTime AND pm.CREATE_ON <:endTime THEN pm.STATE END) AS inspectionAll, " );//统计总数
        sql.append(" COUNT(CASE WHEN pm.FIRST_PARTY IS NOT NULL AND (pm.STATE = '合格' OR pm.STATE = '不合格' OR pm.STATE='已退场') AND pm.CREATE_ON >:startTime AND pm.CREATE_ON <:endTime THEN pm.FIRST_PARTY END ) AS ownerCreate, " );//甲方创建
        sql.append(" COUNT(CASE WHEN pm.STATE = '合格' AND pm.CREATE_ON >:startTime AND pm.CREATE_ON <:endTime THEN pm.STATE END ) AS qualified " );//合格
        sql.append(" FROM project_material pm GROUP BY pm.PROJECT_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("startTime",startTime);
        query.setParameter("endTime",endTime);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getSampleCheckByTime(String startTime, String endTime, String extendedTime) {
        StringBuilder sql = new StringBuilder(" SELECT psc.PROJECT_ID, ");//项目id
        sql.append(" COUNT(CASE WHEN (psc.STATE = '合格' OR psc.STATE = '不合格') AND psc.CREATE_ON >:startTime AND psc.CREATE_ON <:endTime THEN psc.STATE END) AS inspectionAll, ");//统计总数
        sql.append(" COUNT(CASE WHEN psc.STATE = '合格' AND psc.CREATE_ON >:startTime AND psc.CREATE_ON <:endTime THEN psc.STATE END) AS qualified, ");//合格数
        sql.append(" COUNT(CASE WHEN psc.RECTIFICATIONPERIOD<:extendedTime AND psc.STATE='不合格' THEN psc.state END) AS extended ");//超期数
        sql.append(" FROM project_sample_check psc GROUP BY psc.PROJECT_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("startTime",startTime);
        query.setParameter("endTime",endTime);
        query.setParameter("extendedTime",extendedTime);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getKeyProcessesByTime(String startTime, String endTime, String extendedTime) {
        StringBuilder sql = new StringBuilder(" SELECT pkp.PROJECT_ID, ");//项目id
        sql.append(" COUNT(CASE WHEN (pkp.STATE = '合格' OR pkp.STATE = '整改中') AND pkp.CREATE_ON >:startTime AND pkp.CREATE_ON <:endTime THEN pkp.STATE END) AS inspectionAll, ");//统计总数
        sql.append(" COUNT(CASE WHEN pkp.STATE = '合格' AND pkp.CREATE_ON >:startTime AND pkp.CREATE_ON <:endTime THEN pkp.STATE END) AS qualified, ");//合格数
        sql.append(" COUNT(CASE WHEN pkp.COMPLETE_ON<:extendedTime AND pkp.STATE='整改中' THEN pkp.STATE END) AS extended ");//超期数
        sql.append(" FROM project_key_processes pkp GROUP BY pkp.PROJECT_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("startTime",startTime);
        query.setParameter("endTime",endTime);
        query.setParameter("extendedTime",extendedTime);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getSideStationByTime(String startTime, String endTime) {
        StringBuilder sql = new StringBuilder(" SELECT pss.PROJECT_ID, ");//项目id
        sql.append(" COUNT(1) AS inspectionAll FROM project_side_station pss  ");//总数
        sql.append(" WHERE pss.CREATE_ON >:startTime AND pss.CREATE_ON <:endTime ");
        sql.append(" GROUP BY pss.PROJECT_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("startTime",startTime);
        query.setParameter("endTime",endTime);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getLeadInspectByTime(String startTime, String endTime) {
        StringBuilder sql = new StringBuilder(" SELECT plc.PROJECT_ID, ");//项目id
        sql.append(" COUNT(1) AS inspectionAll FROM project_leaders_check plc  ");//总数
        sql.append(" WHERE plc.CREATE_DATE>:startTime AND plc.CREATE_DATE<:endTime ");
        sql.append(" GROUP BY plc.PROJECT_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("startTime",startTime);
        query.setParameter("endTime",endTime);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public void saveStatisticsWeeklyEntity(StatisticsWeeklyEntity statisticsWeeklyEntity) {
        Session session = this.getCurrentSession();
        session.save(statisticsWeeklyEntity);
        session.flush();
        session.close();
    }

    @Override
    public void delStatisticsWeeklyEntity(String type) {
        StringBuilder hql = new StringBuilder(" DELETE FROM StatisticsWeeklyEntity ");
        hql.append(" WHERE type=:type AND (countWeek='1' OR countStatistics='1') ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("type",type);
        int num = query.executeUpdate();
        if(num>0){
            StringBuilder hql1 = new StringBuilder(" UPDATE StatisticsWeeklyEntity ");
            if("1".equals(type)){
                hql1.append(" SET countWeek=countWeek-'1' WHERE type='1' ");
            }else {
                hql1.append(" SET countStatistics=countStatistics-'1' WHERE type='2' ");
            }
            Query query1 = getCurrentSession().createQuery(hql1.toString());
            query1.executeUpdate();
        }
    }
}
