package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.admin.StaffDTO;
import com.maxrocky.vesta.application.DTO.admin.UserPropertystaffDTO;
import com.maxrocky.vesta.application.DTO.json.HI0009.AppFeedBackDTO;
import com.maxrocky.vesta.application.dto.adminDTO.AuthSupplierPeopleDTO;
import com.maxrocky.vesta.application.dto.adminDTO.StaffNameDTO;
import com.maxrocky.vesta.application.dto.adminDTO.batch.StaffReceiveDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserInformationEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;

/**
 * Created by JillChen on 2016/1/13.
 */
public interface UserPropertystaffService {
    UserPropertyStaffEntity CheckStaffByIdAPwd(UserPropertyStaffEntity propertystaff);

    /**
     * Code:For admin login
     * Type:Server Method
     * Describe:返回指定员工ID的员工信息
     * CreateBy:Tom
     * CreateOn:2016-02-17 11:23:51
     */
    UserPropertyStaffEntity get(String staffId);

    /**
     * Code:UI0001
     * Type:UI Method
     * Describe:This is describe.
     * CreateBy:Tom
     * CreateOn:2016-03-18 09:30:48
     */
    UserPropertyStaffEntity getByUserName(String userName);

    /**
     * 获取员工列表
     * @return
     */
    public List<UserPropertystaffDTO> listStaffDTO(UserPropertystaffDTO staffDto,WebPage webPage);

    /**
     * 删除员工
     * @param staffId
     * @return
     */
    public boolean deleteStaff(String staffId,UserPropertyStaffEntity userPropertystaffEntity);

    /**
     * 解除员工组织绑定关系
     * */
    void delStaffAgency(String staffId,String agencyId);

    /**
     * 添加新员工
     * @param userPropertystaffDTO
     * @return
     */
    public UserPropertystaffDTO addStaff(UserPropertystaffDTO userPropertystaffDTO);

    /**
     * 根据Id查找员工详情
     * @param id
     * @return
     */
    public UserPropertystaffDTO getStaffById(String id);

    /**
     * 质检APP获取最新用户信息
     * */
    public ApiResult getRecentStaff(UserPropertyStaffEntity userPropertyStaffEntity);

     /**
     * 更新员工信息
     * @param userPropertystaffDTO
     * @return
     */
    public UserPropertystaffDTO updateStaff(UserPropertystaffDTO userPropertystaffDTO);
    public ApiResult updateAppStaff(UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 获取万达内部员工详情
     * @param userName
     * @return
     */
    //public UserWandaStaffDTO getWandaStaffByUserName(String userName);

    /**
     * 虽然上面有这些方法，但是我不敢改啊，只能重新再写
     * 质检后台创建APP员工信息
     * */
    public String saveStaff(StaffReceiveDTO staffDTO,UserPropertyStaffEntity userPropertyStaffEntity);


    /**
     *  新权限功能 项目-责任单位-人员新增  增加用户信息
     * */
    public String saveAuthStaff(AuthSupplierPeopleDTO authSupplierPeopleDTO,UserInformationEntity userPropertystaffEntity);

    /**
     * 质检修改APP员工信息
     * */
    public String alterStaff(StaffReceiveDTO staffDTO,UserPropertyStaffEntity userPropertyStaffEntity);

    /**
     * 质检修改APP员工信息 新权限
     * */
    public String alterAuthStaff(AuthSupplierPeopleDTO authSupplierPeopleDTO,UserInformationEntity userPropertystaffEntity);

    /**
     * 质检员工后台修改密码
     * */
    void altPassword(StaffDTO staffDTO);

    /**
     * 质检删除APP员工信息
     * */
    public boolean removeStaff(String staffId);

    public StaffDTO getStaffDetail(String staffId);

    /**员工端密码找回校验*/
    public ApiResult foundPassword(String userName,String mobile,String phoneCode);

    /**员工端新增意见反馈*/
    public void saveFeedBack(List<AppFeedBackDTO> feedbackDTOs,UserPropertyStaffEntity userPropertyStaffEntity);

    /**根据员工名搜索*/
    List<StaffNameDTO> searchStaffByName(String staffName);

    /**************************新权限功能***********************************/
    UserInformationEntity CheckUserInforByIdAPwd(UserInformationEntity userInfor,String checkLogin);

    UserInformationEntity CheckAuthUserInforByIdAPwd(UserInformationEntity userInfor);

    boolean getCheckUserMapById(String staffId, String checkLogin);
   }
