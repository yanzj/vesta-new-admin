package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.dto.adminDTO.*;
import com.maxrocky.vesta.application.dto.adminDTO.batch.*;
import com.maxrocky.vesta.application.inf.AgencyService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.baseData.model.BaseProjectPeopleEntity;
import com.maxrocky.vesta.domain.baseData.repository.*;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.project.model.SecurityRoleEntity;
import com.maxrocky.vesta.domain.project.repository.SecurityProjectRepository;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chen on 2016/6/18.
 */
@Service
public class AgencyServiceImpl implements AgencyService {
    @Autowired
    AgencyRepository agencyRepository;
    @Autowired
    UserAgencyRepository userAgencyRepository;
    @Autowired
    RoleDataRepository roleDataRepository;
    @Autowired
    AppRoleSetRepository appRoleSetRepository;
    @Autowired
    SupplierAgencyRepository supplierAgencyRepository;
    @Autowired
    ProjectPeopleRepository projectPeopleRepository;
    @Autowired
    ProjectBuildingRepository projectBuildingRepository;
    @Autowired
    ProjectCategoryRepository projectCategoryRepository;
    @Autowired
    UserPropertyStaffRepository userPropertystaffReposiroty;
    @Autowired
    StaffEmployRepository staffEmployRepository;
    @Autowired
    ProjectProjectRepository projectProjectRepository;
    @Autowired
    SecurityProjectRepository securityProjectRepository;

    @Override
    public List<AgencyTreeDTO> getAgencyRootList() {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AgencyEntity> agencyEntities = agencyRepository.getAgencyRoots();
        if (agencyEntities != null) {
            AgencyTreeDTO agencyTreeDTO;
            for (AgencyEntity agencyEntity : agencyEntities) {
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setId(agencyEntity.getAgencyId());
                agencyTreeDTO.setName(agencyEntity.getAgencyName());
                agencyTreeDTO.setAgencyType(agencyEntity.getAgencyType());
                agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                agencyTreeDTO.setOpen("true");
//                List<AgencyEntity> agencyEntitieList = agencyRepository.getAgencyList(agencyEntity.getAgencyId());
                List<AgencyEntity> agencyEntitieList = agencyRepository.searchAgencyList(agencyEntity.getAgencyId());
//                List<UserPropertyStaffEntity> userPropertyStaffEntities = userAgencyRepository.getStaffByAgencyId(agencyEntity.getAgencyId());
                if (agencyEntitieList != null && agencyEntitieList.size() > 0) {
                    agencyTreeDTO.setIsParent("true");
                } else {
                    agencyTreeDTO.setIsParent("false");
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<AgencyTreeDTO> getAgencyList(String parentId) {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
//        List<AgencyEntity> agencyEntities = agencyRepository.getAgencyList(parentId);
        List<AgencyEntity> agencyEntities = agencyRepository.searchAgencyList(parentId);
        if (agencyEntities != null) {
            AgencyTreeDTO agencyTreeDTO;
//            AgencyTreeDTO agencyTreeDTO1;
            for (AgencyEntity agencyEntity : agencyEntities) {
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setId(agencyEntity.getAgencyId());
                agencyTreeDTO.setName(agencyEntity.getAgencyName());
                agencyTreeDTO.setIcon("../static/img/diy/9.png");
                List<AgencyEntity> agencyEntitieList = agencyRepository.searchAgencyList(agencyEntity.getAgencyId());
//                List<UserPropertyStaffEntity> userPropertyStaffEntities = userAgencyRepository.getStaffByAgencyId(agencyEntity.getAgencyId());
                if (agencyEntitieList != null && agencyEntitieList.size() > 0) {
                    agencyTreeDTO.setIsParent("true");
                } else {
                    agencyTreeDTO.setIsParent("false");
                }
                if (agencyEntity.getAgencyType().equals("2")) {
                    agencyTreeDTO.setIcon("../static/img/diy/4.png");
                }
                agencyTreeDTO.setType("1");
                agencyTreeDTO.setAgencyType(agencyEntity.getAgencyType());
                agencyTreeDTOs.add(agencyTreeDTO);
//                if (userPropertyStaffEntities != null) {
//                    for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities) {          //将人员部分添加到组织机构树下面
//                        agencyTreeDTO1 = new AgencyTreeDTO();
//                        agencyTreeDTO1.setId(userPropertyStaffEntity.getStaffId());
//                        agencyTreeDTO1.setpId(agencyEntity.getAgencyId());
//                        agencyTreeDTO1.setName(userPropertyStaffEntity.getStaffName() + "  （" + userPropertyStaffEntity.getUserName() + "）");
//                        agencyTreeDTO1.setIcon("../static/img/diy/user.png");
//                        agencyTreeDTO1.setType("3");
//                        agencyTreeDTO1.setIsParent(false);
//                        agencyTreeDTOs.add(agencyTreeDTO1);
//                    }
//                }
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<AgencyTreeDTO> getAgencyNext(String parentId, String agencyType) {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        if (!StringUtil.isEmpty(agencyType) && "1".equals(agencyType) || "4".equals(agencyType) || "5".equals(agencyType)) {
            List<UserPropertyStaffEntity> userPropertyStaffEntities = userAgencyRepository.getStaffByAgencyId(parentId);
            if (userPropertyStaffEntities != null) {
                AgencyTreeDTO agencyTreeDTO;
                for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities) {          //将人员部分添加到组织机构树下面
                    agencyTreeDTO = new AgencyTreeDTO();
                    agencyTreeDTO.setId(userPropertyStaffEntity.getStaffId());
                    agencyTreeDTO.setpId(parentId);
                    agencyTreeDTO.setName(userPropertyStaffEntity.getStaffName() + "  （" + userPropertyStaffEntity.getUserName() + "）");
//                        agencyTreeDTO.setIcon("../static/img/diy/user.png");
                    agencyTreeDTO.setType("3");
                    agencyTreeDTO.setIsParent("false");
                    agencyTreeDTOs.add(agencyTreeDTO);
                }
            }
        } else {
            AgencyEntity agencyEntity2 = agencyRepository.getAgencyDetail(parentId);
            List<AgencyEntity> agencyEntities = agencyRepository.getAgencyList(parentId);
            if (agencyEntities != null) {
                AgencyTreeDTO agencyTreeDTO;
                for (AgencyEntity agencyEntity : agencyEntities) {
                    agencyTreeDTO = new AgencyTreeDTO();
                    agencyTreeDTO.setId(agencyEntity.getAgencyId());
                    agencyTreeDTO.setName(agencyEntity.getAgencyName() + "-" + agencyEntity2.getAgencyName());
                    List<AgencyEntity> agencyEntitieList = agencyRepository.getAgencyList(agencyEntity.getAgencyId());
                    if (agencyEntitieList != null && agencyEntitieList.size() > 0) {
                        agencyTreeDTO.setIsParent("true");
                    } else {
                        agencyTreeDTO.setIsParent("false");
                    }
                    agencyTreeDTO.setType("1");
                    agencyTreeDTO.setAgencyType(agencyEntity.getAgencyType());
                    agencyTreeDTOs.add(agencyTreeDTO);
                }
            }
        }

        return agencyTreeDTOs;
    }

    @Override
    public List<AgencyTreeDTO> getAgencyFor1(String agencyId) {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AgencyEntity> agencyEntities = agencyRepository.getAgency1(agencyId);
        if (agencyEntities != null) {
            AgencyTreeDTO agencyTreeDTO;
            for (AgencyEntity agencyEntity : agencyEntities) {
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setId(agencyEntity.getAgencyId());
                agencyTreeDTO.setName(agencyEntity.getAgencyName());
                List<AgencyEntity> agencyEntitieList = agencyRepository.getAgencyList(agencyEntity.getAgencyId());
                if (agencyEntitieList != null && agencyEntitieList.size() > 0) {
                    agencyTreeDTO.setIsParent("true");
                } else {
                    agencyTreeDTO.setIsParent("false");
                }
                agencyTreeDTO.setType("1");
                agencyTreeDTO.setAgencyType(agencyEntity.getAgencyType());
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<AgencyTreeDTO> getAllAgencyList() {
        List<AgencyTreeDTO> agencyAdminDTOs = new ArrayList<AgencyTreeDTO>();
        List<AgencyEntity> agencyEntities = agencyRepository.getAllAgencyList();
        if (agencyEntities != null) {
            AgencyTreeDTO agencyTreeDTO;
            AgencyTreeDTO agencyTreeDTO1;
            for (AgencyEntity agencyEntity : agencyEntities) {
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setId(agencyEntity.getAgencyId());
                agencyTreeDTO.setpId(agencyEntity.getParentId());
                agencyTreeDTO.setName(agencyEntity.getAgencyName());
                agencyTreeDTO.setType("1");
                agencyAdminDTOs.add(agencyTreeDTO);
                List<UserPropertyStaffEntity> userPropertyStaffEntities = userAgencyRepository.getStaffByAgencyId(agencyEntity.getAgencyId());
                if (userPropertyStaffEntities != null) {
                    for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities) {
                        agencyTreeDTO1 = new AgencyTreeDTO();
                        agencyTreeDTO1.setId(userPropertyStaffEntity.getStaffId());
                        agencyTreeDTO1.setpId(agencyEntity.getAgencyId());
                        agencyTreeDTO1.setName(userPropertyStaffEntity.getStaffName() + "  （" + userPropertyStaffEntity.getUserName() + "）");
                        agencyTreeDTO1.setIcon("../static/img/diy/user.png");
                        agencyTreeDTO1.setType("3");
                        agencyAdminDTOs.add(agencyTreeDTO1);
                    }
                }
            }
        }
        return agencyAdminDTOs;
    }

    @Override
    public List<AgencyTreeDTO> getAgencyFullList() {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AgencyEntity> agencyEntities = agencyRepository.getAllAgencyList();
        if (agencyEntities != null) {
            AgencyTreeDTO agencyTreeDTO;
            for (AgencyEntity agencyEntity : agencyEntities) {
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setId(agencyEntity.getAgencyId());
                agencyTreeDTO.setpId(agencyEntity.getParentId());
                agencyTreeDTO.setName(agencyEntity.getAgencyName());
                agencyTreeDTO.setAgencyType(agencyEntity.getAgencyType());
                agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                agencyTreeDTO.setIcon("../static/img/diy/dpt.png");
                if (agencyEntity.getAgencyType().equals("2")) {
                    agencyTreeDTO.setIcon("../static/img/diy/cpy.png");
                }
                agencyTreeDTO.setOpen("false");
                if (agencyEntity.getLevel() < 3) {
                    agencyTreeDTO.setOpen("true");
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<AgencyTreeDTO> getOAAgencyList() {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthOuterAgencyEntity> authOuterAgencyEntityList = agencyRepository.getOAAgencyListIsDel();
        if (authOuterAgencyEntityList != null) {
            AgencyTreeDTO agencyTreeDTO;
            for (AuthOuterAgencyEntity authOuterAgencyEntity : authOuterAgencyEntityList) {
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setId(authOuterAgencyEntity.getAgencyId());
                agencyTreeDTO.setpId(authOuterAgencyEntity.getParentId());
                agencyTreeDTO.setName(authOuterAgencyEntity.getAgencyName());
                agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                agencyTreeDTO.setIcon("../static/img/diy/dpt.png");
                agencyTreeDTO.setOpen("false");
                if (authOuterAgencyEntity.getLevel() < 3) {
                    agencyTreeDTO.setOpen("true");
                }
                if ("0".equals(authOuterAgencyEntity.getStatus())) {
                    agencyTreeDTO.setIsHidden("true");
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    //AuthOuterAgencyEntity
    @Override
    public List<AgencyTreeDTO> getOuterAgencyList() {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthOuterAgencyEntity> authOuterAgencyEntities = agencyRepository.getOuterAgencyList();
        if (null != authOuterAgencyEntities && authOuterAgencyEntities.size() > 0) {
            AgencyTreeDTO agencyTreeDTO;
            for (AuthOuterAgencyEntity authOuterAgencyEntity : authOuterAgencyEntities) {
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                agencyTreeDTO.setIcon("../static/img/diy/dpt.png");
                agencyTreeDTO.setId(authOuterAgencyEntity.getAgencyId());
                agencyTreeDTO.setpId(authOuterAgencyEntity.getParentId());
                agencyTreeDTO.setName(authOuterAgencyEntity.getAgencyName());
                agencyTreeDTO.setOpen("false");
                if (authOuterAgencyEntity.getLevel().equals(2)) {
                    agencyTreeDTO.setOpen("true");
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<AgencyTreeDTO> getOuterAgencyListById(String staffIdW) {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthOuterAgencyEntity> authOuterAgencyEntities = agencyRepository.getOuterAgencyList();
        List<String> agencyIds = agencyRepository.getAgencyByStaffId(staffIdW);
        if (null != authOuterAgencyEntities && authOuterAgencyEntities.size() > 0) {
            AgencyTreeDTO agencyTreeDTO;
            for (AuthOuterAgencyEntity authOuterAgencyEntity : authOuterAgencyEntities) {
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setIconOpen("../static/img/diy/1_open.png");
                agencyTreeDTO.setIconClose("../static/img/diy/1_close.png");
                agencyTreeDTO.setIcon("../static/img/diy/dpt.png");
                agencyTreeDTO.setId(authOuterAgencyEntity.getAgencyId());
                agencyTreeDTO.setpId(authOuterAgencyEntity.getParentId());
                agencyTreeDTO.setName(authOuterAgencyEntity.getAgencyName());
                agencyTreeDTO.setOpen("false");
                if (authOuterAgencyEntity.getLevel().equals(2) || authOuterAgencyEntity.getLevel().equals(3)) {
                    agencyTreeDTO.setOpen("true");
                }
                if (null != agencyIds && agencyIds.size() > 0) {
                    for (String agencyId : agencyIds) {
                        if (agencyId.equals(authOuterAgencyEntity.getAgencyId())) {
                            agencyTreeDTO.setChecked(true);
                        }
                    }
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<AgencyDTO> AgencyParment(String parentId, String agencyName, WebPage webPage) {
        List<AgencyDTO> agencyDTOs = new ArrayList<AgencyDTO>();
        List<AgencyEntity> agencyEntities = agencyRepository.getAgencys(parentId, agencyName, webPage);
        if (agencyEntities != null) {
            AgencyDTO agencyDTO;
            for (AgencyEntity agencyEntity : agencyEntities) {
                agencyDTO = new AgencyDTO();
                agencyDTO.setAgencyId(agencyEntity.getAgencyId());
                agencyDTO.setAgencyName(agencyEntity.getAgencyName());
                AgencyEntity agencyEntity1 = agencyRepository.getAgency(parentId);
                agencyDTO.setParentName(agencyEntity1.getAgencyName());
                agencyDTO.setModifyTime(DateUtils.format(agencyEntity.getModifyTime()));
                agencyDTO.setAgencyType(agencyEntity.getAgencyType());
                agencyDTO.setMemo(agencyEntity.getAgencyDesc());
                agencyDTO.setStatus(agencyEntity.getStatus());
                agencyDTO.setOrderNum(agencyEntity.getOrderNum());
                agencyDTO.setOutEmploy(agencyEntity.getOutEmploy());
                agencyDTOs.add(agencyDTO);
            }
        }
        return agencyDTOs;
    }

    //新增机构
    @Override
    public ApiResult addAgency(AgencyReceiveDTO agencyReceiveDTO) {
        AgencyEntity agencyEntity = new AgencyEntity();
        RoleDataEntity roleDataEntity = new RoleDataEntity();
        agencyEntity.setAgencyId(IdGen.uuid());
        agencyEntity.setParentId(agencyReceiveDTO.getParentId());
        agencyEntity.setAgencyName(agencyReceiveDTO.getAgencyName());
        agencyEntity.setAgencyType(agencyReceiveDTO.getAgencyType());
        agencyEntity.setOrderNum(agencyReceiveDTO.getOrderNum());
        agencyEntity.setOutEmploy(agencyReceiveDTO.getOutEmploy());
        agencyEntity.setAgencyDesc(agencyReceiveDTO.getMemo());
        agencyEntity.setCreateTime(new Date());
        agencyEntity.setModifyTime(new Date());
        agencyEntity.setStatus(agencyReceiveDTO.getStatus());
        //父级ID
        if (agencyReceiveDTO.getParentId() != null) {
            agencyEntity.setIsRoot("0");
            AgencyEntity agencyEntity1 = agencyRepository.getAgency(agencyReceiveDTO.getParentId());
            agencyEntity.setAgencyPath(agencyEntity1.getAgencyPath() + "/" + agencyEntity.getAgencyId());   //设置结构树完整路径
            agencyEntity.setLevel(agencyEntity1.getLevel() + 1);
        } else {
            agencyEntity.setIsRoot("1");
            agencyEntity.setLevel(1);
            agencyEntity.setAgencyPath("/" + agencyEntity.getAgencyId());
        }
        //供应商
        if (!StringUtil.isEmpty(agencyReceiveDTO.getSupplier())) {
            SupplierAgencyMapEntity supplierAgencyMapEntity = new SupplierAgencyMapEntity();
            supplierAgencyMapEntity.setMapId(IdGen.uuid());
            supplierAgencyMapEntity.setSupplierId(agencyReceiveDTO.getSupplier());
            supplierAgencyMapEntity.setAgencyId(agencyEntity.getAgencyId());
            supplierAgencyRepository.addSupplierAgency(supplierAgencyMapEntity);
        }
        //角色关系
        if (!StringUtil.isEmpty(agencyReceiveDTO.getAppRoleSets())) {
            String[] ids = agencyReceiveDTO.getAppRoleSets().split(",");
            for (int i = 0; i < ids.length; i++) {
                roleDataEntity.setId(IdGen.uuid());
                roleDataEntity.setStatus("1");
                roleDataEntity.setDataId(ids[i]);
                roleDataEntity.setDataType("2");
                roleDataEntity.setAuthorityId(agencyEntity.getAgencyId());
                roleDataEntity.setAuthorityType("1");
                roleDataRepository.addRoleData(roleDataEntity);//添加组织机构与角色的关系
            }
        }
        agencyRepository.addAgency(agencyEntity);
        return new SuccessApiResult("ok");
    }

    @Override
    public List<AgencyAdminDTO> getAgencyByProjectId(String projectId, String permission) {
        List<AgencyEntity> agencyEntities = roleDataRepository.getProjectRoles(projectId, permission);
        List<UserPropertyStaffEntity> userPropertyStaffEntities = roleDataRepository.getProjectStaffRole(projectId, permission);
        List<OrganizeEntity> organizeEntities = roleDataRepository.getProjectOrganizeRole(projectId, permission);
        List<AgencyAdminDTO> agencyAdminDTOs = new ArrayList<AgencyAdminDTO>();
        AgencyAdminDTO agencyAdminDTO;
        if (userPropertyStaffEntities != null) {
            for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities) {
                agencyAdminDTO = new AgencyAdminDTO();
                agencyAdminDTO.setAgencyName(userPropertyStaffEntity.getStaffName() + " （" + userPropertyStaffEntity.getUserName() + "）");
                agencyAdminDTO.setAgencyId(userPropertyStaffEntity.getStaffId());
                agencyAdminDTO.setAgencyType("3");
                agencyAdminDTOs.add(agencyAdminDTO);
            }
        }
        if (agencyEntities != null) {
            for (AgencyEntity agencyEntity : agencyEntities) {
                agencyAdminDTO = new AgencyAdminDTO();
                agencyAdminDTO.setAgencyName(agencyEntity.getAgencyName());
                if (!StringUtil.isEmpty(agencyEntity.getParentId())) {
                    AgencyEntity agencyEntity1 = agencyRepository.getAgencyDetail(agencyEntity.getParentId());
                    agencyAdminDTO.setAgencyName(agencyEntity.getAgencyName() + "-" + agencyEntity1.getAgencyName());
                }
                agencyAdminDTO.setAgencyId(agencyEntity.getAgencyId());
                agencyAdminDTO.setParentId(agencyEntity.getParentId());
                agencyAdminDTO.setAgencyType("1");
                agencyAdminDTOs.add(agencyAdminDTO);
            }
        }
        if (organizeEntities != null) {
            for (OrganizeEntity organizeEntity : organizeEntities) {
                agencyAdminDTO = new AgencyAdminDTO();
                agencyAdminDTO.setAgencyName(organizeEntity.getOrganizeName());
                agencyAdminDTO.setAgencyId(organizeEntity.getOrganizeId());
                agencyAdminDTO.setAgencyType("2");
                agencyAdminDTOs.add(agencyAdminDTO);
            }
        }
        return agencyAdminDTOs;
    }

    @Override
    public List<AgencyAdminDTO> getComplaintByProjectId(String projectId) {
        List<Object[]> list = roleDataRepository.getComplintByProjectId(projectId);
        List<AgencyAdminDTO> agencyAdminDTOs = new ArrayList<AgencyAdminDTO>();
        AgencyAdminDTO agencyAdminDTO;
        if (list != null) {
            for (Object[] obj : list) {
                agencyAdminDTO = new AgencyAdminDTO();
                agencyAdminDTO.setAgencyName(obj[1].toString() + " （" + obj[2].toString() + "）");
                agencyAdminDTO.setAgencyId(obj[0].toString());
                agencyAdminDTO.setAgencyType("3");
                agencyAdminDTOs.add(agencyAdminDTO);
            }
        }
        return agencyAdminDTOs;
    }

    @Override
    public List<AgencyAdminDTO> agencyListByProjectId(String projectId) {
        List<AgencyEntity> agencyEntities = roleDataRepository.getRoleDataByProject(projectId);
        List<AgencyAdminDTO> agencyAdminDTOs = new ArrayList<AgencyAdminDTO>();
        AgencyAdminDTO agencyAdminDTO;
        if (agencyEntities != null) {
            for (AgencyEntity agencyEntity : agencyEntities) {
                agencyAdminDTO = new AgencyAdminDTO();
                agencyAdminDTO.setAgencyName(agencyEntity.getAgencyName());
                agencyAdminDTO.setAgencyId(agencyEntity.getAgencyId());
                agencyAdminDTO.setParentId(agencyEntity.getParentId());
                agencyAdminDTO.setAgencyType("1");
                agencyAdminDTOs.add(agencyAdminDTO);
            }
        }
        return agencyAdminDTOs;
    }

    @Override
    public List<AgencyAdminDTO> staffListByProjectId(String projectId) {
        List<UserPropertyStaffEntity> userPropertyStaffEntities = roleDataRepository.getStaffByProject(projectId);
        List<AgencyAdminDTO> agencyAdminDTOs = new ArrayList<AgencyAdminDTO>();
        AgencyAdminDTO agencyAdminDTO;
        if (userPropertyStaffEntities != null) {
            for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities) {
                agencyAdminDTO = new AgencyAdminDTO();
                agencyAdminDTO.setAgencyName(userPropertyStaffEntity.getStaffName() + " （" + userPropertyStaffEntity.getUserName() + "）");
                agencyAdminDTO.setAgencyId(userPropertyStaffEntity.getStaffId());
                agencyAdminDTO.setAgencyType("3");
                agencyAdminDTOs.add(agencyAdminDTO);
            }
        }
        return agencyAdminDTOs;
    }

    @Override
    public List<AgencyAdminDTO> getAgencyByRoleId(String roleSetId) {
        List<AgencyEntity> agencyEntities = roleDataRepository.getAgencyByRoleSet(roleSetId);
        List<UserPropertyStaffEntity> userPropertyStaffEntities = roleDataRepository.getStaffByRoleSet(roleSetId);
        List<UserPropertyStaffEntity> userPropertyStaffEntitieList = appRoleSetRepository.getSetsStaff(roleSetId);
        List<AgencyAdminDTO> agencyAdminDTOs = new ArrayList<AgencyAdminDTO>();
        AgencyAdminDTO agencyAdminDTO;
        if (agencyEntities != null) {
            for (AgencyEntity agencyEntity : agencyEntities) {
                agencyAdminDTO = new AgencyAdminDTO();
                agencyAdminDTO.setAgencyName(agencyEntity.getAgencyName());
                agencyAdminDTO.setAgencyId(agencyEntity.getAgencyId());
                agencyAdminDTO.setParentId(agencyEntity.getParentId());
                agencyAdminDTO.setAgencyType("1");
                agencyAdminDTOs.add(agencyAdminDTO);
            }
        }
        if (userPropertyStaffEntities != null) {
            for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities) {
                agencyAdminDTO = new AgencyAdminDTO();
                agencyAdminDTO.setAgencyName(userPropertyStaffEntity.getStaffName() + "  （" + userPropertyStaffEntity.getUserName() + "）");
                agencyAdminDTO.setAgencyId(userPropertyStaffEntity.getStaffId());
                agencyAdminDTO.setAgencyType("3");
                agencyAdminDTOs.add(agencyAdminDTO);
            }
        }
        if (userPropertyStaffEntitieList != null) {
            for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntitieList) {
                agencyAdminDTO = new AgencyAdminDTO();
                agencyAdminDTO.setAgencyName(userPropertyStaffEntity.getStaffName() + "  （" + userPropertyStaffEntity.getUserName() + "）");
                agencyAdminDTO.setAgencyId(userPropertyStaffEntity.getStaffId());
                agencyAdminDTO.setAgencyType("0");           //纯用户 特殊标识
                agencyAdminDTOs.add(agencyAdminDTO);
            }
        }
        return agencyAdminDTOs;
    }

    @Override
    public List<AgencyAdminDTO> agencyListByRoleId(String roleSetId) {
        List<AgencyEntity> agencyEntities = roleDataRepository.getAgencyByRoleSet(roleSetId);
        List<AgencyAdminDTO> agencyAdminDTOs = new ArrayList<AgencyAdminDTO>();
        AgencyAdminDTO agencyAdminDTO;
        if (agencyEntities != null) {
            for (AgencyEntity agencyEntity : agencyEntities) {
                agencyAdminDTO = new AgencyAdminDTO();
                agencyAdminDTO.setAgencyName(agencyEntity.getAgencyName());
                agencyAdminDTO.setAgencyId(agencyEntity.getAgencyId());
                agencyAdminDTO.setParentId(agencyEntity.getParentId());
                agencyAdminDTO.setAgencyType("1");
                if (!StringUtil.isEmpty(agencyEntity.getParentId())) {
                    AgencyEntity agencyEntity1 = agencyRepository.getAgencyDetail(agencyEntity.getParentId());
                    if (agencyEntity1 != null) {
                        agencyAdminDTO.setAgencyName(agencyEntity.getAgencyName() + "-" + agencyEntity1.getAgencyName());
                    }
                }
                agencyAdminDTOs.add(agencyAdminDTO);
            }
        }
        return agencyAdminDTOs;
    }

    @Override
    public List<AgencyAdminDTO> staffListByRoleSetId(String roleSetId) {
        List<UserPropertyStaffEntity> userPropertyStaffEntities = roleDataRepository.getStaffByRoleSet(roleSetId);
        List<UserPropertyStaffEntity> userPropertyStaffEntitieList = appRoleSetRepository.getSetsStaff(roleSetId);
        List<AgencyAdminDTO> agencyAdminDTOs = new ArrayList<AgencyAdminDTO>();
        AgencyAdminDTO agencyAdminDTO;
        if (userPropertyStaffEntities != null) {
            for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities) {
                agencyAdminDTO = new AgencyAdminDTO();
                agencyAdminDTO.setAgencyName(userPropertyStaffEntity.getStaffName() + "  （" + userPropertyStaffEntity.getUserName() + "）");
                agencyAdminDTO.setAgencyId(userPropertyStaffEntity.getStaffId());
                agencyAdminDTO.setAgencyType("3");
                agencyAdminDTOs.add(agencyAdminDTO);
            }
        }
        if (userPropertyStaffEntitieList != null) {
            for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntitieList) {
                agencyAdminDTO = new AgencyAdminDTO();
                agencyAdminDTO.setAgencyName(userPropertyStaffEntity.getStaffName() + "  （" + userPropertyStaffEntity.getUserName() + "）");
                agencyAdminDTO.setAgencyId(userPropertyStaffEntity.getStaffId());
                agencyAdminDTO.setAgencyType("0");           //纯用户 特殊标识
                agencyAdminDTOs.add(agencyAdminDTO);
            }
        }
        return agencyAdminDTOs;
    }

    @Override
    public ApiResult updateAgency(AgencyReceiveDTO agencyReceiveDTO) {
        AgencyEntity agencyEntity = agencyRepository.getAgencyDetail(agencyReceiveDTO.getAgencyId());
        if (!"1".equals(agencyReceiveDTO.getStatus())) {
            agencyRepository.delAgency(agencyEntity);  //删除所有子节点
        }
        agencyEntity.setAgencyType(agencyReceiveDTO.getAgencyType());
        agencyEntity.setParentId(agencyReceiveDTO.getParentId());
        agencyEntity.setOrderNum(agencyReceiveDTO.getOrderNum());
        agencyEntity.setOutEmploy(agencyReceiveDTO.getOutEmploy());
        agencyEntity.setStatus(agencyReceiveDTO.getStatus());
        agencyEntity.setIsRoot("0");
        agencyEntity.setAgencyDesc(agencyReceiveDTO.getMemo());
        agencyEntity.setModifyTime(new Date());
        AgencyEntity agencyEntity1 = agencyRepository.getAgency(agencyReceiveDTO.getParentId());
        agencyEntity.setAgencyPath(agencyEntity1.getAgencyPath() + "/" + agencyEntity.getAgencyId());   //设置结构树完整路径
        agencyEntity.setLevel(agencyEntity1.getLevel() + 1);

        RoleDataEntity roleDataEntity1 = new RoleDataEntity();
        roleDataEntity1.setAuthorityType("1");
        roleDataEntity1.setAuthorityId(agencyReceiveDTO.getAgencyId());
        roleDataEntity1.setDataType("2");
        roleDataRepository.updateRoleData(roleDataEntity1);       //删除原有的机构与角色关系
        if (!StringUtil.isEmpty(agencyReceiveDTO.getAppRoleSets())) {
            RoleDataEntity roleDataEntity = new RoleDataEntity();
            String[] ids = agencyReceiveDTO.getAppRoleSets().substring(1).split(",");
            for (int i = 0; i < ids.length; i++) {
                roleDataEntity.setId(IdGen.uuid());
                roleDataEntity.setStatus("1");
                roleDataEntity.setDataId(ids[i]);
                roleDataEntity.setDataType("2");
                roleDataEntity.setAuthorityId(agencyReceiveDTO.getAgencyId());
                roleDataEntity.setAuthorityType("1");
                roleDataEntity.setModifyTime(new Date());
                roleDataRepository.addRoleData(roleDataEntity);//添加组织机构与角色的最新关系
            }
        }
        if (!StringUtil.isEmpty(agencyReceiveDTO.getSupplier())) {   //判断供应商ID是否为空
            SupplierAgencyMapEntity supplierAgencyMapEntity = supplierAgencyRepository.getSupplierAgencys(agencyReceiveDTO.getAgencyId());
            if (supplierAgencyMapEntity == null || !supplierAgencyMapEntity.getSupplierId().equals(agencyReceiveDTO.getSupplier())) {
                if (supplierAgencyMapEntity != null) {
                    supplierAgencyRepository.delSupplierAgency(supplierAgencyMapEntity);
                }
                SupplierAgencyMapEntity supplierAgencyMapEntity1 = new SupplierAgencyMapEntity();
                supplierAgencyMapEntity1.setMapId(IdGen.uuid());
                supplierAgencyMapEntity1.setSupplierId(agencyReceiveDTO.getSupplier());
                supplierAgencyMapEntity1.setAgencyId(agencyReceiveDTO.getAgencyId());
                supplierAgencyRepository.addSupplierAgency(supplierAgencyMapEntity1);  //添加组织机构与供应商关系
            }
        }
        if ("4".equals(agencyReceiveDTO.getAgencyType()) || "5".equals(agencyReceiveDTO.getAgencyType())) {  //如果当前机构为责任单位或监理时
            if (!"1".equals(agencyReceiveDTO.getStatus())) {
                projectPeopleRepository.delProjectPeopleByDutyId(agencyEntity.getAgencyId());   //维护基础表
                projectBuildingRepository.delSupplierBuildByDutyId(agencyEntity.getAgencyId()); //删除责任单位与楼栋关系
                projectCategoryRepository.delSupplierCategory(agencyEntity.getAgencyId());     //删除责任单位与检查项关系
            }
            if (!agencyEntity.getAgencyName().equals(agencyReceiveDTO.getAgencyName())) {
                projectPeopleRepository.updateProjectPeople(agencyReceiveDTO.getAgencyName(), agencyEntity.getAgencyId());
            }
        }
        agencyEntity.setAgencyName(agencyReceiveDTO.getAgencyName());
        agencyRepository.updateAgency(agencyEntity);
        return new SuccessApiResult("ok");
    }

    @Override
    public void delAgency(AgencyTreeDTO agencyTreeDTO) {
//        if(agencyTreeDTO.getType().equals("1")){         //当为机构组织时
        AgencyEntity agencyEntity = agencyRepository.getAgencyDetail(agencyTreeDTO.getId());
        agencyEntity.setStatus("0");
        agencyRepository.delAgency(agencyEntity);
        userAgencyRepository.updateUserAgency(agencyTreeDTO.getId());
//        }else {
//            UserAgencymapEntity userAgencymapEntity = new UserAgencymapEntity();
//            userAgencymapEntity.setAgencyId(agencyTreeDTO.getpId());
//            userAgencymapEntity.setStaffId(agencyTreeDTO.getId());
//            userAgencyRepository.delUserAgency(userAgencymapEntity);
//        }
    }

    @Override
    public ApiResult getStaffListByAgency(String agencyId) {
        List<UserPropertyStaffEntity> userPropertyStaffEntities = userAgencyRepository.getStaffByAgencyId(agencyId);
        List<StaffDTO> staffDTOs = new ArrayList<StaffDTO>();
        if (userPropertyStaffEntities != null) {
            StaffDTO staffDTO;
            for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities) {
                staffDTO = new StaffDTO();
                staffDTO.setStaffId(userPropertyStaffEntity.getStaffId());
                staffDTO.setNikeName(userPropertyStaffEntity.getUserName());
                staffDTO.setStaffName(userPropertyStaffEntity.getStaffName() + "  （" + userPropertyStaffEntity.getUserName() + "）");
                staffDTOs.add(staffDTO);
            }
        }
        return new SuccessApiResult(staffDTOs);
    }

    @Override
    public List<AgencyDTO> staffListFor(AgencyAdminDTO agencyAdminDTO, WebPage webPage) {
        List<AgencyDTO> staffReceiveDTOs = new ArrayList<AgencyDTO>();
        UserPropertyStaffEntity userPropertyStaffEntity1 = new UserPropertyStaffEntity();
        userPropertyStaffEntity1.setStaffName(agencyAdminDTO.getAdmStaff());
        userPropertyStaffEntity1.setCompanyId(agencyAdminDTO.getAgencyId());
        userPropertyStaffEntity1.setUserName(agencyAdminDTO.getAdmUser());
        List<UserPropertyStaffEntity> userPropertyStaffEntities = userAgencyRepository.getStaffForNames(userPropertyStaffEntity1, webPage);
        if (userPropertyStaffEntities != null) {
            AgencyDTO agencyDTO;
            for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntities) {
                agencyDTO = new AgencyDTO();
                agencyDTO.setAgencyId(userPropertyStaffEntity.getStaffId());
                agencyDTO.setOrderNum(userPropertyStaffEntity.getOrderNum());
                agencyDTO.setMemo(userPropertyStaffEntity.getMemo());
                agencyDTO.setModifyTime(DateUtils.format(userPropertyStaffEntity.getModifyOn()));
                agencyDTO.setMemo(userPropertyStaffEntity.getEmail());
                agencyDTO.setParentName(userPropertyStaffEntity.getStaffName());
                agencyDTO.setAgencyName(userPropertyStaffEntity.getUserName());
                agencyDTO.setStaffMobile(userPropertyStaffEntity.getMobile());
                staffReceiveDTOs.add(agencyDTO);
            }
        }
        return staffReceiveDTOs;
    }

    @Override
    public List<StaffDTO> getAgencyOutStaff(AgencyAdminDTO agencyAdminDTO) {
        UserPropertyStaffEntity userPropertyStaffEntity = new UserPropertyStaffEntity();
        userPropertyStaffEntity.setStaffName(agencyAdminDTO.getAgencyName());
        userPropertyStaffEntity.setUserName(agencyAdminDTO.getParentId());  //暂将parentId作为登录名
        userPropertyStaffEntity.setProjectId(agencyAdminDTO.getAgencyId()); //暂将projectId作为组织ID条件
        List<UserPropertyStaffEntity> userPropertyStaffEntities = userAgencyRepository.getAgencyOutStaff(userPropertyStaffEntity);
        List<StaffDTO> staffDTOs = new ArrayList<StaffDTO>();
        if (userPropertyStaffEntities != null) {
            StaffDTO staffDTO;
            for (UserPropertyStaffEntity userPropertyStaffEntity1 : userPropertyStaffEntities) {
                staffDTO = new StaffDTO();
                staffDTO.setStaffId(userPropertyStaffEntity1.getStaffId());
                staffDTO.setNikeName(userPropertyStaffEntity1.getUserName());
                staffDTO.setStaffName(userPropertyStaffEntity1.getStaffName());
                staffDTO.setMobile(userPropertyStaffEntity1.getMobile());
                staffDTOs.add(staffDTO);
            }
        }
        return staffDTOs;
    }

    @Override
    public void saveAgencyStaff(AgencyStaffDTO agencyStaffDTO) {
        UserAgencymapEntity userAgencymapEntity = new UserAgencymapEntity();
        userAgencymapEntity.setAgencyId(agencyStaffDTO.getAgencyId());
        userAgencymapEntity.setStatus("1");
        String arr[] = agencyStaffDTO.getStaffId().split(",");
        for (String item : arr) {
            userAgencymapEntity.setMapId(IdGen.uuid());
            userAgencymapEntity.setModifyTime(new Date());
            userAgencymapEntity.setStaffId(item);
            userAgencyRepository.addUserAgency(userAgencymapEntity);
        }
    }

    @Override
    public AgencyDTO getAgencyDetail(String agencyId) {
        AgencyDTO agencyDTO = new AgencyDTO();
        AgencyEntity agencyEntity = agencyRepository.getAgencyDetail(agencyId);
        agencyDTO.setAgencyId(agencyEntity.getAgencyId());
        agencyDTO.setAgencyName(agencyEntity.getAgencyName());
        agencyDTO.setAgencyType(agencyEntity.getAgencyType());
        agencyDTO.setOrderNum(agencyEntity.getOrderNum());
        agencyDTO.setOutEmploy(agencyEntity.getOutEmploy());
        agencyDTO.setStatus(agencyEntity.getStatus());
        agencyDTO.setMemo(agencyEntity.getAgencyDesc());
        if (!StringUtil.isEmpty(agencyEntity.getParentId())) {
            AgencyEntity agencyEntity1 = agencyRepository.getAgencyDetail(agencyEntity.getParentId());
            agencyDTO.setParentName(agencyEntity1.getAgencyName());
            agencyDTO.setParentId(agencyEntity.getParentId());
        }
        SupplierAgencyMapEntity supplierAgencyMapEntity = supplierAgencyRepository.getSupplierAgencys(agencyId);
        if (supplierAgencyMapEntity != null) {
            agencyDTO.setSupplierId(supplierAgencyMapEntity.getSupplierId());
        }
        agencyDTO.setModifyTime(DateUtils.format(agencyEntity.getModifyTime()));
        return agencyDTO;
    }

    @Override
    public List<AppRolesetDTO> getAppRoleSetByAgencyId(String agencyId) {
        List<AppRolesetEntity> appRolesetEntityList = roleDataRepository.getDataByAuthority(agencyId);
        List<AppRolesetDTO> appRolesetDTOs = new ArrayList<AppRolesetDTO>();
        if (appRolesetEntityList != null) {
            AppRolesetDTO appRolesetDTO;
            for (AppRolesetEntity appRolesetEntity : appRolesetEntityList) {
                appRolesetDTO = new AppRolesetDTO();
                appRolesetDTO.setAppSetId(appRolesetEntity.getAppSetId());
                appRolesetDTO.setAppSetName(appRolesetEntity.getAppSetName());
                appRolesetDTOs.add(appRolesetDTO);
            }
        }
        return appRolesetDTOs;
    }

    @Override
    public List<OrganizeProjectDTO> getProjectByAgencyId(String agencyId) {
        List<HouseProjectEntity> houseProjectEntities = roleDataRepository.getProjectByAgencyId(agencyId);
        List<OrganizeProjectDTO> projectDTOs = new ArrayList<OrganizeProjectDTO>();
        if (houseProjectEntities != null) {
            OrganizeProjectDTO organizeProjectDTO;
            for (HouseProjectEntity houseProjectEntity : houseProjectEntities) {
                organizeProjectDTO = new OrganizeProjectDTO();
                organizeProjectDTO.setProjectCode(houseProjectEntity.getPinyinCode());
                organizeProjectDTO.setProjectId(houseProjectEntity.getId());
                organizeProjectDTO.setProjectName(houseProjectEntity.getName());
                projectDTOs.add(organizeProjectDTO);
            }
        }
        return projectDTOs;
    }

    @Override
    public String importPeopleExcel(InputStream fis, UserPropertyStaffEntity userPropertyStaffEntity) {
        try {
            //创建Excel工作薄
            XSSFWorkbook hwb = new XSSFWorkbook(fis);
            //得到第一个工作表
            XSSFSheet sheet = hwb.getSheetAt(0);
            XSSFRow row = null;
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
            AgencyEntity agencyEntity1 = agencyRepository.getAgencyByAgencyName("外部合作单位");
            for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
                sheet = hwb.getSheetAt(i);
                //遍历该工作表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                    row = sheet.getRow(j);
                    //此方法调用getCellValue(HSSFCell cell)对解析出来的数据进行判断，并做相应的处理
                    if (row.getCell(0) != null) {//第一列数据 组织架构
                        String agencyName = row.getCell(0).getRichStringCellValue().getString().trim();
                        AgencyEntity agencyEntity = agencyRepository.getAgencyByName(agencyName, agencyEntity1.getAgencyId());
                        if (agencyEntity != null) {//如果有则使用已有ID
                            if (row.getCell(2) != null) {//第三列数据 用户名
//                                String userNameR = row.getCell(2).getRichStringCellValue().getString().trim();
                                String userNameR = getCellValue(row.getCell(2)).trim();
                                UserPropertyStaffEntity userPropertyStaffEntity1 = userPropertystaffReposiroty.getByUserName(userNameR);//根据用户名查询是否已被注册
                                if (userPropertyStaffEntity1 != null) {
                                    return "错误！第" + (j + 1) + "行第3列用户名已被注册！";
                                } else {
                                    UserPropertyStaffEntity userPropertyStaff = new UserPropertyStaffEntity();
                                    String pwd = PasswordEncode.digestPassword("123456");
                                    userPropertyStaff.setStaffId(IdGen.uuid());
                                    userPropertyStaff.setUserName(userNameR);//用户名
                                    userPropertyStaff.setPassword(pwd);//密码
                                    if (row.getCell(3) != null) {
                                        String staffNameR = row.getCell(3).getRichStringCellValue().getString().trim();
                                        userPropertyStaff.setStaffName(staffNameR);//姓名
                                    } else {
                                        return "错误！数据不规范！ 第" + (j + 1) + "行第4列数据为空";
                                    }
                                    userPropertyStaff.setType("OFF");//编外，自建
                                    if (row.getCell(4) != null) {
                                        row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                                        String userMobile = row.getCell(4).getStringCellValue();
                                        if (StringUtil.isMobile(userMobile)) {
                                            userPropertyStaff.setMobile(userMobile);//手机
                                        } else if (StringUtil.isFixedPhone(userMobile)) {
                                            userPropertyStaff.setMobile(userMobile);//区号+座机号码+分机号码
                                        } else {
                                            return "错误！数据不规范！ 第" + (j + 1) + "行第5列数据格式不对";
                                        }
                                    }
                                    if (row.getCell(5) != null) {//第七列数据 人员备注
                                        String memo = row.getCell(5).getRichStringCellValue().getString().trim();
                                        userPropertyStaff.setMemo(memo);
                                    }
                                    if (row.getCell(6) != null) {//第八列数据 排序
                                        row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                                        String orderNum = row.getCell(6).getStringCellValue();
                                        userPropertyStaff.setOrderNum(Integer.parseInt(orderNum));
                                    }
                                    if (row.getCell(7) != null) {//第九列数据 状态
                                        String staffState = row.getCell(7).getRichStringCellValue().getString().trim();
                                        if ("是".equals(staffState)) {
                                            userPropertyStaff.setStaffState("1");//账号有效
                                        } else if ("否".equals(staffState)) {
                                            userPropertyStaff.setStaffState("0");//账号无效
                                        } else {
                                            return "错误！ 第" + (j + 1) + "行第8列数据不规范！";
                                        }
                                    } else {
                                        return "错误！ 第" + (j + 1) + "行第8列数据为空！";
                                    }

                                    userPropertyStaff.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
                                    userPropertyStaff.setCreateOn(SqlDateUtils.getDate());//创建时间
                                    userPropertyStaff.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
                                    userPropertyStaff.setModifyOn(SqlDateUtils.getDate());//修改时间
                                    userPropertyStaff.setLogo(AppConfig.UserDefaultLogo);//员工默认头像

                                    boolean savestaff = userPropertystaffReposiroty.addStaff(userPropertyStaff);//保存人员信息
                                    if (savestaff) {
                                        UserAgencymapEntity userAgencymapEntity = new UserAgencymapEntity();
                                        BaseProjectPeopleEntity baseProjectPeopleEntity = new BaseProjectPeopleEntity();
                                        baseProjectPeopleEntity.setStatus("1");
                                        baseProjectPeopleEntity.setPeopleName(userPropertyStaff.getStaffName());
                                        baseProjectPeopleEntity.setPeopleId(userPropertyStaff.getStaffId());
                                        if ("4".equals(agencyEntity.getAgencyType())) {  //当类型为责任单位或监理时需维护基础数据表
                                            baseProjectPeopleEntity.setSupplierId(agencyEntity.getAgencyId());
                                            baseProjectPeopleEntity.setSupplierName(agencyEntity.getAgencyName());
                                            baseProjectPeopleEntity.setSupplierType("1");
                                            baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(agencyEntity.getAgencyId()));
                                            baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                                            baseProjectPeopleEntity.setModifyTime(new Date());
                                            projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);   //保存基础人员数据
                                        }
                                        if ("5".equals(agencyEntity.getAgencyType())) {
                                            baseProjectPeopleEntity.setSupplierId(agencyEntity.getAgencyId());
                                            baseProjectPeopleEntity.setSupplierName(agencyEntity.getAgencyName());
                                            baseProjectPeopleEntity.setSupplierType("2");
                                            baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(agencyEntity.getAgencyId()));
                                            baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                                            baseProjectPeopleEntity.setModifyTime(new Date());
                                            projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);
                                        }
                                        userAgencymapEntity.setMapId(IdGen.uuid());
                                        userAgencymapEntity.setStaffId(userPropertyStaff.getStaffId());
                                        userAgencymapEntity.setAgencyId(agencyEntity.getAgencyId());
                                        userAgencyRepository.addDumpUserAgency(userAgencymapEntity);   //保存用户与组织机构关系
                                    } else {
                                        return "添加人员信息出错";
                                    }
                                }
                            } else {
                                return "错误！数据不规范！第" + (j + 1) + "行第3列数据为空！";
                            }
                        } else {//如果没有这个组织机构  则新增组织机构
                            if (row.getCell(2) != null) {//第三列数据 用户名
//                                String userNameR = row.getCell(2).getRichStringCellValue().getString().trim();
                                String userNameR = getCellValue(row.getCell(2)).trim();
                                UserPropertyStaffEntity userPropertyStaffEntity1 = userPropertystaffReposiroty.getByUserName(userNameR);//根据用户名查询是否已被注册
                                if (userPropertyStaffEntity1 != null) {
                                    return "错误！第" + (j + 1) + "行第3列用户名已被注册！";
                                } else {
                                    AgencyEntity agencyEntity2 = new AgencyEntity();
                                    agencyEntity2.setAgencyId(IdGen.uuid());
                                    agencyEntity2.setParentId(agencyEntity1.getAgencyId());
                                    agencyEntity2.setAgencyName(agencyName);
                                    agencyEntity2.setAgencyType("1");
                                    agencyEntity2.setOrderNum(0);
                                    agencyEntity2.setOutEmploy("1");
                                    agencyEntity2.setAgencyDesc(agencyName);
                                    agencyEntity2.setCreateTime(new Date());
                                    agencyEntity2.setModifyTime(new Date());
                                    agencyEntity2.setStatus("1");
                                    agencyEntity2.setIsRoot("0");
                                    agencyEntity2.setAgencyPath(agencyEntity1.getAgencyPath() + "/" + agencyEntity2.getAgencyId());   //设置结构树完整路径
                                    agencyEntity2.setLevel(agencyEntity1.getLevel() + 1);
                                    boolean falg = agencyRepository.addAgencys(agencyEntity2);
                                    if (falg) {
                                        UserPropertyStaffEntity userPropertyStaff = new UserPropertyStaffEntity();
                                        String pwd = PasswordEncode.digestPassword("123456");
                                        userPropertyStaff.setStaffId(IdGen.uuid());
                                        userPropertyStaff.setUserName(userNameR);//用户名
                                        userPropertyStaff.setPassword(pwd);//密码
                                        if (row.getCell(3) != null) {
                                            String staffNameR = row.getCell(3).getRichStringCellValue().getString().trim();
                                            userPropertyStaff.setStaffName(staffNameR);//姓名
                                        } else {
                                            return "错误！数据不规范！ 第" + (j + 1) + "行第4列数据为空";
                                        }
                                        userPropertyStaff.setType("OFF");//编外，自建
                                        if (row.getCell(4) != null) {
                                            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                                            String userMobile = row.getCell(4).getStringCellValue();
                                            if (StringUtil.isMobile(userMobile)) {
                                                userPropertyStaff.setMobile(userMobile);//手机
                                            } else if (StringUtil.isFixedPhone(userMobile)) {
                                                userPropertyStaff.setMobile(userMobile);//区号+座机号码+分机号码
                                            } else {
                                                return "错误！数据不规范！ 第" + (j + 1) + "行第5列数据格式不对";
                                            }
                                        }
                                        if (row.getCell(5) != null) {//第七列数据 人员备注
                                            String memo = row.getCell(5).getRichStringCellValue().getString().trim();
                                            userPropertyStaff.setMemo(memo);
                                        }
                                        if (row.getCell(6) != null) {//第八列数据 排序
                                            row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                                            String orderNum = row.getCell(6).getStringCellValue();
                                            userPropertyStaff.setOrderNum(Integer.parseInt(orderNum));
                                        }
                                        if (row.getCell(7) != null) {//第九列数据 状态
                                            String staffState = row.getCell(7).getRichStringCellValue().getString().trim();
                                            if ("是".equals(staffState)) {
                                                userPropertyStaff.setStaffState("1");//账号有效
                                            } else if ("否".equals(staffState)) {
                                                userPropertyStaff.setStaffState("0");//账号无效
                                            } else {
                                                return "错误！ 第" + (j + 1) + "行第8列数据不规范！";
                                            }
                                        } else {
                                            return "错误！ 第" + (j + 1) + "行第8列数据为空！";
                                        }
                                        userPropertyStaff.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
                                        userPropertyStaff.setCreateOn(SqlDateUtils.getDate());//创建时间
                                        userPropertyStaff.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
                                        userPropertyStaff.setModifyOn(SqlDateUtils.getDate());//修改时间
                                        userPropertyStaff.setLogo(AppConfig.UserDefaultLogo);//员工默认头像

                                        boolean savestaff = userPropertystaffReposiroty.addStaff(userPropertyStaff);//保存人员信息
                                        if (savestaff) {
                                            UserAgencymapEntity userAgencymapEntity = new UserAgencymapEntity();
                                            BaseProjectPeopleEntity baseProjectPeopleEntity = new BaseProjectPeopleEntity();
                                            baseProjectPeopleEntity.setStatus("1");
                                            baseProjectPeopleEntity.setPeopleName(userPropertyStaff.getStaffName());
                                            baseProjectPeopleEntity.setPeopleId(userPropertyStaff.getStaffId());
                                            if ("4".equals(agencyEntity2.getAgencyType())) {  //当类型为责任单位或监理时需维护基础数据表
                                                baseProjectPeopleEntity.setSupplierId(agencyEntity2.getAgencyId());
                                                baseProjectPeopleEntity.setSupplierName(agencyEntity2.getAgencyName());
                                                baseProjectPeopleEntity.setSupplierType("1");
                                                baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(agencyEntity2.getAgencyId()));
                                                baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                                                baseProjectPeopleEntity.setModifyTime(new Date());
                                                projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);   //保存基础人员数据
                                            }
                                            if ("5".equals(agencyEntity2.getAgencyType())) {
                                                baseProjectPeopleEntity.setSupplierId(agencyEntity2.getAgencyId());
                                                baseProjectPeopleEntity.setSupplierName(agencyEntity2.getAgencyName());
                                                baseProjectPeopleEntity.setSupplierType("2");
                                                baseProjectPeopleEntity.setProjectId(staffEmployRepository.getProjectIdByDuty(agencyEntity2.getAgencyId()));
                                                baseProjectPeopleEntity.setProjectName(projectProjectRepository.getProjectProjectDetail(baseProjectPeopleEntity.getProjectId()).getName());
                                                baseProjectPeopleEntity.setModifyTime(new Date());
                                                projectPeopleRepository.addProjectPeople(baseProjectPeopleEntity);
                                            }
                                            userAgencymapEntity.setMapId(IdGen.uuid());
                                            userAgencymapEntity.setStaffId(userPropertyStaff.getStaffId());
                                            userAgencymapEntity.setAgencyId(agencyEntity2.getAgencyId());
                                            userAgencyRepository.addDumpUserAgency(userAgencymapEntity);   //保存用户与组织机构关系
                                        } else {
                                            return "添加人员信息出错";
                                        }
                                    } else {
                                        return "系统异常";
                                    }
                                }
                            } else {
                                return "错误！数据不规范！第" + (j + 1) + "行第3列数据为空！";
                            }
                        }
                    } else {
                        return "错误！第" + (j + 1) + "行第1列机构名称为空！";
                    }
                }
            }
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "未知错误！";
        }
    }

    @Override
    public String exportPeopleModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        BufferedInputStream buffer = null;
        OutputStream out = null;
        HttpSession session = httpServletRequest.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("");
        String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "importStaff.xlsx";
        File file = new File(filePath);
        if (!file.exists()) {
            return "File not found!";
        }
        try {
            buffer = new BufferedInputStream(new FileInputStream(file));
            byte[] buf = new byte[1024];
            int len = 0;
            httpServletResponse.reset(); //非常重要
            httpServletResponse.setContentType("application/x-msdownload");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(file.getName(), "UTF-8"));
            out = httpServletResponse.getOutputStream();
            while ((len = buffer.read(buf)) > 0)
                out.write(buf, 0, len);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                out.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return "ok";
    }

    @Override
    public String exportOuterPeopleModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        BufferedInputStream buffer = null;
        OutputStream out = null;
        HttpSession session = httpServletRequest.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("");
        String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "OuterStaffTemplate.xlsx";
        File file = new File(filePath);
        if (!file.exists()) {
            return "File not found!";
        }
        try {
            buffer = new BufferedInputStream(new FileInputStream(file));
            byte[] buf = new byte[1024];
            int len = 0;
            httpServletResponse.reset(); //非常重要
            httpServletResponse.setContentType("application/x-msdownload");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(file.getName(), "UTF-8"));
            out = httpServletResponse.getOutputStream();
            while ((len = buffer.read(buf)) > 0)
                out.write(buf, 0, len);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                out.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return "ok";
    }

    @Override
    public String qcExportOuterPeopleModel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        BufferedInputStream buffer = null;
        OutputStream out = null;
        HttpSession session = httpServletRequest.getSession();
        ServletContext application = session.getServletContext();
        String serverRealPath = application.getRealPath("");
        String filePath = serverRealPath + "static" + File.separator + "upload" + File.separator + "OuterStaffTemplateQC.xlsx";
        File file = new File(filePath);
        if (!file.exists()) {
            return "File not found!";
        }
        try {
            buffer = new BufferedInputStream(new FileInputStream(file));
            byte[] buf = new byte[1024];
            int len = 0;
            httpServletResponse.reset(); //非常重要
            httpServletResponse.setContentType("application/x-msdownload");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(file.getName(), "UTF-8"));
            out = httpServletResponse.getOutputStream();
            while ((len = buffer.read(buf)) > 0)
                out.write(buf, 0, len);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
                out.close();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return "ok";
    }


    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(XSSFCell cell) {
        String value = null;
        //简单的查检列类型
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC://数字
                long dd = (long) cell.getNumericCellValue();
                value = dd + "";
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }

    @Override
    public List<UserStaffDTO> getStaffListByAgencyId(String agencyId) {
        List<UserStaffDTO> userStaffDTOS = new ArrayList<UserStaffDTO>();
        List<String> idList = new ArrayList<String>();
        //获取所有机构
        List<AuthOuterAgencyEntity> authOuterAgencyEntityList = agencyRepository.getOAAgencyList();
        for (AuthOuterAgencyEntity authOuterAgencyEntity : authOuterAgencyEntityList) {
            String agencyPath = authOuterAgencyEntity.getAgencyPath().replace("/", ",").substring(1);
            String str[] = agencyPath.split(",");
            List<String> list = Arrays.asList(str);
            if (list.contains(agencyId)) {
                idList.add(authOuterAgencyEntity.getAgencyId());
            }
        }
        if (null != agencyId) {
            List<UserAgencyEntity> userAgencyEntities = userAgencyRepository.getStaffIdByAgencyId(idList);
            List<UserInformationEntity> userInformationEntities = userAgencyRepository.getStaffByAgencyId(idList);
            List<UserMapEntity> userMapEntities = userAgencyRepository.getUserMap();
            for (UserInformationEntity user : userInformationEntities) {
                UserStaffDTO userStaffDTO = new UserStaffDTO();
                userStaffDTO.setStaffIdB(user.getStaffId());
                userStaffDTO.setStaffNameB(user.getStaffName());
                userStaffDTO.setSysNameB(user.getSysName());
                userStaffDTO.setUserNameB(user.getUserName());
                userStaffDTO.setEmailB(user.getEmail());
                userStaffDTO.setMobileB(user.getMobile());
                if (agencyId.equals("401dpx")) {
                    userStaffDTO.setMobileB("***********");
                }
                userStaffDTO.setModifyOnB(DateUtils.format((Date) user.getModifyOn()));
                userStaffDTO.setIsEnabledB("0");
                for (UserMapEntity userMapEntity : userMapEntities) {
                    if (userMapEntity.getStaffId().equals(user.getStaffId())) {
                        userStaffDTO.setIsEnabledB("1");
                    }
                }
                for (UserAgencyEntity userAgencyEntity : userAgencyEntities) {
                    if (user.getStaffId().equals(userAgencyEntity.getStaffId())) {
                        for (AuthOuterAgencyEntity authOuterAgencyEntity : authOuterAgencyEntityList) {
                            if (authOuterAgencyEntity.getAgencyId().equals(userAgencyEntity.getAgencyId())) {
                                userStaffDTO.setAgencyNameB(authOuterAgencyEntity.getAgencyName());
                            }
                        }
                    }
                }
                userStaffDTOS.add(userStaffDTO);
            }
        }
        return userStaffDTOS;
    }

    @Override
    public List<UserStaffDTO> conditionQueryUser(UserStaffDTO userStaffDTO, WebPage webPage) {
        Map map = new HashMap();
        List<UserStaffDTO> userStaffDTOS = new ArrayList<>();
        List<UserStaffDTO> flagDTO = new ArrayList<>();
        List<String> idList = new ArrayList<String>();
        Boolean flag = true;
        map.put("agencyId", "");
        map.put("agencyName", "");
        map.put("staffName", "");
        map.put("userName", "");
        map.put("isEnabled", "");
        map.put("mobile", "");
        map.put("email", "");
        if (!StringUtil.isEmpty(userStaffDTO.getAgencyIdB())) {
            //获取所有机构
            List<AuthOuterAgencyEntity> authOuterAgencyEntityList = agencyRepository.getOAAgencyList();
            for (AuthOuterAgencyEntity authOuterAgencyEntity : authOuterAgencyEntityList) {
                String agencyPath = authOuterAgencyEntity.getAgencyPath().replace("/", ",").substring(1);
                String str[] = agencyPath.split(",");
                List<String> list = Arrays.asList(str);
                if (list.contains(userStaffDTO.getAgencyIdB())) {
                    idList.add(authOuterAgencyEntity.getAgencyId());
                }
            }
            flag = false;
        }
        if (!StringUtil.isEmpty(userStaffDTO.getAgencyNameB())) {
            map.put("agencyName", "%" + userStaffDTO.getAgencyNameB() + "%");
            flag = false;
        }
        if (!StringUtil.isEmpty(userStaffDTO.getStaffNameB())) {
            map.put("staffName", "%" + userStaffDTO.getStaffNameB() + "%");
            flag = false;
        }
        if (!StringUtil.isEmpty(userStaffDTO.getUserNameB())) {
            map.put("userName", "%" + userStaffDTO.getUserNameB() + "%");
            flag = false;
        }
        if (!StringUtil.isEmpty(userStaffDTO.getIsEnabledB())) {
            map.put("isEnabled", userStaffDTO.getIsEnabledB());
            flag = false;
        }
        if (!StringUtil.isEmpty(userStaffDTO.getMobileB())) {
            map.put("mobile", "%" + userStaffDTO.getMobileB() + "%");
            flag = false;
        }
        if (!StringUtil.isEmpty(userStaffDTO.getEmailB())) {
            map.put("email", "%" + userStaffDTO.getEmailB() + "%");
            flag = false;
        }
        List<Object[]> list = userAgencyRepository.getOAUserManageListByCondition(map, webPage, idList);
        for (Object[] obj : list) {
            UserStaffDTO userStaffDTO1 = new UserStaffDTO();
            userStaffDTO1.setStaffIdB(obj[0] == null ? "" : obj[0].toString());
            userStaffDTO1.setUserNameB(obj[1] == null ? "" : obj[1].toString());
            userStaffDTO1.setSysNameB(obj[2] == null ? "" : obj[2].toString());
            userStaffDTO1.setMobileB(obj[3] == null ? "" : obj[3].toString());
            userStaffDTO1.setEmailB(obj[4] == null ? "" : obj[4].toString());
            userStaffDTO1.setAgencyNameB(obj[5] == null ? "" : obj[5].toString());
            userStaffDTO1.setModifyOnB(obj[6] == null ? "" : DateUtils.format((Date) obj[6]));
            userStaffDTO1.setStaffNameB(obj[7] == null ? "" : obj[7].toString());
            userStaffDTO1.setIsEnabledB(obj[8] == null ? "" : obj[8].toString());
            if ("401dpx".equals(obj[9] == null ? "" : obj[9].toString())) {
                userStaffDTO1.setMobileB("***********");
            }
            userStaffDTOS.add(userStaffDTO1);
        }
        if (flag) {
            return flagDTO;
        } else {
            return userStaffDTOS;
        }
    }

    @Override
    public void startStaff(StaffBatchDTO staffBatchDTO) {
        if (!StringUtil.isEmpty(staffBatchDTO.getStaffIds())) {
            String str[] = staffBatchDTO.getStaffIds().split(",");
            List<String> list = Arrays.asList(str);
            for (String staffId : list) {
                UserMapEntity userMapEntity = new UserMapEntity();
                userMapEntity.setModifyOn(new Date());
                userMapEntity.setProjectModule("st");
                userMapEntity.setStaffId(staffId);
                userMapEntity.setState(staffBatchDTO.getState());
                userMapEntity.setSourceFrom(staffBatchDTO.getSourceFrom());
                agencyRepository.saveOrUpdateStaff(userMapEntity);
            }
        }
    }

    @Override
    public void startInsideStaff(StaffBatchDTO staffBatchDTO) {
        if (!StringUtil.isEmpty(staffBatchDTO.getStaffIds())) {
            String str[] = staffBatchDTO.getStaffIds().split(",");
            List<String> list = Arrays.asList(str);
            for (String staffId : list) {
                UserMapEntity userMapEntity = new UserMapEntity();
                userMapEntity.setModifyOn(new Date());
                userMapEntity.setProjectModule("st");
                userMapEntity.setStaffId(staffId);
                userMapEntity.setState(staffBatchDTO.getState());
                userMapEntity.setSourceFrom(staffBatchDTO.getSourceFrom());
                agencyRepository.saveOrUpdateStaff(userMapEntity);
            }
        }
    }

    @Override
    public Map getAgencys() {
        List<AuthOuterAgencyEntity> list = agencyRepository.getOuterAgencyList();
        Map<String, String> agencys = new LinkedHashMap<>();
        if (list.size() > 0) {
            for (AuthOuterAgencyEntity authOuterAgencyEntity : list) {
                agencys.put(authOuterAgencyEntity.getAgencyId(), authOuterAgencyEntity.getAgencyName());
            }
        }
        return agencys;
    }

    @Override
    public UserManageDTO getUserManage(String staffId) {
        List<UserAgencyEntity> userAgencyEntities = agencyRepository.getUserAgency(staffId);
        UserMapEntity userMapEntity = agencyRepository.getUserMap(staffId);
        List<UserInformationEntity> userInformationEntities = agencyRepository.getUserInformation(staffId);
        UserManageDTO userManageDTO = new UserManageDTO();
        if (null != userAgencyEntities && userAgencyEntities.size() > 0) {
            userManageDTO.setAgencyIdW(userAgencyEntities.get(0).getAgencyId());
            userManageDTO.setAgencyNameW(agencyRepository.getAgencyByAgencyId(userAgencyEntities.get(0).getAgencyId()).getAgencyName());
        }
        if (null != userInformationEntities && userInformationEntities.size() > 0) {
            userManageDTO.setEmailW(userInformationEntities.get(0).getEmail());
            userManageDTO.setMemoW(userInformationEntities.get(0).getMemo());
            userManageDTO.setMobileW(userInformationEntities.get(0).getMobile());
            userManageDTO.setStaffIdW(userInformationEntities.get(0).getStaffId());
            userManageDTO.setStaffNameW(userInformationEntities.get(0).getStaffName());
            userManageDTO.setSysNameW(userInformationEntities.get(0).getSysName());
            userManageDTO.setModifyOnW(DateUtils.format((Date) userInformationEntities.get(0).getModifyOn()));
        }
        if (null != userMapEntity) {
            userManageDTO.setStatusW(userMapEntity.getState());
        } else {
            userManageDTO.setStatusW("0");
        }
        return userManageDTO;
    }

    @Override
    public void toDeleteUser(String staffId) {
        List<UserAgencyEntity> userAgencyEntities = agencyRepository.getUserAgency(staffId);
        if (null != userAgencyEntities && userAgencyEntities.size() > 0) {
            for (UserAgencyEntity userAgencyEntity : userAgencyEntities) {
                userAgencyEntity.setStatus("0");
                userAgencyEntity.setModifyTime(new Date());
                agencyRepository.saveOrupdate(userAgencyEntity);
            }
        }
        UserMapEntity userMapEntity = agencyRepository.getUserMap(staffId);
        if (null != userMapEntity) {
            userMapEntity.setState("0");
            userMapEntity.setModifyOn(new Date());
            agencyRepository.saveOrupdate(userMapEntity);
        }
        List<UserInformationEntity> userInformationEntities = agencyRepository.getUserInformation(staffId);
        if (null != userInformationEntities && userInformationEntities.size() > 0) {
            for (UserInformationEntity userInformationEntity : userInformationEntities) {
                userInformationEntity.setStaffState("0");
                userInformationEntity.setModifyOn(new Date());
                agencyRepository.saveOrupdate(userInformationEntity);
            }
        }
    }

    @Override
    public void batchDelete(StaffBatchDTO staffBatchDTO) {
        if (!StringUtil.isEmpty(staffBatchDTO.getStaffIds())) {
            String str[] = staffBatchDTO.getStaffIds().split(",");
            List<String> list = Arrays.asList(str);
            for (String staffId : list) {
                toDeleteUser(staffId);
            }
        }
    }

    @Override
    public String updateStaff(UserManageDTO userManageDTO, UserInformationEntity userPropertyStaffEntity) {
        UserInformationEntity userInformationEntity1 = agencyRepository.getUserByNameAndId(userManageDTO.getSysNameW(), userManageDTO.getStaffIdW());
        if (null != userInformationEntity1) {
            return "该用户名已被注册！";
        }
        UserInformationEntity userInformationEntity = agencyRepository.getUserById(userManageDTO.getStaffIdW());
        userInformationEntity.setSysName(userManageDTO.getMobileW());//手机号作为登录账号
        userInformationEntity.setStaffName(userManageDTO.getStaffNameW());//姓名
        userInformationEntity.setMobile(userManageDTO.getMobileW());//手机
        userInformationEntity.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
        userInformationEntity.setModifyOn(new Date());//修改时间
        userInformationEntity.setEmail(userManageDTO.getEmailW());//邮箱
        userInformationEntity.setMemo(userManageDTO.getMemoW());//备注
        agencyRepository.saveOrupdate(userInformationEntity);
        //修改启用状态
        if (null != userManageDTO.getStatusW()) {
            UserMapEntity userMapEntity = agencyRepository.getUserMap(userManageDTO.getStaffIdW());
            userMapEntity.setModifyOn(new Date());//修改时间
            if ("".equals(userManageDTO.getStatusW())) {
                userMapEntity.setState("0");//启用状态
            } else {
                userMapEntity.setState(userManageDTO.getStatusW());//启用状态
            }
            agencyRepository.saveOrupdate(userMapEntity);
        }
        //关联组织机构
        if (null != userManageDTO.getAgencyIdW() && !StringUtil.isEmpty(userManageDTO.getAgencyIdW())) {
            agencyRepository.delUserAgencyMap(userManageDTO.getStaffIdW(), new Date());
            String agencyId[] = userManageDTO.getAgencyIdW().split(",");
            for (int i = 0; i < agencyId.length; i++) {
                UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
                userAgencyEntity.setMapId(IdGen.uuid());//主键id
                userAgencyEntity.setModifyTime(new Date());//修改 时间
                userAgencyEntity.setStatus("1");//状态
                userAgencyEntity.setAgencyId(agencyId[i].toString());//组织机构Id
                userAgencyEntity.setStaffId(userInformationEntity.getStaffId());//人员id
                userAgencyEntity.setSourceFrom("external");
                agencyRepository.saveOrUpdateUserAgencyMap(userAgencyEntity);  //保存组织机构关系
            }
        }
        return "ok";
    }

    @Override
    public String saveStaff(UserManageDTO userManageDTO, UserInformationEntity userPropertyStaffEntity) {
        if (null != userManageDTO.getMobileW() && !StringUtil.isEmpty(userManageDTO.getMobileW())) {
            UserInformationEntity userInformationEntity1 = agencyRepository.getUserByName(userManageDTO.getMobileW());
            if (null != userInformationEntity1) {
                return "该用户名已被注册！";
            }
            UserInformationEntity userInformationEntity = new UserInformationEntity();
            String pwd = PasswordEncode.digestPassword("123456");//默认密码是123456
            userInformationEntity.setStaffId(IdGen.uuid());//id
            userInformationEntity.setSysName(userManageDTO.getMobileW());//手机号作为登录账号
            userInformationEntity.setPassword(pwd);//密码
            userInformationEntity.setStaffName(userManageDTO.getStaffNameW());//姓名
            userInformationEntity.setStaffState("1");//是否启用
            userInformationEntity.setMobile(userManageDTO.getMobileW());//手机
            userInformationEntity.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
            userInformationEntity.setCreateOn(new Date());//创建时间
            userInformationEntity.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
            userInformationEntity.setModifyOn(new Date());//修改时间
            userInformationEntity.setLogo(AppConfig.UserDefaultLogo);//员工默认头像
            userInformationEntity.setEmail(userManageDTO.getEmailW());
            userInformationEntity.setJinmaoIs("0");
            userInformationEntity.setMemo(userManageDTO.getMemoW());
            agencyRepository.saveOrupdate(userInformationEntity);
            //启用此账号
            UserMapEntity userMapEntity = new UserMapEntity();
            userMapEntity.setStaffId(userInformationEntity.getStaffId());//人员Id
            userMapEntity.setModifyOn(new Date());//修改时间
            userMapEntity.setState("0");
            userMapEntity.setSourceFrom("external");//来源
            if (userManageDTO.getStatusW().equals("1")) {
                userMapEntity.setState("1");//启用状态
            }
            userMapEntity.setProjectModule("st");
            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);   //保存用户启用关系表
            userMapEntity.setState("0");
            userMapEntity.setProjectModule("qc");
            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
            userMapEntity.setProjectModule("es");
            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
            //关联组织机构
            if (null != userManageDTO.getAgencyIdW() && !StringUtil.isEmpty(userManageDTO.getAgencyIdW())) {
                String agencyId[] = userManageDTO.getAgencyIdW().split(",");
                for (int i = 0; i < agencyId.length; i++) {
                    UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
                    userAgencyEntity.setMapId(IdGen.uuid());//主键id
                    userAgencyEntity.setModifyTime(new Date());//修改 时间
                    userAgencyEntity.setStatus("1");//状态
                    userAgencyEntity.setAgencyId(agencyId[i].toString());//组织机构Id
                    userAgencyEntity.setStaffId(userInformationEntity.getStaffId());//人员id
                    userAgencyEntity.setSourceFrom("external");
                    agencyRepository.saveOrupdate(userAgencyEntity);  //保存组织机构关系
                }
            }
            return "ok";
        }
        return "错误，手机号码不能为空";
    }

    @Override
    public ApiResult saveOuterAgency(OuterAgencyDTO outerAgencyDTO, UserInformationEntity userPropertyStaffEntity) {
        if (null != outerAgencyDTO) {
            AuthOuterAgencyEntity authOuterAgencyEntity = new AuthOuterAgencyEntity();
            authOuterAgencyEntity.setAgencyId(IdGen.uuid());//组织机构Id
            authOuterAgencyEntity.setAgencyName(outerAgencyDTO.getAgencyName());//组织机构名称
            authOuterAgencyEntity.setAgencyDesc(outerAgencyDTO.getMemo());
            authOuterAgencyEntity.setStatus("0");
            if (null != outerAgencyDTO.getStatus() && !StringUtil.isEmpty(outerAgencyDTO.getStatus())) {
                authOuterAgencyEntity.setStatus(outerAgencyDTO.getStatus());
            }
            authOuterAgencyEntity.setAgencyType("3");
            authOuterAgencyEntity.setCreateTime(new Date());
            authOuterAgencyEntity.setCreateBy(userPropertyStaffEntity.getStaffId());
            authOuterAgencyEntity.setModifyTime(new Date());
            authOuterAgencyEntity.setModifyBy(userPropertyStaffEntity.getStaffId());
            authOuterAgencyEntity.setOutEmploy("1");
            authOuterAgencyEntity.setParentId("03d3df6a599747ef9bfa4332c0f919b6");//在外部合作单位下
            authOuterAgencyEntity.setAgencyPath("/1/03d3df6a599747ef9bfa4332c0f919b6/" + authOuterAgencyEntity.getAgencyId());
            authOuterAgencyEntity.setLevel(3);
            authOuterAgencyEntity.setIsTemporary("0");//是否为临时组
            if (null != outerAgencyDTO.getAgencyId()) {
                AuthOuterAgencyEntity authOuterAgencyEntity1 = agencyRepository.getAgencyByAgencyId(outerAgencyDTO.getAgencyId());
                String agencyPath = authOuterAgencyEntity1.getAgencyPath().replace("/", ",").substring(1);
                String str[] = agencyPath.split(",");
                List<String> list = Arrays.asList(str);
                if (list.contains("3bc5d7defff14c3eb90c14d2a28082e5")) {
                    authOuterAgencyEntity.setIsTemporary("1");//是临时组
                    authOuterAgencyEntity.setTemporaryScope("st");//临时组生效范围
                }
                authOuterAgencyEntity.setParentId(outerAgencyDTO.getAgencyId());
                authOuterAgencyEntity.setAgencyPath(authOuterAgencyEntity1.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId());
                authOuterAgencyEntity.setLevel(authOuterAgencyEntity1.getLevel() + 1);
            }
            agencyRepository.saveOrupdate(authOuterAgencyEntity);
            return new SuccessApiResult("ok");
        } else {
            return new SuccessApiResult("没有可操作数据");
        }
    }


    @Override
    public ApiResult delOuterAgency(String agencyId, UserInformationEntity userPropertyStaffEntity) {
        try {
            if (null != agencyId) {
                if ("3bc5d7defff14c3eb90c14d2a28082e5".equals(agencyId) || "03d3df6a599747ef9bfa4332c0f919b6".equals(agencyId)) {
                    return new SuccessApiResult("错误，无法删除‘临时组’与‘外部合作单位’根目录！");
                } else {
                    AuthOuterAgencyEntity authOuterAgencyEntity = agencyRepository.getAgencyByAgencyId(agencyId);
                    authOuterAgencyEntity.setStatus("0");
                    authOuterAgencyEntity.setModifyBy(userPropertyStaffEntity.getStaffId());
                    authOuterAgencyEntity.setModifyTime(new Date());
                    agencyRepository.saveOrupdate(authOuterAgencyEntity);
                    return new SuccessApiResult("ok");
                }
            } else {
                return new SuccessApiResult("参数错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new SuccessApiResult("操作失败，请联系管理员！");
        }
    }

    @Override
    public List<EnabledUserDTO> getEnabledUserList(EnabledUserDTO enabledUserDTO, WebPage webPage) {
        Map map = new HashMap();
        map.put("agencyName", ""); //所属机构
        map.put("staffName", "");//人员
        map.put("userName", "");//OA账号
        map.put("sourceFrom", enabledUserDTO.getSourceFromE());
        map.put("mobile", "");//电话
        map.put("email", "");//邮箱
        if (!StringUtil.isEmpty(enabledUserDTO.getAgencyNameE())) {
            map.put("agencyName", "%" + enabledUserDTO.getAgencyNameE() + "%");//所属机构
        }
        if (!StringUtil.isEmpty(enabledUserDTO.getStaffNameE())) {
            map.put("staffName", "%" + enabledUserDTO.getStaffNameE() + "%");//人员
        }
        if (!StringUtil.isEmpty(enabledUserDTO.getUserNameE())) {
            map.put("userName", "%" + enabledUserDTO.getUserNameE() + "%");//OA账号
        }
        if (!StringUtil.isEmpty(enabledUserDTO.getMobileE())) {
            map.put("mobile", "%" + enabledUserDTO.getMobileE() + "%");//电话
        }
        if (!StringUtil.isEmpty(enabledUserDTO.getEmailE())) {
            map.put("email", "%" + enabledUserDTO.getEmailE() + "%");//邮箱
        }
        List<EnabledUserDTO> enabledUserDTOS = new ArrayList<EnabledUserDTO>();
        List<Object[]> list = agencyRepository.getEnabledUserList(map, webPage);
        for (Object[] obj : list) {
            EnabledUserDTO enabledUserDTO1 = new EnabledUserDTO();
            enabledUserDTO1.setStaffIdE(obj[0] == null ? "" : obj[0].toString());
            enabledUserDTO1.setUserNameE(obj[1] == null ? "" : obj[1].toString());
            enabledUserDTO1.setSysNameE(obj[2] == null ? "" : obj[2].toString());
            enabledUserDTO1.setStaffNameE(obj[3] == null ? "" : obj[3].toString());
            enabledUserDTO1.setMobileE(obj[4] == null ? "" : obj[4].toString());
            enabledUserDTO1.setEmailE(obj[5] == null ? "" : obj[5].toString());
            enabledUserDTO1.setModifyOnE(obj[6] == null ? "" : DateUtils.format((Date) obj[6]));
            enabledUserDTO1.setSourceFromE(obj[7] == null ? "" : obj[7].toString());
            List<String> agencyNameList = agencyRepository.getAgencyAllNameByStaffId(enabledUserDTO1.getStaffIdE());
            String agencyName = "";  //所属机构
            if (null != agencyNameList && agencyNameList.size() > 0) {
                for (int i = 0; i < agencyNameList.size(); i++) {
                    if (i == 0) {
                        agencyName = agencyNameList.get(i).toString();
                    } else {
                        agencyName = agencyName + " ," + agencyNameList.get(i).toString();
                    }
                }
            }
            enabledUserDTO1.setAgencyNameE(agencyName);
            enabledUserDTOS.add(enabledUserDTO1);
        }
        return enabledUserDTOS;
    }

    @Override
    public List<OuterUserDTO> getOuterUserList(OuterUserDTO outerUserDTO, WebPage webPage) {
        List<OuterUserDTO> outerUserDTOList = new ArrayList<OuterUserDTO>();
        List<OuterUserDTO> flagDTO = new ArrayList<OuterUserDTO>();
        Boolean flag = true;
        Map map = new HashMap();
        map.put("agencyName", "");//所属机构
        map.put("staffName", "");//人员
        map.put("sysName", "");// 系统账号
        map.put("state", outerUserDTO.getStateO());//启用状态
        map.put("mobile", "");//电话
        map.put("email", "");//邮箱
        map.put("agencyId", "");//机构Id
        if (!StringUtil.isEmpty(outerUserDTO.getAgencyNameO())) {
            map.put("agencyName", "%" + outerUserDTO.getAgencyNameO() + "%");//所属机构
            flag = false;
        }
        if (!StringUtil.isEmpty(outerUserDTO.getStaffNameO())) {
            map.put("staffName", "%" + outerUserDTO.getStaffNameO() + "%");//人员
            flag = false;
        }
        if (!StringUtil.isEmpty(outerUserDTO.getSysNameO())) {
            map.put("sysName", "%" + outerUserDTO.getSysNameO() + "%");// 系统账号
            flag = false;
        }
        if (!StringUtil.isEmpty(outerUserDTO.getMobileO())) {
            map.put("mobile", "%" + outerUserDTO.getMobileO() + "%");//电话
            flag = false;
        }
        if (!StringUtil.isEmpty(outerUserDTO.getEmailO())) {
            map.put("email", "%" + outerUserDTO.getEmailO() + "%");//邮箱
            flag = false;
        }
        if (!StringUtil.isEmpty(outerUserDTO.getAgencyIdO())) {
            map.put("agencyId", outerUserDTO.getAgencyIdO());//机构Id
            flag = false;
        }
        if (!StringUtil.isEmpty(outerUserDTO.getStateO())) {
            flag = false;
        }
        List<Object[]> list = agencyRepository.getOuterUserList(map, webPage);
        for (Object[] obj : list) {
            OuterUserDTO outerUserDTO1 = new OuterUserDTO();
            outerUserDTO1.setStaffIdO(obj[0] == null ? "" : obj[0].toString());
            outerUserDTO1.setSysNameO(obj[1] == null ? "" : obj[1].toString());
            outerUserDTO1.setStaffNameO(obj[2] == null ? "" : obj[2].toString());
            outerUserDTO1.setMobileO(obj[3] == null ? "" : obj[3].toString());
            outerUserDTO1.setEmailO(obj[4] == null ? "" : obj[4].toString());
            outerUserDTO1.setModifyOnO(obj[5] == null ? "" : DateUtils.format((Date) obj[5]));
            outerUserDTO1.setStateO(obj[6] == null ? "" : obj[6].toString());
            List<String> agencyNameList = agencyRepository.getAgencyNameByStaffId(outerUserDTO1.getStaffIdO());
            String agencyName = "";  //所属机构
            if (null != agencyNameList && agencyNameList.size() > 0) {
                for (int i = 0; i < agencyNameList.size(); i++) {
                    if (i == 0) {
                        agencyName = agencyNameList.get(i).toString();
                    } else {
                        agencyName = agencyName + " ," + agencyNameList.get(i).toString();
                    }
                }
            }
            outerUserDTO1.setAgencyNameO(agencyName);
            outerUserDTOList.add(outerUserDTO1);
        }
        if (flag) {
            return flagDTO;
        } else {
            return outerUserDTOList;
        }
    }

    @Override
    public void enabledUserExcel(String title, String[] headers, ServletOutputStream out, EnabledUserDTO enabledUserDTO) {
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        List<EnabledUserDTO> enabledUserDTOS = this.getEnabledUserList(enabledUserDTO, webPage);
        List<EnabledUserExcelDTO> enabledUserExcelDTOS = new ArrayList<EnabledUserExcelDTO>();
        ExportExcel<EnabledUserExcelDTO> ex = new ExportExcel();
        if (null != enabledUserDTOS && enabledUserDTOS.size() > 0) {
            for (EnabledUserDTO enabledUserDTO1 : enabledUserDTOS) {
                EnabledUserExcelDTO enabledUserExcelDTO = new EnabledUserExcelDTO();
                enabledUserExcelDTO.setStaffName(enabledUserDTO1.getStaffNameE());
                enabledUserExcelDTO.setUserName(enabledUserDTO1.getUserNameE());
                enabledUserExcelDTO.setSysName(enabledUserDTO1.getSysNameE());
                enabledUserExcelDTO.setSourceFrom(enabledUserDTO1.getSourceFromE());
                if ("external".equals(enabledUserDTO1.getSourceFromE())) {
                    enabledUserExcelDTO.setSourceFrom("外部添加");
                }
                enabledUserExcelDTO.setMobile(enabledUserDTO1.getMobileE());
                enabledUserExcelDTO.setEmail(enabledUserDTO1.getEmailE());
                enabledUserExcelDTO.setAgencyName(enabledUserDTO1.getAgencyNameE());
                enabledUserExcelDTOS.add(enabledUserExcelDTO);
            }
        }
        ex.exportExcel(title, headers, enabledUserExcelDTOS, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    @Override
    public void outerUserExcel(String title, String[] headers, ServletOutputStream out, OuterUserDTO outerUserDTO) {
        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        List<OuterUserDTO> outerUserDTOList = this.getOuterUserList(outerUserDTO, webPage);
        List<OuterUserExcelDTO> outerUserExcelDTOS = new ArrayList<OuterUserExcelDTO>();
        ExportExcel<OuterUserExcelDTO> ex = new ExportExcel();
        if (null != outerUserDTOList && outerUserDTOList.size() > 0) {
            for (OuterUserDTO outerUserDTO1 : outerUserDTOList) {
                OuterUserExcelDTO outerUserExcelDTO = new OuterUserExcelDTO();
                outerUserExcelDTO.setStaffName(outerUserDTO1.getStaffNameO());
                outerUserExcelDTO.setSysName(outerUserDTO1.getSysNameO());
                outerUserExcelDTO.setMobile(outerUserDTO1.getMobileO());
                outerUserExcelDTO.setEmail(outerUserDTO1.getEmailO());
                outerUserExcelDTO.setAgencyName(outerUserDTO1.getAgencyNameO());
                outerUserExcelDTOS.add(outerUserExcelDTO);
            }
        }
        ex.exportExcel(title, headers, outerUserExcelDTOS, out, "yyyy-MM-dd");
        System.out.println("excel导出成功！");
    }

    @Override
    public String importOuterPeopleExcel(InputStream fis, UserInformationEntity userPropertyStaffEntity) {
        try {
            //创建Excel工作薄
            XSSFWorkbook hwb = new XSSFWorkbook(fis);
            //得到第一个工作表
            XSSFSheet sheet = hwb.getSheetAt(0);
            XSSFRow row = null;
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
            for (int i = 0; i < hwb.getNumberOfSheets(); i++) {
                sheet = hwb.getSheetAt(i);
                //遍历该工作表所有的行,j表示行数 getPhysicalNumberOfRows行的总数
                for (int j = 1; j < sheet.getPhysicalNumberOfRows(); j++) {
                    row = sheet.getRow(j);
                    //此方法调用getCellValue(HSSFCell cell)对解析出来的数据进行判断，并做相应的处理
                    if (row.getCell(0) != null) {//第一列数据 组织架构
                        String agencyName = row.getCell(0).getRichStringCellValue().getString().trim();
                        //查看组织架构名是否已存在
                        AuthOuterAgencyEntity authOuterAgencyEntity1 = agencyRepository.getOuterAgencyByName(agencyName);
                        if (authOuterAgencyEntity1 != null) {//如果有则使用已有ID
                            if (row.getCell(3) != null) {//第四列数据 联系方式 做为用户名
                                String userNameR = getCellValue(row.getCell(3)).trim();
                                UserInformationEntity userInformationEntity = userPropertystaffReposiroty.getStaffBySysName(userNameR);//根据用户名查询是否已被注册
                                if (userInformationEntity != null) {
//                                    if(userInformationEntity.getStaffState().equals("0")){
//                                        userInformationEntity.setStaffState("1");
//                                        agencyRepository.saveOrupdate(userInformationEntity);
//                                    }
//                                    UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
//                                    userAgencyEntity.setMapId(IdGen.uuid());
//                                    userAgencyEntity.setStaffId(userInformationEntity.getStaffId());
//                                    userAgencyEntity.setAgencyId(authOuterAgencyEntity1.getAgencyId());
//                                    userAgencyEntity.setModifyTime(new Date());
//                                    userAgencyEntity.setStatus("1");
//                                    userAgencyEntity.setSourceFrom("external");
//                                    userAgencyRepository.addOuterUserAgency(userAgencyEntity);   //保存用户与组织机构关系
                                    return "错误！第" + (j + 1) + "行第4列用户名已被注册！";
//                                    如果存在  则保存人员和组织架构的关系
                                } else {
                                    UserInformationEntity userInformationEntity1 = new UserInformationEntity();
                                    String pwd = PasswordEncode.digestPassword("123456");
                                    userInformationEntity1.setStaffId(IdGen.uuid());
                                    userInformationEntity1.setSysName(userNameR);//用户名
                                    userInformationEntity1.setPassword(pwd);//密码
                                    if (row.getCell(2) != null) {//姓名 第三列 姓名
                                        String staffNameR = row.getCell(2).getRichStringCellValue().getString().trim();
                                        userInformationEntity1.setStaffName(staffNameR);//姓名
                                    } else {
                                        return "错误！数据不规范！ 第" + (j + 1) + "行第3列姓名数据为空";
                                    }
                                    userInformationEntity1.setJinmaoIs("0");//编外，自建
                                    if (row.getCell(3) != null) {//手机
                                        row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                                        String userMobile = row.getCell(3).getStringCellValue();
                                        if (StringUtil.isMobile(userMobile)) {
                                            userInformationEntity1.setMobile(userMobile);//手机
                                        } else if (StringUtil.isFixedPhone(userMobile)) {
                                            userInformationEntity1.setMobile(userMobile);//区号+座机号码+分机号码
                                        }
                                    }
                                    if (row.getCell(4) != null) {//第5列数据 人员备注
                                        String memo = row.getCell(4).getRichStringCellValue().getString().trim();
                                        userInformationEntity1.setMemo(memo);
                                    }
                                    if (row.getCell(5) != null) {//第6列数据 排序
                                        row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                                        String orderNum = row.getCell(5).getStringCellValue();
                                        userInformationEntity1.setOrderNum(Integer.parseInt(orderNum));
                                    }
                                    if (row.getCell(6) != null) {//第7列数据 状态
                                        String staffState = row.getCell(6).getRichStringCellValue().getString().trim();
                                        if ("是".equals(staffState)) {
                                            UserMapEntity userMapEntity = new UserMapEntity();
                                            userMapEntity.setSourceFrom("external");
                                            userMapEntity.setStaffId(userInformationEntity1.getStaffId());
                                            userMapEntity.setState("1");
                                            userMapEntity.setModifyOn(new Date());
                                            userMapEntity.setProjectModule("st");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);//保存用户启用关系表
                                            userMapEntity.setProjectModule("qc");
                                            userMapEntity.setState("0");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                            userMapEntity.setProjectModule("es");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                        } else if ("否".equals(staffState)) {
                                            UserMapEntity userMapEntity = new UserMapEntity();
                                            userMapEntity.setSourceFrom("external");
                                            userMapEntity.setStaffId(userInformationEntity1.getStaffId());
                                            userMapEntity.setState("0");
                                            userMapEntity.setModifyOn(new Date());
                                            userMapEntity.setProjectModule("st");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);  //保存用户启用关系表
                                            userMapEntity.setProjectModule("qc");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                            userMapEntity.setProjectModule("es");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                        } else {
                                            return "错误！ 第" + (j + 1) + "行第7列数据不规范！";
                                        }
                                    } else {
                                        return "错误！ 第" + (j + 1) + "行第7列数据为空！";
                                    }
                                    userInformationEntity1.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
                                    userInformationEntity1.setCreateOn(SqlDateUtils.getDate());//创建时间
                                    userInformationEntity1.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
                                    userInformationEntity1.setModifyOn(SqlDateUtils.getDate());//修改时间
                                    userInformationEntity1.setLogo(AppConfig.UserDefaultLogo);//员工默认头像
                                    userInformationEntity1.setStaffState("1");//账号有效
                                    boolean savestaff = userPropertystaffReposiroty.addOuterStaff(userInformationEntity1);//保存人员信息
                                    if (savestaff) {
                                        UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
                                        userAgencyEntity.setMapId(IdGen.uuid());
                                        userAgencyEntity.setStaffId(userInformationEntity1.getStaffId());
                                        userAgencyEntity.setAgencyId(authOuterAgencyEntity1.getAgencyId());
                                        userAgencyEntity.setModifyTime(new Date());
                                        userAgencyEntity.setStatus("1");
                                        userAgencyEntity.setSourceFrom("external");
                                        agencyRepository.saveOrupdate(userAgencyEntity);   //保存用户与组织机构关系
                                    } else {
                                        return "添加人员信息出错";
                                    }
                                }
                            } else {
                                return "错误！数据不规范！第" + (j + 1) + "行第4列数据为空！";
                            }
                        } else {//如果没有这个组织机构  则新增组织机构
                            AuthOuterAgencyEntity outerAgencyEntity2 = new AuthOuterAgencyEntity();
                            outerAgencyEntity2.setAgencyId(IdGen.uuid());//组织机构Id
                            outerAgencyEntity2.setAgencyName(agencyName);//组织机构名称
                            outerAgencyEntity2.setStatus("1");
                            outerAgencyEntity2.setCreateTime(new Date());
                            outerAgencyEntity2.setCreateBy(userPropertyStaffEntity.getStaffId());
                            outerAgencyEntity2.setModifyTime(new Date());
                            outerAgencyEntity2.setModifyBy(userPropertyStaffEntity.getStaffId());
                            outerAgencyEntity2.setOutEmploy("1");
                            outerAgencyEntity2.setParentId("03d3df6a599747ef9bfa4332c0f919b6");//在外部合作单位下
                            outerAgencyEntity2.setAgencyPath("/1/03d3df6a599747ef9bfa4332c0f919b6/" + outerAgencyEntity2.getAgencyId());
                            outerAgencyEntity2.setLevel(3);
                            outerAgencyEntity2.setIsTemporary("0");//不是临时组
                            agencyRepository.saveOrupdate(outerAgencyEntity2);
                            if (row.getCell(3) != null) {//第4列数据 用户名
                                String userNameR = getCellValue(row.getCell(3)).trim();
                                UserInformationEntity userInformationEntity = userPropertystaffReposiroty.getStaffBySysName(userNameR);//根据用户名查询是否已被注册
                                if (userInformationEntity != null) {
//                                    if(userInformationEntity.getStaffState().equals("0")){
//                                        userInformationEntity.setStaffState("1");
//                                        agencyRepository.saveOrupdate(userInformationEntity);
//                                    }
//                                    UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
//                                    userAgencyEntity.setMapId(IdGen.uuid());
//                                    userAgencyEntity.setStaffId(userInformationEntity.getStaffId());
//                                    userAgencyEntity.setAgencyId(outerAgencyEntity2.getAgencyId());
//                                    userAgencyEntity.setModifyTime(new Date());
//                                    userAgencyEntity.setStatus("1");
//                                    userAgencyEntity.setSourceFrom("external");
//                                    agencyRepository.saveOrupdate(userAgencyEntity);   //保存用户与组织机构关系
                                    return "错误！第" + (j + 1) + "行第4列用户名已被注册！";
                                } else {
                                    UserInformationEntity userInformationEntity1 = new UserInformationEntity();
                                    String pwd = PasswordEncode.digestPassword("123456");
                                    userInformationEntity1.setStaffId(IdGen.uuid());
                                    userInformationEntity1.setSysName(userNameR);//用户名
                                    userInformationEntity1.setPassword(pwd);//密码
                                    if (row.getCell(2) != null) {//姓名 第三列 姓名
                                        String staffNameR = row.getCell(2).getRichStringCellValue().getString().trim();
                                        userInformationEntity1.setStaffName(staffNameR);//姓名
                                    } else {
                                        return "错误！数据不规范！ 第" + (j + 1) + "行第3列姓名数据为空";
                                    }
                                    userInformationEntity1.setJinmaoIs("0");//编外，自建
                                    row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                                    String mobile = row.getCell(3).getStringCellValue();
                                    if (StringUtil.isMobile(mobile)) {
                                        userInformationEntity1.setMobile(mobile);//手机
                                    } else if (StringUtil.isFixedPhone(mobile)) {
                                        userInformationEntity1.setMobile(mobile);//区号+座机号码+分机号码
                                    }
                                    if (row.getCell(4) != null) {//第5列数据 人员备注
                                        String memo = row.getCell(4).getRichStringCellValue().getString().trim();
                                        userInformationEntity1.setMemo(memo);
                                    }
                                    if (row.getCell(5) != null) {//第6列数据 排序
                                        row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                                        String orderNum = row.getCell(5).getStringCellValue();
                                        userInformationEntity1.setOrderNum(Integer.parseInt(orderNum));
                                    }
                                    if (row.getCell(6) != null) {//第7列数据 状态
                                        String state = row.getCell(6).getRichStringCellValue().getString().trim();
                                        if ("是".equals(state)) {
                                            UserMapEntity userMapEntity = new UserMapEntity();
                                            userMapEntity.setSourceFrom("external");
                                            userMapEntity.setStaffId(userInformationEntity1.getStaffId());
                                            userMapEntity.setState("1");
                                            userMapEntity.setModifyOn(new Date());
                                            userMapEntity.setProjectModule("st");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);   //保存用户启用关系表
                                            userMapEntity.setProjectModule("qc");
                                            userMapEntity.setState("0");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                            userMapEntity.setProjectModule("es");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                        } else if ("否".equals(state)) {
                                            UserMapEntity userMapEntity = new UserMapEntity();
                                            userMapEntity.setSourceFrom("external");
                                            userMapEntity.setStaffId(userInformationEntity1.getStaffId());
                                            userMapEntity.setState("0");
                                            userMapEntity.setModifyOn(new Date());
                                            userMapEntity.setProjectModule("st");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);   //保存用户启用关系表
                                            userMapEntity.setProjectModule("qc");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                            userMapEntity.setProjectModule("es");
                                            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                        } else {
                                            return "错误！ 第" + (j + 1) + "行第7列数据不规范！";
                                        }
                                    } else {
                                        return "错误！ 第" + (j + 1) + "行第7列数据为空！";
                                    }
                                    userInformationEntity1.setCreateBy(userPropertyStaffEntity.getStaffName());//创建人
                                    userInformationEntity1.setCreateOn(SqlDateUtils.getDate());//创建时间
                                    userInformationEntity1.setModifyBy(userPropertyStaffEntity.getStaffName());//修改人
                                    userInformationEntity1.setModifyOn(SqlDateUtils.getDate());//修改时间
                                    userInformationEntity1.setLogo(AppConfig.UserDefaultLogo);//员工默认头像
                                    userInformationEntity1.setStaffState("1");//账号有效
                                    boolean savestaff = userPropertystaffReposiroty.addOuterStaff(userInformationEntity1);//保存人员信息
                                    if (savestaff) {
                                        UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
                                        userAgencyEntity.setMapId(IdGen.uuid());
                                        userAgencyEntity.setStaffId(userInformationEntity1.getStaffId());
                                        userAgencyEntity.setAgencyId(outerAgencyEntity2.getAgencyId());
                                        userAgencyEntity.setModifyTime(new Date());
                                        userAgencyEntity.setStatus("1");
                                        userAgencyEntity.setSourceFrom("external");
                                        agencyRepository.saveOrupdate(userAgencyEntity);   //保存用户与组织机构关系
                                    } else {
                                        return "添加人员信息出错";
                                    }
                                }
                            } else {
                                return "错误！数据不规范！第" + (j + 1) + "行第4列数据为空！";
                            }
                        }
                    } else {
                        return "错误！第" + (j + 1) + "行第1列机构名称为空！";
                    }
                }
            }
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "未知错误！";
        }
    }

    @Override
    public AgencyStateDTO getDelAgencyState(String agencyId) {
        AgencyStateDTO agencyStateDTO = new AgencyStateDTO();
        if (null != agencyId) {
            AuthOuterAgencyEntity authOuterAgencyEntity = agencyRepository.getAgencyByAgencyId(agencyId);
            if (null != authOuterAgencyEntity) {
                List<String> list = agencyRepository.getUserByAgencyId(agencyId);
                agencyStateDTO.setDelAgencyId(agencyId);
                agencyStateDTO.setDelAgencyName(authOuterAgencyEntity.getAgencyName());
                agencyStateDTO.setDelIs("1");
                if (null != list && list.size() > 0) {
                    agencyStateDTO.setDelIs("0");
                }
            }
        }
        return agencyStateDTO;
    }

    @Override
    public String getOAUserData() {
        try {
            List<StaffCRMEntity> staffCRMEntities = userAgencyRepository.getStaffByOA();
            if (null != staffCRMEntities && staffCRMEntities.size() > 0) {
                for (StaffCRMEntity staffCRMEntity : staffCRMEntities) {
                    if (null != staffCRMEntity.getLoginid() && !StringUtil.isEmpty(staffCRMEntity.getLoginid())) {
                        UserInformationEntity user = null;
                        user = agencyRepository.getUserByUserId(staffCRMEntity.getUserid().toString());
                        if (null == user) {
                            user = agencyRepository.getUserById(staffCRMEntity.getUserid().toString());
                        }
                        UserInformationEntity userNameRepetition = agencyRepository.getUserByName(staffCRMEntity.getLoginid());//判断登录名相同的用户
                        if (null != userNameRepetition) {
                            if (null != userNameRepetition.getUserId() && !StringUtil.isEmpty(userNameRepetition.getUserId())) {//判断该用户是否是OA新同步的用户
                                if (!userNameRepetition.getUserId().equals(staffCRMEntity.getUserid().toString())) {//是OA新同步的用户判断其userID是否相同
                                    if (null != userNameRepetition.getMemo()) {
                                        if (!"userName重复_0".equals(userNameRepetition.getMemo()) || !"userName重复_1".equals(userNameRepetition.getMemo())) {
                                            userNameRepetition.setMemo("userName重复_" + userNameRepetition.getStaffState());
                                        }
                                    } else {
                                        userNameRepetition.setMemo("userName重复_" + userNameRepetition.getStaffState());
                                    }
                                    userNameRepetition.setStaffState("0");
                                    userAgencyRepository.saveOrUpdateUser_2(userNameRepetition);
                                }
                            } else {
                                if (!userNameRepetition.getStaffId().equals(staffCRMEntity.getUserid().toString())) {
                                    if (null != userNameRepetition.getMemo()) {
                                        if (!"userName重复_0".equals(userNameRepetition.getMemo()) || !"userName重复_1".equals(userNameRepetition.getMemo())) {
                                            userNameRepetition.setMemo("userName重复_" + userNameRepetition.getStaffState());
                                        }
                                    } else {
                                        userNameRepetition.setMemo("userName重复_" + userNameRepetition.getStaffState());
                                    }
                                    userNameRepetition.setStaffState("0");
                                    userAgencyRepository.saveOrUpdateUser_2(userNameRepetition);
                                }
                            }
                        }
                        //存在修改
                        if (null != user) {
//                            if ("0".equals(type) && !user.getUserName().equals(staffCRMEntity.getLoginid())) {
//                                user.setMemo("id重复_" + user.getStaffId());
//                                user.setStaffId(IdGen.uuid());
//                                userAgencyRepository.saveOrUpdateUser(user);
//                                user.setStaffId(staffCRMEntity.getUserid().toString());
//                            }
                            String up = "0";
                            if ("1".equals(staffCRMEntity.getAccounttype().toString())) {
                                if (user.getStaffState().equals("1")) {
                                    user.setStaffState("0");//人员状态
                                    up = "1";
                                }
                            } else {
                                if (!StringUtil.isEmpty(staffCRMEntity.getStatus())) {
                                    if (!StringUtil.isEmpty(user.getStaffState())) {
                                        if (staffCRMEntity.getStatus().equals("0") || staffCRMEntity.getStatus().equals("1") || staffCRMEntity.getStatus().equals("2") || staffCRMEntity.getStatus().equals("3")) {
                                            if (!"1".equals(user.getStaffState())) {
                                                user.setStaffState("1");//人员状态
                                                up = "1";
                                            }
                                        } else {
                                            if (!"0".equals(user.getStaffState())) {
                                                user.setStaffState("0");//人员状态
                                                up = "1";
                                            }
                                        }
                                    } else {
                                        if (staffCRMEntity.getStatus().equals("0") || staffCRMEntity.getStatus().equals("1") || staffCRMEntity.getStatus().equals("2") || staffCRMEntity.getStatus().equals("3")) {
                                            user.setStaffState("1");//人员状态
                                            up = "1";
                                        } else {
                                            user.setStaffState("0");//人员状态
                                            up = "1";
                                        }
                                    }
                                }
                            }
                            if (!StringUtil.isEmpty(staffCRMEntity.getLoginid())) {
                                if (!StringUtil.isEmpty(user.getSysName())) {
                                    if (!user.getSysName().equals(staffCRMEntity.getLoginid())) {
                                        user.setSysName(staffCRMEntity.getLoginid());//本系统的登录账号
                                        user.setUserName(staffCRMEntity.getLoginid());//系统账号
                                        up = "1";
                                    }
                                } else {
                                    user.setSysName(staffCRMEntity.getLoginid());//本系统的登录账号
                                    user.setUserName(staffCRMEntity.getLoginid());//系统账号
                                    up = "1";
                                }
                            }
                            if (!StringUtil.isEmpty(staffCRMEntity.getDsporder().toString())) {
                                if (!StringUtil.isEmpty(user.getOrderNum().toString())) {
                                    if (!user.getOrderNum().toString().equals(staffCRMEntity.getDsporder().toString())) {
                                        user.setOrderNum(staffCRMEntity.getDsporder());//排序
                                        up = "1";
                                    }
                                } else {
                                    user.setOrderNum(staffCRMEntity.getDsporder());//排序
                                    up = "1";
                                }
                            }
                            if (!StringUtil.isEmpty(staffCRMEntity.getEmail())) {
                                if (!StringUtil.isEmpty(user.getEmail())) {
                                    if (!staffCRMEntity.getEmail().equals(user.getEmail())) {
                                        user.setEmail(staffCRMEntity.getEmail());//邮箱
                                        up = "1";
                                    }
                                } else {
                                    user.setEmail(staffCRMEntity.getEmail());//邮箱
                                    up = "1";
                                }
                            }
                            if (!StringUtil.isEmpty(staffCRMEntity.getLastname())) {
                                if (!StringUtil.isEmpty(user.getStaffName())) {
                                    if (!staffCRMEntity.getLastname().equals(user.getStaffName())) {
                                        user.setStaffName(staffCRMEntity.getLastname());//姓名
                                        up = "1";
                                    }
                                } else {
                                    user.setStaffName(staffCRMEntity.getLastname());//姓名
                                    up = "1";
                                }
                            }
                            if (!StringUtil.isEmpty(staffCRMEntity.getMobile())) {
                                if (!StringUtil.isEmpty(user.getMobile())) {
                                    if (!staffCRMEntity.getMobile().equals(user.getMobile())) {
                                        user.setMobile(staffCRMEntity.getMobile());//手机号
                                        up = "1";
                                    }
                                } else {
                                    user.setMobile(staffCRMEntity.getMobile());//手机号
                                    up = "1";
                                }
                            }
                            if (!StringUtil.isEmpty(staffCRMEntity.getTelephone())) {
                                if (!StringUtil.isEmpty(user.getOfficePhone())) {
                                    if (!staffCRMEntity.getTelephone().equals(user.getOfficePhone())) {
                                        user.setOfficePhone(staffCRMEntity.getTelephone());//工作电话
                                        up = "1";
                                    }
                                } else {
                                    user.setOfficePhone(staffCRMEntity.getTelephone());//工作电话
                                    up = "1";
                                }
                            }
                            if (!StringUtil.isEmpty(staffCRMEntity.getSex())) {
                                if (!StringUtil.isEmpty(user.getSex())) {
                                    if (!staffCRMEntity.getSex().equals(user.getSex())) {
                                        user.setSex(staffCRMEntity.getSex());//性别
                                        up = "1";
                                    }
                                } else {
                                    user.setSex(staffCRMEntity.getSex());//性别
                                    up = "1";
                                }
                            }
                            if ("1".equals(up)) {
                                userAgencyRepository.saveOrUpdateUser_2(user);
                            }
                            if (!StringUtil.isEmpty(staffCRMEntity.getDepartmentid())) {
                                agencyRepository.delAgencyByAgencyId(staffCRMEntity.getDepartmentid() + "dpx", user.getStaffId());
                                UserAgencyEntity userAgencyEntity1 = agencyRepository.getAgencyUserByAgencyId(staffCRMEntity.getDepartmentid() + "dpx", user.getStaffId());
                                if (null != userAgencyEntity1) {
                                    userAgencyEntity1.setSourceFrom("OA");
                                    userAgencyEntity1.setStatus("1");
                                    userAgencyEntity1.setModifyTime(new Date());
                                    userAgencyEntity1.setStaffId(user.getStaffId());
                                    userAgencyEntity1.setAgencyId(staffCRMEntity.getDepartmentid() + "dpx");
                                    userAgencyRepository.updateUserAgencyEntity(userAgencyEntity1);
                                } else {
                                    UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
                                    userAgencyEntity.setSourceFrom("OA");
                                    userAgencyEntity.setStatus("1");
                                    userAgencyEntity.setModifyTime(new Date());
                                    userAgencyEntity.setStaffId(user.getStaffId());
                                    userAgencyEntity.setAgencyId(staffCRMEntity.getDepartmentid() + "dpx");
                                    userAgencyEntity.setMapId(IdGen.uuid());
                                    userAgencyRepository.saveOrUpdateUserAgency(userAgencyEntity);
                                }
                            }
                        } else {//不存在新增
                            UserInformationEntity userInformationEntity1 = agencyRepository.getUserByUserId(staffCRMEntity.getUserid().toString());
                            if (null != userInformationEntity1) {
                                continue;
                            } else {
                                UserInformationEntity userInformationEntity = new UserInformationEntity();
                                userInformationEntity.setJinmaoIs("1");//金茂内部同步
                                userInformationEntity.setCreateBy("OA系统同步");
                                userInformationEntity.setCreateOn(new Date());
                                userInformationEntity.setModifyBy("OA系统同步");
                                userInformationEntity.setModifyOn(new Date());
                                userInformationEntity.setPassword("e10adc3949ba59abbe56e057f20f883e");//OA密码为空默认123456
                                userInformationEntity.setUserId(staffCRMEntity.getUserid().toString());//OA的userId
                                userInformationEntity.setStaffId(IdGen.uuid());//用户Id
                                if ("1".equals(staffCRMEntity.getAccounttype())) {
                                    userInformationEntity.setStaffState("0");//人员状态
                                } else {
                                    if (!StringUtil.isEmpty(staffCRMEntity.getStatus())) {
                                        if (staffCRMEntity.getStatus().equals("0") || staffCRMEntity.getStatus().equals("1") || staffCRMEntity.getStatus().equals("2") || staffCRMEntity.getStatus().equals("3")) {
                                            userInformationEntity.setStaffState("1");//人员状态
                                        } else {
                                            userInformationEntity.setStaffState("0");//人员状态
                                        }
                                    }
                                }
                                if (!StringUtil.isEmpty(staffCRMEntity.getLoginid())) {
                                    userInformationEntity.setSysName(staffCRMEntity.getLoginid());//本系统的登录账号
                                    userInformationEntity.setUserName(staffCRMEntity.getLoginid());//系统账号
                                }
                                if (!StringUtil.isEmpty(staffCRMEntity.getDsporder().toString())) {
                                    userInformationEntity.setOrderNum(staffCRMEntity.getDsporder());//排序
                                }
                                if (!StringUtil.isEmpty(staffCRMEntity.getEmail())) {
                                    userInformationEntity.setEmail(staffCRMEntity.getEmail());//邮箱
                                }
                                if (!StringUtil.isEmpty(staffCRMEntity.getLastname())) {
                                    userInformationEntity.setStaffName(staffCRMEntity.getLastname());//姓名
                                }
                                if (!StringUtil.isEmpty(staffCRMEntity.getMobile())) {
                                    userInformationEntity.setMobile(staffCRMEntity.getMobile());//手机号
                                }
                                if (!StringUtil.isEmpty(staffCRMEntity.getTelephone())) {
                                    userInformationEntity.setOfficePhone(staffCRMEntity.getTelephone());//工作电话
                                }
                                if (!StringUtil.isEmpty(staffCRMEntity.getSex())) {
                                    userInformationEntity.setSex(staffCRMEntity.getSex());//性别
                                }
                                userAgencyRepository.saveOrUpdateUser(userInformationEntity);//保存人员信息

                                UserMapEntity userMapEntity = new UserMapEntity();
                                userMapEntity.setSourceFrom("OA");
                                userMapEntity.setStaffId(userInformationEntity.getStaffId());
                                userMapEntity.setModifyOn(new Date());
                                userMapEntity.setState("0");
                                userMapEntity.setProjectModule("st");
                                userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                userMapEntity.setProjectModule("qc");
                                userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                                userMapEntity.setProjectModule("es");
                                userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);//保存启用关系

                                UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
                                userAgencyEntity.setSourceFrom("OA");
                                userAgencyEntity.setStatus("1");
                                userAgencyEntity.setModifyTime(new Date());
                                userAgencyEntity.setStaffId(userInformationEntity.getStaffId());
                                userAgencyEntity.setAgencyId(staffCRMEntity.getDepartmentid() + "dpx");
                                userAgencyEntity.setMapId(IdGen.uuid());
                                userAgencyRepository.saveOrUpdateUserAgency(userAgencyEntity);//保存人员与组织机构关系
                            }
                        }
                    } else {
                        UserInformationEntity user = null;
                        user = agencyRepository.getUserByUserId(staffCRMEntity.getUserid().toString());
                        if (null == user) {
                            user = agencyRepository.getUserById(staffCRMEntity.getUserid().toString());
                        }
                        if (null != user) {
                            if (user.getStaffState().equals("1")) {
                                user.setStaffState("0");//人员状态
                                userAgencyRepository.saveOrUpdateUser_2(user);//保存人员信息
                            }
                        }
                    }
                }
            }
            return "200";
        } catch (Exception e) {
            e.printStackTrace();
            return "500";
        }
    }

    @Override
    public String getOAAgencyData() {
        try {
//            agencyRepository.delAgencyByOA();
            List<SubcompanyCRMEntity> cpx = userAgencyRepository.getSubByOA();
            List<DepartmentCRMEntity> dpx = userAgencyRepository.getDepByOA();
            List<String> cpx_dpx = agencyRepository.getAgencyByOA();
            List<String> cpx_1 = new ArrayList<>();
            if (null != cpx) {
                for (SubcompanyCRMEntity subcompanyCRMEntity : cpx) {
                    cpx_1.add(subcompanyCRMEntity.getSubcompanyid() + "cpx");
                    if (null == subcompanyCRMEntity.getCanceled()) {
                        subcompanyCRMEntity.setCanceled("0");
                    }
                    //判断是否存在
                    AuthOuterAgencyEntity authOuterAgencyEntity = agencyRepository.getAgencyDetailByAgencyId(subcompanyCRMEntity.getSubcompanyid() + "cpx");
                    if (null != authOuterAgencyEntity) {//已存在则修改
                        AuthOuterAgencyEntity authOuterAgencyEntity2 = null;
                        if (!StringUtil.isEmpty(subcompanyCRMEntity.getSupsubcompanyid())) {
                            authOuterAgencyEntity2 = agencyRepository.getAgencyDetailByAgencyId(subcompanyCRMEntity.getSupsubcompanyid() + "cpx");//判断其父级是否存在
                        }
                        String up = "0";
                        if ("1".equals(subcompanyCRMEntity.getCanceled())) {
                            if (!"0".equals(authOuterAgencyEntity.getStatus())) {
                                authOuterAgencyEntity.setStatus("0");//状态
                                up = "1";
                            }
                        } else {
                            if (!"1".equals(authOuterAgencyEntity.getStatus())) {
                                authOuterAgencyEntity.setStatus("1");
                                up = "1";
                            }
                        }
                        if (!StringUtil.isEmpty(subcompanyCRMEntity.getShortname())) {
                            if (!StringUtil.isEmpty(authOuterAgencyEntity.getAgencyName())) {
                                if (!subcompanyCRMEntity.getShortname().equals(authOuterAgencyEntity.getAgencyName())) {
                                    authOuterAgencyEntity.setAgencyName(subcompanyCRMEntity.getShortname()); //名称
                                    up = "1";
                                }
                            } else {
                                authOuterAgencyEntity.setAgencyName(subcompanyCRMEntity.getShortname());
                                up = "1";
                            }
                        }
                        if (!StringUtil.isEmpty(subcompanyCRMEntity.getFullname())) {
                            if (!StringUtil.isEmpty(authOuterAgencyEntity.getAgencyDesc())) {
                                if (!subcompanyCRMEntity.getFullname().equals(authOuterAgencyEntity.getAgencyDesc())) {
                                    authOuterAgencyEntity.setAgencyDesc(subcompanyCRMEntity.getFullname());//备注
                                    up = "1";
                                }
                            } else {
                                authOuterAgencyEntity.setAgencyDesc(subcompanyCRMEntity.getFullname());
                                up = "1";
                            }
                        }
                        if (!StringUtil.isEmpty(subcompanyCRMEntity.getSupsubcompanyid())) {
                            if (!StringUtil.isEmpty(authOuterAgencyEntity.getParentId())) {
                                if (!authOuterAgencyEntity.getParentId().equals(subcompanyCRMEntity.getSupsubcompanyid() + "cpx")) {
                                    if ("0".equals(subcompanyCRMEntity.getSupsubcompanyid())) {
                                        authOuterAgencyEntity.setParentId("1");//父级id
                                        authOuterAgencyEntity.setAgencyPath("/1/" + authOuterAgencyEntity.getAgencyId());//路径
                                        authOuterAgencyEntity.setLevel(2);//等级
                                    } else if ("1".equals(subcompanyCRMEntity.getSupsubcompanyid())) {
                                        authOuterAgencyEntity.setParentId("cbe740dcf7d14d5091403928988634cf");//父级id
                                        authOuterAgencyEntity.setAgencyPath("/1/cbe740dcf7d14d5091403928988634cf/" + authOuterAgencyEntity.getAgencyId());//路径
                                        authOuterAgencyEntity.setLevel(3);//等级
                                    } else {
                                        authOuterAgencyEntity.setParentId(subcompanyCRMEntity.getSupsubcompanyid() + "cpx");//父级id
                                    }
                                    if (null != authOuterAgencyEntity2) {
                                        authOuterAgencyEntity.setAgencyPath(authOuterAgencyEntity2.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId());//路径
                                        authOuterAgencyEntity.setLevel(authOuterAgencyEntity2.getLevel() + 1);//等级
                                    }
                                    up = "1";
                                }
                            } else {
                                if ("0".equals(subcompanyCRMEntity.getSupsubcompanyid())) {
                                    authOuterAgencyEntity.setParentId("1");//父级id
                                    authOuterAgencyEntity.setAgencyPath("/1/" + authOuterAgencyEntity.getAgencyId());//路径
                                    authOuterAgencyEntity.setLevel(2);//等级
                                } else if ("1".equals(subcompanyCRMEntity.getSupsubcompanyid())) {
                                    authOuterAgencyEntity.setParentId("cbe740dcf7d14d5091403928988634cf");//父级id
                                    authOuterAgencyEntity.setAgencyPath("/1/cbe740dcf7d14d5091403928988634cf/" + authOuterAgencyEntity.getAgencyId());//路径
                                    authOuterAgencyEntity.setLevel(3);//等级
                                } else {
                                    authOuterAgencyEntity.setParentId(subcompanyCRMEntity.getSupsubcompanyid() + "cpx");//父级id
                                }
                                if (null != authOuterAgencyEntity2) {
                                    authOuterAgencyEntity.setAgencyPath(authOuterAgencyEntity2.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId());//路径
                                    authOuterAgencyEntity.setLevel(authOuterAgencyEntity2.getLevel() + 1);//等级
                                }
                                up = "1";
                            }
                        }
                        if (!StringUtil.isEmpty(authOuterAgencyEntity.getAgencyPath()) && null != authOuterAgencyEntity2) {
                            if (!authOuterAgencyEntity.getAgencyPath().equals(authOuterAgencyEntity2.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId())) {
                                authOuterAgencyEntity.setAgencyPath(authOuterAgencyEntity2.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId());//路径
                                authOuterAgencyEntity.setLevel(authOuterAgencyEntity2.getLevel() + 1);//等级
                                up = "1";
                            }
                        }
                        if ("1".equals(up)) {
                            agencyRepository.updateAgency(authOuterAgencyEntity);
                        }
                    } else {//没有则新增
                        AuthOuterAgencyEntity authOuterAgencyEntity2 = null;
                        AuthOuterAgencyEntity authOuterAgencyEntity1 = new AuthOuterAgencyEntity();
                        if (null != subcompanyCRMEntity.getCanceled() && !StringUtil.isEmpty(subcompanyCRMEntity.getCanceled())) {
                            if ("0".equals(subcompanyCRMEntity.getCanceled())) {
                                authOuterAgencyEntity1.setStatus("1");
                            } else {
                                authOuterAgencyEntity1.setStatus("0");
                            }
                        } else {
                            continue;
                        }
                        if (null != subcompanyCRMEntity.getSubcompanyid() && !StringUtil.isEmpty(subcompanyCRMEntity.getSubcompanyid())) {
                            authOuterAgencyEntity1.setAgencyId(subcompanyCRMEntity.getSubcompanyid() + "cpx");//为了防止id重复 分部的id后面+cpx
                        }
                        if (null != subcompanyCRMEntity.getSupsubcompanyid() && !StringUtil.isEmpty(subcompanyCRMEntity.getSupsubcompanyid())) {
                            authOuterAgencyEntity2 = agencyRepository.getAgencyDetailByAgencyId(subcompanyCRMEntity.getSupsubcompanyid() + "cpx");//判断其父级是否存在
                            if ("0".equals(subcompanyCRMEntity.getSupsubcompanyid())) {
                                authOuterAgencyEntity1.setParentId("1");//父级id
                                authOuterAgencyEntity1.setAgencyPath("/1/" + authOuterAgencyEntity1.getAgencyId());//路径
                                authOuterAgencyEntity1.setLevel(2);//等级
                            } else if ("1".equals(subcompanyCRMEntity.getSupsubcompanyid())) {
                                authOuterAgencyEntity1.setParentId("cbe740dcf7d14d5091403928988634cf");//父级id
                                authOuterAgencyEntity1.setAgencyPath("/1/cbe740dcf7d14d5091403928988634cf/" + authOuterAgencyEntity1.getAgencyId());//路径
                                authOuterAgencyEntity1.setLevel(3);//等级
                            } else {
                                authOuterAgencyEntity1.setParentId(subcompanyCRMEntity.getSupsubcompanyid() + "cpx");//父级id
                            }
                        }
                        if (null != subcompanyCRMEntity.getShortname() && !StringUtil.isEmpty(subcompanyCRMEntity.getShortname())) {
                            authOuterAgencyEntity1.setAgencyName(subcompanyCRMEntity.getShortname());//组织机构名称
                        }
                        if (null != subcompanyCRMEntity.getFullname() && !StringUtil.isEmpty(subcompanyCRMEntity.getFullname())) {
                            authOuterAgencyEntity1.setAgencyDesc(subcompanyCRMEntity.getFullname());//组织机构备注
                        }
                        if (null != subcompanyCRMEntity.getShoworder() && !StringUtil.isEmpty(subcompanyCRMEntity.getShoworder())) {
                            authOuterAgencyEntity1.setOrderNum(Integer.valueOf(subcompanyCRMEntity.getShoworder()));//排序
                        }
                        if (null != subcompanyCRMEntity.getLastChangdate()) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date date = sdf.parse(subcompanyCRMEntity.getLastChangdate());
                                authOuterAgencyEntity1.setCreateTime(date);
                                authOuterAgencyEntity1.setModifyTime(date);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        authOuterAgencyEntity1.setOutEmploy("0");

                        if (null != authOuterAgencyEntity2) {
                            authOuterAgencyEntity1.setAgencyPath(authOuterAgencyEntity2.getAgencyPath() + "/" + authOuterAgencyEntity1.getAgencyId());//路径
                            authOuterAgencyEntity1.setLevel(authOuterAgencyEntity2.getLevel() + 1);//等级
                        }
                        authOuterAgencyEntity1.setAgencyType("2");
                        agencyRepository.saveAgency(authOuterAgencyEntity1);
                    }
                }
            }
            if (null != dpx) {
                for (DepartmentCRMEntity departmentCRMEntity : dpx) {
                    cpx_1.add(departmentCRMEntity.getDepartmentid() + "dpx");
                    if (null == departmentCRMEntity.getCanceled()) {
                        departmentCRMEntity.setCanceled("0");
                    }
                    //判断是否存在
                    AuthOuterAgencyEntity authOuterAgencyEntity = agencyRepository.getAgencyDetailByAgencyId(departmentCRMEntity.getDepartmentid() + "dpx");
                    if (null != authOuterAgencyEntity) {//已存在则修改
                        AuthOuterAgencyEntity authOuterAgencyEntity2 = null;
                        String up = "0";
                        if ("1".equals(departmentCRMEntity.getCanceled())) {
                            if (!"0".equals(authOuterAgencyEntity.getStatus())) {
                                authOuterAgencyEntity.setStatus("0");//状态
                                up = "1";
                            }
                        } else {
                            if (!"1".equals(authOuterAgencyEntity.getStatus())) {
                                authOuterAgencyEntity.setStatus("1");
                                up = "1";
                            }
                        }
                        if (!StringUtil.isEmpty(departmentCRMEntity.getShortname())) {
                            if (!StringUtil.isEmpty(authOuterAgencyEntity.getAgencyName())) {
                                if (!departmentCRMEntity.getShortname().equals(authOuterAgencyEntity.getAgencyName())) {
                                    authOuterAgencyEntity.setAgencyName(departmentCRMEntity.getShortname()); //名称
                                    up = "1";
                                }
                            } else {
                                authOuterAgencyEntity.setAgencyName(departmentCRMEntity.getShortname());
                                up = "1";
                            }
                        }
                        if (!StringUtil.isEmpty(departmentCRMEntity.getFullname())) {
                            if (!StringUtil.isEmpty(authOuterAgencyEntity.getAgencyDesc())) {
                                if (!departmentCRMEntity.getFullname().equals(authOuterAgencyEntity.getAgencyDesc())) {
                                    authOuterAgencyEntity.setAgencyDesc(departmentCRMEntity.getFullname());//备注
                                    up = "1";
                                }
                            } else {
                                authOuterAgencyEntity.setAgencyDesc(departmentCRMEntity.getFullname());
                                up = "1";
                            }
                        }
                        if (!StringUtil.isEmpty(departmentCRMEntity.getSupdepartmentid())) {
                            if (!"0".equals(departmentCRMEntity.getSupdepartmentid())) {
                                if (!StringUtil.isEmpty(authOuterAgencyEntity.getParentId())) {
                                    if (!authOuterAgencyEntity.getParentId().equals(departmentCRMEntity.getSupdepartmentid() + "dpx")) {
                                        authOuterAgencyEntity2 = agencyRepository.getAgencyDetailByAgencyId(departmentCRMEntity.getSubcompanyid() + "dpx");//判断其父级是否存在
                                        authOuterAgencyEntity.setParentId(departmentCRMEntity.getSupdepartmentid() + "dpx");
                                        if (null != authOuterAgencyEntity2) {
                                            authOuterAgencyEntity.setAgencyPath(authOuterAgencyEntity2.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId());//路径
                                            authOuterAgencyEntity.setLevel(authOuterAgencyEntity2.getLevel() + 1);//等级
                                        }
                                        up = "1";
                                    }
                                } else {
                                    authOuterAgencyEntity2 = agencyRepository.getAgencyDetailByAgencyId(departmentCRMEntity.getSubcompanyid() + "dpx");//判断其父级是否存在
                                    authOuterAgencyEntity.setParentId(departmentCRMEntity.getSupdepartmentid() + "dpx");
                                    if (null != authOuterAgencyEntity2) {
                                        authOuterAgencyEntity.setAgencyPath(authOuterAgencyEntity2.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId());//路径
                                        authOuterAgencyEntity.setLevel(authOuterAgencyEntity2.getLevel() + 1);//等级
                                    }
                                    up = "1";
                                }
                            } else {
                                if ("1".equals(departmentCRMEntity.getSubcompanyid())) {
                                    if (!StringUtil.isEmpty(authOuterAgencyEntity.getParentId())) {
                                        if (!authOuterAgencyEntity.getParentId().equals("cbe740dcf7d14d5091403928988634cf")) {
                                            authOuterAgencyEntity2 = agencyRepository.getAgencyDetailByAgencyId("cbe740dcf7d14d5091403928988634cf");//判断其父级是否存在
                                            authOuterAgencyEntity.setParentId("cbe740dcf7d14d5091403928988634cf");
                                            if (null != authOuterAgencyEntity2) {
                                                authOuterAgencyEntity.setAgencyPath(authOuterAgencyEntity2.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId());//路径
                                                authOuterAgencyEntity.setLevel(authOuterAgencyEntity2.getLevel() + 1);//等级
                                            }
                                            up = "1";
                                        }
                                    } else {
                                        authOuterAgencyEntity2 = agencyRepository.getAgencyDetailByAgencyId("cbe740dcf7d14d5091403928988634cf");//判断其父级是否存在
                                        authOuterAgencyEntity.setParentId("cbe740dcf7d14d5091403928988634cf");
                                        if (null != authOuterAgencyEntity2) {
                                            authOuterAgencyEntity.setAgencyPath(authOuterAgencyEntity2.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId());//路径
                                            authOuterAgencyEntity.setLevel(authOuterAgencyEntity2.getLevel() + 1);//等级
                                        }
                                        up = "1";
                                    }
                                } else {
                                    if (!StringUtil.isEmpty(authOuterAgencyEntity.getParentId())) {
                                        if (!authOuterAgencyEntity.getParentId().equals(departmentCRMEntity.getSubcompanyid() + "cpx")) {
                                            authOuterAgencyEntity.setParentId(departmentCRMEntity.getSubcompanyid() + "cpx");//父级id
                                            authOuterAgencyEntity2 = agencyRepository.getAgencyDetailByAgencyId(departmentCRMEntity.getSubcompanyid() + "cpx");//判断其父级是否存在
                                            if (null != authOuterAgencyEntity2) {
                                                authOuterAgencyEntity.setAgencyPath(authOuterAgencyEntity2.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId());//路径
                                                authOuterAgencyEntity.setLevel(authOuterAgencyEntity2.getLevel() + 1);//等级
                                            }
                                            up = "1";
                                        }
                                    } else {
                                        authOuterAgencyEntity.setParentId(departmentCRMEntity.getSubcompanyid() + "cpx");//父级id
                                        authOuterAgencyEntity2 = agencyRepository.getAgencyDetailByAgencyId(departmentCRMEntity.getSubcompanyid() + "cpx");//判断其父级是否存在
                                        if (null != authOuterAgencyEntity2) {
                                            authOuterAgencyEntity.setAgencyPath(authOuterAgencyEntity2.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId());//路径
                                            authOuterAgencyEntity.setLevel(authOuterAgencyEntity2.getLevel() + 1);//等级
                                        }
                                        up = "1";
                                    }
                                }
                            }
                        }
                        if (!StringUtil.isEmpty(authOuterAgencyEntity.getAgencyPath()) && null != authOuterAgencyEntity2) {
                            if (!authOuterAgencyEntity.getAgencyPath().equals(authOuterAgencyEntity2.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId())) {
                                authOuterAgencyEntity.setAgencyPath(authOuterAgencyEntity2.getAgencyPath() + "/" + authOuterAgencyEntity.getAgencyId());//路径
                                authOuterAgencyEntity.setLevel(authOuterAgencyEntity2.getLevel() + 1);//等级
                                up = "1";
                            }
                        }
                        if ("1".equals(up)) {
                            agencyRepository.updateAgency(authOuterAgencyEntity);
                        }
                    } else {//没有则新增
                        AuthOuterAgencyEntity authOuterAgencyEntity2 = null;
                        AuthOuterAgencyEntity authOuterAgencyEntity1 = new AuthOuterAgencyEntity();
                        if (null != departmentCRMEntity.getSupdepartmentid() && !StringUtil.isEmpty(departmentCRMEntity.getSupdepartmentid())) {
                            if (!"0".equals(departmentCRMEntity.getSupdepartmentid())) {
                                authOuterAgencyEntity1.setParentId(departmentCRMEntity.getSupdepartmentid() + "dpx");//父级id
                                authOuterAgencyEntity2 = agencyRepository.getAgencyDetailByAgencyId(departmentCRMEntity.getSubcompanyid() + "dpx");//判断其父级是否存在
                            } else {
                                if ("1".equals(departmentCRMEntity.getSubcompanyid())) {
                                    authOuterAgencyEntity1.setParentId("cbe740dcf7d14d5091403928988634cf");//父级id
                                    authOuterAgencyEntity2 = agencyRepository.getAgencyDetailByAgencyId("cbe740dcf7d14d5091403928988634cf");//判断其父级是否存在
                                } else {
                                    authOuterAgencyEntity1.setParentId(departmentCRMEntity.getSubcompanyid() + "cpx");//父级id
                                    authOuterAgencyEntity2 = agencyRepository.getAgencyDetailByAgencyId(departmentCRMEntity.getSubcompanyid() + "cpx");//判断其父级是否存在
                                }
                            }
                        }
                        if (null != departmentCRMEntity.getCanceled() && !StringUtil.isEmpty(departmentCRMEntity.getCanceled())) {
                            if ("0".equals(departmentCRMEntity.getCanceled())) {
                                authOuterAgencyEntity1.setStatus("1");
                            } else {
                                authOuterAgencyEntity1.setStatus("0");
                            }
                        } else {
                            continue;
                        }
                        if (null != departmentCRMEntity.getDepartmentid() && !StringUtil.isEmpty(departmentCRMEntity.getDepartmentid())) {
                            authOuterAgencyEntity1.setAgencyId(departmentCRMEntity.getDepartmentid() + "dpx");//为了防止id重复 分部的id后面+cpx
                        }
                        if (null != departmentCRMEntity.getShortname() && !StringUtil.isEmpty(departmentCRMEntity.getShortname())) {
                            authOuterAgencyEntity1.setAgencyName(departmentCRMEntity.getShortname());//组织机构名称
                        }
                        if (null != departmentCRMEntity.getFullname() && !StringUtil.isEmpty(departmentCRMEntity.getFullname())) {
                            authOuterAgencyEntity1.setAgencyDesc(departmentCRMEntity.getFullname());//组织机构备注
                        }
                        if (null != departmentCRMEntity.getShoworder() && !StringUtil.isEmpty(departmentCRMEntity.getShoworder())) {
                            authOuterAgencyEntity1.setOrderNum(Integer.valueOf(departmentCRMEntity.getShoworder()));//排序
                        }
                        if (null != departmentCRMEntity.getLastChangdate()) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date date = sdf.parse(departmentCRMEntity.getLastChangdate());
                                authOuterAgencyEntity1.setCreateTime(date);
                                authOuterAgencyEntity1.setModifyTime(date);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        authOuterAgencyEntity1.setOutEmploy("0");
                        if (null != authOuterAgencyEntity2) {
                            if (null != authOuterAgencyEntity2.getAgencyPath() && !StringUtil.isEmpty(authOuterAgencyEntity2.getAgencyPath())) {
                                authOuterAgencyEntity1.setAgencyPath(authOuterAgencyEntity2.getAgencyPath() + "/" + authOuterAgencyEntity1.getAgencyId());//路径
                            }
                            if (null != authOuterAgencyEntity2.getLevel() && !StringUtil.isEmpty(authOuterAgencyEntity2.getLevel().toString())) {
                                authOuterAgencyEntity1.setLevel(authOuterAgencyEntity2.getLevel() + 1);//等级
                            }
                        }
                        authOuterAgencyEntity1.setAgencyType("1");
                        agencyRepository.saveAgency(authOuterAgencyEntity1);
                    }
                }
            }
            if (null != cpx_dpx && cpx_dpx.size() > 0) {
                if (null != cpx_1 && cpx_1.size() > 0) {
                    cpx_dpx.removeAll(cpx_1);
                    agencyRepository.removeAgency(cpx_dpx);
                }
            }
            updateAgencyByOA();
            agencyRepository.delWasteAgency();
            return "200";
        } catch (Exception e) {
            e.printStackTrace();
            updateAgencyByOA();
            agencyRepository.delWasteAgency();
            return "500";
        }
    }


    @Override
    public void getUserAndAgencyByHistoryTable() {
        List<AgencyEntity> agencyEntityList = userAgencyRepository.getAgencysByOA();
        for (AgencyEntity agencyEntity : agencyEntityList) {
            AuthOuterAgencyEntity authOuterAgencyEntity = new AuthOuterAgencyEntity();
            authOuterAgencyEntity.setAgencyId(agencyEntity.getAgencyId());//组织机构Id
            authOuterAgencyEntity.setModifyTime(agencyEntity.getModifyTime());//修改时间
            authOuterAgencyEntity.setModifyBy("数据导入");
            authOuterAgencyEntity.setStatus(agencyEntity.getStatus());//状态
            authOuterAgencyEntity.setOutEmploy("0");
            authOuterAgencyEntity.setLevel(agencyEntity.getLevel());//等级
            authOuterAgencyEntity.setAgencyPath(agencyEntity.getAgencyPath());//路径
            authOuterAgencyEntity.setCreateBy("数据导入");
            authOuterAgencyEntity.setCreateTime(agencyEntity.getCreateTime());//创建时间
            authOuterAgencyEntity.setParentId(agencyEntity.getParentId());//父级Id
            authOuterAgencyEntity.setAgencyDesc(agencyEntity.getAgencyDesc());//描述
            authOuterAgencyEntity.setAgencyName(agencyEntity.getAgencyName());//组织机构名称
            authOuterAgencyEntity.setOrderNum(agencyEntity.getOrderNum());//排序
            userAgencyRepository.saveOrUpdateAgency(authOuterAgencyEntity);
        }
        List<UserPropertyStaffEntity> userPropertyStaffEntityList = userAgencyRepository.getStaff();
        for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntityList) {
            UserInformationEntity userInformationEntity = new UserInformationEntity();
            userInformationEntity.setStaffState(userPropertyStaffEntity.getStaffState());//人员状态
            userInformationEntity.setJinmaoIs("1");//金茂内部同步
            userInformationEntity.setSysName(userPropertyStaffEntity.getUserName());//本系统的登录账号
            userInformationEntity.setCreateBy(userPropertyStaffEntity.getCreateBy());
            userInformationEntity.setCreateOn(userPropertyStaffEntity.getCreateOn());
            userInformationEntity.setEmail(userPropertyStaffEntity.getEmail());
            if (null != userPropertyStaffEntity.getMobile() && !StringUtil.isEmpty(userPropertyStaffEntity.getMobile())) {
                userInformationEntity.setMobile(userPropertyStaffEntity.getMobile());
            }
            userInformationEntity.setModifyBy(userPropertyStaffEntity.getModifyBy());
            userInformationEntity.setModifyOn(userPropertyStaffEntity.getModifyOn());
            if (null != userPropertyStaffEntity.getOfficePhone() && !StringUtil.isEmpty(userPropertyStaffEntity.getOfficePhone())) {
                userInformationEntity.setOfficePhone(userPropertyStaffEntity.getOfficePhone());
            }
            userInformationEntity.setOrderNum(userPropertyStaffEntity.getOrderNum());
            userInformationEntity.setPassword(userPropertyStaffEntity.getPassword());
            userInformationEntity.setStaffId(userPropertyStaffEntity.getStaffId());
            userInformationEntity.setStaffName(userPropertyStaffEntity.getStaffName());
            userInformationEntity.setUserName(userPropertyStaffEntity.getUserName());
            userAgencyRepository.saveOrUpdateUser(userInformationEntity);
            UserMapEntity userMapEntity = new UserMapEntity();
            userMapEntity.setSourceFrom("OA");
            userMapEntity.setStaffId(userInformationEntity.getStaffId());
            userMapEntity.setModifyOn(new Date());
            userMapEntity.setState("0");
            userMapEntity.setProjectModule("st");
            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
            userMapEntity.setProjectModule("qc");
            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
            userMapEntity.setProjectModule("es");
            userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
        }
        List<UserAgencymapEntity> userAgencymapEntityList = userAgencyRepository.getUserAgency();
        for (UserAgencymapEntity userAgencymapEntity : userAgencymapEntityList) {
            UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
            userAgencyEntity.setSourceFrom("OA");
            userAgencyEntity.setStatus(userAgencymapEntity.getStatus());
            userAgencyEntity.setModifyTime(userAgencymapEntity.getModifyTime());
            userAgencyEntity.setStaffId(userAgencymapEntity.getStaffId());
            userAgencyEntity.setAgencyId(userAgencymapEntity.getAgencyId());
            userAgencyEntity.setMapId(userAgencymapEntity.getMapId());
            userAgencyRepository.saveOrUpdateUserAgency(userAgencyEntity);
        }
    }

    @Override
    public void getUserByHistoryTable() {
        List<String> idList = new ArrayList<String>();
        List<String> staffIdList = new ArrayList<String>();
        List<AgencyEntity> agencyEntityList = userAgencyRepository.getAgencysByOuter();
        for (AgencyEntity agencyEntity : agencyEntityList) {
            AuthOuterAgencyEntity authOuterAgencyEntity = new AuthOuterAgencyEntity();
            authOuterAgencyEntity.setAgencyId(agencyEntity.getAgencyId());//组织机构Id
            authOuterAgencyEntity.setModifyTime(agencyEntity.getModifyTime());//修改时间
            authOuterAgencyEntity.setModifyBy("数据导入");
            authOuterAgencyEntity.setStatus(agencyEntity.getStatus());//状态
            authOuterAgencyEntity.setOutEmploy("1");
            authOuterAgencyEntity.setIsTemporary("0");
            authOuterAgencyEntity.setLevel(agencyEntity.getLevel());//等级
            authOuterAgencyEntity.setAgencyPath(agencyEntity.getAgencyPath());//路径
            authOuterAgencyEntity.setCreateBy("数据导入");
            authOuterAgencyEntity.setCreateTime(agencyEntity.getCreateTime());//创建时间
            authOuterAgencyEntity.setParentId(agencyEntity.getParentId());//父级Id
            authOuterAgencyEntity.setAgencyDesc(agencyEntity.getAgencyDesc());//描述
            authOuterAgencyEntity.setAgencyName(agencyEntity.getAgencyName());//组织机构名称
            authOuterAgencyEntity.setOrderNum(agencyEntity.getOrderNum());//排序
            userAgencyRepository.saveOrUpdateAgency(authOuterAgencyEntity);
            idList.add(agencyEntity.getAgencyId());
        }
        if (null != idList && idList.size() > 0) {
            List<UserAgencymapEntity> userAgencymapEntityList = userAgencyRepository.getUserAgencyById(idList);
            for (UserAgencymapEntity userAgencymapEntity : userAgencymapEntityList) {
                UserAgencyEntity userAgencyEntity = new UserAgencyEntity();
                userAgencyEntity.setSourceFrom("external");
                userAgencyEntity.setStatus(userAgencymapEntity.getStatus());
                userAgencyEntity.setModifyTime(userAgencymapEntity.getModifyTime());
                userAgencyEntity.setStaffId(userAgencymapEntity.getStaffId());
                userAgencyEntity.setAgencyId(userAgencymapEntity.getAgencyId());
                userAgencyEntity.setMapId(userAgencymapEntity.getMapId());
                userAgencyRepository.saveOrUpdateUserAgency(userAgencyEntity);
                staffIdList.add(userAgencymapEntity.getStaffId());
            }
        }
        if (null != staffIdList && staffIdList.size() > 0) {
            List<UserPropertyStaffEntity> userPropertyStaffEntityList = userAgencyRepository.getStaffById(staffIdList);
            for (UserPropertyStaffEntity userPropertyStaffEntity : userPropertyStaffEntityList) {
                UserInformationEntity userInformationEntity = new UserInformationEntity();
                userInformationEntity.setStaffState(userPropertyStaffEntity.getStaffState());//人员状态
                userInformationEntity.setJinmaoIs("0");//外部合作单位同步
                userInformationEntity.setSysName(userPropertyStaffEntity.getUserName());//本系统的登录账号
                userInformationEntity.setCreateBy(userPropertyStaffEntity.getCreateBy());
                userInformationEntity.setCreateOn(userPropertyStaffEntity.getCreateOn());
                if (null != userPropertyStaffEntity.getMobile() && !StringUtil.isEmpty(userPropertyStaffEntity.getMobile())) {
                    userInformationEntity.setMobile(userPropertyStaffEntity.getMobile());
                }
                userInformationEntity.setModifyBy(userPropertyStaffEntity.getModifyBy());
                userInformationEntity.setModifyOn(userPropertyStaffEntity.getModifyOn());
                userInformationEntity.setOrderNum(userPropertyStaffEntity.getOrderNum());
                userInformationEntity.setPassword(userPropertyStaffEntity.getPassword());
                userInformationEntity.setStaffId(userPropertyStaffEntity.getStaffId());
                userInformationEntity.setStaffName(userPropertyStaffEntity.getStaffName());
                userInformationEntity.setMemo(userPropertyStaffEntity.getMemo());
                userAgencyRepository.saveOrUpdateUser(userInformationEntity);
                UserMapEntity userMapEntity = new UserMapEntity();
                userMapEntity.setSourceFrom("external");
                userMapEntity.setStaffId(userInformationEntity.getStaffId());
                userMapEntity.setModifyOn(new Date());
                userMapEntity.setState("0");
                userMapEntity.setProjectModule("es");
                userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                userMapEntity.setProjectModule("qc");
                userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
                userMapEntity.setProjectModule("st");
                userMapEntity.setState("1");
                userAgencyRepository.saveOrUpdateUserAgencyEntity(userMapEntity);
            }
        }
    }

    @Override
    public void getUserAndAgencyAndRoleByHistoryTable() {
        List<SecurityRoleEntity> securityRoleEntityList = securityProjectRepository.securityRoleEntity();
        if (null != securityRoleEntityList && securityRoleEntityList.size() > 0) {
            for (SecurityRoleEntity securityRoleEntity : securityRoleEntityList) {
                RoleStaffProjectMapEntity roleStaffProjectMapEntity = new RoleStaffProjectMapEntity();
                Long id = new Long((long) securityRoleEntity.getId());
                roleStaffProjectMapEntity.setId(id);
                roleStaffProjectMapEntity.setModifyOn(securityRoleEntity.getModifyTime());
                roleStaffProjectMapEntity.setState(securityRoleEntity.getStatus());
                roleStaffProjectMapEntity.setAgencyId(securityRoleEntity.getTypeId());
                roleStaffProjectMapEntity.setStaffId(securityRoleEntity.getDataId());
                if ("1".equals(securityRoleEntity.getTypeRole())) {
                    roleStaffProjectMapEntity.setRoleId("54e35d4e3c2d4646b1aaea3d52a73631");
                } else if ("2".equals(securityRoleEntity.getTypeRole())) {
                    roleStaffProjectMapEntity.setRoleId("90cc85577aa94322a8bb5aafa55e8f94");
                } else if ("4".equals(securityRoleEntity.getTypeRole())) {
                    roleStaffProjectMapEntity.setRoleId("7fd16c1065c149e2aef72842b6bfa6be");
                } else if ("5".equals(securityRoleEntity.getTypeRole())) {
                    roleStaffProjectMapEntity.setRoleId("3a97a374dc424567857f82d72140d1e9");
                } else if ("6".equals(securityRoleEntity.getTypeRole())) {
                    roleStaffProjectMapEntity.setRoleId("75c61d20b62b42daa1bfc5281940a556");
                } else if ("7".equals(securityRoleEntity.getTypeRole())) {
                    roleStaffProjectMapEntity.setRoleId("fa79f4a311f8411a89e2a11b1cb3b55a");
                } else {
                    continue;
                }
                userAgencyRepository.saveOrUpdateUserMap(roleStaffProjectMapEntity);
            }
        }
    }

    @Override
    public AuthSupplierRoleUserDTO getAuthAgencySupplierUser(AuthSupplierRoleUserDTO authAgencyDTO, WebPage webPage) {
        AuthSupplierRoleUserDTO authAgency = new AuthSupplierRoleUserDTO();
        if (!StringUtil.isEmpty(authAgencyDTO.getAgencyId()) && !StringUtil.isEmpty(authAgencyDTO.getSupplierId())) {
            //1.查询当前责任单位和项目绑定关系表
            BaseProjectPeopleEntity baseProjectEntity = staffEmployRepository.getBaseProjectByProjectId(authAgencyDTO.getAgencyId(), authAgencyDTO.getSupplierId());
            if (baseProjectEntity != null) {
                authAgency.setAgencyId(baseProjectEntity.getProjectId());
                authAgency.setAgencyName(baseProjectEntity.getProjectName());
                authAgency.setSupplierId(baseProjectEntity.getSupplierId());
                authAgency.setSupplierName(baseProjectEntity.getSupplierName());
            }
            //2.根据当前当前责任单位性质  查询当前项目关联的角色信息 supplierType
            List<String> roleList = staffEmployRepository.getRoleIdlistById(authAgencyDTO.getAgencyType(), authAgencyDTO.getAgencyId());
            if (roleList != null) {
                String roleNames = String.join(",", roleList);
                authAgency.setRoleAgency(roleNames);
            }
            //3.查询当前 项目-责任单位 绑定的人员信息
            List<AuthSupplierPeopleDTO> userList = new ArrayList<AuthSupplierPeopleDTO>();
//            List<UserInformationEntity> list=staffEmployRepository.getAgencyPeopleUserAuth(authAgencyDTO.getAgencyId(),baseProjectEntity.getSupplierId(),webPage);
//            if(list!=null && list.size()>0){
//                for(UserInformationEntity obj:list){
//                    AuthSupplierPeopleDTO authSupplierPeople=new AuthSupplierPeopleDTO();
//                    authSupplierPeople.setUserId(obj.getStaffId());
//                    authSupplierPeople.setStaffName(obj.getStaffName());
//                    authSupplierPeople.setUserName(obj.getSysName());
//                    authSupplierPeople.setPhone(obj.getMobile());
//                    authSupplierPeople.setMailbox(obj.getEmail());
//                    authSupplierPeople.setUpdateTime(obj.getModifyOn() == null ? "" : DateUtils.format(obj.getModifyOn(),DateUtils.FORMAT_LONG));
//                    //根据当前人、项目、责任单位性质 查询角色信息
//                    List<String> roleNameList=staffEmployRepository.getRoleNamesByEs(baseProjectEntity.getSupplierType(),authAgencyDTO.getAgencyId(),authSupplierPeople.getUserId());
//                    if(roleNameList!=null){
//                        String userRoleName = String.join(",", roleNameList);
//                        authSupplierPeople.setRoleAgency(userRoleName);
//                    }
//                    userList.add(authSupplierPeople);
//                }
//            }


            List<Object[]> list = staffEmployRepository.getAgencyPeopleUser(authAgencyDTO.getAgencyId(), baseProjectEntity.getSupplierId(), webPage);
            if (list != null && list.size() > 0) {
                for (Object[] obj : list) {
                    AuthSupplierPeopleDTO authSupplierPeople = new AuthSupplierPeopleDTO();
                    authSupplierPeople.setUserId(obj[0] == null ? "" : obj[0].toString());
                    authSupplierPeople.setStaffName(obj[1] == null ? "" : obj[1].toString());
                    authSupplierPeople.setUserName(obj[2] == null ? "" : obj[2].toString());
                    authSupplierPeople.setPhone(obj[3] == null ? "" : obj[3].toString());
                    authSupplierPeople.setMailbox(obj[4] == null ? "" : obj[4].toString());
                    authSupplierPeople.setUpdateTime(obj[5] == null ? "" : DateUtils.format((Date) obj[5], DateUtils.FORMAT_LONG));
                    //根据当前人、项目、责任单位性质 查询角色信息
                    List<String> roleNameList = staffEmployRepository.getRoleNamesByEs(baseProjectEntity.getSupplierType(), authAgencyDTO.getAgencyId(), authSupplierPeople.getUserId());
                    if (roleNameList != null) {
                        String userRoleName = String.join(",", roleNameList);
                        authSupplierPeople.setRoleAgency(userRoleName);
                    }
                    userList.add(authSupplierPeople);
                }
            }
            authAgency.setUserList(userList);
        }
        return authAgency;
    }

    @Override
    public AuthSupplierPeopleDTO getAuthStaffDetail(AuthSupplierPeopleDTO authSupplierPeopleDTO) {
        AuthSupplierPeopleDTO getAuthSupplierPeople = new AuthSupplierPeopleDTO();
        //z责任单位信息
        BaseProjectPeopleEntity baseProjectEntity = staffEmployRepository.getBaseProjectByProjectId(authSupplierPeopleDTO.getAgencyId(), authSupplierPeopleDTO.getSupplierId());
        if (baseProjectEntity != null) {
            getAuthSupplierPeople.setSupplierId(baseProjectEntity.getSupplierId());
            getAuthSupplierPeople.setSupplierName(baseProjectEntity.getSupplierName());
        }
        //人员信息
        UserInformationEntity user = staffEmployRepository.get(authSupplierPeopleDTO.getUserId());
        getAuthSupplierPeople.setStaffName(user.getStaffName());
        getAuthSupplierPeople.setPhone(user.getMobile());
        getAuthSupplierPeople.setRemarks(user.getMemo());

        UserMapEntity userMapEntity = staffEmployRepository.getUserMapEntity(user.getStaffId(), "es");
        if (userMapEntity != null) {
            getAuthSupplierPeople.setStatus(userMapEntity.getState());
        }
        //查询角色信息
        List<Object[]> roleList = staffEmployRepository.getRoleIDNamesByEs(authSupplierPeopleDTO.getAgencyType(), authSupplierPeopleDTO.getAgencyId(), authSupplierPeopleDTO.getUserId());
        if (roleList != null) {
            List<AuthSupplierAgencyRoleDTO> authRoleList = new ArrayList<AuthSupplierAgencyRoleDTO>();
            for (Object[] obj : roleList) {
                AuthSupplierAgencyRoleDTO authSupplierAgencyRole = new AuthSupplierAgencyRoleDTO();
                authSupplierAgencyRole.setRoleId(obj[0] == null ? "" : obj[0].toString());
                authSupplierAgencyRole.setRoleName(obj[1] == null ? "" : obj[1].toString());
                authSupplierAgencyRole.setChecked("1");
                authRoleList.add(authSupplierAgencyRole);
            }
            getAuthSupplierPeople.setRoleList(authRoleList);
        }
        return getAuthSupplierPeople;
    }

    @Override
    public AuthSupplierPeopleDTO getsupplierName(AuthSupplierPeopleDTO authSupplierPeopleDTO) {
        AuthSupplierPeopleDTO getAuthSupplierPeople = new AuthSupplierPeopleDTO();
        BaseProjectPeopleEntity baseProjectEntity = staffEmployRepository.getBaseProjectByProjectId(authSupplierPeopleDTO.getAgencyId(), authSupplierPeopleDTO.getSupplierId());
        if (baseProjectEntity != null) {
            getAuthSupplierPeople.setSupplierId(baseProjectEntity.getSupplierId());
            getAuthSupplierPeople.setSupplierName(baseProjectEntity.getSupplierName());
        }
        return getAuthSupplierPeople;
    }

    @Override
    public List<AuthSupplierAgencyRoleDTO> getAllAuthSupplierAgencyRole(AuthSupplierPeopleDTO authSupplierPeopleDTO) {
        List<AuthSupplierAgencyRoleDTO> authRoleList = new ArrayList<AuthSupplierAgencyRoleDTO>();
        List<Object[]> roleList = staffEmployRepository.getRoleIdNamelistById(authSupplierPeopleDTO.getAgencyType(), authSupplierPeopleDTO.getAgencyId());
        if (roleList != null) {
            for (Object[] obj : roleList) {
                AuthSupplierAgencyRoleDTO authSupplierAgencyRole = new AuthSupplierAgencyRoleDTO();
                authSupplierAgencyRole.setRoleId(obj[0] == null ? "" : obj[0].toString());
                authSupplierAgencyRole.setRoleName(obj[1] == null ? "" : obj[1].toString());
                authRoleList.add(authSupplierAgencyRole);
            }
        }
        return authRoleList;
    }

    //废弃
    public List<String> getAgencyEntityId(List<AgencyEntity> agencyEntityList, List<AgencyEntity> agencyEntities) {
        List<String> idList = new ArrayList<String>();
        List<AgencyEntity> agencyEntityList1 = new ArrayList<>();
        if (null != agencyEntityList && agencyEntityList.size() > 0) {
            for (AgencyEntity agencyEntity : agencyEntityList) {
                if (null != agencyEntities && agencyEntities.size() > 0) {
                    for (AgencyEntity agencyEntity1 : agencyEntities) {
                        if (agencyEntity.getParentId().equals(agencyEntity1.getAgencyId())) {
                            idList.add(agencyEntity.getAgencyId());
                            agencyEntityList1.add(agencyEntity);
                        }
                    }
                }
            }
            idList.addAll(getAgencyEntityId(agencyEntityList1, agencyEntities));
        }
        return idList;
    }

    public void updateAgencyByOA() {
        List<AuthOuterAgencyEntity> outerAgencyEntities = userAgencyRepository.getAgency();
        if (null != outerAgencyEntities && outerAgencyEntities.size() > 0) {
            List<AuthOuterAgencyEntity> authOuterAgencyEntities = null;
            Integer i = 1;
            do {
                authOuterAgencyEntities = userAgencyRepository.getAgencyByLevel(i);
                if (null != authOuterAgencyEntities && authOuterAgencyEntities.size() > 0) {
                    for (AuthOuterAgencyEntity authOuterAgencyEntity : authOuterAgencyEntities) {//根据级别查询组织架构
                        for (AuthOuterAgencyEntity authOuterAgencyEntity1 : outerAgencyEntities) { //数据有异常的
                            if (authOuterAgencyEntity.getAgencyId().equals(authOuterAgencyEntity1.getParentId())) {
                                authOuterAgencyEntity1.setLevel(authOuterAgencyEntity.getLevel() + 1);
                                authOuterAgencyEntity1.setAgencyPath(authOuterAgencyEntity.getAgencyPath() + "/" + authOuterAgencyEntity1.getAgencyId());
                                agencyRepository.updateAgency(authOuterAgencyEntity1);
                            }
                        }
                    }
                }
                i++;
            } while (null != authOuterAgencyEntities && authOuterAgencyEntities.size() > 0);
        }
    }
}
