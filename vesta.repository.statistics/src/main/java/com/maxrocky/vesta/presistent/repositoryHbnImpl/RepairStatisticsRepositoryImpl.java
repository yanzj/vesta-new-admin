package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HouseInfoEntity;
import com.maxrocky.vesta.domain.model.PropertyRepairEntity;
import com.maxrocky.vesta.domain.repository.RepairStatisticsRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 报修统计 持久层实现类
 */
@Repository
public class RepairStatisticsRepositoryImpl extends HibernateDaoImpl implements RepairStatisticsRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 根据项目ID查询 报修或投诉
     * @param projectId
     * @return
     */
    @Override
    public List<PropertyRepairEntity> getPropertyRepairList(String projectId,String taskStatus) {
        String hql ="FROM PropertyRepairEntity AS pr where 1=1 ";

        if(!"".equals(projectId)){
            hql = hql + " and pr.regionId = '"+ projectId +"'";
        }
        if(!"".equals(taskStatus)){
            hql = hql + " and pr.taskStatus = '"+ taskStatus +"'";
        }

        Query query = getCurrentSession().createQuery(hql);
        List<PropertyRepairEntity> PropertyRepair = query.list();
        return PropertyRepair;
    }
}
