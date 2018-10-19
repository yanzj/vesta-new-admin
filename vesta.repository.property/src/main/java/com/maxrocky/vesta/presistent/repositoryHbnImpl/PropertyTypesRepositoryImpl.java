package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PropertyTypeEntity;
import com.maxrocky.vesta.domain.repository.PropertyTypesRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunmei on 2016/2/15.
 */
@Repository
public class PropertyTypesRepositoryImpl extends HibernateDaoImpl implements PropertyTypesRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() { return sessionFactory.openSession();  }

    @Override
    public List<PropertyTypeEntity> queryPropertyTypeMessage(PropertyTypeEntity propertyTypeEntity, WebPage page) {
        StringBuffer hql = new StringBuffer("FROM PropertyTypeEntity as ps WHERE 1=1");
        List<PropertyTypeEntity> propertyTypeList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();

        hql.append(" ORDER BY ps.typeId ASC");
        if(page != null){
            return this.findByPage(hql.toString(), page, params);
        }

        propertyTypeList =  ( List<PropertyTypeEntity>)getHibernateTemplate().find(hql.toString());

        return propertyTypeList;
    }


    @Override
    public boolean removePropertyTypeById(String typeId) {
        PropertyTypeEntity propertyTypeEntity = getPropertyTypeById(typeId);
        if(propertyTypeEntity != null){
            Session session = getCurrentSession();
            session.delete(propertyTypeEntity);
            session.flush();
            return true;
        }
        return false;
    }

    @Override
    public void addPropertyType(PropertyTypeEntity propertyTypeEntity) {
        Session session = getCurrentSession();
        session.save(propertyTypeEntity);
        session.flush();

    }

    /**
     * 根据广告类型ID查询信息
     * @param typeId
     * @return
     */
    @Override
    public PropertyTypeEntity getPropertyTypeById(String typeId) {
        return (PropertyTypeEntity)getCurrentSession().get(PropertyTypeEntity.class, typeId);
    }

    @Override
    public void modifyPropertyType(PropertyTypeEntity propertyTypeEntity) {
        Session session = getCurrentSession();
        session.update(propertyTypeEntity);
        session.flush();
    }
}
