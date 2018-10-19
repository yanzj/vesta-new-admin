package com.maxrocky.vesta.presentation.admin.controller.role;

import com.maxrocky.vesta.application.dto.adminDTO.AgencyAdminDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.*;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.OrganizeService;
import com.maxrocky.vesta.application.inf.UserAccreditService;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新权限功能模块  组织机构
 * Created by magic on 2017/12/8.
 */
@Controller
@RequestMapping(value = "/authAgency")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class, UserInformationEntity.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class AuthAgencyController {
    @Autowired
    AuthAgencyService authAgencyService;

    @Autowired
    UserAccreditService userAccreditService;

    @Autowired
    SecurityProjectService securityProjectService;


    @Autowired
    OrganizeService organizeService;
    /**
     * 初始组织结构
     *
     * @param userPropertystaffEntity
     * @param model  安全
     * @param
     * @return
     */
    @RequestMapping(value = "/AuthAgency.html", method = RequestMethod.GET)
    public String AuthAgency(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                             @Valid AgencyAdminDTO agencyAdminDTO, HttpServletRequest request, WebPage webPage, Model model) {
         CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cook = cookies[i];
            if (cook.getName().equalsIgnoreCase("agencyId") && !StringUtil.isEmpty(cook.getValue())) { //获取当前选中的节点
                agencyAdminDTO.setAgencyId(cook.getValue());
            }
            if (cook.getName().equalsIgnoreCase("agencyType") && !StringUtil.isEmpty(cook.getValue())) { //获取当前选中的节点的类型
                agencyAdminDTO.setAgencyType(cook.getValue());
            }
        }
        //获取当前节点下的子节点
        List<AuthAdminAgencyDTO> agencyDTOList=new ArrayList<>();
        if(!StringUtil.isEmpty(agencyAdminDTO.getAgencyId()) || !StringUtil.isEmpty(agencyAdminDTO.getAgencyName())){
            if("3".equals(agencyAdminDTO.getCategory())){//安全
                agencyDTOList= authAgencyService.getAgencyById(agencyAdminDTO);
            }else if("2".equals(agencyAdminDTO.getCategory())){//工程
                agencyDTOList= authAgencyService.getESAgencyById(agencyAdminDTO);
            }else if("1".equals(agencyAdminDTO.getCategory())){//客观
                agencyDTOList= authAgencyService.getQCAgencyById(agencyAdminDTO);
            }
        }else {
            agencyDTOList= authAgencyService.getAgency();
        }
        Map map = null;
        if("3".equals(agencyAdminDTO.getCategory())){
            map = authAgencyService.getAreaListByInit();
            List<String> function=authAgencyService.getAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","3");
            if(function!=null){
                //校验是否有    新增项目权限
                for(int i=0;i<function.size();i++){
                    switch (function.get(i).toString()) {
                        case "STH40020060":
                            checkAuthFunctionDTO.setSth40020060("Y");
                            break;
                    }
                }
            }
        }else if("2".equals(agencyAdminDTO.getCategory())){
            map = authAgencyService.getESAreaListByInit();
            List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","2");
            if(function!=null){
                //校验是否有    新增项目权限
                for(int i=0;i<function.size();i++){
                    switch (function.get(i).toString()) {
                        case "ESH40020096":
                            checkAuthFunctionDTO.setEsh40020096("Y");
                            break;
                    }
                }
            }
        }else if("1".equals(agencyAdminDTO.getCategory())){
            map = authAgencyService.getQCAreaListByInit();
            List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","1");
            /*if(function!=null){
                //校验是否有    新增项目权限
                for(int i=0;i<function.size();i++){
                    switch (function.get(i).toString()) {
                        case "ESH40020096":
                            checkAuthFunctionDTO.setEsh40020096("Y");
                            break;
                    }
                }
            }*/
        }

        String flag = "";
        model.addAttribute("agencyList", agencyDTOList);
        model.addAttribute("flag", flag);
        model.addAttribute("function",checkAuthFunctionDTO);
        model.addAttribute("adminDTO", agencyAdminDTO);
        model.addAttribute("areaList",map);
        return "/authagency/AuthAgencyManage";
    }

    /**
     * @param response
     * @throws IOException
     * 所有机构
     */
    @RequestMapping(value = "/fullAgency",method = RequestMethod.GET)
    public void fullAgency(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = authAgencyService.getAgencyFullList();
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }

    /**
     * @param response
     * @throws IOException
     * 所有机构
     */
    @RequestMapping(value = "/fullAgencyES",method = RequestMethod.GET)
    public void fullAgencyES(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = authAgencyService.getESAgencyFullList();
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }

    /**
     * @param response
     * @throws IOException
     * 所有机构
     */
    @RequestMapping(value = "/fullAgencyQC",method = RequestMethod.GET)
    public void fullAgencyQC(HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = authAgencyService.getQCAgencyFullList();
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }

    /**
     * @param response
     * @throws IOException
     * 根据权限查询层级结构
     */
    @RequestMapping(value = "/authFullAgency",method = RequestMethod.GET)
    public void authFullAgency(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = authAgencyService.getAuthAgencyFullList(userPropertystaffEntity);
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }

    /**
     * @param response
     * @throws IOException
     * 所有机构 安全
     */
    @RequestMapping(value = "/fullAgencyByID",method = RequestMethod.GET)
    public void fullAgencyByID(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                               @Valid AuthRoleseListDTO authRoleseListDTO, HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = authAgencyService.getAgencyFullListByID(authRoleseListDTO.getApply());
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }
    /**
     * @param response
     * @throws IOException
     * 所有机构 工程
     */
    @RequestMapping(value = "/fullAgencyByIDES",method = RequestMethod.GET)
    public void fullAgencyByIDES(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                               @Valid AuthRoleseListDTO authRoleseListDTO, HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = authAgencyService.getAgencyFullListByIDES(authRoleseListDTO.getApply());
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }
    /**
     * @param response
     * @throws IOException
     * 所有机构 客观
     */
    @RequestMapping(value = "/fullAgencyByIDQC",method = RequestMethod.GET)
    public void fullAgencyByIDQC(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                               @Valid AuthRoleseListDTO authRoleseListDTO, HttpServletRequest request,HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = authAgencyService.getAgencyFullListByIDQC(authRoleseListDTO.getApply());
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }
    /**
     * 初始角色頁面
     *
     * @param userPropertystaffEntity
     * @param model
     * @param
     * @return
     */
    @RequestMapping(value = "/authRole.html", method = RequestMethod.GET)
    public String authRole(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                           @Valid AuthRoleseListDTO authRoleseListDTO, HttpServletRequest request, WebPage webPage, Model model) {

        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        //判断校验是否有授权功能点
        if("3".equals(authRoleseListDTO.getCategory())){
            List<String> function=authAgencyService.getAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","3");
            if(function!=null){
                //校验是否有    新增 STH40020020   修改 STH40020021 删除 STH40020022  授权 STH40020023
                for(int i=0;i<function.size();i++){
                    switch (function.get(i).toString()) {
                        case "STH40020020":
                            checkAuthFunctionDTO.setSth40020020("Y");
                            break;
                        case "STH40020021":
                            checkAuthFunctionDTO.setSth40020021("Y");
                            break;
                        case "STH40020023":
                            checkAuthFunctionDTO.setSth40020023("Y");
                            break;
                        case "STH40020022":
                            checkAuthFunctionDTO.setSth40020022("Y");
                            break;
                    }
                }
            }
        }
        if("2".equals(authRoleseListDTO.getCategory())){
            List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","2");
            if(function!=null){
                //校验是否有       ESH40020089;//授权--角色创建管理 ESH40020088;//删除--角色创建管理    ESH40020087;//修改--角色创建管理  ESH40020086;//新建角色--角色创建管理

                for(int i=0;i<function.size();i++){
                    switch (function.get(i).toString()) {
                        case "ESH40020089":
                            checkAuthFunctionDTO.setEsh40020089("Y");
                            break;
                        case "ESH40020088":
                            checkAuthFunctionDTO.setEsh40020088("Y");
                            break;
                        case "ESH40020087":
                            checkAuthFunctionDTO.setEsh40020087("Y");
                            break;
                        case "ESH40020086":
                            checkAuthFunctionDTO.setEsh40020086("Y");
                            break;
                    }
                }
            }
        }
        if("1".equals(authRoleseListDTO.getCategory())){
            List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","1");
            if(function!=null){
                //校验是否有       QCH40010116;//授权--角色创建管理 QCH40010115;//删除--角色创建管理    QCH40010114;//修改--角色创建管理  QCH40010113;//新建角色--角色创建管理
                /*权限控制点需要修改*************************************************************/
                for(int i=0;i<function.size();i++){
                    switch (function.get(i).toString()) {
                        case "QCH40010116":
                            checkAuthFunctionDTO.setQch40010116("Y");
                            break;
                        case "QCH40010115":
                            checkAuthFunctionDTO.setQch40010115("Y");
                            break;
                        case "QCH40010114":
                            checkAuthFunctionDTO.setQch40010114("Y");
                            break;
                        case "QCH40010113":
                            checkAuthFunctionDTO.setQch40010113("Y");
                            break;
                    }
                }
            }
        }

        List<AuthRoleseListDTO> list=authAgencyService.getAuthRoleseList(authRoleseListDTO,webPage);
        model.addAttribute("typeMaps", authRoleseListDTO);
        model.addAttribute("problems",list);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/authagency/AuthRoleManage";

    }

    /**
     * 添加角色信息
     */
    @RequestMapping(value = "/addAuthRole", method = RequestMethod.POST)
    public Map<String,Object> addAuthRole(@ModelAttribute("authPropertystaff")UserInformationEntity userPropertystaffEntity,
                                          Model model, @Valid AuthRoleseListDTO problem){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            int check = authAgencyService.saveAuthRolese(userPropertystaffEntity,problem);
            resultMap.put("check",check);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("check",-1);//异常
        }
        return resultMap;
    }

    /**
     * 按id查询信息 安全 3
     */
    @RequestMapping(value = "/queryAuthRole", method = RequestMethod.GET)
    public Map<String,Object> queryAuthRole(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                            @Valid AuthRoleseListDTO authRoleseListDTO, HttpServletRequest request, WebPage webPage, Model model) {
        Map<String,Object> resultMap = new HashMap<>();
        AuthRoleseListDTO list=authAgencyService.getAuthRoleseById(authRoleseListDTO);
        resultMap.put("problem",list);
        return resultMap;
    }

    /**
     * 修改角色信息
     */
    @RequestMapping(value = "/upAuthRole")
    public Map<String,Object> upAuthRole(@ModelAttribute("authPropertystaff")UserInformationEntity userPropertystaffEntity,
                                         Model model, @Valid AuthRoleseListDTO problem){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            int check = authAgencyService.upAuthRolese(userPropertystaffEntity,problem);
            resultMap.put("check",check);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("check",-1);//异常
        }
        return resultMap;
    }

    /**
     * 删除角色信息
     */
    @RequestMapping(value = "/delAuthRole")
    public Map<String,Object> delAuthRole(@ModelAttribute("authPropertystaff")UserInformationEntity userPropertystaffEntity,
                                    Model model, @Valid AuthRoleseListDTO problem){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            int check = authAgencyService.delAuthRolese(userPropertystaffEntity,problem);
            resultMap.put("check",check);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("check",-1);//异常
        }
        return resultMap;
    }

    /**
     * 初始功能授权页面
     */
    @RequestMapping(value = "/authFunctionPoint.html", method = RequestMethod.GET)
    public String authFunctionPoint(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                    @Valid AuthFunctionPointRoleListDTO authFunctionPointRoleListDTO, HttpServletRequest request, WebPage webPage, Model model) {

        AuthFunctionPointListDTO authFunctionPointList=authAgencyService.getAuthFunctionPoint(authFunctionPointRoleListDTO);

        if(!StringUtil.isEmpty(authFunctionPointRoleListDTO.getCategory())){
            Map<String, String> roles = authAgencyService.getRoleList(authFunctionPointRoleListDTO.getCategory());
            authFunctionPointRoleListDTO.setRoles(roles);
        }

        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        if("3".equals(authFunctionPointRoleListDTO.getCategory())){//安全
            //判断校验是否有功能点
            List<String> function=authAgencyService.getAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","3");
            if(function!=null){
                //校验是否有    保存 STH40020029
                for(int i=0;i<function.size();i++){
                    switch (function.get(i).toString()) {
                        case "STH40020029":
                            checkAuthFunctionDTO.setSth40020029("Y");
                    }
                }
            }
        }
        if("2".equals(authFunctionPointRoleListDTO.getCategory())){//工程
            //判断校验是否有功能点
            List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","2");
            if(function!=null){
                //校验是否有    保存 STH40020029
                for(int i=0;i<function.size();i++){
                    switch (function.get(i).toString()) {
                        case "ESH40020095":
                            checkAuthFunctionDTO.setEsh40020095("Y");
                    }
                }
            }
        }
        if("1".equals(authFunctionPointRoleListDTO.getCategory())){//客观
            //判断校验是否有功能点
            List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","1");
            if(function!=null){
                //校验是否有    保存 QCH40010122
                for(int i=0;i<function.size();i++){
                    switch (function.get(i).toString()) {
                        case "QCH40010122":
                            checkAuthFunctionDTO.setQch40010122("Y");
                    }
                }
            }
        }
        model.addAttribute("typeMaps", authFunctionPointRoleListDTO);
        model.addAttribute("problem", authFunctionPointRoleListDTO);
        model.addAttribute("problems",authFunctionPointList);
        model.addAttribute("function",checkAuthFunctionDTO);
        model.addAttribute("category",authFunctionPointRoleListDTO.getCategory());
        return "/authagency/authFunctionPointManage";
     //修改时候三个页面同时修改
//        if("3".equals(authFunctionPointRoleListDTO.getCategory())){//安全
//            return "/authagency/authFunctionPointManage";
//        } else if("2".equals(authFunctionPointRoleListDTO.getCategory())){//工程
//            return "/authagency/authFunctionPointManageES";
//        } else{//客观
//            return "/authagency/authFunctionPointManageQC";
//        }
    }

    /**
     * 功能授权
     */
    @RequestMapping(value = "/addRole" , method = RequestMethod.POST)
    public Map<String,Object> addRole(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaff,
                                      @Valid AuthFunctionPointRoleListDTO authFunctionPointRoleListDTO) {
        Map<String,Object> resultMap = new HashMap<>();
        try{
            int check =  authAgencyService.addRoleButtonMap(authFunctionPointRoleListDTO,userPropertystaff);
            resultMap.put("check",check);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("check",-1);//异常
        }
        return resultMap;
    }


    @RequestMapping(value="/getRoleList")
    public ApiResult getRoleList(@Valid AuthRoleseListDTO authRoleseListDTO){
        return new SuccessApiResult(authAgencyService.getRoleList(authRoleseListDTO.getCategory()));
    }


    /**
     * 初始化项目授权
     *
     * @param userPropertystaffEntity
     * @param model
     * @param
     * @return
     */
    @RequestMapping(value = "/AuthAgencyProject.html", method = RequestMethod.GET)
    public String AuthAgencyProject(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                    @Valid AgencyAdminDTO agencyAdminDTO, HttpServletRequest request, WebPage webPage, Model model) {

        webPage.setPageSize(20);
        AuthAdminAgencyProjectDTO adminAgencyProject=new AuthAdminAgencyProjectDTO();
        if(!StringUtil.isEmpty(agencyAdminDTO.getAgencyId()) && "3".equals(agencyAdminDTO.getCategory())){//安全
            adminAgencyProject= authAgencyService.getAuthAdminAgencyProjectList(agencyAdminDTO,webPage,userPropertystaffEntity);
            webPage.setRecordCount(adminAgencyProject.getList().size());
        }else if(!StringUtil.isEmpty(agencyAdminDTO.getAgencyId()) && "2".equals(agencyAdminDTO.getCategory())){//工程
            adminAgencyProject= authAgencyService.getESAuthAdminAgencyProjectList(agencyAdminDTO,webPage,userPropertystaffEntity);
            webPage.setRecordCount(adminAgencyProject.getList().size());
        }else if(!StringUtil.isEmpty(agencyAdminDTO.getAgencyId()) && "1".equals(agencyAdminDTO.getCategory())){//客观
            adminAgencyProject= authAgencyService.getQCAuthAdminAgencyProjectList(agencyAdminDTO,webPage,userPropertystaffEntity);
            webPage.setRecordCount(adminAgencyProject.getList().size());
        }
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        if("3".equals(agencyAdminDTO.getCategory())){//安全
            //判断校验是否有授权功能点  sth40020028
            List<String> function=authAgencyService.getAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","3");
            if(function!=null){
                for(int i=0;i<function.size();i++){
                    switch (function.get(i).toString()) {
                        case "STH40020028":
                            checkAuthFunctionDTO.setSth40020028("Y");
                    }
                }
            }
        }
        if("2".equals(agencyAdminDTO.getCategory())){//工程
            //判断校验是否有授权功能点   esh40020090;//授权--项目授权管理
            List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","2");
            if(function!=null){
                for(int i=0;i<function.size();i++){
                    switch (function.get(i).toString()) {
                        case "ESH40020090":
                            checkAuthFunctionDTO.setEsh40020090("Y");
                    }
                }
            }
        }
        if("1".equals(agencyAdminDTO.getCategory())){//客观
            //判断校验是否有授权功能点   QCH40010121;//授权--项目授权管理
            List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(userPropertystaffEntity.getStaffId(),"4","1");
            if(function!=null){
                for(int i=0;i<function.size();i++){
                    switch (function.get(i).toString()) {
                        case "QCH40010121":
                            checkAuthFunctionDTO.setQch40010121("Y");
                    }
                }
            }
        }
        String flag = "";
        model.addAttribute("agencyList", adminAgencyProject);
        model.addAttribute("flag", flag);
        model.addAttribute("adminDTO", agencyAdminDTO);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "/authagency/AuthAgencyProjectManage";
    }

    /**
     * 初始化项目角色授权
     *
     * @param userPropertystaffEntity
     * @param model
     * @param
     * @return
     */
    @RequestMapping(value = "/AuthAgencyRole.html", method = RequestMethod.GET)
    public String AuthAgencyRole(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaffEntity,
                                 @Valid AgencyAdminDTO agencyAdminDTO, HttpServletRequest request, WebPage webPage, Model model) {


        //获取当前节点下的子节点
        AuthAdminAgencyProjectDTO adminAgencyProject=new AuthAdminAgencyProjectDTO();
        if(!StringUtil.isEmpty(agencyAdminDTO.getAgencyId()) && !StringUtil.isEmpty(agencyAdminDTO.getAuthRoleId())){
            adminAgencyProject= authAgencyService.getAuthAdminAgencyRole(agencyAdminDTO);
        }
        String flag = "";
        model.addAttribute("authRoleId", agencyAdminDTO.getAuthRoleId()+"|"+agencyAdminDTO.getAgencyId());
        model.addAttribute("authRoleName", agencyAdminDTO.getAuthRoleName());
        model.addAttribute("adminDTO", adminAgencyProject);
        return "/authagency/AuthAgencyRoleUser";
    }

    @RequestMapping(value="/getAuthAgencyRole")// 安全
    public ApiResult getAuthAgencyRole(@Valid AgencyAdminDTO agencyAdminDTO){
        return new SuccessApiResult(authAgencyService.getAuthAgencyRole(agencyAdminDTO));
    }

    @RequestMapping(value="/getESAuthAgencyRole")//工程
    public ApiResult getESAuthAgencyRole(@Valid AgencyAdminDTO agencyAdminDTO){
        return new SuccessApiResult(authAgencyService.getESAuthAgencyRole(agencyAdminDTO));
    }

    @RequestMapping(value="/getQCAuthAgencyRole")//客观
    public ApiResult getQCAuthAgencyRole(@Valid AgencyAdminDTO agencyAdminDTO){
        return new SuccessApiResult(authAgencyService.getQCAuthAgencyRole(agencyAdminDTO));
    }

    //保存角色与人员关系
    @RequestMapping(value = "saveUserRoleRelation")// 安全
    public Map<String,Object> saveUserRoleRelation(@Valid UserAndRoleRelationDTO userAndRoleRelationDTO){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            if(StringUtil.isEmpty(userAndRoleRelationDTO.getCategory())){
                userAndRoleRelationDTO.setCategory("3");//默认安全
            }
            int check =   authAgencyService.saveOrUpdateUserRoleRelation(userAndRoleRelationDTO);
            resultMap.put("check",check);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("check",-1);//异常
        }
        return resultMap;
    }

    //根据区域id获取其下级城市信息 安全
    @RequestMapping(value = "/getCityByAreaId")
    public ApiResult getThisCity(@RequestParam(value = "areaId") String areaId) {
        Map map = authAgencyService.getCityListByAreaId(areaId,"3");
        return new SuccessApiResult(map);
    }
    //根据区域id获取其下级城市信息 工程
    @RequestMapping(value = "/getESCityByAreaId")
    public ApiResult getESCityByAreaId(@RequestParam(value = "areaId") String areaId) {
        Map map = authAgencyService.getCityListByAreaId(areaId,"2");
        return new SuccessApiResult(map);
    }
    //根据区域id获取其下级城市信息 客观
    @RequestMapping(value = "/getQCCityByAreaId")
    public ApiResult getQCCityByAreaId(@RequestParam(value = "areaId") String areaId) {
        Map map = authAgencyService.getCityListByAreaId(areaId,"4");
        return new SuccessApiResult(map);
    }
    //保存项目
    @RequestMapping(value = "/saveProject" , method = RequestMethod.POST)
    public Map<String,Object> saveProject(@ModelAttribute("authPropertystaff") UserInformationEntity userPropertystaff,
                                          @RequestParam(value = "projectName") String projectName,
                                          @RequestParam(value = "cityId") String cityId,
                                          @RequestParam(value = "category") String category) {
        Map<String,Object> resultMap = new HashMap<>();
        if(null != projectName && !StringUtil.isEmpty(projectName)){
            try{
                authAgencyService.saveProjectByProjectName(projectName,cityId,category);
                resultMap.put("check",1);
            }catch (Exception e){
                e.printStackTrace();
                resultMap.put("check",-1);//异常
            }
            return resultMap;
        }else {
            resultMap.put("check",0);
            return resultMap;
        }

    }


    /**
     * 初始化项目分类人员信息
     */
    @RequestMapping(value = "/authClassifyStaff.html", method = RequestMethod.GET)
    public String authClassifyStaff(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                    @Valid AuthClassifyStaffDTO authClassifyStaffDTO, HttpServletRequest request, WebPage webPage, Model model) {
        webPage.setPageSize(20);
        List<AuthClassifyStaffDTO> getauthClassifyStaffList=authAgencyService.getAuthClassifyStaff(authClassifyStaffDTO,webPage);
        webPage.setRecordCount(getauthClassifyStaffList.size());
        //集团
        Map<String, String> groupList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000000", "0");
        model.addAttribute("groups", groupList);
        if (!StringUtil.isEmpty(authClassifyStaffDTO.getGroupId())) {
            //区域
            Map<String, String> regionList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000001", authClassifyStaffDTO.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if (!StringUtil.isEmpty(authClassifyStaffDTO.getRegionId())) {
            //城市
            Map<String, String> cityList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000003", authClassifyStaffDTO.getRegionId());
            model.addAttribute("citys", cityList);
        }
        if(!StringUtil.isEmpty(authClassifyStaffDTO.getCityId())){
            //项目
            Map<String, String> projectIdList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000002", authClassifyStaffDTO.getCityId());
            Map<String, Object> projectList = organizeService.getProjectNumListProjectId(projectIdList);
            model.addAttribute("proejcts", projectList);
        }

        model.addAttribute("problem", authClassifyStaffDTO);
        model.addAttribute("list", getauthClassifyStaffList);
        return "/authagency/AuthClassifyStaff";

    }
}
