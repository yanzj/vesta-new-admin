package com.maxrocky.vesta.presistent.inspectAcceptance.repositoryHbnImpl;

import com.maxrocky.vesta.domain.inspectAcceptance.model.ProjectExamineTargetDetailsEntity;
import com.maxrocky.vesta.domain.inspectAcceptance.repository.ProjectExamineTargetDetailsRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by jiazefeng on 2016/10/20.
 */
@Repository
public class ProjectExamineTargetDetailsRepositoryImpl extends HibernateDaoImpl implements ProjectExamineTargetDetailsRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;
    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    @Override
    public boolean addProjectExamineTargetInfo(ProjectExamineTargetDetailsEntity projectExamineTargetDetailsEntity) {
//        this.save(projectExamineTargetDetailsEntity);
        Session session=getCurrentSession();
        session.save(projectExamineTargetDetailsEntity);
        session.flush();
        session.close();
        return true;

    }
}
