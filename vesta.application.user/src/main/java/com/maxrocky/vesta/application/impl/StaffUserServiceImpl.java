package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.SMSAlertsDTO;
import com.maxrocky.vesta.application.DTO.admin.UserPropertystaffDTO;
import com.maxrocky.vesta.application.inf.StaffUserService;
import com.maxrocky.vesta.domain.model.MemberRoleMenuEntity;
import com.maxrocky.vesta.domain.model.RoleRoleanthorityEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.MemberAuthorityRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.PasswordEncode;
import com.mysql.jdbc.StringUtils;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * 会员账户管理_Service实现类
 * Created by WeiYangDong on 2016/8/11.
 */
@Service
public class StaffUserServiceImpl implements StaffUserService{

    @Autowired
    private UserPropertyStaffRepository userPropertyStaffRepository;
    @Autowired
    private MemberAuthorityRepository memberAuthorityRepository;


    /**
     * 获取账号信息列表
     */
    public List<Map<String,Object>> getStaffUserList(UserPropertystaffDTO userPropertystaffDTO,WebPage webPage){
        //检索条件
        Map<String,Object> params = new HashMap<>();
        params.put("userName",userPropertystaffDTO.getUserNameDto());//账号名
        params.put("mobile",userPropertystaffDTO.getMobileDto());//手机号
        params.put("company",userPropertystaffDTO.getCompany());//公司名
        params.put("beginTime",userPropertystaffDTO.getBeginTimeDto());//查询开始时间
        params.put("endTime",userPropertystaffDTO.getEndTimeDto());//查询结束时间
        if (null != userPropertystaffDTO.getScopeId() && !"".equals(userPropertystaffDTO.getScopeId()) && !"0".equals(userPropertystaffDTO.getScopeId())){
            //通过城市Id检索城市名称
            Map<String, Object> map = userPropertyStaffRepository.getHouseCityByCityId(userPropertystaffDTO.getScopeId());
            params.put("scope",map.get("cityName"));//区域
        }
        if (null != userPropertystaffDTO.getProjectCode() && !"".equals(userPropertystaffDTO.getProjectCode()) && !"0".equals(userPropertystaffDTO.getProjectCode())){
            //通过项目Code检索项目名称
            Map<String, Object> map = userPropertyStaffRepository.getHouseProjectByCode(userPropertystaffDTO.getProjectCode());
            params.put("projectName",map.get("NAME"));//项目
        }
        params.put("roledesc",userPropertystaffDTO.getRoledesc());//角色
        params.put("roleSetId",userPropertystaffDTO.getRoleSetId());//角色Id(如果不为NULL,查询需排除该角色Id)
        params.put("staffName",userPropertystaffDTO.getStaffNameDto());//姓名
        params.put("batchRoleState",userPropertystaffDTO.getBatchRoleState());  ////批量设置角色全选跳转
        //执行查询
        List<Map<String, Object>> staffUserList = userPropertyStaffRepository.getStaffUserList(params, webPage);
        //封装角色
        for(Map<String,Object> staffUser : staffUserList){
            List<Map<String, Object>> roleList = userPropertyStaffRepository.getRoleByStaffId(staffUser.get("staffId").toString());
            String roleDesc = "";
            for (Map<String,Object> role : roleList){
                roleDesc += role.get("roleDesc").toString()+",";
            }
            staffUser.put("roleDesc",roleDesc);
        }
        return staffUserList;
    }

    /**
     * 检索员工用户总人数
     */
    public Long getStaffUserCount(UserPropertystaffDTO userPropertystaffDTO){
        //检索条件
        Map<String,Object> params = new HashMap<>();
        params.put("userName",userPropertystaffDTO.getUserNameDto());//账号名
        params.put("mobile",userPropertystaffDTO.getMobileDto());//手机号
        params.put("company",userPropertystaffDTO.getCompany());//公司名
        params.put("beginTime",userPropertystaffDTO.getBeginTimeDto());//查询开始时间
        params.put("endTime",userPropertystaffDTO.getEndTimeDto());//查询结束时间
        if (null != userPropertystaffDTO.getScopeId() && !"".equals(userPropertystaffDTO.getScopeId()) && !"0".equals(userPropertystaffDTO.getScopeId())){
            //通过城市Id检索城市名称
            Map<String, Object> map = userPropertyStaffRepository.getHouseCityByCityId(userPropertystaffDTO.getScopeId());
            params.put("scope",map.get("cityName"));//区域
        }
        if (null != userPropertystaffDTO.getProjectCode() && !"".equals(userPropertystaffDTO.getProjectCode()) && !"0".equals(userPropertystaffDTO.getProjectCode())){
            //通过项目Code检索项目名称
            Map<String, Object> map = userPropertyStaffRepository.getHouseProjectByCode(userPropertystaffDTO.getProjectCode());
            params.put("projectName",map.get("NAME"));//项目
        }
//        params.put("scope", userPropertystaffDTO.getScope());//区域
//        params.put("projectName", userPropertystaffDTO.getProjectName());//项目
        params.put("roledesc", userPropertystaffDTO.getRoledesc());//角色
        params.put("roleSetId",userPropertystaffDTO.getRoleSetId());//角色Id(如果不为NULL,查询需排除该角色Id)
        //执行查询
        Long count = userPropertyStaffRepository.getStaffUserCount(params);
        return count;
    }

    /**
     * 重置员工用户密码(默认密码为123456)
     */
    public boolean resetPassWord(UserPropertystaffDTO userPropertystaffDTO){
        UserPropertyStaffEntity staffUserEntity = userPropertyStaffRepository.getStaffUserByStaffId(userPropertystaffDTO.getStaffIdDto());
        staffUserEntity.setPassword(PasswordEncode.digestPassword("123456"));
        staffUserEntity.setModifyBy(userPropertystaffDTO.getModifyByDto());
        staffUserEntity.setModifyOn(new Date());
        boolean flag = userPropertyStaffRepository.updateStaff(staffUserEntity);
        return flag;
    }

    /**
     * 更新员工用户密码(与重置密码方法可合并)
     */
    public boolean updatePassWord(UserPropertystaffDTO userPropertystaffDTO){
        UserPropertyStaffEntity staffUserEntity = userPropertyStaffRepository.getStaffUserByStaffId(userPropertystaffDTO.getStaffIdDto());
        staffUserEntity.setPassword(PasswordEncode.digestPassword(userPropertystaffDTO.getNewPassword_1()));
        staffUserEntity.setModifyBy(userPropertystaffDTO.getModifyByDto());
        staffUserEntity.setModifyOn(new Date());
        boolean flag = userPropertyStaffRepository.updateStaff(staffUserEntity);
        return flag;
    }

    /**
     * 逻辑删除员工信息
     */
    public boolean delStaffUser(UserPropertystaffDTO userPropertystaffDTO){
        //删除员工角色关系
        userPropertyStaffRepository.deleteRoleanthorityByStaffId(userPropertystaffDTO.getStaffIdDto());
        //逻辑删除员工(staffState状态置为0)
        UserPropertyStaffEntity staffUserEntity = userPropertyStaffRepository.getStaffUserByStaffId(userPropertystaffDTO.getStaffIdDto());
        staffUserEntity.setStaffState("0");
        staffUserEntity.setModifyBy(userPropertystaffDTO.getModifyByDto());
        staffUserEntity.setModifyOn(new Date());
        boolean flag = userPropertyStaffRepository.updateStaff(staffUserEntity);
        return flag;
    }

    /**
     * 通过员工用户Id获取员工用户信息
     */
    public UserPropertyStaffEntity getStaffUserById(String staffId){
        return userPropertyStaffRepository.get(staffId);
    }

    /**
     * 保存或更新员工用户信息
     */
    public UserPropertyStaffEntity saveOrUpdateStaffUser(UserPropertystaffDTO userPropertystaffDTO){
        UserPropertyStaffEntity userPropertyStaffEntity = null;
        if (null != userPropertystaffDTO.getStaffIdDto() && !"".equals(userPropertystaffDTO.getStaffIdDto())){
            //执行更新
            userPropertyStaffEntity = getStaffUserById(userPropertystaffDTO.getStaffIdDto());
            userPropertyStaffEntity.setUserName(userPropertystaffDTO.getUserNameDto());     //用户名
//            userPropertyStaffEntity.setPassword(PasswordEncode.digestPassword(userPropertystaffDTO.getPasswordDto()));      //密码
            userPropertyStaffEntity.setStaffName(userPropertystaffDTO.getStaffNameDto());       //姓名
            userPropertyStaffEntity.setStaffState(userPropertyStaffEntity.State_On);    //1-有效,0-无效
            userPropertyStaffEntity.setMobile(userPropertystaffDTO.getMobileDto());     //联系方式(手机)
            userPropertyStaffEntity.setEmail(userPropertystaffDTO.getEmail());      //邮箱
            userPropertyStaffEntity.setScope(userPropertystaffDTO.getScope());      //区域
            userPropertyStaffEntity.setProject(userPropertystaffDTO.getProjectName());      //项目
            userPropertyStaffEntity.setCompany(userPropertystaffDTO.getCompany());      //公司

            userPropertyStaffEntity.setModifyOn(new Date());    //修改时间
            userPropertyStaffEntity.setModifyBy(userPropertystaffDTO.getModifyByDto());     //操作人
            userPropertyStaffRepository.updateStaff(userPropertyStaffEntity);
        }else{
            //执行新增
            userPropertyStaffEntity = new UserPropertyStaffEntity();
            userPropertyStaffEntity.setStaffId(IdGen.uuid());       //员工Id(主键)
            userPropertyStaffEntity.setUserName(userPropertystaffDTO.getUserNameDto());     //用户名
            if (null != userPropertystaffDTO.getPasswordDto() && !"".equals(userPropertystaffDTO.getPasswordDto())){
                userPropertyStaffEntity.setPassword(PasswordEncode.digestPassword(userPropertystaffDTO.getPasswordDto()));      //密码
            }else{
                userPropertyStaffEntity.setPassword(PasswordEncode.digestPassword("123456"));      //密码
            }
            userPropertyStaffEntity.setStaffName(userPropertystaffDTO.getStaffNameDto());       //姓名
            userPropertyStaffEntity.setStaffState(userPropertyStaffEntity.State_On);    //1-有效,0-无效
            userPropertyStaffEntity.setType("OFF");     //IN-引入,OFF-自建
            userPropertyStaffEntity.setMobile(userPropertystaffDTO.getMobileDto());     //联系方式(手机)
            userPropertyStaffEntity.setEmail(userPropertystaffDTO.getEmail());      //邮箱
            userPropertyStaffEntity.setScope(userPropertystaffDTO.getScope());      //区域
            userPropertyStaffEntity.setProject(userPropertystaffDTO.getProjectName());      //项目
            userPropertyStaffEntity.setCompany(userPropertystaffDTO.getCompany());      //公司

            userPropertyStaffEntity.setCreateOn(new Date());    //创建时间
            userPropertyStaffEntity.setCreateBy(userPropertystaffDTO.getModifyByDto());     //操作人
            userPropertyStaffEntity.setModifyOn(new Date());    //修改时间
            userPropertyStaffEntity.setModifyBy(userPropertystaffDTO.getModifyByDto());     //操作人
            userPropertyStaffRepository.addUserPropertyStaff(userPropertyStaffEntity);
        }
        return userPropertyStaffEntity;
    }

    /**
     * 保存或更新员工角色权限关系
     */
    public void saveOrUpdateRoleanthority(UserPropertystaffDTO userPropertystaffDTO){
        //删除员工角色关系
        userPropertyStaffRepository.deleteRoleanthorityByStaffId(userPropertystaffDTO.getStaffIdDto());
        //保存员工角色关系
        List<String> roledescList = userPropertystaffDTO.getRoledescList();
        if (null != roledescList){
            for (String setId : roledescList){
                RoleRoleanthorityEntity roleAnthorityEntity = new RoleRoleanthorityEntity();
                roleAnthorityEntity.setUserId(IdGen.uuid());        //主键
                roleAnthorityEntity.setStaffId(userPropertystaffDTO.getStaffIdDto());       //员工用户Id
                roleAnthorityEntity.setSetId(setId);       //角色Id
                userPropertyStaffRepository.saveOrUpdate(roleAnthorityEntity);
            }
        }
    }

    /**
     * 通过员工用户Id获取角色信息
     */
    public List<Map<String,Object>> getRoleByStaffId(String staffId){
        return userPropertyStaffRepository.getRoleByStaffId(staffId);
    }

    /**
     * 通过员工用户Id获取范围权限
     */
    public List<Map<String,Object>> getRoleScopeByStaffId(String staffId){
        return userPropertyStaffRepository.getRoleScopeByStaffId(staffId);
    }

    /**
     * 通过员工用户Id检索城市列表(角色范围所对应的城市)
     */
    public List<Map<String,Object>> getCityListByStaff(String staffId){
        //城市列表
        List<Map<String,Object>> cityList = new ArrayList<>();
        //通过员工用户Id获取范围权限
        List<Map<String, Object>> roleScopeList = getRoleScopeByStaffId(staffId);
        if (roleScopeList.size() > 0){
            for (Map<String,Object> roleScope : roleScopeList){
                //范围类型
                String scopeType = roleScope.get("scopeType").toString();
                if (scopeType.equals("1")){
                    //城市级别
                    Map<String,Object> city = new HashMap<>();
                    city.put("cityId",roleScope.get("scopeId"));
                    city.put("cityName",roleScope.get("scopeName"));
                    cityList.add(city);
                }else if (scopeType.equals("2")){
                    //项目级别
                    String projectId = roleScope.get("scopeId").toString();
                    Map<String, Object> city = userPropertyStaffRepository.getCityByProjectId(projectId);
                    cityList.add(city);
                }else if (scopeType.equals("0")){
                    //全部城市
                    cityList.clear();
                    cityList = userPropertyStaffRepository.listCity();
                    Map<String,Object> allCity = new HashMap<>();
                    allCity.put("cityId","0");
                    allCity.put("cityName","全部城市");
                    cityList.add(0,allCity);
                    return cityList;
                }
            }
        }
        //去重
        List<Map<String,Object>> resultList = new ArrayList<>();
        if (cityList.size() > 0){
            for (Map<String,Object> city : cityList){
                if (resultList.size() > 0){
                    int flag = 0;
                    for (Map<String,Object> map : resultList){
                        if (map.get("cityId").toString().equals(city.get("cityId").toString())){
                            flag = 1;
                        }
                    }
                    if (flag == 0){
                        resultList.add(city);
                    }
                }else{
                    resultList.add(city);
                }
            }
        }
        return resultList;
    }

    /**
     * 通过员工用户Id检索城市列表(角色范围所对应的城市)
     * 备注:解决角色交叉授权_2016-09-05_Wyd
     */
    public List<Map<String,Object>> getCityListByStaff(String staffId,String menuId){
        //城市列表
        List<Map<String,Object>> cityList = new ArrayList<>();
        //通过员工用户Id获取范围权限
        List<Map<String, Object>> roleScopeList = getRoleScopeByStaffId(staffId);
        if (roleScopeList.size() > 0){
            for (Map<String,Object> roleScope : roleScopeList){
                //用户角色Id
                String setId = roleScope.get("setId").toString();
                Map<String,Object> params = new HashMap<>();
                params.put("setId",setId);
                params.put("menuId",menuId);
                //通过角色Id和菜单Id确定操作级别
                MemberRoleMenuEntity memberRoleMenuEntity = memberAuthorityRepository.getRoleMenuByRoleAndMenu(params);
                //该用户角色是否在该菜单下拥有全部操作权限
                if(null != memberRoleMenuEntity && "1".equals(memberRoleMenuEntity.getOperationLevel())){
                    //范围类型
                    String scopeType = roleScope.get("scopeType").toString();
                    if (scopeType.equals("1")){
                        //城市级别
                        Map<String,Object> city = new HashMap<>();
                        city.put("cityId",roleScope.get("scopeId"));
                        city.put("cityName",roleScope.get("scopeName"));
                        cityList.add(city);
                    }else if (scopeType.equals("2")){
                        //项目级别
                        String projectId = roleScope.get("scopeId").toString();
                        Map<String, Object> city = userPropertyStaffRepository.getCityByProjectId(projectId);
                        cityList.add(city);
                    }else if (scopeType.equals("0")){
                        //全部城市
                        cityList.clear();
                        cityList = userPropertyStaffRepository.listCity();
                        Map<String,Object> allCity = new HashMap<>();
                        allCity.put("cityId","0");
                        allCity.put("cityName","全部城市");
                        cityList.add(0,allCity);
                        return cityList;
                    }
                }
            }
        }
        //去重
        List<Map<String,Object>> resultList = new ArrayList<>();
        if (cityList.size() > 0){
            for (Map<String,Object> city : cityList){
                if (resultList.size() > 0){
                    int flag = 0;
                    for (Map<String,Object> map : resultList){
                        if (map.get("cityId").toString().equals(city.get("cityId").toString())){
                            flag = 1;
                        }
                    }
                    if (flag == 0){
                        resultList.add(city);
                    }
                }else{
                    resultList.add(city);
                }
            }
        }
        return resultList;
    }

    /**
     * 通过员工Id和城市Id检索项目列表(角色范围所对应的项目)
     */
    public List<Object[]> listProjectByCity(String cityId){
        //城市下项目列表
        List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(cityId);
        Object[] allPro = new Object[2];
        allPro[0] = "0";
        allPro[1] = "全部项目";
        projectList.add(0,allPro);
        return projectList;
    }

    /**
     * 通过员工Id和城市Id检索项目列表(角色范围所对应的项目)
     */
    public List<Object[]> listProjectByCity(String staffId,String cityId){
        //结果集
        List<Object[]> list = new ArrayList<>();
        //城市下项目列表
        List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(cityId);
        //通过员工用户Id获取范围权限
        List<Map<String, Object>> roleScopeList = getRoleScopeByStaffId(staffId);
        //1.判定城市范围
        if (roleScopeList.size() > 0){
            for (Map<String,Object> roleScope : roleScopeList){
                //范围类型
                String scopeType = roleScope.get("scopeType").toString();
                if (scopeType.equals("1") && roleScope.get("scopeId").toString().equals(cityId)){
                    //城市级别
                    Object[] allPro = new Object[2];
                    allPro[0] = "0";
                    allPro[1] = "全部项目";
                    projectList.add(0,allPro);
                    return projectList;
                }else if (scopeType.equals("2")){
                    //项目级别
                    String scopeId = roleScope.get("scopeId").toString();
                    for (Object[] object : projectList){
                        if (scopeId.equals(object[0])){
                            list.add(object);
                        }
                    }
                }else if (scopeType.equals("0")){
                    //全部城市
                    Object[] allPro = new Object[2];
                    allPro[0] = "0";
                    allPro[1] = "全部项目";
                    projectList.add(0,allPro);
                    return projectList;
                }
            }
        }
        return list;
    }

    /**
     * 通过员工Id和城市Id检索项目列表(角色范围所对应的项目)
     * 备注:解决角色交叉授权_2016-09-05_Wyd
     */
    public List<Object[]> listProjectByCity(String staffId,String cityId,String menuId){
        //结果集
        List<Object[]> list = new ArrayList<>();
        //城市下项目列表
        List<Object[]> projectList = userPropertyStaffRepository.listProjectByCity(cityId);
        //通过员工用户Id获取范围权限
        List<Map<String, Object>> roleScopeList = getRoleScopeByStaffId(staffId);
        //1.判定城市范围
        if (roleScopeList.size() > 0){
            for (Map<String,Object> roleScope : roleScopeList){
                //用户角色Id
                String setId = roleScope.get("setId").toString();
                Map<String,Object> params = new HashMap<>();
                params.put("setId",setId);
                params.put("menuId",menuId);
                //通过角色Id和菜单Id确定操作级别
                MemberRoleMenuEntity memberRoleMenuEntity = memberAuthorityRepository.getRoleMenuByRoleAndMenu(params);
                //该用户角色是否在该菜单下拥有全部操作权限
                if(null != memberRoleMenuEntity && "1".equals(memberRoleMenuEntity.getOperationLevel())){
                    //范围类型
                    String scopeType = roleScope.get("scopeType").toString();
                    if (scopeType.equals("1") && roleScope.get("scopeId").toString().equals(cityId)){
                        //城市级别
                        Object[] allPro = new Object[2];
                        allPro[0] = "0";
                        allPro[1] = "全部项目";
                        projectList.add(0,allPro);
                        return projectList;
                    }else if (scopeType.equals("2")){
                        //项目级别
                        String scopeId = roleScope.get("scopeId").toString();
                        for (Object[] object : projectList){
                            if (scopeId.equals(object[0])){
                                list.add(object);
                            }
                        }
                    }else if (scopeType.equals("0")){
                        //全部城市
                        Object[] allPro = new Object[2];
                        allPro[0] = "0";
                        allPro[1] = "全部项目";
                        projectList.add(0,allPro);
                        return projectList;
                    }
                }
            }
        }
        return list;
    }

    /**
     * 通过员工用户Id获取权限范围项目列表(用于项目权限控制)
     */
    public List<Map<String,Object>> getProjectScopeByStaffId(String staffId){
        List<Map<String,Object>> projectList = new ArrayList<>();
        List<Map<String, Object>> roleScopeList = getRoleScopeByStaffId(staffId);
        if (roleScopeList.size() > 0){
            for (Map<String,Object> roleScope : roleScopeList){
                //范围类型
                String scopeType = roleScope.get("scopeType").toString();
                if (scopeType.equals("1")){
                    //城市级别
                    List<Object[]> list = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                    if (list.size() > 0){
                        for (Object[] object : list){
                            Map<String,Object> map = new HashMap<>();
                            map.put("projectId",object[0].toString());
                            map.put("projectName",object[1].toString());
                            projectList.add(map);
                        }
                    }
                }else if (scopeType.equals("2")){
                    //项目级别
                    Map<String,Object> map = new HashMap<>();
                    map.put("projectId", roleScope.get("scopeId").toString());
                    map.put("projectName", roleScope.get("scopeName").toString());
                    projectList.add(map);
                }else if (scopeType.equals("0")){
                    //全部城市
                    projectList.clear();
                    projectList = userPropertyStaffRepository.listAllProject();
                    return projectList;
                }
            }
        }
        //去重
        List<Map<String,Object>> resultList = new ArrayList<>();
        if (projectList.size() > 0){
            for (Map<String,Object> project : projectList){
                if (resultList.size() > 0){
                    int flag = 0;
                    for (Map<String,Object> map : resultList){
                        if (map.get("projectId").toString().equals(project.get("projectId").toString())){
                            flag = 1;
                        }
                    }
                    if (flag == 0){
                        resultList.add(project);
                    }
                }else{
                    resultList.add(project);
                }
            }
        }
        return resultList;
    }

    /**
     * 通过员工用户Id和菜单Id获取权限范围项目列表(用于项目权限控制)
     * 备注:解决角色交叉授权_2016-09-05_Wyd
     */
    public List<Map<String,Object>> getProjectScopeByStaffId(String staffId,String menuId){
        List<Map<String,Object>> projectList = new ArrayList<>();
        List<Map<String, Object>> roleScopeList = getRoleScopeByStaffId(staffId);
        if (roleScopeList.size() > 0){
            for (Map<String,Object> roleScope : roleScopeList){
                //用户角色Id
                String setId = roleScope.get("setId").toString();
                Map<String,Object> params = new HashMap<>();
                params.put("setId",setId);
                params.put("menuId",menuId);
                //通过角色Id和菜单Id确定操作级别
                MemberRoleMenuEntity memberRoleMenuEntity = memberAuthorityRepository.getRoleMenuByRoleAndMenu(params);
                //该用户角色是否在该菜单下拥有全部操作权限
                if(null != memberRoleMenuEntity && "1".equals(memberRoleMenuEntity.getOperationLevel())){
                    //该用户角色对该菜单拥有最高权限
                    //执行项目列表查询
                    //范围类型
                    String scopeType = roleScope.get("scopeType").toString();
                    if (scopeType.equals("1")){
                        //城市级别
                        List<Object[]> list = userPropertyStaffRepository.listProjectByCity(roleScope.get("scopeId").toString());
                        if (list.size() > 0){
                            for (Object[] object : list){
                                Map<String,Object> map = new HashMap<>();
                                map.put("projectId",object[0].toString());
                                map.put("projectName",object[1].toString());
                                projectList.add(map);
                            }
                        }
                    }else if (scopeType.equals("2")){
                        //项目级别
                        Map<String,Object> map = new HashMap<>();
                        map.put("projectId", roleScope.get("scopeId").toString());
                        map.put("projectName", roleScope.get("scopeName").toString());
                        projectList.add(map);
                    }else if (scopeType.equals("0")){
                        //全部城市
                        projectList.clear();
                        projectList = userPropertyStaffRepository.listAllProject();
                        return projectList;
                    }
                }else{
                    //该用户角色对该菜单没有最高权限
                    //不执行项目列表查询操作
                }
            }
        }
        //去重
        List<Map<String,Object>> resultList = new ArrayList<>();
        if (projectList.size() > 0){
            for (Map<String,Object> project : projectList){
                if (resultList.size() > 0){
                    int flag = 0;
                    for (Map<String,Object> map : resultList){
                        if (map.get("projectId").toString().equals(project.get("projectId").toString())){
                            flag = 1;
                        }
                    }
                    if (flag == 0){
                        resultList.add(project);
                    }
                }else{
                    resultList.add(project);
                }
            }
        }
        return resultList;
    }

    /**
     * 获取城市列表(ALL)
     * @return  List<Map<String,Object>>
     */
    public List<Map<String,Object>> listCity(){
        return userPropertyStaffRepository.listCity();
    }

    /**
     * 获取批量账号信息列表
     */
    public List<Map<String,Object>> getBatchStaffUser(UserPropertystaffDTO userPropertystaffDTO){
        Map<String,Object> params = new HashMap<>();
        List<String> staffUserIdList = userPropertystaffDTO.getStaffUserIdList();
        String staffUserIds = "(";
        if (null != staffUserIdList){
            for (int i = 0; i < staffUserIdList.size(); i++){
                if (i == staffUserIdList.size()-1){
                    staffUserIds += "'"+staffUserIdList.get(i)+"'";
                }else{
                    staffUserIds += "'"+staffUserIdList.get(i)+"',";
                }
            }
        }
        staffUserIds += ")";
        params.put("staffUserIds", staffUserIds);
        List<Map<String, Object>> staffUserList = userPropertyStaffRepository.getBatchStaffUserList(params);
        //封装角色
        for(Map<String,Object> staffUser : staffUserList){
            List<Map<String, Object>> roleList = userPropertyStaffRepository.getRoleByStaffId(staffUser.get("staffId").toString());
            String roleDesc = "";
            for (Map<String,Object> role : roleList){
                roleDesc += role.get("roleDesc").toString()+",";
            }
            staffUser.put("roleDesc",roleDesc);
        }
        return staffUserList;
    }

    /**
     * 检索所有城市所有项目
     * @return List<Map<String,Object>>
     */
    public List<Map<String,Object>> listAllProject(){
        return userPropertyStaffRepository.listAllProject();
    }

    /**
     * 批量用户设置角色
     */
    public void batchSetRole(UserPropertystaffDTO userPropertystaffDTO){
        List<String> staffUserIdList = userPropertystaffDTO.getStaffUserIdList();
        if(null != staffUserIdList && staffUserIdList.size() > 0){
            for (String staffUserId : staffUserIdList){
                //检索员工已拥有的角色Id
                List<Map<String, Object>> mapList = userPropertyStaffRepository.getRoleByStaffId(staffUserId);
                List<String> userRoleIdList = new ArrayList<>();
                for (Map<String,Object> map : mapList){
                    String setId = map.get("setId").toString();
                    userRoleIdList.add(setId);
                }
                //保存员工角色关系
                List<String> roledescList = userPropertystaffDTO.getRoledescList();
                if (null != roledescList){
                    for (String setId : roledescList){
                        if (!userRoleIdList.contains(setId)){
                            RoleRoleanthorityEntity roleAnthorityEntity = new RoleRoleanthorityEntity();
                            roleAnthorityEntity.setUserId(IdGen.uuid());        //主键
                            roleAnthorityEntity.setStaffId(staffUserId);       //员工用户Id
                            roleAnthorityEntity.setSetId(setId);       //角色Id
                            userPropertyStaffRepository.saveOrUpdate(roleAnthorityEntity);
                        }
                    }
                }
            }
        }
    }

    /**.
     *  @Author: shanshan
     *  @Date:
     *  @Param:
     *  @Description: 导出excel
     */
    @Override
    public String exportExcel(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, List<Map<String, Object>> staffUserList,  WebPage webPage) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        List<UserPropertystaffDTO> list = new ArrayList<>();
        for (int i=0; i<staffUserList.size(); i++) {
            Map<String, Object> staffUser = staffUserList.get(i);
            UserPropertystaffDTO userPropertystaffDTO = new UserPropertystaffDTO();
            if (staffUser.get("staffName") != null) {
                userPropertystaffDTO.setStaffNameDto(staffUser.get("staffName").toString());
            }
            if (staffUser.get("userName") != null) {
                userPropertystaffDTO.setUserNameDto(staffUser.get("userName").toString());
            }
            if (staffUser.get("mobile") != null) {
                userPropertystaffDTO.setMobileDto(staffUser.get("mobile").toString());
            }
            if (staffUser.get("company") != null) {
                userPropertystaffDTO.setCompany(staffUser.get("company").toString());
            }
            if (staffUser.get("scope") != null) {
                userPropertystaffDTO.setScope(staffUser.get("scope").toString());
            }
            if (staffUser.get("project") != null) {
                userPropertystaffDTO.setProjectName(staffUser.get("project").toString());
            }
            if (staffUser.get("roleDesc") != null) {
                userPropertystaffDTO.setRoledesc(staffUser.get("roleDesc").toString());
            }
            list.add(userPropertystaffDTO);
        }

        //创建Sheet页
        XSSFSheet sheet = workBook.createSheet("账号列表");

        ExportUtil exportUtil = new ExportUtil(workBook, sheet);
        XSSFCellStyle headStyle = exportUtil.getHeadStyle();
        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();

        // 百分比
        XSSFDataFormat fmt = workBook.createDataFormat();
        XSSFDataFormat fmt1 = workBook.createDataFormat();

        // 四个边框加粗
        XSSFCellStyle style1 = workBook.createCellStyle();
        style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
        style1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
        style1.setBorderRight(XSSFCellStyle.BORDER_THIN);
        style1.setBorderTop(XSSFCellStyle.BORDER_THIN);
        XSSFFont font = workBook.createFont();

        // 设置字体加粗
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        font.setFontHeight((short) 200);
        style1.setFont(font);

        String[] titles = {"序号", "姓名", "账号名","联系方式","公司","区域","项目","角色"};

        XSSFRow headRow = sheet.createRow(0);

        if(list != null && list.size() > 0){
            list.forEach(a -> {
                XSSFCell cell = null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);

                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }
                sheet.setColumnWidth(0, 1000);
                sheet.setColumnWidth(1, 2500);
                sheet.setColumnWidth(2, 3000);
                sheet.setColumnWidth(3, 3500);
                sheet.setColumnWidth(4, 5000);
                sheet.setColumnWidth(5, 3000);
                sheet.setColumnWidth(6, 3000);
                sheet.setColumnWidth(7, 5000);
                for (int i = 0; i < list.size(); i++) {
                    XSSFRow bodyRow = sheet.createRow(i + 1);
                    UserPropertystaffDTO userPropertystaffDTO = list.get(i);

                    cell = bodyRow.createCell(0);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(i + 1);//序号


                    cell = bodyRow.createCell(1);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(userPropertystaffDTO.getStaffNameDto());//姓名

                    cell = bodyRow.createCell(2);
                    cell.setCellStyle(bodyStyle);// 表格黑色边框
                    cell.setCellValue(userPropertystaffDTO.getUserNameDto());//账号名

                    cell = bodyRow.createCell(3);
                    cell.setCellStyle(bodyStyle);//
                    cell.setCellValue(userPropertystaffDTO.getMobileDto());//联系方式

                    cell = bodyRow.createCell(4);
                    cell.setCellStyle(bodyStyle);//
                    cell.setCellValue(userPropertystaffDTO.getCompany());//公司

                    cell = bodyRow.createCell(5);
                    cell.setCellStyle(bodyStyle);//
                    cell.setCellValue(userPropertystaffDTO.getScope());//区域

                    cell = bodyRow.createCell(6);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(userPropertystaffDTO.getProjectName());//项目

                    cell = bodyRow.createCell(7);
                    cell.setCellStyle(bodyStyle);
                    cell.setCellValue(userPropertystaffDTO.getRoledesc());//角色
                }
            });
            try {
                //String fileName = new String(("报修单").getBytes(), "ISO8859-1");
                String fileName = new String(("账号列表").getBytes(), "ISO8859-1");
                String agent = httpServletRequest.getHeader("USER-AGENT");
                if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                        && -1 != agent.indexOf("Trident")) {// ie

                    fileName = java.net.URLEncoder.encode("账号列表", "UTF8");
                }
                httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
                workBook.write(outputStream);
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            outputStream.flush();
            outputStream.close();
        }
        return "";
    }
}
