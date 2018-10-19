package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.RoomLocationEntity;
import com.maxrocky.vesta.domain.repository.RoomLocationRepository;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/4/22.
 */
@Repository
public class RoomLocationRepositoryImpl implements RoomLocationRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void SaveLocation(RoomLocationEntity roomLocationEntity) {
        Session session = getCurrentSession();
        session.save(roomLocationEntity);
        session.flush();
    }

    @Override
    public void UpdateLocation(RoomLocationEntity roomLocationEntity) {
        Session session = getCurrentSession();
        session.update(roomLocationEntity);
        session.flush();
    }

    @Override
    public RoomLocationEntity getRoomLocation() {
        String hql = "from RoomLocationEntity";
        Query query = getCurrentSession().createQuery(hql);
        RoomLocationEntity roomLocationEntity = (RoomLocationEntity) query.uniqueResult();
        return roomLocationEntity;
    }

    /**
     * 获取房屋位置列表
     * @return List<RoomLocationEntity>
     */
    public List<RoomLocationEntity> getRoomLocations(){
        Session session = getCurrentSession();
        String hql = "from RoomLocationEntity";
        List<RoomLocationEntity> list = session.createQuery(hql).list();
        session.flush();
        session.close();
        return list;
    }
}
