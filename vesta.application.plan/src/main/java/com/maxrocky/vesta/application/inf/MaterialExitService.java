package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.jsonDTO.ExitReceiveDTO;
import com.maxrocky.vesta.application.jsonDTO.MaterialExitDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/24.
 */
public interface MaterialExitService {
    //批量新增退场记录
    void addMaterialExit(List<ExitReceiveDTO> exitReceiveDTOs);
    //获取退场记录列表
    List<MaterialExitDTO> getMaterialExits();
}
