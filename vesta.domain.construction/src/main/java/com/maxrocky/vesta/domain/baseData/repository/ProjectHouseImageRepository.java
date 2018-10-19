package com.maxrocky.vesta.domain.baseData.repository;

import com.maxrocky.vesta.domain.baseData.model.ProjectHouseImageEntity;

/**
 * Created by 27978 on 2016/11/1.
 */
public interface ProjectHouseImageRepository {

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 新增户型图
    */
    void saveOrUpdateHouseImg(ProjectHouseImageEntity projectHouseImageEntity);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 根据楼层id获取对应的户型图
    */
    ProjectHouseImageEntity getImageByFloorId(String floorId);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Param:
    *  @Description: 获取最新的户型图
    */
    ProjectHouseImageEntity getHouseImg();
}
