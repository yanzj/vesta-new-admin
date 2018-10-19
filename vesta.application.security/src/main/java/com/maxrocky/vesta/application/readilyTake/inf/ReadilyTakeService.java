package com.maxrocky.vesta.application.readilyTake.inf;

import com.maxrocky.vesta.application.readilyTake.DTO.*;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * 管理平台随手拍
 * Created by Magic on 2017/6/9.
 */
public interface ReadilyTakeService {

    /**
     * Describe:安全app 查询随手拍列表
     * CreateBy:Magic
     * CreateOn:2017-06-09
     */
    List<ReadilyTakeListDTO> getReadilyTakeList(ReadilyTakeConditionDTO readilyTakeConditionDTO, WebPage webPage,UserInformationEntity userInformationEntity,List<String> agencyidList);

    /**
     * Describe:安全app 导出EXCEL
     * CreateBy:yuanyn
     * CreateOn:2017-07-14
     */
    void readilyTakeExcel(String title, String[] headers, ServletOutputStream out, ReadilyTakeConditionDTO readilyTakeConditionDTO, UserInformationEntity userInformationEntity,List<String> angencyIdList);

    /**
     * Describe:安全app 查询随手拍详情
     * CreateBy:yuanyn
     * CreateOn:2017-07-14
     */
    ReadilyTakeDetailDTO getReadilyTake(String patId);

    /**
     * Describe:安全app 查询人员权限
     * CreateBy:yuanyn
     * CreateOn:2017-07-17
     */
    ReadilyTakeRoleDTO getReadilyTakeRole(String id, String projectId);

    /**
     * Describe:安全app 修改整改单信息
     * CreateBy:yuanyn
     * CreateOn:2017-07-17
     */
    boolean updateReadilyTakeRole(String patId, String state,String supplementaryDescription);

    /**
     * Describe:安全app 修改整改描述
     * CreateBy:yuanyn
     * CreateOn:2017-07-17
     */
    boolean updateReadilyTake(ReadilyTakeRectifyDTO readilyTakeRectifyDTO, MultipartFile[] multipartFiles);

}
