package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.OperationLogDTO;
import com.maxrocky.vesta.application.inf.OperationLogService;
import com.maxrocky.vesta.application.inf.PropertyPriceSettingService;
import com.maxrocky.vesta.application.DTO.PropertyPriceSettingDTO;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.model.PropertyPriceSettingEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.PropertyCompanyRepository;
import com.maxrocky.vesta.domain.repository.PropertyPriceSettingRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/16.
 * 物业管理 维修价格设置 业务逻辑层实现类
 */
@Service
public class PropertyPriceSettingServiceImpl implements PropertyPriceSettingService {

    /**
     * 物业管理 维修价格设置 持久层接口
     */
    @Autowired
    PropertyPriceSettingRepository propertyPriceSettingRepository;

    /**
     * 物业项目公司 持久层接口
     */
    @Autowired
    PropertyCompanyRepository propertyCompanyRepository;

    /**
     *  后台核心日志 业务逻辑层接口
     */
    @Autowired
    private OperationLogService operationLogService;

    @Override
    public List<PropertyPriceSettingDTO> queryPriceSettingByProjectId(UserPropertyStaffEntity userPropertystaffEntity, PropertyPriceSettingDTO propertyPriceSetting,WebPage webPage) {
        List<PropertyPriceSettingDTO> priceSettingDTO = new ArrayList<>();
        // 声明维修价格表实体
        PropertyPriceSettingEntity propertyPrice = new PropertyPriceSettingEntity();
        // 默认条件 (登录人负责项目)
        propertyPrice.setProjectId(userPropertystaffEntity.getProjectId());
        // 搜索条件 项目
        propertyPrice.setProjectName(propertyPriceSetting.getProjectName());
        // 条件查询维修价格
        List<PropertyPriceSettingEntity> priceSettingEntities = propertyPriceSettingRepository.queryPriceSettingByProjectId(propertyPrice,webPage);
        for (PropertyPriceSettingEntity priceSetting : priceSettingEntities) {
            PropertyPriceSettingDTO priceSettings = new PropertyPriceSettingDTO();
            priceSettings.setPriceSettingId(priceSetting.getPriceSettingId());//维修价格ID
            priceSettings.setProjectId(priceSetting.getProjectId());//项目ID
            priceSettings.setProjectName(priceSetting.getProjectName());//项目名称
            priceSettingDTO.add(priceSettings);
        }
        return priceSettingDTO;
    }

    /**
     * 新增价格 或修改价格页面
     * @param propertyPriceSetting
     * @param userPropertystaff
     * @return
     */
    @Override
    public PropertyPriceSettingDTO initializePriceSetting(PropertyPriceSettingDTO propertyPriceSetting, UserPropertyStaffEntity userPropertystaff) {
        String project = "";
        if(null != propertyPriceSetting.getProjectId() && !"".equals(propertyPriceSetting.getProjectId())){
            project = propertyPriceSetting.getProjectId();
        }else {
            project = userPropertystaff.getProjectId();
        }
        // 根据项目ID查询信息
        PropertyPriceSettingEntity propertyPriceSettings = propertyPriceSettingRepository.queryPriceSetting(project);
        PropertyPriceSettingDTO propertyPrice = new PropertyPriceSettingDTO();
        if(null != propertyPriceSettings){
            propertyPrice.setPriceSettingId(propertyPriceSettings.getPriceSettingId());//ID
            propertyPrice.setPriceSettingCount(propertyPriceSettings.getPriceSettingCount());//维修价格
            propertyPrice.setProjectId(propertyPriceSettings.getProjectId());//项目ID
            propertyPrice.setProjectName(propertyPriceSettings.getProjectName());//项目名称
        }else {
            // 查询项目数据
            List<HouseProjectEntity> houseProjectEntities = propertyCompanyRepository.queryHouseProjectEntity(project);
            propertyPrice.setPriceSettingId("");//ID
            propertyPrice.setPriceSettingCount("");//维修价格
            propertyPrice.setProjectId(houseProjectEntities.get(0).getId());//项目ID
            propertyPrice.setProjectName(houseProjectEntities.get(0).getName());//项目名称
        }
        return propertyPrice;
    }

    /**
     * 新增或修改维修价格信息
     * @param propertyPriceSetting
     * @param userProperty
     * @return
     */
    @Override
    public String modifyPriceSetting(PropertyPriceSettingDTO propertyPriceSetting, UserPropertyStaffEntity userProperty) {
        String resultMessage="1";//失败
        // ID不为空则更新信息
        if("".equals(propertyPriceSetting.getPriceSettingId())){
            PropertyPriceSettingEntity priceSettingEntity = new PropertyPriceSettingEntity();
            priceSettingEntity.setPriceSettingId(IdGen.uuid());//id
            priceSettingEntity.setProjectId(propertyPriceSetting.getProjectId());//项目Id
            priceSettingEntity.setProjectName(propertyPriceSetting.getProjectName());//项目名称
            priceSettingEntity.setPriceSettingCount(propertyPriceSetting.getPriceSettingCount());//价格内容
            priceSettingEntity.setCreateBy(userProperty.getStaffName());
            priceSettingEntity.setCreateOn(new Date());//创建时间
            // 执行添加维修信息
            boolean updateMessage = propertyPriceSettingRepository.savePropertyPriceSetting(priceSettingEntity);
            if(updateMessage){
                resultMessage = "0";// 添加成功
                return resultMessage;
            }
            // 后台核心日志
            OperationLogDTO operation = new OperationLogDTO();
            operation.setUserName(userProperty.getStaffName());  // 用户名
            operation.setProjectId(userProperty.getProjectId());// 用户项目ID
            operation.setContent("新增价格信息!");      // 操作动作
            // 添加后台核心日志
            operationLogService.addOperationLog(operation);

        }else {
            PropertyPriceSettingEntity propertyPriceSettings = propertyPriceSettingRepository.queryPriceSetting(propertyPriceSetting.getProjectId());
            if(null != propertyPriceSettings){
                PropertyPriceSettingEntity propertyPrice = new PropertyPriceSettingEntity();
                propertyPrice.setPriceSettingId(propertyPriceSettings.getPriceSettingId());//ID
                propertyPrice.setProjectId(propertyPriceSettings.getProjectId());//项目ID
                propertyPrice.setProjectName(propertyPriceSettings.getProjectName());//项目名称
                propertyPrice.setPriceSettingCount(propertyPriceSetting.getPriceSettingCount());//价格内容
                propertyPrice.setModifyBy(userProperty.getStaffName());//修改人
                propertyPrice.setModifyOn(new Date());//修改时间
                boolean updateMessage = propertyPriceSettingRepository.updatePropertyPriceSetting(propertyPrice);
                if(updateMessage){
                    resultMessage = "0";//更新成功
                    return resultMessage;
                }
                // 后台核心日志
                OperationLogDTO operation = new OperationLogDTO();
                operation.setUserName(userProperty.getStaffName());  // 用户名
                operation.setProjectId(userProperty.getProjectId());// 用户项目ID
                operation.setContent("修改价格信息!");      // 操作动作
                // 添加后台核心日志
                operationLogService.addOperationLog(operation);
            }
        }
        return resultMessage;
    }

    /**
     * 根据维修价格ID删除信息
     * @param priceSettingId
     * @param userPropertystaffs
     * @return
     */
    @Override
    public String removePropertyConsultById(String priceSettingId, UserPropertyStaffEntity userPropertystaffs) {
        // 判断是否删除成功
        String resultMessage = "";
        // 根据维修价格ID查询信息
        PropertyPriceSettingEntity priceSettingEntity = propertyPriceSettingRepository.queryPriceSettingById(priceSettingId);
        // 维修表实体为空则不存在此信息
        if(null == priceSettingEntity){
            resultMessage = "0";//此信息不存在
        }else {
          // 根据维修价格ID删除信息
          boolean  priceSettingMessage = propertyPriceSettingRepository.delPropertyPriceSetting(priceSettingEntity);
            if(priceSettingMessage){
                resultMessage = "1"; //此信息已删除成功
            }else {
                resultMessage = "2";//此信息未删除成功
            }
            // 后台核心日志
            OperationLogDTO operation = new OperationLogDTO();
            operation.setUserName(userPropertystaffs.getStaffName());  // 用户名
            operation.setProjectId(userPropertystaffs.getProjectId());// 用户项目ID
            operation.setContent("删除价格信息!");      // 操作动作
            // 添加后台核心日志
            operationLogService.addOperationLog(operation);

        }
        return resultMessage;
    }
}
