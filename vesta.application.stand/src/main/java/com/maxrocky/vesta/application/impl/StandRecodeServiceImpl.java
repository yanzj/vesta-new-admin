package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.AdminDTO.RecodeAdminDTO;
import com.maxrocky.vesta.application.JsonDTO.RecodeDTO;
import com.maxrocky.vesta.application.inf.StandRecodeService;
import com.maxrocky.vesta.domain.model.StandRecodeEntity;
import com.maxrocky.vesta.domain.repository.StandRecodeRepository;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by chen on 2016/5/24.
 */
@Service
public class StandRecodeServiceImpl implements StandRecodeService {
    @Autowired
    StandRecodeRepository standRecodeRepository;

    @Override
    public void addStandRecode(RecodeAdminDTO recodeAdminDTO) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StandRecodeEntity standRecodeEntity = new StandRecodeEntity();
        standRecodeEntity.setCreateBy(recodeAdminDTO.getCreateBy());
        standRecodeEntity.setStandId(recodeAdminDTO.getStandId());
        standRecodeEntity.setRecodeDesc(recodeAdminDTO.getRecodeDesc());
        standRecodeEntity.setImageUrl(recodeAdminDTO.getImageUrl());
        try {
            standRecodeEntity.setCreateDate(date.parse(recodeAdminDTO.getCreateTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        standRecodeEntity.setId(IdGen.uuid());
        standRecodeRepository.addStandRecode(standRecodeEntity);
    }

    @Override
    public void addRecodes(List<RecodeDTO> recodeDTOs) {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StandRecodeEntity standRecodeEntity;
        for (RecodeDTO recodeDTO : recodeDTOs) {
            standRecodeEntity = new StandRecodeEntity();
            standRecodeEntity.setId(IdGen.uuid());
            standRecodeEntity.setStandId(recodeDTO.getStandId());
            standRecodeEntity.setImageUrl(recodeDTO.getImageUrl());
            try {
                standRecodeEntity.setCreateDate(date.parse(recodeDTO.getCreateDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            standRecodeEntity.setRecodeDesc(recodeDTO.getRecodeDesc());
        }
    }

    @Override
    public void updateRecode(RecodeAdminDTO recodeAdminDTO) {
        StandRecodeEntity standRecodeEntity = standRecodeRepository.getStandRecodeDetail(recodeAdminDTO.getRecodeId());
        standRecodeEntity.setRecodeDesc(recodeAdminDTO.getRecodeDesc());
        standRecodeRepository.updateStandRecode(standRecodeEntity);
    }

}
