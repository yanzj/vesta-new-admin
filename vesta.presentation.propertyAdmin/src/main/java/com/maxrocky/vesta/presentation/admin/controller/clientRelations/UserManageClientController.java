package com.maxrocky.vesta.presentation.admin.controller.clientRelations;

import com.maxrocky.vesta.application.baseData.inf.SynchroOAService;
import com.maxrocky.vesta.application.clientAuthority.inf.ClientUserAuthorityService;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.*;
import com.maxrocky.vesta.application.inf.AgencyService;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.HrmUserDepartmentService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanyn on 2018/5/8.
 */
@Controller
@RequestMapping(value = "/authorityClient")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class UserManageClientController {

    @Autowired
    private AgencyService agencyService;
    @Autowired
    private AuthAgencyService authAgencyService;
    @Autowired
    private ClientUserAuthorityService clientUserAuthorityService;
    @Autowired
    private HrmUserDepartmentService hrmUserDepartmentService;

    //内部人员初始化界面
    @RequestMapping(value = "/initClientUserManage.html", method = RequestMethod.GET)
    public String initClientUserManage(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, @Valid UserStaffDTO userStaffDTO, HttpServletRequest request, WebPage webPage, Model model) {
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
        List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","1");
        if(function!=null){
            //校验是否有  查询 qch40010094 同步oa账户 qch40010095 内部批量启用 qch40010096  内部批量停用 qch40010097  启用  qch40010098 停用 qch40010099
            //    外部用户管理 qch40010100 系统用户管理 qch40010110
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "QCH40010094":
                        checkAuthFunctionDTO.setQch40010094("Y");
                        break;
                    case "QCH40010095":
                        checkAuthFunctionDTO.setQch40010095("Y");
                        break;
                    case "QCH40010096":
                        checkAuthFunctionDTO.setQch40010096("Y");
                        break;
                    case "QCH40010097":
                        checkAuthFunctionDTO.setQch40010097("Y");
                        break;
                    case "QCH40010098":
                        checkAuthFunctionDTO.setQch40010098("Y");
                        break;
                    case "QCH40010099":
                        checkAuthFunctionDTO.setQch40010099("Y");
                        break;
                    case "QCH40010100":
                        checkAuthFunctionDTO.setQch40010100("Y");
                        break;
                    case "QCH40010110":
                        checkAuthFunctionDTO.setQch40010110("Y");
                        break;
                }
            }
        }

        List<UserStaffDTO> userStaffDTOS = clientUserAuthorityService.getStaffListByAgencyId(userStaffDTO.getAgencyIdB());
        model.addAttribute("userStaffDTOS", userStaffDTOS);
        model.addAttribute("userStaffDTO", userStaffDTO);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/construction/clientAuthority/ClientUserManage";
    }

    //条件检索OA同步人员
    @RequestMapping(value = "conditionQueryClientUser.html")
    public String conditionQueryClientUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, @Valid UserStaffDTO userStaffDTO, HttpServletRequest request, WebPage webPage, Model model){
        webPage.setPageSize(20);
        List<UserStaffDTO> userStaffDTOSize = clientUserAuthorityService.conditionQueryClientUser(userStaffDTO,null);
        List<UserStaffDTO> userStaffDTOS = clientUserAuthorityService.conditionQueryClientUser(userStaffDTO,webPage);
        webPage.setRecordCount(userStaffDTOSize.size());
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","1");
        if(function!=null){
            //校验是否有  查询 qch40010094 同步oa账户 qch40010095 内部批量启用 qch40010096  内部批量停用 qch40010097  启用  qch40010098 停用 qch40010099
            //    外部用户管理 qch40010100 系统用户管理 qch40010110
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "QCH40010094":
                        checkAuthFunctionDTO.setQch40010094("Y");
                        break;
                    case "QCH40010095":
                        checkAuthFunctionDTO.setQch40010095("Y");
                        break;
                    case "QCH40010096":
                        checkAuthFunctionDTO.setQch40010096("Y");
                        break;
                    case "QCH40010097":
                        checkAuthFunctionDTO.setQch40010097("Y");
                        break;
                    case "QCH40010098":
                        checkAuthFunctionDTO.setQch40010098("Y");
                        break;
                    case "QCH40010099":
                        checkAuthFunctionDTO.setQch40010099("Y");
                        break;
                    case "QCH40010100":
                        checkAuthFunctionDTO.setQch40010100("Y");
                        break;
                    case "QCH40010110":
                        checkAuthFunctionDTO.setQch40010110("Y");
                        break;
                }
            }
        }
        model.addAttribute("userStaffDTO",userStaffDTO);
        model.addAttribute("userStaffDTOS",userStaffDTOS);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/construction/clientAuthority/ClientUserManage";
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
    @RequestMapping(value = "/syncOAByClient")
    public ApiResult syncOA() throws Exception{
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
    @RequestMapping(value = "batchHandleClientUserState")
    public Map<String,Object> batchHandleClientUserState(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, Model model,
                                          @Valid StaffBatchDTO staffBatchDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            staffBatchDTO.setSourceFrom("OA");
            clientUserAuthorityService.startInsideClientStaff(staffBatchDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
   }


    //批量操作人员数据（外部）
    @ResponseBody
    @RequestMapping(value = "batchHandleClientOuterStaff")
    public Map<String,Object> batchHandleClientOuterStaff(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, Model model,
                                          @Valid StaffBatchDTO staffBatchDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            staffBatchDTO.setSourceFrom("external");
            clientUserAuthorityService.startInsideClientStaff(staffBatchDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }


    //外部所有机构
    @RequestMapping(value = "getClientOuterAgency")
    public void getClientOuterAgency(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOS = clientUserAuthorityService.getClientOuterAgencyList();
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOS).toString());
    }

    //根据用户id 查询所在的机构
    @RequestMapping(value = "getClientOuterAgencyByStaffId")
    public void getClientOuterAgencyByStaffId(HttpServletResponse response,
                                        @Param String staffIdW) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOS = clientUserAuthorityService.getClientOuterAgencyListById(staffIdW);
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOS).toString());
    }

    //外部人员列表
    @RequestMapping(value = "outerClientUserManage.html")
    public String outerClientUserManage(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                  @Valid OuterUserDTO outerUserDTO, HttpServletRequest request, WebPage webPage, Model model){
        webPage.setPageSize(20);
        List<OuterUserDTO> outerUserSize = clientUserAuthorityService.getClientOuterUserList(outerUserDTO,null);
        List<OuterUserDTO> outerUserDTOs = clientUserAuthorityService.getClientOuterUserList(outerUserDTO,webPage);
        webPage.setRecordCount(outerUserSize.size());
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","1");
        if(function!=null){
            //校验是否有  qch40010093;//内部用户管理 qch40010110;//系统用户管理 qch40010101;//外部用户查询 qch40010102;//新增组织架构
//            qch40010103;//添加用户 qch40010104;//外部批量启用 qch40010105;//外部批量停用 qch40010106;//外部批量删除 qch40010107;//外部编辑 qch40010108;//外部停用
//            qch40010109;//外部删除 qch40010123;//下载用户模板 qch40010124;//导入数据 qch40010125;//导出Excel qch40010126;//删除组织架构
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "QCH40010093":
                        checkAuthFunctionDTO.setQch40010093("Y");
                        break;
                    case "QCH40010110":
                        checkAuthFunctionDTO.setQch40010110("Y");
                        break;
                    case "QCH40010101":
                        checkAuthFunctionDTO.setQch40010101("Y");
                        break;
                    case "QCH40010102":
                        checkAuthFunctionDTO.setQch40010102("Y");
                        break;
                    case "QCH40010103":
                        checkAuthFunctionDTO.setQch40010103("Y");
                        break;
                    case "QCH40010104":
                        checkAuthFunctionDTO.setQch40010104("Y");
                        break;
                    case "QCH40010105":
                        checkAuthFunctionDTO.setQch40010105("Y");
                        break;
                    case "QCH40010106":
                        checkAuthFunctionDTO.setQch40010106("Y");
                        break;
                    case "QCH40010107":
                        checkAuthFunctionDTO.setQch40010107("Y");
                        break;
                    case "QCH40010108":
                        checkAuthFunctionDTO.setQch40010108("Y");
                        break;
                    case "QCH40010109":
                        checkAuthFunctionDTO.setQch40010109("Y");
                        break;
                    case "QCH40010123":
                        checkAuthFunctionDTO.setQch40010123("Y");
                        break;
                    case "QCH40010124":
                        checkAuthFunctionDTO.setQch40010124("Y");
                        break;
                    case "QCH40010125":
                        checkAuthFunctionDTO.setQch40010125("Y");
                        break;
                    case "QCH40010126":
                        checkAuthFunctionDTO.setQch40010126("Y");
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
        return "/construction/clientAuthority/ClientOuterUserManage";
    }

    //删除外部用户
    @ResponseBody
    @RequestMapping(value = "toDeleteClientUser")
    public Map<String,Object> toDeleteClientUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                           @Param String staffIdO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            clientUserAuthorityService.toDeleteClientUser(staffIdO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    //批量删除外部用户
    @ResponseBody
    @RequestMapping(value = "batchDeleteClientUser")
    public Map<String,Object> batchDeleteClientUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, Model model,
                                          @Valid StaffBatchDTO staffBatchDTO){
        Map<String,Object> resultMap = new HashedMap();
        try{
            clientUserAuthorityService.batchDeleteClientUser(staffBatchDTO);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    //系统用户列表
    @RequestMapping(value = "getClientEnabledUser.html")
    public String getClientEnabledUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                 @Valid EnabledUserDTO enabledUserDTO, Model model, WebPage webPage){
        webPage.setPageSize(20);
        List<EnabledUserDTO> enabledUserListSize = clientUserAuthorityService.getClientEnabledUserList(enabledUserDTO,null);
        List<EnabledUserDTO> enabledUserDTOs = clientUserAuthorityService.getClientEnabledUserList(enabledUserDTO,webPage);
        webPage.setRecordCount(enabledUserListSize.size());
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","1");
        if(function!=null){
            //校验是否有 内部用户管理: qch40010093  外部用户管理: qch40010100 系统用户查询: qch40010111 系统用户导出excel: qch40010112
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "QCH40010093":
                        checkAuthFunctionDTO.setQch40010093("Y");
                        break;
                    case "QCH40010100":
                        checkAuthFunctionDTO.setQch40010100("Y");
                        break;
                    case "QCH40010111":
                        checkAuthFunctionDTO.setQch40010111("Y");
                        break;
                    case "QCH40010112":
                        checkAuthFunctionDTO.setQch40010112("Y");
                        break;
                }
            }
        }
        model.addAttribute("webPage",webPage);
        model.addAttribute("enabledUserDTO",enabledUserDTO);
        model.addAttribute("enabledUserDTOS",enabledUserDTOs);
        model.addAttribute("isExcel",enabledUserDTOs.size());
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/construction/clientAuthority/ClientEnabledUserManage";
    }

    //添加用户
    @RequestMapping(value = "toEditOuterUser.html")
    public String toEditOuterUser(@RequestParam(value = "staffIdO" , required=false ) String staffIdO , Model model){
        Map<String, String> agencyMaps = clientUserAuthorityService.getClientAgencys();
        model.addAttribute("agencys", agencyMaps);
        UserManageDTO userManageDTO;
        if(null != staffIdO && !StringUtil.isEmpty(staffIdO)){
            userManageDTO = clientUserAuthorityService.getClientUserManage(staffIdO);
        }else {
            userManageDTO = new UserManageDTO();
            userManageDTO.setStatusW("1");
            userManageDTO.setTemporaryW("0");
        }
        model.addAttribute("userManageDTO",userManageDTO);
        return "/construction/clientAuthority/ClientOuterUserUpdate";
    }

    //新建组织机构
    @RequestMapping(value = "toEditOuterAgency.html")
    public String toEditOuterAgency(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity, Model model,
                                    @Valid OuterAgencyDTO outerAgencyDTO){
        Map<String, String> agencyMap = clientUserAuthorityService.getClientAgencys();
        outerAgencyDTO.setStatus("1");
        model.addAttribute("agencys", agencyMap);
        model.addAttribute("outerAgencyDTO",outerAgencyDTO);
        return "/construction/clientAuthority/ClientOuterAgencyManage";
    }

    //新增、编辑 员工
    @RequestMapping(value = "/addOrUpdateOuterUser")
    public ApiResult addOuterUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertyStaffEntity, @Valid UserManageDTO userManageDTO) {
        String flag;

        if (!StringUtil.isEmpty(userManageDTO.getStaffIdW())) {   //如果员工ID不为空 则为编辑
            flag = clientUserAuthorityService.updateClientStaff(userManageDTO, userPropertyStaffEntity);
        } else {        //否则为新增
            flag = clientUserAuthorityService.saveClientStaff(userManageDTO, userPropertyStaffEntity);
        }
        return new SuccessApiResult(flag);
    }
    // 检查用户名是否存在
    @RequestMapping(value = "/checkOuterUser" , method = RequestMethod.POST)
    public Map<String,Object> checkOuterUser(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaff,
                                      @Valid UserManageDTO userManageDTO) {
        Map<String,Object> resultMap = new HashMap<>();
        try{
            int check =  clientUserAuthorityService.checkClientStaff(userManageDTO.getSysNameW());
            resultMap.put("check",check);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("check",-1);//异常
        }
        return resultMap;
    }

    //新增外部合作单位组织机构
    @RequestMapping(value = "/addOrUpdateOuterAgency")
    public ApiResult addOrUpdateOuterAgency(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertyStaffEntity, @Valid OuterAgencyDTO outerAgencyDTO){
        return clientUserAuthorityService.saveClientOuterAgency(outerAgencyDTO, userPropertyStaffEntity);
    }

    //删除外部合作单位组织架构
    @RequestMapping(value = "delClientAgencyById")
    public ApiResult delClientAgencyById(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertyStaffEntity,@Param String agencyId){
        return clientUserAuthorityService.delClientOuterAgency(agencyId, userPropertyStaffEntity);
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
        clientUserAuthorityService.clientEnabledUserExcel(title, headers, out, enabledUserDTO);
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
        clientUserAuthorityService.clientOuterUserExcel(title, headers, out, outerUserDTO);
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

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @return 客户关系机构与人员的下载模板
     */
    @RequestMapping(value = "/qcExportOuterPeopleModel")
    @ResponseBody
    public String qcExportOuterPeopleModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            return agencyService.qcExportOuterPeopleModel(httpServletRequest, httpServletResponse);
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
            String result = clientUserAuthorityService.importClientOuterPeopleExcel(fis, userPropertyStaffEntity);
            httpServletRequest.getSession().setAttribute("result", result);
            //关闭流
            fis.close();
            return "redirect:../authorityClient/outerClientUserManage.html";
        } catch (Exception e) {
            e.printStackTrace();
            httpServletRequest.getSession().setAttribute("result", "导入失败！");
            return "redirect:../authorityClient/outerClientUserManage.html";
        }
    }

    /** 客户关系批量导入*/
    @RequestMapping(value = "/excelAddQCOuterPeople")
    public String excelAddQCOuterPeople(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertyStaffEntity, HttpServletRequest httpServletRequest){
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("myfile");
            InputStream fis = file.getInputStream();
            //POI:得到解析Excel的实体集合
            String result = clientUserAuthorityService.QCimportClientOuterPeopleExcel(fis, userPropertyStaffEntity);
            httpServletRequest.getSession().setAttribute("result", result);
            //关闭流
            fis.close();
            return "redirect:../authorityClient/outerClientUserManage.html";
        } catch (Exception e) {
            e.printStackTrace();
            httpServletRequest.getSession().setAttribute("result", "导入失败！");
            return "redirect:../authorityClient/outerClientUserManage.html";
        }
    }

    //同步OA数据
    @RequestMapping(value = "getOATestByClient")
    public ApiResult getOATest(){
        String result = hrmUserDepartmentService.userDepartment();
        if("200".equals(result)){
            return new SuccessApiResult("0");
        }else {
            return new SuccessApiResult("1");
        }
    }
}
