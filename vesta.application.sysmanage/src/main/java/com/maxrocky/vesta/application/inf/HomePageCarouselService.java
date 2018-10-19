package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.HomePageCarouselDTO;
import com.maxrocky.vesta.domain.model.HomePageCarouselEntity;

import java.util.List;

/**
 * 首页轮播图_Service
 * 2016/6/24_Wyd
 */
public interface HomePageCarouselService {

    /**
     * 保存或更新轮播图信息
     */
    void saveOrUpdateCarousel(HomePageCarouselDTO homePageCarouselDTO);

    /**
     * 通过_projectCode_检索轮播图列表
     * @param projectCode 项目Code
     * @return List<HomePageCarouselEntity>
     */
    List<HomePageCarouselEntity> queryCarouselByProjectCode(String projectCode);

    /**
     * 根据城市查询所有城市下项目
     * @return List
     */
    List<Object[]> listProject(String cityId);

    /**
     * 查询所有城市所有项目
     * @return List
     */
    List<Object[]> listAllProject();

    /**
     * 通过_projectCode_检索项目
     * @return List
     */
    List<Object[]> getProjectByCode(String projectCode);

}
