package com.maxrocky.vesta.application.inf;

import com.maxrocky.vesta.application.DTO.json.AppealDTO;
import com.maxrocky.vesta.application.DTO.json.QualicationDTO;
import com.maxrocky.vesta.application.DTO.json.UI0003.ResetPasswordJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0010.FeedbackParamJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0011.ModifyPassword;
import com.maxrocky.vesta.application.DTO.json.UI0013.ModifyHouseUserJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0002.RegisterParamJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0005.ModifyUserInfoParamJsonDTO;
import com.maxrocky.vesta.application.DTO.json.UI0009.CreateHouseUserJsonDTO;
import com.maxrocky.vesta.common.restHTTPResult.ApiResult;
import com.maxrocky.vesta.domain.model.UserCRMEntity;
import com.maxrocky.vesta.domain.model.UserInfoEntity;
import com.maxrocky.vesta.domain.model.UserTokenEntity;
import com.maxrocky.vesta.exception.GeneralException;
import org.springframework.ui.ModelMap;

import java.text.ParseException;

/**
 * Created by Tom on 2016/1/14 9:45.
 * Describe:用户信息Service接口
 */
public interface UserInfoService {

    /**
     * Code:UI0002
     * Type:UI Method
     * Describe:业主注册
     * CreateBy:Tom
     * CreateOn:2016-01-18 05:01:20
     */
    ApiResult register(RegisterParamJsonDTO registerParamJsonDTO) throws GeneralException;

    /**
     * Code:UI0003
     * Type:UI Method
     * Describe:重置密码
     * CreateBy:Tom
     * CreateOn:2016-01-19 03:37:51
     */
    ApiResult resetPassword(ResetPasswordJsonDTO resetPasswordJsonDTO) throws GeneralException;

    /**
     * Code:UI0004
     * Type:UI Method
     * Describe:我的首页获取用户信息。
     * CreateBy:Tom
     * CreateOn:2016-01-14 10:13:09
     */
    ApiResult getUserInfoForHome(UserInfoEntity userInfoEntity) throws GeneralException;

    /**
     * Code:UI0005
     * Type:UI Method
     * Describe:修改用户信息
     * CreateBy:Tom
     * CreateOn:2016-01-20 10:09:06
     */
    ApiResult modifyUserInfo(ModifyUserInfoParamJsonDTO userInfoJsonDTO) throws GeneralException;

    /**
     * Code:UI0006
     * Type:UI Method
     * Describe:查询房产下用户
     * CreateBy:Tom
     * CreateOn:2016-01-20 05:33:02
     */
    ApiResult getUserInfoList(String userId, String houseId) throws GeneralException;

    /**
     * Code:UI0007
     * Type:UI Method
     * Describe:我的授权列表
     * CreateBy:Tom
     * CreateOn:2016-01-20 06:18:54
     */
    ApiResult getHouseUserList(String userId) throws GeneralException;

    /**
     * Code:UI0008
     * Type:UI Method
     * Describe:删除授权
     * CreateBy:Tom
     * CreateOn:2016-01-20 08:38:02
     */
    ApiResult deleteHouseUserByHouseId(String userId, String houseUserId) throws GeneralException;

    /**
     * Code:UI0009
     * Type:UI Method
     * Describe:新增授权
     * CreateBy:Tom
     * CreateOn:2016-01-20 10:27:49
     */
    ApiResult createHouseUser(CreateHouseUserJsonDTO createHouseUserJsonDTO) throws GeneralException;

    /**
     * Code:UI0010
     * Type:UI Method
     * Describe:新增意见反馈
     * CreateBy:Tom
     * CreateOn:2016-01-21 06:11:16
     */
    ApiResult createFeedback(FeedbackParamJsonDTO feedbackParamJsonDTO,UserInfoEntity userInfoEntity) throws GeneralException;

    /**
     * Code:UI0011
     * Type:UI Method
     * Describe:修改密码
     * CreateBy:Tom
     * CreateOn:2016-01-25 07:20:37
     */
    ApiResult modifyPassword(ModifyPassword modifyPassword) throws GeneralException;

    /**
     * Code:UI0012
     * Type:UI Method
     * Describe:返回指定授权信息
     * CreateBy:Tom
     * CreateOn:2016-02-21 11:35:52
     */
    ApiResult getHouseUserByHouseUserId(String houseUserId);

    /**
     * Code:UI0013
     * Type:UI Method
     * Describe:修改授权信息
     * CreateBy:Tom
     * CreateOn:2016-02-21 01:58:23
     */
    ApiResult modifyHouseUser(ModifyHouseUserJsonDTO houseUserInfo);


    /**
     * 微信用户入会
     * @param qualicationDTO,userInfo,userToken
     * @return
     * CreateBy:chen
     */
       ApiResult wechatAddUser(QualicationDTO qualicationDTO,UserInfoEntity userInfo,UserTokenEntity userToken);

    /**
     * UL0002  微信登录
     * 第一步：用户同意授权，获取code
     2 第二步：通过code换取网页授权access_token
     3 第三步：刷新access_token（如果需要）
     4 第四步：拉取用户信息(需scope为 snsapi_userinfo)  ---本方法实现内容
     5 附：检验授权凭证（access_token）是否有效
     返回系统默认指定token
     * @param code
     * @return
     */
    UserTokenEntity GetLoginByWeChatCode(String code);
    UserTokenEntity GetUserTokenByID(String vestaToken);
    UserInfoEntity GetUserInfoByTokenValue(String  vestatoken);

    /**
     * 业主认证
     * @Author:chen
     * @param qualicationDTO
     * */
    ApiResult  authentication(QualicationDTO qualicationDTO,UserInfoEntity userInfoEntity);

    /**
     * 业主申诉
     * @param appealDTO
     * CreateBy:chen
     * CreateOn:2016-04-12 17:51:23
     * */
    ApiResult appeal(UserInfoEntity userInfo,AppealDTO appealDTO);

    /**
     * APP注册
     * @param qualicationDTO
     * @return
     */
    ApiResult appAddUser(QualicationDTO qualicationDTO);


    /**
     * APP认证
     * @param qualicationDTO
     * @param userInfoEntity
     * @return
     */
    ApiResult  appLandlord(QualicationDTO qualicationDTO,UserInfoEntity userInfoEntity);

    /**
     * APP身份申诉
     * @param appealDTO
     * @return
     */
    ApiResult appAppeal(AppealDTO appealDTO);


    /**
     * 根据手机号取得UserInfoEntity
     * @param mobile
     * @return
     */
    UserInfoEntity getUserInfoByMobile(String mobile);


    /**
     * 根据userId添加真实姓名
     * @param userId
     * @param realname
     * @return
     */
    ApiResult addRealName(String userId,String realname);

    /**
     * 根据userId添加真实姓名
     * @param userId
     * @return
     */
    ApiResult getUserInfoById(String userId);


    /**
     * 根据userId添加真实姓名
     * @param modelMap
     * @return
     */
    ApiResult changeUserInfo(ModelMap modelMap);


    /**
     * 根据userId查询数据
     * @param userId
     * @return
     */
    UserTokenEntity getUserTokenByUserID(String userId);

    /**
     * 通过memberId获取userCRM详情 WeiYangDong 2016-11-22
     */
    UserCRMEntity getByMemberId(String memberId);
}
