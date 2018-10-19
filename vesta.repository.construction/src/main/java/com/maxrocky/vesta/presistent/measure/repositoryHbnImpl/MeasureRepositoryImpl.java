package com.maxrocky.vesta.presistent.measure.repositoryHbnImpl;

import com.maxrocky.vesta.domain.measure.model.*;
import com.maxrocky.vesta.domain.measure.repository.MeasureRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * Created by Itzxs on 2018/7/9.
 */
@Repository
public class MeasureRepositoryImpl extends HibernateDaoImpl implements MeasureRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<MeasureEntity> getMeasures(WebPage webPage, String regionId, String cityId, String projectNum, String buildingNum, String floorCode) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from measure where state = 0 ");
        if (!StringUtil.isEmpty(regionId)) {
            sql.append(" and REGION_ID = '" + regionId + "' ");
        }
        if (!StringUtil.isEmpty(cityId)) {
            sql.append(" and CITY_ID = '" + cityId + "' ");
        }
        if (!StringUtil.isEmpty(projectNum)) {
            sql.append(" and PROJECT_NUM = '" + projectNum + "' ");
        }
        if (!StringUtil.isEmpty(buildingNum)) {
            sql.append(" and BUILDING_NUM = '" + buildingNum + "' ");
        }
        if (!StringUtil.isEmpty(floorCode)) {
            sql.append(" and FLOOR_CODE = '" + floorCode + "' ");
        }
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (webPage != null) {
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        return query.list();
    }

    @Override
    public List<Object[]> getMeasureDetailByWebPage(WebPage webPage, String projectId, String buildingId, String floorId) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT md.PROJECT_ID,md.PROJECT_NAME,md.BUILDING_ID,md.BUILDING_NAME,md.FLOOR_ID,md.FLOOR_NAME,sum(md.ACCEPTANCE_NUM) as an,sum(md.TOTAL_NUM) as tn,max(md.CREATE_DATE),md.measure_id FROM measure_detail md where md.state = '0'");
        if (!StringUtil.isEmpty(projectId) && StringUtil.isEmpty(buildingId)) {
            sql.append(" and md.PROJECT_ID = '" + projectId + "'");
        }
        if (!StringUtil.isEmpty(buildingId) && StringUtil.isEmpty(floorId)) {
            sql.append(" and md.BUILDING_ID = '" + buildingId + "'");
        }
        if (!StringUtil.isEmpty(floorId)) {
            sql.append(" and md.FLOOR_ID = '" + floorId + "'");
        }
        sql.append(" GROUP BY md.FLOOR_ID order by md.CREATE_DATE");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (webPage != null) {
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        return query.list();
    }

    @Override
    public int getMeasuresCount(String projectId, String buildingId, String floorId) {
        StringBuffer sql = new StringBuffer();
        sql.append("select * from measure_detail where state = 0 ");
        if (!StringUtil.isEmpty(projectId) && StringUtil.isEmpty(buildingId)) {
            sql.append(" and PROJECT_ID = '" + projectId + "'");
        }
        if (!StringUtil.isEmpty(buildingId) && StringUtil.isEmpty(floorId)) {
            sql.append(" and BUILDING_ID = '" + buildingId + "'");
        }
        if (!StringUtil.isEmpty(floorId)) {
            sql.append(" and FLOOR_ID = '" + floorId + "'");
        }
        sql.append(" group by FLOOR_ID");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        List<MeasureDetailEntity> list = query.list();
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
        /*BigInteger count = (BigInteger) query.uniqueResult();
        if(count != null){
            if (count.intValue() > 0) {
                return count.intValue();
            } else {
                return 0;
            }
        }else{
            return 0;
        }*/
    }

    @Override
    public List<MeasureEntity> getMeasureUnitByBuildIdAndFloorId(String buildId, String floorId){
        String hql = "from MeasureEntity where buildingId= '"+buildId+"' and floorId = '"+floorId+"' and state='0' and unitId != '' group by unitId";
        Query query = getCurrentSession().createQuery(hql);
        List<MeasureEntity> measureUnitEntities = query.list();
        return measureUnitEntities;
    }

    @Override
    public List<MeasureDataConstant> getAllMeasureDataConstant() {
        String hql = "from MeasureDataConstant where state = '0'";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<MeasureModelEntity> getAllMeasureModel() {
        String hql = "from MeasureModelEntity where state = '0'";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public void addMeasure(MeasureEntity measureEntity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(measureEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<MeasureEntity> getMeasureByBuildIdAndFloorIdAndUnitId(String buildId, String floorId, String unitName) {
        String hql = "from MeasureEntity where buildingId= '" + buildId + "' and floorId = '" + floorId + "' and state='0'";
        if (!StringUtil.isEmpty(unitName)) {
            hql = hql + " and unitName = '" + unitName + "'";
        } else {
            hql = hql + " and (unitName is null or unitName = '')";
        }
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<MeasureDetailEntity> getMeasureByBuildIdAndFloorIdAndInspectionPhaseId(String buildId, String floorId, String inspectionPhaseId, String unitName) {
        String hql = "from MeasureDetailEntity where buildingId= '" + buildId + "' and floorId = '" + floorId + "' and inspectionPhaseId = '" + inspectionPhaseId + "' and state='0'";
        if (!StringUtil.isEmpty(unitName)) {
            hql += "and (unitName is null or unitName = '' or unitName = '" + unitName + "')";
        }
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<MeasureDetailEntity> getMeasureDetailByMeasureId(String measureId) {
        String hql = "from MeasureDetailEntity where measureId= '" + measureId + "' and state='0'";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public void addMeasureDetail(MeasureDetailEntity measureDetailEntity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(measureDetailEntity);
        session.flush();
        session.close();
    }

    @Override
    public void addMeasureData(MeasureDateEntity measureDateEntity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(measureDateEntity);
        session.flush();
        session.close();
    }

    @Override
    public void addMeasureModel(MeasureModelEntity measureModelEntity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(measureModelEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<MeasureDetailEntity> getMeasureDetailByFloorId(String projectId, String buildingId, String floorId) {
        String hql = "from MeasureDetailEntity where projectId = '" + projectId + "' and buildingId='" + buildingId + "' and floorId= '" + floorId + "' and state='0'";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<MeasureModelEntity> getMeasureModelByInspectionPhase() {
        String hql = "from MeasureModelEntity where state='0' group by inspectionPhaseId";
        Query query = getCurrentSession().createQuery(hql);
        return query.list();
    }

    @Override
    public List<Object[]> getMeasureDetail(String regionId, String cityId) {
        String sql = "select sum(md.ACCEPTANCE_NUM) as an,sum(md.TOTAL_NUM) as tn ";
        //总部
        if ("1".equals(regionId) && StringUtil.isEmpty(cityId)) {
            sql += ",m.REGION_ID,m.REGION_NAME ";
        }
        //区域下的所有城市
        if (!StringUtil.isEmpty(regionId) && !"1".equals(regionId) && "1".equals(cityId)) {
            sql += ",m.city_id,m.city_name ";
        }
        //总部下的所有城市
        if ("1".equals(regionId) && "1".equals(cityId)) {
            sql += ",m.city_id,m.city_name ";
        }
        //按项目
        if (!StringUtil.isEmpty(cityId) && !"1".equals(cityId)) {
            sql += ",m.project_id,m.project_name ";
        }
        sql += "from measure_detail md left join measure m " +
                "on md.MEASURE_ID = m.id where md.state = '0' ";
        //总部
        if ("1".equals(regionId) && StringUtil.isEmpty(cityId)) {
            sql += " group by m.REGION_ID";
        }
        //区域下的所有城市
        if (!StringUtil.isEmpty(regionId) && !"1".equals(regionId) && "1".equals(cityId)) {
            sql += " and m.REGION_ID = '" + regionId + "' group by m.city_id";
        }
        //总部下的所有城市
        if ("1".equals(regionId) && "1".equals(cityId)) {
            sql += " group by m.city_id";
        }
        //按项目
        if (!StringUtil.isEmpty(cityId) && !"1".equals(cityId)) {
            sql += " and m.CITY_ID = '" + cityId + "' group by m.project_id";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getMeasureDetailByPIdAndBIdAndFId(String projectId, String buildingId, String floorId) {
        String sql = "select sum(md.ACCEPTANCE_NUM) as an,sum(md.TOTAL_NUM) as tn,md.INSPECTION_PHASE_ID " +
                " from measure_detail md where state = '0'";
        if (!StringUtil.isEmpty(projectId) && StringUtil.isEmpty(buildingId)) {
            sql += " and md.PROJECT_ID = '" + projectId + "'";
        }
        if (!StringUtil.isEmpty(projectId) && !StringUtil.isEmpty(buildingId) && StringUtil.isEmpty(floorId)) {
            sql += " and md.BUILDING_ID = '" + buildingId + "'";
        }
        if (!StringUtil.isEmpty(floorId)) {
            sql += " and md.FLOOR_ID = '" + floorId + "'";
        }
        sql += " group by md.INSPECTION_PHASE_ID";
        Query query = getCurrentSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public List<Object[]> getMeasureNumByPIdAndBidAndFid(String projectId, String buildingId, String floorId) {
        String sql = "select sum(md.ACCEPTANCE_NUM) as an,sum(md.TOTAL_NUM) as tn" +
                " from measure_detail md where md.state = '0' ";
        if (!StringUtil.isEmpty(projectId) && StringUtil.isEmpty(buildingId)) {
            sql += " and md.PROJECT_ID = '" + projectId + "' group by md.PROJECT_ID";
        }
        if (!StringUtil.isEmpty(projectId) && !StringUtil.isEmpty(buildingId) && StringUtil.isEmpty(floorId)) {
            sql += " and md.BUILDING_ID = '" + buildingId + "' group by md.BUILDING_ID";
        }
        if (!StringUtil.isEmpty(floorId)) {
            sql += " and md.FLOOR_ID = '" + floorId + "' group by md.FLOOR_ID";
        }
        Query query = getCurrentSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public MeasureEntity getMeasureById(String measureId) {
        String hql = "from MeasureEntity where state='0' and id = '" + measureId + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<MeasureEntity> list = query.list();
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Object[]> getMeasureModelDTOByFloorId(String floorId, String inspectionPhaseId, String unitId) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT mde.SERIAL_NUM,mmo.INSPECTION_PHASE_NAME,mmo.INSPECTION_CONTENT,mmo.EVALUATION_CRITERIA,mmo.CHECK_POINTS,mde.QUALIFICATION_RATE,mda.DATA FROM measure_detail mde ");
        sql.append(" LEFT JOIN measure_date mda ON mde.ID = mda.MEASURE_ID");
        sql.append(" LEFT JOIN measure_model mmo ON mde.SERIAL_NUM = mmo.SERIAL_NUM");
        sql.append(" WHERE mde.STATE = '0' AND mda.STATE = '0'");
        if (!StringUtil.isEmpty(floorId)) {
            sql.append(" AND mde.FLOOR_ID = '" + floorId + "'");
        }
        if (!StringUtil.isEmpty(inspectionPhaseId)) {
            sql.append(" AND mde.INSPECTION_PHASE_ID = '" + inspectionPhaseId + "'");
        }
        if (!StringUtil.isEmpty(unitId)) {
            sql.append(" AND mde.UNIT_ID = '" + unitId + "'");
        }else{
            sql.append(" AND(mde.UNIT_ID is null or mde.unit_id = '')");
        }
        sql.append(" order by mmo.CHECK_POINTS desc");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return query.list();
    }

    @Override
    public List<Object[]> getInspectionPhaseByFloorId(String floorId) {
        String sql = "SELECT mde.INSPECTION_PHASE_ID,mde.INSPECTION_PHASE_NAME,mde.UNIT_ID,mde.UNIT_NAME FROM measure_detail mde " +
                " WHERE mde.STATE = '0'" +
                " AND mde.FLOOR_ID = '" + floorId + "' GROUP BY mde.INSPECTION_PHASE_ID,mde.UNIT_ID ORDER BY mde.create_Date";
        Query query = getCurrentSession().createSQLQuery(sql);
        return query.list();
    }

    @Override
    public int getMeasureModelNumById(String id) {
        String sql = "SELECT count(*) FROM measure_model WHERE INSPECTION_PHASE_id = '" + id + "'";
        Query query = getCurrentSession().createSQLQuery(sql);
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count != null) {
            if (count.intValue() > 0) {
                return count.intValue();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public boolean getQrCodeByBuildingIdAndFloorId(String buildingId, String floorId) {
        StringBuilder hql = new StringBuilder(" FROM MeasureQrCodeEntity ");
        hql.append(" WHERE buildingId=:buildingId AND floorId=:floorId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("buildingId", buildingId);
        query.setParameter("floorId", floorId);
        List<MeasureQrCodeEntity> list = query.list();
        if (null != list && list.size() > 0 && !StringUtil.isEmpty(list.get(0).getQrCodeUrl())) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<MeasureQrCodeEntity> getQrCodeByBuildingId(String buildingId, String floorId) {
        StringBuilder hql = new StringBuilder(" FROM MeasureQrCodeEntity ");
        hql.append(" WHERE buildingId=:buildingId AND floorId=:floorId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("buildingId", buildingId);
        query.setParameter("floorId", floorId);
        List<MeasureQrCodeEntity> list = query.list();
        return list;
    }

    @Override
    public void saveMeasureQrCodeEntity(MeasureQrCodeEntity measureQrCodeEntity) {
        Session session = this.getCurrentSession();
        session.save(measureQrCodeEntity);
        session.flush();
        session.close();
    }

    @Override
    public void updateQrCode(String userId, String state) {
        String sql = "UPDATE measure_isopen_qrcode SET ISOPEN_QRCODE = '" + state + "',CREATE_BY = '" + userId + "',MODIFY_DATE=NOW()";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.executeUpdate();
    }

    @Override
    public String getQrCodeState() {
        String sql = "select ISOPEN_QRCODE from measure_isopen_qrcode";
        Query query = getCurrentSession().createSQLQuery(sql);
        return (String) query.uniqueResult();
    }

    @Override
    public List<String> getUnit(String projectId, String buildingId, String floorId, String inspectionPhaseId) {
        String sql = "SELECT md.UNIT_ID from measure_detail md WHERE " +
                "md.STATE = '0' AND md.PROJECT_ID = '" + projectId + "' " +
                "AND md.BUILDING_ID = '" + buildingId + "' " +
                "AND md.FLOOR_ID = '" + floorId + "' " +
                "AND md.INSPECTION_PHASE_ID = '" + inspectionPhaseId + "' " +
                "AND md.UNIT_ID is not NULL " +
                "AND md.UNIT_ID != ''";
        Query query = getCurrentSession().createSQLQuery(sql);
        return query.list();
    }

    public List<Object[]> getScoreByAgencyId(String level) {
        if ("3".equals(level)) {
            level = "mea.PROJECT_ID,"; //级别是3 为项目级别
        } else if ("2".equals(level)) {
            level = "mea.CITY_ID,";//级别是2 为城市级别
        } else if ("1".equals(level)) {
            level = "mea.REGION_ID,";//级别是1 为区域级别
        } else {
            return null;
        }
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql.append(level);
        sql.append(" mead.INSPECTION_PHASE_ID, SUM(mead.ACCEPTANCE_NUM), SUM(mead.TOTAL_NUM) ");
        sql.append(" FROM measure mea ");
        sql.append(" LEFT JOIN measure_detail mead ON mea.ID=mead.MEASURE_ID WHERE mead.INSPECTION_PHASE_ID IS NOT NULL ");
        sql.append(" GROUP BY ").append(level).append(" mead.INSPECTION_PHASE_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return query.list();
    }

    @Override
    public List<Object[]> getContentScoreByAgencyId(String level) {
        if ("3".equals(level)) {
            level = "mea.PROJECT_ID,"; //级别是3 为项目级别
        } else if ("2".equals(level)) {
            level = "mea.CITY_ID,";//级别是2 为城市级别
        } else if ("1".equals(level)) {
            level = "mea.REGION_ID,";//级别是1 为区域级别
        } else {
            return null;
        }
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql.append(level);
        sql.append(" mead.INSPECTION_CONTENT_ID, SUM(mead.ACCEPTANCE_NUM), SUM(mead.TOTAL_NUM) ");
        sql.append(" FROM measure mea ");
        sql.append(" LEFT JOIN measure_detail mead ON mea.ID=mead.MEASURE_ID WHERE mead.INSPECTION_CONTENT_ID IS NOT NULL ");
        sql.append(" GROUP BY ").append(level).append(" mead.INSPECTION_CONTENT_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return query.list();
    }

    @Override
    public List<Object[]> getAgency(String level) {
        String name = "";
        if ("3".equals(level)) {
            level = " mea.PROJECT_ID "; //级别是3 为项目级别
            name = " ,mea.PROJECT_NAME ";
        } else if ("2".equals(level)) {
            level = " mea.CITY_ID ";//级别是2 为城市级别
            name = " ,mea.CITY_NAME ";
        } else if ("1".equals(level)) {
            level = " mea.REGION_ID ";//级别是1 为区域级别
            name = " ,mea.REGION_NAME ";
        } else {
            return null;
        }
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql.append(level).append(name);
        sql.append(" FROM measure mea ");
        sql.append(" LEFT JOIN measure_detail mead ON mea.ID = mead.MEASURE_ID WHERE mead.INSPECTION_PHASE_ID IS NOT NULL ");
        sql.append(" GROUP BY ").append(level);
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return query.list();
    }

    @Override
    public void saveMeasureCount(MeasureCountEntity measureCountEntity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(measureCountEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<Object[]> getQuantityByFloorId(String floorId) {
        StringBuilder sql = new StringBuilder(" SELECT mm.INSPECTION_PHASE_NAME,mmd.UNIT_NAME,mm.INSPECTION_CONTENT,mm.EVALUATION_CRITERIA,mm.CHECK_POINTS,mmd.QUALIFICATION_RATE,md.`DATA` ");
        sql.append(" FROM measure_detail mmd ");
        sql.append(" LEFT JOIN measure_date md ON mmd.ID = md.MEASURE_ID ");
        sql.append(" LEFT JOIN measure_model mm ON mmd.INSPECTION_CONTENT_ID = mm.INSPECTION_CONTENT_ID ");
        sql.append(" WHERE mmd.FLOOR_ID=:floorId AND md.`DATA` IS NOT NULL ");
        sql.append(" ORDER BY mmd.INSPECTION_PHASE_ID,mmd.UNIT_NAME ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("floorId", floorId);
        return query.list();
    }

    @Override
    public List<Object[]> getFloorByBuildingId(String buildingId) {
        StringBuilder sql = new StringBuilder(" SELECT md.FLOOR_ID,md.FLOOR_NAME, SUM(md.ACCEPTANCE_NUM), SUM(md.TOTAL_NUM) ");
        sql.append(" FROM measure_detail md ");
        sql.append(" WHERE md.BUILDING_ID=:buildingId AND md.FLOOR_ID IS NOT NULL  ");
        sql.append(" GROUP BY md.FLOOR_ID ");
        sql.append(" ORDER BY -md.FLOOR_NAME DESC ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("buildingId", buildingId);
        return query.list();
    }

    @Override
    public List<Object[]> getBuildingByProjectId(String projectId) {
        StringBuilder sql = new StringBuilder(" SELECT md.BUILDING_ID,md.BUILDING_NAME, SUM(md.ACCEPTANCE_NUM), SUM(md.TOTAL_NUM) ");
        sql.append(" FROM measure_detail md ");
        sql.append(" WHERE md.PROJECT_ID=:projectId AND md.BUILDING_ID IS NOT NULL  ");
        sql.append(" GROUP BY md.BUILDING_ID ");
        sql.append(" ORDER BY -md.BUILDING_NAME DESC ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("projectId", projectId);
        return query.list();
    }

    @Override
    public List<Object[]> getProjectByCityId(String cityId) {
        StringBuilder sql = new StringBuilder(" SELECT md.PROJECT_ID,md.PROJECT_NAME,SUM(md.ACCEPTANCE_NUM), SUM(md.TOTAL_NUM) ");
        sql.append(" FROM measure_detail md ");
        sql.append(" LEFT JOIN measure m ON md.MEASURE_ID = m.ID ");
        sql.append(" WHERE m.CITY_ID=:cityId AND md.PROJECT_ID IS NOT NULL  ");
        sql.append(" GROUP BY md.PROJECT_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("cityId", cityId);
        return query.list();
    }

    @Override
    public List<Object[]> getCityByRegionId(String regionId) {
        StringBuilder sql = new StringBuilder(" SELECT m.CITY_ID,m.CITY_NAME, SUM(md.ACCEPTANCE_NUM), SUM(md.TOTAL_NUM) ");
        sql.append(" FROM measure_detail md ");
        sql.append(" LEFT JOIN measure m ON md.MEASURE_ID = m.ID ");
        sql.append(" WHERE m.REGION_ID=:regionId AND m.CITY_ID IS NOT NULL  ");
        sql.append(" GROUP BY m.CITY_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("regionId", regionId);
        return query.list();
    }

    @Override
    public List<Object[]> getRegionByGroupId() {
        StringBuilder sql = new StringBuilder(" SELECT m.REGION_ID,m.REGION_NAME, SUM(md.ACCEPTANCE_NUM), SUM(md.TOTAL_NUM) ");
        sql.append(" FROM measure_detail md ");
        sql.append(" LEFT JOIN measure m ON md.MEASURE_ID = m.ID ");
        sql.append(" WHERE m.REGION_ID IS NOT NULL  ");
        sql.append(" GROUP BY m.REGION_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return query.list();
    }

    @Override
    public List<Object[]> getSubitemScoreByBuildingId(String buildingId) {
        StringBuilder sql = new StringBuilder(" SELECT md.FLOOR_ID,md.INSPECTION_PHASE_ID,sum(md.ACCEPTANCE_NUM),sum(md.TOTAL_NUM) ");
        sql.append(" FROM measure_detail md ");
        sql.append(" WHERE md.BUILDING_ID=:buildingId AND md.FLOOR_ID IS NOT NULL ");
        sql.append(" GROUP BY md.FLOOR_ID,md.INSPECTION_PHASE_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("buildingId", buildingId);
        return query.list();
    }

    @Override
    public List<Object[]> getSubitemScoreByProjectId(String projectId) {
        StringBuilder sql = new StringBuilder(" SELECT md.BUILDING_ID,md.INSPECTION_PHASE_ID,sum(md.ACCEPTANCE_NUM),sum(md.TOTAL_NUM) ");
        sql.append(" FROM measure_detail md ");
        sql.append(" WHERE md.PROJECT_ID=:projectId AND md.BUILDING_ID IS NOT NULL ");
        sql.append(" GROUP BY md.BUILDING_ID,md.INSPECTION_PHASE_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("projectId", projectId);
        return query.list();
    }

    @Override
    public List<Object[]> getSubitemScoreByCityId(String cityId) {
        StringBuilder sql = new StringBuilder(" SELECT md.PROJECT_ID,md.INSPECTION_PHASE_ID,sum(md.ACCEPTANCE_NUM),sum(md.TOTAL_NUM) ");
        sql.append(" FROM measure_detail md ");
        sql.append(" LEFT JOIN measure m ON md.MEASURE_ID = m.ID ");
        sql.append(" WHERE m.CITY_ID=:cityId AND md.PROJECT_ID IS NOT NULL ");
        sql.append(" GROUP BY md.PROJECT_ID,md.INSPECTION_PHASE_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("cityId", cityId);
        return query.list();
    }

    @Override
    public List<Object[]> getSubitemScoreByRegionId(String regionId) {
        StringBuilder sql = new StringBuilder(" SELECT m.CITY_ID,md.INSPECTION_PHASE_ID,sum(md.ACCEPTANCE_NUM),sum(md.TOTAL_NUM) ");
        sql.append(" FROM measure_detail md ");
        sql.append(" LEFT JOIN measure m ON md.MEASURE_ID = m.ID ");
        sql.append(" WHERE m.REGION_ID=:regionId AND m.CITY_ID IS NOT NULL ");
        sql.append(" GROUP BY m.CITY_ID,md.INSPECTION_PHASE_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("regionId", regionId);
        return query.list();
    }

    @Override
    public List<Object[]> getSubitemScoreByGroupId() {
        StringBuilder sql = new StringBuilder(" SELECT m.REGION_ID,md.INSPECTION_PHASE_ID,sum(md.ACCEPTANCE_NUM),sum(md.TOTAL_NUM) ");
        sql.append(" FROM measure_detail md ");
        sql.append(" LEFT JOIN measure m ON md.MEASURE_ID = m.ID ");
        sql.append(" WHERE m.REGION_ID IS NOT NULL ");
        sql.append(" GROUP BY m.REGION_ID,md.INSPECTION_PHASE_ID ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return query.list();
    }

    @Override
    public List<String> getMeasureSubitemModel() {
        StringBuilder sql = new StringBuilder(" SELECT DISTINCT mm.INSPECTION_PHASE_ID ");
        sql.append(" FROM measure_model mm ");
        sql.append(" ORDER BY -mm.SERIAL_NUM DESC ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return query.list();
    }

    @Override
    public List<Object[]> getMeasureCountModel() {
        StringBuilder sql = new StringBuilder(" SELECT mm.INSPECTION_PHASE_ID,mm.INSPECTION_CONTENT_ID ");
        sql.append(" FROM measure_model mm ");
        sql.append(" ORDER BY -mm.SERIAL_NUM DESC ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return query.list();
    }

    @Override
    public String getAgencyNameByAgencyId(String id) {
        StringBuilder sql = new StringBuilder(" SELECT AGENCY_NAME FROM auth_role_agencyes ");
        sql.append(" WHERE AGENCY_ID=:id AND STATUS='1' ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id",id);
        return (String)query.uniqueResult();
    }

    @Override
    public List<MeasureCountEntity> getMeasureCount(List<String> ids) {
        StringBuilder hql = new StringBuilder(" FROM MeasureCountEntity ");
        hql.append(" WHERE agencyId in(:ids) ");
        hql.append(" ORDER BY level ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameterList("ids",ids);
        return query.list();
    }

    @Override
    public List<Object[]> getAgency(List<String> ids) {
        StringBuilder sql = new StringBuilder(" SELECT distinct m.PROJECT_ID,m.PROJECT_NAME,m.CITY_ID,m.CITY_NAME,m.REGION_ID,m.REGION_NAME ");
        sql.append(" FROM measure m ");
        sql.append(" WHERE m.PROJECT_ID in(:ids) OR m.CITY_ID in(:ids) OR m.REGION_ID in(:ids) ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameterList("ids",ids);
        return query.list();
    }
}
