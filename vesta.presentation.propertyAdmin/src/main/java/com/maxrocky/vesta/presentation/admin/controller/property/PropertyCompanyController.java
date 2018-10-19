package com.maxrocky.vesta.presentation.admin.controller.property;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.dto.adminDTO.RoleRolesetDTO;
import com.maxrocky.vesta.application.inf.PropertyCompanyService;
import com.maxrocky.vesta.application.inf.RoleRoleService;
import com.maxrocky.vesta.application.inf.RoleRolesetService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/1/15.
 *  物业项目公司 控制层
 */
@Controller
@RequestMapping(value = "/property")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class PropertyCompanyController {
    /**
     * 物业项目公司 业务逻辑层接口
     */
    @Autowired
    private PropertyCompanyService propertyCompanyService;
    /**
     * 系统管理 权限逻辑层接口
     */
    @Autowired
    private RoleRoleService roleRoleService;

    @Autowired
    private RoleRolesetService roleRolesetService;
    /**
     * 初始化物业管理公司列表
     * @param
     * @return
     */
    @RequestMapping(value = "/PropertyCompany.html")
    public String gotoPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertySta,@Valid PropertyCompanyDTO propertyCompanySearchDTO,Model model,WebPage webPage){
        // 查询物业公司列表
        List<PropertyCompanyDTO> propertyCompanyMessage = propertyCompanyService.queryPropertyCompanyMessage(propertyCompanySearchDTO, webPage);
        model.addAttribute("propertyCompanyMessage", propertyCompanyMessage);

        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userPropertySta.getProjectId());
        model.addAttribute("propertyCompanyMap",propertyCompanyMap);
        // 搜索条件
        model.addAttribute("propertyCompanySearch",propertyCompanySearchDTO);
        return "/property/PropertyCompany";
    }

    /**
     *  初始化新增物业公司页面
     * @return
     */
    @RequestMapping(value = "/NewPropertyCompany")
    public String newPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userProperty,@Valid PropertyCompanyDTO propertyCompany,Model model){
        // 项目公司ID不为空则执行查询
        if(null != propertyCompany.getCompanyId() && !"".equals(propertyCompany.getCompanyId()) ){
            PropertyCompanyDTO propertyCompanyDTO = propertyCompanyService.queryPropertyCompanyById(propertyCompany.getCompanyId());
            model.addAttribute("propertyCompany",propertyCompanyDTO);
        }
        // 查询角色 下拉框
        List<RoleRolesetDTO> roleRolesetDTOs = roleRolesetService.listRoleset("2");
        model.addAttribute("roleRolesetDTOs",roleRolesetDTOs);

        // 查询 下拉框 区域 公司 项目 Map
        PropertyCompanyMapDTO propertyCompanyMap = propertyCompanyService.queryPropertyCompanyMap(userProperty.getProjectId());
        model.addAttribute("propertyCompanyMap",propertyCompanyMap);


        return "/property/NewPropertyCompany";
    }

    /**
     * 添加或修改物业公司及权限
     * @param propertyServicesDTO
     * @param userPropertystaffEntit
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/addPropertyCompany.html")
    public String addPropertyCompany(@Valid PropertyCompanyDTO propertyServicesDTO,@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntit, Model model,WebPage webPage){
        propertyCompanyService.addORupdatePropertyCompany(propertyServicesDTO, userPropertystaffEntit);
        return "redirect:/property/PropertyCompany.html";
    }

    /**
     * 判断是删除信息或更新状态
     * 根据信息ID
     * 启用(type 0)
     * 禁用(type 1)
     * 删除(type 2)
     * @param userPropertystaffEnt
     * @param companyId
     * @param propertyType
     * @return
     */
    @RequestMapping(value = "/removeORupdatePropertyCompany")
    public ApiResult removePropertyServices(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEnt ,@RequestParam String companyId,@RequestParam String propertyType)
    {
        String propertyCompany = propertyCompanyService.removeORupdatePropertyServices(userPropertystaffEnt,companyId,propertyType);
        return new SuccessApiResult(propertyCompany);
    }

    /**
     * 验证当前 管理员是否已分配负责项目
     * @param projectAdmin
     * @return
     */
    @RequestMapping(value = "/whetherAreAdmin")
    public ApiResult whetherAre(@RequestParam String projectAdmin)
    {
        String propertyCompany = propertyCompanyService.whetherAreAdmin(projectAdmin);
        return new SuccessApiResult(propertyCompany);
    }

    /**
     * 公司
     * 项目
     * 联动
     * @param propertyDrop
     * @return
     */
    @RequestMapping(value = "/propertyDropBox")
    public ApiResult removePropertyServices(@Valid PropertyDropBoxDTO propertyDrop)
    {
        // 公司下拉框
        if(!"".equals(propertyDrop.getCompanyName()) && null != propertyDrop.getCompanyName()){
            List<HouseCompanyDTO> houseCompany = propertyCompanyService.getHouseCompany(propertyDrop.getCompanyName());
            return new SuccessApiResult(houseCompany);
        }
        // 项目下拉框
        if(!"".equals(propertyDrop.getPojectName()) && null != propertyDrop.getPojectName()){
            List<HouseProjectDTO> ouseProject = propertyCompanyService.getHouseProject(propertyDrop.getPojectName());
            return new SuccessApiResult(ouseProject);
        }
        return new SuccessApiResult(null);
    }


}
