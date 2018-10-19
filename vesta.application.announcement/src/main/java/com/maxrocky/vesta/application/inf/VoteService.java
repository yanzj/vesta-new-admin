package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.VoteDTO;
import com.maxrocky.vesta.application.service.BaseService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.model.VoteEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 投票管理Service
 * @author Wyd_2016/05/26
 */
public interface VoteService extends BaseService<VoteEntity> {

    /**
     * 通过投票模板Id_删除投票信息(投票模板、投票项目)
     */
    void deleteVoteById(String userId, String voteId);


    /**
     * 通过投票模板Id_删除投票信息(投票模板、投票项目)
     */
    void deleteVoteById(UserPropertyStaffEntity userPropertystaffEntity,String userId, String voteId);
    /**
     * 根据通告id查询通告所有范围信息
     * User: yifan
     * Date: 2016/5/18
     * Time: 11:10
     * @return
     */
    public List<Object[]> queryAllVoteTitle();

    /**
     * 条件检索投票列表
     * @param voteDTO
     * @param webPage
     * @return
     */
    List<VoteDTO> queryVoteListByPage(VoteDTO voteDTO,WebPage webPage);

    /**
     * 新增一条投票信息
     * @param voteDTO
     */
    void addOrUpdateVote(VoteDTO voteDTO);


    /**
     * 新增一条投票信息
     * @param voteDTO
     */
    void addOrUpdateVote(UserPropertyStaffEntity userPropertystaffEntity,VoteDTO voteDTO);

    /**
     * 投票动作
     * @param announcementVoteId    _公告-投票数据Id
     */
    void voteAction(String userId, String announcementVoteId);

    /**
     * 投票详情
     * @param announcementId    _公告Id
     * @return
     */
    ApiResult getVoteDetail(String userId,String announcementId,String projectCode);

    /**
     * 投票结果
     * @param announcementId    _公告Id
     * @return ApiResult
     */
    ApiResult getVoteResult(String announcementId,String projectCode);

    /**
     * 通过_公告Id或投票Id_查询参与投票总人数
     * @param announcementId
     * @param voteId
     * @return
     */
    Integer quereyVotePersonCount(String announcementId,String voteId);

    /**
     * 通过_投票Id_获取投票模板信息
     * @param voteId
     * @return VoteEntity
     */
    VoteEntity queryVoteById(String voteId);

    /**
     * 通过_投票Id_检索投票项统计信息列表
     * @param voteId
     * @return
     */
    List<Map<String,Object>> queryVoteOptionStatistic(String voteId);

    /**
     * 通过_投票Id_按公告项目进行投票统计
     */
    List<Map<String,Object>> queryVoteProjectStatistic(String voteId);


    /**
     * 通过_投票Id_检索投票项
     */
    List<Map<String,Object>> queryVoteOptionByVoteId(String voteId);

    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    String exportExcel(UserPropertyStaffEntity user,VoteDTO voteDTO,
                       HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException;
}
