package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.inf.MaterialPlanService;
import com.maxrocky.vesta.application.jsonDTO.*;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chen on 2016/5/23.
 */
@Service
public class MaterialPlanServiceImpl implements MaterialPlanService {
    @Autowired
    UserPropertyStaffRepository userPropertyStaffRepository;
    @Autowired
    MaterialPlanRepository materialPlanRepository;
    @Autowired
    MaterialQuotaRepository materialQuotaRepository;
    @Autowired
    AppRoleSetRepository appRoleSetRepository;
    @Autowired
    MaterialImageRepository materialImageRepository;
    @Autowired
    HouseProjectRepository houseProjectRepository;

    @Override
    public void addMaterialPlan(List<MaterialReceiveDTO> materialReceiveDTOs)  throws ParseException {
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        MaterialPlanEntity materialPlanEntity;
        for(MaterialReceiveDTO materialReceiveDTO:materialReceiveDTOs){
            materialPlanEntity = new MaterialPlanEntity();
            materialPlanEntity.setMaterialId(IdGen.uuid());
            materialPlanEntity.setSupplier(materialReceiveDTO.getSupplier());
            materialPlanEntity.setProjectId(materialReceiveDTO.getProjectId());
            materialPlanEntity.setMaterialSpec(materialReceiveDTO.getMaterialSpec());
            materialPlanEntity.setBatchName(materialReceiveDTO.getBatchName());
            materialPlanEntity.setCheckPerson(materialReceiveDTO.getCheckPerson());
            materialPlanEntity.setCheckTime(date.parse(materialReceiveDTO.getCheckTime()));
            materialPlanEntity.setCheckUserId(materialReceiveDTO.getCheckUserId());
            materialPlanEntity.setPersonId(materialReceiveDTO.getPersonId());
            materialPlanEntity.setPersonName(materialReceiveDTO.getPersonName());
            materialPlanEntity.setParadeNum(materialReceiveDTO.getParadeNum());
            materialPlanEntity.setParadeTime(date.parse(materialReceiveDTO.getParadeTime()));
            materialPlanEntity.setUsePlace(materialReceiveDTO.getUsePlace());
            materialPlanEntity.setEligibility(materialReceiveDTO.getCheckPerson());
            materialPlanEntity.setMaterialType(materialReceiveDTO.getCheckPerson());          //材料类型待修改
            if(materialReceiveDTO.getQuotaList()!=null&&materialReceiveDTO.getQuotaList().size()>0){
                for(int i=0;i<materialReceiveDTO.getQuotaList().size();i++){
                    MaterialQuotaEntity materialQuotaEntity = new MaterialQuotaEntity();
                    materialQuotaEntity.setId(IdGen.getDateId());
                    materialQuotaEntity.setMaterialId(materialPlanEntity.getMaterialId());
                    materialQuotaEntity.setQualified(materialReceiveDTO.getQuotaList().get(i).getQualified());
                    materialQuotaEntity.setQuotaDesc(materialReceiveDTO.getQuotaList().get(i).getQuotaDesc());
                    if(materialReceiveDTO.getQuotaList().get(i).getImageList()!=null&&materialReceiveDTO.getQuotaList().get(i).getImageList().size()>0){
                        for(int j=0;j<materialReceiveDTO.getQuotaList().get(i).getImageList().size();j++){
                            MaterialImageEntity materialImageEntity = new MaterialImageEntity();
                            materialImageEntity.setId(IdGen.getAnyImgID());
                            materialImageEntity.setUrl(materialReceiveDTO.getQuotaList().get(i).getImageList().get(j).getUrl());
                            materialImageEntity.setBussinessId(materialQuotaEntity.getId());
                            materialImageEntity.setCrtTime(new Date());
                            materialImageRepository.addMaterialImage(materialImageEntity);   //保存关联图片
                        }
                    }
                    materialQuotaRepository.addMaterialQuota(materialQuotaEntity);  //保存指标
                }
            }
            materialPlanRepository.addMaterialPlan(materialPlanEntity);  //保存验收记录
        }
    }

    @Override
    public List<MaterialAllDTO> getMaterialPlanList() {
        List<MaterialAllDTO> materialAllDTOs = new ArrayList<MaterialAllDTO>();
        List<MaterialPlanEntity> materialPlanEntityList = materialPlanRepository.getMaterialPlans();
        Set<String> ids = new HashSet<String>();
        if(materialPlanEntityList!=null){
            for(MaterialPlanEntity materialPlanEntity:materialPlanEntityList){
                ids.add(materialPlanEntity.getProjectId());
            }
        }
        MaterialAllDTO materialAllDTO;
        for(String str:ids){
            materialAllDTO = new MaterialAllDTO();
            materialAllDTO.setProjectId(str);
            materialAllDTO.setProjectName(houseProjectRepository.get(str).getName());
            List<MaterialPlanDTO> materialPlanDTOs = new ArrayList<MaterialPlanDTO>();
            List<MaterialPlanEntity> materialPlanEntities = materialPlanRepository.getMaterialPlanList("1");
            if(materialPlanEntities!=null){
                MaterialPlanDTO materialPlanDTO;
                for(MaterialPlanEntity materialPlanEntity:materialPlanEntities){
                    materialPlanDTO = new MaterialPlanDTO();
                    materialPlanDTO.setMaterialSpec(materialPlanEntity.getMaterialSpec());
                    materialPlanDTO.setProjectId(materialPlanEntity.getProjectId());
                    materialPlanDTO.setProjectName(materialAllDTO.getProjectName());
                    materialPlanDTO.setBatchName(materialPlanEntity.getBatchName());
                    materialPlanDTO.setParadeNum(materialPlanEntity.getParadeNum());
                    materialPlanDTO.setParadeTime(DateUtils.format(materialPlanEntity.getParadeTime()));
                    materialPlanDTO.setEligibility(materialPlanEntity.getEligibility());
                    materialPlanDTO.setUsePlace(materialPlanEntity.getUsePlace());
                    materialPlanDTO.setMaterialType(materialPlanEntity.getMaterialType());    //材料类型待修改
                    materialPlanDTO.setSupplier(materialPlanEntity.getSupplier());
                    materialPlanDTO.setPersonName(materialPlanEntity.getPersonName());
                    materialPlanDTO.setCheckTime(DateUtils.format(materialPlanEntity.getCheckTime()));
                    materialPlanDTO.setCheckStatus("验收通过");
                    UserPropertyStaffEntity userPropertyStaffEntity = userPropertyStaffRepository.get(materialPlanEntity.getCheckUserId());
                    if(userPropertyStaffEntity!=null){
                        materialPlanDTO.setCheckPerson(userPropertyStaffEntity.getStaffName());
                        materialPlanDTO.setPersonMobile(userPropertyStaffEntity.getMobile());
                    }
                    List<AppRolesetEntity> appRolesetEntityList = appRoleSetRepository.getRoleNames(materialPlanEntity.getCheckUserId());
                    if(appRolesetEntityList!=null&&appRolesetEntityList.size()>0){
                        materialPlanDTO.setPersonRole(appRolesetEntityList.get(0).getAppSetName());
                    }
                    List<MaterialQuotaEntity> materialQuotaEntities = materialQuotaRepository.MaterialQuotaList(materialPlanEntity.getMaterialId());
                    List<QuotaDTO> quotaDTOs = new ArrayList<QuotaDTO>();
                    if(materialQuotaEntities!=null){
                        QuotaDTO quotaDTO;
                        for(MaterialQuotaEntity materialQuotaEntity:materialQuotaEntities){
                            quotaDTO = new QuotaDTO();
                            quotaDTO.setQualified(materialQuotaEntity.getQualified());
                            quotaDTO.setQuotaDesc(materialQuotaEntity.getQuotaDesc());
                            List<MaterialImageEntity> materialImageEntities = materialImageRepository.getImageList(materialQuotaEntity.getId());
                            List<MaterialImageDTO> materialImageDTOList = new ArrayList<MaterialImageDTO>();
                            if(materialImageEntities!=null){
                                MaterialImageDTO materialImageDTO;
                                for(MaterialImageEntity materialImageEntity:materialImageEntities){
                                    materialImageDTO = new MaterialImageDTO();
                                    materialImageDTO.setUrl(materialImageEntity.getUrl());
                                    materialImageDTOList.add(materialImageDTO);
                                }
                            }
                            quotaDTO.setImageList(materialImageDTOList);
                            if(materialQuotaEntity.equals("0")){
                                materialPlanDTO.setCheckStatus("验收不通过");
                            }
                            quotaDTOs.add(quotaDTO);
                        }
                    }
                    materialPlanDTO.setQuotaList(quotaDTOs);
                    materialPlanDTOs.add(materialPlanDTO);
                }
            }
            materialAllDTO.setMaterialPlanList(materialPlanDTOs);
            materialAllDTOs.add(materialAllDTO);
        }
        return materialAllDTOs;
    }

}
