package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.VideoHQDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 视频功能模块Service
 * Created by WeiYangDong on 2017/9/27.
 */
public interface VideoService {

    /**
     * 通过主键ID获取总部视频详情
     * @param id 主键ID
     * @return VideoHQDTO
     */
    VideoHQDTO getVideoHQById(String id) throws InvocationTargetException, IllegalAccessException;

    /**
     * 获取总部视频列表
     */
    List<VideoHQDTO> getVideoHQList(VideoHQDTO videoHQDTO, WebPage webPage) throws InvocationTargetException, IllegalAccessException;

    /**
     * 保存或更新总部视频信息
     */
    void saveOrUpdateVideoHQInfo(VideoHQDTO videoHQDTO);

    /**
     * 物理删除总部视频数据
     */
    void deleteVideoHQInfo(String id);
}
