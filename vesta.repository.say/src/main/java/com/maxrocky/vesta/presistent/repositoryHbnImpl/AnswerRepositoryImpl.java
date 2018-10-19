package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.AnswerEntity;
import com.maxrocky.vesta.domain.model.IsayImageEntity;
import com.maxrocky.vesta.domain.repository.AnswerRepository;
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
public class AnswerRepositoryImpl implements AnswerRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void AddAnswer(AnswerEntity answerEntity) {
        Session session = getCurrentSession();
        session.save(answerEntity);
        session.flush();
    }

    @Override
    public AnswerEntity getAnswer(String consultId,String answerType) {
        String hql = "from AnswerEntity where consultId =:consultId and answerType=:answerType";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("consultId",consultId);
        query.setParameter("answerType",answerType);
        AnswerEntity answerEntity = (AnswerEntity) query.uniqueResult();
        return answerEntity;
    }

    @Override
    public List<AnswerEntity> getAnswers(String consultId,String answerType) {
        String hql = "from AnswerEntity where consultId =:consultId and answerType=:answerType";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("consultId",consultId);
        query.setParameter("answerType",answerType);
        List<AnswerEntity> answerEntities = query.list();
        return answerEntities;
    }

    /**
     * 更新咨询回复信息
     * @param answerEntity
     */
    @Override
    public void chengeAnswerEntity(AnswerEntity answerEntity) {
        Session session = getCurrentSession();
        session.update(answerEntity);
        session.flush();
    }

    /**
     *  查询图片集合
     * @param id
     * @return
     */
    @Override
    public List<IsayImageEntity> getIsayImageList(String id) {
        String hql = "from IsayImageEntity where bussinessId =:id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        List<IsayImageEntity> isayImage = query.list();
        return isayImage;
    }


}
