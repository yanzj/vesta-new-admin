package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.BasicDataRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/3/2.
 * 基础数据 持久层实现类
 */
@Repository
public class BasicDataRepositoryImpl extends HibernateDaoImpl implements BasicDataRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    /**
     * view_app_companyinfo
     * @return
     */
    @Override
    public List<ViewAppCompanyinfoEntity> ViewAppCompany(String yesterDay) {
        String hql = "from ViewAppCompanyinfoEntity as hc where 1=1";
       /* if(!"".equals(yesterDay)){
            hql = hql + " and hc.mdpBatchTime like '"+ yesterDay +"%'";
        }*/
        Query query = getCurrentSession().createQuery(hql);
        List<ViewAppCompanyinfoEntity> viewAppCompanyinfo = query.list();
        return viewAppCompanyinfo;
    }

    /**
     * 查询区域信息
     * @param name
     * @return
     */
    @Override
    public List<HouseAreaEntity> houseArea(String name) {
        String hql = "from HouseAreaEntity as ha where ha.name =:name";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("name", name);
        List<HouseAreaEntity> houseAreaEntities = query.list();
        return houseAreaEntities;
    }

    /**
     * 添加区域信息
     * @param houseAreas
     */
    @Override
    public boolean saveHouseArea(HouseAreaEntity houseAreas) {
        if(null != houseAreas){
            Session session = getCurrentSession();
            session.save(houseAreas);
            session.flush();
            return true;
        }
        return false;
    }

    /**
     * 查询公司信息
     * @param companyName
     * @return
     */
    @Override
    public List<HouseCompanyEntity> houseCompany(String companyName) {
        String hql = "from HouseCompanyEntity as hc where hc.name =:companyName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("companyName", companyName);
        List<HouseCompanyEntity> houseCompany = query.list();
        return houseCompany;
    }

    /**
     * 添加公司信息
     * @param houseCompany
     * @return
     */
    @Override
    public boolean saveHouseCompany(HouseCompanyEntity houseCompany) {
        if(null != houseCompany){
            Session session = getCurrentSession();
            session.save(houseCompany);
            session.flush();
            return true;
        }
        return false;
    }

    /**
     * 分页查询 view_app_houseinfo
     * @param webPage
     * @return
     */
    @Override
    public List<ViewAppHouseInfoEntity> getViewAppCompanyinfo(String yesterDay,WebPage webPage) {
        StringBuffer hql = new StringBuffer("FROM ViewAppHouseInfoEntity as pa WHERE 1=1");
        List<ViewAppHouseInfoEntity> houseInfo = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();

        if(!"".equals(yesterDay)){
            hql.append(" and pa.mdpBatchTime like '"+ yesterDay +"%'");
        }

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }

        houseInfo =  ( List<ViewAppHouseInfoEntity>)getHibernateTemplate().find(hql.toString());
        return houseInfo;
    }

    /**
     * 查询 项目 信息
     * @param projectName
     * @return
     */
    @Override
    public List<HouseProjectEntity> houseProject(String projectName) {
        String hql = "from HouseProjectEntity as hc where hc.name =:projectName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectName",projectName);
        List<HouseProjectEntity> houseProject = query.list();
        return houseProject;
    }

    /**
     *  添加 项目 信息
     * @param houseProjects
     * @return
     */
    @Override
    public boolean saveHouseProject(HouseProjectEntity houseProjects) {
        if(null != houseProjects){
            Session session = getCurrentSession();
            session.save(houseProjects);
            session.flush();
            return true;
        }
        return false;
    }

    /**
     * 查询 业态 信息
     * @param formatName
     * @return
     */
    @Override
    public List<HouseFormatEntity> houseFormat(String projectId, String formatName) {
        String hql = "from HouseFormatEntity as hc where hc.projectId =:projectId and hc.name =:formatName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        query.setParameter("formatName",formatName);
        List<HouseFormatEntity> houseFormat = query.list();
        return houseFormat;
    }

    /**
     * 添加 业态 信息
     * @param houseFormats
     * @return
     */
    @Override
    public boolean saveHouseFormat(HouseFormatEntity houseFormats) {
        if(null != houseFormats){
            Session session = getCurrentSession();
            session.save(houseFormats);
            session.flush();
            return  true;
        }
        return false;
    }

    /**
     * 查询 楼号 信息
     * @param blockName
     * @return
     */
    @Override
    public List<HouseBuildingEntity> houseBuilding(String projectId,String formatId,String blockName) {
        String hql = "from HouseBuildingEntity as hc where hc.projectId =:projectId and hc.formatId =:formatId and hc.name =:blockName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        query.setParameter("formatId",formatId);
        query.setParameter("blockName",blockName);
        List<HouseBuildingEntity> houseBuilding = query.list();
        return houseBuilding;
    }

    /**
     * 添加 楼号 信息
     * @param houseBuildings
     * @return
     */
    @Override
    public boolean saveHouseBuilding(HouseBuildingEntity houseBuildings) {
        if(null != houseBuildings){
            Session session = getCurrentSession();
            session.save(houseBuildings);
            session.flush();
            return  true;
        }
        return false;
    }

    /**
     * 查询 单元 信息
     * @param cellNo
     * @return
     */
    @Override
    public List<HouseUnitEntity> houseUnit(String buildingId,String cellNo) {
        String hql = "from HouseUnitEntity as hc where hc.buildingId =:buildingId and hc.name =:cellNo";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("buildingId",buildingId);
        query.setParameter("cellNo",cellNo);
        List<HouseUnitEntity> HouseUnit = query.list();
        return HouseUnit;
    }

    /**
     * 添加 单元 信息
     * @param houseUnits
     * @return
     */
    @Override
    public boolean saveHouseUnit(HouseUnitEntity houseUnits) {
        if(null != houseUnits){
            Session session = getCurrentSession();
            session.save(houseUnits);
            session.flush();
            return  true;
        }
        return false;
    }

    /**
     * 查询 房间 信息
     * @param houseNo
     * @return
     */
    @Override
    public List<HouseRoomEntity> houseRoom(String unitId,String houseNo) {
        String hql = "from HouseRoomEntity as hc where hc.unitId=:houseNo and hc.name =:houseNo";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("houseNo", houseNo);
        query.setParameter("houseNo", houseNo);
        List<HouseRoomEntity> houseRoom = query.list();
        return houseRoom;
    }

    /**
     * 添加 房间 信息
     * @param houseRooms
     * @return
     */
    @Override
    public boolean saveHouseRoom(HouseRoomEntity houseRooms) {
        if(null != houseRooms){
            Session session = getCurrentSession();
            session.save(houseRooms);
            session.flush();
            return  true;
        }
        return false;
    }

    /**
     * 添加 区域公司 日志
     * @param initialize
     */
    @Override
    public void saveInitializeData(InitializeDataEntity initialize) {
        Session session = getCurrentSession();
        session.save(initialize);
        session.flush();
    }
}
