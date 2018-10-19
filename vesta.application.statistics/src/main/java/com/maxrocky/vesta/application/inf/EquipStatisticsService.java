package com.maxrocky.vesta.application.inf;


import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.EquipStatisticsDTO;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by sunmei on 2016/2/17.
 */
public interface EquipStatisticsService {
    List<EquipStatisticsDTO> EquipManage(ClickTimesSeachDTO clickTimesSeachDTO,  WebPage webPage);

    //int getEquipManageNums (EquipStatisticsDTO equipStatisticsDTO);

    /**
     *
     * @param projectId 项目
     * @param companyId 公司
     * @param regionId 区域
     * @param type 设备类型
     * @param userId 用户id
     */
    void addEquipManage(String projectId,String companyId,String regionId,String type,String userId);
}
