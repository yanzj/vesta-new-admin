package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.MaterialPlanEntity;
import com.maxrocky.vesta.domain.repository.MaterialPlanRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/5/23.
 */
 @Repository
 public class MaterialPlanRepositoryImpl implements MaterialPlanRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<MaterialPlanEntity> getMaterialPlanList(String projectId) {
        String hql =  "from MaterialPlanEntity where projectId=:projectId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        List<MaterialPlanEntity> materialPlanEntities = query.list();
        return materialPlanEntities;
    }

    @Override
    public MaterialPlanEntity getMaterialPlanDetail(String materialId) {
        String hql = "from MaterialPlanEntity where materialId=:materialId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("materialId",materialId);
        MaterialPlanEntity materialPlanEntity = (MaterialPlanEntity) query.uniqueResult();
        return materialPlanEntity;
    }

    @Override
    public void addMaterialPlan(MaterialPlanEntity materialPlanEntity) {
        Session session = getCurrentSession();
        session.save(materialPlanEntity);
        session.flush();
    }

    @Override
    public List<MaterialPlanEntity> getMaterialPlans() {
        String hql =  "from MaterialPlanEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<MaterialPlanEntity> materialPlanEntities = query.list();
        return materialPlanEntities;
 }
}
