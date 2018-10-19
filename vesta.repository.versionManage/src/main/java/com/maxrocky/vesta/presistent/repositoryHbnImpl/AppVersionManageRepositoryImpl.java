package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.domain.model.AppVersionInfoEntity;
import com.maxrocky.vesta.domain.repository.AppVersionManageRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Talent on 2016/11/3.
 */
@Repository
public class AppVersionManageRepositoryImpl extends HibernateDaoImpl implements AppVersionManageRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<AppVersionInfoEntity> getVersionListByType(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from AppVersionInfoEntity avie where avie.state=1 ";
        if (map.get("versionType") != null && !StringUtil.isEmpty(map.get("versionType").toString())) {
            hql += " and avie.appVersionType = ? ";
            params.add(map.get("versionType").toString());
        }
        if (map.get("appEdition") != null && !StringUtil.isEmpty(map.get("appEdition").toString())) {
            hql += " and avie.appEdition = ? ";
            params.add(map.get("appEdition").toString());
        }
        if (map.get("versionName") != null && !StringUtil.isEmpty(map.get("versionName").toString())) {
            hql += " and avie.appVersionName like '%" + map.get("versionName").toString() + "%' ";
        }
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            hql += " and avie.createOn >= '"+startDate+"' ";
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString()+ " 23:59:59";
            hql += " and avie.createOn <= '"+endDate+"' ";
        }
        hql+=" order by avie.createOn desc";
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        List<AppVersionInfoEntity> appVersionInfoEntityList = (List<AppVersionInfoEntity>)getHibernateTemplate().find(hql.toString());
        return appVersionInfoEntityList;
    }

    @Override
    public boolean saveAppVersion(AppVersionInfoEntity appVersionEntity) {
        Session session = getCurrentSession();
        session.save(appVersionEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public AppVersionInfoEntity getVersionById(String appVersionId) {
        String sql = " from AppVersionInfoEntity where appVersionId ='" + appVersionId + "'";
        Query query = getCurrentSession().createQuery(sql);
        return (AppVersionInfoEntity) query.uniqueResult();
    }

    @Override
    public boolean updateAppVersion(AppVersionInfoEntity appVersionInfoEntity) {
        Session session = getCurrentSession();
        session.update(appVersionInfoEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public AppVersionInfoEntity getNewVersionByAppVersionType(String appVersionType,String appEdition) {
        Session session = getCurrentSession();
        String hql = " from AppVersionInfoEntity where createOn = ( select max(createOn) from AppVersionInfoEntity where state = 1 and appVersionType = ? and appEdition = ?) ";
        Query query = session.createQuery(hql);
        query.setParameter(0,appVersionType);
        query.setParameter(1,appEdition);
        AppVersionInfoEntity appVersion = (AppVersionInfoEntity) query.uniqueResult();
        session.flush();
        session.close();
        return appVersion;
    }
}
