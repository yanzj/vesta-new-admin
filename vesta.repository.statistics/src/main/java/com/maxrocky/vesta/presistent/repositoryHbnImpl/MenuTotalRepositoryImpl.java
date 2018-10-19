package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.MenuTotalEntity;
import com.maxrocky.vesta.domain.repository.MenuTotalRepository;
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
public class MenuTotalRepositoryImpl extends HibernateDaoImpl implements MenuTotalRepository {
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
    public MenuTotalEntity getTotalInfo(String date) {
        String hql="FROM MenuTotalEntity WHERE createDate LIKE '%"+date+"%'";
        Query query = getCurrentSession().createQuery(hql);
        List<MenuTotalEntity> totalList=query.list();
        if(totalList.size()>0){
            return totalList.get(0);
        }
        return null;
    }

    /**
     * 获取统计列表
     * param:menuToTalEntity
     * param:webPage
     * return
     */
    @Override
    public List<MenuTotalEntity> getTotalList() {
        String hql = "FROM MenuTotalEntity ORDER BY createDate ASC";
        Query query = getCurrentSession().createQuery(hql);
        List<MenuTotalEntity> totalList=query.list();
        return totalList;
    }

    /**
     * 创建统计信息
     * param:menuToTalEntity
     */
    @Override
    public void create(MenuTotalEntity menuToTalEntity) {
        Session session = getCurrentSession();
        session.save(menuToTalEntity);
        session.flush();
    }

    /**
     * 修改统计信息
     * param:menuToTalEntity
     */
    @Override
    public void update(MenuTotalEntity menuToTalEntity) {
        Session session = getCurrentSession();
        session.update(menuToTalEntity);
        session.flush();
        session.close();
    }
}
