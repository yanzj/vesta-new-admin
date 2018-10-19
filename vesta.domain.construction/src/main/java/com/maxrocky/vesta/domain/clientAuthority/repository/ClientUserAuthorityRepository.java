package com.maxrocky.vesta.domain.clientAuthority.repository;

import com.maxrocky.vesta.domain.model.AuthOuterAgencyEntity;
import com.maxrocky.vesta.domain.model.UserAgencyEntity;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserMapEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by yuanyn on 2018/5/7.
 */
public interface ClientUserAuthorityRepository {

    //获取OA同步已启用的全部用户
    List<UserMapEntity> getUserMap();
    //通过Id获取启用状态
    UserMapEntity getUserMapById(String staffId);
    //条件检索OA用户
    List<Object[]> getOAUserManageListByCondition(Map map, WebPage webPage, List<String> idList);
    //启用、停用人员
    void saveOrUpdateClientStaff(UserMapEntity userMapEntity);
    //获取工程外部组织机构列表
    List<AuthOuterAgencyEntity> getClientOuterAgencyList();
    //根据人员Id获取其所属的组织架构
    List<String> getClientAgencyByStaffId(String staffId);
    //条件检索外部用户列表
    List<Object[]> getClientOuterUserList(Map map , WebPage webPage);
    //根据人员Id获取其所在的外部组织机构
    List<String> getClientAgencyNameByStaffId(String staffId);
    //条件检索系统用户列表
    List<Object[]> getClientEnabledUserList(Map map , WebPage webPage);
    //查询组织机构是否存在
    AuthOuterAgencyEntity getOuterAgencyByName(String agencyName);
    //根据用户名获取员工信息
    UserInformationEntity getStaffBySysName(String sysName);
    //新权限的添加新员工
    boolean addClientOuterStaff(UserInformationEntity userInformationEntity);
}
