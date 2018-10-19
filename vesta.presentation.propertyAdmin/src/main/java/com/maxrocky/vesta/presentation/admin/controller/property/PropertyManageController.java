package com.maxrocky.vesta.presentation.admin.controller.property;

import com.maxrocky.vesta.application.DTO.PropertyManageDTO;
import com.maxrocky.vesta.application.inf.HouseBuildingService;
import com.maxrocky.vesta.application.inf.HouseUnitService;
import com.maxrocky.vesta.application.inf.PropertyManageService;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产权管理
 * Created by WeiYangDong on 2017/6/21.
 */
@Controller
@RequestMapping(value = "/propertyManage")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})
public class PropertyManageController {

    @Autowired
    StaffUserService staffUserService;
    @Autowired
    HouseBuildingService houseBuildingService;
    @Autowired
    HouseUnitService houseUnitService;
    @Autowired
    PropertyManageService propertyManageService;

    /**
     * 产权导入模板下载
     */
    @ResponseBody
    @RequestMapping(value="/downLoadExcelTemplate")
    public String downLoadExcelTemplate(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        try {
            propertyManageService.downLoadExcelTemplate(httpServletRequest, httpServletResponse);
            return "模板下载完成！";
        }catch (Exception e){
            e.printStackTrace();
            return "模板下载错误！";
        }
    }

    /**
     * 产权导入模板及导入数据下载
     */
    @ResponseBody
    @RequestMapping(value="/downLoadExcelTemplateAndData")
    public String downLoadExcelTemplateAndData(HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest,PropertyManageDTO propertyManageDTO){
        try {
            propertyManageService.downLoadExcelTemplateAndData(httpServletResponse,httpServletRequest,propertyManageDTO);
            return "0";
        }catch (Exception e){
            e.printStackTrace();
            return "-1";
        }
    }

    /**
     * 获取产权管理列表
     */
    @RequestMapping(value = "/getPropertyManageList.html")
    public String getPropertyManageList(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,
                                        PropertyManageDTO propertyManageDTO,
                                        WebPage webPage,
                                        Model model
                                        ){
        try{
            //检索条件回显
            List<Map<String, Object>> cityList = staffUserService.getCityListByStaff(userPropertystaffEntity.getStaffId(),propertyManageDTO.getMenuId());
            model.addAttribute("city", cityList);
            if (null != propertyManageDTO.getScopeId() && !"".equals(propertyManageDTO.getScopeId())){
                //城市不为空,回显项目列表
                List<Object[]> projectList = staffUserService.listProjectByCity(userPropertystaffEntity.getStaffId(),propertyManageDTO.getScopeId(),propertyManageDTO.getMenuId());
                model.addAttribute("projectList",projectList);
                if (null != propertyManageDTO.getProjectCode() && !"".equals(propertyManageDTO.getProjectCode())){
                    //项目不为空,回显地块列表
                    Map areaMap =houseBuildingService.getAreaListByProjectNum(propertyManageDTO.getProjectCode());
                    model.addAttribute("areaMap",areaMap);
                    if (null != propertyManageDTO.getBlockCode() && !"".equals(propertyManageDTO.getBlockCode())){
                        //地块不为空,回显楼栋列表
                        Map buildMap =houseBuildingService.getBuildListByBlockNum(propertyManageDTO.getBlockCode());
                        model.addAttribute("buildMap",buildMap);
                        if (null != propertyManageDTO.getBuildingNum() && !"".equals(propertyManageDTO.getBuildingNum())){
                            //楼栋不为空,回显单元列表
                            Map unitMap = houseUnitService.getUnitMapByBuildingId(propertyManageDTO.getBuildingNum());
                            model.addAttribute("unitMap",unitMap);
                        }
                    }
                }
            }
            model.addAttribute("propertyManageDTO",propertyManageDTO);
            //获取数据范围权限
            List<Map<String, Object>> roleScopeList = staffUserService.getRoleScopeByStaffId(userPropertystaffEntity.getStaffId());
            propertyManageDTO.setRoleScopeList(roleScopeList);
            //执行查询
            List<PropertyManageDTO> propertyList = propertyManageService.getPropertyManageList(propertyManageDTO, webPage);
            model.addAttribute("propertyList",propertyList);
            model.addAttribute("isExcel",webPage.getRecordCount());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/property/PropertyManageList";
    }

    /**
     * 批量导入 WeiYangDong_2017-06-28
     */
    @ResponseBody
    @RequestMapping(value = "/uploadPropertyExcel")
    public Map<String,Object> uploadPropertyExcel(@ModelAttribute("propertystaff") UserPropertyStaffEntity user,
                                                  HttpServletRequest httpServletRequest) {
        Map<String,Object> resultMap = new HashMap<>();
        try {
            MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpServletRequest;
            MultipartFile file = request.getFile("excelFile");
            InputStream fis = file.getInputStream();
            //POI:得到解析Excel的实体集合
            Map<String, Object> flagMap = propertyManageService.importEmployeeByPoi(user, fis);
            if (flagMap.containsKey("successNum")){
                resultMap.put("error", "0");
                resultMap.put("flag",flagMap);
            }else{
                resultMap.put("error", "-1");
            }
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("error", "-1");
        }
        return resultMap;
    }

}
