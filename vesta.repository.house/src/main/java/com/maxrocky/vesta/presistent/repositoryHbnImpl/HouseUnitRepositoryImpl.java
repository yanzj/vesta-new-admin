package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseUnitEntity;
import com.maxrocky.vesta.domain.repository.HouseUnitRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tom on 2016/1/18 11:42.
 * Describe:单元Repository接口实现类
 */
@Repository
public class HouseUnitRepositoryImpl implements HouseUnitRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据楼Id获取单元列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 11:44:39
     */
    @Override
    public List<HouseUnitEntity> getListByBuildingId(String buildingId) {
        String hql = "FROM HouseUnitEntity WHERE buildingId = :buildingId ORDER BY name ASC";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("buildingId", buildingId);
        return query.list();
    }

    /**
     * Describe:根据单元Id获取单元
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:17:10
     */
    @Override
    public HouseUnitEntity get(String unitId) {
        return (HouseUnitEntity)getCurrentSession().get(HouseUnitEntity.class, unitId);
    }

    /**
     * Describe:根据楼Id、单元号获取单元
     * CreateBy:Tom
     * CreateOn:2016-01-22 04:20:15
     */
    @Override
    public HouseUnitEntity getByUnitNameAndBuildingId(String unitName, String buildingId) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM HouseUnitEntity WHERE 1 = 1");
        hql.append(" AND name = :name ");
        hql.append(" AND buildingId = :buildingId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("name", unitName);
        query.setParameter("buildingId", buildingId);
        List<HouseUnitEntity> houseUnitEntityList = query.list();
        if(houseUnitEntityList.size() == 0){
            return null;
        }
        return houseUnitEntityList.get(0);
    }

    @Override
    public List<HouseUnitEntity> mapUnit(String buildId) {
        String hql = "from HouseUnitEntity as h ";
        if (!buildId.equals("999")){
            hql = hql + " where h.buildingId = '"+buildId+"'";
        }
        hql = hql+"group by h.name";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        List<HouseUnitEntity> houseUnitEntities = new ArrayList<HouseUnitEntity>();
        houseUnitEntities = query.list();
        return houseUnitEntities;
    }

    /**
     * Describe:根据楼Id、单元号获取单元
     * CreateBy:langamfeng
     * CreateOn:2016-05-07 23:38
     *
     * @param unitId
     */
    @Override
    public HouseUnitEntity getInfoByUnitId(String unitId) {
        String  hql="FROM HouseUnitEntity WHERE id='"+unitId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseUnitEntity> unitList=query.list();
        if(unitList.size()>0){
            return unitList.get(0);
        }
        return null;
    }

    /**
     * Describe:更新单元信息
     * CreatedBy:langmafeng
     * Describe:2016-05-07 23:50
     *
     * @param houseUnitEntity
     */
    @Override
    public void updateUnitInfo(HouseUnitEntity houseUnitEntity) {
        Session session = getCurrentSession();
        session.update(houseUnitEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:创建单元信息
     * CreateBy:langmafeng
     * CreateOn:2016-05-07  23:50
     *
     * @param houseUnitEntity
     */
    @Override
    public void create(HouseUnitEntity houseUnitEntity) {
        Session session = getCurrentSession();
        session.save(houseUnitEntity);
        session.flush();
    }

    /**
     * CreatedBy:dl
     * Describe:
     * 删除
     * ModifyBy:
     */
    @Override
    public void delete() {
        String hql="delete FROM HouseUnitEntity WHERE state='0'";
        Query query = getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }


    /**
     * Describe:根据楼编码获取单元列表
     */
    @Override
    public List<HouseUnitEntity> getListByBuildingNum(String buildingNum) {
        String hql = "FROM HouseUnitEntity WHERE buildingCode = :buildingNum ORDER BY name ASC";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("buildingNum", buildingNum);
        return query.list();
    }

    @Override
    public List<String> getUnitCodeByBuildingNum(String buildingNum){
        String sql = "select UNIT_CODE from house_unit where BUILDING_CODE = :buildingNum ORDER BY name ASC";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("buildingNum",buildingNum);
        return query.list();
    }
}
