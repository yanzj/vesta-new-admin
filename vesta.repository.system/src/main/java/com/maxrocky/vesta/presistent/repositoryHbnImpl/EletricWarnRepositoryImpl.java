package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ElectricWarnEntity;
import com.maxrocky.vesta.domain.repository.ElectricWarnRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.Source;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by zhanghj on 2016/2/23.
 */
@Repository
public class EletricWarnRepositoryImpl implements ElectricWarnRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 插入预警信息
     * @param electricWarnEntity
     * @return
     */
    @Override
    public boolean saveEleWarn(ElectricWarnEntity electricWarnEntity) {
        Session session = getCurrentSession();
        session.save(electricWarnEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 根据语境id查询预警信息
     * @param warnId
     * @return
     */
    @Override
    public ElectricWarnEntity getEleWarnById(String warnId) {
        String hql = "from ElectricWarnEntity as e where e.warnId=:warnId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("warnId",warnId);
        if (query.list().size()>0){
            return (ElectricWarnEntity)query.list().get(0);
        }
        return null;
    }

    /**
     * 查询项目下的预警信息
     * @param projectId
     * @return
     */
    @Override
    public ElectricWarnEntity getEleWarnByProId(String projectId) {
        String hql = "from ElectricWarnEntity as e where e.projectId=:projectId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        if (query.list().size()>0){
            return (ElectricWarnEntity)query.list().get(0);
        }
        return null;
    }

    /**
     * 更新预警信息
     * @param electricWarnEntity
     * @return
     */
    @Override
    public boolean updateEleWarn(ElectricWarnEntity electricWarnEntity) {
        ElectricWarnEntity electricWarnEntity1 = getEleWarnById(electricWarnEntity.getWarnId());
        if (electricWarnEntity1!=null){
            Session session = getCurrentSession();
            session.update(electricWarnEntity);
            session.flush();
            session.close();
            return true;
        }
        return false;
    }

    //************************前端接口****************************

    private Session getSessionForInterface(){
        return sessionFactory.getCurrentSession();
    }
    /**
     * 查询项目下的预警信息
     * @param projectId
     * @return
     */
    @Override
    public ElectricWarnEntity getEleWarnByProIdForInterface(String projectId) {
        String hql = "from ElectricWarnEntity as e where e.projectId=:projectId";
        Query query = getSessionForInterface().createQuery(hql);
        query.setParameter("projectId",projectId);
        if (query.list().size()>0){
            return (ElectricWarnEntity)query.list().get(0);
        }
        return null;
    }
}
