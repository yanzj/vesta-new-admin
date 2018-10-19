package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PropertyPriceSettingEntity;
import com.maxrocky.vesta.domain.repository.PropertyPriceSettingRepository;
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
 * Created by ZhangBailiang on 2016/2/16.
 * 物业管理 维修价格设置 持久层实现类
 */
@Repository
public class PropertyPriceSettingRepositoryImpl extends HibernateDaoImpl implements PropertyPriceSettingRepository {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {

        return sessionFactory.openSession();
    }
    /**
     * 根据当前登录人所负责项目ID查询列表
     * @param propertyPrice
     * @return
     */
    @Override
    public List<PropertyPriceSettingEntity> queryPriceSettingByProjectId(PropertyPriceSettingEntity propertyPrice,WebPage webPage) {
        StringBuffer hql = new StringBuffer("FROM PropertyPriceSettingEntity as pp WHERE 1=1");

        List<PropertyPriceSettingEntity> propertyCompanyEntities = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();

        // 默认条件 (登录人所负责项目ID)
        hql.append(SqlJoiningTogether.sqlStatement("pp.projectId", propertyPrice.getProjectId()));

        // 不为空则拼接项目名称
        if(null != propertyPrice.getProjectName() && !"".equals(propertyPrice.getProjectName())){
            hql.append(" and pp.projectName like ?");
            params.add("%"+ propertyPrice.getProjectName() +"%");
        }

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }

        propertyCompanyEntities = ( List<PropertyPriceSettingEntity>) getHibernateTemplate().find(hql.toString());

        return propertyCompanyEntities;
    }

    /**
     * 根据项目ID查询是否有项目名称及内容
     * @param project
     * @return
     */
    @Override
    public PropertyPriceSettingEntity queryPriceSetting(String project) {
        String hql = "FROM PropertyPriceSettingEntity WHERE projectId = :project";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("project", project);
        List<PropertyPriceSettingEntity> priceSetting = query.list();
        if(priceSetting.size() == 0){
            return null;
        }
        return priceSetting.get(0);
    }

    /**
     * 更新维修价格信息
     * @param priceSettingEntity
     * @return
     */
    @Override
    public boolean updatePropertyPriceSetting(PropertyPriceSettingEntity priceSettingEntity) {
        if(null != priceSettingEntity){
            Session session = getCurrentSession();
            session.update(priceSettingEntity);
            session.flush();
            return true;
        }
        return false;
    }

    /**
     * 添加维修价格信息
     * @param priceSettingEntity
     * @return
     */
    @Override
    public boolean savePropertyPriceSetting(PropertyPriceSettingEntity priceSettingEntity) {
        if(null != priceSettingEntity){
            Session session = getCurrentSession();
            session.save(priceSettingEntity);
            session.flush();
            return true;
        }
        return false;
    }

    /**
     * 根据维修价格ID查询信息
     * @param priceSettingId
     * @return
     */
    @Override
    public PropertyPriceSettingEntity queryPriceSettingById(String priceSettingId) {
        String hql = "FROM PropertyPriceSettingEntity WHERE priceSettingId = :priceSettingId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("priceSettingId", priceSettingId);
        List<PropertyPriceSettingEntity> priceSetting = query.list();
        if(priceSetting.size() == 0){
            return null;
        }
        return priceSetting.get(0);
    }

    /**
     * 根据维修价格ID 删除信息
     * @param priceSettingEntity
     * @return
     */
    @Override
    public boolean delPropertyPriceSetting(PropertyPriceSettingEntity priceSettingEntity) {
        if(null != priceSettingEntity){
            Session session = getCurrentSession();
            session.delete(priceSettingEntity);
            session.flush();
            return true;
        }
        return false;
    }
}
