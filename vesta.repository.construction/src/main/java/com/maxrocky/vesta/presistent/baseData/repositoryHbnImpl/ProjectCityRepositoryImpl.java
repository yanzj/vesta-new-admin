package com.maxrocky.vesta.presistent.baseData.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.ProjectCityEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectCityRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/12/6.
 */
@Repository
public class ProjectCityRepositoryImpl implements ProjectCityRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addProjectCity(ProjectCityEntity projectCityEntity) {
        Session session = getCurrentSession();
        session.save(projectCityEntity);
        session.flush();
    }

    @Override
    public void updateProjectCity(ProjectCityEntity projectCityEntity) {
        Session session = getCurrentSession();
        session.update(projectCityEntity);
        session.flush();
    }

    @Override
    public List<ProjectCityEntity> getAllCityList() {
        String hql ="from ProjectCityEntity where state='1'";
        Query query = getCurrentSession().createQuery(hql);
        List<ProjectCityEntity> projectCityEntities = query.list();
        return projectCityEntities;
    }

    @Override
    public List<ProjectCityEntity> getCityByOptId(String optId) {
        String hql ="from ProjectCityEntity where state='1' and optId=:optId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("optId",optId);
        List<ProjectCityEntity> projectCityEntities = query.list();
        return projectCityEntities;
    }

    @Override
    public ProjectCityEntity getCityDetail(String cityId) {
        String hql = "from ProjectCityEntity where cityId=:cityId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("cityId",cityId);
        ProjectCityEntity projectCityEntity = (ProjectCityEntity) query.uniqueResult();
        return projectCityEntity;
    }

    @Override
    public boolean checkCityDetail(String cityName) {
        String hql ="from ProjectCityEntity where state='1' and cityName=:cityName";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("cityName",cityName);
        if(query.list()!=null && query.list().size()>0){
            return false;
        }else{
            return true;
        }

    }

    @Override
    public List<Object[]> getProejctListByOptId(String optId) {
        String sql = "SELECT pp.PROJECT_ID,pp.PROJECT_NAME FROM project_project pp " +
                "LEFT JOIN project_city pc ON pc.CITY_ID = pp.CITY_ID " +
                "WHERE pc.STATE='1' AND pp.PROJECT_STATE='1' AND pc.OPERATION_ID='"+optId+"'";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<Object[]> list = query.list();
        return list;
    }
}
