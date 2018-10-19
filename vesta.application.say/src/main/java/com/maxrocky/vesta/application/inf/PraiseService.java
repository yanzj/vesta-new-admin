package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.AdminDTO.PraiseAdminDTO;
import com.maxrocky.vesta.application.JsonDTO.PraiseReceiveDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.Page;

import java.util.List;


/**
 * Created by chen on 2016/1/21.
 * 表扬
 */
public interface PraiseService {
    /**新增表扬*/
    ApiResult AddPraise(UserInfoEntity userInfoEntity,PraiseReceiveDTO praiseReceiveDTO);
    /**获取前台用户表扬列表*/
    ApiResult getUserPraises(String userId,Page page);
    /**获取员工表扬列表*/
    ApiResult getStaffPraise(String userId,Page page);
    /**获取表扬详情*/
    ApiResult getPraiseDetail(String praiseId);
    /**获取员工表扬未读条数*/
    ApiResult getUnReaderCount(String targetId);
    /**变更未读状态*/
    ApiResult altReaderStatus(String targetId);

    /**后台获取表扬列表*/
    List<PraiseAdminDTO> getPraiseList(PraiseAdminDTO praiseAdminDTO,WebPage webPage);
    /**后台获取表扬详情*/
    PraiseAdminDTO getPraise(String praiseId);
    /**后台删除表扬*/
    void deletePraise(String praiseId);
}
