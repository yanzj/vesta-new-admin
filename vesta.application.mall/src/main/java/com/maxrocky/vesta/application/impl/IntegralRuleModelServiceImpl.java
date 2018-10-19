package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.AdminDTO.IntegralRuleModelDTO;
import com.maxrocky.vesta.application.AdminDTO.MallDTO;
import com.maxrocky.vesta.application.inf.IntegralRuleModelService;
import com.maxrocky.vesta.domain.model.IntegralRuleModelEntity;
import com.maxrocky.vesta.domain.model.MallProductTypeEntity;
import com.maxrocky.vesta.domain.repository.IntegralRuleModelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/10.
 */
@Service
public class IntegralRuleModelServiceImpl implements IntegralRuleModelService {

    @Autowired
    IntegralRuleModelRepository integralRuleModelRepository;

    @Override
    public List<IntegralRuleModelDTO> getIntegralRuleModelList() {
        List<IntegralRuleModelDTO> integralRuleModelDTOList = new ArrayList<>();
        List<IntegralRuleModelEntity> integralRuleModelEntityList = integralRuleModelRepository.getIntegralRuleModelList();
        //封装结果集
        for (IntegralRuleModelEntity i : integralRuleModelEntityList){
            IntegralRuleModelDTO integralRuleModelDTO = new IntegralRuleModelDTO();
            BeanUtils.copyProperties(i,integralRuleModelDTO);
            integralRuleModelDTOList.add(integralRuleModelDTO);
        }
        return integralRuleModelDTOList;
    }
}
