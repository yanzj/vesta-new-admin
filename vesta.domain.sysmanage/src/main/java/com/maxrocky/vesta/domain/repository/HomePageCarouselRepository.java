package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.HomePageCarouselEntity;

import java.util.List;

/**
 * 首页轮播图_持久层
 * 2016/6/24_Wyd
 */
public interface HomePageCarouselRepository {

    void saveCarousel(HomePageCarouselEntity homePageCarouselEntity);

    void updateCarousel(HomePageCarouselEntity homePageCarouselEntity);

    /**
     * 通过_projectCode_删除轮播图信息
     * @param projectCode 项目Code
     */
    void deleteCarouselByProjectCode(String projectCode);

    /**
     * 通过_projectCode_检索轮播图列表
     * @param projectCode 项目Code
     * @return List<HomePageCarouselEntity>
     */
    List<HomePageCarouselEntity> queryCarouselByProjectCode(String projectCode);

}
