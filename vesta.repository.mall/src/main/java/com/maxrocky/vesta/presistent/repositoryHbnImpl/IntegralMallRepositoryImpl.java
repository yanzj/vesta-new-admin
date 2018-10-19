package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.MallProductEntity;
import com.maxrocky.vesta.domain.model.MallProductTypeEntity;
import com.maxrocky.vesta.domain.repository.IntegralMallRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 积分商城 DAO实现类
 * Created by WeiYangDong on 2017/7/10.
 */
@Repository
public class IntegralMallRepositoryImpl implements IntegralMallRepository{

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 保存或更新Entity
     * @param entity
     */
    public <T> void saveOrUpdate(T entity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(entity);
        session.flush();
        session.close();
    }

    /**
     * 删除Entity
     * @param entity
     */
    public <E> void delete(E entity){
        Session session = this.getCurrentSession();
        session.delete(entity);
        session.flush();
        session.close();
    }

    /**
     * 获取商品类目列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<MallProductTypeEntity>
     */
    public List<MallProductTypeEntity> getProductTypeList(Map<String,Object> paramsMap, WebPage webPage){
        StringBuilder hql = new StringBuilder("FROM MallProductTypeEntity pt WHERE 1=1");
        List<Object> paramsList = new ArrayList<>();
        //商品类目名称
        Object productTypeName = paramsMap.get("productTypeName");
        if (null != productTypeName && !"".equals(productTypeName)){
            hql.append(" AND pt.productTypeName = ? ");
            paramsList.add(productTypeName);
        }
        hql.append(" order by pt.createOn ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        for (int i = 0,length = paramsList.size(); i < length; i++){
            query.setParameter(i,paramsList.get(i));
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 获取商品类目信息
     * @param productTypeId 商品类目ID
     * @return MallProductTypeEntity
     */
    public MallProductTypeEntity getProductTypeById(String productTypeId){
        StringBuilder hql = new StringBuilder("FROM MallProductTypeEntity pt WHERE pt.productTypeId = ?");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString()).setParameter(0, productTypeId);
        MallProductTypeEntity productTypeEntity = (MallProductTypeEntity) query.uniqueResult();
        session.flush();
        session.close();
        return productTypeEntity;
    }

    /**
     * 获取商品列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<MallProductEntity>
     */
    public List<Object> getProductList(Map<String,Object> paramsMap, WebPage webPage){
        StringBuilder hql = new StringBuilder();
        hql.append(" FROM MallProductEntity p,MallProductTypeEntity pt WHERE p.productTypeId = pt.productTypeId ");
        List<Object> paramsList = new ArrayList<>();
        //商品所属客户端
        Object clientId = paramsMap.get("clientId");
        if (null != clientId){
            hql.append(" AND p.clientId = ? ");
            paramsList.add(clientId);
        }
        //商品名称
        Object productName = paramsMap.get("productName");
        if (null != productName && !"".equals(productName)){
            hql.append(" AND p.productName like ? ");
            paramsList.add("%" + productName + "%");
        }
        //商品类目名称
        Object productTypeName = paramsMap.get("productTypeName");
        if (null != productTypeName && !"".equals(productTypeName)){
            hql.append(" AND pt.productTypeName like ? ");
            paramsList.add("%" + productTypeName + "%");
        }
        //商品状态
        Object productState = paramsMap.get("productState");
        if (null != productState){
            hql.append(" AND p.productState = ? ");
            paramsList.add(productState);
        }
        hql.append(" order by p.createOn ");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        for (int i = 0,length = paramsList.size(); i < length; i++){
            query.setParameter(i,paramsList.get(i));
        }
        if (null != webPage){
            query.setFirstResult((webPage.getPageIndex() - 1) * webPage.getPageSize());
            query.setMaxResults(webPage.getPageSize());
        }
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 获取商品信息
     * @param productId 商品类目ID
     * @return MallProductEntity
     */
    public MallProductEntity getProductById(String productId){
        StringBuilder hql = new StringBuilder("FROM MallProductEntity p WHERE p.productId = ?");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString()).setParameter(0, productId);
        MallProductEntity productEntity = (MallProductEntity) query.uniqueResult();
        session.flush();
        session.close();
        return productEntity;
    }
}
