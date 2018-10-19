package com.maxrocky.vesta.application.impl;


import com.maxrocky.vesta.application.DTO.OperationLogDTO;
import com.maxrocky.vesta.application.DTO.PropertyTypesDTO;
import com.maxrocky.vesta.application.inf.OperationLogService;
import com.maxrocky.vesta.application.inf.PropertyTypesService;
import com.maxrocky.vesta.domain.model.PropertyTypeEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.PropertyTypesRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunmei on 2016/2/15.
 */
@Service
public class PropertyTypesServiceImpl implements PropertyTypesService{

    /**
     * 物业服务 持久层接口
     */
    @Autowired
    private PropertyTypesRepository propertyTypesRepository;
    @Autowired
    OperationLogService operationLogService;
    @Override
    public List<PropertyTypesDTO> queryPropertyTypeMessage(PropertyTypesDTO propertyTypesDTO, WebPage page) {
        List<PropertyTypesDTO> propertyTypeDTOs = new ArrayList<>();// 公告类型DTO集合
        PropertyTypeEntity propertyTypeEntity = new PropertyTypeEntity();// 公告类型实体表

        // 查询物业服务表数据
        List<PropertyTypeEntity> propertyTypesEntityList = propertyTypesRepository.queryPropertyTypeMessage(propertyTypeEntity,page);
        for (PropertyTypeEntity typesMessage : propertyTypesEntityList) {
            PropertyTypesDTO propertyTypesMsg = new PropertyTypesDTO();
            propertyTypesMsg.setTypeId(typesMessage.getTypeId());       // 公告类型ID
            propertyTypesMsg.setType(typesMessage.getType());    // 公告类型
            propertyTypesMsg.setPropertyRange(typesMessage.getPropertyRange()); // 公告类型范围
            propertyTypeDTOs.add(propertyTypesMsg);
        }
        return propertyTypeDTOs;
    }

    @Override
    public String removePropertyTypeById(UserPropertyStaffEntity userPropertystaffEntity,String typeId) {
        // 判断是否执行删除成功
        String resultMessage = "";
        // 根据ID查询服务信息
        PropertyTypeEntity propertyTypesEntity = propertyTypesRepository.getPropertyTypeById(typeId);
        // 如果根据公告类型ID查询出结果则执行删除 否则返回此信息不存在
        if(propertyTypesEntity!=null){
            // 根据服务信息ID执行删除
            boolean delPropertyServices = propertyTypesRepository.removePropertyTypeById(propertyTypesEntity.getTypeId());
            if(delPropertyServices){
                resultMessage = "0";//此信息删除成功!
                //添加日志
                OperationLogDTO operationLogDTO =new OperationLogDTO();
                operationLogDTO.setProjectId(userPropertystaffEntity.getQueryScope());
                operationLogDTO.setUserName(userPropertystaffEntity.getUserName());
                operationLogDTO.setContent("删除公告类型");
                operationLogService.addOperationLog(operationLogDTO);
            }else {
                resultMessage = "1";//此信息删除失败!
            }
        }else {
            resultMessage = "2";//此信息不存在!
            return resultMessage;
        }
        return resultMessage;
    }

    @Override
    public void addPropertyTypes(UserPropertyStaffEntity userPropertystaffEntit,PropertyTypesDTO propertyTypeDTO) {
        PropertyTypeEntity propertyType = new PropertyTypeEntity();
        propertyType.setType(propertyTypeDTO.getType());// 公告类型
        propertyType.setPropertyRange(propertyTypeDTO.getPropertyRange());// 公告类型范围

        // 公告类型ID不为空 则更新 否则新增
        if("".equals(propertyTypeDTO.getTypeId())){
            PropertyTypeEntity TypeMsg = new PropertyTypeEntity();
            TypeMsg.setTypeId(IdGen.uuid());
            propertyType.setTypeId(TypeMsg.getTypeId());// 公告类型ID
            // 执行新增
            propertyTypesRepository.addPropertyType(propertyType);
            //添加日志
            OperationLogDTO operationLogDTO =new OperationLogDTO();
            operationLogDTO.setProjectId(userPropertystaffEntit.getQueryScope());
            operationLogDTO.setUserName(userPropertystaffEntit.getUserName());
            operationLogDTO.setContent("新增公告类型");
            operationLogService.addOperationLog(operationLogDTO);
        }else {
            // 根据ID查询公告类型详情
            PropertyTypeEntity propertyTypeMsg = propertyTypesRepository.getPropertyTypeById(propertyTypeDTO.getTypeId());
            // 根据第一条信息id查询
            if(propertyTypeMsg!=null){
                // 执行更新
                propertyType.setTypeId(propertyTypeDTO.getTypeId());
                propertyTypesRepository.modifyPropertyType(propertyType);
                //添加日志
                OperationLogDTO operationLogDTO =new OperationLogDTO();
                operationLogDTO.setProjectId(userPropertystaffEntit.getQueryScope());
                operationLogDTO.setUserName(userPropertystaffEntit.getUserName());
                operationLogDTO.setContent("更新公告类型");
                operationLogService.addOperationLog(operationLogDTO);
            }
        }
    }

    @Override
    public PropertyTypesDTO queryPropertyTypesById(String typeId) {
        PropertyTypesDTO propertyTypesMsg = new PropertyTypesDTO();
        // 根据ID查询信息详情
        PropertyTypeEntity PropertyTypeEntity = propertyTypesRepository.getPropertyTypeById(typeId);
        // 返回信息详情
        if(PropertyTypeEntity!=null){
            propertyTypesMsg.setTypeId(PropertyTypeEntity.getTypeId()); // 公告类型id
            propertyTypesMsg.setType(PropertyTypeEntity.getType()); // 公告类型
            propertyTypesMsg.setPropertyRange(PropertyTypeEntity.getPropertyRange()); // 公告类型范围
        }else {//无返回信息
            propertyTypesMsg.setTypeId("无");
        }
        return propertyTypesMsg;
    }



}
