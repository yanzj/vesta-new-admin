package com.maxrocky.vesta.presistent.repositoryHbnImpl;


import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.RectificationRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Magic on 2016/5/3.extends HibernateDaoImpl
 */
@Repository
public class RectificationRepositoryImpl extends HibernateDaoImpl implements RectificationRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void upMessageTar(MessageTargetEntity messageTargetEntity) {
        Session tempSession = getCurrentSession();
        if (messageTargetEntity != null) {
            tempSession.update(messageTargetEntity);
            tempSession.flush();
        }
    }

    @Override
    public List<MessageTargetEntity> getMessagelist(String channelId, String type) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from message_target where MESSAGE_TOKENNUM=:channelId and userType=:typ").addEntity(MessageTargetEntity.class);
        sqlQuery.setParameter("channelId", channelId);
        sqlQuery.setParameter("typ", type);
        List<MessageTargetEntity> PushList = sqlQuery.list();
        if (PushList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return PushList;
    }

    @Override
    public void saveMessageToken(MessageTokenEntity MessageTokenEntity) {
        Session tempSession = getCurrentSession();
        if (MessageTokenEntity != null) {
            tempSession.save(MessageTokenEntity);
            tempSession.flush();
        }
    }

    @Override
    public void updateMessageToken(MessageTokenEntity MessageTokenEntity) {
        Session tempSession = getCurrentSession();
        if (MessageTokenEntity != null) {
            tempSession.update(MessageTokenEntity);
            tempSession.flush();
        }
    }

    @Override
    public List<MessageTokenEntity> getMessageTokenList(String channelId, String type) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from message_token where MESSAGE_TOKENNUM=:channelId and MESSAGE_MOBILETYPE=:typ").addEntity(MessageTokenEntity.class);
        sqlQuery.setParameter("channelId", channelId);
        sqlQuery.setParameter("typ", type);
        List<MessageTokenEntity> PushList = sqlQuery.list();
        if (PushList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return PushList;
    }

    @Override
    public int getRepairForTimeCountNew(List user, String time, String id) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" count(*) ");
        sql.append(" from property_repair s ");
        sql.append(" LEFT JOIN property_repair_crm y ON s.REPAIR_ID=y.REPAIR_ID ");
        sql.append(" LEFT JOIN house_houseInfo z ON y.ROOM_NUM=z.ROOM_NUM ");
        sql.append(" LEFT JOIN room_location x ON y.ROOM_LOCATION=x.ID ");
        sql.append(" LEFT JOIN user_propertyStaff u ON y.DEAL_PEOPLE=u.STAFF_ID ");
        sql.append(" where 1=1 ");
        if (user != null && !user.isEmpty() && user.get(0) != null) {
            sql.append(" and CONCAT(z.PROJECT_NUM,s.DEPARTMENT) in (:use) ");
        } else {
            sql.append(" and CONCAT(z.PROJECT_NUM,s.DEPARTMENT) in  ('') ");
        }

        sql.append(" and ((s.MODIFY_DATE=:tim and s.ID>:iid) or s.MODIFY_DATE>:tim) ORDER BY s.MODIFY_DATE , s.ID limit 500 ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("tim", time);
        query.setParameter("iid", id);
        if (user != null && !user.isEmpty() && user.get(0) != null) {
            query.setParameterList("use", user);
        }
        List<Object[]> list = query.list();
        if (list != null && !list.isEmpty()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String getRepairForTimeCount(List user, String time, String id, String userid) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" count(*) ");
        sql.append(" from property_repair s ");
        sql.append(" LEFT JOIN property_repair_crm y ON s.REPAIR_ID=y.REPAIR_ID ");
        sql.append(" LEFT JOIN house_houseInfo z ON y.ROOM_NUM=z.ROOM_NUM ");
        sql.append(" where 1=1 ");
        if (user != null && !user.isEmpty() && user.get(0) != null) {
            sql.append(" and ( CONCAT(z.PROJECT_NUM,s.DEPARTMENT) in (:use) and (y.DEAL_PEOPLE IS NULL OR y.DEAL_PEOPLE='')  OR y.DEAL_PEOPLE =:userid)");
        } else {
            sql.append(" and CONCAT(z.PROJECT_NUM,s.DEPARTMENT) in  ('')  or  y.DEAL_PEOPLE =:userid");
        }
        sql.append(" and ((s.MODIFY_DATE=:tim and s.ID>:iid) or s.MODIFY_DATE>:tim) ORDER BY s.MODIFY_DATE , s.ID limit 500 ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("tim", time);
        query.setParameter("iid", id);
        if (user != null && !user.isEmpty() && user.get(0) != null) {
            query.setParameterList("use", user);
        }
        if (userid != null && !"".equals(userid)) {
            query.setParameter("userid", userid);
        }
        List list = query.list();
        if (!list.get(0).toString().equals("0")) {
            return "1";
        } else {
            return "0";
        }
    }

    @Override
    public List<String> getRepairForTimedelete(List user, String time, String id, String userid) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" REPAIR_ID ");
        sql.append(" from property_repair s ");
        sql.append(" where 1=1 ");
        sql.append(" and ((s.MODIFY_DATE=:tim and s.ID>:iid) or s.MODIFY_DATE>:tim)");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("tim", time);
        query.setParameter("iid", id);
        List<String> list = query.list();
        return list;
    }

    @Override
    public List<Object[]> getRepairForTimeNew(List user, String time, String id, String userid) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" s.MODIFY_DATE,u.STAFF_NAME,y.ROOM_ID,y.ROOM_NUM,y.MEMBER_ID,y.CLASSIFY_ONE,y.CLASSIFY_TWO,y.CLASSIFY_THREE,y.MODE,y.REPAIR_DATE,");
        sql.append(" y.STATE,y.CONTENT,y.DUTY_COMPANY_ONE,y.DUTY_COMPANY_TWO,y.DUTY_COMPANY_THREE,y.DEAL_PEOPLE,u.USERNAME,y.DEAL_MODE,y.DEAL_PHONE,y.DEAL_WAY,");
        sql.append(" y.DEAL_COMPLETE_DATE,x.NAME,y.TASK_DATE,y.REPAIR_ID,y.USER_PHONE,y.USER_ADDRESS,y.USER_NAME,y.CREATE_DATE,z.PROJECT_NAME,z.PROJECT_ID,z.ADDRESS, ");
        sql.append(" z.BUILDING_NUM,z.PROJECT_NUM,s.ID,y.REPAIR_COMPANY,s.DEPARTMENT,y.REPAIR_MANAGER_ID,y.DUTY_COMPANY_ONE_USER_ID,y.X_COORDINATES,y.Y_COORDINATES, ");
        sql.append(" y.APP_ID,y.REMINDER_TIME ");
        sql.append(" from property_repair s ");
        sql.append(" LEFT JOIN property_repair_crm y ON s.REPAIR_ID=y.REPAIR_ID ");
        sql.append(" LEFT JOIN house_houseInfo z ON y.ROOM_NUM=z.ROOM_NUM ");
        sql.append(" LEFT JOIN room_location x ON y.ROOM_LOCATION=x.ID ");
        sql.append(" LEFT JOIN user_propertyStaff u ON y.DEAL_PEOPLE=u.STAFF_ID ");
        sql.append(" where 1=1 ");
        if (user != null && !user.isEmpty() && user.get(0) != null) {
            sql.append(" and ( CONCAT(z.PROJECT_NUM,s.DEPARTMENT) in (:use) and (y.DEAL_PEOPLE IS NULL OR y.DEAL_PEOPLE='') OR y.DEAL_PEOPLE =:userid)");
        } else {
            sql.append(" and CONCAT(z.PROJECT_NUM,s.DEPARTMENT) in  ('')  or  y.DEAL_PEOPLE =:userid");
        }
        sql.append(" and ((s.MODIFY_DATE=:tim and s.ID>:iid) or s.MODIFY_DATE>:tim) ORDER BY s.MODIFY_DATE , s.ID limit 500 ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("tim", time);
        query.setParameter("iid", id);
        if (user != null && !user.isEmpty() && user.get(0) != null) {
            query.setParameterList("use", user);
        }
        if (userid != null && !"".equals(userid)) {
            query.setParameter("userid", userid);
        }
        return (List<Object[]>) query.list();
    }

    @Override
    public List<ActiveTemporaryTimeEntity> getActiveTemporaryCountList(String id, String time) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from (select * from active_temporary_time ORDER BY TIME_STAMP , ID ) s where (s.TIME_STAMP=:tim and s.ID>:id) or s.TIME_STAMP>:tim and GRADED='1'").addEntity(ActiveTemporaryTimeEntity.class);
        sqlQuery.setParameter("tim", time);
        sqlQuery.setParameter("id", id);
        List<ActiveTemporaryTimeEntity> ActiveList = sqlQuery.list();
        if (ActiveList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return ActiveList;
    }

    @Override
    public List<ActiveTemporaryTimeEntity> getActiveTemporaryTimeList(String id, String time, String projectNum) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from active_temporary_time s where ((s.TIME_STAMP=:tim and s.ID>:id) or s.TIME_STAMP>:tim) and (CURRENT_NUM like :projectNum or PARENT_NUM like :projectNum) ORDER BY TIME_STAMP , ID  limit 500").addEntity(ActiveTemporaryTimeEntity.class);

        sqlQuery.setParameter("tim", time);
        sqlQuery.setParameter("id", id);
        if(!StringUtil.isEmpty(projectNum)){
            sqlQuery.setParameter("projectNum", "%"+projectNum+"%");
        }else{
            sqlQuery.setParameter("projectNum", "%%");
        }
        List<ActiveTemporaryTimeEntity> ActiveList = sqlQuery.list();
        if (ActiveList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return ActiveList;
    }

    @Override
    public List<ClassificationTemporaryTimeEntity> getClassTemporaryTimeList(String id, String time) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from (select * from classification_temporary_time ORDER BY TIME_STAMP , ID ) s where (s.TIME_STAMP=:tim and s.ID>:id) or s.TIME_STAMP>:tim limit 500").addEntity(ClassificationTemporaryTimeEntity.class);
        sqlQuery.setParameter("tim", time);
        sqlQuery.setParameter("id", id);
        List<ClassificationTemporaryTimeEntity> ClassList = sqlQuery.list();
        if (ClassList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return ClassList;
    }

    @Override
    public List<ThirdClassificationCommEntity> getClassNewTimeList(String id, String time) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from (select * from third_classification_comm ORDER BY TIME_STAMP) s where  s.TIME_STAMP>=:tim  ORDER BY item_order").addEntity(ThirdClassificationCommEntity.class);
        sqlQuery.setParameter("tim", time);
        List<ThirdClassificationCommEntity> ClassList = sqlQuery.list();
        if (ClassList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return ClassList;
    }

    @Override
    public List<BuildingMappingTimeEntity> getBuildingMappingList(String id, String time, String projectNum) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from building_mapping_time s where ((s.TIME_STAMP=:tim and s.ID>:id) or s.TIME_STAMP>:tim) and (CURRENT_NUM like :projectNum or PARENT_NUM like :projectNum) ORDER BY TIME_STAMP , ID   limit 500").addEntity(BuildingMappingTimeEntity.class);
        sqlQuery.setParameter("tim",time);
        sqlQuery.setParameter("id",id);
        if(!StringUtil.isEmpty(projectNum)){
            sqlQuery.setParameter("projectNum", "%"+projectNum+"%");
        }else{
            sqlQuery.setParameter("projectNum", "%%");
        }
        List<BuildingMappingTimeEntity> BuildList = sqlQuery.list();
        if (BuildList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return BuildList;
    }

    @Override
    public PropertyRepairCRMEntity getRepairTimeList(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from property_repair_crm  where state in ('accepted','completed','processing') and REPAIR_ID=:id ORDER BY CREATE_DATE ASC").addEntity(PropertyRepairCRMEntity.class);
        sqlQuery.setParameter("id", id);
        List<PropertyRepairCRMEntity> rectifyList = sqlQuery.list();
        if (rectifyList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return rectifyList.get(0);
    }

    @Override
    public List<PropertyRepairEntity> getRepairForTime(String projectid, String groupid, String time, String id) {
        Session session = getCurrentSession();
//        SQLQuery sqlQuery = session.createSQLQuery("select * from property_repair s WHERE s.DEPARTMENT in ("+groupid+") and ((s.MODIFY_DATE=:tim and s.ID>:id) or s.MODIFY_DATE>:tim) ORDER BY MODIFY_DATE , ID limit 500").addEntity(PropertyRepairEntity.class);
        SQLQuery sqlQuery = session.createSQLQuery("select * from property_repair s LEFT JOIN house_room z on s.ROOM_ID=z.ID where z.PROJECT_NUM in (" + projectid + ") and s.DEPARTMENT in (" + groupid + ") and ((s.MODIFY_DATE=:tim and s.ID>:id) or s.MODIFY_DATE>:tim) ORDER BY MODIFY_DATE , s.ID limit 500").addEntity(PropertyRepairEntity.class);
        sqlQuery.setParameter("id", id);
        sqlQuery.setParameter("tim", time);
        List<PropertyRepairEntity> rectifyList = sqlQuery.list();
        if (rectifyList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return rectifyList;
    }

    @Override
    public List<HouseProjectEntity> getRepairForprojectTime(String projectnum, String time) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from house_project s WHERE s.PINYIN_CODE in (" + projectnum + ") and s.MODIFY_ON>:tim GROUP BY PINYIN_CODE ORDER BY CREATE_ON ASC ").addEntity(HouseProjectEntity.class);
//        sqlQuery.setParameter("id",projectnum);
        sqlQuery.setParameter("tim", time);
        List<HouseProjectEntity> rectifyList = sqlQuery.list();
        if (rectifyList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return rectifyList;
    }

    @Override
    public List<DeliveryPlanCrmEntity> getPlanCrmFroProject(String time, String projectNum) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from delivery_plan_crm s WHERE s.PROJECT_NUM in (" + projectNum + ") and s.PLAN_START>:tim ORDER BY PLAN_START ASC ").addEntity(DeliveryPlanCrmEntity.class);
//        sqlQuery.setParameter("id",projectNum);
        sqlQuery.setParameter("tim", time);
        List<DeliveryPlanCrmEntity> rectifyList = sqlQuery.list();
        if (rectifyList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return rectifyList;
    }

    @Override
    public int getPlanCountFro(String time, String projectNum) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from delivery_plan_crm s WHERE s.PROJECT_NUM in (" + projectNum + ") and s.PLAN_START>:tim ORDER BY PLAN_START ASC ").addEntity(DeliveryPlanCrmEntity.class);
//        sqlQuery.setParameter("id",projectNum);
        sqlQuery.setParameter("tim", time);
        List<DeliveryPlanCrmEntity> rectifyList = sqlQuery.list();
        if (rectifyList.size() == 0) {
            return 0;
        }
        session.flush();
        session.close();
        return rectifyList.size();
    }

    @Override
    public int getRoomCountList(String projectNum, String time) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from house_houseInfo where PROJECT_NUM in (" + projectNum + ") and  MODIFY_ON>:tim or MODIFY_ON is null ORDER BY MODIFY_ON").addEntity(HouseInfoEntity.class);
        sqlQuery.setParameter("tim", time);
        List<HouseInfoEntity> roomList = sqlQuery.list();
        if (roomList.size() == 0) {
            return 0;
        }
        session.flush();
        session.close();
        return roomList.size();
    }
//
//    @Override
//    public List<HouseRoomEntity> getHouseRoomList(String time) {
//        Session session = getCurrentSession();
////        SQLQuery sqlQuery = session.createSQLQuery("select * from house_room where if (MODIFY_ON='',CREATE_ON>:tim,MODIFY_ON>:tim) ORDER BY if(MODIFY_ON='',CREATE_ON,MODIFY_ON)ASC").addEntity(HouseRoomEntity.class);
//        SQLQuery sqlQuery = session.createSQLQuery(" select * from house_room where CREATE_ON>:tim ORDER BY CREATE_ON ASC").addEntity(HouseRoomEntity.class);
//        sqlQuery.setParameter("tim",time);
//        List<HouseRoomEntity> roomList = sqlQuery.list();
//        if(roomList.size() == 0){
//            return null;
//        }
//        session.flush();
//        session.close();
//        return roomList;
//    }

    @Override
    public List<Object> getHouseinfoRoomList(String projectnum, String time) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select z.ROOM_ID,z.ROOM_NUM,z.ROOM_NAME,z.FLOOR_ID,z.FLOOR_NUM,z.CREATE_ON,z.MODIFY_ON,s.HOUSE_TYPE from house_houseInfo z LEFT JOIN house_room_type s on z.ROOM_ID=s.ROOM_ID where PROJECT_NUM in (" + projectnum + ") and CREATE_ON >:tim  ORDER BY CREATE_ON ASC");
        sqlQuery.setParameter("tim", time);
        List<Object> property = sqlQuery.list();
        if (property.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return property;
    }

    @Override
    public List<HouseInfoEntity> getHouseinfoFloorList(String projectnum, String time) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from house_houseInfo where PROJECT_NUM in (" + projectnum + ") and CREATE_ON >:tim GROUP BY FLOOR_NUM ORDER BY CREATE_ON ").addEntity(HouseInfoEntity.class);
        sqlQuery.setParameter("tim", time);
        List<HouseInfoEntity> floorList = sqlQuery.list();
        if (floorList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return floorList;
    }

    @Override
    public List<HouseInfoEntity> getHouseinfoUnitList(String projectnum, String time) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from house_houseInfo where PROJECT_NUM in (" + projectnum + ") and CREATE_ON >:tim GROUP BY UNIT_NUM ORDER BY CREATE_ON ").addEntity(HouseInfoEntity.class);
        sqlQuery.setParameter("tim", time);
        List<HouseInfoEntity> unitList = sqlQuery.list();
        if (unitList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return unitList;
    }

    @Override
    public List<HouseInfoEntity> getHouseinfoBuildList(String projectnum, String time) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from house_houseInfo where PROJECT_NUM in (" + projectnum + ") and CREATE_ON >:tim GROUP BY BUILDING_NUM ORDER BY CREATE_ON ").addEntity(HouseInfoEntity.class);
        sqlQuery.setParameter("tim", time);
        List<HouseInfoEntity> buildList = sqlQuery.list();
        if (buildList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return buildList;
    }

    @Override
    public List<HouseInfoEntity> getHouseinfoProjectList(String projectnum, String time) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from house_houseInfo where CREATE_ON >:tim GROUP BY PROJECT_NUM ORDER BY CREATE_ON ").addEntity(HouseInfoEntity.class);
        sqlQuery.setParameter("tim", time);
        List<HouseInfoEntity> projectList = sqlQuery.list();
        if (projectList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return projectList;
    }

    @Override
    public List<HouseProjectEntity> getProjecList(Object[] projectid, String time) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from house_project s WHERE s.PINYIN_CODE in (:id) and s.MODIFY_ON>:tim ORDER BY CREATE_DATE ASC ").addEntity(HouseProjectEntity.class);
        sqlQuery.setParameter("id", projectid);
        sqlQuery.setParameter("tim", time);
        List<HouseProjectEntity> ProjectEntity = sqlQuery.list();
        if (ProjectEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return ProjectEntity;
    }

    @Override
    public List<Object> getPlanRoomList(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select z.ROOM_ID,z.ROOM_NAME,z.ROOM_NUM,a.HOUSE_TYPE,z.FLOOR,z.FLOOR_NUM,z.CREATE_ON,z.MODIFY_ON from house_plan_crm s JOIN house_houseInfo z ON s.ROOM_NUM=z.ROOM_NUM LEFT JOIN house_room_type a on z.ROOM_ID=a.ROOM_ID where s.PLAN_ID=:id ");
        sqlQuery.setParameter("id", id);
        List<Object> property = sqlQuery.list();
        if (property.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return property;
    }

    @Override
    public List<Object> getPlanFloorList(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select z.FLOOR_ID,z.FLOOR_NUM,z.FLOOR,z.UNIT_ID,z.UNIT_NUM,z.CREATE_ON,z.MODIFY_ON  from house_plan_crm s JOIN house_houseInfo z ON s.ROOM_NUM=z.ROOM_NUM where s.PLAN_ID=:id GROUP BY z.FLOOR_NUM ORDER BY z.MODIFY_ON");
        sqlQuery.setParameter("id", id);
        List<Object> property = sqlQuery.list();
        if (property.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return property;
    }

    @Override
    public List<Object> getPlanUnitList(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select z.UNIT_ID,z.UNIT,z.UNIT_NUM,z.BUILDING_ID,z.BUILDING_NUM,z.CREATE_ON,z.MODIFY_ON from house_plan_crm s JOIN house_houseInfo z ON s.ROOM_NUM=z.ROOM_NUM where s.PLAN_ID=:id GROUP BY z.UNIT_NUM ORDER BY z.MODIFY_ON");
        sqlQuery.setParameter("id", id);
        List<Object> property = sqlQuery.list();
        if (property.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return property;
    }

    @Override
    public List<Object> getPlanBuildList(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select z.BUILDING_ID,z.BUILDING_RECORD,z.BUILDING_NUM,z.CREATE_ON,z.MODIFY_ON from house_plan_crm s JOIN house_houseInfo z ON s.ROOM_NUM=z.ROOM_NUM where s.PLAN_ID=:id GROUP BY z.BUILDING_NUM ORDER BY z.MODIFY_ON");
        sqlQuery.setParameter("id", id);
        List<Object> property = sqlQuery.list();
        if (property.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return property;
    }

    @Override
    public List<PropertyRepairEntity> getAllList(String name) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from property_repair s WHERE s.DEPARTMENT=:id ORDER BY CREATE_DATE ASC ").addEntity(PropertyRepairEntity.class);
        sqlQuery.setParameter("id", name);
        List<PropertyRepairEntity> rectifyList = sqlQuery.list();
        if (rectifyList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return rectifyList;
    }

    @Override
    public PropertyRepairEntity getRepairById(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from property_repair s where REPAIR_ID=:id ").addEntity(PropertyRepairEntity.class);
        sqlQuery.setParameter("id", id);
        List<PropertyRepairEntity> rectifyList = sqlQuery.list();
        if (rectifyList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return rectifyList.get(0);
    }

    @Override
    public PropertyRepairTaskEntity getRepairTaskById(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from property_repair_task s where REPAIR_ID=:id AND (taskStatus='0' OR taskStatus='1')").addEntity(PropertyRepairTaskEntity.class);
        sqlQuery.setParameter("id", id);
        List<PropertyRepairTaskEntity> rectifyList = sqlQuery.list();
        if (rectifyList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return rectifyList.get(0);
    }

    /**
     * 获取报修单信息未完成所有
     **/
    @Override
    public List<PropertyRepairCRMEntity> getPREList() {
        /*String hql="FROM PropertyRepairCRMEntity WHERE state='0' ORDER BY createDate ASC ";
        List<PropertyRepairCRMEntity> PropertyRepairCRMEntity = getCurrentSession().createQuery(hql).list();*/
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from property_repair_crm s where state in('accepted') ORDER BY CREATE_DATE ASC ").addEntity(PropertyRepairCRMEntity.class);
        List<PropertyRepairCRMEntity> rectifyList = sqlQuery.list();
        if (rectifyList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return rectifyList;
    }

    /**
     * 获取已完成的报修个人
     */
    @Override
    public PropertyRepairCRMEntity getRecitifyList(String name, String id) {
        Session session = getCurrentSession();
        // SQLQuery sqlQuery = session.createSQLQuery("select * from property_repair_crm s where state in('completed','closed','returnVisit') and REPAIR_MANAGER =:id ORDER BY CREATE_DATE ASC");
        SQLQuery sqlQuery = session.createSQLQuery("select * from property_repair_crm s where state in('completed','processing') and DEAL_PEOPLE =:uid and REPAIR_ID=:id ORDER BY CREATE_DATE ASC").addEntity(PropertyRepairCRMEntity.class);
        sqlQuery.setParameter("id", id);
        sqlQuery.setParameter("uid", name);
        List<PropertyRepairCRMEntity> rectifyList = sqlQuery.list();
        if (rectifyList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return rectifyList.get(0);


    }

    @Override
    public PropertyRepairCRMEntity getQueryForidList(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from property_repair_crm  where state in ('accepted') and REPAIR_ID=:id ORDER BY CREATE_DATE ASC").addEntity(PropertyRepairCRMEntity.class);
        sqlQuery.setParameter("id", id);
        List<PropertyRepairCRMEntity> rectifyList = sqlQuery.list();
        if (rectifyList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return rectifyList.get(0);
    }

    @Override
    public List<PropertyImageEntity> getImageForId(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * FROM property_image WHERE img_fk_id=:id and state = '0'and IMAGE_TYPE = '0' ORDER BY UPLOAD_DATE ASC").addEntity(PropertyImageEntity.class);
        sqlQuery.setParameter("id", id);
        List<PropertyImageEntity> rectifyList = sqlQuery.list();
        if (rectifyList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return rectifyList;
    }

    @Override
    public List<PropertyImageEntity> getImageForOver(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * FROM property_image WHERE img_fk_id=:id and state = '0'and IMAGE_TYPE = '2' ORDER BY UPLOAD_DATE ASC").addEntity(PropertyImageEntity.class);
        sqlQuery.setParameter("id", id);
        List<PropertyImageEntity> rectifyList = sqlQuery.list();
        if (rectifyList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return rectifyList;
    }

    @Override
    public void updateProperty(PropertyRepairCRMEntity propertyRepairCRMEntity) {
        Session tempSession = getCurrentSession();
        if (propertyRepairCRMEntity != null) {
            tempSession.update(propertyRepairCRMEntity);
            tempSession.flush();
        }
    }

    @Override
    public void updatePropertyRectify(PropertyRectifyCRMEntity propertyRectifyCRMEntity) {
        Session tempSession = getCurrentSession();
        if (propertyRectifyCRMEntity != null) {
            tempSession.update(propertyRectifyCRMEntity);
            tempSession.flush();
        }
    }

    @Override
    public HouseInfoEntity getProject(String id) {
        Session session = getCurrentSession();
        //SQLQuery sqlQuery = session.createSQLQuery("select s.ID,s.ROOM_ID,s.ROOM_NUM,s.MEMBER_ID,s.USER_PHONE,s.USER_ADDRESS,s.USER_NAME,s.CREATE_DATE,s.CLASSIFY_ONE,s.CLASSIFY_TWO,s.CLASSIFY_THREE,s.MODE,s.ROOM_LOCATION,s.STATE,s.CONTENT,s.DUTY_COMPANY_ONE,s.DEAL_PEOPLE,s.DEAL_PHONE, s.DEAL_RESULT,s.DEAL_COMPLETE_DATE,t.PROJECT_NAME,t.PROJECT_ID from property_repair_crm s LEFT JOIN house_houseInfo t on s.ROOM_ID=t.ROOM_ID");
        SQLQuery sqlQuery = session.createSQLQuery("select * from house_houseInfo s where ROOM_NUM =:id  ").addEntity(HouseInfoEntity.class);
        sqlQuery.setParameter("id", id);
        List<HouseInfoEntity> houst = sqlQuery.list();
        if (houst.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return houst.get(0);

    }

    @Override
    public PropertyRepairCRMEntity getdelepople(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from property_repair_crm s where REPAIR_ID =:id ").addEntity(PropertyRepairCRMEntity.class);
        sqlQuery.setParameter("id", id);
        List<PropertyRepairCRMEntity> property = sqlQuery.list();
        if (property.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return property.get(0);
    }

    @Override
    public RoomLocationEntititly getRoomLocation(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * from room_location s where ID =:id ").addEntity(RoomLocationEntititly.class);
        sqlQuery.setParameter("id", id);
        List<RoomLocationEntititly> property = sqlQuery.list();
        if (property.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return property.get(0);
    }

    @Override
    public List<HouseProjectEntity> gethousListAll() {
        Session session = getCurrentSession();
        String hql = "from HouseProjectEntity as hp GROUP BY pinyinCode";
        Query query = session.createQuery(hql);
        List<HouseProjectEntity> houseProjectEntities = query.list();
        if (houseProjectEntities.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return houseProjectEntities;
    }

    @Override
    public List<HouseProjectEntity> gethousListforcompid(String id) {
        Session session = getCurrentSession();
        String hql = "from HouseProjectEntity as hp where companyId=:id GROUP BY pinyinCode";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<HouseProjectEntity> houseProjectEntities = query.list();
        if (houseProjectEntities.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return houseProjectEntities;
    }

    @Override
    public List<HouseAreaEntity> gethousListForProject(String id) {
        Session session = getCurrentSession();
        String hql = "from HouseAreaEntity as hp where projectCode=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<HouseAreaEntity> HouseAreaList = query.list();
        if (HouseAreaList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return HouseAreaList;
    }

    @Override
    public List<HouseRoomEntity> gethouseroom(String id) {
        Session session = getCurrentSession();
        String hql = "from HouseRoomEntity as hp where floorNum=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<HouseRoomEntity> HouseRoomList = query.list();
        if (HouseRoomList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return HouseRoomList;
    }

    @Override
    public List<HouseUnitEntity> gethouseUnit(String id) {
        Session session = getCurrentSession();
        String hql = "from HouseUnitEntity as hp where buildingCode=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<HouseUnitEntity> HouseUnitList = query.list();
        if (HouseUnitList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return HouseUnitList;
    }

    @Override
    public List<HouseFloorEntity> getfloorList(String id) {
        Session session = getCurrentSession();
        String hql = "from HouseFloorEntity as hp where unitCode=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<HouseFloorEntity> HouseFloor = query.list();
        if (HouseFloor.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return HouseFloor;
    }

    @Override
    public List<HouseBuildingEntity> gethouseBuilding(String id) {
        Session session = getCurrentSession();
        String hql = "from HouseBuildingEntity as hp where projectNum=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<HouseBuildingEntity> HouseBuildingList = query.list();
        if (HouseBuildingList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return HouseBuildingList;
    }

    @Override
    public List<PropertyRectifyCRMEntity> getPropertyRectify(String id) {
        Session session = getCurrentSession();
        String hql = "from PropertyRectifyCRMEntity as hp where roomId=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<PropertyRectifyCRMEntity> PropertyRectifyList = query.list();
        if (PropertyRectifyList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return PropertyRectifyList;
    }

    @Override
    public List<HouseInfoEntity> getHouseInfo(String id) {
        Session session = getCurrentSession();
        String hql = "from HouseInfoEntity as hp where roomId=:id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<HouseInfoEntity> HouseInfoList = query.list();
        if (HouseInfoList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return HouseInfoList;
    }

    @Override
    public List<DeliveryPlanCrmEntity> getDeliveryPlanCrm(String type, String id) {
        Session session = getCurrentSession();
        String hql = "from DeliveryPlanCrmEntity as hp where planType=:ty and projectNum=:id and state='start' and status='1'";
        Query query = session.createQuery(hql);
        query.setParameter("ty", type);
        query.setParameter("id", id);
        List<DeliveryPlanCrmEntity> DeliveryPlanCrmEntity = query.list();
        if (DeliveryPlanCrmEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return DeliveryPlanCrmEntity;
    }

    @Override
    public List<Object> gethouseList(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select  z.ROOM_ID,z.ROOM_NAME,z.ROOM_NUM,z.FLOOR,z.FLOOR_NUM,z.UNIT,z.UNIT_NUM,z.BUILDING_ID,z.BUILDING_RECORD,z.BUILDING_NUM from house_plan_crm s JOIN house_houseInfo z ON s.ID=z.ROOM_ID where s.PLAN_ID=:id ");
        sqlQuery.setParameter("id", id);
        List<Object> property = sqlQuery.list();
        if (property.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return property;

    }

    @Override
    public List<Object> gethouseBuildingList(String id) {
        Session session = getCurrentSession();
//        SQLQuery sqlQuery = session.createSQLQuery("select z.BUILDING_NUM,z.BUILDING_ID,z.BUILDING_NAME,z.BUILDING_RECORD from house_plan_crm s  LEFT JOIN house_houseInfo z ON s.ID=z.ROOM_ID where s.PLAN_ID=:id and z.ROOM_ID in (select ROOM_ID from house_houseInfo) GROUP BY BUILDING_NUM");
        SQLQuery sqlQuery = session.createSQLQuery("select z.BUILDING_NUM,z.BUILDING_ID,z.BUILDING_NAME,z.BUILDING_RECORD from house_plan_crm s  JOIN house_houseInfo z ON s.ID=z.ROOM_ID where s.PLAN_ID=:id  GROUP BY BUILDING_NUM");
        sqlQuery.setParameter("id", id);
        List<Object> property = sqlQuery.list();
        if (property.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return property;
    }

    @Override
    public List<Object> gethouseunitList(String id) {
        Session session = getCurrentSession();
//        SQLQuery sqlQuery = session.createSQLQuery("select z.UNIT,z.UNIT_NUM,z.BUILDING_NUM from house_plan_crm s  LEFT JOIN house_houseInfo z ON s.ID=z.ROOM_ID where s.PLAN_ID=:id and z.ROOM_ID in (select ROOM_ID from house_houseInfo) GROUP BY UNIT_NUM");
        SQLQuery sqlQuery = session.createSQLQuery("select z.UNIT,z.UNIT_NUM,z.BUILDING_NUM from house_plan_crm s JOIN house_houseInfo z ON s.ID=z.ROOM_ID where s.PLAN_ID=:id  GROUP BY UNIT_NUM");
        sqlQuery.setParameter("id", id);
        List<Object> property = sqlQuery.list();
        if (property.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return property;
    }

    @Override
    public List<Object> gethouseFloorList(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select z.FLOOR,z.FLOOR_ID,z.FLOOR_NUM,z.UNIT_NUM from house_plan_crm s  JOIN house_houseInfo z ON s.ID=z.ROOM_ID where s.PLAN_ID=:id GROUP BY z.FLOOR_NUM");
        sqlQuery.setParameter("id", id);
        List<Object> property = sqlQuery.list();
        if (property.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return property;
    }

    @Override
    public HouseProjectEntity getHouseProjectEntity(String id) {
        Session session = getCurrentSession();
        String hql = "from HouseProjectEntity as hp where id=:id ";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<HouseProjectEntity> HouseProjectEntity = query.list();
        if (HouseProjectEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return HouseProjectEntity.get(0);
    }

    @Override
    public List<ThirdTypeEntity> getThirdTypeEntity() {

        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select * FROM third_type ").addEntity(ThirdTypeEntity.class);

        List<ThirdTypeEntity> rectifyList = sqlQuery.list();
        if (rectifyList.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return rectifyList;
    }

    @Override
    public void saveHouseReception(HouseReceptionEntity HouseReception) {
        Session tempSession = getCurrentSession();
        if (HouseReception != null) {
            tempSession.save(HouseReception);
            tempSession.flush();
        }
    }

    @Override
    public void saveDeliveryInformation(DeliveryInformationEntity DeliveryInformation) {
        Session tempSession = getCurrentSession();
        if (DeliveryInformation != null) {
            tempSession.save(DeliveryInformation);
            tempSession.flush();
        }
    }

    @Override
    public List<FirstClassificationEntity> getfirstClass() {
        Session session = getCurrentSession();
        String hql = "from FirstClassificationEntity where 1=1";
        Query query = session.createQuery(hql);
        List<FirstClassificationEntity> FirstClass = query.list();
        if (FirstClass.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return FirstClass;
    }

    @Override
    public List<SecondClassificationEntity> getSecondClass() {
        Session session = getCurrentSession();
        String hql = "from SecondClassificationEntity where 1=1";
        Query query = session.createQuery(hql);
        List<SecondClassificationEntity> SecondClass = query.list();
        if (SecondClass.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return SecondClass;
    }

    @Override
    public List<ThirdClassificationEntity> getThirdClass() {
        Session session = getCurrentSession();
        String hql = "from ThirdClassificationEntity where 1=1";
        Query query = session.createQuery(hql);
        List<ThirdClassificationEntity> ThirdClass = query.list();
        if (ThirdClass.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return ThirdClass;
    }

    @Override
    public List<RepairModeEntity> getRepairMode() {
        Session session = getCurrentSession();
        String hql = "from RepairModeEntity where 1=1";
        Query query = session.createQuery(hql);
        List<RepairModeEntity> RepairMode = query.list();
        if (RepairMode.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return RepairMode;
    }

    @Override
    public List<WorkTimeEntity> getWorkTime() {
        Session session = getCurrentSession();
        String hql = "from WorkTimeEntity where 1=1";
        Query query = session.createQuery(hql);
        List<WorkTimeEntity> WorkTime = query.list();
        if (WorkTime.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return WorkTime;
    }

    @Override
    public UserPropertyStaffEntity getusername(String id) {
        Session session = getCurrentSession();
        String hql = "from UserPropertyStaffEntity as hp where staffId=:id ";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        List<UserPropertyStaffEntity> HouseProjectEntity = query.list();
        if (HouseProjectEntity.size() == 0) {
            return null;
        }
        session.flush();
        session.close();
        return HouseProjectEntity.get(0);
    }

    @Override
    public List<Object[]> getAllRepairByApp(String id, String time, String projectNum) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" s.MODIFY_DATE,u.STAFF_NAME,y.ROOM_ID,y.ROOM_NUM,y.MEMBER_ID,y.CLASSIFY_ONE,y.CLASSIFY_TWO,y.CLASSIFY_THREE,y.MODE,y.REPAIR_DATE,");
        sql.append(" y.STATE,y.CONTENT,y.DUTY_COMPANY_ONE,y.DUTY_COMPANY_TWO,y.DUTY_COMPANY_THREE,y.DEAL_PEOPLE,u.USERNAME,y.DEAL_MODE,y.DEAL_PHONE,y.DEAL_WAY,");
        sql.append(" y.DEAL_COMPLETE_DATE,x.NAME,y.TASK_DATE,y.REPAIR_ID,y.USER_PHONE,y.USER_ADDRESS,y.USER_NAME,y.CREATE_DATE,z.PROJECT_NAME,z.PROJECT_ID,z.ADDRESS, ");
        sql.append(" z.BUILDING_NUM,z.PROJECT_NUM,s.ID,y.REPAIR_COMPANY,s.DEPARTMENT,y.REPAIR_MANAGER_ID,y.DUTY_COMPANY_ONE_USER_ID,y.X_COORDINATES,y.Y_COORDINATES, ");
        sql.append(" y.APP_ID,y.REMINDER_TIME ");
        sql.append(" from property_repair s ");
        sql.append(" LEFT JOIN property_repair_crm y ON s.REPAIR_ID=y.REPAIR_ID ");
        sql.append(" LEFT JOIN house_houseInfo z ON y.ROOM_NUM=z.ROOM_NUM ");
        sql.append(" LEFT JOIN room_location x ON y.ROOM_LOCATION=x.ID ");
        sql.append(" LEFT JOIN user_propertyStaff u ON y.DEAL_PEOPLE=u.STAFF_ID ");
        sql.append(" where 1=1 ");
        sql.append(" and z.PROJECT_NUM=:projectNum ");
        sql.append(" and ((s.MODIFY_DATE=:tim and s.ID>:iid) or s.MODIFY_DATE>:tim) ORDER BY s.MODIFY_DATE , s.ID limit 500 ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("tim",time);
        query.setParameter("iid", id);
        query.setParameter("projectNum", projectNum);
        return (List<Object[]>)query.list();
    }

    @Override
    public Object[] getAllRepairById(String id,String appId) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" s.MODIFY_DATE,u.STAFF_NAME,y.ROOM_ID,y.ROOM_NUM,y.MEMBER_ID,y.CLASSIFY_ONE,y.CLASSIFY_TWO,y.CLASSIFY_THREE,y.MODE,y.REPAIR_DATE,");
        sql.append(" y.STATE,y.CONTENT,y.DUTY_COMPANY_ONE,y.DUTY_COMPANY_TWO,y.DUTY_COMPANY_THREE,y.DEAL_PEOPLE,u.USERNAME,y.DEAL_MODE,y.DEAL_PHONE,y.DEAL_WAY,");
        sql.append(" y.DEAL_COMPLETE_DATE,x.NAME,y.TASK_DATE,y.REPAIR_ID,y.USER_PHONE,y.USER_ADDRESS,y.USER_NAME,y.CREATE_DATE,z.PROJECT_NAME,z.PROJECT_ID,z.ADDRESS, ");
        sql.append(" z.BUILDING_NUM,z.PROJECT_NUM,s.ID,y.REPAIR_COMPANY,s.DEPARTMENT,y.REPAIR_MANAGER_ID,y.DUTY_COMPANY_ONE_USER_ID,y.X_COORDINATES,y.Y_COORDINATES, ");
        sql.append(" y.APP_ID ");
        sql.append(" from property_repair s ");
        sql.append(" LEFT JOIN property_repair_crm y ON s.REPAIR_ID=y.REPAIR_ID ");
        sql.append(" LEFT JOIN house_houseInfo z ON y.ROOM_NUM=z.ROOM_NUM ");
        sql.append(" LEFT JOIN room_location x ON y.ROOM_LOCATION=x.ID ");
        sql.append(" LEFT JOIN user_propertyStaff u ON y.DEAL_PEOPLE=u.STAFF_ID ");
        sql.append(" where 1=1 ");
        sql.append(" and s.REPAIR_ID=:id or y.APP_ID=:appid");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("id", id);
        query.setParameter("appid", appId);
        List<Object[]> property = query.list();
        if (property.size() == 0) {
            return null;
        }else{
            return property.get(0);
        }
    }

    /**
     * 按当前登录人的员工id 查询他所负责的所有整改单和保修单（整改中状态）
     * */
    @Override
    public List<Object[]> getHouseInspectionNB(String userId) {
        StringBuffer sql = new StringBuffer(" SELECT ");
        sql.append(" repairs.repairc,rectifys.rectifyc ");
        sql.append(" from ");
        sql.append(" (SELECT count(1) as repairc from property_repair_crm where 1=1 and DEAL_PEOPLE=:userId and STATE='processing') as repairs, ");
        sql.append(" (select count(1) as rectifyc from property_rectify_crm where 1=1 and DEAL_PEOPLE=:userId and RECTIFY_STATE='处理中') as rectifys ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameter("userId",userId);
        return (List<Object[]>)query.list();
    }

    @Override
    public int getRectifyCountById(String userId) {
        String sql = "select count(1) from property_rectify_crm where  RECTIFY_STATE='处理中' ";
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
    }

    @Override
    public int getRepairCountById(String userId) {
        String sql = "select count(1) from property_repair_crm where  STATE='processing' ";
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
    }


}
