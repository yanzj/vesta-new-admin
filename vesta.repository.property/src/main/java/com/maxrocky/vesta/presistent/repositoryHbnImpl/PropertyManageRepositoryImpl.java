package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PropertyOwnerEntity;
import com.maxrocky.vesta.domain.repository.PropertyManageRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.axis2.util.ArrayStack;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 产权管理数据持久层实现类
 * Created by WeiYangDong on 2017/6/22.
 */
@Repository
public class PropertyManageRepositoryImpl extends HibernateDaoImpl implements PropertyManageRepository{

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
     * 获取产权管理列表
     * @param params 参数
     * @param webPage 分页
     * @return List<PropertyEntity>
     */
    public List<PropertyOwnerEntity> getPropertyManageList(Map<String,Object> params, WebPage webPage){
        StringBuilder hql = new StringBuilder("FROM PropertyOwnerEntity p WHERE 1=1");
        List<Object> parameterList = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //权限范围
        Object roleScopes = params.get("roleScopes");
        if (null != roleScopes && !"".equals(roleScopes.toString()) && !roleScopes.toString().contains("all")){
            hql.append(" and p.projectCode in ("+roleScopes.toString().substring(0,roleScopes.toString().length()-1)+")");
        }
        //城市
        Object cityProjects = params.get("cityProjects");
        if (null != cityProjects && !"".equals(cityProjects)){
            hql.append(" and p.projectCode in ("+cityProjects.toString().substring(0,cityProjects.toString().length()-1)+")");
        }
        //项目编码
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            hql.append(" and p.projectCode = ? ");
            parameterList.add(projectCode);
        }
        //地块编码
        Object blockCode = params.get("blockCode");
        if (null != blockCode && !"".equals(blockCode) && !"0".equals(blockCode)){
            hql.append(" and p.blockCode = ? ");
            parameterList.add(blockCode);
        }
        //楼栋编码
        Object buildingNum = params.get("buildingNum");
        if (null != buildingNum && !"".equals(buildingNum) && !"0".equals(buildingNum)){
            hql.append(" and p.buildingNum = ? ");
            parameterList.add(buildingNum);
        }
        //单元编码
        Object unitCode = params.get("unitCode");
        if (null != unitCode && !"".equals(unitCode) && !"0".equals(unitCode)){
            hql.append(" and p.unitCode = ? ");
            parameterList.add(unitCode);
        }
        //房间号
        Object roomName = params.get("roomName");
        if (null != roomName && !"".equals(roomName)){
            hql.append(" and p.roomName = ? ");
            parameterList.add(roomName);
        }
        //业主姓名
        Object ownerName = params.get("ownerName");
        if (null != ownerName && !"".equals(ownerName)){
            hql.append(" and p.ownerName like ? ");
            parameterList.add("%"+ownerName+"%");
        }
        //办理方式
        Object handleMode = params.get("handleMode");
        if (null != handleMode && !"".equals(handleMode)){
            hql.append(" and p.handleMode = ? ");
            parameterList.add(handleMode);
        }
        //办理状态
        Object handleStatus = params.get("handleStatus");
        if (null != handleStatus && !"".equals(handleStatus)){
            hql.append(" and p.handleStatus = ? ");
            parameterList.add(handleStatus);
        }
        hql.append(" ORDER BY p.createOn desc ");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, parameterList);
        }
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        for (int i = 0,length = parameterList.size(); i < length; i++){
            query.setParameter(i,parameterList.get(i));
        }
        List<PropertyOwnerEntity> propertyEntities = query.list();
        return propertyEntities;
    }

    /**
     * 通过房产编码获取产权信息
     * @param roomNum 房屋编码
     * @return List<PropertyOwnerEntity>
     */
    public List<PropertyOwnerEntity> getPropertyManageByRoomNum(String roomNum){
        StringBuilder hql = new StringBuilder("FROM PropertyOwnerEntity p WHERE p.roomNum = ?");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString()).setParameter(0, roomNum);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 根据项目及地块编码获取房产七级数据列表
     * @param params 参数
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getHouseDataByProject(Map<String,Object> params){
        StringBuilder sql = new StringBuilder();
        sql.append(" select hp.PINYIN_CODE projectCode,hp.NAME projectName,ha.NAME areaName,hb.BUILDING_RECORD buildingName, ");
        sql.append(" hu.NAME unitName,hf.FLOOR_NAME floorName,CONCAT(h.ROOM_NAME,'F') roomName ");
        sql.append(" from house_project hp,house_area ha,house_building hb,house_unit hu,house_floor hf,house_houseInfo h ");
        sql.append(" where hp.PINYIN_CODE = ha.PROJECT_CODE  ");
        sql.append(" and ha.BLOCK_CODE = hb.BLOCK_NUM ");
        sql.append(" and hb.building_num = hu.BUILDING_CODE ");
        sql.append(" and hu.UNIT_CODE = hf.UNIT_CODE ");
        sql.append(" and hf.FLOOR_CODE = h.FLOOR_NUM ");

        List<Object> paramsList = new ArrayList<>();
        //项目
        Object projectCode = params.get("projectCode");
        if (null != projectCode && !"".equals(projectCode)){
            sql.append(" and hp.PINYIN_CODE = ? ");
            paramsList.add(projectCode);
        }
        //地块
        Object blockCode = params.get("blockCode");
        if (null != blockCode && !"0".equals(blockCode)){
            sql.append(" and ha.BLOCK_CODE = ? ");
            paramsList.add(blockCode);
        }
        Session session = getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql.toString());
        for (int i = 0,length = paramsList.size(); i < length; i++){
            query.setParameter(i,paramsList.get(i));
        }
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = query.list();
        return list;
    }

    /**
     * 获取产权推送短信数据列表
     */
    public List<PropertyOwnerEntity> getPropertyManageListByMes(){
        StringBuilder hql = new StringBuilder("FROM PropertyOwnerEntity o WHERE o.isPushMessage = '1' AND isPushed = '0'");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        List list= query.list();
        session.flush();
        session.close();
        return list;
    }
}
