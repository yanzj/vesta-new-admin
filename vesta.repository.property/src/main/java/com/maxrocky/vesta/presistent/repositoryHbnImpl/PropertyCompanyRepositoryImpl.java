package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.PropertyCompanyRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/1/17.
 * 物业项目公司 持久层实现类
 */
@Repository
public class PropertyCompanyRepositoryImpl extends HibernateDaoImpl implements PropertyCompanyRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {

        return sessionFactory.openSession();
    }

    /**
     * 初始化物业管理公司列表
     * @param propertyCompanyEntity
     * @param webPage
     * @return
     */
    @Override
    public List<PropertyCompanyEntity> queryPropertyCompanyMessage(PropertyCompanyEntity propertyCompanyEntity,WebPage webPage) {
        StringBuffer hql = new StringBuffer("FROM PropertyCompanyEntity as pc WHERE 1=1");
        List<PropertyCompanyEntity> propertyCompanyEntities = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        // 不为空则拼接搜索条件  区域
        if(null != propertyCompanyEntity.getPropertyArea() && !"".equals(propertyCompanyEntity.getPropertyArea())){
            if(!"0".equals(propertyCompanyEntity.getPropertyArea())){
                hql.append(" and pc.propertyArea like ?");
                params.add("%"+ propertyCompanyEntity.getPropertyArea() +"%");
            }
        }

        // 不为空则拼接搜索添加 公司
        if(null != propertyCompanyEntity.getCompanyName() && !"".equals(propertyCompanyEntity.getCompanyName())){
            if(!"0".equals(propertyCompanyEntity.getCompanyName())){
                hql.append(" and pc.companyName like ?");
                params.add("%"+ propertyCompanyEntity.getCompanyName() +"%");
            }
        }

        // 不为空则拼接搜索添加 项目
        if(null != propertyCompanyEntity.getPropertyProject() && !"".equals(propertyCompanyEntity.getPropertyProject())){
            if(!"0".equals(propertyCompanyEntity.getPropertyProject())){
                hql.append(" and pc.propertyProject like ?");
                params.add("%"+ propertyCompanyEntity.getPropertyProject() +"%");
            }
        }

        // 不为空则拼接搜索添加 管理员
        if(null != propertyCompanyEntity.getProjectAdmin() && !"".equals(propertyCompanyEntity.getProjectAdmin())) {
            hql.append(" and pc.projectAdmin like ?");
            params.add("%"+ propertyCompanyEntity.getProjectAdmin() + "%");
        }

        hql.append(" ORDER BY pc.propertyMessageTime DESC");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }

        propertyCompanyEntities =  ( List<PropertyCompanyEntity>)getHibernateTemplate().find(hql.toString());

        return propertyCompanyEntities;
    }

    /**
     * 区域 列表
     * @return
     */
    @Override
    public List<HouseAreaEntity> queryHouseAreaEntity(String id) {
        String hql = "from HouseAreaEntity as ha where 1=1";
        if(!"".equals(id)){
            hql = hql + " and ha.id = '"+ id +"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<HouseAreaEntity> houseAreaEntities = query.list();
        return houseAreaEntities;
    }

    /**
     * 公司 列表
     * @return
     */
    @Override
    public List<HouseCompanyEntity> queryHouseCompanyEntity(String id) {
        String hql = "from HouseCompanyEntity as hc where 1=1";
        if(!"".equals(id)){
            hql = hql + " and hc.id = '"+ id +"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<HouseCompanyEntity> houseAreaEntities = query.list();
        return houseAreaEntities;
    }

    /**
     * 项目列表
     * @return
     */
    @Override
    public List<HouseProjectEntity> queryHouseProjectEntity(String id) {
        String hql = "from HouseProjectEntity as hp where 1=1";
        if(!"".equals(id) && null != id){
            hql = hql + " and hp.id = '"+ id +"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<HouseProjectEntity> houseProjectEntities = query.list();
        return houseProjectEntities;
    }

    /**
     * 公告类型
     * @param type
     * @param typeId
     * @return
     */
    @Override
    public List<PropertyTypeEntity> queryPropertyTypeEntity(String type,String typeId) {
        String hql = "from PropertyTypeEntity as hp where 1=1";
        if(!"".equals(type)){
            hql = hql + " and hp.typeId = '"+ type +"'";
        }
        if(!"".equals(typeId)){
            hql = hql + " and hp.type = '"+ typeId +"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyTypeEntity> propertyType = query.list();
        return propertyType;
    }

    /**
     * 判断w物业公司权限和关系是否存在
     * @param setId
     * @param roleId
     * @return
     */
    @Override
    public PropertyCompanyRoleMapEntity getRolesetMap(String setId,String roleId) {
        String hql = "from PropertyCompanyRoleMapEntity as o where setId = :setId and rolRoleId = :roleId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("setId",setId);
        query.setParameter("roleId",roleId);
        if (query.list().size()>0){
            return (PropertyCompanyRoleMapEntity)query.list().get(0);
        }else {
            return null;
        }
    }

    /**
     * 根据公司ID查询 公司权限表
     * @param companyId
     * @return
     */
    @Override
    public List<PropertyCompanyRoleMapEntity> queryPropertyCompanyRoleMap(String companyId) {
        String hql = "from PropertyCompanyRoleMapEntity as o where setId = :companyId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("companyId", companyId);
        List<PropertyCompanyRoleMapEntity> propertyCompanyRoleMap = query.list();
        return propertyCompanyRoleMap;
    }

    /**
     *  根据公司ID 删除公司权限表
     * @param propertyCompanyRoleMaps
     */
    @Override
    public void delPropertyCompanyRoleMap(PropertyCompanyRoleMapEntity propertyCompanyRoleMaps) {
        Session session = getCurrentSession();
        session.delete(propertyCompanyRoleMaps);
        session.flush();
    }

    /**
     * 添加物业公司表
     * @param propertyCompany
     */
    @Override
    public void addPropertyCompanyEntity(PropertyCompanyEntity propertyCompany) {
        Session session = getCurrentSession();
        session.save(propertyCompany);
        session.flush();
    }

    /**
     * 添加物业公司权限信息
     * @param propertyCompanyRoleMap
     */
    @Override
    public void savePropertyCompanyRoleMapEntity(PropertyCompanyRoleMapEntity propertyCompanyRoleMap) {
        Session session = getCurrentSession();
        session.save(propertyCompanyRoleMap);
        session.flush();
    }

    /**
     * 更新物业公司信息
     * @param propertyCompany
     */
    @Override
    public void updatePropertyCompanyEntity(PropertyCompanyEntity propertyCompany) {
        Session session = getCurrentSession();
        session.update(propertyCompany);
        session.flush();
    }

    /**
     * 根据物业公司ID查询信息详情
     * @param companyId
     * @return
     */
    @Override
    public PropertyCompanyEntity queryPropertyCompanyById(String companyId) {
        String hql = "from PropertyCompanyEntity as o where companyId = :companyId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("companyId",companyId);
        if (query.list().size()>0){
            return (PropertyCompanyEntity)query.list().get(0);
        }else {
            PropertyCompanyEntity propertyCompany = new PropertyCompanyEntity();
            return propertyCompany;
        }
    }

    /**
     * 根据物业公司ID 删除物业公司信息
     * @param propertyCompanyEntity
     * @return
     */
    @Override
    public void delPropertyCompanyEntity(PropertyCompanyEntity propertyCompanyEntity) {
        Session session = getCurrentSession();
        session.delete(propertyCompanyEntity);
        session.flush();
    }

    @Override
    public List<HouseCompanyEntity> getHouseCompany(String companyName) {
        String hql = "from HouseCompanyEntity as hc where 1=1";
        if(!"".equals(companyName)){
            hql = hql + " and hc.areaId = '"+ companyName +"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<HouseCompanyEntity> houseAreaEntities = query.list();
        return houseAreaEntities;
    }

    /**
     * 联动 根据公司ID查询项目信息
     * @param pojectName
     * @return
     */
    @Override
    public List<HouseProjectEntity> getHouseProject(String pojectName) {
        String hql = "from HouseProjectEntity as hp where 1=1";
        if(!"".equals(pojectName) && null != pojectName){
            hql = hql + " and hp.companyId = '"+ pojectName +"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<HouseProjectEntity> houseProjectEntities = query.list();
        return houseProjectEntities;
    }


}
