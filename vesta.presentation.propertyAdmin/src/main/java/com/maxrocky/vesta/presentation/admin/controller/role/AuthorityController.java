package com.maxrocky.vesta.presentation.admin.controller.role;


import com.maxrocky.vesta.application.dto.adminDTO.RoleRoleDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleRoleMouldDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleRolesetDTO;
import com.maxrocky.vesta.application.dto.adminDTO.StaffNameDTO;
import com.maxrocky.vesta.application.inf.RoleRoleService;
import com.maxrocky.vesta.application.inf.RoleRolesetService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhanghj on 2016/1/18.
 */

@Controller
@RequestMapping(value = "/role")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})

public class AuthorityController {
    @Autowired
    private RoleRolesetService roleRolesetService;

    @Autowired
    private RoleRoleService roleRoleService;



    /**
     * 初始化跳转到角色列表
     * @return
     */
    @RequestMapping(value = "/AuthorityManage.html",method = RequestMethod.GET)
    public String roleRoleSet(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid RoleRolesetDTO roleRolesetDTO,Model model,WebPage webPage){

        List<RoleRolesetDTO> roleRolesetDTOList = roleRolesetService.listRoleset(webPage,"",roleRolesetDTO.getRoledesc());
        model.addAttribute("roleRolesetDTOList", roleRolesetDTOList);
        model.addAttribute("setDto", roleRolesetDTO);
        return "/role/AuthorityManage";
    }

    /**
     * 跳转到角色列表2
     * @return
     */
    @RequestMapping(value = "/AuthorityManage2",method = RequestMethod.GET)
    public String roleRoleSet2(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid RoleRolesetDTO roleRolesetDTO,Model model,WebPage webPage){

        List<RoleRolesetDTO> roleRolesetDTOList = roleRolesetService.listRoleset(webPage,"","");
        model.addAttribute("roleRolesetDTOList", roleRolesetDTOList);
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+roleRolesetDTOList.size());

//        List<RoleRoleDTO> roleRoleDTOs = roleRolesetService.listRoleSetMap("0002","7");
//        model.addAttribute("roleRoleDTOs",roleRoleDTOs);
        return "/role/AuthorityManage";
    }

    /**
     * 跳转到添加角色页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoAddRole",method = RequestMethod.GET)
    public  String gotoAddRole(Model model){
        System.out.println("-----------------新增角色-----------------");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("date",format.format(new Date()));
        return "/role/AddRoleRoleset";
    }

    /**
     * 添加新角色，并返回角色列表
     * @param userPropertystaffEntity
     * @param roleRolesetDTO
     * @return
     */
    @RequestMapping(value = "AddRoleRoleset",method = RequestMethod.POST)
    public ModelAndView addRoleRoleset(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid RoleRolesetDTO roleRolesetDTO,Model model){
        roleRolesetDTO.setOperator(userPropertystaffEntity.getStaffName());
        roleRolesetService.saveRoleSet(roleRolesetDTO);
//        String result = "";
//        if (addResult){
//            result = "添加成功。";
//        }
//        else {
//            result = "角色名已存在，请重新添加。";
//        }
//        model.addAttribute("addResult",result);
        //return new ModelAndView("redirect:/role/AuthorityManage.html");直接返回角色列表页，返回类型要改成ModelAndView
        // return "/role/AddRoleRoleset";
        return new ModelAndView("redirect:/role/AuthorityManage2");
    }

    /**
     * 删除角色
     * @param rolesetId
     * @return
     */
    @RequestMapping(value = "DelRoleRoleset")
    public ModelAndView delRoleRoleset(@Param String rolesetId){
        RoleRolesetDTO roleRolesetDTO = new RoleRolesetDTO();
        roleRolesetDTO.setSetId(rolesetId);
        roleRolesetService.deleteRoleset(roleRolesetDTO);
        return new ModelAndView("redirect:/role/AuthorityManage2");
    }

    /**
     * 列举所有权限及已有权限
     * @param rolesetId
     * @param model
     * @return
     */
    @RequestMapping(value = "listRoleRole.html")
    public String gotoListRole(@Param String rolesetId,Model model){

        List<RoleRoleMouldDTO> roleRoleMouldDTOs = roleRoleService.getGroup();//获取权限分类模块

        List<RoleRoleDTO> roleRoleDTOs = roleRolesetService.listRoleSetMap(rolesetId);
        RoleRolesetDTO roleRolesetDTO = roleRolesetService.getRolesetById(rolesetId);

        model.addAttribute("roleRoleMouldDTOs", roleRoleMouldDTOs);
        model.addAttribute("roleRoleDTOs", roleRoleDTOs);
        model.addAttribute("rolesetId", rolesetId);
        model.addAttribute("roleRoleSet",roleRolesetDTO);
        return "/role/AuthorityList";
    }

    /**
     * 更新角色权限
     * @param checkbox
     * @param rolesetId
     * @return
     */
    @RequestMapping(value = "updateRoleMap")
    public ModelAndView updateRoleMap(@Param String checkbox,@Param String rolesetId){
        int i = roleRolesetService.updateRoleMap(rolesetId, checkbox);
        System.out.println(i);
        return new ModelAndView("redirect:/role/AuthorityManage2");
    }

    /**
     * 跳到角色新增编辑页面
     * @param model
     * @param rolesetId
     * @return
     */
    @RequestMapping(value = "/gotoUpdateRole",method = RequestMethod.GET)
    public  String gotoUpdateRole(Model model,@RequestParam(value = "rolesetId",required = false) String rolesetId){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        RoleRolesetDTO roleRolesetDTO = new RoleRolesetDTO();
        List<RoleRoleDTO> roleRoleDTOs = new ArrayList<RoleRoleDTO>();
        List<RoleRoleMouldDTO> roleRoleMouldDTOs = roleRoleService.getGroup();//获取权限分类模块
        if(!StringUtil.isEmpty(rolesetId)){
            roleRolesetDTO = roleRolesetService.getRolesetById(rolesetId);
        }
        roleRoleDTOs = roleRolesetService.listRoleSetMap(rolesetId);
        model.addAttribute("date", format.format(new Date()));
        model.addAttribute("roleRoleSet",roleRolesetDTO);
        model.addAttribute("roleRoleMouldDTOs", roleRoleMouldDTOs);
        model.addAttribute("roleRoleDTOs", roleRoleDTOs);
        return "/role/UpdateRoleRoleset";
    }

    /**
     * 编辑角色并返回列表页
     * @param userPropertystaffEntity
     * @param roleRolesetDTO
     *
     * @return
     */
    @RequestMapping(value = "UpdateRoleRoleset.html")
    public ModelAndView updateRoleRoleset(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid RoleRolesetDTO roleRolesetDTO){
        if(StringUtil.isEmpty(roleRolesetDTO.getSetId())){   //如果角色ID为空 则为新增
            roleRolesetDTO.setSetId(IdGen.uuid());
            roleRolesetService.saveRoleSet(roleRolesetDTO);
        }else {    //否则为编辑
            roleRolesetService.updateRoleset(roleRolesetDTO);
        }
        roleRolesetService.updateRoleMap(roleRolesetDTO.getSetId(),roleRolesetDTO.getJsonStr());
        return new ModelAndView("redirect:/role/AuthorityManage2");
    }

    /**
     * 跳转后台角色添加员工
     * */
    @RequestMapping(value = "/goToAdminRole.html")
    public String goToAdminRole(@Valid StaffNameDTO staffNameDTO, Model model){
        List<StaffNameDTO> staffNameDTOs = roleRolesetService.getOutStaffs(staffNameDTO);  //获取该角色外的用户列表
        List<StaffNameDTO> staffNameDTOList = roleRolesetService.getInStaffs(staffNameDTO.getRoleSetId());  //获取角色内的用户列表
        RoleRolesetDTO roleRolesetDTO = roleRolesetService.getRolesetById(staffNameDTO.getRoleSetId());  //角色详情
        model.addAttribute("staffs",staffNameDTOs);
        model.addAttribute("roleSet",roleRolesetDTO);
        model.addAttribute("staffList", staffNameDTOList);
        return "/role/AdminRoleSet";
    }

    /**保存人员 后台角色关系*/
    @RequestMapping(value = "/saveAdminRoleSet")
    public ApiResult saveRoleSet(@Valid StaffNameDTO staffNameDTO){
        roleRolesetService.saveRoleMap(staffNameDTO.getRoleSetId(),staffNameDTO.getStaffId());
        return new SuccessApiResult();
    }

    /**
     * 删除人员 后台角色关系
     * */
    @RequestMapping(value = "delAdminRoleSet")
    public ApiResult delAdminRoleSet(@Valid StaffNameDTO staffNameDTO){
        roleRolesetService.delRoleMap(staffNameDTO.getRoleSetId(),staffNameDTO.getStaffId());
        return new SuccessApiResult();
    }
}
