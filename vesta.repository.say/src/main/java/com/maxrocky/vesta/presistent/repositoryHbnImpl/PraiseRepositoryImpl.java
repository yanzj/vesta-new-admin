package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PraiseEntity;
import com.maxrocky.vesta.domain.repository.PraiseRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/1/21.
 */

@Repository
public class PraiseRepositoryImpl extends HibernateDaoImpl implements PraiseRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    @Override
    public void AddPraise(PraiseEntity praiseEntity) {
        Session session =getCurrentSession();
        session.save(praiseEntity);
        session.flush();
    }

    @Override
    public List<PraiseEntity> getPraises(String userId,Page page) {
        String hql = "from PraiseEntity where userId = :userId and status <>"+PraiseEntity.STATUS_INVALID+" order by crtTime desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PraiseEntity> praiseEntities = query.list();
        return praiseEntities;
    }

    @Override
    public List<PraiseEntity> getStaffPraises(String userId,Page page) {
        String hql = "from PraiseEntity where targetId = :userId and status <>"+PraiseEntity.STATUS_INVALID+"and level >=4 order by crtTime desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("userId",userId);
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<PraiseEntity> praiseEntities = query.list();
        return praiseEntities;
    }

    @Override
    public PraiseEntity getPraiseDetail(String id) {
        String hql = "from PraiseEntity where id =:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id",id);
        PraiseEntity praiseEntity = (PraiseEntity) query.uniqueResult();
        return praiseEntity;
    }

    @Override
    public List<PraiseEntity> getPraiseList(PraiseEntity praiseEntity,String beginTime,String endTime,WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from PraiseEntity where 1=1";
        if(!StringUtil.isEmpty(praiseEntity.getProjectId())&&!praiseEntity.getProjectId().equals("0")){
            hql += "and projectId ='"+praiseEntity.getProjectId()+"'";
        }
        if(!StringUtil.isEmpty(beginTime)){
            hql += "and crtTime >= '"+beginTime+"'";
        }
        if(!StringUtil.isEmpty(endTime)){
            hql += "and crtTime <='"+endTime+"'";
        }
        if(!StringUtil.isEmpty(praiseEntity.getContent())){
            hql += "and content like '%"+praiseEntity.getContent()+"%'";
        }
        if(!StringUtil.isEmpty(praiseEntity.getReply())&&!praiseEntity.getReply().equals("0")){
            hql += "and reply ='"+praiseEntity.getReply()+"'";
        }
        hql+="and status="+PraiseEntity.STATUS_VALID+"order by crtTime desc";
        Query query = getCurrentSession().createQuery(hql);

        List<PraiseEntity> praiseEntities = query.list();

        if(webPage !=null){
            return this.findByPage(hql, webPage,params);
        }
        return praiseEntities;
    }

    /**
     * 获取员工未读表扬数量
     * @param targetId
     * @return
     */
    @Override
    public int countUnread(String targetId) {
        String hql = "select count(p.targetId) as number from PraiseEntity as p where p.targetId = :targetId and p.readStatus = :readStatus and p.status =:status";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("targetId",targetId);
        query.setParameter("readStatus",PraiseEntity.READSTATUS_NOT);
        query.setParameter("status",PraiseEntity.STATUS_VALID);
        int number = ((Number)query.iterate().next()).intValue();
        return number;
    }

    @Override
    public void altUnread(String targetId) {
        String hql = "update PraiseEntity set readStatus = "+PraiseEntity.READSTATUS_YES+" where targetId=:targetId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("targetId",targetId);
        query.executeUpdate();
    }

    @Override
    public void altPraise(PraiseEntity praiseEntity) {
        Session session = getCurrentSession();
        session.update(praiseEntity);
        session.flush();
    }
}
