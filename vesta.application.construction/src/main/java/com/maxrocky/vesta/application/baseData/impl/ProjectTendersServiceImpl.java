package com.maxrocky.vesta.application.baseData.impl;

import com.maxrocky.vesta.application.baseData.JsonDTO.BaseDataDTO;
import com.maxrocky.vesta.application.baseData.JsonDTO.TendBuildDTO;
import com.maxrocky.vesta.application.baseData.JsonDTO.TendCategoryDTO;
import com.maxrocky.vesta.application.baseData.adminDTO.AuthSupplierDTO;
import com.maxrocky.vesta.application.baseData.adminDTO.ProjectTendersDTO;
import com.maxrocky.vesta.application.baseData.adminDTO.TreeCategoryDTO;
import com.maxrocky.vesta.application.baseData.inf.ProjectTendersService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.*;
import com.maxrocky.vesta.domain.baseData.repository.ProjectProjectRepository;
import com.maxrocky.vesta.domain.baseData.repository.ProjectTendersRepository;
import com.maxrocky.vesta.domain.model.AgencyEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.AgencyRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chen on 2016/10/24.
 */
@Service
public class ProjectTendersServiceImpl implements ProjectTendersService {
    @Autowired
    ProjectTendersRepository projectTendersRepository;
    @Autowired
    ProjectProjectRepository projectProjectRepository;
    @Autowired
    AgencyRepository agencyRepository;

    @Override
    public void addProjectTenders(ProjectTendersDTO projectTendersDTO, UserInformationEntity userPropertyStaffEntity) {
        ProjectTendersEntity projectTendersEntity = null;
        //新增标段
        projectTendersEntity = new ProjectTendersEntity();
        projectTendersEntity.setTenderId(IdGen.uuid());
        projectTendersEntity.setCreateBy(userPropertyStaffEntity.getStaffName());
        projectTendersEntity.setCreateOn(new Date());
        projectTendersEntity.setModifyBy(userPropertyStaffEntity.getStaffName());
        projectTendersEntity.setModifyOn(new Date());
        projectTendersEntity.setSupplierId(projectTendersDTO.getSupplierId());
        projectTendersEntity.setProjectId(projectTendersDTO.getProjectId());
        projectTendersEntity.setTenderState("1");
        projectTendersEntity.setTenderName(projectTendersDTO.getTenderName());

        if (!StringUtil.isEmpty(projectTendersDTO.getBuildIds())) {  //楼栋范围不为空时
            TendersBuildingEntity tendersBuildingEntity;
            String[] buildId = projectTendersDTO.getBuildIds().split(",");
            for (String id : buildId) {
                tendersBuildingEntity = new TendersBuildingEntity();
//                tendersBuildingEntity.setId(IdGen.uuid());
                tendersBuildingEntity.setTendersId(projectTendersEntity.getTenderId());
                tendersBuildingEntity.setBuildingId(id);
                tendersBuildingEntity.setModifyTime(new Date());
                //先定关系状态为1
                tendersBuildingEntity.setTenderStatus("1");
                projectTendersRepository.addTendersBuild(tendersBuildingEntity);//保存标段楼栋关系
            }
        }

        if (!StringUtil.isEmpty(projectTendersDTO.getCategoryIds())) {  //相关检查项不为空时
            String[] categoryId = projectTendersDTO.getCategoryIds().split(",");
            TendersCategoryEntity tendersCategoryEntity;
            for (String id : categoryId) {
                tendersCategoryEntity = new TendersCategoryEntity();
//                tendersCategoryEntity.setId(IdGen.uuid());
                tendersCategoryEntity.setCategoryId(id);
                tendersCategoryEntity.setTendersId(projectTendersEntity.getTenderId());
                tendersCategoryEntity.setNexusStatus("1");
                tendersCategoryEntity.setModifyTime(new Date());
                projectTendersRepository.addTendersCategory(tendersCategoryEntity);//保存标段与分类关系
            }
        }

        projectTendersRepository.addProjectTenders(projectTendersEntity);
    }

    @Override
    public void updateProjectTenders(ProjectTendersDTO projectTendersDTO) {
        ProjectTendersEntity projectTendersEntity;
        //修改标段
        projectTendersEntity = projectTendersRepository.getTendersDetail(projectTendersDTO.getTenderId());
        projectTendersEntity.setTenderName(projectTendersDTO.getTenderName());
        projectTendersEntity.setSupplierId(projectTendersDTO.getSupplierId());

        String[] ids;
        List<String> compairDTO1 = new ArrayList<String>();
        List<String> compairDTO2 = new ArrayList<String>();
        List<String> compairDTO3 = new ArrayList<String>();
        Iterator<String> it1;
        Iterator<String> it2;
        List<String> buildIds = projectTendersRepository.getTendersBuilds(projectTendersEntity.getTenderId());//已存在的关系数据
        if(buildIds!=null){
            for(String buildId:buildIds){
                compairDTO1.add(buildId);   //将数据库中的数据存放于compairDTO1
            }
        }
        if(!StringUtil.isEmpty(projectTendersDTO.getBuildIds())){
            ids = projectTendersDTO.getBuildIds().split(",");
            for (int i = 0; i <ids.length;i++){
                compairDTO2.add(ids[i]);      //将页面传来的数据存放于compairDTO2
            }
        }
        compairDTO3.addAll(compairDTO1);
        compairDTO1.removeAll(compairDTO2);//比较后 为待删除的数据
        it1 = compairDTO1.iterator();
        TendersBuildingEntity tendersBuildingEntity;
        while (it1.hasNext()){
            tendersBuildingEntity = new TendersBuildingEntity();
            tendersBuildingEntity.setBuildingId(it1.next());
            tendersBuildingEntity.setTendersId(projectTendersEntity.getTenderId());
            projectTendersRepository.deleteTendBuild(tendersBuildingEntity);//删除关联数据
        }
        compairDTO2.removeAll(compairDTO3);//比较后 待新增的数据
        it2 = compairDTO2.iterator();
        while (it2.hasNext()){
            tendersBuildingEntity = new TendersBuildingEntity();
            tendersBuildingEntity.setBuildingId(it2.next());
            tendersBuildingEntity.setTendersId(projectTendersEntity.getTenderId());
            projectTendersRepository.dumpAddTendBuild(tendersBuildingEntity);  //保存最新关系
        }
        projectTendersRepository.updateProjectTenders(projectTendersEntity);
    }

    @Override
    public void delProjectTenders(String tenderId) {
        ProjectTendersEntity projectTendersEntity = projectTendersRepository.getTendersDetail(tenderId);
        projectTendersEntity.setTenderState("0");
        projectTendersEntity.setModifyOn(new Date());
        projectTendersRepository.updateProjectTenders(projectTendersEntity);

        //同时删除标段和楼栋之间的关系，将标段楼栋关系状态改为0
        List<TendersBuildingEntity> list = projectTendersRepository.getTendersBuildingByTenderId(tenderId);
        for (TendersBuildingEntity tendersBuildingEntity : list) {
            tendersBuildingEntity.setTenderStatus("0");
            tendersBuildingEntity.setModifyTime(new Date());
            projectTendersRepository.updateTendersBuild(tendersBuildingEntity);
        }

        //删除标段和检查项之间的关系，将标段检查项关系状态改为0
        List<TendersCategoryEntity> list1 = projectTendersRepository.getTendersCategoryByTenderId(tenderId);
        for (TendersCategoryEntity tendersCategoryEntity : list1) {
            tendersCategoryEntity.setNexusStatus("0");
            tendersCategoryEntity.setModifyTime(new Date());
            projectTendersRepository.updateTendersCategory(tendersCategoryEntity);
        }
    }

    @Override
    public ProjectTendersDTO getTendersDetail(String tenderId) {
        ProjectTendersDTO projectTendersDTO = new ProjectTendersDTO();
        ProjectTendersEntity projectTendersEntity = projectTendersRepository.getTendersDetail(tenderId);
        if (projectTendersEntity != null) {
            projectTendersDTO.setProjectId(projectTendersEntity.getProjectId());
            projectTendersDTO.setProjectName(projectTendersEntity.getTenderName());
            projectTendersDTO.setTenderId(String.valueOf(projectTendersEntity.getTenderId()));
            projectTendersDTO.setTenderName(projectTendersEntity.getTenderName());
            projectTendersDTO.setSupplierId(projectTendersEntity.getSupplierId());
            //获取与该标段对应的所有楼栋
            List<TendersBuildingEntity> list = projectTendersRepository.getTendersBuildingByTenderId(tenderId);
            projectTendersDTO.setBuildingIds(list);
            projectTendersDTO.setModifyOn(DateUtils.format(projectTendersEntity.getModifyOn()));
        }
        return projectTendersDTO;
    }

    @Override
    public List<ProjectTendersDTO> getTenderList(String projectId, WebPage webPage) {
        List<ProjectTendersDTO> projectTendersDTOs = new ArrayList<ProjectTendersDTO>();
        List<ProjectTendersEntity> projectTendersEntities = projectTendersRepository.getTendersByProjectId(projectId, webPage);
        if (projectTendersEntities != null) {
            ProjectTendersDTO projectTendersDTO;
            for (ProjectTendersEntity projectTendersEntity : projectTendersEntities) {
                projectTendersDTO = new ProjectTendersDTO();
                projectTendersDTO.setTenderId(String.valueOf(projectTendersEntity.getTenderId()));
                projectTendersDTO.setTenderName(projectTendersEntity.getTenderName());
                projectTendersDTO.setProjectName(projectProjectRepository.getAuthAgencyES(projectId,"100000002").getAgencyName());
                if (!StringUtil.isEmpty(projectTendersEntity.getSupplierId())) {
                    List<Object []> list=agencyRepository.getAuthSupplierByprojectId("","1",projectTendersEntity.getSupplierId());
                    if(list!=null){
                        projectTendersDTO.setSupplierName(list.get(0)[1].toString());
                    }
//                    AgencyEntity agencyEntity=agencyRepository.getAgencyDetail(projectTendersEntity.getSupplierId());
//                    if(agencyEntity!=null){
//                        projectTendersDTO.setSupplierName(agencyEntity.getAgencyName());
//                    }
                }
                projectTendersDTO.setModifyOn(DateUtils.format(projectTendersEntity.getModifyOn()));
                projectTendersDTOs.add(projectTendersDTO);
            }
        }
        return projectTendersDTOs;
    }

    @Override
    public ApiResult getTendCategory(String projectId, String timeStamp, String autoId) {
        BaseDataDTO baseDataDTO = new BaseDataDTO();
        List<TendCategoryDTO> tendCategoryDTOs = new ArrayList<TendCategoryDTO>();
        List<TendersCategoryEntity> tendersCategoryEntities = projectTendersRepository.getTendersCategoryForTime(projectId, Long.parseLong(autoId), timeStamp);
        if (tendersCategoryEntities != null && tendersCategoryEntities.size() > 0) {
            TendCategoryDTO tendCategoryDTO;
            for (TendersCategoryEntity tendersCategoryEntity : tendersCategoryEntities) {
                tendCategoryDTO = new TendCategoryDTO();
                tendCategoryDTO.setId(tendersCategoryEntity.getId());
                tendCategoryDTO.setCategoryId(tendersCategoryEntity.getCategoryId());
                tendCategoryDTO.setTendersId(tendersCategoryEntity.getTendersId());
                tendCategoryDTO.setNexusStatus(tendersCategoryEntity.getNexusStatus());
                tendCategoryDTO.setDomain(tendersCategoryEntity.getDomain());
                tendCategoryDTOs.add(tendCategoryDTO);
            }
            baseDataDTO.setId(String.valueOf(tendersCategoryEntities.get(tendersCategoryEntities.size() - 1).getId()));
            baseDataDTO.setTimeStamp(DateUtils.format(tendersCategoryEntities.get(tendersCategoryEntities.size() - 1).getModifyTime()));
        }
        baseDataDTO.setList(tendCategoryDTOs);
        return new SuccessApiResult(baseDataDTO);
    }

    @Override
    public ApiResult getTendBuild(String projectId, String timeStamp, String autoId) {
        BaseDataDTO baseDataDTO = new BaseDataDTO();
        List<TendBuildDTO> tendBuildDTOs = new ArrayList<TendBuildDTO>();
        List<TendersBuildingEntity> tendersBuildingEntities = projectTendersRepository.getTendersBuildForTime(projectId, Long.parseLong(autoId), timeStamp);
        if (tendersBuildingEntities != null && tendersBuildingEntities.size() > 0) {
            TendBuildDTO tendBuildDTO;
            for (TendersBuildingEntity tendersBuildingEntity : tendersBuildingEntities) {
                tendBuildDTO = new TendBuildDTO();
                tendBuildDTO.setId(tendersBuildingEntity.getId());
                tendBuildDTO.setBuildId(tendersBuildingEntity.getBuildingId());
                tendBuildDTO.setTendersId(tendersBuildingEntity.getTendersId());
                tendBuildDTO.setNexusStatus(tendersBuildingEntity.getTenderStatus());
                tendBuildDTO.setDutyId(projectTendersRepository.getTendersDetail(tendersBuildingEntity.getTendersId()).getSupplierId());
                tendBuildDTOs.add(tendBuildDTO);
            }
            baseDataDTO.setId(String.valueOf(tendersBuildingEntities.get(tendersBuildingEntities.size() - 1).getId()));
            baseDataDTO.setTimeStamp(DateUtils.format(tendersBuildingEntities.get(tendersBuildingEntities.size() - 1).getModifyTime()));
        }
        baseDataDTO.setList(tendBuildDTOs);
        return new SuccessApiResult(baseDataDTO);
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description: 新建标段下，获取对应总包
     */
    @Override
    public List<AgencyEntity> getAgencyList(String projectId) {
        List<AgencyEntity> list = agencyRepository.getAAgencyList(projectId);
        return list;
    }

    @Override
    public List<AuthSupplierDTO> getAuthSupplierList(String projectId) {
        List<AuthSupplierDTO> authSupplierList=new ArrayList<>();
        List<Object []> list=agencyRepository.getAuthSupplierByprojectId(projectId,"1","");
        if(list!=null){
            for(Object[] obj:list){
                AuthSupplierDTO authSupplierDTO=new AuthSupplierDTO();
                authSupplierDTO.setSupplierId(obj[0]==null ? "" : obj[0].toString());
                authSupplierDTO.setSupplierName(obj[1]==null ? "" : obj[1].toString());
                authSupplierList.add(authSupplierDTO);
            }
        }
        return authSupplierList;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description:
     */
    @Override
    public List<TreeCategoryDTO> getCategoryByParentId(String parentId) {
        List<TreeCategoryDTO> treeCategoryDTOList = new ArrayList<>();
        List<ProjectCategoryEntity> list = projectTendersRepository.getCategoryByParentId(parentId);
        if (!list.isEmpty()) {
            for (ProjectCategoryEntity projectCategoryEntity : list) {
                TreeCategoryDTO treeCategoryDTO = new TreeCategoryDTO();
                treeCategoryDTO.setId(projectCategoryEntity.getCategoryId());
                treeCategoryDTO.setpId(projectCategoryEntity.getParentId());
                treeCategoryDTO.setName(projectCategoryEntity.getCategoryName());

                //判断是否为父节点
                List<ProjectCategoryEntity> categoryEntityList = projectTendersRepository.getCategoryByParentId(projectCategoryEntity.getCategoryId());
                if (categoryEntityList.size() > 0) {
                    treeCategoryDTO.setIsParent("true");
                } else {
                    treeCategoryDTO.setIsParent("false");
                }
                treeCategoryDTOList.add(treeCategoryDTO);
            }
        }
        return treeCategoryDTOList;
    }

    /**
     * .
     *
     * @Author: shanshan
     * @Date:
     * @Description:
     */
    @Override
    public List<TreeCategoryDTO> getCategoryByModelId(String modelId) {
        List<TreeCategoryDTO> treeCategoryDTOList = new ArrayList<>();
        List<ProjectCategoryEntity> list = projectTendersRepository.getCategoryByModelId(modelId);
        if (!list.isEmpty()) {
            for (ProjectCategoryEntity projectCategoryEntity : list) {
                TreeCategoryDTO treeCategoryDTO = new TreeCategoryDTO();
                treeCategoryDTO.setId(projectCategoryEntity.getCategoryId());
                treeCategoryDTO.setName(projectCategoryEntity.getCategoryName());
                //判断是否为父节点
                List<ProjectCategoryEntity> projectCategoryEntityList = projectTendersRepository.getCategoryByParentId(projectCategoryEntity.getCategoryId());
                if (projectCategoryEntityList.size() > 0) {
                    treeCategoryDTO.setIsParent("true");
                } else {
                    treeCategoryDTO.setIsParent("false");
                }
                treeCategoryDTOList.add(treeCategoryDTO);
            }
        }
        return treeCategoryDTOList;
    }
}
