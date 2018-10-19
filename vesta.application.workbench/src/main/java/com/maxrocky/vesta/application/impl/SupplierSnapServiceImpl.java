package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.jsonDTO.SupplierAppDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.SupplierDataDTO;
import com.maxrocky.vesta.application.DTO.jsonDTO.SupplierSnapDTO;
import com.maxrocky.vesta.application.inf.SupplierSnapService;
import com.maxrocky.vesta.domain.model.SupplierSnapEntity;
import com.maxrocky.vesta.domain.repository.SupplierSnapRepository;
import com.maxrocky.vesta.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mql on 2016/6/15.
 */
@Service
public class SupplierSnapServiceImpl implements SupplierSnapService {

    @Autowired
    SupplierSnapRepository supplierSnapRepository;

    @Override
    public SupplierSnapDTO getByModifyDateAndId(String modifyDate, String id) {
        List<SupplierSnapEntity> list = supplierSnapRepository.getByModifyDateAndId(modifyDate,id);
        SupplierSnapDTO supplierSnapDTO = new SupplierSnapDTO();
        List<SupplierDataDTO> supplierDataDTOList = new ArrayList<SupplierDataDTO>();
        if(list != null && !list.isEmpty()){
            for(SupplierSnapEntity supplierSnapEntity : list){
                SupplierDataDTO supplierDataDTO = new SupplierDataDTO();
                supplierDataDTO.setId(supplierSnapEntity.getBusinessId()==null?"":supplierSnapEntity.getBusinessId());
                supplierDataDTO.setType(supplierSnapEntity.getType() == null ? "" : supplierSnapEntity.getType());
                supplierDataDTO.setName(supplierSnapEntity.getName() == null ? "" : supplierSnapEntity.getName());
                supplierDataDTO.setState(supplierSnapEntity.getState() == null ? "" : supplierSnapEntity.getState());
                supplierDataDTO.setGraded(supplierSnapEntity.getGraded() == null ? "" : supplierSnapEntity.getGraded());
                supplierDataDTO.setParentId(supplierSnapEntity.getParentId() == null ? "" : supplierSnapEntity.getParentId());
                supplierDataDTO.setModifyDate(supplierSnapEntity.getModifyDate() == null ? "" : DateUtils.format(supplierSnapEntity.getModifyDate()));
                supplierDataDTOList.add(supplierDataDTO);
            }
            supplierSnapDTO.setTimeStamp(DateUtils.format(list.get(list.size() - 1).getModifyDate()));
            supplierSnapDTO.setId(list.get(list.size()-1).getId()+"");
        }
        supplierSnapDTO.setList(supplierDataDTOList);
        return supplierSnapDTO;
    }
}
