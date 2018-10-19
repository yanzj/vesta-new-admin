package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.JsonDTO.ConsultDTO;
import com.maxrocky.vesta.application.AdminDTO.PropertyConsultDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;

import java.util.List;


/**
 * Created by chen on 2016/1/21.
 */
public interface ConsultService {
    /**新增咨询*/
    ApiResult AddConsult(UserInfoEntity userInfoEntity,ConsultDTO consultDTO);
    /**获取用户咨询历史*/
    ApiResult getConsultList(String userId,Page page);
    /**咨询详情*/
    ApiResult getConsultDetail(String id);

    /****************************************************以下为后台操作************************************************/

    /**
     * 初始化物业咨询管理列表
     * @param propertyConsult
     * @param webPage
     * @return
     */
    List<PropertyConsultDTO> queryConsult(PropertyConsultDTO propertyConsult, WebPage webPage);

    /**
     * 根据咨询信息id删除(更新状态)
     * @param id
     * @param userPropertystaffEntity
     * @return
     */
    String removePropertyConsultById(String id, UserPropertyStaffEntity userPropertystaffEntity);

    /**
     * 根据咨询信息id 查询咨询信息及回复信息
     * @param id
     * @return
     */
    PropertyConsultDTO queryAnswerMessage(String id);

    /**
     * 回复咨询信息(添加或修改回复信息)
     * @param userPropertystaffEntit
     * @param propertyConsult
     */
    void saveORupdatePropertyConsult(UserPropertyStaffEntity userPropertystaffEntit,PropertyConsultDTO propertyConsult);
}
