package com.maxrocky.vesta.presentation.admin.controller.property;

import com.maxrocky.vesta.application.DTO.PropertyAnnouncementPageDTO;
import com.maxrocky.vesta.application.DTO.PropertyCompanyMapDTO;
import com.maxrocky.vesta.application.inf.PropertyAnnouncementService;
import com.maxrocky.vesta.application.inf.PropertyCompanyService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/1/25.
 * 物业公告 控制层
 */
@Controller
@RequestMapping(value = "/property")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class PropertyAnnouncementController {

    /**
     * 物业项目公司 业务逻辑层接口
     */
    @Autowired
    private PropertyCompanyService propertyCompanyService;
    /**
     * 物业公告管理 业务逻辑层接口
     */
    @Autowired
    private PropertyAnnouncementService propertyAnnouncementService;
    /**
     * 初始化物业公告管理列表
     * 初始项目下拉框
     * @param propertyAnnouncement
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/PropertyAnnouncement.html")
    public String gotoPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaff,@Valid PropertyAnnouncementPageDTO propertyAnnouncement,Model model,WebPage webPage){
        // 初始化 登陆人查询范围
        propertyAnnouncement.setQueryScope(userPropertystaff.getQueryScope());
        // 默认登录人下拉框
        propertyAnnouncement.setProject(userPropertystaff.getProjectId());
        List<PropertyAnnouncementPageDTO> propertyAnnouncementList = propertyAnnouncementService.queryPropertyAnnouncement(propertyAnnouncement, webPage);
        model.addAttribute("propertyAnnouncement",propertyAnnouncementList);
        // 查询 下拉框 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userPropertystaff.getProjectId());
        model.addAttribute("propertyCompanyMap", propertyCompanyMap);
        // 保存搜索条件
        model.addAttribute("propertyAnnouncementSearch",propertyAnnouncement);
        return "/property/PropertyAnnouncement";
    }

    /**
     *  根据物业公告信息ID 删除信息
     * @param userPropertystaffEntity
     * @param announcementId
     * @return
     */
    @RequestMapping(value = "/removePropertyAnnouncementById")
    public ApiResult removePropertyAnnouncement(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity ,@RequestParam String announcementId)
    {
        String propertyAnnouncement = propertyAnnouncementService.removePropertyServicesById(announcementId, userPropertystaffEntity);
        return new SuccessApiResult(propertyAnnouncement);
    }


    /**
     * 初始化物业公告 新增 或修改页面
     * @param propertyAnnouncement
     * @param model
     * @return
     */
    @RequestMapping(value = "/NewPropertyAnnouncement")
    public String newPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userProperty,@Valid PropertyAnnouncementPageDTO propertyAnnouncement,Model model){

        //公告ID不为空 (根据物业公告ID查询详情)
        if(null != propertyAnnouncement.getAnnouncementId() && !"".equals(propertyAnnouncement.getAnnouncementId())){
            PropertyAnnouncementPageDTO propertyAnnouncementMsg = propertyAnnouncementService.queryPropertyAnnouncementById(propertyAnnouncement.getAnnouncementId());
            model.addAttribute("propertyAnnouncement", propertyAnnouncementMsg);
        }

        // 初始下拉框
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userProperty.getProjectId());
        model.addAttribute("propertyCompanyMap", propertyCompanyMap);
        // 默认当前登录人项目
        model.addAttribute("project",userProperty.getProjectId());
        return  "property/NewPropertyAnnouncement";
    }

    /**
     * 物业公告 添加或修改
     * @param propertyAnnouncementPage
     * @param userPropertystaffEntit
     * @param model
     * @return
     */
    @RequestMapping(value = "/addPropertyAnnouncement")
    public ApiResult addPropertyServices(@Valid PropertyAnnouncementPageDTO propertyAnnouncementPage,@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntit, Model model){

        boolean propertyAnnouncement = propertyAnnouncementService.saveORupdatePropertyAnnouncement(userPropertystaffEntit,propertyAnnouncementPage);

        return new SuccessApiResult(propertyAnnouncement);
    }

}
