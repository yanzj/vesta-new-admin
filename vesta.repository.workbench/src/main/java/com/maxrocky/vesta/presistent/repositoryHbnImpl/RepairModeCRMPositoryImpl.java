package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.RepairModeEntity;
import com.maxrocky.vesta.domain.repository.RepairModeCRMRepository;
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
public class RepairModeCRMPositoryImpl implements RepairModeCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    /**
     * Describe:创建维修方式
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    @Override
    public void create(RepairModeEntity repairModeEntity) {
        Session session = getCurrentSession();
        session.save(repairModeEntity);
        session.flush();
    }
    /**
     * CreatedBy:dl
     * Describe:
     * 修改维修方式
     * ModifyBy:
     */
    @Override
    public void update(RepairModeEntity repairModeEntity) {
        Session session = getCurrentSession();
        session.update(repairModeEntity);
        session.flush();
        session.close();
    }

    /**
     * CreatedBy:dl
     * Describe:
     * 删除维修方式
     * ModifyBy:
     */
    @Override
    public void delete() {
        String hql="delete FROM RepairModeEntity";
        Query query = getCurrentSession().createQuery(hql);
        query.executeUpdate();
    }

    /**
     * Describe:根据id获取信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     *
     * @param id
     */
    @Override
    public RepairModeEntity get(String id) {
        String hql="FROM RepairModeEntity WHERE value='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<RepairModeEntity>RepairModeList=query.list();
        if(RepairModeList.size()>0){
            return RepairModeList.get(0);
        }
        return null;
    }
}
