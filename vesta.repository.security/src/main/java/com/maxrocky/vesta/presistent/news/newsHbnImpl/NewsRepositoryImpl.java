package com.maxrocky.vesta.presistent.news.newsHbnImpl;

import com.maxrocky.vesta.domain.news.model.NewsEntity;
import com.maxrocky.vesta.domain.news.repository.NewsRepository;
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
 * 新闻管理 数据持久实现
 * Created by yuanyn on 2017/6/13.
 */
@Repository
public class NewsRepositoryImpl implements NewsRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<Map<String, Object>> getNewsList(Map<String, Object> paramsMap, WebPage webPage) {
        StringBuilder hql = new StringBuilder();
        hql.append("FROM NewsEntity ne WHERE 1=1");
        List<Object> paramsList = new ArrayList<>();
        Object newsTitle = paramsMap.get("newsTitle");
        if (null != newsTitle && !"".equals(newsTitle)){
            hql.append(" AND ne.newsTitle LIKE ? ");
            paramsList.add("%"+newsTitle.toString()+"%");
        }
        Object newsId = paramsMap.get("newsId");
        if (null != newsId && !"".equals(newsId)){
            hql.append(" AND ne.slideShow = '1' ");
        }
        hql.append(" ORDER BY ne.slideShow DESC, ne.createDate DESC ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        for (int i = 0,length = paramsList.size(); i < length; i++){
            query.setParameter(i,paramsList.get(i));
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }else {
            List list = query.list();
            session.flush();
            session.close();
            return list;
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public NewsEntity getNewsById(String newsId) {
        StringBuilder hql = new StringBuilder("FROM NewsEntity ne WHERE ne.newsId = ?");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString()).setParameter(0, newsId);
        NewsEntity newsEntity = (NewsEntity) query.uniqueResult();
        session.flush();
        session.close();
        return newsEntity;
    }

    @Override
    public <T> void saveOrUpdate(T entity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    @Override
    public <E> void delete(E entity) {
        Session session = this.getCurrentSession();
        session.delete(entity);
        session.flush();
        session.close();
    }

    @Override
    public void topNews(String newsId,String slideShow) {
        StringBuilder hql = new StringBuilder("UPDATE NewsEntity ne SET ne.slideShow = ? ");
        hql.append(" WHERE ne.newsId = ? ") ;
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        query.setParameter(0, slideShow);
        query.setParameter(1, newsId);
        query.executeUpdate();
        session.flush();
        session.close();
    }

}
