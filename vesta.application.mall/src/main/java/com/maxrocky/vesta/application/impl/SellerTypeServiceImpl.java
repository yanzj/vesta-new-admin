package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.JsonDTO.SellerTypeDTO;
import com.maxrocky.vesta.application.inf.SellerTypeService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.SellerTypeEntity;
import com.maxrocky.vesta.domain.repository.SellerTypeRepository;
import com.maxrocky.vesta.domain.repository.ShopSellerRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/1/17.
 */
@Service
public class SellerTypeServiceImpl implements SellerTypeService {
    @Autowired
    SellerTypeRepository sellerTypeRepository;
    @Autowired
    ShopSellerRepository shopSellerRepository;
    @Override
    public ApiResult getCategoryList() {
        List<SellerTypeEntity> sellerTypeEntities= sellerTypeRepository.getSellerTypeList();
        List<SellerTypeDTO> sellerTypeDTOs = new ArrayList<SellerTypeDTO>();
        if(sellerTypeEntities!=null){
            for(SellerTypeEntity sellerTypeEntity:sellerTypeEntities){
                SellerTypeDTO sellerTypeDTO = new SellerTypeDTO();
                sellerTypeDTO.setTypeName(sellerTypeEntity.getTypeName());
                sellerTypeDTO.setTypeId(sellerTypeEntity.getTypeId());
                sellerTypeDTOs.add(sellerTypeDTO);
            }
        }
        return new SuccessApiResult(sellerTypeDTOs);
    }

    @Override
    public List<SellerTypeDTO> getTypeList() {
        List<SellerTypeEntity> sellerTypeEntities= sellerTypeRepository.getSellerTypeList();
        List<SellerTypeDTO> sellerTypeDTOs = new ArrayList<SellerTypeDTO>();
        if(sellerTypeEntities!=null){
            for(SellerTypeEntity sellerTypeEntity:sellerTypeEntities){
                SellerTypeDTO sellerTypeDTO = new SellerTypeDTO();
                sellerTypeDTO.setTypeName(sellerTypeEntity.getTypeName());
                sellerTypeDTO.setTypeId(sellerTypeEntity.getTypeId());
                sellerTypeDTOs.add(sellerTypeDTO);
            }
        }
        return sellerTypeDTOs;
    }

    @Override
    public List<SellerTypeDTO> getTypeList(WebPage webPage) {
        List<SellerTypeEntity> sellerTypeEntities= sellerTypeRepository.getSellerTypeList(webPage);
        List<SellerTypeDTO> sellerTypeDTOs = new ArrayList<SellerTypeDTO>();
        if(sellerTypeEntities!=null){
            for(SellerTypeEntity sellerTypeEntity:sellerTypeEntities){
                SellerTypeDTO sellerTypeDTO = new SellerTypeDTO();
                sellerTypeDTO.setTypeName(sellerTypeEntity.getTypeName());
                sellerTypeDTO.setTypeId(sellerTypeEntity.getTypeId());
                sellerTypeDTOs.add(sellerTypeDTO);
            }
        }
        return sellerTypeDTOs;
    }

    @Override
    public void addSellerType(SellerTypeDTO sellerTypeDTO) {
        SellerTypeEntity sellerTypeEntity = new SellerTypeEntity();
        sellerTypeEntity.setTypeId(IdGen.getMallID());
        sellerTypeEntity.setTypeName(sellerTypeDTO.getTypeName());
        sellerTypeEntity.setStatus(SellerTypeEntity.STATUS_VALID);
        sellerTypeEntity.setCreateTime(new Date());
        sellerTypeRepository.AddSellerType(sellerTypeEntity);
    }

    @Override
    public void updateSellerType(SellerTypeDTO sellerTypeDTO) {
        SellerTypeEntity sellerTypeEntity = sellerTypeRepository.getSellerTypeDetail(sellerTypeDTO.getTypeId());
        sellerTypeEntity.setTypeName(sellerTypeDTO.getTypeName());
        sellerTypeEntity.setModifyTime(new Date());
        sellerTypeRepository.UpdateSellerType(sellerTypeEntity);
    }

    @Override
    public void delSellerType(String typeId) {
        SellerTypeEntity sellerTypeEntity = sellerTypeRepository.getSellerTypeDetail(typeId);
        sellerTypeEntity.setModifyTime(new Date());
        sellerTypeEntity.setStatus(SellerTypeEntity.STATUS_INVALID);
        shopSellerRepository.DelSellers(typeId);                      //先删除商户
        sellerTypeRepository.UpdateSellerType(sellerTypeEntity);      //再删除该分类
    }

    @Override
    public SellerTypeDTO getSellerTypeDetail(String typeId) {
        SellerTypeEntity sellerTypeEntity = sellerTypeRepository.getSellerTypeDetail(typeId);
        SellerTypeDTO sellerTypeDTO = new SellerTypeDTO();
        sellerTypeDTO.setTypeId(sellerTypeEntity.getTypeId());
        sellerTypeDTO.setTypeName(sellerTypeEntity.getTypeName());
        return sellerTypeDTO;
    }
}
