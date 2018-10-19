package com.maxrocky.vesta.presentation.admin.controller.community;

import com.maxrocky.vesta.application.admin.dto.*;
import com.maxrocky.vesta.application.inf.AnnouncementService;
import com.maxrocky.vesta.application.inf.HouseProjectService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.inf.UserFeedbackService;
import com.maxrocky.vesta.application.service.BlacklistService;
import com.maxrocky.vesta.application.service.CommunityService;
import com.maxrocky.vesta.domain.model.CommunityActivityApplyTimeRangeEntity;
import com.maxrocky.vesta.domain.model.CommunityActivityInfoScopeEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghj on 2016/2/3.
 */

@Controller
@RequestMapping(value = "/community")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})

public class ActivityController {

    @Autowired
    CommunityService communityService;

    @Autowired
    HouseProjectService houseProjectService;

    @Autowired
    AnnouncementService announcementService;

    @Autowired
    StaffUserService staffUserService;

    @Autowired
    BlacklistService blacklistService;

    /**
     * 校验用户是否可以操作该活动(权限范围)
     */
    @ResponseBody
    @RequestMapping(value = "/checkEdit/{activityId}/{menuId}")
    public Map<String,Object> checkEdit(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @PathVariable(value = "activityId")String activityId,
                                        @PathVariable(value = "menuId")String menuId){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //用户权限项目范围
            List<Map<String, Object>> roleProjectList = staffUserService.getProjectScopeByStaffId(userPropertystaffEntity.getStaffId(),menuId);
            //活动发布项目范围
            List<Map<String, Object>> announcementProjectList = communityService.getProjectScopeByActivityId(activityId);
            //用户权限范围包含发布范围
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
            resultMap.put("error",1);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * 初始化活动列表
     * @param userPropertystaffEntity
     * @param activityAdminDto
     * @param webPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/activityManage.html")
        public String activityManage(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid ActivityAdminDto activityAdminDto,WebPage webPage,Model model){
        //权限参与的城市列表
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),"005400030000");
        model.addAttribute("city", cityList);
        //回显权限参与的项目列表及项目
        List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), activityAdminDto.getCity(),"005400030000");
        if(null != projectList && projectList.size() > 0){
            if (projectList.get(0)[0].equals("0")){
                projectList.remove(0);
            }
        }
        model.addAttribute("projectList",projectList);
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
        activityAdminDto.setRoleScopeList(roleScopeList);

        List<ActivityAdminDto> activityAdminDtos = communityService.listActivity(activityAdminDto, webPage);

        for(ActivityAdminDto activityAdmin:activityAdminDtos){

            Map<String, Object> map=communityService.queryByActivityId(activityAdmin.getActivityId());

            activityAdmin.setCu_projectName(map.get("projectNames").toString());
        }

        model.addAttribute("activityAdminDtos",activityAdminDtos);
        model.addAttribute("activityAdminDto", activityAdminDto);
        Map projects =houseProjectService.getProjects();
        model.addAttribute("projects",projects);
        model.addAttribute("isExcel",activityAdminDtos.size());
        return "/community/ActivityManage";
    }

    /**
     * 删除活动--将活动改成已结束
     * @param userPropertystaffEntity
     * @param activityAdminDto
     * @param webPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/activityDelete")
    public ModelAndView activityDelete(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid ActivityAdminDto activityAdminDto,WebPage webPage,Model model){

        activityAdminDto.setOperator(userPropertystaffEntity.getStaffName());
        boolean result = communityService.deleteActivity(userPropertystaffEntity,activityAdminDto);
        return new ModelAndView("redirect:../community/activityManage.html");
    }

    /**
     * 跳转到修改页面
     * @param userPropertystaffEntity
     * @param activityAdminDto
     * @param webPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoActivityUpdate")
    public String gotoActivityUpdate(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid ActivityAdminDto activityAdminDto,WebPage webPage,Model model){

        activityAdminDto = communityService.getActivityById(activityAdminDto.getActivityId());
        Map<String, Object> map = communityService.queryProjectByCommunityId(activityAdminDto.getActivityId());
        model.addAttribute("projectIds",map.get("projectCodes").toString());
        model.addAttribute("projectNameList", map.get("projectNames").toString());
        model.addAttribute("cityName", map.get("cityName"));

        model.addAttribute("activityAdminDto", activityAdminDto);
        return "/community/ActivityUpdate";
    }

    /**
     * 跳转到编辑页面_Wyd
     * @param userPropertystaffEntity
     * @param activityAdminDto
     * @param model
     * @return String
     */
    @RequestMapping(value = "/gotoActivityEdit")
    public String gotoActivityEdit(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid ActivityAdminDto activityAdminDto,
                                   @Valid String menuId,Model model) throws InvocationTargetException, IllegalAccessException {
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),menuId);
        model.addAttribute("city", cityList);
        //项目范围数据准备(回显)
        Map<String, Object> map = communityService.queryProjectByCommunityId(activityAdminDto.getActivityId());
        model.addAttribute("projectIds",map.get("projectCodes"));
        model.addAttribute("projectNameList", map.get("projectNames"));
        model.addAttribute("cityName", map.get("cityName"));
        //首页推荐图数据准备(回显)
        String communityImageSrc = communityService.getCommunityImageById(activityAdminDto.getActivityId());
        model.addAttribute("communityImageSrc",communityImageSrc);
        //活动详情
        activityAdminDto = communityService.getActivityById(activityAdminDto.getActivityId());
        model.addAttribute("activityAdminDto", activityAdminDto);
        //黑名单列表
        if (null != activityAdminDto.getIsBlacklist() && activityAdminDto.getIsBlacklist() == 1){
            List<BlacklistDTO> blacklistList = blacklistService.getBlacklistList(null, null);
            model.addAttribute("blacklistList",blacklistList);
        }
        return "/community/ActivityEdit";
    }

    /**
     * 更新编辑活动_Wyd
     */
    @RequestMapping(value = "/updateEditActivity",method = RequestMethod.POST)
    public ModelAndView updateEditActivity(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                           @Valid ActivityAdminDto activityAdminDto){
        activityAdminDto.setOperator(userPropertystaffEntity.getStaffName());
        communityService.updateEditActicity(userPropertystaffEntity,activityAdminDto);
        return new ModelAndView("redirect:../community/activityManage.html");
    }

    /**
     * 去分配发布房产范围
     */
    @RequestMapping(value = "/toAssignHouseScope")
    public ModelAndView toAssignHouseScope(ActivityAdminDto activityAdminDto,Model model){
        try{
            model.addAttribute("activityAdminDto",activityAdminDto);
            List<CommunityActivityInfoScopeEntity> activityScopeList = communityService.getActivityScopeList(activityAdminDto.getActivityId());
            if (activityScopeList.size() <= 7){
                model.addAttribute("error",0);
                model.addAttribute("activityScopeList",activityScopeList);
            }else{
                //项目过多,已经不适合进行分配房产范围
                model.addAttribute("error",1);
            }
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error",-1);
        }
        return new ModelAndView("/community/AssignHouseScope");
    }

    /**
     * 获取房产范围树形结构JSON
     */
    @ResponseBody
    @RequestMapping(value = "/getHouseScopeTree")
    public Map<String,Object> getHouseScopeTree(ActivityAdminDto activityAdminDto){
        Map<String,Object> resultMap = new HashedMap();
        try{
            ActivityAdminDto adminDto = communityService.getActivityById(activityAdminDto.getActivityId());
            adminDto.setProjectCode(activityAdminDto.getProjectCode());
            String scopeTree = communityService.getScopeTreeByHouseScope(adminDto);
            resultMap.put("error",0);
            resultMap.put("scopeTree",scopeTree);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * 保存活动房产范围
     */
    @ResponseBody
    @RequestMapping(value = "/saveHouseScope",method = RequestMethod.POST)
    public Map<String,Object> saveHouseScope(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                            @Valid ActivityAdminDto activityAdminDto){
        Map<String,Object> resultMap = new HashedMap();
        try{
            activityAdminDto.setOperator(userPropertystaffEntity.getStaffName());
            communityService.saveHouseScope(activityAdminDto);
            resultMap.put("error",0);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

    /**
     * 修改活动资料
     * @param userPropertystaffEntity
     * @param activityAdminDto
     * @param webPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/activityUpdate",method = RequestMethod.POST)
    public ModelAndView activityUpdate(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid ActivityAdminDto activityAdminDto,WebPage webPage,Model model){

        boolean result = communityService.updateActicity(activityAdminDto);
        return new ModelAndView("redirect:../community/activityManage.html");
    }

    /**
     * 跳转到活动添加页面
     * @param userPropertystaffEntity
     * @param activityAdminDto
     * @param webPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/gotoActivityAdd")
    public String gotoActivityAdd(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                  @Valid ActivityAdminDto activityAdminDto,
                                  @Valid String menuId,
                                  WebPage webPage,Model model){
        activityAdminDto.setOperator(userPropertystaffEntity.getStaffName());
        Map projects =houseProjectService.getProjects();
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),menuId);
        model.addAttribute("city",cityList);


        model.addAttribute("projects", projects);
        //boolean result = communityService.updateActicity(activityAdminDto);
        return "/community/ActivityAdd";
    }


    /**
     * 查看报名人
     * @param userPropertystaffEntity
     * @param activityId
     * @param activityApplyAdminDto
     * @param webPage
     * @param model
     * @return
     */
//    @RequestMapping(value = "/showActivityApply.html")
    public String showActivityApply_bak(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Param String activityId,@Valid ActivityApplyAdminDto activityApplyAdminDto,WebPage webPage,Model model){

        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
        activityApplyAdminDto.setRoleScopeList(roleScopeList);
        //获取报名人列表
        activityApplyAdminDto.setActivityId(activityId);
        List<ActivityApplyAdminDto> activityApplyAdminDtos = communityService.listApplyDtos(activityApplyAdminDto, webPage);

        model.addAttribute("activityApplyAdminDtos", activityApplyAdminDtos);
        //返回搜索条件
        model.addAttribute("activityApplyAdminDto", activityApplyAdminDto);
        Map projects = houseProjectService.getProjects();
        model.addAttribute("projects", projects);
        //城市信息
        List<Object[]> objects = this.announcementService.listCity();
        List<TransfersDto> city1 = new ArrayList<TransfersDto>();
        city1.add(new TransfersDto("0", "全部城市"));
        for (Object[] object : objects) {
            String cid = object[0].toString();
            String name = object[1].toString();
            city1.add(new TransfersDto(cid, name));
        }
        model.addAttribute("city", city1);
        model.addAttribute("isExcel", activityApplyAdminDtos.size());

        return "/community/ActivityApplyManage";
    }

    /**
     * 查看活动项目报名 —— From 陆鑫鑫,查询极慢,重写
     * @param userPropertystaffEntity
     * @param activityId
     * @param activityApplyAdminDto
     * @param webPage
     * @param model
     * @return
     */
//    @RequestMapping(value = "/ActivityProjectApply.html")
    public String ActivityProjectApply_bak(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Param String activityId,@Valid ActivityApplyAdminDto activityApplyAdminDto,WebPage webPage,Model model){
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
        activityApplyAdminDto.setRoleScopeList(roleScopeList);
        //获取报名人列表
        activityApplyAdminDto.setActivityId(activityId);
        List<ActivityApplyAdminDto> activityApplyAdminDtos = communityService.activityProjectApply(activityApplyAdminDto, webPage);

        Map acProjects= communityService.queryProjectByActivityId(activityId);

        model.addAttribute("activityApplyAdminDtos", activityApplyAdminDtos);
        //返回搜索条件
        model.addAttribute("activityApplyAdminDto", activityApplyAdminDto);

        Map projects = houseProjectService.getProjects();

        //model.addAttribute("projects",projects);
        model.addAttribute("projects",acProjects);
        model.addAttribute("activityId",activityId);
        //城市信息
        List<Object[]> objects = this.announcementService.listCity();
        List<TransfersDto> city1 = new ArrayList<TransfersDto>();
        city1.add(new TransfersDto("0", "全部城市"));
        for (Object[] object : objects) {
            String cid = object[0].toString();
            String name = object[1].toString();
            city1.add(new TransfersDto(cid, name));
        }
        model.addAttribute("city", city1);
        model.addAttribute("isExcel", activityApplyAdminDtos.size());

       /* String str="[{'name':' 而且企鹅企鹅'},{'name':'切切去'},{'name':'去而且'},{'name':'去去去恩文'}]";
        JSONArray jsonArr = JSONArray.fromObject(str);
        model.addAttribute("jsonArr", jsonArr);*/



        return "/community/ActivityProjectApply";
    }

    /**
     * 查询活动报名明细 WeiYangDong_2016-12-12 预备重写/未完成重写
     * WeiYangDong_2016-12-19 重写/完成重写
     */
    @RequestMapping(value = "/ActivityProjectApply.html")
    public String ActivityProjectApply(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                       @Valid ActivityApplyAdminDto activityApplyAdminDto,
                                       WebPage webPage,Model model){
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
        activityApplyAdminDto.setRoleScopeList(roleScopeList);
        //设置活动ID
        activityApplyAdminDto.setActivityId(activityApplyAdminDto.getActivityId());
        //分页设置并回显
        webPage.setPageSize(20);
        List<Map<String, Object>> list = communityService.getApplyList(activityApplyAdminDto, null);
        webPage.setRecordCount(list.size());
        model.addAttribute("webPage", webPage);
        //导出Excel总数回显
        model.addAttribute("isExcel", list.size());
        //检索条件回显
        model.addAttribute("activityApplyAdminDto", activityApplyAdminDto);
        model.addAttribute("activityId",activityApplyAdminDto.getActivityId());
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(), activityApplyAdminDto.getMenuId());
        model.addAttribute("city", cityList);
        if (null != activityApplyAdminDto.getScopeId() && !"".equals(activityApplyAdminDto.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), activityApplyAdminDto.getScopeId(), activityApplyAdminDto.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        //执行查询
        ActivityAdminDto activityInfo = communityService.getActivityById(activityApplyAdminDto.getActivityId());
        model.addAttribute("activityInfo",activityInfo);
        List<Map<String, Object>> applyList = communityService.getApplyList(activityApplyAdminDto, webPage);
        //处理applyInfo Json格式
        Map<String,Object> apply = null;
        for (int i = 0, length = applyList.size(); i<length; i++){
            apply = applyList.get(i);
            Object applyInfo = apply.get("applyInfo");
            if (null != applyInfo && !"null".equals(applyInfo)){
                apply.put("applyInfoJson",JSONArray.fromObject(applyInfo));
            }
        }
        model.addAttribute("applyList", applyList);
        return "/community/ActivityProjectApply";
    }

    /**
     * 获取报名信息列表 WeiYangDong_2016-12-20 重写/完成重写
     */
    @RequestMapping(value = "/showActivityApply.html")
    public String showActivityApply(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                    @Valid ActivityApplyAdminDto activityApplyAdminDto,WebPage webPage,Model model){
        //获取数据范围权限
        List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
        activityApplyAdminDto.setRoleScopeList(roleScopeList);
        //分页设置并回显
        webPage.setPageSize(20);
        List<Map<String, Object>> list = communityService.getApplyList(activityApplyAdminDto, null);
        webPage.setRecordCount(list.size());
        model.addAttribute("webPage", webPage);
        //导出Excel总数回显
        model.addAttribute("isExcel", list.size());
        //检索条件回显
        model.addAttribute("activityApplyAdminDto", activityApplyAdminDto);
        model.addAttribute("activityId", activityApplyAdminDto.getActivityId());
        List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(), activityApplyAdminDto.getMenuId());
        model.addAttribute("city", cityList);
        if (null != activityApplyAdminDto.getScopeId() && !"".equals(activityApplyAdminDto.getScopeId())) {
            //城市不为空,回显项目列表
            List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), activityApplyAdminDto.getScopeId(), activityApplyAdminDto.getMenuId());
            model.addAttribute("projectList", projectList);
        }
        //执行查询
        List<Map<String, Object>> applyList = communityService.getApplyList(activityApplyAdminDto, webPage);
        model.addAttribute("applyList", applyList);

        return "/community/ActivityApplyManage";
    }

    /**
     * 新增活动
     */
    @RequestMapping(value = "/activityAdd",method = RequestMethod.POST)
    public ModelAndView activityAdd(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                    @Valid ActivityAdminDto activityAdminDto){
        activityAdminDto.setOperator(userPropertystaffEntity.getStaffName());
        boolean result = communityService.addActivity(userPropertystaffEntity,activityAdminDto);
        return new ModelAndView("redirect:../community/activityManage.html");
    }

    //更改发布状态
    @RequestMapping(value = "/turnActivityStatus",method = RequestMethod.GET)
    public ModelAndView turnActivityStatus(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid ActivityAdminDto activityAdminDto){

        activityAdminDto.setOperator(userPropertystaffEntity.getStaffName());

        boolean result = communityService.turnActivity(activityAdminDto);

        return new ModelAndView("redirect:../community/activityManage.html");
    }

    /**
     * 保存活动报名时间段配置
     */
    @ResponseBody
    @RequestMapping(value = "/saveApplyTimeRange",method = RequestMethod.POST)
    public Map<String,Object> saveApplyTimeRange(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                                 ActivityAdminDto activityAdminDto){
        Map<String,Object> resultMap = new HashedMap();
        activityAdminDto.setOperator(user.getStaffName());
        try{
            communityService.saveApplyTimeRange(activityAdminDto);
            resultMap.put("error","0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

    /**
     * 获取活动报名时间段列表
     */
    @ResponseBody
    @RequestMapping(value = "/getApplyTimeRangeList",method = RequestMethod.POST)
    public Map<String,Object> getApplyTimeRangeList(ActivityAdminDto activityAdminDto){
        Map<String,Object> resultMap = new HashedMap();
        try{
            List<CommunityActivityApplyTimeRangeEntity> reservationTimeRangeList = communityService.getApplyTimeRangeList(activityAdminDto);
            resultMap.put("reservationTimeRangeList",reservationTimeRangeList);
            resultMap.put("error","0");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

    /***
     * 导出excel操作   活动报名
     * param:user
     * param:httpServletResponse
     * return
     */
    @RequestMapping("/exportExcelhd")
    @ResponseBody
    public String exportExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,@Valid ActivityAdminDto activityAdminDto,
                              HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            activityAdminDto.setRoleScopeList(roleScopeList);
            return communityService.exportExcel(activityAdminDto,httpServletResponse, httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

    /***
     * 导出excel操作   报名信息
     * param:user
     * param:httpServletResponse
     * return
     */
    @RequestMapping("/applyExportExcel")
    @ResponseBody
    public String applyExportExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,
                                   @Valid ActivityApplyAdminDto activityApplyAdminDto,WebPage webPage,HttpServletResponse response, HttpServletRequest request){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            activityApplyAdminDto.setRoleScopeList(roleScopeList);
//            return communityService.activityProjectApply(activityApplyAdminDto, httpServletResponse, httpServletRequest);
            String fileName = "活动报名信息列表";
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
            String title = "报名信息统计";
            String[] headers = {"序号","真实姓名","用户名","项目","房产信息","联系方式","报名时间","活动主题","报名人数"};
            ServletOutputStream out = response.getOutputStream();
            communityService.applyExportExcel(title,headers,out,activityApplyAdminDto);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }
    /***
     * 导出excel操作   活动项目报名信息
     * param:user
     * param:httpServletResponse
     * return
     */
    @RequestMapping("/activityProjectApplyExcel")
    @ResponseBody
    public String activityProjectApplyExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                            @Valid ActivityApplyAdminDto activityApplyAdminDto,
                                            HttpServletResponse response, HttpServletRequest request){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            activityApplyAdminDto.setRoleScopeList(roleScopeList);
//            return communityService.activityProjectApply(activityApplyAdminDto, httpServletResponse, httpServletRequest);
            String fileName = "活动报名信息列表";
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
            String title = "报名信息统计";
            //导出Exl处理报名信息字段
            List<String> headerList = new ArrayList<>();
            headerList.add("序号");
            headerList.add("真实姓名");
            headerList.add("用户名");
            headerList.add("项目");
            headerList.add("房产信息");
            headerList.add("联系方式");
            headerList.add("报名时间");
            headerList.add("活动主题");
            headerList.add("报名人数");
            headerList.add("时间段");
            ActivityAdminDto activityAdminDto = communityService.getActivityById(activityApplyAdminDto.getActivityId());
            List<JsonDto> applyInfo2 = activityAdminDto.getApplyInfo2();
            if(null != applyInfo2 && applyInfo2.size() > 0){
                for (JsonDto jsonDto : applyInfo2){
                    headerList.add(jsonDto.getName());
                }
            }
            String[] headers = (String[])headerList.toArray(new String[headerList.size()]);
//            String[] headers = {"序号","真实姓名","用户名","项目","房产信息","联系方式","报名时间","活动主题","报名信息","报名人数"};
            ServletOutputStream out = response.getOutputStream();
            communityService.activityProjectApplyExportExcel(title,headers,out,activityApplyAdminDto);
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }
}
