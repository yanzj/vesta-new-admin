package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.OfflineActivityEntity;
import com.maxrocky.vesta.domain.model.OfflineActivitySignEntity;
import com.maxrocky.vesta.domain.repository.OfflineActivityRespository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 线下活动数据持久层实现类
 * Created by WeiYangDong on 2017/8/21.
 */
@Repository
public class OfflineActivityRespositoryImpl implements OfflineActivityRespository{

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){ return sessionFactory.openSession(); }

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
     * 删除Entity
     * @param entity
     */
    public <E> void delete(E entity) {
        Session session = this.getCurrentSession();
        session.delete(entity);
        session.flush();
        session.close();
    }

    /**
     * 获取线下活动列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<OfflineActivityEntity>
     */
    public List<OfflineActivityEntity> getOfflineActivityList(Map<String,Object> paramsMap, WebPage webPage){
        StringBuilder hql = new StringBuilder("FROM OfflineActivityEntity oa WHERE 1=1");
        List<Object> paramsList = new ArrayList<>();
        //城市范围
        Object cityId = paramsMap.get("cityId");
        if (null != cityId && !"".equals(cityId) && !"0".equals(cityId)){
            hql.append(" AND oa.cityId = ? ");
            paramsList.add(cityId.toString());
        }
        //活动状态
        Object activityState = paramsMap.get("activityState");
        if (null != activityState && !"".equals(activityState)){
            Date date = new Date();
            if ("0".equals(activityState.toString())){
                //未开始
                hql.append(" AND oa.activityStartTime > ? ");
                paramsList.add(date);
            }else if ("1".equals(activityState.toString())){
                //进行中
                hql.append(" AND oa.activityStartTime <= ? AND oa.activityEndTime >= ? ");
                paramsList.add(date);
                paramsList.add(date);
            }else {
                //已结束
                hql.append(" AND oa.activityEndTime < ? ");
                paramsList.add(date);
            }
        }
        //活动标题
        Object activityTitle = paramsMap.get("activityTitle");
        if (null != activityTitle && !"".equals(activityTitle)){
            hql.append(" AND oa.activityTitle like ? ");
            paramsList.add("%"+activityTitle.toString()+"%");
        }
        //发布状态
        Object releaseStatus = paramsMap.get("releaseStatus");
        if (null != releaseStatus){
            hql.append(" AND sp.releaseStatus = ? ");
            paramsList.add(releaseStatus);
        }
        //活动日期区间1
        Object activityStartTimeStr = paramsMap.get("activityStartTimeStr");
        if (activityStartTimeStr != null && !"".equals(activityStartTimeStr)) {
            hql.append(" and date_format(oa.activityStartTime,'%Y-%m-%d') >= "+"'"+activityStartTimeStr+"'");
        }
        //活动日期区间2
        Object activityEndTimeStr = paramsMap.get("activityEndTimeStr");
        if (activityEndTimeStr != null && !"".equals(activityEndTimeStr)) {
            hql.append(" and date_format(oa.activityEndTime,'%Y-%m-%d') <= "+"'"+activityEndTimeStr+"'");
        }
        hql.append(" ORDER BY oa.createOn desc");
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
     * 通过主键ID获取线下活动
     * @param activityId 活动主键ID
     * @return OfflineActivityEntity
     */
    public OfflineActivityEntity getOfflineActivityById(String activityId){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM OfflineActivityEntity WHERE activityId = ?").setParameter(0, activityId);
        OfflineActivityEntity offlineActivityEntity = (OfflineActivityEntity) query.uniqueResult();
        session.flush();
        session.close();
        return offlineActivityEntity;
    }

    /**
     * 获取活动签到记录列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> getOfflineActivitySignList(Map<String,Object> paramsMap, WebPage webPage){
        StringBuilder sql = new StringBuilder();
        sql.append(" select oas.sign_id signId,oas.owner_name ownerName,oas.mobile mobile,oas.address address,oas.partake_number partakeNumber, ");
        sql.append(" oas.create_by createBy,oas.create_on createOn,oas.prize_info prizeInfo,oa.activity_title activityTitle ");
        sql.append(" from offline_activity_sign oas left join offline_activity oa on oas.activity_id = oa.activity_id ");
        sql.append(" where 1=1 ");
        List<Object> paramsList = new ArrayList<>();
        //活动ID
        Object activityId = paramsMap.get("activityId");
        if (null != activityId && !"".equals(activityId)){
            sql.append(" and oa.activity_id = ? ");
            paramsList.add(activityId);
        }
        //活动标题
        Object activityTitle = paramsMap.get("activityTitle");
        if (null != activityTitle && !"".equals(activityTitle)){
            sql.append(" and oa.activity_title like ? ");
            paramsList.add("%"+activityTitle+"%");
        }
        //姓名
        Object ownerName = paramsMap.get("ownerName");
        if (null != ownerName && !"".equals(ownerName)){
            sql.append(" and oas.owner_name like ? ");
            paramsList.add("%"+ownerName+"%");
        }
        //联系方式
        Object mobile = paramsMap.get("mobile");
        if (null != mobile && !"".equals(mobile)){
            sql.append(" and oas.mobile = ? ");
            paramsList.add(mobile);
        }
        //签到时间
        Object createOnStr = paramsMap.get("createOnStr");
        if (null != createOnStr && !"".equals(createOnStr)){
            sql.append(" and date_format(oas.create_on,'%Y-%m-%d') = ? ");
            paramsList.add(createOnStr);
        }
        //中奖信息
        Object prizeInfo = paramsMap.get("prizeInfo");
        if (null != prizeInfo && !"".equals(prizeInfo)){
            sql.append(" and oas.prize_info = ? ");
            paramsList.add(prizeInfo);
        }
        sql.append(" order by oas.create_on desc ");
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        for (int i = 0,length = paramsList.size(); i < length; i++){
            sqlQuery.setParameter(i,paramsList.get(i));
        }
        if (null != webPage){
            sqlQuery.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            sqlQuery.setMaxResults(webPage.getPageSize());
        }
        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过活动ID删除活动签到记录
     * @param activityId 活动ID
     */
    public void delActivitySignByActivity(String activityId){
        Query query = getCurrentSession().createQuery("DELETE FROM OfflineActivitySignEntity WHERE activityId = ?");
        query.setParameter(0,activityId).executeUpdate();
    }

    /**
     * 通过活动ID获取活动签到记录列表
     * @param activityId 活动
     * @return List<OfflineActivitySignEntity>
     */
    public List<OfflineActivitySignEntity> getOfflineActivitySignByActivity(String activityId){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM OfflineActivitySignEntity WHERE activityId = ?").setParameter(0, activityId);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过活动ID和联系电话获取活动签到信息
     * @param activityId 活动ID
     * @param mobile 联系电话
     * @return List<OfflineActivitySignEntity>
     */
    public List<OfflineActivitySignEntity> getOfflineActivitySignByActivityAndMobile(String activityId,String mobile){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM OfflineActivitySignEntity WHERE activityId = ? AND mobile = ? ")
                .setParameter(0, activityId).setParameter(1, mobile);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

}
