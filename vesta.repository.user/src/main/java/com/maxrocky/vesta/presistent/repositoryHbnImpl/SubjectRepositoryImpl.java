package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.QuestionTitleSelectEntity;
import com.maxrocky.vesta.domain.model.QuestionnaireEntity;
import com.maxrocky.vesta.domain.model.SubjectEntity;
import com.maxrocky.vesta.domain.model.UserIntegralRuleEntity;
import com.maxrocky.vesta.domain.repository.SubjectRepository;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
@Repository
public class SubjectRepositoryImpl implements SubjectRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public SubjectEntity getById(String questId) {
        String hql = "from SubjectEntity where questId="+"'"+questId+"'";
        Query query = getCurrentSession().createQuery(hql);
        SubjectEntity q = (SubjectEntity) query.uniqueResult();
        return q;
    }

    @Override
    public void create(SubjectEntity q) {
        Session session = getCurrentSession();
        session.save(q);
        session.flush();
        session.close();
    }
}
