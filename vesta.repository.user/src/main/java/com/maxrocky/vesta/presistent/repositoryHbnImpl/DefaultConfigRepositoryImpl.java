package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.DefaultConfigRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.*;

/**
 * 默认配置_持久层实现类
 * 2016/6/21_Wyd
 */
@Repository
public class DefaultConfigRepositoryImpl implements DefaultConfigRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){ return sessionFactory.openSession(); }

    /**
     * 检索默认配置
     */
    public DefaultConfigEntity queryDefaultConfig(String configType){
        Session session = getCurrentSession();
        String hql = " from DefaultConfigEntity where configType = ? ";
        Query query = session.createQuery(hql);
        DefaultConfigEntity defaultConfigEntity = (DefaultConfigEntity) query.setParameter(0,configType).uniqueResult();
        session.flush();
        session.close();
        return defaultConfigEntity;
    }

    /**
     * 保存或更新Entity
     * @param entity
     */
    public <T> void saveOrUpdate(T entity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    /**
     * 通过主键ID获取默认配置信息
     * @param id   _主键ID
     * @return DefaultConfigEntity
     */
    public DefaultConfigEntity getDefaultConfig(String id){
        Session session = getCurrentSession();
        DefaultConfigEntity defaultConfigEntity = (DefaultConfigEntity) session.get(DefaultConfigEntity.class, id);
        session.flush();
        session.close();
        return defaultConfigEntity;
    }

    /**
     * 获取用户类型可视项目配置列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getUserShowProjectConfigList(Map<String,Object> paramsMap,WebPage webPage){
        StringBuilder sql = new StringBuilder();
        sql.append(" select p.PINYIN_CODE projectNum,p.NAME projectName,pu.user_types userTypes ");
        sql.append(" from house_project p ");
        sql.append(" left join project_userType pu on p.PINYIN_CODE = pu.project_num ");
        sql.append(" where 1 = 1 ");
        List<String> paramsList = new ArrayList<>();
        //城市
        Object cityId = paramsMap.get("cityId");
        if (null != cityId && !"".equals(cityId.toString()) && !"0".equals(cityId.toString())){
            sql.append(" and p.CITY_ID = ? ");
            paramsList.add(cityId.toString());
        }
        //项目
        Object projectNum = paramsMap.get("projectNum");
        if (null != projectNum && !"".equals(projectNum.toString()) && !"0".equals(projectNum.toString())){
            sql.append(" and p.PINYIN_CODE = ? ");
            paramsList.add(projectNum.toString());
        }
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        for (int i = 0,length = paramsList.size(); i < length; i++){
            sqlQuery.setParameter(i,paramsList.get(i));
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (null != webPage){
            sqlQuery.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            sqlQuery.setMaxResults(webPage.getPageSize());
        }
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过项目编码获取用户类型可视项目配置
     * @param projectNum 项目编码
     * @return List<UserTypeProjectConfigEntity>
     */
    public List<UserTypeProjectConfigEntity> getUserTypeProjectConfigByProjectNum(String projectNum){
        Session session = getCurrentSession();
        Query query = session.createQuery(" FROM UserTypeProjectConfigEntity uc WHERE uc.projectNum = ? ");
        query.setParameter(0,projectNum);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过项目编码获取项目详情
     * @param projectNum 项目编码
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getProjectInfoByNum(String projectNum){
        StringBuilder sql = new StringBuilder();
        sql.append(" select c.ID cityId,c.CITY_CODE cityNum,c.CITY_NAME cityName,p.PINYIN_CODE projectNum,p.NAME projectName ");
        sql.append(" from house_project p,house_city c ");
        sql.append(" where p.CITY_ID = c.ID ");
        sql.append(" and p.PINYIN_CODE = ? ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0, projectNum);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过用户类型获取城市列表
     * @param userType 用户类型
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getCityListByUserType(String userType){
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct pu.city_id cityId,pu.city_name cityName,pu.city_num cityNum ");
        sql.append(" from project_userType pu ");
        sql.append(" where pu.user_types like ? ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0, "%" + userType + "%");
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过城市和用户类型获取项目列表
     * @param cityId 城市ID
     * @param userType 用户类型
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getProjectListByCityAndUserType(String cityId,String userType){
        StringBuilder sql = new StringBuilder();
        sql.append(" select pu.project_name name,pu.project_num pinyinCode ");
        sql.append(" from project_userType pu ");
        sql.append(" where pu.city_id = ? and pu.user_types like ? ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0, cityId);
        sqlQuery.setParameter(1, "%" + userType + "%");
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 获取App项目可视功能模块配置列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getAppShowFunctionConfigList(Map<String,Object> paramsMap,WebPage webPage){
        StringBuilder sql = new StringBuilder();
        sql.append(" select p.PINYIN_CODE projectNum,p.NAME projectName,af.function_modules functionModules ");
        sql.append(" from house_project p ");
        sql.append(" left join app_function af on p.PINYIN_CODE = af.project_num ");
        sql.append(" where 1 = 1 ");
        List<String> paramsList = new ArrayList<>();
        //城市
        Object cityId = paramsMap.get("cityId");
        if (null != cityId && !"".equals(cityId.toString()) && !"0".equals(cityId.toString())){
            sql.append(" and p.CITY_ID = ? ");
            paramsList.add(cityId.toString());
        }
        //项目
        Object projectNum = paramsMap.get("projectNum");
        if (null != projectNum && !"".equals(projectNum.toString()) && !"0".equals(projectNum.toString())){
            sql.append(" and p.PINYIN_CODE = ? ");
            paramsList.add(projectNum.toString());
        }
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        for (int i = 0,length = paramsList.size(); i < length; i++){
            sqlQuery.setParameter(i,paramsList.get(i));
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (null != webPage){
            sqlQuery.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            sqlQuery.setMaxResults(webPage.getPageSize());
        }
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过项目编码获取App项目可视功能模块配置
     * @param projectNum 项目编码
     * @return List<UserTypeProjectConfigEntity>
     */
    public List<AppFunctionConfigEntity> getAppShowFunctionConfigByProjectNum(String projectNum){
        Session session = getCurrentSession();
        Query query = session.createQuery(" FROM AppFunctionConfigEntity af WHERE af.projectNum = ? ");
        query.setParameter(0,projectNum);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 获取客户端可视项目配置列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getClientShowProjectConfigList(Map<String,Object> paramsMap,WebPage webPage){
        StringBuilder sql = new StringBuilder();
        sql.append(" select p.PINYIN_CODE projectNum,p.NAME projectName,pc.client_ids clientIds ");
        sql.append(" from house_project p ");
        sql.append(" left join project_client pc on p.PINYIN_CODE = pc.project_num ");
        sql.append(" where 1 = 1 ");
        List<String> paramsList = new ArrayList<>();
        //城市
        Object cityId = paramsMap.get("cityId");
        if (null != cityId && !"".equals(cityId.toString()) && !"0".equals(cityId.toString())){
            sql.append(" and p.CITY_ID = ? ");
            paramsList.add(cityId.toString());
        }
        //项目
        Object projectNum = paramsMap.get("projectNum");
        if (null != projectNum && !"".equals(projectNum.toString()) && !"0".equals(projectNum.toString())){
            sql.append(" and p.PINYIN_CODE = ? ");
            paramsList.add(projectNum.toString());
        }
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        for (int i = 0,length = paramsList.size(); i < length; i++){
            sqlQuery.setParameter(i,paramsList.get(i));
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (null != webPage){
            sqlQuery.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            sqlQuery.setMaxResults(webPage.getPageSize());
        }
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过项目编码获取客户端可视项目配置
     * @param projectNum 项目编码
     * @return List<ClientProjectConfigEntity>
     */
    public List<ClientProjectConfigEntity> getClientShowProjectConfigByProjectNum(String projectNum){
        Session session = getCurrentSession();
        Query query = session.createQuery(" FROM ClientProjectConfigEntity cp WHERE cp.projectNum = ? ");
        query.setParameter(0,projectNum);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过项目编码集合获取客户端可视项目配置
     * @param roleScopes 项目编码集合
     * @return List<ClientProjectConfigEntity>
     */
    public List<ClientProjectConfigEntity> getClientShowProjectConfigByProjectNums(String roleScopes){
        Session session = getCurrentSession();
        StringBuilder hql = new StringBuilder("FROM ClientProjectConfigEntity cp");
        if (!roleScopes.equals("") && !roleScopes.contains("all")){
            hql.append(" WHERE cp.projectNum in ("+roleScopes.substring(0,roleScopes.length()-1)+") ");
        }
        Query query = session.createQuery(hql.toString());
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 获取客户端基础配置列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    public List<ClientConfigEntity> getClientConfigList(Map<String,Object> paramsMap, WebPage webPage){
        StringBuilder hql = new StringBuilder();
        hql.append(" FROM ClientConfigEntity cf ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
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
     * 通过客户端ID集合获取客户端基础配置列表
     * @param ids 客户端ID集合
     * @return List<Map<String,Object>>
     */
    public List<ClientConfigEntity> getClientConfigList(Set<Integer> ids){
        StringBuilder hql = new StringBuilder();
        hql.append(" FROM ClientConfigEntity cf WHERE cf.id in (:ids)");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        query.setParameterList("ids",ids);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过主键ID获取客户端基础配置信息
     * @param id 主键
     * @return ClientConfigEntity
     */
    public ClientConfigEntity getClientConfigById(String id){
        StringBuilder hql = new StringBuilder("FROM ClientConfigEntity cf WHERE cf.id = ?");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter(0,Integer.valueOf(id));
        ClientConfigEntity clientConfigEntity = (ClientConfigEntity) query.uniqueResult();
        return clientConfigEntity;
    }

    @Override
    public ClientConfigEntity getClientConfigByAppId(String appId) {
        StringBuilder hql = new StringBuilder("FROM ClientConfigEntity c WHERE c.weChatAppId = ?");
        Query query = getCurrentSession().createQuery(hql.toString()).setParameter(0, appId);
        ClientConfigEntity clientConfigEntity = (ClientConfigEntity) query.uniqueResult();
        return clientConfigEntity;
    }


}
