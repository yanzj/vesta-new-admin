package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.RepairModeEntity;
import com.maxrocky.vesta.domain.model.WorkTimeEntity;
import com.maxrocky.vesta.domain.repository.RepairModeRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 27978 on 2016/8/8.
 */
@Repository
public class RepairModeRepositoryImpl implements RepairModeRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取维修方式列表
    */
    @Override
    public List<RepairModeEntity> getRepairModeList(String thirdId) {
        StringBuffer hql = new StringBuffer(" from RepairModeEntity rme where rme.thirdId=:thirdId ");
        Query query = this.getCurrentSession().createQuery(hql.toString());
        query.setParameter("thirdId", thirdId);
        List<RepairModeEntity> list = query.list();
        return list;
    }

    @Override
    public WorkTimeEntity getStandardDate(String repairMode) {
        StringBuffer hql = new StringBuffer(" from WorkTimeEntity rme where rme.repairId=:repairMode ");
        Query query = this.getCurrentSession().createQuery(hql.toString());
        query.setParameter("repairMode", repairMode);
        List<WorkTimeEntity> list = query.list();
        if(list!=null && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }

    }
}
