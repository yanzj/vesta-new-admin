package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.IntegralRuleEntity;
import com.maxrocky.vesta.domain.model.IntegralRuleModelEntity;
import com.maxrocky.vesta.domain.repository.IntegralRuleRepository;
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
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/10.
 * 积分规则管理
 */
@Repository
public class IntegralRuleRepositoryImpl implements IntegralRuleRepository {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public  List<Object> getIntegralRuleList(Map<String, Object> paramsMap, WebPage webPage) {
        StringBuilder hql = new StringBuilder();
        hql.append("SELECT i.integralRuleId,i.integralNumber,m.modelName,c.clientName FROM IntegralRuleEntity i,IntegralRuleModelEntity m,ClientConfigEntity c where  i.integralRuleModelId = m.modelId  and i.clientConfigId = c.id ");
        List<Object> paramsList = new ArrayList<>();
        //公众号名称
        Object clientName = paramsMap.get("clientName");
        if (null != clientName){
            hql.append(" AND c.clientName like ? ");
            paramsList.add("%"+clientName+"%");
        }
        //模块名称
        Object modelName = paramsMap.get("modelName");
        if (null != modelName && !"".equals(modelName)){
            hql.append(" AND m.modelName like ? ");
            paramsList.add("%"+modelName+"%");
        }

        hql.append(" order by i.createOn ");
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
    public IntegralRuleEntity getIntegralRuleById(String integralRuleId) {
        String hql = "from IntegralRuleEntity where integralRuleId=:integralRuleId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("integralRuleId",integralRuleId);
        IntegralRuleEntity integralRuleEntity = (IntegralRuleEntity) query.uniqueResult();
        return integralRuleEntity;
    }

    @Override
    public void AddIntegralRuleEntity(IntegralRuleEntity itegralRuleEntity) {
        Session session = this.getCurrentSession();
        session.saveOrUpdate(itegralRuleEntity);
        session.flush();
        session.close();
    }

    @Override
    public void UpdateIntegralRuleEntity(IntegralRuleEntity itegralRuleEntity) {
        Session session = this.getCurrentSession();
        session.update(itegralRuleEntity);
        session.flush();
    }

    @Override
    public List<IntegralRuleEntity> CheckIntegralRule(IntegralRuleEntity itegralRuleEntity) {
        String hql = "from IntegralRuleEntity where clientConfigId=:clientConfigId and integralRuleModelId=:integralRuleModelId and integralRuleId !=:integralRuleId";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("clientConfigId",itegralRuleEntity.getClientConfigId());
        query.setParameter("integralRuleModelId",itegralRuleEntity.getIntegralRuleModelId());
        query.setParameter("integralRuleId",itegralRuleEntity.getIntegralRuleId());
        return query.list();
    }

    @Override
    public IntegralRuleEntity getIntegralRuleModelByTypeClien(int appid, String type) {
        String hql = "select i from IntegralRuleEntity i,IntegralRuleModelEntity m  where i.integralRuleModelId = m.modelId and i.clientConfigId=:clientConfigId and m.modelType=:modelType";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("clientConfigId",appid);
        query.setParameter("modelType",type);
        IntegralRuleEntity integralRuleEntity = (IntegralRuleEntity) query.uniqueResult();
        return integralRuleEntity;
    }
}
