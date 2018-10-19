package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SystemConfigEntity;
import com.maxrocky.vesta.domain.repository.SystemConfigRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tom on 2016/1/13 20:14.
 * Describe:系统配置Repository接口实现类
 */
@Repository
public class SystemConfigRepositoryImpl implements SystemConfigRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据系统代码获取系统配置信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 08:15:11
     */
    @Override
    public SystemConfigEntity get(String configCode) {
        return (SystemConfigEntity)getCurrentSession().get(SystemConfigEntity.class, configCode);
    }

    /**
     * Describe:返回系统配置列表
     * CreateBy:Tom
     * CreateOn:2016-03-08 11:07:27
     */
    @Override
    public List<SystemConfigEntity> getList() {
        String hql = "FROM SystemConfigEntity";
        return getCurrentSession().createQuery(hql).list();
    }

}
