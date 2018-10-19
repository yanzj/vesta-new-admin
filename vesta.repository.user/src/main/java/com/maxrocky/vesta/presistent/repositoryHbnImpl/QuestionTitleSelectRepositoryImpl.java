package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.QuestionTitleEntity;
import com.maxrocky.vesta.domain.model.QuestionTitleSelectEntity;
import com.maxrocky.vesta.domain.repository.QuestionTitleSelectRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
@Repository
public class QuestionTitleSelectRepositoryImpl implements QuestionTitleSelectRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<QuestionTitleSelectEntity> getAll(String questionTitleId) {
        String hql = "from QuestionTitleSelectEntity where questionTitleId="+"'"+questionTitleId+"' order by countNum";
        List<QuestionTitleSelectEntity> sele = getCurrentSession().createQuery(hql).list();
        return sele;
    }

    @Override
    public void create(QuestionTitleSelectEntity q) {
        Session session = getCurrentSession();
        session.save(q);
        session.flush();
        session.close();
    }
}
