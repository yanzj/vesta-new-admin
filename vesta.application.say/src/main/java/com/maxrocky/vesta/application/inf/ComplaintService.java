package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.JsonDTO.ComplaintDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.utility.Page;


/**
 * Created by chen on 2016/1/21.
 */
public interface ComplaintService {
    /**新增投诉*/
    ApiResult AddComplaint(UserInfoEntity userInfoEntity,ComplaintDTO complaintDTO);
    /**获取用户投诉列表*/
    ApiResult getUserComplaints(String userId,Page page);
    /**获取投诉列表*/
    ApiResult getComplaintList();
    /**获取投诉详情*/
    ApiResult getComplaintDetail(String id);
}
