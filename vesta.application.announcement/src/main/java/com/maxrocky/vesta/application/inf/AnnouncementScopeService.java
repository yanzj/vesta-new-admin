package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.service.BaseService;
import com.maxrocky.vesta.domain.model.AnnouncementScopeEntity;

import java.util.List;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/18
 * Time: 11:10
 * Describe:
 */
public interface AnnouncementScopeService extends BaseService<AnnouncementScopeEntity> {

    /**
     * 根据通告id查询通告所有范围信息
     * @param id
     * @return
     */
    public List<Object[]> queryByAnnouncementId(String id);
/**===============================================================================**/
    /**
     * 根据新闻id查询新闻所有范围信息
     * @param id
     * @return
     */
    public List<Object[]> queryByCommunitNewsId(String id);


}
