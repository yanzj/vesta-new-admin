package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.QualityLogEntity;
import com.maxrocky.vesta.domain.repository.QualityLogRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
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
 * Created by Talent on 2016/11/16.
 */
@Repository
public class QualityLogRepositoryImpl extends HibernateDaoImpl implements QualityLogRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public boolean addQualityLogInfo(QualityLogEntity qualityLogEntity) {
        Session session = getCurrentSession();
        session.save(qualityLogEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public List<QualityLogEntity> getQualityLogList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from QualityLogEntity qle where 1=1 ";
        if (map.get("userName") != null && !"".equals(map.get("userName").toString())) {
            hql += " AND qle.userName LIKE '%" + map.get("userName").toString() + "%'";
        }
        if (map.get("userMobile") != null && !"".equals(map.get("userMobile").toString())) {
            hql += " AND qle.userMobile LIKE '%" + map.get("userMobile").toString() + "%'";
        }
        if (map.get("sourceFrom") != null && !"".equals(map.get("sourceFrom").toString())) {
            hql += " AND qle.sourceFrom = '" + map.get("sourceFrom").toString() + "'";
        }
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String strDate = (String) map.get("startDate") + " 00:00:00";
            hql += " AND qle.sysTime >= '" + strDate + "' ";
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String strDate = (String) map.get("startDate") +" 00:00:00";
//            try {
//                Date startDate = sdf.parse(strDate);
//                params.add(startDate);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String strDate = (String) map.get("endDate") + " 24:59:59";
            hql += " AND qle.sysTime <= '" + strDate + "' ";
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String strDate = (String) map.get("endDate") + " 24:59:59";
//            try {
//                Date endDate = sdf.parse(strDate);
//                params.add(endDate);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        }
        hql += " ORDER BY qle.sysTime DESC ";
        if (webPage != null) {
            return this.findByPage(hql, webPage, params);
        }
        List<QualityLogEntity> qualityLogEntityList = (List<QualityLogEntity>) getHibernateTemplate().find(hql, params.toArray());
        return qualityLogEntityList;
    }

    @Override
    public List<QualityLogEntity> getQualityLogList(Map map) {
        List<Object> params = new ArrayList<Object>();
        String hql = " from QualityLogEntity qle where 1=1 ";
        if (map.get("userName") != null && !"".equals(map.get("userName").toString())) {
            hql += " AND qle.userName LIKE '%" + map.get("userName").toString() + "%'";
        }
        if (map.get("userMobile") != null && !"".equals(map.get("userMobile").toString())) {
            hql += " AND qle.userMobile LIKE '%" + map.get("userMobile").toString() + "%'";
        }
        if (map.get("sourceFrom") != null && !"".equals(map.get("sourceFrom").toString())) {
            hql += " AND qle.sourceFrom = '" + map.get("sourceFrom").toString() + "'";
        }
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String strDate = (String) map.get("startDate") + " 00:00:00";
            hql += " AND qle.sysTime >= '" + strDate + "' ";
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String strDate = (String) map.get("startDate") +" 00:00:00";
//            try {
//                Date startDate = sdf.parse(strDate);
//                params.add(startDate);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        }
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String strDate = (String) map.get("endDate") + " 24:59:59";
            hql += " AND qle.sysTime <= '" + strDate + "' ";
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String strDate = (String) map.get("endDate") + " 24:59:59";
//            try {
//                Date endDate = sdf.parse(strDate);
//                params.add(endDate);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        }
        hql += " ORDER BY qle.sysTime DESC ";
        List<QualityLogEntity> qualityLogEntityList = (List<QualityLogEntity>) getHibernateTemplate().find(hql, params.toArray());
        return qualityLogEntityList;
    }
}
