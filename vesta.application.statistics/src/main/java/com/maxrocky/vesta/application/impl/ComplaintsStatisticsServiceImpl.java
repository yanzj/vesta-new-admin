package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.ClickTimesSeachDTO;
import com.maxrocky.vesta.application.DTO.ComplaintsStatisticsDTO;
import com.maxrocky.vesta.application.inf.ComplaintsStatisticsService;
import com.maxrocky.vesta.domain.model.PropertyRepairEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.model.UserSettingEntity;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangBailiang on 2016/2/17.
 * 投诉统计 业务逻辑层实现类
 */
@Service
public class ComplaintsStatisticsServiceImpl implements ComplaintsStatisticsService {

    /**
     * 投诉统计 持久层接口
     */
    @Autowired
    ComplaintsStatisticsRepository complaintsStatisticsRepository;
    /**
     * 报修统计 持久层接口
     */
    @Autowired
    RepairStatisticsRepository repairStatisticsRepository;

    @Autowired
    UserSettingRepository userSettingRepository;

    /**
     * 投诉统计
     *
     * @param clickTimesSeach
     * @param webPage
     * @param userPropertySta
     * @return
     */
    @Override
    public List<ComplaintsStatisticsDTO> complaintsStatistics(ClickTimesSeachDTO clickTimesSeach, WebPage webPage, UserPropertyStaffEntity userPropertySta) {
        String companyName = "";            // 公司名称
        String propertyProject = userPropertySta.getProjectId();  // 项目
        String propertyArea = "";           // 区域
        String taskStatus = "1";            // 状态 1 投诉

        List<PropertyRepairEntity> repairs = repairStatisticsRepository.getPropertyRepairList(propertyProject,taskStatus);
        List<ComplaintsStatisticsDTO> complaintsStatistics = new ArrayList<>();
        Integer complaintsNumber = 0;//投诉数量
        Integer feedbackNumber = 0;//反馈数量
        ComplaintsStatisticsDTO repairStatis = new ComplaintsStatisticsDTO();

        if (repairs.size() > 0) {
            // 所属项目
            repairStatis.setProjectName(repairs.get(0).getRegionName());
            for (int i = 0; i < repairs.size(); i++) {
                // 1：投诉数量
                if (repairs.get(i).getTypes().equals("0")) {
                    complaintsNumber += 1;
                    repairStatis.setComplaintsNumber(complaintsNumber);
                }
                // 16: 反馈数量
                if (repairs.get(i).getTypes().equals("1")) {
                    feedbackNumber += 1;
                    repairStatis.setFeedbackNumber(feedbackNumber);
                }
            }
        }else {
            // 根据项目ID 查询项目名称
            List<UserSettingEntity> userSetting = userSettingRepository.userSettingList(propertyProject);
            // 所属项目
            repairStatis.setProjectName(userSetting.get(0).getProjectName());
        }
        complaintsStatistics.add(repairStatis);
        return complaintsStatistics;
    }
}
