package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.DeliveryPlanCRMRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.poi.hssf.record.formula.functions.T;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liudongxin on 2016/4/22.
 */
@Repository
public class DeliveryPlanCRMRepositoryImpl extends HibernateDaoImpl implements DeliveryPlanCRMRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 保存或更新Entity
     * @param entity
     */
    public <T> void saveOrUpdate(T entity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    /**
     * Describe:创建交付计划信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    @Override
    public void create(DeliveryPlanCrmEntity deliveryPlanCrmEntity) {
        Session session = getCurrentSession();
        session.save(deliveryPlanCrmEntity);
        session.flush();
        session.close();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:修改交付计划信息
     * ModifyBy:
     */
    @Override
    public void update(DeliveryPlanCrmEntity deliveryPlanCrmEntity) {
        Session session = getCurrentSession();
        session.update(deliveryPlanCrmEntity);
        session.flush();
        session.close();
    }

    @Override
    public void updatetime(ActiveTemporaryTimeEntity ActiveTemporary) {
        Session session = getCurrentSession();
        session.update(ActiveTemporary);
        session.flush();
        session.close();
    }

    /**
     * Describe:根据计划id获取信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 09:40:37
     */
    @Override
    public DeliveryPlanCrmEntity getById(String id) {
        String hql = "FROM DeliveryPlanCrmEntity WHERE id='" + id + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<DeliveryPlanCrmEntity> planCRMList = query.list();
        if (planCRMList.size() > 0) {
            return planCRMList.get(0);
        }
        return null;
    }

    @Override
    public ActiveTemporaryTimeEntity getBytimeId(String id) {
        String hql = "FROM ActiveTemporaryTimeEntity WHERE currentId='" + id + "'";
        Query query = getCurrentSession().createQuery(hql);
        List<ActiveTemporaryTimeEntity> planCRMList = query.list();
        if (planCRMList.size() > 0) {
            return planCRMList.get(0);
        }
        return null;
    }


    /**
     * Describe:待条件分页查询批次管理信息
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @param deliveryPlanCrmEntity
     * @param webPage
     * @return
     * @throws Exception
     */
    @Override
    public List<DeliveryPlanCrmEntity> queryDeliveryPlanCrmEntity(DeliveryPlanCrmEntity deliveryPlanCrmEntity, WebPage webPage,String roleScopes) throws Exception {
        StringBuffer hql = new StringBuffer("FROM DeliveryPlanCrmEntity as ad WHERE 1=1");
        List<DeliveryPlanCrmEntity> deliveryPlanCrmEntityList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();

        // 项目
//        if (null != deliveryPlanCrmEntity.getProjectNum() && !"".equals(deliveryPlanCrmEntity.getProjectNum())) {
//            hql.append(" and ad.projectNum = ?");
//            params.add(deliveryPlanCrmEntity.getProjectNum());
//        }
        if (null != deliveryPlanCrmEntity.getProjectNum() && !"".equals(deliveryPlanCrmEntity.getProjectNum())) {
            hql.append(" and ad.projectNum in (" + deliveryPlanCrmEntity.getProjectNum().substring(0, deliveryPlanCrmEntity.getProjectNum().length()-1)+")");
        }
        // 计划编号id
        if (!StringUtil.isEmpty(deliveryPlanCrmEntity.getPlanName())  && !"0".equals(deliveryPlanCrmEntity.getPlanName())) {
            hql.append(" and ad.planName = ?");
            params.add(deliveryPlanCrmEntity.getPlanName());
        }
        // 交付状态
        if (null != deliveryPlanCrmEntity.getBatchStatus() && deliveryPlanCrmEntity.getBatchStatus() <= 1) {
            hql.append(" and ad.batchStatus = ?");
            params.add(deliveryPlanCrmEntity.getBatchStatus());
        }
        // 计划描述
        if (!StringUtil.isEmpty(deliveryPlanCrmEntity.getDescription())) {
            hql.append(" and ad.description = ?");
            params.add('%' + deliveryPlanCrmEntity.getDescription() + '%');
        }
        // 集中交付开始时间
        if (null != deliveryPlanCrmEntity.getPlanStart()) {
            hql.append(" and ad.planStart >= ?");
            params.add(deliveryPlanCrmEntity.getPlanStart());
        }
        // 集中交付结束时间
        if (null != deliveryPlanCrmEntity.getPlanEnd()) {
            hql.append(" and ad.planEnd <= ?");
            params.add(deliveryPlanCrmEntity.getPlanEnd());
        }
        //数据权限范围条件
        if (!"".equals(roleScopes) && !roleScopes.contains("all")){
            hql.append(" and ad.projectNum in ("+roleScopes.substring(0,roleScopes.length()-1)+") ) ");
        }
        //设置为查询批次
        //hql.append(" and ad.state = '开始' ");
        hql.append(" ORDER BY ad.planEnd DESC");

        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }
        deliveryPlanCrmEntityList = (List<DeliveryPlanCrmEntity>) getHibernateTemplate().find(hql.toString());

        return deliveryPlanCrmEntityList;
    }
    /**
     * Describe:待条件查询批次管理信息
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @param deliveryPlanCrmEntity
     * @return
     * @throws Exception
     */
    @Override
    public List<DeliveryPlanCrmEntity> findAll(DeliveryPlanCrmEntity deliveryPlanCrmEntity,WebPage webPage) throws Exception {
        StringBuffer hql = new StringBuffer("FROM DeliveryPlanCrmEntity as ad WHERE 1=1");
        List<DeliveryPlanCrmEntity> deliveryPlanCrmEntityList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        // 项目
        if (null != deliveryPlanCrmEntity.getProjectNum() && !"".equals(deliveryPlanCrmEntity.getProjectNum())) {
//            hql.append(" and ad.projectNum = ?");
            hql.append(" and ad.projectNum like ? ");
            params.add(deliveryPlanCrmEntity.getProjectNum()+"%");
        }
        // 计划编号id
        if (!StringUtil.isEmpty(deliveryPlanCrmEntity.getPlanName())  && !"0".equals(deliveryPlanCrmEntity.getPlanName())) {
            hql.append(" and ad.planName = ?");
            params.add(deliveryPlanCrmEntity.getPlanName());
        }
        // 交付状态
//        if (null != deliveryPlanCrmEntity.getBatchStatus() && deliveryPlanCrmEntity.getBatchStatus() <= 1) {
//            hql.append(" and ad.batchStatus = ?");
//            params.add(deliveryPlanCrmEntity.getBatchStatus());
//        }
        // 计划描述
        if (!StringUtil.isEmpty(deliveryPlanCrmEntity.getDescription())) {
            hql.append(" and ad.description = ?");
            params.add('%' + deliveryPlanCrmEntity.getDescription() + '%');
        }
        // 集中交付开始时间
        if (null != deliveryPlanCrmEntity.getPlanStart()) {
            hql.append(" and ad.planStart >= ?");
            params.add(deliveryPlanCrmEntity.getPlanStart());
        }
        // 集中交付结束时间
        if (null != deliveryPlanCrmEntity.getPlanEnd()) {
            hql.append(" and ad.planEnd <= ?");
            params.add(deliveryPlanCrmEntity.getPlanEnd());
        }

        //设置为查询批次
        //hql.append(" and ad.state = '开始' ");
        //设置发布状态为1则查询
        hql.append(" and ad.batchStatus = 1 ");
        hql.append(" ORDER BY ad.planEnd DESC");

        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }
        deliveryPlanCrmEntityList = (List<DeliveryPlanCrmEntity>) getHibernateTemplate().find(hql.toString(),params.toArray());

        return deliveryPlanCrmEntityList;
    }

    /**
     * 查询所有项目
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @return
     */
    @Override
    public LinkedHashMap<String, String> listProject() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        Session session = getCurrentSession();
        List<Object[]> list = session.createSQLQuery("SELECT DISTINCT PROJECT_NAME,PROJECT_NUM FROM house_houseInfo " +
                "WHERE (PROJECT_NAME IS NOT NULL AND PROJECT_NUM IS NOT NULL) " +
                "OR (PROJECT_NAME!='' AND PROJECT_NUM!='')").list();
        if(list.size()>0) {
            for (Object[] o : list) {
                linkedHashMap.put(o[1].toString(), o[0].toString());
            }
        }
        session.flush();
        session.close();
        return linkedHashMap;
    }

    /**
     * 查询所有批次
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @return
     */
    @Override
    public List<String> listBatch() {
        Session session = getCurrentSession();
        List<String> list = session.createSQLQuery("select DISTINCT PLAN_NAME from delivery_plan_crm").list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 更具项目编号获取项目名称
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @return
     */
    @Override
    public String getProNameByProNum(String proNum) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select name from house_project where PINYIN_CODE = ?");
        sqlQuery.setParameter(0, proNum);
       /* WANGYIFAN
       if (sqlQuery.list().size() < 1) {
            return null;
        }
        String proName = (String) sqlQuery.list().get(0);*/
        String proName = (String) sqlQuery.uniqueResult();
        session.flush();
        session.close();
        return proName;
    }

    /**
     * 根据计划id查询房屋列表
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     */
    @Override
    public List<String> getHouseListByPlanId(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select fo.address from house_houseInfo fo,house_plan_crm crm where fo.room_id = crm.room_id and crm.plan_id = ?");
        sqlQuery.setParameter(0, id);
        List<String> houseList = sqlQuery.list();
        session.flush();
        session.close();
        return houseList;
    }

    @Override
    public List<HouseInfoEntity> getHouseInfoListByPlanId(String id,WebPage webPage) {
        String hql = "FROM HouseInfoEntity as hi WHERE hi.roomId in (SELECT hpc.roomId FROM HousePlanCRMEntity as hpc WHERE hpc.planId = '"+id+"')";
        List<HouseInfoEntity> houseInfoEntityList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        houseInfoEntityList = (List<HouseInfoEntity>) getHibernateTemplate().find(hql);
//        Session session = getCurrentSession();
////        SQLQuery sqlQuery = session.createSQLQuery("select fo.`ADDRESS`,fo.`MEMBER_ID` from house_houseInfo fo,house_plan_crm crm where fo.room_id = crm.room_id and crm.plan_id = ?");
//        sqlQuery.setParameter(0, id);
//        List<Object[]> houseInfoList = sqlQuery.list();
//        session.flush();
//        session.close();
        return houseInfoEntityList;
    }

    /**
     * 通过交房计划ID获取交房详情信息列表 WeiYangDong_2017-01-04
     * @param id 交房计划ID
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getPlanHouseListByPlanId(String id,WebPage webPage) {
        /*
        select hi.ADDRESS address,u.REAL_NAME realName,u.MOBILE mobile,u.ID_CARD idCard,ui.USER_ID userId
        from house_plan_crm AS hpc
        left join house_houseInfo AS hi on hi.ROOM_ID = hpc.ROOM_ID
        left join user_crm u on hi.MEMBER_ID = u.MEMBER_ID
        left join user_userInfo ui on hi.MEMBER_ID = ui.ID
        where hpc.PLAN_ID = '36bbd51c-db48-e611-80ca-005056a61361'
        */
        StringBuilder sql = new StringBuilder();
        sql.append(" select hi.ADDRESS address,u.REAL_NAME realName,u.MOBILE mobile,u.ID_CARD idCard,ui.USER_ID userId ");
        sql.append(" from house_plan_crm AS hpc ");
        sql.append(" left join house_houseInfo AS hi on hi.ROOM_ID = hpc.ROOM_ID ");
        sql.append(" left join user_crm u on hi.MEMBER_ID = u.MEMBER_ID ");
        sql.append(" left join user_userInfo ui on hi.MEMBER_ID = ui.ID ");
        sql.append(" where hpc.PLAN_ID = ? ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0,id);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (null != webPage){
            sqlQuery.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            sqlQuery.setMaxResults(webPage.getPageSize());
        }
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 用户房屋信息
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @param userId      用户id
     * @param projectName 项目名称
     */
    @Override
    public List<String> queryPersonalHouseByPlan(String userId, String projectName) {
        Session session = getCurrentSession();
//        SQLQuery sqlQuery = session.createSQLQuery("SELECT ADDRESS FROM house_houseInfo h,user_userInfo f WHERE h.MEMBER_ID = f.ID AND PROJECT_NAME = ? AND f.USER_ID = ? ");
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT h.ADDRESS ");
        sql.append(" FROM house_houseInfo h,house_user_crm hu,user_userInfo f ");
        sql.append(" WHERE h.ROOM_ID = hu.room_id AND hu.member_id = f.ID AND h.PROJECT_NAME = ? AND f.USER_ID = ? ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0, projectName);
        sqlQuery.setParameter(1, userId);
        List<String> houseList = sqlQuery.list();
        session.flush();
        session.close();
        return houseList;
    }

    /**
     * 用户预约时间
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @param userId   用户id
     * @param planName 项目名称
     */
    @Override
    public String queryReservationDate(String userId, String planName) {
        /*  -原业务逻辑-
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select RESERVATION_Date from community_reservation_list where USER_ID = ? and PLAN_NAME = ? ");
        sqlQuery.setParameter(0, userId);
        sqlQuery.setParameter(1, planName);
        List dateList = sqlQuery.list();
        if (dateList.size() < 1) {
            return null;
        }
        Timestamp timestamp = (Timestamp) dateList.get(0);
        String year = timestamp.toString().substring(0, 4);
        String month = timestamp.toString().substring(5, 7);
        String day = timestamp.toString().substring(8, 10);
        String date = year + "/" + month + "/" + day;

        session.flush();
        session.close();
        return date;
        */
        /* 修改返回结果(0:未预约,1:已预约) */
        Session session = getCurrentSession();
       // userId="1461347762743";
        Query query = session.createQuery("from CommunityReservationListEntity where userId = ? and planName = ? and reservationStatus = '1'");
        query.setParameter(0, userId);
        //planName="亚奥．金茂悦-业主集中交房";
        query.setParameter(1, planName);
        List<CommunityReservationListEntity> list = query.list();
        String is = null;
        if (list.size() < 1){
            //未预约 0
            String strDate="";
            is="{"+"}";
        }else{
            //已预约
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate=new Date();
            String strDate = formatter.format(list.get(0).getReservationDate());
            String amOrPm = list.get(0).getAmOrPm();
            is = "{"+"status:"+"1"+",reservationDate:"+strDate+",amOrPm:"+amOrPm+"}";
        }
        return is;
        /* 2016-07-19_Wyd */
    }


    /**
     * 查询项目名称，楼栋名称，单元名称，房间名称
     * CreateBy:yifan
     * CreateOn:2016-04-27 09:40:37
     *
     * @param userId      用户id
     * @param projectName 项目名称
     */
    @Override
    public List<Object[]> queryHouseAddress(String userId, String projectName) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT PROJECT_NAME,BUILDING_RECORD,UNIT,ROOM_NAME,BUILDING_SALE FROM house_houseInfo h,user_userInfo f WHERE h.MEMBER_ID = f.ID AND PROJECT_NAME = ? AND f.USER_ID = ? ");
        sqlQuery.setParameter(0, projectName);
        sqlQuery.setParameter(1, userId);
        List<Object[]> houseList = sqlQuery.list();
        session.flush();
        session.close();
        return houseList;
    }

    /**
     * 根据id获取实体类
     *
     * @param entityClass
     * @param id
     * @param <E>
     * @return
     */
    public <E> E get(Class<E> entityClass, Serializable id) {
        return this.hibernateTemplate.get(entityClass, id);
    }

    /**
     * 预约单添加
     *
     * @param communityReservation
     * @return
     */
    @Override
    public void saveReservation(CommunityReservationListEntity communityReservation) {
        Session session = getCurrentSession();
        session.save(communityReservation);
        session.flush();
        session.close();
    }


    /**
     * 预约单更新
     *
     * @param communityReservation
     * @return
     */
    @Override
    public void updateReservation(CommunityReservationListEntity communityReservation) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("UPDATE community_reservation_list  SET AM_OR_PM = ?,RESERVATION_DATE = ?,RESERVATION_STATUS = 1 WHERE USER_ID = ? AND PLAN_NAME = ?  ");
        sqlQuery.setParameter(0, communityReservation.getAmOrPm());
        sqlQuery.setParameter(1, communityReservation.getReservationDate());
        sqlQuery.setParameter(2, communityReservation.getUserId());
        sqlQuery.setParameter(3, communityReservation.getPlanName());
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 取消预约
     *
     * @param communityReservation
     * @return
     */
    @Override
    public void cancelReservation(CommunityReservationListEntity communityReservation) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("UPDATE community_reservation_list SET RESERVATION_STATUS = 0 WHERE USER_ID = ? AND PLAN_NAME = ?");
        sqlQuery.setParameter(0, communityReservation.getUserId());
        sqlQuery.setParameter(1, communityReservation.getPlanName());
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }


    /**
     * 查询预约状态
     *
     * @param communityReservation
     * @return
     */
    @Override
    public Integer queryReservationStatus(CommunityReservationListEntity communityReservation) {
        Integer reservationStatus = null;
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select RESERVATION_STATUS from community_reservation_list where USER_ID = ? AND PLAN_NAME = ?");
        sqlQuery.setParameter(0, communityReservation.getUserId());
        sqlQuery.setParameter(1, communityReservation.getPlanName());
        List list = sqlQuery.list();

        if (list.size() > 0) {
            reservationStatus = Integer.valueOf(list.get(0).toString());
        }

        session.flush();
        session.close();

        return reservationStatus;

    }

    /**
     * Describe:获取业主预约信息列表
     * CreateBy:liudongxin
     * CreateOn:2016-04-29 10:01:12
     */
    @Override
    public List<CommunityReservationListEntity> getReservationList(CommunityReservationListEntity userAppoint, WebPage webPage,String roleScopes) {
        StringBuffer hql = new StringBuffer("from CommunityReservationListEntity where 1=1 ");
        List<Object> params = new ArrayList<Object>();
        //如果不为空，则加入检索条件：项目名称
        if (!StringUtil.isEmpty(userAppoint.getProjectName())) {
//            hql.append(" and projectName like ?");
//            params.add("%"+userAppoint.getProjectName()+"%");
            hql.append(" and projectName in ("+userAppoint.getProjectName().substring(0, userAppoint.getProjectName().length()-1)+")");
        }
        //如果不为空，则加入检索条件：楼号
        if (!StringUtil.isEmpty(userAppoint.getBuildingName())) {
            hql.append(" and buildingName like ?");
            params.add("%"+userAppoint.getBuildingName()+"%");
        }
        //如果不为空，则加入检索条件：单元
        if (!StringUtil.isEmpty(userAppoint.getUnitName())) {
            hql.append(" and unitName like ?");
            params.add("%"+userAppoint.getUnitName()+"%");
        }
        //如果不为空，则加入检索条件：批次
        if (!StringUtil.isEmpty(userAppoint.getPlanName())) {
            hql.append(" and planName like ?");
            params.add("%"+userAppoint.getPlanName()+"%");
        }
        //如果不为空，则加入检索条件：时间段
        if (!StringUtil.isEmpty(userAppoint.getAmOrPm())) {
            if(userAppoint.getAmOrPm().equals("1")) {
                hql.append(" and amOrPm='0'");
            }else if(userAppoint.getAmOrPm().equals("2")){
                hql.append(" and amOrPm='1'");
            }
        }
        //如果不为空，则加入检索条件：手机号
        if (!StringUtil.isEmpty(userAppoint.getMobile())) {
            hql.append(" and mobile like ?");
            params.add("%" + userAppoint.getMobile() + "%");
        }
        //业主姓名
        if (!StringUtil.isEmpty(userAppoint.getUserName())) {
            hql.append(" and userName like ?");
            params.add("%" + userAppoint.getUserName() + "%");
        }
        //如果不为空，则加入检索条件：预约时间
        if (userAppoint.getOperatorDate() != null) {
            hql.append(" and reservationDate >= ?");
            params.add(userAppoint.getOperatorDate());
        }
        if (userAppoint.getCreateDate() != null) {
            hql.append(" and reservationDate <= ?");
            params.add(userAppoint.getCreateDate());
        }
        //数据权限范围条件
        if (!"".equals(roleScopes) && !roleScopes.contains("all")){
            hql.append(" and projectName in ("+roleScopes.substring(0,roleScopes.length()-1)+") ) ");
        }
        //hahah
        hql.append(" and status = 1 and RESERVATION_STATUS = 1 ");
        hql.append(" ORDER BY reservationDate DESC");
        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }
        List<CommunityReservationListEntity> reservationList = (List<CommunityReservationListEntity>) getHibernateTemplate().find(hql.toString());
        return reservationList;
    }

    /**
     * 根据项目编码查询下面批次信息
     * @param projectNum
     * @return
     */
    public List<String> queryBatchByProjectNum(String projectNum){
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select PLAN_Name from delivery_plan_crm where PROJECT_NUM = ? ");
        sqlQuery.setParameter(0,projectNum);
        List<String> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public List<Object[]> queryPlanByProjectNum(String projectNum,List<String> listPlan) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select PLAN_NUM,PLAN_NAME from delivery_plan_crm where PROJECT_NUM =:projectNum and PLAN_TYPE in(:listPlan)");
        sqlQuery.setParameter("projectNum",projectNum);
        sqlQuery.setParameterList("listPlan",listPlan);
        List<Object[]> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 查询crm信息
     * @param id
     */
    @Override
    public List<Object[]> queryUser(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT crm.ID_CARD,fo.MOBILE,crm.REAL_NAME FROM user_crm crm,user_userInfo fo WHERE crm.MEMBER_ID = fo.id AND fo.USER_ID = ? ");
        sqlQuery.setParameter(0,id);
        List<Object[]> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public Object[] getByRoomNum(String roomNum) {

        String sql = "select id,plan_num from delivery_plan_crm where id in (select plan_id from house_plan_crm where room_num = :roomNum) ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("roomNum",roomNum);
        List<Object[]> list = query.list();
        if(list != null && !list.isEmpty()){
            return list.get(0);
        }
        return null;
    }

    /**
     * 通过userId查询交互预约消息列表
     * @param userId 用户Id
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getDeliveryPlanMsgByUser(String userId){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
//        sql.append(" select distinct u.ID memberId,r.ID roomId,pc.PLAN_ID planId,dpc.PLAN_NAME planName,dpc.STATE state,dpc.PLAN_START planStart,dpc.PLAN_END planEnd ");
//        sql.append(" from user_userInfo u,house_room r,house_plan_crm pc,delivery_plan_crm dpc ");
//        sql.append(" where u.ID = r.MEMBER_ID and r.ID = pc.ROOM_ID and pc.PLAN_ID = dpc.ID and dpc.PLAN_END > now() and dpc.BATCH_STATUS = 1 and u.USER_ID = ? ");
        sql.append(" select distinct u.ID memberId,h.PROJECT_NAME projectName,r.ID roomId,pc.PLAN_ID id,dpc.PLAN_NAME planName,dpc.STATE state,dpc.PLAN_START planStart,dpc.PLAN_END planEnd ");
        sql.append(" from user_userInfo u,house_houseInfo h,house_room r,house_plan_crm pc,delivery_plan_crm dpc ");
        sql.append(" where u.ID = r.MEMBER_ID and u.ID = h.MEMBER_ID and r.ID = pc.ROOM_ID and pc.PLAN_ID = dpc.ID and dpc.PLAN_END > now() and dpc.BATCH_STATUS = 1 and u.USER_ID = ? ");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0, userId);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过userId和planName检索用户预约详情
     * @param userId 用户Id
     * @param planName  交付批次（计划）名称
     * @return CommunityReservationListEntity
     */
    public CommunityReservationListEntity getReservationByUserAndPlan(String userId,String planName){
        Session session = getCurrentSession();
        String hql = " from CommunityReservationListEntity where createDate = "
                +"( select max(crl.createDate) from CommunityReservationListEntity crl "
                +" where crl.reservationStatus = '1' and crl.status = '1' and userId = ? and planName = ? ) ";
        Query query = session.createQuery(hql);
        query.setParameter(0,userId);
        query.setParameter(1,planName);
        CommunityReservationListEntity communityReservation = (CommunityReservationListEntity) query.uniqueResult();
        session.flush();
        session.close();
        return communityReservation;
    }

    @Override
    public CommunityReservationListEntity getReservationByUserId(String userId) {
        Session session = getCurrentSession();
//        String hql = " from CommunityReservationListEntity where createDate = "
//                +"( select max(crl.createDate) from CommunityReservationListEntity crl "
//                +" where crl.reservationStatus = '1' and crl.status = '1' and userId = ?) ";
        String hql = " from CommunityReservationListEntity crl where crl.reservationStatus = '1' and crl.status = '1' and userId = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0,userId);
        CommunityReservationListEntity communityReservation = (CommunityReservationListEntity) query.uniqueResult();
        session.flush();
        session.close();
        return communityReservation;
    }

    /**
     * 获取交付计划时间段配置列表
     * @param deliveryPlanId 交付计划ID
     * @return List<DeliveryPlanReservationTimeRangeEntity>
     */
    public List<DeliveryPlanReservationTimeRangeEntity> getReservationTimeRangeListByPlan(String deliveryPlanId,Date reservationDate){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM DeliveryPlanReservationTimeRangeEntity WHERE deliveryPlanId = ? AND reservationDate = ? ORDER BY createOn")
                .setParameter(0,deliveryPlanId).setParameter(1,reservationDate);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过计划ID及预约日期删除时间段配置
     * @param deliveryPlanId 交付计划ID
     * @param reservationDate 预约日期
     */
    public void deleteReservationTimeRangeByPlanAndDate(String deliveryPlanId,Date reservationDate){
        getCurrentSession().createQuery("DELETE DeliveryPlanReservationTimeRangeEntity d WHERE d.deliveryPlanId = ? AND d.reservationDate = ?")
                .setParameter(0,deliveryPlanId).setParameter(1,reservationDate).executeUpdate();
    }

}
