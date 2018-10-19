package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.PropertyDTO;
import com.maxrocky.vesta.application.inf.PropertyService;
import com.maxrocky.vesta.domain.model.PropertyEntity;
import com.maxrocky.vesta.domain.repository.PropertyRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunmei on 2016/2/26.
 */
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    PropertyRepository propertyRepository;
    @Override
    public List<PropertyDTO> PropertyManage(WebPage webPage) {
        List<PropertyDTO> propertyDTOList = new ArrayList<>();//DTO集合
        PropertyEntity propertyEntity = new PropertyEntity();

        List<PropertyEntity> propertyList = propertyRepository.PropertyManage(webPage);
        for (PropertyEntity property : propertyList) {
            PropertyDTO propertyDTO = new PropertyDTO();
            propertyDTO.setPropertyId(property.getId());
            propertyDTO.setName(property.getName());
            propertyDTO.setImgUrl(property.getImgUrl());
            propertyDTO.setCount(propertyList.size());
            propertyDTOList.add(propertyDTO);

        }
        return propertyDTOList;
    }

    @Override
    public List<PropertyDTO> Propertysort(String id,String state, WebPage webPage) {
        PropertyEntity property=  propertyRepository.getPropertyById(id);
        int sorty=property.getSort();
        if(state.equals("up")){
            PropertyEntity propertyEntity= propertyRepository.getPropertyManageBysortUp(property.getSort());
            int sortm=property.getSort();
            propertyEntity.setSort(sorty);
            propertyRepository.updateProperty(propertyEntity);
            property.setSort(sortm);
            propertyRepository.updateProperty(property);

        }else{
            PropertyEntity propertyEntity= propertyRepository.getPropertyManageBysortDown(property.getSort());
            int sortm=propertyEntity.getSort();
            propertyEntity.setSort(sorty);
            propertyRepository.updateProperty(propertyEntity);
            property.setSort(sortm);
            propertyRepository.updateProperty(property);
        }
        return this.PropertyManage(webPage);
    }
}
