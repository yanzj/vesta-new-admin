package com.maxrocky.vesta.presistent.repositoryHbnImpl;

import com.maxrocky.vesta.domain.model.SystemNotificationEntity;
import com.maxrocky.vesta.domain.repository.SystemNotificationRepository;
import com.maxrocky.vesta.hibernate.HibernateDaoImpl;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.Page;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2016/5/21.
 */
@Repository
public class SystemNotificationRepoitoryImpl extends HibernateDaoImpl implements SystemNotificationRepository {

    @Resource(name="sessionFactory")
    protected SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<SystemNotificationEntity> querySystemNotificationEntityList(SystemNotificationEntity systemNotificationEntity, WebPage webPage) {

        StringBuffer hql =new StringBuffer("FROM SystemNotificationEntity AS sn where 1=1 and status ='1' ");
        List<SystemNotificationEntity> propertyAnnouncementList = new ArrayList<>();
        List<Object> params = new ArrayList<Object>();


        //不为空则拼接搜索条件 系统通知标题
        if(null != systemNotificationEntity.getNotificationTitle() && !"".equals(systemNotificationEntity.getNotificationTitle())){
            hql.append(" and sn.notificationTitle like ?");
            params.add("%"+ systemNotificationEntity.getNotificationTitle() +"%");
        }
        // 不为空则拼接搜索条件 发布开始时间
        if(null != systemNotificationEntity.getNotificationCreateBeginTime() && !"".equals(systemNotificationEntity.getNotificationCreateBeginTime())){
            hql.append(" and sn.notificationCreateTime >= ?");
            params.add(DateUtils.parse(systemNotificationEntity.getNotificationCreateBeginTime() + " 00:00:00"));
        }
        // 不为空则拼接搜索条件 发布结束时间
        if(null != systemNotificationEntity.getNotificationCreateEndTime() && !"".equals(systemNotificationEntity.getNotificationCreateEndTime() )){
            hql.append(" and sn.notificationCreateTime <= ?");
            params.add(DateUtils.parse(systemNotificationEntity.getNotificationCreateEndTime() + " 23:59:59"));
        }
        // 不为空则拼接搜索条件 系统通知内容
        if(null != systemNotificationEntity.getNotificationContent() && !"".equals(systemNotificationEntity.getNotificationContent())){
            hql.append(" and sn.notificationContent like ?");
            params.add("%"+ systemNotificationEntity.getNotificationContent() +"%");
        }

        // 不为空则拼接搜索条件 发布人
        if(null != systemNotificationEntity.getNotificationCreater() && !"".equals(systemNotificationEntity.getNotificationCreater())){
            hql.append(" and sn.notificationCreater like ?");
            params.add("%"+ systemNotificationEntity.getNotificationCreater() +"%");
        }

        // 不为空则拼接搜索条件 系统通知发布状态
        if(null != systemNotificationEntity.getNotificationStatus() && !"".equals(systemNotificationEntity.getNotificationStatus()) && !"0".equals(systemNotificationEntity.getNotificationStatus())){
            hql.append(" and sn.notificationStatus =?");
            params.add( systemNotificationEntity.getNotificationStatus());
        }

        // 不为空则拼接搜索条件 系统通知类型
        if(null != systemNotificationEntity.getNotificationType() && !"".equals(systemNotificationEntity.getNotificationType())&& !"0".equals(systemNotificationEntity.getNotificationType())){
            hql.append(" and sn.notificationType =?");
            params.add( systemNotificationEntity.getNotificationType());
        }

        // 不为空则拼接搜索条件 系统通知置顶状态
        if(null != systemNotificationEntity.getNotificationTopStatus() && !"".equals(systemNotificationEntity.getNotificationTopStatus())&& !"0".equals(systemNotificationEntity.getNotificationTopStatus())){
            hql.append(" and sn.notificationTopStatus =?");
            params.add(systemNotificationEntity.getNotificationTopStatus());
        }

        hql.append(" ORDER BY sn.notificationTopStatus DESC, sn.notificationCreateTime DESC");

        if(webPage != null){
            return this.findByPage(hql.toString(), webPage, params);
        }

        propertyAnnouncementList =  ( List<SystemNotificationEntity>)getHibernateTemplate().find(hql.toString());
        return propertyAnnouncementList;
    }

    @Override
    public List<SystemNotificationEntity> systemNotificationEntityList(SystemNotificationEntity systemNotificationEntity, Page page) {
        String hql ="FROM SystemNotificationEntity AS sn order by sn.notificationTopStatus, sn.notificationCreateTime DESC";
        Query query = getCurrentSession().createQuery(hql);
        //分页
        query.setFirstResult(page.getFirstResult());
        query.setMaxResults(page.getMaxResult());
        List<SystemNotificationEntity> systemNotificationLists = query.list();
        return systemNotificationLists;
    }

    @Override
    public boolean saveSystemNotification(SystemNotificationEntity systemNotificationEntity) {
        boolean flg = false;
        if(systemNotificationEntity != null ){
            Session session = getCurrentSession();
            session.save(systemNotificationEntity);
            session.flush();
            flg = true;
        }
        return flg;
    }

    @Override
    public boolean deleteSystemNotification(SystemNotificationEntity systemNotificationEntity) {

        boolean flg = false;
        if(systemNotificationEntity != null ){
            Session session = getCurrentSession();
            session.delete(systemNotificationEntity);
            session.flush();
            flg = true;
        }
        return flg;

    }

    @Override
    public boolean updateSystemNotification(SystemNotificationEntity systemNotificationEntity) {
        boolean flg = false;
        if(systemNotificationEntity != null ){
            Session session = getCurrentSession();
            session.update(systemNotificationEntity);
            session.flush();
            session.close();
            flg = true;
        }
        return flg;
    }

    @Override
    public SystemNotificationEntity getSystemNotificationEntity(String notificationId) {
        return (SystemNotificationEntity)getCurrentSession().get(SystemNotificationEntity.class,notificationId);
    }
}
