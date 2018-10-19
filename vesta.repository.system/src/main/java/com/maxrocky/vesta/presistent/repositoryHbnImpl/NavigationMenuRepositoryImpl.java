package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.NavigationMenuEntity;
import com.maxrocky.vesta.domain.repository.NavigationMenuRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luxinxin on 2016/8/31.
 */
@Repository
public class NavigationMenuRepositoryImpl extends HibernateDaoImpl implements NavigationMenuRepository {
    @Resource
    private SessionFactory sessionFactory;
    private Session getCurrentSession(){
        return sessionFactory.openSession();
    }
    @Override
    public boolean saveNavigationMenu(NavigationMenuEntity navigationMenuEntity) {
        Session session = getCurrentSession();
        session.save(navigationMenuEntity);
        session.flush();
        session.close();
        return true;
    }

    @Override
    public boolean delNavigationMenu(String navigationMenuId) {
        NavigationMenuEntity navigationMenuEntity=new NavigationMenuEntity();
        navigationMenuEntity.setNavigationId(navigationMenuId);
        Session session = getCurrentSession();
        session.delete(navigationMenuEntity);
        session.flush();
        session.close();
        return false;
    }

    @Override
    public boolean updateNavigationMenu(NavigationMenuEntity navigationMenuEntity) {
        NavigationMenuEntity navigationMenuEntity1 = getNavigationMenu(navigationMenuEntity.getNavigationId());
        if (navigationMenuEntity1 !=null){
            Session session = getCurrentSession();
            session.update(navigationMenuEntity);
            session.flush();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public NavigationMenuEntity getNavigationMenu(String navigationId) {
        String hql = "from NavigationMenuEntity as a where a.navigationId=:navigationId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("navigationId",navigationId);
        if (query.list().size()>0){
            return (NavigationMenuEntity)query.list().get(0);
        }
        return null;
    }
    @Override
    public List<NavigationMenuEntity> getAllnavigationMenus(NavigationMenuEntity navigationMenuEntity, WebPage webPage) {
        List<NavigationMenuEntity> navigationMenuEntitys = new ArrayList<NavigationMenuEntity>();
        List<Object> params = new ArrayList<>();
        StringBuffer hql = new StringBuffer("from NavigationMenuEntity");
        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }
        navigationMenuEntitys = (List<NavigationMenuEntity>)getHibernateTemplate().find(hql.toString());
        return navigationMenuEntitys;
    }

    @Override
    public List<NavigationMenuEntity> getAllnavigationMenus() {
        List<NavigationMenuEntity> navigationMenuEntitys = new ArrayList<NavigationMenuEntity>();
        List<Object> params = new ArrayList<>();
        StringBuffer hql = new StringBuffer("from NavigationMenuEntity");
        navigationMenuEntitys = (List<NavigationMenuEntity>)getHibernateTemplate().find(hql.toString());
        return navigationMenuEntitys;
    }
}
