package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.model.HouseRoomEntity;
import com.maxrocky.vesta.domain.repository.HouseRoomRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 2016/1/18 11:57.
 * Describe:房号Repository接口实现类
 */
@Repository
public class HouseRoomRepositoryImpl implements HouseRoomRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据单元Id获取单元列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 12:01:58
     */
    @Override
    public List<HouseRoomEntity> getListByUnitId(String unitId) {
        String hql = "FROM HouseRoomEntity WHERE unitId = :unitId ORDER BY name ASC";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("unitId", unitId);
        return query.list();
    }

    /**
     * Describe:根据房间Id获取房间信息
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:18:16
     */
    @Override
    public HouseRoomEntity get(String roomId) {
        return (HouseRoomEntity) getCurrentSession().get(HouseRoomEntity.class, roomId);
    }

    /**
     * Describe:根据单元Id、房间号获取房间
     * CreateBy:Tom
     * CreateOn:2016-01-22 04:25:01
     */
    @Override
    public HouseRoomEntity getByRoomNameAndUnitId(String roomName, String unitId) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM HouseRoomEntity WHERE 1 = 1");
        hql.append(" AND name = :name ");
        hql.append(" AND unitId = :unitId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("name", roomName);
        query.setParameter("unitId", unitId);
        List<HouseRoomEntity> houseRoomEntityList = query.list();
        if (houseRoomEntityList.size() == 0) {
            return null;
        }
        return houseRoomEntityList.get(0);
    }

    @Override
    public List<HouseRoomEntity> mapRoom(String unitId) {
        String hql = "from HouseRoomEntity as h ";
        if (!unitId.equals("999")) {
            hql = hql + " where h.unitId = '" + unitId + "'";
        }
        hql = hql + "group by h.name";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        List<HouseRoomEntity> houseRoomEntities = new ArrayList<>();
        houseRoomEntities = query.list();
        return houseRoomEntities;
    }

    /**
     * Describe:创建房间信息
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:01:12
     */
    @Override
    public void create(HouseRoomEntity houseRoomEntity) {
        Session session = getCurrentSession();
        session.save(houseRoomEntity);
        session.flush();
    }

    /**
     * Describe:根据会员编号获取房间信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public HouseRoomEntity getByMemberId(String id) {
        String hql = "FROM HouseRoomEntity WHERE id='" + id + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseRoomEntity> cardCRMList = query.list();
        if (cardCRMList.size() > 0) {
            return cardCRMList.get(0);
        }
        return null;
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改房产信息
     * ModifyBy:
     */
    @Override
    public void updateHouseInfo(HouseRoomEntity houseRoomEntity) {
        Session session = getCurrentSession();
        session.update(houseRoomEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:根据房屋id去查询房屋信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28 16:28
     *
     * @param roomId
     */
    @Override
    public HouseRoomEntity getHouseByRoomId(String roomId) {
        String hql = "FROM HouseRoomEntity WHERE id='" + roomId + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseRoomEntity> roomList = query.list();
        if (roomList.size() > 0) {
            return roomList.get(0);
        }
        return null;
    }

    @Override
    public List<HouseRoomEntity> getListByFloorId(String floorId) {
        String hql = "from HouseRoomEntity as h where h.floorId = '" + floorId + "' group by h.name";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        List<HouseRoomEntity> houseRoomEntities = new ArrayList<>();
        houseRoomEntities = query.list();
        return houseRoomEntities;
    }

    @Override
    public List<HouseRoomEntity> getListByFloorNum(String floorNum) {
        String hql = "from HouseRoomEntity as h where h.floorNum = '" + floorNum + "' group by h.name";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        List<HouseRoomEntity> houseRoomEntities = new ArrayList<>();
        houseRoomEntities = query.list();
        return houseRoomEntities;
    }

    @Override
    public HouseRoomEntity getByRoomNum(String roomNum) {
        String hql = "FROM HouseRoomEntity WHERE roomNum='" + roomNum + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseRoomEntity> roomList = query.list();
        if (roomList.size() > 0) {
            return roomList.get(0);
        }
        return null;
    }

    @Override
    public List<Object[]> getUserCrmForRoomid(String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" h.BUILDING_RECORD,h.ROOM_NAME,h.BUILDING_SALE from house_houseInfo h where 1=1");
        sql.append(" and h.ROOM_NUM in ('" + id + "') ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());

        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getDeliveryPlanList(String projectNum, String type) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" PLAN_NUM,PLAN_NAME,SHORT_NAME ");
        sql.append(" FROM delivery_plan_crm ");
        sql.append(" where 1=1 ");
        sql.append(" and PROJECT_NUM=:projectNum ");
        if ("11".equals(type)) {
            sql.append(" and PLAN_TYPE='houseInternalPreInspection' ");
        } else if ("12".equals(type)) {
            sql.append(" and PLAN_TYPE='clientOpendayActivity' ");
        } else if ("13".equals(type)) {
            sql.append(" and PLAN_TYPE in('centralizeDeliverHouse','scatteredDeliverHouse')");
        } else {
            sql.append(" and PLAN_TYPE='' ");
        }
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("projectNum", projectNum);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getRoomByPlanList(String planNum) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" att.CURRENT_NUM,hh.ADDRESS  ");
        sql.append(" FROM active_temporary_time att ");
        sql.append(" LEFT JOIN house_houseInfo hh on att.CURRENT_NUM=hh.ROOM_NUM  ");
        sql.append("  LEFT JOIN delivery_plan_crm dp on dp.ID=att.PARENT_ID ");
        sql.append(" where 1=1 ");
        sql.append(" and dp.PLAN_NUM=:planNum ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("planNum", planNum);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getRoomByProjectList(String projectNum) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ROOM_NUM,ADDRESS ");
        sql.append(" FROM house_houseInfo ");
        sql.append(" where 1=1 ");
        sql.append(" and PROJECT_NUM=:projectNum ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("projectNum", projectNum);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getRoomByFloor(String floorNum) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" ROOM_NUM,ADDRESS ");
        sql.append(" FROM house_houseInfo ");
        sql.append(" where 1=1 ");
        sql.append(" and FLOOR_NUM=:floorNum ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("floorNum", floorNum);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getUserCrmByRoomNum(String roomNum) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" real_name,room_num ");
        sql.append(" FROM house_user_crm ");
        sql.append(" where 1=1 ");
        sql.append(" and room_num=:roomNum ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("roomNum", roomNum);
        return (List<Object[]>) query.list();
    }

    @Override
    public List<Object[]> getAddressByRoomNum(String roomNum) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" AREA,BUILDING_SALE,BUILDING_RECORD,UNIT,FLOOR,ROOM_NAME from house_houseInfo where 1=1");
        sql.append(" and ROOM_NUM=:roomNum ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("roomNum", roomNum);
        return (List<Object[]>) query.list();
    }

    @Override
    public String getProjectNumById(String projectId) {
        StringBuffer sql = new StringBuffer(" SELECT pinyin_code  FROM house_project WHERE 1=1 and id = :pid");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("pid", projectId);
        return (String) query.uniqueResult();
    }
}
