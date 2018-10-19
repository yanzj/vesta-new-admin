package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.IntegralRuleEntity;
import com.maxrocky.vesta.domain.model.MallProductCardEntity;
import com.maxrocky.vesta.domain.repository.MallProductCardRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/5.
 */
@Repository
public class MallProductCardRepositoryImpl implements MallProductCardRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addOrupdate(MallProductCardEntity c) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(c);
        session.flush();
        session.close();
    }

    @Override
    public List<MallProductCardEntity> getAll(String mallId, WebPage webPage) {
        StringBuilder hql = new StringBuilder("FROM MallProductCardEntity m WHERE m.mallId = ?");
        hql.append(" order by m.createOn desc ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        query.setParameter(0,mallId);
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public MallProductCardEntity getCode(String mallId, String code) {
        String hql = "from MallProductCardEntity where mallId=? and code=? ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter(0,mallId);
        query.setParameter(1,code);
        MallProductCardEntity m = (MallProductCardEntity) query.uniqueResult();
        return m;
    }
}
