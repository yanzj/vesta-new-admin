package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.BuildingMappingTimeEntity;
import com.maxrocky.vesta.domain.model.HouseAreaEntity;
import com.maxrocky.vesta.domain.model.HouseBuildingEntity;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.repository.HouseBuildingRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Tom on 2016/1/18 11:13.
 * Describe:楼Repository接口实现类
 */
@Repository
public class HouseBuildingRepositoryImpl extends HibernateDaoImpl implements HouseBuildingRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据项目Id获取楼列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 11:15:32
     */
    @Override
    public List<HouseBuildingEntity> getListByProjectId(String projectId, String formatId) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM HouseBuildingEntity WHERE projectId = :projectId");
        if(!StringUtil.isEmpty(formatId)){
            hql.append(" AND formatId = '" + formatId + "' ");
        }
        hql.append(" ORDER BY name ASC ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("projectId", projectId);
        return query.list();
    }

    @Override
    public List<HouseBuildingEntity> getListByProjectCode(String projectCode) {
        String hql = "from HouseBuildingEntity where projectNum =:projectCode";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectCode",projectCode);
        List<HouseBuildingEntity> houseBuildingEntities = query.list();
        return houseBuildingEntities;
    }

    /**
     * Describe:根据楼Id获取楼
     * CreateBy:Tom
     * CreateOn:2016-01-18 03:13:35
     */
    @Override
    public HouseBuildingEntity get(String buildingId) {
        return (HouseBuildingEntity)getCurrentSession().get(HouseBuildingEntity.class, buildingId);
    }

    /**
     * Describe:根据项目Id、楼名获取楼
     * CreateBy:Tom
     * CreateOn:2016-01-22 04:14:35
     */
    @Override
    public HouseBuildingEntity getByBuildingNameAmdProjectId(String buildingName, String projectId) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM HouseBuildingEntity WHERE 1 = 1");
        hql.append(" AND name = :name ");
        hql.append(" AND projectId = :projectId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("name", buildingName);
        query.setParameter("projectId", projectId);
        List<HouseBuildingEntity> houseBuildingEntityList = query.list();
        if(houseBuildingEntityList.size() == 0){
            return null;
        }
        return houseBuildingEntityList.get(0);
    }

    /**
     * Describe:返回指定楼名、项目ID、业态ID
     * CreateBy:Tom
     * CreateOn:2016-03-16 04:07:03
     */
    @Override
    public HouseBuildingEntity getByBuildingNameAmdProjectId(String buildingName, String projectId, String formatId) {
        StringBuffer hql = new StringBuffer();
        hql.append("FROM HouseBuildingEntity WHERE 1 = 1");
        hql.append(" AND name = :name ");
        hql.append(" AND projectId = :projectId ");
        hql.append(" AND formatId = :formatId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("name", buildingName);
        query.setParameter("projectId", projectId);
        query.setParameter("formatId", formatId);
        List<HouseBuildingEntity> houseBuildingEntityList = query.list();
        if(houseBuildingEntityList.size() == 0){
            return null;
        }
        return houseBuildingEntityList.get(0);
    }

    @Override
    public List<HouseBuildingEntity> mapBuild(String projectId) {
        String hql = "from HouseBuildingEntity as h ";
        if (!projectId.equals("999")){
            hql = hql + " where h.projectId = '"+projectId+"'";
        }
        hql = hql +"group by h.name order by name asc";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        List<HouseBuildingEntity> houseBuildingEntities = new ArrayList<>();
        houseBuildingEntities = query.list();
        return houseBuildingEntities;
    }

    /**
     * Describe:根据楼栋id去查询信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28
     *
     * @param buildingId
     */
    @Override
    public HouseBuildingEntity getInfoByBuildingId(String buildingId) {
        String  hql="FROM HouseBuildingEntity WHERE id='"+buildingId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseBuildingEntity> buildingList=query.list();
        if(buildingList.size()>0){
            return buildingList.get(0);
        }
        return null;
    }

    @Override
    public HouseBuildingEntity getInfoByBuildingNum(String BuildingNum) {
        String  hql="FROM HouseBuildingEntity WHERE buildingNum='"+BuildingNum+"'";
        Query query = getCurrentSession().createQuery(hql);
        return (HouseBuildingEntity)query.uniqueResult();
    }

    @Override
    public BuildingMappingTimeEntity getBuildingMappingInfoByBuildingNum(String BuildingNum) {
        String  hql="FROM BuildingMappingTimeEntity WHERE currentNum='"+BuildingNum+"'";
        Query query = getCurrentSession().createQuery(hql);
        return (BuildingMappingTimeEntity)query.uniqueResult();
    }

    /**
     * Describe:创建楼栋信息
     * CreatedBy:langmafeng
     * Describe:2016-04-28 17:32
     *
     * @param houseBuildingEntity
     */
    @Override
    public void updateBuildingInfo(HouseBuildingEntity houseBuildingEntity) {
        Session session = getCurrentSession();
        session.update(houseBuildingEntity);
        session.flush();
        session.close();
    }

    @Override
    public void updateBuildingMappingInfo(BuildingMappingTimeEntity buildingMappingTimeEntity) {
        Session session = getCurrentSession();
        session.update(buildingMappingTimeEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:创建楼栋信息
     * CreateBy:langmafeng
     * CreateOn:2016-04-28 17:35
     *
     * @param houseBuildingEntity
     */
    @Override
    public void create(HouseBuildingEntity houseBuildingEntity) {
        Session session = getCurrentSession();
        session.save(houseBuildingEntity);
        session.flush();
    }

    @Override
    public List<HouseBuildingEntity> getBuildListByProjectNum(String projectNum) {
        String hql = "from HouseBuildingEntity as h ";
        hql = hql + " where h.projectNum = '"+projectNum+"' ";
        hql = hql +" order by buildingRecord asc";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        List<HouseBuildingEntity> houseBuildingEntities = new ArrayList<HouseBuildingEntity>();
        houseBuildingEntities = query.list();
        return houseBuildingEntities;
    }

    @Override
    public List<HouseProjectEntity> getProjectListByCityNum(String cityNum) {
        String hql = "from HouseProjectEntity as h ";
        hql = hql + " where h.cityNum = '"+cityNum+"' ";
        hql = hql +" order by pinyinCode asc";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        List<HouseProjectEntity> houseProjectList = new ArrayList<HouseProjectEntity>();
        houseProjectList = query.list();
        return houseProjectList;
    }

    /**
     * 通过项目编码获取地块列表 WeiYangDong_2016-11-04
     * @param projectNum    项目编码
     * @return  List<HouseAreaEntity>
     */
    public List<HouseAreaEntity> getAreaListByProjectNum(String projectNum) {
        String hql = "from HouseAreaEntity as h ";
        hql = hql + " where h.projectCode = '"+projectNum+"' ";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        List<HouseAreaEntity> houseAreaEntities = new ArrayList<HouseAreaEntity>();
        houseAreaEntities = query.list();
        return houseAreaEntities;
    }

    /**
     * 通过地块编码获取楼栋列表 WeiYangDong_2016-11-04
     * @param blockNum    地块编码
     * @return  List<HouseBuildingEntity>
     */
    public List<HouseBuildingEntity> getBuildListByBlockNum(String blockNum) {
        String hql = "from HouseBuildingEntity as h ";
        hql = hql + " where h.blockNum = '"+blockNum+"' ";
        hql = hql +" order by buildingRecord asc";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        List<HouseBuildingEntity> houseBuildingEntities = new ArrayList<HouseBuildingEntity>();
        houseBuildingEntities = query.list();
        return houseBuildingEntities;
    }

    @Override
    public List<HouseBuildingEntity> getBuildListByProjectId(String projectId) {
        String hql = "from HouseBuildingEntity as h ";
        hql = hql + " where h.projectNum = '"+projectId+"'";
        hql = hql +"order by buildingRecord asc";
        Session session = getCurrentSession();
        Query query = session.createQuery(hql);
        List<HouseBuildingEntity> houseBuildingEntities = new ArrayList<HouseBuildingEntity>();
        houseBuildingEntities = query.list();
        return houseBuildingEntities;
    }

    @Override
    public void updateBuildingAlias(String buildingNum, String buildingAlias) {
        //更新house_building表
        String hql = "UPDATE HouseBuildingEntity AS h SET h.name = ?,modifyOn = ? WHERE h.buildingNum = ?";
        Date date = new Date();
        Session session =getCurrentSession();
        Query query = session.createQuery(hql);
        query.setParameter(0,buildingAlias);
        query.setParameter(1,date);
        query.setParameter(2,buildingNum);
        query.executeUpdate();

        String hql1 = "UPDATE BuildingMappingTimeEntity SET buildingAlias = ?,timeStamp = ? WHERE GRADED = 2 AND currentNum = ? ";
        Query query1 = session.createQuery(hql1);
        query1.setParameter(0,buildingAlias);
        query1.setParameter(1,date);
        query1.setParameter(2,buildingNum);
        query1.executeUpdate();

        session.flush();
        session.close();

    }

    @Override
    public List<HouseBuildingEntity> getBuildAliasListByProjectId(String projectId,String buildingId, String alias ,WebPage webPage,String areaId) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder hql = new StringBuilder("from HouseBuildingEntity as h where 1=1");
        if (!StringUtil.isEmpty(projectId) && !"0".equals(projectId)){
            hql.append(" AND h.projectNum = ?");
            params.add(projectId);
        }
        if(!StringUtil.isEmpty(areaId)&& !"0".equals(areaId)){
            hql.append(" AND h.blockNum = ?");
            params.add(areaId);
        }
        if (!StringUtil.isEmpty(buildingId)&& !"0".equals(buildingId)){
            hql.append(" AND h.buildingNum = ?");
            params.add(buildingId);
        }
        if (!StringUtil.isEmpty(alias)){
            hql.append(" AND h.name like '%"+alias+"%'");
        }
        hql.append("order by buildingRecord asc");

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }else {
            Session session = getCurrentSession();
            Query query = session.createQuery(hql.toString());
            if (!StringUtil.isEmpty(projectId)){
                query.setParameter(0,projectId);
            }
            if (!StringUtil.isEmpty(buildingId)){
                params.add(1,buildingId);
            }
            List<HouseBuildingEntity> houseBuildingEntities = new ArrayList<HouseBuildingEntity>();
            houseBuildingEntities = query.list();
            return houseBuildingEntities;
        }

    }
}
