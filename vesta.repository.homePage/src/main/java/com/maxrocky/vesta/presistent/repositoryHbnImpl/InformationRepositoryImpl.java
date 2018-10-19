package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.InformationEntity;
import com.maxrocky.vesta.domain.model.PropertyServicesEntity;
import com.maxrocky.vesta.domain.repository.InformationRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Annie on 2016/2/22.
 * 首页服务信息
 */
@Repository
public class InformationRepositoryImpl implements InformationRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<PropertyServicesEntity> information(String projectId) {
        String hql ="FROM PropertyServicesEntity AS PS WHERE PS.projectId = :projectId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId", projectId);
        List<PropertyServicesEntity> propertyServices = query.list();
        return propertyServices;
    }
}
