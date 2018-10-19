package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ClientRelationsNewsEntity;
import com.maxrocky.vesta.domain.repository.ClientRelationsNewsRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 客关新闻DAO层接口实现
 * Created by Administrator on 2018/6/20 0011.
 */
@Repository
public class ClientRelationsNewsRepositoryImpl implements ClientRelationsNewsRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<ClientRelationsNewsEntity> getNewsList(Map<String, Object> paramsMap, WebPage webPage,  List<String> projectIds) {
        StringBuilder hql = new StringBuilder();
        hql.append("FROM ClientRelationsNewsEntity WHERE 1=1 AND state='1' ");
        Object newsTitle = paramsMap.get("newsTitle");
        if (null != newsTitle && !"".equals(newsTitle)){
            hql.append(" AND newsTitle like '%"+newsTitle.toString()+"%' ");
        }
        if (null != projectIds && projectIds.size()>0){
            hql.append(" AND projectId in (:projectIds) ");
        }
        Object releaseState = paramsMap.get("releaseState");
        if (null != releaseState && !"".equals(releaseState)){
            if("0".equals(releaseState)){
                hql.append(" AND releaseDate > "+"'"+DateUtils.format(new Date())+"' ");
            }else if("1".equals(releaseState)){
                hql.append(" AND releaseDate < "+"'"+DateUtils.format(new Date())+"' ");
            }
        }
        Object createDate = paramsMap.get("createDate");
        if (null != createDate && !"".equals(createDate)){
            hql.append( " AND date_format(createDate,'%Y-%m-%d') = "+"'"+createDate+"' " );
        }
        Object releaseDate = paramsMap.get("releaseDate");
        if (null != releaseDate && !"".equals(releaseDate)){
            hql.append( " AND date_format(releaseDate,'%Y-%m-%d') = "+"'"+releaseDate+"' " );
        }
        hql.append(" ORDER BY createDate DESC ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        query.setParameterList("projectIds",projectIds);
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
    public ClientRelationsNewsEntity getNewsById(String newsId) {
        StringBuilder hql = new StringBuilder();
        hql.append("FROM ClientRelationsNewsEntity WHERE 1=1");
        hql.append(" AND newsId=:newsId ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("newsId",newsId);
        ClientRelationsNewsEntity clientRelationsNewsEntity =(ClientRelationsNewsEntity) query.uniqueResult();
        return clientRelationsNewsEntity;
    }

    @Override
    public void saveNewsEntity(ClientRelationsNewsEntity clientRelationsNewsEntity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(clientRelationsNewsEntity);
        session.flush();
        session.close();
    }

}
