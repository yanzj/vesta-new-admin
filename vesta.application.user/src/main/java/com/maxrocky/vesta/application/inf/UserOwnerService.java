package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.*;
import com.maxrocky.vesta.application.DTO.json.LG0001.LoginReturnJsonDTO;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghj on 2016/1/26.
 */
public interface UserOwnerService {

       /**
        * 初始化列表
        * @param userOwnerDTO
        * @param webPage
        * @return
        */
       public  List<UserOwnerDTO> listOwnerDto(UserOwnerDTO userOwnerDTO,WebPage webPage,String userType);

       /**
        * 初始化搜索栏下拉框
        * @return
        */
       public List<ProjectSelDTO> getProjectSel(String companyId);

    /**
     * 业态下拉框
     * @param projectId
     * @return
     */
    public List<FormatSelDTO> getFormatSel(String projectId);

       /**
        * 楼栋下拉框
        * @param projectId
        * @return
        */
       public List<BuildingSelDTO> getBuildSel(String projectId);

    /**
     * 楼栋下拉框
     * @param projectId,formatId
     * @return
     */
    public List<BuildingSelDTO> getformatBuildSel(String projectId,String formatId);

       /**
        * 单元下拉框
        * @param buildingId
        * @return
        */
       public List<UnitSelDTO> getUnitSel(String buildingId);

       /**
        * 房间下拉框
        * @param unitId
        * @return
        */
       public List<RoomDTO> getRoomSel(String unitId);

       /**
        * 添加业主
        * @param userOwnerDTO
        * @return
        */
       public boolean addOwner(UserOwnerDTO userOwnerDTO);

       /**
        * 解除家属租户关系
        * @param houseUserId
        * @return
        */
       public String delFamTen(String houseUserId);

       /**
        * 添加业主和租户
        * @param userTenantDTO
        * @return
        */
       public UserTenantDTO addFamTen(UserTenantDTO userTenantDTO,UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 用来更改租户家属最高权限
     * @param userTenantDTO
     * @return
     */
    public boolean updateType(UserTenantDTO userTenantDTO);

    /**
     * 更改家属租户缴费授权
     * @param houseuserId
     * @return
     */
    public String updatePayType(String houseuserId);

    /**
     * 获取用户列表
     * param:userDTO
     * param:webPage
     * return
     */
    List<LoginReturnJsonDTO> getUserList(UserPropertyStaffEntity user,LoginReturnJsonDTO userDTO,WebPage webPage);


    /**
     * 导出excel
     * param:user
     * param:httpServletResponse
     * return
     */
    String exportExcel(UserPropertyStaffEntity user,LoginReturnJsonDTO userDTO,
                       HttpServletResponse httpServletResponse,String type,HttpServletRequest httpServletRequest) throws Exception;

    /**
     * 优化会员管理导出Excel
     * @param response
     * @param request
     * @param type
     * @param user
     * @param userLoginDTO
     * @throws Exception
     */
    void exportExcel2(HttpServletResponse response,HttpServletRequest request,String type,UserPropertyStaffEntity user,LoginReturnJsonDTO userLoginDTO) throws Exception;

    /**
     * 获取业主认证信息列表
     */
    List<OwnerAuthenticationDTO> getOwnerAuthenticationList(OwnerAuthenticationDTO ownerAuthenticationDTO,WebPage webPage);

    /**
     * 获取业主认证信息
     */
    OwnerAuthenticationDTO getOwnerAuthenticationInfo(OwnerAuthenticationDTO ownerAuthenticationDTO);

    /**
     * 更新业主认证状态
     */
    int updateOwnerAuthenticationState(OwnerAuthenticationDTO ownerAuthenticationDTO);

    /**
     * 获取业主申诉信息列表
     */
    List<OwnerAuthenticationDTO> getOwnerAppealList(OwnerAuthenticationDTO ownerAuthenticationDTO,WebPage webPage);

    /**
     * 获取业主申诉信息
     */
    OwnerAuthenticationDTO getOwnerAppealInfo(OwnerAuthenticationDTO ownerAuthenticationDTO);

    /**
     * 更新业主申诉状态,执行申诉
     */
    int updateOwnerAppealState(OwnerAuthenticationDTO ownerAuthenticationDTO);

    /**
     * 获取CRM业主数据列表
     */
    List<Map<String,Object>> getCRMOwnerUserList(LoginReturnJsonDTO loginReturnJsonDTO,WebPage webPage);

    /**
     * 用户申诉模块导出Excel
     */
    void exportOwnerAppealListExcel(HttpServletResponse response,HttpServletRequest request,UserPropertyStaffEntity user,OwnerAuthenticationDTO ownerAuthenticationDTO) throws Exception;

    /**
     * 手动同步HouseUserCrm房产数据
     */
    void houseUserCrmSyn();
}
