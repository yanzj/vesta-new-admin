package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.EngineeringProgressEntity;
import com.maxrocky.vesta.domain.model.EngineeringProjectEntity;
import com.maxrocky.vesta.domain.repository.HomeLetterRespository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 家书管理-数据持久层实现类
 * Created by WeiYangDong on 2017/5/17.
 */
@Repository
public class HomeLetterRespositoryImpl implements HomeLetterRespository{

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

    /**
     * 获取工程进展列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    @Override
    public List<Map<String,Object>> getEngineeringProgressList(Map<String,Object> paramsMap, WebPage webPage){
        StringBuilder hql = new StringBuilder();
        hql.append("FROM EngineeringProgressEntity ep WHERE 1=1");
        List<Object> paramsList = new ArrayList<>();
        Object cityId = paramsMap.get("cityId");
        if (null != cityId && !"".equals(cityId.toString()) && !"0".equals(cityId.toString())){
            hql.append(" and ep.cityId = ? ");
            paramsList.add(cityId.toString());
        }
        Object projectCode = paramsMap.get("projectCode");
        if (null != projectCode && !"".equals(projectCode.toString())){
            hql.append(" and ep.projectCode = ? ");
            paramsList.add(projectCode.toString());
        }
        Object engineeringProgressTitle = paramsMap.get("engineeringProgressTitle");
        if (null != engineeringProgressTitle && !"".equals(engineeringProgressTitle)){
            hql.append(" and ep.engineeringProgressTitle like ? ");
            paramsList.add("%"+engineeringProgressTitle.toString()+"%");
        }
        hql.append(" order by ep.createOn desc ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
//        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        for (int i = 0,length = paramsList.size(); i < length; i++){
            query.setParameter(i,paramsList.get(i));
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

    /**
     * 获取工程进展详情
     * @param engineeringProgressId 主键ID
     * @return EngineeringProgressEntity
     */
    @Override
    public EngineeringProgressEntity getEngineeringProgressById(String engineeringProgressId){
        StringBuilder hql = new StringBuilder("FROM EngineeringProgressEntity ep WHERE ep.engineeringProgressId = ?");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString()).setParameter(0, engineeringProgressId);
        EngineeringProgressEntity engineeringProgressEntity = (EngineeringProgressEntity) query.uniqueResult();
        session.flush();
        session.close();
        return engineeringProgressEntity;
    }

    /**
     * 获取工程项目列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    @Override
    public List<EngineeringProjectEntity> getEngineeringProjectList(Map<String,Object> paramsMap, WebPage webPage){
        StringBuilder hql = new StringBuilder();
        hql.append("FROM EngineeringProjectEntity ep WHERE 1=1");
        List<Object> paramsList = new ArrayList<>();
        Object engineeringProjectId = paramsMap.get("engineeringProjectId");
        if (null != engineeringProjectId && !"".equals(engineeringProjectId.toString())){
            hql.append(" and ep.engineeringProjectId = ? ");
            paramsList.add(engineeringProjectId.toString());
        }
        Object cityId = paramsMap.get("cityId");
        if (null != cityId && !"".equals(cityId.toString())){
            hql.append(" and ep.cityId = ? ");
            paramsList.add(cityId.toString());
        }
        hql.append(" order by ep.createOn ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
//        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        for (int i = 0,length = paramsList.size(); i < length; i++){
            query.setParameter(i,paramsList.get(i));
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

}
