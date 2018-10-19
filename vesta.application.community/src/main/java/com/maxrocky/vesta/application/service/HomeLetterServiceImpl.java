package com.maxrocky.vesta.application.service;

import com.maxrocky.vesta.application.admin.dto.HomeLetterDTO;
import com.maxrocky.vesta.domain.model.EngineeringProgressEntity;
import com.maxrocky.vesta.domain.model.EngineeringProjectEntity;
import com.maxrocky.vesta.domain.repository.HomeLetterRespository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 家书管理-Service实现类
 * Created by WeiYangDong on 2017/5/17.
 */
@Service
public class HomeLetterServiceImpl implements HomeLetterService{

    @Autowired
    private HomeLetterRespository homeLetterRespository;

    /**
     * 获取工程进展列表
     */
    public List<Map<String,Object>> getEngineeringProgressList(HomeLetterDTO homeLetterDTO, WebPage webPage){
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("cityId",homeLetterDTO.getCityId());
        paramsMap.put("projectCode",homeLetterDTO.getProjectCode());
        paramsMap.put("engineeringProgressTitle",homeLetterDTO.getEngineeringProgressTitle());
        return homeLetterRespository.getEngineeringProgressList(paramsMap,webPage);
    }

    /**
     * 获取工程进展详情
     */
    public EngineeringProgressEntity getEngineeringProgressById(String engineeringProgressId){
        return homeLetterRespository.getEngineeringProgressById(engineeringProgressId);
    }

    /**
     * 保存或更新工程进展
     */
    public void saveOrUpdateEngineeringProgress(HomeLetterDTO homeLetterDTO){
        EngineeringProgressEntity engineeringProgressEntity = null;
        if (null != homeLetterDTO.getEngineeringProgressId() && !"".equals(homeLetterDTO.getEngineeringProgressId())){
            engineeringProgressEntity = getEngineeringProgressById(homeLetterDTO.getEngineeringProgressId());
            engineeringProgressEntity.setModifyBy(homeLetterDTO.getModifyBy());
            engineeringProgressEntity.setModifyOn(new Date());
        }else{
            engineeringProgressEntity = new EngineeringProgressEntity();
            engineeringProgressEntity.setEngineeringProgressId(IdGen.uuid());
            engineeringProgressEntity.setCreateBy(homeLetterDTO.getModifyBy());
            engineeringProgressEntity.setCreateOn(new Date());
        }
        engineeringProgressEntity.setEngineeringProgressTitle(homeLetterDTO.getEngineeringProgressTitle());
        engineeringProgressEntity.setEngineeringProgressSignImgUrl(homeLetterDTO.getEngineeringProgressSignImgUrl());
        engineeringProgressEntity.setEngineeringProgressContent(homeLetterDTO.getEngineeringProgressContent());
        engineeringProgressEntity.setCityId(homeLetterDTO.getCityId());
        engineeringProgressEntity.setCityName(homeLetterDTO.getCityName());
        engineeringProgressEntity.setProjectCode(homeLetterDTO.getProjectCode());
        engineeringProgressEntity.setProjectName(homeLetterDTO.getProjectName());
        engineeringProgressEntity.setIsLink(homeLetterDTO.getIsLink());//是否外链
        if (homeLetterDTO.getIsLink() == 1){
            engineeringProgressEntity.setLinkSrc(homeLetterDTO.getLinkSrc());//外链地址
        }
        homeLetterRespository.saveOrUpdate(engineeringProgressEntity);
    }

    /**
     * 删除工程进展
     */
    public void deleteEngineeringProgress(HomeLetterDTO homeLetterDTO){
        EngineeringProgressEntity engineeringProgressEntity = homeLetterRespository.getEngineeringProgressById(homeLetterDTO.getEngineeringProgressId());
        if (null != engineeringProgressEntity){
            homeLetterRespository.delete(engineeringProgressEntity);
        }
    }

    /**
     * 获取工程项目列表
     */
    public List<EngineeringProjectEntity> getEngineeringProjectList(HomeLetterDTO homeLetterDTO){
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("engineeringProjectId",homeLetterDTO.getEngineeringProjectId());
        paramsMap.put("cityId",homeLetterDTO.getCityId());
        return homeLetterRespository.getEngineeringProjectList(paramsMap,null);
    }

    /**
     * 保存或更新工程项目
     */
    public void saveOrUpdateEngineeringProject(HomeLetterDTO homeLetterDTO){
        EngineeringProjectEntity engineeringProjectEntity = null;

        engineeringProjectEntity = new EngineeringProjectEntity();
        engineeringProjectEntity.setEngineeringProjectId(IdGen.uuid());
        engineeringProjectEntity.setCityId(homeLetterDTO.getCityId());
        engineeringProjectEntity.setCityName(homeLetterDTO.getCityName());
        engineeringProjectEntity.setProjectName(homeLetterDTO.getProjectName());
        engineeringProjectEntity.setCreateBy(homeLetterDTO.getModifyBy());
        engineeringProjectEntity.setCreateOn(new Date());
        homeLetterRespository.saveOrUpdate(engineeringProjectEntity);
    }

}
