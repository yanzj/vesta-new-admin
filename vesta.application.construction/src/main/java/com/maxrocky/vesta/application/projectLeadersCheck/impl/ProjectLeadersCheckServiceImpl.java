package com.maxrocky.vesta.application.projectLeadersCheck.impl;

import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.projectKeyProcesses.DTO.CheckForUpdateDTO;
import com.maxrocky.vesta.application.projectKeyProcesses.DTO.KeyProcessesCheckForUpdateDTO;
import com.maxrocky.vesta.application.projectKeyProcesses.DTO.ProjectKeyProcessesDTO;
import com.maxrocky.vesta.application.projectLeadersCheck.DTO.LeadersCheckImageDTO;
import com.maxrocky.vesta.application.projectLeadersCheck.DTO.ProjectLeaderCheckExcelDTO;
import com.maxrocky.vesta.application.projectLeadersCheck.DTO.ProjectLeadersCheckDTO;
import com.maxrocky.vesta.application.projectLeadersCheck.DTO.ProjectLeadersCheckDetailDTO;
import com.maxrocky.vesta.application.projectLeadersCheck.inf.ProjectLeadersCheckService;
import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.model.ProjectKeyProcessesEntity;
import com.maxrocky.vesta.domain.projectLeadersCheck.model.ProjectLeadersCheckDetailEntity;
import com.maxrocky.vesta.domain.projectLeadersCheck.model.ProjectLeadersCheckEntity;
import com.maxrocky.vesta.domain.projectLeadersCheck.repository.ProjectLeadersCheckRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportExcel;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.util.*;

/**
 * Created by Talent on 2017/1/16.
 */
@Service
public class ProjectLeadersCheckServiceImpl implements ProjectLeadersCheckService {
    @Autowired
    ProjectLeadersCheckRepository projectLeadersCheckRepository;
    @Autowired
    GetAllClassifyService getAllClassifyService;

    @Override
    public List<ProjectLeadersCheckDTO> getLeaderCheckList(ProjectLeadersCheckDTO projectLeadersCheckDTO, WebPage webPage, UserInformationEntity userInformationEntity) {
        Map map = new HashMap();
        map.put("projectId", projectLeadersCheckDTO.getProjectId());//项目id
        map.put("createName", projectLeadersCheckDTO.getCreateName());//检查人名称
        map.put("assignName", projectLeadersCheckDTO.getAssignName());//整改人名称
        map.put("state", projectLeadersCheckDTO.getState());//问题状态
        map.put("startDate", projectLeadersCheckDTO.getStartDate());//开始时间
        map.put("endDate", projectLeadersCheckDTO.getEndDate());//结束时间
        //获取当前人的菜单权限
//        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userPropertystaff.getStaffId());
        List<ProjectLeadersCheckEntity> projectLeadersCheckEntityList = null;
//        if (f) {
//            projectLeadersCheckEntityList = projectLeadersCheckRepository.getLeaderCheckList(map, webPage);
//        } else {
//            projectLeadersCheckEntityList = projectLeadersCheckRepository.getLeaderCheckList(map, webPage, userPropertystaff.getStaffId());
//        }
        if(!StringUtil.isEmpty(projectLeadersCheckDTO.getProjectId())){
            projectLeadersCheckEntityList = projectLeadersCheckRepository.getLeaderCheckList(map, webPage);
        }
        List<ProjectLeadersCheckDTO> projectLeadersCheckDTOList = new ArrayList<ProjectLeadersCheckDTO>();
        if (projectLeadersCheckEntityList != null && projectLeadersCheckEntityList.size() > 0) {
            for (ProjectLeadersCheckEntity projectLeadersCheckEntity : projectLeadersCheckEntityList) {
                ProjectLeadersCheckDTO leadersCheckDTO = new ProjectLeadersCheckDTO();
                leadersCheckDTO.setCheckId(projectLeadersCheckEntity.getCheckId());//id
                leadersCheckDTO.setProjectId(projectLeadersCheckEntity.getProjectId());//项目ID
                leadersCheckDTO.setProjectName(projectLeadersCheckEntity.getProjectName());//项目名称
                leadersCheckDTO.setCreateName(projectLeadersCheckEntity.getCreateName());//检查人
                leadersCheckDTO.setAssignName(projectLeadersCheckEntity.getAssignName());//整改人
                leadersCheckDTO.setCreateDate(DateUtils.format(projectLeadersCheckEntity.getCreateDate(), DateUtils.FORMAT_LONG));//创建时间
                if (projectLeadersCheckEntity.getState().equals("AbnormalShutdown")) {
                    leadersCheckDTO.setState("非正常关闭");//问题状态
                } else {
                    leadersCheckDTO.setState(projectLeadersCheckEntity.getState());//问题状态
                }
                projectLeadersCheckDTOList.add(leadersCheckDTO);
            }
        }
        return projectLeadersCheckDTOList;
    }

    @Override
    public ProjectLeadersCheckDTO getLeaderCheckDetailByCheckId(String checkId) {
        ProjectLeadersCheckEntity leadersCheckEntity = projectLeadersCheckRepository.getLeaderCheckById(checkId);
        ProjectLeadersCheckDTO leadersCheck = new ProjectLeadersCheckDTO();
        if (leadersCheckEntity != null) {
            leadersCheck.setId(leadersCheckEntity.getId().toString());//自增长ID
            leadersCheck.setAppId(leadersCheckEntity.getAppId()); //唯一校验，防止重复
            leadersCheck.setCheckId(leadersCheckEntity.getCheckId());//检查ID
            leadersCheck.setTitle(DateUtils.format(leadersCheckEntity.getCreateDate(), "MM月dd日") + "-" + leadersCheckEntity.getProjectName());
            leadersCheck.setProjectId(leadersCheckEntity.getProjectId());//项目ID
            leadersCheck.setProjectName(leadersCheckEntity.getProjectName());//项目名称
            leadersCheck.setAssignId(leadersCheckEntity.getAssignId());//整改人ID
            leadersCheck.setAssignName(leadersCheckEntity.getAssignName());//整改人名称
            leadersCheck.setDealPeople(leadersCheckEntity.getAssignId());//处理人ID
            leadersCheck.setCreateBy(leadersCheckEntity.getCreateBy());//创建人ID
            leadersCheck.setCreateName(leadersCheckEntity.getCreateName());//创建人名称
            leadersCheck.setCreateDate(DateUtils.format(leadersCheckEntity.getCreateDate(), DateUtils.FORMAT_LONG));//创建时间
            leadersCheck.setModifyBy(leadersCheckEntity.getModifyBy());//修改人Id
            leadersCheck.setModifyName(leadersCheckEntity.getAssignName());//修改人名称
            leadersCheck.setModifyDate(DateUtils.format(leadersCheckEntity.getModifyDate(), DateUtils.FORMAT_LONG));//修改时间
//            leadersCheck.setState(leadersCheckEntity.getState());//状态
            if (leadersCheckEntity.getState().equals("AbnormalShutdown")) {
                leadersCheck.setState("非正常关闭");//问题状态
            } else {
                leadersCheck.setState(leadersCheckEntity.getState());//问题状态
            }
            leadersCheck.setShutDown(leadersCheckEntity.getShutDown());//关单原因
            leadersCheck.setShutDownBy(leadersCheckEntity.getShutDownBy());//关单人
            leadersCheck.setShutDownOn(DateUtils.format(leadersCheckEntity.getShutDownOn(), DateUtils.FORMAT_LONG));//关单时间
            //详情
            List<ProjectLeadersCheckDetailEntity> leadersCheckDetailList = projectLeadersCheckRepository.getListByCheckId(leadersCheckEntity.getCheckId());
            if (leadersCheckDetailList != null && leadersCheckDetailList.size() > 0) {
                List<ProjectLeadersCheckDetailDTO> leaderList = new ArrayList<ProjectLeadersCheckDetailDTO>();//领导详情
                List<ProjectLeadersCheckDetailDTO> managerList = new ArrayList<ProjectLeadersCheckDetailDTO>();//经理详情
                for (ProjectLeadersCheckDetailEntity leadersCheckDetail : leadersCheckDetailList) {
                    ProjectLeadersCheckDetailDTO leadersCheckDetailDTO = new ProjectLeadersCheckDetailDTO();
                    leadersCheckDetailDTO.setId(leadersCheckDetail.getId());//详情ID
                    leadersCheckDetailDTO.setCheckId(leadersCheckDetail.getCheckId());//检查ID
                    leadersCheckDetailDTO.setDescription(leadersCheckDetail.getDescription());//描述
                    leadersCheckDetailDTO.setCreateBy(leadersCheckDetail.getCreateBy());//创建人
                    leadersCheckDetailDTO.setCreateOn(DateUtils.format(leadersCheckDetail.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
                    leadersCheckDetailDTO.setType(leadersCheckDetail.getType());//0：项目经理
                    leadersCheckDetailDTO.setSerialNumber(leadersCheckDetail.getSerialNumber());//排序号
                    //图片
                    List<ProjectImagesEntity> projectImagesList = projectLeadersCheckRepository.getProjectImages(leadersCheckDetail.getId());
                    if (projectImagesList != null) {
                        List<LeadersCheckImageDTO> imageList = new ArrayList<LeadersCheckImageDTO>();
                        for (ProjectImagesEntity projectImagesEntity : projectImagesList) {
                            LeadersCheckImageDTO getImage = new LeadersCheckImageDTO();
                            getImage.setId(projectImagesEntity.getId());
                            getImage.setBusinessId(projectImagesEntity.getBusinessId());//业务id
                            getImage.setImageUrl(projectImagesEntity.getUrl());//图片地址
                            getImage.setType(projectImagesEntity.getType());//类型 1:日常巡检；2检查验收；3：关键工序；4：样板点评；5：材料验收；6：旁站
                            getImage.setState(projectImagesEntity.getState());//状态 0:不可用；1：可用
                            getImage.setCreateOn(DateUtils.format(projectImagesEntity.getCreateOn()));//创建时间
                            getImage.setModifyOn(DateUtils.format(projectImagesEntity.getModifyOn()));//修改时间
                            getImage.setQualifiedState(projectImagesEntity.getQualifiedState());//合格 不合格
                            imageList.add(getImage);
                        }
                        leadersCheckDetailDTO.setImageList(imageList);
                    }
                    if (leadersCheckDetailDTO.getType().equals("1")) {
                        leaderList.add(leadersCheckDetailDTO);
                    } else {
                        managerList.add(leadersCheckDetailDTO);
                    }
                }
                leadersCheck.setLeaderList(leaderList);
                leadersCheck.setManagerList(managerList);
            }
        }
        return leadersCheck;
    }

    @Override
    public String executeAbnormalOffState(ProjectLeadersCheckDTO projectLeadersCheckDTO,UserInformationEntity userInformationEntity) {
        String resultMessage = "";
        ProjectLeadersCheckEntity leadersCheckEntity = projectLeadersCheckRepository.getLeaderCheckById(projectLeadersCheckDTO.getCheckId());
        if (leadersCheckEntity != null) {
            leadersCheckEntity.setShutDown(projectLeadersCheckDTO.getShutDown());//关单原因
            leadersCheckEntity.setShutDownOn(new Date());//关单时间
            leadersCheckEntity.setShutDownBy(userInformationEntity.getStaffName());//关单人

            leadersCheckEntity.setState("AbnormalShutdown");
            leadersCheckEntity.setModifyName(userInformationEntity.getStaffName());
            leadersCheckEntity.setModifyDate(new Date());
            boolean f = projectLeadersCheckRepository.modifyLeaderCheck(leadersCheckEntity);
            if (f) {
                resultMessage = "0";//成功
            } else {
                resultMessage = "1";//失败
            }
        }
        return resultMessage;
    }

    @Override
    public void exportExcel(String title, String[] headers, ServletOutputStream out, ProjectLeadersCheckDTO projectLeadersCheckDTO, UserInformationEntity userInformationEntity) {
        Map map = new HashMap();
        map.put("projectId", projectLeadersCheckDTO.getProjectId());//项目id
        map.put("createName", projectLeadersCheckDTO.getCreateName());//检查人名称
        map.put("assignName", projectLeadersCheckDTO.getAssignName());//整改人名称
        map.put("state", projectLeadersCheckDTO.getState());//问题状态
        map.put("startDate", projectLeadersCheckDTO.getStartDate());//开始时间
        map.put("endDate", projectLeadersCheckDTO.getEndDate());//结束时间
        //获取当前人的菜单权限
//        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userPropertystaffEntity.getStaffId());
        List<ProjectLeadersCheckEntity> projectLeadersCheckEntityList = null;
        WebPage webPage = null;
//        if (f) {
//            projectLeadersCheckEntityList = projectLeadersCheckRepository.getLeaderCheckList(map, webPage);
//        } else {
//            projectLeadersCheckEntityList = projectLeadersCheckRepository.getLeaderCheckList(map, webPage, userPropertystaffEntity.getStaffId());
//        }
        if(!StringUtil.isEmpty(projectLeadersCheckDTO.getProjectId())){
            projectLeadersCheckEntityList = projectLeadersCheckRepository.getLeaderCheckList(map, webPage);
        }
        List<ProjectLeaderCheckExcelDTO> projectLeaderCheckExcelDTOs = new ArrayList<ProjectLeaderCheckExcelDTO>();
        if (projectLeadersCheckEntityList != null && projectLeadersCheckEntityList.size() > 0) {
            int num = 1;
            for (ProjectLeadersCheckEntity projectLeadersCheckEntity : projectLeadersCheckEntityList) {
                ProjectLeaderCheckExcelDTO projectLeaderCheckExcelDTO = new ProjectLeaderCheckExcelDTO();
                projectLeaderCheckExcelDTO.setSerialNumber(num);
                projectLeaderCheckExcelDTO.setProjectName(projectLeadersCheckEntity.getProjectName());//项目名称
                projectLeaderCheckExcelDTO.setCreateName(projectLeadersCheckEntity.getCreateName());//检查人
                projectLeaderCheckExcelDTO.setCreateDate(DateUtils.format(projectLeadersCheckEntity.getCreateDate(), DateUtils.FORMAT_LONG));//创建时间
                projectLeaderCheckExcelDTO.setAssignName(projectLeadersCheckEntity.getAssignName());//整改人
                if (projectLeadersCheckEntity.getState().equals("AbnormalShutdown")) {
                    projectLeaderCheckExcelDTO.setState("非正常关闭");//问题状态
                } else {
                    projectLeaderCheckExcelDTO.setState(projectLeadersCheckEntity.getState());//问题状态
                }
                projectLeaderCheckExcelDTOs.add(projectLeaderCheckExcelDTO);
                num++;
            }
        }
        // 导出数据
        ExportExcel<ProjectLeaderCheckExcelDTO> ex = new ExportExcel<ProjectLeaderCheckExcelDTO>();
        ex.exportExcel(title, headers, projectLeaderCheckExcelDTOs, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }
}
