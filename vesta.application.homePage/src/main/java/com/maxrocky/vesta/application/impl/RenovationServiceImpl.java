package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.RenovationDTO;
import com.maxrocky.vesta.application.inf.RenovationService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.RenovationEntity;
import com.maxrocky.vesta.domain.repository.RenovationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Annie on 2016/2/23.
 */
@Service
public class RenovationServiceImpl implements RenovationService {
    @Autowired
    RenovationRepository renovationRepository;

    @Override
    public ApiResult renovation() {
        RenovationDTO renovationDTO=new RenovationDTO();
        List<RenovationEntity> renovationEntity=  renovationRepository.renovation();
        renovationDTO.setContent(renovationEntity.get(0).getContent());
        return new SuccessApiResult(renovationDTO);
    }
}
