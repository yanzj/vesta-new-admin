package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.RenovationEntity;
import com.maxrocky.vesta.domain.repository.RenovationRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Annie on 2016/2/23.
 */
@Repository
public class RenovationRepositoryImpl implements RenovationRepository{
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<RenovationEntity> renovation() {
        String hql ="FROM RenovationEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<RenovationEntity> renovationEntities = query.list();
        return renovationEntities;
    }
}
