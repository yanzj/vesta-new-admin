package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.StoryEntity;
import com.maxrocky.vesta.domain.model.StoryScopeEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * @author WeiYangDong
 * @date 2018/3/22 16:55
 * @deprecated 故事荟功能模块Dao实现类
 */
public interface StoryRespository {

    /**
     * 保存或更新Entity
     * @param entity
     */
    <T> void saveOrUpdate(T entity);

    /**
     * 获取故事荟列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<StoryEntity>
     */
    List<StoryEntity> getStoryInfoList(Map<String,Object> paramsMap, WebPage webPage);

    /**
     * 删除故事荟发布范围
     * @param storyId 故事ID
     */
    void delStoryInfoScopeById(String storyId);

    /**
     * 通过故事ID获取故事荟发布范围
     * @param storyId 故事ID
     * @return List<StoryScopeEntity>
     */
    List<StoryScopeEntity> getStoryInfoScopeById(String storyId);
}
