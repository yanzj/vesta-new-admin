package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseUserBookEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.repository.OwnersTenantsRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 业主、租户 持久层实现类
 */
@Repository
public class OwnersTenantsRepositoryImpl extends HibernateDaoImpl implements OwnersTenantsRepository {
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {

        return sessionFactory.openSession();
    }

    /**
     * 用户ID查询 用户信息表
     * @param userId
     * @return
     */
    @Override
    public List<UserInfoEntity> userInfoList(String userId) {
        StringBuffer hql = new StringBuffer();
        hql.append(" FROM UserInfoEntity AS ui where 1=1");
        hql.append(" AND ui.userId = :userId ");
        hql.append(" AND ui.userState = :userState ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("userId", userId);
        query.setParameter("userState", UserInfoEntity.USER_STATE_NORMAL);
        List<UserInfoEntity> user = query.list();
        return user;
    }

    @Override
    public List<Object[]> getOwnersTenants(String projectId) {
        String hql ="SELECT u.userType,h.projectName FROM HouseInfoEntity AS h ,HouseUserBookEntity AS hu ,UserInfoEntity AS u WHERE h.id = hu.houseId AND hu.userId= u.userId" +
                " AND hu.state='NORMAL'" +
                " AND h.projectId ='"+projectId+"'" ;
        Query query = getCurrentSession().createQuery(hql);
        List<Object[]> objects= query.list();
        return objects;
    }
}
