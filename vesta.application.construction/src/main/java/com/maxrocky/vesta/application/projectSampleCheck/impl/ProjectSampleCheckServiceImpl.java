package com.maxrocky.vesta.application.projectSampleCheck.impl;

import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.inspectAcceptance.DTO.ExportExcel;
import com.maxrocky.vesta.application.projectKeyProcesses.DTO.KeyProcessesCopyDTO;
import com.maxrocky.vesta.application.projectSampleCheck.DTO.*;
import com.maxrocky.vesta.application.projectSampleCheck.inf.ProjectSampleCheckService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectProjectEntity;
import com.maxrocky.vesta.domain.baseData.repository.ProjectImagesRepository;
import com.maxrocky.vesta.domain.baseData.repository.StaffEmployRepository;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyDetailsEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.model.ProjectCopyEntity;
import com.maxrocky.vesta.domain.dailyPatrolInspection.repository.DailyPatrolInspectionRepository;
import com.maxrocky.vesta.domain.model.AgencyEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.projectSampleCheck.model.ProjectSampleCheckChangedEntity;
import com.maxrocky.vesta.domain.projectSampleCheck.model.ProjectSampleCheckDetailsEntity;
import com.maxrocky.vesta.domain.projectSampleCheck.model.ProjectSampleCheckEntity;
import com.maxrocky.vesta.domain.projectSampleCheck.repository.ProjectSampleCheckRepository;
import com.maxrocky.vesta.domain.repository.AgencyRepository;
import com.maxrocky.vesta.domain.repository.RectificationRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportPPT;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import com.maxrocky.vesta.utility.Im4JavaUtils;
import org.apache.poi.hslf.model.Picture;
import org.apache.poi.hslf.model.Slide;
import org.apache.poi.hslf.model.TextBox;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

/**
 * Created by Magic on 2017/1/3.
 */
@Service
public class ProjectSampleCheckServiceImpl implements ProjectSampleCheckService {
    @Autowired
    ProjectSampleCheckRepository projectSampleCheckRepository;
    @Autowired
    DailyPatrolInspectionRepository dailyPatrolInspectionRepository;
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    RectificationRepository rectificationRepository;
    @Autowired
    private ProjectImagesRepository projectImagesRepository;
    @Autowired
    StaffEmployRepository staffEmployRepository;
    @Autowired
    GetAllClassifyService getAllClassifyService;

    /**
     * 批量新增样板点评实体
     */
    @Override
    public ApiResult saveProjectSampleCheck(ProjectSampleCheckListDTO projectSampleCheckListDTO, UserPropertyStaffEntity user) {
        if (projectSampleCheckListDTO == null) {
            return ErrorResource.getError("tip_00000431");//用户为空
        }
        //返回数据
        ProjectSampleCheckListDTO returnSampleCheckListDTO = new ProjectSampleCheckListDTO();
        List<ProjectSampleCheckDTO> returnProjectSampleCheckDTO = new ArrayList<ProjectSampleCheckDTO>();
        try {
            List<ProjectSampleCheckDTO> postSampleCheck = projectSampleCheckListDTO.getList();
            for (ProjectSampleCheckDTO SampleCheck : postSampleCheck) {
                if (StringUtil.isEmpty(SampleCheck.getClassifyOne())) {
                    return ErrorResource.getError("tip_00000582");//一级分类
                }
                if (StringUtil.isEmpty(SampleCheck.getClassifyTwo())) {
                    return ErrorResource.getError("tip_00000583");//二级分类
                }
                if (StringUtil.isEmpty(SampleCheck.getClassifyThree())) {
                    return ErrorResource.getError("tip_00000594");//三级分类
                }
                if (StringUtil.isEmpty(SampleCheck.getProjectId())) {
                    return ErrorResource.getError("tip_00000572");//项目ID
                }
                if (StringUtil.isEmpty(SampleCheck.getSupplierId())) {
                    return ErrorResource.getError("tip_00000578");//责任单位ID
                }
                if (StringUtil.isEmpty(SampleCheck.getAssignId())) {
                    return ErrorResource.getError("tip_00000595");//乙方负责人ID
                }
                if (StringUtil.isEmpty(SampleCheck.getSupervisorId())) {
                    return ErrorResource.getError("tip_00000587");//第三方监理id
                }
                if (StringUtil.isEmpty(SampleCheck.getBuildingId())) {
                    return ErrorResource.getError("tip_00000575");//楼栋ID
                }
                ProjectSampleCheckEntity getsampleCheckEntity = projectSampleCheckRepository.querySampleCheckByID(SampleCheck.getId(), SampleCheck.getAppId());
                if (getsampleCheckEntity != null) {
                    ProjectSampleCheckDTO returnSampleCheckDTO = returntSampleCheckDTO(SampleCheck.getId(), SampleCheck.getAppId(), getsampleCheckEntity);
                    returnProjectSampleCheckDTO.add(returnSampleCheckDTO);
                    //直接返回数据
                } else {
                    ProjectSampleCheckEntity sampleCheckEntity = new ProjectSampleCheckEntity();
                    //app端传入 校验唯一性 防止重复
                    sampleCheckEntity.setAppId(SampleCheck.getAppId());
                    //样板点评ID 服务器ID
                    sampleCheckEntity.setSampleCheckId(IdGen.uuid());
                    //样板点评标题
                    if (!StringUtil.isEmpty(SampleCheck.getTitle())) {
                        sampleCheckEntity.setTitle(SampleCheck.getTitle());
                    }
                    //描述
                    if (!StringUtil.isEmpty(SampleCheck.getDescription())) {
                        sampleCheckEntity.setDescription(SampleCheck.getDescription());
                    }

                    sampleCheckEntity.setCreateBy(user.getStaffId());//创建人ID
                    sampleCheckEntity.setCreateName(user.getStaffName());//创建人姓名
                    sampleCheckEntity.setCreateOn(new Date());//创建时间
                    sampleCheckEntity.setModifyDate(new Date()); //修改时间
                    sampleCheckEntity.setProjectId(SampleCheck.getProjectId());//项目ID
                    //项目名称
                    if (!StringUtil.isEmpty(SampleCheck.getProjectName())) {
                        sampleCheckEntity.setProjectName(SampleCheck.getProjectName());
                    }
                    sampleCheckEntity.setBuildingId(SampleCheck.getBuildingId());//楼栋id
                    //楼栋名称
                    if (!StringUtil.isEmpty(SampleCheck.getBuildingName())) {
                        sampleCheckEntity.setBuildingName(SampleCheck.getBuildingName());
                    }
                    sampleCheckEntity.setClassifyOne(SampleCheck.getClassifyOne());//一级分类
                    sampleCheckEntity.setClassifyTwo(SampleCheck.getClassifyTwo()); //二级分类
                    sampleCheckEntity.setClassifyThree(SampleCheck.getClassifyThree());//三级分类
                    //一级分类名称
                    if (!StringUtil.isEmpty(SampleCheck.getClassifyOneName())) {
                        sampleCheckEntity.setClassifyOneName(SampleCheck.getClassifyOneName());
                    }
                    //二级分类名称
                    if (!StringUtil.isEmpty(SampleCheck.getClassifyThreeName())) {
                        sampleCheckEntity.setClassifyThreeName(SampleCheck.getClassifyThreeName());
                    }
                    //三级分类名称
                    if (!StringUtil.isEmpty(SampleCheck.getClassifyThreeName())) {
                        sampleCheckEntity.setClassifyThreeName(SampleCheck.getClassifyThreeName());
                    }
                    //状态
                    sampleCheckEntity.setState(SampleCheck.getState());
                    //严重等级
                    if (!StringUtil.isEmpty(SampleCheck.getSeverityLevel())) {
                        sampleCheckEntity.setSeverityLevel(SampleCheck.getSeverityLevel());
                    }
                    //楼层区间-始
                    if (!StringUtil.isEmpty(SampleCheck.getFloorNum1())) {
                        sampleCheckEntity.setFloorNum1(SampleCheck.getFloorNum1());
                    }
                    //楼层区间-终
                    if (!StringUtil.isEmpty(SampleCheck.getFloorNum2())) {
                        sampleCheckEntity.setFloorNum2(SampleCheck.getFloorNum2());
                    }
                    //检查部位
                    if (!StringUtil.isEmpty(SampleCheck.getCheckPosition())) {
                        sampleCheckEntity.setCheckPosition(SampleCheck.getCheckPosition());
                    }
                    //检查时间
                    if (!StringUtil.isEmpty(SampleCheck.getCheckDate())) {
                        sampleCheckEntity.setCheckDate(DateUtils.parse(SampleCheck.getCheckDate(), DateUtils.FORMAT_LONG));
                    }
                    sampleCheckEntity.setSupplierId(SampleCheck.getSupplierId()); //责任单位ID
                    //责任单位名称
                    if (StringUtil.isEmpty(SampleCheck.getSupplier())) {
                        sampleCheckEntity.setSupplier(SampleCheck.getSupplier());
                    } else {
                        AgencyEntity agency = agencyRepository.getAgencyDetail(sampleCheckEntity.getSupplierId());
                        if (agency != null) {
                            sampleCheckEntity.setSupplier(agency.getAgencyName());
                        }
                    }
                    sampleCheckEntity.setAssignId(SampleCheck.getAssignId());//乙方负责人ID
                    sampleCheckEntity.setSupervisorId(SampleCheck.getSupervisorId());//第三方监理id
                    sampleCheckEntity.setFirstParty(user.getStaffId());//甲方负责人ID
                    if (SampleCheck.getState().equals("不合格")) {
                        sampleCheckEntity.setDealPeople(SampleCheck.getAssignId()); //处理人ID
                    } else {
                        sampleCheckEntity.setDealPeople(sampleCheckEntity.getFirstParty()); //处理人ID
                    }
                    //乙方负责人名字
                    if (!StringUtil.isEmpty(SampleCheck.getAssignName())) {
                        sampleCheckEntity.setAssignName(SampleCheck.getAssignName());
                    } else {
                        UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(SampleCheck.getAssignId());
                        if (UserStaff != null) {
                            sampleCheckEntity.setAssignName(UserStaff.getStaffName());
                        }
                    }
                    //第三方监理名字
                    if (!StringUtil.isEmpty(SampleCheck.getSupervisorName())) {
                        sampleCheckEntity.setSupervisorName(SampleCheck.getSupervisorName());
                    } else {
                        UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(SampleCheck.getSupervisorId());
                        if (UserStaff != null) {
                            sampleCheckEntity.setSupervisorName(UserStaff.getStaffName());
                        }
                    }
                    //甲方负责人名字  只有甲方能创建
                    sampleCheckEntity.setFirstPartyName(user.getStaffName());
//                    if (!StringUtil.isEmpty(SampleCheck.getFirstPartyName())) {
//                        sampleCheckEntity.setFirstPartyName(SampleCheck.getFirstPartyName());
//                    } else {
//                        UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(SampleCheck.getFirstParty());
//                        if (UserStaff != null) {
//                            sampleCheckEntity.setFirstPartyName(UserStaff.getStaffName());
//                        }
//                    }
                    //完成期限
                    sampleCheckEntity.setRectificationPeriod(DateUtils.parse(SampleCheck.getRectificationPeriod(), DateUtils.FORMAT_SHORT));
                    //保存样板点评
                    boolean saveCheck = projectSampleCheckRepository.saveOrUpdateSampleCheck(sampleCheckEntity);
                    if (saveCheck) {
                        //样板点评指标信息
                        if (SampleCheck.getSampleCheckDetails() != null && SampleCheck.getSampleCheckDetails().size() > 0) {
                            for (ProjectSampleCheckDetailsDTO checkDetailsDTO : SampleCheck.getSampleCheckDetails()) {
                                ProjectSampleCheckDetailsEntity checkDetailsEntity = new ProjectSampleCheckDetailsEntity();
                                checkDetailsEntity.setId(IdGen.uuid());//uuid
                                checkDetailsEntity.setSampleCheckId(sampleCheckEntity.getSampleCheckId());//样板点评ID
                                checkDetailsEntity.setTargetId(checkDetailsDTO.getTargetId());//样板点评指标ID
                                if (!StringUtil.isEmpty(checkDetailsDTO.getTargetName())) {//指标名称
                                    checkDetailsEntity.setTargetName(checkDetailsDTO.getTargetName());
                                }
                                checkDetailsEntity.setDescription(checkDetailsDTO.getDescription());//描述
                                //不合格图片
                                if (checkDetailsDTO.getImage2List() != null && checkDetailsDTO.getImage2List().size() > 0) {
                                    checkDetailsEntity.setState("不合格");//状态
                                } else {
                                    checkDetailsEntity.setState("合格");//状态
                                }
                                checkDetailsEntity.setGuide(checkDetailsDTO.getGuide());//指引
                                checkDetailsEntity.setCreateOn(new Date());//创建时间
                                checkDetailsEntity.setModifyDate(new Date());//修改时间
                                //保存样板点评指标信息
                                boolean saveCheckDetails = projectSampleCheckRepository.saveOrUpdateSampleCheckDetails(checkDetailsEntity);
                                if (saveCheckDetails) {
                                    //合格图片
                                    if (checkDetailsDTO.getImageList() != null && checkDetailsDTO.getImageList().size() > 0) {
                                        for (SampleCheckImageDTO imageDTO : checkDetailsDTO.getImageList()) {
                                            //工程图片
                                            ProjectImagesEntity projectImagesEntity = new ProjectImagesEntity();
                                            projectImagesEntity.setId(IdGen.uuid());//图片ID
                                            projectImagesEntity.setBusinessId(checkDetailsEntity.getId());//样板点评指标详细信息ID
                                            projectImagesEntity.setUrl(imageDTO.getImageUrl());//图片URL
                                            projectImagesEntity.setType("4");//所属类型：
                                            projectImagesEntity.setState("1");//状态
                                            projectImagesEntity.setQualifiedState("合格");//合格状态
                                            projectImagesEntity.setCreateOn(new Date());//创建时间
                                            projectImagesEntity.setModifyOn(new Date());//修改时间
                                            //保存图片信息
                                            projectImagesRepository.addProjectImagesInfo(projectImagesEntity);
                                        }
                                    }
                                    //不合格图片
                                    if (checkDetailsDTO.getImage2List() != null && checkDetailsDTO.getImage2List().size() > 0) {
                                        for (SampleCheckImageDTO imageDTO : checkDetailsDTO.getImage2List()) {
                                            //工程图片
                                            ProjectImagesEntity projectImagesEntity = new ProjectImagesEntity();
                                            projectImagesEntity.setId(IdGen.uuid());//图片ID
                                            projectImagesEntity.setBusinessId(checkDetailsEntity.getId());//样板点评指标详细信息ID
                                            projectImagesEntity.setUrl(imageDTO.getImageUrl());//图片URL
                                            projectImagesEntity.setType("4");//所属类型：
                                            projectImagesEntity.setState("1");//状态
                                            projectImagesEntity.setQualifiedState("不合格");//合格状态
                                            projectImagesEntity.setCreateOn(new Date());//创建时间
                                            projectImagesEntity.setModifyOn(new Date());//修改时间
                                            //保存图片信息
                                            projectImagesRepository.addProjectImagesInfo(projectImagesEntity);
                                        }
                                    }
                                }
                            }
                        }
                        //抄送人
                        if (SampleCheck.getCopyList() != null && SampleCheck.getCopyList().size() > 0) {
                            ProjectCopyEntity projectCopyEntity = new ProjectCopyEntity();
                            projectCopyEntity.setId(IdGen.uuid());//抄送人ID
                            projectCopyEntity.setSender(user.getStaffId());//发送人ID
                            projectCopyEntity.setSenderName(user.getStaffName());//发送人名称
                            projectCopyEntity.setBusiness(sampleCheckEntity.getSampleCheckId());//工序ID
                            projectCopyEntity.setDamain("4");//所属模块
                            projectCopyEntity.setCreateOn(new Date());//添加时间
                            //保存抄送人
                            boolean copy = projectSampleCheckRepository.saveProjectCopy(projectCopyEntity);
                            if (copy) {
                                for (KeyProcessesCopyDTO keyProcessesCopyDTO : SampleCheck.getCopyList()) {
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
                                    projectSampleCheckRepository.saveProjectCopyDetails(projectCopyDetailsEntity);
                                }
                            }
                        }
                        ProjectSampleCheckDTO returnSampleCheckDTO = returntSampleCheckEntity(sampleCheckEntity);
                        returnProjectSampleCheckDTO.add(returnSampleCheckDTO);
                    }
                }
            }
            returnSampleCheckListDTO.setList(returnProjectSampleCheckDTO);
            return new SuccessApiResult(returnSampleCheckListDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    @Override
    public List<SampleCheckForUpdateDTO> sampleCheckForUpdate(CheckForUpdateDTO checkForUpdateDTO, UserPropertyStaffEntity userPropertyStaffEntity) {
        List<SampleCheckForUpdateDTO> sampleCheckForUpdateDTOs = new ArrayList<SampleCheckForUpdateDTO>();
        if (checkForUpdateDTO.getList() != null && checkForUpdateDTO.getList().size() > 0) {
            for (SampleCheckForUpdateDTO sampleCheckForUpdateDTO : checkForUpdateDTO.getList()) {
                SampleCheckForUpdateDTO sampleCheckForUpdateDTO1 = new SampleCheckForUpdateDTO();
                //判断权限
                String type = "1";
                if (staffEmployRepository.checkOwner(userPropertyStaffEntity.getStaffId(), sampleCheckForUpdateDTO.getProjectId(), "1")) {//甲方
                    //甲方   该项目下所有数据
                    type = "1";
                } else {
                    String chec = staffEmployRepository.getPurviewName(userPropertyStaffEntity.getStaffId(), sampleCheckForUpdateDTO.getProjectId());
                    if ("4".equals(chec)) {
                        //乙方   处理人为自己 + 完成状态  乙方负责人为自己
                        type = "3";
                    } else if ("5".equals(chec)) {
                        //第三方监理   自己创建 + 第三方监理为自己 + 抄送自己 + 处理人为自己
                        type = "2";
                    }
                }

                String time = "";
                if (!StringUtil.isEmpty(sampleCheckForUpdateDTO.getTimeStamp())) {
                    time = DateUtils.format(DateUtils.parse(sampleCheckForUpdateDTO.getTimeStamp(), "yyyyMMddHHmmss"));
                }
                boolean checkFlag = projectSampleCheckRepository.sampleCheckForUpdate(sampleCheckForUpdateDTO.getId(), time, sampleCheckForUpdateDTO.getProjectId(), userPropertyStaffEntity.getStaffId(), type);
                if (checkFlag) {
                    sampleCheckForUpdateDTO1.setUpdateFlag("Y");
                } else {
                    sampleCheckForUpdateDTO1.setUpdateFlag("N");
                }
                sampleCheckForUpdateDTO1.setProjectId(sampleCheckForUpdateDTO.getProjectId());
                sampleCheckForUpdateDTO1.setId(sampleCheckForUpdateDTO.getId());
                sampleCheckForUpdateDTO1.setTimeStamp(sampleCheckForUpdateDTO.getTimeStamp());
                sampleCheckForUpdateDTOs.add(sampleCheckForUpdateDTO1);
            }
        }
        return sampleCheckForUpdateDTOs;
    }

    @Override
    public ProjectSampleCheckListDTO getAllKeyProcessesQuestion(String id, String time, String projectId, UserPropertyStaffEntity userPropertyStaffEntity) {
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
        List<ProjectSampleCheckEntity> list = projectSampleCheckRepository.getAllKeyProcessesQuestion(id, time, projectId, userPropertyStaffEntity.getStaffId(), type);
        ProjectSampleCheckListDTO returnSampleCheckListDTO = new ProjectSampleCheckListDTO();
        List<ProjectSampleCheckDTO> returnProjectSampleCheckDTO = new ArrayList<ProjectSampleCheckDTO>();
        if (list != null && list.size() > 0) {
            for (ProjectSampleCheckEntity projectSampleCheckEntity : list) {
                ProjectSampleCheckDTO returnSampleCheckDTO = returntSampleCheckEntity(projectSampleCheckEntity);
                returnProjectSampleCheckDTO.add(returnSampleCheckDTO);
            }
            if (returnProjectSampleCheckDTO.size() > 0) {
                returnSampleCheckListDTO.setList(returnProjectSampleCheckDTO);
                returnSampleCheckListDTO.setId(returnProjectSampleCheckDTO.get(returnProjectSampleCheckDTO.size() - 1).getId());
                returnSampleCheckListDTO.setTimeStamp(returnProjectSampleCheckDTO.get(returnProjectSampleCheckDTO.size() - 1).getModifyDate());
                returnSampleCheckListDTO.setProjectId(returnProjectSampleCheckDTO.get(returnProjectSampleCheckDTO.size() - 1).getProjectId());
            }
        }
        return returnSampleCheckListDTO;
    }

    @Override
    public ApiResult searchSampleCheck(String projectId) {
        if (StringUtil.isEmpty(projectId)) {
            return ErrorResource.getError("tip_00000572");//项目编码不能为空
        }
        try {
            List<SearchSampleCheckDTO> searchSampleCheckList = new ArrayList<SearchSampleCheckDTO>();
            int all = 0;
            int ing = 0;
            int end = 0;
            List<Object[]> list = projectSampleCheckRepository.searchSampleCheck(projectId);
            if (list != null && list.size() > 0) {
                SearchSampleCheckDTO searchSampleCheck = new SearchSampleCheckDTO();
                for (Object[] obj : list) {
                    SearchSampleCheckDTO sampleCheck = new SearchSampleCheckDTO();
                    sampleCheck.setProjectId(projectId);
                    sampleCheck.setClassifyOne(obj[0] == null ? "" : obj[0].toString());
                    sampleCheck.setClassifyOneName(obj[1] == null ? "" : obj[1].toString());
                    sampleCheck.setSampleCheckAll(obj[2] == null ? 0 : ((BigInteger) obj[2]).intValue());
                    sampleCheck.setSampleCheckEnd(obj[3] == null ? 0 : ((BigInteger) obj[3]).intValue());
                    sampleCheck.setSampleCheckIng(obj[4] == null ? 0 : ((BigInteger) obj[4]).intValue());
                    //总计数据
                    all += sampleCheck.getSampleCheckAll();
                    ing += sampleCheck.getSampleCheckIng();
                    end += sampleCheck.getSampleCheckEnd();
                    searchSampleCheckList.add(sampleCheck);
                }
                searchSampleCheck.setProjectId(projectId);
                searchSampleCheck.setClassifyOne("totalAll");
                searchSampleCheck.setClassifyOneName("总计");
                searchSampleCheck.setSampleCheckAll(all);
                searchSampleCheck.setSampleCheckIng(ing);
                searchSampleCheck.setSampleCheckEnd(end);
                searchSampleCheckList.add(searchSampleCheck);
            }
            return new SuccessApiResult(searchSampleCheckList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    @Override
    public ApiResult updageProjectSampleCheck(ProjectUpSamplCheckListDTO projectUpSamplCheckListDTO, UserPropertyStaffEntity user) {
        List<ProjectUpSamplCheckDTO> RequestSampleCheckList = projectUpSamplCheckListDTO.getList();
        //返回数据
        ProjectSampleCheckListDTO returnSampleCheckListDTO = new ProjectSampleCheckListDTO();
        List<ProjectSampleCheckDTO> returnProjectSampleCheckDTO = new ArrayList<ProjectSampleCheckDTO>();
        try {
            if (RequestSampleCheckList != null && RequestSampleCheckList.size() > 0) {

                //获取所有的样板点评信息
                for (ProjectUpSamplCheckDTO requestSampleCheck : RequestSampleCheckList) {
                    //样板点评信息为空跳出当前循环
                    if (StringUtil.isEmpty(requestSampleCheck.getSampleCheckId())) {
                        continue;
                    } else {
                        ProjectSampleCheckEntity sampleCheckEntity = projectSampleCheckRepository.querySampleCheckByID(requestSampleCheck.getSampleCheckId(), requestSampleCheck.getSampleCheckId());
                        //当前样板点评处理人不是当前处理人 返回
                        if (!sampleCheckEntity.getDealPeople().equals(user.getStaffId())) {
                            ProjectSampleCheckDTO returnSampleCheckDTO = returntSampleCheckDTO(null, null, sampleCheckEntity);
                            returnProjectSampleCheckDTO.add(returnSampleCheckDTO);
                            continue;
                        } else {
                            //甲方处理情况
                            if ("1".equals(requestSampleCheck.getType())) {
                                String type = "0";
                                if ("合格".equals(requestSampleCheck.getState())) {
                                    sampleCheckEntity.setState("合格");
                                    sampleCheckEntity.setModifyDate(new Date());
                                    type = "1";
                                } else {
                                    //将处理人改为乙方
                                    sampleCheckEntity.setState("不合格");
                                    sampleCheckEntity.setModifyDate(new Date());
                                    sampleCheckEntity.setDealPeople(sampleCheckEntity.getAssignId());
                                }
                                //保存整改信息 公共方法
                                saveSampleCheckDetails(requestSampleCheck.getSampleCheckDetails(), user, type);
                            }
                            //监理处理情况
                            else if ("2".equals(requestSampleCheck.getType())) {
                                if ("合格".equals(requestSampleCheck.getState())) {
                                    sampleCheckEntity.setState("不合格");
                                    sampleCheckEntity.setModifyDate(new Date());
                                    //将处理人改为甲方
                                    sampleCheckEntity.setDealPeople(sampleCheckEntity.getFirstParty());
                                } else {
                                    //将处理人改为乙方
                                    sampleCheckEntity.setState("不合格");
                                    sampleCheckEntity.setModifyDate(new Date());
                                    sampleCheckEntity.setDealPeople(sampleCheckEntity.getAssignId());
                                }
                                //保存整改信息 公共方法
                                saveSampleCheckDetails(requestSampleCheck.getSampleCheckDetails(), user, "2");
                            } else {
                                sampleCheckEntity.setState("不合格");
                                sampleCheckEntity.setModifyDate(new Date());
                                //将处理人改为监理
                                sampleCheckEntity.setDealPeople(sampleCheckEntity.getSupervisorId());
                                //保存整改信息 公共方法
                                saveSampleCheckDetails(requestSampleCheck.getSampleCheckDetails(), user, "3");
                            }
                            projectSampleCheckRepository.saveOrUpdateSampleCheck(sampleCheckEntity);
                            ProjectSampleCheckDTO returnSampleCheckDTO = returntSampleCheckDTO(null, null, sampleCheckEntity);
                            returnProjectSampleCheckDTO.add(returnSampleCheckDTO);
                        }
                    }
                }
                returnSampleCheckListDTO.setList(returnProjectSampleCheckDTO);
            }
            return new SuccessApiResult(returnSampleCheckListDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    @Override
    public List<ProjectSampleCheckAdminListDTO> getSampleCheckAdmin(RequestSampleAdminDTO requestSampleAdminDTO, WebPage webPage, String creaBy) {
        Map map = new HashMap();
        map.put("projectId", requestSampleAdminDTO.getProjectId());//项目id
        map.put("buildingId", requestSampleAdminDTO.getBuildingId());//楼栋id
        map.put("state", requestSampleAdminDTO.getState());//状态
        map.put("classifyOne", requestSampleAdminDTO.getClassifyOne());//一级分类
        map.put("classifyTwo", requestSampleAdminDTO.getClassifyTwo());//二级分类
        map.put("severityLevel", requestSampleAdminDTO.getSeverityLevel());//严重等级
        map.put("startDate", requestSampleAdminDTO.getStartDate());//开始日期
        map.put("endDate", requestSampleAdminDTO.getEndDate());//结束时间

        map.put("supplier", "");//责任单位
        map.put("firstPartyName", "");//甲方负责人名字
        map.put("supervisorName", "");//第三方监理名字
        map.put("assignName", "");//整改人名字
        map.put("projectList", "NO");//项目权限
        map.put("creaBy", creaBy);
        if (!StringUtil.isEmpty(requestSampleAdminDTO.getSupplier())) {
            map.put("supplier", "%" + requestSampleAdminDTO.getSupplier() + "%");//材料单位
        }
        if (!StringUtil.isEmpty(requestSampleAdminDTO.getFirstPartyName())) {
            map.put("firstPartyName", "%" + requestSampleAdminDTO.getFirstPartyName() + "%");//甲方负责人名字
        }
        if (!StringUtil.isEmpty(requestSampleAdminDTO.getSupervisorName())) {//监理
            map.put("supervisorName", "%" + requestSampleAdminDTO.getSupervisorName() + "%");
        }
        if (!StringUtil.isEmpty(requestSampleAdminDTO.getAssignName())) {//乙方
            map.put("assignName", "%" + requestSampleAdminDTO.getAssignName() + "%");
        }
        List<ProjectSampleCheckAdminListDTO> reList = new ArrayList<ProjectSampleCheckAdminListDTO>();
        List<ProjectSampleCheckEntity> list = null;
//        if (!StringUtil.isEmpty(requestSampleAdminDTO.getProjectId())) {
//            list = projectSampleCheckRepository.getSampleCheckAdmin(map, webPage);
//        } else {
//            boolean f = getAllClassifyService.getRoleViewmodelByStaffId(creaBy);
//            if (f) {
//                list = projectSampleCheckRepository.getSampleCheckAdmin(map, webPage);
//            } else {
//                List<ProjectProjectEntity> projectList1 = staffEmployRepository.getProjectListForAgency(creaBy);
//                String proejctAll = "";
//                if (projectList1 != null) {
//                    for (ProjectProjectEntity project : projectList1) {
//                        proejctAll += "'" + project.getId() + "',";
//                    }
//                }
//                List<ProjectProjectEntity> projectList2 = staffEmployRepository.getProjectListByStaffId(creaBy);
//                if (projectList2 != null) {
//                    for (ProjectProjectEntity project : projectList2) {
//                        proejctAll += "'" + project.getId() + "',";
//                    }
//                }
//                String project = proejctAll.substring(0, proejctAll.length() - 1);
//                map.put("projectList", project);//项目权限
//                list = projectSampleCheckRepository.getSampleCheckAdmin(map, webPage);
//            }
//        }
        if(!StringUtil.isEmpty(requestSampleAdminDTO.getProjectId())){
            list = projectSampleCheckRepository.getSampleCheckAdmin(map, webPage);
        }
        if (list != null && !list.isEmpty()) {
            for (ProjectSampleCheckEntity sampleCheckEntity : list) {
                ProjectSampleCheckAdminListDTO sampleCheckAdminDTO = new ProjectSampleCheckAdminListDTO();

                //样板点评Id
                sampleCheckAdminDTO.setSampleCheckId(sampleCheckEntity.getSampleCheckId() == null ? "" : sampleCheckEntity.getSampleCheckId());
                //项目名称
                sampleCheckAdminDTO.setProjectName(sampleCheckEntity.getProjectName() == null ? "" : sampleCheckEntity.getProjectName());
                //项目ID
                sampleCheckAdminDTO.setProjectId(sampleCheckEntity.getProjectId() == null ? "" : sampleCheckEntity.getProjectId());
                //样板点评标题
                sampleCheckAdminDTO.setTitle(sampleCheckEntity.getTitle() == null ? "" : sampleCheckEntity.getTitle());
                //三级分类
                sampleCheckAdminDTO.setClassifyThree(sampleCheckEntity.getClassifyThreeName() == null ? "" : sampleCheckEntity.getClassifyThreeName());
                //严重等级
                sampleCheckAdminDTO.setSeverityLevel(sampleCheckEntity.getSeverityLevel() == null ? "" : sampleCheckEntity.getSeverityLevel());
                //供应商名字
                sampleCheckAdminDTO.setSupplier(sampleCheckEntity.getSupplier() == null ? "" : sampleCheckEntity.getSupplier());
                //甲方负责人
                sampleCheckAdminDTO.setFirstPartyName(sampleCheckEntity.getFirstPartyName() == null ? "" : sampleCheckEntity.getFirstPartyName());
                //第三方监理
                sampleCheckAdminDTO.setSupervisorName(sampleCheckEntity.getSupervisorName() == null ? "" : sampleCheckEntity.getSupervisorName());
                //乙方负责人
                sampleCheckAdminDTO.setAssignName(sampleCheckEntity.getAssignName() == null ? "" : sampleCheckEntity.getAssignName());
                //状态
                sampleCheckAdminDTO.setState(sampleCheckEntity.getState() == null ? "" : sampleCheckEntity.getState());
                //创建时间
                sampleCheckAdminDTO.setCreateOn(sampleCheckEntity.getCreateOn() == null ? "" : DateUtils.format(sampleCheckEntity.getCreateOn(), DateUtils.FORMAT_SHORT));
                reList.add(sampleCheckAdminDTO);
            }
        }
        return reList;
    }

    @Override
    public void exportExcel(String title, String[] headers, ServletOutputStream out, RequestSampleAdminDTO requestSampleAdminDTO, WebPage webPage, String creaBy) {

        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        Map map = new HashMap();
        map.put("projectId", requestSampleAdminDTO.getProjectId());//项目id
        map.put("buildingId", requestSampleAdminDTO.getBuildingId());//楼栋id
        map.put("state", requestSampleAdminDTO.getState());//状态
        map.put("classifyOne", requestSampleAdminDTO.getClassifyOne());//一级分类
        map.put("classifyTwo", requestSampleAdminDTO.getClassifyTwo());//二级分类
        map.put("startDate", requestSampleAdminDTO.getStartDate());//开始日期
        map.put("endDate", requestSampleAdminDTO.getEndDate());//结束时间
        map.put("severityLevel", requestSampleAdminDTO.getSeverityLevel());//严重等级

        map.put("supplier", "");//责任单位
        map.put("firstPartyName", "");//甲方负责人名字
        map.put("supervisorName", "");//第三方监理名字
        map.put("assignName", "");//整改人名字
        map.put("projectList", "NO");//项目权限
        map.put("creaBy", creaBy);
        if (!StringUtil.isEmpty(requestSampleAdminDTO.getSupplier())) {
            map.put("supplier", "%" + requestSampleAdminDTO.getSupplier() + "%");//材料单位
        }
        if (!StringUtil.isEmpty(requestSampleAdminDTO.getFirstPartyName())) {
            map.put("firstPartyName", "%" + requestSampleAdminDTO.getFirstPartyName() + "%");//甲方负责人名字
        }
        if (!StringUtil.isEmpty(requestSampleAdminDTO.getSupervisorName())) {//监理
            map.put("supervisorName", "%" + requestSampleAdminDTO.getSupervisorName() + "%");
        }
        if (!StringUtil.isEmpty(requestSampleAdminDTO.getAssignName())) {//乙方
            map.put("assignName", "%" + requestSampleAdminDTO.getAssignName() + "%");
        }
        List<ExportExcelSampleDTO> reList = new ArrayList<ExportExcelSampleDTO>();
        List<ProjectSampleCheckEntity> list = null;
//        if (!StringUtil.isEmpty(requestSampleAdminDTO.getProjectId())) {
//            list = projectSampleCheckRepository.getSampleCheckAdmin(map, webPage);
//        } else {
//            boolean f = getAllClassifyService.getRoleViewmodelByStaffId(creaBy);
//            if (f) {
//                list = projectSampleCheckRepository.getSampleCheckAdmin(map, webPage);
//            } else {
//                List<ProjectProjectEntity> projectList1 = staffEmployRepository.getProjectListForAgency(creaBy);
//                String proejctAll = "";
//                if (projectList1 != null) {
//                    for (ProjectProjectEntity project : projectList1) {
//                        proejctAll += "'" + project.getId() + "',";
//                    }
//                }
//                List<ProjectProjectEntity> projectList2 = staffEmployRepository.getProjectListByStaffId(creaBy);
//                if (projectList2 != null) {
//                    for (ProjectProjectEntity project : projectList2) {
//                        proejctAll += "'" + project.getId() + "',";
//                    }
//                }
//                String project = proejctAll.substring(0, proejctAll.length() - 1);
//                map.put("projectList", project);//项目权限
//                list = projectSampleCheckRepository.getSampleCheckAdmin(map, webPage);
//            }
//        }
        if(!StringUtil.isEmpty(requestSampleAdminDTO.getProjectId())){
            list = projectSampleCheckRepository.getSampleCheckAdmin(map, webPage);
        }
        if (list != null) {
            int num = 1;
            for (ProjectSampleCheckEntity sampleCheckEntity : list) {
                ExportExcelSampleDTO excelSampleDTO = new ExportExcelSampleDTO();
                excelSampleDTO.setNum(num++);
                excelSampleDTO.setProjectName(sampleCheckEntity.getProjectName() == null ? "" : sampleCheckEntity.getProjectName());
                excelSampleDTO.setTitle(sampleCheckEntity.getTitle() == null ? "" : sampleCheckEntity.getTitle());
                excelSampleDTO.setDescription(sampleCheckEntity.getDescription() == null ? "" : sampleCheckEntity.getDescription());
                excelSampleDTO.setClassifyThree(sampleCheckEntity.getClassifyThreeName() == null ? "" : sampleCheckEntity.getClassifyThreeName());
                excelSampleDTO.setSeverityLevel(sampleCheckEntity.getSeverityLevel() == null ? "" : sampleCheckEntity.getSeverityLevel());
                excelSampleDTO.setSupplier(sampleCheckEntity.getSupplier() == null ? "" : sampleCheckEntity.getSupplier());
                excelSampleDTO.setAssignName(sampleCheckEntity.getAssignName() == null ? "" : sampleCheckEntity.getAssignName());
                excelSampleDTO.setFirstPartyName(sampleCheckEntity.getFirstPartyName() == null ? "" : sampleCheckEntity.getFirstPartyName());
                excelSampleDTO.setSupervisorName(sampleCheckEntity.getSupervisorName() == null ? "" : sampleCheckEntity.getSupervisorName());
                excelSampleDTO.setCreateOn(sampleCheckEntity.getCreateOn() == null ? "" : DateUtils.format(sampleCheckEntity.getCreateOn(), DateUtils.FORMAT_LONG));
                excelSampleDTO.setCreateBy(sampleCheckEntity.getCreateName() == null ? "" : sampleCheckEntity.getCreateName());
                excelSampleDTO.setState(sampleCheckEntity.getState() == null ? "" : sampleCheckEntity.getState());
                reList.add(excelSampleDTO);
            }
        }
        ExportExcel<ExportExcelSampleDTO> ex = new ExportExcel<ExportExcelSampleDTO>();
        ex.exportExcel(title, headers, reList, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    @Override
    public ProjectSampleCheckCountListDTO getSampleCheckCount(RequestSampleAdminDTO requestSampleAdminDTO, WebPage webPage, String creaBy) {
        Map map = new HashMap();
        map.put("projectId", requestSampleAdminDTO.getProjectId());//项目id
        map.put("projectList", "NO");//项目权限
        map.put("creaBy", creaBy);
        //返回数据
        ProjectSampleCheckCountListDTO checkCount = new ProjectSampleCheckCountListDTO();
        if (!StringUtil.isEmpty(requestSampleAdminDTO.getProjectId())) {
            checkCount.setProjectId(requestSampleAdminDTO.getProjectId());
        }
        List<ProjectSampleCheckCountDTO> reList = new ArrayList<ProjectSampleCheckCountDTO>();
        List<Object[]> list = null;
        int count = 0;
        if (!StringUtil.isEmpty(requestSampleAdminDTO.getProjectId())) {
            count = projectSampleCheckRepository.getSampleCheckWebPage(map);
            webPage.setRecordCount(count);
            list = projectSampleCheckRepository.getSampleCheckCount(map, webPage);
        } else {
            boolean f = getAllClassifyService.getRoleViewmodelByStaffId(creaBy);
            if (f) {
                count = projectSampleCheckRepository.getSampleCheckWebPage(map);
                webPage.setRecordCount(count);
                list = projectSampleCheckRepository.getSampleCheckCount(map, webPage);
            } else {
                List<ProjectProjectEntity> projectList1 = staffEmployRepository.getProjectListForAgency(creaBy);
                String proejctAll = "";
                if (projectList1 != null) {
                    for (ProjectProjectEntity project : projectList1) {
                        proejctAll += "'" + project.getId() + "',";
                    }
                }
                List<ProjectProjectEntity> projectList2 = staffEmployRepository.getProjectListByStaffId(creaBy);
                if (projectList2 != null) {
                    for (ProjectProjectEntity project : projectList2) {
                        proejctAll += "'" + project.getId() + "',";
                    }
                }
                String project = proejctAll.substring(0, proejctAll.length() - 1);
                map.put("projectList", project);//项目权限
                count = projectSampleCheckRepository.getSampleCheckWebPage(map);
                webPage.setRecordCount(count);
                list = projectSampleCheckRepository.getSampleCheckCount(map, webPage);
            }
        }


        int qualified = 0;//合格率
        int unqualified = 0;//不合格率
        if (list != null && !list.isEmpty()) {
            for (Object[] obj : list) {
                ProjectSampleCheckCountDTO sampleCheckCount = new ProjectSampleCheckCountDTO();
                sampleCheckCount.setProjectId(obj[0] == null ? "" : obj[0].toString());
                sampleCheckCount.setProjectName(obj[1] == null ? "" : obj[1].toString());
                sampleCheckCount.setClassifyThree(obj[3] == null ? "" : obj[3].toString());
                sampleCheckCount.setQualified(obj[4] == null ? 0 : ((BigInteger) obj[4]).intValue());
                sampleCheckCount.setUnqualified(obj[5] == null ? 0 : ((BigInteger) obj[5]).intValue());
                qualified += sampleCheckCount.getQualified();//合格数
                unqualified += sampleCheckCount.getUnqualified();//不合格数
                reList.add(sampleCheckCount);
            }
        }

        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(0);
        int total = qualified + unqualified;
        if (total > 0) {
            checkCount.setQualified(numberFormat.format((float) qualified / (float) total * 100) + "%");
            checkCount.setUnqualified(numberFormat.format((float) unqualified / (float) total * 100) + "%");
        } else {

            checkCount.setQualified("0%");
            checkCount.setUnqualified("0%");
        }
        checkCount.setTotal(total + "");
        checkCount.setList(reList);
        return checkCount;
    }

    @Override
    public ProjectSampleCheckAdminBackDTO searchSampleCheckDetailBySampleCheckId(String sampleCheckId) {
        ProjectSampleCheckAdminBackDTO projectSampleCheckAdminBackDTO = new ProjectSampleCheckAdminBackDTO();
        ProjectSampleCheckEntity sampleCheckEntity = projectSampleCheckRepository.querySampleCheckByID(sampleCheckId, "");
        if (sampleCheckEntity != null) {
            projectSampleCheckAdminBackDTO.setSampleCheckId(projectSampleCheckAdminBackDTO.getSampleCheckId() == null ? "" : sampleCheckEntity.getSampleCheckId());//样板点评ID
            projectSampleCheckAdminBackDTO.setTitle(projectSampleCheckAdminBackDTO.getTitle() == null ? "" : sampleCheckEntity.getTitle());//样板点评标题
            projectSampleCheckAdminBackDTO.setDescription(projectSampleCheckAdminBackDTO.getDescription() == null ? "" : sampleCheckEntity.getDescription());//描述
            projectSampleCheckAdminBackDTO.setCreateName(projectSampleCheckAdminBackDTO.getCreateName() == null ? "" : sampleCheckEntity.getCreateName());//创建人姓名
            projectSampleCheckAdminBackDTO.setCreateOn(sampleCheckEntity.getCreateOn() == null ? "" : DateUtils.format(sampleCheckEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
            projectSampleCheckAdminBackDTO.setModifyDate(sampleCheckEntity.getModifyDate() == null ? "" : DateUtils.format(sampleCheckEntity.getModifyDate(), DateUtils.FORMAT_LONG));//修改时间
            projectSampleCheckAdminBackDTO.setProjectName(sampleCheckEntity.getProjectName() == null ? "" : sampleCheckEntity.getProjectName());//项目名称
            projectSampleCheckAdminBackDTO.setBuildingName(sampleCheckEntity.getBuildingName() == null ? "" : sampleCheckEntity.getBuildingName());//楼栋名称
            projectSampleCheckAdminBackDTO.setClassifyThreeName(sampleCheckEntity.getClassifyThreeName() == null ? "" : sampleCheckEntity.getClassifyThreeName());//三级分类名称
            projectSampleCheckAdminBackDTO.setState(sampleCheckEntity.getState() == null ? "" : sampleCheckEntity.getState());//状态
            projectSampleCheckAdminBackDTO.setSeverityLevel(sampleCheckEntity.getSeverityLevel() == null ? "" : sampleCheckEntity.getSeverityLevel());//严重等级
            projectSampleCheckAdminBackDTO.setCheckPosition(sampleCheckEntity.getCheckPosition() == null ? "" : sampleCheckEntity.getCheckPosition());//检查部位
            projectSampleCheckAdminBackDTO.setCheckDate(sampleCheckEntity.getCheckDate() == null ? "" : DateUtils.format(sampleCheckEntity.getCheckDate()));//检查时间
            projectSampleCheckAdminBackDTO.setSupplier(sampleCheckEntity.getSupplier() == null ? "" : sampleCheckEntity.getSupplier());//责任单位名称
            projectSampleCheckAdminBackDTO.setAssignName(sampleCheckEntity.getAssignName() == null ? "" : sampleCheckEntity.getAssignName());//乙方负责人名字
            projectSampleCheckAdminBackDTO.setSupervisorName(sampleCheckEntity.getSupervisorName() == null ? "" : sampleCheckEntity.getSupervisorName());//第三方监理名字
            projectSampleCheckAdminBackDTO.setFirstPartyName(sampleCheckEntity.getFirstPartyName() == null ? "" : sampleCheckEntity.getFirstPartyName()); //甲方负责人名字
            projectSampleCheckAdminBackDTO.setRectificationPeriod(sampleCheckEntity.getRectificationPeriod() == null ? "" : DateUtils.format(sampleCheckEntity.getRectificationPeriod(), DateUtils.FORMAT_SHORT));//整改时限
            projectSampleCheckAdminBackDTO.setShutDown(sampleCheckEntity.getShutDown() == null ? "" : sampleCheckEntity.getShutDown());//关闭理由
            projectSampleCheckAdminBackDTO.setShutDownBy(sampleCheckEntity.getShutDownBy() == null ? "" : sampleCheckEntity.getShutDownBy());//关闭人
            projectSampleCheckAdminBackDTO.setShutDownOn(sampleCheckEntity.getShutDownOn() == null ? "" : DateUtils.format(sampleCheckEntity.getShutDownOn(), DateUtils.FORMAT_LONG));//关闭时间
            //抄送人
            List<Object[]> idList = dailyPatrolInspectionRepository.getProjectCopy(sampleCheckEntity.getSampleCheckId());//抄送人list
            List<KeyProcessesCopyDTO> copyDetailsList = new ArrayList<KeyProcessesCopyDTO>();
            if (idList != null) {
                for (Object[] idObj : idList) {
                    KeyProcessesCopyDTO keyProcessesCopyDTO = new KeyProcessesCopyDTO();
                    keyProcessesCopyDTO.setId(idObj[1] == null ? "" : idObj[1].toString());//抄送人ID
                    keyProcessesCopyDTO.setName(idObj[2] == null ? "" : idObj[2].toString());//抄送人名称
                    copyDetailsList.add(keyProcessesCopyDTO);
                }
            }
            projectSampleCheckAdminBackDTO.setCopyList(copyDetailsList);
            //查询该样板点评下指标信息
            List<ProjectSampleCheckDetailsAdminBackDTO> sampleCheckDetailsDTOList = new ArrayList<ProjectSampleCheckDetailsAdminBackDTO>();
            List<ProjectSampleCheckDetailsEntity> sampleCheckDetailsEntity = projectSampleCheckRepository.querySampleCheckDetailsById(sampleCheckEntity.getSampleCheckId());
            if (sampleCheckDetailsEntity != null && sampleCheckDetailsEntity.size() > 0) {
                for (ProjectSampleCheckDetailsEntity checkDetailsEntity : sampleCheckDetailsEntity) {
                    ProjectSampleCheckDetailsAdminBackDTO sampleCheckDetailsDTO = new ProjectSampleCheckDetailsAdminBackDTO();
                    sampleCheckDetailsDTO.setId(checkDetailsEntity.getId() == null ? "" : checkDetailsEntity.getId());//id
                    sampleCheckDetailsDTO.setSampleCheckId(checkDetailsEntity.getSampleCheckId() == null ? "" : checkDetailsEntity.getSampleCheckId());//样板点评ID
                    sampleCheckDetailsDTO.setTargetId(checkDetailsEntity.getTargetId() == null ? "" : checkDetailsEntity.getTargetId());//指标ID
                    sampleCheckDetailsDTO.setTargetName(checkDetailsEntity.getTargetName() == null ? "" : checkDetailsEntity.getTargetName());//指标名
                    sampleCheckDetailsDTO.setDescription(checkDetailsEntity.getDescription() == null ? "" : checkDetailsEntity.getDescription());//描述
                    sampleCheckDetailsDTO.setGuide(checkDetailsEntity.getGuide() == null ? "" : checkDetailsEntity.getGuide());//指引
                    sampleCheckDetailsDTO.setState(checkDetailsEntity.getState() == null ? "" : checkDetailsEntity.getState());//状态
                    sampleCheckDetailsDTO.setCreateOn(checkDetailsEntity.getCreateOn() == null ? "" : DateUtils.format(checkDetailsEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
                    sampleCheckDetailsDTO.setModifyDate(checkDetailsEntity.getModifyDate() == null ? "" : DateUtils.format(checkDetailsEntity.getModifyDate(), DateUtils.FORMAT_LONG));//修改时间
                    sampleCheckDetailsDTO.setFlag(checkDetailsEntity.getFlag() == null ? "" : checkDetailsEntity.getFlag());

                    //查询指标信息图片
                    List<ProjectImagesEntity> imagesList = projectSampleCheckRepository.getProjectImages(checkDetailsEntity.getId());
                    if (imagesList != null) {
                        //******************返回数据list*******指标图片*********
                        List<SampleCheckImageDTO> getimageList = new ArrayList<SampleCheckImageDTO>();
                        List<SampleCheckImageDTO> getimage2List = new ArrayList<SampleCheckImageDTO>();
                        for (ProjectImagesEntity projectImagesEntity : imagesList) {
                            SampleCheckImageDTO getImage = new SampleCheckImageDTO();
                            getImage.setId(projectImagesEntity.getId() == null ? "" : projectImagesEntity.getId());
                            getImage.setBusinessId(projectImagesEntity.getBusinessId() == null ? "" : projectImagesEntity.getBusinessId());//业务id
                            getImage.setImageUrl(projectImagesEntity.getUrl() == null ? "" : projectImagesEntity.getUrl());//图片地址
                            getImage.setType(projectImagesEntity.getType() == null ? "" : projectImagesEntity.getType());//类型 1:日常巡检；2检查验收；3：关键工序；4：样板点评；5：材料验收；6：旁站
                            getImage.setState(projectImagesEntity.getState() == null ? "" : projectImagesEntity.getState());//状态 0:不可用；1：可用
                            //创建时间
                            getImage.setCreateOn(projectImagesEntity.getCreateOn() == null ? "" : DateUtils.format(projectImagesEntity.getCreateOn()));
                            //修改时间
                            getImage.setModifyOn(projectImagesEntity.getModifyOn() == null ? "" : DateUtils.format(projectImagesEntity.getModifyOn()));
                            //合格 不合格
                            getImage.setQualifiedState(projectImagesEntity.getQualifiedState() == null ? "" : projectImagesEntity.getQualifiedState());
                            if (!StringUtil.isEmpty(projectImagesEntity.getQualifiedState()) && "合格".equals(projectImagesEntity.getQualifiedState())) {
                                getimageList.add(getImage);
                            } else {
                                getimage2List.add(getImage);
                            }

                        }
                        //指标信息图片
                        sampleCheckDetailsDTO.setImageList(getimageList);//合格
                        sampleCheckDetailsDTO.setImage2List(getimage2List);//不合格
                    }
//                    List<ProjectSampleCheckChangedDTO> sampleCheckChanged = new ArrayList<ProjectSampleCheckChangedDTO>();//样板点评指标整改信息
                    //根据指标信息中的id查询整改信息
                    List<ProjectSampleCheckChangedEntity> sampleCheckChangedEntity = projectSampleCheckRepository.querySampleCheckChangedEntity(checkDetailsEntity.getId());
                    if (sampleCheckChangedEntity != null) {
                        List<ProjectSampleCheckChangedAdminBackDTO> targetDTOByPartyBAnnalList = new ArrayList<ProjectSampleCheckChangedAdminBackDTO>();//乙方整改信息
                        List<ProjectSampleCheckChangedAdminBackDTO> targetDTOBySupervisionAnnalList = new ArrayList<ProjectSampleCheckChangedAdminBackDTO>();//监理整改信息
                        List<ProjectSampleCheckChangedAdminBackDTO> targetDTOByPartyAAnnalList = new ArrayList<ProjectSampleCheckChangedAdminBackDTO>();//甲方整改信息

                        for (ProjectSampleCheckChangedEntity checkChangedEntity : sampleCheckChangedEntity) {
                            ProjectSampleCheckChangedAdminBackDTO checkChangedDTO = new ProjectSampleCheckChangedAdminBackDTO();
                            checkChangedDTO.setId(checkChangedEntity.getId() == null ? "" : checkChangedEntity.getId());//ID
                            checkChangedDTO.setCheckDetailsId(checkChangedEntity.getCheckDetailsId() == null ? "" : checkChangedEntity.getCheckDetailsId());//指标ID
                            checkChangedDTO.setDescription(checkChangedEntity.getDescription() == null ? "" : checkChangedEntity.getDescription());//描述
                            checkChangedDTO.setChangeTime(checkChangedEntity.getCheckDetailsId() == null ? "" : checkChangedEntity.getCheckDetailsId());//整改时间
                            checkChangedDTO.setCreateBy(checkChangedEntity.getCreateBy() == null ? "" : checkChangedEntity.getCreateBy());//创建人
                            checkChangedDTO.setCreateOn(checkChangedEntity.getCreateOn() == null ? "" : DateUtils.format(checkChangedEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
                            checkChangedDTO.setType(checkChangedEntity.getType() == null ? "" : checkChangedEntity.getType());//3 乙方整改  2 第三方监理整改   1 甲方整改
                            checkChangedDTO.setState(checkChangedEntity.getState() == null ? "" : checkChangedEntity.getState());//状态
                            checkChangedDTO.setSerialNumber(checkChangedEntity.getSerialNumber() == null ? "" : checkChangedEntity.getSerialNumber());//排序号
                            List<ProjectImagesEntity> projectImagesList = projectSampleCheckRepository.getProjectImages(checkChangedEntity.getId());
                            if (projectImagesList != null) {
                                //******************返回数据list*******整改图片*********
                                List<SampleCheckImageDTO> getimageList = new ArrayList<SampleCheckImageDTO>();
                                List<SampleCheckImageDTO> getimage2List = new ArrayList<SampleCheckImageDTO>();
                                for (ProjectImagesEntity projectImagesEntity : projectImagesList) {
                                    SampleCheckImageDTO getImage = new SampleCheckImageDTO();
                                    getImage.setId(projectImagesEntity.getId() == null ? "" : projectImagesEntity.getId());
                                    getImage.setBusinessId(projectImagesEntity.getBusinessId() == null ? "" : projectImagesEntity.getBusinessId());//业务id
                                    getImage.setImageUrl(projectImagesEntity.getUrl() == null ? "" : projectImagesEntity.getUrl());//图片地址
                                    getImage.setType(projectImagesEntity.getType() == null ? "" : projectImagesEntity.getType());//类型 1:日常巡检；2检查验收；3：关键工序；4：样板点评；5：材料验收；6：旁站
                                    getImage.setState(projectImagesEntity.getState() == null ? "" : projectImagesEntity.getState());//状态 0:不可用；1：可用
                                    //创建时间
                                    getImage.setCreateOn(projectImagesEntity.getCreateOn() == null ? "" : DateUtils.format(projectImagesEntity.getCreateOn()));
                                    //修改时间
                                    getImage.setModifyOn(projectImagesEntity.getModifyOn() == null ? "" : DateUtils.format(projectImagesEntity.getModifyOn()));
                                    //合格 不合格
                                    getImage.setQualifiedState(projectImagesEntity.getQualifiedState() == null ? "" : projectImagesEntity.getQualifiedState());
                                    getimageList.add(getImage);
                                    //整改信息图片
                                    if (!StringUtil.isEmpty(projectImagesEntity.getQualifiedState()) && "合格".equals(projectImagesEntity.getQualifiedState())) {
                                        getimageList.add(getImage);//合格图片
                                    } else {
                                        getimage2List.add(getImage);//不合格图片
                                    }
                                }
                                checkChangedDTO.setImageList(getimageList);
                                checkChangedDTO.setImage2List(getimage2List);
                            }

                            if (checkChangedDTO.getType().equals("3")) {//乙方整改
                                targetDTOByPartyBAnnalList.add(checkChangedDTO);
                            } else if (checkChangedDTO.getType().equals("2")) {//监理整改
                                targetDTOBySupervisionAnnalList.add(checkChangedDTO);
                            } else if (checkChangedDTO.getType().equals("1")) {//甲方整改
                                targetDTOByPartyAAnnalList.add(checkChangedDTO);
                            }
                        }
                        sampleCheckDetailsDTO.setTargetDTOByPartyAAnnalList(targetDTOByPartyAAnnalList);
                        sampleCheckDetailsDTO.setTargetDTOByPartyBAnnalList(targetDTOByPartyBAnnalList);
                        sampleCheckDetailsDTO.setTargetDTOBySupervisionAnnalList(targetDTOBySupervisionAnnalList);
                    }
                    sampleCheckDetailsDTOList.add(sampleCheckDetailsDTO);
                }
                projectSampleCheckAdminBackDTO.setSampleCheckDetails(sampleCheckDetailsDTOList);//样板点评指标信息
            }
        }
        return projectSampleCheckAdminBackDTO;
    }

    @Override
    public String executeAbnormalOffState(ProjectSampleCheckAdminBackDTO backDTO, UserInformationEntity userInformationEntity) {
        String resultMessage = "";
        ProjectSampleCheckEntity sampleCheckEntity = projectSampleCheckRepository.querySampleCheckByID(backDTO.getSampleCheckId(), "");
        if (sampleCheckEntity != null) {
            sampleCheckEntity.setState("非正常关闭");
            sampleCheckEntity.setShutDownBy(userInformationEntity.getStaffName());//关单人
            sampleCheckEntity.setShutDown(backDTO.getShutDown());//关单原因
            sampleCheckEntity.setShutDownOn(new Date());//关单时间
            sampleCheckEntity.setModifyDate(new Date());
            boolean f = projectSampleCheckRepository.saveOrUpdateSampleCheck(sampleCheckEntity);
            if (f) {
                resultMessage = "0";//成功
            } else {
                resultMessage = "1";//失败
            }
        }
        return resultMessage;
    }

    /**
     * 创建标题
     *
     * @param slide
     * @param content
     */
    public void createTitle(Slide slide, String content) {
        TextBox title1 = slide.addTitle();
        title1.setFillColor(Color.LIGHT_GRAY);
        title1.setAnchor(new java.awt.Rectangle(0, 0, 100, 80));
        RichTextRun titleRun1 = title1.getTextRun().getRichTextRuns()[0];
        titleRun1.setFontColor(Color.LIGHT_GRAY);
        title1.setText("1");

        TextBox title = slide.addTitle();
        title.setFillColor(new Color(8, 46, 84));
        title.setAnchor(new java.awt.Rectangle(100, 0, 650, 80));
        RichTextRun titleRun = title.getTextRun().getRichTextRuns()[0];
        titleRun.setFontSize(32);
        titleRun.setFontName("华文楷体");
        titleRun.setFontColor(Color.WHITE);
        title.setText(content);
    }

    /**
     * 创建文本内容
     *
     * @param content
     * @param size
     * @param slide
     * @param anchor
     */
    public void createText(String content, int size, Slide slide, Rectangle2D anchor) {
        TextBox text = new TextBox();
        text.setFillColor(Color.WHITE);
        text.setAnchor(anchor);
        RichTextRun richTextRun = text.getTextRun().getRichTextRuns()[0];
        richTextRun.setFontColor(Color.black);
        richTextRun.setBold(true);
        richTextRun.setFontSize(size);
        richTextRun.setFontName("楷体");
        //setText参数字符串可以包含回车、换行符,但是最后一行不能以\r\n结尾,否则设置的格式没有效果(v3.5)
        richTextRun.setText(content);
        slide.addShape(text);
    }

    /**
     * 插入图片
     *
     * @param ppt
     * @param slide
     * @param imageUrl
     * @param filePath
     * @param httpServletRequest
     * @param anchor
     * @throws Exception
     */
    public void insertPicture(SlideShow ppt, Slide slide, String imageUrl, String filePath, HttpServletRequest httpServletRequest, Rectangle2D anchor) throws Exception {

//        ExportPPT.saveImage(imageUrl, httpServletRequest);  //改为导出缩放图

        byte[] pictureData = IOUtils.toByteArray(new FileInputStream(filePath));
        int picIndex1 = ppt.addPicture(pictureData, Picture.JPEG);
        Picture jpg = new Picture(picIndex1);
        jpg.setAnchor(anchor);
        /*if (m == 1) {
            jpg.setAnchor(new java.awt.Rectangle(20, 100, 120, 100));
        } else if (m == 2) {
            jpg.setAnchor(new java.awt.Rectangle(150, 100, 120, 100));
        } else if (m == 3) {
            jpg.setAnchor(new java.awt.Rectangle(280, 100, 120, 100));
        } else if (m == 4) {
            jpg.setAnchor(new java.awt.Rectangle(410, 100, 120, 100));
        } else if (m == 5) {
            jpg.setAnchor(new java.awt.Rectangle(540, 100, 120, 100));
        }*/
        slide.addShape(jpg);
    }

    /**
     * 整改记录的图片方法
     *
     * @param imageList
     * @param ppt
     * @param titleContent
     * @param description
     * @param filePath
     * @param httpServletRequest
     * @throws Exception
     */
    public void annal(List<SampleCheckImageDTO> imageList, SlideShow ppt, String titleContent, String description, String filePath, HttpServletRequest httpServletRequest) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("");
        String oldFilePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "oldExportPPT.jpg"; //用于将远程图片下载到tomcat中

        int size = imageList.size();
        //创建幻灯片
        Slide slide = ppt.createSlide();
        Slide slide12 = null;
        Slide slide13 = null;
        //添加幻灯片标题
        createTitle(slide, titleContent);
        //添加文本框
        createText("描述：" + description, 18, slide, new java.awt.Rectangle(0, 400, 760, 50));
        int m = 1;
        if (size == 3 || size == 4 || size == 5) {
            //创建幻灯片
            slide12 = ppt.createSlide();
            //添加幻灯片标题
            createTitle(slide12, titleContent);
            //添加文本框
            createText("描述：" + description, 18, slide12, new java.awt.Rectangle(0, 400, 760, 50));
        }

        if (size == 1) {
            for (SampleCheckImageDTO sampleCheckImageDTO : imageList) {
                try {
                    ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                    Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                } catch (Exception e) {
                    throw e;
                }
                insertPicture(ppt, slide, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(240, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
            }
        } else if (size == 2) {
            for (SampleCheckImageDTO sampleCheckImageDTO : imageList) {
                if (m == 1) {
                    try {
                        ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                        Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                    } catch (Exception e) {
                        throw e;
                    }
                    insertPicture(ppt, slide, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(30, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
                } else {
                    try {
                        ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                        Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                    } catch (Exception e) {
                        throw e;
                    }
                    insertPicture(ppt, slide, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(390, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
                }
                m++;
            }
        } else if (size == 3) {
            for (SampleCheckImageDTO sampleCheckImageDTO : imageList) {
                if (m == 1) {
                    try {
                        ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                        Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                    } catch (Exception e) {
                        throw e;
                    }
                    insertPicture(ppt, slide, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(30, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
                } else if (m == 2) {
                    try {
                        ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                        Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                    } catch (Exception e) {
                        throw e;
                    }
                    insertPicture(ppt, slide, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(390, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
                } else {
                    try {
                        ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                        Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                    } catch (Exception e) {
                        throw e;
                    }
                    insertPicture(ppt, slide12, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(240, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
                }
                m++;
            }
        } else if (size == 4) {
            for (SampleCheckImageDTO sampleCheckImageDTO : imageList) {
                if (m == 1) {
                    try {
                        ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                        Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                    } catch (Exception e) {
                        throw e;
                    }
                    insertPicture(ppt, slide, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(30, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
                } else if (m == 2) {
                    try {
                        ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                        Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                    } catch (Exception e) {
                        throw e;
                    }
                    insertPicture(ppt, slide, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(390, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
                } else if (m == 3) {
                    try {
                        ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                        Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                    } catch (Exception e) {
                        throw e;
                    }
                    insertPicture(ppt, slide12, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(30, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
                } else {
                    try {
                        ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                        Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                    } catch (Exception e) {
                        throw e;
                    }
                    insertPicture(ppt, slide12, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(390, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
                }
                m++;
            }
        } else if (size == 5) {
            //创建幻灯片
            slide13 = ppt.createSlide();
            //添加幻灯片标题
            createTitle(slide13, titleContent);
            //添加文本框
            createText("描述：" + description, 18, slide13, new java.awt.Rectangle(0, 400, 760, 50));
            for (SampleCheckImageDTO sampleCheckImageDTO : imageList) {
                if (m == 1) {
                    try {
                        ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                        Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                    } catch (Exception e) {
                        throw e;
                    }
                    insertPicture(ppt, slide, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(30, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
                } else if (m == 2) {
                    try {
                        ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                        Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                    } catch (Exception e) {
                        throw e;
                    }
                    insertPicture(ppt, slide, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(390, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
                } else if (m == 3) {
                    try {
                        ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                        Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                    } catch (Exception e) {
                        throw e;
                    }
                    insertPicture(ppt, slide12, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(30, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
                } else if (m == 4) {
                    try {
                        ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                        Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                    } catch (Exception e) {
                        throw e;
                    }
                    insertPicture(ppt, slide12, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(390, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
                } else if (m == 5) {
                    try {
                        ExportPPT.saveImage(sampleCheckImageDTO.getImageUrl(), httpServletRequest);
                        Im4JavaUtils.resetImage(oldFilePath, filePath); //转换图片
                    } catch (Exception e) {
                        throw e;
                    }
                    insertPicture(ppt, slide13, sampleCheckImageDTO.getImageUrl(), filePath, httpServletRequest, new java.awt.Rectangle(240, 100, Im4JavaUtils.getImageWidth(filePath), Im4JavaUtils.getImageHeight(filePath)));
                }
                m++;
            }
        }
    }

    @Override
    public void exportPPT(HttpServletRequest httpServletRequest, ServletOutputStream out, ProjectSampleCheckAdminBackDTO projectSampleCheckDTO) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("");
        String path = serverRealPath + "static" + File.separator;
        String filePath = path + "upload" + File.separator + "exprotPPT.jpg";
        String imgPath = path + "img" + File.separator + "ppt" + File.separator + "ppt.jpg";

        //创建PPT
        SlideShow ppt = new SlideShow();
        //设置幻灯片大小
        ppt.setPageSize(new Dimension(760, 600));
        //创建第一页
        Slide newSlide = ppt.createSlide();
        //设置母板背景,支持多种图片格式
//        SlideMaster master = ppt.getSlidesMasters()[0];
        int picIndex = ppt.addPicture(new File(imgPath), Picture.JPEG);
        Picture background = new Picture(picIndex);
        //设置图片位置
        background.setAnchor(new java.awt.Rectangle(0, 0, ppt.getPageSize().width, ppt.getPageSize().height));
//        master.addShape(background);
        newSlide.addShape(background);
        //添加幻灯片标题
        TextBox txt = newSlide.addTitle();
        txt.setAnchor(new java.awt.Rectangle(40, 400, 650, 100));
        RichTextRun txt1 = txt.getTextRun().getRichTextRuns()[0];
        txt1.setFontColor(Color.BLUE);
        txt.setText(projectSampleCheckDTO.getTitle() + "-样板点评记录\r" + projectSampleCheckDTO.getCheckDate());

        //第二页开始
//        master.removeShape(background);
        Slide slide2 = ppt.createSlide();
//        slide2.setFollowMasterBackground(false);
        //添加幻灯片标题
        createTitle(slide2, "样板点评综述");

        //添加文本框
        String content = "\r" +
                "·项目：" + projectSampleCheckDTO.getProjectName() + "\r\n" +
                "·时间：" + projectSampleCheckDTO.getCreateOn() + "\r" +
                "·检查项：" + projectSampleCheckDTO.getClassifyThreeName() + "\r" +
                "·整改人：" + projectSampleCheckDTO.getAssignName() + "\r" +
                "·点评内容：" + projectSampleCheckDTO.getTitle() + "\r" +
                "·严重等级：" + projectSampleCheckDTO.getSeverityLevel() + "\r" +
                "·检查部位：" + projectSampleCheckDTO.getCheckPosition();
        createText(content, 32, slide2, new java.awt.Rectangle(0, 80, 760, 500));

        //第三页开始
        if (projectSampleCheckDTO.getSampleCheckDetails() != null && projectSampleCheckDTO.getSampleCheckDetails().size() > 0) {
            for (ProjectSampleCheckDetailsAdminBackDTO projectSampleCheckDetailsAdminBackDTO : projectSampleCheckDTO.getSampleCheckDetails()) {
                //指标合格记录
                if (projectSampleCheckDetailsAdminBackDTO.getImageList() != null && projectSampleCheckDetailsAdminBackDTO.getImageList().size() > 0) {
                    String titleContent = projectSampleCheckDetailsAdminBackDTO.getTargetName() + "合格记录";
                    String des = "";
                    if(projectSampleCheckDetailsAdminBackDTO.getImage2List() != null && projectSampleCheckDetailsAdminBackDTO.getImage2List().size() > 0){
                        des="合格";
                    }else {
                        des = projectSampleCheckDetailsAdminBackDTO.getDescription();
                    }
                    annal(projectSampleCheckDetailsAdminBackDTO.getImageList(), ppt, titleContent, des, filePath, httpServletRequest);
                }
                //指标不合格记录
                if (projectSampleCheckDetailsAdminBackDTO.getImage2List() != null && projectSampleCheckDetailsAdminBackDTO.getImage2List().size() > 0) {
                    String titleContent = projectSampleCheckDetailsAdminBackDTO.getTargetName() + "不合格记录";
                    annal(projectSampleCheckDetailsAdminBackDTO.getImage2List(), ppt, titleContent, projectSampleCheckDetailsAdminBackDTO.getDescription(), filePath, httpServletRequest);
                }
                //乙方整改记录信息
                if (projectSampleCheckDetailsAdminBackDTO.getTargetDTOByPartyBAnnalList() != null && projectSampleCheckDetailsAdminBackDTO.getTargetDTOByPartyBAnnalList().size() > 0) {
                    for (ProjectSampleCheckChangedAdminBackDTO projectSampleCheckChangedAdminBackDTO : projectSampleCheckDetailsAdminBackDTO.getTargetDTOByPartyBAnnalList()) {
                        //整改合格记录
                        if (projectSampleCheckChangedAdminBackDTO.getImageList() != null && projectSampleCheckChangedAdminBackDTO.getImageList().size() > 0) {
                            String titleContent = projectSampleCheckDetailsAdminBackDTO.getTargetName() + "-第" + projectSampleCheckChangedAdminBackDTO.getSerialNumber() + "次整改合格记录-乙方";
                            annal(projectSampleCheckChangedAdminBackDTO.getImageList(), ppt, titleContent, "合格", filePath, httpServletRequest);
                        }
                        //整改不合格记录
                        if (projectSampleCheckChangedAdminBackDTO.getImage2List() != null && projectSampleCheckChangedAdminBackDTO.getImage2List().size() > 0) {
                            String titleContent = projectSampleCheckDetailsAdminBackDTO.getTargetName() + "-第" + projectSampleCheckChangedAdminBackDTO.getSerialNumber() + "次整改不合格记录-乙方";
                            annal(projectSampleCheckChangedAdminBackDTO.getImage2List(), ppt, titleContent, projectSampleCheckChangedAdminBackDTO.getDescription(), filePath, httpServletRequest);
                        }
                    }
                }
                //监理整改记录信息
                if (projectSampleCheckDetailsAdminBackDTO.getTargetDTOBySupervisionAnnalList() != null && projectSampleCheckDetailsAdminBackDTO.getTargetDTOBySupervisionAnnalList().size() > 0) {
                    for (ProjectSampleCheckChangedAdminBackDTO projectSampleCheckChangedAdminBackDTO : projectSampleCheckDetailsAdminBackDTO.getTargetDTOBySupervisionAnnalList()) {
                        //整改合格记录
                        if (projectSampleCheckChangedAdminBackDTO.getImageList() != null && projectSampleCheckChangedAdminBackDTO.getImageList().size() > 0) {
                            String titleContent = projectSampleCheckDetailsAdminBackDTO.getTargetName() + "-第" + projectSampleCheckChangedAdminBackDTO.getSerialNumber() + "次整改合格记录-监理";
                            annal(projectSampleCheckChangedAdminBackDTO.getImageList(), ppt, titleContent, "合格", filePath, httpServletRequest);
                        }
                        //整改不合格记录
                        if (projectSampleCheckChangedAdminBackDTO.getImage2List() != null && projectSampleCheckChangedAdminBackDTO.getImage2List().size() > 0) {
                            String titleContent = projectSampleCheckDetailsAdminBackDTO.getTargetName() + "-第" + projectSampleCheckChangedAdminBackDTO.getSerialNumber() + "次整改不合格记录-监理";
                            annal(projectSampleCheckChangedAdminBackDTO.getImage2List(), ppt, titleContent, projectSampleCheckChangedAdminBackDTO.getDescription(), filePath, httpServletRequest);
                        }
                    }
                }
                //甲方整改记录信息
                if (projectSampleCheckDetailsAdminBackDTO.getTargetDTOByPartyAAnnalList() != null && projectSampleCheckDetailsAdminBackDTO.getTargetDTOByPartyAAnnalList().size() > 0) {
                    for (ProjectSampleCheckChangedAdminBackDTO projectSampleCheckChangedAdminBackDTO : projectSampleCheckDetailsAdminBackDTO.getTargetDTOByPartyAAnnalList()) {
                        //整改合格记录
                        if (projectSampleCheckChangedAdminBackDTO.getImageList() != null && projectSampleCheckChangedAdminBackDTO.getImageList().size() > 0) {
                            String titleContent = projectSampleCheckDetailsAdminBackDTO.getTargetName() + "-第" + projectSampleCheckChangedAdminBackDTO.getSerialNumber() + "次整改合格记录-甲方";
                            annal(projectSampleCheckChangedAdminBackDTO.getImageList(), ppt, titleContent, "合格", filePath, httpServletRequest);
                        }
                        //整改不合格记录
                        if (projectSampleCheckChangedAdminBackDTO.getImage2List() != null && projectSampleCheckChangedAdminBackDTO.getImage2List().size() > 0) {
                            String titleContent = projectSampleCheckDetailsAdminBackDTO.getTargetName() + "-第" + projectSampleCheckChangedAdminBackDTO.getSerialNumber() + "次整改不合格记录-甲方";
                            annal(projectSampleCheckChangedAdminBackDTO.getImage2List(), ppt, titleContent, projectSampleCheckChangedAdminBackDTO.getDescription(), filePath, httpServletRequest);
                        }
                    }
                }
            }
        }
        try {
            ppt.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportCountExcel(String title, String[] headers, ServletOutputStream out, RequestSampleAdminDTO requestSampleAdminDTO, UserInformationEntity  user, WebPage webPage) {
        List<ProjectSampleCheckCountDTO> sampleCheckCountList = this.getSampleCheckCount(requestSampleAdminDTO, webPage, user.getStaffId()).getList();
        // 导出数据
        ExportExcel<ProjectSampleCheckCountExcelDTO> ex = new ExportExcel<ProjectSampleCheckCountExcelDTO>();

        List<ProjectSampleCheckCountExcelDTO> sampleCheckCountExcel = new ArrayList<ProjectSampleCheckCountExcelDTO>();
        if (sampleCheckCountList != null && sampleCheckCountList.size() > 0) {
            int num = 1;
            for (ProjectSampleCheckCountDTO sampleCheckCount : sampleCheckCountList) {
                ProjectSampleCheckCountExcelDTO sideStationCountExcel = new ProjectSampleCheckCountExcelDTO();
                sideStationCountExcel.setSerialNumber(num++);//序号
                sideStationCountExcel.setProjectName(sampleCheckCount.getProjectName());
                sideStationCountExcel.setClassifyThree(sampleCheckCount.getClassifyThree());
                sideStationCountExcel.setQualified(sampleCheckCount.getQualified());//合格数
                sideStationCountExcel.setUnqualified(sampleCheckCount.getUnqualified());//不合格数
                sampleCheckCountExcel.add(sideStationCountExcel);
            }
        }
        ex.exportExcel(title, headers, sampleCheckCountExcel, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    /**
     * 整改信息
     */

    public void saveSampleCheckDetails(List<ProjectSampleCheckDetailsDTO> list, UserPropertyStaffEntity user, String type) {
        //指标
        List<ProjectSampleCheckDetailsDTO> requestampleCheckDetails = list;
        if (requestampleCheckDetails != null && requestampleCheckDetails.size() > 0) {
            for (ProjectSampleCheckDetailsDTO sampleCheckDetails : requestampleCheckDetails) {
                ProjectSampleCheckDetailsEntity sampleCheckDetailsEntity = projectSampleCheckRepository.querySampleCheckDetailsEntity(sampleCheckDetails.getId());
                if ("1".equals(type)) {
                    sampleCheckDetailsEntity.setFlag("1");
                }
                sampleCheckDetailsEntity.setState(sampleCheckDetails.getState());
                List<ProjectSampleCheckChangedDTO> requestSampleCheckChanged = sampleCheckDetails.getSampleCheckChanged();
                if (requestSampleCheckChanged != null && requestSampleCheckChanged.size() > 0) {
                    for (ProjectSampleCheckChangedDTO projectSampleCheckChanged : requestSampleCheckChanged) {
                        //指标整改内容保存
                        ProjectSampleCheckChangedEntity sampleCheckChangedEntity = new ProjectSampleCheckChangedEntity();
                        sampleCheckChangedEntity.setId(IdGen.uuid());//整改ID
                        sampleCheckChangedEntity.setCheckDetailsId(sampleCheckDetailsEntity.getId());//样板点评指标信息ID
                        sampleCheckChangedEntity.setDescription(projectSampleCheckChanged.getDescription());//描述
                        sampleCheckChangedEntity.setChangeTime(new Date());
                        sampleCheckChangedEntity.setCreateBy(user.getStaffId());//创建人
                        sampleCheckChangedEntity.setCreateOn(new Date());//创建时间
                        sampleCheckChangedEntity.setType(projectSampleCheckChanged.getType());
                        sampleCheckChangedEntity.setState(projectSampleCheckChanged.getState());
                        sampleCheckChangedEntity.setSerialNumber(projectSampleCheckChanged.getSerialNumber());//排序号
                        projectSampleCheckRepository.saveSampleCheckChanged(sampleCheckChangedEntity);
                        //整改内容图片保存
                        List<SampleCheckImageDTO> imageList = projectSampleCheckChanged.getImageList();
                        if (imageList != null && imageList.size() > 0) {
                            for (SampleCheckImageDTO sampleCheckImage : imageList) {
                                ProjectImagesEntity projectImagesEntity = new ProjectImagesEntity();
                                projectImagesEntity.setId(IdGen.uuid());//图片ID
                                projectImagesEntity.setBusinessId(sampleCheckChangedEntity.getId());//样板点评指标详细信息ID
                                projectImagesEntity.setUrl(sampleCheckImage.getImageUrl());//图片URL
                                projectImagesEntity.setType("4");//所属类型：
                                projectImagesEntity.setState("1");//状态
                                projectImagesEntity.setQualifiedState(sampleCheckImage.getQualifiedState());//合格状态
                                projectImagesEntity.setCreateOn(new Date());//创建时间
                                projectImagesEntity.setModifyOn(new Date());//修改时间
                                //保存图片信息
                                projectImagesRepository.addProjectImagesInfo(projectImagesEntity);
                            }
                        }
                    }
                }
                projectSampleCheckRepository.saveOrUpdateSampleCheckDetails(sampleCheckDetailsEntity);
            }
        }
    }

    /**
     * 根据 ID APPID 查询返回数据
     */
    public ProjectSampleCheckDTO returntSampleCheckDTO(String id, String appId, ProjectSampleCheckEntity sampleCheck) {
        ProjectSampleCheckDTO returntSampleCheck = new ProjectSampleCheckDTO();
        ProjectSampleCheckEntity sampleCheckEntity = null;
        if (sampleCheck != null) {
            sampleCheckEntity = sampleCheck;
        } else {
            sampleCheckEntity = projectSampleCheckRepository.querySampleCheckByID(id, appId);
        }

        returntSampleCheck.setId(sampleCheckEntity.getId() == null ? "" : sampleCheckEntity.getId().toString());//自增长ID
        returntSampleCheck.setAppId(sampleCheckEntity.getAppId() == null ? "" : sampleCheckEntity.getAppId());//app端传入 校验唯一性 防止重复
        returntSampleCheck.setSampleCheckId(sampleCheckEntity.getSampleCheckId() == null ? "" : sampleCheckEntity.getSampleCheckId());//样板点评ID
        returntSampleCheck.setTitle(sampleCheckEntity.getTitle() == null ? "" : sampleCheckEntity.getTitle());//样板点评标题
        returntSampleCheck.setDescription(sampleCheckEntity.getDescription() == null ? "" : sampleCheckEntity.getDescription());//描述
        returntSampleCheck.setCreateBy(sampleCheckEntity.getCreateBy() == null ? "" : sampleCheckEntity.getCreateBy());//创建人ID
        returntSampleCheck.setCreateName(sampleCheckEntity.getCreateName() == null ? "" : sampleCheckEntity.getCreateName());//创建人姓名
        returntSampleCheck.setCreateOn(sampleCheckEntity.getCreateOn() == null ? "" : DateUtils.format(sampleCheckEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
        returntSampleCheck.setModifyDate(sampleCheckEntity.getModifyDate() == null ? "" : DateUtils.format(sampleCheckEntity.getModifyDate(), DateUtils.FORMAT_LONG));//修改时间
        returntSampleCheck.setProjectId(sampleCheckEntity.getProjectId() == null ? "" : sampleCheckEntity.getProjectId());//项目ID
        returntSampleCheck.setProjectName(sampleCheckEntity.getProjectName() == null ? "" : sampleCheckEntity.getProjectName());//项目名称
        returntSampleCheck.setBuildingId(sampleCheckEntity.getBuildingId() == null ? "" : sampleCheckEntity.getBuildingId());//楼栋id
        returntSampleCheck.setBuildingName(sampleCheckEntity.getBuildingName() == null ? "" : sampleCheckEntity.getBuildingName());//楼栋名称
        returntSampleCheck.setClassifyOne(sampleCheckEntity.getClassifyOne() == null ? "" : sampleCheckEntity.getClassifyOne());//一级分类
        returntSampleCheck.setClassifyTwo(sampleCheckEntity.getClassifyTwo() == null ? "" : sampleCheckEntity.getClassifyTwo());//二级分类
        returntSampleCheck.setClassifyThree(sampleCheckEntity.getClassifyThree() == null ? "" : sampleCheckEntity.getClassifyThree());//三级分类
        returntSampleCheck.setClassifyOneName(sampleCheckEntity.getClassifyOneName() == null ? "" : sampleCheckEntity.getClassifyOneName());//一级分类名称
        returntSampleCheck.setClassifyTwoName(sampleCheckEntity.getClassifyTwoName() == null ? "" : sampleCheckEntity.getClassifyTwoName());//二级分类名称
        returntSampleCheck.setClassifyThreeName(sampleCheckEntity.getClassifyThreeName() == null ? "" : sampleCheckEntity.getClassifyThreeName());//三级分类名称
        returntSampleCheck.setState(sampleCheckEntity.getState() == null ? "" : sampleCheckEntity.getState());//状态
        returntSampleCheck.setSeverityLevel(sampleCheckEntity.getSeverityLevel() == null ? "" : sampleCheckEntity.getSeverityLevel());//严重等级
        returntSampleCheck.setFloorNum1(sampleCheckEntity.getFloorNum1() == null ? "" : sampleCheckEntity.getFloorNum1());//楼层区间-始
        returntSampleCheck.setFloorNum2(sampleCheckEntity.getFloorNum2() == null ? "" : sampleCheckEntity.getFloorNum2());//楼层区间-终
        returntSampleCheck.setCheckPosition(sampleCheckEntity.getCheckPosition() == null ? "" : sampleCheckEntity.getCheckPosition());//检查部位
        returntSampleCheck.setCheckDate(sampleCheckEntity.getCheckPosition() == null ? "" : sampleCheckEntity.getCheckPosition());//检查时间
        returntSampleCheck.setSupplierId(sampleCheckEntity.getSupplierId() == null ? "" : sampleCheckEntity.getSupplierId()); //责任单位ID
        returntSampleCheck.setSupplier(sampleCheckEntity.getSupplier() == null ? "" : sampleCheckEntity.getSupplier());//责任单位名称
        returntSampleCheck.setAssignId(sampleCheckEntity.getAssignId() == null ? "" : sampleCheckEntity.getAssignId());//乙方负责人ID
        returntSampleCheck.setAssignName(sampleCheckEntity.getAssignName() == null ? "" : sampleCheckEntity.getAssignName());//乙方负责人名字
        returntSampleCheck.setSupervisorId(sampleCheckEntity.getSupervisorId() == null ? "" : sampleCheckEntity.getSupervisorId());//第三方监理id
        returntSampleCheck.setSupervisorName(sampleCheckEntity.getSupervisorName() == null ? "" : sampleCheckEntity.getSupervisorName());//第三方监理名字
        returntSampleCheck.setFirstParty(sampleCheckEntity.getFirstParty() == null ? "" : sampleCheckEntity.getFirstParty()); //甲方负责人ID
        returntSampleCheck.setFirstPartyName(sampleCheckEntity.getFirstPartyName() == null ? "" : sampleCheckEntity.getFirstPartyName()); //甲方负责人名字
        returntSampleCheck.setDealPeople(sampleCheckEntity.getDealPeople() == null ? "" : sampleCheckEntity.getDealPeople());//处理人ID
        returntSampleCheck.setRectificationPeriod(sampleCheckEntity.getRectificationPeriod() == null ? "" : DateUtils.format(sampleCheckEntity.getRectificationPeriod(), DateUtils.FORMAT_LONG));//整改时限
        //抄送人
        List<Object[]> idList = dailyPatrolInspectionRepository.getProjectCopy(sampleCheckEntity.getSampleCheckId());//抄送人list
        List<KeyProcessesCopyDTO> copyDetailsList = new ArrayList<KeyProcessesCopyDTO>();
        if (idList != null) {
            for (Object[] idObj : idList) {
                KeyProcessesCopyDTO keyProcessesCopyDTO = new KeyProcessesCopyDTO();
                keyProcessesCopyDTO.setId(idObj[1] == null ? "" : idObj[1].toString());//抄送人ID
                keyProcessesCopyDTO.setName(idObj[2] == null ? "" : idObj[2].toString());//抄送人名称
                copyDetailsList.add(keyProcessesCopyDTO);
            }
        }
        //查询该样板点评下指标信息
        returntSampleCheck.setCopyList(copyDetailsList);
        List<ProjectSampleCheckDetailsDTO> sampleCheckDetailsDTOList = new ArrayList<ProjectSampleCheckDetailsDTO>();
        List<ProjectSampleCheckDetailsEntity> sampleCheckDetailsEntity = projectSampleCheckRepository.querySampleCheckDetailsById(sampleCheckEntity.getSampleCheckId());
        if (sampleCheckDetailsEntity != null) {
            for (ProjectSampleCheckDetailsEntity checkDetailsEntity : sampleCheckDetailsEntity) {
                ProjectSampleCheckDetailsDTO sampleCheckDetailsDTO = new ProjectSampleCheckDetailsDTO();
                sampleCheckDetailsDTO.setId(checkDetailsEntity.getId() == null ? "" : checkDetailsEntity.getId());//id
                sampleCheckDetailsDTO.setSampleCheckId(checkDetailsEntity.getSampleCheckId() == null ? "" : checkDetailsEntity.getSampleCheckId());//样板点评ID
                sampleCheckDetailsDTO.setTargetId(checkDetailsEntity.getTargetId() == null ? "" : checkDetailsEntity.getTargetId());//指标ID
                sampleCheckDetailsDTO.setTargetName(checkDetailsEntity.getTargetName() == null ? "" : checkDetailsEntity.getTargetName());//指标名
                sampleCheckDetailsDTO.setDescription(checkDetailsEntity.getDescription() == null ? "" : checkDetailsEntity.getDescription());//描述
                sampleCheckDetailsDTO.setGuide(checkDetailsEntity.getGuide() == null ? "" : checkDetailsEntity.getGuide());//指引
                sampleCheckDetailsDTO.setState(checkDetailsEntity.getState() == null ? "" : checkDetailsEntity.getState());//状态
                sampleCheckDetailsDTO.setCreateOn(checkDetailsEntity.getCreateOn() == null ? "" : DateUtils.format(checkDetailsEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
                sampleCheckDetailsDTO.setModifyDate(checkDetailsEntity.getModifyDate() == null ? "" : DateUtils.format(checkDetailsEntity.getModifyDate(), DateUtils.FORMAT_LONG));//修改时间
                sampleCheckDetailsDTO.setFlag(checkDetailsEntity.getFlag() == null ? "" : checkDetailsEntity.getFlag());
                List<ProjectSampleCheckChangedDTO> sampleCheckChanged = new ArrayList<ProjectSampleCheckChangedDTO>();//样板点评指标整改信息
                //根据指标信息中的id查询整改信息
                List<ProjectSampleCheckChangedEntity> sampleCheckChangedEntity = projectSampleCheckRepository.querySampleCheckChangedEntity(checkDetailsEntity.getId());
                if (sampleCheckChangedEntity != null) {
                    for (ProjectSampleCheckChangedEntity checkChangedEntity : sampleCheckChangedEntity) {
                        ProjectSampleCheckChangedDTO checkChangedDTO = new ProjectSampleCheckChangedDTO();
                        checkChangedDTO.setId(checkChangedEntity.getId() == null ? "" : checkChangedEntity.getId());//ID
                        checkChangedDTO.setCheckDetailsId(checkChangedEntity.getCheckDetailsId() == null ? "" : checkChangedEntity.getCheckDetailsId());//指标ID
                        checkChangedDTO.setDescription(checkChangedEntity.getDescription() == null ? "" : checkChangedEntity.getDescription());//描述
                        checkChangedDTO.setChangeTime(checkChangedEntity.getCheckDetailsId() == null ? "" : checkChangedEntity.getCheckDetailsId());//整改时间
                        checkChangedDTO.setCreateBy(checkChangedEntity.getCreateBy() == null ? "" : checkChangedEntity.getCreateBy());//创建人
                        checkChangedDTO.setCreateOn(checkChangedEntity.getCreateOn() == null ? "" : DateUtils.format(checkChangedEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
                        checkChangedDTO.setType(checkChangedEntity.getType() == null ? "" : checkChangedEntity.getType());//0 乙方整改  1 第三方监理整改   2 甲方整改
                        checkChangedDTO.setState(checkChangedEntity.getState() == null ? "" : checkChangedEntity.getState());//状态
                        checkChangedDTO.setSerialNumber(checkChangedEntity.getSerialNumber() == null ? "" : checkChangedEntity.getSerialNumber());//排序号
                        List<ProjectImagesEntity> projectImagesList = projectSampleCheckRepository.getProjectImages(checkChangedEntity.getId());
                        if (projectImagesList != null) {
                            //******************返回数据list*******整改图片*********
                            List<SampleCheckImageDTO> getimageList = new ArrayList<SampleCheckImageDTO>();
                            for (ProjectImagesEntity projectImagesEntity : projectImagesList) {
                                SampleCheckImageDTO getImage = new SampleCheckImageDTO();
                                getImage.setId(projectImagesEntity.getId() == null ? "" : projectImagesEntity.getId());
                                getImage.setBusinessId(projectImagesEntity.getBusinessId() == null ? "" : projectImagesEntity.getBusinessId());//业务id
                                getImage.setImageUrl(projectImagesEntity.getUrl() == null ? "" : projectImagesEntity.getUrl());//图片地址
                                getImage.setType(projectImagesEntity.getType() == null ? "" : projectImagesEntity.getType());//类型 1:日常巡检；2检查验收；3：关键工序；4：样板点评；5：材料验收；6：旁站
                                getImage.setState(projectImagesEntity.getState() == null ? "" : projectImagesEntity.getState());//状态 0:不可用；1：可用
                                //创建时间
                                getImage.setCreateOn(projectImagesEntity.getCreateOn() == null ? "" : DateUtils.format(projectImagesEntity.getCreateOn()));
                                //修改时间
                                getImage.setModifyOn(projectImagesEntity.getModifyOn() == null ? "" : DateUtils.format(projectImagesEntity.getModifyOn()));
                                //合格 不合格
                                getImage.setQualifiedState(projectImagesEntity.getQualifiedState() == null ? "" : projectImagesEntity.getQualifiedState());
                                getimageList.add(getImage);
                            }
                            //整改信息图片
                            checkChangedDTO.setImageList(getimageList);
                        }
                        sampleCheckChanged.add(checkChangedDTO);
                    }
                    sampleCheckDetailsDTO.setSampleCheckChanged(sampleCheckChanged);
                }
                //查询指标信息图片
                List<ProjectImagesEntity> imagesList = projectSampleCheckRepository.getProjectImages(checkDetailsEntity.getId());
                if (imagesList != null) {
                    //******************返回数据list*******指标图片*********
                    List<SampleCheckImageDTO> getimageList = new ArrayList<SampleCheckImageDTO>();
                    List<SampleCheckImageDTO> getimage2List = new ArrayList<SampleCheckImageDTO>();
                    for (ProjectImagesEntity projectImagesEntity : imagesList) {
                        SampleCheckImageDTO getImage = new SampleCheckImageDTO();
                        getImage.setId(projectImagesEntity.getId() == null ? "" : projectImagesEntity.getId());
                        getImage.setBusinessId(projectImagesEntity.getBusinessId() == null ? "" : projectImagesEntity.getBusinessId());//业务id
                        getImage.setImageUrl(projectImagesEntity.getUrl() == null ? "" : projectImagesEntity.getUrl());//图片地址
                        getImage.setType(projectImagesEntity.getType() == null ? "" : projectImagesEntity.getType());//类型 1:日常巡检；2检查验收；3：关键工序；4：样板点评；5：材料验收；6：旁站
                        getImage.setState(projectImagesEntity.getState() == null ? "" : projectImagesEntity.getState());//状态 0:不可用；1：可用
                        //创建时间
                        getImage.setCreateOn(projectImagesEntity.getCreateOn() == null ? "" : DateUtils.format(projectImagesEntity.getCreateOn()));
                        //修改时间
                        getImage.setModifyOn(projectImagesEntity.getModifyOn() == null ? "" : DateUtils.format(projectImagesEntity.getModifyOn()));
                        //合格 不合格
                        getImage.setQualifiedState(projectImagesEntity.getQualifiedState() == null ? "" : projectImagesEntity.getQualifiedState());
                        if (!StringUtil.isEmpty(projectImagesEntity.getQualifiedState()) && "合格".equals(projectImagesEntity.getQualifiedState())) {
                            getimageList.add(getImage);
                        } else {
                            getimage2List.add(getImage);
                        }

                    }
                    //指标信息图片
                    sampleCheckDetailsDTO.setImageList(getimageList);//合格
                    sampleCheckDetailsDTO.setImage2List(getimage2List);//不合格
                }
                sampleCheckDetailsDTOList.add(sampleCheckDetailsDTO);
            }
            returntSampleCheck.setSampleCheckDetails(sampleCheckDetailsDTOList);//样板点评指标信息
        }

        return returntSampleCheck;
    }

    /**
     * 根据查询出实体返回数据
     */
    public ProjectSampleCheckDTO returntSampleCheckEntity(ProjectSampleCheckEntity sampleCheckEntity) {
        ProjectSampleCheckDTO returntSampleCheck = new ProjectSampleCheckDTO();
        returntSampleCheck.setId(sampleCheckEntity.getId() == null ? "" : sampleCheckEntity.getId().toString());//自增长ID
        returntSampleCheck.setAppId(sampleCheckEntity.getAppId() == null ? "" : sampleCheckEntity.getAppId());//app端传入 校验唯一性 防止重复
        returntSampleCheck.setSampleCheckId(sampleCheckEntity.getSampleCheckId() == null ? "" : sampleCheckEntity.getSampleCheckId());//样板点评ID
        returntSampleCheck.setTitle(sampleCheckEntity.getTitle() == null ? "" : sampleCheckEntity.getTitle());//样板点评标题
        returntSampleCheck.setDescription(sampleCheckEntity.getDescription() == null ? "" : sampleCheckEntity.getDescription());//描述
        returntSampleCheck.setCreateBy(sampleCheckEntity.getCreateBy() == null ? "" : sampleCheckEntity.getCreateBy());//创建人ID
        returntSampleCheck.setCreateName(sampleCheckEntity.getCreateName() == null ? "" : sampleCheckEntity.getCreateName());//创建人姓名
        returntSampleCheck.setCreateOn(sampleCheckEntity.getCreateOn() == null ? "" : DateUtils.format(sampleCheckEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
        returntSampleCheck.setModifyDate(sampleCheckEntity.getModifyDate() == null ? "" : DateUtils.format(sampleCheckEntity.getModifyDate(), DateUtils.FORMAT_LONG));//修改时间
        returntSampleCheck.setProjectId(sampleCheckEntity.getProjectId() == null ? "" : sampleCheckEntity.getProjectId());//项目ID
        returntSampleCheck.setProjectName(sampleCheckEntity.getProjectName() == null ? "" : sampleCheckEntity.getProjectName());//项目名称
        returntSampleCheck.setBuildingId(sampleCheckEntity.getBuildingId() == null ? "" : sampleCheckEntity.getBuildingId());//楼栋id
        returntSampleCheck.setBuildingName(sampleCheckEntity.getBuildingName() == null ? "" : sampleCheckEntity.getBuildingName());//楼栋名称
        returntSampleCheck.setClassifyOne(sampleCheckEntity.getClassifyOne() == null ? "" : sampleCheckEntity.getClassifyOne());//一级分类
        returntSampleCheck.setClassifyTwo(sampleCheckEntity.getClassifyTwo() == null ? "" : sampleCheckEntity.getClassifyTwo());//二级分类
        returntSampleCheck.setClassifyThree(sampleCheckEntity.getClassifyThree() == null ? "" : sampleCheckEntity.getClassifyThree());//三级分类
        returntSampleCheck.setClassifyOneName(sampleCheckEntity.getClassifyOneName() == null ? "" : sampleCheckEntity.getClassifyOneName());//一级分类名称
        returntSampleCheck.setClassifyTwoName(sampleCheckEntity.getClassifyTwoName() == null ? "" : sampleCheckEntity.getClassifyTwoName());//二级分类名称
        returntSampleCheck.setClassifyThreeName(sampleCheckEntity.getClassifyThreeName() == null ? "" : sampleCheckEntity.getClassifyThreeName());//三级分类名称
        returntSampleCheck.setState(sampleCheckEntity.getState() == null ? "" : sampleCheckEntity.getState());//状态
        returntSampleCheck.setSeverityLevel(sampleCheckEntity.getSeverityLevel() == null ? "" : sampleCheckEntity.getSeverityLevel());//严重等级
        returntSampleCheck.setFloorNum1(sampleCheckEntity.getFloorNum1() == null ? "" : sampleCheckEntity.getFloorNum1());//楼层区间-始
        returntSampleCheck.setFloorNum2(sampleCheckEntity.getFloorNum2() == null ? "" : sampleCheckEntity.getFloorNum2());//楼层区间-终
        returntSampleCheck.setCheckPosition(sampleCheckEntity.getCheckPosition() == null ? "" : sampleCheckEntity.getCheckPosition());//检查部位
        returntSampleCheck.setCheckDate(sampleCheckEntity.getCheckPosition() == null ? "" : sampleCheckEntity.getCheckPosition());//检查时间
        returntSampleCheck.setSupplierId(sampleCheckEntity.getSupplierId() == null ? "" : sampleCheckEntity.getSupplierId()); //责任单位ID
        returntSampleCheck.setSupplier(sampleCheckEntity.getSupplier() == null ? "" : sampleCheckEntity.getSupplier());//责任单位名称
        returntSampleCheck.setAssignId(sampleCheckEntity.getAssignId() == null ? "" : sampleCheckEntity.getAssignId());//乙方负责人ID
        returntSampleCheck.setAssignName(sampleCheckEntity.getAssignName() == null ? "" : sampleCheckEntity.getAssignName());//乙方负责人名字
        returntSampleCheck.setSupervisorId(sampleCheckEntity.getSupervisorId() == null ? "" : sampleCheckEntity.getSupervisorId());//第三方监理id
        returntSampleCheck.setSupervisorName(sampleCheckEntity.getSupervisorName() == null ? "" : sampleCheckEntity.getSupervisorName());//第三方监理名字
        returntSampleCheck.setFirstParty(sampleCheckEntity.getFirstParty() == null ? "" : sampleCheckEntity.getFirstParty()); //甲方负责人ID
        returntSampleCheck.setFirstPartyName(sampleCheckEntity.getFirstPartyName() == null ? "" : sampleCheckEntity.getFirstPartyName()); //甲方负责人名字
        returntSampleCheck.setDealPeople(sampleCheckEntity.getDealPeople() == null ? "" : sampleCheckEntity.getDealPeople());//处理人ID
        returntSampleCheck.setRectificationPeriod(sampleCheckEntity.getRectificationPeriod() == null ? "" : DateUtils.format(sampleCheckEntity.getRectificationPeriod(), DateUtils.FORMAT_SHORT));//整改时限

        //抄送人
        List<Object[]> idList = dailyPatrolInspectionRepository.getProjectCopy(sampleCheckEntity.getSampleCheckId());//抄送人list
        List<KeyProcessesCopyDTO> copyDetailsList = new ArrayList<KeyProcessesCopyDTO>();
        if (idList != null) {
            for (Object[] idObj : idList) {
                KeyProcessesCopyDTO keyProcessesCopyDTO = new KeyProcessesCopyDTO();
                keyProcessesCopyDTO.setId(idObj[1] == null ? "" : idObj[1].toString());//抄送人ID
                keyProcessesCopyDTO.setName(idObj[2] == null ? "" : idObj[2].toString());//抄送人名称
                copyDetailsList.add(keyProcessesCopyDTO);
            }
        }
        returntSampleCheck.setCopyList(copyDetailsList);

        //查询该样板点评下指标信息
        List<ProjectSampleCheckDetailsDTO> sampleCheckDetailsDTOList = new ArrayList<ProjectSampleCheckDetailsDTO>();
        List<ProjectSampleCheckDetailsEntity> sampleCheckDetailsEntity = projectSampleCheckRepository.querySampleCheckDetailsById(sampleCheckEntity.getSampleCheckId());
        if (sampleCheckDetailsEntity != null) {
            for (ProjectSampleCheckDetailsEntity checkDetailsEntity : sampleCheckDetailsEntity) {
                ProjectSampleCheckDetailsDTO sampleCheckDetailsDTO = new ProjectSampleCheckDetailsDTO();
                sampleCheckDetailsDTO.setId(checkDetailsEntity.getId() == null ? "" : checkDetailsEntity.getId());//id
                sampleCheckDetailsDTO.setSampleCheckId(checkDetailsEntity.getSampleCheckId() == null ? "" : checkDetailsEntity.getSampleCheckId());//样板点评ID
                sampleCheckDetailsDTO.setTargetId(checkDetailsEntity.getTargetId() == null ? "" : checkDetailsEntity.getTargetId());//指标ID
                sampleCheckDetailsDTO.setTargetName(checkDetailsEntity.getTargetName() == null ? "" : checkDetailsEntity.getTargetName());//指标名
                sampleCheckDetailsDTO.setDescription(checkDetailsEntity.getDescription() == null ? "" : checkDetailsEntity.getDescription());//描述
                sampleCheckDetailsDTO.setGuide(checkDetailsEntity.getGuide() == null ? "" : checkDetailsEntity.getGuide());//指引
                sampleCheckDetailsDTO.setState(checkDetailsEntity.getState() == null ? "" : checkDetailsEntity.getState());//状态
                sampleCheckDetailsDTO.setCreateOn(checkDetailsEntity.getCreateOn() == null ? "" : DateUtils.format(checkDetailsEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
                sampleCheckDetailsDTO.setModifyDate(checkDetailsEntity.getModifyDate() == null ? "" : DateUtils.format(checkDetailsEntity.getModifyDate(), DateUtils.FORMAT_LONG));//修改时间
                sampleCheckDetailsDTO.setFlag(checkDetailsEntity.getFlag() == null ? "" : checkDetailsEntity.getFlag());
                List<ProjectSampleCheckChangedDTO> sampleCheckChanged = new ArrayList<ProjectSampleCheckChangedDTO>();//样板点评指标整改信息
                //根据指标信息中的id查询整改信息
                List<ProjectSampleCheckChangedEntity> sampleCheckChangedEntity = projectSampleCheckRepository.querySampleCheckChangedEntity(checkDetailsEntity.getId());
                if (sampleCheckChangedEntity != null) {
                    for (ProjectSampleCheckChangedEntity checkChangedEntity : sampleCheckChangedEntity) {
                        ProjectSampleCheckChangedDTO checkChangedDTO = new ProjectSampleCheckChangedDTO();
                        checkChangedDTO.setId(checkChangedEntity.getId() == null ? "" : checkChangedEntity.getId());//ID
                        checkChangedDTO.setCheckDetailsId(checkChangedEntity.getCheckDetailsId() == null ? "" : checkChangedEntity.getCheckDetailsId());//指标ID
                        checkChangedDTO.setDescription(checkChangedEntity.getDescription() == null ? "" : checkChangedEntity.getDescription());//描述
                        checkChangedDTO.setChangeTime(checkChangedEntity.getCheckDetailsId() == null ? "" : checkChangedEntity.getCheckDetailsId());//整改时间
                        checkChangedDTO.setCreateBy(checkChangedEntity.getCreateBy() == null ? "" : checkChangedEntity.getCreateBy());//创建人
                        checkChangedDTO.setCreateOn(checkChangedEntity.getCreateOn() == null ? "" : DateUtils.format(checkChangedEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
                        checkChangedDTO.setType(checkChangedEntity.getType() == null ? "" : checkChangedEntity.getType());//0 乙方整改  1 第三方监理整改   2 甲方整改
                        checkChangedDTO.setState(checkChangedEntity.getState() == null ? "" : checkChangedEntity.getState());//状态
                        checkChangedDTO.setSerialNumber(checkChangedEntity.getSerialNumber() == null ? "" : checkChangedEntity.getSerialNumber());//排序号
                        List<ProjectImagesEntity> projectImagesList = projectSampleCheckRepository.getProjectImages(checkChangedEntity.getId());
                        if (projectImagesList != null) {
                            //******************返回数据list*******整改图片*********
                            List<SampleCheckImageDTO> getimageList = new ArrayList<SampleCheckImageDTO>();
                            for (ProjectImagesEntity projectImagesEntity : projectImagesList) {
                                SampleCheckImageDTO getImage = new SampleCheckImageDTO();
                                getImage.setId(projectImagesEntity.getId() == null ? "" : projectImagesEntity.getId());
                                getImage.setBusinessId(projectImagesEntity.getBusinessId() == null ? "" : projectImagesEntity.getBusinessId());//业务id
                                getImage.setImageUrl(projectImagesEntity.getUrl() == null ? "" : projectImagesEntity.getUrl());//图片地址
                                getImage.setType(projectImagesEntity.getType() == null ? "" : projectImagesEntity.getType());//类型 1:日常巡检；2检查验收；3：关键工序；4：样板点评；5：材料验收；6：旁站
                                getImage.setState(projectImagesEntity.getState() == null ? "" : projectImagesEntity.getState());//状态 0:不可用；1：可用
                                //创建时间
                                getImage.setCreateOn(projectImagesEntity.getCreateOn() == null ? "" : DateUtils.format(projectImagesEntity.getCreateOn()));
                                //修改时间
                                getImage.setModifyOn(projectImagesEntity.getModifyOn() == null ? "" : DateUtils.format(projectImagesEntity.getModifyOn()));
                                //合格 不合格
                                getImage.setQualifiedState(projectImagesEntity.getQualifiedState() == null ? "" : projectImagesEntity.getQualifiedState());
                                //
                                getimageList.add(getImage);

                            }
                            //整改信息图片
                            checkChangedDTO.setImageList(getimageList);
                        }
                        sampleCheckChanged.add(checkChangedDTO);
                    }
                    sampleCheckDetailsDTO.setSampleCheckChanged(sampleCheckChanged);
                }
                //查询指标信息图片
                List<ProjectImagesEntity> imagesList = projectSampleCheckRepository.getProjectImages(checkDetailsEntity.getId());
                if (imagesList != null) {
                    //******************返回数据list*******指标图片*********
                    List<SampleCheckImageDTO> getimageList = new ArrayList<SampleCheckImageDTO>();
                    List<SampleCheckImageDTO> getimage2List = new ArrayList<SampleCheckImageDTO>();

                    for (ProjectImagesEntity projectImagesEntity : imagesList) {
                        SampleCheckImageDTO getImage = new SampleCheckImageDTO();
                        getImage.setId(projectImagesEntity.getId() == null ? "" : projectImagesEntity.getId());
                        getImage.setBusinessId(projectImagesEntity.getBusinessId() == null ? "" : projectImagesEntity.getBusinessId());//业务id
                        getImage.setImageUrl(projectImagesEntity.getUrl() == null ? "" : projectImagesEntity.getUrl());//图片地址
                        getImage.setType(projectImagesEntity.getType() == null ? "" : projectImagesEntity.getType());//类型 1:日常巡检；2检查验收；3：关键工序；4：样板点评；5：材料验收；6：旁站
                        getImage.setState(projectImagesEntity.getState() == null ? "" : projectImagesEntity.getState());//状态 0:不可用；1：可用
                        //创建时间
                        getImage.setCreateOn(projectImagesEntity.getCreateOn() == null ? "" : DateUtils.format(projectImagesEntity.getCreateOn()));
                        //修改时间
                        getImage.setModifyOn(projectImagesEntity.getModifyOn() == null ? "" : DateUtils.format(projectImagesEntity.getModifyOn()));
                        //合格放入image 不合格放入image2
                        getImage.setQualifiedState(projectImagesEntity.getQualifiedState() == null ? "" : projectImagesEntity.getQualifiedState());
                        if (!StringUtil.isEmpty(projectImagesEntity.getQualifiedState()) && "合格".equals(projectImagesEntity.getQualifiedState())) {
                            getimageList.add(getImage);
                        } else {
                            getimage2List.add(getImage);
                        }
                    }
                    //指标信息图片
                    sampleCheckDetailsDTO.setImageList(getimageList);
                    sampleCheckDetailsDTO.setImage2List(getimage2List);
                }
                sampleCheckDetailsDTOList.add(sampleCheckDetailsDTO);
            }
            returntSampleCheck.setSampleCheckDetails(sampleCheckDetailsDTOList);//样板点评指标信息
        }

        return returntSampleCheck;
    }
}
