package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserImageEntity;

import java.util.List;

/**
 * Created by Tom on 2016/1/21 17:56.
 * Describe:用户图片Repository接口
 */
public interface UserImageRepository {

    /**
     * Describe:创建用户图片
     * CreateBy:Tom
     * CreateOn:2016-01-21 05:58:44
     */
    void create(List<UserImageEntity> userImageEntityList);


    List<UserImageEntity> getUserImageByBusinessId(String businessId);

}
