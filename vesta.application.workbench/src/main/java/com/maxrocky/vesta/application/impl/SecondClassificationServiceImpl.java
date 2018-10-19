package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.SecondClassificationService;
import com.maxrocky.vesta.domain.model.SecondClassificationEntity;
import com.maxrocky.vesta.domain.repository.SecondClassificationCRMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mql on 2016/6/2.
 */
@Service
public class SecondClassificationServiceImpl implements SecondClassificationService {

    @Autowired
    SecondClassificationCRMRepository secondClassificationCRMRepository;

    @Override
    public Map<String, String> getSecondClassification(String firstid) {
        List<SecondClassificationEntity> list = secondClassificationCRMRepository.getSecondClassification(firstid);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("0000","请选择二级分类");
        if(firstid!=null){
            if(list != null && !list.isEmpty()){
                for(SecondClassificationEntity sce : list){
                    map.put(sce.getValue(),sce.getLabel());
                }
            }
        }
        return map;
    }

}
