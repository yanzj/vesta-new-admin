package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.dto.adminDTO.RoleDataAdminDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;

/**
 * Created by chen on 2016/6/20.
 */
public interface RoleDataService {
    //新增数据权限
    ApiResult addRoleData(RoleDataAdminDTO roleDataAdminDTO);
    //修改数据权限
    void updateRoleData(RoleDataAdminDTO roleDataAdminDTO);
    //删除角色 组关系
    void delRoleOrganize(RoleDataAdminDTO roleDataAdminDTO);
    //APP同步人员权限数据
    ApiResult getPersonAuthority(String timeStamp,String num);
}

