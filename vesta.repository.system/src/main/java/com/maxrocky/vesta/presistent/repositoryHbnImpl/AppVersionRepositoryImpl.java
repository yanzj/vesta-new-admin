package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.AppVersionEntity;
import com.maxrocky.vesta.domain.repository.AppVersionRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/28.
 */
@Repository
public class AppVersionRepositoryImpl extends HibernateDaoImpl implements AppVersionRepository {

    @Resource
    private SessionFactory sessionFactory;
    private Session getCurrentSession(){
       return sessionFactory.openSession();
    }
    /**
     * 获取某手机类型所有版本
     * @param appVersionEntity
     * @return
     */
    @Override
    public List<AppVersionEntity> getVersionByType(AppVersionEntity appVersionEntity,WebPage webPage) {
        List<AppVersionEntity> appVersionEntities = new ArrayList<AppVersionEntity>();
        List<Object> params = new ArrayList<>();
        StringBuffer hql = new StringBuffer("from AppVersionEntity as a where a.state='1'");
        // 版本类型
        if(null != appVersionEntity.getAppVersionType() && !"".equals(appVersionEntity.getAppVersionType()) && !"10".equals(appVersionEntity.getAppVersionType())){
            hql.append(" and a.appVersionType =?");
            params.add(appVersionEntity.getAppVersionType());
        }
        // 版本名称
        if(null != appVersionEntity.getAppVersionName() && !"".equals(appVersionEntity.getAppVersionName())) {
            hql.append(" and a.appVersionName like ?");
            params.add("%"+appVersionEntity.getAppVersionName()+"%");
        }
        //创建开始时间
        if(null != appVersionEntity.getBeginDate() && !"".equals(appVersionEntity.getBeginDate())){
            hql.append(" and a.createOn >=?");
            params.add(DateUtils.parse(appVersionEntity.getBeginDate() + " 00:00:00"));
        }
        //创建结束时间
        if(null != appVersionEntity.getEndDate() && !"".equals(appVersionEntity.getEndDate())){
            hql.append(" and a.createOn <=?");
            params.add(DateUtils.parse(appVersionEntity.getEndDate() + " 23:59:59"));
        }
        hql.append("order by a.createOn desc");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        appVersionEntities = (List<AppVersionEntity>)getHibernateTemplate().find(hql.toString());
        return appVersionEntities;
    }

    /**
     * 获取某手机类型最新版本
     * @param type
     * @return
     */
    @Override
    public AppVersionEntity getNewVersionByType(String type,String userType) {
        String hql = "from AppVersionEntity as a where a.appVersionType = :appVersionType and a.state=:state and a.userType = :userType order by a.createOn desc ";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appVersionType",type);
        query.setParameter("state", AppVersionEntity.STATE_ON);//有效版本
        query.setParameter("userType",userType);
        if (query.list().size()>0){
            AppVersionEntity appVersionEntity = (AppVersionEntity)query.list().get(0);
            return appVersionEntity;
        }
        else {
            return null;
        }
    }

    /**
     * 添加新版本
     * @param appVersionEntity
     * @return
     */
    @Override
    public boolean saveAppVersion(AppVersionEntity appVersionEntity) {
        Session session = getCurrentSession();
        session.save(appVersionEntity);
        session.flush();
        session.close();
        return true;
    }

    /**
     * 修改版本信息
     * @param appVersionEntity
     * @return
     */
    @Override
    public boolean updateAppVersion(AppVersionEntity appVersionEntity) {
        AppVersionEntity appVersionEntity1 = getVersionBy(appVersionEntity.getAppVersionId());
        if (appVersionEntity1!=null){
            Session session = getCurrentSession();
            session.update(appVersionEntity);
            session.flush();
            session.close();
            return true;
        }
        return false;
    }

    /**
     * 根据id获得版本详情
     * @param id
     * @return
     */
    @Override
    public AppVersionEntity getVersionBy(String id) {
        String hql = "from AppVersionEntity as a where a.appVersionId=:appVersionId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("appVersionId",id);
        if (query.list().size()>0){
            return (AppVersionEntity)query.list().get(0);
        }
        return null;
    }

    /**
     * 通过appVersionType获得版本详情
     * 2016-07-19_Wyd
     * @param appVersionType IOS:1,安卓:2
     * @return AppVersionEntity
     */
    public AppVersionEntity getAppVersionByAppType(String appVersionType){
        Session session = getCurrentSession();
        String hql = " from AppVersionEntity where createOn = ( select max(createOn) from AppVersionEntity where state = 1 and appVersionType = ? ) ";
        Query query = session.createQuery(hql);
        query.setParameter(0,appVersionType);
        AppVersionEntity appVersion = (AppVersionEntity) query.uniqueResult();
        session.flush();
        session.close();
        return appVersion;
    }
}
