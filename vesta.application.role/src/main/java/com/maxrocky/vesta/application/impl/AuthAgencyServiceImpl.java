package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.dto.adminDTO.AgencyAdminDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AgencyTreeDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.*;
import com.maxrocky.vesta.application.inf.AuthAgencyService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.AuthAgencyRepository;
import com.maxrocky.vesta.domain.repository.UserAccreditRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by maxrocky on 2017/12/8.
 */
@Service
public class AuthAgencyServiceImpl implements AuthAgencyService {
    @Autowired
    AuthAgencyRepository authAgencyRepository;

    @Autowired
    UserAccreditRepository userAccreditRepository;

    @Override
    public List<AgencyTreeDTO> getAgencyFullList() {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthAgencyEntity> agencyEntities = authAgencyRepository.getAllAgencyList();
        if (agencyEntities != null) {
            AgencyTreeDTO agencyTreeDTO;
            for (AuthAgencyEntity agencyEntity : agencyEntities) {
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
    public List<AgencyTreeDTO> getESAgencyFullList() {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthAgencyESEntity> agencyEntities = authAgencyRepository.getESAllAgencyList();
        if (agencyEntities != null) {
            AgencyTreeDTO agencyTreeDTO;
            for (AuthAgencyESEntity agencyEntity : agencyEntities) {
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
    public List<AgencyTreeDTO> getQCAgencyFullList() {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthAgencyQCEntity> agencyEntities = authAgencyRepository.getQCAllAgencyList();
        if (agencyEntities != null) {
            AgencyTreeDTO agencyTreeDTO;
            for (AuthAgencyQCEntity agencyEntity : agencyEntities) {
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
    public List<AgencyTreeDTO> getAuthAgencyFullList(UserInformationEntity userPropertystaffEntity) {
        //1.查看是否给当前登录人授权过总部权限
        if (authAgencyRepository.checkAuthRoleByUserIdandtype("100000000", userPropertystaffEntity.getStaffId())) {
            List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
            List<AuthAgencyEntity> agencyEntities = authAgencyRepository.getAllAgencyList();
            if (agencyEntities != null) {
                AgencyTreeDTO agencyTreeDTO;
                for (AuthAgencyEntity agencyEntity : agencyEntities) {
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
        } else if (authAgencyRepository.checkAuthRoleByUserIdandtype("100000001", userPropertystaffEntity.getStaffId())) {
            //查询是否绑定了区域层级的信息
            //查询绑定橘角色和区域层级的id
            List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
            //获取当前登录人绑定的区域idList
            List<String> idList = authAgencyRepository.checkAuthRoleListByUserIdandtype("100000001", userPropertystaffEntity.getStaffId());
            if (idList != null) {
                for (String agencyId : idList) {
                    List<AuthAgencyEntity> agencyEntities = authAgencyRepository.getAllAgencyListByAgencyId(agencyId);
                    if (agencyEntities != null) {
                        for (AuthAgencyEntity agencyEntity : agencyEntities) {
                            AgencyTreeDTO agencyTreeDTO = new AgencyTreeDTO();
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
                }
                //根据获取的区域idList查询路径
                List<String> pathList = authAgencyRepository.getAgencyPathListByAgencyId(idList);
                if (pathList != null && pathList.size() > 0) {
                    List<String> pAgencyidList = new ArrayList<>();
                    for (String path : pathList) {
                        String str[] = path.split("/");
                        List<String> list = Arrays.asList(str);
                        pAgencyidList.addAll(list);
                    }
                    pAgencyidList.removeAll(idList);
                    List<AuthAgencyEntity> parEntities = authAgencyRepository.getAllAgencyListByAgencyIdList(pAgencyidList);
                    if (parEntities != null) {
                        for (AuthAgencyEntity agencyEntity : parEntities) {
                            AgencyTreeDTO agencyTreeDTO = new AgencyTreeDTO();
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
                }
            }
            return agencyTreeDTOs;
        } else if (authAgencyRepository.checkAuthRoleByUserIdandtype("100000003", userPropertystaffEntity.getStaffId())) {
            //查询是否绑定了区域层级的信息
            //查询绑定橘角色和城市层级的id
            List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
            //获取当前登录人绑定的城市idList
            List<String> idList = authAgencyRepository.checkAuthRoleListByUserIdandtype("100000003", userPropertystaffEntity.getStaffId());
            if (idList != null) {
                for (String agencyId : idList) {
                    List<AuthAgencyEntity> agencyEntities = authAgencyRepository.getAllAgencyListByAgencyId(agencyId);
                    if (agencyEntities != null) {
                        for (AuthAgencyEntity agencyEntity : agencyEntities) {
                            AgencyTreeDTO agencyTreeDTO = new AgencyTreeDTO();
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
                }
                //根据获取的区域idList查询路径
                List<String> pathList = authAgencyRepository.getAgencyPathListByAgencyId(idList);
                if (pathList != null && pathList.size() > 0) {
                    List<String> pAgencyidList = new ArrayList<>();
                    for (String path : pathList) {
                        String str[] = path.split("/");
                        List<String> list = Arrays.asList(str);
                        pAgencyidList.addAll(list);
                    }
                    pAgencyidList.removeAll(idList);
                    List<AuthAgencyEntity> parEntities = authAgencyRepository.getAllAgencyListByAgencyIdList(pAgencyidList);
                    if (parEntities != null) {
                        for (AuthAgencyEntity agencyEntity : parEntities) {
                            AgencyTreeDTO agencyTreeDTO = new AgencyTreeDTO();
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
                }
            }
            return agencyTreeDTOs;
        } else if (authAgencyRepository.checkAuthRoleByUserIdandtype("100000002", userPropertystaffEntity.getStaffId())) {
            //查询是否绑定了项目
            //查询绑定橘角色和城市层级的id
            List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
            //获取当前登录人绑定的城市idList
            List<String> idList = authAgencyRepository.checkAuthRoleListByUserIdandtype("100000002", userPropertystaffEntity.getStaffId());
            if (idList != null) {
                List<AuthAgencyEntity> agencyEntities = authAgencyRepository.getAllAgencyListByAgencyIdList(idList);
                if (agencyEntities != null) {
                    for (AuthAgencyEntity agencyEntity : agencyEntities) {
                        AgencyTreeDTO agencyTreeDTO = new AgencyTreeDTO();
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
                //根据获取的区域idList查询路径
                List<String> pathList = authAgencyRepository.getAgencyPathListByAgencyId(idList);
                if (pathList != null && pathList.size() > 0) {
                    List<String> pAgencyidList = new ArrayList<>();
                    for (String path : pathList) {
                        String str[] = path.split("/");
                        List<String> list = Arrays.asList(str);
                        pAgencyidList.addAll(list);
                    }
                    pAgencyidList.removeAll(idList);
                    List<AuthAgencyEntity> parEntities = authAgencyRepository.getAllAgencyListByAgencyIdList(pAgencyidList);
                    if (parEntities != null) {
                        for (AuthAgencyEntity agencyEntity : parEntities) {
                            AgencyTreeDTO agencyTreeDTO = new AgencyTreeDTO();
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
                }
            }
            return agencyTreeDTOs;
        } else {
            return null;
        }
    }

    @Override
    public List<AgencyTreeDTO> getAgencyFullListByID(String apply) {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthAgencyEntity> agencyEntities = authAgencyRepository.getAllAgencyList();
        if (agencyEntities != null) {
            AgencyTreeDTO agencyTreeDTO;
            String applys[] = apply.split(",");
            for (AuthAgencyEntity agencyEntity : agencyEntities) {
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
                for (int i = 0; i < applys.length; i++) {
                    if (applys[i].toString().equals(agencyTreeDTO.getId())) {
                        agencyTreeDTO.setChecked(true);
                    }
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<AgencyTreeDTO> getAgencyFullListByIDES(String apply) {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthAgencyESEntity> agencyEntities = authAgencyRepository.getESAllAgencyList();
        if (agencyEntities != null) {
            AgencyTreeDTO agencyTreeDTO;
            String applys[] = apply.split(",");
            for (AuthAgencyESEntity agencyEntity : agencyEntities) {
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
                for (int i = 0; i < applys.length; i++) {
                    if (applys[i].toString().equals(agencyTreeDTO.getId())) {
                        agencyTreeDTO.setChecked(true);
                    }
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<AgencyTreeDTO> getAgencyFullListByIDQC(String apply) {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        List<AuthAgencyQCEntity> agencyEntities = authAgencyRepository.getQCAllAgencyList();
        if (agencyEntities != null) {
            AgencyTreeDTO agencyTreeDTO;
            String applys[] = apply.split(",");
            for (AuthAgencyQCEntity agencyEntity : agencyEntities) {
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
                for (int i = 0; i < applys.length; i++) {
                    if (applys[i].toString().equals(agencyTreeDTO.getId())) {
                        agencyTreeDTO.setChecked(true);
                    }
                }
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }

    @Override
    public List<AuthAdminAgencyDTO> getAgencyById(AgencyAdminDTO agencyAdminDTO) {
        String agencyName = "%%";
        if (!StringUtil.isEmpty(agencyAdminDTO.getAgencyName())) {
            agencyName = agencyAdminDTO.getAgencyName();
        }
        String agencyId = "%%";
        if (!StringUtil.isEmpty(agencyAdminDTO.getAgencyId())) {
            agencyId = agencyAdminDTO.getAgencyId();
        }
        List<AuthAdminAgencyDTO> list = new ArrayList<>();
        List<AuthAgencyEntity> agencyEntities = authAgencyRepository.getAllAgencyListById(agencyId, agencyName);
        if (agencyEntities != null) {
            for (AuthAgencyEntity authAgency : agencyEntities) {
                AuthAdminAgencyDTO agencyDTO = new AuthAdminAgencyDTO();
                agencyDTO.setAgencyId(authAgency.getAgencyId());//ID
                agencyDTO.setAgencyName(authAgency.getAgencyName()); //机构名称
                agencyDTO.setAgencyType(authAgency.getAgencyType()); //类型 级别
                agencyDTO.setParentId(authAgency.getParentId()); //上级部门ID
                AuthAgencyEntity paragencyEntitie = authAgencyRepository.getAuthAgencyByID(authAgency.getParentId());
                if (paragencyEntitie != null) {
                    if (!StringUtil.isEmpty(paragencyEntitie.getAgencyName())) {
                        agencyDTO.setParentName(paragencyEntitie.getAgencyName());//上级部门
                    }
                }
                agencyDTO.setModifyTime(DateUtils.format(authAgency.getModifyTime(), DateUtils.FORMAT_LONG)); //修改时间
                list.add(agencyDTO);
            }
        }
        return list;
    }

    @Override
    public List<AuthAdminAgencyDTO> getESAgencyById(AgencyAdminDTO agencyAdminDTO) {
        String agencyName = "%%";
        if (!StringUtil.isEmpty(agencyAdminDTO.getAgencyName())) {
            agencyName = agencyAdminDTO.getAgencyName();
        }
        String agencyId = "%%";
        if (!StringUtil.isEmpty(agencyAdminDTO.getAgencyId())) {
            agencyId = agencyAdminDTO.getAgencyId();
        }
        List<AuthAdminAgencyDTO> list = new ArrayList<>();
        List<AuthAgencyESEntity> agencyEntities = authAgencyRepository.getAllESAgencyListById(agencyId, agencyName);
        if (agencyEntities != null) {
            for (AuthAgencyESEntity authAgency : agencyEntities) {
                AuthAdminAgencyDTO agencyDTO = new AuthAdminAgencyDTO();
                agencyDTO.setAgencyId(authAgency.getAgencyId());//ID
                agencyDTO.setAgencyName(authAgency.getAgencyName()); //机构名称
                agencyDTO.setAgencyType(authAgency.getAgencyType()); //类型 级别
                agencyDTO.setParentId(authAgency.getParentId()); //上级部门ID
                AuthAgencyESEntity paragencyEntitie = authAgencyRepository.getESAuthAgencyByID(authAgency.getParentId());
                if (paragencyEntitie != null) {
                    if (!StringUtil.isEmpty(paragencyEntitie.getAgencyName())) {
                        agencyDTO.setParentName(paragencyEntitie.getAgencyName());//上级部门
                    }
                }
                agencyDTO.setModifyTime(DateUtils.format(authAgency.getModifyTime(), DateUtils.FORMAT_LONG)); //修改时间
                list.add(agencyDTO);
            }
        }
        return list;
    }

    @Override
    public List<AuthAdminAgencyDTO> getQCAgencyById(AgencyAdminDTO agencyAdminDTO) {
        String agencyName = "%%";
        if (!StringUtil.isEmpty(agencyAdminDTO.getAgencyName())) {
            agencyName = agencyAdminDTO.getAgencyName();
        }
        String agencyId = "%%";
        if (!StringUtil.isEmpty(agencyAdminDTO.getAgencyId())) {
            agencyId = agencyAdminDTO.getAgencyId();
        }
        List<AuthAdminAgencyDTO> list = new ArrayList<>();
        List<AuthAgencyQCEntity> agencyEntities = authAgencyRepository.getAllQCAgencyListById(agencyId, agencyName);
        if (agencyEntities != null) {
            for (AuthAgencyQCEntity authAgency : agencyEntities) {
                AuthAdminAgencyDTO agencyDTO = new AuthAdminAgencyDTO();
                agencyDTO.setAgencyId(authAgency.getAgencyId());//ID
                agencyDTO.setAgencyName(authAgency.getAgencyName()); //机构名称
                agencyDTO.setAgencyType(authAgency.getAgencyType()); //类型 级别
                agencyDTO.setParentId(authAgency.getParentId()); //上级部门ID
                AuthAgencyQCEntity paragencyEntitie = authAgencyRepository.getQCAuthAgencyByID(authAgency.getParentId());
                if (paragencyEntitie != null) {
                    if (!StringUtil.isEmpty(paragencyEntitie.getAgencyName())) {
                        agencyDTO.setParentName(paragencyEntitie.getAgencyName());//上级部门
                    }
                }
                agencyDTO.setModifyTime(DateUtils.format(authAgency.getModifyTime(), DateUtils.FORMAT_LONG)); //修改时间
                list.add(agencyDTO);
            }
        }
        return list;
    }

    @Override
    public List<AuthAdminAgencyDTO> getAgency() {
        List<AuthAdminAgencyDTO> list = new ArrayList<>();
        List<AuthAgencyEntity> agencyEntities = authAgencyRepository.getAllAgency();
        if (agencyEntities != null) {
            for (AuthAgencyEntity authAgency : agencyEntities) {
                AuthAdminAgencyDTO agencyDTO = new AuthAdminAgencyDTO();
                agencyDTO.setAgencyId(authAgency.getAgencyId());//ID
                agencyDTO.setAgencyName(authAgency.getAgencyName()); //机构名称
                agencyDTO.setAgencyType(authAgency.getAgencyType()); //类型 级别
                agencyDTO.setParentId(authAgency.getParentId()); //上级部门ID
                agencyDTO.setModifyTime(DateUtils.format(authAgency.getModifyTime(), DateUtils.FORMAT_LONG)); //修改时间
                list.add(agencyDTO);
            }
        }
        return list;
    }

    @Override
    public List<AuthRoleseListDTO> getAuthRoleseList(AuthRoleseListDTO authRoleseListDTO, WebPage webPage) {
        Map map = new HashMap();
        //角色id
        map.put("roleId", authRoleseListDTO.getRoleId());
        //角色描述
        map.put("roleName", "");
        if (!StringUtil.isEmpty(authRoleseListDTO.getRoleName())) {
            map.put("roleName", "%" + authRoleseListDTO.getRoleName() + "%");
        }
        map.put("category", "");
        if (!StringUtil.isEmpty(authRoleseListDTO.getCategory())) {
            map.put("category", authRoleseListDTO.getCategory());
        }
        List<Object[]> list = authAgencyRepository.getAuthRoleseList(map, webPage);
        List<AuthRoleseListDTO> authRoleseList = new ArrayList<>();
        if (list != null) {
            for (Object[] obj : list) {
                AuthRoleseListDTO authRolese = new AuthRoleseListDTO();
                authRolese.setRoleId(obj[0] == null ? "" : obj[0].toString());         //角色id
                authRolese.setPrefix(obj[6] == null ? "" : obj[6].toString());      //角色前缀
                if (obj[6] != null) {
                    authRolese.setRoleName(obj[1] == null ? obj[6].toString() + "" : obj[6].toString() + obj[1].toString()); //角色前缀+角色描述
                } else {
                    authRolese.setRoleName(obj[1] == null ? "" : obj[1].toString()); //角色描述
                }
                authRolese.setModifyOn(obj[2] == null ? "" : DateUtils.format((Date) obj[2], DateUtils.FORMAT_LONG)); //修改时间
                authRolese.setRoleType(obj[3] == null ? "" : obj[3].toString()); // 角色类型
                authRolese.setRoleLevel(obj[4] == null ? "" : obj[4].toString());//角色级别
                authRolese.setCategory(obj[5] == null ? "" : obj[5].toString());
                if ("3".equals(authRoleseListDTO.getCategory())) {
                    List<String> applyList = authAgencyRepository.getAuthRoleseProject(authRolese.getRoleId());
                    String apply = "";  //适用项目范围
                    if (applyList != null && applyList.size() > 0) {
                        if (applyList.size() == 1) {
                            apply = applyList.get(0).toString();
                        } else {
                            for (int i = 0; i < applyList.size(); i++) {
                                apply = apply + " ," + applyList.get(i).toString();
                            }
                            apply = apply.substring(2, apply.length());
                        }
                    }
                    authRolese.setApply(apply);
                } else if ("2".equals(authRoleseListDTO.getCategory())) {
                    List<String> applyList = authAgencyRepository.getESAuthRoleseProject(authRolese.getRoleId());
                    String apply = "";  //适用项目范围
                    if (applyList != null && applyList.size() > 0) {
                        if (applyList.size() == 1) {
                            apply = applyList.get(0).toString();
                        } else {
                            for (int i = 0; i < applyList.size(); i++) {
                                apply = apply + " ," + applyList.get(i).toString();
                            }
                            apply = apply.substring(2, apply.length());
                        }
                    }
                    authRolese.setApply(apply);

                } else if ("1".equals(authRoleseListDTO.getCategory())) {
                    List<String> applyList = authAgencyRepository.getQCAuthRoleseProject(authRolese.getRoleId());
                    String apply = "";  //适用项目范围
                    if (applyList != null && applyList.size() > 0) {
                        if (applyList.size() == 1) {
                            apply = applyList.get(0).toString();
                        } else {
                            for (int i = 0; i < applyList.size(); i++) {
                                apply = apply + " ," + applyList.get(i).toString();
                            }
                            apply = apply.substring(2, apply.length());
                        }
                    }
                    authRolese.setApply(apply);

                }

                authRoleseList.add(authRolese);
            }
        }
        return authRoleseList;
    }

    @Override
    public int saveAuthRolese(UserInformationEntity userPropertystaffEntity, AuthRoleseListDTO authRoleseListDTO) {
        try {
            AuthRoleseEntity authRoleseEntity = new AuthRoleseEntity();
            authRoleseEntity.setRoleId(IdGen.uuid());//角色id
            authRoleseEntity.setRoleName(authRoleseListDTO.getRoleName());//角色描述
            authRoleseEntity.setPrefix(authRoleseListDTO.getPrefix());//角色前缀
            authRoleseEntity.setCreateOn(new Date());//创建时间
            authRoleseEntity.setCreateBy(userPropertystaffEntity.getStaffId()); //创建人
            authRoleseEntity.setModifyBy(userPropertystaffEntity.getStaffId());//修改人
            authRoleseEntity.setModifyOn(new Date());//修改时间
            authRoleseEntity.setRoleType(authRoleseListDTO.getRoleType());// 角色类型
            authRoleseEntity.setRoleNature(authRoleseListDTO.getRoleLevel());//角色性质
            authRoleseEntity.setState("0");//0 启用  1删除
            authRoleseEntity.setCategory(authRoleseListDTO.getCategory());
            if (!StringUtil.isEmpty(authRoleseListDTO.getApply())) {
                if (authRoleseListDTO.getApply().length() > 0) {
                    String apply[] = authRoleseListDTO.getApply().split(",");
                    for (int i = 0; i < apply.length; i++) {
                        AuthRoleProjectEntity authRoleProjectEntity = new AuthRoleProjectEntity();
                        authRoleProjectEntity.setAuthId(IdGen.uuid());// 关系表id
                        authRoleProjectEntity.setAuthRoleId(authRoleseEntity.getRoleId());//角色ID
                        authRoleProjectEntity.setAuthAgencyId(apply[i].toString());//组织结构id
                        authAgencyRepository.saveAuthRoleseProject(authRoleProjectEntity);
                    }
                }
            }
            authAgencyRepository.saveAuthRolese(authRoleseEntity);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public AuthRoleseListDTO getAuthRoleseById(AuthRoleseListDTO authRoleseListDTO) {
        Map map = new HashMap();
        //角色id
        map.put("roleId", authRoleseListDTO.getRoleId());
        List<Object[]> list = authAgencyRepository.getAuthRoleseList(map, null);
        List<AuthRoleseListDTO> authRoleseList = new ArrayList<>();
        AuthRoleseListDTO authRolese = new AuthRoleseListDTO();
        if (list != null) {
            for (Object[] obj : list) {
                authRolese.setRoleId(obj[0] == null ? "" : obj[0].toString());         //角色id
                authRolese.setRoleName(obj[1] == null ? "" : obj[1].toString()); //角色描述
                authRolese.setModifyOn(obj[2] == null ? "" : DateUtils.format((Date) obj[2], DateUtils.FORMAT_LONG)); //修改时间
                authRolese.setRoleType(obj[3] == null ? "" : obj[3].toString()); // 角色类型
                authRolese.setRoleLevel(obj[4] == null ? "" : obj[4].toString());//角色级别
                authRolese.setCategory(obj[5] == null ? "" : obj[5].toString());
                authRolese.setPrefix(obj[6] == null ? "" : obj[6].toString());
                List<String> applyList = authAgencyRepository.getAuthRoleseProjectID(authRolese.getRoleId());
                String apply = "";  //适用项目范围
                if (applyList != null && applyList.size() > 0) {
                    if (applyList.size() == 1) {
                        apply = applyList.get(0).toString();
                    } else {
                        for (int i = 0; i < applyList.size(); i++) {
                            apply = apply + "," + applyList.get(i).toString();
                        }
                        apply = apply.substring(1, apply.length());
                    }
                }
                authRolese.setApply(apply);
            }
        }
        return authRolese;
    }

    @Override
    public int upAuthRolese(UserInformationEntity userPropertystaffEntity, AuthRoleseListDTO authRoleseListDTO) {
        try {
            if (!StringUtil.isEmpty(authRoleseListDTO.getRoleId())) {
                AuthRoleseEntity authRoleseEntity = authAgencyRepository.getAuthRoleseEntityByid(authRoleseListDTO.getRoleId());
                authRoleseEntity.setRoleName(authRoleseListDTO.getRoleName());//角色描述
                authRoleseEntity.setModifyBy(userPropertystaffEntity.getStaffId());//修改人
                authRoleseEntity.setModifyOn(new Date());//修改时间
                authRoleseEntity.setRoleType(authRoleseListDTO.getRoleType());// 角色类型
                authRoleseEntity.setRoleNature(authRoleseListDTO.getRoleLevel());//角色性质
                authRoleseEntity.setPrefix(authRoleseListDTO.getPrefix());//角色前缀
                authRoleseEntity.setState("0");//0 启用  1删除
                authRoleseEntity.setCategory(authRoleseListDTO.getCategory());
                //删除角色项目
                authAgencyRepository.delAuthRoleseProject(authRoleseListDTO.getRoleId());
                //删除角色项目层级人员绑定信息
                List<String> delRoleMapList = new ArrayList<>();
//                authAgencyRepository.delAuthRoleseProjectMap(authRoleseListDTO.getRoleId(),DateUtils.format(new Date(),DateUtils.FORMAT_LONG));
                if (!StringUtil.isEmpty(authRoleseListDTO.getApply())) {
                    if (authRoleseListDTO.getApply().length() > 0) {
                        String apply[] = authRoleseListDTO.getApply().split(",");
                        for (int i = 0; i < apply.length; i++) {
                            String delRoleMap = "";
                            AuthRoleProjectEntity authRoleProjectEntity = new AuthRoleProjectEntity();
                            authRoleProjectEntity.setAuthId(IdGen.uuid());// 关系表id
                            authRoleProjectEntity.setAuthRoleId(authRoleseEntity.getRoleId());//角色ID
                            authRoleProjectEntity.setAuthAgencyId(apply[i].toString());//组织结构id
                            authAgencyRepository.saveAuthRoleseProject(authRoleProjectEntity);
                            //已存在的项目角色人员工联关系
                            delRoleMap = authRoleseListDTO.getRoleId() + apply[i].toString();
                            delRoleMapList.add(delRoleMap);
//                            authAgencyRepository.upAuthRoleseProjectMap(authRoleseListDTO.getRoleId(),DateUtils.format(new Date(),DateUtils.FORMAT_LONG),apply[i].toString());
                        }
                    }
                }
                authAgencyRepository.upAuthRolese(authRoleseEntity);
                if (delRoleMapList != null && delRoleMapList.size() > 0) {
                    if("1".equals(authRoleseListDTO.getCategory())){ //1.客关 2.工程 3.安全
                        authAgencyRepository.delAuthRoleseProjectQCMapList(delRoleMapList, DateUtils.format(new Date(), DateUtils.FORMAT_LONG), authRoleseListDTO.getRoleId());
                    }else if("2".equals(authRoleseListDTO.getCategory())){
                        authAgencyRepository.delAuthRoleseProjectESMapList(delRoleMapList, DateUtils.format(new Date(), DateUtils.FORMAT_LONG), authRoleseListDTO.getRoleId());
                    }else {
                        authAgencyRepository.delAuthRoleseProjectMapList(delRoleMapList, DateUtils.format(new Date(), DateUtils.FORMAT_LONG), authRoleseListDTO.getRoleId());
                    }
                }

            }
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int delAuthRolese(UserInformationEntity userPropertystaffEntity, AuthRoleseListDTO authRoleseListDTO) {
        try {
            if (!StringUtil.isEmpty(authRoleseListDTO.getRoleId())) {
                if (authAgencyRepository.checkDeleteRole(authRoleseListDTO.getRoleId())) {
                    return 2;
                } else {
                    AuthRoleseEntity authRoleseEntity = authAgencyRepository.getAuthRoleseEntityByid(authRoleseListDTO.getRoleId());
                    authRoleseEntity.setModifyBy(userPropertystaffEntity.getStaffId());//修改人
                    authRoleseEntity.setModifyOn(new Date());//修改时间
                    authRoleseEntity.setState("1");//0 启用  1删除
                    //删除角色项目层级
                    authAgencyRepository.delAuthRoleseProjectMap(authRoleseListDTO.getRoleId(), DateUtils.format(new Date(), DateUtils.FORMAT_LONG));
                    authAgencyRepository.delAuthRoleseProject(authRoleseListDTO.getRoleId());
                    authAgencyRepository.upAuthRolese(authRoleseEntity);
                    return 1;
                }
            }
            return 0;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public AuthFunctionPointListDTO getAuthFunctionPoint(AuthFunctionPointRoleListDTO authFunctionPointRoleListDTO) {
        AuthFunctionPointListDTO authFunctionPointListDTO = new AuthFunctionPointListDTO();
        String category = "";//类别 1.客关 2.工程 3.安全   默认客观
        String classification = "";//分类 0.管理平台   1.前段app  默认前段
        String authRoleId = "";//默认没角色
        if (authFunctionPointRoleListDTO != null) {
            if (!StringUtil.isEmpty(authFunctionPointRoleListDTO.getCategory())) {
                category = authFunctionPointRoleListDTO.getCategory();
            }
            if (!StringUtil.isEmpty(authFunctionPointRoleListDTO.getClassification())) {
                classification = authFunctionPointRoleListDTO.getClassification();
            }
            if (!StringUtil.isEmpty(authFunctionPointRoleListDTO.getAuthRoleId()) && !"0".equals(authFunctionPointRoleListDTO.getAuthRoleId())) {
                authRoleId = authFunctionPointRoleListDTO.getAuthRoleId();
            }
        }
        List<AuthFunctionPointDTO> list = new ArrayList<AuthFunctionPointDTO>();
        //查询第一级模板 一级模块
        List<AuthFunctionPointEntity> firstList = authAgencyRepository.getAuthFunctionPointEntityList(category, classification, "1");
        //查询角色模板关联关系
        List<AuthFunctionPointRoleEntity> roleFirstList = authAgencyRepository.getAuthFunctionPointRoleEntityList(category, classification, authRoleId);
        String isChecked = "";
        if (roleFirstList != null && roleFirstList.size() > 0) {
            //查询第四级角色关联功能点
            for (AuthFunctionPointRoleEntity fourthAuthFunctionPointRoleEntity : roleFirstList) {
                isChecked = isChecked + fourthAuthFunctionPointRoleEntity.getAuthFunctionId() + ",";
            }
        }
        authFunctionPointListDTO.setChecked(isChecked);
        authFunctionPointListDTO.setClassification(classification);
        if (firstList != null && firstList.size() > 0) {
            for (AuthFunctionPointEntity firstAuthFunctionPointEntity : firstList) {
                AuthFunctionPointDTO first = new AuthFunctionPointDTO();
                first.setCid(firstAuthFunctionPointEntity.getCurrentId());//id
                first.setName(firstAuthFunctionPointEntity.getName());//名称
//                if(roleFirstList!=null && roleFirstList.size()>0){
//                    for(AuthFunctionPointRoleEntity firstAuthFunctionPointRoleEntity : roleFirstList){
//                        if(firstAuthFunctionPointRoleEntity.getAuthFunctionId().equals(firstAuthFunctionPointEntity.getCurrentId())){
//                            first.setChecked(true);//是否选中
//                            continue;
//                        }
//                    }
//                }
                //查询第二级模板 二级模块
                List<AuthFunctionPointEntity> secondList = authAgencyRepository.getAuthFunctionPointEntityListById(firstAuthFunctionPointEntity.getCurrentId());
                if (secondList != null && secondList.size() > 0) {
                    List<AuthFunctionPointDTO> selist = new ArrayList<AuthFunctionPointDTO>();
                    for (AuthFunctionPointEntity secondAuthFunctionPointEntity : secondList) {
                        AuthFunctionPointDTO second = new AuthFunctionPointDTO();
                        second.setCid(secondAuthFunctionPointEntity.getCurrentId());
                        second.setName(secondAuthFunctionPointEntity.getName());
//                        if(roleFirstList!=null && roleFirstList.size()>0){
//                            //查询第二级角色关联功能点
//                            for(AuthFunctionPointRoleEntity secondAuthFunctionPointRoleEntity : roleFirstList){
//                                if(secondAuthFunctionPointRoleEntity.getAuthFunctionId().equals(secondAuthFunctionPointEntity.getCurrentId())){
//                                    second.setChecked(true);//是否选中
//                                    continue;
//                                }
//                            }
//                        }
                        //查询第三级级模板 三级模块
                        List<AuthFunctionPointEntity> thirdList = authAgencyRepository.getAuthFunctionPointEntityListById(secondAuthFunctionPointEntity.getCurrentId());
                        if (thirdList != null && thirdList.size() > 0) {
                            List<AuthFunctionPointDTO> thlist = new ArrayList<AuthFunctionPointDTO>();
                            for (AuthFunctionPointEntity thirdAuthFunctionPointEntity : thirdList) {
                                AuthFunctionPointDTO third = new AuthFunctionPointDTO();
                                third.setCid(thirdAuthFunctionPointEntity.getCurrentId());
                                third.setName(thirdAuthFunctionPointEntity.getName());
//                                if(roleFirstList!=null && roleFirstList.size()>0){
//                                    //查询第三级角色关联功能点
//                                    for(AuthFunctionPointRoleEntity thirdAuthFunctionPointRoleEntity : roleFirstList){
//                                        if(thirdAuthFunctionPointRoleEntity.getAuthFunctionId().equals(thirdAuthFunctionPointEntity.getCurrentId())){
//                                            third.setChecked(true);//是否选中
//                                            continue;
//                                        }
//                                    }
//                                }
                                //查询第四级级模板 功能点
                                List<AuthFunctionPointEntity> fourthList = authAgencyRepository.getAuthFunctionPointEntityListById(thirdAuthFunctionPointEntity.getCurrentId());
                                if (fourthList != null && fourthList.size() > 0) {
                                    List<AuthFunctionPointDTO> folist = new ArrayList<AuthFunctionPointDTO>();
                                    for (AuthFunctionPointEntity fourthAuthFunctionPointEntity : fourthList) {
                                        AuthFunctionPointDTO fourth = new AuthFunctionPointDTO();
                                        fourth.setCid(fourthAuthFunctionPointEntity.getCurrentId());
                                        fourth.setName(fourthAuthFunctionPointEntity.getName());
                                        fourth.setConfigurable(fourthAuthFunctionPointEntity.getConfigurable());
                                        fourth.setControl(fourthAuthFunctionPointEntity.getControl());
                                        if (roleFirstList != null && roleFirstList.size() > 0) {
                                            //查询第四级角色关联功能点
                                            for (AuthFunctionPointRoleEntity fourthAuthFunctionPointRoleEntity : roleFirstList) {
                                                if (fourthAuthFunctionPointRoleEntity.getAuthFunctionId().equals(fourthAuthFunctionPointEntity.getCurrentId())) {
//                                                    fourth.setChecked(true);//是否选中
                                                    fourth.setControl(fourthAuthFunctionPointRoleEntity.getControl());
                                                    continue;
                                                }
                                            }
                                        }
                                        fourth.setExplain(fourthAuthFunctionPointEntity.getExplain());
                                        folist.add(fourth);
                                    }
                                    third.setChild(folist);
                                }
                                thlist.add(third);
                            }
                            second.setChild(thlist);
                        }
                        selist.add(second);
                    }
                    first.setChild(selist);
                }
                list.add(first);
            }
            authFunctionPointListDTO.setList(list);
        }
        return authFunctionPointListDTO;
    }

    @Override
    public int addRoleButtonMap(AuthFunctionPointRoleListDTO authFunctionPointRoleList, UserInformationEntity userPropertystaff) {
        try {
            if (authFunctionPointRoleList != null) {
                if (!StringUtil.isEmpty(authFunctionPointRoleList.getAuthRoleId()) && !StringUtil.isEmpty(authFunctionPointRoleList.getCategory()) && !StringUtil.isEmpty(authFunctionPointRoleList.getClassification())) {
                    //删除当前角色 当前类别  当前分类的角色功能点关系
                    List<String> deleteList = new ArrayList<>();
                    authAgencyRepository.upFunctionPointRole(authFunctionPointRoleList.getAuthRoleId(), authFunctionPointRoleList.getCategory(), authFunctionPointRoleList.getClassification(), userPropertystaff.getStaffId(), DateUtils.format(new Date(), DateUtils.FORMAT_LONG));
                    if (!StringUtil.isEmpty(authFunctionPointRoleList.getControlList())) {
                        String apply[] = authFunctionPointRoleList.getControlList().split(",");
                        for (int i = 0; i < apply.length; i++) {
                            String delete = "";
                            AuthFunctionPointRoleEntity authFunctionPointRoleEntity = new AuthFunctionPointRoleEntity();
                            authFunctionPointRoleEntity.setAuthRoleId(authFunctionPointRoleList.getAuthRoleId());//角色id
                            if (apply[i].toString().indexOf(":") != -1) {
                                String con[] = apply[i].toString().split(":");
                                authFunctionPointRoleEntity.setAuthFunctionId(con[0].toString());//功能点id
                                authFunctionPointRoleEntity.setControl(con[1].toString());//控制方式
                            } else {
                                authFunctionPointRoleEntity.setAuthFunctionId(apply[i].toString());//功能点id
                            }
                            authFunctionPointRoleEntity.setState("0");
                            authFunctionPointRoleEntity.setModifyDate(new Date());
                            authFunctionPointRoleEntity.setCategory(authFunctionPointRoleList.getCategory());//类别 1.客关 2.工程 3.安全
                            authFunctionPointRoleEntity.setClassification(authFunctionPointRoleList.getClassification());//分类 0.管理平台   1.前段app
                            authFunctionPointRoleEntity.setModifyBy(userPropertystaff.getStaffId());
                            authAgencyRepository.saveAuthFunctionPointRole(authFunctionPointRoleEntity);
                            delete = authFunctionPointRoleEntity.getAuthRoleId() + authFunctionPointRoleEntity.getAuthFunctionId() + authFunctionPointRoleEntity.getCategory() + authFunctionPointRoleEntity.getClassification();
                            deleteList.add(delete);
                        }
                    }
//                    if(deleteList!=null && deleteList.size()>0){
//                        authAgencyRepository.upConcatFunctionPointRoleBy(authFunctionPointRoleList.getAuthRoleId(), authFunctionPointRoleList.getCategory(), authFunctionPointRoleList.getClassification(), userPropertystaff.getStaffId(),DateUtils.format(new Date(),DateUtils.FORMAT_LONG),deleteList);
//                    }
                }
                return 1;
            } else {
                return 2;
            }
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Map<String, String> getRoleList(String category) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("0", "请选择");
        if (!StringUtil.isEmpty(category) && !"0".equals(category)) {
            List<AuthRoleseEntity> list = authAgencyRepository.getAuthRoleseEntity(category);
            if (list != null && !list.isEmpty()) {
                for (AuthRoleseEntity sce : list) {
                    map.put(sce.getRoleId(), sce.getRoleName());
                }
            }
        } else {
            List<AuthRoleseEntity> list = authAgencyRepository.getAuthRoleseEntityAll();
            if (list != null && !list.isEmpty()) {
                for (AuthRoleseEntity sce : list) {
                    map.put(sce.getRoleId(), sce.getRoleName());
                }
            }
        }
        return map;
    }

    @Override
    public AuthAdminAgencyProjectDTO getAuthAdminAgencyProjectList(AgencyAdminDTO agencyAdminDTO, WebPage webPage, UserInformationEntity userPropertystaffEntity) {
        String agencyId = "%%";
        if (!StringUtil.isEmpty(agencyAdminDTO.getAgencyId())) {
            agencyId = agencyAdminDTO.getAgencyId();
        }
        //1.客关 2.工程 3.安全
        String category = "3";
        if (!StringUtil.isEmpty(agencyAdminDTO.getCategory())) {
            category = agencyAdminDTO.getCategory();
        }
        String type = "st";
        if (!StringUtil.isEmpty(agencyAdminDTO.getCategory())) {
            //st  安全   qc 交付  es 工程   all外部全部
            if ("1".equals(agencyAdminDTO.getCategory())) {
                type = "qc";
            } else if ("2".equals(agencyAdminDTO.getCategory())) {
                type = "es";
            } else if ("3".equals(agencyAdminDTO.getCategory())) {
                type = "st";
            }
        }
        AuthAdminAgencyProjectDTO authgencyProjectDTO = new AuthAdminAgencyProjectDTO();

        AuthAgencyEntity agencyEntities = authAgencyRepository.getAgencyListById(agencyId);
        if (agencyEntities != null) {
            //机构类型 100000000： 100000001： 100000003： 100000002：
            if ("100000000".equals(agencyEntities.getAgencyType())) {
                authgencyProjectDTO.setAgencyType("总部");
            } else if ("100000001".equals(agencyEntities.getAgencyType())) {
                authgencyProjectDTO.setAgencyType("区域");
            } else if ("100000003".equals(agencyEntities.getAgencyType())) {
                authgencyProjectDTO.setAgencyType("城市");
            } else if ("100000002".equals(agencyEntities.getAgencyType())) {
                authgencyProjectDTO.setAgencyType("项目");
            }
            authgencyProjectDTO.setAuthAgencyId(agencyEntities.getAgencyId());
            authgencyProjectDTO.setAuthAgencyName(agencyEntities.getAgencyName());
            List<AuthAdminProjectListDTO> AuthAdminProjectListDTO = new ArrayList<>();

            String check00 = "";//总部
            String check01 = "";//区域
            String check03 = "";//城市
            String check02 = "";//项目
            //1.查看是否给当前登录人授权过总部权限
            if (authAgencyRepository.checkAuthRoleByUserIdandtype("100000000", userPropertystaffEntity.getStaffId())) {
                check00 = "Y";
            }
            //如果当前为区域 查询是否绑定区域
            if ("100000001".equals(agencyEntities.getAgencyType())) {
                if (authAgencyRepository.checkAuthRoleByUserIdAndAgencyId(agencyEntities.getAgencyId(), userPropertystaffEntity.getStaffId())) {
                    check01 = "Y";
                }
            } else if ("100000003".equals(agencyEntities.getAgencyType())) {//当前级别为城市
                //其上一级别为区域 其上一级别为区域
                //判断 单签登录人与 层级id 是否授权绑定
                if (authAgencyRepository.checkAuthRoleByUserIdAndAgencyId(agencyEntities.getAgencyId(), userPropertystaffEntity.getStaffId())) {
                    check03 = "Y";
                }
                //判断是否与上一级id绑定parentId
                if (authAgencyRepository.checkAuthRoleByUserIdAndAgencyId(agencyEntities.getParentId(), userPropertystaffEntity.getStaffId())) {
                    check03 = "Y";
                }
            } else {
                //项目层级比较特殊  上一级别为区域或城市
                //判断当前级别是否绑定
                if (authAgencyRepository.checkAuthRoleByUserIdAndAgencyId(agencyEntities.getAgencyId(), userPropertystaffEntity.getStaffId())) {
                    check02 = "Y";
                }
                //判断是否与上一级id绑定parentId 上一级别 为区域
                if (authAgencyRepository.checkAuthRoleByUserIdAndAgencyId(agencyEntities.getParentId(), userPropertystaffEntity.getStaffId())) {
                    check02 = "Y";
                }
                //判断是否与上一级id绑定parentId 上一级别 为城市
                AuthAgencyEntity agency = authAgencyRepository.getAgencyListById(agencyEntities.getParentId());
                if (agency != null) {
                    if (authAgencyRepository.checkAuthRoleByUserIdAndAgencyId(agency.getParentId(), userPropertystaffEntity.getStaffId())) {
                        check02 = "Y";
                    }
                }
            }
            //
            //判断 当前层id上一级 是否与用户绑定

            if ("Y".equals(check00) || "Y".equals(check01) || "Y".equals(check03) || "Y".equals(check02)) {
                List<Object[]> getAuthRoleList = authAgencyRepository.getAuthAgencyRoleById(agencyId, category, webPage, agencyEntities.getAgencyType());
                if (getAuthRoleList != null) {
                    for (Object[] obj : getAuthRoleList) {
                        AuthAdminProjectListDTO authAdminProjectDTO = new AuthAdminProjectListDTO();
                        authAdminProjectDTO.setAuthRoleId(obj[0] == null ? "" : obj[0].toString());//角色id

                        if (obj[2] != null) {
                            authAdminProjectDTO.setAuthRoleName(obj[1] == null ? obj[2].toString() + "" : obj[2].toString() + obj[1].toString());//角色名称
                        } else {
                            authAdminProjectDTO.setAuthRoleName(obj[1] == null ? "" : obj[1].toString());//角色名称
                        }
                        List<Object[]> applyList = authAgencyRepository.getusetNameByRoleIdAuthAgencyId(authAdminProjectDTO.getAuthRoleId(), agencyId, type);
                        String user = "";  //适用项目范围
                        String modefy = "";
                        if (applyList != null && applyList.size() > 0) {
                            if (applyList.size() == 1) {
                                user = applyList.get(0)[0].toString();
                                modefy = DateUtils.format((Date) applyList.get(0)[1], DateUtils.FORMAT_LONG);
                            } else {
                                for (Object[] userObj : applyList) {
                                    user = user + "," + userObj[0].toString();
                                    modefy = DateUtils.format((Date) userObj[1], DateUtils.FORMAT_LONG);
                                }
                                user = user.substring(1, user.length());
                            }
                        }
                        authAdminProjectDTO.setUserListName(user);
                        authAdminProjectDTO.setUpdaTime(modefy);
                        AuthAdminProjectListDTO.add(authAdminProjectDTO);
                    }
                }
            }
            authgencyProjectDTO.setList(AuthAdminProjectListDTO);
        }
        return authgencyProjectDTO;
    }

    @Override
    public AuthAdminAgencyProjectDTO getESAuthAdminAgencyProjectList(AgencyAdminDTO agencyAdminDTO, WebPage webPage, UserInformationEntity userPropertystaffEntity) {
        String agencyId = "%%";
        if (!StringUtil.isEmpty(agencyAdminDTO.getAgencyId())) {
            agencyId = agencyAdminDTO.getAgencyId();
        }
        //1.客关 2.工程 3.安全
        String category = "2";
        if (!StringUtil.isEmpty(agencyAdminDTO.getCategory())) {
            category = agencyAdminDTO.getCategory();
        }
        String type = "st";
        if (!StringUtil.isEmpty(agencyAdminDTO.getCategory())) {
            //st  安全   qc 交付  es 工程   all外部全部
            if ("1".equals(agencyAdminDTO.getCategory())) {
                type = "qc";
            } else if ("2".equals(agencyAdminDTO.getCategory())) {
                type = "es";
            } else if ("3".equals(agencyAdminDTO.getCategory())) {
                type = "st";
            }
        }
        AuthAdminAgencyProjectDTO authgencyProjectDTO = new AuthAdminAgencyProjectDTO();

        AuthAgencyESEntity agencyEntities = authAgencyRepository.getEStAgencyListById(agencyId);
        if (agencyEntities != null) {
            //机构类型 100000000： 100000001： 100000003： 100000002：
            if ("100000000".equals(agencyEntities.getAgencyType())) {
                authgencyProjectDTO.setAgencyType("总部");
            } else if ("100000001".equals(agencyEntities.getAgencyType())) {
                authgencyProjectDTO.setAgencyType("区域");
            } else if ("100000003".equals(agencyEntities.getAgencyType())) {
                authgencyProjectDTO.setAgencyType("城市");
            } else if ("100000002".equals(agencyEntities.getAgencyType())) {
                authgencyProjectDTO.setAgencyType("项目");
            }
            authgencyProjectDTO.setAuthAgencyId(agencyEntities.getAgencyId());
            authgencyProjectDTO.setAuthAgencyName(agencyEntities.getAgencyName());
            List<AuthAdminProjectListDTO> AuthAdminProjectListDTO = new ArrayList<>();

            String check00 = "";//总部
            String check01 = "";//区域
            String check03 = "";//城市
            String check02 = "";//项目
            //1.查看是否给当前登录人授权过总部权限
            if (authAgencyRepository.checkESAuthRoleByUserIdandtype("100000000", userPropertystaffEntity.getStaffId())) {
                check00 = "Y";
            }
            //如果当前为区域 查询是否绑定区域
            if ("100000001".equals(agencyEntities.getAgencyType())) {
                if (authAgencyRepository.checkESAuthRoleByUserIdAndAgencyId(agencyEntities.getAgencyId(), userPropertystaffEntity.getStaffId())) {
                    check01 = "Y";
                }
            } else if ("100000003".equals(agencyEntities.getAgencyType())) {//当前级别为城市
                //其上一级别为区域 其上一级别为区域
                //判断 单签登录人与 层级id 是否授权绑定
                if (authAgencyRepository.checkESAuthRoleByUserIdAndAgencyId(agencyEntities.getAgencyId(), userPropertystaffEntity.getStaffId())) {
                    check03 = "Y";
                }
                //判断是否与上一级id绑定parentId
                if (authAgencyRepository.checkESAuthRoleByUserIdAndAgencyId(agencyEntities.getParentId(), userPropertystaffEntity.getStaffId())) {
                    check03 = "Y";
                }
            } else {
                //项目层级比较特殊  上一级别为区域或城市
                //判断当前级别是否绑定
                if (authAgencyRepository.checkESAuthRoleByUserIdAndAgencyId(agencyEntities.getAgencyId(), userPropertystaffEntity.getStaffId())) {
                    check02 = "Y";
                }
                //判断是否与上一级id绑定parentId 上一级别 为区域
                if (authAgencyRepository.checkESAuthRoleByUserIdAndAgencyId(agencyEntities.getParentId(), userPropertystaffEntity.getStaffId())) {
                    check02 = "Y";
                }
                //判断是否与上一级id绑定parentId 上一级别 为城市
                AuthAgencyESEntity agency = authAgencyRepository.getEStAgencyListById(agencyEntities.getParentId());
                if (agency != null) {
                    if (authAgencyRepository.checkESAuthRoleByUserIdAndAgencyId(agency.getParentId(), userPropertystaffEntity.getStaffId())) {
                        check02 = "Y";
                    }
                }
            }
            //
            //判断 当前层id上一级 是否与用户绑定

            if ("Y".equals(check00) || "Y".equals(check01) || "Y".equals(check03) || "Y".equals(check02)) {
                List<Object[]> getAuthRoleList = authAgencyRepository.getESAuthAgencyRoleById(agencyId, category, webPage, agencyEntities.getAgencyType());
                if (getAuthRoleList != null) {
                    for (Object[] obj : getAuthRoleList) {
                        AuthAdminProjectListDTO authAdminProjectDTO = new AuthAdminProjectListDTO();
                        authAdminProjectDTO.setAuthRoleId(obj[0] == null ? "" : obj[0].toString());//角色id

                        if (obj[2] != null) {
                            authAdminProjectDTO.setAuthRoleName(obj[1] == null ? obj[2].toString() + "" : obj[2].toString() + obj[1].toString());//角色名称
                        } else {
                            authAdminProjectDTO.setAuthRoleName(obj[1] == null ? "" : obj[1].toString());//角色名称
                        }
                        List<Object[]> applyList = authAgencyRepository.getusetNameByESRoleIdAuthAgencyId(authAdminProjectDTO.getAuthRoleId(), agencyId, type);
                        String user = "";  //适用项目范围
                        String modefy = "";
                        if (applyList != null && applyList.size() > 0) {
                            if (applyList.size() == 1) {
                                user = applyList.get(0)[0].toString();
                                modefy = DateUtils.format((Date) applyList.get(0)[1], DateUtils.FORMAT_LONG);
                            } else {
                                for (Object[] userObj : applyList) {
                                    user = user + "," + userObj[0].toString();
                                    modefy = DateUtils.format((Date) userObj[1], DateUtils.FORMAT_LONG);
                                }
                                user = user.substring(1, user.length());
                            }
                        }
                        authAdminProjectDTO.setUserListName(user);
                        authAdminProjectDTO.setUpdaTime(modefy);
                        AuthAdminProjectListDTO.add(authAdminProjectDTO);
                    }
                }
            }
            authgencyProjectDTO.setList(AuthAdminProjectListDTO);
        }
        return authgencyProjectDTO;
    }

    @Override
    public AuthAdminAgencyProjectDTO getQCAuthAdminAgencyProjectList(AgencyAdminDTO agencyAdminDTO, WebPage webPage, UserInformationEntity userPropertystaffEntity) {
        String agencyId = "%%";
        if (!StringUtil.isEmpty(agencyAdminDTO.getAgencyId())) {
            agencyId = agencyAdminDTO.getAgencyId();
        }
        //1.客关 2.工程 3.安全
        String category = "1";
        if (!StringUtil.isEmpty(agencyAdminDTO.getCategory())) {
            category = agencyAdminDTO.getCategory();
        }
        String type = "qc";
        if (!StringUtil.isEmpty(agencyAdminDTO.getCategory())) {
            //st  安全   qc 交付  es 工程   all外部全部
            if ("1".equals(agencyAdminDTO.getCategory())) {
                type = "qc";
            } else if ("2".equals(agencyAdminDTO.getCategory())) {
                type = "es";
            } else if ("3".equals(agencyAdminDTO.getCategory())) {
                type = "st";
            }
        }
        AuthAdminAgencyProjectDTO authgencyProjectDTO = new AuthAdminAgencyProjectDTO();

        AuthAgencyQCEntity agencyEntities = authAgencyRepository.getQCtAgencyListById(agencyId);
        if (agencyEntities != null) {
            //机构类型 100000000： 100000001： 100000003： 100000002：
            if ("100000000".equals(agencyEntities.getAgencyType())) {
                authgencyProjectDTO.setAgencyType("总部");
            } else if ("100000001".equals(agencyEntities.getAgencyType())) {
                authgencyProjectDTO.setAgencyType("区域");
            } else if ("100000003".equals(agencyEntities.getAgencyType())) {
                authgencyProjectDTO.setAgencyType("城市");
            } else if ("100000002".equals(agencyEntities.getAgencyType())) {
                authgencyProjectDTO.setAgencyType("项目");
            }
            authgencyProjectDTO.setAuthAgencyId(agencyEntities.getAgencyId());
            authgencyProjectDTO.setAuthAgencyName(agencyEntities.getAgencyName());
            List<AuthAdminProjectListDTO> AuthAdminProjectListDTO = new ArrayList<>();

            String check00 = "";//总部
            String check01 = "";//区域
            String check03 = "";//城市
            String check02 = "";//项目
            //1.查看是否给当前登录人授权过总部权限
            if (authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000000", userPropertystaffEntity.getStaffId())) {
                check00 = "Y";
            }
            //如果当前为区域 查询是否绑定区域
            if ("100000001".equals(agencyEntities.getAgencyType())) {
                if (authAgencyRepository.checkQCAuthRoleByUserIdAndAgencyId(agencyEntities.getAgencyId(), userPropertystaffEntity.getStaffId())) {
                    check01 = "Y";
                }
            } else if ("100000003".equals(agencyEntities.getAgencyType())) {//当前级别为城市
                //其上一级别为区域 其上一级别为区域
                //判断 单签登录人与 层级id 是否授权绑定
                if (authAgencyRepository.checkQCAuthRoleByUserIdAndAgencyId(agencyEntities.getAgencyId(), userPropertystaffEntity.getStaffId())) {
                    check03 = "Y";
                }
                //判断是否与上一级id绑定parentId
                if (authAgencyRepository.checkQCAuthRoleByUserIdAndAgencyId(agencyEntities.getParentId(), userPropertystaffEntity.getStaffId())) {
                    check03 = "Y";
                }
            } else {
                //项目层级比较特殊  上一级别为区域或城市
                //判断当前级别是否绑定
                if (authAgencyRepository.checkQCAuthRoleByUserIdAndAgencyId(agencyEntities.getAgencyId(), userPropertystaffEntity.getStaffId())) {
                    check02 = "Y";
                }
                //判断是否与上一级id绑定parentId 上一级别 为区域
                if (authAgencyRepository.checkQCAuthRoleByUserIdAndAgencyId(agencyEntities.getParentId(), userPropertystaffEntity.getStaffId())) {
                    check02 = "Y";
                }
                //判断是否与上一级id绑定parentId 上一级别 为城市
                AuthAgencyQCEntity agency = authAgencyRepository.getQCtAgencyListById(agencyEntities.getParentId());
                if (agency != null) {
                    if (authAgencyRepository.checkQCAuthRoleByUserIdAndAgencyId(agency.getParentId(), userPropertystaffEntity.getStaffId())) {
                        check02 = "Y";
                    }
                }
            }
            //
            //判断 当前层id上一级 是否与用户绑定

            if ("Y".equals(check00) || "Y".equals(check01) || "Y".equals(check03) || "Y".equals(check02)) {
                List<Object[]> getAuthRoleList = authAgencyRepository.getQCAuthAgencyRoleById(agencyId, category, webPage, agencyEntities.getAgencyType());
                if (getAuthRoleList != null) {
                    for (Object[] obj : getAuthRoleList) {
                        AuthAdminProjectListDTO authAdminProjectDTO = new AuthAdminProjectListDTO();
                        authAdminProjectDTO.setAuthRoleId(obj[0] == null ? "" : obj[0].toString());//角色id

                        if (obj[2] != null) {
                            authAdminProjectDTO.setAuthRoleName(obj[1] == null ? obj[2].toString() + "" : obj[2].toString() + obj[1].toString());//角色名称
                        } else {
                            authAdminProjectDTO.setAuthRoleName(obj[1] == null ? "" : obj[1].toString());//角色名称
                        }
                        List<Object[]> applyList = authAgencyRepository.getusetNameByQCRoleIdAuthAgencyId(authAdminProjectDTO.getAuthRoleId(), agencyId, type);
                        String user = "";  //适用项目范围
                        String modefy = "";
                        if (applyList != null && applyList.size() > 0) {
                            if (applyList.size() == 1) {
                                user = applyList.get(0)[0].toString();
                                modefy = DateUtils.format((Date) applyList.get(0)[1], DateUtils.FORMAT_LONG);
                            } else {
                                for (Object[] userObj : applyList) {
                                    user = user + "," + userObj[0].toString();
                                    modefy = DateUtils.format((Date) userObj[1], DateUtils.FORMAT_LONG);
                                }
                                user = user.substring(1, user.length());
                            }
                        }
                        authAdminProjectDTO.setUserListName(user);
                        authAdminProjectDTO.setUpdaTime(modefy);
                        AuthAdminProjectListDTO.add(authAdminProjectDTO);
                    }
                }
            }
            authgencyProjectDTO.setList(AuthAdminProjectListDTO);
        }
        return authgencyProjectDTO;
    }

    @Override
    public AuthAdminAgencyProjectDTO getAuthAdminAgencyRole(AgencyAdminDTO agencyAdminDTO) {
        String agencyId = "%%";
        if (!StringUtil.isEmpty(agencyAdminDTO.getAgencyId())) {
            agencyId = agencyAdminDTO.getAgencyId();
        }
        //1.客关 2.工程 3.安全
        String category = "3";
        if (!StringUtil.isEmpty(agencyAdminDTO.getCategory())) {
            category = agencyAdminDTO.getCategory();
        }
        String type = "st";
        if (!StringUtil.isEmpty(agencyAdminDTO.getCategory())) {
            //st  安全   qc 交付  es 工程   all外部全部
            if ("1".equals(agencyAdminDTO.getCategory())) {
                type = "qc";
            } else if ("2".equals(agencyAdminDTO.getCategory())) {
                type = "es";
            } else if ("3".equals(agencyAdminDTO.getCategory())) {
                type = "st";
            }
        }
        AuthAdminAgencyProjectDTO authgencyProjectDTO = new AuthAdminAgencyProjectDTO();
        List<AuthAdminProjectListDTO> AuthAdminProjectListDTO = new ArrayList<>();
        List<Object[]> applyList = authAgencyRepository.getusetNameByRoleIdAuthAgencyId(agencyAdminDTO.getAuthRoleId(), agencyId, type);
        if (applyList != null && applyList.size() > 0) {
            for (Object[] obj : applyList) {
                AuthAdminProjectListDTO authAdminProjectDTO = new AuthAdminProjectListDTO();
                authAdminProjectDTO.setStaffId(obj[2] == null ? "" : obj[2].toString());
                authAdminProjectDTO.setStaffName(obj[0] == null ? "" : obj[0].toString());
                AuthAdminProjectListDTO.add(authAdminProjectDTO);
            }
        }
        authgencyProjectDTO.setList(AuthAdminProjectListDTO);
        return authgencyProjectDTO;
    }

    @Override
    public Map<String, String> getAuthAgencyRole(AgencyAdminDTO agencyAdminDTO) {
        String agencyId = "%%";
        if (!StringUtil.isEmpty(agencyAdminDTO.getAgencyId())) {
            agencyId = agencyAdminDTO.getAgencyId();
        }
        String type = "st";
        if (!StringUtil.isEmpty(agencyAdminDTO.getCategory())) {
            //st  安全   qc 交付  es 工程   all外部全部
            if ("1".equals(agencyAdminDTO.getCategory())) {
                type = "qc";
            } else if ("2".equals(agencyAdminDTO.getCategory())) {
                type = "es";
            } else if ("3".equals(agencyAdminDTO.getCategory())) {
                type = "st";
            }
        }
        Map<String, String> map = new LinkedHashMap<>();
        List<Object[]> applyList = authAgencyRepository.getusetNameByRoleIdAuthAgencyId(agencyAdminDTO.getAuthRoleId(), agencyId, type);
        if (applyList != null && applyList.size() > 0) {
            for (Object[] obj : applyList) {
                map.put(obj[2] == null ? "" : obj[2].toString(), obj[0] == null ? "" : obj[0].toString());
            }
        }
        return map;
    }

    @Override
    public Map<String, String> getESAuthAgencyRole(AgencyAdminDTO agencyAdminDTO) {
        String agencyId = "%%";
        if (!StringUtil.isEmpty(agencyAdminDTO.getAgencyId())) {
            agencyId = agencyAdminDTO.getAgencyId();
        }
        String type = "st";
        if (!StringUtil.isEmpty(agencyAdminDTO.getCategory())) {
            //st  安全   qc 交付  es 工程   all外部全部
            if ("1".equals(agencyAdminDTO.getCategory())) {
                type = "qc";
            } else if ("2".equals(agencyAdminDTO.getCategory())) {
                type = "es";
            } else if ("3".equals(agencyAdminDTO.getCategory())) {
                type = "st";
            }
        }
        Map<String, String> map = new LinkedHashMap<>();
        List<Object[]> applyList = authAgencyRepository.getusetNameByESRoleIdAuthAgencyId(agencyAdminDTO.getAuthRoleId(), agencyId, type);
        if (applyList != null && applyList.size() > 0) {
            for (Object[] obj : applyList) {
                map.put(obj[2] == null ? "" : obj[2].toString(), obj[0] == null ? "" : obj[0].toString());
            }
        }
        return map;
    }

    @Override
    public Map<String, String> getQCAuthAgencyRole(AgencyAdminDTO agencyAdminDTO) {
        String agencyId = "%%";
        if (!StringUtil.isEmpty(agencyAdminDTO.getAgencyId())) {
            agencyId = agencyAdminDTO.getAgencyId();
        }
        String type = "qc";
        if (!StringUtil.isEmpty(agencyAdminDTO.getCategory())) {
            //st  安全   qc 交付  es 工程   all外部全部
            if ("1".equals(agencyAdminDTO.getCategory())) {
                type = "qc";
            } else if ("2".equals(agencyAdminDTO.getCategory())) {
                type = "es";
            } else if ("3".equals(agencyAdminDTO.getCategory())) {
                type = "st";
            }
        }
        Map<String, String> map = new LinkedHashMap<>();
        List<Object[]> applyList = authAgencyRepository.getusetNameByQCRoleIdAuthAgencyId(agencyAdminDTO.getAuthRoleId(), agencyId, type);
        if (applyList != null && applyList.size() > 0) {
            for (Object[] obj : applyList) {
                map.put(obj[2] == null ? "" : obj[2].toString(), obj[0] == null ? "" : obj[0].toString());
            }
        }
        return map;
    }

    @Override
    public int saveOrUpdateUserRoleRelation(UserAndRoleRelationDTO userAndRoleRelationDTO) {
        String[] staffId;//人员Id
        if (null != userAndRoleRelationDTO.getAuthStaffId() && !StringUtil.isEmpty(userAndRoleRelationDTO.getAuthStaffId())) {
            staffId = userAndRoleRelationDTO.getAuthStaffId().split(",");
        } else {
            staffId = null;
        }
        if (null != userAndRoleRelationDTO.getAuthRoleIds() && !StringUtil.isEmpty(userAndRoleRelationDTO.getAuthRoleIds())) {

            String[] roleId = userAndRoleRelationDTO.getAuthRoleIds().split("\\|");
            if ("3".equals(userAndRoleRelationDTO.getCategory())) {
                authAgencyRepository.delAuthRoleseProjectMapByid(roleId[0].toString(), roleId[1].toString(), DateUtils.format(new Date(), DateUtils.FORMAT_LONG));
                if (null != staffId) {
                    for (String sId : staffId) {
                        RoleStaffProjectMapEntity roleStaffProjectMapEntity = new RoleStaffProjectMapEntity();
                        roleStaffProjectMapEntity.setState("1");
                        roleStaffProjectMapEntity.setStaffId(sId);
                        roleStaffProjectMapEntity.setRoleId(roleId[0].toString());
                        roleStaffProjectMapEntity.setAgencyId(roleId[1].toString());
                        userAccreditRepository.saveOrUpdateRoleStaff(roleStaffProjectMapEntity);
                    }
                }
            } else if ("2".equals(userAndRoleRelationDTO.getCategory())) {
                authAgencyRepository.delESAuthRoleseProjectMapByid(roleId[0].toString(), roleId[1].toString(), DateUtils.format(new Date(), DateUtils.FORMAT_LONG));
                if (null != staffId) {
                    for (String sId : staffId) {
                        RoleStaffProjectMapESEntity roleStaffProjectMapES = new RoleStaffProjectMapESEntity();
                        roleStaffProjectMapES.setState("1");
                        roleStaffProjectMapES.setStaffId(sId);
                        roleStaffProjectMapES.setRoleId(roleId[0].toString());
                        roleStaffProjectMapES.setAgencyId(roleId[1].toString());
                        userAccreditRepository.saveESOrUpdateRoleStaff(roleStaffProjectMapES);
                    }
                }
            } else if ("1".equals(userAndRoleRelationDTO.getCategory())) {
                authAgencyRepository.delQCAuthRoleseProjectMapByid(roleId[0].toString(), roleId[1].toString(), DateUtils.format(new Date(), DateUtils.FORMAT_LONG));
                if (null != staffId) {
                    for (String sId : staffId) {
                        RoleStaffProjectMapQCEntity roleStaffProjectMapQC = new RoleStaffProjectMapQCEntity();
                        roleStaffProjectMapQC.setState("1");
                        roleStaffProjectMapQC.setStaffId(sId);
                        roleStaffProjectMapQC.setRoleId(roleId[0].toString());
                        roleStaffProjectMapQC.setAgencyId(roleId[1].toString());
                        userAccreditRepository.saveQCOrUpdateRoleStaff(roleStaffProjectMapQC);
                    }
                }
            }

        }
        return 1;
    }

    //根据当前登录人id查询一级菜单id
    @Override
    public List<String> getNewVimList(String staffId, String level, String category) {
        if ("3".equals(category)) {
            //按员工id 查询所有关联的角色信息
            List<String> roleIdList = authAgencyRepository.getRoleIdlistById(staffId);
            //判断角色IdList查询所有角色关联的一二三级模块信息
            if (roleIdList != null) {
                List<String> viewIdList = authAgencyRepository.getRoleViewIdlistById(roleIdList, level, "3");
                if (viewIdList != null) {
                    return viewIdList;
                }
            }
        } else if ("2".equals(category)) {
            //按员工id 查询所有关联的角色信息
            List<String> roleIdList = authAgencyRepository.getESRoleIdlistById(staffId);
            //判断角色IdList查询所有角色关联的一二三级模块信息
            if (roleIdList != null) {
                List<String> viewIdList = authAgencyRepository.getRoleViewIdlistById(roleIdList, level, "2");
                if (viewIdList != null) {
                    return viewIdList;
                }
            }
        } else if ("1".equals(category)) {
            //按员工id 查询所有关联的角色信息
            List<String> roleIdList = authAgencyRepository.getQCRoleIdlistById(staffId);

            //判断角色IdList查询所有角色关联的一二三级模块信息
            if (roleIdList != null) {
                List<String> viewIdList = authAgencyRepository.getRoleViewIdlistById(roleIdList, level, "1");
                if (viewIdList != null) {
                    return viewIdList;
                }
            }
        }


        return null;
    }

    @Override
    public List<String> getAuthFunctionByStaffId(String staffId, String level, String category) {

        List<String> roleIdList = authAgencyRepository.getAuthFunctionByStaffId(staffId, level, category);
        if (roleIdList != null) {
            return roleIdList;
        }
        return null;
    }

    @Override
    public List<String> getProjectAuthFunctionByStaffId(String staffId, String level, String category) {
        List<String> roleIdList = authAgencyRepository.getProjectAuthFunctionByStaffId(staffId, level, category);
        if (roleIdList != null) {
            return roleIdList;
        }
        return null;
    }

    @Override
    public List<String> getQCProjectAuthFunctionByStaffId(String staffId, String level, String category) {
        List<String> roleIdList = authAgencyRepository.getQCProjectAuthFunctionByStaffId(staffId, level, category);
        if (roleIdList != null) {
            return roleIdList;
        }
        return null;
    }

    @Override
    public List<String> getAuthFunctionAndProjectIdByStaffId(String function, String staffId, String level) {
        List<String> roleIdList = authAgencyRepository.getAuthFunctionAndProjectIdByStaffId(function, staffId, level);
        List<AuthAgencyEntity> authAgencyEntityList = authAgencyRepository.getAllAuthAgencyList();
        List<String> roleIdList1 = new ArrayList<String>();
        if (roleIdList != null) {
            for (String agencyId : roleIdList) {
                for (AuthAgencyEntity authAgencyEntity : authAgencyEntityList) {
                    String agencyPath = authAgencyEntity.getAgencyPath().replace("/", ",").substring(1);
                    String str[] = agencyPath.split(",");
                    List<String> list = Arrays.asList(str);
                    if (list.contains(agencyId)) {
                        roleIdList1.add(authAgencyEntity.getAgencyId());
                    }
                }
            }
            if (null != roleIdList1 && roleIdList.size() > 0) {
                roleIdList.addAll(roleIdList1);
            }
            return roleIdList;
        }
        return null;
    }

    @Override
    public List<String> getNewsFunctionByStaffId(String function, String staffId) {
        return authAgencyRepository.getNewsFunctionByStaffId(function, staffId);
    }

    @Override
    public Map<String, String> getNewsProjectNameAndIdByStaffId(String function, String staffId) {
        Map<String, String> map = new HashMap<>();
        List<Object[]> list = authAgencyRepository.getNewsProjectNameAndIdByStaffId(function, staffId);
        if(null != list && list.size()>0){
            if(list.size()>1){
                map.put("","请选择");
            }
            for(Object[] obj : list){
                map.put(obj[0] == null ? "" : obj[0].toString(),obj[1] == null ? "" : obj[1].toString());
            }
        }
        return map;
    }

    @Override
    public Map getCityListByAreaId(String areaId, String category) {


        Map<String, String> map = new HashMap<>();
        if ("3".equals(category)) {
            List<AuthAgencyEntity> authAgencyEntities = authAgencyRepository.getCityListByAreaId(areaId);
            if (authAgencyEntities != null) {
                authAgencyEntities.forEach(authAgencyEntity -> map.put(authAgencyEntity.getAgencyId(), authAgencyEntity.getAgencyName()));
            }
        }
        if ("2".equals(category)) {
            List<AuthAgencyESEntity> authAgencyEntities = authAgencyRepository.getESCityListByAreaId(areaId);
            if (authAgencyEntities != null) {
                authAgencyEntities.forEach(authAgencyESEntity -> map.put(authAgencyESEntity.getAgencyId(), authAgencyESEntity.getAgencyName()));
            }
        }
        if ("1".equals(category)) {
            List<AuthAgencyQCEntity> authAgencyEntities = authAgencyRepository.getQCCityListByAreaId(areaId);
            if (authAgencyEntities != null) {
                authAgencyEntities.forEach(authAgencyQCEntity -> map.put(authAgencyQCEntity.getAgencyId(), authAgencyQCEntity.getAgencyName()));
            }
        }
        return map;
    }

    @Override
    public Map getAreaListByInit() {
        List<AuthAgencyEntity> authAgencyEntities = authAgencyRepository.getCityListByAreaId("");
        Map<String, String> map = new HashMap<>();
        if (authAgencyEntities != null) {
            authAgencyEntities.forEach(authAgencyEntity -> map.put(authAgencyEntity.getAgencyId(), authAgencyEntity.getAgencyName()));
        }
        return map;
    }

    @Override
    public Map getESAreaListByInit() {
        List<AuthAgencyESEntity> authAgencyEntities = authAgencyRepository.getESCityListByAreaId("");
        Map<String, String> map = new HashMap<>();
        if (authAgencyEntities != null) {
            authAgencyEntities.forEach(authAgencyESEntity -> map.put(authAgencyESEntity.getAgencyId(), authAgencyESEntity.getAgencyName()));
        }
        return map;
    }

    @Override
    public Map getQCAreaListByInit() {
        List<AuthAgencyQCEntity> authAgencyEntities = authAgencyRepository.getQCCityListByAreaId("");
        Map<String, String> map = new HashMap<>();
        if (authAgencyEntities != null) {
            authAgencyEntities.forEach(authAgencyQCEntity -> map.put(authAgencyQCEntity.getAgencyId(), authAgencyQCEntity.getAgencyName()));
        }
        return map;
    }

    @Override
    public void saveProjectByProjectName(String projectName, String cityId, String category) {
        if ("3".equals(category)) {
            AuthAgencyEntity authAgencyEntity = new AuthAgencyEntity();
            AuthAgencyEntity authAgencyEntity1 = authAgencyRepository.getAuthAgencyByID(cityId);
            authAgencyEntity.setAgencyId(IdGen.uuid());
            authAgencyEntity.setAgencyName(projectName);//项目名称
            authAgencyEntity.setAgencyPath(authAgencyEntity1.getAgencyPath() + "/" + authAgencyEntity.getAgencyId());//路径
            authAgencyEntity.setAgencyType("100000002");//项目
            authAgencyEntity.setParentId(cityId);//父级ID
            authAgencyEntity.setCreateTime(new Date());//创建时间
            authAgencyEntity.setModifyTime(new Date());//修改时间
            authAgencyEntity.setStatus("1");//状态
            authAgencyEntity.setBusinesssource("100000000");//业态
            authAgencyEntity.setIsRoot("0");//是否为根目录
            authAgencyEntity.setOutEmploy("0");//是否为外部单位
            authAgencyEntity.setLevel(authAgencyEntity1.getLevel() + 1);
            authAgencyRepository.saveAuthAgency(authAgencyEntity);
        }
        if ("2".equals(category)) {
            AuthAgencyESEntity authAgencyESEntity = new AuthAgencyESEntity();
            AuthAgencyESEntity authAgencyESEntity1 = authAgencyRepository.getESAuthAgencyByID(cityId);
            authAgencyESEntity.setAgencyId(IdGen.uuid());
            authAgencyESEntity.setAgencyName(projectName);//项目名称
            authAgencyESEntity.setAgencyPath(authAgencyESEntity1.getAgencyPath() + "/" + authAgencyESEntity.getAgencyId());//路径
            authAgencyESEntity.setAgencyType("100000002");//项目
            authAgencyESEntity.setParentId(cityId);//父级ID
            authAgencyESEntity.setCreateTime(new Date());//创建时间
            authAgencyESEntity.setModifyTime(new Date());//修改时间
            authAgencyESEntity.setStatus("1");//状态
            authAgencyESEntity.setBusinesssource("100000000");//业态
            authAgencyESEntity.setIsRoot("0");//是否为根目录
            authAgencyESEntity.setOutEmploy("0");//是否为外部单位
            authAgencyESEntity.setLevel(authAgencyESEntity1.getLevel() + 1);
            authAgencyRepository.saveESAuthAgency(authAgencyESEntity);
        }

    }

    @Override
    public List<String> getAgencyIdList(String userId) {
        List<AgencyTreeDTO> agencylist = getAgencyList(userId);
        List<String> agencyIdlist = new ArrayList<>();
        if (agencylist != null && agencylist.size() > 0) {
            for (int i = 0; i < agencylist.size(); i++) {
                agencyIdlist.add(agencylist.get(i).getId());
            }
        }
        return null;
    }

    @Override
    public List<AuthClassifyStaffDTO> getAuthClassifyStaff(AuthClassifyStaffDTO authClassifyStaffDTO, WebPage webPage) {
        List<AuthClassifyStaffDTO> getAuthClassifyStaffList = new ArrayList<>();
        if (!StringUtil.isEmpty(authClassifyStaffDTO.getProjectNum())) {
            List<Object[]> list = authAgencyRepository.getAuthClassifyStaff(authClassifyStaffDTO.getProjectNum(), authClassifyStaffDTO.getClassifyId(), webPage);
            if (list != null && !list.isEmpty()) {
                for (Object[] obj : list) {
                    AuthClassifyStaffDTO getAuthClassifyStaff = new AuthClassifyStaffDTO();
                    getAuthClassifyStaff.setStaffName(obj[0] == null ? "" : obj[0].toString());
                    getAuthClassifyStaff.setClassifyId(obj[1] == null ? "" : obj[1].toString());
                    getAuthClassifyStaff.setProjectName(obj[2] == null ? "" : obj[2].toString());
                    getAuthClassifyStaff.setModifyOn(obj[3] == null ? "" : DateUtils.format((Date) obj[3], DateUtils.FORMAT_LONG));
                    getAuthClassifyStaffList.add(getAuthClassifyStaff);
                }
            }
        }
        return getAuthClassifyStaffList;
    }

    public List<AgencyTreeDTO> getAgencyList(String userId) {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<AgencyTreeDTO>();
        //查看是否给当前登录人授权过总部权限
        if (authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000000", userId)) {
            //查看总部下的所有内容
            List<AuthAgencyQCEntity> agencyQcEntities = authAgencyRepository.getQCAllAgencyList();
            agencyTreeDTOs = getAgencyTreeDTOS(agencyQcEntities);
        } else if (authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000001", userId)) {
            //如果是区域级别，找出拥有区域权限的权限id
            List<String> parentIdList = authAgencyRepository.checkQCAuthRoleListByUserIdandtype("100000001", userId);
            if (parentIdList != null && !parentIdList.isEmpty()) {
                //区域 城市下项目
                List<AuthAgencyQCEntity> parEntities = authAgencyRepository.getQCAllAgencyListByParentId(parentIdList, null, "100000003");
            }
        } else if (authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000003", userId)) {
            List<String> parentIdList = authAgencyRepository.checkQCAuthRoleListByUserIdandtype("100000003", userId);
            //根据条件删选
            if (parentIdList != null && !parentIdList.isEmpty()) {
                List<AuthAgencyQCEntity> agencyQCEntities = authAgencyRepository.getQCAllAgencyListByParentId(parentIdList, parentIdList, "100000002");
                agencyTreeDTOs = getAgencyTreeDTOS(agencyQCEntities);
            }
        } else if (authAgencyRepository.checkQCAuthRoleByUserIdandtype("100000002", userId)) {
            List<String> agencyIdList = authAgencyRepository.checkQCAuthRoleListByUserIdandtype("100000002", userId);
            //根据条件删选
            if (agencyIdList != null && !agencyIdList.isEmpty()) {
                List<AuthAgencyQCEntity> agencyQCEntities = authAgencyRepository.getQCAllAgencyListByParentId(null, agencyIdList, "100000002");
                agencyTreeDTOs = getAgencyTreeDTOS(agencyQCEntities);
            }
        }
        return agencyTreeDTOs;
    }

    public List<AgencyTreeDTO> getAgencyTreeDTOS(List<AuthAgencyQCEntity> agencyQcEntities) {
        List<AgencyTreeDTO> agencyTreeDTOs = new ArrayList<>();
        if (agencyQcEntities != null && !agencyQcEntities.isEmpty()) {
            AgencyTreeDTO agencyTreeDTO;
            for (AuthAgencyQCEntity pro : agencyQcEntities) {
                agencyTreeDTO = new AgencyTreeDTO();
                agencyTreeDTO.setId(pro.getAgencyId());
                agencyTreeDTO.setpId(pro.getParentId());
                agencyTreeDTO.setName(pro.getAgencyName());
                agencyTreeDTO.setAgencyType(pro.getAgencyType());
                agencyTreeDTOs.add(agencyTreeDTO);
            }
        }
        return agencyTreeDTOs;
    }
}
