package com.maxrocky.vesta.domain.repository;


import com.maxrocky.vesta.domain.model.AnnouncementVoteEntity;
import com.maxrocky.vesta.domain.model.VoteOptionEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 投票管理_持久层
 * @author Wyd_2016/05/26
 */
public interface VoteRepository extends BaseRepository {

    /**
     * 通过_投票模板Id_检索公告Id列表
     * @param voteId
     * @return
     */
    List<String> queryAnnouncementIdByVoteId(String voteId);

    /**
     * 根据通告id查询通告所有范围信息
     * User: yifan
     * Date: 2016/5/18
     * Time: 11:43
     * @return
     */
   public  List<Object[]> queryAllVoteTitle();

    /**
     * 检索公告_投票数据列表
     * @author Wyd_2016.06.01
     * @return
     */
    List<AnnouncementVoteEntity> queryAnnouncementVoteList(String announcementId);

   /**
    * 检索公告_投票数据列表
    * @author lxx_2016-08-16
    * @return
    */
   List<AnnouncementVoteEntity> queryAnnouncementList(String voteId);

    /**
     * 检索投票项数据列表
     * @author Wyd_2016.06.01
     * @return
     */
    List<VoteOptionEntity> queryVoteOptionList(String voteId);


    /**
     * 通过_公告Id或投票Id_查询参与投票总人数
     * @param announcementId    _公告Id
     * @param voteId    _投票Id
     * @return
     */
    Integer quereyVotePersonCount(String announcementId, String voteId);

    /**
     * 通过_投票Id_检索投票项
     * @param voteId
     * @return
     */
    List<Map<String,Object>> queryVoteOptionByVoteId(String voteId);

    /**
     * 通过_公告Id_查询公告-投票数据列表
     * @return
     */
    List<Map<String,Object>> queryAnnouncementVoteDataList(String announcementId,String projectCode);

    /**
     * 通过_用户Id/公告Id/投票Id_(判断用户是否已经投票)
     */
    Integer queryVoteRecordByUser(String userId, String announcementId, String voteId);

    /**
     * 分页条件查询投票列表
     * @param titlt
     * @param voteStatus
     * @param staDate
     * @param endDate
     * @param webPage
     * @return  List
     */
    List<Map<String,Object>> queryVoteListByPage(String titlt,Integer voteStatus,String staDate,String endDate,WebPage webPage);

    /**
     * 通过_投票Id_检索投票项统计信息列表
     * @param voteId
     * @return
     */
    List<Map<String,Object>> queryVoteOptionStatistic(String voteId);

    /**
     * 通过_公告Id_删除公告-投票信息
     * @param announcementId
     */
    void deleteAnnouncementVoteByAnnouncementId(String announcementId);

    /**
     * 获取所有项目列表
     */
    List<Object[]> queryProjectList();

    /**
     * 通过项目Code_查询城市ID/名称
     */
    List<Object[]> queryCityByProjectCode(String projectCode);

    /**
     * 通过_公告Id和项目Code_查询参与投票总人数
     * @param announcementId    _公告Id
     * @param projectCode    _项目Code
     * @return
     */
    Integer quereyVotePersonCountByProject(String announcementId, String projectCode);

    /**
     * 通过_投票模板Id_获取应用该模板的公告项目列表
     */
    List<Map<String,Object>> queryAnnProByVote(String voteId);

    /**
     * 通过_公告Id和项目Code_检索投票项统计信息
     */
    List<AnnouncementVoteEntity> queryVoteOptionNumByAnoPro(String announcementId, String projectCode);

}
