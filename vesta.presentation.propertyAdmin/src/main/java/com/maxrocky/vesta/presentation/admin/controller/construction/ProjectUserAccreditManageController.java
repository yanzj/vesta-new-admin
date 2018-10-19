package com.maxrocky.vesta.presentation.admin.controller.construction;

import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.*;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.UserAccreditService;
import com.maxrocky.vesta.application.projectAccredit.inf.ProjectUserAccreditService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by yuanyn on 2018/3/26.
 */
@Controller
@RequestMapping(value = "/projectAccredit")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class ProjectUserAccreditManageController {

    @Autowired
    ProjectUserAccreditService projectuserAccreditService;
    @Autowired
    AuthAgencyService authAgencyService;


    //获取组织架构
    @RequestMapping(value = "getAllAgencyManage")
    public void getAllAgencyManage(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOS = projectuserAccreditService.getProjectAgencyListAll();
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOS).toString());
    }

    //根据权限获取组织架构
    @RequestMapping(value = "getAllAgencyManageById")
    public void getAllAgencyManageById(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<String> updateProject = projectuserAccreditService.getProjectAuthFunctionAndProjectIdByStaffId("007100050000",userInformationEntity.getStaffId(),"3");
        List<AgencyTreeDTO> agencyTreeDTOS = projectuserAccreditService.getProjectAgencyListAllByIds(updateProject);
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOS).toString());
    }


    //初始化授权首页
    @RequestMapping(value = "initProjectUserAccreditManage.html")
    public String initProjectUserAccreditManage(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                         @Valid AccreditManageDTO accreditManageDTO , Model model , WebPage webPage){
        webPage.setPageSize(20);
        List<AccreditManageDTO> accreditManageSize = projectuserAccreditService.getAccreditManageListByCondition(accreditManageDTO,null,userPropertystaffEntity);
        List<AccreditManageDTO> accreditManageDTOS = projectuserAccreditService.getAccreditManageListByCondition(accreditManageDTO,webPage,userPropertystaffEntity);
        webPage.setRecordCount(accreditManageSize.size());
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有 查询: esh40020091 授权: esh40020093 删除: esh40020094
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020091":
                        checkAuthFunctionDTO.setEsh40020091("Y");
                        break;
                }
            }
        }
        model.addAttribute("accreditManageDTO",accreditManageDTO);
        model.addAttribute("accreditManageDTOS",accreditManageDTOS);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/construction/projectUserAccreditManage/ProjectUserAccreditManage";
    }

    //人员批量授权
    @RequestMapping(value = "userProjectAccreditBatch.html")
    public String userProjectAccreditBatch(Model model,@RequestParam(value = "staffIdA", required = false) String staffIdA,
                                    @RequestParam(value = "staffNameA", required = false) String staffNameA){
        String flag = "0";
        if(null != staffIdA && !StringUtil.isEmpty(staffIdA)){
            model.addAttribute("staffIdA",staffIdA);
            flag = "1";
        }
        if(null != staffNameA && !StringUtil.isEmpty(staffNameA)){
            model.addAttribute("staffNameA",staffNameA);
        }
        model.addAttribute("flag",flag);
        return "/construction/projectUserAccreditManage/ProjectUserAccredit";
    }

    //用户授权管理
    @RequestMapping(value = "projectStaffAccreditBatch.html")
    public String staffAccreditBatch(){
        return "/construction/projectUserAccreditManage/ProjectUserAccreditBatchManage";
    }

    //删除人员角色授权
    @RequestMapping(value = "deleteProjectAccredit.html")
    public String deleteAccredit(@Valid AccreditManageDTO accreditManageDTO,
                                 Model model){
        projectuserAccreditService.deleteProjectAccredit(accreditManageDTO.getPeojectRoleUserIdA());
        model.addAttribute("accreditManageDTO",accreditManageDTO);
        return "forward:../projectAccredit/initProjectUserAccreditManage.html";
    }

    //获取组织架构关系（OA同步和外部合作）
    @RequestMapping(value = "getOAAgencyMessage")
    public void getOAAgencyMessage(HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = projectuserAccreditService.getProjectOAAgencyMessage();
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }

    //获取组织架构关系（OA同步和外部合作）只显示甲方
    @RequestMapping(value = "getOAAgencyMessageProject")
    public void getOAAgencyMessageProject(HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = projectuserAccreditService.getOAAgencyMessageProject();
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }

    //通过Id获取机构下的人
    @RequestMapping(value = "getProjectUserByAgencyId")
    public ApiResult getProjectUserByAgencyId(@RequestParam(value = "id")String agencyId){
        List<AgencyTreeDTO> agencyTreeDTOs = projectuserAccreditService.getUserByAgencyId(agencyId,"2");
        return new SuccessApiResult(agencyTreeDTOs);
    }

    //通过Id获取机构下的人
    @RequestMapping(value = "getAllUserByAgencyId")
    public ApiResult getAllUserByAgencyId(@RequestParam(value = "id")String agencyId,@RequestParam(value = "category")String category) {
        List<AgencyTreeDTO> agencyTreeDTOs = projectuserAccreditService.getUserByAgencyId(agencyId, category);
        return new SuccessApiResult(agencyTreeDTOs);
    }

    //通过Id获取项目层级下的角色
    @RequestMapping(value = "getProjectRoleByAgencyId")
    public ApiResult getProjectRoleByAgencyId(@RequestParam(value = "id")String agencyId){
        List<AgencyTreeDTO> agencyTreeDTOs = projectuserAccreditService.getProjectRoleByAgencyId(agencyId);
        return new SuccessApiResult(agencyTreeDTOs);
    }

    //通过Id获取项目层级下的甲方角色
    @RequestMapping(value = "getOwnerRoleByAgencyId")
    public ApiResult getOwnerRoleByAgencyId(@RequestParam(value = "id")String agencyId){
        List<AgencyTreeDTO> agencyTreeDTOs = projectuserAccreditService.getOwnerRoleByAgencyId(agencyId);
        return new SuccessApiResult(agencyTreeDTOs);
    }

    //模糊搜索人员
    @RequestMapping(value = "getUserByName")
    public ApiResult getUserByName(@RequestParam(value = "staffName") String staffName){
        List<UserOrRoleDTO> list = projectuserAccreditService.getUserByName(staffName,"2");
        return new SuccessApiResult(list);
    }

    //模糊搜索甲方人员
    @RequestMapping(value = "getOwnerUserByName")
    public ApiResult getOwnerUserByName(@RequestParam(value = "staffName") String staffName){
        List<UserOrRoleDTO> list = projectuserAccreditService.getOwnerUserByName(staffName);
        return new SuccessApiResult(list);
    }

    //模糊搜索人员  分类
    @RequestMapping(value = "getAllUserByName")
    public ApiResult getAllUserByName(@RequestParam(value = "staffName") String staffName,@RequestParam(value = "category") String category){
        List<UserOrRoleDTO> list = projectuserAccreditService.getUserByName(staffName,category);
        return new SuccessApiResult(list);
    }
    //模糊搜索角色
    @RequestMapping(value = "getRoleByName")
    public ApiResult getRoleByName(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                   @RequestParam(value = "roleName") String roleName){
        List<UserOrRoleDTO> list = projectuserAccreditService.getRolerByName(roleName);
        return new SuccessApiResult(list);
    }

    //根据项目层级id和角色名模糊搜索角色
    @RequestMapping(value = "getRoleByNameAndProjectId")
    public ApiResult getRoleByNameAndProjectId(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                               @RequestParam(value = "roleName") String roleName){
        List<String> updateProject = projectuserAccreditService.getProjectAuthFunctionAndProjectIdByStaffId("ESH40020093",userInformationEntity.getStaffId(),"4");
        List<UserOrRoleDTO> list = projectuserAccreditService.getRolerByName(roleName,updateProject);
        return new SuccessApiResult(list);
    }

    //根据项目层级id和角色名模糊搜索甲方角色
    @RequestMapping(value = "getOwnerRoleByNameAndProjectId")
    public ApiResult getOwnerRoleByNameAndProjectId(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                               @RequestParam(value = "roleName") String roleName){
        List<String> updateProject = projectuserAccreditService.getProjectAuthFunctionAndProjectIdByStaffId("ESH40020093",userInformationEntity.getStaffId(),"4");
        List<UserOrRoleDTO> list = projectuserAccreditService.getOwnerRolerByName(roleName,updateProject);
        return new SuccessApiResult(list);
    }

    //保存角色与人员关系
    @RequestMapping(value = "saveUserRoleRelation")
    public String saveUserRoleRelation(@Valid UserAndRoleRelationDTO userAndRoleRelationDTO){
        projectuserAccreditService.saveOrUpdateUserRoleRelation(userAndRoleRelationDTO);
        return "redirect:../projectAccredit/initProjectUserAccreditManage.html";
    }

    //保存角色与人员关系
    @ResponseBody
    @RequestMapping(value = "/saveUserRoleRelation_2")
    public ApiResult saveUserRoleRelation_2(@Valid UserModelDTO users) {
        List<UserProjectRoleAccreditDTO> userList = users.getUserProRole();
        return  projectuserAccreditService.saveOrUpdateUserRoleRelation_2(userList);
    }

}
