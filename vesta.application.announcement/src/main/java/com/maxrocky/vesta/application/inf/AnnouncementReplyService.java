package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.BBSReplyAdminDTO;
import com.maxrocky.vesta.application.service.BaseService;
import com.maxrocky.vesta.domain.model.AnnouncementReplyEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/24
 * Time: 11:10
 * Describe:
 */
public interface AnnouncementReplyService extends BaseService<AnnouncementReplyEntity> {


    /**
     * 所有評論
     * @param bbsReplyAdminDTO
     * @param webPage
     * @return
     */
    public List<AnnouncementReplyEntity> queryAllByPage(String topicId,BBSReplyAdminDTO bbsReplyAdminDTO, WebPage webPage);

    /**
     * 楼中楼
     * @param bbsReplyAdminDTO
     */
    public void createReply(BBSReplyAdminDTO bbsReplyAdminDTO);

    /**
     * 删除回帖
     * @param bbsReplyAdminDTO
     */
    public void deleteReply(BBSReplyAdminDTO bbsReplyAdminDTO);

    /**
     * 启用/禁用/删除_回复贴
     * @param bbsReplyAdminDTO
     */
    void updateReply(BBSReplyAdminDTO bbsReplyAdminDTO);

}
