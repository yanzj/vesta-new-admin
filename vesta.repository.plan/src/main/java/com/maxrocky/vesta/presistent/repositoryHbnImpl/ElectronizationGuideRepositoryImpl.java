package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ElectronizationGuideEntity;
import com.maxrocky.vesta.domain.repository.ElectronizationGuideRepository;
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
 * Created by hp on 2018/5/23.
 */
@Repository
public class ElectronizationGuideRepositoryImpl implements ElectronizationGuideRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<ElectronizationGuideEntity> getElectronizationGuideList(Map<String, Object> map, WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        StringBuilder sql = new StringBuilder(" FROM ElectronizationGuideEntity WHERE 1=1 AND state='1' ");
        //文档名
        if(!StringUtil.isEmpty(map.get("fileName").toString())){
            sql.append( " AND fileName like? " );
            params.add(map.get("fileName").toString());
        }
        //修改时间
        Object date = map.get("createTime");
        if(null != date && !"".equals(date)){
            sql.append( " AND date_format(createTime,'%Y-%m-%d') = "+"'"+date+"' " );
        }
        sql.append(" order by createTime ASC");
        Session session = getCurrentSession();
        Query query = session.createQuery(sql.toString());
        for (int i = 0,length = params.size(); i < length; i++){
            query.setParameter(i,params.get(i));
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List<ElectronizationGuideEntity> list = query.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public void saveOrUpdateElectronizationGuide(ElectronizationGuideEntity electronizationGuideEntity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(electronizationGuideEntity);
        session.flush();
        session.close();
    }

    @Override
    public ElectronizationGuideEntity getElectronizationGuideById(Long id) {
        StringBuffer hql = new StringBuffer(" FROM ElectronizationGuideEntity ");
        hql.append(" WHERE 1=1 AND state='1' AND id=:id ");
        Query query = getCurrentSession().createQuery(hql.toString());
        query.setParameter("id",id);
        return (ElectronizationGuideEntity)query.uniqueResult();
    }
}
