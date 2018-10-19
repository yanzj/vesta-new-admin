package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.CommunityAppointManageDto;
import com.maxrocky.vesta.application.admin.dto.HousePayBatchQueryDto;
import com.maxrocky.vesta.application.admin.dto.UserAppointDto;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by Tom on 2016/4/13 23:59.
 * Describe:
 */
public interface CommunityHouseAppointService {

    /**
     * Code:Admin
     * Type:Admin Method
     * Describe:交付批次管理列表
     * CreateBy:Tom
     * CreateOn:2016-04-14 12:02:35
     */
    List<CommunityAppointManageDto> getCommunityAppointList(HousePayBatchQueryDto housePayBatchQueryDto ,WebPage webPage);

    /**
     * 获取用户预约列表
     * @param userAppointDto
     * @param webPage
     * @return
     */
    public List<UserAppointDto> listAppoints(UserAppointDto userAppointDto,WebPage webPage);

    String removeHousePayById(String housepayID, UserPropertyStaffEntity userPropertystaffEntity);





}
