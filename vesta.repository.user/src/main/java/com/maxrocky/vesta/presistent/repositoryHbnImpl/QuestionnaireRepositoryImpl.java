package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.QuestionnaireEntity;
import com.maxrocky.vesta.domain.repository.QuestionnaireRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/9/11.
 */
@Repository
public class QuestionnaireRepositoryImpl implements QuestionnaireRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<Object> getAll(Map<String, Object> paramsMap, WebPage webPage) {
        Object titleName = paramsMap.get("titleName");
        Object status = paramsMap.get("status");

        String sql = "SELECT q.questId,q.questName,p.name,q.questStatus,q.num,q.createon from questionnaire q left join house_project p on p.PINYIN_CODE = q.projectName where q.deleteStatus != 1 and q.questName is not null ";
        List<Object> paramsList = new ArrayList<>();
        //标题
        if (null != titleName){
            sql += " AND q.questName like ? ";
            paramsList.add("%"+titleName+"%");
        }
        //状态
        if (null != status && status != "" ){

            sql += " AND q.questStatus  = ?  ";
            paramsList.add(Integer.parseInt(status.toString()));
        }

        sql += " ORDER BY q.createon DESC";
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        for (int i = 0,length = paramsList.size(); i < length; i++){
            sqlQuery.setParameter(i,paramsList.get(i));
        }
        if (null != webPage){
            sqlQuery.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            sqlQuery.setMaxResults(webPage.getPageSize());
        }

        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public QuestionnaireEntity getDetail(String id) {
        Session session = getCurrentSession();
        QuestionnaireEntity q = (QuestionnaireEntity) session.get(QuestionnaireEntity.class, id);
        session.flush();
        session.close();
        return q;
    }

    @Override
    public void create(QuestionnaireEntity q) {
        Session session = getCurrentSession();
        session.save(q);
        session.flush();
        session.close();
    }

    @Override
    public void update(QuestionnaireEntity q) {
        Session session = getCurrentSession();
        session.update(q);
        session.flush();
        session.close();
    }

    @Override
    public void updateTpKs(String date) {
        String sql = "update QuestionnaireEntity set questStatus = 2 where questStatus = 1 and beginDate <=  '"+date+"'";
        Session session = getCurrentSession();
        Query query = session.createQuery(sql);
        query.executeUpdate();
    }

    @Override
    public void updateTpJs(String date) {
        String sql = "update QuestionnaireEntity set questStatus = 3 where questStatus = 2 and endDate <= '"+date+"'";
        Session session = getCurrentSession();
        Query query = session.createQuery(sql);
        query.executeUpdate();
    }
}
