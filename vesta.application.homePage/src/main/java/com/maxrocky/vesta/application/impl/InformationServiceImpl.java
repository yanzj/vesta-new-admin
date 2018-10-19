package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.InformationDTO;
import com.maxrocky.vesta.application.DTO.InformationTypeDTO;
import com.maxrocky.vesta.application.inf.InformationService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.PropertyServicesEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserSettingEntity;
import com.maxrocky.vesta.domain.repository.InformationRepository;
import com.maxrocky.vesta.domain.repository.PropertyTypesRepository;
import com.maxrocky.vesta.domain.repository.UserSettingRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Annie on 2016/2/22.
 */
@Service
public class InformationServiceImpl implements InformationService {
    @Autowired
    InformationRepository informationRepository;
    @Autowired
    MapperFacade mapper;
    /**
     * 物业服务 持久层接口
     */
    @Autowired
    private PropertyTypesRepository propertyTypesRepository;

    /* 用户设置 */
    @Autowired
    UserSettingRepository userSettingRepository;

    @Override
    public ApiResult information( UserInfoEntity user)
    {
        // 根据用户ID查询 用户默认项目ID
        UserSettingEntity userSettingEntity = userSettingRepository.get(user.getUserId());

        InformationTypeDTO informationPropertyDTO = new InformationTypeDTO();
        // 根据项目ID物业服务信息
        List<PropertyServicesEntity> propertyServices = this.informationRepository.information(userSettingEntity.getProjectId());
        if(propertyServices.size() > 0){
            for (PropertyServicesEntity information : propertyServices){
                if(PropertyServicesEntity.SERVICES_TYPE_PROPERTY.equals(information.getServicesType())){
                    InformationDTO informationDTO = mapper.map(information, InformationDTO.class);
                    informationDTO.setServiceName(information.getServicesName());
                    informationDTO.setPhone(information.getServicesPhone());
                    informationPropertyDTO.getPropertyService().add(informationDTO);
                }else {
                    InformationDTO informationDTO = mapper.map(information, InformationDTO.class);
                    informationDTO.setServiceName(information.getServicesName());
                    informationDTO.setPhone(information.getServicesPhone());
                    informationPropertyDTO.getPublicService().add(informationDTO);
                }
            }
        }
        return new SuccessApiResult(informationPropertyDTO);
    }
}
