package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.ElectronicMagazineEntity;
import com.maxrocky.vesta.domain.repository.ElectronicMagazineRepository;
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
 * 电子杂志数据持久层实现类
 * Created by WeiYangDong on 2017/9/25.
 */
@Repository
public class ElectronicMagazineRepositoryImpl implements ElectronicMagazineRepository{

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
     * 通过杂志ID获取电子杂志详情
     * @param id 杂志ID
     * @return ElectronicMagazineEntity
     */
    public ElectronicMagazineEntity getElectronicMagazineById(String id){
        Session session = getCurrentSession();
        Query query = session.createQuery(" FROM ElectronicMagazineEntity WHERE id = ? ").setParameter(0,id);
        ElectronicMagazineEntity electronicMagazineEntity = (ElectronicMagazineEntity)query.uniqueResult();
        session.flush();
        session.close();
        return electronicMagazineEntity;
    }

    /**
     * 获取电子杂志列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<ElectronicMagazineEntity>
     */
    public List<ElectronicMagazineEntity> getElectronicMagazineList(Map<String,Object> paramsMap, WebPage webPage){
        StringBuilder hql = new StringBuilder();
        hql.append("FROM ElectronicMagazineEntity em WHERE 1=1");
        List<Object> paramsList = new ArrayList<>();
        //杂志名称
        Object magazineName = paramsMap.get("magazineName");
        if (null != magazineName && !"".equals(magazineName)){
            hql.append(" and em.magazineName like ? ");
            paramsList.add("%"+magazineName.toString()+"%");
        }
        //状态
        Object releaseStatus = paramsMap.get("releaseStatus");
        if (null != releaseStatus && !"".equals(releaseStatus)){
            hql.append(" and em.releaseStatus = ? ");
            paramsList.add(releaseStatus);
        }
        //发布日期-开始时间
        Object releaseStartDate = paramsMap.get("releaseStartDate");
        if (null != releaseStartDate && !"".equals(releaseStartDate)){
            hql.append(" and date_format(em.releaseDate,'%Y-%m-%d') >= "+"'"+releaseStartDate+"'");
        }
        //发布日期-结束时间
        Object releaseEndDate = paramsMap.get("releaseEndDate");
        if (null != releaseEndDate && !"".equals(releaseEndDate)){
            hql.append(" and date_format(em.releaseDate,'%Y-%m-%d') <= "+"'"+releaseEndDate+"'");
        }
        hql.append(" order by em.createOn desc ");
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
