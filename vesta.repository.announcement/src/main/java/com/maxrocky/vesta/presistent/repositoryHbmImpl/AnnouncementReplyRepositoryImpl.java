package com.maxrocky.vesta.presistent.repositoryHbmImpl;


import com.maxrocky.vesta.domain.model.AnnouncementReplyEntity;
import com.maxrocky.vesta.domain.repository.AnnouncementReplyRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyifan on 2016/5/09.
 */
@Repository
public class AnnouncementReplyRepositoryImpl extends BaseRepositoryImpl implements AnnouncementReplyRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }


    /**
     * 通过ID检索回复信息
     * @param id
     * @return
     */
    public AnnouncementReplyEntity queryReplayById(String id){
        Session session = getCurrentSession();
        AnnouncementReplyEntity announcementReplyEntity = (AnnouncementReplyEntity) session.get(AnnouncementReplyEntity.class, id);
        session.flush();
        session.close();
        return announcementReplyEntity;
    }

    /**
     * 更新回复
     */
    public void updateReply(AnnouncementReplyEntity announcementReplyEntity){
        Session session = getCurrentSession();
        session.update(announcementReplyEntity);
        session.flush();
        session.close();
    }

    /**
     * 查询所有数据
     *
     * @param webPage
     * @return
     */
    @Override
    public List<AnnouncementReplyEntity> queryAllByPage(String topicId, WebPage webPage) {
        StringBuffer hql = new StringBuffer("FROM AnnouncementReplyEntity as ad WHERE 1=1");
        List<AnnouncementReplyEntity> announcementEntityList = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        hql.append(" and ad.topicId = ? and ad.status != 3");
        params.add(topicId);
        hql.append(" ORDER BY ad.floor ASC");
        if (webPage != null) {
            return this.findByPage(hql.toString(), webPage, params);
        }

        announcementEntityList = (List<AnnouncementReplyEntity>) getHibernateTemplate().find(hql.toString(), params.toArray());
        return announcementEntityList;
    }

    @Override
    public Integer getMaxFloor(String id) {
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select max(floor) ");
        sql.append(" from announcement_reply ar ");
        sql.append(" group by ar.topic_id ");
        sql.append(" having ar.topic_id = ?");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0,id);
        Object o = sqlQuery.uniqueResult();
//        Object o = session.createSQLQuery("select max(floor) from announcement_reply").uniqueResult();
        if (null == o) {
            return 0;
        }
        return Integer.parseInt(o.toString());
    }

    /**
     * 删除回帖
     */
    @Override
    public void deleteReply(String id) {
        Session session = getCurrentSession();
        AnnouncementReplyEntity announcementReplyEntity = new AnnouncementReplyEntity();
        announcementReplyEntity.setId(id);
        session.delete(announcementReplyEntity);
        session.flush();
        session.close();
    }

    /**
     * 删除回帖与子回帖
     */
    @Override
    public void deleteReplyAndChildren(String id) {
        //获取所有子类id
        List<String> list = this.queryReplyByParentId(id);
        for (String s : list) {
            //获取实体
            AnnouncementReplyEntity entity = this.get(AnnouncementReplyEntity.class, s);
            //如果
        }
    }

    /**
     * 根据父回复id获取所有子回复id
     */
    @Override
    public List<String> queryReplyByParentId(String id) {
        Session session = getCurrentSession();
        List<String> list = session.createSQLQuery("select id from announcement_reply where reply_id = ?").list();
        session.flush();
        session.close();
        return list;
    }
}
