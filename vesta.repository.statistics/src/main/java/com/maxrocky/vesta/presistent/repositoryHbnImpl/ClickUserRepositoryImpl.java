package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ClickUsersEntity;
import com.maxrocky.vesta.domain.repository.ClickUserRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
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
 * Created by liudongxin on 2016/6/5.
 */
@Repository
public class ClickUserRepositoryImpl extends HibernateDaoImpl implements ClickUserRepository {
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
     * param:菜单:模块id
     * return
     */
    @Override
    public ClickUsersEntity getTotalInfo(String date,String id,String userId) {
        String hql="FROM ClickUsersEntity WHERE createDate LIKE '%"+date+"%' AND foreignId='"+id+"'";
        if(!StringUtil.isEmpty(userId)){
            hql+=" AND userId='"+userId+"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<ClickUsersEntity> totalList=query.list();
        if(totalList.size()>0){
            return totalList.get(0);
        }
        return null;
    }

    /**
     * 获取模块点击人数量
     * param:创建日期
     * param:菜单:模块id
     * return
     */
    @Override
    public Integer getTotalNum(ClickUsersEntity clickUser,String id) {
        String hql = "from ClickUsersEntity where 1=1";
        if(!StringUtil.isEmpty(id)){
            hql+=" and foreignId = '"+id+"'";

        }
        if(!StringUtil.isEmpty(clickUser.getStartTime())){
            hql+=" and createDate >= '"+clickUser.getStartTime()+" 00:00:00"+"'";
        }
        if(!StringUtil.isEmpty(clickUser.getEndTime())){
            hql+=" and createDate <= '"+clickUser.getEndTime()+" 23:59:59"+"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        List<ClickUsersEntity> totalList=query.list();
        Integer count=totalList.size();
        return count;
    }

    /**
     * 获取模块点击数量
     * param:创建日期
     * return
     */
    @Override
    public Long getClickTotal(ClickUsersEntity clickUser,String id) {
        String hql = "select SUM(clicks) from ClickUsersEntity where 1=1";
        if(!StringUtil.isEmpty(id)){
            hql+=" and foreignId = '"+id+"'";

        }
        if(!StringUtil.isEmpty(clickUser.getStartTime())){
            hql+=" and createDate >= '"+clickUser.getStartTime()+" 00:00:00"+"'";
        }
        if(!StringUtil.isEmpty(clickUser.getEndTime())){
            hql+=" and createDate <= '"+clickUser.getEndTime()+" 23:59:59"+"'";
        }
        Query query = getCurrentSession().createQuery(hql);
        Long clicks=(Long)query.uniqueResult();
        return clicks;
    }

    /**
     * 获取点击人统计列表
     * param clickUser
     * return
     */
    @Override
    public ClickUsersEntity getTotalList(ClickUsersEntity clickUser,String id) {
        String hql = "from ClickUsersEntity where 1=1";
        //统计单号
        if(!StringUtil.isEmpty(id)){
            hql+=" and foreignId = '"+id+"'";

        }
        //如果不为空，则加入检索条件：创建时间
        if(!StringUtil.isEmpty(clickUser.getStartTime())){
            hql+=" and createDate >= '" + clickUser.getStartTime()+" 00:00:00"+"'";
        }
        if(!StringUtil.isEmpty(clickUser.getEndTime())){
            hql+=" and createDate <= '"+clickUser.getEndTime()+" 23:59:59"+"'";
        }
        hql+=" ORDER BY createDate DESC";
        Query query = getCurrentSession().createQuery(hql);
        List<ClickUsersEntity> clickList=query.list();
        if(clickList.size()>0){
            return clickList.get(0);
        }
        return null;
    }

    /**
     * 创建统计信息
     * param:clickUserEntity
     */
    @Override
    public void create(ClickUsersEntity clickUserEntity) {
        Session session = getCurrentSession();
        session.save(clickUserEntity);
        session.flush();
    }

    /**
     * 修改统计信息
     * param:clickUserEntity
     */
    @Override
    public void update(ClickUsersEntity clickUserEntity) {
        Session session = getCurrentSession();
        session.update(clickUserEntity);
        session.flush();
        session.close();
    }
}
