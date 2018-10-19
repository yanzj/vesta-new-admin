package com.maxrocky.vesta.presentation.admin.controller.role;


import com.maxrocky.vesta.application.DTO.admin.UserPropertystaffDTO;
import com.maxrocky.vesta.application.admin.dto.TransfersDto;
import com.maxrocky.vesta.application.dto.adminDTO.RoleRolesetDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleStaffUserDTO;
import com.maxrocky.vesta.application.inf.AnnouncementService;
import com.maxrocky.vesta.application.inf.MemberAuthorityService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.domain.model.RoleRolesetEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员权限管理_Controller
 * Created by WeiYangDong on 2016/8/4.
 */
@Controller
@RequestMapping(value = "/memberAuthority")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class MemberAuthorityController {

    @Autowired
    MemberAuthorityService memberAuthorityService;

    @Autowired
    AnnouncementService announcementService;

    @Autowired
    StaffUserService staffUserService;

    /**
     * 校验用户是否可以操作该角色(权限范围)
     */
    @ResponseBody
    @RequestMapping(value = "/checkEdit/{rolesetId}/{menuId}")
    public Map<String,Object> checkEdit(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @PathVariable(value = "rolesetId")String rolesetId,
                                        @PathVariable(value = "menuId")String menuId){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //用户权限项目范围
            List<Map<String, Object>> roleProjectList = staffUserService.getProjectScopeByStaffId(userPropertystaffEntity.getStaffId(), menuId);
            //角色数据查看范围项目范围
            List<Map<String, Object>> announcementProjectList = memberAuthorityService.getRoleScopeById(rolesetId);
            for (Map<String,Object> announcementProject : announcementProjectList){
                int flag = 0;
                String projectId = announcementProject.get("projectId").toString();
                for (Map<String,Object> roleProject : roleProjectList){
                    if (projectId.equals(roleProject.get("projectId").toString())){
                        flag = 1;
                    }
                }
                if (flag != 1){
                    resultMap.put("error",flag);
                    return resultMap;
                }
            }
            resultMap.put("error",1);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * 获取角色管理列表信息
     */
    @RequestMapping(value = "/roleList.html")
    public ModelAndView roleList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                 RoleRolesetDTO roleRolesetDTO,
                                 Model model,WebPage webPage){
        try{
//            List<Map<String, Object>> cityList = staffUserService.listCity();
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(), roleRolesetDTO.getMenuId());
            model.addAttribute("city", cityList);
            if (null != roleRolesetDTO.getMenuId()){
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), roleRolesetDTO.getScopeId(),roleRolesetDTO.getMenuId());
                if(null != projectList && projectList.size() > 0){
                    if (projectList.get(0)[0].equals("0")){
                        projectList.remove(0);
                    }
                }
                model.addAttribute("projectList",projectList);
            }
            //获取数据范围权限
//            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
//            roleRolesetDTO.setRoleScopeList(roleScopeList);
            List<Map<String, Object>> rolesetList = memberAuthorityService.getMemberRolesetList(roleRolesetDTO, webPage);
            model.addAttribute("isExcel", rolesetList.size());

            webPage.setPageSize(20);
            webPage.setPageIndex(webPage.getPageIndex());
            webPage.setRecordCount(rolesetList.size());

            if (rolesetList.size() < 20){
                //List长度不足20无需分页
                model.addAttribute("roleRolesetDTOs",rolesetList);
            }else{
                //List长度大于20手动分页
                int fromIndex = (webPage.getPageIndex() - 1) * 20;
                int toIndex = (webPage.getPageIndex() - 1) * 20 + 20;
                if (toIndex > rolesetList.size()){
                    model.addAttribute("roleRolesetDTOs",rolesetList.subList(fromIndex,rolesetList.size()));
                }else{
                    model.addAttribute("roleRolesetDTOs",rolesetList.subList(fromIndex,toIndex));
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error", "查询出现异常请联系管理员");
            return new ModelAndView("/role/MemberRoleList");
        }
        return new ModelAndView("/role/MemberRoleList");
    }

    /**
     * 去新建角色页面
     */
    @RequestMapping(value = "/toCreateRole.html")
    public ModelAndView toCreateRole(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                     @Valid String menuId,@Valid RoleRolesetDTO roleRolesetDTO,
                                     Model model){
        //查询所有CRM项目城市列表
//        List<Object[]> objects = this.announcementService.listCity();
//        List<TransfersDto> city = new ArrayList<TransfersDto>();
//        city.add(new TransfersDto("0", "全部城市"));
//        for (Object[] object : objects) {
//            String cid = object[0].toString();
//            String name = object[1].toString();
//            city.add(new TransfersDto(cid, name));
//        }
//        model.addAttribute("city", city);
        if (null != roleRolesetDTO.getSetId() && !"".equals(roleRolesetDTO.getSetId())){
            RoleRolesetDTO rolesetDTO = memberAuthorityService.getRoleById(roleRolesetDTO.getSetId());
            model.addAttribute("RoleRolesetDTO",rolesetDTO);
        }
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),menuId);
        model.addAttribute("city",cityList);
        model.addAttribute("error","0");
        return new ModelAndView("/role/MemberRoleSave");
    }

    /**
     * 去设置会员角色操作权限(保存角色及操作数据范围)
     */
    @RequestMapping(value = "/toSetRoleMenu.html",method = RequestMethod.POST)
    public ModelAndView toSetRoleMenu(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                      @Valid RoleRolesetDTO roleRolesetDTO,Model model){
        try{
            //设置操作人
            roleRolesetDTO.setOperator(userPropertystaffEntity.getStaffName());
            //#1保存角色(设置setState状态为0-失效)
            RoleRolesetEntity roleRolesetEntity = memberAuthorityService.saveOrUpdateRole(roleRolesetDTO);
            //#2保存角色操作数据范围(设置nowState状态为0-不启用)
            //设置角色Id
            roleRolesetDTO.setSetId(roleRolesetEntity.getSetId());
            memberAuthorityService.saveOrUpdateRoleScope(roleRolesetDTO);
            //传递角色Id
            model.addAttribute("setId",roleRolesetEntity.getSetId());
            //传递会员菜单列表
            List<Object> memberMenuList = memberAuthorityService.getMemberMenuList(null);
            model.addAttribute("memberMenuList",memberMenuList);
        }catch (Exception e){
            e.printStackTrace();
            //角色新建失败
            model.addAttribute("error","1");
            model.addAttribute("RoleRolesetDTO",roleRolesetDTO);
            //查询所有CRM项目城市列表
            List<Object[]> objects = this.announcementService.listCity();
            List<TransfersDto> city = new ArrayList<TransfersDto>();
            city.add(new TransfersDto("0", "全部城市"));
            for (Object[] object : objects) {
                String cid = object[0].toString();
                String name = object[1].toString();
                city.add(new TransfersDto(cid, name));
            }
            model.addAttribute("city", city);
            return new ModelAndView("/role/MemberRoleSave");
        }
        return new ModelAndView("/role/MemberRoleSet");
    }

    /**
     * 保存会员角色菜单操作权限
     */
    @RequestMapping(value = "/saveRoleMenu",method= RequestMethod.POST)
    public Map<String,Object> saveRoleMenu(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                           @Valid RoleRolesetDTO roleRolesetDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            roleRolesetDTO.setOperator(userPropertystaffEntity.getStaffName());
            memberAuthorityService.saveOrUpdateRoleMenu(roleRolesetDTO);
            resultMap.put("error", 0);//保存成功
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", 1);//保存失败
        }
        return resultMap;
    }

    /**
     * 通过角色Id删除角色信息
     */
    @ResponseBody
    @RequestMapping(value = "deleteRole",method = RequestMethod.POST)
    public Map<String,Object> deleteRoleById(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                             @Valid RoleRolesetDTO roleRolesetDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //删除角色相关信息,确保该角色下无成员
            if (false){
                resultMap.put("error",2);//该角色下有绑定成员,无法删除
            }else{
                memberAuthorityService.deleteRoleById(roleRolesetDTO);
                resultMap.put("error",0);//删除成功
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", 1);
        }
        return resultMap;
    }

    /**
     * 去编辑角色设置页面
     */
    @RequestMapping(value = "toEditRoleSet")
    public ModelAndView toEditRoleSet(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                      @Valid RoleRolesetDTO roleRolesetDTO,
                                      @Valid String menuId,
                                      Model model){
        try{
            //查询所有CRM项目城市列表
//            List<Object[]> objects = this.announcementService.listCity();
//            List<TransfersDto> city = new ArrayList<TransfersDto>();
//            city.add(new TransfersDto("0", "全部城市"));
//            for (Object[] object : objects) {
//                String cid = object[0].toString();
//                String name = object[1].toString();
//                city.add(new TransfersDto(cid, name));
//            }
//            model.addAttribute("city", city);
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),menuId);
            model.addAttribute("city", cityList);
            //查询会员菜单列表
            List<Object> memberMenuList = memberAuthorityService.getMemberMenuList(roleRolesetDTO.getSetId());
            model.addAttribute("memberMenuList",memberMenuList);
            //获取角色详情
            RoleRolesetDTO rolesetDTO = memberAuthorityService.getRoleById(roleRolesetDTO.getSetId());
            model.addAttribute("RoleRolesetDTO",rolesetDTO);
            //角色Id
            model.addAttribute("setId",roleRolesetDTO.getSetId());
        }catch (Exception e){
            e.printStackTrace();
            return new ModelAndView("redirect:/memberAuthority/roleList.html");
        }
        return new ModelAndView("/role/MemberRoleUpdate");
    }

    /**
     * 保存编辑及另存为角色设置
     */
    @RequestMapping(value = "saveEditRoleSet",method = RequestMethod.POST)
    public Map<String,Object> saveEditRoleSet(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                              @Valid RoleRolesetDTO roleRolesetDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //设置操作人
            roleRolesetDTO.setOperator(userPropertystaffEntity.getStaffName());
            //#1更新或另存为_角色
            RoleRolesetEntity roleRolesetEntity = memberAuthorityService.saveOrUpdateRole(roleRolesetDTO);
            //#2更新或另存为_角色操作数据范围
            roleRolesetDTO.setSetId(roleRolesetEntity.getSetId());
            memberAuthorityService.saveOrUpdateRoleScope(roleRolesetDTO);
            //#3更新或另存为_角色菜单操作级别
            memberAuthorityService.saveOrUpdateRoleMenu(roleRolesetDTO);
            resultMap.put("error", "0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "1");
        }
        return resultMap;
    }

    /**
     * 通过角色名称模糊查询角色列表
     */
    @ResponseBody
    @RequestMapping(value = "/searchRoles")
    public Map<String,Object> searchRoles(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                          @Valid RoleRolesetDTO roleRolesetDTO){
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
        roleRolesetDTO.setRoleScopeList(roleScopeList);

        Map<String,Object> resultMap = new HashMap<>();
        try{
            List<Map<String, Object>> roleList = memberAuthorityService.getRolesByRoledesc(roleRolesetDTO);
            if (roleList.size() == 0){
                Map<String,Object> role = new HashMap<>();
                role.put("setId","0");
                role.put("roledesc","查无结果项...");
                roleList.add(role);
            }
            resultMap.put("roleList",roleList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 角色成员管理-成员列表
     */
    @RequestMapping(value = "/roleMemberList")
    public ModelAndView roleMemberList(@Valid RoleStaffUserDTO userPropertystaffDTO,WebPage webPage,Model model){
        try{
            model.addAttribute("userPropertystaffDTO", userPropertystaffDTO);

            List<Map<String, Object>> staffUserList = memberAuthorityService.getStaffUserListByRoleId(userPropertystaffDTO, webPage);
            model.addAttribute("staffUserList",staffUserList);

            webPage.setPageSize(10);
            webPage.setPageIndex(webPage.getPageIndex());
            webPage.setRecordCount(memberAuthorityService.getStaffUserCountByRoleId(userPropertystaffDTO).intValue());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("/role/RoleMemberList");
    }

    /**
     * 通过用户Id和角色Id删除用户角色关系信息
     */
    @ResponseBody
    @RequestMapping(value = "/delMemberRole")
    public Map<String,Object> delMemberRole(@Valid RoleStaffUserDTO userPropertystaffDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            int flag = memberAuthorityService.delMemberRole(userPropertystaffDTO);
            if (flag != 0){
                resultMap.put("error","0");
            }else{
                resultMap.put("error","1");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "1"); //删除失败
        }
        return resultMap;
    }

    /**
     * 角色成员管理-去到新增角色成员页面
     */
    @RequestMapping(value = "/toAddRoleMember")
    public ModelAndView toAddRoleMember(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @Valid UserPropertystaffDTO userPropertystaffDTO,WebPage webPage,Model model){
        try{
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId());
            model.addAttribute("city", cityList);
            if (null != userPropertystaffDTO.getScopeId() && !"".equals(userPropertystaffDTO.getScopeId())) {
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), userPropertystaffDTO.getScopeId());
                model.addAttribute("projectList", projectList);
            }
            List<Map<String, Object>> staffUserList = staffUserService.getStaffUserList(userPropertystaffDTO, webPage);
            model.addAttribute("staffUserList",staffUserList);
            webPage.setPageSize(20);
            webPage.setPageIndex(webPage.getPageIndex());
            webPage.setRecordCount(staffUserService.getStaffUserCount(userPropertystaffDTO).intValue());

            model.addAttribute("userPropertystaffDTO",userPropertystaffDTO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("/role/RoleMemberAdd");
    }

    /**
     * 角色成员管理-批量保存角色成员
     */
    @ResponseBody
    @RequestMapping(value = "/saveRoleMember",method = RequestMethod.POST)
    public Map<String,Object> saveRoleMember(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                             @Valid RoleStaffUserDTO userPropertystaffDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            memberAuthorityService.saveRoleMember(userPropertystaffDTO);
            resultMap.put("error", "0");//批量保存成功
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "1");//批量保存失败
        }
        return resultMap;
    }

    /**
     * 校验用户菜单操作级别
     */
    @ResponseBody
    @RequestMapping(value = "/checkStaffMenuOperationLevel/{menuId}",method= RequestMethod.GET)
    public Map<String,Object> checkStaffMenuOperationLevel(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                           @PathVariable(value = "menuId")String menuId){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //0:仅查看,1:所有操作
            int flag = memberAuthorityService.checkStaffMenuOperationLevel(userPropertystaffEntity.getStaffId(), menuId);
            resultMap.put("error",flag);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);//异常
        }
        return resultMap;
    }

    /**
     * 角色名称重复校验
     */
    @ResponseBody
    @RequestMapping(value = "/checkRoledesc/{roledesc:.+}",method= RequestMethod.GET)
    public Map<String,Object> checkRoledesc(@PathVariable(value = "roledesc")String roledesc){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            int count = memberAuthorityService.checkRoledesc(roledesc);
            resultMap.put("error",0);
            resultMap.put("count",count);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);//异常
        }
        return resultMap;
    }

    /**
    *  @Author: shanshan
    *  @Date:
    *  @Description: 导出excel
    */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public String exportExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                              @Valid RoleRolesetDTO roleRolesetDTO,
                              HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        try {
            List<Map<String, Object>> rolesetList = memberAuthorityService.getMemberRolesetList(roleRolesetDTO, null);
            memberAuthorityService.exportExcel(rolesetList, httpServletResponse, httpServletRequest);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }

    }

    /*-------TEST-------*/
    @RequestMapping(value = "/aaaa")
    public ModelAndView aaa(Model model){

        List<Object> memberMenuList = memberAuthorityService.getMemberMenuList(null);
        model.addAttribute("memberMenuList",memberMenuList);
        return new ModelAndView("/role/MemberRoleSet");
    }



    @RequestMapping(value = "/cccc",method = RequestMethod.POST)
    public ModelAndView ccc(HttpServletRequest request, @RequestParam("set") List<Object> set){
        int size = set.size();


        return new ModelAndView("/role/MemberRoleSet");
    }


}
