package com.maxrocky.vesta.presentation.admin.controller.plan;

import com.maxrocky.vesta.application.DTO.*;
import com.maxrocky.vesta.application.DTO.admin.*;
import com.maxrocky.vesta.application.DTO.jsonDTO.AllClassificationDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.BuildingAliasDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.SearchClassificationDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.ThirdClassificationCommDTO;
import com.maxrocky.vesta.application.adminDTO.ProblemDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.*;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.HouseOpenEntity;
import com.maxrocky.vesta.domain.model.InternalacceptanceHouseEntity;
import com.maxrocky.vesta.domain.model.PropertyImageEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.repository.HouseTransferRepository;
import com.maxrocky.vesta.domain.repository.PropertyImageRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.json.JSONArray;
import org.json.JSONObject;
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
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lpc on 2016/6/5.
 * 问题
 */
@Controller
@RequestMapping(value = "/problem")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class ProblemController {
    @Autowired
    PlanInfoService planInfoService;
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
    HouseTypeService houseTypeService;

    @Autowired
    HouseRoomService houseRoomService;

    @Autowired
    ImgService imgService;
    @Autowired
    PropertyRectifyCRMService propertyRectifyService;

    @Autowired
    SupplierService supplierService;

    @Autowired
    OrganizeService organizeService;

    @Autowired
    HouseTypeLabelService houseTypeLabelService;

    @Autowired
    HouseRoomTypeService houseRoomTypeService;

    @Autowired
    HouseTransferService houseTransferService;

    @Autowired
    RectificationService rectificationService;

    @Autowired
    PropertyImageRepository propertyImageRepository;
    @Autowired
    private HouseTransferRepository houseTransferRepository;
    @Autowired
    SecurityProjectService securityProjectService;
    @Autowired
    AuthAgencyService authAgencyService;

    /**
     * 问题管理页
     */

    @RequestMapping(value = "/problemManagement.html")
    public String problemManagement(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                                    @Valid PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO) {
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
//        List<String> userPro = organizeService.getOProjectList(userInformationEntity.getStaffId());
//        propertyRectifyCRMSelDTO.setUserProject(userPro);
        // 取得一级分类
        Map<String, String> firstLevel = firstClassificationService.getFirstClassification();
        propertyRectifyCRMSelDTO.setFirstLevels(firstLevel);
        if (null != propertyRectifyCRMSelDTO.getOneType() && !"".equals(propertyRectifyCRMSelDTO.getOneType())) {
            // 取得二级分类
            if (!propertyRectifyCRMSelDTO.getOneType().equals("0000")) {
                Map<String, String> secondLevel = secondClassificationService.getSecondClassification(propertyRectifyCRMSelDTO.getOneType());
                propertyRectifyCRMSelDTO.setSecondLevels(secondLevel);
            }

        }
        if (null != propertyRectifyCRMSelDTO.getTwoType() && !"".equals(propertyRectifyCRMSelDTO.getTwoType())) {
            // 取得三级分类
            if (!propertyRectifyCRMSelDTO.getTwoType().equals("0000")) {
                Map<String, String> thirdLevel = thirdClassificationService.getThirdClassification(propertyRectifyCRMSelDTO.getTwoType());
                propertyRectifyCRMSelDTO.setThirdLevels(thirdLevel);
            }
        }
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
        Map<String, String> projectMap = houseProjectService.getProjects();
        Map<String, String> projects = new HashMap<String, String>();
//        projects.put("", "请选择项目");
//        for (String projectNum : userPro) {
//            projects.put(projectNum, projectMap.get(projectNum));
//        }
//        propertyRectifyCRMSelDTO.setProjects(projects);
        //取得地块信息
        if (!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getProjectId())) {
            Map<String, String> areas = houseBuildingService.getAreaListByProjectNum(propertyRectifyCRMSelDTO.getProjectId());
            propertyRectifyCRMSelDTO.setAreas(areas);
        }
        // 取得楼栋信息------项目id添加
        if (!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getArea())) {
            Map<String, String> builds = houseBuildingService.getBuildListByBlockNum(propertyRectifyCRMSelDTO.getArea());
            propertyRectifyCRMSelDTO.setBuildings(builds);
        }
//        if(null != propertyRectifyCRMSelDTO.getProjectId() && !"".equals(propertyRectifyCRMSelDTO.getProjectId())){
//            Map<String, String> builds = houseBuildingService.getBuildListByProjectId(propertyRectifyCRMSelDTO.getProjectId());
//            propertyRectifyCRMSelDTO.setBuildings(builds);
//        }
        // 取得单元列表
        if (null != propertyRectifyCRMSelDTO.getBuildingId() && !"".equals(propertyRectifyCRMSelDTO.getBuildingId())) {
            Map<String, String> units = houseUnitService.getUnitMapByBuildingId(propertyRectifyCRMSelDTO.getBuildingId());
            propertyRectifyCRMSelDTO.setUnits(units);
        }
        // 取得楼层信息
        if (null != propertyRectifyCRMSelDTO.getUnitId() && !"".equals(propertyRectifyCRMSelDTO.getUnitId())) {
            Map<String, String> floors = houseFloorService.getRoomsByUnitNum(propertyRectifyCRMSelDTO.getUnitId());
            propertyRectifyCRMSelDTO.setFloors(floors);
        }
        // 取得房间信息
        if (null != propertyRectifyCRMSelDTO.getFloorId() && !"".equals(propertyRectifyCRMSelDTO.getFloorId())) {
            Map<String, String> rooms = houseRoomService.getRoomsByFloorNum(propertyRectifyCRMSelDTO.getFloorId());
            propertyRectifyCRMSelDTO.setRooms(rooms);
        }

        //供应商列表
        if (!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getSupplier())) {
            String supplier = supplierService.getSupplierByName(propertyRectifyCRMSelDTO.getSupplier());
            propertyRectifyCRMSelDTO.setSupplier(supplier);
        }
        if (!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getProjectId())) {
            Map<String, String> planList = propertyRectifyService.getPlanByProjectNum(propertyRectifyCRMSelDTO.getProjectId(), propertyRectifyCRMSelDTO.getProType());
            propertyRectifyCRMSelDTO.setPlanList(planList);
        }
        //供应商添加模糊查询
        List<PropertyRectifyCRMListDTO> list = propertyRectifyService.getQuestionList(propertyRectifyCRMSelDTO, webPage);
        //获取功能点
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有内部预验的：
            //  qch40010001 :搜素 ,qch40010002 :新增问题 ,qch40010003 :导出Excel ,qch40010004 :批量提交草稿 qch40010007 :详情 ,
            //工地开放的：
            //  qch40010018 搜素 ,qch40010019 新增问题 ,qch40010020 导出Excel ,qch40010021 批量提交草稿 qch40010024 详情
            // 正式交房的：
            //  qch40010035：搜素 qch40010036：新增问题 qch40010037：导出Excel qch40010038：批量提交草稿 qch40010041 详情
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010001":
                        checkAuthFunctionDTO.setQch40010001("Y");
                        break;
                    case "QCH40010002":
                        checkAuthFunctionDTO.setQch40010002("Y");
                        break;
                    case "QCH40010003":
                        checkAuthFunctionDTO.setQch40010003("Y");
                        break;
                    case "QCH40010004":
                        checkAuthFunctionDTO.setQch40010004("Y");
                        break;
                    case "QCH40010007":
                        checkAuthFunctionDTO.setQch40010007("Y");
                        break;
                    case "QCH40010018":
                        checkAuthFunctionDTO.setQch40010018("Y");
                        break;
                    case "QCH40010019":
                        checkAuthFunctionDTO.setQch40010019("Y");
                        break;
                    case "QCH40010020":
                        checkAuthFunctionDTO.setQch40010020("Y");
                        break;
                    case "QCH40010021":
                        checkAuthFunctionDTO.setQch40010021("Y");
                        break;
                    case "QCH40010024":
                        checkAuthFunctionDTO.setQch40010024("Y");
                        break;
                    case "QCH40010035":
                        checkAuthFunctionDTO.setQch40010035("Y");
                        break;
                    case "QCH40010036":
                        checkAuthFunctionDTO.setQch40010036("Y");
                        break;
                    case "QCH40010037":
                        checkAuthFunctionDTO.setQch40010037("Y");
                        break;
                    case "QCH40010038":
                        checkAuthFunctionDTO.setQch40010038("Y");
                        break;
                    case "QCH40010041":
                        checkAuthFunctionDTO.setQch40010041("Y");
                        break;
                }
            }
        }
        model.addAttribute("problems", list);
        model.addAttribute("typeMaps", propertyRectifyCRMSelDTO);
        model.addAttribute("problem", propertyRectifyCRMSelDTO);
        model.addAttribute("function", checkAuthFunctionDTO);
        if ("11".equals(propertyRectifyCRMSelDTO.getProType())) {
            return "plan/problemManagement";
        } else if ("12".equals(propertyRectifyCRMSelDTO.getProType())) {
            return "plan/problemManagement12";
        } else {
            return "plan/problemManagement13";
        }

    }

    /**
     * 问题管理页
     */

    @RequestMapping(value = "/problemPrint.html")
    public String problemPrint(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                               @Valid PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO) {
        String roomId = propertyRectifyCRMSelDTO.getRoomId();
        List<String> userPro = organizeService.getOProjectList(userInformationEntity.getStaffId());
        propertyRectifyCRMSelDTO.setUserProject(userPro);
        Map<String, String> projectMap = houseProjectService.getProjects();
        Map<String, String> projects = new HashMap<String, String>();
        projects.put("", "请选择项目");
        for (String projectNum : userPro) {
            projects.put(projectNum, projectMap.get(projectNum));
        }
        propertyRectifyCRMSelDTO.setProjects(projects);
        // 取得楼栋信息------项目id添加
        if (null != propertyRectifyCRMSelDTO.getProjectId() && !"".equals(propertyRectifyCRMSelDTO.getProjectId())) {
            Map<String, String> builds = houseBuildingService.getBuildListByProjectId(propertyRectifyCRMSelDTO.getProjectId());
            propertyRectifyCRMSelDTO.setBuildings(builds);
        }
        // 取得单元列表
        if (null != propertyRectifyCRMSelDTO.getBuildingId() && !"".equals(propertyRectifyCRMSelDTO.getBuildingId())) {
            Map<String, String> units = houseUnitService.getUnitMapByBuildingId(propertyRectifyCRMSelDTO.getBuildingId());
            propertyRectifyCRMSelDTO.setUnits(units);
        }
        // 取得楼层信息
        if (null != propertyRectifyCRMSelDTO.getUnitId() && !"".equals(propertyRectifyCRMSelDTO.getUnitId())) {
            Map<String, String> floors = houseFloorService.getRoomsByUnitNum(propertyRectifyCRMSelDTO.getUnitId());
            propertyRectifyCRMSelDTO.setFloors(floors);
        }
        // 取得房间信息
        if (null != propertyRectifyCRMSelDTO.getFloorId() && !"".equals(propertyRectifyCRMSelDTO.getFloorId())) {
            Map<String, String> rooms = houseRoomService.getRoomsByFloorNum(propertyRectifyCRMSelDTO.getFloorId());
            propertyRectifyCRMSelDTO.setRooms(rooms);
        }
        webPage = null;
        if (null == roomId) {
        } else {
            List<PropertyRectifyCRMListDTO> list = propertyRectifyService.getQuestionList(propertyRectifyCRMSelDTO, webPage);
            model.addAttribute("problems", list);
        }
        model.addAttribute("typeMaps", propertyRectifyCRMSelDTO);
        model.addAttribute("problem", propertyRectifyCRMSelDTO);
        if ("11".equals(propertyRectifyCRMSelDTO.getProType())) {
            return "plan/problemPrint";
        } else if ("12".equals(propertyRectifyCRMSelDTO.getProType())) {
            return "plan/problemPrint12";
        } else {
            return "plan/problemPrint13";
        }

    }

    /***
     * 导出excel操作
     * param:user
     * param:httpServletResponse
     * return
     */
    @RequestMapping(value = "/exportExcel")
    @ResponseBody
    public String exportExcel(@ModelAttribute("authPropertystaff") UserInformationEntity user,
                              HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest, PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, Model model, WebPage webPage) {
        try {
            List<String> userPro = organizeService.getOProjectList(user.getStaffId());
            propertyRectifyCRMSelDTO.setUserProject(userPro);
            String fileName = "问题清单";
            httpServletResponse.setContentType("application/x-xls");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            String agent = httpServletRequest.getHeader("USER-AGENT").toLowerCase();
            if (agent.contains("firefox")) {
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
            } else {
                httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
            }

            ServletOutputStream out = httpServletResponse.getOutputStream();
            return propertyRectifyService.exportNewExcel(propertyRectifyCRMSelDTO, out);
//
//            List<String> userPro = organizeService.getOProjectList(user.getStaffId());
//            propertyRectifyCRMSelDTO.setUserProject(userPro);
//            return propertyRectifyService.exportExcel(user,httpServletResponse,propertyRectifyCRMSelDTO,webPage,httpServletRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }

    }

    @RequestMapping(value = "/getSecondTypeList")
    public ApiResult getSecondTypeList(@Valid PropertyRectifyAdminDTO problem) {
        return new SuccessApiResult(secondClassificationService.getSecondClassification(problem.getClassifyOne()));
    }

    @RequestMapping(value = "/getThirdTypeList")
    public ApiResult getThirdTypeList(@Valid PropertyRectifyAdminDTO problem) {
        return new SuccessApiResult(thirdClassificationService.getThirdClassification(problem.getClassifyTwo()));
    }

    //专门为常用三级分类确定使用
    @RequestMapping(value = "/getThirdTypeListNew")
    public ApiResult getThirdTypeListNew(@Valid PropertyRectifyAdminDTO problem) {
        return new SuccessApiResult(thirdClassificationService.getThirdClassificationNew(problem.getClassifyTwo()));
    }


    @RequestMapping(value = "/getSupplierList")
    public ApiResult getSupplierList(@Valid PropertyRectifyAdminDTO problem) {
        return new SuccessApiResult(supplierService.getSupplierByProjectNumAndThirdId(problem.getProjectNum(), problem.getClassifyThree()));
    }

    @RequestMapping(value = "/getProblmetype")
    public ApiResult getProblmetype(@Valid PropertyRectifyAdminDTO problem) {
        return new SuccessApiResult(propertyRectifyService.getDescriptionByThirdId(problem.getClassifyThree()));
    }

    @RequestMapping(value = "/getSupplier")
    public ApiResult getSupplier(@Valid PropertyRectifyAdminDTO problem) {
        return new SuccessApiResult(supplierService.getSupplierByProjectNumAndThirdId(problem.getProjectNum(), problem.getClassifyThree()));
    }

    /**
     * 按项目获取当前项目下 批次列表
     */
    @RequestMapping(value = "/getPlanNumList")
    public ApiResult getPlanNumList(@Valid PropertyRectifyAdminDTO problem) {
        return new SuccessApiResult(propertyRectifyService.getPlanByProjectNum(problem.getProjectNum(), problem.getProblemType()));
    }

    /**
     * 根据房间编码获取户型信息
     *
     * @param houseRoomTypeDTO
     * @return
     */
    @RequestMapping(value = "/getHouseType")
    public ApiResult getRoomList(@Valid HouseRoomTypeDTO houseRoomTypeDTO) {
        HouseTypeDTO houseTypeDTO = houseRoomTypeService.getHouseRoomTypeUrlByRoomNum(houseRoomTypeDTO.getRoomNum());
        return new SuccessApiResult(houseTypeDTO);
    }


    /**
     * 预添加
     */
    @RequestMapping(value = "/preAddProblemManagement")
    public String preAddProblemManagement(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, @Valid PropertyRectifyAdminDTO problem, WebPage webPage) {

        // 取得一级分类
        Map<String, String> firstLevel = firstClassificationService.getFirstClassification();
        problem.setFirstLevels(firstLevel);

        if (null != problem.getClassifyOne() && !"".equals(problem.getClassifyOne())) {
            // 取得二级分类
            Map<String, String> secondLevel = secondClassificationService.getSecondClassification(problem.getClassifyOne());
            problem.setSecondLevels(secondLevel);
        }
        if (null != problem.getClassifyTwo() && !"".equals(problem.getClassifyTwo())) {
            // 取得三级分类
            Map<String, String> thirdLevel = thirdClassificationService.getThirdClassification(problem.getClassifyTwo());
            problem.setThirdLevels(thirdLevel);
        }
        if (null != problem.getClassifyThree() && !"".equals(problem.getClassifyThree())) {
            // 取得简要描述
            Map<String, String> problemTypeMap = propertyRectifyService.getDescriptionByThirdId(problem.getClassifyThree());
            problem.setProblemtypes(problemTypeMap);
        }

        Map<String, String> projectMap = houseProjectService.getProjects();
//        Map<String, String> projects = new HashMap<String, String>();
//        List<String> userPro = organizeService.getOProjectList(userInformationEntity.getStaffId());
//        projects.put("", "请选择项目");
//        for (String projectNum : userPro) {
//            projects.put(projectNum, projectMap.get(projectNum));
//        }
        problem.setProjects(projectMap);
        // 取得楼栋信息
        if (null != problem.getProjectNum() && !"".equals(problem.getProjectNum())) {
            Map<String, String> builds = houseBuildingService.getBuildListByProjectId(problem.getProjectNum());
            problem.setBuildings(builds);
        }

        // 取得单元列表
        if (null != problem.getBuildingNum() && !"".equals(problem.getBuildingNum())) {
            Map<String, String> units = houseUnitService.getUnitMapByBuildingId(problem.getBuildingNum());
            problem.setUnits(units);
        }
        // 取得楼层信息
        if (null != problem.getUnitNum() && !"".equals(problem.getUnitNum())) {
            Map<String, String> floors = houseFloorService.getRoomsByUnitNum(problem.getUnitNum());
            problem.setFloors(floors);
        }
        // 取得房间信息
        if (null != problem.getFloorNum() && !"".equals(problem.getFloorNum())) {
            Map<String, String> rooms = houseRoomService.getRoomsByFloorNum(problem.getFloorNum());
            problem.setRooms(rooms);
        }

        if (problem.getProjectNum() != null && !"".equals(problem.getProjectNum()) && problem.getClassifyThree() != null && !"".equals(problem.getClassifyThree())) {
            problem.setSupplierMap(supplierService.getSupplierByProjectNumAndThirdId(problem.getProjectNum(), problem.getClassifyThree()));
        }
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        //获取功能点
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有内部预验的
            //  qch40010005; 保存 ,qch40010006 :新增问题-提交 ,
            // 工地开放的
            //qch40010022;  保存  qch40010023;  新增问题-提交
            //正式交房的
            // qch40010039：保存  qch40010040：新增问题-提交
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010005":
                        checkAuthFunctionDTO.setQch40010005("Y");
                        break;
                    case "QCH40010006":
                        checkAuthFunctionDTO.setQch40010006("Y");
                        break;
                    case "QCH40010022":
                        checkAuthFunctionDTO.setQch40010022("Y");
                        break;
                    case "QCH40010023":
                        checkAuthFunctionDTO.setQch40010023("Y");
                        break;
                    case "QCH40010039":
                        checkAuthFunctionDTO.setQch40010039("Y");
                        break;
                    case "QCH40010040":
                        checkAuthFunctionDTO.setQch40010040("Y");
                        break;
                }
            }
        }

        model.addAttribute("typeMaps", problem);
        model.addAttribute("function", checkAuthFunctionDTO);
        model.addAttribute("problem", problem);

        if ("11".equals(problem.getPlanType())) {
            return "plan/problemAdd";
        } else if ("12".equals(problem.getPlanType())) {
            return "plan/problemAdd12";
        } else {
            return "plan/problemAdd13";
        }

    }


    /**
     * 添加
     *
     * @param userInformationEntity
     * @param model
     * @param problem
     * @return
     */
    @RequestMapping(value = "/addProblemManagement")
    public ModelAndView addProblemManagement(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                                             @Valid PropertyRectifyAdminDTO problem) {

        problem.setCreateBy(userInformationEntity.getStaffId());
        problem.setCreatePhone(userInformationEntity.getMobile());
        /*
        List<String> list = new ArrayList<>();
        //判断file数组不能为空并且长度大于0
        if(files!=null&&files.length>0){
            //循环获取file数组中得文件
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                String fileName = imgService.uploadAdminImage(file, ImgType.ACTIVITY);
                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");

                list.add(fileName);

            }
        }*/
        propertyRectifyService.saveAdminQeustion(problem, userInformationEntity.getStaffName());

        return new ModelAndView("redirect:../problem/problemManagement.html?proType=" + problem.getPlanType());
    }


    /**
     * 预修改
     */
    @RequestMapping(value = "/preToModifyProblem")
    public String preToModifyProblem(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, @Valid PropertyRectifyAdminDTO propertyRectifyAdminDTO, WebPage webPage) {


        PropertyRectifyAdminDTO problem = propertyRectifyService.getAdminQuestionByRectifyId(propertyRectifyAdminDTO);

        // 取得一级分类
        Map<String, String> firstLevel = firstClassificationService.getFirstClassification();
        problem.setFirstLevels(firstLevel);

        if (null != problem.getClassifyOne() && !"".equals(problem.getClassifyOne())) {
            // 取得二级分类
            Map<String, String> secondLevel = secondClassificationService.getSecondClassification(problem.getClassifyOne());
            problem.setSecondLevels(secondLevel);
        }
        if (null != problem.getClassifyTwo() && !"".equals(problem.getClassifyTwo())) {
            // 取得三级分类
            Map<String, String> thirdLevel = thirdClassificationService.getThirdClassification(problem.getClassifyTwo());
            problem.setThirdLevels(thirdLevel);
        }
        if (null != problem.getClassifyThree() && !"".equals(problem.getClassifyThree())) {
            // 取得简要描述
            Map<String, String> problemTypeMap = propertyRectifyService.getDescriptionByThirdId(problem.getClassifyThree());
            problem.setProblemtypes(problemTypeMap);
        }

        Map<String, String> projects = houseProjectService.getProjects();
        problem.setProjects(projects);
        // 取得楼栋信息
        if (null != problem.getProjectNum() && !"".equals(problem.getProjectNum())) {
            Map<String, String> builds = houseBuildingService.getBuildListByProjectId(problem.getProjectNum());
            problem.setBuildings(builds);
        }

        // 取得单元列表
        if (null != problem.getBuildingNum() && !"".equals(problem.getBuildingNum())) {
            Map<String, String> units = houseUnitService.getUnitMapByBuildingId(problem.getBuildingNum());
            problem.setUnits(units);
        }
        // 取得楼层信息
        if (null != problem.getUnitNum() && !"".equals(problem.getUnitNum())) {
            Map<String, String> floors = houseFloorService.getRoomsByUnitNum(problem.getUnitNum());
            problem.setFloors(floors);
        }
        // 取得房间信息
        if (null != problem.getFloorNum() && !"".equals(problem.getFloorNum())) {
            Map<String, String> rooms = houseRoomService.getRoomsByFloorNum(problem.getFloorNum());
            problem.setRooms(rooms);
        }

        if (problem.getProjectNum() != null && !"".equals(problem.getProjectNum()) && problem.getClassifyThree() != null && !"".equals(problem.getClassifyThree())) {
            problem.setSupplierMap(supplierService.getSupplierByProjectNumAndThirdId(problem.getProjectNum(), problem.getClassifyThree()));
        }
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有内部预验的
            //qch40010012 :详情-处理-保存 ,qch40010013 :详情-处理-提交
            //工地开放
            //qch40010029;//详情-处理-保存--问题清单--工地开放   qch40010030;//详情-处理-提交--问题清单--工地开放
            //正式交房
            //qch40010046：详情-处理-保存  qch40010047：详情-处理-提交
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010012":
                        checkAuthFunctionDTO.setQch40010012("Y");
                        break;
                    case "QCH40010013":
                        checkAuthFunctionDTO.setQch40010013("Y");
                        break;
                    case "QCH40010029":
                        checkAuthFunctionDTO.setQch40010029("Y");
                        break;
                    case "QCH40010030":
                        checkAuthFunctionDTO.setQch40010030("Y");
                        break;
                    case "QCH40010046":
                        checkAuthFunctionDTO.setQch40010046("Y");
                        break;
                    case "QCH40010047":
                        checkAuthFunctionDTO.setQch40010047("Y");
                        break;
                }
            }
        }
        model.addAttribute("typeMaps", problem);
        model.addAttribute("problem", problem);
        model.addAttribute("function", checkAuthFunctionDTO);

        if ("11".equals(problem.getPlanType())) {
            return "plan/problemEdit";
        } else if ("12".equals(problem.getPlanType())) {
            return "plan/problemEdit12";
        } else {
            return "plan/problemEdit13";
        }
    }

    /**
     * 修改
     *
     * @param userInformationEntity
     * @param model
     * @param problem
     * @return
     */
    @RequestMapping(value = "/modifyProblem")
    public ModelAndView modifyProblem(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                                      @Valid PropertyRectifyAdminDTO problem) {

        propertyRectifyService.modifyAdminQeustion(problem, userInformationEntity);

        return new ModelAndView("redirect:../problem/problemManagement.html?proType=" + problem.getPlanType());
    }

    /**
     * 批量提交草稿
     *
     * @param userInformationEntity
     * @param model
     * @param problem
     * @return
     */
    @RequestMapping(value = "/batchCommit")
    public ModelAndView batchCommit(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                                    @Valid PropertyRectifyAdminDTO problem) {

        propertyRectifyService.batchCommit(problem, userInformationEntity);

        return new ModelAndView("redirect:../problem/problemManagement.html?proType=" + problem.getPlanType());
    }


    /**
     * 作废
     *
     * @param userInformationEntity
     * @param model
     * @return
     */
    @RequestMapping(value = "/deleteQeustion")
    public ModelAndView deleteQeustion(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                                       @RequestParam String rectifyId, @RequestParam String caseType) throws UnsupportedEncodingException {
        propertyRectifyService.deleteAdminQeustion(rectifyId, userInformationEntity);
        return new ModelAndView("redirect:../problem/problemManagement.html?proType=" + caseType);
    }

    /**
     * 查看详细
     */
    @RequestMapping(value = "/getProblemManagementDetail")
    public String getProblemManagementDetail(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                             Model model, @Valid PropertyRectifyAdminDTO propertyRectifyAdminDTO, WebPage webPage) {
        PropertyRectifyCRMListDTO propertyRectifyCRMListDTO = propertyRectifyService.getAdminQuestionById(propertyRectifyAdminDTO);
        //查看相应权限
        ProblemRoleDTO problemRole = propertyRectifyService.getProblemRole(userInformationEntity.getStaffId(), propertyRectifyCRMListDTO.getProjectId(), propertyRectifyCRMListDTO.getDealPeople(), propertyRectifyCRMListDTO.getCreateBy());
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有内部预验的
            //qch40010008 :详情-关单 ,qch40010009 :详情-处理 ,qch40010010; 详情-废弃 ,qch40010011 :详情-强制关闭
            //工地开放
            //qch40010025;//详情-关单  qch40010026;//详情-处理  qch40010027;//详情-废弃  qch40010028;//详情-强制关闭
            //正式交房
            //qch40010042：详情-关单  qch40010043：详情-处理   qch40010044：详情-废弃  qch40010045：详情-强制关闭

            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010008":
                        checkAuthFunctionDTO.setQch40010008("Y");
                        break;
                    case "QCH40010009":
                        checkAuthFunctionDTO.setQch40010009("Y");
                        break;
                    case "QCH40010010":
                        checkAuthFunctionDTO.setQch40010010("Y");
                        break;
                    case "QCH40010011":
                        checkAuthFunctionDTO.setQch40010011("Y");
                        break;
                    case "QCH40010025":
                        checkAuthFunctionDTO.setQch40010025("Y");
                        break;
                    case "QCH40010026":
                        checkAuthFunctionDTO.setQch40010026("Y");
                        break;
                    case "QCH40010027":
                        checkAuthFunctionDTO.setQch40010027("Y");
                        break;
                    case "QCH40010028":
                        checkAuthFunctionDTO.setQch40010028("Y");
                        break;
                    case "QCH40010042":
                        checkAuthFunctionDTO.setQch40010042("Y");
                        break;
                    case "QCH40010043":
                        checkAuthFunctionDTO.setQch40010043("Y");
                        break;
                    case "QCH40010044":
                        checkAuthFunctionDTO.setQch40010044("Y");
                        break;
                    case "QCH40010045":
                        checkAuthFunctionDTO.setQch40010045("Y");
                        break;
                }
            }
        }
        model.addAttribute("problem", propertyRectifyCRMListDTO);
        model.addAttribute("problemRole", problemRole);
        if ("11".equals(propertyRectifyCRMListDTO.getPlanType())) {
            return "plan/problemDetail";
        } else if ("12".equals(propertyRectifyCRMListDTO.getPlanType())) {
            return "plan/problemDetail12";
        } else {
            return "plan/problemDetail13";
        }
    }

    /**
     * 常用检查项管理
     */
    @RequestMapping(value = "/commonlyUsedManagement.html")
    public String commonlyUsedManagement(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,Model model,@Valid ProblemDTO problem,WebPage webPage){

        // 取得一级分类
        Map<String, String> firstLevel = firstClassificationService.getFirstClassification();
        problem.setFirstLevels(firstLevel);

        if (null != problem.getOneType() && !"".equals(problem.getOneType())) {
            // 取得二级分类
            Map<String, String> secondLevel = secondClassificationService.getSecondClassification(problem.getOneType());
            problem.setSecondLevels(secondLevel);
        }

        if (null != problem.getOneType() && !"".equals(problem.getOneType())) {
            // 取得三级分类
            Map<String, String> thirdLevel = thirdClassificationService.getThirdClassification(problem.getTwoType());
            problem.setSecondLevels(thirdLevel);
        }
        Map<String, String> cUsedManagements = thirdClassificationService.getAllCommonlyUsedManagement();

        List<ProblemDTO> list = planInfoService.queryProblemList(problem);
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        List<String> function=authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","1");
        if(function!=null){
            //校验是否有     常用检查项    确定  QCH40010065
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "QCH40010065":
                        checkAuthFunctionDTO.setQch40010065("Y");
                        break;
                }
            }
        }
        model.addAttribute("cUsedManagements", cUsedManagements);
        model.addAttribute("problems", list);
        model.addAttribute("typeMaps", problem);
        model.addAttribute("problem",problem);
        model.addAttribute("function",checkAuthFunctionDTO);
        return "plan/commonlyUsedManagement";
    }

    /*常用检查项确认*/
    @RequestMapping(value = "/determine", produces = "application/json;charset=UTF-8")
    public ApiResult determine(@Valid ProblemDTO problem, HttpServletRequest request) {
        String determine = request.getParameter("determine");
        JSONArray myJsonArray = new JSONArray(determine);
        Map<String, String> cUsedManagements = thirdClassificationService.getAllCommonlyUsedManagement();//查询comm中的数据
        //thirdClassificationService.deleteAll();
        for (int i = 0; i < myJsonArray.length(); i++) {
            JSONObject myjObject = myJsonArray.getJSONObject(i);
            String values = String.valueOf(myjObject.getInt("value"));
            String str = "";
            if (cUsedManagements.size() != 0) {
                str = cUsedManagements.get(values);
            }
            if (str != null && !(str.equals(""))) {
                ThirdClassificationCommDTO ThirdClassificationCommDTO = new ThirdClassificationCommDTO();
                ThirdClassificationCommDTO.setTimeStamp(new Date());
                String value = String.valueOf(myjObject.getInt("value"));
                ThirdClassificationCommDTO.setValue(value);
                ThirdClassificationCommDTO.setLabel(myjObject.getString("label"));
                ThirdClassificationCommDTO.setItemOrder(i + 1);

                thirdClassificationService.update(ThirdClassificationCommDTO);
            } else {
                ThirdClassificationCommDTO ThirdClassificationCommDTO = new ThirdClassificationCommDTO();
                ThirdClassificationCommDTO.setId(IdGen.uuid());
                ThirdClassificationCommDTO.setTimeStamp(new Date());
                String value = String.valueOf(myjObject.getInt("value"));
                ThirdClassificationCommDTO.setValue(value);
                ThirdClassificationCommDTO.setLabel(myjObject.getString("label"));
                String secondid = String.valueOf(myjObject.get("second_id"));
                ThirdClassificationCommDTO.setSecondId(secondid);
                ThirdClassificationCommDTO.setItemOrder(i + 1);
                thirdClassificationService.insert(ThirdClassificationCommDTO);
            }

        }
        return new SuccessApiResult();
    }


    /*常用检查项确认*/
//    @RequestMapping(value="/orderThirdClassification",produces = "application/json;charset=UTF-8")
//    public ApiResult orderThirdClassification(HttpServletRequest request){
//
//        String value = request.getParameter("value");
//        String operation = request.getParameter("operation");
//        thirdClassificationService.orderThirdClassification(value,operation);
//        return new SuccessApiResult();
//    }


    /**
     * 批量修改责任人列表
     */
    @RequestMapping(value = "/problemManagements.html")
    public String problemManagements(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                                     @Valid PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, HttpServletRequest request) {
        List<PropertyRectifyCRMListDTO> list = propertyRectifyService.getQuestionLists(propertyRectifyCRMSelDTO, webPage);
        model.addAttribute("problems", list);
        model.addAttribute("typeMaps", propertyRectifyCRMSelDTO);
        model.addAttribute("problem", propertyRectifyCRMSelDTO);
        return "plan/problemManagements";
    }

    /*通过id删除*/
    @RequestMapping(value = "/delRolePeople", produces = "application/json;charset=UTF-8")
    public ApiResult delRolePeople(@Valid ProblemDTO problem, HttpServletRequest request) {
        //
        String valueId = request.getParameter("valueId");
        thirdClassificationService.deleteById(valueId);
        return new SuccessApiResult();
    }

    /*打印问题清单*/
    @RequestMapping(value = "printProblemListing")
    public String printProblemListing(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,Model model,WebPage webPage,
                                    @Valid PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO,HttpServletRequest request) {
       // @RequestParam String id
        //项目id
        String projectId = request.getParameter("projectId");
        String type = request.getParameter("type");//传入的值  11内部预验 12工地开放 13正式交房

        HouseProjectDto HouseProjectDto = houseProjectService.getProjectByProjectCode(projectId);
        String sssid = "";//sssid  打印单的编码
        if (HouseProjectDto.getPinyinCode() != null && !"".equals(type)) {
            sssid = houseProjectService.getPrintSequence(HouseProjectDto.getPinyinCode(), type);
        }
        String prjectName = "";
        if (HouseProjectDto != null) {
            prjectName = HouseProjectDto.getName();
        }
        //房屋标号
        String rId = request.getParameter("rId");
        try {
            rId = java.net.URLDecoder.decode(rId, "UTF-8");/*需要解码处理*/
        } catch (Exception e) {
            System.out.println("房间号异常！");
        }

        String signUrl = "";//签字图片
        HouseOpenEntity houseOpenEntity = new HouseOpenEntity();//工地开放实体
        InternalacceptanceHouseEntity internal = new InternalacceptanceHouseEntity();
        if ("12".equals(type)) {
            //查询签字图片lsit
            houseOpenEntity = houseTransferRepository.getHouseOpenByRoomNum(rId);
            List<PropertyImageEntity> signImageList = propertyImageRepository.getImageByType(rId, "11");
            if (signImageList.size() > 0) {
                for (PropertyImageEntity propertyImage : signImageList) {
                    signUrl = propertyImage.getImagePath();
                }
            }
        } else if ("11".equals(type)) {
            //查询签字图片lsit
            internal = houseTransferRepository.getInternalacceptanceByRoomNum(rId);
            List<PropertyImageEntity> signImageList = propertyImageRepository.getImageByType(rId, "9");
            if (signImageList.size() > 0) {
                for (PropertyImageEntity propertyImage : signImageList) {
                    signUrl = propertyImage.getImagePath();
                }
            }
        } else if ("13".equals(type)) {
            //查询签字图片lsit
            String rimageId = propertyImageRepository.getIdByRoomNum(rId, "13", propertyRectifyCRMSelDTO.getPlanNum());
            List<PropertyImageEntity> signImageList = propertyImageRepository.getImageByType(rimageId, "13");
            if (signImageList.size() > 0) {
                for (PropertyImageEntity propertyImage : signImageList) {
                    signUrl = propertyImage.getImagePath();
                }
            }
        }
        //工地开放时间
        String openTime = "";
        if (houseOpenEntity != null) {
            if (houseOpenEntity.getUpdateTime() != null) {
                openTime = DateUtils.format(houseOpenEntity.getUpdateTime(), DateUtils.FORMAT_SHORT_CN);
            }
        }
        //内部预验时间
        String internalTime = "";
        if (internal != null) {
            if (internal.getUpdateTime() != null) {
                internalTime = DateUtils.format(internal.getUpdateTime(), DateUtils.FORMAT_SHORT_CN);
            }
        }
        //日期
        // SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        Date myDate = new Date();
        String strDate = formatter.format(myDate);
        //caseIds 问题id数组bugra
        String caseIds=request.getParameter("caseIds");//问题id
        /*List<String> userPro = organizeService.getOProjectList(userInformationEntity.getStaffId());
        propertyRectifyCRMSelDTO.setUserProject(userPro);*/
        propertyRectifyCRMSelDTO.setRoomId(rId);
        List<PropertyRectifyCRMListDTO> list = propertyRectifyService.getQuestionList(propertyRectifyCRMSelDTO, webPage);
        List<PropertyRectifyCRMListDTO> problems = new ArrayList<PropertyRectifyCRMListDTO>();
        PropertyRectifyAdminDTO propertyRectifyAdminDTO = new PropertyRectifyAdminDTO();
        if (caseIds.equals("")) {
        } else {
            String[] caseIdsArr = caseIds.split(",");
            if (caseIdsArr.length != 0) {
                for (int i = 0; i < caseIdsArr.length; i++) {
                    propertyRectifyAdminDTO.setRectifyId(caseIdsArr[i]);
                    PropertyRectifyCRMListDTO propertyRectifyCRM = propertyRectifyService.getAdminQuestionById(propertyRectifyAdminDTO);
                    problems.add(propertyRectifyCRM);
                }
            }
        }
        //按照房间id（编码）查询业主信息   注：list.get(0).getRoomId()  必须最小单位是房间查询打印
        UserCrmForRoomidDTO user = houseRoomService.getuserCrm(rId);
        if (user != null) {
            model.addAttribute("mobile", user.getUserMobile());
            model.addAttribute("userName", user.getUserName());
            model.addAttribute("address", user.getRommName());
        }
        //根据房间编码 查询房屋水电表信息
        HouseTransferAdminDTO houseTransferAdminDTO = houseTransferService.getTransferInfoByRoomId(rId);
        model.addAttribute("houseTransferInfo", houseTransferAdminDTO);
        model.addAttribute("problems", problems);
        model.addAttribute("prjectName", prjectName);
        model.addAttribute("rId", sssid);
        model.addAttribute("strDate", strDate);
        model.addAttribute("count", problems.size());
        model.addAttribute("signUrl", signUrl);//签字图片
        model.addAttribute("houseOpen", houseOpenEntity);//工地开放查询实体
        model.addAttribute("openTime", openTime);//工地开放时间
        model.addAttribute("internal", internal);//工地开放查询实体
        model.addAttribute("internalTime", internalTime);//工地开放时间
        model.addAttribute("type", type);//type 11内部预验 12工地开放 13正式交房
        model.addAttribute("InitializeSize", "36");
        if (problems.size() >= 26) {
            model.addAttribute("count", problems.size());

            return "plan/printProblemListingesAll";
        } else if (problems.size() == 0) {
            model.addAttribute("logo", "本次查验无质量问题,以下无正文.");
            return "plan/printProblemListings";
        } else {
            model.addAttribute("logo", "hello");
            return "plan/printProblemListings";
        }
    }

    /**
     * 打印问题清单
     */
    @RequestMapping(value = "signaturePrinting")
    public String signaturePrinting(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                                    @Valid PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO, HttpServletRequest request) {
        // @RequestParam String id
        //项目id
        String projectId = request.getParameter("projectId");
        String type = request.getParameter("type");//传入的值  11内部预验 12工地开放 13正式交房
        String roomNum = request.getParameter("rId");
        try {
            roomNum = java.net.URLDecoder.decode(roomNum, "UTF-8");/*需要解码处理*/
        } catch (Exception e) {
            System.out.println("房间号异常！");
        }
        HouseProjectDto houseProjectDto = houseProjectService.getProjectByProjectCode(projectId);

        // 打印表头
        String prinName = "";
        if (houseProjectDto != null) {
            if ("13".equals(type)) {
                prinName = houseProjectDto.getName() + "房屋交付验收表";
            } else if ("12".equals(type)) {

            } else if ("11".equals(type)) {

            }
        }
        //获取计划编码信息
        String planNum = "";
        if (!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getPlanNum())) {
            planNum = propertyRectifyCRMSelDTO.getPlanNum();
        }
        //签字图片
        String signUrl = "";
        Date signDate = null;
//        List<String> signaList=new ArrayList<>();
        List<String> signIdList = propertyImageRepository.getSignIdBy(planNum, roomNum, type);
        if (signIdList != null) {
            List<PropertyImageEntity> signImageList = propertyImageRepository.getSignImageByIdList(signIdList, "21");
            if (signImageList.size() > 0) {
                for (PropertyImageEntity propertyImage : signImageList) {
//                    signaList.add(propertyImage.getImagePath());
                    signUrl = propertyImage.getImagePath();
                    signDate = propertyImage.getUploadDate();
                }
            }
        }
        //获取业主信息
        UserCrmForRoomidDTO user = houseRoomService.getSignPrint(roomNum);
        if (user != null) {
            model.addAttribute("userName", user.getUserName());
            model.addAttribute("address", user.getRommName());
        }
        //问题id
        String caseIds = request.getParameter("caseIds");//问题id
        //整改问题显示
        List<PropertyRectifyCRMListDTO> problems = new ArrayList<PropertyRectifyCRMListDTO>();
        PropertyRectifyAdminDTO propertyRectifyAdminDTO = new PropertyRectifyAdminDTO();
        Date myDate = null;
        if (caseIds.equals("")) {
        } else {
            String[] caseIdsArr = caseIds.split(",");
            if (caseIdsArr.length != 0) {
                for (int i = 0; i < caseIdsArr.length; i++) {
                    propertyRectifyAdminDTO.setRectifyId(caseIdsArr[i]);
                    PropertyRectifyCRMListDTO propertyRectifyCRM = propertyRectifyService.getAdminQuestionById(propertyRectifyAdminDTO);
                    if (myDate != null) {
                        if (myDate.getTime() < propertyRectifyCRM.getCreateDate().getTime()) {
                            myDate = propertyRectifyCRM.getCreateDate();
                        }
                    } else {
                        myDate = propertyRectifyCRM.getCreateDate();
                    }
                    problems.add(propertyRectifyCRM);
                }
            }
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        SimpleDateFormat fort = new SimpleDateFormat("yyyyMMdd");

        String strDate = ""; //打印日期
        String prinId = roomNum;//打印编码
//        String printDate="";//打印编码日期
        if (myDate != null) {
            //打印日期
            strDate = formatter.format(myDate);
            // 打印单编号
//            printDate=fort.format(myDate);
        } else if (signDate != null) {
            //打印日期
            strDate = formatter.format(signDate);
            // 打印单编号
//            printDate=fort.format(signDate);
        } else {
            //打印日期
            strDate = formatter.format(new Date());
            // 打印单编号
//            printDate=fort.format(new Date());
        }
        // 打印单编号
//        if(houseProjectDto.getPinyinCode()!=null&&!"".equals(type)){
//            prinId=houseProjectService.getPrintSignSequence(houseProjectDto.getPinyinCode(),roomNum,type,printDate);
//        }


        //根据房间编码 查询房屋水电表信息
        model.addAttribute("problems", problems);
        model.addAttribute("prinName", prinName);
        model.addAttribute("prinId", prinId);
        model.addAttribute("strDate", strDate);
        model.addAttribute("count", problems.size());
        if (!StringUtil.isEmpty(signUrl)) {
            model.addAttribute("signUrl", signUrl);//签字图片
        } else {
            model.addAttribute("signUrl", "");//签字图片
        }
        model.addAttribute("type", type);//type 11内部预验 12工地开放 13正式交房
        model.addAttribute("InitializeSize", "36");
        if (problems.size() >= 30) {
            model.addAttribute("count", problems.size());
            return "plan/printSignImageProblemAll";
        } else {
            model.addAttribute("logo", "hello");
            return "plan/printSignImageProblem";
        }
    }

    /**
     * Code:D
     * Type:
     * Describe:关闭问题整改单
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/6
     */
    @RequestMapping(value = "/closeQeustion")
    public ModelAndView closeQeustion(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                                      @Valid PropertyRectifyAdminDTO propertyRectifyAdminDTO) throws UnsupportedEncodingException {
        propertyRectifyService.closeAdminQeustion(propertyRectifyAdminDTO, userInformationEntity.getStaffName());
        return new ModelAndView("redirect:../problem/problemManagement.html?proType=" + propertyRectifyAdminDTO.getProblemType());
    }

    /**
     * Code:D
     * Type:
     * Describe:强制关闭问题整改单
     * CreateBy:Magic
     * CreateOn:2016/11/7
     */
    @RequestMapping(value = "/forceCloseQeustion")
    public ModelAndView forceCloseQeustion(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                                           @RequestParam String rectifyId, @RequestParam String problemType, @RequestParam String forceClose) throws UnsupportedEncodingException {
        propertyRectifyService.forceCloseAdminQeustion(rectifyId, forceClose, userInformationEntity);
        return new ModelAndView("redirect:../problem/problemManagement.html?proType=" + problemType);
    }

    /**
     * Code:D
     * Type:
     * Describe:预关闭问题整改单
     * CreateBy:Magic
     * CreateOn:2016/10/8
     */
    @RequestMapping(value = "/closeQeustionNew")
    public String closeQeustionNew(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, @Valid PropertyRectifyAdminDTO propertyRectifyAdminDTO, WebPage webPage) {


        PropertyRectifyAdminDTO problem = propertyRectifyService.getAdminQuestionByRectifyId(propertyRectifyAdminDTO);

        // 取得一级分类
        Map<String, String> firstLevel = firstClassificationService.getFirstClassification();
        problem.setFirstLevels(firstLevel);

        if (null != problem.getClassifyOne() && !"".equals(problem.getClassifyOne())) {
            // 取得二级分类
            Map<String, String> secondLevel = secondClassificationService.getSecondClassification(problem.getClassifyOne());
            problem.setSecondLevels(secondLevel);
        }
        if (null != problem.getClassifyTwo() && !"".equals(problem.getClassifyTwo())) {
            // 取得三级分类
            Map<String, String> thirdLevel = thirdClassificationService.getThirdClassification(problem.getClassifyTwo());
            problem.setThirdLevels(thirdLevel);
        }
        if (null != problem.getClassifyThree() && !"".equals(problem.getClassifyThree())) {
            // 取得简要描述
            Map<String, String> problemTypeMap = propertyRectifyService.getDescriptionByThirdId(problem.getClassifyThree());
            problem.setProblemtypes(problemTypeMap);
        }

        Map<String, String> projects = houseProjectService.getProjects();
        problem.setProjects(projects);
        // 取得楼栋信息
        if (null != problem.getProjectNum() && !"".equals(problem.getProjectNum())) {
            Map<String, String> builds = houseBuildingService.getBuildListByProjectId(problem.getProjectNum());
            problem.setBuildings(builds);
        }

        // 取得单元列表
        if (null != problem.getBuildingNum() && !"".equals(problem.getBuildingNum())) {
            Map<String, String> units = houseUnitService.getUnitMapByBuildingId(problem.getBuildingNum());
            problem.setUnits(units);
        }
        // 取得楼层信息
        if (null != problem.getUnitNum() && !"".equals(problem.getUnitNum())) {
            Map<String, String> floors = houseFloorService.getRoomsByUnitNum(problem.getUnitNum());
            problem.setFloors(floors);
        }
        // 取得房间信息
        if (null != problem.getFloorNum() && !"".equals(problem.getFloorNum())) {
            Map<String, String> rooms = houseRoomService.getRoomsByFloorNum(problem.getFloorNum());
            problem.setRooms(rooms);
        }

        if (problem.getProjectNum() != null && !"".equals(problem.getProjectNum()) && problem.getClassifyThree() != null && !"".equals(problem.getClassifyThree())) {
            problem.setSupplierMap(supplierService.getSupplierByProjectNumAndThirdId(problem.getProjectNum(), problem.getClassifyThree()));
        }


        model.addAttribute("typeMaps", problem);
        model.addAttribute("problem", problem);

        if ("11".equals(problem.getPlanType())) {
            return "plan/problemClose";
        } else if ("12".equals(problem.getPlanType())) {
            return "plan/problemClose12";
        } else {
            return "plan/problemClose13";
        }
    }

    /**
     * Code:D
     * Type:
     * Describe:关闭问题整改单
     * CreateBy:Magic
     * CreateOn:2016/10/8
     */
    @RequestMapping(value = "/closeQeustionTime")
    public ModelAndView closeQeustionTime(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                                          @Valid PropertyRectifyAdminDTO problem) {

//        propertyRectifyService.modifyAdminQeustion(problem, userInformationEntity);
        propertyRectifyService.modifyAdminQeustionClose(problem, userInformationEntity);


        return new ModelAndView("redirect:../problem/problemManagement.html?proType=" + problem.getPlanType());
    }

    /**
     * Code:D
     * Type:
     * Describe:获得供应商内部负责人列表
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/8
     */
    @RequestMapping(value = "/getSupplierResponsibleList")
    public ApiResult getSupplierResponsibleList(@Valid String supplierId) {
        Map<String, String> map = propertyRectifyService.getSupplierResponsibleList(supplierId);
        return new SuccessApiResult(map);
    }

    /**
     * Code:D
     * Type:
     * Describe:重新派单 redirectQuestionQeustion  未完成
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/6
     */
    @RequestMapping(value = "/redirectQuestionQeustion")
    public String redirectQuestionQeustion(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                                           @Valid PropertyRectifyAdminDTO propertyRectifyAdminDTO) throws UnsupportedEncodingException {
        PropertyRectifyAdminDTO problem = propertyRectifyService.getAdminQuestionByRectifyId(propertyRectifyAdminDTO);
        //查询内部负责人
        Map<String, String> innerPersonList = propertyRectifyService.getInnerPersonList(problem.getProjectNum());
        problem.setInnerPersonList(innerPersonList);
//        if(StringUtil.isEmpty(problem.getInnerPerson())){
//            problem.setInnerPerson(innerPersonList.keySet().iterator().next());
//        }
        //供应商负责人
        Map<String, String> supplierResponsibleList = propertyRectifyService.getSupplierResponsibleList(problem.getSupplier());
        problem.setSupplierResponsibleList(supplierResponsibleList);
        // 取得一级分类
        Map<String, String> firstLevel = firstClassificationService.getFirstClassification();
        problem.setFirstLevels(firstLevel);

        if (null != problem.getClassifyOne() && !"".equals(problem.getClassifyOne())) {
            // 取得二级分类
            Map<String, String> secondLevel = secondClassificationService.getSecondClassification(problem.getClassifyOne());
            problem.setSecondLevels(secondLevel);
        }
        if (null != problem.getClassifyTwo() && !"".equals(problem.getClassifyTwo())) {
            // 取得三级分类
            Map<String, String> thirdLevel = thirdClassificationService.getThirdClassification(problem.getClassifyTwo());
            problem.setThirdLevels(thirdLevel);
        }
        if (null != problem.getClassifyThree() && !"".equals(problem.getClassifyThree())) {
            // 取得简要描述
            Map<String, String> problemTypeMap = propertyRectifyService.getDescriptionByThirdId(problem.getClassifyThree());
            problem.setProblemtypes(problemTypeMap);
        }

        Map<String, String> projects = houseProjectService.getProjects();
        problem.setProjects(projects);
        // 取得楼栋信息
//        if(null != problem.getProjectNum() && !"".equals(problem.getProjectNum())){
//            Map<String, String> builds = houseBuildingService.getBuildListByProjectId(problem.getProjectNum());
//            problem.setBuildings(builds);
//        }
//
//        // 取得单元列表
//        if(null != problem.getBuildingNum() && !"".equals(problem.getBuildingNum())){
//            Map<String, String> units = houseUnitService.getUnitMapByBuildingId(problem.getBuildingNum());
//            problem.setUnits(units);
//        }
//        // 取得楼层信息
//        if(null != problem.getUnitNum() && !"".equals(problem.getUnitNum())){
//            Map<String, String> floors = houseFloorService.getRoomsByUnitNum(problem.getUnitNum());
//            problem.setFloors(floors);
//        }
//        // 取得房间信息
//        if(null != problem.getFloorNum() && !"".equals(problem.getFloorNum())){
//            Map<String, String> rooms = houseRoomService.getRoomsByFloorNum(problem.getFloorNum());
//            problem.setRooms(rooms);
//        }

        if (problem.getProjectNum() != null && !"".equals(problem.getProjectNum()) && problem.getClassifyThree() != null && !"".equals(problem.getClassifyThree())) {
            problem.setSupplierMap(supplierService.getSupplierByProjectNumAndThirdId(problem.getProjectNum(), problem.getClassifyThree()));
        }


        model.addAttribute("typeMaps", problem);
        model.addAttribute("problem", problem);

        if ("11".equals(problem.getPlanType())) {
            return "plan/problemRedirect";
        } else if ("12".equals(problem.getPlanType())) {
            return "plan/problemRedirect12";
        } else {
            return "plan/problemRedirect13";
        }
    }

    /**
     * Code:D
     * Type:
     * Describe:派单
     * CreateBy:zhangzhaowen
     * CreateOn:2016/9/8
     */
    @RequestMapping(value = "/ToRePieOrder")
    public String ToRePieOrder(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                               @Valid PropertyRectifyAdminDTO propertyRectifyAdminDTO) {

        int ss = rectificationService.ToRePieOrder(userInformationEntity, propertyRectifyAdminDTO);
        if (ss == 1) {
            return "redirect:../problem/problemManagement.html?proType=" + propertyRectifyAdminDTO.getProblemType();
        } else {
            return null;
        }

    }

    /**
     * Code:D
     * Type:
     * Describe:三级分类别名管理
     * CreateBy:zhangzhaowen
     * CreateOn:2016/8/26 @ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,Model model,WebPage webPage,
     *
     * @Valid PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO
     */
    @RequestMapping(value = "/thirdClassificationAlias.html")
    public String ThirdClassificationAlias(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                                           @Valid SearchClassificationDTO searchClassificationDTO) {
        //获得所有一级-二级-三级->分类
        List<AllClassificationDTO> dtoList = thirdClassificationService.getAllClassifyLlist(searchClassificationDTO, webPage);
        // 取得一级分类
        Map<String, String> firstLevel = firstClassificationService.getFirstClassification();
        searchClassificationDTO.setFirstLevels(firstLevel);
        if (null != searchClassificationDTO.getOneType() && !"".equals(searchClassificationDTO.getOneType())) {
            // 取得二级分类
            if (!searchClassificationDTO.getOneType().equals("0000")) {
                Map<String, String> secondLevel = secondClassificationService.getSecondClassification(searchClassificationDTO.getOneType());
                searchClassificationDTO.setSecondLevels(secondLevel);
            }

        }
        if (null != searchClassificationDTO.getTwoType() && !"".equals(searchClassificationDTO.getTwoType())) {
            // 取得三级分类
            if (!searchClassificationDTO.getTwoType().equals("0000")) {
                Map<String, String> thirdLevel = thirdClassificationService.getThirdClassification(searchClassificationDTO.getTwoType());
                searchClassificationDTO.setThirdLevels(thirdLevel);
            }
        }
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        //获取功能点
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有   qch40010075; //修改  qch40010076; //修改-保存   qch40010077; //禁用
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010075":
                        checkAuthFunctionDTO.setQch40010075("Y");
                        break;
                    case "QCH40010076":
                        checkAuthFunctionDTO.setQch40010076("Y");
                        break;
                    case "QCH40010077":
                        checkAuthFunctionDTO.setQch40010077("Y");
                        break;
                }
            }
        }
        model.addAttribute("function", checkAuthFunctionDTO);
        model.addAttribute("typeMaps", searchClassificationDTO);
        model.addAttribute("problem", searchClassificationDTO);
        model.addAttribute("dtoList", dtoList);
        return "plan/thirdClassificationAlias";
    }

    /**
     * 更新三级分类别名
     */
    @RequestMapping(value = "/updateThirdAlias", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ApiResult updateThirdAlias(@Valid AllClassificationDTO allClassificationDTO) {
        thirdClassificationService.updateThirdAlias(allClassificationDTO);
        return new SuccessApiResult(allClassificationDTO);
    }

    /**
     * Code:D
     * Type:
     * Describe:楼栋别名修改,分页查询
     * CreateBy:zhangzhaowen
     * CreateOn:2016/8/30
     */
    @RequestMapping(value = "/buildingAlias.html")
    public String buildingAlias(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                                @Valid BuildingAliasDTO buildingAliasDTO) {

        List<String> userPro = organizeService.getOProjectList(userInformationEntity.getStaffId());
        buildingAliasDTO.setUserProject(userPro);

//        Map<String, String> projectMap = houseProjectService.getProjects();
//        Map<String, String> projects = new HashMap<String, String>();
//        projects.put("", "请选择项目");
//        for (String projectNum : userPro) {
//            projects.put(projectNum, projectMap.get(projectNum));
//        }
//        buildingAliasDTO.setProjects(projects);
//        //
        //集团
        Map<String, String> groupList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000000", "0");
        model.addAttribute("groups", groupList);
        if (!StringUtil.isEmpty(buildingAliasDTO.getGroupId())) {
            //区域
            Map<String, String> regionList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000001", buildingAliasDTO.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if (!StringUtil.isEmpty(buildingAliasDTO.getRegionId())) {
            //城市
            Map<String, String> cityList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000003", buildingAliasDTO.getRegionId());
            model.addAttribute("citys", cityList);
        }
        if(!StringUtil.isEmpty(buildingAliasDTO.getCityId())){
            //项目
            Map<String, String> projectIdList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000002", buildingAliasDTO.getCityId());
            Map<String, Object> projectList = organizeService.getProjectNumListProjectId(projectIdList);
            model.addAttribute("projects", projectList);
            buildingAliasDTO.setProjectName(projectList.get(buildingAliasDTO.getProjectId()).toString());
        }
        // 取得楼栋信息------项目id添加
        List<Map<String, String>> buildingList = new ArrayList<>();
        Map ateaList = new HashMap();
        if (!StringUtil.isEmpty(buildingAliasDTO.getProjectId())) {
            ateaList = houseBuildingService.getAreaListByProjectNum(buildingAliasDTO.getProjectId());

        }
        model.addAttribute("areas", ateaList);
        Map buildList = new HashMap();
        if (!StringUtil.isEmpty(buildingAliasDTO.getAreaId())) {
            buildList = houseBuildingService.getBuildListByBlockNum(buildingAliasDTO.getAreaId());
        }
        model.addAttribute("buildings", buildList);

        if (!StringUtil.isEmpty(buildingAliasDTO.getAreaId()) || !StringUtil.isEmpty(buildingAliasDTO.getProjectId()) || !StringUtil.isEmpty(buildingAliasDTO.getBuildingAlias())) {
            buildingList = houseBuildingService.getBuildInfoListByProjectIdNew(buildingAliasDTO.getProjectId(), buildingAliasDTO.getBuildingId(), buildingAliasDTO.getBuildingAlias(), webPage, buildingAliasDTO.getAreaId());
            model.addAttribute("buildingList", buildingList);
        }

//        List <Map<String,String>> buildings = new ArrayList<>();
//        buildings =houseBuildingService.getBuildInfoListByProjectId(buildingAliasDTO.getProjectId(),null,null,null);
//        model.addAttribute("buildings",buildings);

        buildingAliasDTO.setBuildings(buildingList);
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        //获取功能点
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有  qch40010072; //修改--楼栋别名  qch40010073; //修改-保存--楼栋别名 qch40010074; //禁用
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010072":
                        checkAuthFunctionDTO.setQch40010072("Y");
                        break;
                    case "QCH40010073":
                        checkAuthFunctionDTO.setQch40010073("Y");
                        break;
                    case "QCH40010074":
                        checkAuthFunctionDTO.setQch40010074("Y");
                        break;
                }
            }
        }
        model.addAttribute("function", checkAuthFunctionDTO);
        model.addAttribute("typeMaps", buildingAliasDTO);
        model.addAttribute("problem", buildingAliasDTO);
        return "plan/buildingAlias";
    }

    //获得楼栋列表
    @RequestMapping(value = "/getBuildingListByProject")
    public ApiResult getBuildingListByProject(@Valid BuildingAliasDTO buildingAliasDTO) {
//        if()
        List<Map<String, String>> buildingList = houseBuildingService.getBuildInfoListByProjectId(buildingAliasDTO.getProjectId(), buildingAliasDTO.getBuildingId(), null, null);
        return new SuccessApiResult(buildingList);
    }

    /**
     * 更新楼栋别名
     */
    @RequestMapping(value = "/updateBuildingAlias", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ApiResult updateBuildingAlias(@Valid BuildingAliasDTO buildingAliasDTO) {
//        thirdClassificationService.updateBuildingAlias(propertyRectifyCRMSelDTO);
        houseBuildingService.updateBuildingAlias(buildingAliasDTO.getBuildingNum(), buildingAliasDTO.getBuildingAlias());
        return new SuccessApiResult(buildingAliasDTO);
    }


    /**
     * 根据用户和项目层级
     *
     * @param rectifyMagicDTO
     * @return
     */
    @RequestMapping(value = "/getQCAuthAgency")
    public ApiResult getQCAuthAgency(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                     @Valid PropertyRectifyMagicDTO rectifyMagicDTO) {
        if ("100000002".equals(rectifyMagicDTO.getType())) {
            Map map = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, rectifyMagicDTO.getType(), rectifyMagicDTO.getCityId());
            return new SuccessApiResult(map);
        }
        if ("100000003".equals(rectifyMagicDTO.getType())) {
            Map map = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, rectifyMagicDTO.getType(), rectifyMagicDTO.getRegionId());
            return new SuccessApiResult(map);
        } else {
            Map map = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, rectifyMagicDTO.getType(), rectifyMagicDTO.getGroupId());
            return new SuccessApiResult(map);
        }
    }

    /**
     * 根据用户和项目层级
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "/getProjectMun")
    public ApiResult getProjectMun(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                     @RequestParam String type,String cityId) {
        Map map = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, type, cityId);
        Map<String, Object> projectList = organizeService.getProjectNumListProjectId(map);
        return new SuccessApiResult(projectList);
    }

    @RequestMapping(value = "/problemPrintNew.html")
    public String problemPrintNew(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model, WebPage webPage,
                                  @Valid PropertyRectifyMagicDTO rectifyMagicDTO) {
        CheckAuthFunctionDTO checkAuthFunctionDTO = new CheckAuthFunctionDTO();
        Map<String, String> groupList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000000", "0");
        model.addAttribute("groups", groupList);
        if (!StringUtil.isEmpty(rectifyMagicDTO.getGroupId())) {
            Map<String, String> regionList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000001", rectifyMagicDTO.getGroupId());
            model.addAttribute("regions", regionList);
        }
        if (!StringUtil.isEmpty(rectifyMagicDTO.getRegionId())) {
            Map<String, String> cityList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000003", rectifyMagicDTO.getRegionId());
            model.addAttribute("citys", cityList);
        }
        if (!StringUtil.isEmpty(rectifyMagicDTO.getCityId())) {
            Map<String, String> projectList = securityProjectService.getQCAgencyByTypeAndUser(userInformationEntity, "100000002", rectifyMagicDTO.getCityId());
            model.addAttribute("projects", projectList);
        }
        //根据项目编码获取计划信息
        if (!StringUtil.isEmpty(rectifyMagicDTO.getProjectId())) {
            String projectNum = houseRoomService.getProjectNumById(rectifyMagicDTO.getProjectId());
            rectifyMagicDTO.setProjectNum(projectNum);
            if (!StringUtil.isEmpty(projectNum)) {
                Map<String, String> plans = houseRoomService.getPlanListByProjectNumAndType(projectNum, rectifyMagicDTO.getPlanType());
                rectifyMagicDTO.setPlanList(plans);
            }
        }
        //验房打印 必须选择到计划 方才显示数据
        if (!StringUtil.isEmpty(rectifyMagicDTO.getSignaType()) && "1".equals(rectifyMagicDTO.getSignaType())) {
            //根据计划编码获取房间信息
            if (!StringUtil.isEmpty(rectifyMagicDTO.getPlanNum())) {
                Map<String, String> rooms = houseRoomService.getRoomsByPlanNum(rectifyMagicDTO.getPlanNum());
                rectifyMagicDTO.setRoomList(rooms);
                //根据条件获取列表显示lsit
//                List<PropertyRectifyListMagicDTO> list= propertyRectifyService.getPrintList(rectifyMagicDTO, webPage);
                List<PropertyRectifyListMagicDTO> list = propertyRectifyService.getSignaPrintList(rectifyMagicDTO, webPage);
                model.addAttribute("problems", list);
            }
        } else {
            //根据计划编码获取房间信息
            if (!StringUtil.isEmpty(rectifyMagicDTO.getPlanNum())) {
                Map<String, String> rooms = houseRoomService.getRoomsByPlanNum(rectifyMagicDTO.getPlanNum());
                rectifyMagicDTO.setRoomList(rooms);
            }
            //根据条件获取列表显示lsit
            List<PropertyRectifyListMagicDTO> list = propertyRectifyService.getPrintList(rectifyMagicDTO, webPage);
            model.addAttribute("problems", list);
        }

        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有     内部预验 QCH40010014 搜索  QCH40010015 导出Excel QCH40010016 详情   QCH40010017  打印交房单
            //工地开放    QCH40010031  搜索      QCH40010032   导出Excel   QCH40010033  详情   QCH40010034   打印交房单
            //正式交房  交房打印  QCH40010048 搜索  QCH40010049  导出Excel  QCH40010050  详情   QCH40010051  打印交房单
            //验房打印  QCH40010052  搜索  QCH40010053  导出Excel  QCH40010054  详情  QCH40010055  打印验房单
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010014":
                        checkAuthFunctionDTO.setQch40010014("Y");
                        break;
                    case "QCH40010015":
                        checkAuthFunctionDTO.setQch40010015("Y");
                        break;
                    case "QCH40010016":
                        checkAuthFunctionDTO.setQch40010016("Y");
                        break;
                    case "QCH40010031":
                        checkAuthFunctionDTO.setQch40010031("Y");
                        break;
                    case "QCH40010032":
                        checkAuthFunctionDTO.setQch40010032("Y");
                        break;
                    case "QCH40010033":
                        checkAuthFunctionDTO.setQch40010033("Y");
                        break;
                    case "QCH40010048":
                        checkAuthFunctionDTO.setQch40010048("Y");
                        break;
                    case "QCH40010049":
                        checkAuthFunctionDTO.setQch40010049("Y");
                        break;
                    case "QCH40010050":
                        checkAuthFunctionDTO.setQch40010050("Y");
                        break;
                    case "QCH40010052":
                        checkAuthFunctionDTO.setQch40010052("Y");
                        break;
                    case "QCH40010053":
                        checkAuthFunctionDTO.setQch40010053("Y");
                        break;
                    case "QCH40010054":
                        checkAuthFunctionDTO.setQch40010054("Y");
                        break;
                }
            }
        }
        model.addAttribute("typeMaps", rectifyMagicDTO);
        model.addAttribute("problem", rectifyMagicDTO);
        model.addAttribute("signaType", rectifyMagicDTO.getSignaType());
        model.addAttribute("function", checkAuthFunctionDTO);
        if (!StringUtil.isEmpty(rectifyMagicDTO.getSignaType()) && "1".equals(rectifyMagicDTO.getSignaType())) {
            return "plan/printSignImagePrint";
        } else {
            return "plan/propertyPrint";
        }

    }

    /**
     * 按当前登录人权限和城市编码获取项目下拉框
     */
    @RequestMapping(value = "/getPrintProjectList")
    public ApiResult getPrintProjectList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                         @Valid PropertyRectifyMagicDTO rectifyMagic) {
        //根据当前人获取所拥有的项目权限
        List<String> projectList = organizeService.getOProjectList(userInformationEntity.getStaffId());
        //根据当前登录人权限和城市编码  获取项目list
        Map<String, String> projects = new HashMap<String, String>();
        projects.put("", "请选择项目");
        if (!StringUtil.isEmpty(rectifyMagic.getCityId())) {
            Map<String, String> projectMap = houseProjectService.getProjectsMagic(rectifyMagic.getCityId());
            List<String> projectCityList = new ArrayList<>();
            for (String projectNum : projectList) {
                String a[] = projectNum.split("-");
                if (rectifyMagic.getCityNum().equals(a[0])) {
                    projectCityList.add(projectNum);
                }
            }
            for (String projectNum : projectCityList) {
                projects.put(projectNum, projectMap.get(projectNum));
            }
        }
        return new SuccessApiResult(projects);
    }


    /**
     * 按当前项目编码和type查询项目计划编码
     */
    @RequestMapping(value = "/getPrintPlanList")
    public ApiResult getPrintPlanList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                      @Valid PropertyRectifyMagicDTO rectifyMagic) {
        String projectNum = houseRoomService.getProjectNumById(rectifyMagic.getProjectId());
        Map<String, String> plans = houseRoomService.getPlanListByProjectNumAndType(projectNum, rectifyMagic.getPlanType());
        return new SuccessApiResult(plans);
    }

    /**
     * 按当前项目编码和type查询项目计划编码
     */
    @RequestMapping(value = "/getPrintPlanListNew")
    public ApiResult getPrintPlanListNew(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                      @Valid PropertyRectifyMagicDTO rectifyMagic) {
        Map<String, String> plans = houseRoomService.getPlanListByProjectNumAndType(rectifyMagic.getProjectNum(), rectifyMagic.getPlanType());
        return new SuccessApiResult(plans);
    }

    /**
     * 按当前计划获取房间
     */
    @RequestMapping(value = "/getPrintPlanRoomList")
    public ApiResult getPrintPlanRoomList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                          @Valid PropertyRectifyMagicDTO rectifyMagic) {
        Map<String, String> rooms = houseRoomService.getRoomsByPlanNum(rectifyMagic.getPlanNum());
        return new SuccessApiResult(rooms);
    }


    /***
     * 导出excel操作
     * param:user
     * param:httpServletResponse
     * return
     */
    @RequestMapping(value = "/printExportExcel")
    @ResponseBody
    public String printExportExcel(@ModelAttribute("authPropertystaff") UserInformationEntity user,
                                   HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,
                                   PropertyRectifyMagicDTO rectifyMagic, Model model, WebPage webPage) {
        try {
            //根据当前人获取所拥有的项目权限
//            List<String> projectList = organizeService.getOProjectList(user.getStaffId());
            /*List<String> projectList = authAgencyService.getAgencyIdList(user.getStaffId());
            rectifyMagic.setUserProject(projectList);*/
            String fileName = "验房清单";
            httpServletResponse.setContentType("application/x-xls");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            String agent = httpServletRequest.getHeader("USER-AGENT").toLowerCase();
            if (agent.contains("firefox")) {
                httpServletResponse.setCharacterEncoding("utf-8");
                httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xls");
            } else {
                httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + codedFileName + ".xls");
            }
            ServletOutputStream out = httpServletResponse.getOutputStream();
            return propertyRectifyService.printExportNewExcel(rectifyMagic, out);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }

    }


    /**
     * 问题管理页
     */

    @RequestMapping(value = "/problemRoomPrint.html")
    public String problemRoomPrint(@ModelAttribute("authPropertystaff")UserInformationEntity userInformationEntity,Model model,WebPage webPage,
                               @Valid PropertyRectifyCRMSelDTO propertyRectifyCRMSelDTO){
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        webPage=null;
        /*List<String> userPro = organizeService.getOProjectList(userInformationEntity.getStaffId());
        propertyRectifyCRMSelDTO.setUserProject(userPro);*/

        List<PropertyRectifyCRMListDTO> list = propertyRectifyService.getQuestionList(propertyRectifyCRMSelDTO, webPage);
        //为 验房签字打印功能准备（北京区域）
        String cityNum = "";
        if (!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getProjectId())) {
            String projectNum = propertyRectifyCRMSelDTO.getProjectId();
            cityNum = projectNum.substring(0, projectNum.indexOf("-"));
        }
        String planNum = "";
        if (!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getPlanNum())) {
            planNum = propertyRectifyCRMSelDTO.getPlanNum();
        }
        List<String> function = authAgencyService.getQCProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(), "4", "1");
        if (function != null) {
            //校验是否有     内部预验 QCH40010014 搜索  QCH40010015 导出Excel QCH40010016 详情   QCH40010017  打印交房单
            //工地开放    QCH40010031  搜索      QCH40010032   导出Excel   QCH40010033  详情   QCH40010034   打印交房单
            //正式交房  交房打印  QCH40010048 搜索  QCH40010049  导出Excel  QCH40010050  详情   QCH40010051  打印交房单
            //验房打印  QCH40010052  搜索  QCH40010053  导出Excel  QCH40010054  详情  QCH40010055  打印验房单
            for (int i = 0; i < function.size(); i++) {
                switch (function.get(i).toString()) {
                    case "QCH40010017":
                        checkAuthFunctionDTO.setQch40010017("Y");
                        break;
                    case "QCH40010034":
                        checkAuthFunctionDTO.setQch40010034("Y");
                        break;
                    case "QCH40010051":
                        checkAuthFunctionDTO.setQch40010051("Y");
                        break;
                    case "QCH40010055":
                        checkAuthFunctionDTO.setQch40010055("Y");
                        break;
                }
            }
        }
        model.addAttribute("problems", list);
        model.addAttribute("typeMaps", propertyRectifyCRMSelDTO);
        model.addAttribute("problem", propertyRectifyCRMSelDTO);
        model.addAttribute("cityNum", cityNum);
        model.addAttribute("signaType", propertyRectifyCRMSelDTO.getSignaType());
        model.addAttribute("planNum", planNum);
        model.addAttribute("function", checkAuthFunctionDTO);
        if ("11".equals(propertyRectifyCRMSelDTO.getProType())) {
            return "plan/propertyPrint11";
        } else if ("12".equals(propertyRectifyCRMSelDTO.getProType())) {
            return "plan/propertyPrint12";
        } else {
            if (!StringUtil.isEmpty(propertyRectifyCRMSelDTO.getSignaType()) && "1".equals(propertyRectifyCRMSelDTO.getSignaType())) {
                return "plan/printSignImagePrint1";
            } else {
                return "plan/propertyPrint13";
            }
        }

    }
}
