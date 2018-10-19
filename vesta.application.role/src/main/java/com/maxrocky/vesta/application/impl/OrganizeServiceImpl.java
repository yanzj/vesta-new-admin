package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.dto.adminDTO.OrganizeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.OrganizeProjectDTO;
import com.maxrocky.vesta.application.dto.adminDTO.StaffNameDTO;
import com.maxrocky.vesta.application.inf.OrganizeService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.AgencyRepository;
import com.maxrocky.vesta.domain.repository.OrganizeRepository;
import com.maxrocky.vesta.domain.repository.OrganizeUserRepository;
import com.maxrocky.vesta.domain.repository.RoleDataRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import com.sun.xml.xsom.impl.scd.Iterators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by chen on 2016/6/2.
 */
@Service
public class OrganizeServiceImpl implements OrganizeService {
    @Autowired
    OrganizeRepository organizeRepository;
    @Autowired
    OrganizeUserRepository organizeUserRepository;
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    RoleDataRepository roleDataRepository;

    @Override
    public OrganizeDTO getOrganizeDetail(String organizeId) {
        OrganizeDTO organizeDTO = new OrganizeDTO();
        OrganizeEntity organizeEntity = organizeRepository.getOrganizeDetail(organizeId);
        organizeDTO.setOrganizeId(organizeEntity.getOrganizeId());
        organizeDTO.setCrmName(organizeEntity.getCrmName());
        organizeDTO.setCrmId(organizeEntity.getCrmId());
        organizeDTO.setOrganizeName(organizeEntity.getOrganizeName());
        organizeDTO.setMemo(organizeEntity.getMemo());
        organizeDTO.setStatus(organizeEntity.getOrganizeStatus());
        organizeDTO.setOrderNum(organizeEntity.getOrderNum());
        organizeDTO.setModifyTime(DateUtils.format(organizeEntity.getModifyTime()));
        List<UserPropertyStaffEntity> userPropertyStaffEntities = organizeUserRepository.getStaffsByOrganizeId(organizeId);
        List<HouseProjectEntity> houseProjectEntities = roleDataRepository.getProjectByOrganizeId(organizeId);
        List<StaffNameDTO> staffNameDTOList = new ArrayList<StaffNameDTO>();
        List<OrganizeProjectDTO> organizeProjectDTOs = new ArrayList<OrganizeProjectDTO>();
        if(userPropertyStaffEntities!=null){
            StaffNameDTO staffNameDTO;
            for(UserPropertyStaffEntity userPropertyStaffEntity:userPropertyStaffEntities){
                staffNameDTO = new StaffNameDTO();
                staffNameDTO.setStaffId(userPropertyStaffEntity.getStaffId());
                staffNameDTO.setStaffName(userPropertyStaffEntity.getStaffName() + "（" + userPropertyStaffEntity.getUserName() + "）");
                staffNameDTOList.add(staffNameDTO);
            }
        }
        if(houseProjectEntities!=null){
            OrganizeProjectDTO organizeProjectDTO;
            for(HouseProjectEntity houseProjectEntity:houseProjectEntities){
                organizeProjectDTO = new OrganizeProjectDTO();
                organizeProjectDTO.setProjectId(houseProjectEntity.getId());
                organizeProjectDTO.setProjectName(houseProjectEntity.getName());
                organizeProjectDTOs.add(organizeProjectDTO);
            }
        }
        organizeDTO.setStaffDTOList(staffNameDTOList);
        organizeDTO.setProjectOrganizeList(organizeProjectDTOs);
        return organizeDTO;
    }

    @Override
    public List<OrganizeDTO> getOrganizeList() {
        List<OrganizeDTO> organizeDTOs = new ArrayList<OrganizeDTO>();
        List<OrganizeEntity> organizeEntities = organizeRepository.getOrganizeList();
        if(organizeEntities!=null){
            OrganizeDTO organizeDTO;
            for(OrganizeEntity organizeEntity:organizeEntities){
                organizeDTO = new OrganizeDTO();
                organizeDTO.setCrmName(organizeEntity.getCrmName());
                organizeDTO.setOrganizeName(organizeEntity.getOrganizeName());
                organizeDTO.setOrganizeId(organizeEntity.getOrganizeId());
                organizeDTO.setMemo(organizeEntity.getMemo());
                organizeDTOs.add(organizeDTO);
            }
        }
        return organizeDTOs;
    }

    @Override
    public List<OrganizeDTO> getOrganizeInList(String projectId) {
        List<OrganizeDTO> organizeDTOs = new ArrayList<OrganizeDTO>();
        List<OrganizeEntity> organizeEntities = organizeRepository.getOrganizeInProject(projectId);
        if(organizeEntities!=null){
            OrganizeDTO organizeDTO;
            for(OrganizeEntity organizeEntity:organizeEntities){
                organizeDTO = new OrganizeDTO();
                organizeDTO.setCrmName(organizeEntity.getCrmName());
                organizeDTO.setOrganizeName(organizeEntity.getOrganizeName());
                organizeDTO.setOrganizeId(organizeEntity.getOrganizeId());
                organizeDTO.setMemo(organizeEntity.getMemo());
                organizeDTOs.add(organizeDTO);
            }
        }
        return organizeDTOs;
    }

    @Override
    public List<OrganizeDTO> getOrganizeOutList(String projectId) {
        List<OrganizeDTO> organizeDTOs = new ArrayList<OrganizeDTO>();
        List<OrganizeEntity> organizeEntities = organizeRepository.getOrganizeOutProject(projectId);
        if(organizeEntities!=null){
            OrganizeDTO organizeDTO;
            for(OrganizeEntity organizeEntity:organizeEntities){
                organizeDTO = new OrganizeDTO();
                organizeDTO.setCrmName(organizeEntity.getCrmName());
                organizeDTO.setOrganizeName(organizeEntity.getOrganizeName());
                organizeDTO.setOrganizeId(organizeEntity.getOrganizeId());
                organizeDTO.setMemo(organizeEntity.getMemo());
                organizeDTOs.add(organizeDTO);
            }
        }
        return organizeDTOs;
    }

    @Override
    public List<OrganizeDTO> getOrganizeInRole(String roleSetId) {
        List<OrganizeDTO> organizeDTOs = new ArrayList<OrganizeDTO>();
        List<OrganizeEntity> organizeEntities = organizeRepository.getOrganizeInRoleSet(roleSetId);
        if(organizeEntities!=null){
            OrganizeDTO organizeDTO;
            for(OrganizeEntity organizeEntity:organizeEntities){
                organizeDTO = new OrganizeDTO();
                organizeDTO.setCrmName(organizeEntity.getCrmName());
                organizeDTO.setOrganizeName(organizeEntity.getOrganizeName());
                organizeDTO.setOrganizeId(organizeEntity.getOrganizeId());
                organizeDTO.setMemo(organizeEntity.getMemo());
                organizeDTOs.add(organizeDTO);
            }
        }
        return organizeDTOs;
    }

    @Override
    public List<OrganizeDTO> getOrganizeOutRole(String roleSetId) {
        List<OrganizeDTO> organizeDTOs = new ArrayList<OrganizeDTO>();
        List<OrganizeEntity> organizeEntities = organizeRepository.getOrganizeOutRoleSet(roleSetId);
        if(organizeEntities!=null){
            OrganizeDTO organizeDTO;
            for(OrganizeEntity organizeEntity:organizeEntities){
                organizeDTO = new OrganizeDTO();
                organizeDTO.setCrmName(organizeEntity.getCrmName());
                organizeDTO.setOrganizeName(organizeEntity.getOrganizeName());
                organizeDTO.setOrganizeId(organizeEntity.getOrganizeId());
                organizeDTO.setMemo(organizeEntity.getMemo());
                organizeDTOs.add(organizeDTO);
            }
        }
        return organizeDTOs;
    }

    @Override
    public List<OrganizeDTO> getOrganizes(OrganizeDTO organizeDTO,WebPage webPage) {
        OrganizeEntity organizeEntity1 = new OrganizeEntity();
        organizeEntity1.setOrganizeName(organizeDTO.getOrganizeName());
        List<OrganizeDTO> organizeDTOs = new ArrayList<OrganizeDTO>();
        List<OrganizeEntity> organizeEntities = organizeRepository.getOrganizes(organizeEntity1,webPage);
        if(organizeEntities!=null){
            OrganizeDTO organizeDTO1;
            for(OrganizeEntity organizeEntity:organizeEntities){
                organizeDTO1 = new OrganizeDTO();
                organizeDTO1.setCrmName(organizeEntity.getCrmName());
                organizeDTO1.setOrganizeName(organizeEntity.getOrganizeName());
                organizeDTO1.setOrganizeId(organizeEntity.getOrganizeId());
                organizeDTO1.setMemo(organizeEntity.getMemo());
                organizeDTO1.setStaffNum("0");
                organizeDTO1.setCrmId(organizeEntity.getCrmId());
                organizeDTO1.setModifyTime(DateUtils.format(organizeEntity.getModifyTime()));
                organizeDTO1.setStatus(organizeEntity.getOrganizeStatus());
                organizeDTO1.setProjectNum("0");
                List<UserPropertyStaffEntity> userPropertyStaffEntities  = organizeUserRepository.getStaffsByOrganizeId(organizeEntity.getOrganizeId());
//                List<HouseProjectEntity> houseProjectEntities = organizeProjectRepository.getProjectByOrganizeId(organizeEntity.getOrganizeId());
                if(userPropertyStaffEntities!=null){
                    organizeDTO1.setStaffNum(String.valueOf(userPropertyStaffEntities.size()));
                }
//                if(houseProjectEntities!=null){
//                    organizeDTO1.setProjectNum(String.valueOf(houseProjectEntities.size()));
//                }
                organizeDTOs.add(organizeDTO1);
            }
        }
        return organizeDTOs;
    }

    @Override
    public void addOrganize(OrganizeDTO organizeDTO) {
        OrganizeEntity organizeEntity = new OrganizeEntity();
        organizeEntity.setOrganizeName(organizeDTO.getOrganizeName());
        organizeEntity.setCrmName(organizeDTO.getCrmName());
        organizeEntity.setCrmId(organizeDTO.getCrmId());
        organizeEntity.setOrganizeId(IdGen.uuid());
        organizeEntity.setMemo(organizeDTO.getMemo());
        organizeEntity.setOrderNum(organizeDTO.getOrderNum());
        organizeEntity.setOrganizeStatus(organizeDTO.getStatus());
        organizeEntity.setCreateTime(new Date());
        organizeEntity.setModifyTime(new Date());
        if(!StringUtil.isEmpty(organizeDTO.getStaffIds())){
            String[] ids = organizeDTO.getStaffIds().split(",");
            OrganizeUsermapEntity organizeUsermapEntity = new OrganizeUsermapEntity();
            for(String staffId:ids){
                organizeUsermapEntity.setId(IdGen.uuid());
                organizeUsermapEntity.setStaffId(staffId);
                organizeUsermapEntity.setStaffType("1");
                organizeUsermapEntity.setOrganizeId(organizeEntity.getOrganizeId());
                organizeUsermapEntity.setModifyTime(new Date());
                organizeUsermapEntity.setOrgStatus("1");
                organizeUserRepository.addOrganizeUser(organizeUsermapEntity);
            }
        }
        organizeRepository.addOrganize(organizeEntity);
    }

    @Override
    public void delOrganize(String organizeId) {
        RoleDataEntity roleDataEntity = new RoleDataEntity();
        roleDataEntity.setAuthorityType("2");
        roleDataEntity.setAuthorityId(organizeId);
        organizeUserRepository.delOrganizeUser(organizeId);            //删除组时 先清除与该组所有的关系
        roleDataRepository.delOrganizeRoleData(roleDataEntity);
        OrganizeEntity organizeEntity = organizeRepository.getOrganizeDetail(organizeId);
        organizeEntity.setOrganizeStatus("0");
        organizeEntity.setModifyTime(new Date());
        organizeRepository.delOrganize(organizeEntity);
    }

    @Override
    public void updateOrganize(OrganizeDTO organizeDTO) {
        OrganizeEntity organizeEntity = organizeRepository.getOrganizeDetail(organizeDTO.getOrganizeId());
        organizeEntity.setOrganizeName(organizeDTO.getOrganizeName());
        organizeEntity.setCrmName(organizeDTO.getCrmName());
        organizeEntity.setCrmId(organizeDTO.getCrmId());
        organizeEntity.setMemo(organizeDTO.getMemo());
        organizeEntity.setOrderNum(organizeDTO.getOrderNum());
        organizeEntity.setOrganizeStatus(organizeDTO.getStatus());
        organizeEntity.setModifyTime(new Date());
        List<String> compairDTO1 = new ArrayList<String>();
        List<String> compairDTO2 = new ArrayList<String>();
        List<String> compairDTO3 = new ArrayList<String>();
        Iterator<String> it1;
        Iterator<String> it2;
        List<OrganizeUsermapEntity> organizeUsermapEntityList = organizeUserRepository.getOrganizeUsermapList(organizeDTO.getOrganizeId()); //查出数据库中该组已存在的数据
        if(organizeUsermapEntityList!=null){
            for(OrganizeUsermapEntity organizeUsermapEntity:organizeUsermapEntityList){
                compairDTO1.add(organizeUsermapEntity.getStaffId());   //将数据库中的数据存放于compairDTO1
            }
        }
        if(!StringUtil.isEmpty(organizeDTO.getStaffIds())){
            String[] ids =organizeDTO.getStaffIds().split(",");
            for (int i = 0; i <ids.length;i++){
                compairDTO2.add(ids[i]);      //将页面传来的数据存放于compairDTO2
            }
        }
        compairDTO3.addAll(compairDTO1);
        compairDTO1.removeAll(compairDTO2);//比较后 为待删除的数据
        compairDTO2.removeAll(compairDTO3);//比较后 待新增的数据
        it1 = compairDTO1.iterator();
        it2 = compairDTO2.iterator();
        while (it1.hasNext()){
            organizeUserRepository.delOStaffs(organizeDTO.getOrganizeId(), it1.next());//删除权限关联数据
        }
        OrganizeUsermapEntity organizeUsermapEntity;
        while (it2.hasNext()){
            organizeUsermapEntity = new OrganizeUsermapEntity();
            organizeUsermapEntity.setId(IdGen.uuid());
            organizeUsermapEntity.setOrganizeId(organizeDTO.getOrganizeId());
            organizeUsermapEntity.setStaffId(it2.next());
            organizeUserRepository.addDumpOrganizeUser(organizeUsermapEntity);//保存最新关系
        }
        organizeRepository.updateOrganize(organizeEntity);
    }

    @Override
    public List<String> getOrgeList(String staffId) {
        List<String> organizeNameDTOs = new ArrayList<String>();
        List<OrganizeEntity> organizeEntities = organizeRepository.getOrganizeByStaffId(staffId);
        if(organizeEntities!=null){
            for(OrganizeEntity organizeEntity:organizeEntities){
                organizeNameDTOs.add(organizeEntity.getOrganizeId());
            }
        }
        return organizeNameDTOs;
    }

    @Override
    public List<OrganizeDTO> getOrgans(String staffId) {
        List<OrganizeEntity> organizeEntities = organizeRepository.getOrganizeByStaffId(staffId);
        List<OrganizeDTO> organizeDTOs = new ArrayList<OrganizeDTO>();
        if(organizeEntities!=null){
            OrganizeDTO organizeDTO;
            for(OrganizeEntity organizeEntity:organizeEntities){
                organizeDTO = new OrganizeDTO();
                organizeDTO.setModifyTime(DateUtils.format(organizeEntity.getModifyTime()));
                organizeDTO.setOrganizeId(organizeEntity.getOrganizeId());
                organizeDTO.setOrganizeName(organizeEntity.getOrganizeName());
                organizeDTO.setCrmId(organizeEntity.getCrmId());
                organizeDTO.setCrmName(organizeEntity.getCrmName());
                organizeDTOs.add(organizeDTO);
            }
        }
        return organizeDTOs;
    }

    @Override
    public List<String> getOProjectList(String staffId) {
        Set<String> oProjectDTOs = new HashSet<String>();
        List<HouseProjectEntity> houseProjectEntities = organizeUserRepository.getOProjectByStaff(staffId); //根据员工ID从常用组获取对应项目列表
        List<HouseProjectEntity> houseProjectEntities1 = roleDataRepository.getDataByStaffId(staffId); //根据员工ID获取对应项目列表
        List<HouseProjectEntity> houseProjectEntities2 = agencyRepository.getProjectsByAgency(staffId); //根据员工ID从组织架构获取对应项目列表
        if(houseProjectEntities!=null){
            for(HouseProjectEntity houseProjectEntity:houseProjectEntities){
                oProjectDTOs.add(houseProjectEntity.getPinyinCode());
            }
        }
        if(houseProjectEntities1!=null){
            for(HouseProjectEntity houseProjectEntity:houseProjectEntities1){
                oProjectDTOs.add(houseProjectEntity.getPinyinCode());
            }
        }
        if(houseProjectEntities2!=null){
            for(HouseProjectEntity houseProjectEntity:houseProjectEntities2){
                oProjectDTOs.add(houseProjectEntity.getPinyinCode());
            }
        }
        List<String> projectList = new ArrayList<String>(oProjectDTOs);
        return projectList;
    }

    @Override
    public List<String> getOrgeCrmList(String staffId) {
        List<String> organizeNameDTOs = new ArrayList<String>();
        List<OrganizeEntity> organizeEntities = organizeRepository.getOrganizeByStaffId(staffId);
        if(organizeEntities!=null){
            for(OrganizeEntity organizeEntity:organizeEntities){
                organizeNameDTOs.add(organizeEntity.getCrmId());
            }
        }
        return organizeNameDTOs;
    }

    @Override
    public List<String> getProjectRole(String staffId) {
        List<String> ids = roleDataRepository.getProjectRole(staffId);
        return ids;
    }

    @Override
    public List<String> getProjectRoleByNum(String staffId, String projectNum) {
        List<String> ids = roleDataRepository.getProjectRoleByNum(staffId,projectNum);
        return ids;
    }

    @Override
    public Map<String, Object> getProjectNumListProjectId(Map<String, String> map) {
        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("", "请选择项目");
        List<HouseProjectEntity> houseProjectEntities = organizeRepository.getHouseProject();
        if(null != houseProjectEntities && houseProjectEntities.size()>0){
            for(HouseProjectEntity houseProjectEntity : houseProjectEntities){
                if(null != map && map.size()>0){
                    Iterator it = map.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry entry = (Map.Entry) it.next();
                        if(entry.getKey().equals(houseProjectEntity.getId())){
                            map1.put(houseProjectEntity.getPinyinCode(),entry.getValue());
                        }
                    }
                }
            }
        }
        return map1;
    }
}
