package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.HomeLetterDTO;
import com.maxrocky.vesta.domain.model.EngineeringProgressEntity;
import com.maxrocky.vesta.domain.model.EngineeringProjectEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * 家书管理-Service
 * Created by WeiYangDong on 2017/5/17.
 */
public interface HomeLetterService {

    /**
     * 获取工程进展列表
     */
    List<Map<String,Object>> getEngineeringProgressList(HomeLetterDTO homeLetterDTO, WebPage webPage);

    /**
     * 获取工程进展详情
     */
    EngineeringProgressEntity getEngineeringProgressById(String engineeringProgressId);

    /**
     * 保存或更新工程进展
     */
    void saveOrUpdateEngineeringProgress(HomeLetterDTO homeLetterDTO);

    /**
     * 删除工程进展
     */
    void deleteEngineeringProgress(HomeLetterDTO homeLetterDTO);

    /**
     * 获取工程项目列表
     */
    List<EngineeringProjectEntity> getEngineeringProjectList(HomeLetterDTO homeLetterDTO);

    /**
     * 保存或更新工程项目
     */
    void saveOrUpdateEngineeringProject(HomeLetterDTO homeLetterDTO);
}
