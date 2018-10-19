package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chen on 2016/6/17.
 */
public interface AgencyRepository {
    //获取机构根目录
    List<AgencyEntity> getAgencyRoots();
    //根据上级ID获取机构层级目录
    List<AgencyEntity> getAgencyList(String parentId);
    //根据上级ID获取机构层级目录
    List<AgencyEntity> searchAgencyList(String parentId);
    //根据上级ID获取部门
    List<AgencyEntity> getAgency1(String parentId);
    //根据上级ID获取下级列表 带分页
    List<AgencyEntity> getAgencys(String agencyId,String agencyName,WebPage webPage);
    List<AuthOuterAgencyEntity> getAgencys();
    //获取所有机构列表
    List<AgencyEntity> getAllAgencyList();
    //获取外部组织机构
    List<AuthOuterAgencyEntity> getOuterAgencyList();
    //获取OA同步过来的组织机构
    List<String> getAgencyByOA();
    //根据id获取机构
    AgencyEntity getAgencyDetail(String agencyId);
    //根据Id获取机构
    AuthOuterAgencyEntity getAgencyDetailByAgencyId(String agencyId);
    //根据path获取该机构下所有子机构
    List<AgencyEntity> getChildAgancy(String path,String agencyId);
    //根据上级ID获取上级机构
    AgencyEntity getAgency(String parentId);
    //新增机构
    void addAgency(AgencyEntity agencyEntity);
    //新增机构
    boolean addAgencys(AgencyEntity agencyEntity);
    //更新机构
    void updateAgency(AgencyEntity agencyEntity);
    //根据员工ID获取机构权限下的项目
    List<HouseProjectEntity> getProjectsByAgency(String staffId);
    //删除机构节点
    void delAgency(AgencyEntity agencyEntity);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 新增修改标段的时候，获取对应总包
    */
    List<AgencyEntity> getAAgencyList(String projectId);


    List<Object []> getAuthSupplierByprojectId(String  projectId,String type,String supplierId);

    /**
     *
     * @param content
     * @return
     */
    AgencyEntity getAgencyDetailByName(String content);
    /**
     *
     * @param content
     * @return
     */
    AgencyEntity getAgencyDetailListByName(String content,String projectId);
    /**
     *根据组织机构名称搜索组织机构
     * @param agencyName
     * @return
     */
    AgencyEntity getAgencyByName(String agencyName,String agencyId);
    /**
     * 查询外部合作单位机构详情
     */
    AgencyEntity getAgencyByAgencyName(String agencyName);
    //获取所有OA同步过来的机构列表
    List<AuthOuterAgencyEntity> getOAAgencyList();
    List<AuthOuterAgencyEntity> getOAAgencyListIsDel();

    //启用、停用人员
    void saveOrUpdateStaff(UserMapEntity userMapEntity);

    //条件检索系统用户列表
    List<Object[]> getEnabledUserList(Map map , WebPage webPage);

    //条件检索外部用户列表
    List<Object[]> getOuterUserList(Map map , WebPage webPage);

    //根据人员Id获取其所在的外部组织机构
    List<String> getAgencyNameByStaffId(String staffId);

    //根据人员Id获取其所在的全部组织机构
    List<String> getAgencyAllNameByStaffId(String staffId);

    //根据人员Id获取其所在的组织机构
    List<String> getOAAgencyNameByStaffId(String staffId);

    //通过Id获取用户与组织机构关系
    List<UserAgencyEntity> getUserAgency(String staffId);

    //通过机构Id获取机构信息
    AuthAgencyEntity getAgencyById(String AgencyId);

    //通过Id获取启用状态
    UserMapEntity getUserMap(String staffId);

    //通过Id获取人员信息
    List<UserInformationEntity> getUserInformation(String staffId);

    //修改Entity
    <T> void saveOrupdate(T entity);

    //根据Id获取组织架构
    AuthOuterAgencyEntity getAgencyByAgencyId(String id);

    //根据人员Id获取其所属的组织架构
    List<String> getAgencyByStaffId(String staffId);

    //根据用户名获取用户信息
    UserInformationEntity getUserByName(String userName);

    //根据用户名和用户Id获取用户信息
    UserInformationEntity getUserByNameAndId(String userName,String id);

    //根据Id获取用户详情
    UserInformationEntity getUserById(String id);

    //查询组织机构是否存在
    AuthOuterAgencyEntity getOuterAgencyByName(String agencyName);

    //删除人员与组织架构的关系
    void delUserAgencyMap(String staffId, Date modifyOn);

    //保存人员与项目关联关系
    void saveOrUpdateUserAgencyMap(UserAgencyEntity userAgencyEntity);

    //根据组织机构Id查询组织机构下人员
    List<String> getUserByAgencyId(String agencyId);

    void delAgencyByAgencyId(String departmentid, String userId);
    //根据组织机构Id和用户Id查询对应关系
    UserAgencyEntity getAgencyUserByAgencyId(String agencyId,String userId);

    //根据UserId获取用户信息
    UserInformationEntity getUserByUserId(String userId);
    //修改组织机构
    void updateAgency(AuthOuterAgencyEntity authOuterAgencyEntity);
    //保存组织机构
    void saveAgency(AuthOuterAgencyEntity authOuterAgencyEntity);
    //删除重复数据
    void delAgencyByOA();
    //删除垃圾数据
    void delWasteAgency();
    void removeAgency(List<String> id);
    //修改app端基础数据
    void updateBasePeople(String id, String name, Date date);
    void updateRoleStaff(String id, Date date);


    //根据用户名和用户Id获取用户信息
    boolean checkUserByName(String sysName);

}
