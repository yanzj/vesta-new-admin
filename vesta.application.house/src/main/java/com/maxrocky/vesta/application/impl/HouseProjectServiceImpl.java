package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.admin.HouseProjectDto;
import com.maxrocky.vesta.application.DTO.json.HI0001.ProjectReturnJsonDTO;
import com.maxrocky.vesta.application.inf.HouseProjectService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.BuildingMappingTimeEntity;
import com.maxrocky.vesta.domain.model.HouseCityEntity;
import com.maxrocky.vesta.domain.model.HouseProjectEntity;
import com.maxrocky.vesta.domain.model.RoleDataEntity;
import com.maxrocky.vesta.domain.repository.HouseCityRepository;
import com.maxrocky.vesta.domain.repository.HouseProjectRepository;
import com.maxrocky.vesta.domain.repository.RoleDataRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.*;

/**
 * Created by Tom on 2016/1/18 10:01.
 * Describe:项目Service接口实现类
 */
@Service
public class HouseProjectServiceImpl implements HouseProjectService {

    /* 项目 */
    @Autowired
    HouseProjectRepository houseProjectRepository;
    /* mapper */
    @Autowired
    MapperFacade mapper;
    @Autowired
    RoleDataRepository roleDataRepository;

    @Autowired
    HouseCityRepository houseCityRepository;

    /**
     * Code:HI0001
     * Type:UI Method
     * Describe:获取状态正常项目列表
     * CreateBy:Tom
     * CreateOn:2016-01-18 10:20:13
     */
    @Override
    public ApiResult getProjectListOfNormal() {

        List<ProjectReturnJsonDTO> projectJsonList = new ArrayList<ProjectReturnJsonDTO>();

        List<HouseProjectEntity> houseProjectEntityList = houseProjectRepository.getListOfNormal();
        for (HouseProjectEntity houseProjectEntity : houseProjectEntityList) {
            projectJsonList.add(mapper.map(houseProjectEntity, ProjectReturnJsonDTO.class));
        }

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("projectList", projectJsonList);

        return new SuccessApiResult(modelMap);
    }

    /**
     * Describe:查询项目列表
     * CreateBy:Tom
     * CreateOn:2016-04-14 01:27:59
     */
    @Override
    public List<ProjectReturnJsonDTO> getDTOList() {
        List<ProjectReturnJsonDTO> projectJsonList = new ArrayList<ProjectReturnJsonDTO>();

        List<HouseProjectEntity> houseProjectEntityList = houseProjectRepository.getListOfNormal();
        for (HouseProjectEntity houseProjectEntity : houseProjectEntityList) {
            projectJsonList.add(mapper.map(houseProjectEntity, ProjectReturnJsonDTO.class));
        }
        return projectJsonList;
    }

    @Override
    public HouseProjectDto getProjectById(String projectId) {
        HouseProjectEntity houseProjectEntity = houseProjectRepository.get(projectId);
        HouseProjectDto houseProjectDto = new HouseProjectDto();
        if (houseProjectEntity != null) {
            houseProjectDto.setId(houseProjectEntity.getId());
            houseProjectDto.setName(houseProjectEntity.getName());
            houseProjectDto.setPinyinCode(houseProjectEntity.getPinyinCode());
            houseProjectDto.setCompanyId(houseProjectEntity.getCompanyId());
            houseProjectDto.setState(houseProjectEntity.getState());
            houseProjectDto.setCreateBy(houseProjectEntity.getCreateBy());
            houseProjectDto.setCreateOn(DateUtils.format(houseProjectEntity.getCreateOn()));
            houseProjectDto.setModifyBy(houseProjectEntity.getModifyBy());
            houseProjectDto.setModifyOn(DateUtils.format(houseProjectEntity.getModifyOn()));
            houseProjectDto.setOriginName(houseProjectEntity.getOriginName());
            houseProjectDto.setMemo(houseProjectEntity.getMemo());
        }

        return houseProjectDto;
    }

    @Override
    public Map getProjects() {
        List<HouseProjectEntity> list = houseProjectRepository.getListOfNormal();
        Map<String, String> projects = new LinkedHashMap<>();
        projects.put("0", "请选择");
        if (list.size() > 0) {
            for (HouseProjectEntity houseProjectEntity : list) {
                if (!StringUtil.isEmpty(houseProjectEntity.getInstalment())) {
                    projects.put(houseProjectEntity.getPinyinCode(), houseProjectEntity.getName() + "" + houseProjectEntity.getInstalment());
                } else {
                    projects.put(houseProjectEntity.getPinyinCode(), houseProjectEntity.getName());
                }
            }
        }
        return projects;
    }

    @Override
    public Map getProjectsMagic(String cityNum) {
        List<HouseProjectEntity> list = houseProjectRepository.getProjectList(cityNum);
        Map<String, String> projects = new LinkedHashMap<>();
        projects.put("0", "请选择");
        if (list.size() > 0) {
            for (HouseProjectEntity houseProjectEntity : list) {
                if (!StringUtil.isEmpty(houseProjectEntity.getInstalment())) {
                    projects.put(houseProjectEntity.getPinyinCode(), houseProjectEntity.getName() + "" + houseProjectEntity.getInstalment());
                } else {
                    projects.put(houseProjectEntity.getPinyinCode(), houseProjectEntity.getName());
                }
            }
        }
        return projects;
    }

    @Override
    public Map getCitys(List<String> cityList) {
        List<HouseCityEntity> list = houseCityRepository.getCityListMagic(cityList);
        Map<String, String> citys = new LinkedHashMap<>();
        citys.put("", "请选择城市");
        if (list.size() > 0) {
            for (HouseCityEntity cityEntity : list) {
                citys.put(cityEntity.getCityCode(), cityEntity.getCityName());
            }
        }
        return citys;
    }


    @Override
    public Map getProjectsNum() {
        List<HouseProjectEntity> list = houseProjectRepository.getListOfNormal();
        Map<String, String> projects = new LinkedHashMap<>();
        projects.put("0", "请选择");
        if (list.size() > 0) {
            for (HouseProjectEntity houseProjectEntity : list) {
                if (!StringUtil.isEmpty(houseProjectEntity.getInstalment())) {
                    projects.put(houseProjectEntity.getPinyinCode(), houseProjectEntity.getName() + "" + houseProjectEntity.getInstalment());
                } else {
                    projects.put(houseProjectEntity.getPinyinCode(), houseProjectEntity.getName());
                }
            }
        }
        return projects;
    }

    @Override
    public Map getcity() {
        List<HouseCityEntity> list = houseProjectRepository.getCityList();
        Map<String, String> citys = new LinkedHashMap<>();
        citys.put("0", "请选择");
        if (list.size() > 0) {
            for (HouseCityEntity houseCityEntity : list) {
                citys.put(houseCityEntity.getCityCode(), houseCityEntity.getCityName());
            }
        }
        return citys;
    }

    @Override
    public List<HouseProjectDto> getProjectAll(HouseProjectDto houseProjectDto1, WebPage webPage) {
        List<HouseProjectDto> houseProjectDtos = new ArrayList<HouseProjectDto>();
        HouseProjectEntity houseProjectEntity = new HouseProjectEntity();
        houseProjectEntity.setName(houseProjectDto1.getName());
        houseProjectEntity.setOriginName(houseProjectDto1.getOriginName());
        List<HouseProjectEntity> houseProjectEntities = houseProjectRepository.getProjects(houseProjectEntity, webPage);
        if (houseProjectEntities != null) {
            HouseProjectDto houseProjectDto;
            for (HouseProjectEntity houseProjectEntity1 : houseProjectEntities) {
                houseProjectDto = new HouseProjectDto();
                houseProjectDto.setId(houseProjectEntity1.getId());
                houseProjectDto.setName(houseProjectEntity1.getName());
                houseProjectDto.setOriginName(houseProjectEntity1.getOriginName());
                houseProjectDto.setModifyOn(DateUtils.format(houseProjectEntity1.getModifyOn()));
                houseProjectDtos.add(houseProjectDto);
            }
        }
        return houseProjectDtos;
    }

    public void dumpSave(String projectId, RoleDataEntity roleDataEntity, String[] ids) {
        List<String> compairDTO1 = new ArrayList<String>();
        List<String> compairDTO2 = new ArrayList<String>();
        List<String> compairDTO3 = new ArrayList<String>();
        Iterator<String> it1;
        Iterator<String> it2;
        List<RoleDataEntity> roleDataEntities;
        roleDataEntities = roleDataRepository.getProjectRoleData(projectId, roleDataEntity.getAuthorityType(), roleDataEntity.getPermission());//查出数据库中已存在的数据
        if (roleDataEntities != null) {
            for (RoleDataEntity roleDataEntity2 : roleDataEntities) {
                compairDTO1.add(roleDataEntity2.getAuthorityId());   //将数据库中的数据存放于compairDTO1
            }
        }
        if (ids != null) {
            for (int i = 0; i < ids.length; i++) {
                compairDTO2.add(ids[i]);      //将页面传来的数据存放于compairDTO2
            }
        }
        compairDTO3.addAll(compairDTO1);
        compairDTO1.removeAll(compairDTO2);//比较后 为待删除的数据
        it1 = compairDTO1.iterator();
        while (it1.hasNext()) {
            roleDataEntity.setAuthorityId(it1.next());
            roleDataRepository.delProjectRole(roleDataEntity);         //删除权限关联数据
        }
        compairDTO2.removeAll(compairDTO3);//比较后 待新增的数据
        it2 = compairDTO2.iterator();
        while (it2.hasNext()) {
            roleDataEntity.setId(IdGen.uuid());
            roleDataEntity.setStatus("1");
//            roleDataEntity.setAuthorityType(roleDataEntity.getAuthorityType());
            roleDataEntity.setAuthorityId(it2.next());
//            roleDataEntity.setPermission(roleDataEntity.getPermission());
            roleDataRepository.addDumpRoleData(roleDataEntity);  //保存最新关系
        }
        compairDTO1.clear();
        compairDTO2.clear();
        compairDTO3.clear();
    }

    @Override
    public void updateProject(HouseProjectDto houseProjectDto) {
        HouseProjectEntity houseProjectEntity = houseProjectRepository.get(houseProjectDto.getId());
        BuildingMappingTimeEntity BuildingMapping = houseProjectRepository.getprojectbybuild(houseProjectDto.getId());
        if (houseProjectDto.getName() != null && !"".equals(houseProjectDto.getName()) && !BuildingMapping.getName().equals(houseProjectDto.getName())) {
            BuildingMapping.setName(houseProjectDto.getOriginName());
            BuildingMapping.setTimeStamp(new Date());
            houseProjectRepository.updateBUildMapingTime(BuildingMapping);
        }
//        houseProjectEntity.setName(houseProjectDto.getName());
        houseProjectEntity.setOriginName(houseProjectDto.getOriginName());
        houseProjectEntity.setMemo(houseProjectDto.getMemo());
        houseProjectEntity.setModifyOn(new Date());
        RoleDataEntity roleDataEntity = new RoleDataEntity();
        roleDataEntity.setDataType("1");
        roleDataEntity.setDataId(houseProjectDto.getId());
        roleDataEntity.setProjectNum(houseProjectEntity.getPinyinCode());

        //判断当前项目下 数据查看、工程接单、物业接单、开发阶段、派单到人 是否有添加组织机构、群组或人 并进行相关删除保存操作
        /**数据查看关联组织机构部分*/
        roleDataEntity.setAuthorityType("1");
        roleDataEntity.setPermission("1");
        String[] ids;
        if (!StringUtil.isEmpty(houseProjectDto.getViewAgencys())) {
            ids = houseProjectDto.getViewAgencys().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**数据查看关联群组部分*/
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setPermission("1");
        if (!StringUtil.isEmpty(houseProjectDto.getViewOrganizes())) {
            ids = houseProjectDto.getViewOrganizes().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**数据查看关联人部分*/
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setPermission("1");
        if (!StringUtil.isEmpty(houseProjectDto.getViewStaffs())) {
            ids = houseProjectDto.getViewStaffs().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**工程接单关联组织机构部分*/
        roleDataEntity.setAuthorityType("1");
        roleDataEntity.setPermission("2");
        if (!StringUtil.isEmpty(houseProjectDto.getPlumbingAgency())) {
            ids = houseProjectDto.getPlumbingAgency().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**工程接单关联群组部分*/
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setPermission("2");
        if (!StringUtil.isEmpty(houseProjectDto.getPlumbingOrganizes())) {
            ids = houseProjectDto.getPlumbingOrganizes().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**工程接单关联人部分*/
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setPermission("2");
        if (!StringUtil.isEmpty(houseProjectDto.getPlumbingStaffs())) {
            ids = houseProjectDto.getPlumbingStaffs().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**物业接单关联组织机构部分*/
        roleDataEntity.setAuthorityType("1");
        roleDataEntity.setPermission("100000001");
        if (!StringUtil.isEmpty(houseProjectDto.getPropertyAgency())) {
            ids = houseProjectDto.getPropertyAgency().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**物业接单关联群组部分*/
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setPermission("100000001");

        if (!StringUtil.isEmpty(houseProjectDto.getPropertyOrganizes())) {
            ids = houseProjectDto.getPropertyOrganizes().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**物业接单关联人部分*/
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setPermission("100000001");
        if (!StringUtil.isEmpty(houseProjectDto.getPropertyStaffs())) {
            ids = houseProjectDto.getPropertyStaffs().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**开发接单关联组织机构部分*/
        roleDataEntity.setAuthorityType("1");
        roleDataEntity.setPermission("100000000");
        if (!StringUtil.isEmpty(houseProjectDto.getMakesAgencys())) {
            ids = houseProjectDto.getMakesAgencys().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**开发接单关联群组部分*/
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setPermission("100000000");
        if (!StringUtil.isEmpty(houseProjectDto.getMakesOrganizes())) {
            ids = houseProjectDto.getMakesOrganizes().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**开发接单关联人部分*/
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setPermission("100000000");
        if (!StringUtil.isEmpty(houseProjectDto.getMakesStaffs())) {
            ids = houseProjectDto.getMakesStaffs().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**派单到人关联组织机构部分*/
        roleDataEntity.setAuthorityType("1");
        roleDataEntity.setPermission("3");
        if (!StringUtil.isEmpty(houseProjectDto.getDispatchAgencys())) {
            ids = houseProjectDto.getDispatchAgencys().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**派单权限关联群组部分*/
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setPermission("3");

        if (!StringUtil.isEmpty(houseProjectDto.getDispatchOrganizes())) {
            ids = houseProjectDto.getDispatchOrganizes().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**派单到人关联人部分*/
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setPermission("3");
        if (!StringUtil.isEmpty(houseProjectDto.getDispatchStaffs())) {
            ids = houseProjectDto.getDispatchStaffs().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**关单权限关联组织机构部分*/
        roleDataEntity.setAuthorityType("1");
        roleDataEntity.setPermission("4");
        if (!StringUtil.isEmpty(houseProjectDto.getCloseAgencys())) {
            ids = houseProjectDto.getCloseAgencys().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**关单权限关联群组部分*/
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setPermission("4");
        if (!StringUtil.isEmpty(houseProjectDto.getCloseOrganizes())) {
            ids = houseProjectDto.getCloseOrganizes().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**关单权限关联人部分*/
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setPermission("4");
        if (!StringUtil.isEmpty(houseProjectDto.getCloseStaffs())) {
            ids = houseProjectDto.getCloseStaffs().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**验房工程师权限关联组织机构部分*/
        roleDataEntity.setAuthorityType("1");
        roleDataEntity.setPermission("5");
        if (!StringUtil.isEmpty(houseProjectDto.getCheckAgencys())) {
            ids = houseProjectDto.getCheckAgencys().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**验房工程师权限关联群组部分*/
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setPermission("5");
        if (!StringUtil.isEmpty(houseProjectDto.getCheckOrganizes())) {
            ids = houseProjectDto.getCheckOrganizes().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**验房工程师权限关联人部分*/
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setPermission("5");
        if (!StringUtil.isEmpty(houseProjectDto.getCheckStaffs())) {
            ids = houseProjectDto.getCheckStaffs().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**工程经理权限关联组织机构部分*/
        roleDataEntity.setAuthorityType("1");
        roleDataEntity.setPermission("6");
        if (!StringUtil.isEmpty(houseProjectDto.getQualityAgencys())) {
            ids = houseProjectDto.getQualityAgencys().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**工程经理权限关联群组部分*/
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setPermission("6");
        if (!StringUtil.isEmpty(houseProjectDto.getQualityOrganizes())) {
            ids = houseProjectDto.getQualityOrganizes().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**工程经理权限关联人部分*/
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setPermission("6");
        if (!StringUtil.isEmpty(houseProjectDto.getQualityStaffs())) {
            ids = houseProjectDto.getQualityStaffs().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);
        /**乙方工程师权限关联组织机构部分*/
        roleDataEntity.setAuthorityType("1");
        roleDataEntity.setPermission("7");
        if (!StringUtil.isEmpty(houseProjectDto.getSecondAgencys())) {
            ids = houseProjectDto.getSecondAgencys().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**乙方工程师权限关联群组部分*/
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setPermission("7");
        if (!StringUtil.isEmpty(houseProjectDto.getSecondOrganizes())) {
            ids = houseProjectDto.getSecondOrganizes().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**乙方工程师权限关联人部分*/
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setPermission("7");
        if (!StringUtil.isEmpty(houseProjectDto.getSecondStaffs())) {
            ids = houseProjectDto.getSecondStaffs().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        //报事派单权限
        /**
         * 报事派单权限关联组织架构
         */
        roleDataEntity.setAuthorityType("1");
        roleDataEntity.setPermission("8");
        if (!StringUtil.isEmpty(houseProjectDto.getDispatchSheetAgencys())) {
            ids = houseProjectDto.getDispatchSheetAgencys().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);
        /**
         * 报事派单权限关联群组
         */
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setPermission("8");
        if (!StringUtil.isEmpty(houseProjectDto.getDispatchSheetOrganizes())) {
            ids = houseProjectDto.getDispatchSheetOrganizes().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);
        /**
         * 报事派单权限关联人
         */
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setPermission("8");
        if (!StringUtil.isEmpty(houseProjectDto.getDispatchSheetStaffs())) {
            ids = houseProjectDto.getDispatchSheetStaffs().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        //总部客观权限（投诉废弃权限）
        /**
         * 总部客观权限关联组织架构
         */
        roleDataEntity.setAuthorityType("1");
        roleDataEntity.setPermission("9");
        if (!StringUtil.isEmpty(houseProjectDto.getHQObjectiveAgencys())) {
            ids = houseProjectDto.getHQObjectiveAgencys().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);
        /**
         * 总部客观权限关联群组
         */
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setPermission("9");
        if (!StringUtil.isEmpty(houseProjectDto.getHQObjectiveOrganizes())) {
            ids = houseProjectDto.getHQObjectiveOrganizes().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);
        /**
         * 总部客观权限关联人
         */
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setPermission("9");
        if (!StringUtil.isEmpty(houseProjectDto.getHQObjectiveStaffs())) {
            ids = houseProjectDto.getHQObjectiveStaffs().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**
         * 报修 ：数据查看 权限关联组织架构
         */
        roleDataEntity.setAuthorityType("1");
        roleDataEntity.setPermission("10");
        if (!StringUtil.isEmpty(houseProjectDto.getRepair1ObjectiveAgencys())) {
            ids = houseProjectDto.getRepair1ObjectiveAgencys().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);
        /**
         *  报修 ：数据查看 权限关联群组
         */
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setPermission("10");
        if (!StringUtil.isEmpty(houseProjectDto.getRepair1ObjectiveOrganizes())) {
            ids = houseProjectDto.getRepair1ObjectiveOrganizes().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);
        /**
         *  报修 ：数据查看 权限关联人
         */
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setPermission("10");
        if (!StringUtil.isEmpty(houseProjectDto.getRepair1ObjectiveStaffs())) {
            ids = houseProjectDto.getRepair1ObjectiveStaffs().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);

        /**
         * 投诉 ：数据查看 权限关联组织架构
         */
        roleDataEntity.setAuthorityType("1");
        roleDataEntity.setPermission("11");
        if (!StringUtil.isEmpty(houseProjectDto.getComplaintObjectiveAgencys())) {
            ids = houseProjectDto.getComplaintObjectiveAgencys().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);
        /**
         *  投诉  ：数据查看 权限关联群组
         */
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setPermission("11");
        if (!StringUtil.isEmpty(houseProjectDto.getComplaintObjectiveOrganizes())) {
            ids = houseProjectDto.getComplaintObjectiveOrganizes().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);
        /**
         *  投诉  ：数据查看 权限关联人
         */
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setPermission("11");
        if (!StringUtil.isEmpty(houseProjectDto.getComplaintObjectiveStaffs())) {
            ids = houseProjectDto.getComplaintObjectiveStaffs().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);


        /**
         * 投诉 ：项目前台 权限关联组织架构
         */
        roleDataEntity.setAuthorityType("1");
        roleDataEntity.setPermission("12");
        if (!StringUtil.isEmpty(houseProjectDto.getReceptionObjectiveAgencys())) {
            ids = houseProjectDto.getReceptionObjectiveAgencys().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);
        /**
         *  投诉  ：项目前台 权限关联群组
         */
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setPermission("12");
        if (!StringUtil.isEmpty(houseProjectDto.getReceptionObjectiveOrganizes())) {
            ids = houseProjectDto.getReceptionObjectiveOrganizes().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);
        /**
         *  投诉  ：项目前台 权限关联人
         */
        roleDataEntity.setAuthorityType("3");
        roleDataEntity.setPermission("12");
        if (!StringUtil.isEmpty(houseProjectDto.getReceptionObjectiveStaffs())) {
            ids = houseProjectDto.getReceptionObjectiveStaffs().split(",");
        } else {
            ids = null;
        }
        dumpSave(houseProjectDto.getId(), roleDataEntity, ids);



        houseProjectRepository.updateProjectInfo(houseProjectEntity);  //更新项目信息

    }

    @Override
    public void addProject(HouseProjectDto houseProjectDto) {
        HouseProjectEntity houseProjectEntity = new HouseProjectEntity();
        houseProjectEntity.setId(IdGen.uuid());
        houseProjectEntity.setPinyinCode(IdGen.uuid());
        houseProjectEntity.setName(houseProjectDto.getName());
        houseProjectEntity.setOriginName(houseProjectDto.getOriginName());
        houseProjectEntity.setMemo(houseProjectDto.getMemo());
        houseProjectEntity.setCreateOn(new Date());
        houseProjectEntity.setModifyOn(new Date());
        houseProjectEntity.setState(HouseProjectEntity.STATE_NORMAL);
        houseProjectRepository.create(houseProjectEntity);
    }


    /**
     * 获取项目
     * param:城市Id
     * return
     */
    @Override
    public List<HouseProjectDto> getProject(String id) {
        List<HouseProjectDto> lineDTO = new ArrayList<HouseProjectDto>();
        if (!StringUtil.isEmpty(id)) {
            List<HouseProjectEntity> projectList = houseProjectRepository.getProject(id);
            if (projectList.size() > 0) {
                for (HouseProjectEntity project : projectList) {
                    HouseProjectDto line = new HouseProjectDto();
                    line.setId(project.getId());//id
                    if (!StringUtil.isEmpty(project.getInstalment())) {
                        line.setName(project.getName() + "" + project.getInstalment());//名称
                    } else {
                        line.setName(project.getName());//名称
                    }
                    lineDTO.add(line);
                }
            }
        }
        return lineDTO;
    }

    /**
     * 获取项目
     * return
     */
    @Override
    public Map getProjectInfo() {
        List<HouseProjectEntity> list = houseProjectRepository.getListOfNormal();
        Map<String, String> projects = new LinkedHashMap<>();
        projects.put("0", "-请选择项目-");
        if (list.size() > 0) {
            for (HouseProjectEntity houseProjectEntity : list) {
                if (!StringUtil.isEmpty(houseProjectEntity.getInstalment())) {
                    projects.put(houseProjectEntity.getId(), houseProjectEntity.getName() + "" + houseProjectEntity.getInstalment());
                } else {
                    projects.put(houseProjectEntity.getId(), houseProjectEntity.getName());
                }
            }
        }
        return projects;
    }

    @Override
    public HouseProjectDto getProjectByProjectCode(String pinyinCode) {

        HouseProjectEntity houseProjectEntity = houseProjectRepository.getProjectByCode(pinyinCode);

        HouseProjectDto houseProjectDto = new HouseProjectDto();
        if (houseProjectEntity != null) {
            houseProjectDto.setId(houseProjectEntity.getId());
            houseProjectDto.setName(houseProjectEntity.getName());
            houseProjectDto.setPinyinCode(houseProjectEntity.getPinyinCode());
            houseProjectDto.setCompanyId(houseProjectEntity.getCompanyId());
            houseProjectDto.setState(houseProjectEntity.getState());
            houseProjectDto.setCreateBy(houseProjectEntity.getCreateBy());
            houseProjectDto.setCreateOn(DateUtils.format(houseProjectEntity.getCreateOn()));
            houseProjectDto.setModifyBy(houseProjectEntity.getModifyBy());
            houseProjectDto.setModifyOn(DateUtils.format(houseProjectEntity.getModifyOn()));
            houseProjectDto.setOriginName(houseProjectEntity.getOriginName());
        }
        return houseProjectDto;
    }

    @Override
    public List<HouseProjectDto> getProjectsByStaffId(String staffId) {
        List<HouseProjectEntity> houseProjectEntities = roleDataRepository.getDataByStaffId(staffId);
        List<HouseProjectDto> houseProjectDtos = new ArrayList<HouseProjectDto>();
        if (houseProjectEntities != null) {
            HouseProjectDto houseProjectDto;
            for (HouseProjectEntity houseProjectEntity : houseProjectEntities) {
                houseProjectDto = new HouseProjectDto();
                houseProjectDto.setOriginName(houseProjectEntity.getOriginName());
                houseProjectDto.setName(houseProjectEntity.getName());
                houseProjectDto.setId(houseProjectEntity.getId());
                houseProjectDtos.add(houseProjectDto);
            }
        }
        return houseProjectDtos;
    }

    @Override
    public String getPrintSequence(String num, String id) {
        String[] ss = num.split("-");
        String sqcNum = "";
        if ("11".equals(id)) {//内部预验
            sqcNum = ss[ss.length - 1] + "-NY";
        }
        if ("12".equals(id)) {//工地开放
            sqcNum = ss[ss.length - 1] + "-GK";
        }
        if ("13".equals(id)) {//正式交房
            sqcNum = ss[ss.length - 1] + "-JF";
        }
        String SerialNub = roleDataRepository.getPrintSeq(sqcNum);
        if (SerialNub.length() == 1) {
            SerialNub = "000" + SerialNub;
        }
        if (SerialNub.length() == 2) {
            SerialNub = "00" + SerialNub;
        }
        if (SerialNub.length() == 3) {
            SerialNub = "0" + SerialNub;
        }
        String PrintNumber = sqcNum + "-" + DateUtils.getNow("yyyyMMdd") + "-A-" + SerialNub;
        return PrintNumber;
    }

    @Override
    public String getPrintSignSequence(String projectNum,String roomNum, String id,String printDate) {
        String[] ss = projectNum.split("-");
        String sqcNum = "";
        if ("11".equals(id)) {//内部预验
            sqcNum = ss[ss.length - 1] + "-NY";
        }
        if ("12".equals(id)) {//工地开放
            sqcNum = ss[ss.length - 1] + "-GK";
        }
        if ("13".equals(id)) {//正式交房
            sqcNum = ss[ss.length - 1] + "-JF";
        }
        //流水号
        String SerialNub = roleDataRepository.getPrintSeq(sqcNum);
        if (SerialNub.length() == 1) {
            SerialNub = "000" + SerialNub;
        }
        if (SerialNub.length() == 2) {
            SerialNub = "00" + SerialNub;
        }
        if (SerialNub.length() == 3) {
            SerialNub = "0" + SerialNub;
        }
        String[] rooms = roomNum.split("-");
//        String PrintNumber = rooms[1].toString()+ "-" +rooms[2].toString() + "-" + DateUtils.getNow("yyyyMMdd")  + "-A-" + SerialNub;
        String PrintNumber = rooms[1].toString()+ "-" +rooms[2].toString() + "-" + printDate  + "-A-" + SerialNub;
        return PrintNumber;
    }

    @Override
    public List<String> getProjectNumsByProjectIds(List<String> projectIds){
        return houseProjectRepository.getProjectNumsByProjectIds(projectIds);
    }

    @Override
    public Map<String,String> getProNumsByProIds(List<String> projectIds){
        List<Object[]> list = houseProjectRepository.getProNumsByProIds(projectIds);
        Map<String,String> map = new HashMap<>();
        map.put("","请选择");
        if(list != null && !list.isEmpty()){
            for (int i = 0; i < list.size(); i++) {
                map.put((list.get(i)[0] == null ? "" : list.get(i)[0].toString()),(list.get(i)[1] == null ? "" : list.get(i)[1].toString()));
            }
        }
        return map;
    }
}
