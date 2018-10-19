package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;


import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2016/1/22 11:38.
 * Describe:员工信息Repository接口
 */
public interface UserPropertyStaffRepository {

    /**
     * Describe:根据用户名、密码获取用户信息
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:19:53
     */
    UserPropertyStaffEntity getByUserNameAndPassword(String userName, String password);

    /**
     * 根据用户名和手机号校验用户
     * */
    UserPropertyStaffEntity getByUserNameAndMobile(String userName,String mobile);

    /**
     * Describe:根据Id获取员工信息
     * CreateBy:Tom
     * CreateOn:2016-01-22 01:53:04
     */
    UserPropertyStaffEntity get(String staffId);

    UserInformationEntity getUser(String staffId);

    UserPropertyStaffEntity CheckStaffByIdAPwd(UserPropertyStaffEntity propertystaff);

    /**
     * 获取所有员工信息列表
     * @return
     */
    public List<UserPropertyStaffEntity> listPropertyStaff(UserPropertyStaffEntity userPropertyStaffEntity,WebPage webPage);

    /**
     * 删除员工信息
     * @param staffId
     * @return
     */
    public boolean deleteStaff(String staffId);

    /**
     * 添加新员工
     * @param userPropertyStaffEntity
     * @return
     */
    public boolean addStaff(UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 新权限的添加新员工
     * @param userInformationEntity
     * @return
     */
    public boolean addOuterStaff(UserInformationEntity userInformationEntity);

    /**
     * 修改员工
     * @param userPropertyStaffEntity
     * @return
     */
    public boolean updateStaff(UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 修改员工
     * @param userInformationEntity
     * @return
     */
    public boolean saveOrUpdateStaff(UserInformationEntity userInformationEntity);


    /**
     * 根据项目和
     * @param sectionId
     * @param projectId
     * @return
     */
    public List<UserPropertyStaffEntity>  listStaffByCompanyAndSection(String projectId,String sectionId);

    /**
     * 判断该用户名是否存在
     * @param userName
     * @return
     */
    public UserPropertyStaffEntity testStaffByUserName(String userName);

    /**
     * @param userName
     * @return
     * 判断OA的用户名是否存在
     */
    UserPropertyStaffEntity checkOAUserName(String userName);

    /**
     * @param userName
     * @return
     * 判断OA的用户名是否存在
     */
    UserInformationEntity getStaffByName(String userName);

    /**
     * 添加员工
     * @param userPropertyStaffEntity
     */
    void addUserPropertyStaff(UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 添加员工
     * @param userInformationEntity
     */
    void addUserInformation(UserInformationEntity userInformationEntity);

    /**
     * 修改员工信息
     * @param userPropertyStaffEntity
     */
    void updateUserPropertyStaff(UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * Describe:This is describe.
     * CreateBy:Tom
     * CreateOn:2016-03-18 09:31:48
     */
    UserPropertyStaffEntity getByUserName(String userName);

    //根据用户名获取员工信息
    UserInformationEntity getStaffBySysName(String sysName);

    /**根据员工名搜索员工*/
    List<UserPropertyStaffEntity> searchStaffByName(String staffName);

    /**根据用户名和ID来做非我判断*/
    UserPropertyStaffEntity getByNameID(String userName,String id);

    /**根据用户名和ID来做非我判断 工程  新权限*/
    UserInformationEntity getAuthByNameID(String userName,String id);


    /* -------------会员账户管理模块------------- */

    /**
     * 获取员工用户信息列表
     * @param params 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getStaffUserList(Map<String,Object> params,WebPage webPage);

    /**
     * 检索员工用户总人数
     * @param params 参数
     * @return  Long
     */
    Long getStaffUserCount(Map<String,Object> params);

    /**
     * 通过员工用户Id获取角色信息
     * @param staffId  员工用户Id
     * @return
     */
    List<Map<String,Object>> getRoleByStaffId(String staffId);

    /**
     * 通过员工用户Id检索员工详细信息
     * @param staffId 员工用户Id
     * @return UserPropertyStaffEntity
     */
    UserPropertyStaffEntity getStaffUserByStaffId(String staffId);

    /**
     * 通过员工用户Id检索员工详细信息
     * @param staffId 员工用户Id
     * @return UserInformationEntity
     */
    UserInformationEntity getStaffByStaffId(String staffId);

    /**
     * 保存或更新Entity
     */
    <T> void saveOrUpdate(T entity);

    /**
     * 通过员工用户Id删除员工角色关系
     * @param staffId 员工用户Id
     */
    void deleteRoleanthorityByStaffId(String staffId);

    /**
     * 通过员工用户Id获取范围权限
     * @param staffId
     * @return
     */
    List<Map<String,Object>> getRoleScopeByStaffId(String staffId);

    /**
     * 获取城市列表(ALL)
     * @return  List<Map<String,Object>>
     */
    List<Map<String,Object>> listCity();

    /**
     * 通过项目Id检索城市信息
     * @param projectId 项目Id
     * @return  Map<String,Object>
     */
    Map<String,Object> getCityByProjectId(String projectId);

    /**
     * 通过城市Id检索项目列表
     * @param cityId
     * @return
     */
    List<Object[]> listProjectByCity(String cityId);

    /**
     * 检索所有城市所有项目
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> listAllProject();

    /**
     * 通过城市Id检索城市信息
     * @param cityId    城市Id
     * @return  HouseCityEntity
     */
    Map<String,Object> getHouseCityByCityId(String cityId);

    /**
     * 通过项目Code检索项目信息
     * @param projectCode   项目Code
     * @return  HouseProjectEntity
     */
    Map<String,Object> getHouseProjectByCode(String projectCode);

    /**
     * 获取批量员工用户信息列表
     * @param params 参数
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getBatchStaffUserList(Map<String,Object> params);


    /*********************新权限************************************/
    /**
     * 判断当前登录是否存在
     * */
    UserInformationEntity CheckUserStaffByIdAPwd(UserInformationEntity userInformationEntity);

    /**
     * 判断当前登录人是否启用
     * */
    boolean getuserMapById(String staffId,String checkLogin);
}
