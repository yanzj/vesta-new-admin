package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.AnnouncementScopeService;
import com.maxrocky.vesta.application.service.BaseServiceImpl;
import com.maxrocky.vesta.domain.model.AnnouncementScopeEntity;
import com.maxrocky.vesta.domain.repository.AnnouncementScopeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created With IntelliJ IDEA.
 * User: yifan
 * Date: 2016/5/18
 * Time: 11:10
 * Describe:
 */
@Service
public class AnnouncementScopeServiceImpl extends BaseServiceImpl<AnnouncementScopeEntity> implements AnnouncementScopeService {

    @Autowired
    AnnouncementScopeRepository announcementScopeRepository;


    /**
     * 根据通告id查询通告所有范围信息
     *
     * @param id
     * @return
     */
    @Override
    public List<Object[]> queryByAnnouncementId(String id) {
        return this.announcementScopeRepository.queryByAnnouncementId(id);
    }
/**===============================================================================**/
    /**
     * 根据新闻id查询新闻所有范围信息
     * @param id
     * @return
     */
    @Override
    public List<Object[]> queryByCommunitNewsId(String id) {
        return announcementScopeRepository.queryByCommunitNewsId(id);
    }


}
