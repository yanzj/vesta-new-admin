package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.HomePageCarouselEntity;
import com.maxrocky.vesta.domain.repository.HomePageCarouselRepository;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 首页轮播图_持久层实现类
 * 2016/6/24_Wyd
 */
@Repository
public class HomePageCarouselRepositoryImpl implements HomePageCarouselRepository{

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 保存
     */
    public void saveCarousel(HomePageCarouselEntity homePageCarouselEntity){
        Session session = getCurrentSession();
        session.save(homePageCarouselEntity);
        session.flush();
        session.close();
    }

    /**
     * 更新
     */
    public void updateCarousel(HomePageCarouselEntity homePageCarouselEntity){
        Session session = getCurrentSession();
        session.update(homePageCarouselEntity);
        session.flush();
        session.close();
    }

    /**
     * 通过_projectCode_删除轮播图信息
     * @param projectCode 项目Code
     */
    public void deleteCarouselByProjectCode(String projectCode){
        Session session = getCurrentSession();
        String sql = " delete from home_page_carousel where project_code = ? ";
        SQLQuery sqlQuery = session.createSQLQuery(sql);
        sqlQuery.setParameter(0,projectCode);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 通过_projectCode_检索轮播图列表
     * @param projectCode 项目Code
     * @return List<HomePageCarouselEntity>
     */
    public List<HomePageCarouselEntity> queryCarouselByProjectCode(String projectCode){
        Session session = getCurrentSession();
        String hql = " from HomePageCarouselEntity where projectCode = ? ";
        Query query = session.createQuery(hql).setParameter(0, projectCode);
        List<HomePageCarouselEntity> carouselList = query.list();
        session.flush();
        session.close();
        return carouselList;
    }


}
