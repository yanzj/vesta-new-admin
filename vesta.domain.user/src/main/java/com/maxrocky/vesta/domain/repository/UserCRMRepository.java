package com.maxrocky.vesta.domain.repository;

import com.maxrocky.vesta.domain.model.UserCRMEntity;
import com.maxrocky.vesta.taglib.page.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by liudongxin on 2016/4/12.
 */
public interface UserCRMRepository {
    /**
     * Describe:根据用户Id获取用户信息。
     * CreateBy:Tom
     * CreateOn:2016-01-13 07:25:43
     */
    UserCRMEntity get(String userId);

    /**
     * Describe:根据用户名、密码获取用户信息
     * CreateBy:Tom
     * CreateOn:2016-01-17 01:19:53
     */
    UserCRMEntity getByUserNameAndPassword(String username, String password);

    /**
     * Describe:根据手机查询用户
     * CreateBy:Tom
     * CreateOn:2016-01-17 05:07:23
     */
    UserCRMEntity getByMobile(String mobile);

    /**
     * Describe:创建用户
     * CreateBy:Tom
     * CreateOn:2016-01-17 05:19:23
     */
    void create(UserCRMEntity UserCRMEntity);

    /**
     * Describe:修改用户
     * CreateBy:Tom
     * CreateOn:2016-01-19 10:37:54
     */
    void update(UserCRMEntity UserCRMEntity);

    /**
     * 多条件查询业主
     * @param UserCRMEntity
     * @return
     */
    public List<UserCRMEntity> getByUserCRMEntity(UserCRMEntity UserCRMEntity);

    UserCRMEntity getByUserIdByName(String username);

    UserCRMEntity getByName(String username);

    /**
     * 新增微信用户
     * @return
     */
    UserCRMEntity AddNewWechatUser(String userid);

    UserCRMEntity getUserByNickName(String nickName);

    /**
     * 根据token 获取用户信息
     * @param vestaToken
     * @return
     */
    UserCRMEntity GetUserByToken(String vestaToken);
    boolean existBookofuserIDInRegisterType(String userId, String registerType);
    UserCRMEntity GetUserByUnionCode(String wc, String user_unionid);

    /**
     * CreatedBy:liudongxin
     * Describe:
     * 通过真实姓名和手机号获取用户
     * param name:真实名称
     * param phone:电话
     * ModifyBy:
     */
    UserCRMEntity getUserInfo(String realName, String mobile);

    /**
     * Describe:根据金茂业主Id获取用户信息。
     * CreateBy:liudongxin
     * CreateOn:2016-04-11 15:25:43
     */
    UserCRMEntity getById(String id);

    /**
     * author:chen
     * 业主认证
     * Describe:根据手机号和身份证号进行匹配
     * */
    boolean checkOwner(String idCard);

    /**
     * 根据身份证号和手机号获取信息
     * */
    UserCRMEntity getOwner(String idCard);

    /**
     * Describe:根据会员编号获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    UserCRMEntity getByMemberId(String memberId);

    /**
     * Describe:根据会员id获取信息
     * CreateBy:Tom
     * CreateOn:2016-01-14 09:40:37
     */
    UserCRMEntity getMember(String id, String memberId);

    /**
     * 根据手机号和注册时间获取UserCRM信息
     * 2016—08—03_Wyd
     * @param mobile 手机号
     * @param registerDate 注册时间
     * @return UserCRMEntity
     */
    UserCRMEntity getMemberByMobile(String mobile,String registerDate);

    /**
     * Describe:获取会员个人信息
     * CreateBy:dl
     * CreateOn:2016-01-14 09:40:37
     */
    List<UserCRMEntity> getBaseInfo();

    /**
     * 通过身份证查询用户信息
     * @param idCard
     * @return
     */
    UserCRMEntity getByIdCard(String idCard);

    /**
     * 获取CRM业主数据列表
     * @param params 参数
     * @param webPage 分页
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> getCRMOwnerUserList(Map<String,Object> params, WebPage webPage);

    /**
     * 获取CRM业主房产关系表中房产项目为空的数据列表
     */
    List<String> getHouseUserCrmWithHouseNull();
}
