package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PropertyFeedbackEntity;
import com.maxrocky.vesta.domain.repository.PropertyFeedbackRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liudongxin on 2016/1/22.
 * 物业报修评价数据操作实现
 */
@Repository
public class PropertyFeedbackRepositoryImpl implements PropertyFeedbackRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 添加评价
     * @param propertyFeedbackEntity
     */
    @Override
    public void createFeedback(PropertyFeedbackEntity propertyFeedbackEntity) {
        Session session = getCurrentSession();
        session.save(propertyFeedbackEntity);
        session.flush();
    }

    /**
     * 通过id，获取评价信息
     * @param id ：报修单id
     * @return
     */
    @Override
    public PropertyFeedbackEntity getFeedback(String id) {
        String hql="FROM PropertyFeedbackEntity WHERE  repairId='"+id+"' AND state='0' ORDER BY createDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyFeedbackEntity> feedbackList=query.list();
        if(feedbackList.size()>0){
            return feedbackList.get(0);
        }
        return null;
    }

    /**
     * 修改评价信息
     * @param propertyFeedbackEntity
     */
    @Override
    public void updateFeedback(PropertyFeedbackEntity propertyFeedbackEntity) {
        Session session = getCurrentSession();
        session.update(propertyFeedbackEntity);
        session.flush();
        session.close();
    }
}