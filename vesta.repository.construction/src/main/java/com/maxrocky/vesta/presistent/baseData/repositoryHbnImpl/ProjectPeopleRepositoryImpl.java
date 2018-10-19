package com.maxrocky.vesta.presistent.baseData.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baseData.model.BaseProjectPeopleEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectBuildingEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectPeopleRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/11/8.
 */

@Repository
public class ProjectPeopleRepositoryImpl implements ProjectPeopleRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addProjectPeople(BaseProjectPeopleEntity baseProjectPeopleEntity) {
        Session session = getCurrentSession();
        session.saveOrUpdate(baseProjectPeopleEntity);
        session.flush();
    }

    @Override
    public List<BaseProjectPeopleEntity> getProjectPeopleForTime(String projectId, String timeStamp, long autoId) {
        String hql = "from BaseProjectPeopleEntity where projectId=:projectId and ((modifyTime='"+timeStamp+"' and autoId>:autoId) or modifyTime>'"+timeStamp+"') order by modifyTime,autoId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        query.setParameter("autoId",autoId);
        query.setMaxResults(1000);
        List<BaseProjectPeopleEntity> baseProjectPeopleEntities = query.list();
        return baseProjectPeopleEntities;
    }

    @Override
    public void delProjectPeople(String staffId) {
        String hql = "update BaseProjectPeopleEntity set status='0',modifyTime=:modifyTime where peopleId=:staffId and status='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime",new Date());
        query.setParameter("staffId",staffId);
        query.executeUpdate();
    }

    @Override
    public void delProjectPeopleByDutyId(String dutyId) {
        String hql = "update BaseProjectPeopleEntity set status='0',modifyTime=:modifyTime where supplierId=:dutyId and status='1'";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime",new Date());
        query.setParameter("dutyId",dutyId);
        query.executeUpdate();
    }

    @Override
    public void updateProjectPeople(String dutyName, String dutyId) {
        String hql = "update BaseProjectPeopleEntity set supplierName=:dutyName,modifyTime=:modifyTime where supplierId=:dutyId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime",new Date());
        query.setParameter("dutyId",dutyId);
        query.setParameter("dutyName",dutyName);
        query.executeUpdate();
    }

    @Override
    public void updateForProjectName(String projectName, String projectId) {
        String hql = "update BaseProjectPeopleEntity set projectName=:projectName,modifyTime=:modifyTime where projectId=:projectId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime",new Date());
        query.setParameter("projectId",projectId);
        query.setParameter("projectName",projectName);
        query.executeUpdate();
    }

    @Override
    public void updateForStaffName(String staffName, String staffId) {
        String hql = "update BaseProjectPeopleEntity set peopleName=:staffName,modifyTime=:modifyTime where peopleId=:staffId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("modifyTime",new Date());
        query.setParameter("staffId",staffId);
        query.setParameter("staffName",staffName);
        query.executeUpdate();
    }
}
