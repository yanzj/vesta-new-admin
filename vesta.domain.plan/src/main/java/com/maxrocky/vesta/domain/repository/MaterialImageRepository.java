package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.MaterialImageEntity;

import java.util.List;

/**
 * Created by chen on 2016/5/23.
 */
public interface MaterialImageRepository {
    //新增材料验收图片
    void addMaterialImage(MaterialImageEntity materialImageEntity);
    //根据材料ID获取图片列表
    List<MaterialImageEntity> getImageList(String bussinessId);
}
