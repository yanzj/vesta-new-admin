package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseLocationEntity;
import com.maxrocky.vesta.domain.repository.HouseLocationCRMRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dl on 2016/5/10.
 */
@Repository
public class HouseLocationCRMRepositoryImpl implements HouseLocationCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    /**
     * Describe:根据id获取信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     *
     * @param id
     */
    @Override
    public HouseLocationEntity get(String id) {
        String hql="FROM HouseLocationEntity WHERE id='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HouseLocationEntity> HouseLocationList=query.list();
        if(HouseLocationList.size()>0){
            return HouseLocationList.get(0);
        }
        return null;
    }

    /**
     * Describe:创建房屋位置
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     *
     * @param houseLocationEntity
     */
    @Override
    public void create(HouseLocationEntity houseLocationEntity) {
            Session session = getCurrentSession();
            session.save(houseLocationEntity);
            session.flush();
        }


    /**
     * CreatedBy:dl
     * Describe:
     * 修改房屋位置
     * ModifyBy:
     *
     * @param houseLocationEntity
     */
    @Override
    public void update(HouseLocationEntity houseLocationEntity) {
        Session session = getCurrentSession();
        session.update(houseLocationEntity);
        session.flush();
        session.close();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:删除
     * ModifyBy:
     */
    @Override
    public void delete() {
        String hql="delete FROM HouseLocationEntity";
        Query query = getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }
}
