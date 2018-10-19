package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HousePlanCRMEntity;
import com.maxrocky.vesta.domain.repository.HousePlanCRMRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liudongxin on 2016/4/22.
 */
@Repository
public class HousePlanCRMRepositoryImpl implements HousePlanCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * Describe:创建房屋计划信息
     * CreateBy:liudongxin
     * CreateOn:2016-04-22 10:01:12
     */
    @Override
    public void create(HousePlanCRMEntity deliveryPlanCrmEntity) {
        Session session = getCurrentSession();
        session.save(deliveryPlanCrmEntity);
        session.flush();
    }

    /**
     * CreatedBy:liudongxin
     * Describe:修改房屋计划信息
     * ModifyBy:
     */
    @Override
    public void update(HousePlanCRMEntity deliveryPlanCrmEntity) {
        Session session = getCurrentSession();
        session.update(deliveryPlanCrmEntity);
        session.flush();
        session.close();
    }

    /**
     * Describe:根据房屋id获取信息
     * CreateBy:liudongxin
     * CreateOn:2016-01-14 09:40:37
     */
    @Override
    public HousePlanCRMEntity getById(String id) {
        String hql="FROM HousePlanCRMEntity WHERE roomId='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<HousePlanCRMEntity> planCRMList=query.list();
        if(planCRMList.size()>0){
            return planCRMList.get(0);
        }
        return null;
    }

    @Override
    public HousePlanCRMEntity getByIdAndPlanId(String rooId, String planId) {
        String hql="FROM HousePlanCRMEntity WHERE roomId='"+rooId+"' and planId='"+planId+"' ";
        Query query = getCurrentSession().createQuery(hql);
        List<HousePlanCRMEntity> planCRMList=query.list();
        if(planCRMList.size()>0){
            return planCRMList.get(0);
        }
        return null;
    }

    @Override
    public void updateHousePlanStateById(String planId) {
        String sql = "update  house_plan_crm  set STATE='1' where PLAN_ID=:planId  ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("planId",planId);
        query.executeUpdate();
    }

    @Override
    public void updateHousePlanStateByIdList(List<String> idList) {
        String sql = "update  house_plan_crm  set STATE='1' where PLAN_ID in(:idList)  ";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameterList("idList",idList);
        query.executeUpdate();
    }
}
