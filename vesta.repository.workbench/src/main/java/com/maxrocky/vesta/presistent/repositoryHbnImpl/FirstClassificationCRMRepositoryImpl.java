package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.FirstClassificationEntity;
import com.maxrocky.vesta.domain.repository.FirstClassificationCRMRepository;
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
public class FirstClassificationCRMRepositoryImpl implements FirstClassificationCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    /**
     * Describe:创建一级分类
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    @Override
    public void create(FirstClassificationEntity firstClassificationEntity) {
        Session session = getCurrentSession();
        session.save(firstClassificationEntity);
        session.flush();
    }
    /**
     * CreatedBy:dl
     * Describe:
     * 修改一级分类
     * ModifyBy:
     */
    @Override
    public void update(FirstClassificationEntity firstClassificationEntity) {
        Session session = getCurrentSession();
        session.update(firstClassificationEntity);
        session.flush();
        session.close();
    }
    /**
     * Describe:根据id获取信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     *
     * @param id
     */
    @Override
    public FirstClassificationEntity get(String id) {
        String hql="FROM FirstClassificationEntity WHERE VALUE='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<FirstClassificationEntity> FirstClassificationList=query.list();
        if(FirstClassificationList.size()>0){
            return FirstClassificationList.get(0);
        }
        return null;
    }

    /**
     * CreatedBy:dl
     * Describe:
     * 删除一级分类
     * ModifyBy:
     */
    @Override
    public void delete() {
        String hql="delete FROM FirstClassificationEntity";
        Query query = getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }

    @Override
    public List<FirstClassificationEntity> getFirstClassification() {
        String hql = "from FirstClassificationEntity";
        Query query = getCurrentSession().createQuery(hql);
        return (List<FirstClassificationEntity>)query.list();
    }

}
