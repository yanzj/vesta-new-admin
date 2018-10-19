package com.maxrocky.vesta.application.inf;


import com.maxrocky.vesta.application.dto.adminDTO.RoleRoleMouldDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleRoleDTO;

import java.util.List;

/**
 * Created by zhanghj on 2016/1/18.
 */
public interface RoleRoleService {
    /**
     * 查询所有权限
     * @return
     */
    public List<RoleRoleDTO> listRoleRole();

    /**
     * 根据所属模块查询权限：1-物业管理，2-商户管理，3-房屋租赁管理，4-邻里圈管理，5-用户管理管理，6-数据统计
     * @param roledesc
     * @return
     */
    public List<RoleRoleDTO> listRoleRoleByroledesc(String roledesc);

    /**
     *
     * @return
     */
    public List<RoleRoleMouldDTO> getGroup();
}
