package com.maxrocky.vesta.application.ProjectMaterial.impl;

import com.maxrocky.vesta.application.ProjectMaterial.DTO.*;
import com.maxrocky.vesta.application.ProjectMaterial.inf.ProjectMaterialService;
import com.maxrocky.vesta.application.baseData.inf.GetAllClassifyService;
import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.CheckDailyPatrolInspectionDTO;
import com.maxrocky.vesta.application.dailyPatrolInspection.DTO.CheckDailyPatrolInspectionListDTO;
import com.maxrocky.vesta.application.inspectAcceptance.DTO.ExportExcel;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.ProjectImagesEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectProjectEntity;
import com.maxrocky.vesta.domain.baseData.repository.StaffEmployRepository;
import com.maxrocky.vesta.domain.dailyPatrolInspection.repository.DailyPatrolInspectionRepository;
import com.maxrocky.vesta.domain.model.AgencyEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.projectMaterial.model.ProjectMaterialDetailsEntity;
import com.maxrocky.vesta.domain.projectMaterial.model.ProjectMaterialEntity;
import com.maxrocky.vesta.domain.projectMaterial.model.ProjectMaterialOutEntity;
import com.maxrocky.vesta.domain.projectMaterial.repository.ProjectMaterialRepository;
import com.maxrocky.vesta.domain.repository.AgencyRepository;
import com.maxrocky.vesta.domain.repository.RectificationRepository;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by Magic on 2016/11/29.
 */
@Service
public class ProjectMaterialServiceImpl implements ProjectMaterialService {
    @Autowired
    ProjectMaterialRepository projectMaterialRepository;

    @Autowired
    AgencyRepository agencyRepository;

    @Autowired
    RectificationRepository rectificationRepository;

    @Autowired
    DailyPatrolInspectionRepository dailyPatrolInspectionRepository;

    @Autowired
    StaffEmployRepository staffEmployRepository;

    @Autowired
    GetAllClassifyService getAllClassifyService;

    @Override
    public ApiResult saveMaterialNew(ProjectMaterialListDTO projectMaterialListDTO, UserPropertyStaffEntity userProperty) {
        if (projectMaterialListDTO == null) {
            return ErrorResource.getError("tip_00000431");//用户为空
        }
        ProjectMaterialListDTO getMaterialListDTO=new ProjectMaterialListDTO();
        List<ProjectMaterialDTO> getProjectMaterialList=new ArrayList<ProjectMaterialDTO>();
        try {
            //获取上传数据List
            List<ProjectMaterialDTO> postProjectMaterialDTO=projectMaterialListDTO.getList();
            for(ProjectMaterialDTO Material:postProjectMaterialDTO){
                if(StringUtil.isEmpty(Material.getClassifyOne())){
                    return ErrorResource.getError("tip_00000582");//一级分类
                }
                if(StringUtil.isEmpty(Material.getClassifyTwo())){
                    return ErrorResource.getError("tip_00000583");//二级分类
                }
                //判断是否有数据
                String checkUp = "0";
                ProjectMaterialEntity  materialEntitys=projectMaterialRepository.getMaterialByIdandAppId(Material.getMaterialId(),Material.getAppId());
                if(materialEntitys!=null){
                    //判断是否有退场记录 有修改新增  没有直接返回
                    if(!StringUtil.isEmpty(Material.getDescription())){
                        materialEntitys.setState(Material.getState());
                        materialEntitys.setModifyDate(new Date());
                        ProjectMaterialOutEntity materialOutEntity=new ProjectMaterialOutEntity();
                        materialOutEntity.setId(IdGen.uuid());//退场纪录服务器ID
                        materialOutEntity.setCreateOn(new Date());//创建时间
                        materialOutEntity.setCreateBy(userProperty.getStaffName());//创建人
                        materialOutEntity.setAppId(Material.getAppId());//appId
                        materialOutEntity.setMaterialId(materialEntitys.getMaterialId());//材料验收ID
                        materialOutEntity.setOutTime(DateUtils.parse(Material.getOutTime(), DateUtils.FORMAT_LONG));//退场时间
                        materialOutEntity.setDescription(Material.getDescription());//退场描述
                        materialOutEntity.setModifyOn(new Date());//修改时间
                        materialOutEntity.setModifyBy(userProperty.getStaffId());//修改人
                        List<MaterialImageDTO> imageList=Material.getImageList();
                        try{
                            projectMaterialRepository.saveUpdateProjectMaterialOut(materialOutEntity);
                            projectMaterialRepository.updateProjectMaterial(materialEntitys);//修改材料验收
                            if(imageList!=null){
                                for(MaterialImageDTO MaterialImage:imageList){
                                    ProjectImagesEntity saveImage = new ProjectImagesEntity();
                                    saveImage.setUrl(MaterialImage.getImageUrl());
                                    saveImage.setId(IdGen.uuid());
                                    saveImage.setBusinessId(materialOutEntity.getId());//退场iD
                                    saveImage.setType("5");
                                    saveImage.setState("1");
                                    saveImage.setCreateOn(new Date());
                                    saveImage.setModifyOn(new Date());
                                    dailyPatrolInspectionRepository.saveProjectImages(saveImage);
                                }
                            }
                        }catch (Exception e){
                            ProjectMaterialDTO projectMaterialDTO=getMaterialDTO(materialEntitys.getMaterialId(),null);
                            getProjectMaterialList.add(projectMaterialDTO);
                            continue;
                        }
                    }else{
                        ProjectMaterialDTO projectMaterialDTO=getMaterialDTO(materialEntitys.getMaterialId(),null);
                        getProjectMaterialList.add(projectMaterialDTO);
                        continue;
                    }

                }else{
                    //新增
                    ProjectMaterialEntity materialEntityNew=new ProjectMaterialEntity();
                    materialEntityNew.setMaterialId(IdGen.uuid());//服务器ID
                    materialEntityNew.setAppId(Material.getAppId());//appID
                    materialEntityNew.setProjectId(Material.getProjectId());//项目ID
                    materialEntityNew.setProjectName(Material.getProjectName());//项目名称
                    materialEntityNew.setBatchName(Material.getBatchName());//批次名称
                    materialEntityNew.setClassifyOne(Material.getClassifyOne());//一级分类
                    materialEntityNew.setClassifyOneName(Material.getClassifyOneName());//一级分类名称
                    materialEntityNew.setClassifyTwo(Material.getClassifyTwo());//二级分类
                    materialEntityNew.setClassifyTwoName(Material.getClassifyTwoName());//二级分类名称
                    materialEntityNew.setApproachTime(DateUtils.parse(Material.getApproachTime(), DateUtils.FORMAT_SHORT));//进场时间
                    materialEntityNew.setApproachNumber(Material.getApproachNumber());//进场批量
                    materialEntityNew.setUsedPart(Material.getUsedPart());//准备使用部位
                    materialEntityNew.setCreateOn(new Date());//创建时间
                    materialEntityNew.setState(Material.getState());//状态
                    materialEntityNew.setCreateName(Material.getCreateName());//创建人名字
                    materialEntityNew.setCreateBy(Material.getCreateBy());//创建人Id
                    materialEntityNew.setInspectedTime(DateUtils.parse(Material.getInspectedTime(), DateUtils.FORMAT_LONG));//验收时间
                    materialEntityNew.setSupplierId(Material.getSupplierId());//供应商id
                    materialEntityNew.setModifyDate(new Date());//修改时间
                    //供应商名字
                    if(!StringUtil.isEmpty(Material.getSupplierName())){
                        materialEntityNew.setSupplierName(Material.getSupplierName());
                    }else{
                        AgencyEntity agency = agencyRepository.getAgencyDetail(materialEntityNew.getSupplierId());
                        if(agency!=null){
                            materialEntityNew.setSupervisorName(agency.getAgencyName());
                        }
                    }
                    materialEntityNew.setAssignId(Material.getAssignId());//材料负责人Id
                    //材料负责人
                    if(!StringUtil.isEmpty(Material.getAssignName())){
                        materialEntityNew.setAssignName(Material.getAssignName());
                    }else{
                        UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(Material.getAssignId());
                        if (UserStaff != null) {
                            materialEntityNew.setAssignName(UserStaff.getStaffName());//整改人名字
                        }
                    }
                    if(!StringUtil.isEmpty(Material.getSupervisor())){
                        //当前登录人和第三方监理不匹配则 当前登录人为甲方
                        if(!userProperty.getStaffId().equals(Material.getSupervisor())){
                            materialEntityNew.setFirstPartyName(userProperty.getStaffName());
                            materialEntityNew.setFirstParty(userProperty.getStaffId());
                        }
                        materialEntityNew.setSupervisor(Material.getSupervisor());//第三方监理id
                        materialEntityNew.setDealPeople(Material.getSupervisor());//处理人
                        //第三方监理名字
                        if(!StringUtil.isEmpty(Material.getSupervisorName())){
                            materialEntityNew.setSupervisorName(Material.getSupervisorName());
                        }else{
                            UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(Material.getSupervisor());
                            if (UserStaff != null) {
                                materialEntityNew.setSupervisorName(UserStaff.getStaffName());//整改人名字
                            }
                        }
                    }else{
                        //第三方监理不存在 当前登录人为处理人和监理
                        materialEntityNew.setSupervisor(userProperty.getStaffId());//第三方监理id
                        materialEntityNew.setSupervisorName(userProperty.getStaffName());//第三方监理名字
                        materialEntityNew.setDealPeople(userProperty.getStaffId());//处理人
                    }
                    //退场记录 判断是否有退场记录
                    if(!StringUtil.isEmpty(Material.getDescription())){
                        ProjectMaterialOutEntity materialOutEntity=new ProjectMaterialOutEntity();
                        materialOutEntity.setId(IdGen.uuid());//退场纪录服务器ID
                        materialOutEntity.setCreateOn(new Date());//创建时间
                        materialOutEntity.setCreateBy(userProperty.getStaffName());//创建人
                        materialOutEntity.setAppId(Material.getAppId());//appId
                        materialOutEntity.setMaterialId(materialEntityNew.getMaterialId());//材料验收ID
                        materialOutEntity.setOutTime(DateUtils.parse(Material.getOutTime(), DateUtils.FORMAT_LONG));//退场时间
                        materialOutEntity.setDescription(Material.getDescription());//退场描述
                        materialOutEntity.setModifyOn(new Date());//修改时间
                        materialOutEntity.setModifyBy(userProperty.getStaffId());//修改人
                        try{
                            projectMaterialRepository.saveUpdateProjectMaterialOut(materialOutEntity);
                            List<MaterialImageDTO> imageList=Material.getImageList();
                            if(imageList!=null){
                                for(MaterialImageDTO MaterialImage:imageList){
                                    ProjectImagesEntity saveImage = new ProjectImagesEntity();
                                    saveImage.setUrl(MaterialImage.getImageUrl());
                                    saveImage.setId(IdGen.uuid());
                                    saveImage.setBusinessId(materialOutEntity.getId());//退场iD
                                    saveImage.setType("5");
                                    saveImage.setState("1");
                                    saveImage.setCreateOn(new Date());
                                    saveImage.setModifyOn(new Date());
                                    dailyPatrolInspectionRepository.saveProjectImages(saveImage);
                                }
                            }
                        }catch (Exception e){
                            ProjectMaterialDTO projectMaterialDTO=getMaterialDTO(materialEntitys.getMaterialId(),null);
                            getProjectMaterialList.add(projectMaterialDTO);
                            continue;
                        }
                    }
                    try {
                        projectMaterialRepository.saveProjectMaterial(materialEntityNew);//保存材料验收单
                    }catch (Exception e) {
                        ProjectMaterialDTO projectMaterialDTO=getMaterialDTO(materialEntityNew.getMaterialId(),materialEntityNew.getAppId());
                        getProjectMaterialList.add(projectMaterialDTO);
                        continue;
                    }

                    List<ProjectMaterialDetailsDTO> detailsList=Material.getDetailsList();
                    //验收指标
                    if(detailsList!=null && detailsList.size()>0){
                        for(ProjectMaterialDetailsDTO materialDetails : detailsList){
                            ProjectMaterialDetailsEntity materialDetailsEntity=new ProjectMaterialDetailsEntity();
                            materialDetailsEntity.setId(IdGen.uuid());
                            materialDetailsEntity.setMaterialId(materialEntityNew.getMaterialId());//材料验收ID
                            materialDetailsEntity.setTargetId(materialDetails.getTargetId());//指标ID
                            materialDetailsEntity.setTargetName(materialDetails.getTargetName());//指标名
                            materialDetailsEntity.setDescription(materialDetails.getDescription());//描述
                            materialDetailsEntity.setIsQualified(materialDetails.getIsQualified());//合格
                            materialDetailsEntity.setDetailsUrl(materialDetails.getImageUrl());//指标验收图片链接
                            materialDetailsEntity.setGuide(materialDetails.getGuide());//指引
                            materialDetailsEntity.setModifyDate(new Date());
                            projectMaterialRepository.saveUpdateProjectMaterialDetails(materialDetailsEntity);
                        }
                    }
                    ProjectMaterialDTO projectMaterialDTO=getMaterialDTO(materialEntityNew.getMaterialId(),null);
                    getProjectMaterialList.add(projectMaterialDTO);
                }
                getMaterialListDTO.setList(getProjectMaterialList);
            }
            return new SuccessApiResult(getMaterialListDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    /**
     * 材料验收新增实体 批量
     * */
    @Override
    public ApiResult saveMaterial(ProjectMaterialListDTO projectMaterialListDTO, UserPropertyStaffEntity userProperty) {
        if (projectMaterialListDTO == null) {
            return ErrorResource.getError("tip_00000431");//用户为空
        }
        try {
            ProjectMaterialListDTO getMaterialListDTO=new ProjectMaterialListDTO();
            List<ProjectMaterialDTO> getProjectMaterialList=new ArrayList<ProjectMaterialDTO>();
            List<ProjectMaterialDTO> postProjectMaterialDTO=projectMaterialListDTO.getList();
            if(postProjectMaterialDTO!=null){
                for(ProjectMaterialDTO Material:postProjectMaterialDTO){
                    ProjectMaterialEntity  materialEntity=null;
                    if(StringUtil.isEmpty(Material.getClassifyOne())){
                        return ErrorResource.getError("tip_00000582");//一级分类
                    }
                    if(StringUtil.isEmpty(Material.getClassifyTwo())){
                        return ErrorResource.getError("tip_00000583");//二级分类
                    }
                    //判断是新增还是修改
                    String checkUp = "0";
                    if(!StringUtil.isEmpty(Material.getMaterialId())){
                        materialEntity=projectMaterialRepository.getMaterialById(Material.getMaterialId());
                        if(materialEntity !=null ){
                            checkUp = "1";
                        }else{
                            materialEntity=projectMaterialRepository.getMaterialByAppId(Material.getAppId());
                            if(materialEntity !=null){
                                checkUp = "1";
                            }else{
                                materialEntity=new ProjectMaterialEntity();
                                materialEntity.setMaterialId(IdGen.uuid());
                                materialEntity.setAppId(Material.getAppId());
                            }
                        }
                    }else{
                        materialEntity=projectMaterialRepository.getMaterialByAppId(Material.getAppId());
                        if(materialEntity !=null){
                            checkUp = "1";
                        }else{
                            materialEntity=new ProjectMaterialEntity();
                            materialEntity.setMaterialId(IdGen.uuid());
                            materialEntity.setAppId(Material.getAppId());
                        }
                    }
                    materialEntity.setProjectId(Material.getProjectId());//项目ID
                    materialEntity.setProjectName(Material.getProjectName());//项目名称
                    materialEntity.setBatchName(Material.getBatchName());//批次名称
                    materialEntity.setClassifyOne(Material.getClassifyOne());//一级分类
                    materialEntity.setClassifyOneName(Material.getClassifyOneName());//一级分类名称
                    materialEntity.setClassifyTwo(Material.getClassifyTwo());//二级分类
                    materialEntity.setClassifyTwoName(Material.getClassifyTwoName());//二级分类名称
                    materialEntity.setApproachTime(DateUtils.parse(Material.getApproachTime(), DateUtils.FORMAT_SHORT));//进场时间
                    materialEntity.setApproachNumber(Material.getApproachNumber());//进场批量
                    materialEntity.setUsedPart(Material.getUsedPart());//准备使用部位
                    materialEntity.setCreateOn(new Date());//创建时间
                    materialEntity.setState(Material.getState());//状态
                    materialEntity.setCreateName(Material.getCreateName());//创建人名字
                    materialEntity.setCreateBy(Material.getCreateBy());//创建人Id
                    materialEntity.setInspectedTime(DateUtils.parse(Material.getInspectedTime(), DateUtils.FORMAT_LONG));//验收时间
                    materialEntity.setSupplierId(Material.getSupplierId());//供应商id
                    //供应商名字
                    if(!StringUtil.isEmpty(Material.getSupplierName())){
                        materialEntity.setSupplierName(Material.getSupplierName());
                    }else{
                        AgencyEntity agency = agencyRepository.getAgencyDetail(materialEntity.getSupplierId());
                        if(agency!=null){
                            materialEntity.setSupervisorName(agency.getAgencyName());
                        }
                    }
                    materialEntity.setAssignId(Material.getAssignId());//材料负责人Id
                    //材料负责人
                    if(!StringUtil.isEmpty(Material.getAssignName())){
                        materialEntity.setAssignName(Material.getAssignName());
                    }else{
                        UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(Material.getAssignId());
                        if (UserStaff != null) {
                            materialEntity.setAssignName(UserStaff.getStaffName());//整改人名字
                        }
                    }
                    if(!StringUtil.isEmpty(Material.getSupervisor())){
                        //当前登录人和第三方监理不匹配则 当前登录人为甲方
                        if(!userProperty.getStaffId().equals(Material.getSupervisor())){
                            materialEntity.setFirstPartyName(userProperty.getStaffName());
                            materialEntity.setFirstParty(userProperty.getStaffId());
                        }
                        materialEntity.setSupervisor(Material.getSupervisor());//第三方监理id
                        materialEntity.setDealPeople(Material.getSupervisor());//处理人
                        //第三方监理名字
                        if(!StringUtil.isEmpty(Material.getSupervisorName())){
                            materialEntity.setSupervisorName(Material.getSupervisorName());
                        }else{
                            UserPropertyStaffEntity UserStaff = rectificationRepository.getusername(Material.getSupervisor());
                            if (UserStaff != null) {
                                materialEntity.setSupervisorName(UserStaff.getStaffName());//整改人名字
                            }
                        }
                    }else{
                        materialEntity.setSupervisor(userProperty.getStaffId());//第三方监理id
                        materialEntity.setSupervisorName(userProperty.getStaffName());//第三方监理名字
                        materialEntity.setDealPeople(userProperty.getStaffId());//处理人
                    }
                    materialEntity.setModifyDate(new Date());//修改时间
                    try {
                        if ("1".equals(checkUp)) {
                            projectMaterialRepository.updateProjectMaterial(materialEntity);//修改整改单
                        } else {
                            projectMaterialRepository.saveProjectMaterial(materialEntity);//保存整改单
                        }
                    } catch (Exception e) {
                        ProjectMaterialDTO projectMaterialDTO=getMaterialDTO(materialEntity.getMaterialId(),materialEntity.getAppId());
                        getProjectMaterialList.add(projectMaterialDTO);
                        continue;
                    }
                    List<ProjectMaterialDetailsDTO> detailsList=Material.getDetailsList();
                    if(detailsList!=null && detailsList.size()>0){
                        for(ProjectMaterialDetailsDTO materialDetails : detailsList){
//                            ProjectMaterialDetailsEntity materialDetailsEntity=null;
//                            if(!StringUtil.isEmpty(materialDetails.getId())){
//                                materialDetailsEntity=projectMaterialRepository.getProjectMaterialDetails(materialDetails.getId());
//                                if(materialDetailsEntity == null){
//                                    materialDetailsEntity=new ProjectMaterialDetailsEntity();
//                                    materialDetailsEntity.setId(IdGen.uuid());
//                                }
//                            }else{
//                                materialDetailsEntity.setId(IdGen.uuid());
//                                materialDetailsEntity.setCreateOn(new Date());
//                            }
                            ProjectMaterialDetailsEntity materialDetailsEntity=new ProjectMaterialDetailsEntity();
                            materialDetailsEntity.setId(IdGen.uuid());
                            materialDetailsEntity.setMaterialId(materialEntity.getMaterialId());//材料验收ID
                            materialDetailsEntity.setTargetId(materialDetails.getTargetId());//指标ID
                            materialDetailsEntity.setTargetName(materialDetails.getTargetName());//指标名
                            materialDetailsEntity.setDescription(materialDetails.getDescription());//描述
                            materialDetailsEntity.setIsQualified(materialDetails.getIsQualified());//合格
                            materialDetailsEntity.setDetailsUrl(materialDetails.getImageUrl());//指标验收图片链接
                            materialDetailsEntity.setGuide(materialDetails.getGuide());//指引
                            materialDetailsEntity.setModifyDate(new Date());
                            projectMaterialRepository.saveUpdateProjectMaterialDetails(materialDetailsEntity);
                        }
                    }
                    ProjectMaterialDTO projectMaterialDTO=getMaterialDTO(materialEntity.getMaterialId(),null);
                    getProjectMaterialList.add(projectMaterialDTO);
                }
                getMaterialListDTO.setList(getProjectMaterialList);
            }
            return new SuccessApiResult(getMaterialListDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }
    /**
     * 根据项目Id 时间 自增长id 增量查询材料验收  getMaterialByApp
     * */
    @Override
    public ProjectMaterialListDTO getMaterialList(String id, String time, String projectId, UserPropertyStaffEntity userProperty) {
        ProjectMaterialListDTO getMaterialListDTO=new ProjectMaterialListDTO();
        String type="1";
        if (staffEmployRepository.checkOwner(userProperty.getStaffId(), projectId, "1")) {//甲方
            //甲方   该项目下所有数据和自己创建的草稿
            type = "1";
        }else{
            String chec= staffEmployRepository.getPurviewName(userProperty.getStaffId(), projectId);
            if("5".equals(chec)){
                type = "2";
            }else{
                return getMaterialListDTO;
            }
        }
        List<ProjectMaterialEntity> getProjectMaterialList=projectMaterialRepository.getMaterialEntityList(id,time,projectId,type,userProperty.getStaffId());
        if(getProjectMaterialList!=null){
            List<ProjectMaterialDTO> materialList=new ArrayList<ProjectMaterialDTO>();
            for(ProjectMaterialEntity materialEntity:getProjectMaterialList){
                ProjectMaterialDTO materialDTO=new ProjectMaterialDTO();
                materialDTO.setId(materialEntity.getId()+"");
                materialDTO.setMaterialId(materialEntity.getMaterialId());//材料验收Id
                materialDTO.setBatchName(materialEntity.getBatchName() == null ? "" : materialEntity.getBatchName());//批次名称
                materialDTO.setClassifyOne(materialEntity.getClassifyOne() == null ? "" : materialEntity.getClassifyOne());//一级分类
                materialDTO.setClassifyOneName(materialEntity.getClassifyOneName() == null ? "" : materialEntity.getClassifyOneName());//一级分类名称
                materialDTO.setClassifyTwo(materialEntity.getClassifyTwo() == null ? "" : materialEntity.getClassifyTwo());//二级分类
                materialDTO.setClassifyTwoName(materialEntity.getClassifyTwoName() == null ? "" : materialEntity.getClassifyTwoName());//二级分类名称
                materialDTO.setApproachTime(materialEntity.getApproachTime() == null ? "" : DateUtils.format(materialEntity.getApproachTime(), DateUtils.FORMAT_SHORT));//进场时间
                materialDTO.setApproachNumber(materialEntity.getApproachNumber() == null ? "" : materialEntity.getApproachNumber());//进场批量
                materialDTO.setUsedPart(materialEntity.getUsedPart() == null ? "" : materialEntity.getUsedPart());//准备使用部位
                materialDTO.setCreateBy(materialEntity.getCreateBy() == null ? "" : materialEntity.getCreateBy());//创建人Id
                materialDTO.setCreateOn(materialEntity.getCreateOn() == null ? "" : DateUtils.format(materialEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
                materialDTO.setCreateName(materialEntity.getCreateName() == null ? "" : materialEntity.getCreateName());//创建人名字
                materialDTO.setInspectedTime(materialEntity.getInspectedTime() == null ? "" : DateUtils.format(materialEntity.getInspectedTime(), DateUtils.FORMAT_LONG));//验收时间
                materialDTO.setSupplierId(materialEntity.getSupplierId() == null ? "" : materialEntity.getSupplierId());//供应商id
                materialDTO.setSupplierName(materialEntity.getSupplierName() == null ? "" : materialEntity.getSupplierName());//供应商名字
                materialDTO.setAssignId(materialEntity.getAssignId() == null ? "" : materialEntity.getAssignId());//材料负责人Id
                materialDTO.setAssignName(materialEntity.getAssignName() == null ? "" : materialEntity.getAssignName());//材料负责人
                materialDTO.setSupervisor(materialEntity.getSupervisor() == null ? "" : materialEntity.getSupervisor());//第三方监理id
                materialDTO.setSupervisorName(materialEntity.getSupervisorName() == null ? "" : materialEntity.getSupervisorName());//第三方监理名字
                materialDTO.setDealPeople(materialEntity.getDealPeople() == null ? "" : materialEntity.getDealPeople());//处理人
                materialDTO.setModifyDate(materialEntity.getModifyDate() == null ? "" : DateUtils.format(materialEntity.getModifyDate(), DateUtils.FORMAT_LONG));//修改时间
                materialDTO.setProjectId(materialEntity.getProjectId() == null ? "" : materialEntity.getProjectId());
                materialDTO.setProjectName(materialEntity.getProjectName() == null ? "" : materialEntity.getProjectName());
                materialDTO.setAppId(materialEntity.getAppId());//appId
                materialDTO.setState(materialEntity.getState());
                List<ProjectMaterialDetailsEntity> MaterialDetails=projectMaterialRepository.getMaterialDetailsList(materialEntity.getMaterialId());
                if(MaterialDetails!=null){
                    List<ProjectMaterialDetailsDTO> materialDetailsList=new ArrayList<ProjectMaterialDetailsDTO>();
                    for(ProjectMaterialDetailsEntity detailsEntity : MaterialDetails){
                        ProjectMaterialDetailsDTO materialDetailsDTO=new ProjectMaterialDetailsDTO();
                        materialDetailsDTO.setId(detailsEntity.getId());//id
                        materialDetailsDTO.setMaterialId(detailsEntity.getMaterialId());//材料验收ID
                        materialDetailsDTO.setTargetId(detailsEntity.getTargetId() == null ? "" : detailsEntity.getTargetId());//指标ID
                        materialDetailsDTO.setTargetName(detailsEntity.getTargetName() == null ? "" : detailsEntity.getTargetName());//指标名
                        materialDetailsDTO.setDescription(detailsEntity.getDescription() == null ? "" : detailsEntity.getDescription());//描述
                        materialDetailsDTO.setIsQualified(detailsEntity.getIsQualified() == null ? "" : detailsEntity.getIsQualified());//合格
                        materialDetailsDTO.setImageUrl(detailsEntity.getDetailsUrl() == null ? "" : detailsEntity.getDetailsUrl());//指标验收图片链接
                        materialDetailsDTO.setModifyDate(detailsEntity.getModifyDate() == null ? "" : DateUtils.format(detailsEntity.getModifyDate(), DateUtils.FORMAT_LONG));
                        materialDetailsDTO.setGuide(detailsEntity.getGuide() == null ? "" : detailsEntity.getGuide());
                        materialDetailsList.add(materialDetailsDTO);
                    }
                    materialDTO.setDetailsList(materialDetailsList);
                }
                ProjectMaterialOutEntity materialOut=projectMaterialRepository.getMaterialOut(materialEntity.getMaterialId());
                if(materialOut!=null){
                    materialDTO.setOutTime(materialOut.getOutTime() == null ? "" : DateUtils.format(materialOut.getOutTime(),DateUtils.FORMAT_LONG));//退场时间
                    materialDTO.setDescription(materialOut.getDescription() == null ? "" : materialOut.getDescription());
                    List<ProjectImagesEntity> projectImagesList = dailyPatrolInspectionRepository.getProjectImages(materialOut.getId());
                    if (projectImagesList != null) {
                        List<MaterialImageDTO> getimageList = new ArrayList<MaterialImageDTO>();//******************返回数据list*******整改图片*********
                        for (ProjectImagesEntity projectImagesEntity : projectImagesList) {
                            MaterialImageDTO getImage = new MaterialImageDTO();
                            getImage.setId(projectImagesEntity.getId() == null ? "" : projectImagesEntity.getId());
                            getImage.setBusinessId(projectImagesEntity.getBusinessId() == null ? "" : projectImagesEntity.getBusinessId());
                            getImage.setImageUrl(projectImagesEntity.getUrl() == null ? "" : projectImagesEntity.getUrl());
                            getImage.setType(projectImagesEntity.getType() == null ? "" : projectImagesEntity.getType());
                            getImage.setState(projectImagesEntity.getState() == null ? "" : projectImagesEntity.getState());
                            getImage.setCreateOn(projectImagesEntity.getCreateOn() == null ? null : DateUtils.format(projectImagesEntity.getCreateOn()));
                            getImage.setModifyOn(projectImagesEntity.getModifyOn() == null ? null : DateUtils.format(projectImagesEntity.getModifyOn()));
                            getimageList.add(getImage);
                        }
                        materialDTO.setImageList(getimageList);
                    }
                }
                materialList.add(materialDTO);
            }
            if (materialList.size() > 0) {
                getMaterialListDTO.setList(materialList);
                getMaterialListDTO.setId(materialList.get(materialList.size() - 1).getId());
                getMaterialListDTO.setTimeStamp(materialList.get(materialList.size() - 1).getModifyDate());
                getMaterialListDTO.setProjectId(projectId);
            }
//            getMaterialListDTO.setList(materialList);
        }
        return getMaterialListDTO;
    }
    /**
     * 材料验收增加退场纪录 支持批量
     * */
    @Override
    public ApiResult saveMaterialOut(ProjectMaterialOutListDTO materialOutListDTO, UserPropertyStaffEntity userProperty) {
        if(materialOutListDTO == null){
            return ErrorResource.getError("tip_00000431");//用户为空
        }
        try{
           List<ProjectMaterialOutDTO> list=materialOutListDTO.getList();
            ProjectMaterialListDTO getMaterialListDTO=new ProjectMaterialListDTO();

            if(list!=null && list.size()>0){
                List<ProjectMaterialDTO> getProjectMaterialList=new ArrayList<ProjectMaterialDTO>();
                for(ProjectMaterialOutDTO materialOutDTO:list){
                    ProjectMaterialEntity materialEntity=projectMaterialRepository.getMaterialById(materialOutDTO.getMaterialId());
                    if("非正常关闭".equals(materialEntity.getState()) || "已退场".equals(materialEntity.getState())){
                        ProjectMaterialDTO projectMaterialDTO=getMaterialDTO(materialEntity.getMaterialId(),null);
                        getProjectMaterialList.add(projectMaterialDTO);
                        continue;
                    }
                    materialEntity.setState("已退场");
                    materialEntity.setModifyDate(new Date());

                    ProjectMaterialOutEntity materialOutEntity=new ProjectMaterialOutEntity();
                    materialOutEntity.setId(IdGen.uuid());
                    materialOutEntity.setCreateOn(new Date());
                    materialOutEntity.setCreateBy(userProperty.getStaffId());
                    materialOutEntity.setAppId(materialOutDTO.getAppId());
                    materialOutEntity.setMaterialId(materialOutDTO.getMaterialId());
                    materialOutEntity.setOutTime(DateUtils.parse(materialOutDTO.getOutTime(), DateUtils.FORMAT_LONG));
                    materialOutEntity.setDescription(materialOutDTO.getDescription());
                    materialOutEntity.setModifyOn(new Date());
                    materialOutEntity.setModifyBy(userProperty.getStaffId());
                    List<MaterialImageDTO> imageList=materialOutDTO.getImageList();

                    try{
                        projectMaterialRepository.saveUpdateProjectMaterialOut(materialOutEntity);
                        projectMaterialRepository.updateProjectMaterial(materialEntity);//修改材料验收
                        if(imageList!=null){
                            for(MaterialImageDTO MaterialImage:imageList){
                                ProjectImagesEntity saveImage = new ProjectImagesEntity();
                                saveImage.setUrl(MaterialImage.getImageUrl());
                                saveImage.setId(IdGen.uuid());
                                saveImage.setBusinessId(materialOutEntity.getId());//退场iD
                                saveImage.setType("5");
                                saveImage.setState("1");
                                saveImage.setCreateOn(new Date());
                                saveImage.setModifyOn(new Date());
                                dailyPatrolInspectionRepository.saveProjectImages(saveImage);
                            }
                        }
                    }catch (Exception e){
                        ProjectMaterialDTO projectMaterialDTO=getMaterialDTO(materialOutEntity.getMaterialId(),null);
                        getProjectMaterialList.add(projectMaterialDTO);
                        continue;
                    }
                    ProjectMaterialDTO projectMaterialDTO=getMaterialDTO(materialOutEntity.getMaterialId(),null);
                    getProjectMaterialList.add(projectMaterialDTO);
                }
                getMaterialListDTO.setList(getProjectMaterialList);
            }
            return new SuccessApiResult(getMaterialListDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }

    @Override
    public List<CheckDailyPatrolInspectionDTO> checkDailyPatrolInspectionForApp(CheckDailyPatrolInspectionListDTO checkDailyPatrolInspectionList, String creaby) {
        List<CheckDailyPatrolInspectionDTO> checkList = checkDailyPatrolInspectionList.getCheckList();
        List<CheckDailyPatrolInspectionDTO> getCheckList = new ArrayList<CheckDailyPatrolInspectionDTO>();
        if (checkList != null) {
            for (CheckDailyPatrolInspectionDTO check : checkList) {
                CheckDailyPatrolInspectionDTO getCheck = new CheckDailyPatrolInspectionDTO();
                    String type="1";
                if (staffEmployRepository.checkOwner(creaby, check.getProjectId(), "1")) {//甲方
                    //甲方   该项目下所有数据和自己创建的草稿
                    type = "1";
                }else{
                    String chec= staffEmployRepository.getPurviewName(creaby, check.getProjectId());
                    if("5".equals(chec)){
                        type = "2";
                    }else{
                        type = "0";
                    }
                }
                String time = "";
                if (check.getTimeStamp() != null && !"".equals(check.getTimeStamp())) {
                    time = DateUtils.format(DateUtils.parse(check.getTimeStamp(), "yyyyMMddHHmmss"));
                }
//                String id,String time,String projectId,String type,String userId
                boolean checkFlag = projectMaterialRepository.checkoutMaterial(check.getId(), time, check.getProjectId(), type,creaby);
                if (checkFlag) {
                    getCheck.setUpdateFlag("Y");
                } else {
                    getCheck.setUpdateFlag("N");
                }
                getCheck.setProjectId(check.getProjectId());
                getCheck.setId(check.getId());
                getCheck.setTimeStamp(check.getTimeStamp());
                getCheckList.add(getCheck);
            }
        }
        return getCheckList;
    }

    @Override
    public ApiResult searchMaterial(String projectId) {
        if (StringUtil.isEmpty(projectId)) {
            return ErrorResource.getError("tip_00000572");//项目编码不能为空
        }
        try {
            List<SearchMaterialDTO> searchMaterialList = new ArrayList<SearchMaterialDTO>();
            int all = 0;
            int ok = 0;
            int no = 0;
            int out = 0;
            List<Object[]> list = projectMaterialRepository.searchMaterial(projectId);
            if (list != null && list.size() > 0) {
                SearchMaterialDTO searchMaterial = new SearchMaterialDTO();
                for (Object[] obj : list) {
                    SearchMaterialDTO Material = new SearchMaterialDTO();
                    Material.setProjectId(projectId);
                    Material.setClassifyOne(obj[0] == null ? "" : obj[0].toString());
                    Material.setClassifyOneName(obj[1] == null ? "" : obj[1].toString());
                    Material.setMaterialAll(obj[2] == null ? 0 : ((BigInteger) obj[2]).intValue());
                    Material.setMaterialOk(obj[3] == null ? 0 : ((BigInteger) obj[3]).intValue());
                    Material.setMaterialNo(obj[4] == null ? 0 : ((BigInteger) obj[4]).intValue());
                    Material.setMaterialOut(obj[5] == null ? 0 : ((BigInteger) obj[5]).intValue());
                    //总计数据
                    all += Material.getMaterialAll();
                    ok  += Material.getMaterialOk();
                    no  += Material.getMaterialNo();
                    out += Material.getMaterialOut();
                    searchMaterialList.add(Material);
                }
                searchMaterial.setProjectId(projectId);
                searchMaterial.setClassifyOne("totalAll");
                searchMaterial.setClassifyOneName("总计");
                searchMaterial.setMaterialAll(all);
                searchMaterial.setMaterialOk(ok);
                searchMaterial.setMaterialNo(no);
                searchMaterial.setMaterialOut(out);
                searchMaterialList.add(searchMaterial);
            }
            return new SuccessApiResult(searchMaterialList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }
    /**
     * 后台查询材料验收列表
     * */
    @Override
    public List<ProjectMaterialAdminDTO> getMaterialAdmin(GetProjectMaterialDTO getProjectMaterialDTO, WebPage webPage, String creaBy) {
        Map map = new HashMap();
        map.put("projectId", getProjectMaterialDTO.getProjectId());//项目id
        map.put("buildingId", getProjectMaterialDTO.getBuildingId());//楼栋id
        map.put("state", getProjectMaterialDTO.getState());//状态
        map.put("classifyOne", getProjectMaterialDTO.getClassifyOne());//一级分类
        map.put("classifyTwo", getProjectMaterialDTO.getClassifyTwo());//二级分类
        map.put("startDate", getProjectMaterialDTO.getStartDate());//开始日期
        map.put("endDate", getProjectMaterialDTO.getEndDate());//结束时间

        map.put("supplier", "");//责任单位
        map.put("firstPartyName", "");//甲方负责人名字
        map.put("supervisorName", "");//第三方监理名字
        map.put("assignName", "");//整改人名字
        map.put("projectList","NO");//项目权限
        map.put("creaBy", creaBy);
        if (!StringUtil.isEmpty(getProjectMaterialDTO.getSupplier())) {
            map.put("supplier", "%" + getProjectMaterialDTO.getSupplier() + "%");//材料单位
        }
        if (!StringUtil.isEmpty(getProjectMaterialDTO.getFirstPartyName())) {
            map.put("firstPartyName", "%" + getProjectMaterialDTO.getFirstPartyName() + "%");//甲方负责人名字
        }
        if (!StringUtil.isEmpty(getProjectMaterialDTO.getSupervisorName())) {//监理
            map.put("supervisorName", "%" + getProjectMaterialDTO.getSupervisorName() + "%");
        }
        if (!StringUtil.isEmpty(getProjectMaterialDTO.getAssignName())) {//材料负责人
            map.put("assignName", "%" + getProjectMaterialDTO.getAssignName() + "%");
        }
        List<ProjectMaterialAdminDTO> retList = new ArrayList<ProjectMaterialAdminDTO>();
        List<ProjectMaterialEntity> list = null;
//        if(!StringUtil.isEmpty(getProjectMaterialDTO.getProjectId())){
//            list = projectMaterialRepository.getMaterialAdmin(map, webPage);
//        }else{
//            boolean f = getAllClassifyService.getRoleViewmodelByStaffId(creaBy);
//            if(f){
//                list = projectMaterialRepository.getMaterialAdmin(map, webPage);
//            }else{
//                List<ProjectProjectEntity> projectList1=staffEmployRepository.getProjectListForAgency(creaBy);
//                String proejctAll="";
//                if(projectList1!=null){
//                    for(ProjectProjectEntity project:projectList1){
//                        proejctAll+="'"+project.getId()+"',";
//                    }
//                }
//                List<ProjectProjectEntity> projectList2=staffEmployRepository.getProjectListByStaffId(creaBy);
//                if(projectList2!=null){
//                    for(ProjectProjectEntity project:projectList2){
//                        proejctAll+="'"+project.getId()+"',";
//                    }
//                }
//                String project=proejctAll.substring(0,proejctAll.length()-1);
//                map.put("projectList",project);//项目权限
//                list = projectMaterialRepository.getMaterialAdmin(map, webPage);
//            }
//        }
        if(!StringUtil.isEmpty(getProjectMaterialDTO.getProjectId())){
            list = projectMaterialRepository.getMaterialAdmin(map, webPage);
        }
        if (list != null && !list.isEmpty()) {
            for(ProjectMaterialEntity materialEntity:list){
                ProjectMaterialAdminDTO getMaterialAdminDTO=new ProjectMaterialAdminDTO();
                //甲方负责人
                getMaterialAdminDTO.setFirstPartyName(materialEntity.getFirstPartyName() == null ? "" : materialEntity.getFirstPartyName());
                //第三方监理
                getMaterialAdminDTO.setSupervisorName(materialEntity.getSupervisorName() == null ? "" : materialEntity.getSupervisorName());
                //材料负责人
                getMaterialAdminDTO.setAssignName(materialEntity.getAssignName() == null ? "" : materialEntity.getAssignName());
                //材料验收Id
                getMaterialAdminDTO.setMaterialId(materialEntity.getMaterialId() == null ? "" : materialEntity.getMaterialId());
                //项目名称
                getMaterialAdminDTO.setProjectName(materialEntity.getProjectName() == null ? "" : materialEntity.getProjectName());
                //材料类型
                getMaterialAdminDTO.setClassTwoName(materialEntity.getClassifyTwoName() == null ? "" : materialEntity.getClassifyTwoName());
                //供应商名字
                getMaterialAdminDTO.setSupplierName(materialEntity.getSupplierName() == null ? "" : materialEntity.getSupplierName());
                //状态
                getMaterialAdminDTO.setState(materialEntity.getState() == null ? "" : materialEntity.getState());
                //进场时间
                getMaterialAdminDTO.setApproachTime(materialEntity.getApproachTime() == null ? "" : DateUtils.format(materialEntity.getApproachTime(),DateUtils.FORMAT_SHORT));
                //项目ID
                getMaterialAdminDTO.setProjectId(materialEntity.getProjectId() == null ? "" : materialEntity.getProjectId());
                retList.add(getMaterialAdminDTO);
            }
        }
        return retList;
    }

    @Override
    public void exportExcel(String title, String[] headers, ServletOutputStream out, GetProjectMaterialDTO getProjectMaterialDTO, WebPage webPage, String creaBy) {

        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        Map map = new HashMap();
        map.put("projectId", getProjectMaterialDTO.getProjectId());//项目id
        map.put("buildingId", getProjectMaterialDTO.getBuildingId());//楼栋id
        map.put("state", getProjectMaterialDTO.getState());//状态
        map.put("classifyOne", getProjectMaterialDTO.getClassifyOne());//一级分类
        map.put("classifyTwo", getProjectMaterialDTO.getClassifyTwo());//二级分类
        map.put("startDate", getProjectMaterialDTO.getStartDate());//开始日期
        map.put("endDate", getProjectMaterialDTO.getEndDate());//结束时间

        map.put("supplier", "");//责任单位
        map.put("firstPartyName", "");//甲方负责人名字
        map.put("supervisorName", "");//第三方监理名字
        map.put("assignName", "");//整改人名字
        map.put("projectList","NO");//项目权限
        map.put("creaBy", creaBy);
        if (!StringUtil.isEmpty(getProjectMaterialDTO.getSupplier())) {
            map.put("supplier", "%" + getProjectMaterialDTO.getSupplier() + "%");//材料单位
        }
        if (!StringUtil.isEmpty(getProjectMaterialDTO.getFirstPartyName())) {
            map.put("firstPartyName", "%" + getProjectMaterialDTO.getFirstPartyName() + "%");//甲方负责人名字
        }
        if (!StringUtil.isEmpty(getProjectMaterialDTO.getSupervisorName())) {//监理
            map.put("supervisorName", "%" + getProjectMaterialDTO.getSupervisorName() + "%");
        }
        if (!StringUtil.isEmpty(getProjectMaterialDTO.getAssignName())) {//材料负责人
            map.put("assignName", "%" + getProjectMaterialDTO.getAssignName() + "%");
        }
        List<ExportExcelMaterialDTO> retList = new ArrayList<ExportExcelMaterialDTO>();
        List<ProjectMaterialEntity> list = null;
//        if(!StringUtil.isEmpty(getProjectMaterialDTO.getProjectId())){
//            list = projectMaterialRepository.getMaterialAdmin(map, webPage);
//        }else{
//            boolean f = getAllClassifyService.getRoleViewmodelByStaffId(creaBy);
//            if(f){
//                list = projectMaterialRepository.getMaterialAdmin(map, webPage);
//            }else{
//                List<ProjectProjectEntity> projectList1=staffEmployRepository.getProjectListForAgency(creaBy);
//                String proejctAll="";
//                if(projectList1!=null){
//                    for(ProjectProjectEntity project:projectList1){
//                        proejctAll+="'"+project.getId()+"',";
//                    }
//                }
//                List<ProjectProjectEntity> projectList2=staffEmployRepository.getProjectListByStaffId(creaBy);
//                if(projectList2!=null){
//                    for(ProjectProjectEntity project:projectList2){
//                        proejctAll+="'"+project.getId()+"',";
//                    }
//                }
//                String project=proejctAll.substring(0,proejctAll.length()-1);
//                map.put("projectList",project);//项目权限
//                list = projectMaterialRepository.getMaterialAdmin(map, webPage);
//            }
//        }
        if(!StringUtil.isEmpty(getProjectMaterialDTO.getProjectId())){
            list = projectMaterialRepository.getMaterialAdmin(map, webPage);
        }
        if(list!=null){
            int num = 1;
            for(ProjectMaterialEntity materialEntity:list){
                ExportExcelMaterialDTO excelMaterialDTO=new ExportExcelMaterialDTO();
                excelMaterialDTO.setNum(num++);
                //材料验收Id
//                excelMaterialDTO.setMaterialId(materialEntity.getMaterialId() == null ? "" : materialEntity.getMaterialId());
                //项目名称
                excelMaterialDTO.setProjectName(materialEntity.getProjectName() == null ? "" : materialEntity.getProjectName());
                //材料类型
                excelMaterialDTO.setClassifyTwoName(materialEntity.getClassifyTwoName() == null ? "" : materialEntity.getClassifyTwoName());
                //批次名称
                excelMaterialDTO.setBatchName(materialEntity.getBatchName() == null ? "" : materialEntity.getBatchName());
                //进场时间
                excelMaterialDTO.setApproachTime(materialEntity.getApproachTime() == null ? "" : DateUtils.format(materialEntity.getApproachTime(),DateUtils.FORMAT_SHORT));
                //进场批量
                excelMaterialDTO.setApproachNumber(materialEntity.getApproachNumber() == null ? "" : materialEntity.getApproachNumber());
                //准备使用部位
                excelMaterialDTO.setUsedPart(materialEntity.getUsedPart() == null ? "" : materialEntity.getUsedPart());
                //供应商名字
                excelMaterialDTO.setSupplierName(materialEntity.getSupplierName() == null ? "" : materialEntity.getSupplierName());
                //材料负责人
                excelMaterialDTO.setAssignName(materialEntity.getAssignName() == null ? "" : materialEntity.getAssignName());
                //甲方负责人名字
                excelMaterialDTO.setFirstPartyName(materialEntity.getFirstPartyName() == null ? "" : materialEntity.getFirstPartyName());
                //第三方监理名字
                excelMaterialDTO.setSupervisorName(materialEntity.getSupervisorName() == null ? "" : materialEntity.getSupervisorName());
                //创建人名字
                excelMaterialDTO.setCreateName(materialEntity.getCreateName() == null ? "" : materialEntity.getCreateName());
                //状态
                excelMaterialDTO.setState(materialEntity.getState() == null ? "" : materialEntity.getState());
                retList.add(excelMaterialDTO);
            }
            ExportExcel<ExportExcelMaterialDTO> ex = new ExportExcel<ExportExcelMaterialDTO>();
            ex.exportExcel(title, headers, retList, out, "yyyy-MM-dd");
            System.out.println("excel导出成功！");
        }
    }
    /**
     * 材料验收查询详情
     * */
    @Override
    public MaterialAdminDTO getMaterialAdminByID(GetProjectMaterialDTO getProjectMaterialDTO,UserInformationEntity userInformationEntity) {
        MaterialAdminDTO getMaterialAdminDTO=new MaterialAdminDTO();

        //判断是否有关闭权限
        boolean check = staffEmployRepository.checkOwner(userInformationEntity.getStaffId(), getProjectMaterialDTO.getProjectId(), "4");
        if(check){
            getMaterialAdminDTO.setShutDownState("1");//有处理权限
        }else{
            getMaterialAdminDTO.setShutDownState("0");//无处理权限
        }
        if(!StringUtil.isEmpty(getProjectMaterialDTO.getMaterialId())){
            ProjectMaterialEntity materialEntity=projectMaterialRepository.getMaterialById(getProjectMaterialDTO.getMaterialId());
            getMaterialAdminDTO.setMaterialId(materialEntity.getMaterialId());//材料验收Id
            getMaterialAdminDTO.setBatchName(materialEntity.getBatchName() == null ? "" : materialEntity.getBatchName());//批次名称
            getMaterialAdminDTO.setClassifyOne(materialEntity.getClassifyOne() == null ? "" : materialEntity.getClassifyOne());//一级分类
            getMaterialAdminDTO.setClassifyOneName(materialEntity.getClassifyOneName() == null ? "" : materialEntity.getClassifyOneName());//一级分类名称
            getMaterialAdminDTO.setClassifyTwo(materialEntity.getClassifyTwo() == null ? "" : materialEntity.getClassifyTwo());//二级分类
            getMaterialAdminDTO.setClassifyTwoName(materialEntity.getClassifyTwoName() == null ? "" : materialEntity.getClassifyTwoName());//二级分类名称
            getMaterialAdminDTO.setApproachTime(materialEntity.getApproachTime() == null ? "" : DateUtils.format(materialEntity.getApproachTime(), DateUtils.FORMAT_SHORT));//进场时间
            getMaterialAdminDTO.setApproachNumber(materialEntity.getApproachNumber() == null ? "" : materialEntity.getApproachNumber());//进场批量
            getMaterialAdminDTO.setUsedPart(materialEntity.getUsedPart() == null ? "" : materialEntity.getUsedPart());//准备使用部位
            getMaterialAdminDTO.setCreateBy(materialEntity.getCreateBy() == null ? "" : materialEntity.getCreateBy());//创建人Id
            getMaterialAdminDTO.setCreateOn(materialEntity.getCreateOn() == null ? "" : DateUtils.format(materialEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
            getMaterialAdminDTO.setCreateName(materialEntity.getCreateName() == null ? "" : materialEntity.getCreateName());//创建人名字
            getMaterialAdminDTO.setInspectedTime(materialEntity.getInspectedTime() == null ? "" : DateUtils.format(materialEntity.getInspectedTime(), DateUtils.FORMAT_LONG));//验收时间
            getMaterialAdminDTO.setSupplierId(materialEntity.getSupplierId() == null ? "" : materialEntity.getSupplierId());//供应商id
            getMaterialAdminDTO.setSupplierName(materialEntity.getSupplierName() == null ? "" : materialEntity.getSupplierName());//供应商名字
            getMaterialAdminDTO.setAssignId(materialEntity.getAssignId() == null ? "" : materialEntity.getAssignId());//材料负责人Id
            getMaterialAdminDTO.setAssignName(materialEntity.getAssignName() == null ? "" : materialEntity.getAssignName());//材料负责人
            getMaterialAdminDTO.setSupervisor(materialEntity.getSupervisor() == null ? "" : materialEntity.getSupervisor());//第三方监理id
            getMaterialAdminDTO.setSupervisorName(materialEntity.getSupervisorName() == null ? "" : materialEntity.getSupervisorName());//第三方监理名字
            getMaterialAdminDTO.setDealPeople(materialEntity.getDealPeople() == null ? "" : materialEntity.getDealPeople());//处理人
            getMaterialAdminDTO.setModifyDate(materialEntity.getModifyDate() == null ? "" : DateUtils.format(materialEntity.getModifyDate(), DateUtils.FORMAT_LONG));//修改时间
            getMaterialAdminDTO.setProjectId(materialEntity.getProjectId() == null ? "" : materialEntity.getProjectId());
            getMaterialAdminDTO.setProjectName(materialEntity.getProjectName() == null ? "" : materialEntity.getProjectName());
            getMaterialAdminDTO.setAppId(materialEntity.getAppId());//appId
            getMaterialAdminDTO.setState(materialEntity.getState());
            getMaterialAdminDTO.setFirstPartyName(materialEntity.getFirstPartyName() == null ? "" : materialEntity.getFirstPartyName());
            getMaterialAdminDTO.setFirstParty(materialEntity.getFirstParty() == null ? "" : materialEntity.getFirstParty());
            getMaterialAdminDTO.setShutDownBy(materialEntity.getShutDownBy() == null ? "" : materialEntity.getShutDownBy());
            getMaterialAdminDTO.setShutDown(materialEntity.getShutDown() == null ? "" : materialEntity.getShutDown());
            getMaterialAdminDTO.setShutDownOn(materialEntity.getShutDownOn() == null ? "" : DateUtils.format(materialEntity.getShutDownOn(),DateUtils.FORMAT_LONG));
            List<ProjectMaterialDetailsEntity> MaterialDetails=projectMaterialRepository.getMaterialDetailsList(materialEntity.getMaterialId());
            if(MaterialDetails!=null){
                List<ProjectMaterialDetailsDTO> materialDetailsList=new ArrayList<ProjectMaterialDetailsDTO>();
                for(ProjectMaterialDetailsEntity detailsEntity : MaterialDetails){
                    ProjectMaterialDetailsDTO materialDetailsDTO=new ProjectMaterialDetailsDTO();
                    materialDetailsDTO.setId(detailsEntity.getId());//id
                    materialDetailsDTO.setMaterialId(detailsEntity.getMaterialId());//材料验收ID
                    materialDetailsDTO.setTargetId(detailsEntity.getTargetId() == null ? "" : detailsEntity.getTargetId());//指标ID
                    materialDetailsDTO.setTargetName(detailsEntity.getTargetName() == null ? "" : detailsEntity.getTargetName());//指标名
                    materialDetailsDTO.setDescription(detailsEntity.getDescription() == null ? "" : detailsEntity.getDescription());//描述
                    materialDetailsDTO.setIsQualified(detailsEntity.getIsQualified() == null ? "" : detailsEntity.getIsQualified());//合格
                    materialDetailsDTO.setImageUrl(detailsEntity.getDetailsUrl() == null ? "" : detailsEntity.getDetailsUrl());//指标验收图片链接
                    materialDetailsDTO.setModifyDate(detailsEntity.getModifyDate() == null ? "" : DateUtils.format(detailsEntity.getModifyDate(), DateUtils.FORMAT_LONG));
                    materialDetailsDTO.setGuide(detailsEntity.getGuide() == null ? "" : detailsEntity.getGuide());
                    materialDetailsList.add(materialDetailsDTO);
                }
                getMaterialAdminDTO.setDetailsList(materialDetailsList);
            }
            ProjectMaterialOutEntity materialOut=projectMaterialRepository.getMaterialOut(materialEntity.getMaterialId());
            if(materialOut!=null){
                getMaterialAdminDTO.setOutTime(materialOut.getOutTime() == null ? "" : DateUtils.format(materialOut.getOutTime(),DateUtils.FORMAT_LONG));//退场时间
                getMaterialAdminDTO.setDescription(materialOut.getDescription() == null ? "" : materialOut.getDescription());
                List<ProjectImagesEntity> projectImagesList = dailyPatrolInspectionRepository.getProjectImages(materialOut.getId());
                if (projectImagesList != null) {
                    List<MaterialImageDTO> getimageList = new ArrayList<MaterialImageDTO>();//******************返回数据list*******整改图片*********
                    for (ProjectImagesEntity projectImagesEntity : projectImagesList) {
                        MaterialImageDTO getImage = new MaterialImageDTO();
                        getImage.setId(projectImagesEntity.getId() == null ? "" : projectImagesEntity.getId());
                        getImage.setBusinessId(projectImagesEntity.getBusinessId() == null ? "" : projectImagesEntity.getBusinessId());
                        getImage.setImageUrl(projectImagesEntity.getUrl() == null ? "" : projectImagesEntity.getUrl());
                        getImage.setType(projectImagesEntity.getType() == null ? "" : projectImagesEntity.getType());
                        getImage.setState(projectImagesEntity.getState() == null ? "" : projectImagesEntity.getState());
                        getImage.setCreateOn(projectImagesEntity.getCreateOn() == null ? null : DateUtils.format(projectImagesEntity.getCreateOn()));
                        getImage.setModifyOn(projectImagesEntity.getModifyOn() == null ? null : DateUtils.format(projectImagesEntity.getModifyOn()));
                        getimageList.add(getImage);
                    }
                    getMaterialAdminDTO.setImageList(getimageList);
                }
            }
        }

        return getMaterialAdminDTO;
    }

    @Override
    public String shutDownAdmin(MaterialAdminDTO materialAdminDTO, UserInformationEntity userInformationEntity) {
        try {
            if(!StringUtil.isEmpty(materialAdminDTO.getMaterialId())){
                ProjectMaterialEntity materialEntity=projectMaterialRepository.getMaterialById(materialAdminDTO.getMaterialId());

                materialEntity.setModifyDate(new Date());
                materialEntity.setShutDown(materialAdminDTO.getShutDown());//关单原因
                materialEntity.setShutDownOn(new Date());//关单时间
                materialEntity.setShutDownBy(userInformationEntity.getStaffName());//强制关单人
                materialEntity.setState("非正常关闭");
                projectMaterialRepository.updateProjectMaterial(materialEntity);
            }
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
            throw new GeneralException("100", "系统处理错误");
        }
    }


    public ProjectMaterialDTO getMaterialDTO(String id,String appId) {
        ProjectMaterialDTO getMaterial=new ProjectMaterialDTO();
        ProjectMaterialEntity materialEntity=null;
        if(!StringUtil.isEmpty(id)){
            materialEntity=projectMaterialRepository.getMaterialById(id);
        }else{
            materialEntity=projectMaterialRepository.getMaterialByAppId(appId);
        }
        getMaterial.setMaterialId(materialEntity.getMaterialId());//材料验收Id
        getMaterial.setBatchName(materialEntity.getBatchName() == null ? "" : materialEntity.getBatchName());//批次名称
        getMaterial.setClassifyOne(materialEntity.getClassifyOne() == null ? "" : materialEntity.getClassifyOne());//一级分类
        getMaterial.setClassifyOneName(materialEntity.getClassifyOneName() == null ? "" : materialEntity.getClassifyOneName());//一级分类名称
        getMaterial.setClassifyTwo(materialEntity.getClassifyTwo() == null ? "" : materialEntity.getClassifyTwo());//二级分类
        getMaterial.setClassifyTwoName(materialEntity.getClassifyTwoName() == null ? "" : materialEntity.getClassifyTwoName());//二级分类名称
        getMaterial.setApproachTime(materialEntity.getApproachTime() == null ? "" : DateUtils.format(materialEntity.getApproachTime(), DateUtils.FORMAT_SHORT));//进场时间
        getMaterial.setApproachNumber(materialEntity.getApproachNumber() == null ? "" : materialEntity.getApproachNumber());//进场批量
        getMaterial.setUsedPart(materialEntity.getUsedPart() == null ? "" : materialEntity.getUsedPart());//准备使用部位
        getMaterial.setCreateBy(materialEntity.getCreateBy() == null ? "" : materialEntity.getCreateBy());//创建人Id
        getMaterial.setCreateOn(materialEntity.getCreateOn() == null ? "" : DateUtils.format(materialEntity.getCreateOn(), DateUtils.FORMAT_LONG));//创建时间
        getMaterial.setCreateName(materialEntity.getCreateName() == null ? "" : materialEntity.getCreateName());//创建人名字
        getMaterial.setInspectedTime(materialEntity.getInspectedTime() == null ? "" : DateUtils.format(materialEntity.getInspectedTime(), DateUtils.FORMAT_LONG));//验收时间
        getMaterial.setSupplierId(materialEntity.getSupplierId() == null ? "" : materialEntity.getSupplierId());//供应商id
        getMaterial.setSupplierName(materialEntity.getSupplierName() == null ? "" : materialEntity.getSupplierName());//供应商名字
        getMaterial.setAssignId(materialEntity.getAssignId() == null ? "" : materialEntity.getAssignId());//材料负责人Id
        getMaterial.setAssignName(materialEntity.getAssignName() == null ? "" : materialEntity.getAssignName());//材料负责人
        getMaterial.setSupervisor(materialEntity.getSupervisor() == null ? "" : materialEntity.getSupervisor());//第三方监理id
        getMaterial.setSupervisorName(materialEntity.getSupervisorName() == null ? "" : materialEntity.getSupervisorName());//第三方监理名字
        getMaterial.setDealPeople(materialEntity.getDealPeople() == null ? "" : materialEntity.getDealPeople());//处理人
        getMaterial.setModifyDate(materialEntity.getModifyDate() == null ? "" : DateUtils.format(materialEntity.getModifyDate(), DateUtils.FORMAT_LONG));//修改时间
        getMaterial.setProjectId(materialEntity.getProjectId() == null ? "" : materialEntity.getProjectId());
        getMaterial.setProjectName(materialEntity.getProjectName() == null ? "" : materialEntity.getProjectName());
        getMaterial.setAppId(materialEntity.getAppId());//appId
        getMaterial.setState(materialEntity.getState());
        List<ProjectMaterialDetailsEntity> MaterialDetails=projectMaterialRepository.getMaterialDetailsList(materialEntity.getMaterialId());
        if(MaterialDetails!=null){
            List<ProjectMaterialDetailsDTO> materialDetailsList=new ArrayList<ProjectMaterialDetailsDTO>();
            for(ProjectMaterialDetailsEntity detailsEntity : MaterialDetails){
                ProjectMaterialDetailsDTO materialDetailsDTO=new ProjectMaterialDetailsDTO();
                materialDetailsDTO.setId(detailsEntity.getId());//id
                materialDetailsDTO.setMaterialId(detailsEntity.getMaterialId());//材料验收ID
                materialDetailsDTO.setTargetId(detailsEntity.getTargetId() == null ? "" : detailsEntity.getTargetId());//指标ID
                materialDetailsDTO.setTargetName(detailsEntity.getTargetName() == null ? "" : detailsEntity.getTargetName());//指标名
                materialDetailsDTO.setDescription(detailsEntity.getDescription() == null ? "" : detailsEntity.getDescription());//描述
                materialDetailsDTO.setIsQualified(detailsEntity.getIsQualified() == null ? "" : detailsEntity.getIsQualified());//合格
                materialDetailsDTO.setImageUrl(detailsEntity.getDetailsUrl() == null ? "" : detailsEntity.getDetailsUrl());//指标验收图片链接
                materialDetailsDTO.setModifyDate(detailsEntity.getModifyDate() == null ? "" : DateUtils.format(detailsEntity.getModifyDate(), DateUtils.FORMAT_LONG));
                materialDetailsDTO.setGuide(detailsEntity.getGuide() == null ? "" : detailsEntity.getGuide());
                materialDetailsList.add(materialDetailsDTO);
            }
            getMaterial.setDetailsList(materialDetailsList);
        }
        ProjectMaterialOutEntity materialOut=projectMaterialRepository.getMaterialOut(getMaterial.getMaterialId());
        if(materialOut!=null){
            getMaterial.setOutTime(materialOut.getOutTime() == null ? "" : DateUtils.format(materialOut.getOutTime(),DateUtils.FORMAT_LONG));//退场时间
            getMaterial.setDescription(materialOut.getDescription() == null ? "" : materialOut.getDescription());
            List<ProjectImagesEntity> projectImagesList = dailyPatrolInspectionRepository.getProjectImages(materialOut.getId());
            if (projectImagesList != null) {
                List<MaterialImageDTO> getimageList = new ArrayList<MaterialImageDTO>();//******************返回数据list*******整改图片*********
                for (ProjectImagesEntity projectImagesEntity : projectImagesList) {
                    MaterialImageDTO getImage = new MaterialImageDTO();
                    getImage.setId(projectImagesEntity.getId() == null ? "" : projectImagesEntity.getId());
                    getImage.setBusinessId(projectImagesEntity.getBusinessId() == null ? "" : projectImagesEntity.getBusinessId());
                    getImage.setImageUrl(projectImagesEntity.getUrl() == null ? "" : projectImagesEntity.getUrl());
                    getImage.setType(projectImagesEntity.getType() == null ? "" : projectImagesEntity.getType());
                    getImage.setState(projectImagesEntity.getState() == null ? "" : projectImagesEntity.getState());
                    getImage.setCreateOn(projectImagesEntity.getCreateOn() == null ? "" : DateUtils.format(projectImagesEntity.getCreateOn()));
                    getImage.setModifyOn(projectImagesEntity.getModifyOn() == null ? "" : DateUtils.format(projectImagesEntity.getModifyOn()));
                    getimageList.add(getImage);
                }
                getMaterial.setImageList(getimageList);
            }
        }
        return getMaterial;
    }

}
