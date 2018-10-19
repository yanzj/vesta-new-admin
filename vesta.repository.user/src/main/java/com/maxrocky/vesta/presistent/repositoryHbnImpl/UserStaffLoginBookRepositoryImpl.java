package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.UserStaffLoginBookEntity;
import com.maxrocky.vesta.domain.repository.UserStaffLoginBookRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tom on 2016/1/22 11:59.
 * Describe:员工登录信息Repository接口实现类
 */
@Repository
public class UserStaffLoginBookRepositoryImpl implements UserStaffLoginBookRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据员工Id获取有效登录信息
     * CreateBy:Tom
     * CreateOn:2016-01-22 12:01:38
     */
    @Override
    public UserStaffLoginBookEntity getLoginByStaffId(String staffId) {
        String hql = "FROM UserStaffLoginBookEntity WHERE state = :state AND staffId = :staffId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("state", UserStaffLoginBookEntity.STATE_NORMAL);
        query.setParameter("staffId", staffId);
        List<UserStaffLoginBookEntity> userStaffLoginBookEntityList = query.list();
        if(userStaffLoginBookEntityList.size() > 0){
            return userStaffLoginBookEntityList.get(0);
        }
        return null;
    }

    /**
     * Describe:修改员工登陆信息
     * CreateBy:Tom
     * CreateOn:2016-01-22 12:07:48
     */
    @Override
    public void update(UserStaffLoginBookEntity userStaffLoginBookEntity) {
        Session session = getCurrentSession();
        session.update(userStaffLoginBookEntity);
        session.flush();
    }

    /**
     * Describe:创建员工登录信息
     * CreateBy:Tom
     * CreateOn:2016-01-22 12:10:45
     */
    @Override
    public void create(UserStaffLoginBookEntity userStaffLoginBook) {
        Session session = getCurrentSession();
        session.save(userStaffLoginBook);
        session.flush();
    }

    /**
     * Describe:根据Id获取员工登陆信息
     * CreateBy:Tom
     * CreateOn:2016-01-22 01:52:12
     */
    @Override
    public UserStaffLoginBookEntity get(String loginId) {
        return (UserStaffLoginBookEntity)getCurrentSession().get(UserStaffLoginBookEntity.class, loginId);
    }
}
