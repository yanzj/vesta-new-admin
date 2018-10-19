package com.maxrocky.vesta.presistent.repositoryHbmImpl;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.AnnouncementRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by wangyifan on 2016/5/09.
 */
@Repository
public class AnnouncementRepositoryImpl extends BaseRepositoryImpl implements AnnouncementRepository {
    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.openSession();
    }

    /**
     * 更新公告信息(投票数、回复数、点赞数)
     * @param announcementEntity
     * @author Wyd_2016.06.01
     */
    public void updateAnnouncement(AnnouncementEntity announcementEntity){
        Session session = getCurrentSession();
        session.update(announcementEntity);
        session.flush();
        session.close();
    }

    /**
     * 通过公告ID检索覆盖城市、项目范围
     * @author Wyd_2016.05.31
     */
    public List<Object[]> queryCitysById(String announcementId){
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select sc.city_name,sc.project_name from announcement_scope sc where sc.announcement_detail_id =:announcementId");
        sqlQuery.setParameter("announcementId", announcementId);
        List<Object[]> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * Describe:
     * CreateBy:yifan
     * CreateOn:2016-05-18 15:40:37
     *
     * @param announcementEntity
     * @return
     * @throws Exception
     */
    @Override
    public List<AnnouncementEntity> queryAllByPage(AnnouncementEntity announcementEntity, WebPage webPage, Date staDate, Date endDate,String roleScopes) throws Exception {
        StringBuffer hql = new StringBuffer();
        List<AnnouncementEntity> announcementEntityList = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        hql.append("FROM AnnouncementEntity as ad WHERE 1=1");
        //数据权限范围条件
        if (!roleScopes.contains("all")){
            hql.append(" and ad.id in (select distinct sc.announcementDetailId from AnnouncementScopeEntity sc ");
            hql.append(" where sc.cityId = '0' or sc.projectId in ("+roleScopes.substring(0,roleScopes.length()-1)+") ) ");
        }
        // 发布状态
        if (null != announcementEntity.getReleaseStatus() && 999 != announcementEntity.getReleaseStatus()) {
            hql.append(" and ad.releaseStatus = ? ");
            params.add(announcementEntity.getReleaseStatus());
        }
        //关键词
        if (null != announcementEntity.getTitle() && !"".equals(announcementEntity.getTitle())) {
            hql.append(" and (ad.title like ? or ad.content like ?)");
            params.add('%' + announcementEntity.getTitle() + '%');
            params.add('%' + announcementEntity.getContent() + '%');
        }
        /*
        // 标题
        if (null != announcementEntity.getTitle() && !"".equals(announcementEntity.getTitle())) {
            hql.append(" and ad.title like ?");
            params.add('%' + announcementEntity.getTitle() + '%');
        }
        // 内容
        if (!StringUtil.isEmpty(announcementEntity.getContent()) && !"".equals(announcementEntity.getContent())) {
            hql.append(" or ad.content like ?");
            params.add('%' + announcementEntity.getContent() + '%');
        }
        */
        // 发布人
        if (!StringUtil.isEmpty(announcementEntity.getReleasePerson())) {
            hql.append(" and ad.releasePerson like ?");
            params.add('%' + announcementEntity.getReleasePerson() + '%');
        }
        //发布日期区间1
        if (staDate != null) {
            hql.append(" and ad.createDate >= ?");
            params.add(staDate);
        }
        //发布日期区间2
        if (endDate != null) {
            hql.append(" and ad.createDate <= ?");
            params.add(endDate);
        }
        hql.append(" and ad.status = 1 ");
        hql.append(" ORDER BY ad.releaseDate DESC, ad.createDate DESC");
        /**
         * 追加分页_Wyd_2016.06.02
         */
        if (webPage != null){
            return this.findByPage(hql.toString(),webPage,params);
        }
        announcementEntityList = (List<AnnouncementEntity>) getHibernateTemplate().find(hql.toString(), params.toArray());
        return announcementEntityList;
    }

    /**
     * 查詢所有CRM_传输项目城市
     *
     * @return
     */
    public List<Object[]> listCity() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        Session session = getCurrentSession();
        List<Object[]> list = session.createSQLQuery("select DISTINCT c.id,c.city_name  from house_project p,house_city c where p.city_id = c.id").list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 根据城市查询所有城市下项目
     *
     * @return
     */
    public List<Object[]> listProject(String cityId) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select DISTINCT p.PINYIN_CODE,p.name from house_project p,house_city c where p.city_id = c.id and p.CITY_ID = ?");
        sqlQuery.setParameter(0, cityId);
        List<Object[]> list = (List<Object[]>) sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 查询所有城市所有项目
     * @return List<Object[]>
     */
    public List<Object[]> listAllProject() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(" select DISTINCT p.PINYIN_CODE,p.name from house_project p ");
        List<Object[]> list = (List<Object[]>) sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过_projectCode_检索项目
     * @return List<Object[]>
     */
    public List<Object[]> getProjectByCode(String projectCode) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>();
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery(" select DISTINCT p.PINYIN_CODE,p.name from house_project p where p.pinyin_code = ? ");
        sqlQuery.setParameter(0,projectCode);
        List<Object[]> list = (List<Object[]>) sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }


    /**
     * 根据通告id，删除通告id相同的通告范围信息
     */
    @Override
    public void deleteAnnouncementScope(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("delete from announcement_scope where announcement_detail_id = ?");
        sqlQuery.setParameter(0, id);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 据挺高id获取当前通告下的关联投票
     *
     * @param id
     */
    @Override
    public List<Object[]> queryVoteByAnnouncementId(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("select distinct vd.id,vd.title from announcement_vote av,announcement_detail ad,vote_detail vd where av.vote_id = vd.id and av.announcement_id = ad.id and  ad.id = ?");
        sqlQuery.setParameter(0, id);
        List<Object[]> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 根据活动id查更新关联投票信息
     *
     * @return
     */
    @Override
    public boolean updateVoteByAnnouncementId(String announcementId, String voteId) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("UPDATE announcement_vote SET vote_id = ? WHERE announcement_id = ? ");
        sqlQuery.setParameter(0, voteId);
        sqlQuery.setParameter(1, announcementId);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
        return true;
    }

    /**
     * 插入公告，投票关联数据
     *
     * @param av
     */
    @Override
    public void saveAnnouncementVote(AnnouncementVoteEntity av) {
        Session session = getCurrentSession();
        session.save(av.getClass().getName(), av);
        session.flush();
        session.close();
    }

    /**
     * 删除公告关联投票
     *
     * @param id
     */
    @Override
    public void deleteAnnouncementVote(String id) {
        Session session = getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("delete from announcement_vote where announcement_id = ? ");
        sqlQuery.setParameter(0, id);
        sqlQuery.executeUpdate();
        session.flush();
        session.close();
    }

    /**
     * 通过公告Id检索发布范围列表
     * @param announcementId
     * @return
     */
    public List<AnnouncementScopeEntity> getScopeByAnnouncement(String announcementId){
        Session session = getCurrentSession();
        String hql = " from AnnouncementScopeEntity where announcementDetailId = ? ";
        Query query = session.createQuery(hql);
        query.setParameter(0,announcementId);
        List list = query.list();
        session.flush();
        session.close();
        return list;
    }

    //----------------------------------前台接口-------------------------------------

    /**
     * 查询所有在当前项目下的社区公告 ，与区域为项目所在区域的公告
     *
     * @param city
     * @param projectName
     * @return
     */
    @Override
    public List<Object[]> queryAnnouncementByCityAndProjectName(String city, String projectName) {
        Session session = getCurrentSession();
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT DISTINCT(ANNOUNCEMENT_DETAIL_ID),TITLE,RELEASE_DATE,IS_VOTE,LIKE_NUM,REPLY_NUM,DETAIL.CREATE_PERSON ");
        sb.append(" FROM announcement_scope SCOPE RIGHT JOIN announcement_detail DETAIL  ");
        sb.append(" ON SCOPE.ANNOUNCEMENT_DETAIL_ID = DETAIL.ID WHERE  ");
        sb.append(" DETAIL.STATUS = '1' AND DETAIL.RELEASE_STATUS = '1' ");
        sb.append(" OR SCOPE.IS_ALL = '全部城市' ");
        sb.append(" OR SCOPE.CITY_NAME = ? OR SCOPE.PROJECT_NAME = ? ");

        SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
        sqlQuery.setParameter(0, city);
        sqlQuery.setParameter(1, projectName);
        List<Object[]> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 通过用户Id和公告Id_检索记录条数
     * @param userId
     * @param announcementId
     * @author Wyd_2016.06.01
     * @return
     */
    public Integer queryLogByUserAndAnnouncement(String userId, String announcementId){
        Session session = getCurrentSession();
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(1) from announcement_log al where al.user_id = ? and al.announcement_id = ?");
        SQLQuery sqlQuery = session.createSQLQuery(sql.toString());
        sqlQuery.setParameter(0,userId);
        sqlQuery.setParameter(1, announcementId);
        List<BigInteger> list = sqlQuery.list();
        BigInteger bigInteger = list.get(0);
        session.flush();
        session.close();
        return bigInteger.intValue();
    }

    @Override
    public AnnouncementEntity queryAnnouncementByID(String id) {

        String hql = "FROM AnnouncementEntity WHERE id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        List<AnnouncementEntity> announcementList = query.list();
        if(announcementList.size() == 0){
            return null;
        }

        return announcementList.get(0);
    }



    @Override
    public List<AnnouncementReplyEntity> queryAnnouncementReplyById(String announcementId , String userId) {
        Session session = getCurrentSession();
//        String hql = "FROM AnnouncementReplyEntity WHERE status = '1' and topicId = :announcementId and ( type='2' or(type = '1' and userId = :userId )) or (id in (select replyId from AnnouncementReplyEntity WHERE replyId is not null and topicId = :announcementId)) ORDER BY FLOOR ASC ";
//        String hql = " FROM AnnouncementReplyEntity WHERE status = '1' and topicId = :announcementId and userId = :userId or type='2' ";
        String hql_1 = " FROM AnnouncementReplyEntity WHERE status = '1' and topicId = :announcementId and userId = :userId order by createOn desc ";
        Query query_1 = session.createQuery(hql_1);
        query_1.setParameter("announcementId", announcementId);
        query_1.setParameter("userId", userId);
        List<AnnouncementReplyEntity> announcementList = query_1.list();

        String hql_2 = " FROM AnnouncementReplyEntity WHERE status = '1' and type='2' and replyContent != '' order by createOn desc ";
        Query query_2 = session.createQuery(hql_2);
        List<AnnouncementReplyEntity> list = query_2.list();
        announcementList.addAll(list);

        session.flush();
        session.close();
        return announcementList;
    }

    /**
     * 查询所有在当前项目下的社区公告 ，与区域为项目所在区域的公告
     *
     * @param houseProjectId
     * @return
     */
    @Override
    public List<Object[]> queryAnnouncementByProjectID(String houseProjectId,String userId) {
        Session session = getCurrentSession();
        StringBuffer sb = new StringBuffer();
//        sb.append(" SELECT announcement.*,c.id from (SELECT DISTINCT(ANNOUNCEMENT_DETAIL_ID) as DETAILID,TITLE,CONTENT,RELEASE_DATE,IS_VOTE,LIKE_NUM,REPLY_NUM,DETAIL.CREATE_PERSON ");
//        sb.append(" FROM announcement_scope SCOPE, announcement_detail DETAIL  ");
//        sb.append(" where SCOPE.ANNOUNCEMENT_DETAIL_ID = DETAIL.ID AND  ");
//        sb.append(" DETAIL.STATUS = '1' AND DETAIL.RELEASE_STATUS = '1' ");
//        sb.append(" AND SCOPE.PROJECT_ID = ?  OR SCOPE.IS_ALL = '1' ) AS announcement LEFT JOIN ");
//        sb.append(" collection c on announcement.DETAILID = c.message_id ORDER BY RELEASE_DATE DESC");
        //2016-06-16 修改SQL_Wyd
        sb.append(" SELECT announcement.*,c.id ");
        sb.append(" FROM ( SELECT DISTINCT(ANNOUNCEMENT_DETAIL_ID) AS DETAILID,TITLE,CONTENT,RELEASE_DATE,IS_VOTE,LIKE_NUM,REPLY_NUM,DETAIL.CREATE_PERSON,DETAIL.content_synopsis ");
        sb.append(" FROM announcement_scope SCOPE, announcement_detail DETAIL ");
        sb.append(" WHERE SCOPE.ANNOUNCEMENT_DETAIL_ID = DETAIL.ID AND DETAIL.STATUS = '1' AND DETAIL.RELEASE_STATUS = '1' ");
        sb.append(" AND (SCOPE.PROJECT_ID = ? OR SCOPE.IS_ALL = '1')) AS announcement ");
        sb.append(" LEFT JOIN collection c ON announcement.DETAILID = c.message_id and c.USER_ID = ? ");
        sb.append(" ORDER BY RELEASE_DATE DESC ");
        SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
        sqlQuery.setParameter(0, houseProjectId).setParameter(1,userId);
        List<Object[]> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 查询所有在当前项目下的社区公告
     */
    public List<Object[]> queryAnnouncementByProjectID(String houseProjectId) {
        Session session = getCurrentSession();
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT DISTINCT(ANNOUNCEMENT_DETAIL_ID) AS DETAILID,TITLE,CONTENT,RELEASE_DATE,IS_VOTE,LIKE_NUM,REPLY_NUM,DETAIL.CREATE_PERSON,DETAIL.content_synopsis ");
        sb.append(" FROM announcement_scope SCOPE, announcement_detail DETAIL ");
        sb.append(" WHERE SCOPE.ANNOUNCEMENT_DETAIL_ID = DETAIL.ID AND DETAIL.STATUS = '1' AND DETAIL.RELEASE_STATUS = '1' ");
        sb.append(" AND (SCOPE.PROJECT_ID = ? OR SCOPE.IS_ALL = '1') ");
        sb.append(" ORDER BY RELEASE_DATE DESC ");
        SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
        sqlQuery.setParameter(0, houseProjectId);
        List<Object[]> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    @Override
    public boolean saveCollection(CollectionEntity collectionEntity) {
        if(null != collectionEntity){
            Session session = getCurrentSession();
            session.save(collectionEntity);
            session.flush();
            return true;
        }
        return false;
    }

    @Override
    public CollectionEntity getCollectionById(String collectionId) {

        String hql = "FROM CollectionEntity WHERE id = :id";
        Query query = getCurrentSession().createQuery(hql);
        query.setParameter("id", collectionId);
        List<CollectionEntity> collections = query.list();
        if(collections.size() == 0){
            return null;
        }

        return collections.get(0);

    }

    @Override
    public boolean deleteCollection(CollectionEntity collectionEntity) {
        if(null != collectionEntity){
            Session session = getCurrentSession();
            session.delete(collectionEntity);
            session.flush();
            return true;
        }
        return false;
    }

    @Override
    public List<Object[]> getCollectionList(String userId) {
        Session session = getCurrentSession();
        StringBuffer sb = new StringBuffer();
        sb.append(" select c.id as c_id,detail.id ,TITLE,content,RELEASE_DATE,IS_VOTE,LIKE_NUM,REPLY_NUM,CREATE_PERSON,content_synopsis ");
        sb.append(" from announcement_detail detail,collection c where detail.id = c.message_id AND c.user_id = ?  ");
        sb.append(" ORDER BY c.create_date DESC  ");

        SQLQuery sqlQuery = session.createSQLQuery(sb.toString());
        sqlQuery.setParameter(0, userId);
        List<Object[]> list = sqlQuery.list();
        session.flush();
        session.close();
        return list;
    }

    /**
     * 查询用户是否收藏
     */
    public List<CollectionEntity> queryUserCollection(String messageType, String messageId, String userId){
        Session session = getCurrentSession();
        String hql = " from CollectionEntity where messageType = ? and messageId = ? and userId = ? ";
        Query query = session.createQuery(hql);
        List<CollectionEntity> list = query.setParameter(0, messageType).setParameter(1, messageId).setParameter(2, userId).list();
        session.flush();
        session.close();
        return list;
    }

}
