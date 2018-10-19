package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.AdminDTO.RecodeAdminDTO;
import com.maxrocky.vesta.application.JsonDTO.RecodeDTO;

import java.util.List;

/**
 * Created by chen on 2016/5/24.
 */
public interface StandRecodeService {
    //新增旁站记录
    void addStandRecode(RecodeAdminDTO recodeAdminDTO);
    //APP批量新增旁站记录
    void addRecodes(List<RecodeDTO> recodeDTOs);
    //更新旁站记录
    void updateRecode(RecodeAdminDTO recodeAdminDTO);

}
