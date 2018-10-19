package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.BBSReplyAdminDTO;
import com.maxrocky.vesta.application.inf.AnnouncementReplyService;
import com.maxrocky.vesta.application.service.BaseServiceImpl;
import com.maxrocky.vesta.domain.model.AnnouncementEntity;
import com.maxrocky.vesta.domain.model.AnnouncementReplyEntity;
import com.maxrocky.vesta.domain.repository.AnnouncementReplyRepository;
import com.maxrocky.vesta.domain.repository.AnnouncementRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/18
 * Time: 11:10
 * Describe:
 */
@Service
public class AnnouncementReplyServiceImpl extends BaseServiceImpl<AnnouncementReplyEntity> implements AnnouncementReplyService {

    @Autowired
    AnnouncementReplyRepository announcementReplyRepository;

    @Autowired
    AnnouncementRepository announcementRepository;

    @Override
    public List<AnnouncementReplyEntity> queryAllByPage(String topicId,BBSReplyAdminDTO bbsReplyAdminDTO, WebPage webPage) {
        List<AnnouncementReplyEntity> list = this.announcementReplyRepository.queryAllByPage(topicId,webPage);
        return list;
    }

    @Override
    public void createReply(BBSReplyAdminDTO bbsReplyAdminDTO) {
        //防止_topicId_空指针异常
        String topicId = bbsReplyAdminDTO.getTopicId();
        if (topicId == null || topicId.equals("")){
            topicId = bbsReplyAdminDTO.getTopicId1();
        }
        if (!StringUtils.isEmpty(bbsReplyAdminDTO.getTopicId())){
            //回复主贴(直接帖)
            AnnouncementReplyEntity announcementReplyEntity = new AnnouncementReplyEntity();
            announcementReplyEntity.setId(IdGen.uuid());
            announcementReplyEntity.setCreateOn(new Date());
            announcementReplyEntity.setCreateBy(bbsReplyAdminDTO.getUserId());
            announcementReplyEntity.setModifyBy(bbsReplyAdminDTO.getUserId());
            announcementReplyEntity.setModifyOn(new Date());
            announcementReplyEntity.setUserId(bbsReplyAdminDTO.getUserId());
            announcementReplyEntity.setUserNickName(bbsReplyAdminDTO.getCreateBy());
            //回复楼层
            Integer maxFloor = this.announcementReplyRepository.getMaxFloor(topicId);
            announcementReplyEntity.setFloor(maxFloor + 1);
            announcementReplyEntity.setType(bbsReplyAdminDTO.getType());
            announcementReplyEntity.setStatus("1");

            announcementReplyEntity.setReplyId(bbsReplyAdminDTO.getReplyId());
            announcementReplyEntity.setReplyUserId(bbsReplyAdminDTO.getReplyUserId());
            announcementReplyEntity.setContent(bbsReplyAdminDTO.getContent().replace("\r\n", "<br/>"));
            announcementReplyEntity.setTopicId(bbsReplyAdminDTO.getTopicId());
            this.announcementReplyRepository.saveOrUpdate(announcementReplyEntity);

            //修改主贴(公告)回复数
            AnnouncementEntity announcementEntity = announcementRepository.queryAnnouncementByID(topicId);
            synchronized (announcementEntity){
                announcementEntity.setReplyNum(announcementEntity.getReplyNum() + 1);
                announcementRepository.updateAnnouncement(announcementEntity);
            }
        }else{
            //管理员回复主贴评论(楼中楼)
            String replyId1 = bbsReplyAdminDTO.getReplyId1();
            //通过Id检索回复信息
            AnnouncementReplyEntity announcementReplyEntity = this.announcementReplyRepository.queryReplayById(replyId1);
            //设置状态为管理员回复
            announcementReplyEntity.setType("2");
            announcementReplyEntity.setReplyId(bbsReplyAdminDTO.getReplyId1());
            announcementReplyEntity.setReplyUserId(bbsReplyAdminDTO.getReplyUserId1());
            announcementReplyEntity.setReplyContent(bbsReplyAdminDTO.getContent1());
            announcementReplyEntity.setTopicId(bbsReplyAdminDTO.getTopicId1());

            this.announcementReplyRepository.saveOrUpdate(announcementReplyEntity);
        }
//        AnnouncementReplyEntity announcementReplyEntity = new AnnouncementReplyEntity();
//        announcementReplyEntity.setId(IdGen.uuid());
//        announcementReplyEntity.setCreateOn(new Date());
//        announcementReplyEntity.setCreateBy(bbsReplyAdminDTO.getUserId());
//        announcementReplyEntity.setModifyBy(bbsReplyAdminDTO.getUserId());
//        announcementReplyEntity.setModifyOn(new Date());
//        announcementReplyEntity.setUserId(bbsReplyAdminDTO.getUserId());
//        announcementReplyEntity.setUserNickName(bbsReplyAdminDTO.getCreateBy());
//        //回复楼层
//        Integer maxFloor = this.announcementReplyRepository.getMaxFloor(topicId);
//        announcementReplyEntity.setFloor(maxFloor + 1);
//        announcementReplyEntity.setType(bbsReplyAdminDTO.getType());
//        announcementReplyEntity.setStatus("1");

        //设置楼中楼与直接帖的回复信息分离
//        if (!StringUtils.isEmpty(bbsReplyAdminDTO.getTopicId())) {
//            //直接回帖
//            announcementReplyEntity.setReplyId(bbsReplyAdminDTO.getReplyId());
//            announcementReplyEntity.setReplyUserId(bbsReplyAdminDTO.getReplyUserId());
//            announcementReplyEntity.setContent(bbsReplyAdminDTO.getContent().replace("\r\n", "<br/>"));
//            announcementReplyEntity.setTopicId(bbsReplyAdminDTO.getTopicId());
//        } else {
//            //楼中楼
//            announcementReplyEntity.setReplyId(bbsReplyAdminDTO.getReplyId1());
//            announcementReplyEntity.setReplyUserId(bbsReplyAdminDTO.getReplyUserId1());
//            announcementReplyEntity.setReplyContent(bbsReplyAdminDTO.getContent1());
////            announcementReplyEntity.setContent("回复 "+bbsReplyAdminDTO.getReplyUserId1()+" : "+bbsReplyAdminDTO.getContent1().replace("\r\n", "<br/>"));
//            announcementReplyEntity.setTopicId(bbsReplyAdminDTO.getTopicId1());
//        }
//        this.announcementReplyRepository.saveOrUpdate(announcementReplyEntity);


    }


    /**
     * 删除回帖
     *
     * @param bbsReplyAdminDTO
     */
    @Override
    public void deleteReply(BBSReplyAdminDTO bbsReplyAdminDTO) {
        this.announcementReplyRepository.deleteReply(bbsReplyAdminDTO.getId());
    }

    /**
     * 启用/禁用/删除_回复贴
     * @param bbsReplyAdminDTO
     */
    public void updateReply(BBSReplyAdminDTO bbsReplyAdminDTO){
        AnnouncementReplyEntity announcementReplyEntity = this.announcementReplyRepository.queryReplayById(bbsReplyAdminDTO.getId());
        if (bbsReplyAdminDTO.getStatus().equals("1")){

            if (announcementReplyEntity.getStatus().equals("1")){
                //启用则禁用
                announcementReplyEntity.setStatus("2");
            }else{
                //禁用则启用
                announcementReplyEntity.setStatus("1");
            }
        }
        if (bbsReplyAdminDTO.getStatus().equals("2")){
            //执行逻辑删除
            announcementReplyEntity.setStatus("3");
            //修改主贴(公告)回复数
            AnnouncementEntity announcementEntity = announcementRepository.queryAnnouncementByID(bbsReplyAdminDTO.getTopicId());
            synchronized (announcementEntity){
                announcementEntity.setReplyNum(announcementEntity.getReplyNum() - 1);
                announcementRepository.updateAnnouncement(announcementEntity);
            }
        }
        announcementReplyEntity.setModifyBy(bbsReplyAdminDTO.getModifyBy());
        announcementReplyEntity.setModifyOn(new Date());
        this.announcementReplyRepository.updateReply(announcementReplyEntity);
    }



}
