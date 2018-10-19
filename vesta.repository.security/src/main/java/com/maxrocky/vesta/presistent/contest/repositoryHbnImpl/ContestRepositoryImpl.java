package com.maxrocky.vesta.presistent.contest.repositoryHbnImpl;

import com.maxrocky.vesta.domain.contest.repository.ContestRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 安全比武
 * Created by yuanyn on 2017/9/1.
 */
@Repository
public class ContestRepositoryImpl extends HibernateDaoImpl implements ContestRepository{

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<Object[]> getContestList(Map map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" rt.patId,rt.projectName,rt.createName,rt.createDate,rt.projectId,rt.description ");
        sql.append(" from ReadilyTakeEntity rt ");
        sql.append(" where 1=1 ");
        sql.append(" and additional='1' ");
        //项目公司ID
        if (!StringUtil.isEmpty(map.get("projectId").toString())) {
            sql.append(" and rt.projectId=? ");
            params.add(map.get("projectId").toString());
        }
        //隐患关键字
        if (!StringUtil.isEmpty(map.get("describe").toString())) {
            sql.append(" and rt.description like? ");
            params.add(map.get("describe").toString());
        }
        //开始日期
        if (map.get("startDate") != null && !"".equals(map.get("startDate").toString())) {
            String startDate = map.get("startDate").toString() + " 00:00:00";
            sql.append(" and rt.createDate >= '" + startDate +"' ");
        }
        //结束时间
        if (map.get("endDate") != null && !"".equals(map.get("endDate").toString())) {
            String endDate = map.get("endDate").toString() + " 23:59:59";
            sql.append(" and rt.createDate <= '" + endDate +"' ");
        }
        //创建人
        if(!StringUtil.isEmpty(map.get("createName").toString())){
            sql.append(" and rt.createName like '%"+map.get("createName")+"%' ");
        }
        sql.append(" order by rt.modifyDate desc ");
        if (webPage != null) {
            return this.findByPage(sql.toString(), webPage, params);
        }
        List<Object[]> list = (List<Object[]>) getHibernateTemplate().find(sql.toString(), params.toArray());
        return list;
    }

    @Override
    public List<Object[]> getContestImageList(List<String> idList) {
        StringBuffer sql = new StringBuffer(" select ");
        sql.append(" BUSINESS_ID,ID,IMAGE_URL,IMAGE_TYPE ");
        sql.append(" from st_security_image ");
        sql.append(" where 1=1");
        sql.append(" and BUSINESS_ID in(:idList) ");
        sql.append(" and STATE='1' and IMAGE_TYPE='1' ");
        Query query = getCurrentSession().createSQLQuery(sql.toString());
        query.setParameterList("idList", idList);
        return (List<Object[]>) query.list();
    }
}
