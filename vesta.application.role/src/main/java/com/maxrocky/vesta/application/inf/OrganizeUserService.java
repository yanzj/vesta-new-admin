package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.dto.adminDTO.OrganizeUserDTO;
import com.maxrocky.vesta.application.dto.adminDTO.OrganizeUserRecDTO;

import java.util.List;

/**
 * Created by chen on 2016/6/2.
 */
public interface OrganizeUserService {
    //新增组 用户 关系
    void addOrganizeUser(OrganizeUserRecDTO organizeUserRecDTO);
    //删除组 用户 关系
    void delOrganizeUser(OrganizeUserRecDTO organizeUserRecDTO);
    //根据条件获取组外用户列表
    List<OrganizeUserDTO> getStaffoutOrganize(OrganizeUserDTO organizeUserDTO);
    //根据组ID获取组内用户列表
    List<OrganizeUserDTO> getStaffByOrganizeId(String organizeId);
}
