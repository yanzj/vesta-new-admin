package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.OperationLogDTO;
import com.maxrocky.vesta.application.inf.OperationLogService;
import com.maxrocky.vesta.application.inf.PropertyServicesService;
import com.maxrocky.vesta.application.DTO.PropertyServicesDTO;
import com.maxrocky.vesta.domain.model.PropertyServicesEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.PropertyServicesRepository;
import com.maxrocky.vesta.domain.repository.PropertyTypesRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by ZhangBailiang on 2016/1/19.
 * 物业服务模块 业务逻辑层实现类
 */
@Service
public class PropertyServicesServiceImpl implements PropertyServicesService {

    /**
     * 物业服务 持久层接口
     */
    @Autowired
    private PropertyServicesRepository propertyServicesRepository;

    /**
     * 物业服务 持久层接口
     */
    @Autowired
    private PropertyTypesRepository propertyTypesRepository;

    /**
     *  后台核心日志 业务逻辑层接口
     */
    @Autowired
    private OperationLogService operationLogService;

    /**
     * 初始化物业服务信息类型
     * @return
     */
    @Override
    public PropertyServicesDTO queryPropertyServicesType() {
        PropertyServicesDTO servicesDTO = new PropertyServicesDTO();
        Map<Integer, String> statusMap = new HashMap<Integer, String>();
        statusMap.put(0, "-请选择类型-");
        statusMap.put(1, "物业服务信息");
        statusMap.put(2, "公共服务信息");
        servicesDTO.setStatusMap(statusMap);
        return servicesDTO;
    }

    /**
     * 初始化物业服务信息列表
     * @param propertyServicesDTO
     * @param webPage
     * @return
     */
    @Override
    public List<PropertyServicesDTO> queryPropertyServicesMessage(PropertyServicesDTO propertyServicesDTO, WebPage webPage) {
        List<PropertyServicesDTO> propertyServicesDTOs = new ArrayList<>();// 物业服务DTO集合
        PropertyServicesEntity propertyServicesEntity = new PropertyServicesEntity();// 物业服务实体表
        // 初始化 登陆人所负责范围
        propertyServicesEntity.setProjectId(propertyServicesDTO.getQueryScope());
        // 搜索条件
        propertyServicesEntity.setServicesName(propertyServicesDTO.getServiceName());// 服务信息名称(单位)
        propertyServicesEntity.setServicesPhone(propertyServicesDTO.getServicesPhone());// 服务信息电话
        propertyServicesEntity.setServicesType(propertyServicesDTO.getServiceType()); // 服务信息类别
        // 查询物业服务表数据
        List<PropertyServicesEntity> propertyServicesEntityList = propertyServicesRepository.queryPropertyServicesMessage(propertyServicesEntity, webPage);
        for (PropertyServicesEntity servicesMessage : propertyServicesEntityList) {
            PropertyServicesDTO propertyServicesMsg = new PropertyServicesDTO();
            propertyServicesMsg.setServicesId(servicesMessage.getServicesId());       // 服务信息ID
            propertyServicesMsg.setServiceName(servicesMessage.getServicesName());    // 服务信息名称
            propertyServicesMsg.setServicesPhone(servicesMessage.getServicesPhone()); // 服务信息电话

            if(servicesMessage.getServicesType().equals(PropertyServicesEntity.SERVICES_TYPE_PROPERTY)){
                propertyServicesMsg.setServiceType("物业服务信息"); // 物业服务信息 1（服务信息类别）
            }else{
                propertyServicesMsg.setServiceType(" 公共服务信息");//  公共服务信息 2（服务信息类别）
            }
            propertyServicesMsg.setAddMessageTime(DateUtils.format(servicesMessage.getAddMessageTime())); // 服务信息添加时间
            propertyServicesDTOs.add(propertyServicesMsg);
        }
        return propertyServicesDTOs;
    }

    /**
     * 根据ID删除服务信息
     * @param servicesId
     * @return
     */
    @Override
    public String removePropertyServicesById(String servicesId,UserPropertyStaffEntity user) {
        // 判断是否执行删除成功
        String resultMessage = "";
        // 根据ID查询服务信息
        List<PropertyServicesEntity> propertyServicesEntityList = propertyServicesRepository.queryPropertyServicesById(servicesId);
        // 如果根据服务信息ID查询出结果则执行删除 否则返回此信息不存在
        if(propertyServicesEntityList.size() > 0){
            // 根据服务信息ID执行删除
            boolean delPropertyServices = propertyServicesRepository.removePropertyServicesById(propertyServicesEntityList.get(0).getServicesId());
            if(delPropertyServices){
                resultMessage = "0";//此信息删除成功!
            }else {
                resultMessage = "1";//此信息删除失败!
            }
            // 后台核心日志
            OperationLogDTO operation = new OperationLogDTO();
            operation.setUserName(user.getStaffName());  // 用户名
            operation.setProjectId(user.getProjectId());// 用户项目ID
            operation.setContent("删除服务信息!");      // 操作动作
            // 添加后台核心日志
            operationLogService.addOperationLog(operation);
        }else {
            resultMessage = "2";//此信息不存在!
            return resultMessage;
        }
        return resultMessage;
    }

    /**
     * 新增服务信息
     * @param userPropertystaffEntit
     * @param propertyServicesDTO
     */
    @Override
    public void addPropertyServices(UserPropertyStaffEntity userPropertystaffEntit, PropertyServicesDTO propertyServicesDTO) {
        PropertyServicesEntity propertyServicesEntity = new PropertyServicesEntity();
        propertyServicesEntity.setServicesName(propertyServicesDTO.getServiceName());// 服务信息名称
        propertyServicesEntity.setServicesPhone(propertyServicesDTO.getServicesPhone());// 服务信息电话
        propertyServicesEntity.setProjectId(userPropertystaffEntit.getProjectId());
        propertyServicesEntity.setServicesType(propertyServicesDTO.getServiceType());//服务信息类别
        propertyServicesEntity.setAddMessageTime(new Date());// 服务信息添加时间
        propertyServicesEntity.setOptname(userPropertystaffEntit.getStaffName()); // 操作人
        propertyServicesEntity.setOptdate(new Date());// 操作时间

        // 服务信息ID不为空 则更新 否则新增
        if(!"".equals(propertyServicesDTO.getServicesId())){
            // 根据ID查询信息详情(如果多条 默认只取第一条)
            List<PropertyServicesEntity> propertyServicesEntityList = propertyServicesRepository.queryPropertyServicesById(propertyServicesDTO.getServicesId());
            // 根据第一条信息id查询
            if(propertyServicesEntityList.size() > 0){
                PropertyServicesEntity propertyServicesEntitys = propertyServicesRepository.getPropertyServicesById(propertyServicesEntityList.get(0).getServicesId());
                propertyServicesEntity.setServicesId(propertyServicesEntitys.getServicesId());
                // 执行更新
                propertyServicesRepository.modifyPropertyServices(propertyServicesEntity);
                // 后台核心日志
                OperationLogDTO operation = new OperationLogDTO();
                operation.setUserName(userPropertystaffEntit.getStaffName());  // 用户名
                operation.setProjectId(userPropertystaffEntit.getProjectId());// 用户项目ID
                operation.setContent("修改服务信息!");      // 操作动作
                // 添加后台核心日志
                operationLogService.addOperationLog(operation);
            }
        }else {
            PropertyServicesEntity servicesId = new PropertyServicesEntity();
            servicesId.setServicesId(IdGen.uuid());
            propertyServicesEntity.setServicesId(servicesId.getServicesId());// 服务信息ID
            // 执行新增
            propertyServicesRepository.addPropertyServices(propertyServicesEntity);
            // 后台核心日志
            OperationLogDTO operation = new OperationLogDTO();
            operation.setUserName(userPropertystaffEntit.getStaffName());  // 用户名
            operation.setProjectId(userPropertystaffEntit.getProjectId());// 用户项目ID
            operation.setContent("新增服务信息!");      // 操作动作
            // 添加后台核心日志
            operationLogService.addOperationLog(operation);
        }
    }

    /**
     * 根据服务信息ID查询详情
     * @param servicesId
     * @return
     */
    @Override
    public PropertyServicesDTO queryPropertyServicesById(String servicesId) {
        PropertyServicesDTO propertyServicesMsg = new PropertyServicesDTO();
        // 根据ID查询信息详情
        List<PropertyServicesEntity> propertyServicesEntityList = propertyServicesRepository.queryPropertyServicesById(servicesId);
        // 返回信息详情
        if(propertyServicesEntityList.size() > 0){
            propertyServicesMsg.setServicesId(propertyServicesEntityList.get(0).getServicesId());       // 服务信息ID
            propertyServicesMsg.setServiceName(propertyServicesEntityList.get(0).getServicesName());    // 服务信息名称
            propertyServicesMsg.setServicesPhone(propertyServicesEntityList.get(0).getServicesPhone()); // 服务信息电话
            propertyServicesMsg.setServiceType(propertyServicesEntityList.get(0).getServicesType());    // 服务信息类别
        }else {//无返回信息
            propertyServicesMsg.setServicesId("无");
        }
        return propertyServicesMsg;
    }
}
