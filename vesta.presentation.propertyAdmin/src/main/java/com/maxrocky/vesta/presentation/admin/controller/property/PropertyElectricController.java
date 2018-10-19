package com.maxrocky.vesta.presentation.admin.controller.property;

import com.maxrocky.vesta.application.DTO.PropertyElectricDTO;
import com.maxrocky.vesta.application.DTO.admin.BuildingSelDTO;
import com.maxrocky.vesta.application.DTO.admin.FormatSelDTO;
import com.maxrocky.vesta.application.inf.PropertyElectricService;
import com.maxrocky.vesta.application.inf.UserOwnerService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghj on 2016/2/23.
 */
@Controller
@RequestMapping(value = "/property")
@SessionAttributes(types={UserPropertyStaffEntity.class,String.class},value = {"propertystaff","menulist","secanViewlist"})


public class PropertyElectricController {

    @Autowired
    private PropertyElectricService propertyElectricService;
    @Autowired
    private UserOwnerService userOwnerService;


    /**
     * 获取电量列表
     * @param userPropertystaffEntity
     * @param propertyElectricDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/electricManage.html")
    public String electricManage(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid PropertyElectricDTO propertyElectricDTO, Model model,WebPage webPage){
       //获取项目值
        propertyElectricDTO.setProjectId(userPropertystaffEntity.getQueryScope());
        //获取电量列表
       List<PropertyElectricDTO> propertyElectricDTOs = propertyElectricService.listEleDTO(propertyElectricDTO,webPage);
        model.addAttribute("propertyElectricDTOs",propertyElectricDTOs);
        model.addAttribute("propertyElectricDTO",propertyElectricDTO);
        return "/property/ElectricManage";
    }

    /**
     * 删除电量
     * @param userPropertystaffEntity
     * @param propertyElectricDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/delElectric")
    public ModelAndView delElectric(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@Valid PropertyElectricDTO propertyElectricDTO, Model model,WebPage webPage){

        //删除电量
        boolean result = propertyElectricService.delEleDTO(propertyElectricDTO.getElectricId());

        return new ModelAndView("redirect:../property/electricManage.html?pageIndex="+webPage.getPageIndex());
    }

    /**
     * 导出excel模板
     * @param userPropertystaffEntity
     * @param response
     * @return
     */
    @RequestMapping(value = "/exportHouseExcel")
    public String exportHouseExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,HttpServletResponse response,@RequestParam String building,@RequestParam String formatId){
        response.setContentType("application/binary;charset=ISO8859_1");

            try{
                ServletOutputStream outputStream = response.getOutputStream();
                String fileName = new String(("项目房屋列表").getBytes(), "ISO8859_1");
                response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式

                // 第一个XSSFSheet (房屋列表)
                String[] titles = { "项目id","项目","房屋id","业态","楼号","单元","房间号","剩余电量","抄表人"};
                propertyElectricService.exportExcel(titles,outputStream,userPropertystaffEntity.getQueryScope(),building,formatId);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        return null;
    }

    /**
     * 导入excel
     * @return
     */
    @RequestMapping(value = "/importHouseExcel",method = RequestMethod.POST)
    public String importHouseExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity userPropertystaffEntity,@RequestParam MultipartFile[] myfiles,HttpServletRequest request,Model model)throws IOException{

       String result =  propertyElectricService.importExcel(myfiles,request,userPropertystaffEntity.getQueryScope());
        PropertyElectricDTO propertyElectricDTO = new PropertyElectricDTO();
        propertyElectricDTO.setImResult(result);
        model.addAttribute("result", propertyElectricDTO);
        return "/property/ElectricImport";
    }

    @RequestMapping(value = "/gotoImport")
    public String gotoImport(){

        return "/property/ElectricImport";
    }

    /**
     * 初始化下载模板
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/electricUpload")
    public String exportHouseExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,Model model){
        List<BuildingSelDTO> buildingSelDTOs = new ArrayList<>();
        BuildingSelDTO buildingSelDTO = new BuildingSelDTO();
        buildingSelDTO.setBuildingId("0");
        buildingSelDTO.setBuildingName("----------请选择楼号----------");
        buildingSelDTOs.add(buildingSelDTO);
        model.addAttribute("buildingSel",buildingSelDTOs);
        List<FormatSelDTO> formatSelDTOs = userOwnerService.getFormatSel(user.getQueryScope());
        model.addAttribute("formatSelDTOs",formatSelDTOs);
        return "/property/ElectricUpload";
    }

    @RequestMapping(value = "/showBuilding")
    public ApiResult showBuilding(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,Model model,@RequestParam String formatSelId){

        List<BuildingSelDTO> buildingSelDTOs = userOwnerService.getformatBuildSel(user.getQueryScope(),formatSelId);
        return new SuccessApiResult(buildingSelDTOs);
    }
}
