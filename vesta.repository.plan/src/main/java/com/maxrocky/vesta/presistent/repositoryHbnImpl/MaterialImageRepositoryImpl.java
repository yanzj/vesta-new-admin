package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.MaterialImageEntity;
import com.maxrocky.vesta.domain.repository.MaterialImageRepository;
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
public class MaterialImageRepositoryImpl implements MaterialImageRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addMaterialImage(MaterialImageEntity materialImageEntity) {
        Session session = getCurrentSession();
        session.save(materialImageEntity);
        session.flush();
    }

    @Override
    public List<MaterialImageEntity> getImageList(String bussinessId) {
        String hql = "from MaterialImageEntity where bussinessId=:bussinessId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("bussinessId",bussinessId);
        List<MaterialImageEntity> materialImageEntities = query.list();
        return materialImageEntities;
    }
}
