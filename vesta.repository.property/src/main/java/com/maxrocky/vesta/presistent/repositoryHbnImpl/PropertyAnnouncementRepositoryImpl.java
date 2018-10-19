package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.PropertyAnnouncementEntity;
import com.maxrocky.vesta.domain.repository.PropertyAnnouncementRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.Page;
import com.maxrocky.vesta.utility.SqlJoiningTogether;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JillChen on 2016/1/23.
 * 物业公告 持久层实现类
 */

@Repository
public class PropertyAnnouncementRepositoryImpl extends HibernateDaoImpl implements PropertyAnnouncementRepository {
    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    /**
     * 物业公告
     * code:FQ0006
     * @return
     */

    @Override
    public List<PropertyAnnouncementEntity> propertyAnnouncementList(String projectId,Page page) {

        String hql ="FROM PropertyAnnouncementEntity AS pa where pa.projectId =:projectId order by pa.createTime DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        query.setParameter("projectId", projectId);
        List<PropertyAnnouncementEntity> propertyAnnouncementEntities = query.list();
        return propertyAnnouncementEntities;

    }

    /**
     * 获取最新的公告id和title
     * @return
     */
    @Override
    public List<PropertyAnnouncementEntity> propertyAnnouncementList(String projectId) {
        String hql ="FROM PropertyAnnouncementEntity AS pa where pa.projectId =:projectId order by pa.createTime DESC";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("projectId", projectId);
        List<PropertyAnnouncementEntity> propertyAnnouncementEntities = query.list();
        return propertyAnnouncementEntities;

    }

    /**
     * 获取物业公告详细信息
     * @param announcementId
     */

    public  PropertyAnnouncementEntity propertyAnnouncementDetail(String announcementId){
       /* String hql = "from PropertyAnnouncementEntity where announcementId='"+announcementId+"'";
        Query query = getCurrentSession().createQuery(hql);
        List<PropertyAnnouncementEntity> propertyAnnouncement=query.list();
        if(propertyAnnouncement.size()>0){
            return propertyAnnouncement.get(0);
        }
        return null;*/

        return (PropertyAnnouncementEntity)getCurrentSession().get(PropertyAnnouncementEntity.class, announcementId);


    }
    /************************************************以下为后端********************************************************/

    /**
     * 物业公告列表
     * @param propertyAnnouncement
     * @return
     */
    @Override
    public List<PropertyAnnouncementEntity> queryPropertyAnnouncement(PropertyAnnouncementEntity propertyAnnouncement,WebPage webPage) {
        StringBuffer hql = new StringBuffer("FROM PropertyAnnouncementEntity as pa WHERE 1=1");
        List<PropertyAnnouncementEntity> propertyAnnouncementList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();

        // 初始化 默认条件 查询范围
        hql.append(SqlJoiningTogether.sqlStatement("pa.projectId",propertyAnnouncement.getMemo()));

        //不为空则拼接搜索条件 标题、
        if(null != propertyAnnouncement.getTitle() && !"".equals(propertyAnnouncement.getTitle())){
            hql.append(" and pa.title like ?");
            params.add("%"+ propertyAnnouncement.getTitle() +"%");
        }
        // 不为空则拼接搜索条件 发布开始时间
        if(null != propertyAnnouncement.getAnnouncementSummary() && !"".equals(propertyAnnouncement.getAnnouncementSummary())){
            hql.append(" and pa.announcementTime >= ?");
            params.add(DateUtils.parse(propertyAnnouncement.getAnnouncementSummary() + " 00:00:00"));
        }
        // 不为空则拼接搜索条件 发布结束时间
        if(null != propertyAnnouncement.getAnnouncementContent() && !"".equals(propertyAnnouncement.getAnnouncementContent())){
            hql.append(" and pa.announcementTime <= ?");
            params.add(DateUtils.parse(propertyAnnouncement.getAnnouncementContent() + " 23:59:59"));
        }
        // 不为空则拼接搜索条件 项目
        if(null != propertyAnnouncement.getProjectId() && !"".equals(propertyAnnouncement.getProjectId())){
            hql.append(" and pa.projectId like ?");
            params.add("%"+ propertyAnnouncement.getProjectId() +"%");
        }
        hql.append(" ORDER BY pa.announcementTime DESC");

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }

        propertyAnnouncementList =  ( List<PropertyAnnouncementEntity>)getHibernateTemplate().find(hql.toString());
        return propertyAnnouncementList;
    }

    /**
     * 删除物业公告信息
     * @param propertyAnnouncement
     * @return
     */
    @Override
    public boolean delPropertyAnnouncement(PropertyAnnouncementEntity propertyAnnouncement) {
        if(null != propertyAnnouncement){
            Session session = getCurrentSession();
            session.delete(propertyAnnouncement);
            session.flush();
            return true;
        }
        return false;
    }

    /**
     * 更新物业公告信息
     * @param propertyAnnouncementEntity
     * @return
     */
    @Override
    public void changePropertyAnnouncement(PropertyAnnouncementEntity propertyAnnouncementEntity) {
        Session session = getCurrentSession();
        session.update(propertyAnnouncementEntity);
        session.flush();
    }

    /**
     * 添加物业公告信息
     * @param propertyAnnouncement
     * @return
     */
    @Override
    public boolean savePropertyAnnouncement(PropertyAnnouncementEntity propertyAnnouncement) {
        if(null != propertyAnnouncement){
            Session session = getCurrentSession();
            session.save(propertyAnnouncement);
            session.flush();
            return true;
        }
        return false;
    }



}