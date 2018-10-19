package com.maxrocky.vesta.presentation.admin.controller.security;

import com.maxrocky.vesta.application.baseData.inf.SynchroOAService;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyAdminDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.*;
import com.maxrocky.vesta.application.inf.AgencyService;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.HrmUserDepartmentService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import net.sf.json.JSONArray;
import org.apache.commons.collections.map.HashedMap;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanyn on 2017/12/6.
 */
@Controller
@RequestMapping(value = "/authority")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class UserManageController {

    @Autowired
    AgencyService agencyService;
    @Autowired
    SynchroOAService synchroOAService;
    @Autowired
    AuthAgencyService authAgencyService;
    @Autowired
    HrmUserDepartmentService hrmUserDepartmentService;

    //内部人员初始化界面
    @RequestMapping(value = "/initUserManage.html", method = RequestMethod.GET)
    public String listStaff(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, @Valid UserStaffDTO userStaffDTO, HttpServletRequest request, WebPage webPage, Model model) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cook = cookies[i];
            if (cook.getName().equalsIgnoreCase("agencyRoleId") && !StringUtil.isEmpty(cook.getValue())) { //获取当前选中的节点
                userStaffDTO.setAgencyIdB(cook.getValue());
            }
            if (cook.getName().equalsIgnoreCase("agencyRoleType") && !StringUtil.isEmpty(cook.getValue())) { //获取当前选中的节点的类型
                userStaffDTO.setAgencyTypeB(cook.getValue());
            }
        }
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function=authAgencyService.getAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","3");
        if(function!=null){
            //校验是否有  查询 sth40020001 同步oa账户 sth40020002 内部批量启用 sth40020003  内部批量停用 sth40020004  启用  sth40020005 停用 sth40020006
            //    外部用户管理 sth40020007 系统用户管理 sth40020017
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "STH40020001":
                        checkAuthFunctionDTO.setSth40020001("Y");
                        break;
                    case "STH40020002":
                        checkAuthFunctionDTO.setSth40020002("Y");
                        break;
                    case "STH40020003":
                        checkAuthFunctionDTO.setSth40020003("Y");
                        break;
                    case "STH40020004":
                        checkAuthFunctionDTO.setSth40020004("Y");
                        break;
                    case "STH40020005":
                        checkAuthFunctionDTO.setSth40020005("Y");
                        break;
                    case "STH40020006":
                        checkAuthFunctionDTO.setSth40020006("Y");
                        break;
                    case "STH40020007":
                        checkAuthFunctionDTO.setSth40020007("Y");
                        break;
                    case "STH40020017":
                        checkAuthFunctionDTO.setSth40020017("Y");
                        break;
                }
            }
        }

        List<UserStaffDTO> userStaffDTOS = agencyService.getStaffListByAgencyId(userStaffDTO.getAgencyIdB());
        model.addAttribute("userStaffDTOS", userStaffDTOS);
        model.addAttribute("userStaffDTO", userStaffDTO);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/security/authorityManage/UserManage";
    }

    //条件检索OA同步人员
    @RequestMapping(value = "conditionQueryUser.html")
    public String conditionQueryUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, @Valid UserStaffDTO userStaffDTO, HttpServletRequest request, WebPage webPage, Model model){
        webPage.setPageSize(20);
        List<UserStaffDTO> userStaffDTOSize = agencyService.conditionQueryUser(userStaffDTO,null);
        List<UserStaffDTO> userStaffDTOS = agencyService.conditionQueryUser(userStaffDTO,webPage);
        webPage.setRecordCount(userStaffDTOSize.size());
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function=authAgencyService.getAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","3");
        if(function!=null){
            //校验是否有  查询 sth40020001 同步oa账户 sth40020002 内部批量启用 sth40020003  内部批量停用 sth40020004  启用  sth40020005 停用 sth40020006
            //    外部用户管理 sth40020007 系统用户管理 sth40020017
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "STH40020001":
                        checkAuthFunctionDTO.setSth40020001("Y");
                        break;
                    case "STH40020002":
                        checkAuthFunctionDTO.setSth40020002("Y");
                        break;
                    case "STH40020003":
                        checkAuthFunctionDTO.setSth40020003("Y");
                        break;
                    case "STH40020004":
                        checkAuthFunctionDTO.setSth40020004("Y");
                        break;
                    case "STH40020005":
                        checkAuthFunctionDTO.setSth40020005("Y");
                        break;
                    case "STH40020006":
                        checkAuthFunctionDTO.setSth40020006("Y");
                        break;
                    case "STH40020007":
                        checkAuthFunctionDTO.setSth40020007("Y");
                        break;
                    case "STH40020017":
                        checkAuthFunctionDTO.setSth40020017("Y");
                        break;
                }
            }
        }
        model.addAttribute("userStaffDTO",userStaffDTO);
        model.addAttribute("userStaffDTOS",userStaffDTOS);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/security/authorityManage/UserManage";
    }

     // 所有机构
    @RequestMapping(value = "/getAllAgency",method = RequestMethod.GET)
    public void getAllAgency(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = agencyService.getOAAgencyList();
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }

    //同步OA
    @RequestMapping(value = "/syncOA")
    public ApiResult syncOA() throws Exception{
//        try {
//            synchroOAService.synchronousOA();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        String result = agencyService.getOAUserData();
        String result1 = agencyService.getOAAgencyData();
        if("200".equals(result) && "200".equals(result1)){
            return new SuccessApiResult("1");
        }else {
            return new SuccessApiResult("0");
        }
    }

    //批量操作人员数据（OA）
    @ResponseBody
    @RequestMapping(value = "batchHandle")
    public Map<String,Object> batchHandle(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, Model model,
                                          @Valid StaffBatchDTO staffBatchDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            staffBatchDTO.setSourceFrom("OA");
            agencyService.startInsideStaff(staffBatchDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
   }


    //批量操作人员数据（外部）
    @ResponseBody
    @RequestMapping(value = "batchHandleStaff")
    public Map<String,Object> batchHandleStaff(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, Model model,
                                          @Valid StaffBatchDTO staffBatchDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            staffBatchDTO.setSourceFrom("external");
            agencyService.startStaff(staffBatchDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }


    //外部所有机构
    @RequestMapping(value = "getOuterAgency")
    public void getOuterAgency(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOS = agencyService.getOuterAgencyList();
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOS).toString());
    }

    //根据用户id 查询所在的机构
    @RequestMapping(value = "getOuterAgencyByStaffId")
    public void getOuterAgencyByStaffId(HttpServletResponse response,
                                        @Param String staffIdW) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOS = agencyService.getOuterAgencyListById(staffIdW);
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOS).toString());
    }

    //外部人员列表
    @RequestMapping(value = "outerUserManage.html")
    public String outerUserManage(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                  @Valid OuterUserDTO outerUserDTO, HttpServletRequest request, WebPage webPage, Model model){
        webPage.setPageSize(20);
        List<OuterUserDTO> outerUserSize = agencyService.getOuterUserList(outerUserDTO,null);
        List<OuterUserDTO> outerUserDTOs = agencyService.getOuterUserList(outerUserDTO,webPage);
        webPage.setRecordCount(outerUserSize.size());
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function=authAgencyService.getAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","3");
        if(function!=null){
            //校验是否有  sth40020000;//内部用户管理 sth40020017;//系统用户管理 sth40020008;//外部用户查询 sth40020009;//新增组织架构
//            sth40020010;//添加用户 sth40020011;//外部批量启用 sth40020012;//外部批量停用 sth40020013;//外部批量删除 sth40020014;//外部编辑 sth40020015;//外部停用
//            sth40020016;//外部删除 sth40020030="N";//下载用户模板 sth40020031="N";//导入数据 sth40020032="N";//导出Excel sth40020033="N";//删除组织架构
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "STH40020000":
                        checkAuthFunctionDTO.setSth40020000("Y");
                        break;
                    case "STH40020017":
                        checkAuthFunctionDTO.setSth40020017("Y");
                        break;
                    case "STH40020008":
                        checkAuthFunctionDTO.setSth40020008("Y");
                        break;
                    case "STH40020009":
                        checkAuthFunctionDTO.setSth40020009("Y");
                        break;
                    case "STH40020010":
                        checkAuthFunctionDTO.setSth40020010("Y");
                        break;
                    case "STH40020011":
                        checkAuthFunctionDTO.setSth40020011("Y");
                        break;
                    case "STH40020012":
                        checkAuthFunctionDTO.setSth40020012("Y");
                        break;
                    case "STH40020013":
                        checkAuthFunctionDTO.setSth40020013("Y");
                        break;
                    case "STH40020014":
                        checkAuthFunctionDTO.setSth40020014("Y");
                        break;
                    case "STH40020015":
                        checkAuthFunctionDTO.setSth40020015("Y");
                        break;
                    case "STH40020016":
                        checkAuthFunctionDTO.setSth40020016("Y");
                        break;
                    case "STH40020030":
                        checkAuthFunctionDTO.setSth40020030("Y");
                        break;
                    case "STH40020031":
                        checkAuthFunctionDTO.setSth40020031("Y");
                        break;
                    case "STH40020032":
                        checkAuthFunctionDTO.setSth40020032("Y");
                        break;
                    case "STH40020033":
                        checkAuthFunctionDTO.setSth40020033("Y");
                        break;
                }
            }
        }
        if(null != outerUserDTO.getAgencyIdO() && !StringUtil.isEmpty(outerUserDTO.getAgencyIdO())){
            AgencyStateDTO agencyStateDTO = agencyService.getDelAgencyState(outerUserDTO.getAgencyIdO());
            model.addAttribute("agencyStateDTO",agencyStateDTO);
        }
        model.addAttribute("webPage",webPage);
        model.addAttribute("outerUserDTO",outerUserDTO);
        model.addAttribute("outerUserDTOs",outerUserDTOs);
        model.addAttribute("isExcel",outerUserDTOs.size());
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/security/authorityManage/OuterUserManage";
    }

    //删除外部用户
    @ResponseBody
    @RequestMapping(value = "toDeleteUser")
    public Map<String,Object> toDeleteUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                           @Param String staffIdO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            agencyService.toDeleteUser(staffIdO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    //批量删除外部用户
    @ResponseBody
    @RequestMapping(value = "batchDelete")
    public Map<String,Object> batchDelete(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, Model model,
                                          @Valid StaffBatchDTO staffBatchDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            agencyService.batchDelete(staffBatchDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    //系统用户列表
    @RequestMapping(value = "getEnabledUser.html")
    public String getEnabledUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                 @Valid EnabledUserDTO enabledUserDTO, Model model, WebPage webPage){
        webPage.setPageSize(20);
        List<EnabledUserDTO> enabledUserListSize = agencyService.getEnabledUserList(enabledUserDTO,null);
        List<EnabledUserDTO> enabledUserDTOs = agencyService.getEnabledUserList(enabledUserDTO,webPage);
        webPage.setRecordCount(enabledUserListSize.size());
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function=authAgencyService.getAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","3");
        if(function!=null){
            //校验是否有 内部用户管理: sth40020000  外部用户管理: sth40020007 系统用户查询: sth40020018 系统用户导出excel: sth40020019
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "STH40020018":
                        checkAuthFunctionDTO.setSth40020018("Y");
                        break;
                    case "STH40020019":
                        checkAuthFunctionDTO.setSth40020019("Y");
                        break;
                    case "STH40020000":
                        checkAuthFunctionDTO.setSth40020000("Y");
                        break;
                    case "STH40020007":
                        checkAuthFunctionDTO.setSth40020007("Y");
                        break;
                }
            }
        }
        model.addAttribute("webPage",webPage);
        model.addAttribute("enabledUserDTO",enabledUserDTO);
        model.addAttribute("enabledUserDTOS",enabledUserDTOs);
        model.addAttribute("isExcel",enabledUserDTOs.size());
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/security/authorityManage/EnabledUserManage";
    }

    //添加用户
    @RequestMapping(value = "toEditOuterUser.html")
    public String toEditOuterUser(@RequestParam(value = "staffIdO" , required=false ) String staffIdO , Model model){
        Map<String, String> agencyMap = agencyService.getAgencys();
        model.addAttribute("agencys", agencyMap);
        UserManageDTO userManageDTO;
        if(null != staffIdO && !StringUtil.isEmpty(staffIdO)){
            userManageDTO = agencyService.getUserManage(staffIdO);
        }else {
            userManageDTO = new UserManageDTO();
            userManageDTO.setStatusW("1");
        }
        model.addAttribute("userManageDTO",userManageDTO);
        return "/security/authorityManage/OuterUserUpdate";
    }

    //新建组织机构
    @RequestMapping(value = "toEditOuterAgency.html")
    public String toEditOuterAgency(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, Model model,
                                    @Valid OuterAgencyDTO outerAgencyDTO){
        Map<String, String> agencyMap = agencyService.getAgencys();
        outerAgencyDTO.setStatus("1");
        model.addAttribute("agencys", agencyMap);
        model.addAttribute("outerAgencyDTO",outerAgencyDTO);
        return "/security/authorityManage/OuterAgencyManage";
    }

    //新增、编辑 员工
    @RequestMapping(value = "/addOrUpdateOuterUser")
    public ApiResult addOuterUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertyStaffEntity, @Valid UserManageDTO userManageDTO) {
        String flag;

        if (!StringUtil.isEmpty(userManageDTO.getStaffIdW())) {   //如果员工ID不为空 则为编辑
            flag = agencyService.updateStaff(userManageDTO, userPropertyStaffEntity);
        } else {        //否则为新增
            flag = agencyService.saveStaff(userManageDTO, userPropertyStaffEntity);
        }
        return new SuccessApiResult(flag);
    }

    //新增外部合作单位组织机构
    @RequestMapping(value = "/addOrUpdateOuterAgency")
    public ApiResult addOrUpdateOuterAgency(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertyStaffEntity, @Valid OuterAgencyDTO outerAgencyDTO){
        return agencyService.saveOuterAgency(outerAgencyDTO, userPropertyStaffEntity);
    }

    //删除外部合作单位组织架构
    @RequestMapping(value = "delAgencyById")
    public ApiResult delAgencyById(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertyStaffEntity,@Param String agencyId){
        return agencyService.delOuterAgency(agencyId, userPropertyStaffEntity);
    }

    //系统用户导出Excel
    @RequestMapping(value = "enabledUserExportExcel")
    public void enabledUserExportExcel(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaff, HttpServletRequest request, HttpServletResponse response,
                                       @Valid EnabledUserDTO enabledUserDTO) throws Exception {

        String fileName = "系统用户列表";
        response.reset();
        response.setContentType("application/x-xls");
        String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        if (agent.contains("firefox")) {
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
        } else {
            response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
        }
        String title = "系统用户列表";
        String[] headers = {"姓名", "OA账号", "系统账号", "用户来源", "电话","邮箱","所属机构"};
        ServletOutputStream out = response.getOutputStream();
        agencyService.enabledUserExcel(title, headers, out, enabledUserDTO);
    }

    //外部用户导出Excel
    @RequestMapping(value = "outerUserExportExcel")
    public void outerUserExportExcel(@ModelAttribute("authPropertystaff")UserInformationEntity userPropertystaff, HttpServletRequest request, HttpServletResponse response,
                                     @Valid OuterUserDTO outerUserDTO) throws Exception {
        String fileName = "外部用户列表";
        response.reset();
        response.setContentType("application/x-xls");
        String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        String agent = request.getHeader("USER-AGENT").toLowerCase();
        if (agent.contains("firefox")) {
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
        } else {
            response.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
        }
        String title = "外部用户列表";
        String[] headers = {"姓名", "系统账号", "联系方式", "邮箱", "所属机构"};
        ServletOutputStream out = response.getOutputStream();
        agencyService.outerUserExcel(title, headers, out, outerUserDTO);
    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @return 机构与人员的下载模板
     */
    @RequestMapping(value = "/exportOuterPeopleModel")
    @ResponseBody
    public String exportOuterPeopleModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            return agencyService.exportOuterPeopleModel(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }

    /** 批量导入*/
    @RequestMapping(value = "/excelAddOuterPeople")
    public String excelAddOuterPeople(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertyStaffEntity, HttpServletRequest httpServletRequest){
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("myfile");
            InputStream fis = file.getInputStream();
            //POI:得到解析Excel的实体集合
            String result = agencyService.importOuterPeopleExcel(fis, userPropertyStaffEntity);
            httpServletRequest.getSession().setAttribute("result", result);
            //关闭流
            fis.close();
            return "redirect:../authority/outerUserManage.html";
        } catch (Exception e) {
            e.printStackTrace();
            httpServletRequest.getSession().setAttribute("result", "导入失败！");
            return "redirect:../authority/outerUserManage.html";
        }
    }

    //迁移历史数据
    @RequestMapping(value = "")
    public void getUserAndAgencyByHistoryTable(){
        agencyService.getUserAndAgencyAndRoleByHistoryTable();
    }

    //同步OA数据
    @RequestMapping(value = "getOATest")
    public ApiResult getOATest(){
        String result = hrmUserDepartmentService.userDepartment();
        if("200".equals(result)){
            return new SuccessApiResult("0");
        }else {
            return new SuccessApiResult("1");
        }
    }
}
