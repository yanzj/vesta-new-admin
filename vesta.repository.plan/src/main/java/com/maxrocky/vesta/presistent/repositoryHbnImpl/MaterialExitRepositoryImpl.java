package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.MaterialExitEntity;
import com.maxrocky.vesta.domain.repository.MaterialExitRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/5/24.
 */
@Repository
public class MaterialExitRepositoryImpl implements MaterialExitRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addMaterialExit(MaterialExitEntity materialExitEntity) {
        Session session = getCurrentSession();
        session.save(materialExitEntity);
        session.flush();
    }

    @Override
    public List<MaterialExitEntity> getMaterialExitList() {
        String hql = "from MaterialExitEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<MaterialExitEntity> materialExitEntities = query.list();
        return materialExitEntities;
    }
}
