package com.maxrocky.vesta.domain.repository;


import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/18
 * Time: 11:43
 * Describe:
 */
public interface AnnouncementRepository extends BaseRepository {

    /**
     * 更新公告信息(投票数、回复数、点赞数)
     * @param announcementEntity
     * @author Wyd_2016.06.01
     */
    void updateAnnouncement(AnnouncementEntity announcementEntity);

    /**
     * 通过公告ID检索覆盖城市范围
     * @author Wyd_2016.05.31
     */
    List<Object[]> queryCitysById(String announcementId);

    /**
     * Describe:公告信息
     * CreateBy:yifan
     * @param announcementEntity
     * @param webPage
     * @return DeliveryPlanCrmEntity
     * @throws Exception
     */
    public List<AnnouncementEntity> queryAllByPage(AnnouncementEntity announcementEntity, WebPage webPage,Date staDate, Date endDate,String roleScopes) throws Exception;

    /**
     * 查詢所有CRM_传输项目城市
     * @return
     */
    public List<Object[]> listCity();
    /**
     * 根据城市查询所有城市下项目
     * @return
     */
    public List<Object[]> listProject(String cityId);

    /**
     * 查询所有城市所有项目
     * @return List<Object[]>
     */
    List<Object[]> listAllProject();

    /**
     * 通过_projectCode_检索项目
     * @return List<Object[]>
     */
    List<Object[]> getProjectByCode(String projectCode);
    /**
     * 根据通告id，删除通告id相同的通告范围信息
     */
    public void deleteAnnouncementScope(String id);

    /**
     * 据通告id获取当前通告下的关联投票
     * @param id
     */
    public List<Object[]> queryVoteByAnnouncementId(String id);

    /**
     * 根据活动id查更新关联投票信息
     *
     * @return
     */
    public boolean updateVoteByAnnouncementId(String announcementId, String voteId);


    /**
     * 活动_投票 数据增加，自定义
     * @param av
     */
    public void saveAnnouncementVote(AnnouncementVoteEntity av);

    /**
     * 删除公告关联投票
     * @param id
     */
    public void deleteAnnouncementVote(String id);

    /**
     * 通过公告Id检索发布范围列表
     * @param announcementId
     * @return
     */
    List<AnnouncementScopeEntity> getScopeByAnnouncement(String announcementId);

    //--------------------------前台接口
    /**
     * 查询所有在当前项目下的社区公告 ，与区域为项目所在区域的公告
     * @param city
     * @param projectName
     * @return
     */
    public List<Object[]> queryAnnouncementByCityAndProjectName(String city,String projectName);

    /**
     * 查询所有在当前项目下的社区公告
     */
    List<Object[]> queryAnnouncementByProjectID(String houseProjectId);

    /**
     * 根据公告ID查询公告详细
     * @param id
     * @return
     */
    public AnnouncementEntity queryAnnouncementByID(String id);

    /**
     * 通过用户Id和公告Id_检索记录条数
     * @param userId
     * @param announcementId
     * @author Wyd_2016.06.01
     * @return
     */
    Integer queryLogByUserAndAnnouncement(String userId, String announcementId);

    /**
     * 根据公告ID查询所有公告回复
     * @param announcementId
     * @return
     */
    public List<AnnouncementReplyEntity> queryAnnouncementReplyById(String announcementId , String userId);

    /**
     * 查询所有在当前项目下的社区公告 ，与区域为项目所在区域的公告
     * @param houseProjectId
     * @return
     */
    public List<Object[]> queryAnnouncementByProjectID(String houseProjectId, String userId);

    /**
     * 添加个人收藏
     * @param collectionEntity
     * @return
     */
    boolean saveCollection(CollectionEntity collectionEntity);


    /**
     * 根据Id取得数据
     * @param collectionId
     * @return
     */
    CollectionEntity getCollectionById(String collectionId);


    /**
     * 删除个人收藏
     * @param collectionEntity
     * @return
     */
    boolean deleteCollection(CollectionEntity collectionEntity);

    /**
     * 查询个人收藏
     * @param userId
     * @return
     */
    List<Object[]> getCollectionList(String userId);

    /**
     * 查询用户是否收藏
     */
    List<CollectionEntity> queryUserCollection(String messageType, String messageId, String userId);

}
