package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SecondClassificationEntity;
import com.maxrocky.vesta.domain.repository.SecondClassificationCRMRepository;
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
public class SecondClassificationCRMRepositoryImpl implements SecondClassificationCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    /**
     * Describe:创建二级分类
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    @Override
    public void create(SecondClassificationEntity secondClassificationEntity) {
        Session session = getCurrentSession();
        session.save(secondClassificationEntity);
        session.flush();
    }
    /**
     * CreatedBy:dl
     * Describe:
     * 修改二级分类
     * ModifyBy:
     */
    @Override
    public void update(SecondClassificationEntity secondClassificationEntity) {
        Session session = getCurrentSession();
        session.update(secondClassificationEntity);
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
    public SecondClassificationEntity get(String id) {
        String hql="FROM SecondClassificationEntity WHERE value='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<SecondClassificationEntity> SecondClassificationList=query.list();
        if(SecondClassificationList.size()>0){
            return SecondClassificationList.get(0);
        }
        return null;
    }

    /**
     * CreatedBy:dl
     * Describe:
     * 删除二级分类
     * ModifyBy:
     */
    @Override
    public void delete() {
        String hql="delete FROM SecondClassificationEntity";
        Query query = getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }

    @Override
    public List<SecondClassificationEntity> getSecondClassification(String firstid) {
        String hql = "from SecondClassificationEntity where firstId = :firstid ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("firstid",firstid);
        return (List<SecondClassificationEntity>)query.list();
    }
}
