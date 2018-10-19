package com.maxrocky.vesta.presentation.admin.controller.role;

import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.*;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.UserAccreditService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import net.sf.json.JSONArray;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * Created by hp on 2017/12/21.
 */


@Controller
@RequestMapping(value = "/userAccredit")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class UserAccreditController {

    @Autowired
    UserAccreditService userAccreditService;
    @Autowired
    AuthAgencyService authAgencyService;


    //获取组织架构
    @RequestMapping(value = "getAllAgencyManage")
    public void getAllAgencyManage(HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOS = userAccreditService.getAgencyListAll();
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOS).toString());
    }

    //根据权限获取组织架构
    @RequestMapping(value = "getAllAgencyManageById")
    public void getAllAgencyManageById(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
//        List<String> updateProject = authAgencyService.getAuthFunctionAndProjectIdByStaffId("STH40020026",userInformationEntity.getStaffId(),"4");
        List<String> updateProject = authAgencyService.getAuthFunctionAndProjectIdByStaffId("007000050000",userInformationEntity.getStaffId(),"3");
        List<AgencyTreeDTO> agencyTreeDTOS = userAccreditService.getAgencyListAll(updateProject);
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOS).toString());
    }

    //初始化授权首页
    @RequestMapping(value = "initUserAccreditManage.html")
    public String initUserAccreditManage(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                         @Valid AccreditManageDTO accreditManageDTO , Model model , WebPage webPage){
        webPage.setPageSize(20);
        List<AccreditManageDTO> accreditManageSize = userAccreditService.getAccreditManageListByCondition(accreditManageDTO,null,userPropertystaffEntity);
        List<AccreditManageDTO> accreditManageDTOS = userAccreditService.getAccreditManageListByCondition(accreditManageDTO,webPage,userPropertystaffEntity);
        webPage.setRecordCount(accreditManageSize.size());
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function=authAgencyService.getAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","3");
        if(function!=null){
            //校验是否有 查询: sth40020024  批量授权: sth40020025 授权: sth40020026 删除: sth40020027
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "STH40020024":
                        checkAuthFunctionDTO.setSth40020024("Y");
                        break;
                    case "STH40020025":
                        checkAuthFunctionDTO.setSth40020025("Y");
                        break;
                }
            }
        }
        model.addAttribute("accreditManageDTO",accreditManageDTO);
        model.addAttribute("accreditManageDTOS",accreditManageDTOS);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/authagency/UserAccreditManage";
    }

    //人员批量授权（废弃）
    @RequestMapping(value = "userAccreditBatch.html")
    public String userAccreditBatch(Model model,@RequestParam(value = "staffIdA", required = false) String staffIdA,
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
        return "/authagency/UserAccreditBatch";
    }

    //用户授权管理
    @RequestMapping(value = "staffAccreditBatch.html")
    public String staffAccreditBatch(){
        return "/authagency/UserAccreditBatchManage";
    }

    //删除人员角色授权
    @RequestMapping(value = "deleteAccredit.html")
    public String deleteAccredit(@Valid AccreditManageDTO accreditManageDTO,
                                 Model model){
        userAccreditService.deleteAccredit(accreditManageDTO.getPeojectRoleUserIdA());
        model.addAttribute("accreditManageDTO",accreditManageDTO);
        return "forward:../userAccredit/initUserAccreditManage.html";
    }

    //获取组织架构关系（OA同步和外部合作）
    @RequestMapping(value = "getOAAgencyMessage")
    public void getOAAgencyMessage(HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = userAccreditService.getOAAgencyMessage("3");
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }

    //获取组织架构关系（OA同步和外部合作）
    @RequestMapping(value = "getAllOAAgencyMessage")
    public void getAllOAAgencyMessage(HttpServletResponse response, @RequestParam(value = "category") String category) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = userAccreditService.getOAAgencyMessage(category);
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }

    //通过Id获取机构下的人
    @RequestMapping(value = "getUserByAgencyId")
    public ApiResult getUserByAgencyId(@RequestParam(value = "id")String agencyId){
        List<AgencyTreeDTO> agencyTreeDTOs = userAccreditService.getUserByAgencyId(agencyId,"3");
        return new SuccessApiResult(agencyTreeDTOs);
    }

    //通过Id获取机构下的人
    @RequestMapping(value = "getAllUserByAgencyId")
    public ApiResult getAllUserByAgencyId(@RequestParam(value = "id")String agencyId,@RequestParam(value = "category")String category){
        List<AgencyTreeDTO> agencyTreeDTOs = userAccreditService.getUserByAgencyId(agencyId,category);
        return new SuccessApiResult(agencyTreeDTOs);
    }
    //通过Id获取项目层级下的角色
    @RequestMapping(value = "getRoleByAgencyId")
    public ApiResult getRoleByAgencyId(@RequestParam(value = "id")String agencyId){
        List<AgencyTreeDTO> agencyTreeDTOs = userAccreditService.getRoleByAgencyId(agencyId);
        return new SuccessApiResult(agencyTreeDTOs);
    }

    //模糊搜索人员
    @RequestMapping(value = "getUserByName")
    public ApiResult getUserByName(@RequestParam(value = "staffName") String staffName){
        List<UserOrRoleDTO> list = userAccreditService.getUserByName(staffName,"3");
        return new SuccessApiResult(list);
    }

    //模糊搜索人员  分类
    @RequestMapping(value = "getAllUserByName")
    public ApiResult getAllUserByName(@RequestParam(value = "staffName") String staffName,@RequestParam(value = "category") String category){
        List<UserOrRoleDTO> list = userAccreditService.getUserByName(staffName,category);
        return new SuccessApiResult(list);
    }
    //模糊搜索角色
    @RequestMapping(value = "getRoleByName")
    public ApiResult getRoleByName(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                   @RequestParam(value = "roleName") String roleName){
        List<UserOrRoleDTO> list = userAccreditService.getRolerByName(roleName);
        return new SuccessApiResult(list);
    }

    //根据项目层级id和角色名模糊搜索角色
    @RequestMapping(value = "getRoleByNameAndProjectId")
    public ApiResult getRoleByNameAndProjectId(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                   @RequestParam(value = "roleName") String roleName){
        List<String> updateProject = authAgencyService.getAuthFunctionAndProjectIdByStaffId("STH40020026",userInformationEntity.getStaffId(),"4");
        List<UserOrRoleDTO> list = userAccreditService.getRolerByName(roleName,updateProject);
        return new SuccessApiResult(list);
    }

    //保存角色与人员关系
    @RequestMapping(value = "saveUserRoleRelation")
    public String saveUserRoleRelation(@Valid UserAndRoleRelationDTO userAndRoleRelationDTO){
        userAccreditService.saveOrUpdateUserRoleRelation(userAndRoleRelationDTO);
        return "redirect:../userAccredit/initUserAccreditManage.html";
    }

    //保存角色与人员关系
    @ResponseBody
    @RequestMapping(value = "/saveUserRoleRelation_2")
    public ApiResult saveUserRoleRelation_2(@Valid UserModelDTO users) {
        List<UserProjectRoleAccreditDTO> userList = users.getUserProRole();
        return  userAccreditService.saveOrUpdateUserRoleRelation_2(userList);
    }
}
