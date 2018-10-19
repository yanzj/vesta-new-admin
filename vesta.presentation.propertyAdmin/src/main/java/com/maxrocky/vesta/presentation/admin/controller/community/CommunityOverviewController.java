package com.maxrocky.vesta.presentation.admin.controller.community;

import com.maxrocky.vesta.application.admin.dto.CommunityNewsDto;
import com.maxrocky.vesta.application.admin.dto.CommunityOverviewDto;
import com.maxrocky.vesta.application.admin.dto.DeliveryPlanCrmDto;
import com.maxrocky.vesta.application.admin.dto.TransfersDto;
import com.maxrocky.vesta.application.inf.AnnouncementService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.service.CommunityDeliveryPlanCRMService;
import com.maxrocky.vesta.application.service.CommunityOverviewService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.CommunityDetailEntity;
import com.maxrocky.vesta.domain.model.CommunityOverviewEntity;
import com.maxrocky.vesta.domain.model.CommunityOverviewReservationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.CommunityOverviewRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.StringUtil;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/09
 * Time: 11:46
 * Describe:
 */
@Controller
@RequestMapping(value = "/overview")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class CommunityOverviewController {

    @Autowired
    CommunityOverviewService communityOverviewService;
    @Autowired
    CommunityDeliveryPlanCRMService communityDeliveryPlanCRMService;
    @Autowired
    AnnouncementService announcementService;
    @Autowired
    StaffUserService staffUserService;
    @Autowired
    ImgService imgService;


    /**
     * 更新批次信息
     */
    @RequestMapping(value = "/updateDeliveryPlan.html",method = RequestMethod.POST)
    public ModelAndView updateDeliveryPlan(DeliveryPlanCrmDto deliveryPlanCrmDto){
        ModelAndView modelAndView = new ModelAndView("redirect:../deliveryPlan/DeliveryPlan.html?menuId=005400010000");
        try{
            this.communityDeliveryPlanCRMService.update(deliveryPlanCrmDto);
        }catch (Exception e){
            e.printStackTrace();
            return modelAndView;
        }
        return modelAndView;
    }

    /**
     * 品牌新闻查询展示
     * @param communityOverviewDto
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/List.html")
    public String showNewsList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                               CommunityOverviewDto communityOverviewDto, Model model, WebPage webPage) {
        //数据回显
        model.addAttribute("communityOverviewDto", communityOverviewDto);
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            communityOverviewDto.setRoleScopeList(roleScopeList);
            //#1.1项目_樓盤名稱
//            List<String> projects = communityOverviewService.listProject();
//            model.addAttribute("projects", projects);
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),communityOverviewDto.getMenuId());
            model.addAttribute("city", cityList);
            if (null != communityOverviewDto.getScopeId() && !"".equals(communityOverviewDto.getScopeId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(),communityOverviewDto.getScopeId(),communityOverviewDto.getMenuId());
                model.addAttribute("projectList",projectList);
            }
            //#2
            List<CommunityOverviewDto> communityOverviewDtos = this.communityOverviewService.queryAllByPage(communityOverviewDto, webPage);
            model.addAttribute("communityOverviewDtos", communityOverviewDtos);
            /**=========================================================**/
            //记录集合长度，如果没有查询出数据则不可导出
            model.addAttribute("isExcel",communityOverviewDtos.size());
            /**=========================================================**/
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "查询出现异常请联系管理员");
            return "/community/OverviewList";
        }
        return "/community/OverviewList";
    }

    /**
     * 状态修改
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult updateStatus(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,CommunityOverviewDto communityOverviewDto, Model model, @Valid String select, @Valid Integer flag) {
        try {
            CommunityOverviewEntity communityOverviewEntity = this.communityOverviewService.get(CommunityOverviewEntity.class, communityOverviewDto.getId());
            switch (select) {
                case "releaseStatus":
                    communityOverviewEntity.setReleaseStatus(flag);
                    break;
                case "cancelStatus":
                    communityOverviewEntity.setStatus(flag);
                    break;
            }
            this.communityOverviewService.saveOrUpdate(communityOverviewEntity);

            //物理删除楼盘项目关系数据
            communityOverviewService.deleteCommunityOverviewScope(userPropertystaffEntity, communityOverviewEntity.getId());
            return new SuccessApiResult(communityOverviewEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorApiResult(500, "服务器端故障，请联系管理人员");
        }
    }

    /**
     * 新增/修改 页面跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "/transferPage")
    public String transferPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                               CommunityOverviewDto communityOverviewDto,
                               @Valid String menuId,
                               Model model) {
        //#1.查询所有CRM项目城市列表
//        List<Object[]> objects = this.announcementService.listCity();
//        List<TransfersDto> city = new ArrayList<TransfersDto>();
//        city.add(new TransfersDto("0", "全部城市"));
//        for (Object[] object : objects) {
//            String cid = object[0].toString();
//            String name = object[1].toString();
//            city.add(new TransfersDto(cid, name));
//        }
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),menuId);
        model.addAttribute("city", cityList);
        //规避新增脏数据(项目列表,城市列表,开盘时间未置空)
        model.addAttribute("orderDate", null);
        model.addAttribute("projectIds",null);
        model.addAttribute("projectNameList",null);
        model.addAttribute("cityIdInScope",null);
        model.addAttribute("allCityInScope",null);
        if (!StringUtil.isEmpty(communityOverviewDto.getId())) {
            CommunityOverviewEntity communityOverviewEntity = this.communityOverviewService.get(CommunityOverviewEntity.class, communityOverviewDto.getId());
            String orderDate = DateUtils.format(communityOverviewEntity.getOrderDate(), DateUtils.FORMAT_SHORT);
            model.addAttribute("communityOverview", communityOverviewEntity);
            model.addAttribute("orderDate", orderDate);
            //回显项目列表
            Map<String, Object> map = communityOverviewService.queryProjectByCommunityId(communityOverviewDto.getId());
            model.addAttribute("projectIds",map.get("projectCodes"));
            model.addAttribute("projectNameList",map.get("projectNames"));
            //回显城市列表
            Map<String, Object> mapCity = communityOverviewService.queryCityByProjectIds(map.get("projectCodes").toString());
            model.addAttribute("cityIdInScope",mapCity.get("cityIds"));
            model.addAttribute("allCityInScope",mapCity.get("cityNames"));
        }
        return "/community/OverviewUpdate";
    }

    /**
     * 跳转到楼盘详情编辑页面
     * @param userPropertystaffEntity
     * @param communityOverviewDto
     * @param model
     * @return ModelAndView
     */
    @RequestMapping(value = "/editOverviewDetail")
    public ModelAndView editOverviewDetail(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                     CommunityOverviewDto communityOverviewDto,
                                     Model model){
        /*
        //判断修改或者新增
        if (StringUtil.isEmpty(communityOverviewDto.getId())) {
            //新增
            communityOverviewDto.setCreatePerson(userPropertystaffEntity.getStaffName());

        } else {
            //修改
            communityOverviewDto.setOperator(userPropertystaffEntity.getStaffName());
            //回显楼盘详情

        }
        this.communityOverviewService.saveOrUpdateOverview(userPropertystaffEntity,communityOverviewDto);
        */
        //获取去编辑的所有信息
        List<CommunityDetailEntity> list = new ArrayList<>();
        if (!StringUtils.isNullOrEmpty(communityOverviewDto.getId())) {
            list = communityOverviewService.getAllDetails(communityOverviewDto.getId());
        }
        model.addAttribute("size", list.size());
        model.addAttribute("communityDetailList", list);

        //全景图_图片上传
        String fileName = imgService.uploadAdminImage(communityOverviewDto.getHomePageimgpath(), ImgType.ACTIVITY);
        //图片地址特殊处理
        //String urlTitle = "http://211.94.93.223/images/";
        String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
        //fileName = urlTitle + fileName.replace("/opt/image.server/images/images_source/", "");
        fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
        communityOverviewDto.setPanoramaImg(fileName);
        model.addAttribute("communityOverview", communityOverviewDto);
        return new ModelAndView("/community/OverviewDetailEdit");
    }

    /**
     * 保存或更新楼盘详情信息
     * @param userPropertystaffEntity
     * @param communityOverviewDto
     * @return ModelAndView
     */
    @RequestMapping(value = "/saveOrUpdateOverviewDetail")
    public ModelAndView saveOrUpdateOverviewDetail(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                                   CommunityOverviewDto communityOverviewDto){
        if (communityOverviewDto.getReleaseStatus() == 1) {
            communityOverviewDto.setReleasePerson(userPropertystaffEntity.getStaffName());
        }
        //判断修改或者新增
        if (StringUtil.isEmpty(communityOverviewDto.getId())) {
            communityOverviewDto.setCreatePerson(userPropertystaffEntity.getStaffName());
        } else {
            communityOverviewDto.setOperator(userPropertystaffEntity.getStaffName());
        }

        //编辑楼盘详情页
        communityOverviewService.saveOrUpdateDetailEdit(userPropertystaffEntity, communityOverviewDto);
        return new ModelAndView("redirect:../overview/List.html?menuId=005300020000");
    }

    /**
     * 新增/修改 功能
     */
    @RequestMapping(value = "/addOrUpdatePage", method = RequestMethod.POST)
    public ModelAndView addOrUpdatePage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @RequestParam(value = "homePageimgFile",required = false) MultipartFile homePageimgFile,
                                        @RequestParam(value = "floorPlanFile",required = false) MultipartFile[] floorPlanFiles,
                                        @RequestParam(value = "masterPlanFile",required = false) MultipartFile[] masterPlanFiles,
                                        @RequestParam(value = "interiorDesignFile",required = false) MultipartFile[] interiorDesignFiles,
                                        @RequestParam(value = "gardenDesignFile",required = false) MultipartFile[] gardenDesignFiles,
                                        @RequestParam(value = "supportingFacilitiesFile",required = false) MultipartFile[] supportingFacilitiesFiles,
                                        CommunityOverviewDto communityOverviewDto,
                                        Model model) {
        if (communityOverviewDto.getReleaseStatus() == 1) {
            communityOverviewDto.setReleasePerson(userPropertystaffEntity.getStaffName());
        }
        //设置首页推荐图
        if (null != homePageimgFile){
            String homePageimgUrl = imgUpload(homePageimgFile);
            if (homePageimgUrl.lastIndexOf("/") != homePageimgUrl.length()-1){
                communityOverviewDto.setHomePageimgUrl(homePageimgUrl);
            }
        }
        //设置户型图地址列表
        List<String> floorPlanUrlList = new ArrayList<>();
        if (null != floorPlanFiles && floorPlanFiles.length > 0){
            for (MultipartFile floorPlanFile : floorPlanFiles){
                String floorPlanUrl = imgUpload(floorPlanFile);
                if (floorPlanUrl.lastIndexOf("/") != floorPlanUrl.length()-1){
                    floorPlanUrlList.add(floorPlanUrl);
                }
            }
        }
        communityOverviewDto.setFloorPlanUrlList(floorPlanUrlList);
        //设置总平面图地址列表
        List<String> masterPlanUrlList = new ArrayList<>();
        if (null != masterPlanFiles && masterPlanFiles.length > 0){
            for (MultipartFile masterPlanFile : masterPlanFiles){
                String masterPlanUrl = imgUpload(masterPlanFile);
                if (masterPlanUrl.lastIndexOf("/") != masterPlanUrl.length()-1){
                    masterPlanUrlList.add(masterPlanUrl);
                }
            }
        }
        communityOverviewDto.setMasterPlanUrlList(masterPlanUrlList);
        //设置室内设计图地址列表
        List<String> interiorDesignList = new ArrayList<>();
        if (null != interiorDesignFiles && interiorDesignFiles.length > 0){
            for (MultipartFile interiorDesignFile : interiorDesignFiles){
                String interiorDesignUrl = imgUpload(interiorDesignFile);
                if (interiorDesignUrl.lastIndexOf("/") != interiorDesignUrl.length()-1){
                    interiorDesignList.add(interiorDesignUrl);
                }
            }
        }
        communityOverviewDto.setInteriorDesignList(interiorDesignList);
        //设置园林设计图地址列表
        List<String> gardenDesignList = new ArrayList<>();
        if (null != gardenDesignFiles && gardenDesignFiles.length > 0){
            for (MultipartFile gardenDesignFile : gardenDesignFiles){
                String gardenDesignUrl = imgUpload(gardenDesignFile);
                if (gardenDesignUrl.lastIndexOf("/") != gardenDesignUrl.length()-1){
                    gardenDesignList.add(gardenDesignUrl);
                }
            }
        }
        communityOverviewDto.setGardenDesignList(gardenDesignList);
        //设置配套设施图片地址列表
        List<String> supportingFacilitiesList = new ArrayList<>();
        if (null != supportingFacilitiesFiles && supportingFacilitiesFiles.length > 0){
            for (MultipartFile supportingFacilitiesFile : supportingFacilitiesFiles){
                String supportingFacilitiesUrl = imgUpload(supportingFacilitiesFile);
                if (supportingFacilitiesUrl.lastIndexOf("/") != supportingFacilitiesUrl.length()-1){
                    supportingFacilitiesList.add(supportingFacilitiesUrl);
                }
            }
        }
        communityOverviewDto.setSupportingFacilitiesList(supportingFacilitiesList);
        //判断修改或者新增
        if (StringUtil.isEmpty(communityOverviewDto.getId())) {
            communityOverviewDto.setCreatePerson(userPropertystaffEntity.getStaffName());
        } else {
            communityOverviewDto.setOperator(userPropertystaffEntity.getStaffName());
        }
        this.communityOverviewService.saveOrUpdateOverview(userPropertystaffEntity, communityOverviewDto);
        return new ModelAndView("redirect:../overview/List.html?menuId="+communityOverviewDto.getMenuId());
    }

    /**
     * 跳转到详情页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public String page(CommunityOverviewDto communityOverviewDto, Model model,@PathVariable("name") String name) {
        return "/community/"+name;
    }

    /**
     * 获取楼盘预约列表
     */
    @RequestMapping(value = "/overviewReservation.html")
    public ModelAndView overviewReservation(CommunityOverviewDto communityOverviewDto,WebPage webPage){
        ModelAndView modelAndView = new ModelAndView("/community/OverviewReservation");
        try{
            modelAndView.addObject("communityOverviewDto",communityOverviewDto);
            List<CommunityOverviewReservationEntity> overviewReservationList = communityOverviewService.getOverviewReservationList(communityOverviewDto,webPage);
            modelAndView.addObject("overviewReservationList",overviewReservationList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    /**
     * 多图片上传
     * @param model
     * @param request
     * @param response
     * @param files
     * @return
     */
    @RequestMapping(value = "detailAdd",method = RequestMethod.POST)
    public ModelAndView detailAdd(Model model,HttpServletRequest request,HttpServletResponse response
    ,@RequestParam(value = "files",required = false) MultipartFile[] files){
        //判断file数组不能为空并且长度大于0
        if(files!=null&&files.length>0){
            //循环获取file数组中得文件
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                ImgService imgService = new ImgServiceImpl();
                String fileName = imgService.uploadAdminImage(file, ImgType.ACTIVITY);
                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                fileName = urlTitle + fileName.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");


                //保存文件
                System.out.println("--------" + file.getOriginalFilename() + "------" + file.getContentType());
                System.out.println("图片URL:"+urlTitle+"--"+fileName);

            }
        }

        return new ModelAndView("redirect:../overview/List.html?menuId=005300020000");
    }
    /***
     * 导出excel操作
     * param:user
     * param:httpServletResponse
     * return
     */
    @RequestMapping(value="/exportExcel")
    @ResponseBody
    public String exportExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                              HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest,CommunityOverviewDto communityOverviewDto, Model model, WebPage webPage){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            communityOverviewDto.setRoleScopeList(roleScopeList);
            return communityOverviewService.exportExcel(user,httpServletResponse,communityOverviewDto,webPage,httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }

    }

    /**
     * 校验用户是否可以操作该楼盘信息(权限范围)
     */
    @ResponseBody
    @RequestMapping(value = "/checkEdit/{communityOverviewId}/{menuId}")
    public Map<String,Object> checkEdit(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @PathVariable(value = "communityOverviewId")String communityOverviewId,
                                        @PathVariable(value = "menuId")String menuId){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //用户权限项目范围
            List<Map<String, Object>> roleProjectList = staffUserService.getProjectScopeByStaffId(userPropertystaffEntity.getStaffId(),menuId);
            //社区公告发布项目范围
            List<Map<String, Object>> announcementProjectList = communityOverviewService.getProjectScopeByCommunityOverviewId(communityOverviewId);
            //用户权限范围包含公告发布范围
            for (Map<String,Object> announcementProject : announcementProjectList){
                int flag = 0;
                String projectId = announcementProject.get("projectId").toString();
                for (Map<String,Object> roleProject : roleProjectList){
                    if (projectId.equals(roleProject.get("projectId").toString())){
                        flag = 1;
                    }
                }
                if (flag != 1){
                    resultMap.put("error",flag);
                    return resultMap;
                }
            }
            resultMap.put("error", 1);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", -1);
        }
        return resultMap;
    }

    public String imgUpload(MultipartFile multipartFile){
        String imgUrl = "";
        try{
            //处理图片上传
            if (null != multipartFile){
                ImgService imgService = new ImgServiceImpl();
                //图片地址特殊处理
                String urlTitle = ImageConfig.PIC_OSS_ADMIN_URL;
                imgUrl = imgService.uploadAdminImage(multipartFile, ImgType.VOTEIMG);
                imgUrl = urlTitle + imgUrl.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return imgUrl;
    }
}
