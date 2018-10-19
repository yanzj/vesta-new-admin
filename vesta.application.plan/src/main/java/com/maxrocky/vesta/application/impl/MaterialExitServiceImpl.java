package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.MaterialExitService;
import com.maxrocky.vesta.application.jsonDTO.ExitReceiveDTO;
import com.maxrocky.vesta.application.jsonDTO.MaterialExitDTO;
import com.maxrocky.vesta.domain.model.MaterialExitEntity;
import com.maxrocky.vesta.domain.model.MaterialImageEntity;
import com.maxrocky.vesta.domain.repository.MaterialExitRepository;
import com.maxrocky.vesta.domain.repository.MaterialImageRepository;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by chen on 2016/5/24.
 */

@Service
public class MaterialExitServiceImpl implements MaterialExitService {
    @Autowired
    MaterialExitRepository materialExitRepository;
    @Autowired
    MaterialImageRepository materialImageRepository;

    @Override
    public void addMaterialExit(List<ExitReceiveDTO> exitReceiveDTOs) {
        MaterialExitEntity materialExitEntity;
        for(ExitReceiveDTO exitReceiveDTO:exitReceiveDTOs){
            materialExitEntity = new MaterialExitEntity();
            materialExitEntity.setExitId(IdGen.uuid());
            materialExitEntity.setMaterialId(exitReceiveDTO.getMaterialId());
            materialExitEntity.setProblem(exitReceiveDTO.getProblem());
            materialExitEntity.setSupplier(exitReceiveDTO.getSupplier());
            if(exitReceiveDTO.getImageList()!=null&&exitReceiveDTO.getImageList().size()>0){
                for(int i=0;i<exitReceiveDTO.getImageList().size();i++){
                    MaterialImageEntity materialImageEntity = new MaterialImageEntity();
                    materialImageEntity.setId(IdGen.getAnyImgID());
                    materialImageEntity.setCrtTime(new Date());
                    materialImageEntity.setBussinessId(materialExitEntity.getExitId());
                    materialImageEntity.setUrl(exitReceiveDTO.getImageList().get(i).getUrl());
                    materialImageRepository.addMaterialImage(materialImageEntity);   //保存相关图片列表
                }
            }
            materialExitRepository.addMaterialExit(materialExitEntity);  //保存退场记录
        }
    }

    @Override
    public List<MaterialExitDTO> getMaterialExits() {
        return null;
    }
}
