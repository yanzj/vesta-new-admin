package com.maxrocky.vesta.presistent.baseData.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectImagesRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by jiazefeng on 2016/10/20.
 */
@Repository
public class ProjectImagesRepositoryImpl extends HibernateDaoImpl implements ProjectImagesRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public boolean addProjectImagesInfo(ProjectImagesEntity projectImagesEntity) {
//        this.save(projectImagesEntity);
        Session session = getCurrentSession();
        session.save(projectImagesEntity);
        session.flush();
        session.close();
        return true;
    }
}
