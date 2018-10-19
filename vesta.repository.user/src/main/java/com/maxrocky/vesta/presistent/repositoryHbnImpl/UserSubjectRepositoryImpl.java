package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.UserSubjectEntity;
import com.maxrocky.vesta.domain.repository.UserSubjectRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/12.
 */
@Repository
public class UserSubjectRepositoryImpl implements UserSubjectRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<Object> getAll(String subjectId) {
        String sql = "select ua.REAL_NAME,ua.MOBILE,u.message from usersubject u ,user_userInfo ua where u.userId = ua.USER_ID and u.subjectId = ?";
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter(0,subjectId);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }
}
