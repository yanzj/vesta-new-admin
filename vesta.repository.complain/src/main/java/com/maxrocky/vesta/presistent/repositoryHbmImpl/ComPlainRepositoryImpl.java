package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.ComplainEntity;
import com.maxrocky.vesta.domain.model.PropertyImageEntity;
import com.maxrocky.vesta.domain.repository.ComPlainRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/7/19.
 */
@Repository
public class ComPlainRepositoryImpl extends HibernateDaoImpl implements ComPlainRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public ComplainEntity getComplainEntity(String id) {
        String hql = "FROM ComplainEntity WHERE complainName='" + id + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<ComplainEntity> userList = query.list();
        if (userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public void saveComplain(ComplainEntity complainEntity) {
        Session session = getCurrentSession();
        session.save(complainEntity);
        session.flush();
        session.close();
    }

    @Override
    public void updateComplain(ComplainEntity complainEntity) {
        Session session = getCurrentSession();
        session.update(complainEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<Object[]> getComplainList(Map map, WebPage webPage) {
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append("qc.complain_id,hc.CITY_NAME,hp.`NAME`,hhi.ADDRESS,qc.comlaints_des,qc.document_state,qc.CREATE_BY," +
                "qc.portal_complain_person_name,qc.limited_reply_time,qc.submit_time");
        sql.append(" FROM qc_complain qc ");
        sql.append(" LEFT JOIN house_city hc ON hc.CITY_CODE = qc.city_num ");
        sql.append(" LEFT JOIN house_project hp ON hp.PINYIN_CODE = qc.project_num ");
        sql.append(" LEFT JOIN house_houseInfo hhi ON hhi.ROOM_NUM = qc.house_code ");
        sql.append(" LEFT JOIN user_information ups ON ups.STAFF_ID = qc.disposal ");
        sql.append(" WHERE 1=1 ");
        if (map.get("city") != null && !"".equals(map.get("city").toString())) {
            sql.append(" and hc.CITY_CODE ='" + map.get("city").toString() + "'");
        }
        if (map.get("projectNum") != null && !"".equals(map.get("projectNum").toString())) {
            sql.append(" and hp.PINYIN_CODE ='" + map.get("projectNum").toString() + "'");
        }else{
            sql.append(" and hp.PINYIN_CODE =' '");
        }
        if (map.get("area") != null && !"".equals(map.get("area").toString())&& !"0".equals(map.get("area"))) {
            sql.append(" and hhi.AREA_NUM ='" + map.get("area").toString() + "'");
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())&& !"0".equals(map.get("buildingId"))) {
            sql.append(" and hhi.BUILDING_NUM ='" + map.get("buildingId").toString() + "'");
        }
        if (map.get("unitId") != null && !"".equals(map.get("unitId").toString())&& !"0".equals(map.get("unitId"))) {
            sql.append(" and hhi.UNIT_NUM ='" + map.get("unitId").toString() + "'");
        }
        if (map.get("floorId") != null && !"".equals(map.get("floorId").toString())&& !"0".equals(map.get("floorId"))) {
            sql.append(" and hhi.FLOOR_NUM ='" + map.get("floorId").toString() + "'");
        }
        if (map.get("houseCode") != null && !"".equals(map.get("houseCode").toString())&& !"0".equals(map.get("houseCode"))) {
            sql.append(" and hhi.ROOM_NUM ='" + map.get("houseCode").toString() + "'");
        }
        if (map.get("complaintPersonName") != null && !"".equals(map.get("complaintPersonName").toString())) {
            sql.append(" and qc.portal_complain_person_name like'%" + map.get("complaintPersonName").toString() + "%'");
        }
        if (map.get("complaintPhone") != null && !"".equals(map.get("complaintPhone").toString())) {
            sql.append(" and qc.complain_phone like'%" + map.get("complaintPhone").toString() + "%'");
        }
        if (map.get("complainName") != null && !"".equals(map.get("complainName").toString())) {
            sql.append(" and qc.complain_name like'%" + map.get("complainName").toString() + "%'");
        }
        if (map.get("disposalName") != null && !"".equals(map.get("disposalName").toString())) {
            sql.append(" and ups.STAFF_NAME like'%" + map.get("disposalName").toString() + "%'");
        }
        if (map.get("creatByName") != null && !"".equals(map.get("creatByName").toString())) {
            sql.append(" and qc.CREATE_BY like'%" + map.get("creatByName").toString() + "%'");
        }
        if (map.get("startTime") != null && !"".equals(map.get("startTime").toString())) {
            String startDate = map.get("startTime").toString() + " 00:00:00";
            sql.append(" and qc.submit_time >='" + startDate + "'");
        }
        if (map.get("endTime") != null && !"".equals(map.get("endTime").toString())) {
            String endDate = map.get("endTime").toString() + " 23:59:59";
            sql.append(" and qc.submit_time <='" + endDate + "'" );
        }
        sql.append(" order by qc.submit_time desc");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (webPage != null) {
            query.setFirstResult(webPage.getStartRow());
            query.setMaxResults(webPage.getPageSize());
        }
        return query.list();
    }

    @Override
    public int getCount(Map map) {
        StringBuffer sql = new StringBuffer(" SELECT count(1) ");
        sql.append(" FROM qc_complain qc ");
        sql.append(" LEFT JOIN house_city hc ON hc.CITY_CODE = qc.city_num ");
        sql.append(" LEFT JOIN house_project hp ON hp.PINYIN_CODE = qc.project_num ");
        sql.append(" LEFT JOIN house_houseInfo hhi ON hhi.ROOM_NUM = qc.house_code ");
        sql.append(" LEFT JOIN user_information ups ON ups.STAFF_ID = qc.disposal ");
        sql.append(" WHERE 1=1 ");
        if (map.get("city") != null && !"".equals(map.get("city").toString())) {
            sql.append(" and hc.CITY_CODE ='" + map.get("city").toString() + "'");
        }
        if (map.get("projectNum") != null && !"".equals(map.get("projectNum").toString())) {
            sql.append(" and hp.PINYIN_CODE ='" + map.get("projectNum").toString() + "'");
        }else{
            sql.append(" and hp.PINYIN_CODE =' '");
        }

        if (map.get("area") != null && !"".equals(map.get("area").toString())&& !"0".equals(map.get("area"))) {
            sql.append(" and hhi.AREA_NUM ='" + map.get("area").toString() + "'");
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString())&& !"0".equals(map.get("buildingId"))) {
            sql.append(" and hhi.BUILDING_NUM ='" + map.get("buildingId").toString() + "'");
        }
        if (map.get("unitId") != null && !"".equals(map.get("unitId").toString())&& !"0".equals(map.get("unitId"))) {
            sql.append(" and hhi.UNIT_NUM ='" + map.get("unitId").toString() + "'");
        }
        if (map.get("floorId") != null && !"".equals(map.get("floorId").toString())&& !"0".equals(map.get("floorId"))) {
            sql.append(" and hhi.FLOOR_NUM ='" + map.get("floorId").toString() + "'");
        }

        if (map.get("houseCode") != null && !"".equals(map.get("houseCode").toString())&& !"0".equals(map.get("houseCode"))) {
            sql.append(" and hhi.ROOM_NUM ='" + map.get("houseCode").toString() + "'");
        }
        if (map.get("complaintPersonName") != null && !"".equals(map.get("complaintPersonName").toString())) {
            sql.append(" and qc.portal_complain_person_name like'%" + map.get("complaintPersonName").toString() + "%'");
        }
        if (map.get("complaintPhone") != null && !"".equals(map.get("complaintPhone").toString())) {
            sql.append(" and qc.complain_phone like'%" + map.get("complaintPhone").toString() + "%'");
        }
        if (map.get("complainName") != null && !"".equals(map.get("complainName").toString())) {
            sql.append(" and qc.complain_name like'%" + map.get("complainName").toString() + "%'");
        }
        if (map.get("disposalName") != null && !"".equals(map.get("disposalName").toString())) {
            sql.append(" and ups.STAFF_NAME like'%" + map.get("disposalName").toString() + "%'");
        }
        if (map.get("creatByName") != null && !"".equals(map.get("creatByName").toString())) {
            sql.append(" and qc.CREATE_BY like'%" + map.get("creatByName").toString() + "%'");
        }
        if (map.get("startTime") != null && !"".equals(map.get("startTime").toString())) {
            String startDate = map.get("startTime").toString() + " 00:00:00";
            sql.append(" and qc.submit_time >='" + startDate + "'");
        }
        if (map.get("endTime") != null && !"".equals(map.get("endTime").toString())) {
            String endDate = map.get("endTime").toString() + " 23:59:59";
            sql.append(" and qc.submit_time <='" + endDate + "'");
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
    public Object[] getComplainInfoById(String complainId) {
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append("qc.complain_id,qc.complain_name,qc.complete_time,qc.document_state,qc.revisit,qc.visit_opinion,qc.visit_date,");
        sql.append("qc.revisit_satisfaction,qc.portal_complain_person_name,qc.emotion,qc.complain_phone,qc.relate_number,qc.classify_complaints,");
        sql.append("qc.comlaints_des,qc.up_grade,qc.submit_time,qc.limited_reply_time,qc.reply_time,qc.time_out,qc.complain_source,");
        sql.append("qc.complain_canal,qc.complain_level,qc.major_complain_reason,qc.important_complain_reason,qc.whether_swarmsUes,");
        sql.append("qc.dispatch_time,qc.complain_type,qc.owner_version,qc.treatment_plan,qc.processing_results,qc.return_time,");
        sql.append("qc.last_return_time,qc.CREATE_BY,qc.CREATE_ON,hc.CITY_NAME,hp.PINYIN_CODE,hp.NAME,hhi.ROOM_NUM,hhi.ADDRESS,qc.house_des,");
        sql.append("ups.STAFF_ID,ups.STAFF_NAME,ups.LOGO,ups.MOBILE");
        sql.append(" FROM qc_complain qc ");
        sql.append(" LEFT JOIN house_city hc ON hc.id = qc.city_num ");
        sql.append(" LEFT JOIN house_project hp ON hp.PINYIN_CODE = qc.project_num ");
        sql.append(" LEFT JOIN house_houseInfo hhi ON hhi.ROOM_NUM = qc.house_code ");
        sql.append(" LEFT JOIN user_information ups ON ups.STAFF_ID = qc.disposal ");
        sql.append(" WHERE 1=1 AND qc.complain_id=:id");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", complainId);
        return (Object[]) query.uniqueResult();
    }

    @Override
    public List<PropertyImageEntity> getComplainImageList(String complainId) {
        String hql = " from PropertyImageEntity where imgFkId=:fkId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("fkId", complainId);
        return query.list();
    }

    @Override
    public List<Object[]> getComplainList(Map map) {
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append("qc.complain_id,hc.CITY_NAME,hp.`NAME`,hhi.ADDRESS,qc.comlaints_des,qc.document_state,qc.CREATE_BY," +
                "qc.portal_complain_person_name,qc.limited_reply_time,qc.submit_time");
        sql.append(" FROM qc_complain qc ");
        sql.append(" LEFT JOIN house_city hc ON hc.CITY_CODE = qc.city_num ");
        sql.append(" LEFT JOIN house_project hp ON hp.PINYIN_CODE = qc.project_num ");
        sql.append(" LEFT JOIN house_houseInfo hhi ON hhi.ROOM_NUM = qc.house_code ");
        sql.append(" LEFT JOIN user_information ups ON ups.STAFF_ID = qc.disposal ");
        sql.append(" WHERE 1=1 ");
        if (map.get("city") != null && !"".equals(map.get("city").toString())) {
            sql.append(" and hc.CITY_CODE ='" + map.get("city").toString() + "'");
        }
        if (map.get("projectNum") != null && !"".equals(map.get("projectNum").toString())) {
            sql.append(" and hp.PINYIN_CODE ='" + map.get("projectNum").toString() + "'");
        }

        if (map.get("area") != null && !"".equals(map.get("area").toString()) && !"0".equals(map.get("area"))) {
            sql.append(" and hhi.AREA_NUM ='" + map.get("area").toString() + "'");
        }
        if (map.get("buildingId") != null && !"".equals(map.get("buildingId").toString()) && !"0".equals(map.get("buildingId"))) {
            sql.append(" and hhi.BUILDING_NUM ='" + map.get("buildingId").toString() + "'");
        }
        if (map.get("unitId") != null && !"".equals(map.get("unitId").toString()) && !"0".equals(map.get("unitId"))) {
            sql.append(" and hhi.UNIT_NUM ='" + map.get("unitId").toString() + "'");
        }
        if (map.get("floorId") != null && !"".equals(map.get("floorId").toString()) && !"0".equals(map.get("floorId"))) {
            sql.append(" and hhi.FLOOR_NUM ='" + map.get("floorId").toString() + "'");
        }

        if (map.get("houseCode") != null && !"".equals(map.get("houseCode").toString())&& !"0".equals(map.get("houseCode"))) {
            sql.append(" and hhi.ROOM_NUM ='" + map.get("houseCode").toString() + "'");
        }
        if (map.get("complaintPersonName") != null && !"".equals(map.get("complaintPersonName").toString())) {
            sql.append(" and qc.portal_complain_person_name like'%" + map.get("complaintPersonName").toString() + "%'");
        }
        if (map.get("complaintPhone") != null && !"".equals(map.get("complaintPhone").toString())) {
            sql.append(" and qc.complain_phone like'%" + map.get("complaintPhone").toString() + "%'");
        }
        if (map.get("complainName") != null && !"".equals(map.get("complainName").toString())) {
            sql.append(" and qc.complain_name like'%" + map.get("complainName").toString() + "%'");
        }
        if (map.get("disposalName") != null && !"".equals(map.get("disposalName").toString())) {
            sql.append(" and ups.STAFF_NAME like'%" + map.get("disposalName").toString() + "%'");
        }
        if (map.get("creatByName") != null && !"".equals(map.get("creatByName").toString())) {
            sql.append(" and qc.CREATE_BY like'%" + map.get("creatByName").toString() + "%'");
        }
        if (map.get("startTime") != null && !"".equals(map.get("startTime").toString())) {
            String startDate = map.get("startTime").toString() + " 00:00:00";
            sql.append(" and qc.submit_time >='" + startDate + "'");
        }
        if (map.get("endTime") != null && !"".equals(map.get("endTime").toString())) {
            String endDate = map.get("endTime").toString() + " 23:59:59";
            sql.append(" and qc.submit_time <='" + endDate + "'");
        }
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return query.list();
    }
}
