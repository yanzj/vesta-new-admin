package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.ActivitySurveyInfoEntity;
import com.maxrocky.vesta.domain.model.ActivitySurveyListEntity;
import com.maxrocky.vesta.domain.repository.ActivitySurveyRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author WeiYangDong
 * @date 2018/5/10 14:24
 * @deprecated 线下活动调查DAO实现类
 */
@Repository
public class ActivitySurveyRepositoryImpl implements ActivitySurveyRepository{

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
     * 通过主键ID获取活动回顾
     * @param id 主键ID
     * @return ActivitySurveyInfoEntity
     */
    @Override
    public ActivitySurveyInfoEntity getInfoById(String id) {
        Session session = this.getCurrentSession();
        Query query = session.createQuery("FROM ActivitySurveyInfoEntity WHERE id = ?");
        query.setParameter(0,id);
        ActivitySurveyInfoEntity activitySurveyInfoEntity = (ActivitySurveyInfoEntity) query.uniqueResult();
        session.flush();
        session.close();
        return activitySurveyInfoEntity;
    }

    /**
     * 通过主键ID获取活动调查详情
     * @param id 主键ID
     * @return ActivitySurveyListEntity
     */
    @Override
    public ActivitySurveyListEntity getListById(String id) {
        Session session = this.getCurrentSession();
        Query query = session.createQuery("FROM ActivitySurveyListEntity WHERE id = ?");
        query.setParameter(0,id);
        ActivitySurveyListEntity activitySurveyListEntity = (ActivitySurveyListEntity) query.uniqueResult();
        session.flush();
        session.close();
        return activitySurveyListEntity;
    }

    /**
     * 获取活动调查列表数据
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<ActivitySurveyListEntity>
     */
    @Override
    public List<ActivitySurveyListEntity> getList(Map<String, Object> paramsMap, WebPage webPage) {
        StringBuilder hql = new StringBuilder("FROM ActivitySurveyListEntity sl WHERE 1=1");
        List<Object> paramsList = new ArrayList<>();
        //城市ID
        Object cityId = paramsMap.get("cityId");
        if (null != cityId && !"".equals(cityId) && !"0".equals(cityId)){
            hql.append(" AND sl.cityId = ? ");
            paramsList.add(cityId);
        }
        //项目Code
        Object projectCode = paramsMap.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            hql.append(" AND sl.projectCode = ? ");
            paramsList.add(projectCode);
        }
        //当班日期
        Object workOnSta = paramsMap.get("workOnSta");
        if (null != workOnSta && !"".equals(workOnSta)){
            hql.append(" and date_format(sl.workOn,'%Y-%m-%d') >= "+"'"+workOnSta+"'");
        }
        Object workOnEnd = paramsMap.get("workOnEnd");
        if (null != workOnEnd && !"".equals(workOnEnd)){
            hql.append(" and date_format(sl.workOn,'%Y-%m-%d') <= "+"'"+workOnEnd+"'");
        }
        hql.append("ORDER BY sl.createOn DESC");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
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
     * 返回活动回顾城市列表
     * @return List<Map<String,Object>>
     */
    @Override
    public List<Map<String,Object>> getCityList(){
        StringBuilder sql = new StringBuilder("select distinct sp.city_id cityId,sp.city_name cityName from activity_survey_project sp");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 返回活动回顾城市项目列表
     * @param cityId 城市ID
     * @return List<Map<String, Object>>
     */
    @Override
    public List<Map<String, Object>> getProjectList(String cityId) {
        StringBuilder sql = new StringBuilder(" select distinct sp.project_code projectCode,sp.project_name projectName ");
        sql.append(" from activity_survey_project sp ");
        sql.append(" where sp.city_id = ? ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0,cityId);
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }
}
