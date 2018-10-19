package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.AnnouncementReplyEntity;
import com.maxrocky.vesta.taglib.page.WebPage;
import java.util.List;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/18
 * Time: 11:43
 * Describe:
 */
public interface AnnouncementReplyRepository extends BaseRepository {

    /**
     * 通过ID检索回复信息
     * @param id
     * @return
     */
    AnnouncementReplyEntity queryReplayById(String id);

    /**
     * 更新回复
     */
    void updateReply(AnnouncementReplyEntity announcementReplyEntity);

    /**
     * 查询数据
     * @param webPage
     * @return
     */
    public List<AnnouncementReplyEntity> queryAllByPage(String topicId,WebPage webPage);

    /**
     * 查詢最大回覆樓層
     * @return
     */
    Integer getMaxFloor(String id);

    /**
     * 删除回帖
     */
    void deleteReply(String id);

    /**
     * 删除回帖与子回帖
     */
    public void deleteReplyAndChildren(String id);

    /**
     * 根据父回复id获取所有子回复id
     */
    public List<String> queryReplyByParentId(String id);

}
