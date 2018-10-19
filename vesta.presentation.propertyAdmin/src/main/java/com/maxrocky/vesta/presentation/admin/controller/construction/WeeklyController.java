package com.maxrocky.vesta.presentation.admin.controller.construction;

import com.maxrocky.vesta.application.statisticsAndWeekly.inf.StatisticsAndWeeklyService;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.application.weekly.DTO.ExportExcel;
import com.maxrocky.vesta.application.weekly.DTO.WeeklyDTO;
import com.maxrocky.vesta.application.weekly.DTO.WeeklyExcelDTO;
import com.maxrocky.vesta.application.weekly.inf.WeeklyService;
import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 周报
 * Created by Itzxs on 2018/4/8.
 */
@Controller
@RequestMapping(value = "/weekly")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class WeeklyController {

    @Autowired
    AuthAgencyService authAgencyService;

    @Autowired
    WeeklyService weeklyService;

    @Autowired
    SecurityProjectService securityProjectService;

    @Autowired
    StatisticsAndWeeklyService statisticsAndWeeklyService;

    /**
     * @param response
     * @throws IOException
     * 所有机构
     */
    @RequestMapping(value = "/weeklyAgency",method = RequestMethod.GET)
    public void weeklyAgency(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,HttpServletResponse response) throws IOException {
        //需要用户id来限制权限
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<AgencyTreeDTO> agencyTreeDTOList = weeklyService.getAgencyList(userInformationEntity);
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOList).toString());
    }

    @RequestMapping(value = "/weekly.html",method = RequestMethod.GET)
    public String Weekly(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                          HttpServletRequest request, WebPage webPage, Model model){
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String agencyId = request.getParameter("agencyId");
        String agencyType = request.getParameter("agencyType");
        String visits = String.valueOf(request.getParameter("visits"));

        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cook = cookies[i];
            if (cook.getName().equals("agencyId") && !StringUtil.isEmpty(cook.getValue())) { //获取当前选中的节点
                agencyId = cook.getValue();
            }
            if (cook.getName().equals("agencyType") && !StringUtil.isEmpty(cook.getValue())) { //获取当前选中的节点
                agencyType = cook.getValue();
            }
        }

        AuthAgencyESEntity authAgencyESEntity = new AuthAgencyESEntity();
        if(agencyId != null && !"".equals(agencyId)){//根据具体项目查找
            authAgencyESEntity = weeklyService.getESAuthAgencyByID(agencyId);
        }else{//根据用户权限找到用户的最高级权限
            authAgencyESEntity = weeklyService.getTopESAuthAgency(userInformationEntity);
        }
        //登录人员所拥有的项目Id
        List<String> agencyIdList = weeklyService.getAgencyIdList(userInformationEntity);
        //根据具体点击项目查询项目下的所有子项目
        Map<String,Object> map= getAgencyListByAgencyId(agencyId,agencyType);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        if(map.get("projectIds") == null && (visits == "1" || "1".equals(visits)) && StringUtil.isEmpty(agencyId) && StringUtil.isEmpty(agencyType)){
            map.put("projectIds",agencyIdList);
        }
        //返回的各个统计的总数据
        Map<String,WeeklyDTO> count = new HashMap<>();
        //日常巡检统计
        WeeklyDTO weeklyDTO_ProjectInspection = weeklyService.getDailyPatrolInspectionData(map);
        count.put("ProjectInspection",weeklyDTO_ProjectInspection);
        //检查验收统计
        WeeklyDTO weeklyDTO_ProjectExamine = weeklyService.getInspectAcceptanceData(map);
        count.put("ProjectExamine",weeklyDTO_ProjectExamine);
        //材料验收统计
        WeeklyDTO weeklyDTO_ProjectMaterial = weeklyService.getProjectMaterialData(map);
        count.put("ProjectMaterial",weeklyDTO_ProjectMaterial);
        //样板点评统计
        WeeklyDTO weeklyDTO_ProjectSampleCheck = weeklyService.getProjectSampleCheckData(map);
        count.put("ProjectSampleCheck",weeklyDTO_ProjectSampleCheck);
        //关键工序统计
        WeeklyDTO weeklyDTO_ProjectKeyProcesses = weeklyService.getProjectKeyProcessesData(map);
        count.put("ProjectKeyProcesses",weeklyDTO_ProjectKeyProcesses);
        //旁站统计
        WeeklyDTO weeklyDTO_ProjectSideStation = weeklyService.getProjectSideStationData(map);
        count.put("ProjectSideStation",weeklyDTO_ProjectSideStation);
        //领导检查统计
        WeeklyDTO weeklyDTO_ProjectLeadersCheck = weeklyService.getProjectLeadersCheckData(map);
        count.put("ProjectLeadersCheck",weeklyDTO_ProjectLeadersCheck);
        model.addAttribute("count",count);
        model.addAttribute("agencyIdList",agencyIdList);
        model.addAttribute("project",authAgencyESEntity);

        List allProjectData = dataByProjectAll(startDate,endDate,agencyId,agencyType,userInformationEntity);
        String allProjectDataJson = null;
        if(!allProjectData.isEmpty() && allProjectData.size()>0){
            allProjectDataJson="[";
            for (int i=0;i<allProjectData.size();i++){
                allProjectDataJson+="{name:"+"'"+((Map)allProjectData.get(i)).get("projectName")+"'";
                allProjectDataJson+=",";
                allProjectDataJson+="CheckNum:"+"'"+((WeeklyDTO)((Map)allProjectData.get(i)).get("allData")).getCheckNum()+"'";
                allProjectDataJson+=",";
                allProjectDataJson+="PercentOfPass:"+"'"+((WeeklyDTO)((Map)allProjectData.get(i)).get("allData")).getPercentOfPass()+"'";
                allProjectDataJson+=",";
                allProjectDataJson+="OverTwoWeekNum:"+"'"+((WeeklyDTO)((Map)allProjectData.get(i)).get("allData")).getOverTwoWeekNum()+"'";
                allProjectDataJson+="}";
                if (i!=allProjectData.size()-1){
                    allProjectDataJson+=",";
                }
            }
            allProjectDataJson+="]";
        }else{
            allProjectDataJson="[";
            for (int i=0;i<allProjectData.size();i++){
                allProjectDataJson+="{name:"+"''";
                allProjectDataJson+=",";
                allProjectDataJson+="CheckNum:"+"''";
                allProjectDataJson+=",";
                allProjectDataJson+="PercentOfPass:"+"''";
                allProjectDataJson+=",";
                allProjectDataJson+="OverTwoWeekNum:"+"''";
                allProjectDataJson+="}";
                if (i!=allProjectData.size()-1){
                    allProjectDataJson+=",";
                }
            }
            allProjectDataJson+="]";
        }
        model.addAttribute("allProjectData",allProjectData);
        model.addAttribute("allProjectDataJson",allProjectDataJson);
        model.addAttribute("startDate",startDate);
        model.addAttribute("endDate",endDate);

        AuthAgencyESEntity authAgencyESEntityTop = weeklyService.getTopESAuthAgency(userInformationEntity);
        String type = agencyType;
        if(StringUtil.isEmpty(type) && (visits == "1" || "1".equals(visits))){
            if(authAgencyESEntityTop != null){
                type = authAgencyESEntityTop.getAgencyType();
            }
        }
        model.addAttribute("project_type",type);
        model.addAttribute("agencyType",agencyType);
        model.addAttribute("agencyId",agencyId);

        //获取二级项目数量
        List<AuthAgencyESEntity> secondaryAgencyIdList = new ArrayList<>();
        if(!StringUtil.isEmpty(agencyId) && !StringUtil.isEmpty(agencyType)
                && !"100000002".equals(agencyType)){
            //次级项目
            secondaryAgencyIdList = weeklyService.getSecondaryAgencyIdByAgencyIdAndType(agencyId,agencyType);
        }else if("100000002".equals(agencyType)){
            secondaryAgencyIdList.add(weeklyService.getESAuthAgencyByID(agencyId));
        }else{//根据用户权限获取的次级项目
            secondaryAgencyIdList = weeklyService.getSecondaryAgencyByUser(userInformationEntity);
        }
        int projectSize = 0;
        if(!secondaryAgencyIdList.isEmpty()){
            projectSize = secondaryAgencyIdList.size();
        }
        model.addAttribute("projectSize",projectSize);
        return "weekly/weekly";
    }

    @RequestMapping(value = "/dataByProject")
    public void dataByProject(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,HttpServletRequest request,HttpServletResponse response) throws IOException {
        //需要用户id来限制权限
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String agencyId = request.getParameter("agencyId");
        String agencyType = request.getParameter("agencyType");
        String requestParam = request.getParameter("requestParam");

        List<Object> dataList = new ArrayList<>();
        List<AuthAgencyESEntity> secondaryAgencyIdList = new ArrayList<>();
        //具体点击项目
        if(!StringUtil.isEmpty(agencyId) && !StringUtil.isEmpty(agencyType)
                && !"100000002".equals(agencyType)){
            //次级项目
            secondaryAgencyIdList = weeklyService.getSecondaryAgencyIdByAgencyIdAndType(agencyId,agencyType);
        }else if("100000002".equals(agencyType)){
            secondaryAgencyIdList.add(weeklyService.getESAuthAgencyByID(agencyId));
        }else{//根据用户权限获取的次级项目
            secondaryAgencyIdList = weeklyService.getSecondaryAgencyByUser(userInformationEntity);
        }
        if(secondaryAgencyIdList != null && !secondaryAgencyIdList.isEmpty()){
            for (int i = 0; i < secondaryAgencyIdList.size(); i++) {
                Map<String,Object> map = new HashMap<>();
                Map<String,Object> data = new HashMap<>();
                map = getAgencyListByAgencyId(secondaryAgencyIdList.get(i).getAgencyId(),secondaryAgencyIdList.get(i).getAgencyType());
                map.put("startDate",startDate);
                map.put("endDate",endDate);
                data.put("projectName",secondaryAgencyIdList.get(i).getAgencyName());
                if(requestParam != null && !"".equals(requestParam)){
                    if("ProjectInspection".equals(requestParam)){
                        data.put("projectData",weeklyService.getDailyPatrolInspectionData(map));
                    }else if("ProjectExamine".equals(requestParam)){
                        data.put("projectData",weeklyService.getInspectAcceptanceData(map));
                    }else if("ProjectMaterial".equals(requestParam)){
                        data.put("projectData",weeklyService.getProjectMaterialData(map));
                    }else if("ProjectSampleCheck".equals(requestParam)){
                        data.put("projectData",weeklyService.getProjectSampleCheckData(map));
                    }else if("ProjectKeyProcesses".equals(requestParam)){
                        data.put("projectData",weeklyService.getProjectKeyProcessesData(map));
                    }else if("ProjectSideStation".equals(requestParam)){
                        data.put("projectData",weeklyService.getProjectSideStationData(map));
                    }else if("ProjectLeadersCheck".equals(requestParam)){
                        data.put("projectData",weeklyService.getProjectLeadersCheckData(map));
                    }else{
                        data.put("projectData",null);
                    }
                }else{
                    data.put("projectData",null);
                }
                dataList.add(data);
            }
        }
        response.getWriter().print(JSONArray.fromObject(dataList).toString());
    }

    @RequestMapping(value = "/export")
    public void export(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,HttpServletRequest request,HttpServletResponse response) {
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String agencyId = request.getParameter("agencyId");
        String agencyType = request.getParameter("agencyType");
        export(response,userInformationEntity,startDate,endDate, agencyId, agencyType);
    }

    public void export(HttpServletResponse response,UserInformationEntity userInformationEntity,String startDate,String endDate,String  agencyId,String agencyType){
        AuthAgencyESEntity authAgencyESEntity = new AuthAgencyESEntity();
        //次级项目id
        List<AuthAgencyESEntity> secondaryAgencyIdList = new ArrayList<>();
        if(!StringUtil.isEmpty(agencyId)){//根据具体项目查找
            authAgencyESEntity = weeklyService.getESAuthAgencyByID(agencyId);
            if(!"100000002".equals(agencyType)){
                //次级项目
                secondaryAgencyIdList = weeklyService.getSecondaryAgencyIdByAgencyIdAndType(agencyId,agencyType);
            }else{
                secondaryAgencyIdList.add(weeklyService.getESAuthAgencyByID(agencyId));
            }
        }else{
            //根据用户权限找到用户的最高级权限
            authAgencyESEntity = weeklyService.getTopESAuthAgency(userInformationEntity);
            secondaryAgencyIdList = weeklyService.getSecondaryAgencyByUser(userInformationEntity);
        }
        //根据具体点击项目查询项目下的所有子项目
        Map<String,Object> map= getAgencyListByAgencyId(agencyId,agencyType);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        /*if(map.get("projectIds") == null){
            //登录人员所拥有的项目Id
            List<String> agencyIdList = weeklyService.getAgencyIdList(userInformationEntity);
            map.put("projectIds",agencyIdList);
        }*/

        //封装数据为excle表格式
        WeeklyExcelDTO weeklyExcelDTO = new WeeklyExcelDTO();
        weeklyExcelDTO.setProjectName(authAgencyESEntity.getAgencyName());
        //日常巡检统计
        weeklyExcelDTO.setProjectInspection(projectDetail(weeklyService.getDailyPatrolInspectionData(map),"ProjectInspection"));
        //检查验收统计
        weeklyExcelDTO.setProjectExamine(projectDetail(weeklyService.getInspectAcceptanceData(map),"ProjectExamine"));
        //材料验收统计
        weeklyExcelDTO.setProjectMaterial(projectDetail(weeklyService.getProjectMaterialData(map),"ProjectMaterial"));
        //样板点评统计
        weeklyExcelDTO.setProjectSampleCheck(projectDetail(weeklyService.getProjectSampleCheckData(map),"ProjectSampleCheck"));
        //关键工序统计
        weeklyExcelDTO.setProjectKeyProcesses(projectDetail(weeklyService.getProjectKeyProcessesData(map),"ProjectKeyProcesses"));
        //旁站统计
        weeklyExcelDTO.setProjectSideStation(projectDetail(weeklyService.getProjectSideStationData(map),"ProjectSideStation"));
        //领导检查统计
        weeklyExcelDTO.setProjectLeadersCheck(projectDetail(weeklyService.getProjectLeadersCheckData(map),"ProjectLeadersCheck"));

        String [] headArr = {"层级名称","日常巡检使用详情","检查验收使用详情","材料验收使用详情","样板点评使用详情","关键工序使用详情","旁站使用详情","领导检查使用详情"};

        List<WeeklyExcelDTO> weeklyExcelDTOList = new ArrayList<>();
        weeklyExcelDTOList.add(weeklyExcelDTO);

        //封装二级菜单的数据，如果是项目权限，则没有二级数据
        if(!weeklyService.isProjectAuthority(userInformationEntity) && !"100000002".equals(agencyType) && "100000002" != agencyType){
            if(secondaryAgencyIdList != null && !secondaryAgencyIdList.isEmpty()){
                for (int i = 0; i < secondaryAgencyIdList.size(); i++) {
                    Map<String,Object> secondaryMap = new HashMap<>();
                    //二级项目的数据封装
                    WeeklyExcelDTO weeklyExcelSecondaryDTO = new WeeklyExcelDTO();
                    secondaryMap = getAgencyListByAgencyId(secondaryAgencyIdList.get(i).getAgencyId(),secondaryAgencyIdList.get(i).getAgencyType());
                    secondaryMap.put("startDate",startDate);
                    secondaryMap.put("endDate",endDate);
                    weeklyExcelSecondaryDTO.setProjectName(secondaryAgencyIdList.get(i).getAgencyName());
                    weeklyExcelSecondaryDTO.setProjectInspection(projectDetail(weeklyService.getDailyPatrolInspectionData(secondaryMap),"ProjectInspection"));
                    weeklyExcelSecondaryDTO.setProjectExamine(projectDetail(weeklyService.getInspectAcceptanceData(secondaryMap),"ProjectExamine"));
                    weeklyExcelSecondaryDTO.setProjectMaterial(projectDetail(weeklyService.getProjectMaterialData(secondaryMap),"ProjectMaterial"));
                    weeklyExcelSecondaryDTO.setProjectSampleCheck(projectDetail(weeklyService.getProjectSampleCheckData(secondaryMap),"ProjectSampleCheck"));
                    weeklyExcelSecondaryDTO.setProjectKeyProcesses(projectDetail(weeklyService.getProjectKeyProcessesData(secondaryMap),"ProjectKeyProcesses"));
                    weeklyExcelSecondaryDTO.setProjectSideStation(projectDetail(weeklyService.getProjectSideStationData(secondaryMap),"ProjectSideStation"));
                    weeklyExcelSecondaryDTO.setProjectLeadersCheck(projectDetail(weeklyService.getProjectLeadersCheckData(secondaryMap),"ProjectLeadersCheck"));
                    weeklyExcelDTOList.add(weeklyExcelSecondaryDTO);
                }
            }
        }
        ExportExcel ee = new ExportExcel();
        ee.exportExcel(headArr,weeklyExcelDTOList,"周报",response);
    }

    public String projectDetail(WeeklyDTO weeklyDTO,String param){
        String checkNum = null;
        String percentOfPass = null;
        String overTwoWeekNum = null;
        String firstParty = null;
        if(weeklyDTO != null){
            firstParty = String.valueOf(weeklyDTO.getFirstParty());
            checkNum = String.valueOf(weeklyDTO.getCheckNum());
            percentOfPass = String.valueOf(weeklyDTO.getPercentOfPass());
            overTwoWeekNum = String.valueOf(weeklyDTO.getOverTwoWeekNum());
        }
        StringBuffer detail = new StringBuffer();
        if("ProjectInspection".equals(param) || "ProjectMaterial".equals(param)){
            detail.append("甲方："+firstParty+"次\n");
            detail.append("监理："+String.valueOf(Integer.parseInt(checkNum)-Integer.parseInt(firstParty))+"次\n");
            detail.append("整改合格率："+percentOfPass+"%\n");
            detail.append("超过2周以上未整改内容："+overTwoWeekNum+"条");
        }else if("ProjectExamine".equals(param) || "ProjectSideStation".equals(param)){
            detail.append("监理："+checkNum+"次\n");
            detail.append("整改合格率："+percentOfPass+"%\n");
            detail.append("超过2周以上未整改内容："+overTwoWeekNum+"条");
        }else if("ProjectSampleCheck".equals(param) || "ProjectKeyProcesses".equals(param)){
            detail.append("甲方："+checkNum+"次\n");
            detail.append("整改合格率："+percentOfPass+"%\n");
            detail.append("超过2周以上未整改内容："+overTwoWeekNum+"条");
        }else{
            detail.append("检查次数："+checkNum+"次\n");
            detail.append("整改合格率："+percentOfPass+"%\n");
            detail.append("超过2周以上未整改内容："+overTwoWeekNum+"条");
        }
        return detail.toString();
    }

    public List dataByProjectAll(String startDate,String endDate,String agencyId,String agencyType,UserInformationEntity userInformationEntity){
        List<Object> dataList = new ArrayList<>();
        List<AuthAgencyESEntity> secondaryAgencyIdList = new ArrayList<>();
        //具体点击项目
        if(!StringUtil.isEmpty(agencyId) && !StringUtil.isEmpty(agencyType)
                && !"100000002".equals(agencyType)){
            //次级项目
            secondaryAgencyIdList = weeklyService.getSecondaryAgencyIdByAgencyIdAndType(agencyId,agencyType);
        }else if("100000002".equals(agencyType)){
            AuthAgencyESEntity authAgencyESEntity = weeklyService.getESAuthAgencyByID(agencyId);
            if(authAgencyESEntity != null){
                secondaryAgencyIdList.add(weeklyService.getESAuthAgencyByID(agencyId));
            }
        }else{//根据用户权限获取的次级项目
            secondaryAgencyIdList = weeklyService.getSecondaryAgencyByUser(userInformationEntity);
        }
        if(secondaryAgencyIdList != null && !secondaryAgencyIdList.isEmpty()){
            for (int i = 0; i < secondaryAgencyIdList.size(); i++) {
                Map<String,Object> map = new HashMap<>();
                Map<String,Object> data = new HashMap<>();
                map = getAgencyListByAgencyId(secondaryAgencyIdList.get(i).getAgencyId(),secondaryAgencyIdList.get(i).getAgencyType());
                map.put("startDate",startDate);
                map.put("endDate",endDate);
                data.put("projectName",secondaryAgencyIdList.get(i).getAgencyName());
                data.put("allData",weeklyService.getWeeklyDTOData(map));
                dataList.add(data);
            }
        }
        return dataList;
    }

    //根据具体点击项目查询项目下的所有子项目
    public Map<String,Object> getAgencyListByAgencyId(String agencyId,String agencyType){
        Map<String,Object> map= new HashMap<>();
        List<String> agencyIdList = new ArrayList<>();
        if(!StringUtil.isEmpty(agencyId) && !StringUtil.isEmpty(agencyType)){
            if(!"100000002".equals(agencyType)){
                List<AuthAgencyESEntity> AuthAgencyESEntityList= weeklyService.getAgencyListByAgencyId(agencyId,agencyType);
                if(AuthAgencyESEntityList.size() > 0 && !AuthAgencyESEntityList.isEmpty()){
                    for (int i = 0; i < AuthAgencyESEntityList.size(); i++) {
                        agencyIdList.add(AuthAgencyESEntityList.get(i).getAgencyId());
                    }
                    map.put("projectIds",agencyIdList);
                }
            }else{
                agencyIdList.add(agencyId);
                map.put("projectIds",agencyIdList);
            }
        }
        return map;
    }

    //app端预置周报统计数据
    @RequestMapping(value = "/saveWeeklyByApp.html")
    public void saveWeeklyByApp() {
//        statisticsAndWeeklyService.statisticsByTime("2018-03-19","2018-03-26","2018-03-25","2018-04-01","1", 5);
//        statisticsAndWeeklyService.statisticsByTime("2018-03-26","2018-04-02","2018-04-01","2018-04-01","1", 5);
//        statisticsAndWeeklyService.statisticsByTime("2018-04-02","2018-04-09","2018-04-08","2018-04-01","1", 5);
//        statisticsAndWeeklyService.statisticsByTime("2018-04-09","2018-04-16","2018-04-15","2018-04-01","1", 5);
//
        statisticsAndWeeklyService.statisticsByTime("2018-01-01","2018-02-01","2018-01-31","2018-04-01","2", 4);
        statisticsAndWeeklyService.statisticsByTime("2018-02-01","2018-03-01","2018-02-28","2018-04-01","2", 4);
        statisticsAndWeeklyService.statisticsByTime("2018-03-01","2018-04-01","2018-03-31","2018-04-01","2", 4);
    }
}
