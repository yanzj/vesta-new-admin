package com.maxrocky.vesta.presentation.admin.controller.announcement;

import com.maxrocky.vesta.application.DTO.AnnouncementDTO;
import com.maxrocky.vesta.application.admin.dto.TransfersDto;
import com.maxrocky.vesta.application.inf.AnnouncementScopeService;
import com.maxrocky.vesta.application.inf.AnnouncementService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.application.inf.VoteService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.AnnouncementEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping(value = "/announcement")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class AnnouncementController {

    @Autowired
    AnnouncementService announcementService;

    @Autowired
    AnnouncementScopeService announcementScopeService;

    @Autowired
    VoteService voteService;

    @Autowired
    StaffUserService staffUserService;

    /**
     * 活动详情查询展示
     *
     * @param announcementDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/List.html")
    public String showNewsList(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                               AnnouncementDTO announcementDTO, Model model, WebPage webPage) {
        //#1.数据回显
        model.addAttribute("announcementDTO", announcementDTO);
        //关键词查询(标题、内容)
        announcementDTO.setContent(announcementDTO.getTitle());
        try {
            //设置状态
            List<TransfersDto> statusMap = new ArrayList<TransfersDto>();
            statusMap.add(new TransfersDto(999, "--请选择状态--"));
            statusMap.add(new TransfersDto(0, "未发布"));
            statusMap.add(new TransfersDto(1, "已发布"));
            model.addAttribute("statusMap", statusMap);
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            announcementDTO.setRoleScopeList(roleScopeList);
            //执行检索
            List<AnnouncementDTO> announcementDTOs = this.announcementService.queryAllByPage(announcementDTO, webPage);
            model.addAttribute("announcementDTOs", announcementDTOs);
            //记录集合长度，如果没有查询出数据则不可导出
            model.addAttribute("isExcel",announcementDTOs.size());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "查询出现异常请联系管理员");
            return "/announcement/AnxnouncementList";
        }
        return "/announcement/AnnouncementList";
    }

    /**
     * 状态修改
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult updateStatus(AnnouncementDTO announcementDTO, Model model, @Valid String select, @Valid Integer flag) {
        try {
            AnnouncementEntity announcementEntity = this.announcementService.get(AnnouncementEntity.class, announcementDTO.getId());
            switch (select) {
                case "releaseStatus":
                    announcementEntity.setReleaseStatus(flag);
                    break;
                case "cancelStatus":
                    announcementEntity.setStatus(flag);
                    break;
            }
            this.announcementService.saveOrUpdate(announcementEntity);
            return new SuccessApiResult(announcementEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorApiResult(500, "服务器端故障，请联系管理人员");
        }
    }

    /**
     * 新增/修改 页面跳转
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/transferPage")
    public String transferPage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                               AnnouncementDTO announcementDTO,
                               @Valid String menuId,
                               Model model, HttpServletRequest request, HttpServletResponse response) {

        //数据干扰，初始化model属性
        /*model.addAttribute("city", null);
        model.addAttribute("allCityInScope", null);
        model.addAttribute("cityInScope", null);
        model.addAttribute("projectInScope", null);
        model.addAttribute("voteList", null);
        model.addAttribute("isVote", null);
        model.addAttribute("vote", null);*/

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
        //model.addAttribute("city", city);
        request.setAttribute("city", cityList);
        //#2.根据通告id查询所有通告范围,根据通告范围，返回前台数据,划分为二级数据
        if (!StringUtils.isEmpty(announcementDTO.getId())) {
            List<Object[]> announcementScopes = this.announcementScopeService.queryByAnnouncementId(announcementDTO.getId());
            //all
            String allCityInScope = "";
            //城市
            String cityInScope = "";
            //城市Id
            String cityIdInScope = "";
            //项目
            String projectInScope = "";
            //项目Id
            String projectIdInScope = "";
            for (Object[] announcementScope : announcementScopes) {
                if (null != announcementScope[2])
                    allCityInScope = allCityInScope + announcementScope[2].toString() + ",";
                if (null != announcementScope[1])
                    if (!cityInScope.contains(announcementScope[1].toString())){
                        cityInScope = cityInScope + announcementScope[1].toString() + ",";
                    }
                if (null != announcementScope[3])
                    projectInScope = projectInScope + announcementScope[3].toString() + ",";
                if (null != announcementScope[4]){
                    if (!cityIdInScope.contains(announcementScope[4].toString())){
                        cityIdInScope = cityIdInScope + announcementScope[4].toString() + ",";
                    }
                }

                if (null != announcementScope[5])
                    projectIdInScope = projectIdInScope + announcementScope[5].toString() + ",";
            }
            if (!StringUtils.isEmpty(allCityInScope))
                allCityInScope = StringUtils.substring(allCityInScope, 0, allCityInScope.length() - 1);
            if (!StringUtils.isEmpty(cityInScope))
                cityInScope = StringUtils.substring(cityInScope, 0, cityInScope.length() - 1);
            if (!StringUtils.isEmpty(projectInScope))
                projectInScope = StringUtils.substring(projectInScope, 0, projectInScope.length() - 1);
            //model.addAttribute("allCityInScope", allCityInScope);
            request.setAttribute("allCityInScope", allCityInScope);
            //model.addAttribute("cityInScope", cityInScope);
            request.setAttribute("cityInScope", cityInScope);
            //model.addAttribute("projectInScope", projectInScope);
            request.setAttribute("projectInScope", projectInScope);
            request.setAttribute("cityIdInScope", cityIdInScope);
            request.setAttribute("projectIdInScope",projectIdInScope);
        }
        //#4.是否具有投票功能
        List<TransfersDto> isVote = new ArrayList<TransfersDto>();
        isVote.add(new TransfersDto(0, "否"));
        isVote.add(new TransfersDto(1, "是"));
        //model.addAttribute("isVote", isVote);
        request.setAttribute("isVote", isVote);
        //#5.基础数据回显
        if (!StringUtil.isEmpty(announcementDTO.getId())) {
            AnnouncementEntity announcementEntity;
            announcementEntity = this.announcementService.get(AnnouncementEntity.class, announcementDTO.getId());
            //model.addAttribute("announcementEntity", announcementEntity);
            request.setAttribute("announcementEntity", announcementEntity);
        }
        //#6.查询所有的投票id与名称  List<TransfersDto>
        List<TransfersDto> voteList = new ArrayList<TransfersDto>();
        List<Object[]> list = this.voteService.queryAllVoteTitle();
        for (Object[] objects1 : list) {
            voteList.add(new TransfersDto(objects1[0].toString(), objects1[1].toString()));
        }
        //model.addAttribute("voteList", voteList);
        request.setAttribute("voteList", voteList);

        //#7.活动关联投票信息
        if (!StringUtil.isEmpty(announcementDTO.getId())) {
            List<Object[]> list1 = this.announcementService.queryVoteByAnnouncementId(announcementDTO.getId());
            TransfersDto vote = new TransfersDto();
            Object[] obj = {""};
            if (list1.size() > 0) {
                obj = list1.get(0);
                vote.setSid(obj[0].toString());
                vote.setName(obj[1].toString());
            }
            //model.addAttribute("vote", vote);
            request.setAttribute("vote", vote);
        }
        return "/announcement/AnnouncementUpdate";
    }

    /**
     * 根据城市id查询城市下所有项目
     * @param cityId
     * @param model
     */
    @RequestMapping(value = "/queryProjectNameByCityId/{cityId}/{menuId}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult queryBatchByProNum(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @PathVariable(value = "cityId") String cityId,
                                        @PathVariable(value = "menuId") String menuId,Model model) {
        List<Object[]> list = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(), cityId,menuId);
//        List<Object[]> list = this.announcementService.listProject(cityId);
        return new SuccessApiResult(list);
    }

    /**
     * 根据城市id查询城市下所有项目
     * @param cityId
     * @param model
     */
    @RequestMapping(value = "/queryProjectNameByCityId/{cityId}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult queryBatchByProNum(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @PathVariable(value = "cityId") String cityId,
                                        Model model) {
        List<Object[]> list = staffUserService.listProjectByCity(cityId);
//        List<Object[]> list = this.announcementService.listProject(cityId);
        return new SuccessApiResult(list);
    }

    /**
     * 新增/修改 功能
     *
     * @param announcementDTO
     * @return
     */
    @RequestMapping(value = "/addOrUpdatePage", method = RequestMethod.POST)
    public ModelAndView addOrUpdatePage(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, AnnouncementDTO announcementDTO) {
        try {
            //#设置登陆人
            announcementDTO.setReleasePerson(userPropertystaffEntity.getStaffName());
            this.announcementService.updateAnnouncementAndScope(userPropertystaffEntity,announcementDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("redirect:../error/500.html");
        }

        return new ModelAndView("redirect:/announcement/List.html");
    }

    /**
     * 删除 功能
     * @param announcementDTO
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAnnouncementVote", method = RequestMethod.POST)
    public Map<String,Object> deleteAnnouncementVote(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, AnnouncementDTO announcementDTO) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            //#1.删除announcement_detail与,announcement_vote表中信息,并且记录日志
            this.announcementService.deleteAnnouncementVote(userPropertystaffEntity, announcementDTO);
            resultMap.put("error", 0);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", 1);
        }
        return resultMap;
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
                              @Valid AnnouncementDTO announcementDTO,
                              HttpServletResponse httpServletResponse,HttpServletRequest httpServletRequest){
        try {
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(user.getStaffId());
            announcementDTO.setRoleScopeList(roleScopeList);
            return announcementService.exportExcel(user,announcementDTO,httpServletResponse, httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }
    }

    /**
     * 校验用户是否可以操作该社区公告(权限范围)
     */
    @ResponseBody
    @RequestMapping(value = "/checkEdit/{announcementId}/{menuId}")
    public Map<String,Object> checkEdit(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity,
                                        @PathVariable(value = "announcementId")String announcementId,
                                        @PathVariable(value = "menuId")String menuId){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            //用户权限项目范围
            List<Map<String, Object>> roleProjectList = staffUserService.getProjectScopeByStaffId(userPropertystaffEntity.getStaffId(),menuId);
            //社区公告发布项目范围
            List<Map<String, Object>> announcementProjectList = announcementService.getProjectScopeByAnnouncementId(announcementId);
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
            resultMap.put("error",1);
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error",-1);
        }
        return resultMap;
    }

}
