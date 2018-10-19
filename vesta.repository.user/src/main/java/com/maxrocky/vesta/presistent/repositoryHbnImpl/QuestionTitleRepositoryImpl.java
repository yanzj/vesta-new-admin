package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.QuestionTitleEntity;
import com.maxrocky.vesta.domain.repository.QuestionTitleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
@Repository
public class QuestionTitleRepositoryImpl implements QuestionTitleRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<QuestionTitleEntity> getAll(String questId) {
        String hql = "from QuestionTitleEntity where questId="+"'"+questId+"'  order by countNum";
        List<QuestionTitleEntity> title = getCurrentSession().createQuery(hql).list();
        return title;
    }

    @Override
    public void create(QuestionTitleEntity q) {
        Session session = getCurrentSession();
        session.save(q);
        session.flush();
        session.close();
    }
}
