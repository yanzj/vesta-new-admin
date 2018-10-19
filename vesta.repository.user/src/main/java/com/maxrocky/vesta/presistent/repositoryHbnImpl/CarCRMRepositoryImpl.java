package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.CarCRMEntity;
import com.maxrocky.vesta.domain.repository.CarCRMRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liudongxin on 2016/4/28.
 */
@Repository
public class CarCRMRepositoryImpl implements CarCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据id、会员编号获取信息
     * CreateBy:lingdongxin
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public CarCRMEntity get(String id, String memberId) {
        String hql="FROM CarCRMEntity WHERE id='"+id+"' and memberId='"+memberId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<CarCRMEntity> familyCRMList=query.list();
        if(familyCRMList.size()>0){
            return familyCRMList.get(0);
        }
        return null;
    }

    /**
     * Describe:创建车辆信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-19 10:01:12
     */
    @Override
    public void create(CarCRMEntity carCRMEntity) {
        Session session = getCurrentSession();
        session.save(carCRMEntity);
        session.flush();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改车辆信息
     * ModifyBy:
     */
    @Override
    public void update(CarCRMEntity carCRMEntity) {
        Session session = getCurrentSession();
        session.update(carCRMEntity);
        session.flush();
        session.close();
    }
    /**
     * Describe:获取会员的车辆信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public List<CarCRMEntity> getcarinfo() {
        String hql="FROM CarCRMEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<CarCRMEntity> carList=query.list();
        return carList;
    }
}
