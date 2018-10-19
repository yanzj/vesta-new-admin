package com.maxrocky.vesta.presentation.admin.controller.construction;

import com.maxrocky.vesta.application.baseData.inf.SynchroOAService;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.*;
import com.maxrocky.vesta.application.inf.AgencyService;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.HrmUserDepartmentService;
import com.maxrocky.vesta.application.projectAuthority.inf.ProjectUserAuthorityService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
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
 * Created by yuanyn on 2018/3/22.
 */
@Controller
@RequestMapping(value = "/authorityForProject")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class ProjectUserManageController {

    @Autowired
    private AgencyService agencyService;
    @Autowired
    private SynchroOAService synchroOAService;
    @Autowired
    private AuthAgencyService authAgencyService;
    @Autowired
    private ProjectUserAuthorityService projectUserAuthorityService;
    @Autowired
    private HrmUserDepartmentService hrmUserDepartmentService;

    //内部人员初始化界面
    @RequestMapping(value = "/initProjectUserManage.html", method = RequestMethod.GET)
        public String initProjectUserManage(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, @Valid UserStaffDTO userStaffDTO, HttpServletRequest request, WebPage webPage, Model model) {
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
        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有  查询 esh40020063 同步oa账户 esh40020064 内部批量启用 esh40020065  内部批量停用 esh40020066  启用  esh40020067 停用 esh40020068
            //    外部用户管理 esh40020069 系统用户管理 esh40020079
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020063":
                        checkAuthFunctionDTO.setEsh40020063("Y");
                        break;
                    case "ESH40020064":
                        checkAuthFunctionDTO.setEsh40020064("Y");
                        break;
                    case "ESH40020065":
                        checkAuthFunctionDTO.setEsh40020065("Y");
                        break;
                    case "ESH40020066":
                        checkAuthFunctionDTO.setEsh40020066("Y");
                        break;
                    case "ESH40020067":
                        checkAuthFunctionDTO.setEsh40020067("Y");
                        break;
                    case "ESH40020068":
                        checkAuthFunctionDTO.setEsh40020068("Y");
                        break;
                    case "ESH40020069":
                        checkAuthFunctionDTO.setEsh40020069("Y");
                        break;
                    case "ESH40020079":
                        checkAuthFunctionDTO.setEsh40020079("Y");
                        break;
                }
            }
        }

        List<UserStaffDTO> userStaffDTOS = projectUserAuthorityService.getStaffListByAgencyId(userStaffDTO.getAgencyIdB());
        model.addAttribute("userStaffDTOS", userStaffDTOS);
        model.addAttribute("userStaffDTO", userStaffDTO);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/construction/projectAuthorityManage/ProjectUserManage";
    }

    //条件检索OA同步人员
    @RequestMapping(value = "conditionQueryProjectUser.html")
    public String conditionQueryProjectUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, @Valid UserStaffDTO userStaffDTO, HttpServletRequest request, WebPage webPage, Model model){
        webPage.setPageSize(20);
        List<UserStaffDTO> userStaffDTOSize = projectUserAuthorityService.conditionQueryProjectUser(userStaffDTO,null);
        List<UserStaffDTO> userStaffDTOS = projectUserAuthorityService.conditionQueryProjectUser(userStaffDTO,webPage);
        webPage.setRecordCount(userStaffDTOSize.size());
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有  查询 esh40020063 同步oa账户 esh40020064 内部批量启用 esh40020065  内部批量停用 esh40020066  启用  esh40020067 停用 esh40020068
            //    外部用户管理 esh40020069 系统用户管理 esh40020079
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020063":
                        checkAuthFunctionDTO.setEsh40020063("Y");
                        break;
                    case "ESH40020064":
                        checkAuthFunctionDTO.setEsh40020064("Y");
                        break;
                    case "ESH40020065":
                        checkAuthFunctionDTO.setEsh40020065("Y");
                        break;
                    case "ESH40020066":
                        checkAuthFunctionDTO.setEsh40020066("Y");
                        break;
                    case "ESH40020067":
                        checkAuthFunctionDTO.setEsh40020067("Y");
                        break;
                    case "ESH40020068":
                        checkAuthFunctionDTO.setEsh40020068("Y");
                        break;
                    case "ESH40020069":
                        checkAuthFunctionDTO.setEsh40020069("Y");
                        break;
                    case "ESH40020079":
                        checkAuthFunctionDTO.setEsh40020079("Y");
                        break;
                }
            }
        }
        model.addAttribute("userStaffDTO",userStaffDTO);
        model.addAttribute("userStaffDTOS",userStaffDTOS);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/construction/projectAuthorityManage/ProjectUserManage";
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
    @RequestMapping(value = "/syncOAByProject")
    public ApiResult syncOAByProject() throws Exception{
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
    @RequestMapping(value = "batchHandleProjectUserState")
    public Map<String,Object> batchHandleProjectUserState(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, Model model,
                                          @Valid StaffBatchDTO staffBatchDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            staffBatchDTO.setSourceFrom("OA");
            projectUserAuthorityService.startInsideProjectStaff(staffBatchDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
   }


    //批量操作人员数据（外部）
    @ResponseBody
    @RequestMapping(value = "batchHandleProjectOuterStaff")
    public Map<String,Object> batchHandleProjectOuterStaff(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, Model model,
                                          @Valid StaffBatchDTO staffBatchDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            staffBatchDTO.setSourceFrom("external");
            projectUserAuthorityService.startInsideProjectStaff(staffBatchDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    //外部所有机构
    @RequestMapping(value = "getProjectOuterAgency")
    public void getOuterAgency(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOS = projectUserAuthorityService.getProjectOuterAgencyList();
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOS).toString());
    }

    //根据用户id 查询所在的机构
    @RequestMapping(value = "getProjectOuterAgencyByStaffId")
    public void getProjectOuterAgencyByStaffId(HttpServletResponse response,
                                        @Param String staffIdW) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOS = projectUserAuthorityService.getProjectOuterAgencyListById(staffIdW);
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOS).toString());
    }

    //外部人员列表
    @RequestMapping(value = "outerProjectUserManage.html")
    public String outerProjectUserManage(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                  @Valid OuterUserDTO outerUserDTO, HttpServletRequest request, WebPage webPage, Model model){
        webPage.setPageSize(20);
        List<OuterUserDTO> outerUserSize = projectUserAuthorityService.getProjectOuterUserList(outerUserDTO,null);
        List<OuterUserDTO> outerUserDTOs = projectUserAuthorityService.getProjectOuterUserList(outerUserDTO,webPage);
        webPage.setRecordCount(outerUserSize.size());
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有  esh40020062;//内部用户管理 esh40020079;//系统用户管理 esh40020070;//外部用户查询 esh40020071;//新增组织架构
//            esh40020072;//添加用户 esh40020073;//外部批量启用 esh40020074;//外部批量停用 esh40020075;//外部批量删除 esh40020076;//外部编辑 esh40020077;//外部停用
//            esh40020078;//外部删除 esh40020082//下载用户模板 esh40020083//导入数据 esh40020084//导出Excel esh40020085//删除组织架构
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020062":
                        checkAuthFunctionDTO.setEsh40020062("Y");
                        break;
                    case "ESH40020079":
                        checkAuthFunctionDTO.setEsh40020079("Y");
                        break;
                    case "ESH40020070":
                        checkAuthFunctionDTO.setEsh40020070("Y");
                        break;
                    case "ESH40020071":
                        checkAuthFunctionDTO.setEsh40020071("Y");
                        break;
                    case "ESH40020072":
                        checkAuthFunctionDTO.setEsh40020072("Y");
                        break;
                    case "ESH40020073":
                        checkAuthFunctionDTO.setEsh40020073("Y");
                        break;
                    case "ESH40020074":
                        checkAuthFunctionDTO.setEsh40020074("Y");
                        break;
                    case "ESH40020075":
                        checkAuthFunctionDTO.setEsh40020075("Y");
                        break;
                    case "ESH40020076":
                        checkAuthFunctionDTO.setEsh40020076("Y");
                        break;
                    case "ESH40020077":
                        checkAuthFunctionDTO.setEsh40020077("Y");
                        break;
                    case "ESH40020078":
                        checkAuthFunctionDTO.setEsh40020078("Y");
                        break;
                    case "ESH40020082":
                        checkAuthFunctionDTO.setEsh40020082("Y");
                        break;
                    case "ESH40020083":
                        checkAuthFunctionDTO.setEsh40020083("Y");
                        break;
                    case "ESH40020084":
                        checkAuthFunctionDTO.setEsh40020084("Y");
                        break;
                    case "ESH40020085":
                        checkAuthFunctionDTO.setEsh40020085("Y");
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
        return "/construction/projectAuthorityManage/ProjectOuterUserManage";
    }

    //删除外部用户
    @ResponseBody
    @RequestMapping(value = "toDeleteProjectUser")
    public Map<String,Object> toDeleteProjectUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                           @Param String staffIdO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            projectUserAuthorityService.toDeleteProjectUser(staffIdO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    //批量删除外部用户
    @ResponseBody
    @RequestMapping(value = "batchDeleteProjectUser")
    public Map<String,Object> batchDeleteProjectUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, Model model,
                                          @Valid StaffBatchDTO staffBatchDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            projectUserAuthorityService.batchDeleteProjectUser(staffBatchDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    //系统用户列表
    @RequestMapping(value = "getProjectEnabledUser.html")
    public String getProjectEnabledUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                        @Valid EnabledUserDTO enabledUserDTO, Model model, WebPage webPage){
        webPage.setPageSize(20);
        List<EnabledUserDTO> enabledUserListSize = projectUserAuthorityService.getProjectEnabledUserList(enabledUserDTO,null);
        List<EnabledUserDTO> enabledUserDTOs = projectUserAuthorityService.getProjectEnabledUserList(enabledUserDTO,webPage);
        webPage.setRecordCount(enabledUserListSize.size());
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有 内部用户管理: esh40020062  外部用户管理: esh40020069 系统用户查询: esh40020080 系统用户导出excel: esh40020081
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020062":
                        checkAuthFunctionDTO.setEsh40020062("Y");
                        break;
                    case "ESH40020069":
                        checkAuthFunctionDTO.setEsh40020069("Y");
                        break;
                    case "ESH40020080":
                        checkAuthFunctionDTO.setEsh40020080("Y");
                        break;
                    case "ESH40020081":
                        checkAuthFunctionDTO.setEsh40020081("Y");
                        break;
                }
            }
        }
        model.addAttribute("webPage",webPage);
        model.addAttribute("enabledUserDTO",enabledUserDTO);
        model.addAttribute("enabledUserDTOS",enabledUserDTOs);
        model.addAttribute("isExcel",enabledUserDTOs.size());
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/construction/projectAuthorityManage/ProjectEnabledUserManage";
    }

    //添加用户
    @RequestMapping(value = "toEditOuterUser.html")
    public String toEditOuterUser(@RequestParam(value = "staffIdO" , required=false ) String staffIdO , Model model){
        Map<String, String> agencyMaps = projectUserAuthorityService.getProjectAgencys();
        model.addAttribute("agencys", agencyMaps);
        UserManageDTO userManageDTO;
        if(null != staffIdO && !StringUtil.isEmpty(staffIdO)){
            userManageDTO = projectUserAuthorityService.getProjectUserManage(staffIdO);
        }else {
            userManageDTO = new UserManageDTO();
            userManageDTO.setStatusW("1");
        }
        model.addAttribute("userManageDTO",userManageDTO);
        return "/construction/projectAuthorityManage/ProjectOuterUserUpdate";
    }

    //新建组织机构
    @RequestMapping(value = "toEditOuterAgency.html")
    public String toEditOuterAgency(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, Model model,
                                    @Valid OuterAgencyDTO outerAgencyDTO){
        Map<String, String> agencyMap = projectUserAuthorityService.getProjectAgencys();
        outerAgencyDTO.setStatus("1");
        model.addAttribute("agencys", agencyMap);
        model.addAttribute("outerAgencyDTO",outerAgencyDTO);
        return "/construction/projectAuthorityManage/ProjectOuterAgencyManage";
    }

    //新增、编辑 员工
    @RequestMapping(value = "/addOrUpdateOuterUser")
    public ApiResult addOuterUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertyStaffEntity, @Valid UserManageDTO userManageDTO) {
        String flag;

        if (!StringUtil.isEmpty(userManageDTO.getStaffIdW())) {   //如果员工ID不为空 则为编辑
            flag = projectUserAuthorityService.updateProjectStaff(userManageDTO, userPropertyStaffEntity);
        } else {        //否则为新增
            flag = projectUserAuthorityService.saveProjectStaff(userManageDTO, userPropertyStaffEntity);
        }
        return new SuccessApiResult(flag);
    }

    //新增外部合作单位组织机构
    @RequestMapping(value = "/addOrUpdateOuterAgency")
    public ApiResult addOrUpdateOuterAgency(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertyStaffEntity, @Valid OuterAgencyDTO outerAgencyDTO){
        return projectUserAuthorityService.saveProjectOuterAgency(outerAgencyDTO, userPropertyStaffEntity);
    }

    //删除外部合作单位组织架构
    @RequestMapping(value = "delProjectAgencyById")
    public ApiResult delAgencyById(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertyStaffEntity,@Param String agencyId){
        return projectUserAuthorityService.delProjectOuterAgency(agencyId, userPropertyStaffEntity);
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
        projectUserAuthorityService.projectEnabledUserExcel(title, headers, out, enabledUserDTO);
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
        projectUserAuthorityService.projectOuterUserExcel(title, headers, out, outerUserDTO);
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
            String result = projectUserAuthorityService.importProjectOuterPeopleExcel(fis, userPropertyStaffEntity);
            httpServletRequest.getSession().setAttribute("result", result);
            //关闭流
            fis.close();
            return "redirect:../authorityForProject/outerProjectUserManage.html";
        } catch (Exception e) {
            e.printStackTrace();
            httpServletRequest.getSession().setAttribute("result", "导入失败！");
            return "redirect:../authorityForProject/outerProjectUserManage.html";
        }
    }

    //同步OA数据
    @RequestMapping(value = "getOATestByProject")
    public ApiResult getOATestByProject(){
        String result = hrmUserDepartmentService.userDepartment();
        if("200".equals(result)){
            return new SuccessApiResult("0");
        }else {
            return new SuccessApiResult("1");
        }
    }
}
