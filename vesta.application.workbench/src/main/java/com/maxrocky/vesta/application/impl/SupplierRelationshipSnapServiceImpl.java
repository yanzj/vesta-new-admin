package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.jsonDTO.SupplierRelationshipDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.SupplierRelationshipSnapDTO;
import com.maxrocky.vesta.application.inf.SupplierRelationshipSnapService;
import com.maxrocky.vesta.domain.model.SupplierRelationshipSnapEntity;
import com.maxrocky.vesta.domain.repository.SupplierRelationshipSnapRepository;
import com.maxrocky.vesta.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mql on 2016/6/16.
 */
@Service
public class SupplierRelationshipSnapServiceImpl implements SupplierRelationshipSnapService {

    @Autowired
    SupplierRelationshipSnapRepository supplierRelationshipSnapRepository;

    @Override
    public SupplierRelationshipSnapDTO getByModifyDateAndId(Date modifyDate, String id) {
        List<SupplierRelationshipSnapEntity> list = supplierRelationshipSnapRepository.getByModifyDateAndId(modifyDate,id);
        SupplierRelationshipSnapDTO supplierRelationshipSnapDTO = new SupplierRelationshipSnapDTO();
        List<SupplierRelationshipDTO> supplierRelationshipDTOList = new ArrayList<SupplierRelationshipDTO>();
        if(list != null && !list.isEmpty()){
            for(SupplierRelationshipSnapEntity supplierRelationshipSnapEntity : list){
                SupplierRelationshipDTO supplierRelationshipDTO = new SupplierRelationshipDTO();
                supplierRelationshipDTO.setId(supplierRelationshipSnapEntity.getBusinessId()==null?"":supplierRelationshipSnapEntity.getBusinessId());
                supplierRelationshipDTO.setSupplierId(supplierRelationshipSnapEntity.getSupplierId()==null?"":supplierRelationshipSnapEntity.getSupplierId());
                supplierRelationshipDTO.setProjectNum(supplierRelationshipSnapEntity.getProjectNum()==null?"":supplierRelationshipSnapEntity.getProjectNum());
                supplierRelationshipDTO.setProjectId(supplierRelationshipSnapEntity.getProjectId()==null?"":supplierRelationshipSnapEntity.getProjectId());
                supplierRelationshipDTO.setThirdId(supplierRelationshipSnapEntity.getThirdId()==null?"":supplierRelationshipSnapEntity.getThirdId());
                supplierRelationshipDTO.setState(supplierRelationshipSnapEntity.getState());
                supplierRelationshipDTO.setModifyDate(supplierRelationshipSnapEntity.getModifyDate() == null ? "" : DateUtils.format(supplierRelationshipSnapEntity.getModifyDate()));
                supplierRelationshipDTOList.add(supplierRelationshipDTO);
            }
            supplierRelationshipSnapDTO.setTimeStamp(DateUtils.format(list.get(list.size() - 1).getModifyDate()));
            supplierRelationshipSnapDTO.setId(list.get(list.size()-1).getId()+"");
        }
        supplierRelationshipSnapDTO.setList(supplierRelationshipDTOList);
        return supplierRelationshipSnapDTO;
    }
}
