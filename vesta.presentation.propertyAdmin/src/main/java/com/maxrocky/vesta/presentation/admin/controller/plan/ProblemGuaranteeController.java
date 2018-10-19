package com.maxrocky.vesta.presentation.admin.controller.plan;

import com.maxrocky.vesta.application.DTO.PropertyRectifyAdminDTO;
import com.maxrocky.vesta.application.DTO.PropertyRectifyCRMSelDTO;
import com.maxrocky.vesta.application.DTO.PropertyRepairCRMDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by 27978 on 2016/8/2.
 */
@Controller
@RequestMapping(value = "/problemGuarantee")
@SessionAttributes(types={UserInformationEntity.class,String.class},value = {"userInformationEntity","menulist","secanViewlist"})
public class ProblemGuaranteeController {

    @Autowired
    OrganizeService organizeService;

    @Autowired
    private FirstClassificationService firstClassificationService;

    @Autowired
    private SecondClassificationService secondClassificationService;

    @Autowired
    private ThirdClassificationService thirdClassificationService;

    @Autowired
    HouseProjectService houseProjectService;

    @Autowired
    HouseBuildingService houseBuildingService;

    @Autowired
    HouseUnitService houseUnitService;

    @Autowired
    HouseFloorService houseFloorService;

    @Autowired
    HouseRoomService houseRoomService;

    @Autowired
    PropertyRepairCRMService propertyRepairCRMService;

    @Autowired
    RepairModeService repairModeService;

    @Autowired
    CompanyListService companyListService;

    @Autowired
    SecurityProjectService securityProjectService;

    @Autowired
    AuthAgencyService authAgencyService;

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取报修表列表
    */
    @RequestMapping(value = "/problemGuaranteeManagement.html")
    public String problemGuaranteeManagement(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                                             @Valid PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO){

        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        List<String> userPro = organizeService.getOProjectList(userInformationEntity.getStaffId());
        propertyRectifyCRMSelDTO.setUserProject(userPro);
        // 取得一级分类
        Map<String,String> firstLevel = firstClassificationService.getFirstClassification();
        propertyRectifyCRMSelDTO.setFirstLevels(firstLevel);
        if(null != propertyRectifyCRMSelDTO.getOneType() && !"".equals(propertyRectifyCRMSelDTO.getOneType())){
            // 取得二级分类
            if(!propertyRectifyCRMSelDTO.getOneType().equals("0000")){
                Map<String,String> secondLevel = secondClassificationService.getSecondClassification(propertyRectifyCRMSelDTO.getOneType());
                propertyRectifyCRMSelDTO.setSecondLevels(secondLevel);
            }

        }
        if(null != propertyRectifyCRMSelDTO.getTwoType() && !"".equals(propertyRectifyCRMSelDTO.getTwoType())) {
            // 取得三级分类
            if(!propertyRectifyCRMSelDTO.getTwoType().equals("0000")){
                Map<String, String> thirdLevel = thirdClassificationService.getThirdClassification(propertyRectifyCRMSelDTO.getTwoType());
                propertyRectifyCRMSelDTO.setThirdLevels(thirdLevel);
            }
        }
//        Map<String, String>  projectMap =houseProjectService.getProjects();
//        Map<String, String>  projects = new HashMap<String, String>();
//        projects.put("","请选择项目");
//        for(String projectNum:userPro){
//            projects.put(projectNum,projectMap.get(projectNum));
//        }
//        propertyRectifyCRMSelDTO.setProjects(projects);
        //集团
        Map<String, String> groupList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000000", "0");
        model.addAttribute("groups", groupList);
        if (!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getGroupId())) {
            //区域
            Map<String, String> regionList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000001", propertyRectifyCRMSelDTO.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if (!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getRegionId())) {
            //城市
            Map<String, String> cityList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000003", propertyRectifyCRMSelDTO.getRegionId());
            model.addAttribute("citys", cityList);
        }
        if(!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getCityId())){
            //项目
            Map<String, String> projectIdList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000002", propertyRectifyCRMSelDTO.getCityId());
            Map<String, Object> projectList = organizeService.getProjectNumListProjectId(projectIdList);
            model.addAttribute("proejcts", projectList);
        }
        //取得地块信息
        if(!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getProjectId())){
            Map<String, String> areas=houseBuildingService.getAreaListByProjectNum(propertyRectifyCRMSelDTO.getProjectId());
            propertyRectifyCRMSelDTO.setAreas(areas);
        }
        // 取得楼栋信息------项目id添加
        if(!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getArea())){
            Map<String, String> builds=houseBuildingService.getBuildListByBlockNum(propertyRectifyCRMSelDTO.getArea());
            propertyRectifyCRMSelDTO.setBuildings(builds);
        }
//        // 取得楼栋信息------项目id添加
//        if(null != propertyRectifyCRMSelDTO.getProjectId() && !"".equals(propertyRectifyCRMSelDTO.getProjectId())){
//            Map<String, String> builds = houseBuildingService.getBuildListByProjectId(propertyRectifyCRMSelDTO.getProjectId());
//            propertyRectifyCRMSelDTO.setBuildings(builds);
//        }
        // 取得单元列表
        if(null != propertyRectifyCRMSelDTO.getBuildingId() && !"".equals(propertyRectifyCRMSelDTO.getBuildingId())){
            Map<String, String> units = houseUnitService.getUnitMapByBuildingId(propertyRectifyCRMSelDTO.getBuildingId());
            propertyRectifyCRMSelDTO.setUnits(units);
        }
        // 取得楼层信息
        if(null != propertyRectifyCRMSelDTO.getUnitId() && !"".equals(propertyRectifyCRMSelDTO.getUnitId())){
            Map<String, String> floors = houseFloorService.getRoomsByUnitNum(propertyRectifyCRMSelDTO.getUnitId());
            propertyRectifyCRMSelDTO.setFloors(floors);
        }
        // 取得房间信息
        if(null != propertyRectifyCRMSelDTO.getFloorId() && !"".equals(propertyRectifyCRMSelDTO.getFloorId())){
            Map<String, String> rooms = houseRoomService.getRoomsByFloorNum(propertyRectifyCRMSelDTO.getFloorId());
            propertyRectifyCRMSelDTO.setRooms(rooms);
        }

        //获取报修表列表
//        //根据当前登录人获取项目组权限
//        List<String> projectRole = organizeService.getProjectRole(userPropertystaffEntity.getStaffId());
//        List<String> nullArr = new ArrayList<String>();
//        nullArr.add(null);
//        projectRole.removeAll(nullArr);
//        propertyRectifyCRMSelDTO.setUserProject(projectRole);

        List<PropertyRepairCRMDTO> list= propertyRepairCRMService.getQuestionListNew(propertyRectifyCRMSelDTO, webPage);
        //获取功能点
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有  qch40010084; 搜素 qch40010085    导出Excel qch40010086 详情
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010084":
                        checkAuthFunctionDTO.setQch40010084("Y");
                        break;
                    case "QCH40010085":
                        checkAuthFunctionDTO.setQch40010085("Y");
                        break;
                    case "QCH40010086":
                        checkAuthFunctionDTO.setQch40010086("Y");
                        break;
                }
            }
        }
        model.addAttribute("function", checkAuthFunctionDTO);
        model.addAttribute("problems", list);
        model.addAttribute("typeMaps", propertyRectifyCRMSelDTO);
        model.addAttribute("problem",propertyRectifyCRMSelDTO);
        return "plan/probleamGuarantee";
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 预修改
    */
    /**预修改*/
    @RequestMapping(value = "/preToModifyProblemGuarantee")
    public String preToModifyProblem(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,Model model,@Valid PropertyRectifyAdminDTO propertyRectifyAdminDTO,WebPage webPage){
        //jiaoyan
//        List<String> projectRole = organizeService.getProjectRoleByNum(userPropertystaffEntity.getStaffId(), propertyRectifyAdminDTO.getProjectNum());

        PropertyRepairCRMDTO problem = propertyRepairCRMService.getAdminQuestionById(propertyRectifyAdminDTO,userInformationEntity.getStaffId());
        //PropertyRectifyAdminDTO problem = propertyRepairCRMService.getAdminQuestionByRectifyId(propertyRectifyAdminDTO);

        // 取得一级分类
        Map<String,String> firstLevel = firstClassificationService.getFirstClassification();
        problem.setFirstLevels(firstLevel);

        if(null != problem.getClassifyOne() && !"".equals(problem.getClassifyOne())){
            // 取得二级分类
            Map<String,String> secondLevel = secondClassificationService.getSecondClassification(problem.getClassifyOne());
            problem.setSecondLevels(secondLevel);
        }
        if(null != problem.getClassifyTwo() && !"".equals(problem.getClassifyTwo())) {
            // 取得三级分类
            Map<String, String> thirdLevel = thirdClassificationService.getThirdClassification(problem.getClassifyTwo());
            problem.setThirdLevels(thirdLevel);
        }
        //取得维修方式
        if (null != problem.getClassifyThree() && !"".equals(problem.getClassifyThree())) {
            Map<String,String> repairModes = repairModeService.getRepairModeList(problem.getClassifyThree());
            problem.setRepairModes(repairModes);

            if (null != problem.getProjectId() && !"".equals(problem.getProjectId())) {
                //取得责任单位列表
                Map<String,String> companys = companyListService.getCompanyOnes(problem.getProjectId(), problem.getClassifyThree());
                problem.setCompanys(companys);
            }
        }
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        //获取功能点
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有    qch40010089; //保存
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010089":
                        checkAuthFunctionDTO.setQch40010089("Y");
                        break;
                }
            }
        }
        model.addAttribute("function", checkAuthFunctionDTO);
        model.addAttribute("typeMaps", problem);
        model.addAttribute("problem",problem);

        if("accepted".equals(problem.getCaseState())){
            return "plan/problemAcceptedDetail";
        }else{
            return "plan/problemProcessingDetail";
        }
    }

    /** 获取详情*/
    @RequestMapping(value = "/getProblemGuaranteeManagementDetail")
    public String getProblemManagementDetail(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,
                                             Model model,@Valid PropertyRectifyAdminDTO propertyRectifyAdminDTO,WebPage webPage){
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        //这里只需要获取id，所以与参数中的dto并没有多大关系
        PropertyRepairCRMDTO propertyRepairCRMDTO = propertyRepairCRMService.getAdminQuestionById(propertyRectifyAdminDTO,userInformationEntity.getStaffId());
        //获取功能点
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有   qch40010087; //废弃   qch40010088; //处理    qch40010089; //保存
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010087":
                        checkAuthFunctionDTO.setQch40010087("Y");
                        break;
                    case "QCH40010088":
                        checkAuthFunctionDTO.setQch40010088("Y");
                        break;
                }
            }
        }
        model.addAttribute("problem", propertyRepairCRMDTO);
        model.addAttribute("function", checkAuthFunctionDTO);
        //根据状态获取不同的详情页面
        if("completed".equals(propertyRepairCRMDTO.getCaseState())){
            return "plan/problemGuaranteeDetail2";
        }else{
            return "plan/problemGuaranteeDetail";
        }
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 接受任务
     */
    @RequestMapping(value = "/acceptTask")
    public ModelAndView acceptTask(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,Model model,
                               @Valid PropertyRepairCRMDTO problem) throws UnsupportedEncodingException {
        propertyRepairCRMService.acceptTask(userInformationEntity, problem);
        return new ModelAndView("redirect:../problemGuarantee/problemGuaranteeManagement.html?proType=11");
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 提交
    */
    @RequestMapping(value = "/tijiao")
    public ModelAndView tijiao(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,Model model,
                                       @Valid PropertyRepairCRMDTO problem) throws UnsupportedEncodingException {
        propertyRepairCRMService.tijiao(problem);
        return new ModelAndView("redirect:../problemGuarantee/problemGuaranteeManagement.html?proType=11");
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Description: 保存
     */
    @RequestMapping(value = "/saveRepair")
    public ModelAndView saveRepair(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,Model model,
                               @Valid PropertyRepairCRMDTO problem) throws UnsupportedEncodingException {
        propertyRepairCRMService.saveRepair(problem);
        return new ModelAndView("redirect:../problemGuarantee/problemGuaranteeManagement.html?proType=11");
    }

    @RequestMapping(value="/getGuaranteeSecondTypeList")
    public ApiResult getSecondTypeList(@Valid PropertyRepairCRMDTO problem){
        return new SuccessApiResult(secondClassificationService.getSecondClassification(problem.getOneType()));
    }

    @RequestMapping(value="/getGuaranteeThirdTypeList")
    public ApiResult getThirdTypeList(@Valid PropertyRepairCRMDTO problem){
        return new SuccessApiResult(thirdClassificationService.getThirdClassification(problem.getTwoType()));
    }

    @RequestMapping(value="/getRepairModeList")
    public ApiResult getRepairModeList(@Valid PropertyRepairCRMDTO problem){
        return new SuccessApiResult(repairModeService.getRepairModeList(problem.getThreeType()));
    }

    @RequestMapping(value="/getStandardDate")
    public ApiResult getStandardDate(@Valid PropertyRepairCRMDTO problem){
        return new SuccessApiResult(repairModeService.getStandardDate(problem.getRepairMode()));
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 作废
    */
    @RequestMapping(value = "/deleteRepair")
    public ModelAndView deleteQeustion(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,Model model,
                                       @Valid PropertyRectifyAdminDTO propertyRectifyAdminDTO) throws UnsupportedEncodingException {
        propertyRepairCRMService.deleteAdminQeustion(propertyRectifyAdminDTO);
        return new ModelAndView("redirect:../problemGuarantee/problemGuaranteeManagement.html?proType=11");
    }

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 导出excel
    */
    @RequestMapping(value="/guaranteeExportExcel")
    @ResponseBody
    public String guaranteeExportExcel(@ModelAttribute("authPropertystaff")UserInformationEntity user,
                              HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest,PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, Model model, WebPage webPage){
        try {
            List<String> userPro = organizeService.getOProjectList(user.getStaffId());
            propertyRectifyCRMSelDTO.setUserProject(userPro);
            return propertyRepairCRMService.exportExcel(user,httpServletResponse,propertyRectifyCRMSelDTO,webPage,httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }
    /**
     * 导出EXCEL
     *
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/propertyRepairExportExcels", method = RequestMethod.GET)
    public void propertyRepairExportExcels(@ModelAttribute("authPropertystaff")UserInformationEntity user,HttpServletRequest request, HttpServletResponse response,PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, Model model, WebPage webPage) throws Exception {
        String fileName = "物业报修单";
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
        String title = "物业报修单EXCEL文档";
        String[] headers = {"序号", "问题描述", "位置","一级分类","二级分类","三级分类","维修责任人","登记时间","状态"};
        ServletOutputStream out = response.getOutputStream();
        List<String> userPro = organizeService.getOProjectList(user.getStaffId());
        propertyRectifyCRMSelDTO.setUserProject(userPro);
        propertyRepairCRMService.propertyRepairExportExcels(title, headers, out, propertyRectifyCRMSelDTO, webPage);
    }
}
