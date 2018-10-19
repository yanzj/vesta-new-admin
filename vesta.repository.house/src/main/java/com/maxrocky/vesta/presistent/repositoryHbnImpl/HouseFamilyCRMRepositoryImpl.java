package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseFamilyCRMEntity;
import com.maxrocky.vesta.domain.repository.HouseFamilyCRMRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;

/**
 * Created by liudongxin on 2016/4/13.
 */
public class HouseFamilyCRMRepositoryImpl implements HouseFamilyCRMRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    /**
     * Describe:根据业主关系Id获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public HouseFamilyCRMEntity get(String id) {
        return (HouseFamilyCRMEntity)getCurrentSession().get(HouseFamilyCRMEntity.class, id);
    }

    /**
     * Describe:创建家庭关系信息
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:01:12
     */
    @Override
    public void create(HouseFamilyCRMEntity houseFamilyCRMEntity) {
        Session session = getCurrentSession();
        session.save(houseFamilyCRMEntity);
        session.flush();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 修改家庭关系信息
     * ModifyBy:
     */
    @Override
    public void updateHouseInfo(HouseFamilyCRMEntity houseFamilyCRMEntity) {
        Session session = getCurrentSession();
        session.update(houseFamilyCRMEntity);
        session.flush();
        session.close();
    }

}
