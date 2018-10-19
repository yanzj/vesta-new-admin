package com.maxrocky.vesta.presentation.admin.controller.role;

import com.maxrocky.vesta.application.adminDTO.SupplierDTO;
import com.maxrocky.vesta.application.dto.adminDTO.*;
import com.maxrocky.vesta.application.dto.adminDTO.batch.AgencyReceiveDTO;
import com.maxrocky.vesta.application.inf.AgencyService;
import com.maxrocky.vesta.application.inf.SupplierService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.utility.StringUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by chen on 2016/6/29.
 * 组织机构管理
 */
@Controller
@RequestMapping(value = "/agency")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class AgencyController {
    @Autowired
    AgencyService agencyService;
    @Autowired
    SupplierService supplierService;

    /**
     * 组织树信息
     * */
    @RequestMapping(value = "/initAgency",method = RequestMethod.POST)
    public void initAgency(HttpServletResponse response,@Valid AgencyTreeDTO agencyTreeDTO) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList;
        if(agencyTreeDTO.getId()!=null&&!StringUtil.isEmpty(agencyTreeDTO.getId())){
            agencyTreeDTOList = agencyService.getAgencyList(agencyTreeDTO.getId());
        }else{
            agencyTreeDTOList = agencyService.getAgencyRootList();
        }
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
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
        List<AgencyTreeDTO> agencyTreeDTOList = agencyService.getAgencyFullList();
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }

    /**
     * 获取下级树信息
     * */
    @RequestMapping(value = "/nextAgency",method = RequestMethod.GET)
    public ApiResult nextAgency(@RequestParam(value = "id")String agencyId,@RequestParam(value = "type")String agencyType){
        List<AgencyTreeDTO> agencyTreeDTOs = agencyService.getAgencyNext(agencyId,agencyType);
        return new SuccessApiResult(agencyTreeDTOs);
    }

    /**获取下级部门*/
    @RequestMapping(value = "/nextDepartment",method = RequestMethod.GET)
    public ApiResult nextDepartment(@RequestParam(value = "id")String agencyId){
        List<AgencyTreeDTO> agencyTreeDTOs = agencyService.getAgencyFor1(agencyId);
        return new SuccessApiResult(agencyTreeDTOs);
    }

    /**
     * 组织详情页
     * */
    @RequestMapping(value = "/agencyDetail.html")
    public String agencyDetail(@RequestParam(value = "agencyId")String agencyId, Model model){
        AgencyDTO agencyDTO = agencyService.getAgencyDetail(agencyId);
        SupplierDTO supplierDTO = new SupplierDTO();
        List<AppRolesetDTO> appRolesetDTOs = agencyService.getAppRoleSetByAgencyId(agencyId);   //组织机构关联的角色
        List<OrganizeProjectDTO> organizeProjectDTOs = agencyService.getProjectByAgencyId(agencyId); //组织机构关联的项目
        if(!StringUtil.isEmpty(agencyDTO.getSupplierId())){
            supplierDTO = supplierService.getSupplierDetail(agencyDTO.getSupplierId());
        }
        model.addAttribute("appRoleSetList",appRolesetDTOs);
        model.addAttribute("projectList",organizeProjectDTOs);
        model.addAttribute("agency", agencyDTO);
        model.addAttribute("supplier",supplierDTO);
        return "role/AgencyDetail";
    }

    /**组织机构管理页面*/
    @RequestMapping(value = "/agencyManage.html")
    public String agencyManage(Model model){
        return "role/AgencyManage";
    }

//    /**新增根级组织机构*/
//    @RequestMapping(value = "/addRootAgency.html")
//    public String addRootAgency(@Valid AgencyAdminDTO agencyAdminDTO){
//        agencyService.addAgency(agencyAdminDTO);
//        return "redirect:../agency/agencyManage.html";
//    }

    /**供应商列表*/
    @RequestMapping(value = "/supplierAdmin",produces = "application/json",method = RequestMethod.GET)
    public ApiResult getSuppliers(@RequestParam(value = "supplierName")String supplierName){
        return supplierService.getSuplliers(supplierName);
    }

    /**跳转新增 编辑组织机构页面*/
    @RequestMapping(value = "/toAddAgency.html")
    public String toAddAgency(@RequestParam(value = "agencyId",required = false)String agencyId,Model model,HttpServletRequest request){
        AgencyDTO agencyDTO1 = new AgencyDTO();
        AgencyDTO agencyDTO = new AgencyDTO();
        Cookie[] cookies = request.getCookies();
        if(!StringUtil.isEmpty(agencyId)){
            agencyDTO = agencyService.getAgencyDetail(agencyId);
            agencyDTO1 = agencyService.getAgencyDetail(agencyDTO.getParentId());
        }else{
            for (int i = 0; i < cookies.length; i++) {
                Cookie cook = cookies[i];
                if (cook.getName().equalsIgnoreCase("agencyId")&& !StringUtil.isEmpty(cook.getValue())) { //获取当前选中的节点
                    agencyDTO1 = agencyService.getAgencyDetail(cook.getValue());
                }
            }
        }
        List<AppRolesetDTO> appRolesetDTOs = agencyService.getAppRoleSetByAgencyId(agencyId);   //组织机构关联的角色
        List<OrganizeProjectDTO> organizeProjectDTOs = agencyService.getProjectByAgencyId(agencyId); //组织机构关联的项目
        List<SupplierDTO> supplierDTOs = supplierService.getSupplierList();      //供应商列表
        model.addAttribute("roleDTO", appRolesetDTOs);
        model.addAttribute("projectDTO",organizeProjectDTOs);
        model.addAttribute("agency",agencyDTO);
        model.addAttribute("pAgency",agencyDTO1);
        model.addAttribute("supplierList",supplierDTOs);
        return "role/AddAgency";
    }

    /**新增组织机构*/
    @RequestMapping(value = "/addAgency",method = RequestMethod.POST)
    public ApiResult addAgency(@Valid AgencyReceiveDTO agencyAdminDTO){
        if(StringUtil.isEmpty(agencyAdminDTO.getAgencyId())){
            return agencyService.addAgency(agencyAdminDTO);
        }else {
            return agencyService.updateAgency(agencyAdminDTO);
        }
    }

    /**编辑组织机构*/
    @RequestMapping(value = "/editAgency",method = RequestMethod.GET)
    public ApiResult editAgency(@Valid AgencyReceiveDTO agencyReceiveDTO){
//        if(agencyAdminDTO.getAgencyType().equals("3")){
//            return new ErrorApiResult(22,"用户信息不可在此修改！");
//        }
        agencyService.updateAgency(agencyReceiveDTO);
        return new SuccessApiResult("ok");
    }

    /**删除组织机构*/
    @RequestMapping(value = "/delAgency.html",method = RequestMethod.GET)
    public String delAgency(@Valid AgencyTreeDTO agencyTreeDTO){
        agencyService.delAgency(agencyTreeDTO);
        return "redirect:../user/userStaffManage.html";
    }

    /**根据组织机构ID获取人员列表*/
    @RequestMapping(value = "/agencyPeople",method = RequestMethod.GET)
    public ApiResult agencyPeople(@RequestParam(value = "agencyId")String agencyId){
        return agencyService.getStaffListByAgency(agencyId);
    }

    /**跳转新增组织机构人员页面*/
    @RequestMapping(value = "/addAgencyPeople.html")
    public String addAgencyPeople(@Valid AgencyAdminDTO agencyAdminDTO,HttpServletRequest request,Model model){
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cook = cookies[i];
            if (cook.getName().equalsIgnoreCase("agencyId")&& !StringUtil.isEmpty(cook.getValue())) { //获取当前选中的节点
                AgencyDTO agencyDTO1 = agencyService.getAgencyDetail(cook.getValue());
                agencyAdminDTO.setAgencyId(agencyDTO1.getAgencyId());
                agencyAdminDTO.setAgencyType(agencyDTO1.getAgencyType());
            }
        }
        if(agencyAdminDTO.getAgencyType().equals("1")){
            List<StaffDTO> staffDTOs =agencyService.getAgencyOutStaff(agencyAdminDTO);
            model.addAttribute("agencyId",agencyAdminDTO.getAgencyId());
            model.addAttribute("staffList",staffDTOs);
            model.addAttribute("nikeName",agencyAdminDTO.getParentId());
            model.addAttribute("staffName",agencyAdminDTO.getAgencyName());
            return "role/AgencyPeople";
        }
        return "redirect:../agency/agencyManage.html";
    }

    /**新增组织机构人员关系*/
    @RequestMapping(value = "/saveAP.html")
    public String saveAP(@Valid AgencyStaffDTO agencystaffDTO){
        if(!StringUtil.isEmpty(agencystaffDTO.getStaffId())){
            agencyService.saveAgencyStaff(agencystaffDTO);
        }
        return "redirect:../user/userStaffManage.html";
    }

    /** 批量导入*/
    @RequestMapping(value = "/ExcelAddPeople.html")
    public String ExcelAddPeople(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertyStaffEntity, HttpServletRequest httpServletRequest){
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("myfile");
            InputStream fis = file.getInputStream();
            //POI:得到解析Excel的实体集合
            String result = agencyService.importPeopleExcel(fis, userPropertyStaffEntity);
            httpServletRequest.getSession().setAttribute("result", result);
            //关闭流
            fis.close();
            return "redirect:../user/userStaffManage.html";
        } catch (Exception e) {
            e.printStackTrace();
            httpServletRequest.getSession().setAttribute("result", "导入失败！");
            return "redirect:../user/userStaffManage.html";
        }
    }

    /**
     * @param httpServletRequest
     * @param httpServletResponse
     * @return 机构与人员的下载模板
     */
    @RequestMapping(value = "/exportPeopleModel")
    @ResponseBody
    public String exportPeopleModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            return agencyService.exportPeopleModel(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }

}
