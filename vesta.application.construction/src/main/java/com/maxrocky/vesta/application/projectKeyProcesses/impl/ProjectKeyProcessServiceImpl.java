package com.maxrocky.vesta.application.projectKeyProcesses.impl;

import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.projectKeyProcesses.DTO.*;
import com.maxrocky.vesta.application.projectKeyProcesses.inf.ProjectKeyProcessService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.ProjectBuildingEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectCategoryEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectProjectEntity;
import com.maxrocky.vesta.domain.baseData.repository.*;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyDetailsEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.repository.DailyPatrolInspectionRepository;
import com.maxrocky.vesta.domain.inspectAcceptance.repository.InspectAcceptanceRepository;
import com.maxrocky.vesta.domain.model.AgencyEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.model.ProjectKeyProcessesEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.model.ProjectKeyProcessesTargetDetailsEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.model.ProjectKeyProcessesTargetEntity;
import com.maxrocky.vesta.domain.projectKeyProcesses.repository.ProjectKeyProcessesRepository;
import com.maxrocky.vesta.domain.repository.AgencyRepository;
import com.maxrocky.vesta.domain.repository.RectificationRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportExcel;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletOutputStream;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by Talent on 2016/11/22.
 */
@Service
public class ProjectKeyProcessServiceImpl implements ProjectKeyProcessService {
    @Autowired
    private ProjectKeyProcessesRepository projectKeyProcessesRepository;
    @Autowired
    private InspectAcceptanceRepository inspectAcceptanceRepository;
    @Autowired
    RectificationRepository rectificationRepository;
    @Autowired
    ProjectProjectRepository projectProjectRepository;
    @Autowired
    ProjectBuildingRepository projectBuildingRepository;
    @Autowired
    private ProjectImagesRepository projectImagesRepository;
    @Autowired
    DailyPatrolInspectionRepository dailyPatrolInspectionRepository;
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    StaffEmployRepository staffEmployRepository;
    @Autowired
    ProjectCategoryRepository projectCategoryRepository;
    @Autowired
    GetAllClassifyService getAllClassifyService;

    @Override
    public KeyProcessesBackDTO getKeyProcessesBackInfoByProcessId(String processId) {
        if (!StringUtil.isEmpty(processId)) {
            ProjectKeyProcessesEntity projectKeyProcessesEntity = projectKeyProcessesRepository.getKeyProcessesInfoByProcessId(processId);
            if (projectKeyProcessesEntity != null) {
                KeyProcessesBackDTO keyProcessesBackDTO = new KeyProcessesBackDTO();
                keyProcessesBackDTO.setAppId(projectKeyProcessesEntity.getAppId() == null ? "" : projectKeyProcessesEntity.getAppId());//APPID
                keyProcessesBackDTO.setProcessId(projectKeyProcessesEntity.getProcessId() == null ? "" : projectKeyProcessesEntity.getProcessId());//工序ID
                keyProcessesBackDTO.setProcessName(projectKeyProcessesEntity.getProcessName() == null ? "" : projectKeyProcessesEntity.getProcessName());//工序名称
                keyProcessesBackDTO.setProjectId(projectKeyProcessesEntity.getProjectId() == null ? "" : projectKeyProcessesEntity.getProjectId());//项目ID
                keyProcessesBackDTO.setProjectName(projectKeyProcessesEntity.getProjectName() == null ? "" : projectKeyProcessesEntity.getProjectName());//项目名称
                keyProcessesBackDTO.setBuildingId(projectKeyProcessesEntity.getBuildingId() == null ? "" : projectKeyProcessesEntity.getBuildingId());//楼栋ID
                keyProcessesBackDTO.setBuildingName(projectKeyProcessesEntity.getBuildingName() == null ? "" : projectKeyProcessesEntity.getBuildingName());//楼栋名称
                keyProcessesBackDTO.setFloorStar(projectKeyProcessesEntity.getFloorStar() == null ? "" : projectKeyProcessesEntity.getFloorStar());//开始楼层
                keyProcessesBackDTO.setFloorEnd(projectKeyProcessesEntity.getFloorEnd() == null ? "" : projectKeyProcessesEntity.getFloorEnd());//结束楼层
                keyProcessesBackDTO.setSerial(projectKeyProcessesEntity.getSerial() == null ? "" : projectKeyProcessesEntity.getSerial());//流水段
                keyProcessesBackDTO.setSeverityRating(projectKeyProcessesEntity.getSeverityRating() == null ? "" : projectKeyProcessesEntity.getSeverityRating());//严重等级
                keyProcessesBackDTO.setExaminationDate(projectKeyProcessesEntity.getExaminationDate() == null ? "" : DateUtils.format(projectKeyProcessesEntity.getExaminationDate(), DateUtils.FORMAT_LONG));//检查时间
                keyProcessesBackDTO.setSupervisorId(projectKeyProcessesEntity.getSupervisorId() == null ? "" : projectKeyProcessesEntity.getSupervisorId());//第三方监理ID
                keyProcessesBackDTO.setSupervisorName(projectKeyProcessesEntity.getSupervisorName() == null ? "" : projectKeyProcessesEntity.getSupervisorName());//第三方监理名称
                keyProcessesBackDTO.setSupplierId(projectKeyProcessesEntity.getSupplierId() == null ? "" : projectKeyProcessesEntity.getSupplierId());//责任单位id
                keyProcessesBackDTO.setSupplierName(projectKeyProcessesEntity.getSupplierName() == null ? "" : projectKeyProcessesEntity.getSupplierName());//责任单位名称
                keyProcessesBackDTO.setAssignId(projectKeyProcessesEntity.getAssignId() == null ? "" : projectKeyProcessesEntity.getAssignId());//整改人ID
                keyProcessesBackDTO.setAssignName(projectKeyProcessesEntity.getAssignName() == null ? "" : projectKeyProcessesEntity.getAssignName());//整改人名称
                keyProcessesBackDTO.setCompleteOn(projectKeyProcessesEntity.getCreateOn() == null ? "" : DateUtils.format(projectKeyProcessesEntity.getCreateOn(), DateUtils.FORMAT_SHORT));//完成期限
                keyProcessesBackDTO.setQualifiedState(projectKeyProcessesEntity.getQualifiedState() == null ? "" : projectKeyProcessesEntity.getQualifiedState());//工序合格状态
                keyProcessesBackDTO.setHandlePeopleId(projectKeyProcessesEntity.getHandlePeopleId() == null ? "" : projectKeyProcessesEntity.getHandlePeopleId());//处理人
                keyProcessesBackDTO.setFirstSort(projectKeyProcessesEntity.getFirstSort() == null ? "" : projectKeyProcessesEntity.getFirstSort());//一级分类
                keyProcessesBackDTO.setFirstSortName(projectKeyProcessesEntity.getFirstSortName() == null ? "" : projectKeyProcessesEntity.getFirstSortName());//一级分类名称
                keyProcessesBackDTO.setSecondSort(projectKeyProcessesEntity.getSecondSort() == null ? "" : projectKeyProcessesEntity.getSecondSort());//二级分类
                keyProcessesBackDTO.setSecondSortName(projectKeyProcessesEntity.getSecondSortName() == null ? "" : projectKeyProcessesEntity.getSecondSortName());//二级分类名称
                keyProcessesBackDTO.setThreeSort(projectKeyProcessesEntity.getThreeSort() == null ? "" : projectKeyProcessesEntity.getThreeSort());//三级分类
                keyProcessesBackDTO.setThreeSortName(projectKeyProcessesEntity.getThreeSortName() == null ? "" : projectKeyProcessesEntity.getThreeSortName());//三级分类名称
                keyProcessesBackDTO.setFourSort(projectKeyProcessesEntity.getFourSort() == null ? "" : projectKeyProcessesEntity.getFourSort());//四级分类
                keyProcessesBackDTO.setFourSortName(projectKeyProcessesEntity.getFourSortName() == null ? "" : projectKeyProcessesEntity.getFourSortName());//四级分类名称
                keyProcessesBackDTO.setState(projectKeyProcessesEntity.getState() == null ? "" : projectKeyProcessesEntity.getState());//问题状态
                keyProcessesBackDTO.setCreateDate(projectKeyProcessesEntity.getCreateOn() == null ? "" : DateUtils.format(projectKeyProcessesEntity.getCreateOn()));//创建时间
                keyProcessesBackDTO.setModifyDate(projectKeyProcessesEntity.getModifyOn() == null ? "" : DateUtils.format(projectKeyProcessesEntity.getModifyOn()));//修改时间
                //抄送人
                List<Object[]> idList = dailyPatrolInspectionRepository.getProjectCopy(projectKeyProcessesEntity.getProcessId());//抄送人list
                List<KeyProcessesCopyDTO> copyDetailsList = new ArrayList<KeyProcessesCopyDTO>();
                if (idList != null) {
                    for (Object[] idObj : idList) {
                        KeyProcessesCopyDTO keyProcessesCopyDTO = new KeyProcessesCopyDTO();
                        keyProcessesCopyDTO.setId(idObj[1] == null ? "" : idObj[1].toString());//抄送人ID
                        keyProcessesCopyDTO.setName(idObj[2] == null ? "" : idObj[2].toString());//抄送人名称
                        copyDetailsList.add(keyProcessesCopyDTO);
                    }
                }
                keyProcessesBackDTO.setCopyDTOList(copyDetailsList);

                //工序指标信息
                List<Object[]> targetList = projectKeyProcessesRepository.getKeyProcessesTargetListByProcessId(projectKeyProcessesEntity.getProcessId());
                List<KeyProcessesTargetBackDTO> keyProcessesTargetBackDTOList = new ArrayList<KeyProcessesTargetBackDTO>();
                if (targetList != null && targetList.size() > 0) {
                    for (Object[] target : targetList) {
                        KeyProcessesTargetBackDTO keyProcessesTargetBackDTO = new KeyProcessesTargetBackDTO();
                        keyProcessesTargetBackDTO.setProcessId(target[0] == null ? "" : (String) target[0]);//工序ID
                        keyProcessesTargetBackDTO.setId(target[1] == null ? "" : (String) target[1]);//工序指标ID
                        keyProcessesTargetBackDTO.setTargetId(target[2] == null ? "" : (String) target[2]);//指标ID
                        keyProcessesTargetBackDTO.setTargetName(target[3] == null ? "" : (String) target[3]);//指标名称
                        keyProcessesTargetBackDTO.setQualifiedState(target[4] == null ? "" : (String) target[4]);//指标状态
                        keyProcessesTargetBackDTO.setDescription(target[5] == null ? "" : (String) target[5]);//指标描述
                        keyProcessesTargetBackDTO.setImageUrl(target[6] == null ? "" : (String) target[6]);//指标图片
                        keyProcessesTargetBackDTO.setFlag(target[7] == null ? "" : (String) target[7]);//指标标识
                        //指标的整改验收记录
                        List<Object[]> detailList = projectKeyProcessesRepository.getKeyProcessesTargetDetailListByProcessId((String) target[1]);
                        if (detailList != null && detailList.size() > 0) {
                            List<KeyProcessesTargetDetailsBackDTO> keyProcessesTargetDetailsBackDTOs = new ArrayList<KeyProcessesTargetDetailsBackDTO>();
                            for (Object[] detail : detailList) {
                                KeyProcessesTargetDetailsBackDTO keyProcessesTargetDetailsBackDTO = new KeyProcessesTargetDetailsBackDTO();
                                keyProcessesTargetDetailsBackDTO.setId(detail[0] == null ? "" : (String) detail[0]);//详情ID
                                keyProcessesTargetDetailsBackDTO.setProcessTargetId(detail[1] == null ? "" : (String) detail[1]);//工序指标ID
                                keyProcessesTargetDetailsBackDTO.setDescription(detail[2] == null ? "" : (String) detail[2]);//描述
                                keyProcessesTargetDetailsBackDTO.setChangeTime(detail[3] == null ? "" : (String) detail[3]);//整改时间
                                keyProcessesTargetDetailsBackDTO.setImageUrl(detail[4] == null ? "" : (String) detail[4]);//图片
                                keyProcessesTargetDetailsBackDTO.setType(detail[5] == null ? "" : (String) detail[5]);//类型
                                keyProcessesTargetDetailsBackDTO.setState(detail[6] == null ? "" : (String) detail[6]);//状态
                                keyProcessesTargetDetailsBackDTO.setSerialNumber(detail[7] == null ? "" : (String) detail[7]);//排序号

                                keyProcessesTargetDetailsBackDTOs.add(keyProcessesTargetDetailsBackDTO);
                            }
                            keyProcessesTargetBackDTO.setTargetDetailsBackDTOList(keyProcessesTargetDetailsBackDTOs);
                        }
                        keyProcessesTargetBackDTOList.add(keyProcessesTargetBackDTO);
                    }
                }
                keyProcessesBackDTO.setTargetBackDTOList(keyProcessesTargetBackDTOList);
                return keyProcessesBackDTO;
            }
        }
        return null;
    }

    /**
     * 新增关键工序信息
     *
     * @param keyProcessesApplyListDTO
     * @return
     */
    @Override
    public ApiResult addKeyProcesses(KeyProcessesApplyListDTO keyProcessesApplyListDTO, UserPropertyStaffEntity userPropertyStaffEntity) {
        if (keyProcessesApplyListDTO.getList() == null && keyProcessesApplyListDTO.getList().size() == 0) {
            return new ErrorApiResult("tip_00000054");
        }
        try {
            KeyProcessesBackListDTO keyProcessesBackListDTO = new KeyProcessesBackListDTO();
            List<KeyProcessesBackDTO> keyProcessesBackDTOs = new ArrayList<KeyProcessesBackDTO>();
            for (KeyProcessesApplyDTO keyProcessesApplyDTO : keyProcessesApplyListDTO.getList()) {
                if (StringUtil.isEmpty(keyProcessesApplyDTO.getProjectId())) {
                    return ErrorResource.getError("tip_00000572");//项目编码不能为空
                }
                if (StringUtil.isEmpty(keyProcessesApplyDTO.getBuildingId())) {
                    return ErrorResource.getError("tip_00000575");//楼栋编码不存在
                }
                if (StringUtil.isEmpty(keyProcessesApplyDTO.getProcessName())) {
                    return ErrorResource.getError("tip_00000580");//批次名称不存在
                }
//                if (StringUtil.isEmpty(keyProcessesApplyDTO.getSeverityRating())) {
//                    return ErrorResource.getError("tip_00000574");//严重等级不存在
//                }
                if (StringUtil.isEmpty(keyProcessesApplyDTO.getFourSortName())) {
                    return ErrorResource.getError("tip_00000586");//检查项不存在
                }
                if (StringUtil.isEmpty(keyProcessesApplyDTO.getSupervisorId())) {
                    return ErrorResource.getError("tip_00000587");//监理信息不存在
                }
                if (StringUtil.isEmpty(keyProcessesApplyDTO.getSupplierId())) {
                    return ErrorResource.getError("tip_00000578");//责任单位不存在
                }
                if (StringUtil.isEmpty(keyProcessesApplyDTO.getAssignId())) {
                    return ErrorResource.getError("tip_00000579");//整改人不存在
                }
                if (StringUtil.isEmpty(keyProcessesApplyDTO.getAppId())) {
                    continue;
                }
                ProjectKeyProcessesEntity projectKeyProcessesEntity = projectKeyProcessesRepository.getKeyProcessesInfoByAppId(keyProcessesApplyDTO.getAppId());
                if (projectKeyProcessesEntity != null) {
                    KeyProcessesBackDTO keyProcessesBackDTO = getKeyProcessesBackInfoByProcessId(projectKeyProcessesEntity.getProcessId());
                    keyProcessesBackDTOs.add(keyProcessesBackDTO);
                    continue;
                }
                ProjectKeyProcessesEntity keyProcessesEntity = new ProjectKeyProcessesEntity();
                keyProcessesEntity.setProcessId(IdGen.uuid());//工序ID
                keyProcessesEntity.setAppId(keyProcessesApplyDTO.getAppId());//APPID
                keyProcessesEntity.setProcessName(keyProcessesApplyDTO.getProcessName());//工序名称
                keyProcessesEntity.setProjectId(keyProcessesApplyDTO.getProjectId());//项目ID
                if (StringUtil.isEmpty(keyProcessesApplyDTO.getProjectName())) {
                    ProjectProjectEntity projectProjectEntity = projectProjectRepository.getProjectProjectDetail(keyProcessesApplyDTO.getProjectId());
                    if (projectProjectEntity != null) {
                        keyProcessesEntity.setProjectName(projectProjectEntity.getName());
                    }
                } else {
                    keyProcessesEntity.setProjectName(keyProcessesApplyDTO.getProjectName());//项目名称
                }
                keyProcessesEntity.setBuildingId(keyProcessesApplyDTO.getBuildingId());//楼栋ID
                if (StringUtil.isEmpty(keyProcessesApplyDTO.getBuildingName())) {
                    ProjectBuildingEntity projectBuildingEntity = projectBuildingRepository.getBuildDetail(keyProcessesApplyDTO.getBuildingId());
                    if (projectBuildingEntity != null) {
                        keyProcessesEntity.setBuildingName(projectBuildingEntity.getName());
                    }
                } else {
                    keyProcessesEntity.setBuildingName(keyProcessesApplyDTO.getBuildingName());//楼栋名称
                }
                //一级分类
                if (!StringUtil.isEmpty(keyProcessesApplyDTO.getFirstSort())) {
                    keyProcessesEntity.setFirstSort(keyProcessesApplyDTO.getFirstSort());//一级分类
                    if (!StringUtil.isEmpty(keyProcessesApplyDTO.getFirstSortName())) {
                        keyProcessesEntity.setFirstSortName(keyProcessesApplyDTO.getFirstSortName());//一级分类名称
                    } else {
                        ProjectCategoryEntity projectCategoryEntity = projectCategoryRepository.getCategoryDetail(keyProcessesApplyDTO.getFirstSort());
                        if (projectCategoryEntity != null) {
                            keyProcessesEntity.setFirstSortName(projectCategoryEntity.getCategoryName());//一级分类名称
                        }
                    }
                }
                //二级分类
                if (!StringUtil.isEmpty(keyProcessesApplyDTO.getSecondSort())) {
                    keyProcessesEntity.setSecondSort(keyProcessesApplyDTO.getSecondSort());//二级分类
                    if (!StringUtil.isEmpty(keyProcessesApplyDTO.getSecondSortName())) {
                        keyProcessesEntity.setSecondSortName(keyProcessesApplyDTO.getSecondSortName());//二级分类名称
                    } else {
                        ProjectCategoryEntity projectCategoryEntity = projectCategoryRepository.getCategoryDetail(keyProcessesApplyDTO.getSecondSort());
                        if (projectCategoryEntity != null) {
                            keyProcessesEntity.setSecondSortName(projectCategoryEntity.getCategoryName());//二级分类名称
                        }
                    }
                }
                //三级分类
                if (!StringUtil.isEmpty(keyProcessesApplyDTO.getThreeSort())) {
                    keyProcessesEntity.setThreeSort(keyProcessesApplyDTO.getThreeSort());//三级分类
                    if (!StringUtil.isEmpty(keyProcessesApplyDTO.getThreeSortName())) {
                        keyProcessesEntity.setThreeSortName(keyProcessesApplyDTO.getThreeSortName());//三级分类名称
                    } else {
                        ProjectCategoryEntity projectCategoryEntity = projectCategoryRepository.getCategoryDetail(keyProcessesApplyDTO.getThreeSort());
                        if (projectCategoryEntity != null) {
                            keyProcessesEntity.setThreeSortName(projectCategoryEntity.getCategoryName());//二级分类名称
                        }
                    }
                }
                //四级分类
                if (!StringUtil.isEmpty(keyProcessesApplyDTO.getFourSort())) {
                    keyProcessesEntity.setFourSort(keyProcessesApplyDTO.getFourSort());//四级分类
                    if (!StringUtil.isEmpty(keyProcessesApplyDTO.getFourSortName())) {
                        keyProcessesEntity.setFourSortName(keyProcessesApplyDTO.getFourSortName());//四级分类名称
                    } else {
                        ProjectCategoryEntity projectCategoryEntity = projectCategoryRepository.getCategoryDetail(keyProcessesApplyDTO.getFourSort());
                        if (projectCategoryEntity != null) {
                            keyProcessesEntity.setFourSortName(projectCategoryEntity.getCategoryName());//二级分类名称
                        }
                    }
                }
                keyProcessesEntity.setExaminationDate(DateUtils.parse(keyProcessesApplyDTO.getExaminationDate(), DateUtils.FORMAT_LONG));//检查时间
                keyProcessesEntity.setFloorStar(keyProcessesApplyDTO.getFloorStar());//开始楼层
                keyProcessesEntity.setFloorEnd(keyProcessesApplyDTO.getFloorEnd());//结束楼层
                keyProcessesEntity.setSerial(keyProcessesApplyDTO.getSerial());//流水段
                keyProcessesEntity.setSeverityRating(keyProcessesApplyDTO.getSeverityRating());//严重等级

                keyProcessesEntity.setPartyPrincipalId(userPropertyStaffEntity.getStaffId());//甲方负责人ID
                keyProcessesEntity.setPartyPrincipalName(userPropertyStaffEntity.getStaffName());//甲方负责人名称

                keyProcessesEntity.setSupervisorId(keyProcessesApplyDTO.getSupervisorId());//第三方监理ID
                if (StringUtil.isEmpty(keyProcessesApplyDTO.getSupervisorName())) {
                    UserPropertyStaffEntity userStaff = rectificationRepository.getusername(keyProcessesApplyDTO.getSupervisorId());
                    if (userStaff != null) {
                        keyProcessesEntity.setSupervisorName(userStaff.getStaffName());//第三方监理名称
                    }
                } else {
                    keyProcessesEntity.setSupervisorName(keyProcessesApplyDTO.getSupervisorName());//第三方监理名称
                }

                keyProcessesEntity.setSupplierId(keyProcessesApplyDTO.getSupplierId());//责任单位ID
                if (StringUtil.isEmpty(keyProcessesApplyDTO.getSupplierName())) {
                    AgencyEntity agency = agencyRepository.getAgencyDetail(keyProcessesApplyDTO.getSupplierId());
                    if (agency != null) {
                        keyProcessesEntity.setSupplierName(agency.getAgencyName());//责任单位名称
                    }
                } else {
                    keyProcessesEntity.setSupplierName(keyProcessesApplyDTO.getSupplierName());//责任单位名称
                }

                keyProcessesEntity.setAssignId(keyProcessesApplyDTO.getAssignId());//整改人ID
                if (StringUtil.isEmpty(keyProcessesApplyDTO.getAssignName())) {
                    UserPropertyStaffEntity userStaff = rectificationRepository.getusername(keyProcessesApplyDTO.getSupervisorId());
                    if (userStaff != null) {
                        keyProcessesEntity.setAssignName(userStaff.getStaffName());
                    }
                } else {
                    keyProcessesEntity.setAssignName(keyProcessesApplyDTO.getAssignName());//整改人名称
                }
                keyProcessesEntity.setCompleteOn(DateUtils.parse(keyProcessesApplyDTO.getCompleteOn(), DateUtils.FORMAT_SHORT));//完成期限

                keyProcessesEntity.setQualifiedState(keyProcessesApplyDTO.getQualifiedState());//合格状态
                if (keyProcessesApplyDTO.getQualifiedState().equals("合格")) {
                    keyProcessesEntity.setState(keyProcessesApplyDTO.getQualifiedState());//状态
                    keyProcessesEntity.setFirstQualifiedState(keyProcessesApplyDTO.getQualifiedState());//一次性合格
                    keyProcessesEntity.setHandlePeopleId(keyProcessesEntity.getPartyPrincipalId());//处理人
                } else {
                    keyProcessesEntity.setState("整改中");
                    keyProcessesEntity.setHandlePeopleId(keyProcessesApplyDTO.getAssignId());
                }
                keyProcessesEntity.setCreateBy(userPropertyStaffEntity.getStaffId());//创建人ID
                keyProcessesEntity.setCreateName(userPropertyStaffEntity.getStaffName());//创建人名称
                keyProcessesEntity.setCreateOn(new Date());//创建时间

                keyProcessesEntity.setModifyBy(userPropertyStaffEntity.getStaffId());//修改人ID
                keyProcessesEntity.setModifyName(userPropertyStaffEntity.getStaffName());//修改人名称
                keyProcessesEntity.setModifyOn(new Date());//修改时间

                //保存工序信息
                boolean key = projectKeyProcessesRepository.addKeyProcesses(keyProcessesEntity);
                if (key) {
                    //保存抄送人
                    if (keyProcessesApplyDTO.getKeyProcessesCopyDTOList() != null && keyProcessesApplyDTO.getKeyProcessesCopyDTOList().size() > 0) {
                        ProjectCopyEntity projectCopyEntity = new ProjectCopyEntity();
                        projectCopyEntity.setId(IdGen.uuid());//抄送人ID
                        projectCopyEntity.setSender(userPropertyStaffEntity.getStaffId());//发送人ID
                        projectCopyEntity.setSenderName(userPropertyStaffEntity.getStaffName());//发送人名称
                        projectCopyEntity.setBusiness(keyProcessesEntity.getProcessId());//工序ID
                        projectCopyEntity.setDamain("3");//所属模块
                        projectCopyEntity.setCreateOn(new Date());//添加时间
                        //保存抄送人
                        boolean copy = inspectAcceptanceRepository.saveProjectCopy(projectCopyEntity);
                        if (copy) {
                            for (KeyProcessesCopyDTO keyProcessesCopyDTO : keyProcessesApplyDTO.getKeyProcessesCopyDTOList()) {
                                ProjectCopyDetailsEntity projectCopyDetailsEntity = new ProjectCopyDetailsEntity();
                                projectCopyDetailsEntity.setId(IdGen.uuid());//详情id
                                projectCopyDetailsEntity.setCopyId(projectCopyEntity.getId());//抄送ID
                                projectCopyDetailsEntity.setMemberId(keyProcessesCopyDTO.getId());//所选的抄送人ID
                                if (StringUtil.isEmpty(keyProcessesCopyDTO.getName())) {
                                    UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(keyProcessesCopyDTO.getId());
                                    if (UserStaff != null) {
                                        projectCopyDetailsEntity.setMemberName(UserStaff.getStaffName());
                                    }
                                } else {
                                    projectCopyDetailsEntity.setMemberName(keyProcessesCopyDTO.getName());
                                }
                                //保存抄送详情
                                inspectAcceptanceRepository.saveProjectCopyDetails(projectCopyDetailsEntity);
                            }
                        }
                    }
                    if (keyProcessesApplyDTO.getKeyProcessesTargetApplyDTOList() != null && keyProcessesApplyDTO.getKeyProcessesTargetApplyDTOList().size() > 0) {
                        for (KeyProcessesTargetApplyDTO keyProcessesTargetApplyDTO : keyProcessesApplyDTO.getKeyProcessesTargetApplyDTOList()) {
                            //工序指标
                            ProjectKeyProcessesTargetEntity projectKeyProcessesTargetEntity = new ProjectKeyProcessesTargetEntity();
                            projectKeyProcessesTargetEntity.setId(IdGen.uuid());//工序指标ID
                            projectKeyProcessesTargetEntity.setProcessId(keyProcessesEntity.getProcessId());//工序ID
                            projectKeyProcessesTargetEntity.setTargetId(keyProcessesTargetApplyDTO.getTargetId());//指标ID
                            projectKeyProcessesTargetEntity.setTargetName(keyProcessesTargetApplyDTO.getTargetName());//指标名称
                            projectKeyProcessesTargetEntity.setQualifiedState(keyProcessesTargetApplyDTO.getQualifiedState());//指标状态
                            projectKeyProcessesTargetEntity.setCreateBy(userPropertyStaffEntity.getStaffId());//创建人
                            projectKeyProcessesTargetEntity.setCreateOn(new Date());//创建时间
                            projectKeyProcessesTargetEntity.setModifyBy(userPropertyStaffEntity.getStaffId());//修改人
                            projectKeyProcessesTargetEntity.setModifyOn(new Date());//修改时间
                            boolean target = projectKeyProcessesRepository.addKeyProcessesTarget(projectKeyProcessesTargetEntity);
                            if (target) {
                                //工序指标详情
                                ProjectKeyProcessesTargetDetailsEntity processesTargetDetailsEntity = new ProjectKeyProcessesTargetDetailsEntity();
                                processesTargetDetailsEntity.setId(IdGen.uuid());//工序详情ID
                                processesTargetDetailsEntity.setProcessTargetId(projectKeyProcessesTargetEntity.getId());//工序指标ID
                                processesTargetDetailsEntity.setDescription(keyProcessesTargetApplyDTO.getDescription());//指标描述
                                processesTargetDetailsEntity.setState(keyProcessesTargetApplyDTO.getQualifiedState());//状态
                                processesTargetDetailsEntity.setSerialNumber(keyProcessesTargetApplyDTO.getSerialNumber());//排序号
                                processesTargetDetailsEntity.setCreateBy(userPropertyStaffEntity.getStaffId());//创建人ID
                                processesTargetDetailsEntity.setCreateOn(new Date());//创建时间
                                boolean detail = projectKeyProcessesRepository.addKeyProcessesTargetDetails(processesTargetDetailsEntity);
                                if (detail) {
                                    //工程图片
                                    ProjectImagesEntity projectImagesEntity = new ProjectImagesEntity();
                                    projectImagesEntity.setId(IdGen.uuid());//图片ID
                                    projectImagesEntity.setBusinessId(processesTargetDetailsEntity.getId());//工序指标详细信息ID
                                    projectImagesEntity.setUrl(keyProcessesTargetApplyDTO.getImageUrl());//图片URL
                                    projectImagesEntity.setType("3");//所属类型：
                                    projectImagesEntity.setState("1");//状态
                                    projectImagesEntity.setCreateOn(new Date());//创建时间
                                    projectImagesEntity.setModifyOn(new Date());//修改时间
                                    //保存图片信息
                                    projectImagesRepository.addProjectImagesInfo(projectImagesEntity);
                                }
                            }
                        }
                    }
                    //返回成功数据
                    KeyProcessesBackDTO keyProcessesBackDTO = getKeyProcessesBackInfoByProcessId(keyProcessesEntity.getProcessId());
                    keyProcessesBackDTOs.add(keyProcessesBackDTO);
                }
            }
            keyProcessesBackListDTO.setList(keyProcessesBackDTOs);
            return new SuccessApiResult(keyProcessesBackListDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * 检查关键工序是否有更新
     *
     * @param checkForUpdateDTO
     * @return
     */
    @Override
    public List<KeyProcessesCheckForUpdateDTO> keyProcessesCheckForUpdate(CheckForUpdateDTO checkForUpdateDTO, UserPropertyStaffEntity userPropertyStaffEntity) {
        List<KeyProcessesCheckForUpdateDTO> keyProcessesCheckForUpdateDTOs = new ArrayList<KeyProcessesCheckForUpdateDTO>();
        if (checkForUpdateDTO.getList() != null && checkForUpdateDTO.getList().size() > 0) {
            for (KeyProcessesCheckForUpdateDTO keyProcessesCheckForUpdateDTO : checkForUpdateDTO.getList()) {
                KeyProcessesCheckForUpdateDTO processesCheckForUpdateDTO = new KeyProcessesCheckForUpdateDTO();
                //判断权限
                String type = "1";
                if (staffEmployRepository.checkOwner(userPropertyStaffEntity.getStaffId(), keyProcessesCheckForUpdateDTO.getProjectId(), "1")) {//甲方
                    //甲方   该项目下所有数据
                    type = "1";
                } else {
                    String chec = staffEmployRepository.getPurviewName(userPropertyStaffEntity.getStaffId(), keyProcessesCheckForUpdateDTO.getProjectId());
                    if ("4".equals(chec)) {
                        //乙方   处理人为自己 + 完成状态  乙方负责人为自己
                        type = "3";
                    } else if ("5".equals(chec)) {
                        //第三方监理   自己创建 + 第三方监理为自己 + 抄送自己 + 处理人为自己
                        type = "2";
                    }
                }

                String time = "";
                if (!StringUtil.isEmpty(keyProcessesCheckForUpdateDTO.getTimeStamp())) {
                    time = DateUtils.format(DateUtils.parse(keyProcessesCheckForUpdateDTO.getTimeStamp(), "yyyyMMddHHmmss"));
                }
                boolean checkFlag = projectKeyProcessesRepository.keyProcessesCheckForUpdate(keyProcessesCheckForUpdateDTO.getId(), time, keyProcessesCheckForUpdateDTO.getProjectId(), userPropertyStaffEntity.getStaffId(), type);
                if (checkFlag) {
                    processesCheckForUpdateDTO.setUpdateFlag("Y");
                } else {
                    processesCheckForUpdateDTO.setUpdateFlag("N");
                }
                processesCheckForUpdateDTO.setProjectId(keyProcessesCheckForUpdateDTO.getProjectId());
                processesCheckForUpdateDTO.setId(keyProcessesCheckForUpdateDTO.getId());
                processesCheckForUpdateDTO.setTimeStamp(keyProcessesCheckForUpdateDTO.getTimeStamp());
                keyProcessesCheckForUpdateDTOs.add(processesCheckForUpdateDTO);
            }
        }
        return keyProcessesCheckForUpdateDTOs;
    }

    /**
     * 下载关键工序
     *
     * @param id
     * @param time
     * @param projectId
     * @return
     */
    @Override
    public KeyProcessesQuestionDTO getAllKeyProcessesQuestion(String id, String time, String projectId, UserPropertyStaffEntity userPropertyStaffEntity) {
        //判断权限
        String type = "1";
        if (staffEmployRepository.checkOwner(userPropertyStaffEntity.getStaffId(), projectId, "1")) {//甲方
            //甲方   该项目下所有数据
            type = "1";
        } else {
            String chec = staffEmployRepository.getPurviewName(userPropertyStaffEntity.getStaffId(), projectId);
            if ("4".equals(chec)) {
                //乙方   处理人为自己 + 完成状态  乙方负责人为自己
                type = "3";
            } else if ("5".equals(chec)) {
                //第三方监理   自己创建 + 第三方监理为自己 + 抄送自己 + 处理人为自己
                type = "2";
            }
        }
//        List<ProjectKeyProcessesEntity> list = projectKeyProcessesRepository.getAllKeyProcessesQuestion(id, time, projectId,userPropertyStaffEntity.getStaffId(),type);
        List<Object[]> list = projectKeyProcessesRepository.getAllKeyProcessesQuestion(id, time, projectId, userPropertyStaffEntity.getStaffId(), type);
        List<KeyProcessesBackDTO> backDTOList = new ArrayList<KeyProcessesBackDTO>();
        KeyProcessesQuestionDTO keyProcessesQuestionDTO = new KeyProcessesQuestionDTO();
        if (list != null && list.size() > 0) {
            for (Object[] obj : list) {
                KeyProcessesBackDTO keyProcessesBackDTO = getKeyProcessesBackInfoByProcessId(obj[1].toString());
                keyProcessesBackDTO.setLongId(obj[0].toString());
                backDTOList.add(keyProcessesBackDTO);
            }
            if (backDTOList.size() > 0) {
                keyProcessesQuestionDTO.setList(backDTOList);
                keyProcessesQuestionDTO.setId(backDTOList.get(backDTOList.size() - 1).getLongId());
                keyProcessesQuestionDTO.setTimeStamp(backDTOList.get(backDTOList.size() - 1).getModifyDate());
            }
        }
        return keyProcessesQuestionDTO;
    }

    /**
     * 统计关键工序
     *
     * @param projectId
     * @return
     */
    @Override
    public ApiResult addUpKeyProcessesByProjectId(String projectId) {
        if (StringUtil.isEmpty(projectId)) {
            new ErrorApiResult("tip_00000572");
        }
        try {
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(0);
            List<Object[]> list = projectKeyProcessesRepository.addUpKeyProcessesByProjectId(projectId);
            List<KeyProcessesCountDTO> keyProcessesCountDTOs = new ArrayList<KeyProcessesCountDTO>();
            int hasBeenGetOnTotal = 0;
            int qualifiedToatl = 0;
            int unqualifiedToatl = 0;
            int onePassToatl = 0;
            if (list != null && list.size() > 0) {
                String resultQualifiedTotal = "0";
                String resultOnePassTotal = "0";
                KeyProcessesCountDTO keyProcessesCountAllDTO = new KeyProcessesCountDTO();
                for (Object[] obj : list) {
                    String resultQualified = "0";
                    String resultOnePass = "0";
                    if (((BigInteger) obj[3]).intValue() > 0) {
                        resultQualified = numberFormat.format((float) ((BigInteger) obj[4]).intValue() / (float) ((BigInteger) obj[3]).intValue() * 100);
                        resultOnePass = numberFormat.format((float) ((BigInteger) obj[6]).intValue() / (float) ((BigInteger) obj[3]).intValue() * 100);
                    }
                    String qualifiedRate = resultQualified + "%";//合格率
                    String onePassRate = resultOnePass + "%";//一次通过率

                    KeyProcessesCountDTO keyProcessesCountDTO = new KeyProcessesCountDTO();
                    keyProcessesCountDTO.setProjectId((String) obj[0]);//项目ID
                    keyProcessesCountDTO.setBuildingId((String) obj[1]);//楼栋ID
                    keyProcessesCountDTO.setBuildingName((String) obj[2]);//楼栋名称
                    keyProcessesCountDTO.setHasBeenGetOn(((BigInteger) obj[3]).intValue());//已进行
                    keyProcessesCountDTO.setUnqualified(((BigInteger) obj[5]).intValue());//不合格数
                    keyProcessesCountDTO.setQualified(qualifiedRate);//合格率
                    keyProcessesCountDTO.setOnePass(onePassRate);//一次通过率

                    hasBeenGetOnTotal += keyProcessesCountDTO.getHasBeenGetOn();//已进行总数
                    unqualifiedToatl += keyProcessesCountDTO.getUnqualified();//不合格总数
                    qualifiedToatl += ((BigInteger) obj[4]).intValue();//合格总数
                    onePassToatl += ((BigInteger) obj[6]).intValue();//一次通过数

                    keyProcessesCountDTOs.add(keyProcessesCountDTO);
                }
                if (hasBeenGetOnTotal > 0) {
                    resultQualifiedTotal = numberFormat.format((float) qualifiedToatl / (float) hasBeenGetOnTotal * 100);//总合格率
                    resultOnePassTotal = numberFormat.format((float) onePassToatl / (float) hasBeenGetOnTotal * 100);//总一次通过率
                }
                String qualifiedRateTotal = resultQualifiedTotal + "%";//总合格率
                String onePassRateTotal = resultOnePassTotal + "%";//总一次通过率

                keyProcessesCountAllDTO.setProjectId(projectId);//项目ID
                keyProcessesCountAllDTO.setBuildingId("totalAll");//总计
                keyProcessesCountAllDTO.setHasBeenGetOn(hasBeenGetOnTotal);//一次通过总数
                keyProcessesCountAllDTO.setUnqualified(unqualifiedToatl);//不合格总数
                keyProcessesCountAllDTO.setQualified(qualifiedRateTotal);//总数合格率
                keyProcessesCountAllDTO.setOnePass(onePassRateTotal);//总一次通过率

                keyProcessesCountDTOs.add(keyProcessesCountAllDTO);
            }
            return new SuccessApiResult(keyProcessesCountDTOs);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * 关键工序整改、审核
     *
     * @param keyProcessesApplyAnnalListDTO
     * @param userPropertyStaffEntity
     * @return
     */
    @Override
    public ApiResult keyProcessesAnnal(KeyProcessesApplyAnnalListDTO keyProcessesApplyAnnalListDTO, UserPropertyStaffEntity userPropertyStaffEntity) {
        if (keyProcessesApplyAnnalListDTO.getList() == null && keyProcessesApplyAnnalListDTO.getList().size() == 0) {
            return new ErrorApiResult("error_00000002");
        }
        try {
            KeyProcessesBackListDTO keyProcessesBackListDTO = new KeyProcessesBackListDTO();
            List<KeyProcessesBackDTO> keyProcessesBackDTOs = new ArrayList<KeyProcessesBackDTO>();
            for (KeyProcessesApplyAnnalDTO keyProcessesApplyAnnalDTO : keyProcessesApplyAnnalListDTO.getList()) {
                if (StringUtil.isEmpty(keyProcessesApplyAnnalDTO.getProcessId())) {
                    return new ErrorApiResult("tip_00000589");
                }
                if (StringUtil.isEmpty(keyProcessesApplyAnnalDTO.getType())) {
                    return new ErrorApiResult("tip_00000591");
                }
                ProjectKeyProcessesEntity projectKeyProcessesEntity = projectKeyProcessesRepository.getKeyProcessesInfoByProcessId(keyProcessesApplyAnnalDTO.getProcessId());
                if (projectKeyProcessesEntity == null) {
                    return new ErrorApiResult("tip_00000590");
                }
                //工序整改记录
                if (keyProcessesApplyAnnalDTO.getTargetApplyAnnalDTOs() != null && keyProcessesApplyAnnalDTO.getTargetApplyAnnalDTOs().size() > 0) {
                    for (KeyProcessesTargetApplyAnnalDTO keyProcessesTargetApplyAnnalDTO : keyProcessesApplyAnnalDTO.getTargetApplyAnnalDTOs()) {
                        if (StringUtil.isEmpty(keyProcessesTargetApplyAnnalDTO.getProcessTargetId())) {
                            return new ErrorApiResult("tip_00000592");
                        }
                        //指标详情为空的时候是新增的记录，反之，不做任何处理
                        if (!StringUtil.isEmpty(keyProcessesTargetApplyAnnalDTO.getProcessTargetDetailId())) {
                            continue;
                        }
                        //乙方整改(推送到监理)
                        if (keyProcessesApplyAnnalDTO.getType().equals("0")) {
                            //修改工序信息
                            projectKeyProcessesEntity.setHandlePeopleId(projectKeyProcessesEntity.getSupervisorId());//处理人（监理审核）
                            this.keyProcesses(projectKeyProcessesEntity, userPropertyStaffEntity);

                            ProjectKeyProcessesTargetEntity projectKeyProcessesTargetEntity = projectKeyProcessesRepository.getKeyProcessesTargetListById(keyProcessesTargetApplyAnnalDTO.getProcessTargetId());
                            if (projectKeyProcessesTargetEntity == null) {
                                return new ErrorApiResult("tip_00000593");
                            }
                            //修改工序指标详情信息
                            this.keyProcessesTargetDetail(keyProcessesTargetApplyAnnalDTO, keyProcessesApplyAnnalDTO, userPropertyStaffEntity);
                        } else if (keyProcessesApplyAnnalDTO.getType().equals("1")) {
                            //监理审核（合格：推送到甲方；不合格：推送到乙方）
                            if (keyProcessesApplyAnnalDTO.getQualifiedState().equals("合格")) {
                                projectKeyProcessesEntity.setHandlePeopleId(projectKeyProcessesEntity.getPartyPrincipalId());//处理人（甲方）
                            } else {
                                projectKeyProcessesEntity.setHandlePeopleId(projectKeyProcessesEntity.getAssignId());//处理人（乙方）
                            }
                            this.keyProcesses(projectKeyProcessesEntity, userPropertyStaffEntity);
                            //指标
                            if (keyProcessesTargetApplyAnnalDTO.getQualifiedState().equals("合格")) {
                                ProjectKeyProcessesTargetEntity projectKeyProcessesTargetEntity = projectKeyProcessesRepository.getKeyProcessesTargetListById(keyProcessesTargetApplyAnnalDTO.getProcessTargetId());
                                if (projectKeyProcessesTargetEntity != null) {
                                    projectKeyProcessesTargetEntity.setQualifiedState(keyProcessesTargetApplyAnnalDTO.getQualifiedState());
                                    this.keyProcessesTarget(projectKeyProcessesTargetEntity, keyProcessesTargetApplyAnnalDTO, userPropertyStaffEntity);
                                } else {
                                    return new ErrorApiResult("tip_00000593");
                                }
                            }
                            //指标详情
                            this.keyProcessesTargetDetail(keyProcessesTargetApplyAnnalDTO, keyProcessesApplyAnnalDTO, userPropertyStaffEntity);
                        } else if (keyProcessesApplyAnnalDTO.getType().equals("2")) {
                            //甲方审核（合格：这个单子就合格；不合格：推送到乙方）
                            if (keyProcessesApplyAnnalDTO.getQualifiedState().equals("合格")) {
                                projectKeyProcessesEntity.setQualifiedState("合格");//合格状态
                                projectKeyProcessesEntity.setState("合格");//问题状态
//                                projectKeyProcessesEntity.setHandlePeopleId(projectKeyProcessesEntity.getPartyPrincipalId());//处理人（甲方）
                            } else {
                                projectKeyProcessesEntity.setHandlePeopleId(projectKeyProcessesEntity.getAssignId());//处理人（乙方）
                            }
                            this.keyProcesses(projectKeyProcessesEntity, userPropertyStaffEntity);

                            //指标
                            ProjectKeyProcessesTargetEntity projectKeyProcessesTargetEntity = projectKeyProcessesRepository.getKeyProcessesTargetListById(keyProcessesTargetApplyAnnalDTO.getProcessTargetId());
                            if (projectKeyProcessesTargetEntity != null) {
                                projectKeyProcessesTargetEntity.setQualifiedState(keyProcessesTargetApplyAnnalDTO.getQualifiedState());
                                this.keyProcessesTarget(projectKeyProcessesTargetEntity, keyProcessesTargetApplyAnnalDTO, userPropertyStaffEntity);
                            } else {
                                return new ErrorApiResult("tip_00000593");
                            }
//                            if (keyProcessesTargetApplyAnnalDTO.getQualifiedState().equals("合格")) {
//                                ProjectKeyProcessesTargetEntity projectKeyProcessesTargetEntity = projectKeyProcessesRepository.getKeyProcessesTargetListById(keyProcessesTargetApplyAnnalDTO.getProcessTargetId());
//                                if (projectKeyProcessesTargetEntity != null) {
//                                    projectKeyProcessesTargetEntity.setQualifiedState(keyProcessesTargetApplyAnnalDTO.getQualifiedState());
//                                    this.keyProcessesTarget(projectKeyProcessesTargetEntity, keyProcessesTargetApplyAnnalDTO, userPropertyStaffEntity);
//                                } else {
//                                    return new ErrorApiResult("tip_00000593");
//                                }
//                            }
                            this.keyProcessesTargetDetail(keyProcessesTargetApplyAnnalDTO, keyProcessesApplyAnnalDTO, userPropertyStaffEntity);
                        }
                    }
                }
                //返回成功数据
                KeyProcessesBackDTO keyProcessesBackDTO = getKeyProcessesBackInfoByProcessId(keyProcessesApplyAnnalDTO.getProcessId());
                keyProcessesBackDTOs.add(keyProcessesBackDTO);
            }
            keyProcessesBackListDTO.setList(keyProcessesBackDTOs);
            return new SuccessApiResult(keyProcessesBackListDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * 关键工序列表
     *
     * @param projectKeyProcessesDTO
     * @param webPage
     * @return
     */
    @Override
    public List<ProjectKeyProcessesDTO> searchKeyProcessesList(ProjectKeyProcessesDTO projectKeyProcessesDTO, WebPage webPage,  UserInformationEntity userInformationEntity) {
        Map map = new HashMap();
        map.put("projectId", projectKeyProcessesDTO.getProjectId());//项目id
        map.put("buildingId", projectKeyProcessesDTO.getBuildingId());//楼栋id
        map.put("threeSort", projectKeyProcessesDTO.getThreeSort());//三级分类
        map.put("state", projectKeyProcessesDTO.getState());//问题状态
        map.put("startDate", projectKeyProcessesDTO.getStartDate());//开始时间
        map.put("endDate", projectKeyProcessesDTO.getEndDate());//结束时间
        map.put("partyPrincipalName", projectKeyProcessesDTO.getPartyPrincipalName());//甲方负责人名称
        map.put("supervisorName", projectKeyProcessesDTO.getSupervisorName());//第三方监理名称
        map.put("supplier", projectKeyProcessesDTO.getSupplierName());//责任单位名称
        map.put("assignName", projectKeyProcessesDTO.getAssignName());//整改人名称
        map.put("severityRating", projectKeyProcessesDTO.getSeverityRating());//严重等级
//        //获取当前人的菜单权限
//        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userInformationEntity.getStaffId());
        List<ProjectKeyProcessesEntity> projectKeyProcessesEntityList = null;
//        if (f) {
//            projectKeyProcessesEntityList = projectKeyProcessesRepository.searchKeyProcessesList(map, webPage);
//        } else {
//            projectKeyProcessesEntityList = projectKeyProcessesRepository.searchKeyProcessesList(map, webPage, userInformationEntity.getStaffId());
//        }
        if(!StringUtil.isEmpty(projectKeyProcessesDTO.getProjectId())){
            projectKeyProcessesEntityList = projectKeyProcessesRepository.searchKeyProcessesList(map, webPage);
        }
        List<ProjectKeyProcessesDTO> projectKeyProcessesDTOList = new ArrayList<ProjectKeyProcessesDTO>();
        if (projectKeyProcessesEntityList != null && projectKeyProcessesEntityList.size() > 0) {
            for (ProjectKeyProcessesEntity projectKeyProcessesEntity : projectKeyProcessesEntityList) {
                ProjectKeyProcessesDTO keyProcessesDTO = new ProjectKeyProcessesDTO();
                keyProcessesDTO.setProcessId(projectKeyProcessesEntity.getProcessId());//工序ID
                keyProcessesDTO.setProcessName(projectKeyProcessesEntity.getProcessName());//工序名称
                keyProcessesDTO.setProjectId(projectKeyProcessesEntity.getProjectId());//项目ID
                keyProcessesDTO.setProjectName(projectKeyProcessesEntity.getProjectName());//项目名称
                keyProcessesDTO.setBuildingName(projectKeyProcessesEntity.getBuildingName());//楼栋名称
                keyProcessesDTO.setFourSortName(projectKeyProcessesEntity.getFourSortName());//检查项
                keyProcessesDTO.setPartyPrincipalName(projectKeyProcessesEntity.getPartyPrincipalName());//甲方负责人
                keyProcessesDTO.setSupervisorName(projectKeyProcessesEntity.getSupervisorName());//第三方监理
                keyProcessesDTO.setSupplierName(projectKeyProcessesEntity.getSupplierName());//责任单位
                keyProcessesDTO.setAssignName(projectKeyProcessesEntity.getAssignName());//整改人
                keyProcessesDTO.setCreateDate(DateUtils.format(projectKeyProcessesEntity.getCreateOn(), DateUtils.FORMAT_SHORT));//完成期限
                keyProcessesDTO.setSeverityRating(projectKeyProcessesEntity.getSeverityRating());//严重等级
                if (projectKeyProcessesEntity.getState().equals("AbnormalShutdown")) {
                    keyProcessesDTO.setState("非正常关闭");//问题状态
                } else {
                    keyProcessesDTO.setState(projectKeyProcessesEntity.getState());//问题状态
                }
                if (projectKeyProcessesEntity.getCompleteOn().after(new Date())) {
                    keyProcessesDTO.setOverdue("是");//是否超期
                } else {
                    keyProcessesDTO.setOverdue("否");
                }
                keyProcessesDTO.setAbnormalShutdown(projectKeyProcessesEntity.getAbnormalShutdown());//非正常关闭

                projectKeyProcessesDTOList.add(keyProcessesDTO);
            }
        }
        return projectKeyProcessesDTOList;
    }

    /**
     * 查看关键工序详情
     *
     * @param processesId
     * @return
     */
    @Override
    public ProjectKeyProcessesDTO searchKeyProcessesDetailByBatchId(String processesId) {
        if (!StringUtil.isEmpty(processesId)) {
            ProjectKeyProcessesEntity projectKeyProcessesEntity = projectKeyProcessesRepository.getKeyProcessesInfoByProcessId(processesId);
            if (projectKeyProcessesEntity != null) {
                ProjectKeyProcessesDTO projectKeyProcessesDTO = new ProjectKeyProcessesDTO();
                projectKeyProcessesDTO.setProcessId(projectKeyProcessesEntity.getProcessId() == null ? "" : projectKeyProcessesEntity.getProcessId());//工序ID
                projectKeyProcessesDTO.setProcessName(projectKeyProcessesEntity.getProcessName() == null ? "" : projectKeyProcessesEntity.getProcessName());//工序名称
                projectKeyProcessesDTO.setProjectId(projectKeyProcessesEntity.getProjectId() == null ? "" : projectKeyProcessesEntity.getProjectId());//项目ID
                projectKeyProcessesDTO.setProjectName(projectKeyProcessesEntity.getProjectName() == null ? "" : projectKeyProcessesEntity.getProjectName());//项目名称
                projectKeyProcessesDTO.setBuildingId(projectKeyProcessesEntity.getBuildingId() == null ? "" : projectKeyProcessesEntity.getBuildingId());//楼栋ID
                projectKeyProcessesDTO.setBuildingName(projectKeyProcessesEntity.getBuildingName() == null ? "" : projectKeyProcessesEntity.getBuildingName());//楼栋名称
                projectKeyProcessesDTO.setFloorStar(projectKeyProcessesEntity.getFloorStar() == null ? "" : projectKeyProcessesEntity.getFloorStar());//开始楼层
                projectKeyProcessesDTO.setFloorEnd(projectKeyProcessesEntity.getFloorEnd() == null ? "" : projectKeyProcessesEntity.getFloorEnd());//结束楼层
                projectKeyProcessesDTO.setSerial(projectKeyProcessesEntity.getSerial() == null ? "" : projectKeyProcessesEntity.getSerial());//流水段
                projectKeyProcessesDTO.setSeverityRating(projectKeyProcessesEntity.getSeverityRating() == null ? "" : projectKeyProcessesEntity.getSeverityRating());//严重等级
                projectKeyProcessesDTO.setExaminationDate(projectKeyProcessesEntity.getExaminationDate() == null ? "" : DateUtils.format(projectKeyProcessesEntity.getExaminationDate(), DateUtils.FORMAT_LONG));//检查时间
                projectKeyProcessesDTO.setPartyPrincipalId(projectKeyProcessesEntity.getPartyPrincipalId() == null ? "" : projectKeyProcessesEntity.getPartyPrincipalId());//甲方负责人ID
                projectKeyProcessesDTO.setPartyPrincipalName(projectKeyProcessesEntity.getPartyPrincipalName() == null ? "" : projectKeyProcessesEntity.getPartyPrincipalName());//甲方负责人名称
                projectKeyProcessesDTO.setSupervisorId(projectKeyProcessesEntity.getSupervisorId() == null ? "" : projectKeyProcessesEntity.getSupervisorId());//第三方监理ID
                projectKeyProcessesDTO.setSupervisorName(projectKeyProcessesEntity.getSupervisorName() == null ? "" : projectKeyProcessesEntity.getSupervisorName());//第三方监理名称
                projectKeyProcessesDTO.setSupplierId(projectKeyProcessesEntity.getSupplierId() == null ? "" : projectKeyProcessesEntity.getSupplierId());//责任单位id
                projectKeyProcessesDTO.setSupplierName(projectKeyProcessesEntity.getSupplierName() == null ? "" : projectKeyProcessesEntity.getSupplierName());//责任单位名称
                projectKeyProcessesDTO.setAssignId(projectKeyProcessesEntity.getAssignId() == null ? "" : projectKeyProcessesEntity.getAssignId());//整改人ID
                projectKeyProcessesDTO.setAssignName(projectKeyProcessesEntity.getAssignName() == null ? "" : projectKeyProcessesEntity.getAssignName());//整改人名称
                projectKeyProcessesDTO.setCompleteOn(projectKeyProcessesEntity.getCompleteOn() == null ? "" : DateUtils.format(projectKeyProcessesEntity.getCompleteOn(), DateUtils.FORMAT_SHORT));//完成期限
                projectKeyProcessesDTO.setQualifiedState(projectKeyProcessesEntity.getQualifiedState() == null ? "" : projectKeyProcessesEntity.getQualifiedState());//工序合格状态
                projectKeyProcessesDTO.setFirstSort(projectKeyProcessesEntity.getFirstSort() == null ? "" : projectKeyProcessesEntity.getFirstSort());//一级分类
                projectKeyProcessesDTO.setFirstSortName(projectKeyProcessesEntity.getFirstSortName() == null ? "" : projectKeyProcessesEntity.getFirstSortName());//一级分类名称
                projectKeyProcessesDTO.setSecondSort(projectKeyProcessesEntity.getSecondSort() == null ? "" : projectKeyProcessesEntity.getSecondSort());//二级分类
                projectKeyProcessesDTO.setSecondSortName(projectKeyProcessesEntity.getSecondSortName() == null ? "" : projectKeyProcessesEntity.getSecondSortName());//二级分类名称
                projectKeyProcessesDTO.setThreeSort(projectKeyProcessesEntity.getThreeSort() == null ? "" : projectKeyProcessesEntity.getThreeSort());//三级分类
                projectKeyProcessesDTO.setThreeSortName(projectKeyProcessesEntity.getThreeSortName() == null ? "" : projectKeyProcessesEntity.getThreeSortName());//三级分类名称
                projectKeyProcessesDTO.setFourSort(projectKeyProcessesEntity.getFourSort() == null ? "" : projectKeyProcessesEntity.getFourSort());//四级分类
                projectKeyProcessesDTO.setFourSortName(projectKeyProcessesEntity.getFourSortName() == null ? "" : projectKeyProcessesEntity.getFourSortName());//四级分类名称
                if (projectKeyProcessesEntity.getState().equals("AbnormalShutdown")) {
                    projectKeyProcessesDTO.setState("非正常关闭");
                } else {
                    projectKeyProcessesDTO.setState(projectKeyProcessesEntity.getState() == null ? "" : projectKeyProcessesEntity.getState());//问题状态
                }
                projectKeyProcessesDTO.setAbnormalShutdown(projectKeyProcessesEntity.getAbnormalShutdown());//非正常关闭
                projectKeyProcessesDTO.setCreateBy(projectKeyProcessesEntity.getCreateName());//创建人
                projectKeyProcessesDTO.setCreateDate(DateUtils.format(projectKeyProcessesEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间

                projectKeyProcessesDTO.setModifyName(projectKeyProcessesEntity.getModifyName() == null ? "" : projectKeyProcessesEntity.getModifyName());//修改人
                projectKeyProcessesDTO.setModifyDate(DateUtils.format(projectKeyProcessesEntity.getModifyOn(), DateUtils.FORMAT_LONG));//修改时间
                //抄送人
                List<Object[]> idList = dailyPatrolInspectionRepository.getProjectCopy(projectKeyProcessesEntity.getProcessId());//抄送人list
                List<KeyProcessesCopyDTO> copyDetailsList = new ArrayList<KeyProcessesCopyDTO>();
                if (idList != null) {
                    for (Object[] idObj : idList) {
                        KeyProcessesCopyDTO keyProcessesCopyDTO = new KeyProcessesCopyDTO();
                        keyProcessesCopyDTO.setId(idObj[1] == null ? "" : idObj[1].toString());//抄送人ID
                        keyProcessesCopyDTO.setName(idObj[2] == null ? "" : idObj[2].toString());//抄送人名称
                        copyDetailsList.add(keyProcessesCopyDTO);
                    }
                }
                projectKeyProcessesDTO.setCopyDTOList(copyDetailsList);

                //工序指标信息
                List<Object[]> targetList = projectKeyProcessesRepository.searchKeyProcessesTargetListByProcessId(projectKeyProcessesEntity.getProcessId());
                List<ProjectKeyProcessesTargetDTO> projectKeyProcessesTargetDTOs = new ArrayList<ProjectKeyProcessesTargetDTO>();
                if (targetList != null && targetList.size() > 0) {
                    for (Object[] target : targetList) {
                        ProjectKeyProcessesTargetDTO projectKeyProcessesTargetDTO = new ProjectKeyProcessesTargetDTO();
                        projectKeyProcessesTargetDTO.setTargetName(target[0] == null ? "" : (String) target[0]);//指标名称
                        projectKeyProcessesTargetDTO.setQualifiedState(target[1] == null ? "" : (String) target[1]);//指标状态
                        projectKeyProcessesTargetDTO.setTargetDescription(target[2] == null ? "" : (String) target[2]);//指标描述
                        projectKeyProcessesTargetDTO.setDescription(target[3] == null ? "" : (String) target[3]);//工序指标描述
                        projectKeyProcessesTargetDTO.setTargetImgUrl(target[4] == null ? "" : (String) target[4]);//指标图片

                        //指标的整改验收记录
                        List<Object[]> detailList = projectKeyProcessesRepository.getKeyProcessesTargetDetailListByProcessId((String) target[5]);
                        if (detailList != null && detailList.size() > 0) {
                            List<ProjectKeyProcessesTargetDTO> targetDTOByPartyBAnnalList = new ArrayList<ProjectKeyProcessesTargetDTO>();//乙方整改信息
                            List<ProjectKeyProcessesTargetDTO> targetDTOBySupervisionAnnalList = new ArrayList<ProjectKeyProcessesTargetDTO>();//监理整改信息
                            List<ProjectKeyProcessesTargetDTO> targetDTOByPartyAAnnalList = new ArrayList<ProjectKeyProcessesTargetDTO>();//甲方整改信息
                            for (Object[] detail : detailList) {
                                ProjectKeyProcessesTargetDTO keyProcessesTargetDTO = new ProjectKeyProcessesTargetDTO();
                                keyProcessesTargetDTO.setDescription(detail[2] == null ? "" : (String) detail[2]);//描述
                                keyProcessesTargetDTO.setTargetImgUrl(detail[4] == null ? "" : (String) detail[4]);//图片
                                keyProcessesTargetDTO.setQualifiedState(detail[7] == null ? "" : (String) detail[7]);//排序
                                //乙方整改
                                if (((String) detail[5]).equals("0")) {
                                    targetDTOByPartyBAnnalList.add(keyProcessesTargetDTO);

                                } else if (((String) detail[5]).equals("1")) {
                                    targetDTOBySupervisionAnnalList.add(keyProcessesTargetDTO);

                                } else if (((String) detail[5]).equals("2")) {
                                    targetDTOByPartyAAnnalList.add(keyProcessesTargetDTO);

                                }
                            }
                            projectKeyProcessesTargetDTO.setTargetDTOByPartyBAnnalList(targetDTOByPartyBAnnalList);
                            projectKeyProcessesTargetDTO.setTargetDTOBySupervisionAnnalList(targetDTOBySupervisionAnnalList);
                            projectKeyProcessesTargetDTO.setTargetDTOByPartyAAnnalList(targetDTOByPartyAAnnalList);
                        }
                        projectKeyProcessesTargetDTOs.add(projectKeyProcessesTargetDTO);
                    }
                }
                projectKeyProcessesDTO.setTargetDTOList(projectKeyProcessesTargetDTOs);
                return projectKeyProcessesDTO;
            }
        }
        return null;
    }

    /**
     * 关键工序列表excel
     *
     * @param title
     * @param headers
     * @param out
     * @param projectKeyProcessesDTO
     */
    @Override
    public void exportExcel(String title, String[] headers, ServletOutputStream out, ProjectKeyProcessesDTO projectKeyProcessesDTO, UserInformationEntity userInformationEntity) {
        List<ProjectKeyProcessesDTO> projectKeyProcessesDTOList = this.searchKeyProcessesList(projectKeyProcessesDTO, userInformationEntity);
        // 导出数据
        ExportExcel<ProjectKeyProcessesExcelDTO> ex = new ExportExcel<ProjectKeyProcessesExcelDTO>();

        List<ProjectKeyProcessesExcelDTO> projectKeyProcessesExcelDTOs = new ArrayList<ProjectKeyProcessesExcelDTO>();

        if (projectKeyProcessesDTOList != null && projectKeyProcessesDTOList.size() > 0) {
            int num = 1;
            for (ProjectKeyProcessesDTO keyProcessesDTO : projectKeyProcessesDTOList) {

                ProjectKeyProcessesExcelDTO projectKeyProcessesExcelDTO = new ProjectKeyProcessesExcelDTO();
                projectKeyProcessesExcelDTO.setSerialNumber(num++);//序号
//                projectKeyProcessesExcelDTO.setOverdue(keyProcessesDTO.getOverdue());//超期
                projectKeyProcessesExcelDTO.setProcessName(keyProcessesDTO.getProcessName());//批次名称
                projectKeyProcessesExcelDTO.setProjectName(keyProcessesDTO.getProjectName());//项目名称
                projectKeyProcessesExcelDTO.setBuildingName(keyProcessesDTO.getBuildingName());//楼栋名称
                projectKeyProcessesExcelDTO.setFourSortName(keyProcessesDTO.getFourSortName());//检查项
                projectKeyProcessesExcelDTO.setSeverityRating(keyProcessesDTO.getSeverityRating());//严重等级
                projectKeyProcessesExcelDTO.setPartyPrincipalName(keyProcessesDTO.getPartyPrincipalName());//甲方负责人
                projectKeyProcessesExcelDTO.setSupervisorName(keyProcessesDTO.getSupervisorName());//第三方监理
                projectKeyProcessesExcelDTO.setSupplierName(keyProcessesDTO.getSupplierName());//责任单位
                projectKeyProcessesExcelDTO.setAssignName(keyProcessesDTO.getAssignName());//整改人
                projectKeyProcessesExcelDTO.setCompleteOn(keyProcessesDTO.getCompleteOn());//登记时间
                if (keyProcessesDTO.getState().equals("AbnormalShutdown")) {
                    projectKeyProcessesExcelDTO.setState("非正常关闭");
                } else {
                    projectKeyProcessesExcelDTO.setState(keyProcessesDTO.getState());//问题状态
                }
                projectKeyProcessesExcelDTOs.add(projectKeyProcessesExcelDTO);
            }
        }
        ex.exportExcel(title, headers, projectKeyProcessesExcelDTOs, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    /**
     * 关键工序统计列表
     *
     * @param keyProcessesDTO
     * @param webPage
     * @return
     */
    @Override
    public ProjectKeyProcessesCountListDTO searchKeyProcessesCountList(ProjectKeyProcessesDTO keyProcessesDTO, WebPage webPage, UserInformationEntity userInformationEntity) {
        if (keyProcessesDTO == null) {
            new ErrorApiResult("error_00000002");
        }
        Map map = new HashMap();
        map.put("projectId", keyProcessesDTO.getProjectId());//项目id
        map.put("tenderId", keyProcessesDTO.getTenderId());//标段ID
        map.put("buildingId", keyProcessesDTO.getBuildingId());//楼栋ID
//        int count = projectKeyProcessesRepository.searchKeyProcessesCount(map);
        int count = 0;
        int pageCount = 0;
        //获取当前人的菜单权限
        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userInformationEntity.getStaffId());
        List<Object[]> list = null;
        List<Object[]> rateList = null;
        if (f) {
            pageCount = projectKeyProcessesRepository.getCount(map);
            webPage.setRecordCount(pageCount);
            list = projectKeyProcessesRepository.searchKeyProcessesCountList(map, webPage);
            rateList = projectKeyProcessesRepository.searchKeyProcessesCountList(map);
        } else {
            pageCount = projectKeyProcessesRepository.getCount(map, userInformationEntity.getStaffId());
            webPage.setRecordCount(pageCount);
            list = projectKeyProcessesRepository.searchKeyProcessesCountList(map, webPage, userInformationEntity.getStaffId());
            rateList = projectKeyProcessesRepository.searchKeyProcessesCountList(map, userInformationEntity.getStaffId());
        }
//        List<Object[]> list = projectKeyProcessesRepository.searchKeyProcessesCountList(map, webPage, userPropertystaff.getStaffId());
        List<ProjectKeyProcessesCountDTO> projectKeyProcessesCountDTOs = new ArrayList<ProjectKeyProcessesCountDTO>();
        ProjectKeyProcessesCountListDTO projectKeyProcessesCountListDTO = new ProjectKeyProcessesCountListDTO();
        int qualifiedAllTotal = 0;//合格总数
        int unqualifiedAllTotal = 0;//不合格总数
        int onePassAllTotal = 0;//一次通过总数
        String qualifiedRate = "";//合格率
        String unqualifiedRate = "";//不合格率
        String onePassRate = "";//一次通过率
        String resultQualified = "0";
        String resultUnqualified = "0";
        String resultOnePass = "0";
        if (list != null && list.size() > 0) {
            for (Object[] obj : list) {
                ProjectKeyProcessesCountDTO projectKeyProcessesCountDTO = new ProjectKeyProcessesCountDTO();
                projectKeyProcessesCountDTO.setBuildingName((String) obj[0]);//楼栋名称
                projectKeyProcessesCountDTO.setTenderName((String) obj[1]);//标段名称
                projectKeyProcessesCountDTO.setProjectName((String) obj[2]);//项目名称
//                projectKeyProcessesCountDTO.setTotal(((BigInteger) obj[3]).intValue());//总数
                projectKeyProcessesCountDTO.setQualifiedTotal(((BigInteger) obj[4]).intValue());//合格数
                projectKeyProcessesCountDTO.setUnqualifiedTotal(((BigInteger) obj[5]).intValue());//不合格数
                projectKeyProcessesCountDTO.setOnePassTotal(((BigInteger) obj[6]).intValue());//一次性通过数
                projectKeyProcessesCountDTO.setAbnormalShutdown(((BigInteger) obj[7]).intValue());//非正常关闭
                projectKeyProcessesCountDTO.setTotal(((BigInteger) obj[4]).intValue() + ((BigInteger) obj[5]).intValue() + ((BigInteger) obj[7]).intValue());

                projectKeyProcessesCountDTOs.add(projectKeyProcessesCountDTO);
            }
            projectKeyProcessesCountListDTO.setList(projectKeyProcessesCountDTOs);
        }
        if (rateList != null && rateList.size() > 0) {
            for (Object[] obj : rateList) {
                qualifiedAllTotal += ((BigInteger) obj[4]).intValue();//合格总数
                unqualifiedAllTotal += ((BigInteger) obj[5]).intValue();//不合格总数
                onePassAllTotal += ((BigInteger) obj[6]).intValue();//一次性通过总数
                count += ((BigInteger) obj[4]).intValue() + ((BigInteger) obj[5]).intValue() + ((BigInteger) obj[7]).intValue();//总数
            }
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(0);
            if (count > 0) {
                resultQualified = numberFormat.format((float) qualifiedAllTotal / (float) count * 100);
                resultUnqualified = numberFormat.format((float) unqualifiedAllTotal / (float) count * 100);
                resultOnePass = numberFormat.format((float) onePassAllTotal / (float) count * 100);
            }
            qualifiedRate = resultQualified + "%";
            unqualifiedRate = resultUnqualified + "%";//不合格率
            onePassRate = resultOnePass + "%";//一次通过率

            projectKeyProcessesCountListDTO.setQualifiedRate(qualifiedRate);
            projectKeyProcessesCountListDTO.setUnqualifiedRate(unqualifiedRate);
            projectKeyProcessesCountListDTO.setOnePassRate(onePassRate);
        }
        return projectKeyProcessesCountListDTO;
    }

    /**
     * 关键工序统计excel
     *
     * @param title
     * @param headers
     * @param out
     * @param keyProcessesDTO
     */
    @Override
    public void exportCountExcel(String title, String[] headers, ServletOutputStream out, ProjectKeyProcessesDTO keyProcessesDTO, UserInformationEntity userInformationEntity) {
        List<ProjectKeyProcessesCountDTO> projectKeyProcessesCountDTOs = this.searchKeyProcessesCountList(keyProcessesDTO, userInformationEntity);
        // 导出数据
        ExportExcel<ProjectKeyProcessesCountExcelDTO> ex = new ExportExcel<ProjectKeyProcessesCountExcelDTO>();

        List<ProjectKeyProcessesCountExcelDTO> keyProcessesCountExcelDTOs = new ArrayList<ProjectKeyProcessesCountExcelDTO>();

        if (projectKeyProcessesCountDTOs != null && projectKeyProcessesCountDTOs.size() > 0) {
            int num = 1;
            for (ProjectKeyProcessesCountDTO keyProcessesCountDTO : projectKeyProcessesCountDTOs) {
                ProjectKeyProcessesCountExcelDTO projectKeyProcessesCountExcelDTO = new ProjectKeyProcessesCountExcelDTO();
                projectKeyProcessesCountExcelDTO.setSerialNumber(num++);//序号
                projectKeyProcessesCountExcelDTO.setProjectName(keyProcessesCountDTO.getProjectName());//项目名称
                projectKeyProcessesCountExcelDTO.setTenderName(keyProcessesCountDTO.getTenderName());//标段名称
                projectKeyProcessesCountExcelDTO.setBuildingName(keyProcessesCountDTO.getBuildingName());//楼栋名称
                projectKeyProcessesCountExcelDTO.setTotal(keyProcessesCountDTO.getTotal());//全部
                projectKeyProcessesCountExcelDTO.setQualifiedTotal(keyProcessesCountDTO.getQualifiedTotal());//合格
                projectKeyProcessesCountExcelDTO.setUnqualifiedTotal(keyProcessesCountDTO.getUnqualifiedTotal());//不合格
                projectKeyProcessesCountExcelDTO.setOnePassTotal(keyProcessesCountDTO.getOnePassTotal());//一次性通过
                projectKeyProcessesCountExcelDTO.setAbnormalShutdown(keyProcessesCountDTO.getAbnormalShutdown());//非正常关闭

                keyProcessesCountExcelDTOs.add(projectKeyProcessesCountExcelDTO);
            }
        }
        ex.exportExcel(title, headers, keyProcessesCountExcelDTOs, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    /**
     * 查询自己的批次
     *
     * @param processId
     * @param staffId
     * @return
     */
    @Override
    public ApiResult searchKeyProcessByStaffId(String processId, String staffId) {
        ModelMap modelMap = new ModelMap();
        ProjectKeyProcessesEntity keyProcessesEntity = projectKeyProcessesRepository.searchKeyProcessByStaffId(processId, staffId);
        if (keyProcessesEntity == null) {
            modelMap.addAttribute("error", "对不起，您没有权限操作此条记录！");
        } else {
            modelMap.addAttribute("success", "确定要关闭此条记录吗？");
        }
        return new SuccessApiResult(modelMap);
    }

    /**
     * 执行非正常关闭
     *
     * @return
     */
    @Override
    public String executeAbnormalOffState(ProjectKeyProcessesDTO keyProcessesDTO, UserInformationEntity userInformationEntity) {
        String resultMessage = "";
        ProjectKeyProcessesEntity keyProcessesEntity = projectKeyProcessesRepository.getKeyProcessesInfoByProcessId(keyProcessesDTO.getProcessId());
        if (keyProcessesEntity != null) {
            keyProcessesEntity.setAbnormalShutdown(keyProcessesDTO.getAbnormalShutdown());
            keyProcessesEntity.setState("AbnormalShutdown");
            keyProcessesEntity.setModifyOn(new Date());
            keyProcessesEntity.setModifyBy(userInformationEntity.getStaffId());
            keyProcessesEntity.setModifyName(userInformationEntity.getStaffName());
            boolean f = projectKeyProcessesRepository.modifyKeyProcesses(keyProcessesEntity);
            if (f) {
                resultMessage = "0";//成功
            } else {
                resultMessage = "1";//失败
            }
        }
        return resultMessage;
    }

    /**
     * 获取自己的权限（是否有关单权限）
     *
     * @param projectId
     * @param staffId
     * @return
     */
    @Override
    public ApiResult searchAuthorityByStaffId(String projectId, String staffId) {
        ModelMap modelMap = new ModelMap();
        boolean check = staffEmployRepository.checkOwner(staffId, projectId, "4");
        if (check) {
            modelMap.addAttribute("success", "确定要关闭此条记录吗？");
        } else {
            modelMap.addAttribute("error", "对不起，您没有权限执行此操作！");
        }
        return new SuccessApiResult(modelMap);
    }

    public List<ProjectKeyProcessesDTO> searchKeyProcessesList(ProjectKeyProcessesDTO projectKeyProcessesDTO, UserInformationEntity userInformationEntity) {
        Map map = new HashMap();
        map.put("projectId", projectKeyProcessesDTO.getProjectId());//项目id
        map.put("buildingId", projectKeyProcessesDTO.getBuildingId());//楼栋id
        map.put("threeSort", projectKeyProcessesDTO.getThreeSort());//三级分类
        map.put("state", projectKeyProcessesDTO.getState());//问题状态
        map.put("startDate", projectKeyProcessesDTO.getStartDate());//开始时间
        map.put("endDate", projectKeyProcessesDTO.getEndDate());//结束时间
        map.put("partyPrincipalName", projectKeyProcessesDTO.getPartyPrincipalName());//甲方负责人名称
        map.put("supervisorName", projectKeyProcessesDTO.getSupervisorName());//第三方监理名称
        map.put("supplier", projectKeyProcessesDTO.getSupplierName());//责任单位名称
        map.put("assignName", projectKeyProcessesDTO.getAssignName());//整改人名称
        map.put("severityRating", projectKeyProcessesDTO.getSeverityRating());//严重等级
        //获取当前人的菜单权限
//        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userInformationEntity.getStaffId());
        List<ProjectKeyProcessesEntity> projectKeyProcessesEntityList = null;
        if(!StringUtil.isEmpty(projectKeyProcessesDTO.getProjectId())){
            projectKeyProcessesEntityList = projectKeyProcessesRepository.searchKeyProcessesList(map);
        }
//        if (f) {
//            projectKeyProcessesEntityList = projectKeyProcessesRepository.searchKeyProcessesList(map);
//        } else {
//            WebPage webPage = null;
//            projectKeyProcessesEntityList = projectKeyProcessesRepository.searchKeyProcessesList(map, webPage, userInformationEntity.getStaffId());
//        }
//        List<ProjectKeyProcessesEntity> projectKeyProcessesEntityList = projectKeyProcessesRepository.searchKeyProcessesList(map);
        List<ProjectKeyProcessesDTO> projectKeyProcessesDTOList = new ArrayList<ProjectKeyProcessesDTO>();
        if (projectKeyProcessesEntityList != null && projectKeyProcessesEntityList.size() > 0) {
            for (ProjectKeyProcessesEntity projectKeyProcessesEntity : projectKeyProcessesEntityList) {
                ProjectKeyProcessesDTO keyProcessesDTO = new ProjectKeyProcessesDTO();
                keyProcessesDTO.setProcessId(projectKeyProcessesEntity.getProcessId());//工序ID
                keyProcessesDTO.setProcessName(projectKeyProcessesEntity.getProcessName());//工序名称
                keyProcessesDTO.setProjectName(projectKeyProcessesEntity.getProjectName());//项目名称
                keyProcessesDTO.setBuildingName(projectKeyProcessesEntity.getBuildingName());//楼栋名称
                keyProcessesDTO.setFourSortName(projectKeyProcessesEntity.getFourSortName());//检查项
                keyProcessesDTO.setPartyPrincipalName(projectKeyProcessesEntity.getPartyPrincipalName());//甲方负责人
                keyProcessesDTO.setSupervisorName(projectKeyProcessesEntity.getSupervisorName());//第三方监理
                keyProcessesDTO.setSupplierName(projectKeyProcessesEntity.getSupplierName());//责任单位
                keyProcessesDTO.setAssignName(projectKeyProcessesEntity.getAssignName());//整改人
                keyProcessesDTO.setCompleteOn(DateUtils.format(projectKeyProcessesEntity.getCompleteOn(), DateUtils.FORMAT_SHORT));//完成期限
                keyProcessesDTO.setSeverityRating(projectKeyProcessesEntity.getSeverityRating());//严重等级
                if (projectKeyProcessesEntity.getState().equals("AbnormalShutdown")) {
                    keyProcessesDTO.setState("非正常关闭");
                } else {
                    keyProcessesDTO.setState(projectKeyProcessesEntity.getState());//问题状态
                }
                if (projectKeyProcessesEntity.getCompleteOn().after(new Date())) {
                    keyProcessesDTO.setOverdue("是");//是否超期
                } else {
                    keyProcessesDTO.setOverdue("否");
                }
                keyProcessesDTO.setAbnormalShutdown(projectKeyProcessesEntity.getAbnormalShutdown());//非正常关闭
                projectKeyProcessesDTOList.add(keyProcessesDTO);
            }
        }
        return projectKeyProcessesDTOList;
    }

    public void keyProcesses(ProjectKeyProcessesEntity projectKeyProcessesEntity, UserPropertyStaffEntity userPropertyStaffEntity) {
        projectKeyProcessesEntity.setModifyBy(userPropertyStaffEntity.getStaffId());//修改人ID
        projectKeyProcessesEntity.setModifyName(userPropertyStaffEntity.getStaffName());//修改人名称
        projectKeyProcessesEntity.setModifyOn(new Date());//修改时间
        //更新关键工序信息
        projectKeyProcessesRepository.modifyKeyProcesses(projectKeyProcessesEntity);
    }

    public void keyProcessesTargetDetail(KeyProcessesTargetApplyAnnalDTO keyProcessesTargetApplyAnnalDTO, KeyProcessesApplyAnnalDTO keyProcessesApplyAnnalDTO, UserPropertyStaffEntity userPropertyStaffEntity) {
        //工序指标详情
        ProjectKeyProcessesTargetDetailsEntity processesTargetDetailsEntity = new ProjectKeyProcessesTargetDetailsEntity();
        processesTargetDetailsEntity.setId(IdGen.uuid());//工序详情ID
        processesTargetDetailsEntity.setProcessTargetId(keyProcessesTargetApplyAnnalDTO.getProcessTargetId());//工序指标ID
        processesTargetDetailsEntity.setDescription(keyProcessesTargetApplyAnnalDTO.getDescription());//指标描述
        processesTargetDetailsEntity.setType(keyProcessesApplyAnnalDTO.getType());//整改类型
        processesTargetDetailsEntity.setState(keyProcessesTargetApplyAnnalDTO.getQualifiedState());//状态
        processesTargetDetailsEntity.setSerialNumber(keyProcessesTargetApplyAnnalDTO.getSerialNumber());//排序号
        processesTargetDetailsEntity.setCreateBy(userPropertyStaffEntity.getStaffId());//创建人ID
        processesTargetDetailsEntity.setCreateOn(new Date());//创建时间
        boolean detail = projectKeyProcessesRepository.addKeyProcessesTargetDetails(processesTargetDetailsEntity);
        if (detail) {
            //工程图片
            ProjectImagesEntity projectImagesEntity = new ProjectImagesEntity();
            projectImagesEntity.setId(IdGen.uuid());//图片ID
            projectImagesEntity.setBusinessId(processesTargetDetailsEntity.getId());//工序指标详细信息ID
            projectImagesEntity.setUrl(keyProcessesTargetApplyAnnalDTO.getImageUrl());//图片URL
            projectImagesEntity.setType("3");//所属类型：
            projectImagesEntity.setState("1");//状态
            projectImagesEntity.setCreateOn(new Date());//创建时间
            projectImagesEntity.setModifyOn(new Date());//修改时间
            //保存图片信息
            projectImagesRepository.addProjectImagesInfo(projectImagesEntity);
        }
    }

    public void keyProcessesTarget(ProjectKeyProcessesTargetEntity projectKeyProcessesTargetEntity, KeyProcessesTargetApplyAnnalDTO keyProcessesTargetApplyAnnalDTO, UserPropertyStaffEntity userPropertyStaffEntity) {
//        projectKeyProcessesTargetEntity.setQualifiedState(keyProcessesTargetApplyAnnalDTO.getQualifiedState());//指标合格状态
        projectKeyProcessesTargetEntity.setModifyBy(userPropertyStaffEntity.getStaffId());//修改人
        projectKeyProcessesTargetEntity.setModifyOn(new Date());//修改时间
        projectKeyProcessesTargetEntity.setFlag(keyProcessesTargetApplyAnnalDTO.getFlag());//指标审核标识
        //修改关键工序指标信息
        projectKeyProcessesRepository.modifyKeyProcessesTarget(projectKeyProcessesTargetEntity);
    }

    public List<ProjectKeyProcessesCountDTO> searchKeyProcessesCountList(ProjectKeyProcessesDTO keyProcessesDTO,UserInformationEntity userInformationEntity) {
        if (keyProcessesDTO == null) {
            new ErrorApiResult("error_00000002");
        }
        Map map = new HashMap();
        map.put("projectId", keyProcessesDTO.getProjectId());//项目id
        map.put("tenderId", keyProcessesDTO.getTenderId());//标段ID
        map.put("buildingId", keyProcessesDTO.getBuildingId());//楼栋ID
        WebPage webPage = null;
        //获取当前人的菜单权限
        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userInformationEntity.getStaffId());
        List<Object[]> list = null;
        if (f) {
            list = projectKeyProcessesRepository.searchKeyProcessesCountList(map, webPage);
        } else {
            list = projectKeyProcessesRepository.searchKeyProcessesCountList(map, webPage, userInformationEntity.getStaffId());
        }
//        List<Object[]> list = projectKeyProcessesRepository.searchKeyProcessesCountList(map);
        List<ProjectKeyProcessesCountDTO> projectKeyProcessesCountDTOs = new ArrayList<ProjectKeyProcessesCountDTO>();
        int qualifiedAllTotal = 0;//合格总数
        int unqualifiedAllTotal = 0;//不合格总数
        int onePassAllTotal = 0;//一次通过总数
        String qualifiedRate = "";//合格率
        String unqualifiedRate = "";//不合格率
        String onePassRate = "";//一次通过率
        if (list != null && list.size() > 0) {
            for (Object[] obj : list) {
                ProjectKeyProcessesCountDTO projectKeyProcessesCountDTO = new ProjectKeyProcessesCountDTO();
                projectKeyProcessesCountDTO.setBuildingName((String) obj[0]);//楼栋名称
                projectKeyProcessesCountDTO.setTenderName((String) obj[1]);//标段名称
                projectKeyProcessesCountDTO.setProjectName((String) obj[2]);//项目名称
                projectKeyProcessesCountDTO.setTotal(((BigInteger) obj[3]).intValue());//总数
                projectKeyProcessesCountDTO.setQualifiedTotal(((BigInteger) obj[4]).intValue());//合格数
                projectKeyProcessesCountDTO.setUnqualifiedTotal(((BigInteger) obj[5]).intValue());//不合格数
                projectKeyProcessesCountDTO.setOnePassTotal(((BigInteger) obj[6]).intValue());//一次性通过数
                projectKeyProcessesCountDTO.setAbnormalShutdown(((BigInteger) obj[7]).intValue());//非正常关闭
                qualifiedAllTotal += projectKeyProcessesCountDTO.getQualifiedTotal();//合格总数
                unqualifiedAllTotal += projectKeyProcessesCountDTO.getUnqualifiedTotal();//不合格总数
                onePassAllTotal += projectKeyProcessesCountDTO.getOnePassTotal();//一次性通过总数

                projectKeyProcessesCountDTOs.add(projectKeyProcessesCountDTO);
            }
        }
        return projectKeyProcessesCountDTOs;
    }

}
