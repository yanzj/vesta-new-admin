package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SimilarAppContentEntity;
import com.maxrocky.vesta.domain.model.SimilarAppPictureEntity;
import com.maxrocky.vesta.domain.repository.SimilarAppRepository;
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
 * @author WeiYangDong
 * @Description 类APP管理DAO实现类
 * @data 2018/5/28
 */
@Repository
public class SimilarAppRepositoryImpl implements SimilarAppRepository{

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){ return sessionFactory.openSession(); }

    /**
     * 保存或更新Entity
     * @param entity
     */
    @Override
    public <T> void saveOrUpdate(T entity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    /**
     * 删除Entity
     * @param entity
     */
    @Override
    public <E> void delete(E entity) {
        Session session = this.getCurrentSession();
        session.delete(entity);
        session.flush();
        session.close();
    }

    @Override
    public List<SimilarAppPictureEntity> getPictureList(Map<String,Object> paramsMap, WebPage webPage){
        StringBuilder hql = new StringBuilder(" FROM SimilarAppPictureEntity WHERE 1=1 ");
        //客户端范围权限
        Object clientIdList = paramsMap.get("clientIdList");
        if (null != clientIdList){
            hql.append(" AND clientId in (:clientIdList) ");
        }
        //客户端
        Object clientId = paramsMap.get("clientId");
        if (null != clientId){
            hql.append(" AND clientId = :clientId ");
        }
        hql.append(" ORDER BY createOn DESC ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        if (null != clientIdList){
            query.setParameterList("clientIdList",(List)clientIdList);
        }
        if (null != clientId){
            query.setParameter("clientId",clientId);
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public SimilarAppPictureEntity getPictureById(String id){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM SimilarAppPictureEntity WHERE id = ?");
        query.setParameter(0,id);
        SimilarAppPictureEntity similarAppPictureEntity = (SimilarAppPictureEntity) query.uniqueResult();
        session.flush();
        session.close();
        return similarAppPictureEntity;
    }

    @Override
    public List<SimilarAppContentEntity> getContentList(Map<String,Object> paramsMap, WebPage webPage){
        StringBuilder hql = new StringBuilder(" FROM SimilarAppContentEntity WHERE 1=1 ");
        //客户端范围权限
        Object clientIdList = paramsMap.get("clientIdList");
        if (null != clientIdList){
            hql.append(" AND clientId in (:clientIdList) ");
        }
        //客户端
        Object clientId = paramsMap.get("clientId");
        if (null != clientId){
            hql.append(" AND clientId = :clientId ");
        }
        //位置类型
        Object positionType = paramsMap.get("positionType");
        if (null != positionType && !"".equals(positionType.toString())){
            hql.append(" AND positionType = :positionType ");
        }
        hql.append("ORDER BY createOn DESC");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        if (null != clientIdList){
            query.setParameterList("clientIdList",(List)clientIdList);
        }
        if (null != clientId){
            query.setParameter("clientId",clientId);
        }
        if (null != positionType && !"".equals(positionType.toString())){
            query.setParameter("positionType",positionType);
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public SimilarAppContentEntity getContentById(String id){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM SimilarAppContentEntity WHERE id = ?");
        query.setParameter(0,id);
        SimilarAppContentEntity similarAppContentEntity = (SimilarAppContentEntity) query.uniqueResult();
        session.flush();
        session.close();
        return similarAppContentEntity;
    }
}
