package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ThirdTypeEntity;
import com.maxrocky.vesta.domain.repository.ThirdTypeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by chen on 2016/4/22.
 */
@Repository
public class ThirdTypeRepositoryImpl implements ThirdTypeRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void AddThirdType(ThirdTypeEntity thirdTypeEntity) {
        Session session = getCurrentSession();
        session.save(thirdTypeEntity);
        session.flush();
    }

    @Override
    public void UpdateThirdType(ThirdTypeEntity thirdTypeEntity) {
        Session session = getCurrentSession();
        session.update(thirdTypeEntity);
        session.flush();
    }
}
