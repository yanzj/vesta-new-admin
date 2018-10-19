package com.maxrocky.vesta.presistent.basic.repositoryHbnImpl;

import com.maxrocky.vesta.domain.baisc.model.SecurityAssessmentTempEntity;
import com.maxrocky.vesta.domain.baisc.repository.BasicRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 2017/6/15.
 */
@Repository
public class BasicRepositoryImpl extends HibernateDaoImpl implements BasicRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public SecurityAssessmentTempEntity getSecurityAssessByName(String level, String parentId, String name, String domain) {
        String hql = "from SecurityAssessmentTempEntity where assessmentName=:assessmentName and domain=:domain AND level=:level ";
        if (!StringUtil.isEmpty(parentId)) {
            hql += " and parentId = '" + parentId + "'";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("assessmentName", name);
        query.setParameter("domain", domain);
        query.setParameter("level", level);
        SecurityAssessmentTempEntity assessmentTempEntity = (SecurityAssessmentTempEntity) query.uniqueResult();
        return assessmentTempEntity;
    }

    @Override
    public SecurityAssessmentTempEntity getSecurityAssessByContent(String level, String parentId, String content, String domain) {
        String hql = "from SecurityAssessmentTempEntity where assessmentContent=:assessmentContent and domain=:domain AND level=:level ";
        if (!StringUtil.isEmpty(parentId)) {
            hql += " and parentId = '" + parentId + "'";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("assessmentContent", content);
        query.setParameter("domain", domain);
        query.setParameter("level", level);
        SecurityAssessmentTempEntity assessmentTempEntity = (SecurityAssessmentTempEntity) query.uniqueResult();
        return assessmentTempEntity;
    }

    @Override
    public void updateEntity(Object object) {
        Session session = getCurrentSession();
        session.update(object);
        session.flush();
        session.close();
    }

    @Override
    public void addEntity(Object object) {
        Session session = getCurrentSession();
        session.save(object);
        session.flush();
        session.close();
    }

    @Override
    public List<Object[]> getAssessTempList(Map map, WebPage webPage) {
        StringBuffer sql = new StringBuffer(" SELECT sat.ASSESSMENT_ID,sat.ASSESSMENT_NAME,sat.ASSESSMENT_SCORE,");
        sql.append("satm.CONTENT_NUM,satm.ASSESSMENT_CONTENT,satm.ASSESSMENT_GRADE,satm.DEDUCTION_PRINCIPLE,satm.ASSESSMENT_DOMAIN ");
        sql.append(" FROM st_assessment_temp sat ");
        sql.append(" LEFT JOIN st_assessment_temp satm ON satm.ASSESSMENT_PARENT_ID = sat.ASSESSMENT_ID ");
        sql.append(" WHERE sat.STATE = '1' AND satm.ASSESSMENT_LEVEL = '2' ");
        if (map.get("domain") != null && !"".equals(map.get("domain").toString())) {
            sql.append(" and sat.ASSESSMENT_DOMAIN = '" + map.get("domain").toString() + "'");
        }
        sql.append(" ORDER BY sat.ASSESSMENT_ID ASC,satm.CONTENT_NUM ASC,sat.MODIFY_DATE DESC");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        if (webPage != null) {
            query.setFirstResult(webPage.getStartRow());
            query.setMaxResults(webPage.getPageSize());
        }
        return query.list();
    }

    @Override
    public List<Object[]> getAssessTempList(Map map) {
        StringBuffer sql = new StringBuffer(" SELECT sat.ASSESSMENT_ID,sat.ASSESSMENT_NAME,sat.ASSESSMENT_SCORE,");
        sql.append("satm.CONTENT_NUM,satm.ASSESSMENT_CONTENT,satm.ASSESSMENT_GRADE,satm.DEDUCTION_PRINCIPLE,satm.ASSESSMENT_DOMAIN ");
        sql.append(" FROM st_assessment_temp sat ");
        sql.append(" LEFT JOIN st_assessment_temp satm ON satm.ASSESSMENT_PARENT_ID = sat.ASSESSMENT_ID ");
        sql.append(" WHERE sat.STATE = '1' AND satm.ASSESSMENT_LEVEL = '2' ");
        if (map.get("domain") != null && !"".equals(map.get("domain").toString())) {
            sql.append(" and sat.ASSESSMENT_DOMAIN = '" + map.get("domain").toString() + "'");
        }
        sql.append(" ORDER BY sat.ASSESSMENT_ID ASC,satm.CONTENT_NUM ASC,sat.MODIFY_DATE DESC");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        return query.list();
    }

    @Override
    public int getCount(Map map) {
        StringBuffer sql = new StringBuffer(" SELECT count(1) ");
        sql.append(" FROM st_assessment_temp sat ");
        sql.append(" LEFT JOIN st_assessment_temp satm ON satm.ASSESSMENT_PARENT_ID = sat.ASSESSMENT_ID ");
        sql.append(" WHERE sat.STATE = '1' AND satm.ASSESSMENT_LEVEL = '2' ");
        if (map.get("domain") != null && !"".equals(map.get("domain").toString())) {
            sql.append(" and sat.ASSESSMENT_DOMAIN = '" + map.get("domain").toString() + "'");
        }
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        BigInteger count = (BigInteger) query.uniqueResult();
        if (count.intValue() > 0) {
            return count.intValue();
        } else {
            return 0;
        }
    }
}
