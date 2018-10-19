package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.IsayImageEntity;

import java.util.List;

/**
 * Created by chen on 2016/1/21.
 */
public interface IsayImageRepository {
    /**新增图片*/
    void AddSayImage(IsayImageEntity isayImageEntity);
    /**获取图片列表*/
    List<IsayImageEntity> getImageList(String bussinessId);
}
