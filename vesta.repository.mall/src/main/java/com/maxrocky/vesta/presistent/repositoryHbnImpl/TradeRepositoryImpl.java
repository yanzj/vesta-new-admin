package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.TradeEntity;
import com.maxrocky.vesta.domain.repository.TradeRepository;
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
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/16.
 */
@Repository
public class TradeRepositoryImpl implements TradeRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Object> getTradeAll(Map<String, Object> paramsMap, WebPage webPage) {
        StringBuilder hql = new StringBuilder();
        hql.append("SELECT t.tradeId,t.tradeNo,m.productName,t.number,t.createOn,t.name,t.phone,t.amount,t.tradeStatus,t.address,t.payment FROM TradeEntity t , UserInfoEntity u , MallProductEntity m ");
        hql.append(" WHERE  t.productId = m.productId and t.userId = u.userId ");
        List<Object> paramsList = new ArrayList<>();
        //订单号
        Object tradeno = paramsMap.get("tradeno");
        if (null != tradeno){
            hql.append(" AND t.tradeNo like ? ");
            paramsList.add("%"+tradeno+"%");
        }

        //商品名称
        Object productName = paramsMap.get("productName");
        if (null != productName){
            hql.append(" AND m.productName like ? ");
            paramsList.add("%"+productName+"%");
        }

        //订单状态
        Object tradeStatus = paramsMap.get("tradeStatus");
        if (null != tradeStatus){
            hql.append(" AND t.tradeStatus like ? ");
            paramsList.add("%"+tradeStatus+"%");
        }

        //姓名
        Object name = paramsMap.get("name");
        if (null != name){
            hql.append(" AND t.name like ? ");
            paramsList.add("%"+name+"%");
        }

        //手机号
        Object phone = paramsMap.get("phone");
        if (null != phone){
            hql.append(" AND t.phone like ? ");
            paramsList.add("%"+phone+"%");
        }
        //微信号
        Object weChatAppId = paramsMap.get("weChatAppId");
        if (null != weChatAppId){
            hql.append(" AND t.weChatAppId like ? ");
            paramsList.add("%"+weChatAppId+"%");
        }

        hql.append(" order by t.createOn desc ");
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

    @Override
    public List<Object> getTradeById(String id) {
        StringBuilder hql = new StringBuilder();
        hql.append("SELECT t.tradeId,t.tradeNo,m.productName,m.productBusiness,t.number,t.createOn,m.productIntegral,t.name,t.phone,t.amount,t.tradeStatus,t.address,t.message,u.mobile,u.realName FROM TradeEntity t , UserInfoEntity u , MallProductEntity m ");
        hql.append(" WHERE  t.productId = m.productId and t.userId = u.userId AND t.tradeId = ?");
        Session session = getCurrentSession();
        Query query = session.createQuery(hql.toString());
        query.setParameter(0,id);
        return query.list();
    }

    @Override
    public void updateTrade(TradeEntity t) {
        Session session = this.getCurrentSession();
        session.update(t);
        session.flush();
        session.close();
    }

    @Override
    public TradeEntity get(String id) {
        String hql = "from TradeEntity where tradeId=:tradeId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("tradeId",id);
        TradeEntity t = (TradeEntity) query.uniqueResult();
        return t;
    }
}
