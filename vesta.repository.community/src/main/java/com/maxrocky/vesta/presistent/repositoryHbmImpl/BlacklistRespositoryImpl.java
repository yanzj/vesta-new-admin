package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.BlacklistEntity;
import com.maxrocky.vesta.domain.repository.BlacklistRespository;
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
 * 黑名单功能模块数据持久层实现类
 * Created by WeiYangDong on 2017/11/21.
 */
@Repository
public class BlacklistRespositoryImpl implements BlacklistRespository{

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){ return sessionFactory.openSession(); }

    /**
     * 保存或更新Entity
     * @param entity
     */
    @Override
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
    @Override
    public <E> void delete(E entity) {
        Session session = this.getCurrentSession();
        session.delete(entity);
        session.flush();
        session.close();
    }

    /**
     * 获取黑名单列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<BlacklistEntity>
     */
    @Override
    public List<BlacklistEntity> getBlacklistList(Map<String,Object> paramsMap, WebPage webPage){
        StringBuilder hql = new StringBuilder("FROM BlacklistEntity bl WHERE 1=1");
        List<Object> paramsList = new ArrayList<>();
        //城市ID
        Object cityId = paramsMap.get("cityId");
        if (null != cityId && !"".equals(cityId) && !"0".equals(cityId)){
            hql.append(" AND bl.cityId = ? ");
            paramsList.add(cityId);
        }
        //项目Code
        Object projectCode = paramsMap.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            hql.append(" AND bl.projectCode = ? ");
            paramsList.add(projectCode);
        }
        //名单名称
        Object listName = paramsMap.get("listName");
        if (null != listName && !"".equals(listName)){
            hql.append(" AND bl.listName like ? ");
            paramsList.add("%"+listName+"%");
        }
        hql.append("ORDER BY bl.createOn DESC");
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
     * 通过主键ID获取黑名单信息
     * @param id 主键ID
     * @return BlacklistEntity
     */
    @Override
    public BlacklistEntity getBlacklistById(String id){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM BlacklistEntity WHERE id = ?").setParameter(0, id);
        BlacklistEntity blacklistEntity = (BlacklistEntity) query.uniqueResult();
        session.flush();
        session.close();
        return blacklistEntity;
    }

    /**
     * 检验黑名单名称唯一性
     * @param id 黑名单ID
     * @param name 黑名单名称
     * @return List<BlacklistEntity>
     */
    @Override
    public List<BlacklistEntity> checkBlacklistName(String id,String name){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM BlacklistEntity WHERE listName = ? AND id != ?").setParameter(0, name).setParameter(1,id);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }
}
