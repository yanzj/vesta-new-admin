package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.BusinessBulletinEntity;
import com.maxrocky.vesta.domain.repository.BusinessBulletinRepository;
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
 * 商业公告数据持久层实现类
 * Created by WeiYangDong on 2017/9/18.
 */
@Repository
public class BusinessBulletinRepositoryImpl implements BusinessBulletinRepository{

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){ return sessionFactory.openSession(); }

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
     * 通过公告ID获取商业公告详情
     * @param id 公告ID
     * @return BusinessBulletinEntity
     */
    public BusinessBulletinEntity getBusinessBulletinById(String id){
        Session session = getCurrentSession();
        Query query = session.createQuery(" FROM BusinessBulletinEntity WHERE id = ? ").setParameter(0,id);
        BusinessBulletinEntity businessBulletinEntity = (BusinessBulletinEntity)query.uniqueResult();
        session.flush();
        session.close();
        return businessBulletinEntity;
    }

    /**
     * 获取商业公告列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<BusinessBulletinEntity>
     */
    public List<BusinessBulletinEntity> getBusinessBulletinList(Map<String,Object> paramsMap, WebPage webPage){
        StringBuilder hql = new StringBuilder();
        hql.append("FROM BusinessBulletinEntity bb WHERE 1=1");
        //数据权限
        Object roleScopes = paramsMap.get("roleScopes");
        if (null != roleScopes && !"".equals(roleScopes.toString()) && !roleScopes.toString().contains("all")){
            hql.append(" and bb.cityId = '0' or bb.projectCode in ("+roleScopes.toString().substring(0,roleScopes.toString().length()-1)+") ");
        }
        List<Object> paramsList = new ArrayList<>();
        //区域
        Object cityId = paramsMap.get("cityId");
        if (null != cityId && !"".equals(cityId.toString()) && !"0".equals(cityId.toString())){
            hql.append(" and bb.cityId = ? ");
            paramsList.add(cityId.toString());
        }
        //项目
        Object projectCode = paramsMap.get("projectCode");
        if (null != projectCode && !"".equals(projectCode.toString()) && !"0".equals(projectCode.toString())){
            hql.append(" and bb.projectCode = ? ");
            paramsList.add(projectCode.toString());
        }
        //公告标题
        Object title = paramsMap.get("title");
        if (null != title && !"".equals(title)){
            hql.append(" and bb.title like ? ");
            paramsList.add("%"+title.toString()+"%");
        }
        //状态
        Object releaseStatus = paramsMap.get("releaseStatus");
        if (null != releaseStatus && !"".equals(releaseStatus)){
            hql.append(" and bb.releaseStatus = ? ");
            paramsList.add(releaseStatus);
        }
        //发布日期-开始时间
        Object releaseStartDate = paramsMap.get("releaseStartDate");
        if (null != releaseStartDate && !"".equals(releaseStartDate)){
            hql.append(" and date_format(bb.releaseDate,'%Y-%m-%d') >= "+"'"+releaseStartDate+"'");
        }
        //发布日期-结束时间
        Object releaseEndDate = paramsMap.get("releaseEndDate");
        if (null != releaseEndDate && !"".equals(releaseEndDate)){
            hql.append(" and date_format(bb.releaseDate,'%Y-%m-%d') <= "+"'"+releaseEndDate+"'");
        }
        hql.append(" order by bb.createOn desc ");
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
}
