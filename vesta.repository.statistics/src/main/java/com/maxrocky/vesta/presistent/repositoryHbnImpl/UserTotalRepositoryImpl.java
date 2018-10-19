package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.UserToTalEntity;
import com.maxrocky.vesta.domain.repository.UserTotalRepository;
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
 * Created by liudongxin on 2016/6/4.
 */
@Repository
public class UserTotalRepositoryImpl extends HibernateDaoImpl implements UserTotalRepository {
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
     * return
     */
    @Override
    public UserToTalEntity getTotalInfo(String date) {
        String hql="FROM UserToTalEntity WHERE createDate LIKE '%"+date+"%'";
        Query query = getCurrentSession().createQuery(hql);
        List<UserToTalEntity> totalList=query.list();
        if(totalList.size()>0){
            return totalList.get(0);
        }
        return null;
    }

    /**
     * 获取统计列表
     * param:userToTalEntity
     * param:webPage
     * return
     */
    @Override
    public List<UserToTalEntity> getTotalList(UserToTalEntity userToTalEntity, WebPage webPage) {
        StringBuffer hql = new StringBuffer("FROM UserToTalEntity WHERE 1=1");
        List<Object> params = new ArrayList<Object>();
        //时间
        if(!StringUtil.isEmpty(userToTalEntity.getStartTime())){
            hql.append(" and createDate >= ?");
            params.add(DateUtils.parse(userToTalEntity.getStartTime() + " 00:00:00"));
        }
        if(!StringUtil.isEmpty(userToTalEntity.getEndTime())){
            hql.append(" and createDate <= ?");
            params.add(DateUtils.parse(userToTalEntity.getEndTime() + " 23:59:59"));
        }
        hql.append(" ORDER BY createDate DESC");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        List<UserToTalEntity> totalList=(List<UserToTalEntity>)getHibernateTemplate().find(hql.toString());
        return totalList;
    }

    /**
     * 创建统计信息
     * param:userToTalEntity
     */
    @Override
    public void create(UserToTalEntity userToTalEntity) {
        Session session = getCurrentSession();
        session.save(userToTalEntity);
        session.flush();
    }

    /**
     * 修改统计信息
     * param:userToTalEntity
     */
    @Override
    public void update(UserToTalEntity userToTalEntity) {
        Session session = getCurrentSession();
        session.update(userToTalEntity);
        session.flush();
        session.close();
    }
}
