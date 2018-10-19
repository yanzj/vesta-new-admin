package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.AdminDTO.IntegralRuleDTO;
import com.maxrocky.vesta.application.AdminDTO.IntegralRuleListDTO;
import com.maxrocky.vesta.application.AdminDTO.IntegralRuleModelDTO;
import com.maxrocky.vesta.application.AdminDTO.IntegralRuleQuerry;
import com.maxrocky.vesta.application.inf.IntegralRuleService;
import com.maxrocky.vesta.domain.model.IntegralRuleEntity;
import com.maxrocky.vesta.domain.model.IntegralRuleModelEntity;
import com.maxrocky.vesta.domain.repository.IntegralRuleRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.IdGen;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2017/8/10.
 */
@Service
public class IntegralRuleServiceImpl implements IntegralRuleService {

    @Autowired
    IntegralRuleRepository integralRuleRepository;

    @Override
    public List<IntegralRuleListDTO> getIntegralRuleList(IntegralRuleQuerry q, WebPage webPage) {
        Map<String,Object> paramsMap = new HashedMap();
        paramsMap.put("clientName",q.getClientName());
        paramsMap.put("modelName",q.getModelName());
        List<Object> list = integralRuleRepository.getIntegralRuleList(paramsMap,webPage);
        List<IntegralRuleListDTO> integralRuleListDTOList = new ArrayList<>();
        for (Object l : list){
            IntegralRuleListDTO idto = new IntegralRuleListDTO();
            Object[] obj = (Object[]) l;
            idto.setIntegralRuleId((String) obj[0]);
            idto.setIntegralNumber((String)obj[1]);
            idto.setModelName((String) obj[2]);
            idto.setClientName((String) obj[3]);
            integralRuleListDTOList.add(idto);
        }

        return integralRuleListDTOList;
    }

    @Override
    public IntegralRuleDTO getIntegralRuleById(String integralRuleId) {
        if(null == integralRuleId){
            return null;
        }
        IntegralRuleDTO integralRuleDTO = new IntegralRuleDTO();
        BeanUtils.copyProperties(integralRuleRepository.getIntegralRuleById(integralRuleId),integralRuleDTO);
        return integralRuleDTO;
    }

    @Override
    public Integer AddIntegralRuleEntity(IntegralRuleDTO integralRuleDTO) {
        IntegralRuleEntity check = new IntegralRuleEntity();
        BeanUtils.copyProperties(integralRuleDTO,check);
        List<IntegralRuleEntity> list = integralRuleRepository.CheckIntegralRule(check);
        if(list.size() != 0){
            return 1;
        }

        if (null != integralRuleDTO.getIntegralRuleId() && !"".equals(integralRuleDTO.getIntegralRuleId())){
            //更新
            IntegralRuleEntity ruleEntity = integralRuleRepository.getIntegralRuleById(integralRuleDTO.getIntegralRuleId());
            ruleEntity.setClientConfigId(integralRuleDTO.getClientConfigId());
            ruleEntity.setIntegralNumber(integralRuleDTO.getIntegralNumber());
            ruleEntity.setIntegralRuleModelId(integralRuleDTO.getIntegralRuleModelId());
            ruleEntity.setCreateBy(integralRuleDTO.getCreateBy());
            ruleEntity.setCreateOn(integralRuleDTO.getCreateOn());
            integralRuleRepository.UpdateIntegralRuleEntity(ruleEntity);
            return 0;
        }else{
            //保存
            IntegralRuleEntity integralRuleEntity = new IntegralRuleEntity();
            integralRuleDTO.setIntegralRuleId(IdGen.uuid());
            BeanUtils.copyProperties(integralRuleDTO,integralRuleEntity);
            integralRuleRepository.AddIntegralRuleEntity(integralRuleEntity);
            return 0;
        }

    }

    @Override
    public void UpdateIntegralRuleEntity(IntegralRuleDTO integralRuleDTO) {

    }

    @Override
    public Integer getIntegralRuleList(IntegralRuleDTO i) {
        IntegralRuleEntity integralRuleEntity = new IntegralRuleEntity();
        BeanUtils.copyProperties(i,integralRuleEntity);
        List<IntegralRuleEntity> list = integralRuleRepository.CheckIntegralRule(integralRuleEntity);
        if(list.size() == 0){
            return 0;
        }else{
            return 1;
        }

    }
}
