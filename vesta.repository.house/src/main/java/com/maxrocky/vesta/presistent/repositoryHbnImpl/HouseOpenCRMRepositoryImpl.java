package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseOpenEntity;
import com.maxrocky.vesta.domain.repository.HouseOpenCRMRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dl on 2016/5/9.
 */
@Repository
public class HouseOpenCRMRepositoryImpl implements HouseOpenCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    /**
     * Describe:创建业主开放日
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    @Override
    public void create(HouseOpenEntity HouseOpenEntity) {
        Session session = getCurrentSession();
        session.save(HouseOpenEntity);
        session.flush();
    }

    /**
     * Describe:修改业主开放日
     * CreateBy:dl
     * CreateOn:2016-01-19 10:37:54
     */
    @Override
    public void update(HouseOpenEntity HouseOpenEntity) {
        Session session = getCurrentSession();
        session.update(HouseOpenEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:根据房间获取业主开放日
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:19:53
     *
     * @param housecode
     */
    @Override
    public HouseOpenEntity getByHouseCode(String housecode) {
        String hql="FROM HouseOpenEntity WHERE housecode='"+housecode+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseOpenEntity> openHouseList=query.list();
        if(openHouseList.size()>0){
            return openHouseList.get(0);
        }
        return null;
    }

    /**
     * Describe:获取全部业主开放日信息
     * CreateBy:dl
     * CreateOn:2016-01-17 01:19:53
     */
    @Override
    public List<HouseOpenEntity> getOpenHouse() {
        String hql="FROM HouseOpenEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseOpenEntity> openHouseList=query.list();
        return openHouseList;
    }
}
