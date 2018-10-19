package com.maxrocky.vesta.domain.baseData.repository;

import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;

/**
 * Created by jiazefeng on 2016/10/20.
 */
public interface ProjectImagesRepository {
    /**
     * 新增工程图片信息
     *
     * @param projectImagesEntity
     * @return
     */
    boolean addProjectImagesInfo(ProjectImagesEntity projectImagesEntity);
}
