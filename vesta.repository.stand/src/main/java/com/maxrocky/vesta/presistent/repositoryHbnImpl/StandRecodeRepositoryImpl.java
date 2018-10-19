package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.StandRecodeEntity;
import com.maxrocky.vesta.domain.repository.StandRecodeRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/5/17.
 */
@Repository
public class StandRecodeRepositoryImpl implements StandRecodeRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addStandRecode(StandRecodeEntity standRecodeEntity) {
        Session session = getCurrentSession();
        session.save(standRecodeEntity);
        session.flush();
    }

    @Override
    public void updateStandRecode(StandRecodeEntity standRecodeEntity) {
        Session session = getCurrentSession();
        session.update(standRecodeEntity);
        session.flush();
    }

    @Override
    public List<StandRecodeEntity> getStandRecodeList(String standId) {
        String hql = "from StandRecodeEntity where standId=:standId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("standId",standId);
        List<StandRecodeEntity> standRecodeEntities = query.list();
        return standRecodeEntities;
    }

    @Override
    public StandRecodeEntity getStandRecodeDetail(String id) {
        String hql = "from StandRecodeEntity where id=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        StandRecodeEntity standRecodeEntity = (StandRecodeEntity) query.uniqueResult();
        return standRecodeEntity;
    }

}
