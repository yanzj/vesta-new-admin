package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.VideoHQEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 视频功能模块数据持久层
 * Created by WeiYangDong on 2017/9/27.
 */
public interface VideoRepository {

    /**
     * 保存或更新Entity
     * @param entity
     */
    <T> void saveOrUpdate(T entity);

    /**
     * 删除Entity
     * @param entity
     */
    <E> void delete(E entity);

    /**
     * 通过主键ID获取总部视频详情
     * @param id 主键ID
     * @return VideoHQEntity
     */
    VideoHQEntity getVideoHQById(String id);

    /**
     * 获取总部视频列表
     * @param paramsMap 参数
     * @param webPage 分页
     * @return List<VideoHQEntity>
     */
    List<VideoHQEntity> getVideoHQList(Map<String,Object> paramsMap, WebPage webPage);
}
