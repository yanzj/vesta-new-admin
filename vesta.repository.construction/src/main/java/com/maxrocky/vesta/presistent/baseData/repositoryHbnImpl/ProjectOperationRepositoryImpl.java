package com.maxrocky.vesta.presistent.baseData.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.ProjectOperationEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectOperationRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/12/7.
 */
@Repository
public class ProjectOperationRepositoryImpl implements ProjectOperationRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addProjectOperation(ProjectOperationEntity projectOperationEntity) {
        Session session = getCurrentSession();
        session.save(projectOperationEntity);
        session.flush();
    }

    @Override
    public void updateProjectOperation(ProjectOperationEntity projectOperationEntity) {
        Session session = getCurrentSession();
        session.update(projectOperationEntity);
        session.flush();
    }

    @Override
    public ProjectOperationEntity getProjectOperationDetail(String optId) {
        String hql = "from ProjectOperationEntity where optId=:optId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("optId",optId);
        ProjectOperationEntity projectOperationEntity = (ProjectOperationEntity) query.uniqueResult();
        return projectOperationEntity;
    }

    @Override
    public List<ProjectOperationEntity> getProjectOperationList() {
        String hql = "from ProjectOperationEntity where state='1'";
        Query query = getCurrentSession().createQuery(hql);
        List<ProjectOperationEntity> projectOperationEntities = query.list();
        return projectOperationEntities;
    }
}
