package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.MaterialQuotaEntity;
import com.maxrocky.vesta.domain.repository.MaterialQuotaRepository;
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
public class MaterialQuotaRepositoryImpl implements MaterialQuotaRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addMaterialQuota(MaterialQuotaEntity materialQuotaEntity) {
        Session session = getCurrentSession();
        session.save(materialQuotaEntity);
        session.flush();
    }

    @Override
    public List<MaterialQuotaEntity> MaterialQuotaList(String materialId) {
        String hql = "from MaterialQuotaEntity where materialId=:materialId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("materialId",materialId);
        List<MaterialQuotaEntity> materialQuotaEntities = query.list();
        return materialQuotaEntities;
    }
}
