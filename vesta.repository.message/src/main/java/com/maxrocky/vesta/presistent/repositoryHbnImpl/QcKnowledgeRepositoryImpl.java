package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.QCKnowledgeEntity;
import com.maxrocky.vesta.domain.repository.QcKnowledgeRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Itzxs on 2018/6/8.
 */
@Repository
public class QcKnowledgeRepositoryImpl extends HibernateDaoImpl implements QcKnowledgeRepository{
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){
        return sessionFactory.openSession();
    }

    @Override
    public List<Object[]> getQcKnowledgeList(String contentType,String knowledgeType,String title,String userName,String createDate, WebPage webPage){
        StringBuffer sql = new StringBuffer();
        sql.append("select ");
        sql.append("ID,CONTENT_TYPE,KNOWLEDGE_TYPE,TITLE,USER_NAME,CREATE_DATE,CONTENT,VISITS,FILE_NAME,FILE_URL,FILE_SIZE,VIDEO_URL,USER_ID,OUT_VIDEO_URL ");
        sql.append("from qc_knowledge ");
        sql.append("where STATE = 0 ");
        if(contentType != null && !"".equals(contentType)){
            sql.append(" and CONTENT_TYPE = '"+contentType+"'");
        }
        if(knowledgeType != null && !"".equals(knowledgeType)){
            if("工作方法".equals(knowledgeType) || "工作成果".equals(knowledgeType)){
                sql.append(" and KNOWLEDGE_TYPE = '"+knowledgeType+"'");
            }else{
                sql.append(" and KNOWLEDGE_TYPE <> '工作方法' and KNOWLEDGE_TYPE <> '工作成果'");
            }
        }
        if(title != null && !"".equals(title)){
            sql.append(" and TITLE like '%"+title+"%'" );
        }
        if(userName != null && !"".equals(userName)){
            sql.append(" and USER_NAME like '%"+userName+"%'");
        }
        if(createDate != null && !"".equals(createDate)){
            sql.append(" and date_format(CREATE_DATE,'%Y-%m-%d') = '"+createDate+"'");
        }
        sql.append(" order by CREATE_DATE desc");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (webPage != null) {
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        /*if (contentType != null && !"".equals(contentType)) {
            query.setParameter("contentType", contentType);
        }
        if (knowledgeType != null && !"".equals(knowledgeType)) {
            query.setParameter("knowledgeType", knowledgeType);
        }
        if (title != null && !"".equals(title)) {
            query.setParameter("title", "%" + title + "%");
        }
        if (userName != null && !"".equals(userName)) {
            query.setParameter("userName", "%" + userName + "%");
        }
        if (createDate != null && !"".equals(createDate)) {
            query.setParameter("createDate", createDate);
        }*/
        return query.list();
    }

    @Override
    public int getQcKnowledgeCount(String contentType,String knowledgeType,String title,String userName,String createDate){
        StringBuffer sql = new StringBuffer();
        sql.append("select count(1) from qc_knowledge WHERE state = '0'");
        if(contentType != null && !"".equals(contentType)){
            sql.append(" and CONTENT_TYPE = '"+contentType+"'");
        }
        if(knowledgeType != null && !"".equals(knowledgeType)){
            if("工作方法".equals(knowledgeType) || "工作成果".equals(knowledgeType)){
                sql.append(" and KNOWLEDGE_TYPE = '"+knowledgeType+"'");
            }else{
                sql.append(" and KNOWLEDGE_TYPE <> '工作方法' and KNOWLEDGE_TYPE <> '工作成果'");
            }
        }
        if(title != null && !"".equals(title)){
            sql.append(" and TITLE like '%"+title+"%'" );
        }
        if(userName != null && !"".equals(userName)){
            sql.append(" and USER_NAME like '%"+userName+"%'");
        }
        if(createDate != null && !"".equals(createDate)){
            sql.append(" and date_format(CREATE_DATE,'%Y-%m-%d') = '"+createDate+"'");
        }
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return count.intValue();
        } else {
            return 0;
        }
    }

    @Override
    public List<String> getContentTypeList(){
        String sql = "select CONTENT_TYPE from qc_knowledge group by CONTENT_TYPE";
        Query query = getCurrentSession().createSQLQuery(sql);
        List<String> list = query.list();
        return list;
    }

    @Override
    public QCKnowledgeEntity getKnowledgeById(int id){
        StringBuilder hql = new StringBuilder("FROM QCKnowledgeEntity WHERE id = ?");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString()).setParameter(0, id);
        QCKnowledgeEntity qcKnowledgeEntity = (QCKnowledgeEntity) query.uniqueResult();
        session.flush();
        session.close();
        return qcKnowledgeEntity;
    }

    @Override
    public <T> void saveOrUpdate(T entity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    @Override
    public void delKnowledge(String id){
        String sql = "UPDATE qc_knowledge SET state = '1' WHERE id = :id";
        Query query = getCurrentSession().createSQLQuery(sql);
        query.setParameter("id",id);
        query.executeUpdate();
    }
}
