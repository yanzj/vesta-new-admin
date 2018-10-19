package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ComplaintEntity;
import com.maxrocky.vesta.domain.repository.ComplaintRepository;
import com.maxrocky.vesta.utility.Page;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chen on 2016/1/21.
 */
@Repository
public class ComplaintRepositoryImpl implements ComplaintRepository{
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void AddComplaint(ComplaintEntity complaintEntity) {
        Session session = getCurrentSession();
        session.save(complaintEntity);
        session.flush();
    }

    @Override
    public List<ComplaintEntity> getUserComplaints(String userId,Page page) {
        String hql = "from ComplaintEntity where userId = :userId and status<>"+ComplaintEntity.STATUS_INVALID+" order by crtTime desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<ComplaintEntity> complaintEntityList = query.list();
        return complaintEntityList;
    }

    @Override
    public List<ComplaintEntity> getComplaintList() {
        String hql = "from ComplaintEntity";
        Query query = getCurrentSession().createQuery(hql);
        List<ComplaintEntity> complaintEntityList = query.list();
        return complaintEntityList;
    }

    @Override
    public ComplaintEntity getComplaintDetail(String id) {
        String hql = "from ComplaintEntity where id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        ComplaintEntity complaintEntity = (ComplaintEntity) query.uniqueResult();
        return complaintEntity;
    }
}
