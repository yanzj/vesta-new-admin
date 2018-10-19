package com.maxrocky.vesta.application.inspectAcceptance.impl;

import com.maxrocky.vesta.application.DTO.QuestionUpdateCheckDTO;
import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.CopyDetailsListDTO;
import com.maxrocky.vesta.application.inspectAcceptance.DTO.*;
import com.maxrocky.vesta.application.inspectAcceptance.inf.InspectAcceptanceService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.ProjectBuildingEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectProjectEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectBuildingRepository;
import com.maxrocky.vesta.domain.baseData.repository.ProjectImagesRepository;
import com.maxrocky.vesta.domain.baseData.repository.ProjectProjectRepository;
import com.maxrocky.vesta.domain.baseData.repository.StaffEmployRepository;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyDetailsEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.repository.DailyPatrolInspectionRepository;
import com.maxrocky.vesta.domain.inspectAcceptance.model.ProjectExamineEntity;
import com.maxrocky.vesta.domain.inspectAcceptance.model.ProjectExamineTargetDetailsEntity;
import com.maxrocky.vesta.domain.inspectAcceptance.model.ProjectExamineTargetEntity;
import com.maxrocky.vesta.domain.inspectAcceptance.repository.InspectAcceptanceRepository;
import com.maxrocky.vesta.domain.inspectAcceptance.repository.ProjectExamineTargetDetailsRepository;
import com.maxrocky.vesta.domain.inspectAcceptance.repository.ProjectExamineTargetRepository;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.RectificationRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by jiazefeng on 2016/10/17.
 */
@Service
public class InspectAcceptanceServiceImpl implements InspectAcceptanceService {
    @Autowired
    private InspectAcceptanceRepository inspectAcceptanceRepository;
    @Autowired
    private ProjectExamineTargetRepository projectExamineTargetRepository;
    @Autowired
    private ProjectImagesRepository projectImagesRepository;
    @Autowired
    private ProjectExamineTargetDetailsRepository projectExamineTargetDetailsRepository;
    @Autowired
    DailyPatrolInspectionRepository dailyPatrolInspectionRepository;
    @Autowired
    RectificationRepository rectificationRepository;
    @Autowired
    ProjectProjectRepository projectProjectRepository;
    @Autowired
    ProjectBuildingRepository projectBuildingRepository;
    @Autowired
    StaffEmployRepository staffEmployRepository;
    @Autowired
    private GetAllClassifyService getAllClassifyService;

    @Override
    public List<InspectAcceptanceDTO> searchInspectAcceptanceList(InspectAcceptanceDTO inspectAcceptanceDTO, WebPage webPage, String staffId) {
        Map map = new HashMap();
        map.put("projectNum", inspectAcceptanceDTO.getProjectId());//项目id
        map.put("buildingId", inspectAcceptanceDTO.getBuildingId());//楼栋id
        map.put("classfiyThree", inspectAcceptanceDTO.getThreeClassification());//三级分类
        map.put("State", inspectAcceptanceDTO.getState());//问题状态
        map.put("startDate", inspectAcceptanceDTO.getStartDate());//开始时间
        map.put("endDate", inspectAcceptanceDTO.getEndDate());//结束时间

        map.put("supplier", inspectAcceptanceDTO.getSupplierName());//责任单位名称
        map.put("supervisorName", inspectAcceptanceDTO.getSupervisorName());//第三方监理名称
        map.put("assignName", inspectAcceptanceDTO.getAssignnName());//整改人名称
//        map.put("partyPrincipalName", inspectAcceptanceDTO.getPartyPrincipalName());//甲方负责人名称
        map.put("severityRating", inspectAcceptanceDTO.getSeverityRating());//严重等级
        //获取当前人的菜单权限
        List<ProjectExamineEntity> inspectAcceptanceEntityList = null;
        if(!StringUtil.isEmpty(inspectAcceptanceDTO.getProjectId())){
            inspectAcceptanceEntityList = inspectAcceptanceRepository.searchInspectAcceptanceList(map, webPage);
        }
        List<InspectAcceptanceDTO> inspectAcceptanceDTOs = new ArrayList<InspectAcceptanceDTO>();
        if (inspectAcceptanceEntityList != null && inspectAcceptanceEntityList.size() > 0) {
            for (ProjectExamineEntity inspectAcceptanceEntity : inspectAcceptanceEntityList) {
                InspectAcceptanceDTO inspectAcceptanceDTO1 = new InspectAcceptanceDTO();
                inspectAcceptanceDTO1.setBatchId(inspectAcceptanceEntity.getBatchId());//批次ID
                inspectAcceptanceDTO1.setProjectId(inspectAcceptanceEntity.getProjectNum());//项目ID
                inspectAcceptanceDTO1.setProjectName(inspectAcceptanceEntity.getProjectName());//项目名称
                inspectAcceptanceDTO1.setBatchName(inspectAcceptanceEntity.getBatchName());//批次名称
                inspectAcceptanceDTO1.setBuildingName(inspectAcceptanceEntity.getBuildingName());//楼栋名称
                inspectAcceptanceDTO1.setCategoryName(inspectAcceptanceEntity.getCategoryName());//检查项
//                inspectAcceptanceDTO1.setPartyPrincipalName(inspectAcceptanceEntity.getPartyPrincipalName());//甲方
                inspectAcceptanceDTO1.setSupervisorName(inspectAcceptanceEntity.getSupervisorName());//第三方监理
                inspectAcceptanceDTO1.setSupplierName(inspectAcceptanceEntity.getSupplierName());//责任单位
                inspectAcceptanceDTO1.setAssignnName(inspectAcceptanceEntity.getAssignnName());//整改人
                inspectAcceptanceDTO1.setCompleteOn(inspectAcceptanceEntity.getCreateOn());//完成时间
                if (inspectAcceptanceEntity.getState().equals("AbnormalShutdown")) {
                    inspectAcceptanceDTO1.setState("非正常关闭");
                } else {
                    inspectAcceptanceDTO1.setState(inspectAcceptanceEntity.getState());//问题状态
                }
                inspectAcceptanceDTO1.setSeverityRating(inspectAcceptanceEntity.getSeverityRating());//严重等级

                if (inspectAcceptanceEntity.getCompleteOn() != null) {
                    if (inspectAcceptanceEntity.getCompleteOn().after(new Date())) {
                        inspectAcceptanceDTO1.setOverdue("否");
                    } else {
                        inspectAcceptanceDTO1.setOverdue("是");
                    }
                }
                inspectAcceptanceDTO1.setAbnormalShutdown(inspectAcceptanceEntity.getAbnormalShutdown());
                inspectAcceptanceDTOs.add(inspectAcceptanceDTO1);
            }
        }
        return inspectAcceptanceDTOs;
    }

    @Override
    public ApiResult addAcceptanceInfo(UserPropertyStaffEntity userPropertyStaffEntity, ProjectAcceptanceQuestionDTO projectAcceptanceQuestionDTO) {
        //projectAcceptanceQuestionDTO.getList() 批次信息
        if (projectAcceptanceQuestionDTO.getList() == null && projectAcceptanceQuestionDTO.getList().size() == 0) {
            return new ErrorApiResult("error_00000002");
        }
        try {
            ProjectAcceptanceQuestionDTO acceptanceQuestionDTO = new ProjectAcceptanceQuestionDTO();
            List<ProjectAcceptanceBatchDTO> projectAcceptanceBatchDTOList = new ArrayList<ProjectAcceptanceBatchDTO>();
            for (ProjectAcceptanceBatchDTO projectAcceptanceBatchDTO : projectAcceptanceQuestionDTO.getList()) {
                if (StringUtil.isEmpty(projectAcceptanceBatchDTO.getProjectNum())) {
                    return ErrorResource.getError("tip_00000572");//项目编码不能为空
                }
                if (StringUtil.isEmpty(projectAcceptanceBatchDTO.getBatchName())) {
                    return ErrorResource.getError("tip_00000580");//批次名称不存在
                }
//                if (StringUtil.isEmpty(projectAcceptanceBatchDTO.getSeverityRating())) {
//                    return ErrorResource.getError("tip_00000574");//严重等级不存在
//                }
                if (StringUtil.isEmpty(projectAcceptanceBatchDTO.getBuildingId())) {
                    return ErrorResource.getError("tip_00000575");//楼栋编码不存在
                }

                if (StringUtil.isEmpty(projectAcceptanceBatchDTO.getSupplierId())) {
                    return ErrorResource.getError("tip_00000578");//责任单位不存在
                }
                if (StringUtil.isEmpty(projectAcceptanceBatchDTO.getAssignId())) {
                    return ErrorResource.getError("tip_00000579");//整改人不存在
                }
                ProjectExamineEntity projectExamineEntity = inspectAcceptanceRepository.getProjectExamineByAppId(projectAcceptanceBatchDTO.getId());
                if (projectExamineEntity != null) {
                    ProjectAcceptanceBatchDTO projectAcceptanceBatchDTO1 = searchAcceptanceBatchInfo(projectExamineEntity.getBatchId());
                    projectAcceptanceBatchDTOList.add(projectAcceptanceBatchDTO1);
                    continue;
                }
                ProjectExamineEntity inspectAcceptanceEntity = new ProjectExamineEntity();
                inspectAcceptanceEntity.setAppId(projectAcceptanceBatchDTO.getId());//APPID
                inspectAcceptanceEntity.setBatchId(IdGen.uuid());//批次ID
                inspectAcceptanceEntity.setBatchName(projectAcceptanceBatchDTO.getBatchName());//批次名称
                inspectAcceptanceEntity.setProjectNum(projectAcceptanceBatchDTO.getProjectNum());//项目ID
                if (StringUtil.isEmpty(projectAcceptanceBatchDTO.getPorjectName())) {
                    ProjectProjectEntity projectProjectEntity = projectProjectRepository.getProjectProjectDetail(projectAcceptanceBatchDTO.getProjectNum());
                    if (projectProjectEntity != null) {
                        inspectAcceptanceEntity.setProjectName(projectProjectEntity.getName());//项目名称
                    }
                } else {
                    inspectAcceptanceEntity.setProjectName(projectAcceptanceBatchDTO.getPorjectName());//项目名称
                }
                inspectAcceptanceEntity.setBuildingId(projectAcceptanceBatchDTO.getBuildingId());//楼栋ID
                if (StringUtil.isEmpty(projectAcceptanceBatchDTO.getBuildingName())) {
                    ProjectBuildingEntity projectBuildingEntity = projectBuildingRepository.getBuildDetail(projectAcceptanceBatchDTO.getBuildingId());
                    if (projectBuildingEntity != null) {
                        inspectAcceptanceEntity.setBuildingName(projectBuildingEntity.getName());//楼栋名称
                    }
                } else {
                    inspectAcceptanceEntity.setBuildingName(projectAcceptanceBatchDTO.getBuildingName());//楼栋名称
                }
                inspectAcceptanceEntity.setFloorStar(projectAcceptanceBatchDTO.getFloorStar());//开始楼层
                inspectAcceptanceEntity.setFloorEnd(projectAcceptanceBatchDTO.getFloorEnd());//结束楼层
                inspectAcceptanceEntity.setSerial(projectAcceptanceBatchDTO.getSerial());//流水段
                inspectAcceptanceEntity.setCompleteOn(DateUtils.parse(projectAcceptanceBatchDTO.getCompleteOn(), DateUtils.FORMAT_SHORT));//完成期限

                inspectAcceptanceEntity.setSupervisorId(userPropertyStaffEntity.getStaffId());//第三方监理ID
                inspectAcceptanceEntity.setSupervisorName(userPropertyStaffEntity.getStaffName());//第三方监理名称
                inspectAcceptanceEntity.setSupplierId(projectAcceptanceBatchDTO.getSupplierId());//责任单位ID
                inspectAcceptanceEntity.setSupplierName(projectAcceptanceBatchDTO.getSupplierName());//责任单位名称
                inspectAcceptanceEntity.setAssignId(projectAcceptanceBatchDTO.getAssignId());//整改人ID
                inspectAcceptanceEntity.setHandlePeopleId(projectAcceptanceBatchDTO.getAssignId());//处理人ID
                //整改人姓名 有则直接存入  无则查询存入
                if (!StringUtil.isEmpty(projectAcceptanceBatchDTO.getAssignName())) {
                    inspectAcceptanceEntity.setAssignnName(projectAcceptanceBatchDTO.getAssignName());//整改人名字
                    inspectAcceptanceEntity.setHandlePeopleName(projectAcceptanceBatchDTO.getAssignName());//处理人名称
                } else {
                    UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(projectAcceptanceBatchDTO.getAssignId());
                    if (UserStaff != null) {
                        inspectAcceptanceEntity.setAssignnName(UserStaff.getStaffName());//整改人名字
                        inspectAcceptanceEntity.setHandlePeopleName(UserStaff.getStaffName());//处理人名称
                    }
                }
                inspectAcceptanceEntity.setCreateBy(userPropertyStaffEntity.getStaffId());//添加人ID
                inspectAcceptanceEntity.setCreateName(userPropertyStaffEntity.getStaffName());//添加人名称
                inspectAcceptanceEntity.setCreateOn(new Date());//创建日期
                inspectAcceptanceEntity.setModifyBy(userPropertyStaffEntity.getStaffId());//修改人
                inspectAcceptanceEntity.setModifyName(userPropertyStaffEntity.getStaffName());
                inspectAcceptanceEntity.setModifyOn(new Date());//修改日期
                inspectAcceptanceEntity.setIsQualified(projectAcceptanceBatchDTO.getIsQualified());//是否合格
                if (projectAcceptanceBatchDTO.getIsQualified().equals("合格")) {
                    inspectAcceptanceEntity.setState("合格");//状态
                    inspectAcceptanceEntity.setIsFirstQualified("合格");
                } else {
                    inspectAcceptanceEntity.setState("整改中");
                }
                inspectAcceptanceEntity.setSeverityRating(projectAcceptanceBatchDTO.getSeverityRating());//严重等级
                inspectAcceptanceEntity.setCheckTime(DateUtils.parse(projectAcceptanceBatchDTO.getCheckTime()));//检查时间

                inspectAcceptanceEntity.setDomain("2");//所属模块
                inspectAcceptanceEntity.setFirstClassification(projectAcceptanceBatchDTO.getFirstSort());//一级分类
                inspectAcceptanceEntity.setFirstClassificationName(projectAcceptanceBatchDTO.getFirstSortName());//一级分类名称
                inspectAcceptanceEntity.setSecondaryClassification(projectAcceptanceBatchDTO.getSecondSort());//二级分类
                inspectAcceptanceEntity.setSecondaryClassificationName(projectAcceptanceBatchDTO.getSecondSortName());//二级分类名称
                inspectAcceptanceEntity.setThreeClassification(projectAcceptanceBatchDTO.getThreeSort());//三级分类
                inspectAcceptanceEntity.setThreeClassificationName(projectAcceptanceBatchDTO.getThreeSortName());//三级分类名称
                inspectAcceptanceEntity.setFourClassification(projectAcceptanceBatchDTO.getFourSort());//四级分类
                inspectAcceptanceEntity.setCategoryName(projectAcceptanceBatchDTO.getCategoryName());//检查项(四级分类名称)
                //保存批次信息
                boolean flag = inspectAcceptanceRepository.addProjectExamineInfo(inspectAcceptanceEntity);
                if (flag) {
                    //工程抄送人
                    if (projectAcceptanceBatchDTO.getCopyDetailsEntities() != null && projectAcceptanceBatchDTO.getCopyDetailsEntities().size() > 0) {
                        ProjectCopyEntity projectCopyEntity = new ProjectCopyEntity();
                        projectCopyEntity.setId(IdGen.uuid());//抄送人ID
                        projectCopyEntity.setSender(userPropertyStaffEntity.getStaffId());//发送人ID
                        projectCopyEntity.setSenderName(userPropertyStaffEntity.getStaffName());//发送人名称
                        projectCopyEntity.setBusiness(inspectAcceptanceEntity.getBatchId());//批次ID
                        projectCopyEntity.setDamain("2");//所属模块
                        projectCopyEntity.setCreateOn(new Date());//添加时间
                        //保存抄送人
                        boolean f = inspectAcceptanceRepository.saveProjectCopy(projectCopyEntity);
                        if (f) {
                            for (CopyDetailsListDTO copyDetailsListDTO : projectAcceptanceBatchDTO.getCopyDetailsEntities()) {
                                ProjectCopyDetailsEntity projectCopyDetailsEntity = new ProjectCopyDetailsEntity();
                                projectCopyDetailsEntity.setId(IdGen.uuid());//详情id
                                projectCopyDetailsEntity.setCopyId(projectCopyEntity.getId());//抄送ID
                                projectCopyDetailsEntity.setMemberId(copyDetailsListDTO.getId());//所选的抄送人ID
                                if (StringUtil.isEmpty(copyDetailsListDTO.getName())) {
                                    UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(copyDetailsListDTO.getId());
                                    if (UserStaff != null) {
                                        projectCopyDetailsEntity.setMemberName(UserStaff.getStaffName());
                                    }
                                } else {
                                    projectCopyDetailsEntity.setMemberName(copyDetailsListDTO.getName());
                                }
                                //保存抄送详情
                                inspectAcceptanceRepository.saveProjectCopyDetails(projectCopyDetailsEntity);
                            }
                        }
                    }

                    //指标信息
                    if (projectAcceptanceBatchDTO.getProjectExamineTargetDetailsDTOs() != null) {
                        for (ProjectExamineTargetDetailsDTO projectExamineTargetDetailsDTO : projectAcceptanceBatchDTO.getProjectExamineTargetDetailsDTOs()) {
                            //工程检查验收指标
                            ProjectExamineTargetEntity projectExamineTargetEntity = new ProjectExamineTargetEntity();
                            projectExamineTargetEntity.setId(IdGen.uuid());//验收指标ID
                            projectExamineTargetEntity.setExamineId(inspectAcceptanceEntity.getBatchId());//批次ID
                            projectExamineTargetEntity.setTargetId(projectExamineTargetDetailsDTO.getTargetId());//指标ID
                            projectExamineTargetEntity.setTargetName(projectExamineTargetDetailsDTO.getTargetName());//指标名称
                            projectExamineTargetEntity.setState(projectExamineTargetDetailsDTO.getIsQualifiedForTarget());//
                            projectExamineTargetEntity.setIsQualified(projectExamineTargetDetailsDTO.getIsQualifiedForTarget());//是否合格
                            projectExamineTargetEntity.setModifyOn(new Date());//修改时间
                            projectExamineTargetEntity.setCreateOn(new Date());//创建时间
                            projectExamineTargetEntity.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
                            projectExamineTargetEntity.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人

                            //保存验收指标信息
                            boolean flag1 = projectExamineTargetRepository.addProjectExamineTargetInfo(projectExamineTargetEntity);
                            if (flag1) {
                                //工程检查验收指标详情
                                ProjectExamineTargetDetailsEntity projectExamineTargetDetailsEntity = new ProjectExamineTargetDetailsEntity();
                                projectExamineTargetDetailsEntity.setId(IdGen.uuid());//详情ID
                                projectExamineTargetDetailsEntity.setExamineTargetId(projectExamineTargetEntity.getId());//验收指标ID
                                projectExamineTargetDetailsEntity.setDescription(projectExamineTargetDetailsDTO.getDescription());//指标详情
                                projectExamineTargetDetailsEntity.setState(projectExamineTargetDetailsDTO.getIsQualifiedForTarget());//状态
                                projectExamineTargetDetailsEntity.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
                                projectExamineTargetDetailsEntity.setCreateOn(new Date());//创建时间

                                //保存验收指标详细信息
                                boolean f = projectExamineTargetDetailsRepository.addProjectExamineTargetInfo(projectExamineTargetDetailsEntity);
                                if (f) {
                                    //工程图片
                                    ProjectImagesEntity projectImagesEntity = new ProjectImagesEntity();
                                    projectImagesEntity.setId(IdGen.uuid());//图片ID
                                    projectImagesEntity.setBusinessId(projectExamineTargetDetailsEntity.getId());//验收指标详细信息ID
                                    projectImagesEntity.setUrl(projectExamineTargetDetailsDTO.getImageUrl());//图片URL
                                    projectImagesEntity.setType("2");//所属类型：
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
                    ProjectAcceptanceBatchDTO projectAcceptanceBatchDTO1 = searchAcceptanceBatchInfo(inspectAcceptanceEntity.getBatchId());
                    projectAcceptanceBatchDTOList.add(projectAcceptanceBatchDTO1);
                } else {
                    //返回失败数据
                    projectAcceptanceBatchDTOList.add(projectAcceptanceBatchDTO);
                }
            }
            acceptanceQuestionDTO.setList(projectAcceptanceBatchDTOList);
            return new SuccessApiResult(acceptanceQuestionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    @Override
    public ApiResult searchAcceptanceListByProjectNum(String projectNum) throws Exception {
        List<ProjectAcceptanceDTO> projectAcceptanceDTOs = new ArrayList<ProjectAcceptanceDTO>();
//        ProjectAcceptanceTotalDTO projectAcceptanceTotalDTO = new ProjectAcceptanceTotalDTO();
        if (StringUtil.isEmpty(projectNum)) {
            new ErrorApiResult("error_00000002");
        }
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(0);
        int hasBeenGetOnTotal = 0;
        int qualifiedToatl = 0;
        int unqualifiedToatl = 0;
        int onePassToatl = 0;
        String resultQualifiedTotal = "0";
        String resultOnePassTotal = "0";
        List<Object[]> list = inspectAcceptanceRepository.searchAcceptanceListByProjectNum(projectNum);
        if (list != null && list.size() > 0) {
            ProjectAcceptanceDTO projectAcceptanceAllDTO = new ProjectAcceptanceDTO();
            for (Object[] obj : list) {
                String resultQualified = "0";
                String resultOnePass = "0";
                if (((BigInteger) obj[1]).intValue() > 0) {
                    resultQualified = numberFormat.format((float) ((BigInteger) obj[2]).intValue() / (float) ((BigInteger) obj[1]).intValue() * 100);
                    resultOnePass = numberFormat.format((float) ((BigInteger) obj[4]).intValue() / (float) ((BigInteger) obj[1]).intValue() * 100);
                }
                String qualifiedRate = resultQualified + "%";//合格率
                String onePassRate = resultOnePass + "%";//一次通过率

                ProjectAcceptanceDTO projectAcceptanceDTO = new ProjectAcceptanceDTO();
                projectAcceptanceDTO.setBuildingName((String) obj[0]);//楼栋名称
                projectAcceptanceDTO.setHasBeenGetOn(((BigInteger) obj[1]).intValue());//已进行
                projectAcceptanceDTO.setUnqualified(((BigInteger) obj[3]).intValue());//不合格数
                projectAcceptanceDTO.setQualified(qualifiedRate);//合格率
                projectAcceptanceDTO.setOnePass(onePassRate);//一次通过率
                projectAcceptanceDTO.setProjectId((String) obj[5]);//项目id
                projectAcceptanceDTO.setBuildingId((String) obj[6]);//楼栋ID


                hasBeenGetOnTotal += projectAcceptanceDTO.getHasBeenGetOn();//已经进行总数
                qualifiedToatl += ((BigInteger) obj[2]).intValue();//合格总数
                unqualifiedToatl += projectAcceptanceDTO.getUnqualified();//不合格总数
                onePassToatl += ((BigInteger) obj[4]).intValue();//一次通过总数

                projectAcceptanceDTOs.add(projectAcceptanceDTO);
            }
            if (hasBeenGetOnTotal > 0) {
                resultQualifiedTotal = numberFormat.format((float) qualifiedToatl / (float) hasBeenGetOnTotal * 100);//总合格率
                resultOnePassTotal = numberFormat.format((float) onePassToatl / (float) hasBeenGetOnTotal * 100);//总一次通过率
            }
            String qualifiedRateTotal = resultQualifiedTotal + "%";//总合格率
            String onePassRateTotal = resultOnePassTotal + "%";//总一次通过率

            projectAcceptanceAllDTO.setBuildingId("totalAll");
            projectAcceptanceAllDTO.setProjectId(projectNum);//项目ID
            projectAcceptanceAllDTO.setHasBeenGetOn(hasBeenGetOnTotal);//已进行总数
            projectAcceptanceAllDTO.setUnqualified(unqualifiedToatl);//不合格总数
            projectAcceptanceAllDTO.setQualified(qualifiedRateTotal);//总合格率
            projectAcceptanceAllDTO.setOnePass(onePassRateTotal);//总一次通过率

            projectAcceptanceDTOs.add(projectAcceptanceAllDTO);

        }
        ModelMap result = new ModelMap();
        result.addAttribute("projectAcceptanceDTOs", projectAcceptanceDTOs);
        return new SuccessApiResult(result);
    }

    @Override
    public ApiResult searchAcceptanceBatchListByParameter(String buildingId, String projectCategoryName) {
        List<Object[]> list = inspectAcceptanceRepository.searchAcceptanceBatchList(buildingId, projectCategoryName);
        List<ProjectAcceptanceBatchDTO> projectAcceptanceBatchDTOList = new ArrayList<ProjectAcceptanceBatchDTO>();
        if (list != null && list.size() > 0) {
            for (Object[] obj : list) {
                ProjectAcceptanceBatchDTO projectAcceptanceBatchDTO = new ProjectAcceptanceBatchDTO();
                projectAcceptanceBatchDTO.setBatchId((String) obj[0]);
                projectAcceptanceBatchDTO.setBatchName((String) obj[1]);
                projectAcceptanceBatchDTO.setState((String) obj[2]);
                projectAcceptanceBatchDTO.setStartDate(DateUtils.format((Date) obj[3]));

                projectAcceptanceBatchDTOList.add(projectAcceptanceBatchDTO);
            }
        }
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("projectAcceptanceBatchDTOList", projectAcceptanceBatchDTOList);
        return new SuccessApiResult(modelMap);
    }

    @Override
    public ProjectAcceptanceBatchDTO searchAcceptanceBatchInfo(String batchId) {
        if (!StringUtil.isEmpty(batchId)) {
            ProjectExamineEntity projectExamineEntity = inspectAcceptanceRepository.searchAcceptanceBatchInfo(batchId);
//            if(projectExamineEntity != null) {
            ProjectAcceptanceBatchDTO projectAcceptanceBatchDTO = new ProjectAcceptanceBatchDTO();

            projectAcceptanceBatchDTO.setId(projectExamineEntity.getAppId());//APPID
            projectAcceptanceBatchDTO.setSelfId((projectExamineEntity.getId()).toString());//自增ID
            projectAcceptanceBatchDTO.setBatchId(projectExamineEntity.getBatchId());//批次ID
            projectAcceptanceBatchDTO.setBatchName(projectExamineEntity.getBatchName() == null ? "" : projectExamineEntity.getBatchName());//批次名称

            projectAcceptanceBatchDTO.setProjectNum(projectExamineEntity.getProjectNum() == null ? "" : projectExamineEntity.getProjectNum());//项目ID
            projectAcceptanceBatchDTO.setPorjectName(projectExamineEntity.getProjectName() == null ? "" : projectExamineEntity.getProjectName());//项目名称

            projectAcceptanceBatchDTO.setCheckTime(DateUtils.format(projectExamineEntity.getCheckTime()) == null ? "" : DateUtils.format(projectExamineEntity.getCheckTime()));//检查时间

            projectAcceptanceBatchDTO.setBuildingId(projectExamineEntity.getBuildingId() == null ? "" : projectExamineEntity.getBuildingId());//楼栋ID
            projectAcceptanceBatchDTO.setBuildingName(projectExamineEntity.getBuildingName() == null ? "" : projectExamineEntity.getBuildingName());//楼栋名称

            projectAcceptanceBatchDTO.setFloorStar(projectExamineEntity.getFloorStar());//开始楼层
            projectAcceptanceBatchDTO.setFloorEnd(projectExamineEntity.getFloorEnd());//结束楼层
            projectAcceptanceBatchDTO.setSerial(projectExamineEntity.getSerial() == null ? "" : projectExamineEntity.getSerial());//流水段

            projectAcceptanceBatchDTO.setSeverityRating(projectExamineEntity.getSeverityRating() == null ? "" : projectExamineEntity.getSeverityRating());//严重等级
            projectAcceptanceBatchDTO.setStartDate(DateUtils.format(projectExamineEntity.getCreateOn()) == null ? "" : DateUtils.format(projectExamineEntity.getCreateOn()));//创建时间
            projectAcceptanceBatchDTO.setModifyTime(DateUtils.format(projectExamineEntity.getModifyOn()) == null ? "" : DateUtils.format(projectExamineEntity.getModifyOn()));//修改时间

            projectAcceptanceBatchDTO.setSupplierId(projectExamineEntity.getSupplierId() == null ? "" : projectExamineEntity.getSupplierId());//责任单位ID
            projectAcceptanceBatchDTO.setSupplierName(projectExamineEntity.getSupplierName() == null ? "" : projectExamineEntity.getSupplierName());//责任单位名称

            projectAcceptanceBatchDTO.setAssignId(projectExamineEntity.getAssignId() == null ? "" : projectExamineEntity.getAssignId());//整改人ID
            projectAcceptanceBatchDTO.setAssignName(projectExamineEntity.getAssignnName() == null ? "" : projectExamineEntity.getAssignnName());//整改人名称

            projectAcceptanceBatchDTO.setHandlePeopleId(projectExamineEntity.getHandlePeopleId() == null ? "" : projectExamineEntity.getHandlePeopleId());//处理人

            projectAcceptanceBatchDTO.setCompleteOn(DateUtils.format(projectExamineEntity.getCompleteOn()) == null ? "" : DateUtils.format(projectExamineEntity.getCompleteOn(), DateUtils.FORMAT_SHORT));//完成期限

            projectAcceptanceBatchDTO.setIsQualified(projectExamineEntity.getIsQualified() == null ? "" : projectExamineEntity.getIsQualified());//是否合格

            projectAcceptanceBatchDTO.setState(projectExamineEntity.getState() == null ? "" : projectExamineEntity.getState());//状态

            projectAcceptanceBatchDTO.setFirstSort(projectExamineEntity.getFirstClassification() == null ? "" : projectExamineEntity.getFirstClassification());//一级分类
            projectAcceptanceBatchDTO.setFirstSortName(projectExamineEntity.getFirstClassificationName() == null ? "" : projectExamineEntity.getFirstClassificationName());//一级分类名称
            projectAcceptanceBatchDTO.setSecondSort(projectExamineEntity.getSecondaryClassification() == null ? "" : projectExamineEntity.getSecondaryClassification());//二级分类
            projectAcceptanceBatchDTO.setSecondSortName(projectExamineEntity.getSecondaryClassificationName() == null ? "" : projectExamineEntity.getSecondaryClassificationName());//二级分类名称
            projectAcceptanceBatchDTO.setThreeSort(projectExamineEntity.getThreeClassification() == null ? "" : projectExamineEntity.getThreeClassification());//三级分类
            projectAcceptanceBatchDTO.setThreeSortName(projectExamineEntity.getThreeClassificationName() == null ? "" : projectExamineEntity.getThreeClassificationName());//三级分类名称
            projectAcceptanceBatchDTO.setFourSort(projectExamineEntity.getFourClassification() == null ? "" : projectExamineEntity.getFourClassification());//四级分类
            projectAcceptanceBatchDTO.setCategoryName(projectExamineEntity.getCategoryName() == null ? "" : projectExamineEntity.getCategoryName());//检查项（四级分类名称）

//            projectAcceptanceBatchDTO.setAbnormalShutdown(projectExamineEntity.getAbnormalShutdown() == null ? "" : projectExamineEntity.getAbnormalShutdown());//非正常关闭

            //抄送人
            List<Object[]> idList = dailyPatrolInspectionRepository.getProjectCopy(projectExamineEntity.getBatchId());//抄送人list
            List<CopyDetailsListDTO> copyDetailsList = new ArrayList<CopyDetailsListDTO>();
            if (idList != null) {
                for (Object[] idObj : idList) {
                    CopyDetailsListDTO copyDetailsListDTO = new CopyDetailsListDTO();
                    copyDetailsListDTO.setBusiness(idObj[0] == null ? "" : idObj[0].toString());
                    copyDetailsListDTO.setId(idObj[1] == null ? "" : idObj[1].toString());
                    copyDetailsListDTO.setName(idObj[2] == null ? "" : idObj[2].toString());
                    copyDetailsList.add(copyDetailsListDTO);
                }
            }
            projectAcceptanceBatchDTO.setCopyDetailsEntities(copyDetailsList);

            //验收指标详细信息
            List<Object[]> list = projectExamineTargetRepository.searchProjectExamineTargetListByBatchId(batchId);
            //验收指标详情
            List<ProjectExamineTargetDetailsDTO> projectExamineTargetDetailsDTOs = new ArrayList<ProjectExamineTargetDetailsDTO>();
            if (list != null && list.size() > 0) {
                for (Object[] obj : list) {
                    ProjectExamineTargetDetailsDTO projectExamineTargetDetailsDTO = new ProjectExamineTargetDetailsDTO();
//                    projectTargetDTO.setTargetId((String) obj[0]);//指标ID
                    projectExamineTargetDetailsDTO.setExaminetTargetId(obj[0] == null ? "" : (String) obj[0]);//验收指标Id
                    projectExamineTargetDetailsDTO.setTargetName(obj[1] == null ? "" : (String) obj[1]);//指标名称
                    projectExamineTargetDetailsDTO.setDescription(obj[3] == null ? "" : (String) obj[3]);//指标描述
                    projectExamineTargetDetailsDTO.setImageUrl(obj[4] == null ? "" : (String) obj[4]);//图片URL
                    projectExamineTargetDetailsDTO.setTargetDescription(obj[5] == null ? "" : (String) obj[5]);//检查指标描述
                    projectExamineTargetDetailsDTO.setIsQualifiedForTarget(obj[2] == null ? "" : (String) obj[2]);//是否合格

                    //整改记录
                    List<Object[]> list1 = projectExamineTargetRepository.searchProjectExamineTargetCheckListByBatchId((String) obj[0]);
                    if (list1 != null && list1.size() > 0) {
                        //整改记录
                        List<ProjectExamineTargetDetailsChangeDTO> projectExamineTargetDetailsChangeDTOs = new ArrayList<ProjectExamineTargetDetailsChangeDTO>();
                        for (Object[] obj1 : list1) {
                            ProjectExamineTargetDetailsChangeDTO projectExamineTargetDetailsChangeDTO = new ProjectExamineTargetDetailsChangeDTO();
                            projectExamineTargetDetailsChangeDTO.setExaminetTargetId(obj1[0] == null ? "" : (String) obj1[0]);//验收指标ID
                            projectExamineTargetDetailsChangeDTO.setExaminetTargetDetailId(obj1[1] == null ? "" : (String) obj1[1]);//验收指标详情ID
                            projectExamineTargetDetailsChangeDTO.setDescription(obj1[2] == null ? "" : (String) obj1[2]);//指标整改描述
                            projectExamineTargetDetailsChangeDTO.setImageUrl(obj1[3] == null ? "" : (String) obj1[3]);//指标整改图片
                            projectExamineTargetDetailsChangeDTO.setChangeTime(obj1[4] == null ? "" : DateUtils.format((Date) obj1[4]));//整改时间

                            projectExamineTargetDetailsChangeDTOs.add(projectExamineTargetDetailsChangeDTO);
                        }
                        projectExamineTargetDetailsDTO.setProjectExamineTargetDetailsChangeDTOs(projectExamineTargetDetailsChangeDTOs);
                    }

                    //验收记录
                    List<Object[]> list2 = projectExamineTargetRepository.searchProjectExamineTargetAcceptanceListByBatchId((String) obj[0]);
                    if (list2 != null && list2.size() > 0) {
                        //验收记录
                        List<ProjectExamineTargetDetailsAcceptanceDTO> projectExamineTargetDetailsAcceptanceDTOs = new ArrayList<ProjectExamineTargetDetailsAcceptanceDTO>();
                        for (Object[] obj2 : list2) {
                            ProjectExamineTargetDetailsAcceptanceDTO projectExamineTargetDetailsAcceptanceDTO = new ProjectExamineTargetDetailsAcceptanceDTO();
                            projectExamineTargetDetailsAcceptanceDTO.setExaminetTargetId(obj2[0] == null ? "" : (String) obj2[0]);//验收指标ID
                            projectExamineTargetDetailsAcceptanceDTO.setExaminetTargetDetailId(obj2[1] == null ? "" : (String) obj2[1]);//验收指标详情ID
                            projectExamineTargetDetailsAcceptanceDTO.setDescription(obj2[3] == null ? "" : (String) obj2[3]);//验收描述
                            projectExamineTargetDetailsAcceptanceDTO.setImageUrl(obj2[4] == null ? "" : (String) obj2[4]);//验收图片
                            projectExamineTargetDetailsAcceptanceDTO.setIsQualifiedForTarget(obj2[5] == null ? "" : (String) obj2[5]);//状态

                            projectExamineTargetDetailsAcceptanceDTOs.add(projectExamineTargetDetailsAcceptanceDTO);
                        }
                        projectExamineTargetDetailsDTO.setProjectExamineTargetDetailsAcceptanceDTOs(projectExamineTargetDetailsAcceptanceDTOs);
                    }
                    projectExamineTargetDetailsDTOs.add(projectExamineTargetDetailsDTO);
                }
            }
            projectAcceptanceBatchDTO.setProjectExamineTargetDetailsDTOs(projectExamineTargetDetailsDTOs);
            return projectAcceptanceBatchDTO;
        }
        return null;
    }

    @Override
    public ApiResult modifyAcceptanceBatchInfo(UserPropertyStaffEntity userPropertyStaffEntity, ProjectAcceptanceQuestionDTO projectAcceptanceQuestionDTO) {
        if (projectAcceptanceQuestionDTO.getList() == null && projectAcceptanceQuestionDTO.getList().size() == 0) {
            return new ErrorApiResult("error_00000002");
        }
        try {
            List<ProjectAcceptanceBatchDTO> projectAcceptanceBatchDTOList = new ArrayList<ProjectAcceptanceBatchDTO>();
            for (ProjectAcceptanceBatchDTO projectAcceptanceBatchDTO : projectAcceptanceQuestionDTO.getList()) {
                if (StringUtil.isEmpty(projectAcceptanceBatchDTO.getBatchId())) {
                    return ErrorResource.getError("tip_00000580");//批次不存在
                }
                //对应指标信息
                if (projectAcceptanceBatchDTO.getProjectExamineTargetDetailsDTOs() != null && projectAcceptanceBatchDTO.getProjectExamineTargetDetailsDTOs().size() > 0) {
                    List<ProjectExamineTargetDetailsEntity> projectExamineTargetDetailsEntityList = new ArrayList<ProjectExamineTargetDetailsEntity>();
                    for (ProjectExamineTargetDetailsDTO projectExamineTargetDetailsDTO : projectAcceptanceBatchDTO.getProjectExamineTargetDetailsDTOs()) {
                        //指标对应的整改记录
                        if (projectExamineTargetDetailsDTO.getProjectExamineTargetDetailsChangeDTOs() != null && projectExamineTargetDetailsDTO.getProjectExamineTargetDetailsChangeDTOs().size() > 0) {
                            //查询验收指标信息
                            ProjectExamineEntity projectExamineEntity = inspectAcceptanceRepository.searchAcceptanceBatchInfo(projectAcceptanceBatchDTO.getBatchId());
                            for (ProjectExamineTargetDetailsChangeDTO projectExamineTargetDetailsChangeDTO : projectExamineTargetDetailsDTO.getProjectExamineTargetDetailsChangeDTOs()) {
                                //如果验收指标详情ID为空，说明是新增的整改记录，就去往库里插入数据；反之，不做处理
                                if (StringUtil.isEmpty(projectExamineTargetDetailsChangeDTO.getExaminetTargetDetailId())) {
                                    if (projectExamineEntity != null) {
                                        projectExamineEntity.setHandlePeopleId(projectExamineEntity.getSupervisorId());//处理人
                                        projectExamineEntity.setHandlePeopleName(projectExamineEntity.getSupervisorName());
                                        projectExamineEntity.setModifyOn(new Date());//修改时间
                                        projectExamineEntity.setModifyBy(userPropertyStaffEntity.getStaffId());//修改人
                                        projectExamineEntity.setModifyName(userPropertyStaffEntity.getStaffName());//修改人名称
                                        //更新批次信息
                                        inspectAcceptanceRepository.modifyAcceptanceBatchInfo(projectExamineEntity);
                                    }
                                    //工程检查验收指标详情
                                    ProjectExamineTargetDetailsEntity projectExamineTargetDetailsEntity = new ProjectExamineTargetDetailsEntity();
                                    projectExamineTargetDetailsEntity.setId(IdGen.uuid());//检查验收指标详情ID
                                    projectExamineTargetDetailsEntity.setExamineTargetId(projectExamineTargetDetailsChangeDTO.getExaminetTargetId());//检查验收指标ID
                                    projectExamineTargetDetailsEntity.setDescription(projectExamineTargetDetailsChangeDTO.getDescription());//指标描述
                                    projectExamineTargetDetailsEntity.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
                                    projectExamineTargetDetailsEntity.setCreateOn(new Date());//创建时间
                                    projectExamineTargetDetailsEntity.setChangeTime(DateUtils.parse(projectExamineTargetDetailsChangeDTO.getChangeTime()));//整改时间
                                    projectExamineTargetDetailsEntity.setRectification("整改记录");//用来标示
                                    //保存验收指标详情
                                    boolean f1 = projectExamineTargetDetailsRepository.addProjectExamineTargetInfo(projectExamineTargetDetailsEntity);
                                    if (f1) {
                                        //工程图片
                                        ProjectImagesEntity projectImagesEntity = new ProjectImagesEntity();
                                        projectImagesEntity.setId(IdGen.uuid());
                                        projectImagesEntity.setBusinessId(projectExamineTargetDetailsEntity.getId());
                                        projectImagesEntity.setUrl(projectExamineTargetDetailsChangeDTO.getImageUrl());
                                        projectImagesEntity.setType("2");
                                        projectImagesEntity.setState("1");
                                        projectImagesEntity.setCreateOn(new Date());
                                        projectImagesEntity.setModifyOn(new Date());
                                        //保存工程图片
                                        projectImagesRepository.addProjectImagesInfo(projectImagesEntity);
                                    }
                                }
                            }
                        }
                        //指标对应的验收记录
                        if (projectExamineTargetDetailsDTO.getProjectExamineTargetDetailsAcceptanceDTOs() != null && projectExamineTargetDetailsDTO.getProjectExamineTargetDetailsAcceptanceDTOs().size() > 0) {
                            //查询验收指标信息
                            ProjectExamineEntity projectExamineEntity = inspectAcceptanceRepository.searchAcceptanceBatchInfo(projectAcceptanceBatchDTO.getBatchId());
                            for (ProjectExamineTargetDetailsAcceptanceDTO projectExamineTargetDetailsAcceptanceDTO : projectExamineTargetDetailsDTO.getProjectExamineTargetDetailsAcceptanceDTOs()) {
                                //如果验收指标详情ID为空，说明是新增的验收记录，就去往库里插入数据；反之，不做处理
                                if (StringUtil.isEmpty(projectExamineTargetDetailsAcceptanceDTO.getExaminetTargetDetailId())) {
                                    if (projectExamineEntity != null) {
                                        projectExamineEntity.setIsQualified(projectAcceptanceBatchDTO.getIsQualified());//是否合格
                                        if (projectAcceptanceBatchDTO.getIsQualified().equals("合格")) {
                                            projectExamineEntity.setState("合格");
                                        } else {
                                            projectExamineEntity.setHandlePeopleId(projectExamineEntity.getAssignId());//处理人
                                            projectExamineEntity.setHandlePeopleName(projectExamineEntity.getAssignnName());
                                        }
                                        projectExamineEntity.setModifyOn(new Date());//修改时间
                                        projectExamineEntity.setModifyBy(userPropertyStaffEntity.getStaffId());//修改人ID
                                        projectExamineEntity.setModifyName(userPropertyStaffEntity.getStaffName());//修改人名称
                                        //更新批次信息
                                        inspectAcceptanceRepository.modifyAcceptanceBatchInfo(projectExamineEntity);
                                    }
                                    //验收指标信息
                                    if (projectExamineTargetDetailsAcceptanceDTO.getIsQualifiedForTarget().equals("合格")) {
                                        ProjectExamineTargetEntity projectExamineTargetEntity = projectExamineTargetRepository.getProjectExamineTargetById(projectExamineTargetDetailsAcceptanceDTO.getExaminetTargetId());
                                        if (projectExamineEntity != null) {
                                            projectExamineTargetEntity.setState(projectExamineTargetDetailsAcceptanceDTO.getIsQualifiedForTarget());
                                            projectExamineTargetEntity.setIsQualified(projectExamineTargetDetailsAcceptanceDTO.getIsQualifiedForTarget());
                                            projectExamineTargetEntity.setModifyBy(userPropertyStaffEntity.getStaffId());//修改人
                                            projectExamineTargetEntity.setModifyOn(new Date());//修改时间
                                            //修改验收指标信息
                                            projectExamineTargetRepository.updateProjectExamineTarget(projectExamineTargetEntity);
                                        }
                                    }
                                    //工程检查验收指标详情
                                    ProjectExamineTargetDetailsEntity projectExamineTargetDetailsEntity = new ProjectExamineTargetDetailsEntity();
                                    projectExamineTargetDetailsEntity.setId(IdGen.uuid());//详情ID
                                    projectExamineTargetDetailsEntity.setExamineTargetId(projectExamineTargetDetailsAcceptanceDTO.getExaminetTargetId());//验收指标ID
                                    projectExamineTargetDetailsEntity.setDescription(projectExamineTargetDetailsAcceptanceDTO.getDescription());//描述
                                    projectExamineTargetDetailsEntity.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
                                    projectExamineTargetDetailsEntity.setCreateOn(new Date());//创建时间
                                    projectExamineTargetDetailsEntity.setAcceptance("验收记录");
                                    projectExamineTargetDetailsEntity.setState(projectExamineTargetDetailsAcceptanceDTO.getIsQualifiedForTarget());//状态
                                    //保存验收指标详情
                                    boolean f1 = projectExamineTargetDetailsRepository.addProjectExamineTargetInfo(projectExamineTargetDetailsEntity);
                                    if (f1) {
                                        //工程图片
                                        ProjectImagesEntity projectImagesEntity = new ProjectImagesEntity();
                                        projectImagesEntity.setId(IdGen.uuid());
                                        projectImagesEntity.setBusinessId(projectExamineTargetDetailsEntity.getId());
                                        projectImagesEntity.setUrl(projectExamineTargetDetailsAcceptanceDTO.getImageUrl());
                                        projectImagesEntity.setType("2");
                                        projectImagesEntity.setState("1");
                                        projectImagesEntity.setCreateOn(new Date());
                                        projectImagesEntity.setModifyOn(new Date());
                                        //保存工程图片
                                        projectImagesRepository.addProjectImagesInfo(projectImagesEntity);
                                    }
                                }
                            }
                        }
                    }
                }
                //返回成功数据
                ProjectAcceptanceBatchDTO projectAcceptanceBatchDTO1 = searchAcceptanceBatchInfo(projectAcceptanceBatchDTO.getBatchId());
                projectAcceptanceBatchDTOList.add(projectAcceptanceBatchDTO1);
            }
            projectAcceptanceQuestionDTO.setList(projectAcceptanceBatchDTOList);
            return new SuccessApiResult(projectAcceptanceQuestionDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    @Override
    public QuestionUpdateCheckDTO searchToUpdateByType(String id, String beginDateNew, String projectNum) {
//        boolean checkFlag = inspectAcceptanceRepository.searchToUpdateForAcceptance(id, beginDateNew, projectNum);
//        if (checkFlag) {
//            return new QuestionUpdateCheckDTO("Y");
//        }
        return new QuestionUpdateCheckDTO("N");
    }

    @Override
    public ProjectAcceptanceQuestionDTO getAllProjectAcceptanceQuestion(String id, String timeStamp, String projectNum, UserPropertyStaffEntity userPropertyStaffEntity) {
        Date date = new Date();
        Date day7Before = DateUtils.get7Days(date);
        String day7Ago = DateUtils.format(day7Before);
//        System.out.println(DateUtils.format(day7Before));

        //判断权限
        String type = "1";
        if (staffEmployRepository.checkOwner(userPropertyStaffEntity.getStaffId(), projectNum, "1")) {//甲方
            //甲方   该项目下所有数据
            type = "1";
        } else {
            String chec = staffEmployRepository.getPurviewName(userPropertyStaffEntity.getStaffId(), projectNum);
            if ("4".equals(chec)) {
                //乙方   处理人为自己 + 完成状态  乙方负责人为自己
                type = "3";
            } else if ("5".equals(chec)) {
                //第三方监理   自己创建 + 第三方监理为自己 + 抄送自己 + 处理人为自己
                type = "2";
            }
        }
        ProjectAcceptanceQuestionDTO projectAcceptanceQuestionDTO = new ProjectAcceptanceQuestionDTO();
        List<ProjectAcceptanceBatchDTO> projectAcceptanceBatchDTOList = new ArrayList<ProjectAcceptanceBatchDTO>();
        List<Object[]> listAll = inspectAcceptanceRepository.getAllProjectAcceptanceQuestion(id, timeStamp, projectNum, day7Ago,userPropertyStaffEntity.getStaffId(), type);
        if (listAll != null && listAll.size() > 0) {
            for (Object[] objAll : listAll) {
                ProjectAcceptanceBatchDTO  projectAcceptanceBatchDTO = searchAcceptanceBatchInfo(objAll[1].toString());
                projectAcceptanceBatchDTOList.add(projectAcceptanceBatchDTO);
            }
        }
        if (projectAcceptanceBatchDTOList != null && projectAcceptanceBatchDTOList.size() > 0) {
            projectAcceptanceQuestionDTO.setId(projectAcceptanceBatchDTOList.get(0).getSelfId());
            projectAcceptanceQuestionDTO.setTimeStamp(projectAcceptanceBatchDTOList.get(0).getModifyTime());
            projectAcceptanceQuestionDTO.setList(projectAcceptanceBatchDTOList);
        }
        return projectAcceptanceQuestionDTO;
    }

    @Override
    public List<CheckForUpdateToAcceptanceDTO> checkForUpdatesToAcceptance(CheckForUpdate checkForUpdate, UserPropertyStaffEntity userPropertyStaffEntity) {
        List<CheckForUpdateToAcceptanceDTO> checkForUpdateToAcceptanceDTOList = checkForUpdate.getList();
        Date date = new Date();
        Date day7Before = DateUtils.get7Days(date);
        String day7Ago = DateUtils.format(day7Before);

        List<CheckForUpdateToAcceptanceDTO> checkForUpdateToAcceptanceDTOs = new ArrayList<CheckForUpdateToAcceptanceDTO>();
        if (checkForUpdateToAcceptanceDTOList != null && checkForUpdateToAcceptanceDTOList.size() > 0) {
            for (CheckForUpdateToAcceptanceDTO checkForUpdateToAcceptanceDTO : checkForUpdateToAcceptanceDTOList) {
                CheckForUpdateToAcceptanceDTO updateToAcceptanceDTO = new CheckForUpdateToAcceptanceDTO();

                //判断权限
                String type = "1";
                if (staffEmployRepository.checkOwner(userPropertyStaffEntity.getStaffId(), checkForUpdateToAcceptanceDTO.getProjectNum(), "1")) {//甲方
                    //甲方   该项目下所有数据
                    type = "1";
                } else {
                    String chec = staffEmployRepository.getPurviewName(userPropertyStaffEntity.getStaffId(), checkForUpdateToAcceptanceDTO.getProjectNum());
                    if ("4".equals(chec)) {
                        //乙方   处理人为自己 + 完成状态  乙方负责人为自己
                        type = "3";
                    } else if ("5".equals(chec)) {
                        //第三方监理   自己创建 + 第三方监理为自己 + 抄送自己 + 处理人为自己
                        type = "2";
                    }
                }
                String time = "";
                if (!StringUtil.isEmpty(checkForUpdateToAcceptanceDTO.getTimeStamp())) {
                    time = DateUtils.format(DateUtils.parse(checkForUpdateToAcceptanceDTO.getTimeStamp(), "yyyyMMddHHmmss"));
                }
                boolean checkFlag = inspectAcceptanceRepository.searchToUpdateForAcceptance(checkForUpdateToAcceptanceDTO.getId(), time, checkForUpdateToAcceptanceDTO.getProjectNum(), userPropertyStaffEntity.getStaffId(), type,day7Ago);
                if (checkFlag) {
                    updateToAcceptanceDTO.setUpdateFlag("Y");
                } else {
                    updateToAcceptanceDTO.setUpdateFlag("N");
                }
                updateToAcceptanceDTO.setProjectNum(checkForUpdateToAcceptanceDTO.getProjectNum());
                updateToAcceptanceDTO.setId(checkForUpdateToAcceptanceDTO.getId());
                updateToAcceptanceDTO.setTimeStamp(checkForUpdateToAcceptanceDTO.getTimeStamp());
                checkForUpdateToAcceptanceDTOs.add(updateToAcceptanceDTO);
            }
        }
        return checkForUpdateToAcceptanceDTOs;
    }

    @Override
    public InspectAcceptanceDTO searchAcceptanceBatchInfoByBatchId(String batchId) {
        if (batchId != null && !batchId.equals("")) {
            ProjectExamineEntity projectExamineEntity = inspectAcceptanceRepository.searchAcceptanceBatchInfo(batchId);
            if (projectExamineEntity != null) {
                InspectAcceptanceDTO inspectAcceptanceDTO = new InspectAcceptanceDTO();
                inspectAcceptanceDTO.setBatchId(projectExamineEntity.getBatchId());//批次ID
                inspectAcceptanceDTO.setBatchName(projectExamineEntity.getBatchName());//批次名称
                inspectAcceptanceDTO.setProjectId(projectExamineEntity.getProjectNum());//项目ID
                inspectAcceptanceDTO.setProjectName(projectExamineEntity.getProjectName());//项目名称
                inspectAcceptanceDTO.setCheckTime(projectExamineEntity.getCheckTime());//检查时间
                inspectAcceptanceDTO.setBuildingName(projectExamineEntity.getBuildingName());//楼栋名称
                inspectAcceptanceDTO.setFloorStar(projectExamineEntity.getFloorStar());//开始楼层
                inspectAcceptanceDTO.setFloorEnd(projectExamineEntity.getFloorEnd());//结束楼层
                inspectAcceptanceDTO.setSerial(projectExamineEntity.getSerial());//流水段
                inspectAcceptanceDTO.setSeverityRating(projectExamineEntity.getSeverityRating());//严重等级
                inspectAcceptanceDTO.setSupplierName(projectExamineEntity.getSupplierName());//责任单位名称
                inspectAcceptanceDTO.setAssignnName(projectExamineEntity.getAssignnName());//整改人名称
                inspectAcceptanceDTO.setCompleteOn(projectExamineEntity.getCompleteOn());//完成期限
                if (projectExamineEntity.getState().equals("AbnormalShutdown")) {
                    inspectAcceptanceDTO.setState("非正常关闭");//状态
                } else {
                    inspectAcceptanceDTO.setState(projectExamineEntity.getState());//状态
                }
                inspectAcceptanceDTO.setCategoryName(projectExamineEntity.getCategoryName());//检查项
                inspectAcceptanceDTO.setCreatDate(projectExamineEntity.getCreateOn());//创建时间
                inspectAcceptanceDTO.setCreatBy(projectExamineEntity.getCreateName());//创建人
                inspectAcceptanceDTO.setAbnormalShutdown(projectExamineEntity.getAbnormalShutdown());//非正常关闭
                inspectAcceptanceDTO.setModifyByName(projectExamineEntity.getModifyName());//修改人
                inspectAcceptanceDTO.setModifyDate(DateUtils.format(projectExamineEntity.getModifyOn(), DateUtils.FORMAT_LONG));//修改时间
                //抄送人
                List<Object[]> idList = dailyPatrolInspectionRepository.getProjectCopy(projectExamineEntity.getBatchId());//抄送人list
                List<CopyDetailsListDTO> copyDetailsList = new ArrayList<CopyDetailsListDTO>();
                if (idList != null) {
                    for (Object[] idObj : idList) {
                        CopyDetailsListDTO copyDetailsListDTO = new CopyDetailsListDTO();
                        copyDetailsListDTO.setBusiness(idObj[0] == null ? "" : idObj[0].toString());
                        copyDetailsListDTO.setId(idObj[1] == null ? "" : idObj[1].toString());
                        copyDetailsListDTO.setName(idObj[2] == null ? "" : idObj[2].toString());
                        copyDetailsList.add(copyDetailsListDTO);
                    }
                }
                inspectAcceptanceDTO.setCopyDetailsEntities(copyDetailsList);

                //检查验收指标
                List<Object[]> list = projectExamineTargetRepository.searchProjectExamineTargetListByBatchId(batchId);
                List<ProjectTargetDTO> projectTargetDTOList = new ArrayList<ProjectTargetDTO>();
                if (list != null && list.size() > 0) {
                    for (Object[] obj : list) {
                        ProjectTargetDTO projectTargetDTO = new ProjectTargetDTO();
                        projectTargetDTO.setTargetName((String) obj[1]);
                        projectTargetDTO.setIsQualifiedForTarget((String) obj[2]);
                        projectTargetDTO.setDescription((String) obj[3]);
                        projectTargetDTO.setTargetImgUrl((String) obj[4]);
                        projectTargetDTO.setTargetDescripion((String) obj[5]);

                        projectTargetDTOList.add(projectTargetDTO);

                        //整改记录
                        List<Object[]> list1 = projectExamineTargetRepository.searchProjectExamineTargetCheckListByBatchId((String) obj[0]);

                        if (list1 != null && list1.size() > 0) {
                            //整改记录
                            List<ProjectTargetDTO> projectTargetChangDTOList = new ArrayList<ProjectTargetDTO>();
                            for (Object[] obj1 : list1) {
                                ProjectTargetDTO projectTargetDTO1 = new ProjectTargetDTO();
                                projectTargetDTO1.setTargetName((String) obj[1]);
                                projectTargetDTO1.setDescription((String) obj1[2]);
                                projectTargetDTO1.setTargetImgUrl((String) obj1[3]);
                                projectTargetDTO1.setIsQualifiedForTarget((String) obj1[5]);

                                projectTargetChangDTOList.add(projectTargetDTO1);
                            }
                            projectTargetDTO.setProjectTargetChangeDTOList(projectTargetChangDTOList);
                        }


                        //验收记录
                        List<Object[]> list2 = projectExamineTargetRepository.searchProjectExamineTargetAcceptanceListByBatchId((String) obj[0]);
                        if (list2 != null && list2.size() > 0) {
                            List<ProjectTargetDTO> projectTargetAcceptanceDTOList = new ArrayList<ProjectTargetDTO>();
                            for (Object[] obj2 : list2) {
                                ProjectTargetDTO projectTargetDTO2 = new ProjectTargetDTO();
                                projectTargetDTO2.setTargetName((String) obj[1]);
                                projectTargetDTO2.setDescription((String) obj2[3]);
                                projectTargetDTO2.setTargetImgUrl((String) obj2[4]);
                                projectTargetDTO2.setIsQualifiedForTarget((String) obj2[2]);
                                projectTargetAcceptanceDTOList.add(projectTargetDTO2);
                            }
                            projectTargetDTO.setProjectTargetAcceptanceDTOList(projectTargetAcceptanceDTOList);
                        }
                    }
//                    inspectAcceptanceDTO.setProjectTargetChangeDTOList(projectTargetChangDTOList);
//                    inspectAcceptanceDTO.setProjectTargetAcceptanceDTOList(projectTargetAcceptanceDTOList);
                }
                inspectAcceptanceDTO.setProjectTargetDTOList(projectTargetDTOList);
                return inspectAcceptanceDTO;
            }
        }
        return null;
    }

    /**
     * 按检索条件查询信息（不带分页）
     *
     * @param inspectAcceptanceDTO
     * @return
     */
    @Override
    public List<InspectAcceptanceDTO> searchInspectAcceptanceAllList(InspectAcceptanceDTO inspectAcceptanceDTO, UserInformationEntity userInformationEntity) {
        Map map = new HashMap();
        map.put("projectNum", inspectAcceptanceDTO.getProjectId());//项目id
        map.put("buildingId", inspectAcceptanceDTO.getBuildingId());//楼栋id
        map.put("classfiyThree", inspectAcceptanceDTO.getThreeClassification());//三级分类
        map.put("State", inspectAcceptanceDTO.getState());//问题状态
        map.put("startDate", inspectAcceptanceDTO.getStartDate());//开始时间
        map.put("endDate", inspectAcceptanceDTO.getEndDate());//结束时间
        map.put("supplier", inspectAcceptanceDTO.getSupplierName());//责任单位名称
        map.put("supervisorName", inspectAcceptanceDTO.getSupervisorName());//第三方监理名称
        map.put("assignName", inspectAcceptanceDTO.getAssignnName());//整改人名称
        map.put("severityRating", inspectAcceptanceDTO.getSeverityRating());//严重等级
        //获取当前人的菜单权限
        WebPage webPage = null;
//        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userInformationEntity.getStaffId());
        List<ProjectExamineEntity> inspectAcceptanceEntityList = null;
        if(!StringUtil.isEmpty(inspectAcceptanceDTO.getProjectId())){
            inspectAcceptanceEntityList = inspectAcceptanceRepository.searchInspectAcceptanceList(map, webPage);
        }
//        if (f) {
//            inspectAcceptanceEntityList = inspectAcceptanceRepository.searchInspectAcceptanceList(map, webPage);
//        } else {
//            inspectAcceptanceEntityList = inspectAcceptanceRepository.searchInspectAcceptanceList(map, webPage, userInformationEntity.getStaffId());
//        }
//        List<ProjectExamineEntity> inspectAcceptanceEntityList = inspectAcceptanceRepository.searchInspectAcceptanceAllList(map);
        List<InspectAcceptanceDTO> inspectAcceptanceDTOs = new ArrayList<InspectAcceptanceDTO>();
        if (inspectAcceptanceEntityList != null && inspectAcceptanceEntityList.size() > 0) {
            for (ProjectExamineEntity inspectAcceptanceEntity : inspectAcceptanceEntityList) {
                InspectAcceptanceDTO inspectAcceptanceDTO1 = new InspectAcceptanceDTO();
                inspectAcceptanceDTO1.setBatchId(inspectAcceptanceEntity.getBatchId());//批次ID
                inspectAcceptanceDTO1.setProjectName(inspectAcceptanceEntity.getProjectName());//项目名称
                inspectAcceptanceDTO1.setBatchName(inspectAcceptanceEntity.getBatchName());//批次名称
                inspectAcceptanceDTO1.setBuildingName(inspectAcceptanceEntity.getBuildingName());//楼栋名称
                inspectAcceptanceDTO1.setCategoryName(inspectAcceptanceEntity.getCategoryName());//检查项
//                inspectAcceptanceDTO1.setPartyPrincipalName(inspectAcceptanceEntity.getPartyPrincipalName());//甲方
                inspectAcceptanceDTO1.setSupervisorName(inspectAcceptanceEntity.getSupervisorName());//第三方监理
                inspectAcceptanceDTO1.setSupplierName(inspectAcceptanceEntity.getSupplierName());//责任单位
                inspectAcceptanceDTO1.setAssignnName(inspectAcceptanceEntity.getAssignnName());//整改人
                inspectAcceptanceDTO1.setCompleteOn(inspectAcceptanceEntity.getCreateOn());//等级时间
                if (inspectAcceptanceEntity.getState().equals("AbnormalShutdown")) {
                    inspectAcceptanceDTO1.setState("非正常关闭");
                } else {
                    inspectAcceptanceDTO1.setState(inspectAcceptanceEntity.getState());//问题状态
                }
                inspectAcceptanceDTO1.setSeverityRating(inspectAcceptanceEntity.getSeverityRating());//严重等级
                if (inspectAcceptanceEntity.getCompleteOn() != null) {
                    if (inspectAcceptanceEntity.getCompleteOn().after(new Date())) {
                        inspectAcceptanceDTO1.setOverdue("是");
                    } else {
                        inspectAcceptanceDTO1.setOverdue("否");
                    }
                }
                inspectAcceptanceDTOs.add(inspectAcceptanceDTO1);
            }
        }
        return inspectAcceptanceDTOs;
    }

    /**
     * 查询全部信息
     *
     * @return
     */
    @Override
    public List<InspectAcceptanceDTO> searchInspectAcceptanceAllList() {
        List<ProjectExamineEntity> inspectAcceptanceEntityList = inspectAcceptanceRepository.searchInspectAcceptanceAllList();
        List<InspectAcceptanceDTO> inspectAcceptanceDTOs = new ArrayList<InspectAcceptanceDTO>();
        if (inspectAcceptanceEntityList != null && inspectAcceptanceEntityList.size() > 0) {
            for (ProjectExamineEntity inspectAcceptanceEntity : inspectAcceptanceEntityList) {
                InspectAcceptanceDTO inspectAcceptanceDTO1 = new InspectAcceptanceDTO();
                inspectAcceptanceDTO1.setBatchId(inspectAcceptanceEntity.getBatchId());//批次ID
                inspectAcceptanceDTO1.setProjectName(inspectAcceptanceEntity.getProjectName());//项目名称
                inspectAcceptanceDTO1.setBatchName(inspectAcceptanceEntity.getBatchName());//批次名称
                inspectAcceptanceDTO1.setBuildingName(inspectAcceptanceEntity.getBuildingName());//楼栋名称
                inspectAcceptanceDTO1.setCategoryName(inspectAcceptanceEntity.getCategoryName());//检查项
//                inspectAcceptanceDTO1.setPartyPrincipalName(inspectAcceptanceEntity.getPartyPrincipalName());//甲方
                inspectAcceptanceDTO1.setSupervisorName(inspectAcceptanceEntity.getSupervisorName());//第三方监理
                inspectAcceptanceDTO1.setSupplierName(inspectAcceptanceEntity.getSupplierName());//责任单位
                inspectAcceptanceDTO1.setAssignnName(inspectAcceptanceEntity.getAssignnName());//整改人
                inspectAcceptanceDTO1.setCompleteOn(inspectAcceptanceEntity.getCreateOn());//等级时间
                if (inspectAcceptanceEntity.getState().equals("AbnormalShutdown")) {
                    inspectAcceptanceDTO1.setState("非正常关闭");
                } else {
                    inspectAcceptanceDTO1.setState(inspectAcceptanceEntity.getState());//问题状态
                }
                inspectAcceptanceDTO1.setSeverityRating(inspectAcceptanceEntity.getSeverityRating());//严重等级
                if (inspectAcceptanceEntity.getCompleteOn() != null) {
                    if (inspectAcceptanceEntity.getCompleteOn().after(new Date())) {
                        inspectAcceptanceDTO1.setOverdue("是");
                    } else {
                        inspectAcceptanceDTO1.setOverdue("否");
                    }
                }
                inspectAcceptanceDTOs.add(inspectAcceptanceDTO1);
            }
        }
        return inspectAcceptanceDTOs;
    }

    @Override
    public void exportExcel(String title, String[] headers, OutputStream out, InspectAcceptanceDTO inspectAcceptanceDTO, UserInformationEntity userInformationEntity) throws Exception {
        List<InspectAcceptanceDTO> inspectAcceptanceDTOs = this.searchInspectAcceptanceAllList(inspectAcceptanceDTO, userInformationEntity);
        // 导出数据
        ExportExcel<InspectAcceptanceExcelDTO> ex = new ExportExcel<InspectAcceptanceExcelDTO>();

        List<InspectAcceptanceExcelDTO> inspectAcceptanceExcelDTOs = new ArrayList<InspectAcceptanceExcelDTO>();

        if (inspectAcceptanceDTOs != null && inspectAcceptanceDTOs.size() > 0) {
            int num = 1;
            for (InspectAcceptanceDTO inspectAcceptanceDTO1 : inspectAcceptanceDTOs) {

                InspectAcceptanceExcelDTO exportExcelByInspectAcceptanceDTO = new InspectAcceptanceExcelDTO();
                exportExcelByInspectAcceptanceDTO.setSerialNumber(num++);//序号
//                exportExcelByInspectAcceptanceDTO.setOverdue(inspectAcceptanceDTO1.getOverdue());//超期
                exportExcelByInspectAcceptanceDTO.setBatchName(inspectAcceptanceDTO1.getBatchName());//批次名称
                exportExcelByInspectAcceptanceDTO.setProjectName(inspectAcceptanceDTO1.getProjectName());//项目名称
                exportExcelByInspectAcceptanceDTO.setBuildingName(inspectAcceptanceDTO1.getBuildingName());//楼栋名称
                exportExcelByInspectAcceptanceDTO.setCategoryName(inspectAcceptanceDTO1.getCategoryName());//检查项
                exportExcelByInspectAcceptanceDTO.setSeverityRating(inspectAcceptanceDTO1.getSeverityRating());//严重等级
                exportExcelByInspectAcceptanceDTO.setSupervisorName(inspectAcceptanceDTO1.getSupervisorName());//第三方监理
                exportExcelByInspectAcceptanceDTO.setSupplierName(inspectAcceptanceDTO1.getSupplierName());//责任单位
                exportExcelByInspectAcceptanceDTO.setAssignnName(inspectAcceptanceDTO1.getAssignnName());//整改人
                exportExcelByInspectAcceptanceDTO.setCompleteOn(inspectAcceptanceDTO1.getCompleteOn());//登记时间
                exportExcelByInspectAcceptanceDTO.setState(inspectAcceptanceDTO1.getState());//问题状态

                inspectAcceptanceExcelDTOs.add(exportExcelByInspectAcceptanceDTO);
            }
        }
        ex.exportExcel(title, headers, inspectAcceptanceExcelDTOs, out, "yyyy-MM-dd");
//            out = new FileOutputStream("d:\\" + System.getProperties()
//                    + "报警信息.xls");
        System.out.println("excel导出成功！");
    }

    @Override
    public ProjectAcceptanceStatisticsDTO searchInspectAcceptanceCountList(InspectAcceptanceDTO inspectAcceptanceDTO, WebPage webPage, String staffId) {
        Map map = new HashMap();
        map.put("projectId", inspectAcceptanceDTO.getProjectId());//项目id
        map.put("tenderId", inspectAcceptanceDTO.getTenderId());//标段ID
        map.put("buildingId", inspectAcceptanceDTO.getBuildingId());//楼栋ID
        List<ProjectAcceptanceDTO> projectAcceptanceDTOs = new ArrayList<ProjectAcceptanceDTO>();
        if (inspectAcceptanceDTO == null) {
            new ErrorApiResult("error_00000002");
        }
        int qualifiedAllToatl = 0;//合格总数
        int unqualifiedAllToatl = 0;//不合格总数
        int onePassAllToatl = 0;//一次通过总数
        String qualifiedRate = "";//合格率
        String unqualifiedRate = "";//不合格率
        String onePassRate = "";//一次通过率
        String resultQualified = "0";
        String resultUnqualified = "0";
        String resultOnePass = "0";
        int count = 0;
//        int count = inspectAcceptanceRepository.searchInspectAcceptanceCount(map);
        int pageCount = 0;
        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(staffId);
        List<Object[]> list = null;
        List<Object[]> rateList = null;
        if (f) {
            pageCount = inspectAcceptanceRepository.getCount(map);
            webPage.setRecordCount(pageCount);
            list = inspectAcceptanceRepository.searchAcceptanceCountList(map, webPage);
            rateList = inspectAcceptanceRepository.searchAcceptanceCountList(map);
        } else {
            pageCount = inspectAcceptanceRepository.getCount(map, staffId);
            webPage.setRecordCount(pageCount);
            list = inspectAcceptanceRepository.searchAcceptanceCountList(map, webPage, staffId);
            rateList = inspectAcceptanceRepository.searchAcceptanceCountList(map, staffId);
        }
//        List<Object[]> list = inspectAcceptanceRepository.searchAcceptanceCountList(map, webPage, staffId);
        ProjectAcceptanceStatisticsDTO projectAcceptanceStatisticsDTO = new ProjectAcceptanceStatisticsDTO();
        if (list != null && list.size() > 0) {
            List<InspectAcceptanceCountDTO> inspectAcceptanceCountDTOList = new ArrayList<InspectAcceptanceCountDTO>();
            for (Object[] obj : list) {
                InspectAcceptanceCountDTO inspectAcceptanceCountDTO = new InspectAcceptanceCountDTO();
                inspectAcceptanceCountDTO.setBuildingName((String) obj[0]);//楼栋名称
                inspectAcceptanceCountDTO.setTenderName((String) obj[1]);//标段名称
                inspectAcceptanceCountDTO.setProjectName((String) obj[2]);//项目名称
//                inspectAcceptanceCountDTO.setTotal(((BigInteger) obj[3]).intValue());//全部
                inspectAcceptanceCountDTO.setQualifiedToatl(((BigInteger) obj[4]).intValue());//合格
                inspectAcceptanceCountDTO.setUnqualifiedToatl(((BigInteger) obj[5]).intValue());//不合格
                inspectAcceptanceCountDTO.setOnePassToatl(((BigInteger) obj[6]).intValue());//一次通过
                inspectAcceptanceCountDTO.setAbnormalShutdown(((BigInteger) obj[7]).intValue());//非正常关闭数
                inspectAcceptanceCountDTO.setTotal(((BigInteger) obj[4]).intValue() + ((BigInteger) obj[5]).intValue() + ((BigInteger) obj[7]).intValue());//全部

                inspectAcceptanceCountDTOList.add(inspectAcceptanceCountDTO);
            }
            projectAcceptanceStatisticsDTO.setList(inspectAcceptanceCountDTOList);
        }
        if (rateList != null && rateList.size() > 0) {
            for (Object[] obj : rateList) {
                qualifiedAllToatl += ((BigInteger) obj[4]).intValue();//合格总数
                unqualifiedAllToatl += ((BigInteger) obj[5]).intValue();//不合格总数
                onePassAllToatl += ((BigInteger) obj[6]).intValue();//一次通过总数
                count += ((BigInteger) obj[4]).intValue() + ((BigInteger) obj[5]).intValue() + ((BigInteger) obj[7]).intValue();//全部总数
            }
            // 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(0);
            if (count > 0) {
                resultQualified = numberFormat.format((float) qualifiedAllToatl / (float) count * 100);
                resultUnqualified = numberFormat.format((float) unqualifiedAllToatl / (float) count * 100);
                resultOnePass = numberFormat.format((float) onePassAllToatl / (float) count * 100);
            }
            qualifiedRate = resultQualified + "%";//合格率
            unqualifiedRate = resultUnqualified + "%";//不合格率
            onePassRate = resultOnePass + "%";//一次通过率

            projectAcceptanceStatisticsDTO.setQualifiedRate(qualifiedRate);
            projectAcceptanceStatisticsDTO.setUnqualifiedRate(unqualifiedRate);
            projectAcceptanceStatisticsDTO.setOnePassRate(onePassRate);

        }
        return projectAcceptanceStatisticsDTO;
    }

    @Override
    public String executeAbnormalOffState(InspectAcceptanceDTO inspectAcceptanceDTO, UserInformationEntity userInformationEntity) {
        String resultMessage = "";
        ProjectExamineEntity projectExamineEntity = inspectAcceptanceRepository.searchAcceptanceBatchInfo(inspectAcceptanceDTO.getBatchId());
        if (projectExamineEntity != null) {
            projectExamineEntity.setAbnormalShutdown(inspectAcceptanceDTO.getAbnormalShutdown());
            projectExamineEntity.setState("AbnormalShutdown");
            projectExamineEntity.setModifyBy(userInformationEntity.getStaffId());
            projectExamineEntity.setModifyName(userInformationEntity.getStaffName());
            projectExamineEntity.setModifyOn(new Date());
            boolean f = inspectAcceptanceRepository.modifyAcceptanceBatchInfo(projectExamineEntity);
            if (f) {
                resultMessage = "0";//成功
            } else {
                resultMessage = "1";//失败
            }
        }
        return resultMessage;
    }

    @Override
    public ApiResult searchAcceptanceBatchInfoByStaffId(String batchId, String staffId) {
        ModelMap modelMap = new ModelMap();
        ProjectExamineEntity projectExamineEntity = inspectAcceptanceRepository.searchAcceptanceBatchInfoByStaffId(batchId, staffId);
        if (projectExamineEntity == null) {
            modelMap.addAttribute("error", "对不起，您没有权限操作此条记录！");
        } else {
            modelMap.addAttribute("success", "确定要关闭此条记录吗？");
        }
        return new SuccessApiResult(modelMap);
    }

    @Override
    public void exportCountExcel(String title, String[] headers, ServletOutputStream out, InspectAcceptanceDTO inspectAcceptanceDTO, WebPage webPage,  UserInformationEntity userInformationEntity) {
        Map map = new HashMap();
        map.put("projectId", inspectAcceptanceDTO.getProjectId());//项目id
        map.put("tenderId", inspectAcceptanceDTO.getTenderId());//标段ID
        map.put("buildingId", inspectAcceptanceDTO.getBuildingId());//楼栋ID
        List<ProjectAcceptanceDTO> projectAcceptanceDTOs = new ArrayList<ProjectAcceptanceDTO>();
        if (inspectAcceptanceDTO == null) {
            new ErrorApiResult("error_00000002");
        }
        int number = 1;//序号
        webPage = null;
        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(userInformationEntity.getStaffId());
        List<Object[]> list = null;
        if (f) {
            list = inspectAcceptanceRepository.searchAcceptanceCountList(map, webPage);
        } else {
            list = inspectAcceptanceRepository.searchAcceptanceCountList(map, webPage, userInformationEntity.getStaffId());
        }
//        List<Object[]> list = inspectAcceptanceRepository.searchAcceptanceCountList(map);
        // 导出数据
        ExportExcel<InspectAcceptanceCountExcelDTO> ex = new ExportExcel<InspectAcceptanceCountExcelDTO>();
        List<InspectAcceptanceCountExcelDTO> inspectAcceptanceCountExcelDTOList = new ArrayList<InspectAcceptanceCountExcelDTO>();
        if (list != null && list.size() > 0) {
            for (Object[] obj : list) {
                InspectAcceptanceCountExcelDTO inspectAcceptanceCountExcelDTO = new InspectAcceptanceCountExcelDTO();
                inspectAcceptanceCountExcelDTO.setNumber(number++);
                inspectAcceptanceCountExcelDTO.setBuildingName((String) obj[0]);//楼栋名称
                inspectAcceptanceCountExcelDTO.setTenderName((String) obj[1]);//标段名称
                inspectAcceptanceCountExcelDTO.setProjectName((String) obj[2]);//项目名称
                inspectAcceptanceCountExcelDTO.setTotal(((BigInteger) obj[3]).intValue());//全部
                inspectAcceptanceCountExcelDTO.setQualifiedToatl(((BigInteger) obj[4]).intValue());//合格
                inspectAcceptanceCountExcelDTO.setUnqualifiedToatl(((BigInteger) obj[5]).intValue());//不合格
                inspectAcceptanceCountExcelDTO.setOnePassToatl(((BigInteger) obj[6]).intValue());//一次通过
                inspectAcceptanceCountExcelDTO.setAbnormalShutdown(((BigInteger) obj[7]).intValue());//非正常关闭

                inspectAcceptanceCountExcelDTOList.add(inspectAcceptanceCountExcelDTO);
            }
        }
        ex.exportExcel(title, headers, inspectAcceptanceCountExcelDTOList, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

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

    @Override
    public boolean getAuthorityByStaffId(String projectId, String staffId) {
        return staffEmployRepository.checkOwner(staffId, projectId, "4");
    }
}
