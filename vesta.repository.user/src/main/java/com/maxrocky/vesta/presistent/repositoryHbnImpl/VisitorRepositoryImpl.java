package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.VisitorPassEntity;
import com.maxrocky.vesta.domain.repository.VisitorRepository;
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
 * 访客模块Repository实现类
 * Created by WeiYangDong on 2017/12/18.
 */
@Repository
public class VisitorRepositoryImpl implements VisitorRepository {

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession(){ return sessionFactory.openSession(); }

    /**
     * 保存或更新Entity
     * @param entity 实体类
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
     * @param entity 实体类
     */
    @Override
    public <E> void delete(E entity) {
        Session session = this.getCurrentSession();
        session.delete(entity);
        session.flush();
        session.close();
    }

    /**
     * 获取访客通行列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<VisitorPassEntity>
     */
    @Override
    public List<VisitorPassEntity> getVisitorPassList(Map<String,Object> paramsMap, WebPage webPage){
        //拼接HQL-->>
        StringBuilder hql = new StringBuilder("FROM VisitorPassEntity vp WHERE 1=1");
        List<Object> paramsList = new ArrayList<>();
        //项目编码
        Object projectCode = paramsMap.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            hql.append(" AND vp.projectCode = ? ");
            paramsList.add(projectCode);
        }
        //房产地址
        Object houseAddress = paramsMap.get("houseAddress");
        if (null != houseAddress && !"".equals(houseAddress)){
            hql.append(" AND vp.houseAddress = ? ");
            paramsList.add(houseAddress);
        }
        //访客姓名
        Object visitorName = paramsMap.get("visitorName");
        if (null != visitorName && !"".equals(visitorName)){
            hql.append(" AND vp.visitorName = ? ");
            paramsList.add(visitorName);
        }
        //被访人
        Object ownerName = paramsMap.get("ownerName");
        if (null != ownerName && !"".equals(ownerName)){
            hql.append(" AND vp.ownerName = ? ");
            paramsList.add(ownerName);
        }
        //查询开始日期
        Object createOnStr = paramsMap.get("createOnStr");
        if (null != createOnStr && !"".equals(createOnStr)){
            hql.append(" AND date_format(visitDate,'%Y-%m-%d') >= "+"'"+createOnStr+"'");
        }
        //查询结束日期
        Object createOnEnd = paramsMap.get("createOnEnd");
        if (null != createOnEnd && !"".equals(createOnEnd)){
            hql.append(" AND date_format(visitDate,'%Y-%m-%d') <= "+"'"+createOnEnd+"'");
        }
        hql.append(" ORDER BY vp.createOn DESC ");
        //执行HQL-->>
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
     * 通过主键ID获取访客通行信息
     * @param id 主键ID
     * @return VisitorPassEntity
     */
    @Override
    public VisitorPassEntity getVisitorPassById(String id){
        Session session = getCurrentSession();
        Query query = session.createQuery("FROM VisitorPassEntity vp WHERE vp.id = ?").setParameter(0, id);
        VisitorPassEntity visitorPassEntity = (VisitorPassEntity) query.uniqueResult();
        session.flush();
        session.close();
        return visitorPassEntity;
    }

    /**
     * 获取访客通行日志列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<Object[]>
     */
    @Override
    public List<Object[]> getVisitorPassLogList(Map<String,Object> paramsMap, WebPage webPage){
        //拼接HQL-->>
        StringBuilder hql = new StringBuilder("FROM VisitorPassLogEntity vpl,VisitorPassEntity vp WHERE vp.id = vpl.visitorPassId");
        List<Object> paramsList = new ArrayList<>();
        //项目编码
        Object projectCode = paramsMap.get("projectCode");
        if (null != projectCode && !"".equals(projectCode) && !"0".equals(projectCode)){
            hql.append(" AND vp.projectCode = ? ");
            paramsList.add(projectCode);
        }
        //房产地址
        Object houseAddress = paramsMap.get("houseAddress");
        if (null != houseAddress && !"".equals(houseAddress)){
            hql.append(" AND vp.houseAddress = ? ");
            paramsList.add(houseAddress);
        }
        //访客姓名
        Object visitorName = paramsMap.get("visitorName");
        if (null != visitorName && !"".equals(visitorName)){
            hql.append(" AND vp.visitorName = ? ");
            paramsList.add(visitorName);
        }
        //被访人
        Object ownerName = paramsMap.get("ownerName");
        if (null != ownerName && !"".equals(ownerName)){
            hql.append(" AND vp.ownerName = ? ");
            paramsList.add(ownerName);
        }
        //查询开始日期(放行时间)
        Object createOnStr = paramsMap.get("createOnStr");
        if (null != createOnStr && !"".equals(createOnStr)){
            hql.append(" AND date_format(vpl.passDate,'%Y-%m-%d') >= "+"'"+createOnStr+"'");
        }
        //查询结束日期(放行时间)
        Object createOnEnd = paramsMap.get("createOnEnd");
        if (null != createOnEnd && !"".equals(createOnEnd)){
            hql.append(" AND date_format(vpl.passDate,'%Y-%m-%d') <= "+"'"+createOnEnd+"'");
        }
        hql.append(" ORDER BY vp.createOn DESC ");
        //执行HQL-->>
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
