package com.maxrocky.vesta.domain.readilyTake.repository;

import com.maxrocky.vesta.domain.readilyTake.model.ReadilyRecordEntity;
import com.maxrocky.vesta.domain.readilyTake.model.ReadilyTakeEntity;
import com.maxrocky.vesta.domain.readilyTake.model.SecurityImageEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Magic on 2017/6/9.
 */
public interface ReadilyTakeRepository {
    /**
     * 查询随手拍数据List
     */
    List<Object[]> getReadilyTakeList(Map map, WebPage webPage);

    /**
     * 查询随手拍详情
     */
    Object[] getReadilyTake(String patId);

    /**
     * 根据随手拍id查询随手拍图片信息
     */
    List<Object[]> getSecurityImageList(List<String> idList);

    /**
     * 根据随手拍id查询随手拍整改信息
     */
    List<Object[]> getRectificationDescriptionList(String patId);

    /**
     * 根据当前登录人和项目公司id查询在当前公司下的权限
     */
    List<String> queryRoleById(String userId, String projectId);

    /**
     * 根据id 获取随手拍详情
     */
    ReadilyTakeEntity getReadilyTakeById(String patId);

    /**
     * 修改整改单
     */
    void updateReadilyTake(ReadilyTakeEntity readilyTakeEntity);

    /**
     * 保存整改描述
     */
    void saveReadilyTakeRectify(ReadilyRecordEntity readilyRecordEntity);

    /**
     * 保存图片
     */
    void saveImage(SecurityImageEntity securityImageEntity);
}
