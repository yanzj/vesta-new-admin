package com.maxrocky.vesta.presentation.admin.controller.announcement;

import com.maxrocky.vesta.application.DTO.BBSReplyAdminDTO;
import com.maxrocky.vesta.application.inf.AnnouncementReplyService;
import com.maxrocky.vesta.application.inf.AnnouncementService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.AnnouncementEntity;
import com.maxrocky.vesta.domain.model.AnnouncementReplyEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 *  公告论坛Controller
 *  @author Wyd_2016.05.31
 */
@Controller
@RequestMapping(value = "/replyDetail")
@SessionAttributes(types = {UserPropertyStaffEntity.class, String.class}, value = {"propertystaff", "menulist", "secanViewlist"})
public class AnnouncementReplyController {

    @Autowired
    private AnnouncementReplyService announcementReplyService;

    @Autowired
    private AnnouncementService announcementService;

    /**
     * 主贴详情
     * 回复贴列表
     *
     * @param bbsReplyAdminDTO
     */
    @RequestMapping(value = "/BBSreplyList.html")
    public String BBSreplys(@ModelAttribute("BBSReply") @Valid BBSReplyAdminDTO bbsReplyAdminDTO, @RequestParam("topicId") String id, WebPage webPage, HttpServletRequest request) {
        //#1。title,content回显
        AnnouncementEntity announcementEntity = this.announcementService.get(AnnouncementEntity.class, id);
        request.setAttribute("announcementEntity", announcementEntity);
        //#2.分頁查詢所有
        List<AnnouncementReplyEntity> announcementReplyEntitys = this.announcementReplyService.queryAllByPage(id, bbsReplyAdminDTO, webPage);
        request.setAttribute("announcementReplyEntitys", announcementReplyEntitys);

        return "/announcement/BBSreplyList";
    }

    /**
     * 回复_提重
     */
    @RequestMapping(value = "/createReply")
    public String createReply(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, @Valid BBSReplyAdminDTO bbsReplyAdminDTO) {
        bbsReplyAdminDTO.setCreateBy(userPropertystaffEntity.getStaffName());
        bbsReplyAdminDTO.setUserId(userPropertystaffEntity.getStaffId());
        //2为管理员回复
        bbsReplyAdminDTO.setType("2");
        this.announcementReplyService.createReply(bbsReplyAdminDTO);
        String redirectAddress = "redirect:/replyDetail/BBSreplyList.html?topicId=";
        redirectAddress = (!StringUtils.isEmpty(bbsReplyAdminDTO.getTopicId())) ? (redirectAddress += bbsReplyAdminDTO.getTopicId()) : (redirectAddress += bbsReplyAdminDTO.getTopicId1());
        return redirectAddress;
    }

    /**
     * 删除回帖(物理删除)_已废
     */
    @RequestMapping(value = "/deleteReply")
    public String deleteReply(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertystaffEntity, @Valid BBSReplyAdminDTO bbsReplyAdminDTO) {
        this.announcementReplyService.deleteReply(bbsReplyAdminDTO);
        String redirectAddress = "redirect:/replyDetail/BBSreplyList.html?topicId=";
        redirectAddress = (!StringUtils.isEmpty(bbsReplyAdminDTO.getTopicId())) ? (redirectAddress += bbsReplyAdminDTO.getTopicId()) : (redirectAddress += bbsReplyAdminDTO.getTopicId1());
        return redirectAddress;
    }

    /**
     * 启用/禁用/删除_回复贴
     * @param userPropertyStaffEntity
     * @param bbsReplyAdminDTO
     * @return
     */
    @RequestMapping(value = "updateReply")
    public String updateReply(@ModelAttribute("propertystaff") UserPropertyStaffEntity userPropertyStaffEntity, @Valid BBSReplyAdminDTO bbsReplyAdminDTO){
        //设置操作人
        bbsReplyAdminDTO.setModifyBy(userPropertyStaffEntity.getStaffId());
        this.announcementReplyService.updateReply(bbsReplyAdminDTO);
        String redirectAddress = "redirect:/replyDetail/BBSreplyList.html?topicId=";
        redirectAddress = (!StringUtils.isEmpty(bbsReplyAdminDTO.getTopicId())) ? (redirectAddress += bbsReplyAdminDTO.getTopicId()) : (redirectAddress += bbsReplyAdminDTO.getTopicId1());
        return redirectAddress;
    }

}


