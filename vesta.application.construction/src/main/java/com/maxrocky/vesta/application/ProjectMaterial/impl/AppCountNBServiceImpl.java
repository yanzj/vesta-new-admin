package com.maxrocky.vesta.application.ProjectMaterial.impl;

import com.maxrocky.vesta.application.ProjectMaterial.DTO.AppCountNumberDTO;
import com.maxrocky.vesta.application.ProjectMaterial.inf.AppCountNBService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.dailyPatrolInspection.repository.DailyPatrolInspectionRepository;
import com.maxrocky.vesta.domain.inspectAcceptance.repository.InspectAcceptanceRepository;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.repository.ProjectKeyProcessesRepository;
import com.maxrocky.vesta.domain.projectMaterial.repository.ProjectMaterialRepository;
import com.maxrocky.vesta.domain.projectSampleCheck.repository.ProjectSampleCheckRepository;
import com.maxrocky.vesta.domain.repository.RectificationRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Magic on 2016/11/30.
 */
@Service
public class AppCountNBServiceImpl implements AppCountNBService {
    @Autowired
    RectificationRepository rectificationRepository;

    @Autowired
    DailyPatrolInspectionRepository dailyPatrolInspectionRepository;

    @Autowired
    ProjectMaterialRepository projectMaterialRepository;
    @Autowired
    InspectAcceptanceRepository inspectAcceptanceRepository;
    @Autowired
    ProjectKeyProcessesRepository projectKeyProcessesRepository;
    @Autowired
    ProjectSampleCheckRepository projectSampleCheckRepository;

    /**
     * app待办事项统计列表
     * 只统计整改中
     */
    @Override
    public ApiResult getAppCountNB(AppCountNumberDTO appCountNumberDTO, UserPropertyStaffEntity userProperty) {
        AppCountNumberDTO getAppCountNB = new AppCountNumberDTO();
        try {
            //日常巡检统计
            if (!StringUtil.isEmpty(appCountNumberDTO.getDailyPatrol()) && "Y".equals(appCountNumberDTO.getDailyPatrol())) {
                int all = dailyPatrolInspectionRepository.inspectionCount(userProperty.getStaffId());
                getAppCountNB.setDailyPatrol(appCountNumberDTO.getDailyPatrol());
                getAppCountNB.setDailyPatrolNB(all + "");
            }
            //检查验收
            if (!StringUtil.isEmpty(appCountNumberDTO.getInspectionAcceptance()) && "Y".equals(appCountNumberDTO.getInspectionAcceptance())) {
                //整改中的总条数
                int rectificationCount = inspectAcceptanceRepository.getRectificationCount(userProperty.getStaffId());
                getAppCountNB.setInspectionAcceptance(appCountNumberDTO.getInspectionAcceptance());
                getAppCountNB.setInspectionAcceptanceNB(rectificationCount + "");
            }
            //样板点评
            if (!StringUtil.isEmpty(appCountNumberDTO.getModelReviews()) && "Y".equals(appCountNumberDTO.getModelReviews())) {
                int sampleCheck=projectSampleCheckRepository.getCountSampleCheck(userProperty.getStaffId());
                getAppCountNB.setModelReviews(appCountNumberDTO.getModelReviews());
                getAppCountNB.setModelReviewsNB(sampleCheck+"");
            }
            //材料验收
            if (!StringUtil.isEmpty(appCountNumberDTO.getMaterialAcceptance()) && "Y".equals(appCountNumberDTO.getMaterialAcceptance())) {
                int all = projectMaterialRepository.coutMaterial(userProperty.getStaffId());
                getAppCountNB.setMaterialAcceptance(appCountNumberDTO.getMaterialAcceptance());
                getAppCountNB.setMaterialAcceptanceNB(all + "");
            }
            //关键工序
            if (!StringUtil.isEmpty(appCountNumberDTO.getKeyWorkingProcedure()) && "Y".equals(appCountNumberDTO.getKeyWorkingProcedure())) {
                int rectificationCount = projectKeyProcessesRepository.getRectificationCount(userProperty.getStaffId());
                getAppCountNB.setKeyWorkingProcedure(appCountNumberDTO.getKeyWorkingProcedure());
                getAppCountNB.setKeyWorkingProcedureNB(rectificationCount + "");
            }
            //统计报表
            if (!StringUtil.isEmpty(appCountNumberDTO.getStatisticalReport()) && "Y".equals(appCountNumberDTO.getStatisticalReport())) {

                getAppCountNB.setStatisticalReport(appCountNumberDTO.getStatisticalReport());
                getAppCountNB.setStatisticalReportNB("");
            }
            //交房验房+物业运营
            if (!StringUtil.isEmpty(appCountNumberDTO.getHouseInspection()) && "Y".equals(appCountNumberDTO.getHouseInspection())) {
                int repair=rectificationRepository.getRepairCountById(userProperty.getStaffId());
                int recfiy=rectificationRepository.getRectifyCountById(userProperty.getStaffId());
                getAppCountNB.setHouseInspection(appCountNumberDTO.getHouseInspection());
                getAppCountNB.setHouseInspectionNB(repair+recfiy+"");

            }
            return new SuccessApiResult(getAppCountNB);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }

    }
}
