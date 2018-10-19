package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.FirstClassificationService;
import com.maxrocky.vesta.domain.model.FirstClassificationEntity;
import com.maxrocky.vesta.domain.repository.FirstClassificationCRMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mql on 2016/6/2.
 */
@Service
public class FirstClassificationServiceImpl implements FirstClassificationService {

    @Autowired
    FirstClassificationCRMRepository firstClassificationCRMRepository;

    @Override
    public Map<String, String> getFirstClassification() {
        List<FirstClassificationEntity> list = firstClassificationCRMRepository.getFirstClassification();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("0000","请选择一级分类");
        if(list != null && !list.isEmpty()){
            for(FirstClassificationEntity fce : list){
                map.put(fce.getValue(),fce.getLabel());
            }
        }

        return map;
    }

    @Override
    public FirstClassificationEntity getFirstClassificationById(String id) {
        return firstClassificationCRMRepository.get(id);
    }
}
