package com.maxrocky.vesta.domain.model;

import com.maxrocky.vesta.utility.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by liudongxin on 2016/4/12.
 * 第三方用户信息实体类
 */
@Entity
@Table(name = "user_crm")
public class UserCRMEntity {
    /* 用户类型 */
    public final static String USER_TYPE_VISITOR = "1";//游客
    public final static String USER_TYPE_NORMAL = "2";//普通
    public final static String USER_TYPE_OWNER = "3";//业主
    public final static String USER_TYPE_FAMILY = "4";//家属
    public final static String USER_TYPE_TENANT = "5";//租户

    /* 用户状态 */
    public final static int USER_STATE_NORMAL = 1;//正常

    private String userId;//主键Id
    private String userName;//用户名
    private String memberId;//会员编号
    private String password;//登录密码
    private String mobile;//手机
    private String nickName;//昵称
    private String realName;//真实姓名
    private String sex;//性别
    private Date birthday;//生日
    private String idCard;//证件号码
    private String idType;//证件类型
    private String logo;//头像
    private String marriage;//婚姻状况
    private Integer userState;//用户状态
    private String userType;//用户类型
    private Date registerDate;//注册时间
    private String createBy;//创建人
    private Date createOn;//创建时间
    private String modifyBy;//修改人
    private Date modifyOn;//修改时间
    private String id;//金茂项目：业主id
    private String nationality;//国籍
    private String nativesPlace;//籍贯
    private String education;//教育程度
    private String state;//酒店会员标识：0为会员，1为非会员
    private String hobby;//兴趣爱好
    private String englishName;//英文名字
    private String occupation;//职业
    private String jobUnit;//工作单位
    private String incomeLevel;//会员收入
    private String companyType;//单位类别

    public UserCRMEntity() {
    }
    /*private String houseId;//房间id
    private String houseOwnerId;//房主用户id
    private String houseNum;//房间编号*/

    @Id
    @Column(name = "USER_ID",nullable = false, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "MEMBER_ID",nullable = true, length = 50)
    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Basic
    @Column(name = "USER_NAME",nullable = true, length = 50)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "PASSWORD",nullable = true, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "MOBILE",nullable = false, length = 11)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "NICK_NAME",nullable = true, length = 20)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Basic
    @Column(name = "REAL_NAME", length = 60)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Basic
    @Column(name = "SEX",nullable = true)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "BIRTHDAY")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "ID_CARD", length = 30)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "ID_TYPE", length = 10)
    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    @Basic
    @Column(name = "LOGO", length = 300)
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "MARRIAGE", length = 10)
    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    @Basic
    @Column(name = "USER_STATE")
    public Integer getUserState() {
        return userState;
    }

    public void setUserState(Integer userState) {
        this.userState = userState;
    }

    @Basic
    @Column(name = "USER_TYPE", length = 10)
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Basic
    @Column(name = "REGISTER_DATE")
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Basic
    @Column(name = "CREATE_BY", nullable = true, length = 50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "CREATE_ON", nullable = true)
    public Date getCreateOn() {
        return createOn;
    }

    public void setCreateOn(Date createOn) {
        this.createOn = createOn;
    }

    @Basic
    @Column(name = "MODIFY_BY", nullable = true, length = 50)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Basic
    @Column(name = "MODIFY_ON", nullable = true)
    public Date getModifyOn() {
        return modifyOn;
    }

    public void setModifyOn(Date modifyOn) {
        this.modifyOn = modifyOn;
    }

    @Basic
    @Column(name = "ID", nullable = true, length = 50)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NATIONALITY", nullable = true, length = 50)
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Basic
    @Column(name = "NATIVES_PLACE", nullable = true, length = 50)
    public String getNativesPlace() {
        return nativesPlace;
    }

    public void setNativesPlace(String nativesPlace) {
        this.nativesPlace = nativesPlace;
    }

    @Basic
    @Column(name = "EDUCATION", nullable = true, length = 50)
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Basic
    @Column(name = "STATE", nullable = true, length = 2)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    @Basic
    @Column(name = "HOBBY", nullable = true)
    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Basic
    @Column(name = "ENGLISH_NAME", nullable = true, length = 50)
    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    @Basic
    @Column(name = "OCCUPATION",nullable = true,length = 30)
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Basic
    @Column(name = "JOB_UNIT",nullable = true, length = 100)
    public String getJobUnit() {
        return jobUnit;
    }

    public void setJobUnit(String jobUnit) {
        this.jobUnit = jobUnit;
    }

    @Basic
    @Column(name = "INCOME_LEVEL",nullable = true, length = 100)
    public String getIncomeLevel() {
        return incomeLevel;
    }

    public void setIncomeLevel(String incomeLevel) {
        this.incomeLevel = incomeLevel;
    }

    @Basic
    @Column(name = "COMPANY_TYPE",nullable = true, length = 100)
    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }


/*@Basic
    @Column(name = "HOUSE_ID", nullable = true, length = 50)
    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    @Basic
    @Column(name = "HOUSEOWNER_ID", nullable = true, length = 50)
    public String getHouseOwnerId() {
        return houseOwnerId;
    }

    public void setHouseOwnerId(String houseOwnerId) {
        this.houseOwnerId = houseOwnerId;
    }

    @Basic
    @Column(name = "HOUSE_NUM", nullable = true, length = 50)
    public String getHouseNum() {
        return houseNum;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }*/
}
