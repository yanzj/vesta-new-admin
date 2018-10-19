package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.UserSettingEntity;
import com.maxrocky.vesta.domain.repository.UserSettingRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tom on 2016/1/17 13:49.
 * Describe:用户设置Repository接口实现类
 */
@Repository
public class UserSettingRepositoryImpl implements UserSettingRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据用户Id获取用户设置
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:50:01
     */
    @Override
    public UserSettingEntity get(String userId) {
        return (UserSettingEntity)getCurrentSession().get(UserSettingEntity.class, userId);
    }

    /**
     * Describe:创建用户设置
     * CreateBy:Tom
     * CreateOn:2016-01-19 11:06:06
     */
    @Override
    public void create(UserSettingEntity userSettingEntity) {
        Session session = getCurrentSession();
        session.save(userSettingEntity);
        session.flush();
    }

    /**
     * Describe:修改用户设置
     * CreateBy:Tom
     * CreateOn:2016-02-20 04:06:27
     */
    @Override
    public void update(UserSettingEntity userSettingEntity) {
        Session session = getCurrentSession();
        session.update(userSettingEntity);
        session.flush();
    }

    /**
     * 根据项目ID 查询用户ID
     * @param project
     * @return
     */
    @Override
    public List<UserSettingEntity> userSettingList(String project) {
        String hql = "from UserSettingEntity as o where projectId = :project";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("project", project);
        List<UserSettingEntity> userSetting = query.list();
        return userSetting;
    }
}
