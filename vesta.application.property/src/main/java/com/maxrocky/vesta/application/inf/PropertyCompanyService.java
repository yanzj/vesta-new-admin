package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.PropertyCompanyMapDTO;
import com.maxrocky.vesta.application.DTO.HouseCompanyDTO;
import com.maxrocky.vesta.application.DTO.HouseProjectDTO;
import com.maxrocky.vesta.application.DTO.PropertyCompanyDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleRoleDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;

import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by Administratord on 2016/1/15.
 * 物业项目公司 业务逻辑层接口
 */
public interface PropertyCompanyService {

    /**
     * 初始化物业管理公司列表
     * @param propertyCompanySearchDTO
     * @param webPage
     * @return
     */
    List<PropertyCompanyDTO> queryPropertyCompanyMessage(PropertyCompanyDTO propertyCompanySearchDTO,WebPage webPage);

    /**
     * 查询下拉框
     * 区域 Map
     * 公司 Map
     * 项目 Map
     * @return
     */
    PropertyCompanyMapDTO queryPropertyCompanyMap(String projectId);


    /**
     * 公司权限管理页面列表
     * @param roleRole
     *
     * @return
     */
    List<RoleRoleDTO> listRoleSetMap(RoleRoleDTO roleRole);

    /**
     * 添加或修改物业公司及权限
     * @param propertyServicesDTO
     * @param userPropertystaffEntit
     */
    void addORupdatePropertyCompany(PropertyCompanyDTO propertyServicesDTO,UserPropertyStaffEntity userPropertystaffEntit);

    /**
     * 根据物业公司ID查询信息详情
     * @param companyId
     * @return
     */
    PropertyCompanyDTO queryPropertyCompanyById(String companyId);

    /**
     * 删除信息或更新状态
     * @return
     */
    String removeORupdatePropertyServices(UserPropertyStaffEntity userPropertystaffEntity,String companyId,String propertyType);

    /**
     * 查询当前管理员是否已分配负责项目
     * @param projectAdmin
     * @return
     */
    String  whetherAreAdmin(String projectAdmin);

    /**
     * 联动 根据区域ID查询公司信息
     * @param companyName
     * @return
     */
    List<HouseCompanyDTO> getHouseCompany(String companyName);

    /**
     * 联动 根据公司ID查询项目信息
     * @param pojectName
     * @return
     */
    List<HouseProjectDTO> getHouseProject(String pojectName);
}
