package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SideStandEntity;
import com.maxrocky.vesta.domain.repository.SideStandRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/5/17.
 */
@Repository
public class SideStandRepositoryImpl extends HibernateDaoImpl implements SideStandRepository{
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addSideStand(SideStandEntity sideStandEntity) {
        Session session = getCurrentSession();
        session.save(sideStandEntity);
        session.flush();
    }

    @Override
    public void updateSideStand(SideStandEntity sideStandEntity) {
        Session session = getCurrentSession();
        session.update(sideStandEntity);
        session.flush();
    }

    @Override
    public List<SideStandEntity> getSideStandList() {
        String hql = "from SideStandEntity where status <> '2' order by createOn";
        Query query = getCurrentSession().createQuery(hql);
        List<SideStandEntity> sideStandEntities = query.list();
        return sideStandEntities;
    }

    @Override
    public List<SideStandEntity> getSideStands(String projectId) {
        String hql = "from SideStandEntity where status <> '2' and projectId=:projectId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId",projectId);
        List<SideStandEntity> sideStandEntities = query.list();
        return sideStandEntities;
    }

    @Override
    public SideStandEntity getSideStandDetail(String id) {
        String hql = "from SideStandEntity where status<>'2' and standId=:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        SideStandEntity sideStandEntity = (SideStandEntity) query.uniqueResult();
        return sideStandEntity;
    }

    @Override
    public List<SideStandEntity> getStandList(SideStandEntity sideStandEntity,WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from SideStandEntity where 1=1";
        if(!StringUtils.isEmpty(sideStandEntity.getCaseName())){
            hql += "and caseName like '%"+sideStandEntity.getCaseName()+"%'";
        }
        hql+="and status<>'2' order by createOn";
        Query query = getCurrentSession().createQuery(hql);
        List<SideStandEntity> sideStandEntityList = query.list();
        if(webPage !=null){
            return this.findByPage(hql, webPage,params);
        }
        return sideStandEntityList;
    }
}
