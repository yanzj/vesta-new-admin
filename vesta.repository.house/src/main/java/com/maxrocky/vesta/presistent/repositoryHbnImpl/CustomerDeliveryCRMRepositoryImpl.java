package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.CustomerDeliveryEntity;
import com.maxrocky.vesta.domain.repository.CustomerDeliveryCRMRepository;
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
public class CustomerDeliveryCRMRepositoryImpl implements CustomerDeliveryCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    /**
     * Describe:创建预验单
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    @Override
    public void create(CustomerDeliveryEntity customerDeliveryEntity) {
        Session session = getCurrentSession();
        session.save(customerDeliveryEntity);
        session.flush();
    }

    /**
     * Describe:修改预验单
     * CreateBy:dl
     * CreateOn:2016-01-19 10:37:54
     */
    @Override
    public void update(CustomerDeliveryEntity customerDeliveryEntity) {
        Session session = getCurrentSession();
        session.update(customerDeliveryEntity);
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
    public CustomerDeliveryEntity getByHouseCode(String housecode) {
        String hql="FROM CustomerDeliveryEntity WHERE housecode='"+housecode+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<CustomerDeliveryEntity> customerDeliveryList=query.list();
        if(customerDeliveryList.size()>0){
            return customerDeliveryList.get(0);
        }
        return null;
    }

    /**
     * Describe:获取全部预验单信息
     * CreateBy:dl
     * CreateOn:2016-01-17 01:19:53
     */
    @Override
    public List<CustomerDeliveryEntity> getCustomerDelivery() {
        String hql="FROM CustomerDeliveryEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<CustomerDeliveryEntity> customerDeliveryList=query.list();
        return customerDeliveryList;
    }
}
