package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.OSSFilesEntity;

import java.util.List;

/**
 * Created by HaiXiang Yu on 2015/9/25.
 */
public interface OSSFilesRepository {

    /**
     * 创建wechat图片
     * @param ossFiles
     */
    void createOSSFiles(OSSFilesEntity ossFiles);

    /**
     * 创建wechat图片列表
     * @param ossFileses
     */
    void createOSSFiles(List<OSSFilesEntity> ossFileses);

    /**
     * 获取没有上传OSS的图片信息
     * @return
     */
    List<OSSFilesEntity> getWaitOSSFiles();

    /**
     * 设置oss图片已上传
     * @param ossId
     */
    void setIsUpload(String ossId);

}
