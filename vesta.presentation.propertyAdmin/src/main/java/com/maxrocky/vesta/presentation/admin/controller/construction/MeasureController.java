package com.maxrocky.vesta.presentation.admin.controller.construction;

import com.maxrocky.vesta.application.baseData.inf.ProjectBuildingService;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.CheckAuthFunctionDTO;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.application.inf.HouseFloorService;
import com.maxrocky.vesta.application.inf.HouseUnitService;
import com.maxrocky.vesta.application.measure.DTO.*;
import com.maxrocky.vesta.application.measure.inf.MeasureService;
import com.maxrocky.vesta.application.project.inf.SecurityProjectService;
import com.maxrocky.vesta.application.projectAccredit.inf.ProjectUserAccreditService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.measure.model.MeasureDetailEntity;
import com.maxrocky.vesta.domain.measure.model.MeasureEntity;
import com.maxrocky.vesta.domain.model.AuthAgencyESEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.StringUtil;
import net.sf.json.JSONArray;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Itzxs on 2018/7/9.
 * 实测实量
 */
@Controller
@RequestMapping(value = "/measure")
@SessionAttributes(types = {UserInformationEntity.class, String.class}, value = {"authPropertystaff", "menulist", "secanViewlist"})
public class MeasureController {

    @Autowired
    SecurityProjectService securityProjectService;

    @Autowired
    ProjectBuildingService projectBuildingService;

    @Autowired
    HouseUnitService houseUnitService;

    @Autowired
    HouseFloorService houseFloorService;

    @Autowired
    MeasureService measureService;

    @Autowired
    AuthAgencyService authAgencyService;

    @Autowired
    ProjectUserAccreditService projectUserAccreditService;

    @RequestMapping(value = "/measureList.html")
    public String measureList(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,WebPage webPage, Model model,
                              HttpServletRequest request,@Valid MeasureDTO measureDTO){
        //判断是否是首次进来
        String visits = String.valueOf(request.getParameter("visits"));

        //根据用户权限查的所拥有的项目
        List<AuthAgencyESEntity> authAgencyESEntities = measureService.getAgencyList(userInformationEntity);
        model.addAttribute("agencyEntities",authAgencyESEntities);
        model.addAttribute("measureDTO",measureDTO);
        Map<String,String> inspectionPhases = measureService.getInspectionPhaseList();
        model.addAttribute("inspectionPhases",inspectionPhases);
        CheckAuthFunctionDTO checkAuthFunctionDTO=new CheckAuthFunctionDTO();
        List<String> function=authAgencyService.getProjectAuthFunctionByStaffId(userInformationEntity.getStaffId(),"4","2");
        if(function!=null){
            //校验是否有   搜索  ESH40020103  下载模板 ESH40020104  导出全数据 ESH40020105  添加实测实量 ESH40020106
            //  二维码配置 ESH40020107  导出数据 ESH40020108  详情 ESH40020109  二维码详情 ESH40020110
            for(int i=0;i<function.size();i++){
                switch (function.get(i).toString()) {
                    case "ESH40020103":
                        checkAuthFunctionDTO.setEsh40020103("Y");
                        break;
                    case "ESH40020104":
                        checkAuthFunctionDTO.setEsh40020104("Y");
                        break;
                    case "ESH40020105":
                        checkAuthFunctionDTO.setEsh40020105("Y");
                        break;
                    case "ESH40020106":
                        checkAuthFunctionDTO.setEsh40020106("Y");
                        break;
                    case "ESH40020107":
                        checkAuthFunctionDTO.setEsh40020107("Y");
                        break;
                    case "ESH40020108":
                        checkAuthFunctionDTO.setEsh40020108("Y");
                        break;
                    case "ESH40020109":
                        checkAuthFunctionDTO.setEsh40020109("Y");
                        break;
                    case "ESH40020110":
                        checkAuthFunctionDTO.setEsh40020110("Y");
                        break;
                }
            }
        }
        model.addAttribute("function",checkAuthFunctionDTO);
        //如果是首次进来，则不显示数据
        if("1".equals(visits)){
            return "/construction/measure/measureList";
        }
        //判断是否筛选到项目
        if (!StringUtil.isEmpty(measureDTO.getProjectId())) {
            webPage = measureService.getMeasuresCount(webPage,measureDTO);
            List<MeasureDetailDTO> measureDetailDTOS = measureService.getMeasures(webPage,measureDTO);
            MeasureDataDTO measureDataDTO = measureService.getMeasureData(measureDTO);
            model.addAttribute("measureDataDTO",measureDataDTO);
            model.addAttribute("measureDetailDTOS",measureDetailDTOS);
            model.addAttribute("webPage",webPage);
            model.addAttribute("isExcel",measureDetailDTOS.size());
            return "/construction/measure/measureList";
        }
        return "/construction/measure/measureList";
    }

    @RequestMapping(value = "/measureData.html",method = RequestMethod.POST)
    public void measureData(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,WebPage webPage, Model model,
                              HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,@Valid MeasureDTO measureDTO){
        httpServletResponse.setContentType("text/html;charset=utf-8");
        //判断是否筛选到项目
        Map<String,Object> map = measureService.getMeasureData(measureDTO,userInformationEntity);
        try{
            httpServletResponse.getWriter().print(JSONArray.fromObject(map).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 根据项目id获取楼栋数据
     *
     * @return thirdLevel
     */
    @RequestMapping(value = "/getBuilds")
    public ApiResult getBuilds(String projectId) {
        Map map  = projectBuildingService.getBuildListByProjectId(projectId);
        return new SuccessApiResult(map);
    }

    /**
     * 根据楼栋ID获取楼层ID
     *
     * @return thirdLevel
     */
    @RequestMapping(value = "/getFloors")
    public ApiResult getFloors(String buildingId) {
        Map map = projectBuildingService.getProjectFloors(buildingId);
        return new SuccessApiResult(map);
    }

    /**
     * 根据楼栋ID获取楼层ID
     *
     * @return thirdLevel
     */
    @RequestMapping(value = "/isFirstSave")
    public ApiResult isFirstSave(String projectId,String buildingId,String floorId) {
        List<MeasureDetailEntity> measureDetailEntities = measureService.isFirstSave(projectId,buildingId,floorId);
        Map<String,String> map = new HashMap<>();
        if(measureDetailEntities != null && measureDetailEntities.size() > 0){
            map.put("isFirst","1");
            map.put("isOpenQrCode",measureDetailEntities.get(0).getIsOpenQrCode());
        }else{
            map.put("isFirst","0");
        }
        return new SuccessApiResult(map);
    }

    /**
     * 根据项目id和楼层id获取单元信息
     *
     * @return thirdLevel
     */
    @RequestMapping(value = "/getUnits")
    public ApiResult getUnits(String buildingId,String floorId) {
        Map map = measureService.getUnitByBuildIdAndFloorId(buildingId,floorId);
        return new SuccessApiResult(map);
    }

    @RequestMapping(value = "/editMeasure.html",method = RequestMethod.POST)
    public void editMeasure(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, MeasureDetailEntity measureDetailEntity, MeasureEntity measureEntity){
        MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
        MultipartFile measureDetail = request.getFile("measureDetail");
        httpServletResponse.setContentType("text/html;charset=utf-8");
        try{
            InputStream fis = measureDetail.getInputStream();
            String fileName = measureDetail.getOriginalFilename();
            Map map = measureService.addMeasure(measureEntity,measureDetailEntity,fis,userInformationEntity);
            fis.close();
            httpServletResponse.getWriter().print(JSONArray.fromObject(map).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 实测实量详情
     * @param userInformationEntity
     * @param model
     * @param request
     * @param measureId
     * @param projectId
     * @param buildingId
     * @param floorId
     * @return
     */
    @RequestMapping(value = "/measureDetail.html")
    public String measureDetail(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, Model model,
                              HttpServletRequest request,String measureId,String projectId,String buildingId,String floorId,String type) {
        //measureId只是用来查区域，城市的，其他地方不能用
        MeasureDTO measureDTO = measureService.getMeasureDTOById(measureId,projectId,buildingId,floorId);
        model.addAttribute("measureDTO",measureDTO);
        if("1".equals(type)){//详情页
            List<MeasureInspectionPhaseDTO> measureInspectionPhaseDTOs = measureService.getMeasureInspectionPhaseByFloorId(floorId);
            measureDTO.setFloorId(floorId);
            model.addAttribute("measureInspectionPhaseDTOs",measureInspectionPhaseDTOs);
            return "/construction/measure/measureDetail";
        }else if("2".equals(type)){//二维码
            String url = measureService.getQrCodeByBuildingIdAndFloorId(buildingId,floorId);
            model.addAttribute("url",url);
            return "/construction/measure/measureQrCode";
        }
        return "/construction/measure/measureDetail";
    }

    @RequestMapping(value = "/measureModelAndData")
    public ApiResult measureModelAndData(String floorId,String inspectionPhaseId,String unitId) {
        List<MeasureModelDTO> measureModelDTOS = measureService.getMeasureModelByFloorAndInspectionPhaseId(floorId,inspectionPhaseId,unitId);
        return new SuccessApiResult(measureModelDTOS);
    }

    @RequestMapping(value="/getMeasureExcelModel")
    @ResponseBody
    public String getMeasureExcelModel(HttpServletRequest request,HttpServletResponse response){
        try {
            return measureService.getMeasureExcelModel(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误";
        }
    }

    @RequestMapping(value="/downLoadQrCode")
    @ResponseBody
    public void downLoadQrCode(HttpServletRequest request,HttpServletResponse response){
        try {
            String urlString = request.getParameter("url");
            BufferedInputStream dis = null;
            BufferedOutputStream fos = null;
            String fileName = urlString.substring(urlString.lastIndexOf('/') + 1);
            try {

                URL url = new URL(urlString);
                response.setContentType("application/x-msdownload;");
                response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
                response.setHeader("Content-Length", String.valueOf(url.openConnection().getContentLength()));

                dis = new BufferedInputStream(url.openStream());
                fos = new BufferedOutputStream(response.getOutputStream());

                byte[] buff = new byte[2048];
                int bytesRead;
                while (-1 != (bytesRead = dis.read(buff, 0, buff.length))) {
                    fos.write(buff, 0, bytesRead);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (dis != null)
                    dis.close();
                if (fos != null)
                    fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/updateQrCodeState")
    @ResponseBody
    public ApiResult updateQrCodeState(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,HttpServletRequest request,HttpServletResponse response) {
        try {
            String state = request.getParameter("state");
            if (!StringUtil.isEmpty(state)) {
                measureService.updateQrCodeState(userInformationEntity, state);
                return new SuccessApiResult();
            } else {
                return new ErrorApiResult("参数为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorApiResult("系统错误");
        }
    }

    @RequestMapping(value="/getQrCodeState")
    @ResponseBody
    public ApiResult getQrCodeState(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,HttpServletRequest request,HttpServletResponse response) {
        return new SuccessApiResult(measureService.getQrCodeState());
    }

    //根据楼栋id和楼层id获取二维码详情
    @ResponseBody
    @RequestMapping(value = "/getQrCodeByBuildingIdAndFloorId")
    public Map<String,Object> getQrCodeByBuildingIdAndFloorId(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                                  String buildingId,String floorId){
        Map<String,Object> resultMap = new HashMap<>();
        try{
            resultMap.put("data",measureService.getQrCodeByBuildingIdAndFloorId(buildingId,floorId));
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error","-1");
        }
        return resultMap;
    }

    //该楼层该检查分项是否添加过单元
    @RequestMapping(value="/getIsUnit")
    @ResponseBody
    public ApiResult getIsUnit(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,HttpServletRequest request,HttpServletResponse response,
                               String projectId,String buildingId,String floorId,String inspectionPhaseId) {
        if(!StringUtil.isEmpty(projectId) && !StringUtil.isEmpty(buildingId) && !StringUtil.isEmpty(floorId) && !StringUtil.isEmpty(inspectionPhaseId)){
            if(measureService.isUnit(projectId,buildingId,floorId,inspectionPhaseId)){
                return new SuccessApiResult("true");
            }else{
                return new SuccessApiResult("false");
            }
        }else{
            return new ErrorApiResult("服务器异常");
        }
    }

    //根据功能点、用户查询权限范围
    @RequestMapping(value = "getAllAgencyById")
    public void getAllAgencyById(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        List<String> updateProject = projectUserAccreditService.getProjectAuthFunctionAndProjectIdByStaffId("ESH40020105",userInformationEntity.getStaffId(),"4");
        List<AgencyTreeDTO> agencyTreeDTOS = projectUserAccreditService.getProjectAgencyListAllByIds(updateProject);
        response.getWriter().print(JSONArray.fromObject(agencyTreeDTOS).toString());
    }

    //导出EXCEL
    @RequestMapping(value = "/exportExcels", method = RequestMethod.GET)
    public void exportExcels(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                  HttpServletRequest request, HttpServletResponse response,
                                  @Valid MeasureDTO measureDTO) throws Exception {
        measureService.getMeasureDataToExcle(measureDTO,userInformationEntity,request, response);
    }

    //导出EXCEL全数据
    @RequestMapping(value = "/exportAllDataForExcel")
    public void exportAllDataForExcel(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                             HttpServletRequest request, HttpServletResponse response,
                               String agencyId) throws Exception {
        measureService.exportAllDataForExcel(agencyId,userInformationEntity,request, response);
    }

    @RequestMapping(value="/getDataByagencyId")
    @ResponseBody
    public Map<String,Object> getDataByagencyId(@ModelAttribute("authPropertystaff") UserInformationEntity userInformationEntity,
                                       HttpServletRequest request, HttpServletResponse response,
                                       String agencyId) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            if (!StringUtil.isEmpty(agencyId)) {
                if(measureService.getDataByagencyId(agencyId)){
                    resultMap.put("error","1");
                }else {
                    resultMap.put("error","当前项目无数据！");
                }
            }else {
                resultMap.put("error","请选择要导出的项目！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error","系统错误");
        }
        return resultMap;
    }

}
