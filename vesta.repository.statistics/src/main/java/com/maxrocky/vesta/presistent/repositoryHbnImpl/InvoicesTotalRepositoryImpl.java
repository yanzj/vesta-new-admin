package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.InvoicesTotalEntity;
import com.maxrocky.vesta.domain.repository.InvoicesTotalRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import ma.glasnost.orika.MapperFacade;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudongxin on 2016/6/2.
 */
@Repository
public class InvoicesTotalRepositoryImpl extends HibernateDaoImpl implements InvoicesTotalRepository {
    /* mapper */
    @Autowired
    private MapperFacade mapper;
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 通过日期获取统计信息
     * param:创建日期
     * param:项目
     * return
     */
    @Override
    public InvoicesTotalEntity getTotalInfo(String date,String project) {
        String hql="FROM InvoicesTotalEntity WHERE createDate LIKE '%"+date+"%'";
        if(!StringUtil.isEmpty(project)){
            hql+=" AND project='"+project+"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<InvoicesTotalEntity> totalList=query.list();
        if(totalList.size()>0){
            return totalList.get(0);
        }
        return null;
    }

    /**
     * 获取统计列表
     * param:invoicesTotalEntity
     * param:webPage
     * return
     */
    @Override
    public List<InvoicesTotalEntity> getTotalList(InvoicesTotalEntity invoicesTotalEntity, WebPage webPage) {
        StringBuffer hql = new StringBuffer("FROM InvoicesTotalEntity WHERE 1=1");
        List<Object> params = new ArrayList<Object>();
        //城市
        /*if(!StringUtil.isEmpty(invoicesTotalEntity.getCity())){
            if(!"0".equals(invoicesTotalEntity.getCity())){
                hql.append(" and city=?");
                params.add(invoicesTotalEntity.getCity());
            }

        }
        //项目
        if(!StringUtil.isEmpty(invoicesTotalEntity.getProject())) {
            if (!"0".equals(invoicesTotalEntity.getProject())) {
                hql.append(" and project=?");
                params.add(invoicesTotalEntity.getProject());
            }
        }*/
        //时间
        if(!StringUtil.isEmpty(invoicesTotalEntity.getStartTime())){
            hql.append(" and createDate >= ?");
            params.add(DateUtils.parse(invoicesTotalEntity.getStartTime() + " 00:00:00"));
        }
        if(!StringUtil.isEmpty(invoicesTotalEntity.getEndTime())){
            hql.append(" and createDate <= ?");
            params.add(DateUtils.parse(invoicesTotalEntity.getEndTime() + " 23:59:59"));
        }
        hql.append(" ORDER BY createDate DESC");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        List<InvoicesTotalEntity> totalList=(List<InvoicesTotalEntity>)getHibernateTemplate().find(hql.toString());
        return totalList;
    }

    /**
     * 创建统计信息
     * param:invoicesTotalEntity
     */
    @Override
    public void create(InvoicesTotalEntity invoicesTotalEntity) {
        Session session = getCurrentSession();
        session.save(invoicesTotalEntity);
        session.flush();
    }

    /**
     * 修改统计信息
     * param:invoicesTotalEntity
     */
    @Override
    public void update(InvoicesTotalEntity invoicesTotalEntity) {
        Session session = getCurrentSession();
        session.update(invoicesTotalEntity);
        session.flush();
        session.close();
    }
}
