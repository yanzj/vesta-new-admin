package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.InternalacceptanceHouseEntity;
import com.maxrocky.vesta.domain.repository.InspectionCRMRepository;
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
public class InspectionCRMRepositoryImpl implements InspectionCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:创建预验单
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     *
     */
    @Override
    public void create(InternalacceptanceHouseEntity internalacceptanceHouseEntity) {
        Session session = getCurrentSession();
        session.save(internalacceptanceHouseEntity);
        session.flush();
    }

    /**
     * Describe:修改预验单
     * CreateBy:dl
     * CreateOn:2016-01-19 10:37:54
     */
    @Override
    public void update(InternalacceptanceHouseEntity internalacceptanceHouseEntity) {
        Session session = getCurrentSession();
        session.update(internalacceptanceHouseEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:根据房间获取预验单
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:19:53
     *
     * @param housecode
     */
    @Override
    public InternalacceptanceHouseEntity getByHouseCode(String housecode) {
        String hql="FROM InternalacceptanceHouseEntity WHERE housecode='"+housecode+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<InternalacceptanceHouseEntity> InternalacceptanceHouseList=query.list();
        if(InternalacceptanceHouseList.size()>0){
            return InternalacceptanceHouseList.get(0);
        }
        return null;
    }

    /**
     * Describe:获取全部预验单信息
     * CreateBy:dl
     * CreateOn:2016-01-17 01:19:53
     */
    @Override
    public List<InternalacceptanceHouseEntity> getInternalacceptance() {
        String hql="FROM InternalacceptanceHouseEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<InternalacceptanceHouseEntity> InternalacceptanceHouseList=query.list();
        return InternalacceptanceHouseList;
    }
}
