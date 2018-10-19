package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.ShopSellerEntity;
import com.maxrocky.vesta.domain.repository.ShopSellerRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import com.maxrocky.vesta.utility.Page;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2016/1/15.
 */

@Repository
public class ShopSellerRepositoryImpl extends HibernateDaoImpl implements ShopSellerRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    @Override
    public void AddShopSeller(ShopSellerEntity shopSellerEntity) {
        Session session = getCurrentSession();
        session.save(shopSellerEntity);
        session.flush();
    }

    @Override
    public boolean UpdateShopSeller(ShopSellerEntity shopSellerEntity) {
        Session session = getCurrentSession();
        session.update(shopSellerEntity);
        session.flush();
        return true;
    }

    @Override
    public void DelSellers(String typeId) {
        String hql = "update ShopSellerEntity set sellerStatus ="+ShopSellerEntity.STATUS_INVALID+"where sellerType =:typeId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("typeId",typeId);
        query.executeUpdate();
    }

    @Override
    public List<Object[]> getShopSellerList(String projectId,String categoryId,String level,String range,String range2,Page page) {
        String hql ="SELECT sellerId,sellerLogo,sellerTel,sellerName,sellerAddress," +
                "(SELECT COUNT(*) FROM SellerEvaluateEntity AS b WHERE a.sellerId = b.sellerId AND b.evaluateCircs = '1') AS good," +
                "(SELECT COUNT(*) FROM SellerEvaluateEntity AS b WHERE a.sellerId = b.sellerId AND b.evaluateCircs = '2') AS bad " +
                "FROM ShopSellerEntity as a where sellerStatus = '"+ShopSellerEntity.STATUS_VALID+"'and projectId= '"+projectId+"'";
        if(!categoryId.equals("-1")){
            hql += " and sellerType= '"+categoryId+"'";
        }
        if(!range.equals("-1")){
            hql +="and range>='"+range+"'";
        }
        if(!range2.equals("-1")){
            hql+="and range<='"+range2+"'";
        }
        if(level.equals("1")){
            hql += "order by good desc,sellerSort";
        }else if(level.equals("2")){
            hql +=" order by bad desc,sellerSort";
        }else{
            hql +="order by sellerSort";
        }
        Query query = getCurrentSession().createQuery(hql);
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<Object[]> shopSellerEntities = query.list();
        return shopSellerEntities;
    }

    @Override
    public ShopSellerEntity getShopSellerDetail(String sellerId) {
        String hql = "FROM ShopSellerEntity WHERE sellerId = :sellerId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("sellerId", sellerId);
        ShopSellerEntity shopSellerEntity = (ShopSellerEntity) query.uniqueResult();
        return shopSellerEntity;
    }

    @Override
    public List<ShopSellerEntity> getShopSellers(ShopSellerEntity shopSellerEntity,WebPage webPage) {
        List<Object> params = new ArrayList<Object>();
        String hql = "from ShopSellerEntity where 1=1";
        if(!StringUtils.isEmpty(shopSellerEntity.getSellerName())){
            hql += "and sellerName like '%"+shopSellerEntity.getSellerName()+"%'";
        }
        if(!StringUtils.isEmpty(shopSellerEntity.getSellerDirector())){
            hql +="and sellerDirector like '%"+shopSellerEntity.getSellerDirector()+"%'";
        }
        if(!StringUtils.isEmpty(shopSellerEntity.getSellerDirectorTel())){
            hql +="and sellerDirectorTel = '"+shopSellerEntity.getSellerDirectorTel()+"'";
        }
        hql+="and sellerStatus ="+ShopSellerEntity.STATUS_VALID+" and projectId ='"+shopSellerEntity.getProjectId()+"' order by sellerSort asc,sellerCreate desc";
        Query query = getCurrentSession().createQuery(hql);
        List<ShopSellerEntity> shopSellerEntities = query.list();
        if(webPage !=null){
            return this.findByPage(hql, webPage,params);
        }
        return shopSellerEntities;
    }

    @Override
    public ShopSellerEntity getGreaterShopSeller(Integer sellerSort) {
        String hql = "from ShopSellerEntity where sellerSort>:sellerSort order by sellerSort asc";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("sellerSort",sellerSort);
        query.setFirstResult(0);
        query.setMaxResults(1);
        ShopSellerEntity shopSellerEntity = (ShopSellerEntity) query.uniqueResult();
        return shopSellerEntity;
    }

    @Override
    public ShopSellerEntity getLessShopSeller(Integer sellerSort) {
        String hql = "from ShopSellerEntity where sellerSort<:sellerSort order by sellerSort desc";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("sellerSort",sellerSort);
        query.setFirstResult(0);
        query.setMaxResults(1);
        ShopSellerEntity shopSellerEntity = (ShopSellerEntity) query.uniqueResult();
        return shopSellerEntity;
    }

    @Override
    public ShopSellerEntity getMaxShopseller() {
        String hql = "from ShopSellerEntity where sellerSort=(select max(sellerSort) from ShopSellerEntity)";
        Query query = getCurrentSession().createQuery(hql);
        ShopSellerEntity shopSellerEntity = (ShopSellerEntity) query.uniqueResult();
        return shopSellerEntity;
    }

    @Override
    public void topShopSeller() {
        String sql = "update ShopSellerEntity set sellerSort = sellerSort+1";
        Query query = getCurrentSession().createQuery(sql);
        query.executeUpdate();
    }
}
