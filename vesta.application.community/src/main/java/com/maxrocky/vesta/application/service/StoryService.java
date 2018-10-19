package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.StoryDTO;
import com.maxrocky.vesta.domain.model.StoryEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * @author WeiYangDong
 * @date 2018/3/22 16:53
 * @deprecated 故事荟Service
 */
public interface StoryService {

    /**
     * 保存或更新故事荟
     */
    void saveOrUpdateStoryInfo(StoryDTO storyDTO);

    /**
     * 获取故事荟列表
     */
    List<StoryDTO> getStoryInfoList(StoryDTO storyDTO, WebPage webPage);

    /**
     * 获取故事详情
     */
    StoryEntity getStoryInfoById(String storyId);
    StoryDTO getStoryInfoDTOById(String storyId);

    /**
     * 删除故事荟(逻辑删除)
     */
    void deleteStoryInfo(String storyId);

}
