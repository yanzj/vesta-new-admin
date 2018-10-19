package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.PropertyDoorRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.collections.map.TransformedMap;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import sun.nio.cs.ext.IBM037;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 物业门禁管理-持久层接口实现类
 * Created by WeiYangDong on 2016/11/1.
 */
@Repository
public class PropertyDoorRepositoryImpl extends HibernateDaoImpl implements PropertyDoorRepository{

    @Autowired
    @Qualifier("sessionFactory")
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
     * 删除Entity
     * @param entity
     */
    public <E> void delete(E entity){
        Session session = this.getCurrentSession();
        session.delete(entity);
        session.flush();
        session.close();
    }

    /**
     * 获取物业访客列表 WeiYangDong_2016-11-01
     * @param params    参数Map
     * @param webPage   分页
     * @return  List<PropertyVisitorEntity>
     */
    public List<PropertyVisitorEntity> getPropertyVisitorList(Map<String,Object> params,WebPage webPage){
        StringBuffer hql = new StringBuffer();
        hql.append("FROM PropertyVisitorEntity pv WHERE 1=1");
        List<Object> parameterList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //项目名称
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode.toString())){
            hql.append(" and pv.projectCode = ? ");
            parameterList.add(projectCode.toString().trim());
        }
        //房间地址
        Object address = params.get("address");
        if (null != address && !"".equals(address.toString())){
            hql.append(" and pv.address like ? ");
            parameterList.add("%"+address.toString().trim()+"%");
        }
        //访客名称
        Object visitorName = params.get("visitorName");
        if (null != visitorName && !"".equals(visitorName.toString())){
            hql.append(" and pv.visitorName like ? ");
            parameterList.add("%"+visitorName.toString().trim()+"%");
        }
        //业主名称
        Object ownerName = params.get("ownerName");
        if (null != ownerName && !"".equals(ownerName.toString())){
            hql.append(" and pv.ownerName like ? ");
            parameterList.add("%"+ownerName.toString().trim()+"%");
        }
        //创建时间_开始日期
        Object searchDateSta = params.get("searchDateSta");
        if (null != searchDateSta && !"".equals(searchDateSta.toString())) {
            hql.append(" and pv.createOn >= ? ");
            try {
                parameterList.add(dateFormat.parse(searchDateSta.toString().trim()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        //创建时间_结束日期
        Object searchDateEnd = params.get("searchDateEnd");
        if (null != searchDateEnd && !"".equals(searchDateEnd.toString())) {
            hql.append(" and pv.createOn <= ? ");
            try {
                parameterList.add(dateFormat.parse(searchDateEnd.toString().trim()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        hql.append("ORDER BY pv.createOn desc");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, parameterList);
        }
        List<PropertyVisitorEntity> propertyVisitorList=(List<PropertyVisitorEntity>)getHibernateTemplate().find(hql.toString());
        return propertyVisitorList;
    }

    /**
     * 获取物业门禁列表 WeiYangDong_2016-11-01
     * @param params    参数Map
     * @param webPage   分页
     * @return  List<PropertyDoorEntity>
     */
    public List<PropertyDoorEntity> getPropertyDoorList(Map<String,Object> params,WebPage webPage){
        StringBuilder hql = new StringBuilder();
        hql.append("FROM PropertyDoorEntity pv WHERE 1=1");
        List<Object> parameterList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //权限范围
        Object roleScopes = params.get("roleScopes");
        if (null != roleScopes && !"".equals(roleScopes.toString()) && !roleScopes.toString().contains("all")){
            hql.append(" and pv.projectCode in ("+roleScopes.toString().substring(0,roleScopes.toString().length()-1)+")");
        }
        //城市
        Object cityProjects = params.get("cityProjects");
        if (null != cityProjects && !"".equals(cityProjects)){
            hql.append(" and pv.projectCode in ("+cityProjects.toString().substring(0,cityProjects.toString().length()-1)+")");
        }
        //项目编码
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            hql.append(" and pv.projectCode = ? ");
            parameterList.add(projectCode);
        }
        //地块编码
        Object blockCode = params.get("blockCode");
        if (null != blockCode && !"".equals(blockCode) && !"0".equals(blockCode)){
            hql.append(" and pv.blockCode = ? ");
            parameterList.add(blockCode);
        }
        //楼栋编码
        Object buildingNum = params.get("buildingNum");
        if (null != buildingNum && !"".equals(buildingNum) && !"0".equals(buildingNum)){
            hql.append(" and pv.buildingNum = ? ");
            parameterList.add(buildingNum);
        }
        //单元编码
        Object unitCode = params.get("unitCode");
        if (null != unitCode && !"".equals(unitCode) && !"0".equals(unitCode)){
            hql.append(" and pv.unitCode = ? ");
            parameterList.add(unitCode);
        }
        //楼层编码
        Object floorCode = params.get("floorCode");
        if (null != floorCode && !"".equals(floorCode) && !"0".equals(floorCode)){
            hql.append(" and pv.floorCode = ? ");
            parameterList.add(floorCode);
        }
        //楼层
        Object floor = params.get("floor");
        if (null != floor && !"".equals(floor) && !"0".equals(floor)) {
            hql.append(" and pv.floor = ? ");
            parameterList.add(floor);
        }
        //门禁Mac地址
        Object macAddress = params.get("macAddress");
        if (null != macAddress && !"".equals(macAddress) && !"0".equals(macAddress)){
            hql.append(" and pv.macAddress = ? ");
            parameterList.add(macAddress);
        }
        //设备类型
        Object deviceType = params.get("deviceType");
        if (null != deviceType && !"".equals(deviceType) && !"0".equals(deviceType)){
            hql.append(" and pv.deviceType = ? ");
            parameterList.add(deviceType);
        }
        hql.append("ORDER BY pv.createOn desc");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, parameterList);
        }
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        for (int i = 0,length = parameterList.size(); i < length; i++){
            query.setParameter(i,parameterList.get(i));
        }
        List<PropertyDoorEntity> propertyDoorEntityList = query.list();
        session.flush();
        session.close();
        return propertyDoorEntityList;
    }

    /**
     * 获取用户门禁关系的用户列表
     * @param params    参数Map
     * @param webPage   分页
     * @return  List<Map<String,Object>>
     */
    public List<Map<String,Object>> getPropertyUserDoorList(Map<String,Object> params,WebPage webPage){
        /*
        select u.USER_ID userId,u.USER_NAME userName,u.NICK_NAME nickName,u.REAL_NAME realName
        ,u.MOBILE mobile,u.CREATE_ON createOn,u.USER_TYPE userType,u.ID id,h.ADDRESS address,
        d.door_id doorId
        from user_userInfo u
        left join house_houseInfo h on u.ID = h.MEMBER_ID
        left join property_user_door d on u.USER_ID = d.user_id
        where u.USER_STATE = 1
        and u.USER_TYPE in ('2','3','4')
        ORDER BY u.CREATE_ON DESC
        */
        StringBuffer sql = new StringBuffer();
        sql.append(" select u.USER_ID userId,u.USER_NAME userName,u.NICK_NAME nickName,u.REAL_NAME realName,u.MOBILE mobile,u.CREATE_ON createOn ");
        sql.append(" ,u.USER_TYPE userType,u.ID id,h.ADDRESS address,d.door_id doorId ");
        sql.append(" from user_userInfo u left join house_houseInfo h on u.ID = h.MEMBER_ID ");
        sql.append(" left join property_user_door d on u.USER_ID = d.user_id ");
        //门禁
        Object doorId = params.get("doorId");
        if (null != doorId && !"".equals(doorId)){
            sql.append(" and (d.door_id = :doorId or isnull(d.door_id)) ");
        }
        sql.append(" where u.USER_STATE = 1 and u.USER_TYPE in ('2','3','4','6') ");
        //姓名(昵称/真实姓名/用户名)
        Object name = params.get("name");
        if (null != name && !"".equals(name)){
            sql.append(" and (u.USER_NAME like :name or u.NICK_NAME like :name or u.REAL_NAME like :name) ");
        }
        //手机
        Object mobile = params.get("mobile");
        if (null != mobile && !"".equals(mobile)){
            sql.append(" and u.MOBILE like :mobile ");
        }
        //地址
        Object address = params.get("address");
        if (null != address && !"".equals(address)){
            sql.append(" and h.ADDRESS like :address ");
        }

        //城市
        Object scopeId = params.get("scopeId");
        if (null != scopeId && !"".equals(scopeId) && !"0".equals(scopeId)){
            sql.append(" and h.CITY_ID = :scopeId ");
        }
        //项目
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            sql.append(" and h.PROJECT_NUM = :projectCode ");
        }

        sql.append(" ORDER BY d.door_id DESC,u.CREATE_ON DESC ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        if (null != name && !"".equals(name)){
            sqlQuery.setParameter("name", "%" + name + "%");
        }
        if (null != mobile && !"".equals(mobile)){
            sqlQuery.setParameter("mobile", "%" + mobile + "%");
        }
        if (null != address && !"".equals(address)){
            sqlQuery.setParameter("address", "%" + address + "%");
        }
        if (null != doorId && !"".equals(doorId)){
            sqlQuery.setParameter("doorId", doorId);
        }
        if (null != scopeId && !"".equals(scopeId) && !"0".equals(scopeId)){
            sqlQuery.setParameter("scopeId", scopeId);
        }
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            sqlQuery.setParameter("projectCode", projectCode);
        }
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
     * 获取用户门禁关系的门禁列表
     * @param params    参数Map
     * @param webPage   分页
     * @return  List<Map<String,Object>>
     */
    public List<Map<String,Object>> getUserDoorMapDoorList(Map<String,Object> params,WebPage webPage){
        //TODO,尝试封装方式

        return null;
    }

    /**
     * 获取用户列表
     * @param params    参数Map
     * @param webPage   分页
     * @return  List<Map<String,Object>>
     */
    public List<Map<String,Object>> getUserList(Map<String,Object> params,WebPage webPage){
        /*
        SELECT u.USER_ID userId,u.USER_NAME userName,u.NICK_NAME nickName,u.REAL_NAME realName,
        u.MOBILE mobile,u.CREATE_ON createOn,u.USER_TYPE userType,u.ID id,h.ADDRESS address
        FROM user_userInfo u
        LEFT JOIN house_houseInfo h ON u.ID = h.MEMBER_ID
        WHERE u.USER_STATE = 1 AND u.USER_TYPE in ('2','3','4')
        ORDER BY u.CREATE_ON DESC
        */
        StringBuffer sql = new StringBuffer();
        sql.append(" select u.USER_ID userId,u.USER_NAME userName,u.NICK_NAME nickName,u.REAL_NAME realName,u.MOBILE mobile,u.CREATE_ON createOn ");
        sql.append(" ,u.USER_TYPE userType,u.ID id,h.ADDRESS address ");
        sql.append(" from user_userInfo u left join house_houseInfo h on u.ID = h.MEMBER_ID ");
        sql.append(" where u.USER_STATE = 1 and u.USER_TYPE in ('2','3','4','6') ");
        //姓名(昵称/真实姓名/用户名)
        Object name = params.get("name");
        if (null != name && !"".equals(name)) {
            sql.append(" and (u.USER_NAME like :name or u.NICK_NAME like :name or u.REAL_NAME like :name) ");
        }
        //手机
        Object mobile = params.get("mobile");
        if (null != mobile && !"".equals(mobile)) {
            sql.append(" and u.MOBILE like :mobile ");
        }
        //地址
        Object address = params.get("address");
        if (null != address && !"".equals(address)) {
            sql.append(" and h.ADDRESS like :address ");
        }
        //用户类型
        Object userType = params.get("userType");
        if (null != userType && !"".equals(userType)) {
            sql.append(" and u.USER_TYPE = :userType ");
        }
        //项目编码
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)) {
            sql.append(" and h.PROJECT_NUM = :projectCode ");
        }
        //地块编码
        Object blockCode = params.get("blockCode");
        if (null != blockCode && !"".equals(blockCode)){
            sql.append(" and h.AREA_NUM = :blockCode ");
        }
        //楼栋编码
        Object buildingNum = params.get("buildingNum");
        if (null != buildingNum && !"".equals(buildingNum)){
            sql.append(" and h.BUILDING_NUM = :buildingNum ");
        }
        //单元编码
        Object unitCode = params.get("unitCode");
        if (null != unitCode && !"".equals(unitCode)){
            sql.append(" and h.UNIT_NUM = :unitCode ");
        }
        sql.append(" ORDER BY u.CREATE_ON DESC ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        if (null != name && !"".equals(name)){
            sqlQuery.setParameter("name", "%" + name + "%");
        }
        if (null != mobile && !"".equals(mobile)) {
            sqlQuery.setParameter("mobile", "%" + mobile + "%");
        }
        if (null != address && !"".equals(address)){
            sqlQuery.setParameter("address", "%" + address + "%");
        }
        if (null != userType && !"".equals(userType)) {
            sqlQuery.setParameter("userType",userType);
        }
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            sqlQuery.setParameter("projectCode", projectCode);
        }
        if (null != blockCode && !"".equals(blockCode)){
            sqlQuery.setParameter("blockCode", blockCode);
        }
        if (null != buildingNum && !"".equals(buildingNum)){
            sqlQuery.setParameter("buildingNum", buildingNum);
        }
        if (null != unitCode && !"".equals(unitCode)){
            sqlQuery.setParameter("unitCode", unitCode);
        }
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
     * 通过房产位置信息(项目/地块/楼栋/单元)获取用户(业主/同住人/虚拟业主)列表
     * @param params    参数Map
     * @return  List<Map<String,Object>>
     */
    public List<Map<String,Object>> getUserListByHousePosition(Map<String,Object> params){
        /*
        select u.USER_ID userId
        from user_userInfo u,house_user_crm hu,house_houseInfo h
        where u.ID = hu.member_id
        and hu.room_id = h.ROOM_ID
        and u.USER_STATE = 1
        and u.USER_TYPE in ('3','4','6')
        */
        StringBuilder sql = new StringBuilder();
        sql.append(" select u.USER_ID userId ");
        sql.append(" from user_userInfo u,house_user_crm hu,house_houseInfo h ");
        sql.append(" where u.ID = hu.member_id and hu.room_id = h.ROOM_ID ");
        sql.append(" and u.USER_STATE = 1 and u.USER_TYPE in ('3','4','6') ");
        //参数值列表
        List<String> paramsValList = new ArrayList<>();
        //项目编码
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)) {
            sql.append(" and h.PROJECT_NUM = ? ");
            paramsValList.add(params.get("projectCode").toString());
        }
        //地块编码
        Object blockCode = params.get("blockCode");
        if (null != blockCode && !"".equals(blockCode)){
            sql.append(" and h.AREA_NUM = ? ");
            paramsValList.add(params.get("blockCode").toString());
        }
        //楼栋编码
        Object buildingNum = params.get("buildingNum");
        if (null != buildingNum && !"".equals(buildingNum)){
            sql.append(" and h.BUILDING_NUM = ? ");
            paramsValList.add(params.get("buildingNum").toString());
        }
        //单元编码
        Object unitCode = params.get("unitCode");
        if (null != unitCode && !"".equals(unitCode)){
            sql.append(" and h.UNIT_NUM = ? ");
            paramsValList.add(params.get("unitCode").toString());
        }
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        for (int i = 0, length = paramsValList.size(); i<length; i++){
            sqlQuery.setParameter(i,paramsValList.get(i));
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 获取用户详情
     * @param params    参数Map
     * @return  List<Map<String,Object>>
     */
    public List<Map<String,Object>> getUserInfo(Map<String,Object> params){
        StringBuilder sql = new StringBuilder();
        sql.append(" select u.USER_ID userId,u.USER_NAME userName,u.NICK_NAME nickName,u.REAL_NAME realName,u.MOBILE mobile,u.CREATE_ON createOn ");
        sql.append(" ,u.USER_TYPE userType,u.ID id,h.ADDRESS address ");
        sql.append(" from user_userInfo u left join house_houseInfo h on u.ID = h.MEMBER_ID ");
        sql.append(" where u.USER_STATE = 1 ");
        //userId
        Object userId = params.get("userId");
        if (null != userId && !"".equals(userId)){
            sql.append(" and u.user_id = :userId ");
        }

        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        if (null != userId && !"".equals(userId)){
            sqlQuery.setParameter("userId",userId);
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过门禁Id获取门禁详情 WeiYangDong_2016-11-10
     * @param id 门禁Id
     * @return  PropertyDoorEntity
     */
    public PropertyDoorEntity getPropertyDoorById(String id){
        String hql = "FROM PropertyDoorEntity WHERE id = ?";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter(0, id);
        PropertyDoorEntity propertyDoorEntity = null;
        List<PropertyDoorEntity> propertyDoorEntityList = query.list();
        session.flush();
        session.close();
        if (propertyDoorEntityList.size() > 0){
            propertyDoorEntity = propertyDoorEntityList.get(0);
        }
        return propertyDoorEntity;
    }

    /**
     * 获取用户门禁关系列表 WeiYangDong_2016-11-11
     * @param params 参数
     * @return  List<PropertyUserDoorMapEntity>
     */
    public List<PropertyUserDoorMapEntity> getUserDoorMapList(Map<String,Object> params){
        StringBuffer hql = new StringBuffer();
        hql.append(" FROM PropertyUserDoorMapEntity ud WHERE 1=1 ");
        //userId
        Object userId = params.get("userId");
        if (null != userId && !"".equals(userId)){
            hql.append(" AND ud.userId = :userId ");
        }
        //doorId
        Object doorId = params.get("doorId");
        if (null != doorId && !"".equals(doorId)){
            hql.append(" AND ud.doorId = :doorId ");
        }
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        if (null != userId && !"".equals(userId)){
            query.setParameter("userId",userId);
        }
        if (null != doorId && !"".equals(doorId)){
            query.setParameter("doorId",doorId);
        }
        List<PropertyUserDoorMapEntity> userDoorMapEntityList = query.list();
        session.flush();
        session.close();
        return userDoorMapEntityList;
    }

    /**
     * 通过项目编码与地块名称检索地块信息
     * @param projectCode   项目编码
     * @param areaName  地块名称
     * @return  List<HouseAreaEntity>
     */
    public List<HouseAreaEntity> getAreaListByProjectAndAreaName(String projectCode,String areaName){
        String hql = "FROM HouseAreaEntity h WHERE h.projectCode = ? and h.name = ?";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter(0,projectCode);
        query.setParameter(1,areaName);
        List<HouseAreaEntity> houseAreaEntityList = query.list();
        session.flush();
        session.close();
        return houseAreaEntityList;
    }

    /**
     * 通过地块编码与备案楼号检索楼栋信息
     * @param blockCode 地块编码
     * @param buildingRecord    备案楼号
     * @return  List<HouseBuildingEntity>
     */
    public List<HouseBuildingEntity> getBuildingListByAreaAndBuildingRecord(String blockCode,String buildingRecord){
        String hql = "FROM HouseBuildingEntity h WHERE h.blockNum = ? and h.buildingRecord = ?";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter(0,blockCode);
        query.setParameter(1,buildingRecord);
        List<HouseBuildingEntity> houseBuildingEntityList = query.list();
        session.flush();
        session.close();
        return houseBuildingEntityList;
    }

    /**
     * 通过地块编码与预售楼号检索楼栋信息
     * @param blockCode 地块编码
     * @param buildingSale  预售楼号
     * @return  List<HouseBuildingEntity>
     */
    public List<HouseBuildingEntity> getBuildingListByAreaAndBuildingSale(String blockCode,String buildingSale){
        String hql = "FROM HouseBuildingEntity h WHERE h.blockNum = ? and h.buildingSale = ?";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter(0,blockCode);
        query.setParameter(1,buildingSale);
        List<HouseBuildingEntity> houseBuildingEntityList = query.list();
        session.flush();
        session.close();
        return houseBuildingEntityList;
    }

    /**
     * 通过楼栋编码与单元名称检索单元信息
     * @param buildingNum  楼栋编码
     * @param unitName  单元名称
     * @return  List<HouseUnitEntity>
     */
    public List<HouseUnitEntity> getUnitListByBuildingAndUnitName(String buildingNum,String unitName){
        String hql = "FROM HouseUnitEntity h WHERE h.buildingCode = ? ";
        if (null != unitName){
            hql += "and h.name = ?";
        }
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter(0,buildingNum);
        if (null != unitName){
            query.setParameter(1,unitName);
        }
        List<HouseUnitEntity> houseUnitEntityList = query.list();
        session.flush();
        session.close();
        return houseUnitEntityList;
    }

    /**
     * 通过单元编码与楼层名称检索楼层信息
     * @param unitCode  单元编码
     * @param floorName  楼层名称
     * @return  List<HouseFloorEntity>
     */
    public List<HouseFloorEntity> getFloorListByUnitAndFloorName(String unitCode,String floorName){
        String hql = "FROM HouseFloorEntity h WHERE h.unitCode = ? ";
        if (null != floorName){
            hql += " and h.floorName = ? ";
        }
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter(0,unitCode);
        if (null != floorName){
            query.setParameter(1,floorName);
        }
        List<HouseFloorEntity> houseFloorEntityList = query.list();
        session.flush();
        session.close();
        return houseFloorEntityList;
    }

    /**
     * 通过楼层编码与房间号检索房屋信息
     * @param floorCode 楼层编码
     * @param roomName 房间号
     * @return List<HouseInfoEntity>
     */
    public List<HouseInfoEntity> getHouseInfoListByFloorAndRoomName(String floorCode,String roomName){
        StringBuilder hql = new StringBuilder("FROM HouseInfoEntity h WHERE h.floorNum = ? and h.roomName = ?");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString()).setParameter(0, floorCode).setParameter(1, roomName);
        List<HouseInfoEntity> houseInfoEntityList = query.list();
        session.flush();
        session.close();
        return houseInfoEntityList;
    }

    /**
     * 通过用户获取门禁权限列表
     * @param userId    用户Id
     * @return  List<PropertyDoorEntity>
     */
    public List<PropertyDoorEntity> getPropertyDoorListByUser(String userId){
        StringBuilder hql = new StringBuilder();
        hql.append(" FROM PropertyUserDoorMapEntity ud,PropertyDoorEntity d WHERE ud.doorId = d.id ");
        hql.append(" and ud.userId = :userId ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        query.setParameter("userId", userId);
        List list = query.list();
        session.flush();
        session.close();
        List<PropertyDoorEntity> propertyDoorEntityList = new ArrayList<>();
        for (int i = 0,length = list.size(); i < length; i++){
            Object[] object = (Object[]) list.get(i);
            propertyDoorEntityList.add((PropertyDoorEntity) object[1]);
        }
        return propertyDoorEntityList;
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param: PropertyDoorLogEntity
     *  @Description: 保存开门记录
     */
    @Override
    public void saveDoorLog(PropertyDoorLogEntity propertyDoorLogEntity) {
        Session session = getCurrentSession();
        session.save(propertyDoorLogEntity);
        session.flush();
        session.close();
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param: params 参数
     *  @Description: 获取开门记录列表
     */
    @Override
    public List<PropertyDoorLogEntity> getDoorLogList(Map<String, Object> params, WebPage webPage) {
        StringBuffer hql = new StringBuffer(" from PropertyDoorLogEntity pd where 1=1 ");
        List<Object> parameterList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //权限范围
        Object roleScopes = params.get("roleScopes");
        if (null != roleScopes && !"".equals(roleScopes.toString()) && !roleScopes.toString().contains("all")){
            hql.append(" and pd.projectCode in ("+roleScopes.toString().substring(0,roleScopes.toString().length()-1)+")");
        }
        //城市
        Object cityProjects = params.get("cityProjects");
        if (null != cityProjects && !"".equals(cityProjects.toString())){
            hql.append(" and pd.projectCode in (" + cityProjects.toString().substring(0, cityProjects.toString().length() - 1) + ")");
        }
        //项目编码
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            hql.append(" and pd.projectCode = ? ");
            parameterList.add(projectCode);
        }
        //设备描述
        Object deviceDesc = params.get("deviceDesc");
        if (null != deviceDesc && !"".equals(deviceDesc.toString())) {
            hql.append(" and pd.deviceDesc like ?");
            parameterList.add("%" + deviceDesc.toString().trim() + "%");
        }
        //用户名称
        Object userName = params.get("userName");
        if (null != userName && !"".equals(userName.toString())) {
            hql.append(" and pd.userName like ?");
            parameterList.add("%" + userName.toString().trim() + "%");
        }
        //用户电话
        Object userPhone = params.get("userPhone");
        if (null != userPhone && !"".equals(userPhone.toString())) {
            hql.append(" and pd.userPhone like ?");
            parameterList.add("%" + userPhone.toString().trim() + "%");
        }
        //日期设定
        Object startDate = params.get("startDate");
        if (null != startDate && !"".equals(startDate.toString())) {
            hql.append(" and pd.openDateTime >= ?");
            try {
                parameterList.add(dateFormat.parse(startDate.toString().trim()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Object endDate = params.get("endDate");
        if (null != endDate && !"".equals(endDate.toString())) {
            hql.append(" and pd.openDateTime <= ?");
            try {
                parameterList.add(dateFormat.parse(endDate.toString().trim()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        hql.append(" order by pd.openDateTime desc");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, parameterList);
        }
        List<PropertyDoorLogEntity> propertyDoorLogEntities=(List<PropertyDoorLogEntity>)getHibernateTemplate().find(hql.toString());
        return propertyDoorLogEntities;
    }

    /**
     * 通过房产信息获取门禁列表
     * @param params  参数
     * @return  List<PropertyDoorEntity>
     */
    public List<PropertyDoorEntity> getPropertyDoorListByHouse(Map<String,Object> params){
        StringBuilder hql = new StringBuilder();
        hql.append("FROM PropertyDoorEntity pv WHERE 1=1");
        List<Object> parameterList = new ArrayList<>();
        //门禁设备类型(单元门、小区大门)
        Object deviceType = params.get("deviceType");
        if (null != deviceType && !"".equals(deviceType)){
            hql.append(" and pv.deviceType = ? ");
            parameterList.add(deviceType);
        }
        //项目编码
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            hql.append(" and pv.projectCode = ? ");
            parameterList.add(projectCode);
        }
        //地块
        Object area = params.get("area");
        if (null != area && !"".equals(area) && !"0".equals(area)){
            hql.append(" and pv.area = ? ");
            parameterList.add(area);
        }
        //楼栋编码
        Object buildingNum = params.get("buildingNum");
        if (null != buildingNum && !"".equals(buildingNum) && !"0".equals(buildingNum)){
            hql.append(" and pv.buildingNum = ? ");
            parameterList.add(buildingNum);
        }
        //单元编码
        Object unitCode = params.get("unitCode");
        if (null != unitCode && !"".equals(unitCode) && !"0".equals(unitCode)){
            hql.append(" and pv.unitCode = ? ");
            parameterList.add(unitCode);
        }
        hql.append("ORDER BY pv.createOn desc");

        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        for (int i = 0,length = parameterList.size(); i < length; i++){
            query.setParameter(i,parameterList.get(i));
        }
        List<PropertyDoorEntity> propertyDoorEntityList = query.list();
        session.flush();
        session.close();
        return propertyDoorEntityList;
    }

    /**
     * 通过楼层获取门禁日志统计信息
     * @param params 参数
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getDoorLogStatisticsByFloor(Map<String,Object> params){
        StringBuilder sql = new StringBuilder(" select d.floor floor, count(1) num ");
        sql.append(" from property_door_log dl ");
        sql.append(" left join property_door d on dl.mac_address = d.mac_address ");
        sql.append(" where dl.project_code = ? ");
        sql.append(" group by d.floor ");
        sql.append(" order by d.floor ");
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(sql.toString()).setParameter(0,params.get("projectCode").toString());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        return list;
    }

    /**
     * 通过时间段(小时)获取门禁日志统计信息
     * @param params 参数
     * @return List<Map<String,Object>>
     */
    public List<Map<String,String>> getDoorLogStatisticsByTime(Map<String,Object> params){
        String projectCode = params.get("projectCode").toString();
        StringBuilder sql = new StringBuilder(" select * from ");
        sql.append(" (select count(1) 00点 from property_door_log dl where dl.project_code = '"+projectCode+"' and Hour(dl.open_dateTime) >=0 and Hour(dl.open_dateTime) <2 ) a, ");
        sql.append(" (select count(1) 02点 from property_door_log dl where dl.project_code = '"+projectCode+"' and Hour(dl.open_dateTime) >=2 and Hour(dl.open_dateTime) <4 ) b, ");
        sql.append(" (select count(1) 04点 from property_door_log dl where dl.project_code = '"+projectCode+"' and Hour(dl.open_dateTime) >=4 and Hour(dl.open_dateTime) <6 ) c, ");
        sql.append(" (select count(1) 06点 from property_door_log dl where dl.project_code = '"+projectCode+"' and Hour(dl.open_dateTime) >=6 and Hour(dl.open_dateTime) <8 ) d, ");
        sql.append(" (select count(1) 08点 from property_door_log dl where dl.project_code = '"+projectCode+"' and Hour(dl.open_dateTime) >=8 and Hour(dl.open_dateTime) <10 ) e, ");
        sql.append(" (select count(1) 10点 from property_door_log dl where dl.project_code = '"+projectCode+"' and Hour(dl.open_dateTime) >=10 and Hour(dl.open_dateTime) <12 ) f, ");
        sql.append(" (select count(1) 12点 from property_door_log dl where dl.project_code = '"+projectCode+"' and Hour(dl.open_dateTime) >=12 and Hour(dl.open_dateTime) <14 ) g, ");
        sql.append(" (select count(1) 14点 from property_door_log dl where dl.project_code = '"+projectCode+"' and Hour(dl.open_dateTime) >=14 and Hour(dl.open_dateTime) <16 ) h, ");
        sql.append(" (select count(1) 16点 from property_door_log dl where dl.project_code = '"+projectCode+"' and Hour(dl.open_dateTime) >=16 and Hour(dl.open_dateTime) <18 ) i, ");
        sql.append(" (select count(1) 18点 from property_door_log dl where dl.project_code = '"+projectCode+"' and Hour(dl.open_dateTime) >=18 and Hour(dl.open_dateTime) <20 ) j, ");
        sql.append(" (select count(1) 20点 from property_door_log dl where dl.project_code = '"+projectCode+"' and Hour(dl.open_dateTime) >=20 and Hour(dl.open_dateTime) <22 ) k, ");
        sql.append(" (select count(1) 22点 from property_door_log dl where dl.project_code = '"+projectCode+"' and Hour(dl.open_dateTime) >=22 and Hour(dl.open_dateTime) <24 ) l ");
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(sql.toString());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        return list;
    }

    /**
     * 通过楼栋获取门禁日志统计信息
     * @param params 参数
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getDoorLogStatisticsByBuilding(Map<String,Object> params){
        StringBuilder sql = new StringBuilder(" select d.building_num buildingNum,d.building_record buildingRecord, count(1) num ");
        sql.append(" from property_door_log dl ");
        sql.append(" left join property_door d on dl.mac_address = d.mac_address ");
        sql.append(" where dl.project_code = ? and d.building_num != '' ");
        sql.append(" group by d.building_num ");
        sql.append(" order by d.building_num ");
        Session session = getCurrentSession();
        Query query = session.createSQLQuery(sql.toString()).setParameter(0,params.get("projectCode").toString());
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        return list;
    }
}
