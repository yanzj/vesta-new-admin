package com.maxrocky.vesta.presistent.nursery.repositoryHbnImpl;

import com.maxrocky.vesta.domain.nursery.model.NurseryEntity;
import com.maxrocky.vesta.domain.nursery.repository.NurseryRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yuanyn on 2017/9/29.
 */
@Repository
public class NurseryRepositoryImpl implements NurseryRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public void addNurseryEntity(NurseryEntity nurseryEntity) {
        Session session = this.getCurrentSession();
        session.save(nurseryEntity);
        session.flush();
        session.close();
    }

    @Override
    public List<NurseryEntity> getNurseryList(WebPage webPage) {
        String sql = " FROM NurseryEntity WHERE 1=1 ORDER BY modifyDate DESC";
        Query query = getCurrentSession().createQuery(sql);
        if (null != webPage) {
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List<NurseryEntity> list =(List<NurseryEntity>)query.list();
        return list;
    }

    @Override
    public void updateNursery(NurseryEntity nurseryEntity) {
        Session session = this.getCurrentSession();
        session.update(nurseryEntity);
        session.flush();
        session.close();
    }
}
