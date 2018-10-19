package com.maxrocky.vesta.presistent.repositoryHbnImpl;
import com.maxrocky.vesta.domain.model.WorkTimeEntity;
import com.maxrocky.vesta.domain.repository.WorkTimeCRMRepository;
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
public class WorkTimeCRMPositoryImpl implements WorkTimeCRMRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }
    /**
     * Describe:创建维修工时
     * CreateBy:dl
     * CreateOn:2016-01-17 05:19:23
     */
    @Override
    public void create(WorkTimeEntity workTimeEntity) {
        Session session = getCurrentSession();
        session.save(workTimeEntity);
        session.flush();
    }
    /**
     * CreatedBy:dl
     * Describe:
     * 修改维修工时
     * ModifyBy:
     */
    @Override
    public void update(WorkTimeEntity workTimeEntity) {
        Session session = getCurrentSession();
        session.update(workTimeEntity);
        session.flush();
        session.close();
    }

    /**
     * CreatedBy:dl
     * Describe:
     * 删除维修工时
     * ModifyBy:
     */
    @Override
    public void delete() {
        String hql="delete FROM WorkTimeEntity";
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
    public WorkTimeEntity get(String id) {
        String hql="FROM WorkTimeEntity WHERE value='"+id+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<WorkTimeEntity> WorkTimeList=query.list();
        if(WorkTimeList.size()>0){
            return WorkTimeList.get(0);
        }
        return null;
    }
}
