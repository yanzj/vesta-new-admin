package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PropertyServicesEntity;
import com.maxrocky.vesta.domain.repository.PropertyServicesRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/1/19.
 * 物业服务 持久层实现类
 */
@Repository
public class PropertyServicesRepositoryImpl extends HibernateDaoImpl implements PropertyServicesRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() { return sessionFactory.openSession();  }

    /**
     * 初始化物业服务信息
     * @param propertyServicesEntity
     * @param webPage
     * @return
     */
    @Override
    public List<PropertyServicesEntity> queryPropertyServicesMessage(PropertyServicesEntity propertyServicesEntity, WebPage webPage) {
        StringBuffer hql = new StringBuffer("FROM PropertyServicesEntity as ps WHERE 1=1");
        List<PropertyServicesEntity> propertyServicesEntityList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        // 初始化 登陆人所负责范围
        hql.append(SqlJoiningTogether.sqlStatement("ps.projectId", propertyServicesEntity.getProjectId()));

        // 不为空则拼接搜索条件 单位
        if(null != propertyServicesEntity.getServicesName() && !"".equals(propertyServicesEntity.getServicesName())){
            hql.append(" and ps.servicesName like ?");
            params.add("%"+ propertyServicesEntity.getServicesName() +"%");
        }
        // 不为空则拼接搜索条件 电话
        if(null != propertyServicesEntity.getServicesPhone() && !"".equals(propertyServicesEntity.getServicesPhone())){
            hql.append(" and ps.servicesPhone like ?");
            params.add("%"+ propertyServicesEntity.getServicesPhone() +"%");
        }
        // 不为空则拼接搜索条件 类别
        if(null != propertyServicesEntity.getServicesType() && !"".equals(propertyServicesEntity.getServicesType())){
            if(!"0".equals(propertyServicesEntity.getServicesType())){
                hql.append(" and ps.servicesType = ?");
                params.add(propertyServicesEntity.getServicesType());
            }
        }
        hql.append(" ORDER BY ps.addMessageTime DESC");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }

        propertyServicesEntityList =  ( List<PropertyServicesEntity>)getHibernateTemplate().find(hql.toString());

        return propertyServicesEntityList;
    }

    /**
     * 根据ID查询物业服务信息
     * @param servicesId
     * @return
     */
    @Override
    public List<PropertyServicesEntity> queryPropertyServicesById(String servicesId) {
        String hql = "FROM PropertyServicesEntity AS ps where ps.servicesId = :servicesId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("servicesId", servicesId);
        List<PropertyServicesEntity> propertyServicesEntityList = query.list();
        return propertyServicesEntityList;
    }

    /**
     * 根据服务信息ID删除信息
     * @param servicesId
     * @return
     */
    @Override
    public boolean removePropertyServicesById(String servicesId) {
        PropertyServicesEntity propertyServicesEntity = getPropertyServicesById(servicesId);
        if(propertyServicesEntity != null){
            Session session = getCurrentSession();
            session.delete(propertyServicesEntity);
            session.flush();
            return true;
        }
        return false;
    }

    /**
     * 新增服务信息
     * @param propertyServicesEntity
     */
    @Override
    public void addPropertyServices(PropertyServicesEntity propertyServicesEntity) {
        Session session = getCurrentSession();
        session.save(propertyServicesEntity);
        session.flush();
    }

    /**
     * 根据服务信息ID查询信息
     * @param servicesId
     * @return
     */
    @Override
    public PropertyServicesEntity getPropertyServicesById(String servicesId) {
        return (PropertyServicesEntity)getCurrentSession().get(PropertyServicesEntity.class, servicesId);
    }

    /**
     * 更新服务信息
     * @param propertyServicesEntity
     */
    @Override
    public void modifyPropertyServices(PropertyServicesEntity propertyServicesEntity) {
        Session session = getCurrentSession();
        session.update(propertyServicesEntity);
        session.flush();
    }
}
