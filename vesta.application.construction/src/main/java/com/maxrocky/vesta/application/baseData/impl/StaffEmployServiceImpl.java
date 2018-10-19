package com.maxrocky.vesta.application.baseData.impl;

import com.maxrocky.vesta.application.baseData.JsonDTO.BaseDataDTO;
import com.maxrocky.vesta.application.baseData.JsonDTO.OwnerPeopleDTO;
import com.maxrocky.vesta.application.baseData.adminDTO.ProjectEmployDTO;
import com.maxrocky.vesta.application.baseData.inf.StaffEmployService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.BaseProjectPeopleEntity;
import com.maxrocky.vesta.domain.baseData.model.BuildingSupplierEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectProjectEntity;
import com.maxrocky.vesta.domain.baseData.model.ProjectStaffEmployEntity;
import com.maxrocky.vesta.domain.baseData.repository.*;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.AgencyRepository;
import com.maxrocky.vesta.domain.repository.SupplierAgencyRepository;
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
 * Created by chen on 2016/10/26.
 */
@Service
public class StaffEmployServiceImpl implements StaffEmployService {
    @Autowired
    StaffEmployRepository staffEmployRepository;
    @Autowired
    ProjectProjectRepository projectProjectRepository;
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    SupplierAgencyRepository supplierAgencyRepository;
    @Autowired
    ProjectPeopleRepository projectPeopleRepository;
    @Autowired
    ProjectBuildingRepository projectBuildingRepository;
    @Autowired
    ProjectCategoryRepository projectCategoryRepository;

    @Override
    public List<ProjectEmployDTO> getEmploys(ProjectEmployDTO projectEmployDTO1, WebPage webPage) {

        ProjectProjectEntity projectProjectEntity = projectProjectRepository.getProjectProjectDetail(projectEmployDTO1.getProjectId());
        List<ProjectEmployDTO> projectEmployDTOs = new ArrayList<ProjectEmployDTO>();
        ProjectStaffEmployEntity projectStaffEmployEntity = new ProjectStaffEmployEntity();
        projectStaffEmployEntity.setProjectId(projectEmployDTO1.getProjectId());
        projectStaffEmployEntity.setDataId(projectEmployDTO1.getsName());
        List<AgencyEntity> agencyEntities = staffEmployRepository.getEmploys(projectStaffEmployEntity,webPage);
        if(agencyEntities!=null){
            ProjectEmployDTO projectEmployDTO;
            for(AgencyEntity agencyEntity:agencyEntities){
                projectEmployDTO = new ProjectEmployDTO();
                projectEmployDTO.setsId(agencyEntity.getAgencyId());
                projectEmployDTO.setsName(agencyEntity.getAgencyName());
                projectEmployDTO.setsNature(agencyEntity.getNature());
                projectEmployDTO.setProjectName(projectProjectEntity.getName());
                projectEmployDTOs.add(projectEmployDTO);
            }
        }
        return projectEmployDTOs;
    }

    @Override
    public List<ProjectEmployDTO> getAuthEmploys(ProjectEmployDTO projectEmployDTO1, WebPage webPage) {
        AuthAgencyESEntity AuthAgencyESEntity=projectProjectRepository.getAuthAgencyES(projectEmployDTO1.getProjectId(),"100000002");
        List<ProjectEmployDTO> projectEmployDTOs = new ArrayList<ProjectEmployDTO>();
        List<Object []> list=staffEmployRepository.getAuthOuterAgencyProject(projectEmployDTO1.getProjectId(),projectEmployDTO1.getsName(),webPage);
        if(list!=null){
            for(Object[] obj:list){
                ProjectEmployDTO projectEmployDTO = new ProjectEmployDTO();
                projectEmployDTO.setsId(obj[0] == null ? "" : obj[0].toString());
                projectEmployDTO.setsName(obj[1] == null ? "" : obj[1].toString());
                projectEmployDTO.setsNature(obj[2] == null ? "" : obj[2].toString());
                projectEmployDTO.setProjectName(AuthAgencyESEntity.getAgencyName());
                projectEmployDTOs.add(projectEmployDTO);
            }
        }
        return projectEmployDTOs;
    }

    @Override
    public ProjectEmployDTO getEmployDetail(String employId) {
        AgencyEntity agencyEntity = staffEmployRepository.getEmployDetail(employId);
        ProjectEmployDTO projectEmployDTO = new ProjectEmployDTO();
        projectEmployDTO.setsId(agencyEntity.getAgencyId());
        projectEmployDTO.setsNature(agencyEntity.getNature());
        projectEmployDTO.setsName(agencyEntity.getAgencyName());
        projectEmployDTO.setsStatus(agencyEntity.getStatus());
        projectEmployDTO.setsMemo(agencyEntity.getAgencyDesc());
        projectEmployDTO.setAbbreviationName(agencyEntity.getAbbreviationName());
        projectEmployDTO.setModifyTime(DateUtils.format(agencyEntity.getModifyTime()));
        SupplierAgencyMapEntity supplierAgencyMapEntity = supplierAgencyRepository.getSupplierAgencys(employId);
        if(supplierAgencyMapEntity!=null){
            projectEmployDTO.setsSupplierId(supplierAgencyMapEntity.getSupplierId());
        }
        return projectEmployDTO;
    }

    @Override
    public ProjectEmployDTO getAuthEmployDetail(String employId,String projectId) {
        ProjectEmployDTO projectEmployDTO = new ProjectEmployDTO();
        BaseProjectPeopleEntity baseProjectEntity=staffEmployRepository.getBaseProjectByProjectId(projectId,employId);
        if(baseProjectEntity!=null){
            AuthOuterAgencyEntity authOuterAgency=staffEmployRepository.getAuthOuterAgency(employId);
            projectEmployDTO.setsId(authOuterAgency.getAgencyId());
            projectEmployDTO.setsNature(baseProjectEntity.getSupplierType());
            projectEmployDTO.setsName(authOuterAgency.getAgencyName());
            projectEmployDTO.setsStatus(baseProjectEntity.getStatus());
            projectEmployDTO.setsMemo(authOuterAgency.getAgencyDesc());
            projectEmployDTO.setAbbreviationName(baseProjectEntity.getAbbreviationName());
            projectEmployDTO.setModifyTime(DateUtils.format(baseProjectEntity.getModifyTime()));
        }
        return projectEmployDTO;
    }

    @Override
    public void addEmploy(ProjectEmployDTO projectEmployDTO) {
        AgencyEntity agencyEntity = new AgencyEntity();
        agencyEntity.setAgencyId(IdGen.uuid());
        agencyEntity.setIsRoot("0");
        agencyEntity.setAgencyType("4");
        agencyEntity.setNature(projectEmployDTO.getsNature());
        agencyEntity.setAgencyDesc(projectEmployDTO.getsMemo());
        agencyEntity.setAgencyName(projectEmployDTO.getsName());
        agencyEntity.setStatus(projectEmployDTO.getsStatus());
        agencyEntity.setCreateTime(new Date());
        agencyEntity.setModifyTime(new Date());
        ProjectStaffEmployEntity projectStaffEmployEntity = new ProjectStaffEmployEntity();
//        projectStaffEmployEntity.setId(IdGen.uuid());

        if("1".equals(projectEmployDTO.getsStatus())){
            projectStaffEmployEntity.setDataId(agencyEntity.getAgencyId());
            projectStaffEmployEntity.setProjectId(projectEmployDTO.getProjectId());
            projectStaffEmployEntity.setDataType("1");
            projectStaffEmployEntity.setStatus("1");
            projectStaffEmployEntity.setModifyTime(new Date());
            staffEmployRepository.addProjectStaffEmploy(projectStaffEmployEntity);  //保存责任单位与项目的关系
            if(!StringUtil.isEmpty(projectEmployDTO.getBuildId())){
                String[] ids = projectEmployDTO.getBuildId().split(",");
                BuildingSupplierEntity buildingSupplierEntity;
                for(String buildId:ids){
                    buildingSupplierEntity = new BuildingSupplierEntity();
                    buildingSupplierEntity.setAgencyId(agencyEntity.getAgencyId());
                    buildingSupplierEntity.setBuildId(buildId);
                    buildingSupplierEntity.setStatus("1");
                    buildingSupplierEntity.setModifyTime(new Date());
                    projectBuildingRepository.addSupplierBuild(buildingSupplierEntity);
                }
            }
        }

        AgencyEntity agencyEntity1;
        if(!"3".equals(projectEmployDTO.getsNature())){
            agencyEntity.setNature(projectEmployDTO.getsNature());
            agencyEntity1 = agencyRepository.getAgencyDetail("301");   //责任单位根目录主键暂定为301
            if(!StringUtil.isEmpty(projectEmployDTO.getsSupplierId())&&"1".equals(projectEmployDTO.getsStatus())){
                SupplierAgencyMapEntity supplierAgencyMapEntity = new SupplierAgencyMapEntity();
                supplierAgencyMapEntity.setMapId(IdGen.uuid());
                supplierAgencyMapEntity.setAgencyId(agencyEntity.getAgencyId());
                supplierAgencyMapEntity.setSupplierId(projectEmployDTO.getsSupplierId());
                supplierAgencyMapEntity.setStatus("1");
                supplierAgencyMapEntity.setModifyTime(new Date());
                supplierAgencyRepository.addSupplierAgency(supplierAgencyMapEntity);     //保存责任单位与原供应商的对应关系
            }
        }else{
            agencyEntity.setAgencyType("5");
            agencyEntity1 = agencyRepository.getAgencyDetail("304");   //监理根目录主键暂定为304
        }
        agencyEntity.setParentId(agencyEntity1.getAgencyId());
        agencyEntity.setAgencyPath(agencyEntity1.getAgencyPath() + "/" + agencyEntity.getAgencyId());
        agencyEntity.setLevel(agencyEntity1.getLevel()+1);
        agencyRepository.addAgency(agencyEntity);  //保存责任单位
    }

    @Override
    public void addAuthEmploy(ProjectEmployDTO projectEmployDTO,UserInformationEntity user) {
        if(!StringUtil.isEmpty(projectEmployDTO.getsName())){
            AuthOuterAgencyEntity authOuterAgency=staffEmployRepository.getAuthOuterAgencyByName(projectEmployDTO.getsName());
            if(authOuterAgency!=null){
                //如果存在外部组织机构  则新增组织机构和项目关联关系
                //组织关联关系  责任单位+项目+人员
                BaseProjectPeopleEntity baseProjectPeopleEntity=new BaseProjectPeopleEntity();
                baseProjectPeopleEntity.setSupplierId(authOuterAgency.getAgencyId());
                baseProjectPeopleEntity.setSupplierName(authOuterAgency.getAgencyName());
                baseProjectPeopleEntity.setAbbreviationName(projectEmployDTO.getAbbreviationName());
                baseProjectPeopleEntity.setStatus(projectEmployDTO.getsStatus());
                baseProjectPeopleEntity.setSupplierType(projectEmployDTO.getsNature());
                baseProjectPeopleEntity.setProjectId(projectEmployDTO.getProjectId());
                baseProjectPeopleEntity.setProjectName(projectEmployDTO.getProjectName());
                baseProjectPeopleEntity.setModifyTime(new Date());
                baseProjectPeopleEntity.setPeopleId("0");
                baseProjectPeopleEntity.setPeopleName("");
                staffEmployRepository.addbaseProjectPeople(baseProjectPeopleEntity);

                if(!StringUtil.isEmpty(projectEmployDTO.getBuildId())){
                    String[] ids = projectEmployDTO.getBuildId().split(",");

                    BuildingSupplierEntity buildingSupplierEntity;
                    for(String buildId:ids){
//                        buildingSupplierEntity = new BuildingSupplierEntity();
//                        buildingSupplierEntity.setAgencyId(authOuterAgency.getAgencyId());
//                        buildingSupplierEntity.setBuildId(buildId);
//                        buildingSupplierEntity.setStatus("1");
//                        buildingSupplierEntity.setModifyTime(new Date());
//                        projectBuildingRepository.addSupplierBuild(buildingSupplierEntity);
                        buildingSupplierEntity = new BuildingSupplierEntity();
                        buildingSupplierEntity.setAgencyId(authOuterAgency.getAgencyId());
                        buildingSupplierEntity.setBuildId(buildId);
                        projectBuildingRepository.dumpAddSupplierBuild(buildingSupplierEntity);  //保存最新关系
                    }
                }
            }else{
                AuthOuterAgencyEntity authOuterAgencyEntity=new AuthOuterAgencyEntity();
                authOuterAgencyEntity.setAgencyId(IdGen.uuid());
                authOuterAgencyEntity.setAgencyName(projectEmployDTO.getsName());
                authOuterAgencyEntity.setParentId("03d3df6a599747ef9bfa4332c0f919b6");//上一级别id
                authOuterAgencyEntity.setAgencyPath("/1/03d3df6a599747ef9bfa4332c0f919b6/"+authOuterAgencyEntity.getAgencyId());//路径
                authOuterAgencyEntity.setCreateBy(user.getStaffId());
                authOuterAgencyEntity.setCreateTime(new Date());
                authOuterAgencyEntity.setModifyBy(user.getStaffId());
                authOuterAgencyEntity.setModifyTime(new Date());
                authOuterAgencyEntity.setLevel(3);
                authOuterAgencyEntity.setOutEmploy("1");
                authOuterAgencyEntity.setStatus("1");
                authOuterAgencyEntity.setIsTemporary("0");
                staffEmployRepository.addAgency(authOuterAgencyEntity);
                //组织关联关系  责任单位+项目+人员
                BaseProjectPeopleEntity baseProjectPeopleEntity=new BaseProjectPeopleEntity();
                baseProjectPeopleEntity.setSupplierId(authOuterAgencyEntity.getAgencyId());
                baseProjectPeopleEntity.setSupplierName(authOuterAgencyEntity.getAgencyName());
                baseProjectPeopleEntity.setAbbreviationName(projectEmployDTO.getAbbreviationName());
                baseProjectPeopleEntity.setSupplierType(projectEmployDTO.getsNature());
                baseProjectPeopleEntity.setStatus(projectEmployDTO.getsStatus());
                baseProjectPeopleEntity.setProjectId(projectEmployDTO.getProjectId());
                baseProjectPeopleEntity.setProjectName(projectEmployDTO.getProjectName());
                baseProjectPeopleEntity.setModifyTime(new Date());
                baseProjectPeopleEntity.setPeopleId("0");
                baseProjectPeopleEntity.setPeopleName("");
                staffEmployRepository.addbaseProjectPeople(baseProjectPeopleEntity);

                if(!StringUtil.isEmpty(projectEmployDTO.getBuildId())){
                    String[] ids = projectEmployDTO.getBuildId().split(",");
                    BuildingSupplierEntity buildingSupplierEntity;
                    for(String buildId:ids){
                        buildingSupplierEntity = new BuildingSupplierEntity();
                        buildingSupplierEntity.setAgencyId(authOuterAgencyEntity.getAgencyId());
                        buildingSupplierEntity.setBuildId(buildId);
                        buildingSupplierEntity.setStatus("1");
                        buildingSupplierEntity.setModifyTime(new Date());
                        projectBuildingRepository.addSupplierBuild(buildingSupplierEntity);
                    }
                }

            }
        }
    }

    @Override
    public void updateEmploy(ProjectEmployDTO projectEmployDTO) {
        AgencyEntity agencyEntity = agencyRepository.getAgencyDetail(projectEmployDTO.getsId());
        agencyEntity.setAgencyDesc(projectEmployDTO.getsMemo());
        agencyEntity.setModifyTime(new Date());
        agencyEntity.setStatus(projectEmployDTO.getsStatus());
        if(!projectEmployDTO.getsName().equals(agencyEntity.getAgencyName())){  //如果责任单位改名了则维护基础表对应名称
            projectPeopleRepository.updateProjectPeople(projectEmployDTO.getsName(), projectEmployDTO.getsId());
        }
        if(!"1".equals(projectEmployDTO.getsStatus())){ //如果删除了当前责任单位 则需要维护基础数据表
            projectPeopleRepository.delProjectPeopleByDutyId(agencyEntity.getAgencyId());
            projectBuildingRepository.delSupplierBuildByDutyId(agencyEntity.getAgencyId()); //删除责任单位与楼栋关系
            projectCategoryRepository.delSupplierCategory(agencyEntity.getAgencyId());     //删除责任单位与检查项关系
        }else{
            String[] ids;
            List<String> compairDTO1 = new ArrayList<String>();
            List<String> compairDTO2 = new ArrayList<String>();
            List<String> compairDTO3 = new ArrayList<String>();
            Iterator<String> it1;
            Iterator<String> it2;
            List<String> buildIds = projectBuildingRepository.getBuildSupplier(agencyEntity.getAgencyId());//已存在的关系数据
            if(buildIds!=null){
                for(String buildId:buildIds){
                    compairDTO1.add(buildId);   //将数据库中的数据存放于compairDTO1
                }
            }
            if(!StringUtil.isEmpty(projectEmployDTO.getBuildId())){
                ids = projectEmployDTO.getBuildId().split(",");
                for (int i = 0; i <ids.length;i++){
                    compairDTO2.add(ids[i]);      //将页面传来的数据存放于compairDTO2
                }
            }
            compairDTO3.addAll(compairDTO1);
            compairDTO1.removeAll(compairDTO2);//比较后 为待删除的数据
            it1 = compairDTO1.iterator();
            BuildingSupplierEntity buildingSupplierEntity;
            while (it1.hasNext()){
                buildingSupplierEntity = new BuildingSupplierEntity();
                buildingSupplierEntity.setBuildId(it1.next());
                buildingSupplierEntity.setAgencyId(agencyEntity.getAgencyId());
                projectBuildingRepository.deleteSupplierBuild(buildingSupplierEntity);//删除权限关联数据
            }
            compairDTO2.removeAll(compairDTO3);//比较后 待新增的数据
            it2 = compairDTO2.iterator();
            while (it2.hasNext()){
                buildingSupplierEntity = new BuildingSupplierEntity();
                buildingSupplierEntity.setAgencyId(agencyEntity.getAgencyId());
                buildingSupplierEntity.setBuildId(it2.next());
                projectBuildingRepository.dumpAddSupplierBuild(buildingSupplierEntity);  //保存最新关系
            }
        }
        if(!"3".equals(projectEmployDTO.getsNature())){
            agencyEntity.setNature(projectEmployDTO.getsNature());
            agencyEntity.setAgencyType("4");
            AgencyEntity agencyEntity1 = agencyRepository.getAgencyDetail("301");   //责任单位目录主键暂定为301
            agencyEntity.setParentId(agencyEntity1.getAgencyId());
            agencyEntity.setAgencyPath(agencyEntity1.getAgencyPath() + "/" + agencyEntity.getAgencyId());
            agencyEntity.setLevel(agencyEntity1.getLevel()+1);
            if(!StringUtil.isEmpty(projectEmployDTO.getsSupplierId())){
                SupplierAgencyMapEntity supplierAgencyMapEntity = supplierAgencyRepository.getSupplierAgencys(projectEmployDTO.getsId());
                if(supplierAgencyMapEntity!=null){
                    supplierAgencyMapEntity.setSupplierId(projectEmployDTO.getsSupplierId());
                    supplierAgencyMapEntity.setStatus("1");
                    supplierAgencyMapEntity.setModifyTime(new Date());
                    supplierAgencyRepository.updateSupplierAgency(supplierAgencyMapEntity);     //保存责任单位与原供应商的对应关系
                }else{
                    SupplierAgencyMapEntity supplierAgencyMapEntity1 = new SupplierAgencyMapEntity();
                    supplierAgencyMapEntity1.setMapId(IdGen.uuid());
                    supplierAgencyMapEntity1.setAgencyId(agencyEntity.getAgencyId());
                    supplierAgencyMapEntity1.setSupplierId(projectEmployDTO.getsSupplierId());
                    supplierAgencyMapEntity1.setStatus("1");
                    supplierAgencyMapEntity1.setModifyTime(new Date());
                    supplierAgencyRepository.addSupplierAgency(supplierAgencyMapEntity1);     //保存责任单位与原供应商的对应关系
                }
            }
        }else{
            agencyEntity.setNature(projectEmployDTO.getsNature());
            agencyEntity.setAgencyType("5");
            AgencyEntity agencyEntity1 = agencyRepository.getAgencyDetail("304");   //监理根目录主键暂定为304
            agencyEntity.setParentId(agencyEntity1.getAgencyId());
            agencyEntity.setAgencyPath(agencyEntity1.getAgencyPath() + "/" + agencyEntity.getAgencyId());
            agencyEntity.setLevel(agencyEntity1.getLevel()+1);
        }
        agencyEntity.setAgencyName(projectEmployDTO.getsName());
        agencyRepository.updateAgency(agencyEntity);
    }

    @Override
    public void updateAuthEmploy(ProjectEmployDTO projectEmployDTO, UserInformationEntity userInformationEntity) {
        AuthOuterAgencyEntity authOuterAgency=staffEmployRepository.getAuthOuterAgency(projectEmployDTO.getsId());
        if(authOuterAgency!=null){
            authOuterAgency.setAgencyName(projectEmployDTO.getsName());
            authOuterAgency.setModifyTime(new Date());
            authOuterAgency.setModifyBy(userInformationEntity.getStaffId());
            staffEmployRepository.addAgency(authOuterAgency);
            if(!"1".equals(projectEmployDTO.getsStatus())){
                //删除关联关系
                staffEmployRepository.deleteBaseProjectPeople(projectEmployDTO.getProjectId(),authOuterAgency.getAgencyId(),"","","","","0");
//                projectBuildingRepository.delSupplierBuildByDutyId(authOuterAgency.getAgencyId()); //删除责任单位与楼栋关系
                projectBuildingRepository.delProjectSupplierBuildByDutyId(authOuterAgency.getAgencyId(),projectEmployDTO.getProjectId()); //删除该项目下责任单位与楼栋关系
                projectCategoryRepository.delSupplierCategory(authOuterAgency.getAgencyId());     //删除责任单位与检查项关系
           }else{
                staffEmployRepository.deleteBaseProjectPeople(projectEmployDTO.getProjectId(),authOuterAgency.getAgencyId(),"",projectEmployDTO.getsName(),projectEmployDTO.getsNature(),projectEmployDTO.getAbbreviationName(),"1");

                String[] ids;
                List<String> compairDTO1 = new ArrayList<String>();
                List<String> compairDTO2 = new ArrayList<String>();
                List<String> compairDTO3 = new ArrayList<String>();
                Iterator<String> it1;
                Iterator<String> it2;
//                List<String> buildIds = projectBuildingRepository.getBuildSupplier(authOuterAgency.getAgencyId());//已存在的关系数据
                List<String> buildIds = projectBuildingRepository.getProjectBuildSupplier(authOuterAgency.getAgencyId(),projectEmployDTO.getProjectId());//已存在的关系数据

                if(buildIds!=null){
                    for(String buildId:buildIds){
                        compairDTO1.add(buildId);   //将数据库中的数据存放于compairDTO1
                    }
                }
                if(!StringUtil.isEmpty(projectEmployDTO.getBuildId())){
                    ids = projectEmployDTO.getBuildId().split(",");
                    for (int i = 0; i <ids.length;i++){
                        compairDTO2.add(ids[i]);      //将页面传来的数据存放于compairDTO2
                    }
                }
                compairDTO3.addAll(compairDTO1);
                compairDTO1.removeAll(compairDTO2);//比较后 为待删除的数据
                it1 = compairDTO1.iterator();
                BuildingSupplierEntity buildingSupplierEntity;
                while (it1.hasNext()){
                    buildingSupplierEntity = new BuildingSupplierEntity();
                    buildingSupplierEntity.setBuildId(it1.next());
                    buildingSupplierEntity.setAgencyId(authOuterAgency.getAgencyId());
                    projectBuildingRepository.deleteSupplierBuild(buildingSupplierEntity);//删除权限关联数据
                }
                compairDTO2.removeAll(compairDTO3);//比较后 待新增的数据
                it2 = compairDTO2.iterator();
                while (it2.hasNext()){
                    buildingSupplierEntity = new BuildingSupplierEntity();
                    buildingSupplierEntity.setAgencyId(authOuterAgency.getAgencyId());
                    buildingSupplierEntity.setBuildId(it2.next());
                    projectBuildingRepository.dumpAddSupplierBuild(buildingSupplierEntity);  //保存最新关系
                }
            }


        }

    }

    @Override
    public ApiResult getProjectOwnerPeopleForTime(String projectId, String timeStamp, String num) {
        List<Object[]> objects = staffEmployRepository.getOwnerProjectRole(projectId,timeStamp,Integer.parseInt(num));
        List<OwnerPeopleDTO> ownerPeopleDTOs = new ArrayList<OwnerPeopleDTO>();
        BaseDataDTO baseDataDTO = new BaseDataDTO();
        if(objects!=null&&objects.size()>0){
            OwnerPeopleDTO ownerPeopleDTO;
            for(Object[] object:objects){
                ownerPeopleDTO = new OwnerPeopleDTO();
                ownerPeopleDTO.setPeopleId(object[0].toString());
                ownerPeopleDTO.setPeopleName(object[1].toString());
                ownerPeopleDTO.setProjectId(object[2].toString());
                ownerPeopleDTO.setProjectName(object[3].toString());
                ownerPeopleDTO.setStatus(object[4].toString());
                ownerPeopleDTO.setUnionId(object[0].toString()+object[2].toString());
                ownerPeopleDTOs.add(ownerPeopleDTO);
            }
            baseDataDTO.setTimeStamp(DateUtils.format((Date)objects.get(objects.size()-1)[5]));
        }
        baseDataDTO.setList(ownerPeopleDTOs);
        return new SuccessApiResult(baseDataDTO);
    }

    @Override
    public List<String> getAllSuoolierNameList(String projectId) {
        List<String> nameList=new ArrayList<>();
        //查询是否有关联关系数据
        if(!StringUtil.isEmpty(projectId)){
            List<String> agencyIdList=staffEmployRepository.getIdListByProjectId(projectId);
            nameList=staffEmployRepository.getNameListByProjectId(agencyIdList);
        }
        return nameList;
    }

}
