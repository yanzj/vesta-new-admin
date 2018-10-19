package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.*;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Tom on 2016/1/13 19:25.
 * Describe:用户信息Repository接口
 */
public interface UserInfoRepository {

    /**
     * 保存或更新Entity
     * @param entity
     */
    <T> void saveOrUpdate(T entity);

    /**
     * Describe:根据用户Id获取用户信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 07:25:43
     */
    UserInfoEntity get(String userId);

    /**
     * Describe:根据用户名、密码获取用户信息
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:19:53
     */
    UserInfoEntity getByUserNameAndPassword(String username, String password);

    /**
     * Describe:根据手机查询用户
     * CreateBy:Tom
     * CreateOn:2016-01-17 05:07:23
     */
    UserInfoEntity getByMobile(String mobile);

    /**
     * Describe:创建用户
     * CreateBy:Tom
     * CreateOn:2016-01-17 05:19:23
     */
    void create(UserInfoEntity userInfoEntity);

    /**
     * Describe:修改用户
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:37:54
     */
    void update(UserInfoEntity userInfoEntity);

    /**
     * 判断身份证号是否未认证
     * */
    boolean checkIdCard(String idCard);

    /**
     * 通过证件号码检索用户列表
     * @param idCard 证件号码
     * @return List<UserInfoEntity>
     */
    List<UserInfoEntity> getUserInfoListByIdCard(String idCard);

    /**
     * 多条件查询业主
     * @param userInfoEntity
     * @return
     */
    public List<UserInfoEntity> getByUserInfoEntity(UserInfoEntity userInfoEntity);

    UserInfoEntity getByUserIdByName(String username);

    UserInfoEntity getByName(String username);

    /**
     * 新增微信用户
     * @return
     */
    UserInfoEntity AddNewWechatUser(String userid);

    UserInfoEntity getUserByNickName(String nickName);

    /**
     * 根据token 获取用户信息
     * @param vestaToken
     * @return
     */
    UserInfoEntity GetUserByToken(String vestaToken);
    boolean existBookofuserIDInRegisterType(String userId, String registerType);
    UserInfoEntity GetUserByUnionCode(String wc, String user_unionid);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 通过真实姓名和手机号获取用户
     * param name:真实名称
     * param phone:电话
     * ModifyBy:
     */
    UserInfoEntity getUserInfo(String realName,String mobile);

    /**
     * Describe:根据金茂业主Id获取用户信息。
     * CreateBy:liudongxin
     * CreateOn:2016-04-11 15:25:43
     */
    UserInfoEntity getById(String id);

    /**
     * Describe:获取普通用户信息
     * CreateBy:liudongxin
     * param:userType
     * return
     */
    List<Object[]> getCommonUsers(UserInfoEntity user, WebPage webPage);

    /**
     * Describe:获取业主用户信息
     * CreateBy:liudongxin
     * param:userType
     * return
     */
    List<Object[]> getOwnerUsers(UserInfoEntity user, WebPage webPage, String roleScopes);

    /**
     * Describe:获取同住人用户信息
     * CreateBy:liudongxin
     * param:userType
     * return
     */
    List<Object[]> getHousemateUsers(UserInfoEntity user, WebPage webPage, String roleScopes);

    /**
     * APP 注册判断手机号是否唯一
     * @param mobile
     * @return
     */
    boolean existBookofuserIDInRegisterType(String mobile);

    /**
     * 根据userId 取得用户信息
     * @param UserId
     * @return
     */
    UserInfoEntity getByUserId(String UserId);


    /**
     * 根据token查询UserInfo
     * @param vestaToken
     * @return
     */
    UserInfoEntity GetUserInfoByToken(String vestaToken);

    /**
     * 男性用户统计
     * param date
     * return
     */
    Integer getForMale(String date);

    /**
     * 女性用户统计
     * param date
     * return
     */
    Integer getForFeMale(String date);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 微信和app用户绑定，清除微信用户信息
    */
    void delWCUser(UserInfoEntity user);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 根据vestaToken获取对应vestaTokenEntity
    */
    UserTokenEntity getUserTokenByVestaToken(String vestaToken);

    /**.
    *  @Author: shanshan
    *  @Date:
    *  @Description: 更新UserTokenEntity
    */
    void updateTokenEntity(UserTokenEntity userTokenEntity);

    /**
     * 验证用户昵称的的唯一性
     * @param userId 用户ID
     * @param nickName 用户名
     * @return UserInfoEntity
     */
    UserInfoEntity ckUserByNickName(String userId,String nickName);


    /**
     * 根据账号检索分类人员信息
     */
    UserPropertyStaffEntity getUserByUserName(String userName);

    /**
     * 根据账号检索人员信息
     */
    UserInformationEntity getUserInformationEntity(String userName);

    /**
     * 获取业主认证信息列表
     * @param params 参数
     * @param webPage 分页
     * @return List<OwnerAuthenticationEntity>
     */
    List<OwnerAuthenticationEntity> getOwnerAuthenticationList(Map<String,Object> params, WebPage webPage);

    /**
     * 获取业主认证信息
     * @param id 主键ID
     * @return OwnerAuthenticationEntity
     */
    OwnerAuthenticationEntity getOwnerAuthenticationById(String id);

    /**
     * 获取业主申诉信息列表
     * @param params 参数
     * @param webPage 分页
     * @return List<OwnerAppealEntity>
     */
    List<OwnerAppealEntity> getOwnerAppealList(Map<String,Object> params, WebPage webPage);

    /**
     * 获取业主申诉信息
     * @param id 主键ID
     * @return OwnerAppealEntity
     */
    OwnerAppealEntity getOwnerAppealById(String id);

    /**
     * 通过业主姓名和证件号码检索业主申诉信息列表
     * @param ownerName 业主姓名
     * @param idCard 证件号码
     * @return List<OwnerAppealEntity>
     */
    List<OwnerAppealEntity> getOwnerAppealByOwner(String ownerName,String idCard);

    /**
     * 通过userId获取用户微信登录信息
     * @param userId 用户ID
     * @param loginType 登录类型(WC)
     * @return UserLoginBookEntity
     */
    UserLoginBookEntity getUserLoginBookByUserId(String userId, String loginType);

    /**
     * 通过memberId获取UserLoginBook信息
     * @param memberId 业主ID
     * @param loginType 登录类型
     * @return UserLoginBookEntity
     */
    UserLoginBookEntity getUserLoginBookByMemberId(String memberId,String loginType);

    /**
     * 按照功能点信息查询 绑定的角色信息
     * */
    List<String> getRoleByFunction(String functionId);
}
