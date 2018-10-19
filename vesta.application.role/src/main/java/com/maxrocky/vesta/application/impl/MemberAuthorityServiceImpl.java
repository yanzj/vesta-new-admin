package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.dto.adminDTO.RoleRolesetDTO;
import com.maxrocky.vesta.application.dto.adminDTO.RoleStaffUserDTO;
import com.maxrocky.vesta.application.inf.MemberAuthorityService;
import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.domain.repository.MemberAuthorityRepository;
import com.maxrocky.vesta.domain.repository.RoleRolesetmapRepository;
import com.maxrocky.vesta.domain.repository.UserPropertyStaffRepository;
import com.maxrocky.vesta.taglib.page.WebPage;
import com.maxrocky.vesta.utility.DateUtils;
import com.maxrocky.vesta.utility.ExportUtil;
import com.maxrocky.vesta.utility.IdGen;
import com.maxrocky.vesta.utility.SqlDateUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * 会员权限管理_Service实现类
 * Created by WeiYangDong on 2016/8/4.
 */
@Service
public class MemberAuthorityServiceImpl implements MemberAuthorityService{

    @Autowired
    private MemberAuthorityRepository memberAuthorityRepository;

    @Autowired
    private RoleRolesetmapRepository roleRolesetmapRepository;

    @Autowired
    private UserPropertyStaffRepository userPropertyStaffRepository;

    /**
     * 获取会员角色列表信息
     */
    public List<Map<String,Object>> getMemberRolesetList(RoleRolesetDTO roleRolesetDTO,WebPage webPage){
        //检索条件
        Map<String,Object> params = new HashMap<>();
        params.put("roledesc",roleRolesetDTO.getRoledesc());        //角色描述(名称)
        params.put("remarks",roleRolesetDTO.getRemarks());          //角色备注

        if (null != roleRolesetDTO.getProjectCode() && !"".equals(roleRolesetDTO.getProjectCode()) && !"0".equals(roleRolesetDTO.getProjectCode())){
            //通过城市Id检索城市名称
            Map<String, Object> map = userPropertyStaffRepository.getHouseProjectByCode(roleRolesetDTO.getProjectCode());
            params.put("projectScope",map.get("NAME"));//区域

        }
//        params.put("projectScope",roleRolesetDTO.getProjectScope());//数据查看范围
        params.put("staDate", roleRolesetDTO.getStaDate());         //创建时间查询_开始日期
        params.put("endDate", roleRolesetDTO.getEndDate());         //创建时间查询_结束日期

        //执行查询(此处webPage无法满足分页,将在Controller层手动分页)
        List<Map<String, Object>> rolesetList = memberAuthorityRepository.getMemberRolesetList(params, webPage);
        //返回结果集
        List<Map<String,Object>> resultList = new ArrayList<>();

        for (int i=0; i<rolesetList.size(); i++){
            Map<String, Object> roleSet = rolesetList.get(i);
            //数据查询范围处理
            String scopeName = "";
            String setId = (String) roleSet.get("setId");
            for (int j=0; j<rolesetList.size(); j++){
                Map<String, Object> map = rolesetList.get(j);
                if (setId.equals(map.get("setId").toString())){
                    scopeName += map.get("scopeName") + ",";
                }
            }
            roleSet.put("scopeName",scopeName);
            //去重
            int f = 0;//0,结果集中未出现数据,1,结果集中已经存在数据
            for (int z=0; z<resultList.size(); z++){
                Map<String, Object> map = resultList.get(z);
                if (setId.equals(map.get("setId").toString())){
                    f = 1;
                }
            }
            if (f == 0) {
                resultList.add(roleSet);
            }
        }
        return resultList;
    }

    /**
     * 保存或更新角色信息
     */
    public RoleRolesetEntity saveOrUpdateRole(RoleRolesetDTO roleRolesetDTO){
        RoleRolesetEntity roleRolesetEntity = null;
        if (roleRolesetDTO.getSetId() != null && !"".equals(roleRolesetDTO.getSetId())){
            //执行更新,检索该角色信息
            roleRolesetEntity = memberAuthorityRepository.getRoleById(roleRolesetDTO.getSetId());
            roleRolesetEntity.setRoledesc(roleRolesetDTO.getRoledesc());//角色名称(描述)
            roleRolesetEntity.setRemarks(roleRolesetDTO.getRemarks());//角色备注
            roleRolesetEntity.setModifyDate(SqlDateUtils.getDate());
            roleRolesetEntity.setModifyTime(SqlDateUtils.getTime());
            roleRolesetEntity.setOperator(roleRolesetDTO.getOperator());//角色修改人
            memberAuthorityRepository.saveOrUpdate(roleRolesetEntity);
        }else{
            //执行新增
            roleRolesetEntity = new RoleRolesetEntity();
            roleRolesetEntity.setSetId(IdGen.uuid());
            roleRolesetEntity.setRoledesc(roleRolesetDTO.getRoledesc());//角色名称(描述)
            roleRolesetEntity.setRemarks(roleRolesetDTO.getRemarks());//角色备注
            roleRolesetEntity.setMakeDate(SqlDateUtils.getDate());
            roleRolesetEntity.setMakeTime(SqlDateUtils.getTime());
            roleRolesetEntity.setModifyDate(SqlDateUtils.getDate());
            roleRolesetEntity.setModifyTime(SqlDateUtils.getTime());
            roleRolesetEntity.setOperator(roleRolesetDTO.getOperator());//角色修改人
            roleRolesetEntity.setSetState("0");//是否有效(0:无效)
            roleRolesetEntity.setSetType("1");//角色类型(1:金茂会员)
            roleRolesetEntity.setCompanyId(null);//公司Id(默认Null)
            roleRolesetEntity.setIsallot("1");//是否允许被分配(未实用该字段,默认1:允许)
            memberAuthorityRepository.saveOrUpdate(roleRolesetEntity);
        }
        return roleRolesetEntity;
    }

    /**
     * 保存或更新角色操作数据范围信息
     */
    public void saveOrUpdateRoleScope(RoleRolesetDTO roleRolesetDTO){
        //删除该角色操作数据范围
        memberAuthorityRepository.deleteRoleScopeBySetId(roleRolesetDTO.getSetId());
        //新增该角色操作数据范围
        List<String> cityList = Arrays.asList(roleRolesetDTO.getCityList().split(","));
        List<String> cityIds = Arrays.asList(roleRolesetDTO.getCityIds().split(","));
        List<String> projectList = Arrays.asList(roleRolesetDTO.getProjectList().split(","));
        List<String> projectIds = Arrays.asList(roleRolesetDTO.getProjectIds().split(","));

        if (cityIds.size() == 1 && cityIds.get(0).equals("0")){
            //新增-->全部城市级别
            MemberRoleScopeEntity memberRoleScopeEntity = new MemberRoleScopeEntity();
            memberRoleScopeEntity.setId(IdGen.uuid());
            memberRoleScopeEntity.setRoleSetId(roleRolesetDTO.getSetId());//角色Id
            memberRoleScopeEntity.setScopeType("0");//权限范围类型(0:全部城市级别,1:城市级别,2:项目级别)
            memberRoleScopeEntity.setScopeId("0");//权限范围Id(0/城市Id/项目Code)
            memberRoleScopeEntity.setScopeName("全部城市");//权限范围名称(全部城市/北京/亚奥金茂悦)
            memberRoleScopeEntity.setNowState("0");//当前状态(0:不启用,1:启用)
            memberRoleScopeEntity.setCreateBy(roleRolesetDTO.getOperator());
            memberRoleScopeEntity.setCreateOn(new Date());
            memberRoleScopeEntity.setModifyBy(roleRolesetDTO.getOperator());
            memberRoleScopeEntity.setModifyOn(new Date());
            memberAuthorityRepository.saveOrUpdate(memberRoleScopeEntity);
        }else if (cityIds.size() > 0 && projectIds.size() == 1 && projectIds.get(0).equals("0")){
            //新增-->城市范围级别
            for (int i = 0; i < cityIds.size(); i++){
                MemberRoleScopeEntity memberRoleScopeEntity = new MemberRoleScopeEntity();
                memberRoleScopeEntity.setId(IdGen.uuid());
                memberRoleScopeEntity.setRoleSetId(roleRolesetDTO.getSetId());//角色Id
                memberRoleScopeEntity.setScopeType("1");//权限范围类型(0:全部城市级别,1:城市级别,2:项目级别)
                memberRoleScopeEntity.setScopeId(cityIds.get(i));//权限范围Id(0/城市Id/项目Code)
                memberRoleScopeEntity.setScopeName(cityList.get(i));//权限范围名称(全部城市/北京/亚奥金茂悦)
                memberRoleScopeEntity.setNowState("0");//当前状态(0:不启用,1:启用)
                memberRoleScopeEntity.setCreateBy(roleRolesetDTO.getOperator());
                memberRoleScopeEntity.setCreateOn(new Date());
                memberRoleScopeEntity.setModifyBy(roleRolesetDTO.getOperator());
                memberRoleScopeEntity.setModifyOn(new Date());
                memberAuthorityRepository.saveOrUpdate(memberRoleScopeEntity);
            }
        }else if (cityIds.size() > 0 && projectIds.size() > 0){
            //新增-->项目范围级别
            for (int i = 0; i < projectIds.size(); i++){
                MemberRoleScopeEntity memberRoleScopeEntity = new MemberRoleScopeEntity();
                memberRoleScopeEntity.setId(IdGen.uuid());
                memberRoleScopeEntity.setRoleSetId(roleRolesetDTO.getSetId());//角色Id
                memberRoleScopeEntity.setScopeType("2");//权限范围类型(0:全部城市级别,1:城市级别,2:项目级别)
                memberRoleScopeEntity.setScopeId(projectIds.get(i));//权限范围Id(0/城市Id/项目Code)
                memberRoleScopeEntity.setScopeName(projectList.get(i));//权限范围名称(全部城市/北京/亚奥金茂悦)
                memberRoleScopeEntity.setNowState("0");//当前状态(0:不启用,1:启用)
                memberRoleScopeEntity.setCreateBy(roleRolesetDTO.getOperator());
                memberRoleScopeEntity.setCreateOn(new Date());
                memberRoleScopeEntity.setModifyBy(roleRolesetDTO.getOperator());
                memberRoleScopeEntity.setModifyOn(new Date());
                memberAuthorityRepository.saveOrUpdate(memberRoleScopeEntity);
            }
        }
    }

    /**
     * 通过角色Id删除角色信息
     */
    public void deleteRoleById(RoleRolesetDTO roleRolesetDTO){
        //删除角色信息
        memberAuthorityRepository.deleteRoleById(roleRolesetDTO.getSetId());
        //删除角色操作数据范围信息
        memberAuthorityRepository.deleteRoleScopeBySetId(roleRolesetDTO.getSetId());
    }

    /**
     * 保存会员角色菜单操作权限
     */
    public void saveOrUpdateRoleMenu(RoleRolesetDTO roleRolesetDTO){
        //删除该角色菜单操作级别数据及角色菜单权限数据
        memberAuthorityRepository.deleteRoleMenuBySetId(roleRolesetDTO.getSetId());
        List<RoleRolesetmapEntity> roleRolesetmapEntityList = roleRolesetmapRepository.listRolesetMapBySetId(roleRolesetDTO.getSetId());
        if (roleRolesetmapEntityList.size()>0){
            for (RoleRolesetmapEntity roleRolesetmapEntity:roleRolesetmapEntityList){
                roleRolesetmapRepository.deleteRoleRolesetMap(roleRolesetmapEntity);
            }
        }
        //保存会员角色菜单操作权限及角色菜单权限
        JSONArray jsonArray = JSONArray.fromObject(roleRolesetDTO.getJsonStr());
        for (int i=0; i<jsonArray.size(); i++){
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            MemberRoleMenuEntity memberRoleMenuEntity = new MemberRoleMenuEntity();
            memberRoleMenuEntity.setId(IdGen.uuid());   //主键Id
            memberRoleMenuEntity.setRoleSetId(roleRolesetDTO.getSetId());   //角色Id
            memberRoleMenuEntity.setMenuId(jsonObject.get("id").toString());    //菜单Id
            RoleViewmodelEntity roleViewmodelEntity = memberAuthorityRepository.getViewmodelByMenuId(jsonObject.get("id").toString());
            if (null != roleViewmodelEntity){
                memberRoleMenuEntity.setMenuName(roleViewmodelEntity.getMenuName());    //菜单名称
            }
            memberRoleMenuEntity.setOperationLevel(jsonObject.get("type").toString());  //操作级别(0:仅查看,1:所有操作)
            memberRoleMenuEntity.setNowState("1");  //当前状态(0:不启用,1:启用)
            memberRoleMenuEntity.setCreateBy(roleRolesetDTO.getOperator());
            memberRoleMenuEntity.setCreateOn(new Date());
            memberRoleMenuEntity.setModifyBy(roleRolesetDTO.getOperator());
            memberRoleMenuEntity.setModifyOn(new Date());
            memberAuthorityRepository.saveOrUpdate(memberRoleMenuEntity);
            //设置角色菜单权限
            RoleRolesetmapEntity roleRolesetmapEntity = new RoleRolesetmapEntity();
            roleRolesetmapEntity.setRolesetid(IdGen.uuid());
            roleRolesetmapEntity.setRolRoleId(jsonObject.get("id").toString());
            roleRolesetmapEntity.setSetId(roleRolesetDTO.getSetId());
            roleRolesetmapRepository.saveRolesetMap(roleRolesetmapEntity);
        }
        //设置角色及角色范围数据生效
        RoleRolesetEntity rolesetEntity = memberAuthorityRepository.getRoleById(roleRolesetDTO.getSetId());
        rolesetEntity.setSetState("01");
        memberAuthorityRepository.saveOrUpdate(rolesetEntity);
        memberAuthorityRepository.updateRoleScopeStateBySetId(roleRolesetDTO.getSetId());

    }

    /**
     * 获取会员菜单列表
     * @return  null
     */
    public List<Object> getMemberMenuList(String setId){
        List<Object> list = new ArrayList<>();
        //角色Id不为空,检索角色下菜单操作级别列表
        List<MemberRoleMenuEntity> roleMenuEntities = null;
        if (null != setId){
            roleMenuEntities = memberAuthorityRepository.getRoleMenuBySetId(setId);
        }
        List<RoleViewmodelEntity> menuLevelOneList = memberAuthorityRepository.getMenuLevelOneList();
        //遍历一级菜单封装二级菜单
        for (RoleViewmodelEntity menuLevelOne : menuLevelOneList){
            Map<String,Object> map = new HashMap<>();
            map.put("menuOneName",menuLevelOne.getMenuName());
            List<Map<String,Object>> menuTwoList = new ArrayList<>();
            List<RoleViewmodelEntity> menuLevelTwoList = memberAuthorityRepository.getMenuLevelTwoListByMenuId(menuLevelOne.getMenuId());
            for (RoleViewmodelEntity menuLevelTwo : menuLevelTwoList){
                Map<String,Object> menuMap = new HashMap<>();
                menuMap.put("menuId",menuLevelTwo.getMenuId());
                menuMap.put("menuName", menuLevelTwo.getMenuName());
                //角色Id不为空(编辑页面),追加操作级别回显
                if (null != setId){
                    String operationLevel = "-1";   //无操作级别
                    for (MemberRoleMenuEntity roleMenuEntity : roleMenuEntities){
                        if (menuLevelTwo.getMenuId().equals(roleMenuEntity.getMenuId())){
                            operationLevel = roleMenuEntity.getOperationLevel();
                        }
                    }
                    menuMap.put("operationLevel",operationLevel);
                }
                menuTwoList.add(menuMap);
            }
            map.put("menuTwoList",menuTwoList);
            list.add(map);
        }
        return list;
    }

    /**
     * 通过角色Id获取角色详情
     * @param setId 角色Id
     * @return  RoleRolesetEntity
     */
    public RoleRolesetDTO getRoleById(String setId){
        RoleRolesetDTO roleRolesetDTO = new RoleRolesetDTO();
        //封装角色详细信息
        RoleRolesetEntity rolesetEntity = memberAuthorityRepository.getRoleById(setId);
        roleRolesetDTO.setSetId(rolesetEntity.getSetId());          //角色Id
        roleRolesetDTO.setRoledesc(rolesetEntity.getRoledesc());    //角色名
        roleRolesetDTO.setRemarks(rolesetEntity.getRemarks());      //备注
        //项目
        String cityIds = "";
        String cityList = "";
        String projectIds = "";
        String projectList = "";
        List<MemberRoleScopeEntity> roleScopeEntities = memberAuthorityRepository.getRoleScopeById(setId);
        for (MemberRoleScopeEntity roleScopeEntity : roleScopeEntities){
            String scopeType = roleScopeEntity.getScopeType();
            if ("0".equals(scopeType)){
                //全部城市级别
                cityIds += "0,";
                cityList += "全部城市,";
                projectIds += "0,";
                projectList += "全部项目,";
            }else if ("1".equals(scopeType)){
                //城市级别
                cityIds += roleScopeEntity.getScopeId()+",";
                cityList += roleScopeEntity.getScopeName()+",";
                if (!projectIds.contains("0")){
                    projectIds += "0,";
                    projectList += "全部项目,";
                }
            }else if ("2".equals(scopeType)){
                //项目级别
                projectIds += roleScopeEntity.getScopeId()+",";
                projectList += roleScopeEntity.getScopeName()+",";
                List<Object[]> cities = memberAuthorityRepository.queryCityByProjectCode(roleScopeEntity.getScopeId());
                if (cities.size() > 0){
                    if (!cityIds.contains(cities.get(0)[0].toString())){
                        cityIds += cities.get(0)[0].toString()+",";
                        cityList += cities.get(0)[1].toString()+",";
                    }
                }
            }
        }
        roleRolesetDTO.setCityIds(cityIds);
        roleRolesetDTO.setCityList(cityList);
        roleRolesetDTO.setProjectIds(projectIds);
        roleRolesetDTO.setProjectList(projectList);
        return roleRolesetDTO;
    }

    /**
     * 通过角色名称模糊查询角色信息列表
     */
    public List<Map<String,Object>> getRolesByRoledesc(RoleRolesetDTO roleRolesetDTO){
        //角色模糊查询结果列表
        List<Map<String, Object>> roleScopeList = memberAuthorityRepository.getRoleScopesByRoledesc(roleRolesetDTO.getRoledesc());
        List<Map<String, Object>> roleScope2List = new ArrayList<>();
        //对roleScopeList集合去重合并项目范围
        if (roleScopeList != null){
            for (Map<String,Object> map1 : roleScopeList){
                if (roleScope2List.size() == 0){
                    roleScope2List.add(map1);
                }else{
                    int flag = 0;
                    for (Map<String,Object> map2 : roleScope2List){
                        String setId = map1.get("setId").toString();
                        String setId2 = map2.get("setId").toString();
                        if (setId.equals(setId2)){
                            flag = 1;
                            map2.put("scopeId",map2.get("scopeId").toString()+","+map1.get("scopeId").toString());
                            map2.put("scopeName",map2.get("scopeName").toString()+","+map1.get("scopeName").toString());
                        }
                    }
                    if (flag == 0){
                        roleScope2List.add(map1);
                    }
                }
            }
        }
        //当前登陆人可操作角色范围列表
        List<Map<String, Object>> userRoleScopeList = roleRolesetDTO.getRoleScopeList();
        List<Map<String, Object>> userRoleScope2List = new ArrayList<>();
        //对userRoleScopeList集合去重合并项目范围
        if (userRoleScopeList != null){
            for (Map<String,Object> map1 : userRoleScopeList){
                if (userRoleScope2List.size() == 0){
                    userRoleScope2List.add(map1);
                }else{
                    int flag = 0;
                    for (Map<String,Object> map2 : userRoleScope2List){
                        String setId = map1.get("setId").toString();
                        String setId2 = map2.get("setId").toString();
                        if (setId.equals(setId2)){
                            flag = 1;
                            map2.put("scopeId",map2.get("scopeId").toString()+","+map1.get("scopeId").toString());
                            map2.put("scopeName",map2.get("scopeName").toString()+","+map1.get("scopeName").toString());
                        }
                    }
                    if (flag == 0){
                        userRoleScope2List.add(map1);
                    }
                }
            }
        }
        //对集合roleScope2List、userRoleScope2List权限校验
        List<Map<String, Object>> resultList = new ArrayList<>();
        if (userRoleScope2List != null){
            for (Map<String,Object> userRoleScopeMap : userRoleScope2List){
                String scopeType = userRoleScopeMap.get("scopeType").toString();
                String scopeId = userRoleScopeMap.get("scopeId").toString();
                List<String> scopeIdList = Arrays.asList(scopeId.split(","));
                if (scopeType.equals("0")){
                    //全部城市
                    return memberAuthorityRepository.getRolesByRoledesc(roleRolesetDTO.getRoledesc());
                }else if (scopeType.equals("1")){
                    //城市级别
                    //1.选出同为该城市级别的角色(为该用户角色的子集)
                    if (roleScope2List != null){
                        for (Map<String,Object> roleScopeMap : roleScope2List){
                            String scopeType1 = roleScopeMap.get("scopeType").toString();
                            if (scopeType1.equals("1")){
                                String scopeId1 = roleScopeMap.get("scopeId").toString();
                                List<String> scopeId1List = Arrays.asList(scopeId1.split(","));
                                if (scopeId1List.size() <= scopeIdList.size()){
                                    int flag = 0;
                                    for (String scope_Id1 : scopeId1List){
                                        for (String scope_Id : scopeIdList){
                                            if (scope_Id1.equals(scope_Id)){
                                                flag += 1;
                                            }
                                        }
                                    }
                                    if (flag == scopeId1List.size()){
                                        resultList.add(roleScopeMap);
                                    }
                                }
                            }
                        }
                    }
                    //2.选出该城市下所有项目级别的角色
                    List<Object[]> projectList = new ArrayList<>();
                    for (String scope : scopeIdList){
                        projectList.addAll(userPropertyStaffRepository.listProjectByCity(scope));
                    }
                    if (roleScope2List != null){
                        for (Map<String,Object> roleScopeMap : roleScope2List){
                            String scopeType1 = roleScopeMap.get("scopeType").toString();
                            if (scopeType1.equals("2")){
                                String scopeId1 = roleScopeMap.get("scopeId").toString();
                                List<String> scopeId1List = Arrays.asList(scopeId1.split(","));
                                if (scopeId1List.size() <= projectList.size()){
                                    int flag = 0;
                                    for (String scope_Id1 : scopeId1List){
                                        for (Object[] scope_Id : projectList){
                                            if (scope_Id1.equals(scope_Id[0])){
                                                flag += 1;
                                            }
                                        }
                                    }
                                    if (flag == scopeId1List.size()){
                                        resultList.add(roleScopeMap);
                                    }
                                }
                            }
                        }
                    }
                }else if (scopeType.equals("2")){
                    //项目级别
                    if (roleScope2List != null){
                        for (Map<String,Object> roleScopeMap : roleScope2List){
                            String scopeType1 = roleScopeMap.get("scopeType").toString();
                            if (scopeType1.equals("2")){
                                String scopeId1 = roleScopeMap.get("scopeId").toString();
                                List<String> scopeId1List = Arrays.asList(scopeId1.split(","));
                                if (scopeId1List.size() <= scopeIdList.size()){
                                    int flag = 0;
                                    for (String scope_Id1 : scopeId1List){
                                        for (String scope_Id : scopeIdList){
                                            if (scope_Id1.equals(scope_Id)){
                                                flag += 1;
                                            }
                                        }
                                    }
                                    if (flag == scopeId1List.size()){
                                        resultList.add(roleScopeMap);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        //对集合resultList去重
        List<Map<String, Object>> result1List = new ArrayList<>();
        if (resultList != null){
            for (Map<String,Object> result : resultList){
                if (result1List.size() == 0){
                    result1List.add(result);
                }else{
                    int flag = 0;
                    for (Map<String,Object> result1 : result1List){
                        if (result.get("setId").toString().equals(result1.get("setId").toString())){
                            flag = 1;
                        }
                    }
                    if (flag == 0){
                        result1List.add(result);
                    }
                }
            }
        }
        return result1List;
    }

    /**
     * 通过角色Id检索成员列表
     */
    public List<Map<String,Object>> getStaffUserListByRoleId(RoleStaffUserDTO userPropertystaffDTO,WebPage webPage){
        //检索条件
        Map<String,Object> params = new HashMap<>();
        params.put("userName",userPropertystaffDTO.getUserNameDto());//账号名
        params.put("mobile",userPropertystaffDTO.getMobileDto());//手机号
        params.put("company",userPropertystaffDTO.getCompany());//公司名
        params.put("beginTime",userPropertystaffDTO.getBeginTimeDto());//查询开始时间
        params.put("endTime",userPropertystaffDTO.getEndTimeDto());//查询结束时间
        params.put("scope",userPropertystaffDTO.getScope());//区域
        params.put("projectName",userPropertystaffDTO.getProjectName());//项目
        params.put("roleSetId",userPropertystaffDTO.getRoleSetId());//角色Id
        //执行查询
        List<Map<String, Object>> staffUserList = memberAuthorityRepository.getStaffUserListByRoleId(params, webPage);
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
     * 通过角色Id检索成员总数
     */
    public Long getStaffUserCountByRoleId(RoleStaffUserDTO userPropertystaffDTO){
        //检索条件
        Map<String,Object> params = new HashMap<>();
        params.put("userName",userPropertystaffDTO.getUserNameDto());//账号名
        params.put("mobile",userPropertystaffDTO.getMobileDto());//手机号
        params.put("company",userPropertystaffDTO.getCompany());//公司名
        params.put("beginTime",userPropertystaffDTO.getBeginTimeDto());//查询开始时间
        params.put("endTime",userPropertystaffDTO.getEndTimeDto());//查询结束时间
        params.put("scope", userPropertystaffDTO.getScope());//区域
        params.put("projectName", userPropertystaffDTO.getProjectName());//项目
        params.put("roleSetId", userPropertystaffDTO.getRoleSetId());//角色Id
        //执行查询
        Long count = memberAuthorityRepository.getStaffUserCountByRoleId(params);
        return count;
    }

    /**
     * 通过用户Id和角色Id删除用户角色关系信息
     */
    public int delMemberRole(RoleStaffUserDTO userPropertystaffDTO){
        //参数
        Map<String,Object> params = new HashMap<>();
        params.put("setId",userPropertystaffDTO.getRoleSetId());
        params.put("staffId",userPropertystaffDTO.getStaffIdDto());
        //执行删除
        int flag = memberAuthorityRepository.delMemberRole(params);
        return flag;
    }

    /**
     * 角色成员管理-批量保存角色成员
     */
    public void saveRoleMember(RoleStaffUserDTO userPropertystaffDTO){
        List<String> staffIdList = Arrays.asList(userPropertystaffDTO.getStaffIds().split(","));
        for (String staffId :staffIdList){
            RoleRoleanthorityEntity roleAnthorityEntity = new RoleRoleanthorityEntity();
            roleAnthorityEntity.setUserId(IdGen.uuid());        //主键
            roleAnthorityEntity.setStaffId(staffId);       //员工用户Id
            roleAnthorityEntity.setSetId(userPropertystaffDTO.getRoleSetId());       //角色Id
            memberAuthorityRepository.saveOrUpdate(roleAnthorityEntity);
        }
    }

    /**
     * 通过角色Id检索数据范围信息
     * @param rolesetId 角色Id
     * @return  List<Map<String,Object>>
     */
    public List<Map<String,Object>> getRoleScopeById(String rolesetId){
        List<Map<String,Object>> projectList = new ArrayList<>();
        List<MemberRoleScopeEntity> roleScopeEntities = memberAuthorityRepository.getRoleScopeById(rolesetId);
        if (null != roleScopeEntities){
            for (MemberRoleScopeEntity roleScopeEntity : roleScopeEntities){
                String scopeType = roleScopeEntity.getScopeType();
                if (scopeType.equals("0")){
                    //全部城市
                    List<Map<String, Object>> list = userPropertyStaffRepository.listAllProject();
                    for (Map<String,Object> map : list){
                        Map<String,Object> project = new HashMap<>();
                        project.put("projectId",map.get("projectId"));
                        project.put("projectName",map.get("projectName"));
                        projectList.add(project);
                    }
                }else if (scopeType.equals("1")){
                    //城市级别
                    List<Object[]> list = userPropertyStaffRepository.listProjectByCity(roleScopeEntity.getScopeId());
                    for (Object[] object : list){
                        Map<String,Object> project = new HashMap<>();
                        project.put("projectId",object[0].toString());
                        project.put("projectName",object[1].toString());
                        projectList.add(project);
                    }
                }else{
                    //项目级别
                    Map<String,Object> project = new HashMap<>();
                    project.put("projectId",roleScopeEntity.getScopeId());
                    project.put("projectName",roleScopeEntity.getScopeName());
                    projectList.add(project);
                }
            }
        }
        return projectList;
    }

    /**
     * 校验用户菜单操作级别(取最高级别)
     * @param staffId   用户Id
     * @param menuId    菜单Id
     * @return  int
     */
    public int checkStaffMenuOperationLevel(String staffId,String menuId){
        int flag = 0;//0:无操作权限
        Map<String,Object> params = new HashMap<>();
        params.put("staffId",staffId);
        params.put("menuId", menuId);
        List<Map<String, Object>> mapList = memberAuthorityRepository.getRoleMenuByStaffAndMenu(params);
        if (mapList.size() > 0){
            for (Map map : mapList){
                String operationLevel = map.get("operationLevel").toString();
                if (operationLevel.equals("1")){
                    flag = 1;//1:拥有操作权限
                }
            }
        }
        return flag;
    }

    /**
     * 校验角色名称是否重复
     * @param roledesc  角色描述
     * @return int
     */
    public int checkRoledesc(String roledesc){
        return memberAuthorityRepository.getRoleCountByRoledesc(roledesc);
    }

    /**
     * 导出excel
     * @param
     * @return
     */
    @Override
    public void exportExcel(List<Map<String, Object>> rolesetList, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) throws IOException{
        OutputStream outputStream = httpServletResponse.getOutputStream();

        WebPage webPage = new WebPage();
        webPage.setPageIndex(0);
        webPage.setPageSize(1000);

        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        List<RoleRolesetDTO> list = new ArrayList<>();
        for (int i=0; i<rolesetList.size(); i++) {
            Map<String, Object> roleSet = rolesetList.get(i);
            RoleRolesetDTO roleRolesetDTO = new RoleRolesetDTO();
            roleRolesetDTO.setRoledesc((String) roleSet.get("roledesc"));
            roleRolesetDTO.setRemarks((String) roleSet.get("remarks"));
            roleRolesetDTO.setProjectCode((String) roleSet.get("scopeName"));
            String setType = (String) roleSet.get("setType");
            if ("1".equals(setType)) {
                roleRolesetDTO.setSetType("金茂会员");
            }else if ("2".equals(setType)) {
                roleRolesetDTO.setSetType("金茂质检");
            }else if ("3".equals(setType)) {
                roleRolesetDTO.setSetType("全部");
            }
            java.sql.Date date = (java.sql.Date)roleSet.get("makeDate");
            roleRolesetDTO.setMakeDate((java.sql.Date)roleSet.get("makeDate"));
            list.add(roleRolesetDTO);
        }

        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        XSSFSheet sheet = workBook.createSheet("角色管理列表");
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

        String[] titles = {"序号", "角色名称", "备注", "数据查看范围", "分类", "创建时间"};
        XSSFRow headRow = sheet.createRow(0);

        if (list.size() > 0) {

            list.forEach(userDTO -> {

                XSSFCell cell=null;
                for (int i = 0; i < titles.length; i++) {
                    cell = headRow.createCell(i);
                    headRow.createCell(i).setCellValue(titles.length);
                    sheet.setColumnWidth((short) i, (short) (titles[i].length() * 800));
                    cell.setCellStyle(headStyle);
                    cell.setCellValue(titles[i]);
                }

                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        XSSFRow bodyRow = sheet.createRow(i + 1);
                        RoleRolesetDTO roleRolesetDTO = list.get(i);
                        cell = bodyRow.createCell(0);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(i + 1);//序号

                        cell = bodyRow.createCell(1);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(roleRolesetDTO.getRoledesc());//角色名称

                        cell = bodyRow.createCell(2);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(roleRolesetDTO.getRemarks());//备注

                        cell = bodyRow.createCell(3);
                        cell.setCellStyle(bodyStyle);
                        cell.setCellValue(roleRolesetDTO.getProjectCode());//数据查看范围

                        cell = bodyRow.createCell(4);
                        cell.setCellStyle(bodyStyle);
                        cell.setCellValue(roleRolesetDTO.getSetType());//分类

                        cell = bodyRow.createCell(5);
                        cell.setCellStyle(bodyStyle);// 表格黑色边框
                        cell.setCellValue(DateUtils.format(roleRolesetDTO.getMakeDate()));//创建时间
                    }
                }
            });
        }
        try {
            String fileName = new String(("角色管理列表").getBytes(), "ISO8859-1");
            String agent = httpServletRequest.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident")) {// ie

                fileName = java.net.URLEncoder.encode("角色管理列表", "UTF8");
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
    }

}
