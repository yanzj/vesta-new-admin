package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.AnnouncementDTO;
import com.maxrocky.vesta.application.DTO.CollectionDTO;
import com.maxrocky.vesta.application.service.BaseService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.AnnouncementEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/18
 * Time: 11:10
 * Describe:
 */
public interface AnnouncementService extends BaseService<AnnouncementEntity> {

    /**
     * Describe:
     * CreateBy:yifan
     *
     * @param AnnouncementDTO
     * @param webPage
     * @return AnnouncementDTO
     * @throws Exception
     */
    public List<AnnouncementDTO> queryAllByPage(AnnouncementDTO AnnouncementDTO, WebPage webPage) throws Exception;

    /**
     * 查詢所有CRM_传输项目城市
     *
     * @return
     */
    public List<Object[]> listCity();

    /**
     * 根据城市查询所有城市下项目
     *
     * @return
     */
    public List<Object[]> listProject(String cityId);

    /**
     * 更新通知范围与通知详情
     *
     * @param announcementDTO
     */
    public void updateAnnouncementAndScope(AnnouncementDTO announcementDTO);


    /**
     * 更新通知范围与通知详情
     *
     * @param announcementDTO
     */
    public void updateAnnouncementAndScope(UserPropertyStaffEntity userPropertystaffEntit,AnnouncementDTO announcementDTO);

    /**
     * 根据通告id获取当前通告下的关联投票
     *
     * @param id
     * @return
     */
    public List<Object[]> queryVoteByAnnouncementId(String id);

    /**
     * 删除announcement_detail与announcement_vote中间表信息
     *
     * @param announcementDTO
     */
    public void deleteAnnouncementVote(AnnouncementDTO announcementDTO);

    /**
     * 删除announcement_detail与announcement_vote中间表信息
     *
     * @param announcementDTO
     */
    public void deleteAnnouncementVote(UserPropertyStaffEntity userPropertystaffEntity,AnnouncementDTO announcementDTO);

    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    String exportExcel(UserPropertyStaffEntity user, AnnouncementDTO announcementDTO,
                       HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws Exception;

    /**
     * 通过社区公告Id检索发布项目范围
     */
    List<Map<String,Object>> getProjectScopeByAnnouncementId(String announcementId);

    //---------------前端接口
    /**
     * 查询所有在当前项目下的社区公告 ，与区域为项目所在区域的公告
     * @param city
     * @param projectName
     * @return
     */
    public ApiResult queryAnnouncementByCityAndProjectName(String city,String projectName);

    /**
     * 公告_点赞
     * @param userInfoEntity
     * @param announcementId
     * @return
     */
    ApiResult clickPraise(UserInfoEntity userInfoEntity, String announcementId);

    /**
     * 根据Id取得公告信息
     * @param announcementid
     * @return
     */
    ApiResult queryAnnouncementReplyById(String announcementid);

    /**
     * 根据Id和UserId取得公告信息
     * @param announcementid
     * @return
     */
    ApiResult queryAnnouncementReplyById(String announcementid,String userId);

    /**
     * 物业公告列表
     * @param houseProjectId
     * @return
     */
    ApiResult getPropertyAnnouncementList(String houseProjectId,UserInfoEntity user);

    ApiResult getPropertyAnnouncementList(String houseProjectId);


    /**
     * 添加收藏
     * @param collection
     * @return
     */
    ApiResult addCollection(CollectionDTO collection);


    /**
     * 删除收藏
     * @param collection
     * @return
     */
    ApiResult delectCollection(CollectionDTO collection);


    /**
     * 取得收藏列表
     * @param userInfoEntity
     * @return
     */
    ApiResult getCollectionList(UserInfoEntity userInfoEntity);


}
