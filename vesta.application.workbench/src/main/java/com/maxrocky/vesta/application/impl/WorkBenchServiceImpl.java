package com.maxrocky.vesta.application.impl;

import com.maxrocky.vesta.application.DTO.jsonDTO.*;
import com.maxrocky.vesta.application.inf.UserAnthorityService;
import com.maxrocky.vesta.application.DTO.admin.UserAnthorityDTO;
import com.maxrocky.vesta.application.inf.WorkBenchService;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.common.restHTTPResult.SuccessApiResult;
import com.maxrocky.vesta.domain.model.AppVersionEntity;
import com.maxrocky.vesta.domain.model.HouseSectionEntity;
import com.maxrocky.vesta.domain.model.UserPropertyStaffEntity;
import com.maxrocky.vesta.domain.repository.*;
import com.maxrocky.vesta.exception.GeneralException;
import com.maxrocky.vesta.message.error.ErrorResource;
import com.maxrocky.vesta.message.success.SuccessResource;
import com.maxrocky.vesta.utility.PasswordEncode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

/**
 * Created by zhanghj on 2016/1/28.
 */
@Service
public class WorkBenchServiceImpl implements WorkBenchService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PraiseRepository praiseRepository;

    @Autowired
    private AppVersionRepository appVersionRepository;

    @Autowired
    private UserPropertyStaffRepository userPropertyStaffRepository;

    @Autowired
    private HouseSectionRepository houseSectionRepository;

    @Autowired
    private UserAnthorityService userAnthorityService;

    @Override
    public ApiResult getWorkBenchInfo(String userId,String type,String token) {

        WorkBenchDTO workBenchDTO = new WorkBenchDTO();

        UserPropertyStaffEntity userPropertyStaffEntity = userPropertyStaffRepository.get(userId);
        if (userPropertyStaffEntity==null){
            return ErrorResource.getError("tip_wb0001");
        }
        workBenchDTO.setStaffId(userPropertyStaffEntity.getStaffId());//用户id
        workBenchDTO.setUserName(userPropertyStaffEntity.getUserName());//用户名
        workBenchDTO.setLogo(userPropertyStaffEntity.getLogo());//头像

        int number = praiseRepository.countUnread(userId);
        workBenchDTO.setCountPraise(number);//未读表扬数

        if (!type.equals("1")&&!type.equals("2")){
            return ErrorResource.getError("tip_wb0002");
        }
        AppVersionEntity appVersionEntity = appVersionRepository.getNewVersionByType(type,"staff");//查找员工最新版本
        if (appVersionEntity==null){
            return ErrorResource.getError("tip_wb0003");
        }

        workBenchDTO.setVersion(appVersionEntity.getAppVersion());//版本信息

        UserAnthorityDTO userAnthorityDTO = userAnthorityService.getUserAnthority(userId);
        StaffCacheDto staffCacheDto = new StaffCacheDto();
        staffCacheDto.setStaffId(userId);//员工id
        staffCacheDto.setRoleId(userAnthorityDTO.getAppRoleSetId());//权限id
        staffCacheDto.setToken(token);//vestaToken

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("workBenchDTO",workBenchDTO);
        modelMap.addAttribute("staffCacheDto",staffCacheDto);
        return new SuccessApiResult(modelMap);
    }


    /**
     * 获取员工信息
     * @param staffId
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult getStaffInfo(String staffId) throws GeneralException {
        try {
            UserPropertyStaffEntity userPropertyStaffEntity = userPropertyStaffRepository.get(staffId);
            if (userPropertyStaffEntity == null) {
                return ErrorResource.getError("tip_Workbench_IDError");
            }
            StaffInfoDTO staffInfoDTO = new StaffInfoDTO();
            //员工Id
            staffInfoDTO.setStaffId(userPropertyStaffEntity.getStaffId());
            //员工姓名
            staffInfoDTO.setStaffName(userPropertyStaffEntity.getStaffName());
            //员工姓名
            if (userPropertyStaffEntity.getLogo()!=null) {
                staffInfoDTO.setStaffLogo(userPropertyStaffEntity.getLogo());
            }
            else {
                staffInfoDTO.setStaffLogo("");
            }
            //员工手机号
            staffInfoDTO.setStaffMobile(userPropertyStaffEntity.getMobile());
            HouseSectionEntity sectionEntity = houseSectionRepository.getSectionById(userPropertyStaffEntity.getDepartmentId());

            if (sectionEntity!=null) {
                //部门Id
                staffInfoDTO.setSectionId(sectionEntity.getSectionId());
                //部门名称
                staffInfoDTO.setSectionName(sectionEntity.getSectionName());
            }else {
                //部门Id
                staffInfoDTO.setSectionId("");
                //部门名称
                staffInfoDTO.setSectionName("");
            }

            //返回结果ＤTO
//            ModelMap modelMap = new ModelMap();
//            modelMap.addAttribute("staffInfoDTO",staffInfoDTO);
            return new SuccessApiResult(staffInfoDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }
    }

    /**
     *
     * @param staffInfoDTO
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult updateLOGO(StaffInfoDTO staffInfoDTO) throws GeneralException {
        if (staffInfoDTO.getStaffLogo()==null||staffInfoDTO.getStaffLogo().equals("")){
            return ErrorResource.getError("tip_Workbench_LOGOError");//头像路径为空
        }
        try {
            UserPropertyStaffEntity userPropertyStaffEntity = userPropertyStaffRepository.get(staffInfoDTO.getStaffId());
            if (userPropertyStaffEntity == null) {
                return ErrorResource.getError("tip_Workbench_IDError");//获取员工信息失败
            }
            userPropertyStaffEntity.setLogo(staffInfoDTO.getStaffLogo());
            boolean result = userPropertyStaffRepository.updateStaff(userPropertyStaffEntity);//更新头像
        if (!result){
            return ErrorResource.getError("tip_Workbench_LOGOupdateFaild");//头像更新失败
        }else {
            return new SuccessApiResult(SuccessResource.getResource("tip_Workbench_LOGOupdateSuc"));//头像更新成功
        }
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }

    }

    /**
     * 更新密码
     * @param staffPasswordDTO
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult updatePwd(StaffPasswordDTO staffPasswordDTO) throws GeneralException {
        if (staffPasswordDTO.getOldPassword()==null||staffPasswordDTO.equals("")){
            return ErrorResource.getError("tip_Workbench_OldPwdNull");//原密码不能为空
        }
        if (staffPasswordDTO.getNewPassword_1()==null||"".equals(staffPasswordDTO.getNewPassword_1())){
            return  ErrorResource.getError("tip_Workbench_NewPwdNull_1");//新密码不能为空
        }
        if (staffPasswordDTO.getNewPassword_2()==null||"".equals(staffPasswordDTO.getNewPassword_2())){
            return  ErrorResource.getError("tip_Workbench_NewPwdNull_2");//新密码不能为空
        }
        if (!staffPasswordDTO.getNewPassword_2().equals(staffPasswordDTO.getNewPassword_1())){
            return  ErrorResource.getError("tip_Workbench_NewPwdError");//新密码不能为空
        }
        try {
            UserPropertyStaffEntity userPropertyStaffEntity = new UserPropertyStaffEntity();
            //加密原密码
            String oldPwd = PasswordEncode.digestPassword(staffPasswordDTO.getOldPassword());
            userPropertyStaffEntity.setStaffId(staffPasswordDTO.getStaffId());
            userPropertyStaffEntity.setPassword(oldPwd);

            UserPropertyStaffEntity staffEntity = userPropertyStaffRepository.CheckStaffByIdAPwd(userPropertyStaffEntity);
            if (staffEntity == null) {
                return ErrorResource.getError("tip_Workbench_OldPwdError");//原密码错误
            }
            //加密新密码
            String newPwd = PasswordEncode.digestPassword(staffPasswordDTO.getNewPassword_1());
            staffEntity.setPassword(newPwd);
            boolean result = userPropertyStaffRepository.updateStaff(staffEntity);//更新密码
            if (!result){
                return ErrorResource.getError("tip_Workbench_PwdUpFailed");//密码更新失败
            }else {
                return new SuccessApiResult(SuccessResource.getResource("tip_Workbench_PwdUpSuccess"));//密码更新成功
            }
    }catch (Exception e){
        e.printStackTrace();
        throw new GeneralException("100","系统处理错误");
    }
    }


    /**
     * 获取版本信息
     * @param mobileType
      * @param userType
     * @return
     * @throws GeneralException
     */
    @Override
    public ApiResult showVersion(String mobileType,String userType) throws GeneralException {
        try {
            AppVersionEntity appVersionEntity = appVersionRepository.getNewVersionByType(mobileType,userType);//查找员工最新版本
            if (appVersionEntity==null){
                return ErrorResource.getError("tip_wb0003");
            }
            VersionDTO versionDTO = new VersionDTO();
            versionDTO.setAppVersionId(appVersionEntity.getAppVersionId());
            versionDTO.setAppVersionType(appVersionEntity.getAppVersionType());
            versionDTO.setAppVersion(appVersionEntity.getAppVersion());
            versionDTO.setAppVersionStatus(appVersionEntity.getAppVersionStatus());
            versionDTO.setDownUrl(appVersionEntity.getDownUrl());
            versionDTO.setAppVersionName(appVersionEntity.getAppVersionName());
            versionDTO.setAppRemark(appVersionEntity.getAppRemark());
            versionDTO.setUserType(appVersionEntity.getUserType());
            return new SuccessApiResult(versionDTO);
        }catch (Exception e){
            e.printStackTrace();
            throw new GeneralException("100","系统处理错误");
        }

    }
}
