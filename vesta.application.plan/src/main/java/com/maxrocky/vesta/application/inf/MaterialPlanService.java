package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.jsonDTO.MaterialAllDTO;
import com.maxrocky.vesta.application.jsonDTO.MaterialReceiveDTO;

import java.text.ParseException;
import java.util.List;

/**
 * Created by chen on 2016/5/23.
 */
public interface MaterialPlanService {
    //批量新增验收记录
    void addMaterialPlan(List<MaterialReceiveDTO> materialReceiveDTOs) throws ParseException;
    //获取验收记录列表
    List<MaterialAllDTO> getMaterialPlanList();
}
