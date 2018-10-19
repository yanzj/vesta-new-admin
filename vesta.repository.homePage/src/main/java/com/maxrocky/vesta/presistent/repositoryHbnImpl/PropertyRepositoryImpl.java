package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ImgActivitiesEntity;
import com.maxrocky.vesta.domain.model.PropertyEntity;
import com.maxrocky.vesta.domain.model.SharingActivitiesEntity;
import com.maxrocky.vesta.domain.repository.PropertyRepository;
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
 * Created by sunmei on 2016/2/26.
 */
@Repository
public class PropertyRepositoryImpl extends HibernateDaoImpl implements PropertyRepository{
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {

        return sessionFactory.openSession();
    }
    @Override
    public List<PropertyEntity> PropertyManage(WebPage webPage) {
        StringBuffer hql = new StringBuffer("FROM PropertyEntity p  WHERE 1=1");
        List<PropertyEntity> propertyList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();
        hql.append(" ORDER BY p.sort ASC");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }

        propertyList = (List<PropertyEntity>) getHibernateTemplate().find(hql.toString());

        return propertyList;
    }

    @Override
    public PropertyEntity getPropertyById(String id) {
            return (PropertyEntity)getCurrentSession().get(PropertyEntity.class, id);
    }

    @Override
    public PropertyEntity getPropertyManageBysortDown(int sort) {
        String hql = "from PropertyEntity where  sort>"+sort+"ORDER BY sort ASC";
        Query query = getCurrentSession().createQuery(hql);
        if(query.list().size()!=0){
            return (PropertyEntity)query.list().get(0);
        }else{
            return null;
        }


    }

    @Override
    public PropertyEntity getPropertyManageBysortUp(int sort) {
        String hql = "from PropertyEntity where sort<"+sort+"ORDER BY sort DESC";
        Query query = getCurrentSession().createQuery(hql);
        if(query.list().size()!=0){
            return (PropertyEntity)query.list().get(0);
        }else{
            return null;
        }

    }

    @Override
    public void updateProperty(PropertyEntity propertyEntity) {
        Session tempSession = getCurrentSession();
        if(propertyEntity != null){
            tempSession.update(propertyEntity);
            tempSession.flush();
        }
    }
}
