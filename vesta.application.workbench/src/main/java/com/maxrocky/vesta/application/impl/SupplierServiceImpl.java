package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.jsonDTO.*;
import com.maxrocky.vesta.application.adminDTO.SupplierDTO;
import com.maxrocky.vesta.application.inf.SupplierService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.SupplierEntity;
import com.maxrocky.vesta.domain.model.SupplierRelationshipEntity;
import com.maxrocky.vesta.domain.repository.SupplierRelationshipRepository;
import com.maxrocky.vesta.domain.repository.SupplierRepository;
import com.maxrocky.vesta.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by mql on 2016/6/13.
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    SupplierRelationshipRepository supplierRelationshipRepository;

    @Override
    public SupplierAppDTO getSupplier(Date supplierDate, Date relationshipDate) {
        SupplierAppDTO supplierAppDTO = new SupplierAppDTO();
        List<SupplierEntity> supplierEntityList = supplierRepository.getByModifyDate(supplierDate);
        List<SupplierRelationshipEntity> relationshipEntityList = supplierRelationshipRepository.getByModifyDate(relationshipDate);
        List<SupplierDataDTO> supplierList = new ArrayList<SupplierDataDTO>();
        List<SupplierRelationshipDTO> relationshipList = new ArrayList<SupplierRelationshipDTO>();
        if(supplierEntityList != null && !supplierEntityList.isEmpty()){
            supplierAppDTO.setSupplierTime(DateUtils.format(supplierEntityList.get(0).getModifyDate()));
            for(SupplierEntity supplierEntity:supplierEntityList){
                SupplierDataDTO supplierDTO = new SupplierDataDTO();
                supplierDTO.setId(supplierEntity.getId()==null?"":supplierEntity.getId());
                supplierDTO.setName(supplierEntity.getName()==null?"":supplierEntity.getName());
                supplierDTO.setType(supplierEntity.getType()==null?"":supplierEntity.getType());
                supplierList.add(supplierDTO);
            }
        }
        supplierAppDTO.setSupplierList(supplierList);
        if(relationshipEntityList != null && !relationshipEntityList.isEmpty()){
            supplierAppDTO.setRelationshipTime(DateUtils.format(relationshipEntityList.get(0).getModifyDate()));
            for(SupplierRelationshipEntity supplierRelationshipEntity : relationshipEntityList){
                SupplierRelationshipDTO supplierRelationshipDTO = new SupplierRelationshipDTO();
                supplierRelationshipDTO.setId(supplierRelationshipEntity.getId()==null?"":supplierRelationshipEntity.getId());
                supplierRelationshipDTO.setProjectId(supplierRelationshipEntity.getProjectId()==null?"":supplierRelationshipEntity.getProjectId());
                supplierRelationshipDTO.setProjectNum(supplierRelationshipEntity.getProjectNum()==null?"":supplierRelationshipEntity.getProjectNum());
                supplierRelationshipDTO.setSupplierId(supplierRelationshipEntity.getSupplierId()==null?"":supplierRelationshipEntity.getSupplierId());
                supplierRelationshipDTO.setThirdId(supplierRelationshipEntity.getThirdId()==null?"":supplierRelationshipEntity.getThirdId());
                relationshipList.add(supplierRelationshipDTO);
            }
        }

        supplierAppDTO.setRelationshipList(relationshipList);

        return supplierAppDTO;
    }

    @Override
    public Map<String, String> getSupplierByProjectNumAndThirdId(String projectNum, String thirdId) {
        List<Object[]> list = supplierRepository.getByProjectNumAndThirdId(projectNum,thirdId);
        Map<String,String> map = new HashMap<String,String>();
        map.put("","请选择供应商");
        if(list != null && !list.isEmpty()){
            for(Object[] obj:list){
                map.put(obj[0].toString(),obj[1]==null?"":obj[1].toString());
            }
        }
        return map;
    }

    @Override
    public String getSupplierByName(String supplierName) {

        List<Object> list = supplierRepository.getSupplierByName(supplierName);
        StringBuilder supplierId = new StringBuilder("(");
        if(list != null && !list.isEmpty()){
            for(Object obj:list){
                supplierId.append("'");
                supplierId.append(obj.toString());
                supplierId.append("',");
            }
        }
        supplierId.setLength(supplierId.length()-1);
        supplierId.append(")");
        String id = supplierId.toString() ;
        return id;
    }

    @Override
    public ApiResult getSupplierPeople(String timeStamp, String num) {
        List<Object[]> objects = supplierRepository.getSupplierPeople(timeStamp,Integer.parseInt(num));
        SupplierPeopleDTO supplierPeopleDTO = new SupplierPeopleDTO();
        List<SupplierSnapViewDTO> supplierSnapViewDTOs = new ArrayList<SupplierSnapViewDTO>();
        String tempTime="";
        if(objects!=null){
            SupplierSnapViewDTO supplierSnapViewDTO;
            for(Object[] object:objects){
                supplierSnapViewDTO = new SupplierSnapViewDTO();
                supplierSnapViewDTO.setId(String.valueOf(object[0]));
                supplierSnapViewDTO.setName(String.valueOf(object[1]));
                supplierSnapViewDTO.setParentId(String.valueOf(object[2]));
                supplierSnapViewDTO.setModifyDate(DateUtils.format((Date) object[3]));
                supplierSnapViewDTO.setState(String.valueOf(object[4]));
                supplierSnapViewDTO.setGraded("2");
                supplierSnapViewDTO.setUnionCode(String.valueOf(object[0])+String.valueOf(object[2]));
                tempTime = supplierSnapViewDTO.getModifyDate();
                supplierSnapViewDTOs.add(supplierSnapViewDTO);
            }
        }
        supplierPeopleDTO.setTimeStamp(tempTime);
        supplierPeopleDTO.setList(supplierSnapViewDTOs);
        return new SuccessApiResult(supplierPeopleDTO);
    }

    @Override
    public SupplierDTO getSupplierDetail(String supplierId) {
        SupplierDTO supplierDTO = new SupplierDTO();
        SupplierEntity supplierEntity= supplierRepository.getById(supplierId);
        if(supplierEntity!=null){
            supplierDTO.setSupplierId(supplierEntity.getId());
            supplierDTO.setSupplierName(supplierEntity.getName());
        }
        return supplierDTO;
    }

    @Override
    public List<SupplierDTO> getSupplierList() {
        List<SupplierEntity> supplierEntities = supplierRepository.getSupplierList();
        List<SupplierDTO> supplierDTOs = new ArrayList<SupplierDTO>();
        if(supplierEntities!=null){
            SupplierDTO supplierDTO;
            for(SupplierEntity supplierEntity:supplierEntities){
                supplierDTO = new SupplierDTO();
                supplierDTO.setSupplierId(supplierEntity.getId());
                supplierDTO.setSupplierName(supplierEntity.getName());
                supplierDTOs.add(supplierDTO);
            }
        }
        return supplierDTOs;
    }

    @Override
    public ApiResult getSuplliers(String supplierName) {
        List<SupplierMapDTO> supplierMapDTOs = new ArrayList<SupplierMapDTO>();
        List<SupplierEntity> supplierEntities = supplierRepository.getSuppliers(supplierName);
        if(supplierEntities!=null){
            SupplierMapDTO supplierMapDTO;
            for(SupplierEntity supplierEntity:supplierEntities){
                supplierMapDTO = new SupplierMapDTO();
                supplierMapDTO.setSupplierId(supplierEntity.getId());
                supplierMapDTO.setSupplierName(supplierEntity.getName());
                supplierMapDTOs.add(supplierMapDTO);
            }
        }
        return new SuccessApiResult(supplierMapDTOs);
    }
}
