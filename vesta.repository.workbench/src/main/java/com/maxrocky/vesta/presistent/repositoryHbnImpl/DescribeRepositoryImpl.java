package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.DescribeEntity;
import com.maxrocky.vesta.domain.repository.DescribeRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liudongxin on 2016/6/15.
 */
@Repository
public class DescribeRepositoryImpl implements DescribeRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:根据id获取信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public DescribeEntity get(String id) {
        String hql="FROM DescribeEntity WHERE value='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<DescribeEntity> describeList=query.list();
        if(describeList.size()>0){
            return describeList.get(0);
        }
        return null;
    }

    /**
     * Describe:创建简要描述
     * CreateBy:liudongxin
     * CreateOn:2016-01-17 05:19:23
     */
    @Override
    public void create(DescribeEntity describeEntity) {
        Session session = getCurrentSession();
        session.save(describeEntity);
        session.flush();
    }

    /**
     * Describe:修改简要描述
     * CreatedBy:liudongxin
     * ModifyBy:
     */
    @Override
    public void update(DescribeEntity describeEntity) {
        Session session = getCurrentSession();
        session.update(describeEntity);
        session.flush();
        session.close();
    }

    /**
     * CreatedBy:dl
     * Describe:
     * 删除简要描述
     * ModifyBy:
     */
    @Override
    public void delete() {
        String hql="delete FROM DescribeEntity";
        Query query = getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }

    @Override
    public List<DescribeEntity> getListsByIds(String ids) {
        String hql="FROM DescribeEntity WHERE value in (:ids)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameterList("ids",ids.split(","));
        List<DescribeEntity> describeList=query.list();
        if(describeList.size()>0){
            return describeList;
        }
        return null;
    }

    @Override
    public List<DescribeEntity> getListByThirdId(String thirdId) {
        String hql="FROM DescribeEntity WHERE thirdId in (:thirdId)";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("thirdId",thirdId);
        List<DescribeEntity> describeList=query.list();
        if(describeList.size()>0){
            return describeList;
        }
        return null;
    }
}
