package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.UserFeedbackDTO;
import com.maxrocky.vesta.application.DTO.admin.UserFeedbackSearchDTO;
import com.maxrocky.vesta.application.DTO.json.AppealDTO;
import com.maxrocky.vesta.application.DTO.json.UI0010.FeedbackParamJsonDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserFeedbackEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by sunmei on 2016/2/24.
 */
public interface UserFeedbackService {
    /**
     * 意见反馈列表
     *
     * @param userFeedbackSearchDTO
     * @param webPage
     * @return
     */
    List<UserFeedbackDTO> FeedbackList(UserFeedbackSearchDTO userFeedbackSearchDTO, WebPage webPage);

    UserFeedbackDTO getUserImageByBusinessId(String id);

    /**
     * 意见反馈列表
     * param user
     * param feedbackDTO
     * param webPage
     * return
     */
    List<UserFeedbackSearchDTO> getFeedbackList(UserPropertyStaffEntity user, UserFeedbackSearchDTO feedbackDTO, WebPage webPage);

    /**
     * 通过主键Id获取意见反馈信息
     *
     * @param feedbackId 主键Id
     * @return UserFeedbackEntity
     */
    public UserFeedbackEntity getFeedbackById(String feedbackId);

    /**
     * 详情
     * param feedbackDTO
     * return
     */
    UserFeedbackSearchDTO feedInfo(UserFeedbackSearchDTO feedbackDTO);

    /**
     * 修改
     * param feedbackDTO
     * return
     */
    String feedUpdate(UserPropertyStaffEntity user, UserFeedbackSearchDTO feedbackDTO);

    /**
     * 检查用户是否已经是业主
     *
     * @param feedbackId
     * @return
     */
    ApiResult searchFeedBack(String feedbackId);

    /**
     * 保存意见反馈
     *
     * @param user 用户
     * @return ApiResult
     */
    ApiResult saveFeedback(UserInfoEntity user, FeedbackParamJsonDTO feedbackParamJsonDTO);

    /**
     * 保存身份申述
     *
     * @param user 用户
     * @return ApiResult
     */
    ApiResult saveUserAppeal(UserInfoEntity user, AppealDTO appealDTO);

    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    String exportExcel(UserPropertyStaffEntity user, UserFeedbackSearchDTO feedbackDTO,
                       HttpServletResponse httpServletResponse, String type, HttpServletRequest httpServletRequest) throws IOException;


}
