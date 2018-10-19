package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.CommunityNewScope;
import com.maxrocky.vesta.domain.repository.AnnouncementScopeRepository;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangyifan on 2016/5/09.
 */
@Repository
public class AnnouncementScopeRepositoryImpl extends BaseRepositoryImpl implements AnnouncementScopeRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 根据通告id查询通告所有范围信息
     * @param id
     * @return
     */
    @Override
    public List<Object[]> queryByAnnouncementId(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select id,city_name,is_all,project_name,city_id,project_id from  announcement_scope s where s.announcement_detail_id = ?");
        sqlQuery.setParameter(0, id);
        List<Object[]> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 根据通告id删除关联活动范围
     * @param id
     */
    @Override
    public void deleteAnnouncementScopeByAnnouncementId(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("delete from announcement_scope where announcement_detail_id = ? ");
        sqlQuery.setParameter(0,id);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }
/**===============================================================================**/

    /**
     * 根据新闻id查询新闻通告范围
     * @param id
     * @return
     */
    @Override
    public List<Object[]> queryByCommunitNewsId(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select id,city_name,is_all,project_name,city_id,project_id from  community_news_scope s where s.community_detail_id = ?");
        sqlQuery.setParameter(0, id);
        List<Object[]> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 根据新闻Id删除所有新闻
     * @param id
     */
    @Override
    public void deleteCommunitNewsId(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("delete from community_news_scope where community_detail_id = ? ");
        sqlQuery.setParameter(0,id);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 添加\修改新闻范围信息
     * @param communityNewScope
     */
    @Override
    public void addOrUpdateCommunitNews(CommunityNewScope communityNewScope) {
        Session session = getCurrentSession();
        session.saveOrUpdate(communityNewScope);
        session.flush();
        session.close();
    }

    /**
     * 按照城市（pinyingCode）查询新闻信息
     * @param communityNewScope
     * @return
     */
    @Override
    public List<CommunityNewScope> findByProjectIdAndNewsId(CommunityNewScope communityNewScope) {
        Session session = getCurrentSession();
        String hql = "from CommunityNewScope where projectId = ? or isAll = 1";
        Query query = session.createQuery(hql);
        query.setParameter(0,communityNewScope.getProjectId());
        List<CommunityNewScope> result = query.list();
        return result;
    }


}
