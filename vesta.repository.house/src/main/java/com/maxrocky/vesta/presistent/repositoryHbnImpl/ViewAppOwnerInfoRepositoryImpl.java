package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ViewAppOwnerInfoEntity;
import com.maxrocky.vesta.domain.repository.ViewAppOwnerInfoRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tom on 2016/1/18 16:16.
 * Describe:基础业主信息Repository接口实现类
 */
@Repository
public class ViewAppOwnerInfoRepositoryImpl implements ViewAppOwnerInfoRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据业主Id查询业主信息
     * CreateBy:Tom
     * CreateOn:2016-01-18 04:17:48
     */
    @Override
    public ViewAppOwnerInfoEntity getByOwnerId(int ownerId) {
        String hql = "FROM ViewAppOwnerInfoEntity WHERE ownerId =:ownerId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("ownerId", ownerId);
        List<ViewAppOwnerInfoEntity> viewAppOwnerInfoEntityList = query.list();
        if(viewAppOwnerInfoEntityList.size() == 0){
            return null;
        }
        return viewAppOwnerInfoEntityList.get(0);
    }
}
