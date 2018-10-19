package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.RepairStatisticsDTO;
import com.maxrocky.vesta.application.inf.RepairStatisticsService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.RepairStatisticsRepository;
import com.maxrocky.vesta.domain.repository.UserSettingRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 报修统计 业务逻辑层实现类
 */
@Service
public class RepairStatisticsServiceImpl implements RepairStatisticsService {

    /**
     * 报修统计 持久层接口
     */
    @Autowired
    RepairStatisticsRepository repairStatisticsRepository;

    @Autowired
    UserSettingRepository userSettingRepository;

    @Override
    public List<RepairStatisticsDTO> repairStatistics(ClickTimesSeachDTO clickTimesSeach, WebPage webPage, UserPropertyStaffEntity userPropertystaff) {
        String companyName = "";            // 公司名称
        String propertyProject = userPropertystaff.getProjectId();  // 项目
        String propertyArea = "";           // 区域
        String taskStatus = "0";            // 状态 0 维修
        List<PropertyRepairEntity> repairs = repairStatisticsRepository.getPropertyRepairList(propertyProject,taskStatus);
        List<RepairStatisticsDTO> repairStatistics = new ArrayList<>();
        RepairStatisticsDTO repairStatis = new RepairStatisticsDTO();
        if(repairs.size() > 0){
            // 所属项目
            repairStatis.setProjectName(repairs.get(0).getRegionName());
            // 报修数量
            repairStatis.setRepairNumber(repairs.size());
            Integer noSolvedNumber = 0;//未解决数量
            Integer yesSolvedNumber = 0;//解决数量
            for(int i = 0; i < repairs.size();i++){
                // 0：未完成报修;
                if (repairs.get(i).getTypes().equals("0")) {
                    noSolvedNumber += 1;
                    repairStatis.setNoSolvedNumber(noSolvedNumber);
                }
                // 1：已完成报修
                if (repairs.get(i).getTypes().equals("1")) {
                    yesSolvedNumber += 1;
                    repairStatis.setYesSolvedNumber(yesSolvedNumber);
                }
            }
        }else {
            // 根据项目ID 查询项目名称
            List<UserSettingEntity> userSetting = userSettingRepository.userSettingList(propertyProject);
            // 所属项目
            repairStatis.setProjectName(userSetting.get(0).getProjectName());
        }
        repairStatistics.add(repairStatis);
        return repairStatistics;
    }
}
