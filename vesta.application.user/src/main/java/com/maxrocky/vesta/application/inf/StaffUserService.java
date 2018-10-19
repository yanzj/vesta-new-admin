package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.UserPropertystaffDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 会员账户管理_Service
 * Created by WeiYangDong on 2016/8/11.
 */
public interface StaffUserService {

    /**
     * 获取账号信息列表
     */
    List<Map<String,Object>> getStaffUserList(UserPropertystaffDTO userPropertystaffDTO,WebPage webPage);

    /**
     * 检索员工用户总人数
     */
    Long getStaffUserCount(UserPropertystaffDTO userPropertystaffDTO);

    /**
     * 重置员工用户密码(默认密码为123456)
     */
    boolean resetPassWord(UserPropertystaffDTO userPropertystaffDTO);

    /**
     * 更新员工用户密码(与重置密码方法可合并)
     */
    boolean updatePassWord(UserPropertystaffDTO userPropertystaffDTO);

    /**
     * 逻辑删除员工信息
     */
    boolean delStaffUser(UserPropertystaffDTO userPropertystaffDTO);

    /**
     * 通过员工用户Id获取员工用户信息
     */
    UserPropertyStaffEntity getStaffUserById(String staffId);

    /**
     * 保存或更新员工用户信息
     */
    UserPropertyStaffEntity saveOrUpdateStaffUser(UserPropertystaffDTO userPropertystaffDTO);

    /**
     * 保存或更新员工角色权限关系
     */
    void saveOrUpdateRoleanthority(UserPropertystaffDTO userPropertystaffDTO);

    /**
     * 通过员工用户Id获取角色信息
     */
    List<Map<String,Object>> getRoleByStaffId(String staffId);

    /**
     * 通过员工用户Id获取范围权限
     */
    List<Map<String,Object>> getRoleScopeByStaffId(String staffId);

    /**
     * 通过员工用户Id检索城市列表(角色范围所对应的城市)
     */
    List<Map<String,Object>> getCityListByStaff(String staffId);

    /**
     * 通过员工用户Id检索城市列表(角色范围所对应的城市)
     * 备注:解决角色交叉授权_2016-09-05_Wyd
     */
    List<Map<String,Object>> getCityListByStaff(String staffId,String menuId);

    /**
     * 通过员工Id和城市Id检索项目列表
     */
    List<Object[]> listProjectByCity(String cityId);

    /**
     * 通过员工Id和城市Id检索项目列表(角色范围所对应的项目)
     */
    List<Object[]> listProjectByCity(String staffId,String cityId);

    /**
     * 通过员工Id和城市Id检索项目列表(角色范围所对应的项目)
     * 备注:解决角色交叉授权_2016-09-05_Wyd
     */
    public List<Object[]> listProjectByCity(String staffId,String cityId,String menuId);

    /**
     * 通过员工用户Id获取权限范围项目列表(用于项目权限控制)
     */
    List<Map<String,Object>> getProjectScopeByStaffId(String staffId);

    /**
     * 通过员工用户Id和菜单Id获取权限范围项目列表(用于项目权限控制)
     */
    List<Map<String,Object>> getProjectScopeByStaffId(String staffId,String menuId);

    /**
     * 获取城市列表(ALL)
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> listCity();

    /**
     * 获取批量账号信息列表
     */
    List<Map<String,Object>> getBatchStaffUser(UserPropertystaffDTO userPropertystaffDTO);

    /**
     * 检索所有城市所有项目
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> listAllProject();

    /**
     * 批量用户设置角色
     */
    void batchSetRole(UserPropertystaffDTO userPropertystaffDTO);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Param:
    *  @Description: 导出excel
    */
    public String exportExcel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, List<Map<String, Object>> staffUserList, WebPage webPage) throws IOException;
}
