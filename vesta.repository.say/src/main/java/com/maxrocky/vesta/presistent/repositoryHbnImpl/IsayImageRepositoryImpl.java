package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.IsayImageEntity;
import com.maxrocky.vesta.domain.repository.IsayImageRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/1/21.
 */
@Repository
public class IsayImageRepositoryImpl implements IsayImageRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void AddSayImage(IsayImageEntity isayImageEntity) {
        Session session = getCurrentSession();
        session.save(isayImageEntity);
        session.flush();
    }

    @Override
    public List<IsayImageEntity> getImageList(String bussinessId) {
        String hql = "from IsayImageEntity where bussinessId = :bussinessId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("bussinessId",bussinessId);
        List<IsayImageEntity> isayImageEntities = query.list();
        return isayImageEntities;
    }
}
