package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.PropertyImageDTO;
import com.maxrocky.vesta.application.inf.PropertyImageService;
import com.maxrocky.vesta.application.DTO.WorkOrderDetailDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.PropertyImageEntity;
import com.maxrocky.vesta.domain.model.PropertyRepairEntity;
import com.maxrocky.vesta.domain.repository.PropertyImageRepository;
import com.maxrocky.vesta.domain.repository.PropertyRepairRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.message.success.SuccessResource;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liudongxin on 2016/1/17.
 * 物业报修图片实现方法
 */
@Service
public class PropertyImageServiceImpl implements PropertyImageService {
    @Autowired
    private PropertyImageRepository propertyImageRepository;
    @Autowired
    private PropertyRepairRepository propertyRepairRepository;
    /**
     * 查看报修图片
     * @param id
     * @return
     */
    @Override
    public List<PropertyImageEntity> getImageUrl(String id) {
        return propertyImageRepository.getImageUrl(id);
    }

    /**
     * 添加报修图片
     */
    @Override
    public ApiResult createImage(WorkOrderDetailDTO workOrderDetailDTO) throws GeneralException {
        if (workOrderDetailDTO == null) {
            return ErrorResource.getError("tip_00000040");//报修信息为空
        }
        if(StringUtil.isEmpty(workOrderDetailDTO.getId())){
            return ErrorResource.getError("tip_pe00000002");//报修id为空
        }
        try{
            //查询报修信息
            PropertyRepairEntity propertyRepairEntity=propertyRepairRepository.getRepairDetail(workOrderDetailDTO.getId());
            if(propertyRepairEntity==null){
                return ErrorResource.getError("tip_pe00000018");//报修信息不存在
            }
            if(workOrderDetailDTO.getImageList().size()>0) {
                for (PropertyImageDTO propertyImage : workOrderDetailDTO.getImageList()) {
                    PropertyImageEntity propertyImageEntity = new PropertyImageEntity();
                    propertyImageEntity.setImageId(IdGen.uuid());
                    propertyImageEntity.setUploadDate(DateUtils.getDate());
                    propertyImageEntity.setImgFkId(workOrderDetailDTO.getId());
                    propertyImageEntity.setImagePath(propertyImage.getImageUrl());
                    propertyImageEntity.setState("0");//0代表有效;1代表无效
                    propertyImageEntity.setImageType("2");//维修完成
                    propertyImageEntity.setUploadName(propertyImage.getImageUrl());
                    propertyImageRepository.saveImage(propertyImageEntity);
                }
            }
            return new SuccessApiResult(SuccessResource.getResource("tip_ps00000001"), null);//添加成功
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }
}