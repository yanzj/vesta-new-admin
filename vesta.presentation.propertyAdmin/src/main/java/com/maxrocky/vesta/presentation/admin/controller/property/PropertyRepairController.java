package com.maxrocky.vesta.presentation.admin.controller.property;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/2/14.
 * 物业报修(后端:工单管理)
 */
@Controller
@RequestMapping(value = "/property")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class PropertyRepairController {
    @Autowired
    private PropertyRepairService propertyRepairService;
    @Autowired
    private PropertyRepairTaskService propertyRepairTaskService;
    @Autowired
    private StaffRegisterService staffRegisterService;
    @Autowired
    private FirstClassificationService firstClassificationService;
    @Autowired
    private SecondClassificationService secondClassificationService;
    @Autowired
    private HouseInfoService houseInfoService;
    @Autowired
    private StaffUserService staffUserService;

    /**
     * 报修列表
     * @param user
     * @param workOrderDetailDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/workOrderManagement.html")
    public String workOrderManage(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                      @Valid WorkOrderDetailDTO workOrderDetailDTO,Model model,WebPage webPage){
        //检索条件回显
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(user.getStaffId(),workOrderDetailDTO.getMenuId());
        model.addAttribute("city", cityList);
        if (null != workOrderDetailDTO.getScopeId() && !"".equals(workOrderDetailDTO.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(user.getStaffId(), workOrderDetailDTO.getScopeId(), workOrderDetailDTO.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
        workOrderDetailDTO.setRoleScopeList(roleScopeList);
        //获取报修信息
        List<WorkOrderDetailDTO> workOrderList=propertyRepairService.repairInfoList(user, workOrderDetailDTO, webPage);
        model.addAttribute("workOrders",workOrderList);
        //工单状态下拉框
        WorkOrderDetailDTO workOrder = new WorkOrderDetailDTO();
        Map<String, String> stateMap = new LinkedHashMap<>();
        stateMap.put("0", "--请选择状态--");
        stateMap.put("1", "已创建");
        stateMap.put("2", "已受理");
        stateMap.put("3", "处理中");
        stateMap.put("4", "已完成");
        //stateMap.put("5", "已关闭");
        stateMap.put("6", "已评价");
        workOrder.setOrderState(stateMap);
        model.addAttribute("stateMaps", workOrder);
        Map<String,String> typeMap=new LinkedHashMap<>();
        typeMap.put("0", "--请选择类型--");
        typeMap.put("1", "报修");
        typeMap.put("2", "投诉");
        workOrder.setOrderType(typeMap);
        Map<String, String> resourceMap = new LinkedHashMap<>();
        resourceMap.put("0", "--请选择来源--");
        resourceMap.put("1", "APP");
        resourceMap.put("2", "微信");
        resourceMap.put("3", "呼叫中心");
        resourceMap.put("4", "网站");
        resourceMap.put("5", "项目前台");
        resourceMap.put("6", "物业单据");
        workOrder.setOrderResource(resourceMap);
        model.addAttribute("typeMaps", workOrder);
        //搜索条件
        model.addAttribute("workOrderSearch",workOrderDetailDTO);
        //记录集合长度，如果没有查询出数据则不可导出
        model.addAttribute("isExcel",workOrderList.size());
        return "/property/PropertyRepair";
    }

    @RequestMapping(value = "/repairManage.html")
    public String repairManage(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                  @Valid WorkOrderDetailDTO workOrderDetailDTO,Model model,WebPage webPage){
        //获取报修信息
        List<WorkOrderDetailDTO> workOrderList=propertyRepairService.repairList(user, workOrderDetailDTO, webPage);
        model.addAttribute("workOrders",workOrderList);
        //工单状态下拉框
        WorkOrderDetailDTO workOrder = new WorkOrderDetailDTO();
        Map<String, String> stateMap = new LinkedHashMap<>();
        stateMap.put("0000", "--请选择状态--");
        stateMap.put("1", "已创建");
        stateMap.put("2", "已受理");
        stateMap.put("3", "处理中");
        stateMap.put("4", "已完成");
        stateMap.put("5", "已关闭");
        stateMap.put("6", "已评价");
        workOrder.setOrderState(stateMap);
        //model.addAttribute("stateMaps", workOrder);
        Map<String,String> typeMap=new LinkedHashMap<>();
        typeMap.put("0000", "--请选择类型--");
        typeMap.put("0", "未完成报修");
        typeMap.put("1", "已完成报修");
        typeMap.put("3", "已删除");
        workOrder.setOrderType(typeMap);

        Map<String,String> buildNum = houseInfoService.getBuildNum();
        workOrder.setBuildingNumMap(buildNum);

        Map<String,String> buildFloor = houseInfoService.getBuildFloor(workOrderDetailDTO.getBuildingNum());
        workOrder.setBuildingFloorMap(buildFloor);

        Map<String,String> firstType = firstClassificationService.getFirstClassification();
        workOrder.setFirstTypeMap(firstType);

        Map<String,String> secondType = secondClassificationService.getSecondClassification(workOrderDetailDTO.getFirstType());
        workOrder.setSecondTypeMap(secondType);

        model.addAttribute("typeMaps", workOrder);
        //搜索条件
        model.addAttribute("workOrderSearch",workOrderDetailDTO);
        return "/property/PropertyRepairManage";
    }

    /**
     * 反馈显示
     * @param workOrderContentDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/feedBack.html")
    public String freeBack(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                           @Valid WorkOrderContentDTO workOrderContentDTO,Model model){
        //获取报修信息
        WorkOrderContentDTO workOrderContent=propertyRepairTaskService.repairFreeBack(user, workOrderContentDTO);
        model.addAttribute("progressInfo",workOrderContent);
        return "/property/RepairFeedBack";
    }

    /**
     * 反馈提交
     * @param workOrderContentDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/feedBack")
    public ApiResult saveFreeBack(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                  @Valid WorkOrderContentDTO workOrderContentDTO,Model model){
        //保存反馈
        String freeBack=propertyRepairTaskService.saveFreeBack(user, workOrderContentDTO);
        return new SuccessApiResult(freeBack);
    }

    /**
     * 分配部门显示
     * @param workApportionDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/assign.html")
    public String assign(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                         @Valid WorkApportionDTO workApportionDTO,Model model){
        //获取工作人员信息
        WorkApportionDTO workerDTO=propertyRepairTaskService.repairsAssign(user, workApportionDTO);
        model.addAttribute("worker", workerDTO);
        //搜索条件
        model.addAttribute("workerInfo",workApportionDTO);
        return "/property/RepairAssign";
    }

    /**
     * 分配人员显示
     * @param departmentId
     * @return
     */
    @RequestMapping(value = "/showWorker")
    public ApiResult showWorker(@Param String departmentId){
        //获取人员
        List<WorkApportionDTO> workerDTO=staffRegisterService.registers(departmentId);
        return new SuccessApiResult(workerDTO);
    }

    /**
     * 分配提交
     * @param workApportionDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveAssign")
    public ApiResult saveAssign(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                @Valid WorkApportionDTO workApportionDTO,Model model){
        String worker=propertyRepairTaskService.saveAssign(user, workApportionDTO);
        return new SuccessApiResult(worker);
    }

    /**
     * 删除
     * @param user
     * @param repairId
     * @return
     */
    @RequestMapping(value = "/removeRepair")
    public ApiResult removeRepair(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                  @RequestParam String repairId){
        String repaired=propertyRepairService.removeRepairById(user, repairId);
        return new SuccessApiResult(repaired);
    }

    /**
     * 详情
     * @param propertyRepairDTO
     * @param model
     * @return
     */
    @RequestMapping(value = "/repairDetail.html")
    public String repairDetail(@ModelAttribute("repairInfo")@Valid PropertyRepairDTO propertyRepairDTO,Model model){
        //获取报修信息
        PropertyRepairDTO repairDTO=propertyRepairService.repairInfo(propertyRepairDTO);
        model.addAttribute("repairs", repairDTO);
        //获取报修进展
        PropertyProgressInfoDTO progressInfo=propertyRepairTaskService.repairProgress(propertyRepairDTO.getId());
        model.addAttribute("progress", progressInfo);
        //Describe:获取报修服务调查问卷
        //CreateBy:WeiYangDong_2017-05-16
        List<Map<String, Object>> repairServiceQuestionList = propertyRepairService.getRepairServiceQuestionList(propertyRepairDTO.getId());
        if (null != repairServiceQuestionList && repairServiceQuestionList.size() > 0){
            model.addAttribute("repairServiceQuestionList",repairServiceQuestionList);
        }
        return "/property/RepairDetail";
    }

    /***
     * 导出excel操作
     * param:user
     * param:httpServletResponse
     * return
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public String exportExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                              @Valid WorkOrderDetailDTO workOrderDetailDTO,
                              HttpServletResponse response, HttpServletRequest request){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            workOrderDetailDTO.setRoleScopeList(roleScopeList);
//            return propertyRepairService.exportExcel(user,workOrderDetailDTO,httpServletResponse,httpServletRequest);
            String fileName = "报事报修管理列表";
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
            String title = "报事报修列表";
            String[] headers = {"序号", "项目名称", "楼号", "单元号", "房号", "负责人", "投诉一级分类", "投诉二级分类", "投诉三级分类", "基本内容","诉求提出人","回访电话","问题详细与处理方案"
                    ,"单据状态","诉求单号","处理方式","责任单位","责任单位2","责任单位3","维修单位","问题程度","创建时间","前台派单给负责人时间","投诉单负责人接单时间","受理人员完工时间"};
            ServletOutputStream out = response.getOutputStream();
            propertyRepairService.exportExcel2(title,headers,out,workOrderDetailDTO,user);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

    /***
     * 导出物业报修服务评价Excel操作
     */
    @RequestMapping("/exportExcelServiceQuestion")
    @ResponseBody
    public String exportExcelServiceQuestion(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                             @Valid WorkOrderDetailDTO workOrderDetailDTO,
                                             HttpServletResponse response, HttpServletRequest request){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            workOrderDetailDTO.setRoleScopeList(roleScopeList);
            String fileName = "报事报修管理列表";
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
            String title = "报事报修列表";
            String[] headers = {"序号", "项目名称", "楼号", "单元号", "房号", "负责人", "投诉一级分类", "投诉二级分类", "投诉三级分类", "基本内容","诉求提出人","回访电话","问题详细与处理方案"
                    ,"单据状态","诉求单号","处理方式","责任单位","责任单位2","责任单位3","维修单位","问题程度","创建时间","前台派单给负责人时间","投诉单负责人接单时间","受理人员完工时间"
                    ,"微信评价时间","维修服务的整体满意程度","报修渠道通畅","响应反馈及时","着装礼仪规范","维修整改高效"
                    ,"保护清洁到位","您觉得还有哪些方面需要改进？"};
            ServletOutputStream out = response.getOutputStream();
            propertyRepairService.exportExcelRepairAllDate(title,headers,out,workOrderDetailDTO,user);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }


    /**
     * 根据楼栋获取楼层
     * @param workOrderDetailDTO
     * @return
     */
    @RequestMapping(value="/getBuildingFloor")
    public ApiResult getBuildingFloor(@Valid WorkOrderDetailDTO workOrderDetailDTO){
        return new SuccessApiResult(houseInfoService.getBuildFloor(workOrderDetailDTO.getContent()));
    }

    /**
     * 根据一级分类获取二级分类
     * @param workOrderDetailDTO
     * @return
     */
    @RequestMapping(value="/getSecondType")
    public ApiResult getSecondType(@Valid WorkOrderDetailDTO workOrderDetailDTO){
        return new SuccessApiResult(secondClassificationService.getSecondClassification(workOrderDetailDTO.getContent()));
    }


}