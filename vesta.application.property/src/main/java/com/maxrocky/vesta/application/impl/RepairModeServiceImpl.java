package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.RepairModeService;
import com.maxrocky.vesta.domain.model.RepairModeEntity;
import com.maxrocky.vesta.domain.model.SecondClassificationEntity;
import com.maxrocky.vesta.domain.model.WorkTimeEntity;
import com.maxrocky.vesta.domain.repository.RepairModeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 27978 on 2016/8/8.
 */
@Service
public class RepairModeServiceImpl implements RepairModeService{

    @Autowired
    RepairModeRepository repairModeRepository;

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 获取维修方式列表
    */
    @Override
    public Map<String, String> getRepairModeList(String thirdId) {
        List<RepairModeEntity> list = repairModeRepository.getRepairModeList(thirdId);
        Map<String, String> map = new LinkedHashMap<>();
        if(thirdId!=null){
            if(list != null && !list.isEmpty()){
                for(RepairModeEntity rme : list){
                    map.put(rme.getValue(),rme.getLabel());
                }
            }
        }
        return map;
    }

    @Override
    public Map<String,String> getStandardDate(String repairMode) {
        WorkTimeEntity list = repairModeRepository.getStandardDate(repairMode);
        Map<String, String> map = new LinkedHashMap<>();
        if(list!=null){
            map.put(list.getValue(),list.getLabel());
        }
        return map;
    }
}
