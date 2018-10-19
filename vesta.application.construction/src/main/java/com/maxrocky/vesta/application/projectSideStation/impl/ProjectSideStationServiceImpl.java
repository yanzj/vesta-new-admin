package com.maxrocky.vesta.application.projectSideStation.impl;

import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.inspectAcceptance.DTO.ExportExcel;
import com.maxrocky.vesta.application.projectSideStation.DTO.*;
import com.maxrocky.vesta.application.projectSideStation.inf.ProjectSideStationService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.ErrorApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.ProjectCategoryEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectCategoryRepository;
import com.maxrocky.vesta.domain.baseData.repository.ProjectImagesRepository;
import com.maxrocky.vesta.domain.baseData.repository.StaffEmployRepository;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.projectSideStation.model.ProjectSideStationDetailsEntity;
import com.maxrocky.vesta.domain.projectSideStation.model.ProjectSideStationEntity;
import com.maxrocky.vesta.domain.projectSideStation.repository.ProjectSideStationRepository;
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
import java.math.BigInteger;
import java.sql.Time;
import java.util.*;

/**
 * Created by Talent on 2016/11/8.
 */
@Service
public class ProjectSideStationServiceImpl implements ProjectSideStationService {
    @Autowired
    ProjectSideStationRepository projectSideStationRepository;
    @Autowired
    private ProjectImagesRepository projectImagesRepository;
    @Autowired
    private GetAllClassifyService getAllClassifyService;
    @Autowired
    private ProjectCategoryRepository projectCategoryRepository;
    @Autowired
    StaffEmployRepository staffEmployRepository;

    @Override
    public List<CheckForUpdatesFromSideStationDTO> checkForUpdatesFromSideStation(CheckForUpdates checkForUpdates, UserPropertyStaffEntity userPropertyStaffEntity) {
        List<CheckForUpdatesFromSideStationDTO> checkForUpdatesFromSideStationDTOList = checkForUpdates.getList();
        List<CheckForUpdatesFromSideStationDTO> checkForUpdatesFromSideStationDTOs = new ArrayList<CheckForUpdatesFromSideStationDTO>();
        if (checkForUpdatesFromSideStationDTOList != null && checkForUpdatesFromSideStationDTOList.size() > 0) {
            for (CheckForUpdatesFromSideStationDTO checkForUpdatesFromSideStationDTO : checkForUpdatesFromSideStationDTOList) {
                CheckForUpdatesFromSideStationDTO checkForUpdatesFromSideStationDTO1 = new CheckForUpdatesFromSideStationDTO();

                //判断权限
                String type = "1";
                if (staffEmployRepository.checkOwner(userPropertyStaffEntity.getStaffId(), checkForUpdatesFromSideStationDTO.getProjectId(), "1")) {//甲方
                    //甲方   该项目下所有数据
                    type = "1";
                } else {
                    String chec = staffEmployRepository.getPurviewName(userPropertyStaffEntity.getStaffId(), checkForUpdatesFromSideStationDTO.getProjectId());
                    if ("4".equals(chec)) {
                        //乙方   处理人为自己 + 完成状态  乙方负责人为自己
                        type = "3";
                    } else if ("5".equals(chec)) {
                        //第三方监理   自己创建 + 第三方监理为自己 + 抄送自己 + 处理人为自己
                        type = "2";
                    }
                }
                String time = "";
                if (!StringUtil.isEmpty(checkForUpdatesFromSideStationDTO.getTimeStamp())) {
                    time = DateUtils.format(DateUtils.parse(checkForUpdatesFromSideStationDTO.getTimeStamp(), "yyyyMMddHHmmss"));
                }
                boolean checkFlag = projectSideStationRepository.checkForUpdatesFromSideStation(checkForUpdatesFromSideStationDTO.getId(), time, checkForUpdatesFromSideStationDTO.getProjectId(), userPropertyStaffEntity.getStaffId(), type);
                if (checkFlag) {
                    checkForUpdatesFromSideStationDTO1.setUpdateFlag("Y");
                } else {
                    checkForUpdatesFromSideStationDTO1.setUpdateFlag("N");
                }
                checkForUpdatesFromSideStationDTO1.setProjectId(checkForUpdatesFromSideStationDTO.getProjectId());
                checkForUpdatesFromSideStationDTO1.setId(checkForUpdatesFromSideStationDTO.getId());
                checkForUpdatesFromSideStationDTO1.setTimeStamp(checkForUpdatesFromSideStationDTO.getTimeStamp());
                checkForUpdatesFromSideStationDTOs.add(checkForUpdatesFromSideStationDTO1);
            }
        }
        return checkForUpdatesFromSideStationDTOs;
    }

    @Override
    public ProjectSideStationListDTO getAllSideStationInfo(String id, String beginDateNew, String projectId, UserPropertyStaffEntity userPropertyStaffEntity) {
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
        List<Object[]> list = projectSideStationRepository.getSideStationInfoByParameter(id, beginDateNew, projectId, userPropertyStaffEntity.getStaffId(), type);
        ProjectSideStationListDTO projectSideStationListDTO = new ProjectSideStationListDTO();
        List<ProjectSideStationDTO> projectSideStationInfoDTOList = new ArrayList<ProjectSideStationDTO>();
        if (list != null && list.size() > 0) {
            for (Object[] obj : list) {
                ProjectSideStationDTO projectSideStationInfoDTO = this.getProjectSideStationInfo(obj[1].toString());
                projectSideStationInfoDTOList.add(projectSideStationInfoDTO);
            }
        }
        if (projectSideStationInfoDTOList != null && projectSideStationInfoDTOList.size() > 0) {
            projectSideStationListDTO.setId(projectSideStationInfoDTOList.get(0).getSelfId());
            projectSideStationListDTO.setTimeStamp(projectSideStationInfoDTOList.get(0).getCreateDate());
            projectSideStationListDTO.setList(projectSideStationInfoDTOList);
        }
        return projectSideStationListDTO;
    }

    @Override
    public ApiResult getSideStationCountByProjectId(String projectId) {
        List<ProjectSideStationCountDTO> projectSideStationCountDTOs = new ArrayList<ProjectSideStationCountDTO>();
        if (StringUtil.isEmpty(projectId)) {
            new ErrorApiResult("error_00000002");
        }
        int allTotal = 0;
        List<Object[]> list = projectSideStationRepository.getSideStationCountByProjectId(projectId);
        if (list != null && list.size() > 0) {
            ProjectSideStationCountDTO projectAcceptanceAllDTO = new ProjectSideStationCountDTO();
            for (Object[] obj : list) {
                ProjectSideStationCountDTO projectSideStationCountDTO = new ProjectSideStationCountDTO();
                projectSideStationCountDTO.setBuildingName((String) obj[0]);
                projectSideStationCountDTO.setBuildingId((String) obj[1]);
                projectSideStationCountDTO.setProjectId((String) obj[2]);
                projectSideStationCountDTO.setTotal(((BigInteger) obj[3]).intValue());

                allTotal += projectSideStationCountDTO.getTotal();

                projectSideStationCountDTOs.add(projectSideStationCountDTO);
            }
            projectAcceptanceAllDTO.setBuildingId("totalAll");
            projectAcceptanceAllDTO.setProjectId(projectId);
            projectAcceptanceAllDTO.setTotal(allTotal);
            projectSideStationCountDTOs.add(projectAcceptanceAllDTO);

        }
        ModelMap result = new ModelMap();
        result.addAttribute("projectSideStationCountDTOs", projectSideStationCountDTOs);
        return new SuccessApiResult(result);
    }

    @Override
    public ApiResult addSideStationInfo(UserPropertyStaffEntity userPropertyStaffEntity, ProjectSideStationRequestListDTO projectSideStationRequestListDTO) {
        if (projectSideStationRequestListDTO.getList() == null && projectSideStationRequestListDTO.getList().size() == 0) {
            return new ErrorApiResult("error_00000002");
        }
        try {
            List<ProjectSideStationDTO> projectSideStationDTOList = projectSideStationRequestListDTO.getList();
            List<ProjectSideStationDTO> projectSideStationDTOs = new ArrayList<ProjectSideStationDTO>();
            ProjectSideStationListDTO projectSideStationListDTO = new ProjectSideStationListDTO();
            for (ProjectSideStationDTO projectSideStationDTO : projectSideStationDTOList) {
                //判断是否要新增旁站批次(如果旁站ID不为空，只做新增旁站详情操作；否则，操作新增旁站信息和新增旁站详情信息)
                if (!StringUtil.isEmpty(projectSideStationDTO.getSideStationId())) {
                    ProjectSideStationEntity sideStationEntity = projectSideStationRepository.getSideStationInfoById(projectSideStationDTO.getSideStationId());
                    if (sideStationEntity == null) {
                        return new ErrorApiResult("error_00000049");
                    }
                    List<ProjectSideStationDetailsDTO> projectSideStationDetailsDTOList = projectSideStationDTO.getProjectSideStationDetailsDTOs();
                    if (projectSideStationDetailsDTOList != null && projectSideStationDetailsDTOList.size() > 0) {
                        sideStationEntity.setCreateOn(new Date());
                        projectSideStationRepository.updateSideStationInfo(sideStationEntity);
                        for (ProjectSideStationDetailsDTO projectSideStationDetailsDTO : projectSideStationDetailsDTOList) {
                            //判断是否已存在此条旁站详情(如果详情ID为空，说明是新增的旁站详情（做新增旁站详情处理）；如果详情ID不为空，说明已经存在此条旁站详情，不做任何处理)
                            if (!StringUtil.isEmpty(projectSideStationDetailsDTO.getDetailsId())) {
                                ProjectSideStationDetailsEntity projectSideStationDetailsEntity = projectSideStationRepository.getSideStationDetailsInfoBySideStationId(projectSideStationDetailsDTO.getDetailsId());
                                if (projectSideStationDetailsEntity == null) {
                                    return new ErrorApiResult("error_00000049");
                                }
                            } else {
                                ProjectSideStationDetailsEntity projectSideStationDetailsEntity = new ProjectSideStationDetailsEntity();
                                projectSideStationDetailsEntity.setId(IdGen.uuid());//id
                                projectSideStationDetailsEntity.setDescription(projectSideStationDetailsDTO.getDescription());//描述
                                projectSideStationDetailsEntity.setRecordTime(new Time(DateUtils.parse(projectSideStationDetailsDTO.getRecordTime(), "HH:mm").getTime()));//记录时间
                                projectSideStationDetailsEntity.setSideStationId(projectSideStationDTO.getSideStationId());//旁站ID
                                projectSideStationDetailsEntity.setCreateById(userPropertyStaffEntity.getStaffId());//创建人
                                projectSideStationDetailsEntity.setCreateByName(userPropertyStaffEntity.getStaffName());//创建人名称
                                projectSideStationDetailsEntity.setCreateOn(new Date());//创建时间
                                projectSideStationDetailsEntity.setSerialNumber(projectSideStationDetailsDTO.getSerialNumber());//排序号
                                boolean f1 = projectSideStationRepository.addSideStationDetailsInnfo(projectSideStationDetailsEntity);
                                if (f1) {
                                    //旁站图片
                                    ProjectImagesEntity projectImagesEntity = new ProjectImagesEntity();
                                    projectImagesEntity.setId(IdGen.uuid());//图片ID
                                    projectImagesEntity.setBusinessId(projectSideStationDetailsEntity.getId());//旁站详情ID
                                    projectImagesEntity.setUrl(projectSideStationDetailsDTO.getImageUrl());//图片URL
                                    projectImagesEntity.setType("6");//所属类型：
                                    projectImagesEntity.setState("1");//状态
                                    projectImagesEntity.setCreateOn(new Date());//创建时间
                                    projectImagesEntity.setModifyOn(new Date());//修改时间
                                    //保存图片信息
                                    projectImagesRepository.addProjectImagesInfo(projectImagesEntity);
                                }
                            }
                        }
                    }
                    ProjectSideStationDTO projectSideStationDTO1 = this.getProjectSideStationInfo(projectSideStationDTO.getSideStationId());
                    projectSideStationDTOs.add(projectSideStationDTO1);
                } else {
                    if (StringUtil.isEmpty(projectSideStationDTO.getProjectId())) {
                        return ErrorResource.getError("tip_00000572");//项目编码不能为空
                    }
                    if (StringUtil.isEmpty(projectSideStationDTO.getProcessName())) {
                        return ErrorResource.getError("tip_00000581");//工序名称不存在
                    }
                    if (StringUtil.isEmpty(projectSideStationDTO.getBuildingId())) {
                        return ErrorResource.getError("tip_00000575");//楼栋编码不存在
                    }
                    if (StringUtil.isEmpty(projectSideStationDTO.getFirstCategory())) {
                        return ErrorResource.getError("tip_00000582");//一级分类不存在
                    }
//                    if (StringUtil.isEmpty(projectSideStationDTO.getBuildingId())) {
//                        return ErrorResource.getError("tip_00000583");//二级分类不存在
//                    }
                    ProjectSideStationEntity projectSideStation = projectSideStationRepository.getSideStationInfoByAppId(projectSideStationDTO.getId());
                    if (projectSideStation != null) {
                        ProjectSideStationDTO projectSideStationDTO1 = this.getProjectSideStationInfo(projectSideStation.getSideStationId());
                        projectSideStationDTOs.add(projectSideStationDTO1);
                        continue;
                    }
                    ProjectSideStationEntity projectSideStationEntity = new ProjectSideStationEntity();
                    projectSideStationEntity.setSideStationId(IdGen.uuid());//ID
                    projectSideStationEntity.setAppId(projectSideStationDTO.getId());//APPID
                    projectSideStationEntity.setProcessName(projectSideStationDTO.getProcessName());//工序名称
                    projectSideStationEntity.setProjectId(projectSideStationDTO.getProjectId());//项目ID
                    projectSideStationEntity.setProjectName(projectSideStationDTO.getProjectName());//项目名称
                    projectSideStationEntity.setBuildingId(projectSideStationDTO.getBuildingId());//楼栋ID
                    projectSideStationEntity.setBuildingName(projectSideStationDTO.getBuildingName());//楼栋名称
                    projectSideStationEntity.setFloorStart(projectSideStationDTO.getFloorStart());//开始楼层
                    projectSideStationEntity.setFloorEnd(projectSideStationDTO.getFloorEnd());//结束楼层
                    projectSideStationEntity.setFirstCategory(projectSideStationDTO.getFirstCategory());//一级分类
                    projectSideStationEntity.setFirstCategoryName(projectSideStationDTO.getFirstCategoryName());//一级分类名称
                    projectSideStationEntity.setSecondCategory(projectSideStationDTO.getSecondCategory());//二级分类
                    projectSideStationEntity.setSecondCategoryName(projectSideStationDTO.getSecondCategoryName());//二级分类名称
                    projectSideStationEntity.setLocation(projectSideStationDTO.getLocation());//具体位置
                    projectSideStationEntity.setSideStationPoint(projectSideStationDTO.getSideStationPoint());//旁站要点
                    projectSideStationEntity.setSideStationDate(DateUtils.parse(projectSideStationDTO.getSideStationDate(), DateUtils.FORMAT_SHORT));//旁站时间
                    projectSideStationEntity.setSideStationStaffId(projectSideStationDTO.getSideStationStaffId());//旁站人ID
                    projectSideStationEntity.setSideStationStaffName(projectSideStationDTO.getSideStationStaffName());//旁站人名称
                    projectSideStationEntity.setCreateById(userPropertyStaffEntity.getStaffId());//创建人
                    projectSideStationEntity.setCreateByName(userPropertyStaffEntity.getStaffName());//创建人名称
                    projectSideStationEntity.setCreateOn(new Date());//创建时间

                    boolean f = projectSideStationRepository.addSideStationInfo(projectSideStationEntity);
                    if (f) {
                        List<ProjectSideStationDetailsDTO> projectSideStationDetailsDTOList = projectSideStationDTO.getProjectSideStationDetailsDTOs();
                        if (projectSideStationDetailsDTOList != null && projectSideStationDetailsDTOList.size() > 0) {
                            for (ProjectSideStationDetailsDTO projectSideStationDetailsDTO : projectSideStationDetailsDTOList) {
                                ProjectSideStationDetailsEntity projectSideStationDetailsEntity = new ProjectSideStationDetailsEntity();
                                projectSideStationDetailsEntity.setId(IdGen.uuid());
                                projectSideStationDetailsEntity.setDescription(projectSideStationDetailsDTO.getDescription());
                                projectSideStationDetailsEntity.setSideStationId(projectSideStationEntity.getSideStationId());
                                projectSideStationDetailsEntity.setRecordTime(new Time(DateUtils.parse(projectSideStationDetailsDTO.getRecordTime(), "HH:mm").getTime()));
                                projectSideStationDetailsEntity.setCreateById(userPropertyStaffEntity.getStaffId());
                                projectSideStationDetailsEntity.setCreateByName(userPropertyStaffEntity.getStaffName());
                                projectSideStationDetailsEntity.setCreateOn(new Date());
                                projectSideStationDetailsEntity.setSerialNumber(projectSideStationDetailsDTO.getSerialNumber());//排序号
                                boolean f1 = projectSideStationRepository.addSideStationDetailsInnfo(projectSideStationDetailsEntity);
                                if (f1) {
                                    //旁站图片
                                    ProjectImagesEntity projectImagesEntity = new ProjectImagesEntity();
                                    projectImagesEntity.setId(IdGen.uuid());//图片ID
                                    projectImagesEntity.setBusinessId(projectSideStationDetailsEntity.getId());//旁站详情ID
                                    projectImagesEntity.setUrl(projectSideStationDetailsDTO.getImageUrl());//图片URL
                                    projectImagesEntity.setType("6");//所属类型：
                                    projectImagesEntity.setState("1");//状态
                                    projectImagesEntity.setCreateOn(new Date());//创建时间
                                    projectImagesEntity.setModifyOn(new Date());//修改时间
                                    //保存图片信息
                                    projectImagesRepository.addProjectImagesInfo(projectImagesEntity);
                                }
                            }
                        }
                        ProjectSideStationDTO projectSideStationInfoDTO = this.getProjectSideStationInfo(projectSideStationEntity.getSideStationId());
                        projectSideStationDTOs.add(projectSideStationInfoDTO);
                    } else {
                        projectSideStationDTOs.add(projectSideStationDTO);
                    }
                }
            }
            projectSideStationListDTO.setList(projectSideStationDTOs);
            return new SuccessApiResult(projectSideStationListDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    @Override
    public List<ProjectSideStationInfoDTO> searchBesideStationList(ProjectSideStationInfoDTO projectSideStationInfoDTO, WebPage webPage, String staffId) {
        Map map = new HashMap();
        map.put("projectId", projectSideStationInfoDTO.getProjectId());//项目id
        map.put("classfiyOne", projectSideStationInfoDTO.getFirstCategory());//一级分类
        map.put("classfiyTwo", projectSideStationInfoDTO.getSecondCategory());//二级分类
        map.put("startDate", projectSideStationInfoDTO.getSideStationStartDate());//开始时间
        map.put("endDate", projectSideStationInfoDTO.getSideStationEndDate());//结束时间
        map.put("sideStationStaffName", projectSideStationInfoDTO.getSideStationStaffName());//责任单位名称
//        boolean f = getAllClassifyService.getRoleViewmodelByStaffId(staffId);
        List<ProjectSideStationEntity> projectSideStationEntityList = null;
//        if (f) {
//            projectSideStationEntityList = projectSideStationRepository.searchBesideStationList(map, webPage);
//        } else {
//            projectSideStationEntityList = projectSideStationRepository.searchBesideStationList(map, webPage, staffId);
//        }

        if(!StringUtil.isEmpty(projectSideStationInfoDTO.getProjectId())){
            projectSideStationEntityList = projectSideStationRepository.searchBesideStationList(map, webPage, staffId);
        }
//        List<ProjectSideStationEntity> projectSideStationEntityList = projectSideStationRepository.searchBesideStationList(map, webPage,staffId);
        List<ProjectSideStationInfoDTO> projectSideStationDTOList = new ArrayList<ProjectSideStationInfoDTO>();
        if (projectSideStationEntityList != null && projectSideStationEntityList.size() > 0) {
            for (ProjectSideStationEntity projectSideStationEntity : projectSideStationEntityList) {
                ProjectSideStationInfoDTO projectSideStationDTO = new ProjectSideStationInfoDTO();
                projectSideStationDTO.setSideStationId(projectSideStationEntity.getSideStationId());
                projectSideStationDTO.setProjectName(projectSideStationEntity.getProjectName());
                projectSideStationDTO.setProcessName(projectSideStationEntity.getProcessName());
                projectSideStationDTO.setLocation(projectSideStationEntity.getLocation());
                projectSideStationDTO.setSideStationStaffName(projectSideStationEntity.getSideStationStaffName());
                projectSideStationDTO.setSideStationDate(projectSideStationEntity.getSideStationDate());
                projectSideStationDTO.setState(projectSideStationEntity.getState());
                projectSideStationDTOList.add(projectSideStationDTO);
            }
        }
        return projectSideStationDTOList;
    }

    @Override
    public ProjectSideStationDTO getProjectSideStationInfo(String sideStationId) {
        ProjectSideStationDTO projectSideStationDTO = new ProjectSideStationDTO();
        if (!StringUtil.isEmpty(sideStationId)) {
            ProjectSideStationEntity sideStationEntity = projectSideStationRepository.getSideStationInfoById(sideStationId);
            if (sideStationEntity != null) {
                projectSideStationDTO.setId(sideStationEntity.getAppId());//appID
                projectSideStationDTO.setSelfId((sideStationEntity.getId()).toString());//自增ID
                projectSideStationDTO.setSideStationId(sideStationEntity.getSideStationId());//旁站ID
                projectSideStationDTO.setProcessName(sideStationEntity.getProcessName() == null ? "" : sideStationEntity.getProcessName());//工序名称
                projectSideStationDTO.setProjectId(sideStationEntity.getProjectId() == null ? "" : sideStationEntity.getProjectId());//项目ID
                projectSideStationDTO.setProjectName(sideStationEntity.getProjectName() == null ? "" : sideStationEntity.getProjectName());//项目名称
                projectSideStationDTO.setBuildingId(sideStationEntity.getBuildingId() == null ? "" : sideStationEntity.getBuildingId());//楼栋ID
                projectSideStationDTO.setBuildingName(sideStationEntity.getBuildingName() == null ? "" : sideStationEntity.getBuildingName());//楼栋名称
                projectSideStationDTO.setFloorStart(sideStationEntity.getFloorStart());//开始楼层
                projectSideStationDTO.setFloorEnd(sideStationEntity.getFloorEnd());//结束楼层
                projectSideStationDTO.setFirstCategory(sideStationEntity.getFirstCategory() == null ? "" : sideStationEntity.getFirstCategory());//一级分类
                projectSideStationDTO.setFirstCategoryName(sideStationEntity.getFirstCategoryName() == null ? "" : sideStationEntity.getFirstCategoryName());//一级分类名称
                projectSideStationDTO.setSecondCategory(sideStationEntity.getSecondCategory() == null ? "" : sideStationEntity.getSecondCategory());//二级分类
                projectSideStationDTO.setSecondCategoryName(sideStationEntity.getSecondCategoryName() == null ? "" : sideStationEntity.getSecondCategoryName());//二级分类名称
                projectSideStationDTO.setLocation(sideStationEntity.getLocation() == null ? "" : sideStationEntity.getLocation());//具体位置
                projectSideStationDTO.setSideStationPoint(sideStationEntity.getSideStationPoint() == null ? "" : sideStationEntity.getSideStationPoint());//旁站要点
                projectSideStationDTO.setSideStationStaffName(sideStationEntity.getSideStationStaffName() == null ? "" : sideStationEntity.getSideStationStaffName());//旁站人员
                projectSideStationDTO.setSideStationStaffId(sideStationEntity.getSideStationStaffId() == null ? "" : sideStationEntity.getSideStationStaffId());//旁站人员ID
                projectSideStationDTO.setSideStationDate(DateUtils.format(sideStationEntity.getSideStationDate(), "yyyy-MM-dd") == null ? "" : DateUtils.format(sideStationEntity.getSideStationDate(), "yyyy-MM-dd"));//旁站时间
                projectSideStationDTO.setCreateDate(DateUtils.format(sideStationEntity.getCreateOn()) == null ? "" : DateUtils.format(sideStationEntity.getCreateOn()));//创建时间

                //旁站详情信息
                List<Object[]> list = projectSideStationRepository.getSideStationDetailsBySideStationId(sideStationEntity.getSideStationId());
                List<ProjectSideStationDetailsDTO> projectSideStationDetailsDTOList = new ArrayList<ProjectSideStationDetailsDTO>();
                if (list != null && list.size() > 0) {
                    for (Object[] obj : list) {
                        ProjectSideStationDetailsDTO projectSideStationDetailsDTO = new ProjectSideStationDetailsDTO();
                        projectSideStationDetailsDTO.setDetailsId(obj[0] == null ? "" : (String) obj[0]);//旁站详情ID
                        projectSideStationDetailsDTO.setDescription(obj[1] == null ? "" : (String) obj[1]);//旁站描述
                        projectSideStationDetailsDTO.setImageUrl(obj[2] == null ? "" : (String) obj[2]);//图片地址
                        projectSideStationDetailsDTO.setRecordTime(obj[3] == null ? "" : DateUtils.format((Time) obj[3], "HH:mm"));//记录时间
                        projectSideStationDetailsDTO.setSerialNumber(obj[4] == null ? "" : (String) obj[4]);//排序号
                        projectSideStationDetailsDTOList.add(projectSideStationDetailsDTO);
                    }
                }
                projectSideStationDTO.setProjectSideStationDetailsDTOs(projectSideStationDetailsDTOList);
            }
        }
        return projectSideStationDTO;
    }

    @Override
    public ProjectSideStationInfoDTO getProjectSideStationInfoById(String sideStationId) {
        ProjectSideStationInfoDTO projectSideStationInfoDTO = new ProjectSideStationInfoDTO();
        if (!StringUtil.isEmpty(sideStationId)) {
            ProjectSideStationEntity sideStationEntity = projectSideStationRepository.getSideStationInfoById(sideStationId);
            if (sideStationEntity != null) {
                projectSideStationInfoDTO.setProjectName(sideStationEntity.getProjectName());//项目名称
                projectSideStationInfoDTO.setProcessName(sideStationEntity.getProcessName());//工序名称
                projectSideStationInfoDTO.setLocation(sideStationEntity.getLocation());//具体位置
                projectSideStationInfoDTO.setSideStationStaffName(sideStationEntity.getSideStationStaffName());//旁站人
                ProjectCategoryEntity projectCategoryEntity = projectCategoryRepository.getCategoryDetail(sideStationEntity.getFirstCategory());
                if (projectCategoryEntity != null) {
                    projectSideStationInfoDTO.setSideStationTimeSpace(projectCategoryEntity.getTimeSpace());//旁站时间
                }

                //旁站详情信息
                List<Object[]> list = projectSideStationRepository.getSideStationDetailsInfoListBySideStationId(sideStationEntity.getSideStationId());
                List<ProjectSideStationDetailsDTO> projectSideStationDetailsDTOList = new ArrayList<ProjectSideStationDetailsDTO>();
                if (list != null && list.size() > 0) {
                    for (Object[] obj : list) {
                        ProjectSideStationDetailsDTO projectSideStationDetailsDTO = new ProjectSideStationDetailsDTO();
                        projectSideStationDetailsDTO.setDetailsId((String) obj[0]);
                        projectSideStationDetailsDTO.setDescription((String) obj[1]);
                        projectSideStationDetailsDTO.setImageUrl((String) obj[2]);
                        projectSideStationDetailsDTO.setRecordTime(DateUtils.format((Time) obj[3], "HH:mm"));

                        projectSideStationDetailsDTOList.add(projectSideStationDetailsDTO);
                    }
                }
                projectSideStationInfoDTO.setSideStationStartDate(DateUtils.format(sideStationEntity.getSideStationDate(), "yyyy-MM-dd") + " " + projectSideStationDetailsDTOList.get(0).getRecordTime());
                projectSideStationInfoDTO.setSideStationEndDate(DateUtils.format(sideStationEntity.getSideStationDate(), "yyyy-MM-dd") + " " + projectSideStationDetailsDTOList.get(projectSideStationDetailsDTOList.size() - 1).getRecordTime());
                projectSideStationInfoDTO.setProjectSideStationDetailsDTOs(projectSideStationDetailsDTOList);
            }
        }
        return projectSideStationInfoDTO;
    }

    @Override
    public void exportExcel(String title, String[] headers, ServletOutputStream out,ProjectSideStationInfoDTO projectSideStationInfoDTO1,UserInformationEntity userInformationEntity) {
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);
        List<ProjectSideStationInfoDTO> projectSideStationInfoDTOList = this.searchBesideStationList(projectSideStationInfoDTO1,webPage,userInformationEntity.getStaffId());
        // 导出数据
        ExportExcel<ProjectSideStationExcelDTO> ex = new ExportExcel<ProjectSideStationExcelDTO>();
        List<ProjectSideStationExcelDTO> projectSideStationExcelDTOList = new ArrayList<ProjectSideStationExcelDTO>();
        if (projectSideStationInfoDTOList != null && projectSideStationInfoDTOList.size() > 0) {
            int number = 1;
            for (ProjectSideStationInfoDTO projectSideStationInfoDTO : projectSideStationInfoDTOList) {
                ProjectSideStationExcelDTO projectSideStationExcelDTO = new ProjectSideStationExcelDTO();
                projectSideStationExcelDTO.setSerialNumber(number++);//编号
                projectSideStationExcelDTO.setProjectName(projectSideStationInfoDTO.getProjectName());//项目名称
                projectSideStationExcelDTO.setProcessName(projectSideStationInfoDTO.getProcessName());//工序名称
                projectSideStationExcelDTO.setLocation(projectSideStationInfoDTO.getLocation());//具体位置
                projectSideStationExcelDTO.setSideStationStaffName(projectSideStationInfoDTO.getSideStationStaffName());//旁站人员
                projectSideStationExcelDTO.setSideStationDate(projectSideStationInfoDTO.getSideStationDate());//旁站时间

                projectSideStationExcelDTOList.add(projectSideStationExcelDTO);
            }
        }
        ex.exportExcel(title, headers, projectSideStationExcelDTOList, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    @Override
    public List<ProjectSideStationInfoDTO> getAllBesideStationList() {
        List<ProjectSideStationEntity> projectSideStationEntityList = projectSideStationRepository.searchBesideStationList();
        List<ProjectSideStationInfoDTO> projectSideStationInfoDTOList = new ArrayList<ProjectSideStationInfoDTO>();
        if (projectSideStationEntityList != null && projectSideStationEntityList.size() > 0) {
            for (ProjectSideStationEntity projectSideStationEntity : projectSideStationEntityList) {
                ProjectSideStationInfoDTO projectSideStationInfoDTO = new ProjectSideStationInfoDTO();
                projectSideStationInfoDTO.setSideStationId(projectSideStationEntity.getSideStationId());
                projectSideStationInfoDTO.setProcessName(projectSideStationEntity.getProcessName());
                projectSideStationInfoDTO.setProjectName(projectSideStationEntity.getProjectName());
                projectSideStationInfoDTO.setLocation(projectSideStationEntity.getLocation());
                projectSideStationInfoDTO.setSideStationDate(projectSideStationEntity.getSideStationDate());
                projectSideStationInfoDTO.setSideStationStaffName(projectSideStationEntity.getSideStationStaffName());

                projectSideStationInfoDTOList.add(projectSideStationInfoDTO);
            }
        }
        return projectSideStationInfoDTOList;
    }
}
