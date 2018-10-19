package com.maxrocky.vesta.presentation.admin.controller.announcement;

import com.maxrocky.vesta.application.DTO.VoteDTO;
import com.maxrocky.vesta.application.admin.dto.TransfersDto;
import com.maxrocky.vesta.application.inf.VoteService;
import com.maxrocky.vesta.application.service.impl.ImgServiceImpl;
import com.maxrocky.vesta.application.service.inf.ImgService;
import com.maxrocky.vesta.axis.DevelopReceiveServerStub;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.config.ImgType;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.model.VoteEntity;
import com.maxrocky.vesta.setting.ImageConfig;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** * 投票管理
 * @author  Wyd_2016/5/26
 */
@Controller
@RequestMapping(value = "/vote")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class AnnouncementVoteController {

    @Autowired
    VoteService voteService;

    /**
     * 投票管理列表
     * @param voteDTO
     * @param model
     * @param webPage
     * @return
     */
    @RequestMapping(value = "/voteList.html")
    public String voteList(VoteDTO voteDTO, Model model, WebPage webPage){
        //数据回显
        model.addAttribute("voteDTO",voteDTO);
        try {
            //设置投票状态
            List<TransfersDto> statusMap = new ArrayList<>();
            statusMap.add(new TransfersDto(0,"末开始"));
            statusMap.add(new TransfersDto(1,"进行中"));
            statusMap.add(new TransfersDto(2,"已结束"));
            model.addAttribute("statusMap",statusMap);
            //执行检索
            List<VoteDTO> voteDTOs = this.voteService.queryVoteListByPage(voteDTO, webPage);
            model.addAttribute("voteDTOs",voteDTOs);
            webPage.setPageSize(10);
            webPage.setPageIndex(webPage.getPageIndex());
            webPage.setRecordCount(voteDTOs.size());
            //记录集合长度，如果没有查询出数据则不可导出
            model.addAttribute("isExcel", voteDTOs.size());
        }catch (Exception e){
            e.printStackTrace();
            model.addAttribute("error", "查询出现异常请联系管理员");
            return "/announcement/VoteList";
        }
        return "/announcement/VoteList";
    }

    /**
     * 去到新增/更新页面
     * @return
     */
    @RequestMapping(value = "/toVoteEdit.html")
    public String toVoteEdit(){
        //如要执行更新,在此处查询数据回显
        return "/announcement/VoteEdit";
    }

    /**
     * 新增/更新投票
     * @param userPropertystaffEntity
     * @param voteDTO
     * @return
     */
    @RequestMapping(value = "/addOrUpdateVote.html")
    public ModelAndView addOrUpdateVote(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, @Valid VoteDTO voteDTO){
        try {
            //设置当前操作人
            voteDTO.setOperatePerson(userPropertystaffEntity.getStaffName());
            //处理_截止时间,于2016-12-21修正,去除小时与分钟参数,endDateStr可接收到分钟
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//            endDateStr += " " + voteDTO.getHour() + ":" + voteDTO.getMinute();
            try {
                voteDTO.setEndDate(dateFormat.parse(voteDTO.getEndDateStr()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //处理_多图片上传Url
            MultipartFile[] multipartFiles = voteDTO.getMultipartFiles();
            ArrayList<String> imgUrls = new ArrayList<String>();
            ImgService imgService = new ImgServiceImpl();
            for (int i = 0; i < multipartFiles.length; i++){
                if (multipartFiles[i].isEmpty()){
                    imgUrls.add("");
                }else{
                    //图片地址特殊处理
                    String urlTitle =ImageConfig.PIC_OSS_ADMIN_URL;
                    String imgUrl = imgService.uploadAdminImage(multipartFiles[i], ImgType.VOTEIMG);
                    imgUrl = urlTitle + imgUrl.replace(ImageConfig.PIC_SERVER_ADMIN_URL, "");
                    imgUrls.add(imgUrl);
                }
            }
            voteDTO.setImgUrls(imgUrls);
            this.voteService.addOrUpdateVote(userPropertystaffEntity,voteDTO);
        }catch (Exception e){
            e.printStackTrace();
//            return new ModelAndView("redirect:../error/500.html");
        }
        return new ModelAndView("redirect:/vote/voteList.html");
    }

    /**
     * 通过Id_删除投票
     * @param voteDTO
     * @return
     */
    @RequestMapping(value = "/delVote", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public ApiResult deleteVote(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, @Valid VoteDTO voteDTO){
        try {
            this.voteService.deleteVoteById(userPropertystaffEntity,userPropertystaffEntity.getStaffId(),voteDTO.getId());
            return new SuccessApiResult();
        }catch (Exception e){
            e.printStackTrace();
            return new ErrorApiResult("error_00000035");
        }
    }

    /**
     * 通过Id_获取投票统计详情
     * @param model
     * @param voteDTO
     * @return
     */
    @RequestMapping(value = "/getVoteDetail.html")
    public ModelAndView getVoteDetail(Model model, @Valid VoteDTO voteDTO){
        try {
            //_#1,投票模板信息(投票名称,截止时间)
            VoteEntity voteEntity = this.voteService.queryVoteById(voteDTO.getId());
            model.addAttribute("voteDetail",voteEntity);
            //_#2,投票人数(通过_voteId_查询投票人数)
            Integer count = this.voteService.quereyVotePersonCount("", voteDTO.getId());
            model.addAttribute("count",count);
            //_#3,投票项列表(图片Img,投票项文本,票数,百分比)
            List<Map<String, Object>> voteOptions = this.voteService.queryVoteOptionStatistic(voteDTO.getId());

            if (voteOptions.isEmpty() || null == voteOptions){
                //该投票还未被绑定公告
               voteOptions = this.voteService.queryVoteOptionByVoteId(voteDTO.getId());
               for (int i = 0; i < voteOptions.size(); i++){
                   Map<String, Object> map = voteOptions.get(i);
                   map.put("voteNumber",0);
                   map.put("voteNumPer",0);
               }
            }else{
                //处理投票数百分比
                NumberFormat formatter = new DecimalFormat("0.00");
                for (int i = 0; i < voteOptions.size(); i++){
                    Map<String, Object> map = voteOptions.get(i);
                    map.get("voteNumber");
                    int voteNumber = new BigDecimal(map.get("voteNumber").toString()).intValue();
                    Double voteNumPer=new Double((double)voteNumber/(double)count * 100);
                    map.put("voteNumPer", formatter.format(voteNumPer));
                }
            }

            model.addAttribute("voteOptions", voteOptions);
            //_#4,投票统计
            List<Map<String, Object>> list = this.voteService.queryVoteProjectStatistic(voteDTO.getId());
            model.addAttribute("voteProjects",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ModelAndView("/announcement/VoteDetail");
    }

    /***
     * 导出excel操作
     * param:user
     * param:httpServletResponse
     * return
     */
    @RequestMapping("/exportExcel")
    @ResponseBody
    public String exportExcel(@ModelAttribute("propertystaff")UserPropertyStaffEntity user,@Valid VoteDTO voteDTO,
                              HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){
        try {
            return voteService.exportExcel(user,voteDTO,httpServletResponse, httpServletRequest);
        }catch (Exception e){
            e.printStackTrace();
            return "系统错误";
        }

    }
}
